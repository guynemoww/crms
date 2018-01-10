package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

public class ConInfoLoad_GDZC extends ConInfoLoad_Product {

	@SuppressWarnings("unchecked")
	protected void setConInfo(Map<String, Object> conMap) {
		super.setConInfo(conMap);
		Map<String, String> param = new HashMap<String, String>();
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_ZH", param);
		if (datas != null && datas.length > 0) {
			for (Object obj : datas) {
				// 优先使用还款准备金账户，没有使用第一还款账户
				if ("8".equals(((Map<String, String>) obj).get("ZHLX"))) {
					conMap.put("HKZBJZH", ((Map<String, String>) obj).get("ZH"));
					break;
				} else if ("1".equals(((Map<String, String>) obj).get("ZHLX"))) {
					conMap.put("HKZBJZH", ((Map<String, String>) obj).get("ZH"));
				}
			}
		}
	}
}
