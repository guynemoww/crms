package com.bos.bps.dao;

import com.bos.bps.util.FlowConstants;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;

import commonj.sdo.DataObject;

/**
 * 参与者操作类
 * @author ljf
 *
 */
public class WorkParticipantDAO {
	public static  TraceLogger logger = new TraceLogger(WorkParticipantDAO.class);
	/**
	 * 根据传入的参与者拼接字符串，循环新增
	 * @param users
	 * @param workitemId
	 */
	public static void addTbWfmParticipantByUsers(String users , String workitemId){
		
		logger.info("------------>下一节点参与者："+users+",插入开始！");
		if(null!=users&&!"".equals(users)&&!"null".equals(users)){
			
			if(users.indexOf(",")!=-1){
				String[] userArry = users.split(",");
				DataObject [] datas = new DataObject[userArry.length];
				for (int i = 0; i < userArry.length; i++) {
					String string = userArry[i];
					DataObject template = DataObjectUtil.createDataObject(FlowConstants.WORKPARTICIPANT_URL);
					template.set("wid", workitemId);
					template.set("userNum", string);
					datas[i] = template;
				}
				DatabaseUtil.insertEntityBatch(GitUtil.DEFAULT_DS_NAME, datas);
			}else{
				
				DataObject template = DataObjectUtil.createDataObject(FlowConstants.WORKPARTICIPANT_URL);
				template.set("wid", workitemId);
				template.set("userNum", users);
				DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, template);
				
			}
		}
		logger.info("------------>新增下一节点参与者结束！");
	}
}
