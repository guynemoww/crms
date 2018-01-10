package com.bos.crd.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeCache {

	private long createTime = System.currentTimeMillis();

	private Map<String, Set<String>> dataMap;

	private boolean useRoot;

	public TreeCache(Object[] ojbs) {
		loading(ojbs);
	}

	@SuppressWarnings("unchecked")
	private void loading(Object[] ojbs) {
		Map<String, String> codeMap = new HashMap<String, String>();
		Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();
		for (Object obj : ojbs) {
			String id = (String) ((Map<String, Object>) obj).get("ID");
			codeMap.put(id, (String) ((Map<String, Object>) obj).get("CODE"));
			tempMap.put(id, (Map<String, Object>) obj);
		}
		int size;
		Map<String, Object> parentMap;
		do {
			size = tempMap.size();
			Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
			for (Map<String, Object> objMap : tempMap.values()) {
				String parentId = (String) objMap.get("PARENT_ID");// 当没有父节点或者找不到父节点时直接存入自己
				if (parentId == null || (parentMap = tempMap.get(parentId)) == null) {
					map.put((String) objMap.get("ID"), objMap);
					continue;
				}
				List<Map<String, Object>> tempList = (List<Map<String, Object>>) parentMap.get("sonList");
				objMap.put("parent", parentMap);
				if (tempList == null) {
					tempList = new ArrayList<Map<String, Object>>();
					parentMap.put("sonList", tempList);
				}
				tempList.add(objMap);
			}
			tempMap = map;
		} while (size != tempMap.size());
		useRoot = tempMap.size() == 1;
		dataMap = new HashMap<String, Set<String>>();
		for (Object obj : ojbs) {
			Map<String, Object> objMap = (Map<String, Object>) obj;
			dataMap.put(objMap.get("CODE") + "_son", recursionSon(objMap));
			if (objMap.get("PARENT_ID") != null) {
				dataMap.put(objMap.get("CODE") + "_parent", recursionParent(objMap));
			}
		}
		// for (Map.Entry<String, Set<String>> entry : dataMap.entrySet()) {
		// System.out.println("----------------------");
		// System.out.println(entry.getKey());
		// System.out.println(entry.getValue());
		// }
		// System.out.println("----------------------");
	}

	@SuppressWarnings("unchecked")
	private Set<String> recursionSon(Map<String, Object> objMap) {
		List<Map<String, Object>> tempList = (List<Map<String, Object>>) objMap.get("sonList");
		Set<String> dataSet = new HashSet<String>();
		dataSet.add((String) objMap.get("CODE"));
		if (tempList != null) {
			for (Map<String, Object> map : tempList) {
				dataSet.addAll(recursionSon(map));
			}
		}
		return dataSet;
	}

	@SuppressWarnings("unchecked")
	private Set<String> recursionParent(Map<String, Object> objMap) {
		Map<String, Object> parentMap = (Map<String, Object>) objMap.get("parent");
		Set<String> dataSet = new HashSet<String>();
		dataSet.add((String) objMap.get("CODE"));
		if (parentMap != null) {
			dataSet.addAll(recursionParent(parentMap));
		}
		return dataSet;
	}

	public boolean isParent(String code, String parentCode) {
		Set<String> set = dataMap.get(code + "_parent");
		return set != null && set.contains(parentCode);
	}

	public String[] getParents(String code) {
		Set<String> parentSet = dataMap.get(code + "_parent");
		return parentSet == null ? new String[] {} : parentSet.toArray(new String[] {});
	}

	public Set<String> getParentSet(String code) {
		Set<String> tempSet = dataMap.get(code + "_parent");
		Set<String> dataSet = new HashSet<String>();
		if (tempSet != null) {
			dataSet.addAll(tempSet);
		}
		return dataSet;
	}

	public boolean isRoot(String code) {
		return useRoot && dataMap.get(code + "_parent") == null;
	}

	public boolean isSon(String code, String sonCode) {
		Set<String> set = dataMap.get(code + "_son");
		return set != null && set.contains(sonCode);
	}

	public String[] getSons(String code) {
		Set<String> set = dataMap.get(code + "_son");
		return set == null ? new String[] {} : set.toArray(new String[] {});
	}

	public Set<String> getSonSet(String code) {
		Set<String> tempSet = dataMap.get(code + "_son");
		Set<String> dataSet = new HashSet<String>();
		if (tempSet != null) {
			dataSet.addAll(tempSet);
		}
		return dataSet;
	}

	public long getCreateTime() {
		return createTime;
	}
}
