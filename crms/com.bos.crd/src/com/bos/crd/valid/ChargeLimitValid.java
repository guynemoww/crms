package com.bos.crd.valid;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bos.crd.LimitServiceImp;
import com.bos.crd.LimitUtil.GROUP_TYPE;
import com.bos.pub.entity.name.CrdTableName;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

public class ChargeLimitValid implements LimitValid {

	@SuppressWarnings("unchecked")
	public void valid(Map<String, Object> param) {
		Map<String, Object> tempMap = getChargeValidInfo((String) param.get("APPLY_ID"));
		GROUP_TYPE limitType = GROUP_TYPE.get((String) tempMap.get("JG_LIMIT_TYPE"));
		BigDecimal partyAmt = (BigDecimal) tempMap.get("AMT");
		// 根据监管类型查询监管限额
		DataObject limit = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_LIMIT2);
		limit.set("jgLimitType", limitType);
		if (DatabaseUtil.expandEntityByTemplate(getDBName(), limit, limit) == 0) {
			return;
		}
		BigDecimal limitAmt = limit.getBigDecimal("limitAmt");
		// 监管限额为0时代表不监管
		if (limitAmt.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		param.clear();
		param.put("groupType", limitType.value());
		Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitValid.sumLoanBal", param);
		for (Object temp : temps) {
			partyAmt = partyAmt.add((BigDecimal) ((Map<String, Object>) temp).get("AMT"));
		}
		if (partyAmt.compareTo(limitAmt) > 0) {
			throw new RuntimeException("该客户的有效批复金额已经超过监管限额");
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getChargeValidInfo(String applyId) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("applyId", applyId);
		Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitService.getChargeValidInfo", tempMap);
		tempMap = (Map<String, Object>) temps[0];
		String temp = (String) tempMap.get("IS_GROUP_CUST");
		if (temp != null) {
			GROUP_TYPE jgLimitType = "1".equals(temp) ? GROUP_TYPE.GROUP : GROUP_TYPE.SINGLE;
			tempMap.put("JG_LIMIT_TYPE", jgLimitType);
		}
		return tempMap;
	}

	private String getDBName() {
		return "default";
	}
}
