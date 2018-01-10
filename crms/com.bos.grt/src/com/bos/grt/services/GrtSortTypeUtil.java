package com.bos.grt.services;

import java.util.HashMap;
import java.util.Map;

public class GrtSortTypeUtil {

	public Map<String, String> typeMap;
	public Map<String, String> aliasMap;

	/**
	 * 是否为动产
	 * 
	 * @param sortType
	 * @return
	 */
	public boolean isDynamicAsset(String sortType) {
		return isType("dynamic", sortType);
	}

	/**
	 * 是否为票据类
	 * 
	 * @param sortType
	 * @return
	 */
	public boolean isBill(String sortType) {
		return isType("bill", sortType);
	}

	/**
	 * 是否为应收账款
	 * 
	 * @param sortType
	 * @return
	 */
	public boolean isYSZK(String sortType) {
		return isType("yszk", sortType);
	}

	/**
	 * 是否为知识产权
	 * 
	 * @param sortType
	 * @return
	 */
	public boolean isZSCQ(String sortType) {
		return isType("zscq", sortType);
	}

	public Map<String, String> getAlias(String sortType) {
		String temp = aliasMap.get(sortType);
		Map<String, String> map = new HashMap<String, String>();
		if (temp == null || temp.isEmpty()) {
			return map;
		}
		String[] temps = temp.split(",");
		for (String t : temps) {
			String[] ts = t.split("[|]");
			if (ts.length > 1) {
				map.put(ts[0], ts[1]);
			}
		}
		return map;
	}

	/**
	 * 是否为股票
	 * 
	 * @param type
	 * @param sortType
	 * @return
	 */
	public boolean isStock(String type, String sortType) {
		return isType("stock", sortType);
	}

	public boolean isType(String type, String sortType) {
		String v = "," + typeMap.get(sortType) + ",";
		return v.contains("," + type + ",");
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}

	public Map<String, String> getAliasMap() {
		return aliasMap;
	}

	public void setAliasMap(Map<String, String> aliasMap) {
		this.aliasMap = aliasMap;
	}

}
