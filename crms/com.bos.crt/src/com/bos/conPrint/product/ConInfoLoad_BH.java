package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

public class ConInfoLoad_BH extends ConInfoLoad_Product {

	@SuppressWarnings("unchecked")
	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null) {
			return;
		}
		// 保函期限方式
		conMap.put("BHQXFS", "2");
		String conId = (String) conMap.get("CONTRACT_ID");
		// 根据账户类型分别存入账户信息
		if (conId != null) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("contractId", conId);
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_BZJZH", param);
			if (datas != null && datas.length > 0) {
				conMap.put("BZJ_ZH", ((Map<String, String>) datas[0]).get("MARGIN_ACCOUNT"));
				conMap.put("BZJ_OPEN_BANK_CN", ((Map<String, String>) datas[0]).get("OPEN_BANK_CN"));
			}
		}
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		// TODO 自动生成的方法存根
		super.convertCon(conMap);
		ConInfoLoadUtil.setNumToCNKH(conMap, new String[] { "BHQXFS" });
		// 时间数据转换
		ConInfoLoadUtil.setDateFormat(conMap, new String[] { "KLRQ" }, new String[] { "DQRQ" });
		// 字典数据转换
		ConInfoLoadUtil.setCodeByDict(conMap, new String[] { "BHZL", "XD_SXYW0207" });
	}
}
