package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.csm.pub.CsmUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;

import commonj.sdo.DataObject;

public class ConInfoLoad_WT extends ConInfoLoad_Product {

	private final static String REPORT_C1 = "c_1";
	private final static String REPORT_C2 = "c_2";

	@SuppressWarnings("unchecked")
	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		Map<String, String> param = new HashMap<String, String>();
		param.put("PRODUCT_TYPE", (String) conMap.get("PRODUCT_TYPE"));
		param.put("AMOUNT_DETAIL_ID", (String) conMap.get("AMOUNT_DETAIL_ID"));
		Map<String, Object> cMap = loadC(param);
		if (cMap != null) {
			if (CsmUtil.isNatural((String) cMap.get("PARTY_TYPE"))) {
				reportMap.put(REPORT_C2, cMap);
			} else {
				reportMap.put(REPORT_C1, cMap);
			}
		}
	}

	@Override
	protected void setConInfo(Map<String, Object> conMap) {
		super.setConInfo(conMap);
		// 发放方式
		conMap.put("FFFS", "1");
	}

	@Override
	public void changeCon(Map<String, Object> conMap) {
		super.changeCon(conMap);
		String value = (String) ConInfoLoadUtil.getValueOld(conMap, "MAIN_GUARANTY_TYPE");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "01", "1", "04", "2", "02", "3", "03", "4" });
		conMap.put("MAIN_GUARANTY_TYPE", value);
	}

	public Map<String, Object> loadC(Map<String, String> param) {
		DataObject product = EntityUtil.searchEntity(BizTableName.TB_BIZ_PRODUCT_INFO, "productCd", (String) getNeedValue(param, "PRODUCT_TYPE"));
		if (product != null && product.getString("entityName") != null) {
			DataObject obj = EntityUtil.searchEntity(product.getString("entityName"), "amountDetailId", (String) getNeedValue(param, "AMOUNT_DETAIL_ID"));
			if (obj != null && obj.getString("wtr") != null) {
				return loadParty(obj.getString("wtr"));
			}
		}
		return null;
	}

	@Override
	protected void changeRate(Map<String, Object> conMap) {
		String value = ConInfoLoadUtil.decode((String) conMap.get("IR_UPDATE_FREQUENCY"), null, new String[] { "01", "1", "02", "2", "04", "3" });
		conMap.put("IR_UPDATE_FREQUENCY", value);

		value = ConInfoLoadUtil.decode((String) conMap.get("INTEREST_COLLECT_TYPE"), "5", new String[] { "1", "1", "2", "2", "4", "3" });
		conMap.put("INTEREST_COLLECT_TYPE", value);
		if ("5".equals(value)) {
			conMap.put("INTEREST_COLLECT_TYPE_CN", conMap.get("INTEREST_COLLECT_TYPE"));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void convert(Map<String, Object> reportMap) {
		super.convert(reportMap);
		Map<String, Object> cMap = (Map<String, Object>) reportMap.get(REPORT_C1);
		if (cMap != null || (cMap = (Map<String, Object>) reportMap.get(REPORT_C2)) != null) {
			ConInfoLoadUtil.convertData(cMap);
		}
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		super.convertCon(conMap);
		String[] keys = new String[] { "PAY_WAY", "RATE_TYPE", "INTEREST_COLLECT_TYPE", "MAIN_GUARANTY_TYPE" };
		for (String k : keys) {
			conMap.put(k, ConInfoLoadUtil.getValueOld(conMap, k));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getReportName(Map<String, Object> dataMap) {
		Map<String, Object> map = (Map<String, Object>) dataMap.get(REPORT_A);
		String partyType = (String) getNeedValue(map, "PARTY_TYPE");
		String name = getReportName() + (CsmUtil.isNatural(partyType) ? "_2" : "_1");
		return name + ".docx";
	}
}
