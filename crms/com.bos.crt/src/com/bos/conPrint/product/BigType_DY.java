/**
 * 
 */
package com.bos.conPrint.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class BigType_DY extends BigType_GRT {

	@SuppressWarnings("unchecked")
	@Override
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
		param.put("subType", subType);
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getGrt_dzy", param);
		if (datas != null && datas.length > 0) {
			Object[] trdzys = getWTrdzy(param);
			List<Map<String, Object>> dcList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> bdcList = new ArrayList<Map<String, Object>>();
			Map<String, Object> temp;
			for (Object obj : datas) {
				temp = (Map<String, Object>) obj;
				temp.put("WTRDZY", isWTrdzy(trdzys, (String) temp.get("SURETY_ID")));
				temp.putAll(getYpMap((String) getNeedValue(temp, "SURETY_NO"), (String) temp.get("SORT_TYPE")));// "SORT_TYPE"存在空值
				if (getSortTypeUtil().isDynamicAsset((String) temp.get("SORT_TYPE"))) {
					dcList.add(temp);
				} else {
					bdcList.add(temp);
				}
			}
			if (!dcList.isEmpty()) {
				reportMap.put("sub_dc", dcList);
			}
			if (!bdcList.isEmpty()) {
				reportMap.put("sub_bdc", bdcList);
			}
		}
	}
}
