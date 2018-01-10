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

public class HandleProcessSubmit implements IBIZProcess {
	public static TraceLogger logger = new TraceLogger(HandleProcessSubmit.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		try {
			DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
			DataObject plan = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", process.getString("productId"));
			AssetsUtil.editValidate(AssetsUtil.getDBName(), plan);
			String planType = plan.getString("planType");
			String cleanTakeType = plan.getString("cleanTakeType");
			IAssetHandlePlanService service = AssetsUtil.getService(planType, cleanTakeType);
			service.processSubmitValid(plan.getString("id"));
			plan.set("status", "20");
			plan.set("updateDate", GitUtil.getBusiDate());
			plan.set("updateUserId", GitUtil.getCurrentUserId());
			DatabaseUtil.updateEntity(AssetsUtil.getDBName(), plan);
		} catch (Throwable e) {
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		new HandlePlanServiceImp().remove(EosUtil.getBizId(processInstId));
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		try {
			DataObject plan = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", EosUtil.getBizId(processInstId));
			AssetsUtil.editValidate(AssetsUtil.getDBName(), plan);
		} catch (Throwable e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("errorContent", e.getMessage());
			return map;
		}
		return null;
	}

}
