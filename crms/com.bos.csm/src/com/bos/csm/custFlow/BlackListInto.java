package com.bos.csm.custFlow;

import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class BlackListInto implements IBIZProcess {

	 /**
	 * 
	* @Title: executeAfterAbort
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		//撤销流程，status=05
		/*
		 bizStatus:
		01	移入审核中
		02	移出审核中
		03	移入已审核
		04	移出已审核
		05	移入被撤销
		06	移出被撤销
		bizType:
		1 追加
	    2 退出
		*/
		String[] xpath={"bizType"};//获取相关数据的数组
		
		try {
			//获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				if("1".equals(list.get(0).toString())){
					//黑名单转入时撤销 1表示否决，2表示撤销
					removeInOfRevoke("2",processInstId);
				}else{ 
					//黑名单转出时撤销 1表示否决，2表示撤销
					removeOutOfRevoke("2",processInstId);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		

	}

	

	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// 创建流程 examineState=审核中

	}

	/**
	 * 
	* @Title: executeBeforeIntegration
	* @Description: TODO(用于与外围系统交互前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	/**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)---流程中途被否决结束
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// 流程否决时， status=04
		/*
		 bizStatus:
		01	移入审核中
		02	移出审核中
		03	移入已审核
		04	移出已审核
		05	移入被撤销
		06	移出被撤销
		bizType:
		1 追加
	    2 退出
		*/
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
	 * 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)--流程下一节点同意，往下一节点走的情况
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		// 流程进行时
			

	}

	/**
	 * 
	* @Title: executeBeforeTerminate
	* @Description: TODO(用于普通流程结束前业务逻辑)---流程正常结束的情况
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// 流程结束时，
		/*
		 bizStatus:
		01	移入审核中
		02	移出审核中
		03	移入已审核
		04	移出已审核
		05	移入被撤销
		06	移出被撤销
		bizType:
		1 追加
	    2 退出
		*/
		String[] xpath={"bizType"};//获取相关数据的数组
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				if("1".equals(list.get(0).toString())){
					//黑名单转入时正常结束
					removeInOfEnd(processInstId);
				}else{
					//黑名单转出时正常结束
					removeOutOfEnd(processInstId);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		

	}
	
	/**
	 * 黑名单转出时，流程正常结束时的操作。
	 * @param str
	 */
	private void removeOutOfEnd(String str) {
		String msg = "";		
		String componentName = "com.bos.csm.blacklist.blackFlow";
		String operationName ="removeOutOfEnd";
		
		ILogicComponent logicComponent = LogicComponentFactory
		.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = str;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			msg = (String) result[0];
			if(("").equals(msg)||msg == null){
				
			}else{
				throw new EOSException("黑名单转出时，流程正常结束时操作失败!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}
	
	/**
	 * 黑名单转入时，流程正常结束时的操作。
	 * @param str
	 */
	private void removeInOfEnd(String str) {
		String msg = "";		
		String componentName = "com.bos.csm.blacklist.blackFlow";
		String operationName ="removeInOfEnd";
		
		ILogicComponent logicComponent = LogicComponentFactory
		.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = str;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			msg = (String) result[0];
			if(("").equals(msg)||msg == null){
				
			}else{
				throw new EOSException("黑名单转入时，流程正常结束时失败!");
			}
		} catch (Throwable e) {
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
				throw new EOSException("黑名单转入时，流程撤销或者流程否决操作失败!");
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
				throw new EOSException("黑名单转出时，流程撤销或者流程否决操作失败!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}

	/**
	 * 
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)--流程退回的情况
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		return null;
	}

}
