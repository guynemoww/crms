package com.bos.mq.client;

import com.bos.mq.conf.ConfigManager;
import com.bos.mq.util.KeyGenerator;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class BaseMQ {
	
	public MQQueueManager qMgr;
	
	public MQQueue queue;
	
	/**
	 * 连接MQ
	 * @param mqbean
	 * @return 
	 * @return
	 * @throws MQException
	 */
	public void mqConn(MQbean mqbean) throws MQException{
		String hostName = mqbean.getHostname();
		String channel = mqbean.getChannel();
		String qManager = mqbean.getQManager();
		MQEnvironment.hostname = hostName;
		MQEnvironment.channel = channel;
		MQEnvironment.CCSID = mqbean.getCcsid();
		// 建立MQEnvironment 属性以便客户机连接
		MQEnvironment.port = mqbean.getPort();
		MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES);
		// Connection To the Queue Manager(连接到队列管理器)
		qMgr = new MQQueueManager(qManager);
	}
	
	/**
	 * 关闭MQ连接
	 * @throws MQException
	 */
	public void mqClose() throws MQException{
		try {
			if(qMgr.isConnected()){
				qMgr.commit();// Commit the transaction.(提交事务处理)
			}
			if(queue.isOpen()){
				queue.close();// Close the the Queue and Queue manager objects.(关闭队列和队列管理器对象)
			}
			if(qMgr.isConnected()){
				qMgr.disconnect();
			}
		} catch (MQException e) {
			// TODO: handle exception
			if(e.getMessage().indexOf("2033") < 0){
				e.printStackTrace();
			}
		}finally{
			if(queue.isOpen()){
				queue.close();
			}
			if(qMgr.isConnected()){
				qMgr.disconnect();
			}
		}
	}
	
	/**
	 * 发送消息初始化MQ参数
	 * @param data
	 * @return MQbean
	 */
	public MQbean getMQParameter(byte[] data) {
		MQbean mqbean = new MQbean();
		mqbean.setHostname(ConfigManager.getStringProperty("hostname"));
		mqbean.setPort(ConfigManager.getIntProperty("port"));
		mqbean.setChannel(ConfigManager.getStringProperty("channel"));
		mqbean.setQManager(ConfigManager.getStringProperty("qManager"));
		//		mqbean.setQueuename(ConfigManager.getStringProperty("sendqname"));	//设置发送队列
		mqbean.setCcsid(ConfigManager.getIntProperty("CCSID"));
		mqbean.setContext(data);
		mqbean.setRelationid(KeyGenerator.getUUID());
		return mqbean;
	}

	/**
	 * 接收消息初始化MQ参数
	 * @return MQbean
	 */
	public MQbean getMQParameter() {
		MQbean mqbean = new MQbean();
		mqbean.setHostname(ConfigManager.getStringProperty("hostname"));
		mqbean.setPort(ConfigManager.getIntProperty("port"));
		mqbean.setChannel(ConfigManager.getStringProperty("channel"));
		mqbean.setQManager(ConfigManager.getStringProperty("qManager"));
		mqbean.setCcsid(ConfigManager.getIntProperty("CCSID"));
		mqbean.setRelationid(KeyGenerator.getUUID());
		return mqbean;
	}
}
