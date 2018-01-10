package com.bos.asset.transfer;

import java.util.Map;

import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.DictContents;
import com.bos.pub.EosUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.WfmTableName;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class TransferProcessSubmit implements IBIZProcess {
	public static TraceLogger logger = new TraceLogger(TransferProcessSubmit.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		DataObject process = EntityUtil.getEntityById(WfmTableName.TB_WFM_PROCESSINSTANCE, "processId", processInstId);
		String applyId = process.getString("productId");
		logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
		DataObject transfer = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", applyId);

		if (transfer.get("tasUserNum") == null) {
			throw new RuntimeException("请先录入并保存移交信息");
		}

		transfer.set("status", ASSETS_STATUS.AUDIT);
		DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, transfer);
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
		String applyId = EosUtil.getBizId(processInstId);

		DataObject transfer = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", applyId);
		DatabaseUtil.deleteEntity(DictContents.DB_NAME_CRMS, transfer);
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		return null;
	}

}
