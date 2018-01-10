package com.bos.acc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;

/**
 * @author jiangzhan
 * @date 2016-05-25 08:36:11
 * 
 */
@Bizlet("")
public class AccFinanceDataCheckUtil {

	/**
	 * 如果截止日期相同则后面的数据将覆盖前面的数据
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @param rqObjs
	 * @return
	 */
	@Bizlet("保存利润明细表和银行对账单汇总表的时候校验财报截止日期")
	public List<DataObject> checkDeadline(String financeId, String reportType,String sheetCode, DataObject[] rqObjs) {
		List<DataObject> list = new ArrayList<DataObject>();
		for (int i = 0; i < rqObjs.length; i++) {
			String firstDeadline = rqObjs[i].getString("financeDeadline");
			boolean flag = true;
			for (int j = i + 1; j < rqObjs.length; j++) {
				String secondDeadline = rqObjs[j].getString("financeDeadline");
				if ((!"".equals(firstDeadline) && null != firstDeadline)&& (!"".equals(secondDeadline) && null != secondDeadline)) {
					if (firstDeadline.equals(secondDeadline)) {
						flag = false;
					}
				}
			}
			if (flag) {
				list.add(rqObjs[i]);
			}
		}
		return list;
	}

	/**
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("判断小贷业务品种类利润明细表和对账单汇总表科目值是否录入完全")
	public String checkDataEmpty(String financeId, String reportType,String sheetCode) {
		String msg = "";
		if ("09".equals(sheetCode)) {//利润明细表里面sheetCode存的是09
			DataObject proData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceNprofitData");
			proData.set("financeId", financeId);
			Object[] result = DatabaseUtil.queryEntitiesByTemplate("default",proData);
			if (result.length <= 0) {
				msg = "利润明细表数据不能全部为空，或者数据没有保存请点击利润明细表保存按钮！";
			}
		}

		if ("08".equals(sheetCode)) {// 对账单汇总
			DataObject billData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillData");
			billData.set("financeId", financeId);
			Object[] result = DatabaseUtil.queryEntitiesByTemplate("default",billData);
			if (result.length <= 0) {
				msg = "对账单汇总表数据不能全部为空，或者数据没有保存请点击对账单汇总表保存按钮！";
			}
		}
		return msg;
	}

	/**
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("报表校验入口方法")
	public String checkCenterControl(String financeId, String reportType,String sheetCode) throws Throwable {
		String str = "";
		String msg = "数据校验不通过，请先点击保存按钮！";
		if ("01".equals(sheetCode)) {// 资产负债表
			str = checkData(financeId, reportType, sheetCode);
			if (!("".equals(str) || null == str)) {
				str = "资产负债表" + msg;
			}
		}

		if ("03".equals(sheetCode)) {// 现金流量表
			str = checkCashFlowData(financeId, reportType, sheetCode);
			if (!("".equals(str) || null == str)) {
				str = "现金流量表" + msg;
			}
		}

		if ("019".equals(reportType) && "02".equals(sheetCode)) {// 个人经营类利润明细表（sheetCode为09时不做重复校验）
			str = checkProfitDetailData(financeId, reportType, sheetCode);
			if (!("".equals(str) || null == str)) {
				str = "利润明细表" + msg;
			}
		}
		return str;
	}

	/**
	 * 校验公司客户及个人经营类资产负债表、利润表数据和小贷业务品种类资产负债表数据
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("报表校验")
	public String checkData(String financeId, String reportType,String sheetCode) {
		String str = "";
		HashMap<String, Object> allValueMap = new HashMap<String, Object>();
		HashMap<String, Object> allPreValueMap = new HashMap<String, Object>();
		Map<String, Object> faMap = new HashMap<String, Object>();

		DataObject dataObj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		dataObj.setString("financeId", financeId);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default",dataObj);
		if (result == null || result.length == 0) {
			return "数据为空！";
		}
		for (int i = 0; i < result.length; i++) {
			DataObject obj = (DataObject) result[i];
			String projectCd = obj.getString("projectCd");
			String projectValue = obj.getString("projectValue");
			String preValue = obj.getString("preTotalValue");

			faMap.put("KM"+projectCd, projectValue);
			faMap.put("EM"+projectCd, preValue);
			
			// 组装当期平衡校验map
			allValueMap.put(projectCd, projectValue);

			// 组装期初、累计值校验map
			allPreValueMap.put(projectCd, preValue);
		}

		//个人经营类资产负债表平衡公式:固定资产≧动产+不动产的校验
		if("01".equals(sheetCode)&&"019".equals(reportType)){
			str = checkFixedAssets(faMap);
			if(str!=null){
				return str;
			}
		}
		
		
		try {
			// 校验当期勾稽关系
			UploadAccExcel.checkRule(allValueMap, reportType);
			// 校验期初、累计值勾稽关系
			UploadAccExcel.checkPreRule(allPreValueMap, reportType);
		} catch (Exception e) {
			str = e.toString();
		}
		return str;
	}

	/**
	 * @param faMap
	 * @throws Exception 
	 */
	public static String checkFixedAssets(Map<String, Object> faMap) {
		String msg = "触发平衡校验：请检查期初或累计值是否满足以下平衡公式:【固定资产合计>=动产+不动产】";
		//固定资产>=动产+不动产；
		//公司客户及个人经营类
		Double KMgdzc = Double.parseDouble((faMap.get("KM01901017")==null?0:faMap.get("KM01901017"))+"");
		Double EMgdzc = Double.parseDouble((faMap.get("EM01901017")==null?0:faMap.get("EM01901017"))+"");
		Double KMdc = Double.parseDouble((faMap.get("KM01901013")==null?0:faMap.get("KM01901013"))+"");
		Double EMdc = Double.parseDouble((faMap.get("EM01901013")==null?0:faMap.get("EM01901013"))+"");
		Double KMbdc = Double.parseDouble((faMap.get("KM01901015")==null?0:faMap.get("KM01901015"))+"");
		Double EMbdc = Double.parseDouble((faMap.get("EM01901015")==null?0:faMap.get("EM01901015"))+"");
		if(KMgdzc<(KMdc+KMbdc)){
			return msg;
		}
		
		if(EMgdzc<(EMdc+EMbdc)){
			return msg;
		}
		return null;
	}

	/**
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("现金流量表校验")
	public String checkCashFlowData(String financeId, String reportType,String sheetCode) {
		String str = "";
		DataObject dataObj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		dataObj.setString("financeId", financeId);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default",dataObj);
		if (result == null || result.length == 0) {
			return "数据为空！";
		}

		HashMap<String, Double> map = new HashMap<String, Double>();
		// 将所有满足条件（financeId）的科目期初值（KM）和期末值(EM)都保存到map中
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];
			map.put(resultDataObject.getString("projectCd"), resultDataObject.getDouble("projectValue"));
			map.put("KM" + resultDataObject.getString("projectCd"),resultDataObject.getDouble("projectValue"));
			map.put("EM" + resultDataObject.getString("projectCd"),resultDataObject.getDouble("preTotalValue"));
		}
		str = checkCashFlowData(map, sheetCode,reportType);
		return str;
	}

	/**
	 * 
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("小贷业务品种类利润明细表校验")
	public String checkProfitDetailData(String financeId, String reportType,
			String sheetCode) throws Throwable {
		String str = "";

		// 调用规则引擎逻辑流
		String componentName = "com.bos.rule.ruleMgr";
		String operationName = "runRule";
		String rid = "CUSACC_0001";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("financeId", financeId);
		String eType = "1";
		Object[] obj = new Object[3];
		Object[] result = null;
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		obj[0] = rid;
		obj[1] = paraMap;
		obj[2] = eType;
		result = logicComponent.invoke(operationName, obj);
		if (null != result[0] && (!"".equals(result[0]))) {
			return "校验不通过！";
		}
		return str;
	}
	
	/**
	 * 校验现金流量表数据的方法（KM期末，EM期初）
	 * @param map
	 * @param sheetCode
	 * @param reportType
	 * @return
	 */
	public static String checkCashFlowData(HashMap<String, Double> map, String sheetCode, String reportType) {
		map=getDataMap(reportType, map);
		String msg = "";
		DecimalFormat df = new DecimalFormat("#.00");
		if("019".equals(reportType)&& "03".equals(sheetCode)){
			if (map.get("01904006") != Double.parseDouble(df.format(map.get("01904003") - map.get("01904005")))) {
				msg = "经营活动产生的现金流量净额＝经营活动现金流入小计-经营活动现金流出小计";
				return msg;
			}
			if (map.get("01904010") != Double.parseDouble(df.format(map.get("01904008") - map.get("01904009")))) {
				msg = "投资活动产生的现金流量净额＝投资活动现金流入小计-投资活动现金流出小计";
				return msg;
			}
			if (map.get("01904014") != Double.parseDouble(df.format(map.get("01904012") - map.get("01904013")))) {
				msg = "筹资活动产生的现金流量净额＝筹资活动现金流入小计－筹资活动现金流出小计";
				return msg;
			}
			if (map.get("01904015") != Double.parseDouble(df.format(map.get("01904006") + map.get("01904010") + map.get("01904014")))) {
				msg = "期末现金及现金等价物余额＝经营活动产生的现金流量净额+投资活动产生的现金流量净额+筹资活动产生的现金流量净额或（有效货币资金期末数－有效货币资金期初数）";
				return msg;
			}
			if (map.get("01904002") != Double.parseDouble(df.format(map.get("KM01902001") + map.get("KM01902013") + map.get("EM01901003") - map.get("KM01901003")
					+ map.get("KM01901008") - map.get("EM01901008")))) {
				msg = "销售商品、提供劳务收到的现金＝主营业务收入+其他业务净收入+（应收账款期初数－应收账款的期末数）+（预收款项期末数－预收款项期初数）";
				return msg;
			}
			if (map.get("01904004") != Double.parseDouble(df.format(map.get("KM01902002") + map.get("KM01901007") - map.get("EM01901007") + map.get("EM01901006")- map.get("KM01901006") + map.get("KM01901005")- map.get("EM01901005")))) {
				msg = "购买商品、接受劳务支付的现金＝主营业务成本+（存货期末数—存贷的期初数）+（应付账款期初数－应付账款期末数）+（预付款项期末数—预付款项期初数）";
				return msg;
			}
			if (map.get("01904008") != Double.parseDouble(df.format(map.get("KM01902011") + map.get("KM01901022") - map.get("EM01901022")))) {
				msg = "投资活动现金流入＝投资性收入+（实收资本（或股本）净额期末数－实收资本（或股本）净额期初数）";
				return msg;
			}
			if (map.get("01904009") != Double.parseDouble(df.format(map.get("KM01902012") + map.get("KM01901017") - map.get("EM01901017")))) {
				msg = "投资活动现金流出＝投资性支出+（固定资产期末数－固定资产期初数）";
				return msg;
			}
			if (map.get("01904012") != Double.parseDouble(df.format(map.get("KM01901002") - map.get("EM01901002") + map.get("KM01901018") - map.get("EM01901018")))) {
				msg = "筹资活动现金流入＝（短期借款期末数－短期借款期初数）+（非流动负债期末数－非流动负债期初数）";
				return msg;
			}
			if (map.get("01904013") != Double.parseDouble(df.format(map.get("KM01902009"))) + 0.0) {
				msg = "筹资活动现金流出＝财务费用";
				return msg;
			}
		}
		return msg;
	}
	
	/**
	 * 处理科目值，将所有科目值存到map中，如果为空则置为0
	 * @param reportType
	 * @param map
	 * @return
	 */
	public static HashMap<String, Double>  getDataMap(String reportType,HashMap<String, Double> map){
		//查找所有科目的Item_code
		Map<String,String> paraMap = new HashMap<String, String>();
		paraMap.put("reportType", reportType);
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.acc.getAccCopfinanceData.getItemCode", paraMap);
		if (result == null || result.length == 0) {
			return map;
		}
		
		for (int i = 0; i < result.length; i++) {
			DataObject rObj = (DataObject) result[i];
			String key = rObj.getString("ITEM_CODE");
			String key1 = "KM"+rObj.getString("ITEM_CODE");
			String key2 = "EM"+rObj.getString("ITEM_CODE");
			
			if(map.get(key)==null){
				map.put(key, 0.0);
			}
			
			if(map.get(key1)==null){
				map.put(key1, 0.0);
			}
			
			if(map.get(key2)==null){
				map.put(key2, 0.0);
			}
		}
		return map;
	}
	
	/**
	 * 计算并校验财务指标的值
	 * @param map
	 * @param financeId
	 * @param reportType
	 * @param sheetCode
	 * @return
	 */
	@Bizlet("计算并校验财务指标")
	public static DataObject saveAccIndexData(HashMap<String, Double> map, String financeId, String reportType, String sheetCode) {
		//将财报所有科目值存到map中，如果没有值则置为0
		map = getDataMap(reportType, map);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		DataObject rsObj = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");
		String indexValue = "";
		//个人经营类指标计算
		if("019".equals(reportType) && "06".equals(sheetCode)){
			// 毛利率=（主营业务收入-主营业务成本）/主营业务收入*100%
			indexValue = getResult((map.get("KM01902001") - map.get("KM01902002")), map.get("KM01902001"), 2);
			dataList.add(getMap("MLL", indexValue, financeId));
			
			// 利润率=利润总额/（主营业务收入+投资性收入+其他业务净收入）*100%
			indexValue = getResult(map.get("KM01902012"), (map.get("KM01902001") + map.get("KM01902011")), 2);
			dataList.add(getMap("LRL", indexValue, financeId));
			
			// 净利润增长率=（本期净利润-上期净利润）/上期净利润*100%
			indexValue = getResult((map.get("KM01902012") - map.get("EM01902012")), map.get("EM01902012"), 1);
			dataList.add(getMap("JLRZZL", indexValue, financeId));
			
			// 资产收益率=利润总额/【（期初资产总额+期末资产总额）/2】*100%
			indexValue = getResult(2 * map.get("KM01902012"), (map.get("KM01901025") + map.get("EM01901025")), 2);
			dataList.add(getMap("ZCSYL", indexValue, financeId));
			
			// 应收账款周转率=营业收入/【（期初应收账款+期末应收账款）/2】*100%
			indexValue = getResult(2 * map.get("KM01902001"), (map.get("KM01901003") + map.get("EM01901003")), 2);
			dataList.add(getMap("YSZKZZL", indexValue, financeId));
			
			// 存货周转率=营业成本/【（期初存货+期末存货）/2】*100%
			indexValue = getResult(2 * map.get("KM01902002"), (map.get("KM01901007") + map.get("EM01901007")), 2);
			dataList.add(getMap("CHZZL", indexValue, financeId));
			
			// 流动比率 = 流动资产/流动负债
			indexValue = getResult(map.get("KM01901011"), map.get("KM01901012"), 2);
			dataList.add(getMap("LDBL", indexValue, financeId));
			
			// 速动比率 = （流动资产-存货-预付账款）/流动负债
			indexValue = getResult((map.get("KM01901011") - map.get("KM01901007") - map.get("KM01901005")), map.get("KM01901012"), 2);
			dataList.add(getMap("SDBL", indexValue, financeId));
			
			// 资产负债率 = 负债合计/资产总计*100%
			indexValue = getResult(map.get("KM01901020"), map.get("KM01901025"), 2);
			dataList.add(getMap("ZCFZL", indexValue, financeId));
		}
		rsObj.set("dataList", dataList);
		return rsObj;
	}
	
	/**
	 * 
	 * @param d1 分子
	 * @param d2 分母
	 * @param n 校验规则种类（1:分母为上期值 2：分母为计算值）
	 * @return
	 */
	private static String getResult(Double d1, Double d2, int n) {
		if(n==1){
			if(d2<=0){return "01";}
			if(d1<0){return "05";}
		}else{
			if(d2<=0){return "03";}
			if(d1<0){return "05";}
		}
		Double result = d1/d2;
		return result.toString();
	}

	/**
	 * 封装每个指标计算的结果map
	 * @param indexCd
	 * @param indexValue
	 * @param stringType
	 * @param financeId
	 * @return
	 */
	public static Map<String, Object> getMap(String indexCd, String indexValue, String financeId) {
		String dataId = GitUtil.genUUIDString();
		String stringType = "";//初始化stringType
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("INDEX_DATA_ID", dataId);
		dataMap.put("FINANCE_ID", financeId);
		dataMap.put("INDEX_CD", indexCd);// 指标代码
		dataMap.put("STRING_TYPE", stringType);
		dataMap.put("CREATE_TIME", GitUtil.getBusiDate());
		dataMap.put("UPDATE_TIME", GitUtil.getBusiDate());
		if (!stringType.equals("")) {
			dataMap.put("INDEX_VALUE_DATA_TYPE", "null");// 指标值
		} else {
			if("01".equals(indexValue.toString()) ||"02".equals(indexValue.toString())||"03".equals(indexValue.toString())
					||"04".equals(indexValue.toString())||"05".equals(indexValue.toString())||"06".equals(indexValue.toString())){
				dataMap.put("STRING_TYPE", indexValue.toString());//标识
				dataMap.put("INDEX_VALUE_DATA_TYPE", "null");//指标值
			} else {
				dataMap.put("INDEX_VALUE_DATA_TYPE", (new BigDecimal(indexValue)).setScale(6, BigDecimal.ROUND_HALF_UP));// 指标值
			}
		}
		return dataMap;
	}

}
