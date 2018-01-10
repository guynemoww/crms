package com.bos.crd.valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.crd.LimitUtil;
import com.bos.crd.LimitUtil.LIMIT_TYPE;
import com.bos.pub.DictContents;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.sun.star.uno.RuntimeException;

import edu.emory.mathcs.backport.java.util.Arrays;

public class RiskLimitValid implements LimitValid {
	private long cacheTime = 600;// 缓存10分钟

	private Map<LIMIT_TYPE, Object[][]> getTypeConfig(LIMIT_TYPE... filters) {
		// 配置纬度
		// Object[0] 风险限额查询条件，存在树形数据时，第二参数从树形中获取父节点，不存在时直接从param中获取数据
		// Object[1] 查询当前借据金额条件，第一个参数为sqlName，后面参数将直接从param中获取并组装成新map作为查询条件
		Map<LIMIT_TYPE, Object[][]> limitTypeMap = new HashMap<LIMIT_TYPE, Object[][]>();
		LIMIT_TYPE[] types = LIMIT_TYPE.values();
		for (LIMIT_TYPE type : types) {
			Object[][] configs = LimitUtil.getTypeConfig(type);
			for (LIMIT_TYPE f : filters) {
				if (f == type) {
					continue;
				}
			}
			// 存在树形结构，查询树形数据备用
			configs[0][0] = configs[0][0] == null ? null : LimitUtil.getTreeCache(getDBName(), (String) configs[0][0], cacheTime);// 考虑是否使用缓存
			limitTypeMap.put(type, configs);
		}
		return limitTypeMap;
	}

	private String[][] biyaoshuju = { { "AMT", "请传入正确的额度数据" } };

	// 可选项:ORG_NUM,PRODUCT_TYPE,LOAN_TURN,GARANTY_TYPE
	public void valid(Map<String, Object> param) {
		for (String[] xxx : biyaoshuju) {
			if (param.get(xxx[0]) == null) {
				throw new RuntimeException(xxx[1]);
			}
		}
		// long start = System.currentTimeMillis();
		Map<LIMIT_TYPE, Object[][]> limitTypeMap = getTypeConfig();
		// System.out.println("getTypeConfig:" + (System.currentTimeMillis() - start));
		// 查询需要比较的风险限额数据
		Map<LIMIT_TYPE, List<Object[]>> limitMap = searchRiskLimit(limitTypeMap, param);
		if (limitMap.isEmpty()) {
			return;
		}
		for (Map.Entry<LIMIT_TYPE, List<Object[]>> limit : limitMap.entrySet()) {
			LIMIT_TYPE limitType = limit.getKey();
			Object xxx = param.get("AMT");
			if ("unsight".equals(xxx)) {
				continue;
			}
			BigDecimal amt = xxx == null ? BigDecimal.ZERO : (xxx instanceof BigDecimal ? (BigDecimal) xxx : new BigDecimal((String) xxx));// 当前需要借贷的金额
			Object[][] limitTypeObj = limitTypeMap.get(limitType);
			if (limitTypeObj[1][0] == null) {
				throw new RuntimeException("请配置风险限额类型[" + limitType + "]的查询条件");
			}
			Map<String, Object> tempMap = new HashMap<String, Object>();
			// 根据参数筛选查询条件
			for (int i = 1; i < limitTypeObj[1].length; i++) {
				tempMap.put((String) limitTypeObj[1][i], param.get(limitTypeObj[1][i]));
			}
			Object[] objs = DatabaseExt.queryByNamedSql(getDBName(), (String) limitTypeObj[1][0], tempMap);
			if (objs == null || objs.length == 0) {
				continue;
			}
			BalCache balCache = new BalCache(objs);
			TreeCache treeCache = (TreeCache) limitTypeObj[0][0];
			for (Object[] temps : limit.getValue()) {
				BigDecimal bal;
				if (treeCache == null) {
					bal = balCache.getBal((String) temps[0]);
				} else {
					bal = balCache.getBal(treeCache.getSons((String) temps[0]));
				}
				amt = amt.add(bal);
				if (((BigDecimal) temps[1]).compareTo(BigDecimal.ZERO) != 0 && amt.compareTo(((BigDecimal) temps[1])) > 0) {
					String dictName = BusinessDictUtil.getDictName("XD_SXYW0238", limit.getKey().value());
					throw new RuntimeException("{limitType:'" + limitType.value() + "',limitCode:'" + temps[0] + "',msg:'超过[" + dictName + "]'}");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map<LIMIT_TYPE, List<Object[]>> searchRiskLimit(Map<LIMIT_TYPE, Object[][]> limitTypeMap, Map<String, Object> param) {
		String whereSql = searchRiskLimitWhereSql(limitTypeMap, param);
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("whereSql", whereSql);
		if (param.get("ORG_NUM") != null) {
			// 存在机构的时候需要根据机构所在组限制组的数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("limitType", LIMIT_TYPE.ORG.value());
			TreeCache cache = LimitUtil.getTreeCache(getDBName(), (String) LimitUtil.getTypeConfig(LIMIT_TYPE.ORG)[0][0], cacheTime);
			String[] codes = cache.getParents((String) param.get("ORG_NUM"));
			map.put("limitCodes", Arrays.asList(codes));
			Object[] objs = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitValid.searchRiskGroupId", map);
			if (objs != null && objs.length > 0) {
				tempMap.put("groupIds", Arrays.asList(objs));
			}
		}
		Object[] ojbs = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitValid.searchRiskLimit", tempMap);
		Map<LIMIT_TYPE, List<Object[]>> map = new HashMap<LIMIT_TYPE, List<Object[]>>();
		for (Object obj : ojbs) {
			tempMap = (Map<String, Object>) obj;
			LIMIT_TYPE type = LIMIT_TYPE.get((String) tempMap.get("LIMIT_TYPE"));
			List<Object[]> tempList = map.get(type);
			if (tempList == null) {
				tempList = new ArrayList<Object[]>();
				map.put(type, tempList);
			}
			tempList.add(new Object[] { tempMap.get("LIMIT_CODE"), (BigDecimal) tempMap.get("AMT") });
		}
		return map;
	}

	/**
	 * 根据配置信息组装sql
	 * 
	 * @param limitTypeMap
	 * @param param
	 * @return
	 */
	public String searchRiskLimitWhereSql(Map<LIMIT_TYPE, Object[][]> limitTypeMap, Map<String, Object> param) {
		StringBuilder sb = new StringBuilder(limitTypeMap.size() * 50);
		for (Map.Entry<LIMIT_TYPE, Object[][]> entry : limitTypeMap.entrySet()) {
			String codeTitle = (String) entry.getValue()[0][1];
			String code = (String) param.get(codeTitle);
			if ("unsight".equals(code)) {
				continue;
			}
			TreeCache cache = (TreeCache) entry.getValue()[0][0];
			if (sb.length() != 0) {
				sb.append(" OR");
			}
			sb.append(LimitUtil.getSqlStr(entry.getKey(), code, cache, true, "r"));
		}
		// System.out.println("*****whereSql=" + sb.toString());
		return "(" + sb.toString() + ")";
	}

	private String getDBName() {
		return DictContents.DB_NAME_CRMS;
	}

}
