package com.bos.lst.callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.utp.tools.DataObjectExt;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

public class ComfirmLst implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// 同意移交
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.pub.lst.lst";
			// 逻辑流名称
			String operationName = "updateInfoOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// 退回
	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// 拒绝

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// 撤销
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String partyId = list.get(0).toString();                   //业务ID

				DataObject lstInfo = DataObjectUtil.createDataObject("com.bos.lst.lst.TbLstInfo");
				lstInfo.set("partyId", partyId);
				DatabaseUtil.expandEntityByTemplate("default", lstInfo, lstInfo);
				
				//在提交意见那里 选择撤销时 会直接将名单制客户信息删除(撤销时 应该不采取任何动作)
				lstInfo.set("approveStatus", "03");
				DatabaseUtil.updateEntity("default", lstInfo);
				//DataObject lstInfo =DataObjectUtil.createDataObject("com.bos.lst.lst.TbLstInfo");
				//lstInfo.set("partyId", partyId);
				//DatabaseUtil.deleteEntity("default", lstInfo);
    	} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {

		return null;
	}

}
