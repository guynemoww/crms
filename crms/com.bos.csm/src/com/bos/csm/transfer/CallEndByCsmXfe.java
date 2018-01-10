package com.bos.csm.transfer;

import java.rmi.RemoteException;
import java.util.Map;

import com.bos.bps.util.IBIZProcess;
import com.bos.pub.EosUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class CallEndByCsmXfe implements IBIZProcess {

	@Override
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String bizId = EosUtil.getBizId(processInstId);
		try {
			new CsmxfeService().actionCsmxfe(bizId);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		String xx = (String) workitem.get("");
		if (!"".equals(xx)) {// 测试用
			return;
		}
		String bizId = EosUtil.getBizId(processInstId);
		DataObject csmxfe = DataObjectUtil.createDataObject(CsmTableName.TB_CSMXFE_TRANSFER);
		csmxfe.set("transferId", bizId);
		csmxfe.set("status", "00");
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@Override
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String bizId = EosUtil.getBizId(processInstId);
		DataObject csmxfe = DataObjectUtil.createDataObject(CsmTableName.TB_CSMXFE_TRANSFER);
		csmxfe.set("transferId", bizId);
		csmxfe.set("status", "30");
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@Override
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@Override
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

}
