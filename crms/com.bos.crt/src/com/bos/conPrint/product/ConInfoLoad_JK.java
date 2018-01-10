package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.csm.pub.CsmUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

public class ConInfoLoad_JK extends ConInfoLoad_Product {

	private String[][] reprotNameCns;

	@Override
	public String getReportNameCN(String... params) {
		String productType = params[0];
		if (reprotNameCns == null && super.getReportNameCN(productType) != null) {
			synchronized (this) {
				if (reprotNameCns == null && super.getReportNameCN(productType) != null) {
					String[] temps = super.getReportNameCN(productType).split(",");
					reprotNameCns = new String[temps.length][];
					for (int i = 0; i < temps.length; i++) {
						String[] ts = temps[i].split("_");
						if (ts.length != 2) {
							continue;
						}
						reprotNameCns[i] = ts;
					}
				}
			}
		}
		for (int i = 0; i < reprotNameCns.length; i++) {
			if (reprotNameCns[i] != null && productType.startsWith(reprotNameCns[i][0])) {
				return reprotNameCns[i][1];
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getReportName(Map<String, Object> reportMap) {
		Map<String, Object> map = (Map<String, Object>) reportMap.get(REPORT_A);
		String partyType = (String) getNeedValue(map, "PARTY_TYPE");
		String name = getReportName() + (CsmUtil.isNatural(partyType) ? "_2" : "_1");
		map = (Map<String, Object>) reportMap.get(REPORT_CON);
		name += "_" + getNeedValue(map, "CYCLE_IND_CON");
		return name + ".docx";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setConInfo(Map<String, Object> conMap) {
		super.setConInfo(conMap);
		Map<String, String> param = new HashMap<String, String>();
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		param.put("zhlx", "1");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_ZH", param);
		if (datas != null && datas.length > 0) {
			conMap.put("ZJHLZH", ((Map<String, String>) datas[0]).get("ZH"));
		}
	}
}
