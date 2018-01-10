package com.bos.conPrint.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bos.conPrint.ConInfoLoadService;
import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

public class BigType_ZQ extends ConInfoLoad_Product {
	private ConInfoLoadService conInfoLoadService;

	@Override
	protected void loadConSub(Map<String, Object> conMap) {
		// 不需要原来的数据了
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null) {
			return;
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		param.put("searchMode", "zq");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getConSubInfo", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Set<String> partys = new HashSet<String>();
		for (int i = 0, j = 1; i < datas.length; i++) {
			Map<String, Object> subMap = (Map<String, Object>) datas[i];
			String temp = conInfoLoadService.getReportNameCN((String) getNeedValue(subMap, "SUBCONTRACT_TYPE"), (String) getNeedValue(subMap, "PARTY_TYPE_CD"), (String) getNeedValue(subMap, "IF_TOP_SUBCON"));
			subMap.put("REPORT_NAME_CN", temp);
			dataList.add(subMap);
			String partyId = getNeedValue(subMap, "PARTY_ID");
			if (j < 4 && !partys.contains(partyId)) {
				partys.add(partyId);
				reportMap.put("sub" + (j++), loadParty(partyId));
			}
		}
		reportMap.put("sub", dataList);
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		// TODO 自动生成的方法存根
		super.convertCon(conMap);
		// 金额转换为中文
		ConInfoLoadUtil.setMoneyToCN(conMap, new String[] { "SUMMARY_AMT", "SUMMARY_AMT_CN" }, new String[] { "OLD_SUMMARYAMT", "OLD_SUMMARYAMT_CN" });
		// 时间数据转换
		ConInfoLoadUtil.setDateFormat(conMap, new String[] { "LOAN_END_DATE_OLD" }, new String[] { "LOAN_END_DATE_NEW" });
		// 字典数据转换
		ConInfoLoadUtil.setCodeByDict(conMap, new String[] { "BHZL", "XD_SXYW0207" });
	}

	public ConInfoLoadService getConInfoLoadService() {
		return conInfoLoadService;
	}

	public void setConInfoLoadService(ConInfoLoadService conInfoLoadService) {
		this.conInfoLoadService = conInfoLoadService;
	}

}
