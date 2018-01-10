package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ConInfoLoad_CDXY extends ConInfoLoad_Product {

	@SuppressWarnings("unchecked")
	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null) {
			return;
		}
		String detailId = (String) conMap.get("AMOUNT_DETAIL_ID");
		if (detailId != null) {
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getConInfo_PJXX", detailId);
			List<Object> dataList = Arrays.asList(datas);
			if (dataList != null && dataList.size() > 0) {
				reportMap.put("arraypj", dataList);
			}
		}
		// 根据账户类型分别存入账户信息
		Map<String, String> param = new HashMap<String, String>();
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_BZJZH", param);
		if (datas != null && datas.length > 0) {
			reportMap.put("zh_bzj", (Map<String, Object>) datas[0]);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getReportName(Map<String, Object> dataMap) {
		Map<String, Object> map = (Map<String, Object>) dataMap.get(REPORT_CON);
		String name = getReportName() + "_" + getNeedValue(map, "CYCLE_IND_CON");
		return name + ".docx";
	}
}
