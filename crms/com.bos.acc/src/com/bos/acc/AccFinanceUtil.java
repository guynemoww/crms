/**
 * 
 */
package com.bos.acc;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.bos.utp.tools.ChangeUtil;
import commonj.sdo.DataObject;

/**
 * @author C_ture
 * @date 2014-04-16 10:44:06
 *
 */
@Bizlet("财报工具类")
public class AccFinanceUtil {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);

	/**
	 * @param paramMap 包括
	 * 1、传入客户财报id-FINANCE_ID,返回指定财报指标； 
	 * 2、传入客户id-PARTY_ID,财报类型-FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报；
	 * 财报截止日期-FINANCE_DEADLINE；返回客户指定类型的最新或指定日期财报指标
	 * 为满足取即期财报新增参数取值范围-DATE_TYPE：1-最近一期（即期），2-指定日期
	 * @return Object[] (INDEX_CD-指标代码,INDEX_VALUE_DATA_TYPE-指标值(数值类型),STRING_TYPE-指标值(字符类型))
	 * STRING_TYPE为空（NullOrEmpty）时取INDEX_VALUE_DATA_TYPE,否则取STRING_TYPE
	 * @author C_ture
	 */
	@Bizlet("获取财报数据（指标)")
	public static Object[] getIndexValue(HashMap<String, Object> paramMap)  throws Exception {
		
		
		String financeId = paramMap.get("FINANCE_ID") != null ? paramMap
				.get("FINANCE_ID").toString() : "";
		String partyId = paramMap.get("PARTY_ID") != null ? paramMap
				.get("PARTY_ID").toString() : "";
		String dateType = paramMap.get("DATE_TYPE") != null ? paramMap
						.get("DATE_TYPE").toString() : "";

		Object[] financeDatas = null;
		if(!financeId.equals("")){
			financeDatas = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.acc.exportExcel.selectIndexValueByFinanceId", paramMap);
			
		}else if(!partyId.equals("")){//根据客户id、财报类型financeTypeCd、财报日期取财报指标

			paramMap.put("FINANCE_STATUS_CD", "02");//生效 只能查生效的财报
			String financeTypeCd = paramMap.get("FINANCE_TYPE_CD") != null ? paramMap
					.get("FINANCE_TYPE_CD").toString() : "";
			Date financeDeadline = paramMap.get("FINANCE_DEADLINE") != null ? (Date)paramMap
					.get("FINANCE_DEADLINE"): null;
			if(financeTypeCd.equals("") ){
				throw new Exception("传入的财报类型或财报日期为空，无法找不到对应的财报记录！");
			}
			if(dateType.equals("1")){//最近一期（即期）
				financeDatas = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.acc.exportExcel.selectIndexValueByPartyId", paramMap);
			}else if (dateType.equals("2")){//2-指定日期
				if (financeDeadline==null){
					throw new Exception("传入的财报日期为空，无法找不到对应的财报记录！");
				}
				financeDatas = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.acc.exportExcel.selectIndexValueByPartyIds", paramMap);
			}
			if (null == financeDatas || financeDatas.length < 1) {
				return financeDatas;
			}
		}else{
			throw new Exception("传入的财报id和客户id都为空，无法找不到对应的财报记录！");
		}

		return financeDatas;
		
	}

	/**
	 * @param paramMap 包括
	 * 1、传入客户财报id-FINANCE_ID,返回指定财报信息； 
	 * 2、传入客户id-PARTY_ID,财报类型-FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报；
	 * 财报截止日期-FINANCE_DEADLINE(没有财报截止日期可以传入营业日期)；返回客户指定类型的最新或指定日期财报科目
	 * 报表状态-FINANCE_STATUS_CD 01-未生效，02-生效（不给则查所有的）；
	 * @return financeData 传入financeId的返回结果包括财报附注信息+所有科目(科目代码不带"c")、 
	 * 			传入PARTY_ID的返回结果包括所有科目（返回的科目代码前加"c"）
	 * @author C_ture
	 */
	@Bizlet("获取财报数据（附注+科目）")
	public static HashMap getProjectValue(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> financeData = new HashMap<String, Object>();
		
		String financeId = paramMap.get("financeId") != null ? paramMap
				.get("financeId").toString() : "";
		String partyId = paramMap.get("PARTY_ID") != null ? paramMap
				.get("PARTY_ID").toString() : "";
				//根据financeId取财报信息
				if(!financeId.equals("")){
//					附注信息
					Object[] financeDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.acc.exportExcel.selectFinanceDatasByFinanceId", paramMap);
					if (null == financeDatas || financeDatas.length != 1) {
						throw new Exception("传入的财报id找不到对应的财报记录！");
					}
					Map fmap = (Map) financeDatas[0];
					financeData.putAll(fmap);
					//财报科目
					Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.acc.exportExcel.selectProjectDatasByFinanceId", paramMap);
					if (null == ProjectDatas || ProjectDatas.length < 1) {
						throw new Exception("传入的财报id找不到对应的财报科目，请确认是否已经录入科目！");
					}
					for (int i = 0; i < ProjectDatas.length; i++) {
						Map pmap = (Map) ProjectDatas[i];
						if(pmap.get("PROJECT_VALUE") == null){
							financeData.put((String) pmap.get("PROJECT_CD"),null);
						}else{
						financeData.put((String) pmap.get("PROJECT_CD"), (new BigDecimal(pmap.get("PROJECT_VALUE").toString()))
								.setScale(6,BigDecimal.ROUND_HALF_UP));
						}
					}			
				}else if(!partyId.equals("")){//根据客户id、财报类型financeTypeCd、财报日期取财报科目
					String financeTypeCd = paramMap.get("FINANCE_TYPE_CD") != null ? paramMap
							.get("FINANCE_TYPE_CD").toString() : "";
					Date financeDeadline = paramMap.get("FINANCE_DEADLINE") != null ? (Date)paramMap
							.get("FINANCE_DEADLINE"): null;
					if(financeTypeCd.equals("") || financeDeadline==null){
						throw new Exception("传入的财报类型或财报日期为空，无法找不到对应的财报记录！");
					}
					//财报科目
					Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.acc.exportExcel.selectProjectDatasByPartyId", paramMap);
					for (int i = 0; i < ProjectDatas.length; i++) {
						Map pmap = (Map) ProjectDatas[i];
						if(pmap.get("PROJECT_VALUE") == null){
							financeData.put((String) pmap.get("PROJECT_CD"),null);
						}else{
						financeData.put("c" +(String) pmap.get("PROJECT_CD"), (ChangeUtil
								.formatDouble(new Double(pmap.get("PROJECT_VALUE").toString()), 2)));//为防止map的key为全数字,科目代码前加字母“c”
						}
					}
				}else{
					throw new Exception("传入的财报id和客户id都为空，无法找不到对应的财报记录！");
				}

		return financeData;
 
	}
	
	/**
	 * @param paramMap 包括
	 * 1、传入客户财报id-FINANCE_ID,返回指定财报信息；
	 *  @return financeData 传入financeId的返回结果包括财报附注信息+所有科目(科目代码不带"c")、 
	 * @author C_ture
	 */
	@Bizlet("获取财报数据（期初、累计值）")
	public static HashMap getPreValue(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> preFinData = new HashMap<String, Object>();
		
		String financeId = paramMap.get("financeId") != null ? paramMap
				.get("financeId").toString() : "";
				if(!financeId.equals("")){
					//财报科目
					Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.acc.exportExcel.selectPreDatasByFinanceId", paramMap);
					if (null == ProjectDatas || ProjectDatas.length < 1) {
						throw new Exception("传入的财报id找不到对应的财报科目，请确认是否已经录入科目！");
					}
					for (int i = 0; i < ProjectDatas.length; i++) {
						Map pmap = (Map) ProjectDatas[i];
						if(pmap.get("PRE_TOTAL_VALUE") == null){
							preFinData.put((String) pmap.get("PROJECT_CD"),null);
						}else{
							preFinData.put((String) pmap.get("PROJECT_CD"), (new BigDecimal(pmap.get("PRE_TOTAL_VALUE").toString()))
								.setScale(6,BigDecimal.ROUND_HALF_UP));
						}
					}			
				}else{
					throw new Exception("传入的财报id为空！");
				}

		return preFinData;
 
	}
	
	/**
	 * 指标初始化获取财报id方法(暂未使用)
	 * @param paramMap 包括
	 * @return ProjectDatas financeId 财报id reportType 财报类别 
	 * @author C_ture
	 */
	@Bizlet("根据年份获取财报id")
	public static Object[] getRecentlyFinance(HashMap<String, Object> paramMap) throws Exception {

		//初始化暂时只针对2014年三年内数据
		paramMap.put("year1", "2014");
		paramMap.put("year2", "2013");
		paramMap.put("year3", "2012");
		paramMap.put("FINANCE_TYPE_CD", "1");//暂时只初始化年报
		
		Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectRecentlyFinanceByYear", paramMap);

		return ProjectDatas;
	}
	
	/**
	 * 获取近三年日期(考虑性能暂时只取一年)
	 * @param paramDate 
	 * @return paramMap year1 year2 year3
	 * @author C_ture
	 */
	@Bizlet("获取近三年年末日期")
	public static HashMap getRecentlyDate(Date paramDate) throws Exception {

		try{
			
	
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(paramDate.getMonth()!=11){
			throw new Exception("目前只支持初始化年报指标，月份请选择十二！");
		}
		//获取该日期月末日期
		Date tempDate =ChangeUtil.date2LastDayOfMonth(paramDate);
		
		Date year1 = tempDate;
		Date year2 = ChangeUtil.dateAdd(tempDate, -1, 0, 0);//近一年
		Date year3 = ChangeUtil.dateAdd(tempDate, -2, 0, 0);//近两年
		
		paramMap.put("year1", year1);
		paramMap.put("year2", year2);
		paramMap.put("year3", year3);
		
		return paramMap;	
		}catch (Exception e) {
			throw new Exception("日期获取失败！" + e.getMessage());
		}
	}
	/**
	 * 获取初始化指标日志
	 * @param msg 
	 * @return 
	 * @author C_ture
	 */
	@Bizlet("获取指标日志信息")
	public static void getMsg(String preCount,String doCount,String suCount,String indexMsg) throws Exception {
		
		log.info("待初始化条数："+preCount);
		log.info("已执行条数条数："+doCount);
		log.info("成功条数："+suCount);
		log.info("初始化异常信息："+indexMsg);

	}
	
	/**
	 * 获取指定指标值
	 * @param paramMap 
	 * @return indexValueMap
	 * @author C_ture
	 */
	@Bizlet("获取指定指标值")
	public static HashMap<String, Object> getComIndexValue(HashMap paramMap) throws Exception{
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Set<String> keySet = paramMap.keySet();
		for(String key : keySet){
			if ("b026".equals(key)){//资产总额
				dataMap.put(key, !"".equals(paramMap.get(key)) ? paramMap.get(key):null);
			}else if ("b059".equals(key)){//营业收入
				dataMap.put(key, !"".equals(paramMap.get(key)) ? paramMap.get(key):null);
			}
		}
		return dataMap;

	}
}
