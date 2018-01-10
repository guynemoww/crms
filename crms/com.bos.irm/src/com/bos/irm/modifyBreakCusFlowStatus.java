package com.bos.irm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.exception.EOSRuntimeException;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

public class modifyBreakCusFlowStatus implements IBIZProcess {
	
	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException{
		/**
		 String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
			BpsResultArray fg = FlowService.getRelativeDataBatch(processInstId, xpath);
			if(null == fg.getErrCode() || "".equals(fg.getErrCode())){
//				转化从流程中获取的业务数据，转化成数据
				ArrayOfString arrt = fg.getResult();
				String [] str =arrt.getString();
				//获取申请记录id
				String drId=str[0];
				String type=str[1];
				int status;
				
				//  type: 1  违约认定  2  违约重生
				if("1".equals(type)){
					status = 12 ;				// 待认定
				}else{
					status = 21 ;				// 待审核
				}
				
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "modifyDefaultStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
				 logicComponent.invoke(operationName, params);

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
		* 
		 */
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
		String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//  type: 1  违约认定  else  违约重生
				if("1".equals(type)){
					status = 12 ;				//	待认定
				}else{
					status = 21 ;				//	重生待认定
				}
				
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "modifyDefaultStatus";
				ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
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
    	String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//  type: 1  违约认定  2  违约重生
				if("1".equals(type)){
					status = 15 ;				//	认定生效
				}else{
					status = 25 ;				//	重生失效
				}
				
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "modifyDefaultSaveResult";
				ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
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
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException{
    	String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//  type: 1  违约认定  2  违约重生
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
				String operationName = "modifyDefaultStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
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
    	String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//type: 1  违约认定  2  违约重生
				if("1".equals(type)){
					status = 16 ;		// 认定失效
				}else if("3".equals(type)){
					status = 15 ;
				}else{
					status = 20;		// 重生待发起
				}
				
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "modifyDefaultStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
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
    	String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> fg = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String drId=fg.get(0).toString();
				String type=fg.get(1).toString();
				int status;
				
				//  type: 1  违约认定  2  违约重生
				if("1".equals(type)){
					status = 10 ;				// 待认定
				}else if("3".equals(type)){
					status = 15 ;				// 待审核
				}else{
					status = 20 ;				// 待审核
				}
				
				// 逻辑构件名称             
				String componentName = "com.bos.irm.irm_default_work";
				// 逻辑流名称 
				String operationName = "modifyDefaultStatus";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				Object[] params = new Object[3];
				params[0] = drId;
				params[1]= status;
				params[2]= type;
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
//		Map<String, String> map = new HashMap<String, String>();
//		String[] xpath={"bizId"};//获取相关数据的数组
//		//com.bos.crt.crt_contract.checkIntegrityData.biz.ext
//		//DataObject dataOb1 = DataObjectUtil.createDataObject("com.bos.crt.contractmanager.QueryContractsInfo");
//		Object[] result1 = null;
//		String conclusion = workitem.get("conclusion") == null ? "" : workitem.get("conclusion").toString();
//		if(conclusion.equals("99") || conclusion.equals("999") || conclusion == ""){
//			return null;
//		}
//		
//		try {
//			BpsResultArray fg1 = FlowService.getRelativeDataBatch(processInstId, xpath);
//			if(null == fg1.getErrCode() || "".equals(fg1.getErrCode())){
//				ArrayOfString arrt = fg1.getResult();
//				String [] str =arrt.getString();
//				//获取申请记录id
//				String bizId=str[0];
//				//逻辑构件名称
//				String componentName1 = "com.bos.irm.queryRatingResult";
//				// 逻辑流名称
//				String operationName1 = "checkIsNotSave";
//				ILogicComponent logicComponent1 = LogicComponentFactory
//				.create(componentName1);
//				//获取流程中的岗位信息
//				String postCd = workitem.get("activityDefId").toString();
//				//逻辑流的输入参数
//				Object[] params1 = new Object[2];
//				params1[0] = bizId;
//				params1[1] = postCd;
//				result1 = logicComponent1.invoke(operationName1, params1);
//				
//				if(result1[0] == "00"){
//					map.put("errorNum", "2");
//					map.put("errorCode", "01");
//					map.put("errorContent", "前先保存评级结论再提交");
//					return map;
//				}
//			}
//		} catch (EOSRuntimeException e1) {
//			// TODO 自动生成 catch 块
//			e1.printStackTrace();
//			map.put("errorNum", "2");
//			map.put("errorCode", "01");
//			map.put("errorContent", "检查出错！");
//			return map;
//		} catch (Exception e1) {
//			// TODO 自动生成 catch 块
//			e1.printStackTrace();
//			map.put("errorNum", "2");
//			map.put("errorCode", "01");
//			map.put("errorContent", "检查出错！");
//		} catch (Throwable e1) {
//			// TODO 自动生成 catch 块
//			e1.printStackTrace();
//			map.put("errorNum", "2");
//			map.put("errorCode", "01");
//			map.put("errorContent", "检查出错！");
//		}
//		return map;
		return null;
	}
	
}
