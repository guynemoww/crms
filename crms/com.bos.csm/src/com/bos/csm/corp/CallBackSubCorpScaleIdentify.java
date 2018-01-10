package com.bos.csm.corp;

import java.util.HashMap;
import java.util.Map;

import com.bos.bps.util.FlowUtil.FLOW_STATUS;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.DictContents;
import com.bos.pub.EosUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.pub.entity.name.WfmTableName;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class CallBackSubCorpScaleIdentify implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		DataObject scale = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY, "id", process.getString("productId"));
		scale.set("status", FLOW_STATUS.RUN.value());
		DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, scale);
		updatePartyScale(scale.getString("partyId"), null);
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		DataObject scale = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY, "id", process.getString("productId"));
		scale.set("status", FLOW_STATUS.COMPLETE.value());
		DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, scale);
		updatePartyScale(scale.getString("partyId"), scale.getString("scaleCode"));
	}

	private void updatePartyScale(String partyId, String scaleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partyId", partyId);
		map.put("scaleCode", scaleCode);
		DatabaseExt.executeNamedSql(DictContents.DB_NAME_CRMS, "com.bos.csm.corporation.corporation.updateCorpScale", map);
	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// 退回
	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		DataObject scale = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY, "id", process.getString("productId"));
		// 当撤销时还原原有企业规模
		String oldScaleCode = scale.getString("oldScaleCode");
		if (oldScaleCode != null) {
			updatePartyScale(scale.getString("partyId"), oldScaleCode);
		}
		DatabaseUtil.deleteEntity(DictContents.DB_NAME_CRMS, scale);
	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		String bizId = EosUtil.getBizId(processInstId);
		DataObject scale = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY, "id", bizId);
		// 当撤销时还原原有企业规模
		String oldScaleCode = scale.getString("oldScaleCode");
		if (oldScaleCode != null) {
			updatePartyScale(scale.getString("partyId"), oldScaleCode);
		}
		DatabaseUtil.deleteEntity(DictContents.DB_NAME_CRMS, scale);
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		DataObject scale = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY, "id", process.getString("productId"));
		if (scale.getString("scaleCode") == null || scale.getString("scaleCode").trim().isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("errorNum", "2");
			map.put("errorContent", "请先保存企业认定信息");
			map.put("errorCode", "请先保存企业认定信息");
			return map;
		}
		return null;
	}

}
