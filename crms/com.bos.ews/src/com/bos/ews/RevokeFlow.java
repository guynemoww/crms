package com.bos.ews;

import java.util.List;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBusiness;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class RevokeFlow implements IBusiness {

	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId=list.get(0).toString();
				String type=list.get(1).toString();
				Object[] result = null;
				String componentName;
				String operationName;
				if(type.equals("1")){
                    // 逻辑构件名称            
					 componentName = "com.bos.ews.warnInfo";
					// 逻辑流名称 
					 operationName = "voteAddWarnInfo";
				}else{
                    //逻辑构件名称            
					 componentName = "com.bos.ews.warnInfo";
					// 逻辑流名称 
					 operationName = "voteCloseWarnInfo";
				}
				
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[1];
				params[0] = applyId;
				
				result = logicComponent.invoke(operationName, params);
				if(result[0]=="1"){
					throw new EOSException("出错");
				}

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
