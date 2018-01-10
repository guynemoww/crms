/**
 * 
 */
package com.bos.bps.dao;


import java.util.Map;

import com.bos.bps.util.FlowConstants;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * 流程实例表操作类
 * @author lijianfei
 * @date 2014-03-18 16:36:06
 *
 */
public class ProcessInstanceDAO {

	public static  TraceLogger logger = new TraceLogger(ProcessInstanceDAO.class);
	/**
	 * 更新流程实例信息
	 * @param it
	 * @throws EOSException 
	 */
	public static void updateProcessInstance(Map<String,String> it) throws EOSException{
		try{
			
			DataObject pi = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTANCE_URL);
			pi.set("processId", it.get("processInstId"));
			pi.set("activityName", it.get("nextPostName"));
			pi.set("postCd", it.get("nextPostCd"));
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
				//nextUsersName = nextUsersName.substring(0,nextUsersName.indexOf(","))+"...";
			}
			pi.set("appointUserName", nextUsersName);
			String nextUsersNum = it.get("nextUsersNum");
			if(null!=nextUsersNum && nextUsersNum.indexOf(",")!=-1){
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
			pi.set("appointUserNum",nextUsersNum );
			pi.set("appointOrgCd", it.get("nextOrgCd"));
			pi.set("appointOrgName", it.get("nextOrgName"));
			pi.set("lastupdatetime", GitUtil.getBusiDate());
			
			DatabaseUtil.updateEntity("default", pi);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------>流程编号："+it.get("processInstId")+",更新流程实例失败！");
			throw new EOSException(e);
		}
	}
	
	/**
	 * 更新流程状态
	 * @param it
	 * @throws EOSException 
	 */
	public static void updateProcessInstanceStatus(Map<String,String> it) throws EOSException{
		try{
			DataObject pi = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTANCE_URL);
			pi.set("processId", it.get("processInstId"));
			pi.set("processStatus", "finish");
			pi.set("appointUserName", "无");
			pi.set("appointUserNum", "无");
			pi.set("appointOrgCd", "无");
			pi.set("appointOrgName", "无");
			pi.set("finishTime",GitUtil.getBusiDate());
			pi.set("lastupdatetime", GitUtil.getBusiDate());
			
			DatabaseUtil.updateEntity("default", pi);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("------------>流程编号："+it.get("processInstId")+",更新流程实例失败！");
			throw new EOSException(e);
		}
	}
	
	/**
	 * 根据业务主键，流程状态，获取一条流程实例信息
	 * @param bizId
	 * @param status
	 * @return
	 */
	public static DataObject getProcessInstanceByBizId(String bizId,String status){
		
		DataObject pi = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTANCE_URL);
		pi.set("productId", bizId);
		pi.set("processStatus", status);
		DatabaseUtil.expandEntityByTemplate("default", pi, pi);
		
		return pi;
	}
}
