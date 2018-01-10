package com.bos.acc;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;
import com.lowagie.tools.handout_pdf;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;

@Bizlet("根据前台传过来的Map保存财务报表数据")
public class AccCustomerFinanceUtil {
	private String financeId_key = "financeId";

	private String financialStatementId_key = "financeStatementId";

	private String subject_key = "KM";
	private String preSubject_key = "EM";

	@Bizlet("保存财务报表子项数据")
	/**
	 * @param financeStatementData
	 *            date 财务报表子项数据(科目代码、科目值) 创建时间
	 *            说明：同时保存期初值（累计值）和本期值在同一条记录中
	 *            年报：PROJECT_VALUE 	为本期值（资产负债）/本年累计值 （利润）
	 *            	   PRE_TOTAL_VALUE 	为上期值（资产负债）/上年累计值 (利润）
	 *            除年报以外，PROJECT_VALUE为 本期值(资产负债、利润)
	 *            			PRE_TOTAL_VALUE 上期值（资产负债）/本年累计值（利润）
	 */
	public void saveAccFinanceStatementData(Map<String, Object> financeStatementData, Date date, String financialStatementSortCd,
			String customerTypeCd) throws Exception {
		
		//个人经营类需要校验这个平衡公式:固定资产合计>=动产+不动产（其他的是等于没有影响）
		String msg = AccFinanceDataCheckUtil.checkFixedAssets(financeStatementData);
		if(null!=msg){
			throw new Exception(msg);
		}
		
		String financeId = "";
		DecimalFormat df = new DecimalFormat("#.00");

		if (financeStatementData.containsKey(financeId_key) && financeStatementData.get(financeId_key) != null
				&& !"".equals(financeStatementData.get(financeId_key))) {
			financeId = financeStatementData.get(financeId_key).toString();
		}
		String financialStatementId = "";
		if (financeStatementData.containsKey(financialStatementId_key)
				&& financeStatementData.get(financialStatementId_key) != null
				&& !"".equals(financeStatementData.get(financialStatementId_key))) {
			financialStatementId = financeStatementData.get(financialStatementId_key).toString();
		}

		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> deletelList = new ArrayList<Map<String, Object>>();
		if (StringUtil.isEmpty(financialStatementId) || StringUtil.isEmpty(financeId)) {
			throw new Exception("财报信息不存在请确认后再保存");
		}
		// 1、先删除原来的财务报表子项数据
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		deleteMap.put("FINANCIAL_STATEMENT_ID", financialStatementId);// 财务报表子项ID
		deleteMap.put("FINANCE_ID", financeId);// 财务信息ID
		deletelList.add(deleteMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.TbAccFinanceStatementData.deleteAccFinanceStatementData",
				deletelList.toArray(new HashMap[deletelList.size()]));
		// 2、删除财报已经保存的指标
		List<Map<String, Object>> deletelIndexList = new ArrayList<Map<String, Object>>();
		Map<String, Object> deleteIndexMap = new HashMap<String, Object>();
		deleteIndexMap.put("FINANCE_ID", financeId);// 财务信息ID
		deletelIndexList.add(deleteIndexMap);
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.TbAccFinanceStatementData.deleteAccFinanceIndexData",
				deletelIndexList.toArray(new HashMap[deletelIndexList.size()]));
		// 3、保存新的财务报表子项数据
		// 循环遍历financeStatementData，构造AccFinanceStatementData
		Iterator<Entry<String, Object>> iterator = financeStatementData.entrySet().iterator();

		HashMap<String, Object> allValueMap = new HashMap<String, Object>();
		HashMap<String, Object> allPreValueMap = new HashMap<String, Object>();
		java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());

		while (iterator.hasNext()) {
			Map<String, Object> detail = new HashMap<String, Object>();
			Map.Entry entry = iterator.next();
			if (entry.getKey() != null && !"".equals(entry.getKey())) {
				String projectCd = entry.getKey().toString();
				Object projectValue = entry.getValue();
				if (!projectCd.equals(financeId_key) && !projectCd.equals(financialStatementId_key)) {
					if (projectCd.startsWith(subject_key)) {
						projectCd = projectCd.split(subject_key)[1];

						// 处理期初值、累计值
						Iterator<Entry<String, Object>> Preite = financeStatementData.entrySet().iterator();
						Object preValue = null;
						while (Preite.hasNext()) {
							Map.Entry preEntry = Preite.next();
							if (preEntry.getKey() != null && !"".equals(preEntry.getKey())) {
								String preCd = preEntry.getKey().toString();// 期初值、累计值编码
								if (!preCd.equals(financeId_key) && !preCd.equals(financialStatementId_key)) {
									if (preCd.startsWith("EM")) {
										preCd = preCd.split("EM")[1];
										if (preCd.equals(projectCd)) {
											preValue = preEntry.getValue();// 期初值、累计值
										}
									}
								}
							}
						}

						String id = GitUtil.genUUIDString();
						// 构造AccFinanceStatementData
						detail.put("FINANCE_STATEMENT_DATA_ID", id);// ID
						detail.put("FINANCIAL_STATEMENT_ID", financialStatementId);// 财务报表子项ID
						detail.put("FINANCE_ID", financeId);// 财务信息ID
						detail.put("PROJECT_CD", projectCd);// 科目代码
						if ("05".equals(financialStatementSortCd)) {// 除补充资料外，科目为空补0处理
							projectValue = (projectValue == null) ? "null" : projectValue;
							preValue = (preValue == null) ? "null" : preValue;

						} else {
							projectValue = (projectValue == null) ? new BigDecimal(0.00)
									: new BigDecimal(projectValue.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
							preValue = (preValue == null) ? new BigDecimal(0.00) : new BigDecimal(preValue.toString()).setScale(
									2, BigDecimal.ROUND_HALF_UP);
						}
						detail.put("PROJECT_VALUE", projectValue);// 科目值
						detail.put("PRE_TOTAL_VALUE", preValue);// 期初累计值
						detail.put("CREATE_TIME", time);// 创建时间
						detail.put("UPDATE_TIME", time);// 更新时间
						detailList.add(detail);

						// 组装当期平衡校验map
						allValueMap.put(projectCd, projectValue);

						// 组装期初、累计值校验map
						allPreValueMap.put(projectCd, preValue);
					}
				}
			}
		}
		// 校验当期勾稽关系
		UploadAccExcel.checkRule(allValueMap, customerTypeCd);
		// 校验期初、累计值勾稽关系
		UploadAccExcel.checkPreRule(allPreValueMap, customerTypeCd);

		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.TbAccFinanceStatementData.insertAccFinanceStatementData",
				detailList.toArray(new HashMap[detailList.size()]));
	}

	/**
	 * 保存现金流量表数据
	 * @param financeStatementData
	 * @throws Exception
	 */
	@Bizlet("保存现金流量表数据")
	public static void saveAccFinanceStatementData(Map<String, Object> financeStatementData) throws Exception {
		// 去掉所有key中的标记（EM,KM）
		Iterator<Entry<String, Object>> Preite = financeStatementData.entrySet().iterator();
		// 校验数据的map
		HashMap<String, Double> map = new HashMap<String, Double>();

		Date date = new Date();
		double preValue = 0;
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();

		while (Preite.hasNext()) {
			// 存储数据的map
			Map<String, Object> detail = new HashMap<String, Object>();
			Map.Entry preEntry = Preite.next();
			String preCd = preEntry.getKey().toString();
			if (!("reportType".equals(preCd) || "financeStatementId".equals(preCd) || "financeId".equals(preCd) || "sheetCode"
					.equals(preCd))) {
				if (preEntry.getKey() != null && !"".equals(preEntry.getKey())) {
					if (preCd.startsWith("EM")) {
						preCd = preCd.split("EM")[1];
						preValue = Double.parseDouble((preEntry.getValue() == null ? 0.00 : preEntry.getValue()) + "");
						map.put(preCd, preValue);
					}
				}
				String id = GitUtil.genUUIDString();
				// 构造AccFinanceStatementData
				detail.put("FINANCE_STATEMENT_DATA_ID", id);// ID
				detail.put("FINANCIAL_STATEMENT_ID", financeStatementData.get("financeStatementId").toString());// 财务报表子项ID
				detail.put("FINANCE_ID", financeStatementData.get("financeId").toString());// 财务信息ID
				detail.put("PROJECT_CD", preCd);// 科目代码
				detail.put("PROJECT_VALUE", new BigDecimal(preValue));// 科目值
				detail.put("PRE_TOTAL_VALUE", new BigDecimal(0));// 存进去但是不用，页面只显示一列
				detail.put("CREATE_TIME", date);// 创建时间
				detail.put("UPDATE_TIME", date);// 更新时间
				detailList.add(detail);
			}
		}

		String financeId = financeStatementData.get("financeId").toString();// 获取客户财报ID
		// 根据financeId 查找所有科目的值
		DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		obj.set("financeId", financeId);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default", obj);
		if (result == null || result.length == 0) {
			throw new Exception("请先保存左边报表科目信息！");
		}
		// 将所有满足条件（financeId）的科目期末值（KM）和期初值(EM)都保存到map中
		String keyStr = "";
		for(String key:map.keySet()){
			keyStr+=key+"+";
		}
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];
			if(!keyStr.contains(resultDataObject.getString("projectCd"))){//此处的判断是防止在使用excel导入时已经存入到财报数据表的现金流量表科目的干扰
				map.put("KM" + resultDataObject.getString("projectCd"), resultDataObject.getDouble("projectValue"));
				map.put("EM" + resultDataObject.getString("projectCd"), resultDataObject.getDouble("preTotalValue"));
			}
		}
		
		//校验数据
		String reportType = financeStatementData.get("reportType")+"";
		String sheetCode = financeStatementData.get("sheetCode")+"";
		String msg = AccFinanceDataCheckUtil.checkCashFlowData(map,sheetCode,reportType);
		if (msg != null && !"".equals(msg)) {
			throw new Exception("触发平衡校验：请检查本期值是否满足以下平衡公式:【" + msg + "】");
		}
		//存的时候先删一遍
		DataObject statementDataObj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");;
		String financialStatementId = financeStatementData.get("financeStatementId").toString();
		statementDataObj.set("financeId", financeId);
		statementDataObj.set("financialStatementId", financialStatementId);
		DatabaseUtil.deleteByTemplate("default", statementDataObj);
		
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.TbAccFinanceStatementData.insertAccFinanceStatementData",
				detailList.toArray(new HashMap[detailList.size()]));
	}

	
	/**
	 * 保存小贷利润表数据
	 * @param obj
	 */
	@Bizlet("处理小贷利润表数据并保存到财报数据表中")
	public static void saveSheetLrData(DataObject obj) {
		String financeId = obj.getString("financeId");
		String stateMentId = obj.getString("stateMentId");
		
		String financialStatementId = "";
		if("".equals(stateMentId)||null==stateMentId){
			//查找statementId(financialStatementSortCd对应利润表sheetCode为02)
			DataObject statementData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinancialStatement");
			statementData.setString("financeId", financeId);
			statementData.setString("financialStatementSortCd", "02");
			Object[] sDatas = DatabaseUtil.queryEntitiesByTemplate("default", statementData);
			financialStatementId = ((DataObject)sDatas[0]).getString("financialStatementId")==null?"":((DataObject)sDatas[0]).getString("financialStatementId");
		}else{
			financialStatementId = stateMentId;
		}
		
		// 构造AccFinanceStatementData
		DataObject lrData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		lrData.set("financialStatementId", financialStatementId);// 财务报表子项ID
		lrData.set("financeId", financeId);// 财务信息ID
		lrData.set("preTotalValue", new BigDecimal(0));// 存进去但是不用，页面只显示一列
		lrData.set("createTime", GitUtil.getBusiDate());// 创建时间
		lrData.set("updateTime", GitUtil.getBusiDate());// 更新时间

		Map<String, Double> rsMap = getProfitMap(financeId);
		for (String key : rsMap.keySet()) {
			lrData.set("financeStatementDataId", GitUtil.genUUIDString());
			lrData.set("projectCd", key);
			lrData.set("projectValue", new BigDecimal(rsMap.get(key)));
			DatabaseUtil.insertEntity("default", lrData);
		}
	}
	
	/**
	 * 计算利润表数据
	 * @param financeId
	 * @return
	 */
	public static Map<String ,Double> getProfitMap(String financeId){
		Map<String, Double> rsMap = new HashMap<String, Double>();
		//查询参数map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("financeId", financeId);
		Object[] custFinanceData = DatabaseExt.queryByNamedSql("default","com.bos.acc.getAccCopfinanceData.getAccCopfinanceLrAvgData", map);
		DecimalFormat df = new DecimalFormat("#.00");
		DataObject dObj = (DataObject) custFinanceData[0];
		rsMap.put("01902001", Double.parseDouble(df.format(dObj.getDouble("AVG_ZYYWSR"))));// 一、主营业务收入
		rsMap.put("01902002", Double.parseDouble(df.format(dObj.getDouble("AVG_ZYYWCB"))));// 减：主营业务成本
		rsMap.put("01902003", Double.parseDouble(df.format(dObj.getDouble("AVG_JYFYHJ"))));// 减：经营费用合计
		rsMap.put("01902004", Double.parseDouble(df.format(dObj.getDouble("AVG_LWGZ"))));// 劳务工资
		rsMap.put("01902005", Double.parseDouble(df.format(dObj.getDouble("AVG_ZJ"))));// 租金
		rsMap.put("01902006", Double.parseDouble(df.format(dObj.getDouble("AVG_SDF"))));// 水电费
		rsMap.put("01902007", Double.parseDouble(df.format(dObj.getDouble("AVG_S"))));// 税
		rsMap.put("01902008", Double.parseDouble(df.format(dObj.getDouble("AVG_QTJYFY"))));// 其他经营费用
		rsMap.put("01902009", Double.parseDouble(df.format(dObj.getDouble("AVG_CWFY"))));// 减：财务费用
		rsMap.put("01902010", Double.parseDouble(df.format(dObj.getDouble("AVG_JTKZ"))));// 减：家庭开支
		rsMap.put("01902011", Double.parseDouble(df.format(dObj.getDouble("AVG_TZXSR"))));// 二、投资性收入
		rsMap.put("01902012", Double.parseDouble(df.format(dObj.getDouble("AVG_TZXZC"))));// 减：投资性支出
		rsMap.put("01902013", Double.parseDouble(df.format(dObj.getDouble("AVG_QTYYJSR"))));// 三、其他业务净收入
		rsMap.put("01902014", Double.parseDouble(df.format(dObj.getDouble("AVG_LRZE"))));// 利润总额（亏损总额以“-”号填列）
		return rsMap;
	}
	
	/**
	 * 指标计算
	 * @param financeStatementData
	 * @throws Exception
	 */
	@Bizlet("指标计算")
	public static void saveIndexCalculateData(Map<String, Object> financeStatementData) throws Exception {
		
		HashMap<String, Double> map = new HashMap<String, Double>();
		String financeId = financeStatementData.get("FINANCE_ID").toString();// 获取客户财报ID
		String reportType = financeStatementData.get("CUSTOMER_TYPE_CD").toString();
		String sheetCode = financeStatementData.get("sheetCode").toString();
		
		// 根据financeId 查找所有科目的值
		DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		obj.set("financeId", financeId);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default", obj);
		if (result == null || result.length == 0) {
			throw new Exception("请先保存左边报表科目信息！");
		}
		//先删一遍财务指标信息
		DataObject delObj = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceIndexData");
		delObj.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", delObj);
		
		// 将所有满足条件（financeId）的科目期初值（KM）和期末值(EM)都保存到map中
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];
			map.put("KM" + resultDataObject.getString("projectCd"), resultDataObject.getDouble("projectValue"));
			map.put("EM" + resultDataObject.getString("projectCd"), resultDataObject.getDouble("preTotalValue"));
		}
		//计算并校验财务指标
		DataObject rsObj = AccFinanceDataCheckUtil.saveAccIndexData(map, financeId,reportType,sheetCode);
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) rsObj.get("dataList");
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME, "com.bos.acc.exportExcel.insertIndexData",
				dataList.toArray(new HashMap[dataList.size()]));
	}
}
