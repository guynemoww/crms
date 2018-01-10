package com.bos.csm.callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.internal.sca.metadata.DataObjectHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

public class ComfirmGuarGroup implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "updateGuarGroupOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "02";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "updateGuarGroupOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "03";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "updateGuarGroupOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "01";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

	/**
	 * 否决
	 * */
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "updateGuarGroupOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "09";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	// 撤销
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "cancelGuarGroup";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String errorNum = "";
		String errorContent = "";
		String errorCode = "1";
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			// String processId = processInstId; //流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.guar.guarGroup";
			// 逻辑流名称
			String operationName = "getGuarGroupInfo";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[2];
			DataObject dataObject = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			dataObject.setString("partyId", applyId);
			params[0] = dataObject;
			params[1] = processInstId;
			Object os[] = logicComponent.invoke(operationName, params);
			String memberNum = os[2].toString();
			int num = Integer.parseInt(memberNum);
			if (num >= 3) {
				errorNum = "1";
				// 满足人数条件
				String componentName2 = "com.bos.csm.guar.guarGroup";
				// 逻辑流名称
				String operationName2 = "checkGuarGroupInfo";
				ILogicComponent logicComponent2 = LogicComponentFactory.create(componentName2);
				Object os2[] = logicComponent2.invoke(operationName2, params);
				boolean flag = Boolean.parseBoolean(os2[0].toString());
				if (flag) {

				} else {
					errorNum = "2";
					errorContent = "已存在相同成员联保小组";
				}
			} else {
				errorNum = "2";
				errorContent = "联保小组人数不得少于三人";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorNum = "2";
			errorContent = "数据异常,联系管理员.";
		} catch (Throwable e) {
			e.printStackTrace();
			errorNum = "2";
			errorContent = "数据异常,联系管理员.";
		}
		resultMap.put("errorNum", errorNum);
		resultMap.put("errorContent", errorContent);
		resultMap.put("errorCode", errorCode);
		return resultMap;
	}

}
