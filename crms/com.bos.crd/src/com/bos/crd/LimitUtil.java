package com.bos.crd;

import java.util.HashMap;
import java.util.Map;

import com.bos.crd.valid.TreeCache;
import com.eos.foundation.database.DatabaseExt;
import com.sun.star.uno.RuntimeException;

import edu.emory.mathcs.backport.java.util.Arrays;

public class LimitUtil {

	public static enum GROUP_TYPE {
		// XD_SXYW0230
		SINGLE("01"), GROUP("02");

		private String key;

		private GROUP_TYPE(String key) {
			this.key = key;
		}

		public String toString() {
			return key;
		};

		public String value() {
			return key;
		}

		public static GROUP_TYPE get(String key) {
			GROUP_TYPE[] temps = values();
			for (GROUP_TYPE t : temps) {
				if (t.value().equals(key)) {
					return t;
				}
			}
			throw new RuntimeException("错误的类型参数[" + key + "]");
		}
	}

	public enum LIMIT_TYPE {
		ORG("10"), PRODUCT("20"), GUARANTY("30"), TRADE("90");

		private String key;

		private LIMIT_TYPE(String key) {
			this.key = key;
		}

		public String toString() {
			return key;
		};

		public String value() {
			return key;
		}

		public static LIMIT_TYPE get(String key) {
			LIMIT_TYPE[] temps = values();
			for (LIMIT_TYPE t : temps) {
				if (t.value().equals(key)) {
					return t;
				}
			}
			throw new RuntimeException("错误的类型参数[" + key + "]");
		}
	}

	private final static Map<LIMIT_TYPE, Object[][]> limitTypeMap = new HashMap<LIMIT_TYPE, Object[][]>();

	static {
		// 配置纬度
		// Object[0] 风险限额查询条件，存在树形数据时，第二参数从树形中获取父节点，不存在时直接从param中获取数据
		// Object[1] 查询当前借据金额条件，第一个参数为sqlName，后面参数将直接从param中获取并组装成新map作为查询条件
		limitTypeMap.put(LIMIT_TYPE.ORG, new Object[][] { { "com.bos.crd.LimitValid.searchOrgs", "ORG_NUM" }, { "com.bos.crd.LimitValid.sumLoanBal_org" } });
		limitTypeMap.put(LIMIT_TYPE.PRODUCT, new Object[][] { { "com.bos.crd.LimitValid.searchProducts", "PRODUCT_TYPE" }, { "com.bos.crd.LimitValid.sumLoanBal_product" } });
		limitTypeMap.put(LIMIT_TYPE.TRADE, new Object[][] { { "com.bos.crd.LimitValid.searchTrades", "LOAN_TURN" }, { "com.bos.crd.LimitValid.sumLoanBal_trade" } });
		limitTypeMap.put(LIMIT_TYPE.GUARANTY, new Object[][] { { null, "GARANTY_TYPE" }, { "com.bos.crd.LimitValid.sumLoanBal_guaranty" } });
	}

	public static Object[][] getTypeConfig(LIMIT_TYPE type) {
		Object[][] temps = limitTypeMap.get(type);
		if (temps == null) {
			throw new RuntimeException("风险限额类型[" + type + "]尚未配置相应参数");
		}
		Object[][] newObjs = new Object[temps.length][];
		for (int i = 0; i < temps.length; i++) {
			newObjs[i] = Arrays.copyOf(temps[i], temps[i].length);
		}
		return newObjs;
	}

	public static String getSqlStr(LIMIT_TYPE type, String code, String tableTitle) {
		return getSqlStr(type, code, null, true, tableTitle);
	}

	/**
	 * 
	 * @param type
	 * @param code
	 * @param cache
	 *            查询树形结构
	 * @param parent
	 *            cache!=null 情况下 true 包含父节点，false包含子节点
	 * @return
	 */
	public static String getSqlStr(LIMIT_TYPE type, String code, TreeCache cache, boolean parent, String tableTitle) {
		if (tableTitle == null) {
			tableTitle = "";
		} else {
			tableTitle += ".";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" ( ").append(tableTitle).append("LIMIT_TYPE='").append(type).append("'");

		if (code != null) {
			if (cache == null) {
				sb.append(" AND ").append(tableTitle).append("LIMIT_CODE ='").append(code).append("'");
			} else if (!parent && cache.isRoot(code)) {
				// 如果是根节点不需要参数
			} else {
				String[] codes = parent ? cache.getParents(code) : cache.getSons(code);
				if (codes != null && codes.length != 0) {
					sb.append(" AND ").append(tableTitle).append("LIMIT_CODE IN (");
					for (int i = 0; i < codes.length; i++) {
						sb.append(i == 0 ? "'" : ",'").append(codes[i]).append("'");
					}
					sb.append(")");
				}
			}
		}
		sb.append(")");
		return sb.toString();
	}

	private final static Map<String, TreeCache> cacheMap = new HashMap<String, TreeCache>();

	public static TreeCache getTreeCache(String dbName, String treeSqlName, long cacheTime) {
		String key = dbName + "_" + treeSqlName;
		TreeCache treeCache;
		// 0：不要缓存 ，-1：一直缓存 ,>0缓存秒数
		if (cacheTime == 0 || (treeCache = cacheMap.get(key)) == null || (cacheTime > 0 && System.currentTimeMillis() - treeCache.getCreateTime() > cacheTime * 1000)) {
			treeCache = new TreeCache(DatabaseExt.queryByNamedSql(dbName, treeSqlName, null));
			cacheMap.put(key, treeCache);
		}
		return treeCache;
	}
}
