/**
 * 
 */
package com.bos.bps.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.util.CommonUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFWorkItemManager;
import com.eos.workflow.data.WFActivityInst;
import com.eos.workflow.data.WFWorkItem;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

/**
 * @author ljf
 * @date 2015-05-06 10:42:53
 *
 */
@Bizlet("工作项管理类")
public class WorkItemManangerService {

	
	/**
	 * 根据工作项ID ，将指定的工作项改派到参与者（只能是个人）
	 * @param workItemId
	 * @param personId
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Bizlet("根据工作项ID ，将指定的工作项改派到参与者")
	public static void reassignWorkItem(String workItemId,String personId) throws NumberFormatException, Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		workItemMananger.reassignWorkItem(Long.valueOf(workItemId),personId);
	}
	
	/**
	 * 待办事项认领
	 * @param workItemID 工作项id
	 * @return
	 * @throws Exception
	 */
	@Bizlet("待办事项认领")
	public static void assignWorkItemToSelf(String workItemID)throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		workItemMananger.assignWorkItemToSelf(Long.valueOf(workItemID));
		
	}
	
	/**
	 * 根据活动实例，获取工作项
	 * @param actInstID
	 * @return
	 * @throws Exception 
	 */
	@Bizlet("根据活动实例，获取工作项")
	public static List<WFWorkItem> queryWorkItemsByActivityInstID(long actInstID) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		 IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		 List<WFWorkItem> wis = workItemMananger.queryWorkItemsByActivityInstID(actInstID, null);
		return wis;
	}

	/**
	 * 根据流程实例ID，查询正在运行的工作项
	 * @return
	 * @throws Exception 
	 */
	@Bizlet("根据流程实例ID，查询正在运行的工作项")
	public static List<Map<String,Object>> getCurrentWorkItems(String processInstId) throws NumberFormatException, Exception{
		
		List<Map<String,Object>> rlist = new ArrayList<Map<String,Object>>();
		
		//根据流程编号，获取当前正在运行的活动节点
		WFActivityInst ait = ActivityInstManagerService.getCurrentActivityInstByProcessInstID(Long.valueOf(processInstId));
		
		List<WFWorkItem>  wis = queryWorkItemsByActivityInstID(ait.getActivityInstID());
		if(null != wis && wis.size()>0){
			
			for (int i = 0; i < wis.size(); i++) {
				WFWorkItem wi = wis.get(i);
				int status = wi.getCurrentState();
				if(status==10){
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("processInstId", processInstId);
					temp.put("processInstName",wi.getProcessInstName());
					temp.put("activityDefId", wi.getActivityDefID());
					temp.put("activityInstId",wi.getActivityInstID());
					temp.put("createTime", CommonUtil.formatDate(wi.getCreateTime()));
					temp.put("workItemId", wi.getWorkItemID());
					temp.put("activityInstName", wi.getWorkItemName());
					temp.put("participant", wi.getParticipant());
					temp.put("partiName", wi.getPartiName());
					temp.put("processDefName",wi.getProcessDefName());
					
					//查询相关数据 approveType:为单独给业务申请模块使用
					String[] xpath ={"wfCreateOrgCode","wfCreateOrgName","wfCreateUserId","wfCreateUserName","cusName","bizId","bizType","approveType","wfOtherOrgCode","isWfOtherOrg","wfReadStatus","authAmt"};
					//实例化相关数据域服务对象
					List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
					temp.put("wfCreateOrgCode",list.get(0));
					temp.put("wfCreateOrgName",list.get(1));
					temp.put("wfCreateUserId",list.get(2));
					temp.put("wfCreateUserName",list.get(3));
					temp.put("cusName",list.get(4));
					temp.put("bizId",list.get(5));
					temp.put("bizType", list.get(6));
					temp.put("approveType", list.get(7));
					temp.put("wfOtherOrgCode", list.get(8));
					temp.put("isWfOtherOrg", list.get(9));
					if(null==list.get(10) || "".equals(list.get(10))){
						temp.put("wfReadStatus", "1");
					}else{
						
						temp.put("wfReadStatus", list.get(10));
					}
					temp.put("authAmt", list.get(11));
					
					rlist.add(temp);
				}
			}
		}
		return rlist;
	}
	
	/**
	 * 根据活动实例，查询正在运行的工作项个数
	 * @return
	 * @throws Exception 
	 */
	public static int getWorkingItemNumByActivityInstId(long actInstID) throws Exception{
		
		int wnum =0;
		List<WFWorkItem>  wis = queryWorkItemsByActivityInstID(actInstID);
		if(null != wis && wis.size()>0){
			
			for (int i = 0; i < wis.size(); i++) {
				WFWorkItem wi = wis.get(i);
				int status = wi.getCurrentState();
				if(status==10){
					
					wnum++;
				}
			}
		}
		return wnum;
	}
	
	
	
	/**
	 * 完成指定工作项(提交审批流程)
	 * @param relaDatas 相关数据
	 * @param workItemId 当前工作项实例id号
	 * @throws Exception
	 */
	@Bizlet("完成指定工作项")
	public static  void sumitProcessToNext(String workItemId,Map<String,Object> relaDatas) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		workItemMananger.finishWorkItemWithRelativeData(Long.valueOf(workItemId),relaDatas, false);
	}
	

	/**
	 * 完成指定工作项
	 * @param workItemId 当前工作项实例id号
	 * @throws Exception
	 */
	@Bizlet("完成指定工作项")
	public static  void finishWorkItem(String workItemId) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		workItemMananger.finishWorkItem(Long.valueOf(workItemId),false);
	}
	
	
	/**
	 * 终止指定工作项
	 * @param workItemId 当前工作项实例id号
	 * @throws Exception
	 */
	@Bizlet("终止指定工作项")
	public static  void terminateWorkItem(String workItemId) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager workItemMananger = client.getWorkItemManager();
		workItemMananger.terminateWorkItem(Long.valueOf(workItemId));
	}
	
	
}
