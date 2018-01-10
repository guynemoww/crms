/**
 * 
 */
package com.bos.bps.dao;


import java.util.Map;

import com.bos.bps.util.CommonUtil;
import com.bos.bps.util.FlowConstants;
import com.bos.pub.GitUtil;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author lijianfei
 * @date 2014-03-18 16:36:42
 *
 */
public class WorkItemInstanceDAO {

	public static  TraceLogger logger = new TraceLogger(WorkItemInstanceDAO.class);
	/**
	 * 新增一条工作实例信息
	 * @param it
	 * @throws EOSException 
	 */
	public static void insertWorkItemInstance(Map<String,String> it) throws EOSException{
		
		
		try{
		
			DataObject wi = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
			IUserObject user = CommonUtil.getIUserObject();
			Map<String,Object> attmap = user.getAttributes();
			wi.set("workInstanceId", it.get("workItemId"));
			wi.set("tbWfmProcessinstance/processId", it.get("processInstId"));
			wi.set("activityName", it.get("activityInstName"));
			wi.set("userName", user.getUserName());
			wi.set("userNum", user.getUserId());
			wi.set("orgName",user.getUserOrgName());
			wi.set("orgNum", attmap.get("orgcode"));
			wi.set("postName", it.get("activityInstName"));
			String posicode = it.get("activityDefId");
			//去掉P_,_01等前后缀
			posicode = CommonUtil.getPosition(posicode);
			//去掉第二位的等级标志
			posicode = "P1"+posicode.substring(2);
			
			wi.set("postCd",posicode);
			wi.set("receiveTime",GitUtil.toDate(it.get("startTime"),"yyyy-MM-dd HH:mm:ss"));
			wi.set("finishTime", GitUtil.getBusiDate());
			String nextUsersNum = it.get("nextUsersNum");
			if(null!= nextUsersNum && nextUsersNum.indexOf(",")!=-1){
				String[] nextUserNu = nextUsersNum.split(",");
				if(nextUserNu.length > 4){
					String nextUsersNum2 = "";
					for (int i = 0; i < 4; i++) {
						nextUsersNum2 += nextUserNu[i]+",";
					}
					nextUsersNum = nextUsersNum2.substring(0,nextUsersNum2.length()-1)+"...";
				}
				//nextUsersNum = nextUsersNum.substring(0,nextUsersNum.indexOf(","))+"...";
			}
			wi.set("nextUsersNum", nextUsersNum);
			String nextUsersName = it.get("nextUsersName");
			if(null != nextUsersName && nextUsersName.indexOf(",")!=-1){
				String[] nextUserNa = nextUsersName.split(",");
				if(nextUserNa.length > 4){
					String nextUsersName2 = "";
					for (int i = 0; i < 4; i++) {
						nextUsersName2 += nextUserNa[i]+",";
					}
					nextUsersName = nextUsersName2.substring(0,nextUsersName2.length()-1)+"...";
				}
				//nextUsersName=nextUsersName.substring(0,nextUsersName.indexOf(","))+"...";
			}
			wi.set("nextUsersName",nextUsersName);
			wi.set("nextOrgName",it.get("nextOrgName"));
			wi.set("nextOrgNum",it.get("nextOrgCd"));
			
			//处理下一岗位
			String nextPostNum = it.get("nextPostCd");
			if((null!=nextPostNum &&!"".equals(nextPostNum))||FlowConstants.ACTIVITYPE_FINISH.equals(it.get("activityType"))){
				
				wi.set("nextPostName",it.get("nextPostName"));
				wi.set("nextPostNum",nextPostNum);
				
			}else{
				
				nextPostNum = it.get("userVariable");
				wi.set("nextPostName","客户经理岗");
				wi.set("nextPostNum",nextPostNum);
				
			}
			wi.set("opinion",it.get("opinion"));
			wi.set("conclusion",it.get("conclusion"));
			wi.set("workitemNum", "");
			wi.set("isSign", "");
			wi.set("status", "finish");
			wi.set("submitType", "1");//默认都是正常提交
			wi.set("tag",it.get("activityDefId"));
			wi.set("activityId",it.get("activityInstId"));
			
			String isSrc = (String)it.get("isSrc");
			//如果是首次发起，又来源业务模块，，则更新执行日期
			if(null != isSrc && !"".equals(isSrc)){
				
				wi.set("performtime", GitUtil.getBusiDate());
			}
			     
			//保存数据
			String workItemId = it.get("workItemId");
			DataObject temp = getWorkItemInstanceByKey(workItemId,it.get("processInstId"));
			String wid = "";
			if(null != temp.getString("userNum")&& !"".equals(temp.getString("userNum"))){
				//赋置主键
				wid = temp.getString("id");//为参与者插入准备
				wi.set("id", temp.getString("id"));
				DatabaseUtil.updateEntity("default", wi);
			}else{
				DatabaseUtil.insertEntity("default", wi);
				wid = wi.getString("id");//为参与者插入准备
			}
			//插入参与者
			WorkParticipantDAO.addTbWfmParticipantByUsers(it.get("nextUsersNum"), wid);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------>流程编号："+it.get("processInstId")+",更新或新增工作项失败！");
			throw new EOSException(e);
		}
	}
	
	
	/**
	 * 当拒绝时，更新一条工作实例信息
	 * @param it
	 * @throws EOSException 
	 */
	public static void updateWorkItemInstanceByReject(Map<String,String> it) throws EOSException{
		
		
		try{
		
			DataObject wi = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
			IUserObject user = CommonUtil.getIUserObject();
			Map<String,Object> attmap = user.getAttributes();
			wi.set("workInstanceId", it.get("workItemId"));
			wi.set("tbWfmProcessinstance/processId", it.get("processInstId"));
			wi.set("activityName", it.get("activityInstName"));
			wi.set("userName", user.getUserName());
			wi.set("userNum", user.getUserId());
			wi.set("orgName",user.getUserOrgName());
			wi.set("orgNum", attmap.get("orgcode"));
			wi.set("postName", it.get("activityInstName"));
			String posicode = it.get("activityDefId");
			//去掉P_,_01等前后缀
			posicode = CommonUtil.getPosition(posicode);
			//去掉第二位的等级标志
			posicode = "P1"+posicode.substring(2);
			
			wi.set("postCd",posicode);
			wi.set("receiveTime",GitUtil.toDate(it.get("startTime"),"yyyy-MM-dd HH:mm:ss"));
			wi.set("finishTime", GitUtil.getBusiDate());
			wi.set("nextUsersNum", "");
			wi.set("nextUsersName","");
			wi.set("nextOrgName","");
			wi.set("nextOrgNum","");
			wi.set("nextPostName","");
			wi.set("nextPostNum","");
			wi.set("opinion",it.get("opinion"));
			wi.set("conclusion",it.get("conclusion"));
			wi.set("workitemNum", "");
			wi.set("isSign", "");
			wi.set("status", "finish");
			wi.set("submitType", "1");//默认都是正常提交
			wi.set("tag",it.get("activityDefId"));
			wi.set("activityId",it.get("activityInstId"));
			
			String isSrc = (String)it.get("isSrc");
			//如果是首次发起，又来源业务模块，，则更新执行日期
			if(null != isSrc && !"".equals(isSrc)){
				
				wi.set("performtime", GitUtil.getBusiDate());
			}
			     
			//保存数据
			String workItemId = it.get("workItemId");
			DataObject temp = getWorkItemInstanceByKey(workItemId,it.get("processInstId"));
			String wid = "";
			if(null != temp.getString("userNum")&& !"".equals(temp.getString("userNum"))){
				//赋置主键
				wid = temp.getString("id");//为参与者插入准备
				wi.set("id", temp.getString("id"));
				DatabaseUtil.updateEntity("default", wi);
			}else{
				DatabaseUtil.insertEntity("default", wi);
				wid = wi.getString("id");//为参与者插入准备
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------>流程编号："+it.get("processInstId")+",更新或新增工作项失败！");
			throw new EOSException(e);
		}
	}
	
	/**
	 * 新增一条工作实例信息,无session的新增
	 * @param it
	 * @throws EOSException 
	 */
	public static void insertWorkItemInstanceNoSession(Map<String,String> it) throws EOSException{
		try{
			DataObject wi = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
			wi.set("workInstanceId", it.get("workItemId"));
			wi.set("tbWfmProcessinstance/processId", it.get("processInstId"));
			wi.set("activityName", it.get("activityInstName"));
			wi.set("userName", it.get("userName"));
			wi.set("userNum", it.get("userId"));
			wi.set("orgName",it.get("orgName"));
			wi.set("orgNum", it.get("orgCode"));
			wi.set("postName", it.get("activityInstName"));
			String posicode = it.get("activityDefId");
			//去掉P_,_01等前后缀
			posicode = CommonUtil.getPosition(posicode);
			//去掉第二位的等级标志
			posicode = "P1"+posicode.substring(2);
			
			wi.set("postCd",posicode);
			wi.set("receiveTime",GitUtil.toDate(it.get("startTime"),"yyyy-MM-dd HH:mm:ss"));
			wi.set("finishTime", GitUtil.getBusiDate());
			wi.set("nextUsersNum", it.get("nextUsersNum"));
			wi.set("nextUsersName",it.get("nextUsersName"));
			wi.set("nextOrgName",it.get("nextOrgName"));
			wi.set("nextOrgNum",it.get("nextOrgCd"));
			wi.set("nextPostName",it.get("nextPostName"));
			wi.set("nextPostNum",it.get("nextPostCd"));
			wi.set("opinion",it.get("opinion"));
			wi.set("conclusion",it.get("conclusion"));
			wi.set("workitemNum", "");
			wi.set("isSign", "");
			wi.set("status", "finish");
			wi.set("submitType", "1");//默认都是正常提交
			wi.set("tag",it.get("activityDefId"));
			wi.set("activityId",it.get("activityInstId"));
			     
			//保存数据
			String workItemId = it.get("workItemId");
			DataObject temp = getWorkItemInstanceByKey(workItemId,it.get("processInstId"));
			if(null != temp.getString("userNum")&& !"".equals(temp.getString("userNum"))){
				//赋置主键
				wi.set("id", temp.getString("id"));
				DatabaseUtil.updateEntity("default", wi);
			}else{
				wi.set("performtime", GitUtil.getBusiDate());
				DatabaseUtil.insertEntity("default", wi);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------>流程编号："+it.get("processInstId")+",更新或新增工作项失败！");
			throw new EOSException(e);
		}
	}
	
	
	
	/**
	 * 根据工作项ID,状态，获取一条记录
	 * @param workItemId
	 * @return
	 */
	public static DataObject getWorkItemInstanceByKey(String workItemId,String processInstId){
		
		DataObject obj = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
		obj.set("tbWfmProcessinstance/processId", processInstId);
		obj.set("workInstanceId", workItemId);
		obj.set("status", "run");
		DatabaseUtil.expandEntityByTemplate("default", obj, obj);
		
		return obj;
		
	}
	
	/**
	 * 根据流程编号，活动定义ID，获取一条工作项记录
	 * @param processId 流程编号
	 * @param activityId 活动定义ID
	 * @return
	 */
	public static DataObject getWorkItemByProcessIdAndActivityId(String processId,String activityId){
		
		DataObject obj = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
		obj.set("tbWfmProcessinstance/processId", processId);
		obj.set("tag", activityId);
		DataObject [] datas = DatabaseUtil.queryEntitiesByTemplate("default", obj);
		if(null !=datas && datas.length>0){
			
			obj = datas[0];
		}
		
		return obj;
	}
	
}
