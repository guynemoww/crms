package com.bos.bps.util;


import java.util.Map;

import com.primeton.bfs.tp.common.exception.EOSException;

public interface IBIZProcess {

	
	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId,Map workitem) throws EOSException;
	
	/**
	 * 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeSubmit(String processInstId,Map workitem) throws EOSException;
	
	/**
	 * 
	* @Title: executeBeforeIntegration
	* @Description: TODO(用于与外围系统交互前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeIntegration(String processInstId,Map workitem) throws EOSException;
	
	/**
	 * 
	* @Title: executeBeforeTerminate
	* @Description: TODO(用于普通流程结束前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeTerminate(String processInstId,Map workitem) throws EOSException;
	
	/**
	 * 
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeUntread(String processInstId,Map workitem) throws EOSException;
	
	/**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeReject(String processInstId,Map workitem) throws EOSException;
    
    /**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeAfterAbort(String processInstId,Map workitem)throws EOSException;
    /**
	 * 
	* @Title: executeDataCheck
	* @Description: TODO(用于校验数据是否完整正确)
	* @param processInstId 流程实例ID号
	* @return Map     返回类型 返回 null 则通过，或者给key设值  errorNum=1通过errorNum=2不通过errorNum=3询问是否通过，errorCode提示码，errorContent提示内容
	* @throws
	 */
    public Map<String,String> executeDataCheck(String processInstId,Map workitem)throws Exception;
}
