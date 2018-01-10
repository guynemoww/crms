/**
 * 
 */
package com.bos.bps.service;


import java.util.Map;

import com.bos.bps.util.FlowUtil;
import com.bos.utp.auth.bizlet.LogonUtil;
import com.eos.access.http.OnlineUserManager;
import com.eos.data.datacontext.IUserObject;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFProcessInstManager;
import com.eos.workflow.data.WFProcessInst;
import com.eos.workflow.omservice.WFParticipant;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

/**
 * @author ljf
 * @date 2015-04-13 11:06:27
 *
 */
@Bizlet("流程实例管理类")
public class ProcessInstManagerService {
	
	
	/**
	 * 创建并启动流程，同时设置相关数据域值
	 * @param processDefName 模板定义名称
	 * @param processInstName 流程实例名称
	 * @param processInstDesc 流程实例描述
	 * @param relaData 相关数据对象
	 * @return
	 * @throws Exception
	 */
	@Bizlet("创建并启动流程，设置相关数据域")
	public long createAndStartProcInstAndSetRelativeData(String processDefName,String processInstName,String processInstDesc,Map<String,Object> relaData) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		//默认启动流程模板默认生效的版本号 
		long processId = processInstManager.createAndStartProcInstAndSetRelativeData(processDefName, processInstName, processInstDesc,false,relaData);
		
		//创建成功后，回调接口创建方法
		FlowUtil.callbankAfterCreateMetchod(processId,processDefName);
		
		return processId;
	}
	
	/**
	 * 人工指定发起人，创建并启动流程，同时设置相关数据域值
	 * @param userid 流程发起人
	 * @param userName 发起人名称
	 * @param processDefName 模板定义名称
	 * @param processInstName 流程实例名称
	 * @param processInstDesc 流程实例描述
	 * @param relaData 相关数据对象
	 * @return
	 * @throws Exception
	 */
	@Bizlet("创建并启动流程，设置相关数据域")
	public long createAndStartProcInstAndSetRelativeDataByUserId(String userid,String userName,String processDefName,String processInstName,String processInstDesc,Map<String,Object> relaData) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		//默认启动流程模板默认生效的版本号 
		long processId = processInstManager.createProcessInstance(processDefName, processInstName, processInstDesc);
		//设置相关数据域参数
		RelativeDataManagerService.setRelativeDataBatch(processId, relaData);
		//设置相关数据域参与者数据
		WFParticipant[]  bpsParticipant =FlowUtil.createParticipant(userid+"_"+userName, "person");
		RelativeDataManagerService.setRelativeDataForParticipant(processId, "startPerson", bpsParticipant);
		//启动流程
		processInstManager.startProcessInstance(processId);
		//创建成功后，回调接口创建方法
		FlowUtil.callbankAfterCreateMetchod(processId,processDefName);
		
		return processId;
	}
	
	/**
	 * 网银通过接口发起流程
	 * @param userid 流程发起人
	 * @param userName 发起人名称
	 * @param processDefName 模板定义名称
	 * @param processInstName 流程实例名称
	 * @param processInstDesc 流程实例描述
	 * @param relaData 相关数据对象
	 * @return
	 * @throws Exception
	 */
	@Bizlet("创建并启动流程，设置相关数据域")
	//因为有登录操作---要设置安全锁
	public  long  createAndStartProcInstAndSetRelativeDataForWy(String userid,String userName,String processDefName,String processInstName,String processInstDesc,Map<String,Object> relaData) throws Exception{
		System.out.println("--------------同步等待------");
		//登录信贷系统---构建session 创建流程会从session中取值
		DataObject acOperator = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcOperator");
		acOperator.setString("userid",userid);
		acOperator.setString("ipaddress","127.0.0.1");
		IUserObject userObject = LogonUtil.initUserObject(acOperator);
		OnlineUserManager.login(userObject);
		BPSServiceClientFactory.getLoginManager().setCurrentUser(userObject.getUserId(),userObject.getUserName(),"", ""); 

		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		//默认启动流程模板默认生效的版本号 
		long processId = processInstManager.createProcessInstance(processDefName, processInstName, processInstDesc);
		//设置相关数据域参数
		RelativeDataManagerService.setRelativeDataBatch(processId, relaData);
		//设置相关数据域参与者数据
		WFParticipant[]  bpsParticipant =FlowUtil.createParticipant(userid+"_"+userName, "person");
		RelativeDataManagerService.setRelativeDataForParticipant(processId, "startPerson", bpsParticipant);
		//启动流程
		processInstManager.startProcessInstance(processId);
		//创建成功后，回调接口创建方法
		FlowUtil.callbankAfterCreateMetchod(processId,processDefName);
		
		//删除登录
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pub.userLoginManage");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("deleteUserLogin", new Object[] { userid });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return processId;
	}
	
	/**
	 * 终止审批流程
	 * @param processInstId 流程实例号
	 * @return
	 * @throws Exception
	 */
	@Bizlet("终止审批流程")
	public static void terminateProcessInstance(String processInstId) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		processInstManager.terminateProcessInstance(Long.valueOf(processInstId));
	}
	
	
	/**
	 * 获取流程实例详细信息
	 * @param processInstID 流程实例ID
	 * @return
	 * @throws Exception
	 */
	@Bizlet("获取流程实例详细信息")
	public static  WFProcessInst queryProcessInstDetail(long processInstID) throws Exception{
		
		WFProcessInst pi = null;
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		pi=processInstManager.queryProcessInstDetail(processInstID);
		
		return pi;
	}
	
	/**
	 * 恢复挂起的流程
	 * @param xid 分布式事务ID号
	 * @param processInstID 流程实例ID
	 * @return
	 * @throws Exception
	 */
	@Bizlet("恢复挂起的流程")
	public static void resumeProcessInstance(String processInstID) throws Exception{
		
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		processInstManager.resumeProcessInstance(Long.valueOf(processInstID));
		
	}
	
	/**
	 * 挂起流程
	 * @param processInstID 流程实例ID
	 * @return
	 * @throws Exception
	 */
	@Bizlet("挂起流程")
	public static void suspendProcessInstance(String processInstID) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFProcessInstManager processInstManager = client.getProcessInstManager(); 
		processInstManager.suspendProcessInstance(Long.valueOf(processInstID));
	}
}
