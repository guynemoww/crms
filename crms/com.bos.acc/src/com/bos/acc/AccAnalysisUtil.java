/**
 * 
 */
package com.bos.acc;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.util.DateUtils;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.bos.utp.tools.ChangeUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;
import com.bos.utp.tools.ChangeUtil;

/**
 * @author C_ture
 * @date 2014-12-29 11:19:22
 *
 */
@Bizlet("财务分析工具类")
public class AccAnalysisUtil {

	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * @param args
	 * @author C_ture
	 */
	/**
	 * @param paramMap 
	 * 财务分析指标计算
	 * 返回计算结果存入分析指标数据表
	 * @param financeId
	 * @param partyId 
	 * @param allValueMap 
	 */
	@Bizlet("财报分析指标计算")
	public static void runAnalysisIndex(HashMap<String, Object> allValueMap,String finanysisDetailId) throws Exception {

		String customerTypeCd = (String) allValueMap.get("CUSTOMER_TYPE_CD");
		String partyId = (String) allValueMap.get("PARTY_ID");
		//根据规则分类id及规则类别调规则进行校验
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		String pid = "301";//企业财报分析 TODO
		typemap.put("pid", pid);
		typemap.put("rtype", "06"); //规则类别 06 指标计算

		//获取上一期的科目值
		String financeTypeCd = (String) allValueMap.get("FINANCE_TYPE_CD");
		Date deadlineDate = (Date) allValueMap.get("FINANCE_DEADLINE");
		Map<String, Object> map = new HashMap<String, Object>();
		String caliberCd = (String) allValueMap.get("CALIBER_CD");
		map.put("FINANCE_TYPE_CD", financeTypeCd);//模板类型 1-年报,2-半年报，3-季报，4-月报，5-类年报
		map.put("FINANCE_DEADLINE", deadlineDate);//财报日期
		map.put("PARTY_ID", partyId);//客户id
		map.put("CUSTOMER_TYPE_CD", customerTypeCd);//财报类别
		map.put("CALIBER_CD", caliberCd);//财报口径

		HashMap<String, Object> financeMap = getEverFinanceData(map);
		allValueMap.putAll(financeMap);

		//按规则公式计算
		HashMap<String, HashMap<String, Object>> resultMap = new DecisionUtil()
				.execRuleByTypeId(typemap, allValueMap);
		Set<String> keySet = resultMap.keySet();
		if (null == resultMap || resultMap.size() == 0) {
			throw new Exception("当前财报指标公式未配置生效或有误，请联系管理员处理！");
		}
		
		//删除已有方案明细指标
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		List<Map<String, Object>> deletelList = new ArrayList<Map<String, Object>>();
		deleteMap.put("finanysis_detail_id", finanysisDetailId);//方案明细id
		deletelList.add(deleteMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.deleteAnalyDataByfinanysisDetailId", 
				deletelList.toArray(new HashMap[deletelList.size()]));
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (String key : keySet) {
			HashMap<String, Object> reMap = resultMap.get(key);
			Set<String> rekeySet = reMap.keySet();
			String indexCd = "";
			Object indexValue = null;
			String stringType = "";
			for (String rekey : rekeySet) {
				Object reMsg = reMap.get(rekey);
				if (rekey.equals("msg")
						&&!reMsg.toString().contains("by zero")) {
					throw new Exception("异常：" + reMsg);
				} else if (rekey.equals("msg")
						&& reMsg.toString().contains("by zero")) {
					stringType = "NaN";
				}else if (rekey.equals("result")
						&& !reMsg.toString().equals("NaN")
						&& !reMsg.toString().contains("Infinity")) {
					indexValue = reMsg;
				} else if (rekey.equals("result")
						&& reMsg.toString().equals("NaN")) {
					stringType = "NaN";
				} else if (rekey.equals("result")
						&& reMsg.toString().contains("Infinity")) {
					stringType = "Infinity";
				} else if (rekey.equals("rind") && !reMsg.equals("")) {
					indexCd = reMsg.toString();
				}
			}
			String dataId = GitUtil.genUUIDString();
			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("INDEX_DATA_ID", dataId);
			dataMap.put("FINANYSIS_DETAIL_ID", finanysisDetailId);
			dataMap.put("INDEX_CD", indexCd);//指标代码
			dataMap.put("STRING_TYPE", stringType);
			dataMap.put("CREATE_TIME", GitUtil.getBusiDate());
			dataMap.put("UPDATE_TIME", GitUtil.getBusiDate());
			if (!stringType.equals("")) {
				dataMap.put("INDEX_VALUE_DATA_TYPE", "null");//指标值
			} else {
				if("01".equals(indexValue.toString()) ||"02".equals(indexValue.toString()) 
						||"03".equals(indexValue.toString())||"04".equals(indexValue.toString())
						||"05".equals(indexValue.toString())||"06".equals(indexValue.toString())){
					dataMap.put("STRING_TYPE", indexValue.toString());//标识
					dataMap.put("INDEX_VALUE_DATA_TYPE", "null");//指标值
				}else{
				dataMap.put("INDEX_VALUE_DATA_TYPE",(new BigDecimal(indexValue.toString())).setScale(6,BigDecimal.ROUND_HALF_UP));//指标值
				}
			}
			dataList.add(dataMap);
		}
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAnalyIndexData", dataList
						.toArray(new HashMap[dataList.size()]));

	}
	
	/**
	 * @param paramMap 
	 * 财务粉饰预警计算
	 * 返回计算结果存入预警结果表
	 * @param financeId
	 * @param partyId 
	 * @param allValueMap 
	 */
	@Bizlet("财报粉饰预警指标计算")
	public static void runAlertIndex(HashMap<String, Object> allValueMap,String infoId) throws Exception {

		String customerTypeCd = (String) allValueMap.get("CUSTOMER_TYPE_CD");
		String partyId = (String) allValueMap.get("PARTY_ID");
		//根据规则分类id及规则类别调规则进行校验
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		String pid = "401";//企业粉饰指标计算
		typemap.put("pid", pid);
		typemap.put("rtype", "06"); //规则类别 06 指标计算

		//获取上一期的科目值
		String financeTypeCd = (String) allValueMap.get("FINANCE_TYPE_CD");
		Date deadlineDate = (Date) allValueMap.get("FINANCE_DEADLINE");
		Map<String, Object> map = new HashMap<String, Object>();
		String caliberCd = (String) allValueMap.get("CALIBER_CD");
		map.put("FINANCE_TYPE_CD", financeTypeCd);//模板类型 1-年报,2-半年报，3-季报，4-月报，5-类年报
		map.put("FINANCE_DEADLINE", deadlineDate);//财报日期
		map.put("PARTY_ID", partyId);//客户id
		map.put("CUSTOMER_TYPE_CD", customerTypeCd);//财报类别
		map.put("CALIBER_CD", caliberCd);//财报口径

		HashMap<String, Object> financeMap = getEverFinanceData(map);
		allValueMap.putAll(financeMap);

		//按规则公式计算预警指标
		HashMap<String, HashMap<String, Object>> resultMap = new DecisionUtil()
				.execRuleByTypeId(typemap, allValueMap);
		Set<String> keySet = resultMap.keySet();
		if (null == resultMap || resultMap.size() == 0) {
			throw new Exception("当前财报指标公式未配置生效或有误，请联系管理员处理！");
		}
		
		//预警指标计算结果集合
		HashMap<String, Object>  sMap = new HashMap<String, Object>();
		for (String key : keySet) {
			HashMap<String, Object> reMap = resultMap.get(key);
			Set<String> rekeySet = reMap.keySet();
			String indexCd = "";
			Object indexValue = null;
			String stringType = "";
			for (String rekey : rekeySet) {
				Object reMsg = reMap.get(rekey);
				if (rekey.equals("msg")
						&&!reMsg.toString().contains("by zero")) {
					throw new Exception("异常：" + reMsg);
				} else if (rekey.equals("msg")
						&& reMsg.toString().contains("by zero")) {
					stringType = "NaN";
				}else if (rekey.equals("result")
						&& !reMsg.toString().equals("NaN")
						&& !reMsg.toString().contains("Infinity")) {
					indexValue = reMsg;
				} else if (rekey.equals("result")
						&& reMsg.toString().equals("NaN")) {
					stringType = "NaN";
				} else if (rekey.equals("result")
						&& reMsg.toString().contains("Infinity")) {
					stringType = "Infinity";
				} else if (rekey.equals("rind") && !reMsg.equals("")) {
					indexCd = reMsg.toString();
				}
			}
			if("NaN".equals(stringType)){
				sMap.put(indexCd,null);
			}else{
				sMap.put(indexCd,(new BigDecimal(indexValue.toString())).setScale(6,BigDecimal.ROUND_HALF_UP));//指标值
			}
		}
		//按规则公式计算预警指标结果
		HashMap<String, Object> tMap = new HashMap<String, Object>();
		String rPid = "501";//企业粉饰预警
		tMap.put("pid", rPid);
		tMap.put("rtype", "06"); //规则类别 06 指标计算
		HashMap<String, HashMap<String, Object>> rMap = new DecisionUtil()
				.execRuleByTypeId(tMap, sMap);

		if (null == rMap || rMap.size() == 0) {
			throw new Exception("当前财报预警规则未配置生效或有误，请联系管理员处理！");
		}
		//删除已有预警指标
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		List<Map<String, Object>> deletelList = new ArrayList<Map<String, Object>>();
		deleteMap.put("info_Id", infoId);//方案明细id
		deletelList.add(deleteMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.deleteAlertResultByinfoId", 
				deletelList.toArray(new HashMap[deletelList.size()]));
		
		Set<String> rkeySet = rMap.keySet();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (String key : rkeySet) {
			HashMap<String, Object> reMap = rMap.get(key);
			Set<String> rekeySet = reMap.keySet();
			String indexCd = "";
			Object indexValue = null;
			String stringType = "";
			for (String rekey : rekeySet) {
				Object reMsg = reMap.get(rekey);
				if (rekey.equals("msg")
						&&!reMsg.toString().contains("by zero")) {
					throw new Exception("异常：" + reMsg);
				}else if (rekey.equals("result")
						&& !reMsg.toString().equals("NaN")
						&& !reMsg.toString().contains("Infinity")) {
					indexValue = reMsg;
				}else if (rekey.equals("rind") && !reMsg.equals("")) {
					indexCd = reMsg.toString();
				}
			}
			String dataId = GitUtil.genUUIDString();
			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("RESULT_ID", dataId);
			dataMap.put("INFO_ID", infoId);
			dataMap.put("INDEX_CODE", indexCd);//指标码
			dataMap.put("INDEX_SCORE", sMap.get(indexCd));//预警指标值
			if (!stringType.equals("")) {
				dataMap.put("ALERT_DESC", "异常数据");//预警结果
			} else {
				dataMap.put("ALERT_DESC", indexValue.toString());//预警结果
			}
			dataList.add(dataMap);
		}
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAlertResult", dataList
						.toArray(new HashMap[dataList.size()]));
	}
	
	/**
	 * 获取上一期财报科目值 
	 * @param map 包括FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报
	 				FINANCE_DEADLINE 财报日期
	 				PARTY_ID 客户id
	 				CUSTOMER_TYPE_CD 财报类别
	 * @return financeDataMap 包括PROJECT_CD 科目代码 PROJECT_VALUE 科目值
	 */
	@Bizlet("返回上一期报表科目")
	public static  HashMap<String, Object> getEverFinanceData(
			Map<String, Object> map) {
		HashMap<String, Object> financeData = new HashMap<String, Object>();
		map.put("FINANCE_STATUS_CD", "02");//生效

		Date financeDeadline = (Date)map.get("FINANCE_DEADLINE");//本期日期
		String financeTypeCd = (String)map.get("FINANCE_TYPE_CD");//类型
		Date eFinanceDate = null;//上期
		if("1".equals(financeTypeCd) || "5".equals(financeTypeCd)){
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,-1,0,0);//上一年
		}else if ("2".equals(financeTypeCd)){
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,0,-6,0);//上半年
		}else if ("3".equals(financeTypeCd)){
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,0,-3,0);//上一季
		}else if ("4".equals(financeTypeCd)){
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,0,-1,0);//上一月
		}
		map.put("eFinanceDate",ChangeUtil.date2LastDayOfMonth(eFinanceDate));//传上期财报日期
		Object[] financeDatas = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectEverfinanceDatas", map);
		if (null == financeDatas || financeDatas.length < 1) {
			return financeData;
		}
		for (int i = 0; i < financeDatas.length; i++) {
			Map fmap = (Map) financeDatas[i];
			if(fmap.get("PROJECT_VALUE") == null){
				financeData.put("e" +(String) fmap.get("PROJECT_CD"),null);
			}else{
			financeData.put("e" + (String) fmap.get("PROJECT_CD"), (BigDecimal)(fmap.get("PROJECT_VALUE")));//上期数据科目代码前加"e"
			}
		}
		return financeData;

	}
	
	/**
	 * 获取财报分析数据 
	 * @param map 
	 * @return IndexDataMap 包括PROJECT_CD 科目代码 PROJECT_VALUE 科目值
	 */
	@Bizlet("获取财报分析数据")
	public static HashMap<String, Object> getAnyIndexData(
			Map<String, Object> map) {
		HashMap<String, Object> IndexDataMap = new HashMap<String, Object>();

		Object[] analysisDetail = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectAnalysisDetail", map);
		if (null == analysisDetail || analysisDetail.length < 1) {
			return IndexDataMap;
		}
		int index=0;
		if(analysisDetail.length==1){
			index=2;
			IndexDataMap.put("a"+"FINANCE_DEADLINE", "");
			IndexDataMap.put("b"+"FINANCE_DEADLINE", "");
		}
		if (analysisDetail.length==2){
			index=1;
			IndexDataMap.put("a"+"FINANCE_DEADLINE", "");
		}
		
		for (int i = 0; i < analysisDetail.length; i++) {
			Map fmap = (Map) analysisDetail[i];
			//fmap.get("FINANYSIS_DETAIL_ID").toString();
			String flag ="";
			switch(index){
			case 0:flag="a";
			break;
			case 1:flag="b";
			break;
			case 2:flag="c";
			break;
			}
			index=index+1;
			IndexDataMap.put(flag+"FINANCE_DEADLINE", simpleDateFormat.format((Date)fmap.get("FINANCE_DEADLINE")));
			Object[] indexData = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.acc.exportExcel.selectAnalysisIndexData", fmap);
			for (int j = 0; j < indexData.length; j++) {
				Map imap = (Map) indexData[j];
			if(imap.get("INDEX_VALUE_DATA_TYPE") == null){
				IndexDataMap.put(flag +(String) imap.get("INDEX_CD"),null);
			}else{
				IndexDataMap.put(flag + (String) imap.get("INDEX_CD"), (BigDecimal)(imap.get("INDEX_VALUE_DATA_TYPE")));
			}
			}
		}
		return IndexDataMap;

	}
	
	@Bizlet("校验所选财报是否连续")
	public static String verifyContinuity(String financeId1, String financeId2, String financeId3) {
		String msg = "";
		HashMap<String, Object> financeIds = new HashMap<String, Object>();
		if(StringUtil.isEmpty(financeId1)&&StringUtil.isEmpty(financeId2)&&StringUtil.isEmpty(financeId3)){
			return msg;
		}else{
			financeIds.put("FINANCEID1", financeId1);
			financeIds.put("FINANCEID2", financeId2);
			if(StringUtil.isEmpty(financeId3)){
				Object[] financeDates = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.acc.exportExcel.selectFinanceDates1", financeIds);
				Map financeDate1 =(Map) financeDates[0];
				Map financeDate2 =(Map) financeDates[1];
				String financeTypeCd=(String)financeDate1.get("FINANCE_TYPE_CD");
				Date financeDeadline1=(Date)financeDate1.get("FINANCE_DEADLINE");
				Date financeDeadline2=(Date)financeDate2.get("FINANCE_DEADLINE");
				if(financeTypeCd.equals("1")||financeTypeCd.equals("5")){//年报、类年报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, -1, 0, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0){
						msg="请选择连续两年的年报";
					}
				}else if(financeTypeCd.equals("2")){//半年报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -6, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0){
						msg="请选择连续两个半年的半年报";
					}
				}else if(financeTypeCd.equals("3")){//季报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -3, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0){
						msg="请选择连续两个季度的季报";
					}
				}else{//月报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -1, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0){
						msg="请选择连续两个月的月报";
					}
				}
			}else{
				financeIds.put("FINANCEID3", financeId3);
				Object[] financeDates = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.acc.exportExcel.selectFinanceDates", financeIds);
				Map financeDate1 =(Map) financeDates[0];
				Map financeDate2 =(Map) financeDates[1];
				Map financeDate3 =(Map) financeDates[2];
				String financeTypeCd=(String)financeDate1.get("FINANCE_TYPE_CD");
				Date financeDeadline1=(Date)financeDate1.get("FINANCE_DEADLINE");
				Date financeDeadline2=(Date)financeDate2.get("FINANCE_DEADLINE");
				Date financeDeadline3=(Date)financeDate3.get("FINANCE_DEADLINE");
				if(financeTypeCd.equals("1")||financeTypeCd.equals("5")){//年报、类年报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, -1, 0, 0));
					Date  newfinanceDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline2, -1, 0, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0||newfinanceDeadline.compareTo(financeDeadline3)!=0){
						msg="请选择连续三年的年报";
					}
				}else if(financeTypeCd.equals("2")){//半年报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -6, 0));
					Date  newfinanceDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline2, 0, -6, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0||newfinanceDeadline.compareTo(financeDeadline3)!=0){
						msg="请选择连续三个半年的半年报";
					}
				}else if(financeTypeCd.equals("3")){//季报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -3, 0));
					Date  newfinanceDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline2, 0, -3, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0||newfinanceDeadline.compareTo(financeDeadline3)!=0){
						msg="请选择连续三个季度的季报";
					}
				}else{//月报
					Date  financeDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline1, 0, -1, 0));
					Date  newfinanceDeadline=ChangeUtil.date2LastDayOfMonth(ChangeUtil.dateAdd(financeDeadline2, 0, -1, 0));
					if(financeDeadline.compareTo(financeDeadline2)!=0||newfinanceDeadline.compareTo(financeDeadline3)!=0){
						msg="请选择连续三个月的月报";
					}
				}
			}
			
			
			return msg;
		}
	}
	
	/**
	 * 获取财报预警数据 
	 * @param map 
	 * @return IndexDataMap 包括PROJECT_CD 科目代码 PROJECT_VALUE 科目值
	 */
	@Bizlet("获取财报预警数据")
	public static HashMap<String, Object> getAlertData(
			Map<String, Object> map) {
		HashMap<String, Object> IndexDataMap = new HashMap<String, Object>();

		Object[] alertInfo = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectAlertInfo", map);
		if (null == alertInfo || alertInfo.length < 1) {
			return IndexDataMap;
		}
		Map infoMap = (Map) alertInfo[0];
		IndexDataMap.put("CREATE_TIME", simpleDateFormat.format((Date)infoMap.get("CREATE_TIME")));//预警日期
		
		Object[] alertResult = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectAlertData", infoMap);

		for (int i = 0; i < alertResult.length; i++) {
			Map rMap = (Map) alertResult[i];
			//预警指标值
			if(rMap.get("INDEX_SCORE") == null){
				IndexDataMap.put((String) rMap.get("INDEX_CODE"),null);
			}else{
				IndexDataMap.put((String) rMap.get("INDEX_CODE"), (BigDecimal)(rMap.get("INDEX_SCORE")));
			}
			//预警结果
			if(rMap.get("ALERT_DESC") == null){
				IndexDataMap.put("a"+(String) rMap.get("INDEX_CODE"),null);
			}else{
				IndexDataMap.put("a"+(String) rMap.get("INDEX_CODE"), (String)(rMap.get("ALERT_DESC")));
			}
		}
		return IndexDataMap;

	}
	

}
