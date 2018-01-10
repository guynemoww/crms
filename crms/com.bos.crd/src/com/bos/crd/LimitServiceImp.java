package com.bos.crd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bos.crd.LimitUtil.LIMIT_TYPE;
import com.bos.crd.valid.TreeCache;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.name.CrdTableName;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

public class LimitServiceImp {

	public void saveChargeLimit(DataObject riskLimit) {
		String jgType = riskLimit.getString("limitType");
		if (jgType == null || jgType.isEmpty()) {
			throw new RuntimeException("必须选择监管限额类型");
		}
		String riskId = riskLimit.getString("limitId");
		if (riskId == null || riskId.isEmpty()) {
			DataObject tempRisk = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_LIMIT2);
			tempRisk.set("limitType", jgType);
			DatabaseUtil.expandEntityByTemplate(getDBName(), tempRisk, tempRisk);
			if (tempRisk.getString("limitId") != null) {
				throw new RuntimeException("监管限额类型已存在");
			}
		}
		DatabaseUtil.saveEntity(getDBName(), riskLimit);
	}

	public void saveRiskLimit(DataObject riskLimit) {
		String type = riskLimit.getString("limitType");
		if (type == null || type.isEmpty()) {
			throw new RuntimeException("必须选择监管限额类别");
		}
		if (riskLimit.get("limitAmt") == null) {
			riskLimit.setBigDecimal("limitAmt", BigDecimal.ZERO);
		}

		LIMIT_TYPE limitType = LIMIT_TYPE.get(type);
		String riskId = riskLimit.getString("limitId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("limitType", type);
		param.put("limitCode", riskLimit.getString("limitCode"));
		param.put("limitId", riskLimit.getString("limitId"));
		riskLimitValidExist(riskId, param);
		if (riskLimit.getString("limitCap") == null) {
			param.put("limitAmt", riskLimit.getBigDecimal("limitAmt"));
			riskLimitValidAmt(limitType, param);// 监控限额就两条数据 没有验证必要

			riskLimit.set("updateUserNum", GitUtil.getCurrentUserId());
			riskLimit.set("updateOrgNum", GitUtil.getCurrentOrgCd());
			riskLimit.set("updateDate", GitUtil.getBusiDate());
		}
		DatabaseUtil.saveEntity(getDBName(), riskLimit);
	}

	@SuppressWarnings("rawtypes")
	private void riskLimitValidExist(String riskId, Map<String, Object> param) {
		Object[] objs = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitService.saveRiskLimitValidExist", param);
		if (objs != null && objs.length != 0) {
			if (riskId == null || riskId.isEmpty()) {
				throw new RuntimeException("限额类型已存在");
			} else {
				for (Object obj : objs) {// 排除已经保存数据的自己
					if (riskId != null && !riskId.equals(((Map) obj).get("LIMIT_ID"))) {
						throw new RuntimeException("限额类型已存在");
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void riskLimitValidAmt(LIMIT_TYPE limitType, Map<String, Object> dataMap) {
		Object[][] config = LimitUtil.getTypeConfig(limitType);
		String treeSqlName = (String) config[0][0];
		if (treeSqlName == null) {// 此处只验证树形关系数据
			return;
		}
		BigDecimal amt = (BigDecimal) dataMap.get("limitAmt");
		if (amt.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		String code = (String) dataMap.get("limitCode");
		String id = (String) dataMap.get("limitId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limitType", limitType.value());
		Object[] ojbs = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitService.searchRiskLimitValidAmt", map);
		if (ojbs != null && ojbs.length > 0) {
			// 基础数据变动不大，缓存10分钟
			TreeCache treeCache = LimitUtil.getTreeCache(getDBName(), treeSqlName, 600);
			Set<String> parentSet = treeCache.getParentSet(code);
			Set<String> sonSet = treeCache.getSonSet(code);
			for (Object obj : ojbs) {
				String tempId = (String) ((Map<String, Object>) obj).get("LIMIT_ID");
				if (id != null && tempId.equals(id)) {
					continue;
				}
				String tempCode = (String) ((Map<String, Object>) obj).get("LIMIT_CODE");
				String tempType = (String) ((Map<String, Object>) obj).get("LIMIT_TYPE");
				BigDecimal tempAmt = (BigDecimal) ((Map<String, Object>) obj).get("LIMIT_AMT");
				if (sonSet.contains(tempCode) && amt.compareTo(tempAmt) < 0) {
					throw new RuntimeException("{limitType:'" + tempType + "',limitCode:'" + tempCode + "',limitAmt:" + tempAmt + ",msg:'保存失败，上级限额不能小于已有下级限额'}");
				} else if (parentSet.contains(tempCode) && amt.compareTo(tempAmt) > 0) {
					throw new RuntimeException("{limitType:'" + tempType + "',limitCode:'" + tempCode + "',limitAmt:" + tempAmt + ",msg:'保存失败，下级限额不能大于已有上级限额'}");
				}
			}
		}
	}

	public void saveRiskGroup(DataObject group, DataObject[] details) {
		String groupId = group.getString("groupId");
		groupDetailValidExist(groupId, details);
		groupDetailValidAmt(details);
		if (group.getString("createUserNum") == null) {
			group.set("createUserNum", GitUtil.getCurrentUserId());
			group.set("createOrgNum", GitUtil.getCurrentOrgCd());
			group.set("createDate", GitUtil.getBusiDate());
		}
		DatabaseUtil.saveEntity(getDBName(), group);
		groupId = group.getString("groupId");
		saveGroupDetail(groupId, details);
	}

	private void saveGroupDetail(String groupId, DataObject[] details) {
		if (groupId == null) {
			throw new RuntimeException("错误的风险限额组编号");
		}
		String userNum = GitUtil.getCurrentUserId();
		String orgNum = GitUtil.getCurrentOrgCd();
		Date date = GitUtil.getBusiDate();
		for (DataObject detail : details) {
			String detailId = detail.getString("DETAIL_ID");
			detail.set("detailId", detailId);
			detail.set("groupId", groupId);
			detail.set("limitId", detail.getString("LIMIT_ID"));
			detail.set("limitAmt", detail.get("LIMIT_AMT"));
			detail.set("updateDate", date);
			detail.set("updateUserNum", userNum);
			detail.set("updateOrgNum", orgNum);
			if (detailId != null) {
				DatabaseUtil.updateEntity(getDBName(), detail);
			} else {
				DatabaseUtil.insertEntity(getDBName(), detail);
			}
		}
	}

	private void groupDetailValidExist(String groupId, DataObject[] details) {
		for (DataObject detail : details) {
			String newRiskId = detail.getString("NEW_LIMIT_ID");
			if (newRiskId != null) {
				if (detail.get("DETAIL_ID") != null) {
					Map<String, Object> tempMap = new HashMap<String, Object>();
					tempMap.put("groupId", groupId);
					tempMap.put("limitId", newRiskId);
					Object[] objs = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.crd.LimitService.saveGroupDetailValidExist", tempMap);
					for (Object obj : objs) {
						if (!newRiskId.equals(((Map) obj).get("DETAIL_ID"))) {
							throw new RuntimeException("项目[" + detail.getString("LIMIT_CODE_TEXT") + "]已存在,请删除该项目");
						}
					}
				}
				// 检查成功之后修改原有编号，方便后面保存
				detail.set("LIMIT_ID", newRiskId);
			}
		}
	}

	private void groupDetailValidAmt(DataObject[] details) {
		for (DataObject detail : details) {
			DataObject obj = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_LIMIT2);
			obj.set("limitId", detail.getString("LIMIT_ID"));
			if (DatabaseUtil.expandEntity(getDBName(), obj) != 1) {
				throw new RuntimeException("错误的风险限额编号");
			}
			if (obj.getBigDecimal("limitAmt").compareTo(BigDecimal.ZERO) != 0 && obj.getBigDecimal("limitAmt").compareTo(detail.getBigDecimal("LIMIT_AMT")) < 0) {
				throw new RuntimeException("当前项目限额必须小于选中项目的限额");
			}
		}
	}

	private String getDBName() {
		return "default";
	}

	public void removeRiskLimit(String riskId) {
		throw new RuntimeException("接口都没得，调啥子...");
	}

	public void removeRiskGroup(String groupId) {
		DataObject group = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_GROUP2);
		group.set("groupId", groupId);
		DatabaseUtil.expandEntity(getDBName(), group);

		DataObject detail = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_GROUP_DETAIL);
		detail.set("groupId", groupId);
		DataObject[] details = DatabaseUtil.queryEntitiesByTemplate(getDBName(), detail);

		DatabaseUtil.deleteEntityBatch(getDBName(), details);
		DatabaseUtil.deleteEntity(getDBName(), group);
	}

	public void removeRiskGroupDetail(String[] detailIds) {
		List<DataObject> removeList = new ArrayList<DataObject>(detailIds.length);
		for (String detailId : detailIds) {
			DataObject detail = DataObjectUtil.createDataObject(CrdTableName.TB_CRD_RISK_GROUP_DETAIL);
			detail.set("detailId", detailId);
			removeList.add(detail);
		}
		DatabaseUtil.deleteEntityBatch(getDBName(), removeList.toArray(new DataObject[] {}));
	}
}
