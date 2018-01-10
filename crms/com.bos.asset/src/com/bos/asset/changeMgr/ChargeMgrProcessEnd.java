package com.bos.asset.changeMgr;

import java.util.Map;

import org.springframework.transaction.TransactionDefinition;

import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.DictContents;
import com.bos.pub.EosUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.WfmTableName;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class ChargeMgrProcessEnd implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(ChargeMgrProcessEnd.class);

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
		String applyId = process.getString("productId");

		logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");

		DataObject chargeMgr = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_CHANGE_MGR, "id", applyId);
		DataObject tas = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", chargeMgr.getString("transferId"));
		tas.set("tasOrgNum", chargeMgr.get("changeOrgNum"));
		tas.set("tasUserNum", chargeMgr.get("changeUserNum"));
		chargeMgr.set("status", ASSETS_STATUS.PASS);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(TransactionDefinition.PROPAGATION_REQUIRED);
		try {
			DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, tas);
			DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, chargeMgr);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String applyId = EosUtil.getBizId(processInstId);
		logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");

		DataObject chargeMgr = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_CHANGE_MGR, "id", applyId);
		chargeMgr.set("status", ASSETS_STATUS.REJECT);
		DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, chargeMgr);
	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
