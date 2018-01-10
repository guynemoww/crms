package com.bos.ews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class warnAddReview implements IBIZProcess {
	//校验数据完整性
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		Map<String, String> map = new HashMap<String, String>();
        int panduan = 0;
        String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			
				//获取申请记录id
				String applyId=list.get(0).toString();                   //业务ID
				String conclusion = (String)workitem.get("conclusion");
				String processId = processInstId;        //流程实例ID
				String bizType=list.get(1).toString();                   //判断是关闭信号还是新增信号
				if(conclusion.equals("99")){
					panduan=1;
				}else{
				Object[] result = null;
				// 逻辑构件名称            .
				String componentName = "com.bos.ews.warnInfo";
				// 逻辑流名称 
				String operationName = "checkToSetWarnLevel";
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数  .
				Object[] params = new Object[1];
				params[0] = applyId;
				result = logicComponent.invoke(operationName, params);
				if(bizType.equals("2")){                                      //信号关闭暂时不做校验
					panduan=1;
				}else{
				     if(result[0]=="1"){
					         panduan=1;
				       }else if(result[0]=="0"){
					         map.put("errorNum", "2");
        			         map.put("errorCode", "error");
					         map.put("errorContent", "该客户未有存量生效信号，不能发起级别认定！");
				      }
				}
				}
				/*else if(result[0]=="2"){
					map.put("errorNum", "2");
        			map.put("errorCode", "error");
					map.put("errorContent", "黄色以上级别需制定预案！");
				}*/
			if(panduan==1){
				return null;
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
		return map;
	}

	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId,Map workitem) throws EOSException
	{
		
	};
	
	/**
	 * 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeSubmit(String processInstId,Map workitem) throws EOSException{
		String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId=list.get(0).toString();                   //业务ID
				String processId = processInstId;        //流程实例ID
				String bizType=list.get(1).toString();                   //判断是关闭信号还是新增信号
				Object[] result = null;
				String componentName;                    //逻辑流URL
				String operationName;                    //逻辑流名称
				Object[] params = null;         //传入逻辑流的参数
				if(bizType.equals("2")){
					params = new Object[3];
				     // 逻辑构件名称             
				      componentName = "com.bos.ews.warnInfo";
				     // 逻辑流名称 
				      operationName = "closeWarnInfoReview";
				      params[0] = applyId;                     //业务ID
				      params[1] = processId;                   //流程实例ID
				}else{
					 params = new Object[4];
                     //逻辑构件名称            
					 componentName = "com.bos.ews.warnInfo";
					 // 逻辑流名称 
					 operationName = "addWarnInfoReview";
					 params[0] = applyId;                     //业务ID
				     params[1] = processId;                   //流程实例ID
				     params[2] = "4";                         //流程实例ID
				}
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				
				
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
	};
	
	/**
	 * 
	* @Title: executeBeforeIntegration
	* @Description: TODO(用于与外围系统交互前业务逻辑)
	* @param processInstId 流程实例ID号 
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeIntegration(String processInstId,Map workitem) throws EOSException{
		
	};
	
	/**
	 * 
	* @Title: executeBeforeTerminate
	* @Description: TODO(用于普通流程结束前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeTerminate(String processInstId,Map workitem) throws EOSException{
		
    	String[] xpath={"bizId","bizType"};//获取相关数据的数组
		//获取流程中的业务数据
    	try {
    		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取申请记录id
				String applyId=list.get(0).toString();                   //业务ID
				String processId = processInstId;        //流程实例ID
				String bizType=list.get(1).toString();                   //判断是关闭信号还是新增信号
				Object[] result = null;
				String componentName;                    //逻辑流URL
				String operationName;                    //逻辑流名称
				Object[] params = null;         //传入逻辑流的参数
				if(bizType.equals("2")){
					  params = new Object[3];
				     // 逻辑构件名称             
				      componentName = "com.bos.ews.warnInfo";
				     // 逻辑流名称 
				      operationName = "closeWarnInfoReview";
				      params[0] = applyId;                     //业务ID
				      params[1] = processId;                   //流程实例ID
				      params[2] = "4";                         //变更状态
				}else{
					 params = new Object[4];
                     //逻辑构件名称            
					 componentName = "com.bos.ews.warnInfo";
					 // 逻辑流名称 
					 operationName = "addWarnInfoReview";
					 params[0] = applyId;                     //业务ID
					 params[1] = processId;                   //流程实例ID
					 params[2] = "4";                        //流程实例ID
					 params[3] = "1";                        //结束流程
				}
				ILogicComponent logicComponent = LogicComponentFactory
				.create(componentName);
                //逻辑流的输入参数
				
				
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
    };
	
	/**
	 * 
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeUntread(String processInstId,Map workitem) throws EOSException{
		
	};
	
	/**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeBeforeReject(String processInstId,Map workitem) throws EOSException{
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
	};
    
    /**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
    public void executeAfterAbort(String processInstId,Map workitem)throws EOSException{
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
	};

}
