package com.bos.irm.rating;

import com.bos.bps.util.IBusiness;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class ModifyRatingCutFlow implements IBusiness {
	//用于普通流程终止前业务逻辑
	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		try {
			// 逻辑构件名称             
			String componentName = "com.bos.irm.irm_rating_work";
			// 逻辑流名称 
			String operationName = "modifyRatingState4";//设置评级状态为7（中止）
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
