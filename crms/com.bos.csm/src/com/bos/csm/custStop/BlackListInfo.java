package com.bos.csm.custStop;

import java.util.List;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBusiness;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
/**
 * 终止流程
 * 实现流程接口，处理业务数据
 * 如果为流程中，状态删除
 * 如果未发起流程，直接删除业务数据
 * 黑名单客户
 * */
public class BlackListInfo implements IBusiness {

	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		String[] xpath={"bizType"};//获取相关数据的数组
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				if("1".equals(list.get(0).toString())){
					//黑名单转入时否决  1表示否决，2表示撤销
					removeInOfRevoke("1",processInstId);
				}else{
					//黑名单转出时否决  1表示否决，2表示撤销
					removeOutOfRevoke("1",processInstId);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}
	
	/**
	 * 黑名单转入时，流程被撤销或者被否决的时的操作。
	 * @param str
	 */
	private void removeInOfRevoke(String flag, String str) {
		String msg = "";		
		String componentName = "com.bos.csm.blacklist.blackFlow";
		String operationName ="removeInOfRevoke";
		
		ILogicComponent logicComponent = LogicComponentFactory
		.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[2];
		params[0] = flag;
		params[1] = str;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			msg = (String) result[0];
			if(("").equals(msg)||msg == null){
				
			}else{
				throw new EOSException("黑名单转入中止流程失败!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}
	
	/**
	 * 黑名单转出时，流程被撤销或者被否决的时的操作。
	 * @param str
	 */
	private void removeOutOfRevoke(String flag,String str) {
		String msg = "";		
		String componentName = "com.bos.csm.blacklist.blackFlow";
		String operationName ="removeOutOfRevoke";
		
		ILogicComponent logicComponent = LogicComponentFactory
		.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[2];
		params[0] = flag;
		params[1] = str;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			msg = (String) result[0];
			if(("").equals(msg)||msg == null){
				
			}else{
				throw new EOSException("黑名单转出中止流程失败!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}
}
