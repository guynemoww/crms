/**
 * 
 */
package com.bos.bps.service;

import java.util.List;

import com.eos.system.annotation.Bizlet;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFActivityInstManager;
import com.eos.workflow.api.IWFBackActivityManager;
import com.eos.workflow.data.WFActivityDefine;
import com.eos.workflow.data.WFActivityInst;
import com.primeton.workflow.api.WFServiceException;

/**
 * @author ljf
 * @date 2015-05-06 16:22:00
 *
 */
@Bizlet("活动实例管理类")
public class ActivityInstManagerService {
	
	
	/**
	 * 获取活动实例集合
	 * @param processInstID 流程实例
	 * @return
	 * @throws WFServiceException 
	 * @throws Exception
	 */
	@Bizlet("获取活动实例集合")
	public static List<WFActivityInst> queryActivityInstsByProcessInstID(long processInstID) throws WFServiceException{
		
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFActivityInstManager activityInstManager = client.getActivityInstManager(); 
		List<WFActivityInst>  list = activityInstManager.queryActivityInstsByProcessInstID(processInstID,null); 
		
		return list;
	}
	
	/**
	 * 根据流程实例ID，获取当前正在运行的活动实例
	 * @param processInstID
	 * @return
	 * @throws Exception
	 */
	@Bizlet("根据流程实例ID，获取当前正在运行的活动实例")
	public static WFActivityInst getCurrentActivityInstByProcessInstID(long processInstID) throws Exception{
		WFActivityInst ai = null;
		
		List<WFActivityInst>  list = queryActivityInstsByProcessInstID(processInstID);
		if(null != list && list.size()>0){
			
			for (int i = 0; i < list.size(); i++) {
				WFActivityInst inst = list.get(i);
				int status = inst.getCurrentState();
				//状态为运行
				if(status == 2){
					ai = inst;
					break;
				}
			}
		}
		return ai;
	}
	
	/**
	 * 根据流程实例，活动定义，获取最近实例化的活动实例
	 * @param procInstID 流程实例ID
	 * @param activityDefID 活动定义
	 * @param xid 分布式事务
	 * @return
	 * @throws Exception
	 */
	@Bizlet("根据流程实例，活动定义，获取最近实例化的活动实例")
	public static WFActivityInst findLastActivityInstByActivityID(String procInstID, String activityDefID) throws Exception{
		
		WFActivityInst ai = null;
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFActivityInstManager activityInstManager = client.getActivityInstManager(); 
		ai = activityInstManager.findLastActivityInstByActivityID(Long.valueOf(procInstID), activityDefID);
		
		return ai;
	}
	
	
	
	/**
	 * 回退流程
	 * @param processInstID 流程实例ID号
	 * @param currentActInstID 当前活动实例ID号
	 * @param destActDefID 目标活动定义ID号
	 * @return
	 * @throws Exception
	 */
	@Bizlet("回退流程")
	public static void backActivity(String currentActInstID, String destActDefID) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFBackActivityManager backActManager = client.getBackActivityManager(); 
		backActManager.backActivity(Long.valueOf(currentActInstID),destActDefID,"path");
	}
	
	/**
	 * 查询当前实例到目标活动定义之间所有运行时经过的活动定义
	 * @param processInstId
	 * @param currentActInstID
	 * @param destActDefID
	 * @throws Exception
	 */
	public static List<WFActivityDefine>  getPreviousWorkInfo(String processInstId,String currentActInstID,String destActDefID) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFBackActivityManager backActManager = client.getBackActivityManager(); 
		List<WFActivityDefine> list = backActManager.getPreviousActivities(Long.valueOf(currentActInstID), destActDefID);
		
		return list;
	}
	
}
