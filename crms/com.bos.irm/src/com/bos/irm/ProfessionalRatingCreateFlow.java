package com.bos.irm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.exception.EOSRuntimeException;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class ProfessionalRatingCreateFlow implements IBIZProcess {
	
	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException{
	}
		
	
	/**
	 * 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException{
		String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String iraApplyId=fg.get(0).toString();
				int status =1;
				// 逻辑构件名称             
				String componentName = "com.bos.irm.getProfessionalRateInfo";
				// 逻辑流名称 
				String operationName = "modifyAppModelTypeCd";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[1];
				params[0] = iraApplyId;
				 logicComponent.invoke(operationName, params);
				 
				 status =2;
				// 逻辑构件名称             
				componentName = "com.bos.irm.getProfessionalRateInfo";
				// 逻辑流名称 
				operationName = "professionModifyStatus";
				logicComponent = LogicComponentFactory.create(componentName);
                //逻辑流的输入参数
				Object[] param = new Object[2];
				param[0] = iraApplyId;
				param[1] = status;
				logicComponent.invoke(operationName, param);
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
	 * 
	* @Title: executeBeforeIntegration
	* @Description: TODO(用于与外围系统交互前业务逻辑)
	* @param processInstId 流程实例ID号 
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException{
		
	}
	
	/**
	 * 
	* @Title: executeBeforeTerminate
	* @Description: TODO(用于普通流程结束前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException{
	}
	
	/**
	 * 
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException{
    	String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String iraApplyId=fg.get(0).toString();
				int status =1;
				// 逻辑构件名称             
				String componentName = "com.bos.irm.getProfessionalRateInfo";
				// 逻辑流名称 
				String operationName = "professionModifyStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[2];
				params[0] = iraApplyId;
				params[1] = status;
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
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeReject(String processInstId, Map workitem) throws EOSException{
    	String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String iraApplyId=fg.get(0).toString();
				int status =4;
				// 逻辑构件名称             
				String componentName = "com.bos.irm.getProfessionalRateInfo";
				// 逻辑流名称 
				String operationName = "professionModifyStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[2];
				params[0] = iraApplyId;
				params[1] = status;
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
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeAfterAbort(String processInstId, Map workitem)throws EOSException{
    	String[] xpath={"bizId"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String bizId=fg.get(0).toString();
				// 逻辑构件名称             
				String componentName = "com.bos.irm.getProfessionalRateInfo";
				// 逻辑流名称 
				String operationName = "professionalRatingDelete";
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

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> map = new HashMap<String, String>();
		String[] xpath={"bizId"};//获取相关数据的数组
		//com.bos.crt.crt_contract.checkIntegrityData.biz.ext
		//DataObject dataOb1 = DataObjectUtil.createDataObject("com.bos.crt.contractmanager.QueryContractsInfo");
		Object[] result1 = null;
		String conclusion = workitem.get("conclusion") == null ? "" : workitem.get("conclusion").toString();
		if(conclusion.equals("99") || conclusion.equals("999") ){
			return null;
		}
		
		try {
			List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String bizId=fg.get(0).toString();
				//逻辑构件名称
				String componentName1 = "com.bos.irm.queryRatingResult";
				// 逻辑流名称
				String operationName1 = "checkIsNotSave";
				ILogicComponent logicComponent1 = LogicComponentFactory
				.create(componentName1);
//				获取流程中的岗位信息
				String postCd = workitem.get("activityDefId").toString();
				//逻辑流的输入参数
				Object[] params1 = new Object[2];
				params1[0] = bizId;
				params1[1] = postCd;
				result1 = logicComponent1.invoke(operationName1, params1);
				
				if(result1[0] == "00"){
					map.put("errorNum", "2");
					map.put("errorCode", "01");
					map.put("errorContent", "前先保存评级结论再提交");
					return map;
				}
		} catch (EOSRuntimeException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			map.put("errorNum", "2");
			map.put("errorCode", "01");
			map.put("errorContent", "检查出错！");
			return map;
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			map.put("errorNum", "2");
			map.put("errorCode", "01");
			map.put("errorContent", "检查出错！");
		} catch (Throwable e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			map.put("errorNum", "2");
			map.put("errorCode", "01");
			map.put("errorContent", "检查出错！");
		}
		return map;
	}
	
}
