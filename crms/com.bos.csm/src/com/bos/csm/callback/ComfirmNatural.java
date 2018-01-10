package com.bos.csm.callback;

import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class ComfirmNatural implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId = list.get(0).toString();                   //业务ID
				String processId = processInstId;          //流程实例ID
				// 逻辑构件名称             
				String componentName = "com.bos.csm.natural.natural";
				// 逻辑流名称 
				String operationName = "updateNaturalOK";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[2];
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
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
