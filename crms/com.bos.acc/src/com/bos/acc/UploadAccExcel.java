/**
 * 
 */
package com.bos.acc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.bos.pub.DecisionUtil;
import com.bos.pub.ExcelFileReader;
import com.bos.pub.GitUtil;
import com.bos.pub.document.utils.ExcelUtils;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;




















import com.primeton.components.scriptengine.spi.util.DataUtils;
import com.sun.star.sdb.application.DatabaseObject;

import commonj.sdo.DataObject;

import com.bos.utp.tools.ChangeUtil;
import com.ctc.wstx.util.DataUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author C_ture
 * @date 2014-04-01 17:52:15
 *
 */
@Bizlet("上传财报Excel")
public class UploadAccExcel {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);

	/**
	 * 根据导入的Excel形成财报记录
	 * @return financeId
	 * @author C_ture
	 * @throws Exception 
	 */
	@Bizlet("获取Excel单元格")
	public static HashMap<String, Object> getSheetMap(Map<String, HSSFSheet> sheetMap,
			String customerTypeCd, String financeId, String partyId)
			throws Exception {
		
		//查询参数
		HashMap<String, Object> paramMap = new HashMap();
		paramMap.put("FINANCE_ID", financeId);
		paramMap.put("CUSTOMER_TYPE_CD", customerTypeCd);
		paramMap.put("PARTY_ID", partyId);
		
		//科目值集合
		HashMap<String, Object> allValueMap = new HashMap();
		Set<String> keySet = sheetMap.keySet();

		for (String key : keySet) {
			HSSFSheet sheet = sheetMap.get(key);
			String finTypeCd = "";
			String stateMentId = "";
			Map<String, Object> valueMap = new HashMap<String, Object>();
			
			//小贷业务品种利润明细表和银行对账单汇总要分开
			if("019".equals(customerTypeCd)){
				if(key != null && !"rule".equals(key)){
					if(key.contains("利润明细表")){
						//往利润明细表里面存sheeteCod为 09
						finTypeCd = "02";//往报表子表tb_acc_financial_statement存02
						//利润表明细先要获取stateMentId，因为在存入利润表数据时要用到
						stateMentId = saveStatement(finTypeCd, financeId);
						saveProfitDetailData(sheet, financeId,"09",customerTypeCd ,stateMentId);
					}
					if(key.contains("银行对账单")){
						finTypeCd = "08";
						//银行对账单汇总
						saveBillData(sheet, financeId,finTypeCd,customerTypeCd);
					}
				}
			}
			
			//如果不是小贷业务品种利润明细表和银行对账单汇总按原来方式读取excel
			if(!(("019".equals(customerTypeCd)&&"02".equals(finTypeCd))||("019".equals(customerTypeCd)&&"08".equals(finTypeCd)))){
				valueMap = (Map<String, Object>) ExcelFileReader.readSheetForm(sheet);
			}

			//校验模板正确性
			if (key != null && "报表附注信息".equals(key)) {
				//通过命名SQL保存附属信息
				try {
					updateFinData(valueMap, customerTypeCd, financeId, partyId);

				} catch (Exception e) {
					log.error("保存附注信息出错：", e);
					throw new Exception("请下载最新财报模板并确认【报表附注信息】输入正确！" + e.getMessage());
				}
			}else if (key != null && !"rule".equals(key)) {
				if (key.contains("资产负债表")) {
					finTypeCd = "01";
				} else if (key.contains("利润表")) {
					finTypeCd = "02";
				} else if (key.contains("现金流量表")) {
					finTypeCd = "03";
				} else if (key.contains("收入支出表")) {
					finTypeCd = "02";
				} else if (key.contains("补充资料")) {
					finTypeCd = "05";
				}
				//创建子表
				if("".equals(stateMentId)||null ==stateMentId){
					stateMentId = saveStatement(finTypeCd, financeId);
				}
				try {
					if(!(("019".equals(customerTypeCd)&&"02".equals(finTypeCd))||("019".equals(customerTypeCd)&&"08".equals(finTypeCd)))){
						saveData(valueMap, stateMentId, financeId,finTypeCd);
					}
				} catch (Exception e) {
					log.error("保存子表信息出错：", e);
					throw new Exception("请下载最新财报模板，保持模板格式不变，并确认【" + key+"】信息输入正确！"+e.getMessage());
				}
			}
			//全量科目数据
			allValueMap.putAll(valueMap);
		}
		
		//全量指标计算
		try {
			if(!"019".equals(customerTypeCd)){
				//个人经营类先不校验，页面保存时校验
				allValueMap.putAll(paramMap);
				saveIndexCompute(allValueMap);
			}
		} catch (Exception e) {
			log.error("指标计算出错：", e);
			throw new Exception("指标计算错误！请确认：" + e.getMessage());
		}
		
		//如果是企业类，事业类，手工增加“补充资料”页签。
		String str="002,004,012,013,014";
		if(str.indexOf(customerTypeCd)!=-1){
			
			//创建子表
			saveStatement("05", financeId);
		}
		if(null==allValueMap.get("ECIF_PARTY_NUM") || "".equals(allValueMap.get("ECIF_PARTY_NUM"))){
			log.error("导入ECIF客户编号为空，或不是最新的模板，请检查！");
			throw new Exception("导入ECIF客户编号为空，或不是最新的模板，请检查！" );
		}
		//校验ecif客户编号是否一致
		try {
			if(partyId!=null && allValueMap.get("ECIF_PARTY_NUM")!=null ){
				checkEcifNum(allValueMap,partyId);
			}
		} catch (Exception e) {
			log.error("保存附注信息出错：", e);
			throw new Exception(e.getMessage());
		}
				
		return allValueMap;
	}
	
	/**
	 * 处理导入的利润表明细数据
	 * @param sheet
	 * @param financeId
	 * @param finTypeCd(对应的是sheetCode)
	 * @param customerTypeCd (对应的是reportType)
	 * @param stateMentId 
	 * @return 
	 * @return 
	 * @throws Exception 
	 */
	private static void saveProfitDetailData(HSSFSheet sheet,String financeId, String finTypeCd, String customerTypeCd, String stateMentId) throws Exception {
		Map<String ,Object> dataMap = ExcelUtils.getExcellData(sheet);
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get(sheet.getSheetName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < dataList.size(); i++) {
			String id = GitUtil.genUUIDString();
			if(checkMapData(dataList.get(i))){
				//校验数据
				checkAccData(dataList.get(i),1,(i+1));
				if(dataList.get(i).get("profitMonth")!=null){
					if(!("".equals(dataList.get(i).get("profitMonth").toString())||null == dataList.get(i).get("profitMonth").toString())){
						dataList.get(i).put("profitMonth", df.format(df.parse(dataList.get(i).get("profitMonth").toString())));
					}
				}
				dataList.get(i).put("profitDataId", id);
				dataList.get(i).put("financeId", financeId);
				dataList.get(i).put("reportType", customerTypeCd);
				dataList.get(i).put("sheetCode", finTypeCd);
			}else{
				//当且仅当所有科目值全部为空（excel数据表的空行）不插到数据库中
				dataList.remove(i);
				i--;
			}
		}
		//将数据插到明细表里面
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAccProfitData",dataList
						.toArray(new HashMap[dataList.size()]));
		
		//将平均值计算出来并将利润表数据存到财报数据表中
		DataObject obj = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		obj.set("financeId", financeId);
		obj.set("stateMentId", stateMentId);
		AccCustomerFinanceUtil.saveSheetLrData(obj);
	}

	/**
	 * 处理导入的对账单汇总数据
	 * @param sheet
	 * @param financeId
	 * @param finTypeCd (对应的是sheetCode)
	 * @param customerTypeCd (对应的是reportType)
	 * @return 
	 * @throws Exception 
	 */
	private static void saveBillData(HSSFSheet sheet, String financeId, String finTypeCd, String customerTypeCd) throws Exception {
		Map<String ,Object> dataMap = ExcelUtils.getExcellData(sheet);
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get(sheet.getSheetName());
		
		DataObject accountObj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillAccount");
		for (int i = 0; i < dataList.size(); i++) {
			String id = GitUtil.genUUIDString();
			Map<String, Object> proData = dataList.get(i);
			//校验数据
			checkAccData(proData,2,(i+2));
			//账户信息
			if(proData.keySet().size()<4){
				accountObj.set("financeId", financeId);
				for (String key : proData.keySet()) {
					accountObj.set(key, proData.get(key));
				}
				//将数据插到账户信息表里面去
				DatabaseUtil.insertEntity("default", accountObj);
				dataList.remove(i);
				i--;
			}else{
				if(checkMapData(dataList.get(i))){
					dataList.get(i).put("billDataId", id);
					dataList.get(i).put("financeId", financeId);
					dataList.get(i).put("reportType", customerTypeCd);
					dataList.get(i).put("sheetCode", finTypeCd);
				}else{
					//当且仅当所有科目值全部为空（excel数据表的空行）不插到数据库中
					dataList.remove(i);
					i--;
				}
			}
		}
		//将数据插到对账单汇总表里面
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAccBillData",dataList
						.toArray(new HashMap[dataList.size()]));
	}


	//校验一个Map<key,Value>里面在有key的情况下所有的valaue是否为空
	public static boolean checkMapData(Map<String,Object> map){
		boolean flag = false;
		if(map.keySet().size()!=0){
			for (String key : map.keySet()) {
				if(!("".equals(map.get(key)) || null==map.get(key))){
					flag = true;
				}
			}
		}
		return flag;
	}
	
	//校验利润明细表和对账单汇总表数据
	private static void checkAccData(Map<String, Object> proData,int a, int index) throws Exception{
		//DecimalFormat df = new DecimalFormat("#.00");
		Set<String> keySet = proData.keySet();
		if(a == 1){//利润表明细信息
			for (String key : keySet) {
				if(proData.get(key)!=null){
					String msg = getCellName(key);
					String value = proData.get(key).toString();
					if("profitMonth".equals(key)){
						//月份信息单独校验(yyyy-MM-dd)
						String reMsg = "利润明细表第【"+index+"】行科目【"+msg+"】值【"+value+"】的填写有误，可以非必输，如果要输入请输入正确的月份信息且必须为【yyyy-MM-dd】格式，并且为指定月的最后一天!";
						if(!checkVailde(value,"\\d{4}-\\d{1,2}-\\d{1,2}")){//首先判断是否符合日期格式yyyy-MM-dd
							throw new Exception(reMsg);
						}
						int year = Integer.parseInt(value.split("-")[0]);
						int m = Integer.parseInt(value.split("-")[1]);
						int day = Integer.parseInt(value.split("-")[2]);
						//再判断月份信息是否正确
						if(!(m>0&&m<=12)){
							throw new Exception(reMsg);
						}
						//最后判断天数是否正确
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Calendar ca = Calendar.getInstance();
						ca.set(year, m, 0);
						String date = df.format(ca.getTime());
						int day2 = Integer.parseInt(date.split("-")[2]);
						if(day!=day2){
							throw new Exception(reMsg);
						}
					}else{
						//验证是不是数字
						if(!checkVailde(value,"[0-9]{0,20}(\\.[0-9]{0,2})?")){
							throw new Exception("利润明细表第【"+index+"】行科目【"+msg+"】值【"+value+"】的填写有误，可以非必输，如果要输入必须为数字,可以是整数或者浮点数,小数位最多两位!");
						}
					}
				}
			}
		}
		if(a == 2){
			if(keySet.size()<4){
				if(proData.get("billZh")!=null){
					if(!("".equals(proData.get("billZh").toString())||null == proData.get("billZh").toString())){
						if(!checkVailde(proData.get("billZh").toString(), "\\d[0-9]*$")){
							throw new Exception("对账单汇总表【银行账号】的值【"+proData.get("billZh").toString()+"】填写有误，可以非必输，如果要输入必须输入纯数字");
						}
					}
				}
			}else{
				for (String key:keySet) {
					if(proData.get(key)!=null){
						String msg = getCellName(key);
						String value = proData.get(key).toString();
						if("billMonth".equals(key)&&!checkVailde(proData.get(key).toString(), "^(0?[1-9]|1[0-2])$")){
							throw new Exception("对账单汇总表第【"+index+"】行科目【"+msg+"】的值【"+value+"】填写有误，可以非必输，如果要输入请输入[1-12]之间的整数!");
						}
						if("billYear".equals(key)){
							Calendar time = Calendar.getInstance();
							int year = time.get(Calendar.YEAR);
							if(!checkVailde(proData.get(key).toString(), "^\\d{4}")){
								throw new Exception("对账单汇总表第【"+index+"】行科目【"+msg+"】的值【"+value+"】填写有误，可以非必输，如果要输入请输入小于"+year+"的正确年份信息!");
							}else{
								if(Integer.parseInt(proData.get(key).toString())>year){
									throw new Exception("对账单汇总表第【"+index+"】行科目【"+msg+"】的值【"+value+"】填写有误，可以非必输，如果要输入请输入请填写小于"+year+"的正确年份信息!");
								}
							}
						}
						if(("billInCash".equals(key)||"billOutCash".equals(key)||"billMonthRest".equals(key))&&!checkVailde(proData.get(key).toString(), "[0-9]{0,20}(\\.[0-9]{0,2})?")){
							throw new Exception("对账单汇总表第【"+index+"】行科目【"+msg+"】的值【"+value+"】填写有误，可以非必输，如果要输入请输入请填写正确的数字，可以是整数或者浮点数,小数位最多两位!");
						}
						if(("billInCount".equals(key)||"billOutCount".equals(key))&&!checkVailde(proData.get(key).toString(), "\\d[0-9]*$")){
							throw new Exception("对账单汇总表第【"+index+"】行科目【"+msg+"】的值【"+value+"】填写有误，可以非必输，如果要输入请输入请填写正确的整数!");
						}
					}
				}
			}
		}
	}
	
	private static boolean checkVailde(String str,String patt){
		Pattern pattern = Pattern.compile(patt);
		//BigDecimal bd = new BigDecimal(str);
		Matcher isNum = pattern.matcher(str);
		if(isNum.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	private static String getCellName(String cellComment){
		String cellName = "";
		//利润表明细
		if("profitMonth".equals(cellComment)){
			cellName = "月份";
		}
		if("profitZyywsr".equals(cellComment)){
			cellName = "一、主营业务收入";
		}
		if("profitZyywcb".equals(cellComment)){
			cellName = "减：主营业务成本";
		}
		if("profitJyfyhe".equals(cellComment)){
			cellName = "减：经营费用合计";
		}
		if("profitLwgz".equals(cellComment)){
			cellName = "劳务工资";
		}
		if("profitZj".equals(cellComment)){
			cellName = "租金";
		}
		if("profitSdf".equals(cellComment)){
			cellName = "水电税";
		}
		if("profitS".equals(cellComment)){
			cellName = "税";
		}
		if("profitTjyfy".equals(cellComment)){
			cellName = "其他经营费用";
		}
		if("profitCwfy".equals(cellComment)){
			cellName = "减：财务费用";
		}
		if("profitJtkz".equals(cellComment)){
			cellName = "减：家庭开支";
		}
		if("profitTzxsr".equals(cellComment)){
			cellName = "投资性收入";
		}
		if("profitTzxzc".equals(cellComment)){
			cellName = "投资性支出";
		}
		if("profitQtyyjsr".equals(cellComment)){
			cellName = "其他营业净收入";
		}
		if("profitLrze".equals(cellComment)){
			cellName = "利润总额（亏损总额以“-”号填列）";
		}
		
		//银行对账单
		if("billKhh".equals(cellComment)){
			cellName = "开户行";
		}
		if("billZhmc".equals(cellComment)){
			cellName = "账户";
		}
		if("billZh".equals(cellComment)){
			cellName = "账号";
		}
		if("billYear".equals(cellComment)){
			cellName = "年份";
		}
		if("billMonth".equals(cellComment)){
			cellName = "月份";
		}
		if("billInCash".equals(cellComment)){
			cellName = "存入金额";
		}
		if("billInCount".equals(cellComment)){
			cellName = "存入次数";
		}
		if("billOutCash".equals(cellComment)){
			cellName = "支出金额";
		}
		if("billOutCount".equals(cellComment)){
			cellName = "支出次数";
		}
		if("billMonthRest".equals(cellComment)){
			cellName = "月末余额";
		}
		return cellName;
	}
	
	/**
	 * 查找规则分类id
	 * @param customerTypeCd
	 * @return pid
	 * @throws Exception
	 */
	public static String queryPid(String customerTypeCd, String rtype)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String pid = "";
		map.put("customerTypeCd", customerTypeCd);//模板类型
		map.put("reportStatus", "1");//启用
		Object[] nfdReports = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectNfdReport", map);
		if (null == nfdReports || nfdReports.length != 1) {
			throw new Exception("财报模板配置不存在或者配置有误，请联系管理员处理！");
		}
		DataObject nfdReport = (DataObject) nfdReports[0];

		String balancePid = nfdReport.getString("PID");//平衡校验规则分类id
		String indexPid = nfdReport.getString("INDEX_PID");//指标计算规则分类id
		if (rtype.equals("05")) { //平衡校验
			pid = balancePid;
		} else if (rtype.equals("06")) {//指标计算
			pid = indexPid;
		}
		return pid;
	}

	/**
	 * @param paramMap 
	 * 指标计算
	 * 返回计算结果存入数据库
	 * @param financeId
	 * @param partyId 
	 * @param allValueMap 
	 */
	@Bizlet("指标计算")
	public static void saveIndexCompute(HashMap<String, Object> allValueMap) throws Exception {

		String financeId = (String) allValueMap.get("FINANCE_ID");
		String customerTypeCd = (String) allValueMap.get("CUSTOMER_TYPE_CD");
		String partyId = (String) allValueMap.get("PARTY_ID");
		//根据规则分类id及规则类别调规则进行校验
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		String pid = queryPid(customerTypeCd, "06");//规则类别 06 指标计算
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
		
		//删除已有财务报表指标
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		List<Map<String, Object>> deletelList = new ArrayList<Map<String, Object>>();
		deleteMap.put("FINANCE_ID", financeId);//财务信息ID
		deletelList.add(deleteMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.deleteIndexDataByFinanceId", 
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
			dataMap.put("FINANCE_ID", financeId);
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
				"com.bos.acc.exportExcel.insertIndexData", dataList
						.toArray(new HashMap[dataList.size()]));

	}
	
	/**
	 * @param paramMap 
	 * 指标全量计算
	 * 返回计算结果存入数据库
	 * 出于性能考虑，不删除原指标记录
	 * @param financeId
	 * @param partyId 
	 * @param allValueMap 
	 */
	@Bizlet("指标全量计算")
	public static void runAllIndex(HashMap<String, Object> allValueMap) throws Exception {

		String financeId = (String) allValueMap.get("FINANCE_ID");
		String customerTypeCd = (String) allValueMap.get("CUSTOMER_TYPE_CD");
		String partyId = (String) allValueMap.get("PARTY_ID");
		//根据规则分类id及规则类别调规则进行校验
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		String pid = queryPid(customerTypeCd, "06");//规则类别 06 指标计算
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
		
//		删除已有财务报表指标
//		Map<String, Object> deleteMap = new HashMap<String, Object>();
//		List<Map<String, Object>> deletelList = new ArrayList<Map<String, Object>>();
//		deleteMap.put("FINANCE_ID", financeId);//财务信息ID
//		deletelList.add(deleteMap);
//		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
//				"com.bos.acc.exportExcel.deleteIndexDataByFinanceId", 
//				deletelList.toArray(new HashMap[deletelList.size()]));
		
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
			dataMap.put("FINANCE_ID", financeId);
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
				"com.bos.acc.exportExcel.insertIndexData", dataList
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
	public static HashMap<String, Object> getEverFinanceData(
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
	 * 获取上期或期初科目值 
	 * @param map 包括FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报
	 				FINANCE_DEADLINE 财报日期
	 				PARTY_ID 客户id
	 				CUSTOMER_TYPE_CD 财报类别
	 * @return financeDataMap 包括PROJECT_CD 科目代码 PROJECT_VALUE 科目值
	 */
	@Bizlet("返回年初报表科目")
	public static HashMap<String, Object> getEvFinanceData(
			Map<String, Object> map) {
		HashMap<String, Object> financeData = new HashMap<String, Object>();
		map.put("FINANCE_STATUS_CD", "02");//生效

		Date financeDeadline = (Date)map.get("FINANCE_DEADLINE");//本期日期
		String financeTypeCd = (String)map.get("FINANCE_TYPE_CD");//类型
		Date eFinanceDate = null;//上期
		if("1".equals(financeTypeCd) || "5".equals(financeTypeCd)){
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,-1,0,0);//上一年
		}else{
			eFinanceDate = ChangeUtil.dateAdd(financeDeadline,0,-financeDeadline.getMonth()-1,0);//取年初值
			map.put("FINANCE_TYPE_CD", "1");//年初数，置为年报
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
	 * 保存财报子表
	 * @param finTypeCd
	 * @param financeId
	 */
	private static String saveStatement(String finTypeCd, String financeId)
			throws Exception {
		String stateMentId = GitUtil.genUUIDString();
		List<Map<String, Object>> stateMentList = new ArrayList<Map<String, Object>>();

		Map<String, Object> stateMentMap = new HashMap<String, Object>();
		stateMentMap.put("FINANCIAL_STATEMENT_ID", stateMentId);
		stateMentMap.put("FINANCE_ID", financeId);
		stateMentMap.put("FINANCIAL_STATEMENT_SORT_CD", finTypeCd);
		stateMentMap.put("CREATE_TIME", GitUtil.getBusiDate());
		stateMentMap.put("UPDATE_TIME", GitUtil.getBusiDate());
		stateMentList.add(stateMentMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAccStatement", stateMentList
						.toArray(new HashMap[stateMentList.size()]));
		return stateMentId;
	}

	/**
	 * 保存附属信息
	 * @param valueMap
	 */
	private static void updateFinData(Map<String, Object> valueMap,
			String customerTypeCd, String financeId, String partyId)
			throws Exception {

		//数据校验
		String deadLine = valueMap.get("FINANCE_DEADLINE") != null ? valueMap
				.get("FINANCE_DEADLINE").toString() : "";//财务报表截止日期

		String auditedInd = valueMap.get("AUDITED_IND") != null ? valueMap.get(
				"AUDITED_IND").toString() : "";//是否经过审计

		String financeTypeCd = valueMap.get("FINANCE_TYPE_CD") != null ? valueMap
				.get("FINANCE_TYPE_CD").toString()
				: "";//财务报表类型

		String caliberCd = valueMap.get("CALIBER_CD") != null ? valueMap.get(
				"CALIBER_CD").toString() : "";//财务报表口径

		String regulatedInd = valueMap.get("REGULATED_IND") != null ? valueMap
				.get("REGULATED_IND").toString() : "";//财务报表是否经过调整

		String regulatedReason = valueMap.get("REGULATED_REASON") != null ? valueMap
				.get("REGULATED_REASON").toString()
				: "";//财务报表调整原因

		String accountingPrin = valueMap.get("ACCOUNTING_PRINCIPLE") != null ? valueMap
				.get("ACCOUNTING_PRINCIPLE").toString()
				: "";//财务报表会计准则

		String auditCommCd = valueMap.get("AUDIT_COMMENT_TYPE_CD") != null ? valueMap
				.get("AUDIT_COMMENT_TYPE_CD").toString()
				: "";//审计意见类型

		String auditCoName = valueMap.get("AUDIT_CO_NAME") != null ? valueMap
				.get("AUDIT_CO_NAME").toString() : "";//审计事务所名称

		String bizLinceseNum = valueMap.get("BIZ_LICENSE_NUM") != null ? valueMap
				.get("BIZ_LICENSE_NUM").toString()
				: "";//审计事务所经营执照号码

		if (deadLine.equals("") || auditedInd.equals("")
				|| financeTypeCd.equals("") || caliberCd.equals("")
				|| regulatedInd.equals("")) {
			throw new Exception(
					"附属信息中截止日期、是否经过审计、财务报表类型、财务报表口径、财务报表是否经过调整必须填写！");
		}
		String temp = deadLine.substring(4, 5);
		if (!deadLine.substring(4, 5).equals("-")
				|| !deadLine.substring(7, 8).equals("-")) {
			if(!deadLine.substring(6, 7).equals("-")){
			throw new Exception("附属信息中截止日期格式请按【年-月-日】填写");
			}
		}
		Date deadlineDate = GitUtil.toDate(deadLine);
		
		Date currentDate = GitUtil.getBusiDate();
		if(deadlineDate.getTime()>currentDate.getTime()){
			
			throw new Exception("财务报表日期不能大于当前营业时间");
		}
		
		
		//校验日期是否符合规则
		checkDeadline(financeTypeCd,deadlineDate);

		if (auditedInd.equals("1")) {//经过审计
			if (auditCoName.equals("")) {
				//throw new Exception("如果为已审计，审计意见类型、审计事务所名称、审计事务所经营执照号码三项必须填写！");
				throw new Exception("如果为已审计，审计事务所名称必须填写！");
			}
		} else if (auditedInd.equals("0")) {//未审计
			if (!auditCoName.equals("")) {
				//throw new Exception("如果为未审计，审计意见类型、审计事务所名称、审计事务所经营执照号码三项不能填写！");
				throw new Exception("如果为未审计，审计事务所名称不能填写！");
			}
		}
		if (regulatedInd.equals("1")) {//经过调整
			if (regulatedReason.equals("")) {
				throw new Exception("如果为经过调整，财务报表调整原因必须填写！");
			}
		} else if (auditedInd.equals("0")) {//未调整
			if (!regulatedReason.equals("")) {
				throw new Exception("如果为未经过调整，财务报表调整原因不能填写！");
			}
		}
		//数据校验
		
//		//财务报表
//		DataObject tbAccCustomerFinance = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
//		tbAccCustomerFinance.set("partyId",partyId);
//		DataObject[]  tbAccCustomerFinances =DatabaseUtil.queryEntitiesByTemplate("default", tbAccCustomerFinance);
//		for (int i = 0; i < tbAccCustomerFinances.length; i++) {
//			tbAccCustomerFinance = tbAccCustomerFinances[i];
//			if(customerTypeCd.equals(tbAccCustomerFinance.getString("customerTypeCd")) && deadlineDate.equals(tbAccCustomerFinance.getDate("financeDeadline")) ){
//				log.error("导入有误，导入报表日期类型已存在，请检查！");
//				throw new Exception("导入有误，导入报表日期类型已存在，请检查！" );
//			}
//		}
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Date sysdate = GitUtil.getBusiDate();
		String userId = GitUtil.getCurrentUserId();
		String ortId = GitUtil.getCurrentOrgCd();
		valueMap.put("FINANCE_ID", financeId);
		//参与人ID
		valueMap.put("PARTY_ID", partyId);
		valueMap.put("CUSTOMER_TYPE_CD", customerTypeCd);
		valueMap.put("FINANCE_STATUS_CD", "01");//状态为未生效
		valueMap.put("CREATE_TIME", sysdate);
		valueMap.put("UPDATE_TIME", sysdate);
		valueMap.put("USER_CD", userId);
		valueMap.put("ORG_CD", ortId);
		valueMap.put("CURRENCY", "CNY");//默认为人民币156
		
		valueMap.put("REGULATED_REASON", regulatedReason);
		valueMap.put("ACCOUNTING_PRINCIPLE", accountingPrin);
		valueMap.put("AUDIT_COMMENT_TYPE_CD", auditCommCd);
		valueMap.put("AUDIT_CO_NAME", auditCoName);
		valueMap.put("BIZ_LICENSE_NUM", bizLinceseNum);
		
		
		valueMap.put("FINANCE_DEADLINE", deadlineDate);

		detailList.add(valueMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAccFinance", detailList
						.toArray(new HashMap[detailList.size()]));
	}
	/**
	 * 报表日期校验 根据报表类型校验报表日期是否符合规则
	 * @param customerTypeCd 报表类型1-年报,2-半年报，3-季报，4-月报,5-类年报；deadlineDate 报表截止日期
	 * @return
	 */
	@Bizlet("报表日期校验")
	public static void checkDeadline(String financeTypeCd, Date deadlineDate)throws Exception {
		Date tempDate = deadlineDate;
		int month =deadlineDate.getMonth();//0代表1月 1代表2月 11代表12月 以此类推
		if("1".equals(financeTypeCd)){
			if(month !=11){
				throw new Exception("输入的报表截止日期不符合年报规定日期，请输入月份为12的年报！");
			}
		}else if ("3".equals(financeTypeCd)){
			if(month!=2 && month!=5 && month!=8 && month!=11){
				throw new Exception("输入的报表截止日期不符合季报规定日期，请输入月份为3、6、9、12的季报！");
			}
		}else if ("2".equals(financeTypeCd)){
			if(month!=5 && month!=11){
				throw new Exception("输入的报表截止日期不符合半年报规定日期，请输入月份为6、12的半年报！");
			}
		}else if ("5".equals(financeTypeCd)){
			if(month!=11){
				throw new Exception("输入的报表截止日期不符合类年报规定日期，请输入月份为12的类年报！");
			}
		}
		Date checkDate =ChangeUtil.date2LastDayOfMonth(tempDate);
		if(!checkDate.equals(deadlineDate)){
			throw new Exception("输入的报表截止日期不符合报表规定日期，请输入该月最后一天！");
		}
		
	}

	/**
	 * 保存财务报表数据
	 * @param valueMap
	 */
	private static void saveData(Map<String, Object> valueMap,
			String stateMentId, String financeId,String finTypeCd) throws Exception {
		//校验valueMap里面的值是不是纯数字（公司客户及个人经营类和小贷业务品种类在导入的时候表格中没有校验需要校验一下，不然存到数据库可能出问题）
		if(!checkAccData(valueMap)){
			throw new Exception("请检查科目值输入格式，可以不输入，如果输入请输入正确的数值！");
		}
		
		DecimalFormat df = new DecimalFormat("#.00");
		Set<String> keySet = valueMap.keySet();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (String key : keySet) {
			if (!key.startsWith("e")) {//处理累计值期初值
			//double value = Double.parseDouble(valueMap.get(key)+"");
			String dataId = GitUtil.genUUIDString();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("FINANCE_STATEMENT_DATA_ID", dataId);
			dataMap.put("FINANCIAL_STATEMENT_ID", stateMentId);
			dataMap.put("FINANCE_ID", financeId);
			dataMap.put("PROJECT_CD", key);
			if("05".equals(finTypeCd)){//05 补充资料时科目值保持空
				dataMap.put("PROJECT_VALUE",!"".equals(valueMap.get(key)) ? valueMap.get(key):"null");
				dataMap.put("PRE_TOTAL_VALUE","null");
			}else if("02".equals(finTypeCd)){//利润表取本年累计值
				dataMap.put("PROJECT_VALUE",!"".equals(valueMap.get(key)) ? df.format(valueMap.get(key)):new BigDecimal(0.00));
				if(!"".equals(valueMap.get("e"+key)) && null!=valueMap.get("e"+key)){
				dataMap.put("PRE_TOTAL_VALUE",!"".equals(valueMap.get("e"+key)) ? df.format(valueMap.get("e"+key)):"null");
				}else{
					dataMap.put("PRE_TOTAL_VALUE","null");	
				}
			}else{//其它科目值为空的默认为0.00
				dataMap.put("PROJECT_VALUE",!"".equals(valueMap.get(key)) ? valueMap.get(key):new BigDecimal(0.00));
				dataMap.put("PRE_TOTAL_VALUE","null");
			}
			dataMap.put("CREATE_TIME", GitUtil.getBusiDate());
			dataMap.put("UPDATE_TIME", GitUtil.getBusiDate());

			dataList.add(dataMap);
			}
		}
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.insertAccStatementData", dataList
						.toArray(new HashMap[dataList.size()]));

	}
	
	/**
	 * @param valueMap
	 * @param finTypeCd
	 * @return
	 */
	private static boolean checkAccData(Map<String, Object> valueMap) {
		for (String key : valueMap.keySet()) {
			if(!"".equals(valueMap.get(key))||null != valueMap.get(key)){
				
				return checkVailde(GitUtil.formatData("########", valueMap.get(key)), "^(-?\\d+)(\\.\\d+)?$");
			}
		}
		return true;
	}

	/**
	 * 校验
	 * @param valueMap
	 * @return
	 */
	@Bizlet("平衡校验")
	public static void checkRule(HashMap<String, Object> allValueMap,
			String customerTypeCd) throws Exception {

		//根据规则分类id及规则类别调规则进行校验
		String pid = queryPid(customerTypeCd, "05");
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		typemap.put("pid", pid);
		typemap.put("rtype", "05"); //规则类别 05 平衡校验
		HashMap<String, HashMap<String, Object>> resultMap = new DecisionUtil()
				.execRuleByTypeId(typemap, allValueMap);
		Set<String> keySet = resultMap.keySet();
		if (null == resultMap || resultMap.size() == 0) {
			throw new Exception("当前财报规则未配置或未生效，请联系管理员处理！");
		}
		for (String key : keySet) {
			HashMap<String, Object> reMap = resultMap.get(key);
			Set<String> rekeySet = reMap.keySet();
			for (String rekey : rekeySet) {
				String reMsg = reMap.get(rekey) != null ? reMap.get(rekey)
						.toString() : "";
				if (rekey.equals("msg")) {
					throw new Exception("异常：" + reMsg);
				} else if (rekey.equals("result") && !reMsg.equals("true")) {
					throw new Exception("触发平衡校验：请检查本期值是否满足以下平衡公式:【" + reMsg + "】");
				}
			}
		}
	}
	
	/**
	 * 期初、累计值校验
	 * @param valueMap
	 * @return
	 */
	@Bizlet("期初、累计值平衡校验")
	public static void checkPreRule(HashMap<String, Object> allValueMap,
			String customerTypeCd) throws Exception {

		//根据规则分类id及规则类别调规则进行校验
		String pid = queryPid(customerTypeCd, "05");
		HashMap<String, Object> typemap = new HashMap<String, Object>();
		typemap.put("pid", pid);
		typemap.put("rtype", "05"); //规则类别 05 平衡校验
		HashMap<String, HashMap<String, Object>> resultMap = new DecisionUtil()
				.execRuleByTypeId(typemap, allValueMap);
		Set<String> keySet = resultMap.keySet();
		if (null == resultMap || resultMap.size() == 0) {
			throw new Exception("当前财报规则未配置或未生效，请联系管理员处理！");
		}
		for (String key : keySet) {
			HashMap<String, Object> reMap = resultMap.get(key);
			Set<String> rekeySet = reMap.keySet();
			for (String rekey : rekeySet) {
				String reMsg = reMap.get(rekey) != null ? reMap.get(rekey)
						.toString() : "";
				if (rekey.equals("msg")) {
					throw new Exception("异常：" + reMsg);
				} else if (rekey.equals("result") && !reMsg.equals("true")) {
					throw new Exception("触发平衡校验：请检查期初或累计值是否满足以下平衡公式:【" + reMsg + "】");
				}
			}
		}
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("转换错误信息换行格式")
	public static String replaceMsg(String param) {

		return param.replaceAll("\n", "");
	}
	@Bizlet("校验ecif客户编号是否一致")
	public  static void checkEcifNum(HashMap<String, Object> allValueMap,String partyId) throws Exception {
		
		DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		tbCsmParty.set("partyId",partyId);
		DatabaseUtil.expandEntity("default", tbCsmParty);
		String partyName=((String)tbCsmParty.getString("partyName")).trim();
		String ecifPartyNum=((String)tbCsmParty.getString("ecifPartyNum")).trim();
		String ecifNum =((String)allValueMap.get("ECIF_PARTY_NUM")).trim();
		String partyName1=((String)allValueMap.get("PARTY_NAME")).trim();
		if(!partyName.equals(partyName1)){
			throw new Exception("导入客户名称有误，导入客户名称与当前客户名称不一致，请检查导入的客户名称！");
		}
		if(null ==ecifNum || "".equals(ecifNum)){
			throw new Exception("导入ECIF客户编号为空，或不是最新的模板，请检查！");
		}
		if(!ecifPartyNum.equals(ecifNum)){
			throw new Exception("导入ECIF客户编号与当前客户ECIF客户编号不一致，请检查导入的ECIF客户编号！");
		}
		
		
	}
}
