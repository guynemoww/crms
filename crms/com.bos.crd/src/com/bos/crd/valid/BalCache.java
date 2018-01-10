package com.bos.crd.valid;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BalCache {

	private Map<String, BigDecimal> balMap;

	public BalCache(Object[] objs) {
		loading(objs);
	}

	@SuppressWarnings("unchecked")
	private void loading(Object[] objs) {
		balMap = new HashMap<String, BigDecimal>();
		for (Object obj : objs) {
			Map<String, Object> objMap = (Map<String, Object>) obj;
			String code = (String) objMap.get("CODE");
			if (code == null) {
				continue;//历史数据存在没有code的数据，所以不检查数据完整性
//				throw new RuntimeException("需要统计的数据必须包含[CODE]字段");
			}
			BigDecimal bal = (BigDecimal) objMap.get("BAL");
			if (bal == null) {
				throw new RuntimeException("需要统计的数据必须包含[BAL]字段");
			}
			BigDecimal tempBal = balMap.get(code);
			if (tempBal == null) {
				tempBal = bal;
			} else {
				tempBal = tempBal.add(bal);
			}
			balMap.put(code, tempBal);
		}
	}

	public BigDecimal getBal(String... codes) {
		BigDecimal bal = BigDecimal.ZERO;
		for (String code : codes) {
			BigDecimal temp = balMap.get(code);
			if (temp == null) {
				continue;
			}
			bal = bal.add(temp);
		}
		return bal;
	}
}
