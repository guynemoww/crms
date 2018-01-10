package com.bos.aft;

import com.bos.bps.util.IBusiness;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class warrantRevokeFlow implements IBusiness {

	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		// TODO 自动生成方法存根
		try {
			// 逻辑构件名称             com.bos.aft.aft_inspect_report.
			String componentName = "com.bos.aft.aft_inspect_report";
			// 逻辑流名称 
			String operationName = "stopWarrantFlow";//设置评级状态为7（中止）
			ILogicComponent logicComponent = LogicComponentFactory
			.create(componentName);
            //逻辑流的输入参数
			Object[] params = new Object[1];
			params[0] = bizId;
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

}
