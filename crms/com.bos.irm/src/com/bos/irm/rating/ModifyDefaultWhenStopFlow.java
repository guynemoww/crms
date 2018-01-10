package com.bos.irm.rating;

import java.util.List;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBusiness;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class ModifyDefaultWhenStopFlow implements IBusiness {
	//用于普通流程终止前业务逻辑
	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//  type: 1  违约认定  2、3  违约重生
				if("1".equals(type)){
					status = 10 ;		// 待发起
				}else if("3".equals(type)){
					status = 15 ;
				}else{
					status = 20;		// 重生待发起
				}
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "ModifyDefaultWhenStopStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
				 //逻辑流的输入参数
				Object[] params = new Object[2];
				params[0] = drId;
				params[1]= status;
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
