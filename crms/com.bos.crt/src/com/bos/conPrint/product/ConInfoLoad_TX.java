package com.bos.conPrint.product;

import java.util.Map;

import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ConInfoLoad_TX extends ConInfoLoad_Product {

	@SuppressWarnings("unchecked")
	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null) {
			return;
		}
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getCon_TXXX", (String) getNeedValue(conMap, "CONTRACT_ID"));
		if (datas != null && datas.length > 0) {
			reportMap.put("txs", Arrays.asList(datas));
		}
	}

	@Override
	public void changeCon(Map<String, Object> conMap) {
		// TODO 自动生成的方法存根
		super.changeCon(conMap);
		String value = (String) conMap.get("HPZL");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "0", "1", "1", "2" });
		conMap.put("HPZL", value);

		value = (String) ConInfoLoadUtil.getValueOld(conMap, "MAIN_GUARANTY_TYPE");
		value = ConInfoLoadUtil.decode(value, null, new String[] { "01", "1", "04", "2", "02", "3", "03", "4" });
		conMap.put("MAIN_GUARANTY_TYPE", value);
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		// TODO 自动生成的方法存根
		super.convertCon(conMap);

		conMap.put("MAIN_GUARANTY_TYPE", ConInfoLoadUtil.getValueOld(conMap, "MAIN_GUARANTY_TYPE"));
		// 时间数据转换
		ConInfoLoadUtil.setDateFormat(conMap, new String[] { "KLRQ" }, new String[] { "DQRQ" });
		// 字典数据转换
		ConInfoLoadUtil.setCodeByDict(conMap, new String[] { "BHZL", "XD_SXYW0207" });
	}
}
