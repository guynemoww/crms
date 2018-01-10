package com.bos.csm.callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

public class ComfirmBusinessMove implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		//同意移交
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId = list.get(0).toString();                   //业务ID
				String processId = processInstId;          //流程实例ID
				// 逻辑构件名称             
				String componentName = "com.bos.csm.callback.moveBusiness";
				// 逻辑流名称 
				String operationName = "updateMoveBusinessOK";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
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

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		//退回
	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		//拒绝
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId = list.get(0).toString();                   //业务ID
				String processId = processInstId;          //流程实例ID
				// 逻辑构件名称             
				String componentName = "com.bos.csm.callback.moveBusiness";
				// 逻辑流名称 
				String operationName = "updateMoveBusinessReject";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
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

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId = list.get(0).toString();                   //业务ID
				String processId = processInstId;          //流程实例ID
				// 逻辑构件名称             
				String componentName = "com.bos.csm.callback.moveBusiness";
				// 逻辑流名称 
				String operationName = "cancelMoveBusiness";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
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

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		
		return null;
	}

}
