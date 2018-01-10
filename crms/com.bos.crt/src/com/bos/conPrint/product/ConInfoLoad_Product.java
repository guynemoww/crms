package com.bos.conPrint.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bos.bizApply.getBasicRate;
import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.LoanTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;
import edu.emory.mathcs.backport.java.util.Arrays;

public class ConInfoLoad_Product extends ConInfoLoad {

	private Set<String> products;
	private int showRepayNum;
	private int showPayNum;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> loadCon(Map<String, String> param) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getConInfo_Product", param);
		Map<String, Object> conMap = datas == null || datas.length == 0 ? null : (Map<String, Object>) datas[0];
		if (conMap == null) {
			return null;
		}
		if (getLoadConSql() != null && !getLoadConSql().isEmpty()) {
			setConParam(conMap, param);
			datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, getLoadConSql(), param);
			if (datas != null && datas.length > 0) {
				conMap.putAll((Map<String, Object>) datas[0]);
			}
		}
		loadConSub(conMap);
		changeCon(conMap);
		setConInfo(conMap);
		setConRate(conMap);
		return conMap;
	}

	protected void setConParam(Map<String, Object> conMap, Map<String, String> param) {
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
	}

	protected void loadConSub(Map<String, Object> conMap) {
		if (conMap == null || "none".equals(conMap.get("SEARCH_SUB"))) {
			return;
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		Map<String, Object> conSub = getConSubInfo(param, true);
		if (conSub != null) {
			conMap.putAll(conSub);
		}
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> getConSubInfo(Map<String, String> param, boolean unite) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getConSubInfo", param);
		if (datas == null || datas.length == 0) {
			return null;
		} else if (!unite) {
			return (Map<String, Object>) datas[0];
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String[] keys = { "SUB_PARTY_NAME", "SUBCONTRACT_NUM" };
		Set<String> havValue = new HashSet<String>();
		for (Object obj : datas) {
			String type = (String) ((Map<String, Object>) obj).get("SUBCONTRACT_TYPE");
			for (String k : keys) {
				String key = k + "_" + type;
				String value = (String) ((Map<String, Object>) obj).get(k);
				String temp = (String) map.get(key);
				if (havValue.contains(type + value)) {
					continue;
				}
				if (temp != null) {
					value = temp + "," + value;
				}
				havValue.add(type + value);
				map.put(key, value);
			}
		}
		return map;
	}

	protected void setConInfo(Map<String, Object> conMap) {
	}

	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		setPayoutPlan(reportMap);
		setRepayPlan(reportMap);
	}

	@SuppressWarnings("unchecked")
	public void setPayoutPlan(Map<String, Object> reportMap) {
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null || showPayNum < 1) {
			return;
		}
		String contractId = (String) conMap.get("CONTRACT_ID");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_Pay", contractId);
		if (datas != null && datas.length > 0) {
			reportMap.put("pays", Arrays.asList(datas));
			BigDecimal planAmt = BigDecimal.ZERO;
			for (int i = 0; i < datas.length && i < showPayNum; i++) {
				reportMap.put("pay" + (i + 1), datas[i]);
				planAmt = planAmt.add((BigDecimal) ((Map<String, Object>) datas[i]).get("PAYOUT_AMT"));
			}
			if (planAmt.compareTo(BigDecimal.ZERO) > 0) {
				reportMap.put("pay_totalAmt", planAmt);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void setRepayPlan(Map<String, Object> reportMap) {
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null || showRepayNum < 1) {
			return;
		}
		String contractId = (String) conMap.get("CONTRACT_ID");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_Repay", contractId);
		if (datas != null && datas.length > 0) {
			reportMap.put("repays", Arrays.asList(datas));
			BigDecimal planAmt = BigDecimal.ZERO;
			for (int i = 0; i < datas.length && i < showRepayNum; i++) {
				reportMap.put("repay" + (i + 1), datas[i]);
				planAmt = planAmt.add((BigDecimal) ((Map<String, Object>) datas[i]).get("REPAY_AMT"));
			}
			if (planAmt.compareTo(BigDecimal.ZERO) > 0) {
				reportMap.put("repay_totalAmt", planAmt);
			}
		}
	}

	public void setConRate(Map<String, Object> conMap) {
		if (conMap == null || "none".equals(conMap.get("SEARCH_RALE"))) {
			return;
		}
		String contractId = (String) getNeedValue(conMap, "CONTRACT_ID");
		DataObject conRate = EntityUtil.searchEntity(ConTableName.TB_CON_LOANRATE, new String[] { "contractId", contractId }, new String[] { "yearRate", "DESC" });
		if (conRate == null) {
			return;
		}
		conMap.put("FLOAT_WAY", conRate.get("floatWay"));
		conMap.put("RATE_TYPE", conRate.get("rateType"));
		conMap.put("YEAR_RATE", conRate.get("yearRate"));
		conMap.put("RATE_FLOAT_PROPORTION", conRate.get("rateFloatProportion"));
		conMap.put("IR_UPDATE_FREQUENCY", conRate.get("irUpdateFrequency"));
		conMap.put("INTEREST_COLLECT_TYPE", conRate.get("interestCollectType"));
		conMap.put("OVERDUE_RATE_UP_PROPORTION", conRate.get("overdueRateUpProportion"));
		conMap.put("HOLIDAY_INT_FLG", conRate.get("holidayIntFlg"));
		if (conRate.get("yearRate") == null) {
			// 从借据利率上获取最大利率
			DataObject loanRate = EntityUtil.searchEntity(LoanTableName.TB_LOAN_LOANRATE, new String[] { "contractId", contractId }, new String[] { "yearRate", "DESC" });
			if (loanRate != null) {
				conMap.put("YEAR_RATE", loanRate.getString("yearRate"));
			} else {
				// 根据合同期限计算当前利率
				conMap.put("YEAR_RATE", accountRate(conMap, conRate));
			}
		}
		changeRate(conMap);
	}

	protected void changeRate(Map<String, Object> conMap) {
		String value = ConInfoLoadUtil.decode((String) conMap.get("IR_UPDATE_FREQUENCY"), null, new String[] { "01", "1", "02", "2", "04", "3" });
		conMap.put("IR_UPDATE_FREQUENCY", value);

		value = ConInfoLoadUtil.decode((String) conMap.get("INTEREST_COLLECT_TYPE"), "4", new String[] { "1", "1", "2", "2" });
		conMap.put("INTEREST_COLLECT_TYPE", value);
		if ("4".equals(value)) {
			conMap.put("INTEREST_COLLECT_TYPE_CN", conMap.get("INTEREST_COLLECT_TYPE"));
		}
	}

	public String accountRate(Map<String, Object> conMap, DataObject conRate) {
		if (conRate.getBigDecimal("rateFloatProportion") == null) {
			return null;
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("beginDate", (String) getNeedValue(conMap, "BEGIN_DATE"));
		param.put("endDate", (String) getNeedValue(conMap, "END_DATE"));
		String productType = (String) getNeedValue(conMap, "PRODUCT_TYPE");
		BigDecimal rateFloatProportion = conRate.getBigDecimal("rateFloatProportion").divide(new BigDecimal(100));
		Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.bizApply.bizApply.getLoanlength", param);
		if (objs == null || objs.length == 0) {
			return null;
		}
		int loanlength = ((DataObject) objs[0]).getInt("LOANLENGTH");
		String ratettype = "1";
		if ("02005001".equals(productType) || "02005003".equals(productType) || "02005010".equals(productType)) {
			ratettype = "2";
		}
		BigDecimal basicRate = new getBasicRate().getBasicrate(loanlength, ratettype);
		if ("2".equals(conRate.getString("floatWay"))) {
			basicRate = basicRate.subtract(basicRate.multiply(rateFloatProportion));
		} else {
			basicRate = basicRate.add(basicRate.multiply(rateFloatProportion));
		}
		basicRate = basicRate.setScale(4, BigDecimal.ROUND_HALF_UP);
		return basicRate.toString();
	}

	@Override
	public String getReportName(Map<String, Object> reportMap) {
		return getReportName();
	}

	public void changeCon(Map<String, Object> conMap) {
		// String partyType = (String) conMap.get("PARTY_TYPE");
		String value = (String) conMap.get("REPAYMENT_TYPE");
		// if (CsmUtil.isNatural(partyType)) {
		value = ConInfoLoadUtil.decode(value, "2", new String[] { "1100", "1" });
		// } else {
		// value = ConInfoLoadUtil.decode(value, "5", new String[] { "0100", "2", "0200", "1", "1100", "3", "1200", "4" });
		// }
		ConInfoLoadUtil.convertValue(conMap, "REPAYMENT_TYPE", value);
		if ("5".equals(value)) {
			conMap.put("REPAYMENT_TYPE_CN", value);
		}
		value = (String) conMap.get("PAY_WAY");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "0", "2", "1", "1" });
		ConInfoLoadUtil.convertValue(conMap, "PAY_WAY", value);

		value = (String) conMap.get("MAIN_GUARANTY_TYPE");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "04", "1", "02", "2", "03", "3" });
		ConInfoLoadUtil.convertValue(conMap, "MAIN_GUARANTY_TYPE", value);

		value = (String) conMap.get("ARBITRATE_TYPE");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "01", "1", "02", "2", "03", "3" });
		ConInfoLoadUtil.convertValue(conMap, "ARBITRATE_TYPE", value);

		value = (String) conMap.get("PREPAYMENT_PENALTY");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "1", "1", "0", "2" });
		ConInfoLoadUtil.convertValue(conMap, "PREPAYMENT_PENALTY", value);

	}

	@Override
	public void convert(Map<String, Object> reportMap) {
		super.convert(reportMap);
		for (String key : reportMap.keySet()) {
			if (key.equals("pays") || key.equals("repays")) {
				convertPlan(reportMap.get(key));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void convertPlan(Object plan) {
		if (plan instanceof List) {
			for (Object o : (List<Map<String, Object>>) plan) {
				convertPlan(o);
			}
		} else if (plan instanceof Map) {
			ConInfoLoadUtil.setDateFormat((Map<String, Object>) plan, new String[] { "REPAY_DATE" }, new String[] { "PAYOUT_DATE" });
		}
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		super.convertCon(conMap);
		// 金额转换为中文
		ConInfoLoadUtil.setMoneyToCN(conMap, new String[] { "CONTRACT_AMT", "CONTRACT_AMT_CN" });
		// 数字转换为中文
		ConInfoLoadUtil.setNumToCN(conMap, new String[] { "MAIN_GUARANTY_TYPE" });
		// 数字转换为带括号中文数字
		ConInfoLoadUtil.setNumToCNKH(conMap, new String[] { "RATE_TYPE" }, new String[] { "INTEREST_COLLECT_TYPE" }, new String[] { "PAY_WAY" });
		// new String[] { "ARBITRATE_TYPE" }
		// 字典数据转换
		ConInfoLoadUtil.setCodeByDict(conMap, new String[] { "FLOAT_WAY", "XD_SXCD1147" }, new String[] { "INTEREST_COLLECT_TYPE_CN", "XD_SXCD1018" }, //
				new String[] { "CYCLE_UNIT", "XD_GGCD6009" }, new String[] { "CURRENCY_CD", "CD000001" }, new String[] { "REPAYMENT_TYPE_CN", "XD_SXCD1162" });
	}

	public boolean contains(String productType) {
		return products.contains(productType);
	}

	public Set<String> getProducts() {
		return products;
	}

	public void setProducts(Set<String> products) {
		this.products = products;
	}

	public int getShowRepayNum() {
		return showRepayNum;
	}

	public void setShowRepayNum(int showRepayNum) {
		this.showRepayNum = showRepayNum;
	}

	public int getShowPayNum() {
		return showPayNum;
	}

	public void setShowPayNum(int showPayNum) {
		this.showPayNum = showPayNum;
	}
}
