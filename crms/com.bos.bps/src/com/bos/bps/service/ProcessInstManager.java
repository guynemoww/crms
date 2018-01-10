/**
 * 
 */
package com.bos.bps.service;


import com.bos.bps.dao.WorkItemInstanceDAO;
import com.bos.bps.util.FlowConstants;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author lijf
 * @date 2014-09-19 14:05:12
 * 流程实例管理类。操作流程上的复杂业务处理。
 */
@Bizlet("流程实例管理类")
public class ProcessInstManager {
	
	public static  TraceLogger logger = new TraceLogger(ProcessInstManager.class);
	/**
	 * 自动提交工作项，完成流程
	 * @param bizId 业务申请主键
	 * @throws Exception 
	 */
	@Bizlet("自动提交工作项，完成流程")
	public String autoSubmitWorkItemToFinishProcessInst(String bizId) throws Exception{
		
		logger.info("----------->自动完成工作项，结束流程开始，业务主键："+bizId);
		String result ="1";
		Object[] obj = DatabaseExt.queryByNamedSql("default", "com.bos.bps.dataset.bpmNamingSql.queryWorkItemByBizId", bizId);
		if(null != obj && obj.length>0){
			
			DataObject dataObj = (DataObject)obj[0];
			String processId = dataObj.getString("PROCESSID");
			String workItemId = dataObj.getString("WORKITEMID");
			String userNum = dataObj.getString("USERNUM");
			String orgNum = dataObj.getString("ORGNUM");
			String pwd = dataObj.getString("PASSWORD");
			logger.info("----------->完成工作项的参数：流程实例ID："+processId+",工作项ID:"+workItemId+",参与者:"+userNum+",机构号:"+orgNum+",密码:"+pwd);
			WorkItemManangerService.sumitProcessToNext(workItemId,null);
				
			DataObject data = WorkItemInstanceDAO.getWorkItemInstanceByKey(workItemId,processId);
			//更新工作项实例信息
			DataObject wi = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
			wi.set("id", data.getString("id"));
			wi.set("tbWfmProcessinstance/processId", processId);
			wi.set("workInstanceId", workItemId);
			wi.set("status", "finish");
			wi.set("conclusion","1");
			wi.set("submitType", "1");//默认都是正常提交
			wi.set("finishTime", GitUtil.getBusiDate());
			DatabaseUtil.updateEntity("default", wi);
			//更新流程实例信息
			DataObject pi = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTANCE_URL);
			pi.set("processId", processId);
			pi.set("activityName", "结束");
			pi.set("processStatus", "finish");
			pi.set("appointUserName", "无");
			pi.set("appointUserNum", "无");
			pi.set("appointOrgCd", "无");
			pi.set("appointOrgName", "无");
			pi.set("lastupdatetime", GitUtil.getBusiDate());
			pi.set("finishTime",GitUtil.getBusiDate());
			DatabaseUtil.updateEntity("default", pi);
			
		}else{
			
			logger.info("----------->没有获取到工作项信息");
			result="0";
		}
		return result;
	}

}
