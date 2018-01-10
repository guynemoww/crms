/**
 * 
 */
package com.bos.biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-07-07 13:32:05
 * 
 */
@Bizlet("")
public class MeasureEos {
	@Bizlet("")
	public Map<String, Object> createCreditLineMea(String applyId, Map<String, String>[] codes) {
		DataObject apply = EntityUtil.getEntityById(BizTableName.TB_BIZ_APPLY, "applyId", applyId);

		int num = DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.createMeaValid", applyId);
		Map<String, Object> param = new HashMap<String, Object>();
		if (num > 0) {
			param.put("msg", "该申请信息已经存在测算记录");
			return param;
		}
		Map<String, Object> limitMap = getCreditLineLimit(true);

		DataObject measure = DataObjectUtil.createDataObject(BizTableName.TB_BIZ_CREDIT_LINE_MEASURE);
		measure.setString("id", apply.getString("applyId"));
		measure.setDate("meaDate", GitUtil.getBusiDate());
		measure.setString("userNum", GitUtil.getCurrentUserId());
		measure.setString("orgNum", GitUtil.getCurrentOrgCd());
		param.put("partyId", apply.getString("partyId"));
		// 计算我行授信额度
		Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.getCrdPartyLimit", param);
		if (objs != null && objs.length > 0) {
			measure.set("b21", objs[0]);
		}
		// 计算评级系数
		objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.getPartyCreditRatingCD", param);
		if (objs != null && objs.length > 0) {
			BigDecimal temp = (BigDecimal) limitMap.get(objs[0]);
			if (temp == null) {
				param.put("msg", "客户等级[" + objs[0] + "]没有配置对应的等级调节系数");
				return param;
			}
			measure.set("b4", temp);
		}
		param = getCreditLineMeaParam(param, codes);
		if (param.get("msg") != null) {
			return param;
		}
		for (Map.Entry<String, Object> entity : param.entrySet()) {
			measure.set(entity.getKey(), entity.getValue());
		}
		// measure.set("b1", limitMap.get("rebate"));
		DatabaseUtil.insertEntity(DBUtil.DB_NAME_DEF, measure);

		param.clear();
		param.put("measure", measure);
		return param;
	}

	@SuppressWarnings("unchecked")
	@Bizlet("")
	public Map<String, Object> getCreditLineLimit(boolean haveLevel) {
		Map<String, Object> limitMap = new HashMap<String, Object>();
		if (!haveLevel) {
			limitMap.put("codes", new String[] { "rebate", "rebate_2" });
		}
		Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.getCreditLineLimit", limitMap);
		if (objs == null || objs.length == 0) {
			throw new RuntimeException("[TB_BIZ_CREDIT_LINE_LIMIT]表没有维护数据");
		}
		limitMap.clear();
		for (Object obj : objs) {
			limitMap.put((String) ((Map<String, Object>) obj).get("CODE"), ((Map<String, Object>) obj).get("VALUE"));
		}
		return limitMap;
	}

	@Bizlet("")
	public void removeCreditLineMea(String applyId) {
		DataObject entity = EntityUtil.getEntityById(BizTableName.TB_BIZ_CREDIT_LINE_MEASURE, "id", applyId);

		DatabaseUtil.deleteEntity(DBUtil.DB_NAME_DEF, entity);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getCreditLineMeaParam(Map<String, Object> param, Map<String, String>[] codes) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] temps = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.getCustomerFinanceId", param);
		if (temps == null || temps.length == 0) {
			map.put("msg", "该客户没有生效的财务报表");
		} else {
			out: for (Map<String, String> codeMap : codes) {
				for (Object temp : temps) {
					if (codeMap.get("customerTypeCd").equals(((Map<String, String>) temp).get("CUSTOMER_TYPE_CD"))) {
						param.put("financeId", ((Map<String, String>) temps[0]).get("FINANCE_ID"));
						codeMap.remove("customerTypeCd");
						if (!codeMap.isEmpty()) {
							param.put("codes", codeMap.values().toArray());
						}
						Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.biz.measure.getCreditLineMeaParam", param);
						if (objs != null && objs.length != 0) {
							for (Map.Entry<String, String> entry : codeMap.entrySet()) {
								for (Object obj : objs) {
									if (entry.getValue().equals(((Map<String, String>) obj).get("PROJECT_CD"))) {
										map.put(entry.getKey(), ((Map<String, String>) obj).get("PROJECT_VALUE"));
									}
								}
							}
							break out;
						}
					}
				}
			}
		}
		return map;
	}
}
