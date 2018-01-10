/**
 * 
 */
package com.bos.conPrint.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

/**
 * <pre>
 * Title: 抵质押，保证 合同打印信息
 * Description: 程序功能的描述
 * </pre>
 * 
 * @author Administrator
 * @version 1.00.00
 * 
 */

public class BigType_ZY extends BigType_GRT {
	private String[] sortTypes;

	@SuppressWarnings("unchecked")
	protected void loadSub(Map<String, Object> reportMap) {
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null || "none".equals(conMap.get("SEARCH_SUB"))) {
			return;
		}
		Map<String, String> param = new HashMap<String, String>();
		String subType = (String) getNeedValue(conMap, "MAIN_GUARANTY_TYPE");
		subType = ConInfoLoadUtil.getSubType(subType);
		if (subType == null) {
			throw new RuntimeException("合同的主担保关系对应错误[" + conMap.get("MAIN_GUARANTY_TYPE") + "]");
		}
		param.put("subcontractId", (String) getNeedValue(conMap, "SUBCONTRACT_ID"));
		param.put("contractId", (String) getNeedValue(conMap, "CONTRACT_ID"));
		param.put("subType", subType);
		if ("03".equals(subType)) {
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getGrt_bzj", param);
			if (datas != null && datas.length > 0) {
				Map<String, Object> temp = (Map<String, Object>) datas[0];
				String tr = isWTrdzy(getWTrdzy(param), (String) getNeedValue(temp, "SURETY_ID"));
				temp.put("WTRDZY", tr);
				reportMap.put("sub_bzj", temp);
			}
		} else if (sortTypes != null && sortTypes.length > 0) {
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getGrt_dzy", param);
			if (datas != null && datas.length > 0) {
				Map<String, Object> temp = (Map<String, Object>) datas[0];
				String sortType = getNeedValue(temp, "SORT_TYPE");
				for (String t : sortTypes) {
					if (getSortTypeUtil().isType(t, sortType)) {
						if ("yszk".equals(t)) {
							String conNum = (String) conMap.get("CONTRACT_NUM");
							if (conNum == null) {
								conNum = "";
							}
							String conName = getConInfoLoadService().getReportNameCN((String) conMap.get("PRODUCT_TYPE"));
							if (conName == null) {
								conName = "";
							}
							temp.put("CON_NAME_NUM", "《" + conName + "》" + conNum);
						}
						temp.put("WTRDZY", isWTrdzy(getWTrdzy(param), (String) getNeedValue(temp, "SURETY_ID")));
						temp.putAll(getYpMap((String) getNeedValue(temp, "SURETY_NO"), sortType));
						reportMap.put("sub_" + t, temp);
						break;
					}
				}
			}
		}
	}

	public String[] getSortTypes() {
		return sortTypes;
	}

	public void setSortTypes(String[] sortTypes) {
		this.sortTypes = sortTypes;
	}

}
