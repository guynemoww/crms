package com.bos.inter.CallBPS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.dao.WorkItemInstanceDAO;
import com.bos.bps.op.DisposeTool;
import com.bos.bps.service.ActivityInstManagerService;
import com.bos.bps.service.WorkItemManangerService;
import com.bos.bps.util.FlowConstants;
import com.bos.bps.util.FlowUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.PositionUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.eos.workflow.data.WFActivityInst;
import com.eos.workflow.data.WFWorkItem;
import com.eos.workflow.omservice.WFParticipant;
import commonj.sdo.DataObject;

@Bizlet("CallBpsForLoan")
public class CallBpsForLoan {
	public static TraceLogger logger = new TraceLogger(CallBpsForLoan.class);
	@Bizlet("创建流程")
	public static Boolean setBpsForLoan(String processInstId,DataObject user,DataObject bpsPara) throws Exception {

		logger.info("=====>【CallBpsForLoan.setBpsForLoan】流程程实例ID："
				+ processInstId);
		//		获取当前活动实例
		WFActivityInst activityInst = ActivityInstManagerService.getCurrentActivityInstByProcessInstID(Long.valueOf(processInstId));
		
		logger.info("=====>【CallBpsForLoan.setBpsForLoan】获取当前活动实例成功，实例号为："+activityInst.getActivityInstID()+",用户名："+user.getString("createUserNum")+"_"+user.getString("createOrgNum")+",密码："+user.getString("password"));
		
		//		获取工作项
		List<WFWorkItem> temp = WorkItemManangerService
				.queryWorkItemsByActivityInstID(activityInst.getActivityInstID());
		logger.info("=====>【CallBpsForLoan.setBpsForLoan】获取工作项成功！");
		//		设置下一审批人员
		DisposeTool dl=new DisposeTool();
		String partit = dl.getSaleTeamerByUserId(user.getString("createUserNum"));
		if(null == partit){
			partit=dl.getUserIdNameStr("P1005",bpsPara.getString("orgcode") ,bpsPara.getString("orglevel"));
		}
		WFParticipant[] bpsParticipant = FlowUtil.createParticipant(partit,"person");
		
		String next_post="next_P"+bpsPara.getString("orglevel")+"005";
		logger.info("=====>【CallBpsForLoan.setBpsForLoan】下一审批人为："+partit+",下一岗位参与人变量："+next_post);
		String acDefId = activityInst.getActivityDefID();
			logger.info("=====>【CallBpsForLoan.setBpsForLoan】设置参与人成功！");
			PositionUtil p=new PositionUtil();
			String isNotConf=p.checkPositionExist("R_P1001_3",bpsPara.getString("orgid"));
			logger.info("=====>【isNotConf：放款支付岗配置标志】 "+isNotConf);
//			设置相关参数
			Map<String,Object> relaDatas = new HashMap<String,Object>();
			relaDatas.put("isNotConf",isNotConf);
			relaDatas.put(activityInst.getActivityDefID()+"_conclusion", "1");
			//			提交至下一节点
		   WorkItemManangerService.sumitProcessToNext(String.valueOf(temp.get(0).getWorkItemID()), relaDatas);
				logger.info("=====>【CallBpsForLoan.setBpsForLoan】提交至下一岗成功,保存工作项！");
				String next_user_num = null;
				String next_user_name = null;
				if(null!=partit){
					
					if(partit.indexOf("|")!=-1){
						
						next_user_num = "P1005";
						next_user_name = "全体岗位人员";
					}else{
						
						String[] strs = partit.split("_");
						next_user_num = strs[0];
						next_user_name = strs[1];
					}
					
				}
				
				//保存工作项
				DataObject wi = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
				wi.set("processInstId", processInstId);
				wi.set("workItemId", String.valueOf(temp.get(0).getWorkItemID()));
				wi.set("activityInstName", activityInst.getActivityInstName());
				wi.set("conclusion", "1");//同意
				wi.set("activityDefId", acDefId);
				wi.set("activityInstId", String.valueOf(activityInst
						.getActivityInstID()));
				
				
				wi.set("workInstanceId", String.valueOf(temp.get(0).getWorkItemID()));
				wi.set("tbWfmProcessinstance/processId", processInstId);
				wi.set("activityName", activityInst.getActivityInstName());
				wi.set("userName", user.getString("createUserName"));
				wi.set("userNum", user.getString("createUserNum"));
				wi.set("orgName",user.getString("createOrgName"));
				wi.set("orgNum", user.getString("createOrgNum"));
				wi.set("postName", user.getString("createPostName"));
				wi.set("postCd",user.getString("createPostCd"));
				wi.set("receiveTime",GitUtil.getBusiDate());
				wi.set("finishTime", GitUtil.getBusiDate());
				wi.set("nextUsersNum", next_user_num);
				wi.set("nextUsersName",next_user_name);
				wi.set("nextOrgName",bpsPara.getString("orgname"));
				wi.set("nextOrgNum",bpsPara.getString("orgcode"));
				wi.set("nextPostName","营销团队负责人");
				wi.set("nextPostNum",next_post);
				wi.set("opinion","同意");
				wi.set("conclusion", "1");//同意
				wi.set("workitemNum", "");
				wi.set("isSign", "");
				wi.set("status", "finish");
				wi.set("submitType", "1");//默认都是正常提交
				wi.set("tag",acDefId);
				wi.set("activityInstId", String.valueOf(activityInst
						.getActivityInstID()));
				wi.set("performtime", GitUtil.getBusiDate());
				//保存数据
				String workItemId =  String.valueOf(temp.get(0).getWorkItemID());
				DataObject data = WorkItemInstanceDAO.getWorkItemInstanceByKey(workItemId,processInstId);
				if(null != data.getString("userNum")&& !"".equals(data.getString("userNum")) ){
					
					DatabaseUtil.updateEntity("default", wi);
				}else{
					
					DatabaseUtil.insertEntity("default", wi);
				}
				
				//更新流程实例表
				DataObject pi = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTANCE_URL);
				pi.set("processId", processInstId);
				pi.set("activityName", "营销团队负责人");
				pi.set("postCd", next_post);
				pi.set("appointUserName", next_user_name);
				pi.set("appointUserNum", next_user_num);
				pi.set("appointOrgCd", bpsPara.getString("orgcode"));
				pi.set("appointOrgName", bpsPara.getString("orgname"));
				pi.set("lastupdatetime", GitUtil.getBusiDate());
				
				DatabaseUtil.updateEntity("default", pi);
				logger.info("=====>【CallBpsForLoan.setBpsForLoan】流程跳转结束！");
				return true;
	}

}
