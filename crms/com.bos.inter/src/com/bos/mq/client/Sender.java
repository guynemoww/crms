package com.bos.mq.client;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.bos.mq.conf.ConfigManager;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.spring.TraceLogger;
import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import commonj.sdo.DataObject;

/**
 * @author Sunny
 * 
 */
public class Sender extends BaseMQ {
	static TraceLogger log = new TraceLogger(Sender.class);
	
	public MQbean clientSend(MQbean mqbean) throws MQException{
		log.info("[客户端]开始发送消息...-[MessageID:"+ConfigManager.getStringProperty("messageID")+"]-[msgKey:"+mqbean.getMsgKey()+"]");
		MQbean mb = send(mqbean,ConfigManager.getStringProperty("messageID"));
		log.info("[客户端]发送消息完成!!!-[MessageID:"+ConfigManager.getStringProperty("messageID")+"]-[msgKey:"+mqbean.getMsgKey()+"]");
		return mb;
	}
	
	public MQbean serverSend(MQbean mqbean) throws MQException{
		log.info("[服务端]开始发送消息...-[MessageID:"+ConfigManager.getStringProperty("servermessageID")+"]-[msgKey:"+mqbean.getMsgKey()+"]");
		MQbean mb = send(mqbean,ConfigManager.getStringProperty("servermessageID"));
		log.info("[服务端]发送消息完成!!!-[MessageID:"+ConfigManager.getStringProperty("servermessageID")+"]-[msgKey:"+mqbean.getMsgKey()+"]");
		return mb;
	}
	/**
	 * @param mqbean
	 * @return
	 * @throws MQException 
	 */
	private MQbean send(MQbean mqbean,String messageID) throws MQException {
		String qName = ConfigManager.getStringProperty("sendqname");
		int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
		try {
			
			
			// Set the put message options , we will use the default
			// setting.(设置放置消息选项我们将使用默认设置。)
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			
			MQMessage outMsg = new MQMessage(); // Create The messagemqstr
			outMsg.expiry = MQC.MQEI_UNLIMITED;
			outMsg.messageId = messageID.getBytes();//定义系统消息ID
			if(mqbean.getRelationid() != null && !"".equals(mqbean.getRelationid())){
				outMsg.correlationId = mqbean.getRelationid().getBytes();
			}
//			outMsg.correlationId = KeyGenerator.genRandomNum(24).getBytes();
			// buffer(创建消息缓冲区)
			outMsg.format = MQC.MQFMT_STRING; // Set the MQMD format
			// field.(设置MQMD 格式字段)    Delphi
			// Prepare message with user data(准备用户数据消息)
			byte[] msgString;
			outMsg.characterSet = ConfigManager.getIntProperty("character");
			msgString = mqbean.getContext();
			//LogUtil.logInfo("消息发送的长度--" + msgString.length,null,null);
			log.info(mqbean.getMsgKey()+"]消息发送的长度--" + msgString.length,null,null);
			String msg = new String(msgString,"UTF-8");
			msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+msg;
			outMsg.write(msg.getBytes("UTF-8"));//采用二进制方式发送报文
			if(queue == null || !queue.isOpen()){
				if(qMgr == null || !qMgr.isConnected){
					log.info(mqbean.getMsgKey()+"]开始创建MQ连接...",null,null);
					mqConn(mqbean);//创建连接
					log.info(mqbean.getMsgKey()+"]成功创建MQ连接!!!",null,null);
				}
				// Open the queue(打开队列)
				log.info(mqbean.getMsgKey()+"]开始打开MQ队列["+qName+"]...",null,null);
				queue = qMgr.accessQueue(qName, openOptions, null, null, null);
				log.info(mqbean.getMsgKey()+"]成功打开MQ队列["+qName+"]!!!",null,null);
			}
			// Now we put The message on the Queue(现在我们在队列上放置消息)
			log.info(mqbean.getMsgKey()+"]开始发送消息，msgID为["+new String(outMsg.messageId)+"],correlationId为["+new String(outMsg.correlationId)+"]",null,null);
			log.info(mqbean.getMsgKey()+"]消息内容为："+msg,null,null);
			queue.put(outMsg, pmo);
			//LogUtil.logDebug("消息发送成功，msgID为["+new String(outMsg.messageId)+"],correlationId为["+new String(outMsg.correlationId)+"],消息内容为："+msg,null,null);
			log.info(mqbean.getMsgKey()+"]消息发送成功，msgID为["+new String(outMsg.messageId)+"],correlationId为["+new String(outMsg.correlationId)+"]",null,null);
			mqbean.setContext(msg.getBytes("UTF-8"));
			log.info(mqbean.getMsgKey()+"]开始保存交易信息...",null,null);
			saveTradInfo(mqbean);
			log.info(mqbean.getMsgKey()+"]成功保存交易对象!!!",null,null);
		} catch (Exception e) {
			log.error(mqbean.getMsgKey()+"]发送消息出现未知异常："+e.getMessage(),null,null);
			e.printStackTrace();
		}finally{
			log.info(mqbean.getMsgKey()+"]发送信息完成，开始关闭MQ连接...",null,null);
			mqClose();
			log.info(mqbean.getMsgKey()+"]发送信息完成，成功关闭MQ连接!!!",null,null);
		}
		return mqbean;
	}
	
	/**
	 * 保存交易明细信息
	 * @param mqBean
	 * @throws UnsupportedEncodingException 
	 */
	private void saveTradInfo(MQbean mqBean) throws UnsupportedEncodingException{
		if(mqBean.getIsHisMsg()){
			return;
		}
		DataObject tradInfo = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubTradInfo");
		tradInfo.set("rquid", mqBean.getRquid());
		tradInfo.set("direction", "PUT");
		tradInfo.set("spname", mqBean.getSpName());
		tradInfo.set("cleardate", mqBean.getClearDate());
		tradInfo.set("traddate", mqBean.getTradDate());
		tradInfo.set("tradtime", mqBean.getTradTime());
		tradInfo.set("msginfo", new String(mqBean.getContext(),"UTF-8"));
		tradInfo.set("createtime", GitUtil.currDateTime());
		tradInfo.set("updatetime", GitUtil.currDateTime());
		tradInfo.set("bizNumber", mqBean.getBizNumber());
		log.info("send[流水记录添加] = "+mqBean.toString());
		DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, tradInfo);
	}
	
	public static void main(String[] args) {
		String str = "Hello world!";
		// string转byte
		byte[] bs = str.getBytes();
		LogUtil.logDebug(Arrays.toString(bs),null,null);

		// byte转string
		String str2 = new String(bs);
		LogUtil.logDebug(str2,null,null);
	}
}
