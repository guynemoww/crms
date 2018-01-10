package com.bos.pub.credit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;

public class CreditAmtReckon {
	/**
	 * 计算批复占用额度
	 * 
	 * @param map
	 * @return
	 */
	public static BigDecimal getOccupyAppAmt(List<Map<String, Object>> dataList) {
		BigDecimal conOccupy = BigDecimal.ZERO;
		BigDecimal minConBzjbl = hundred;
		for (Map<String, Object> map : dataList) {
			conOccupy = conOccupy.add(getOccupyConAmt(map, true));
			BigDecimal temp = getValue(map, "CON_BZJBL", hundred);
			if (minConBzjbl.compareTo(temp) > 0) {
				minConBzjbl = temp;
			}
		}
		Map<String, Object> map = dataList.get(0);
		map.put("CON_OCCUPY", conOccupy);
		BigDecimal temp = getValue(map, "APP_BZJBL", hundred);
		if (temp.compareTo(minConBzjbl) > 0) {
			// 当合同最小保证金比例大于批复保证金比例，以合同保证金比例为准
			map.put("APP_BZJBL", minConBzjbl);
		}
		return _getOccupyAppAmt(map);
	}

	public static BigDecimal getAppBZJBL(List<Map<String, Object>> dataList) {
		if (dataList == null || dataList.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal minConBzjbl = hundred;
		for (Map<String, Object> map : dataList) {
			BigDecimal temp = getValue(map, "CON_BZJBL", hundred);
			if (minConBzjbl.compareTo(temp) > 0) {
				minConBzjbl = temp;
			}
		}
		BigDecimal temp = getValue(dataList.get(0), "APP_BZJBL", hundred);
		return temp.compareTo(minConBzjbl) > 0 ? minConBzjbl : temp;
	}

	public static BigDecimal _getOccupyAppAmt(Map<String, Object> map) {
		BigDecimal appAmt = getValue(map, "APP_AMT", BigDecimal.ZERO);
		BigDecimal conOccupy = getValue(map, "CON_OCCUPY", BigDecimal.ZERO);
		// 合同占用额度大于批复占用额度 或者 批复向下存在合同占用额度，批复如果失效，合同占用额度作为批复占用额度
		if (conOccupy.compareTo(appAmt) >= 0 || GitUtil.contain(getValue(map, "APP_STATUS", "03"), new String[] { "04", "05", "06", "08" })) {
			return conOccupy;
		}
		appAmt = appAmt.subtract(conOccupy);
		BigDecimal appBzjbl = quotiety(getValue(map, "APP_BZJBL", BigDecimal.ZERO));
		return appAmt.multiply(appBzjbl).add(conOccupy);
	}

	/**
	 * 计算批复可用额度
	 * 
	 * @param map
	 * @return
	 */
	public static BigDecimal getCanUseAppAmt(List<Map<String, Object>> dataList) {
		BigDecimal conOccupy = BigDecimal.ZERO;
		BigDecimal conAssoil = BigDecimal.ZERO;
		for (Map<String, Object> map : dataList) {
			conOccupy = conOccupy.add(getOccupyConAmt(map, new String[] { "06" }, false));
			// 只有结清状态的合同才返还占用额度，否则一直占用
			if (conIsSettle(map)) {
				conAssoil = conAssoil.add(conOccupy);
			}
		}
		Map<String, Object> map = dataList.get(0);
		map.put("CON_OCCUPY", conOccupy);
		map.put("CON_ASSOIL", conAssoil);
		return _getCanUseAppAmt(map);
	}

	public static BigDecimal _getCanUseAppAmt(Map<String, Object> map) {
		if (GitUtil.contain(getValue(map, "APP_STATUS", "03"), new String[] { "01", "02", "04", "05", "06", "07", "08" })) {
			return BigDecimal.ZERO;
		}
		BigDecimal appAmt = getValue(map, "APP_AMT", BigDecimal.ZERO);
		// 扣除名下的合同申请金额
		BigDecimal conOccupy = getValue(map, "CON_OCCUPY", BigDecimal.ZERO);
		if (BigDecimal.ZERO.compareTo(conOccupy) > 0) {
			conOccupy = BigDecimal.ZERO;
		}
		appAmt = appAmt.subtract(conOccupy);
		// 当合同占用大于批复占用，可以判断为数据错误，直接返回负数以便后期处理bug
		if (BigDecimal.ZERO.compareTo(appAmt) > 0) {
			return appAmt;
		}
		// 非循环的批复额度只有在批复失效时才释放
		if ("1".equals(getValue(map, "APP_CYCLE", "0"))) {
			BigDecimal conAssoil = getValue(map, "CON_ASSOIL", BigDecimal.ZERO);
			appAmt = appAmt.compareTo(conAssoil) > 0 ? appAmt.add(conAssoil) : appAmt;
		}
		return appAmt;
	}

	/**
	 * 计算合同占用额度
	 * 
	 * @param map
	 * @return
	 */
	public static BigDecimal getOccupyConAmt(Map<String, Object> map, boolean revert) {
		return getOccupyConAmt(map, new String[] { "04", "05", "06" }, revert);
	}

	public static BigDecimal getOccupyConAmt(Map<String, Object> map, String[] filters, boolean revert) {
		BigDecimal conBzjbl = getConBZJBL(map);// 放在这是为了计算保证金比例
		if (GitUtil.contain(getValue(map, "CON_STATUS", "03"), filters)) {
			// 合同失效情况下返回借据占用额度
			return getValue(map, "SUM_BAL", BigDecimal.ZERO);
		}
		// 合同没有结清状态，当借据金额大于等于合同时代表合同已全部放款，此时借据余额为0可代表合同已结清
		if (revert && conIsSettle(map)) {
			return BigDecimal.ZERO;
		}
		BigDecimal conAmt = getValue(map, "CON_AMT", BigDecimal.ZERO);
		return conAmt.multiply(quotiety(conBzjbl));
	}

	/**
	 * 判断合同是否结清
	 * 
	 * @param map
	 * @return
	 */
	public static boolean conIsSettle(Map<String, Object> map) {
		BigDecimal conAmt = getValue(map, "CON_AMT", BigDecimal.ZERO);
		BigDecimal sumAmt = getValue(map, "SUM_AMT", BigDecimal.ZERO);
		BigDecimal sumBal = getValue(map, "SUM_BAL", sumAmt);
		// 合同没有结清状态，当借据金额大于等于合同时代表合同已全部放款，此时借据余额为0可代表合同已结清
		return sumAmt.compareTo(conAmt) >= 0 && BigDecimal.ZERO.compareTo(sumBal) >= 0;
	}

	/**
	 * 计算合同可用额度
	 * 
	 * @param map
	 * @return
	 */
	public static BigDecimal getCanUseConAmt(Map<String, Object> map) {
		if (GitUtil.contain(getValue(map, "CON_STATUS", "03"), new String[] { "01", "02", "04", "05", "06", "07", "08" })) {
			return BigDecimal.ZERO;
		}
		BigDecimal conAmt = getValue(map, "CON_AMT", BigDecimal.ZERO);
		BigDecimal sumAmt = getValue(map, "SUM_AMT", BigDecimal.ZERO);
		BigDecimal sumBal = getValue(map, "SUM_BAL", sumAmt);// 没有借据余额数据默认为借据金额
		if (sumBal.compareTo(sumAmt) > 0) {
			sumBal = sumAmt;
		}
		if ("1".equals(getValue(map, "CON_CYCLE", "0"))) {
			conAmt = conAmt.subtract(sumBal);
		} else {
			conAmt = conAmt.subtract(sumAmt);
		}
		return conAmt;
	}

	public static final BigDecimal hundred = new BigDecimal(100.00);

	public static BigDecimal getConBZJBL(Map<String, Object> map) {
		BigDecimal conBzjbl = (BigDecimal) map.get("CON_BZJBL");
		if (conBzjbl == null) {
			BigDecimal conBzjje = getValue(map, "CON_BZJJE", BigDecimal.ZERO);
			if (conBzjje.compareTo(BigDecimal.ZERO) > 0) {
				conBzjbl = conBzjje.divide(getValue(map, "CON_AMT", BigDecimal.ZERO), 8, BigDecimal.ROUND_HALF_UP).multiply(hundred);
			} else {
				conBzjbl = BigDecimal.ZERO;
			}
			// 记录合同保证金比例
			map.put("CON_BZJBL", conBzjbl);
		}
		return conBzjbl;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(Map<String, Object> map, String key, T def) {
		Object value = map.get(key);
		return value == null ? def : (T) value;
	}

	public static BigDecimal quotiety(BigDecimal bzjbl) {
		if (BigDecimal.ZERO.compareTo(bzjbl) >= 0) {
			return BigDecimal.ONE;
		} else if (bzjbl.compareTo(hundred) > 0) {
			return BigDecimal.ZERO;
		}
		bzjbl = bzjbl.setScale(8);
		bzjbl = hundred.subtract(bzjbl).divide(hundred);
		return bzjbl;
	}
}
