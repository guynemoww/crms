package com.bos.asset.handle;

import java.util.HashMap;
import java.util.Map;

import com.bos.asset.AssetsUtil;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.EosUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.WfmTableName;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class HandleProcessEnd implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(HandleProcessEnd.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		DataObject plan = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", process.getString("productId"));
		String planId = plan.getString("id");
		String planType = plan.getString("planType");
		String cleanTakeType = plan.getString("cleanTakeType");
		Map<String, Object> param = new HashMap<String, Object>();
		
		IAssetHandlePlanService service = AssetsUtil.getService(planType, cleanTakeType);
		service.submitToInterface(planId, param);

		plan.set("status", "30");
		plan.set("updateDate", GitUtil.getBusiDate());
		plan.set("updateUserId", GitUtil.getCurrentUserId());
		DatabaseUtil.updateEntity(AssetsUtil.getDBName(), plan);
	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		logger.info("流程结束，开始处理业务数据------processInstId=" + processInstId + "------->开始!");
		DataObject plan = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", EosUtil.getBizId(processInstId));
		plan.set("status", "40");
		plan.set("updateDate", GitUtil.getBusiDate());
		plan.set("updateUserId", GitUtil.getCurrentUserId());
		DatabaseUtil.updateEntity(AssetsUtil.getDBName(), plan);
	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
