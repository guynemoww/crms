package com.bos.mq.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.bos.jaxb.JAXBUtil;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.jaxb.javabean.CommonRqHdr;
import com.bos.jaxb.javabean.CommonRsHdr;
import com.bos.mq.conf.ConfigManager;
import com.bos.mq.util.KeyGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import commonj.sdo.DataObject;

public class Receiver extends BaseMQ {
	static TraceLogger log = new TraceLogger(Receiver.class);
	
	/**
	 * 根据消息ID获取队列中的指定消息
	 * @param mqbean
	 * @return
	 * @throws MQException
	 */
	public MQbean receive(MQbean mqbean) throws MQException {
		log.info("[作为客户端开始从队列中接收报文] , receive  方法");
		MQbean mq = mqbean;
		BOSFXII rebf = new BOSFXII();
		String qName = ConfigManager.getStringProperty("receiveqname");//设置接收队列名称
		mq.setQueuename(qName);
		//(建立打开选项以便打开用于输出的队列，进一步而言，如果队列管理器是停顿的话，我们也已设置了选项去应对不成功情况。)
		int openOptions = MQC.MQOO_INPUT_SHARED | MQC.MQOO_FAIL_IF_QUIESCING;
		try {
			// Set the put message options.(设置放置消息选项)
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT; // Get messages
			//在同步点控制下获取消息;
			gmo.options = gmo.options + MQC.MQGMO_WAIT; // Wait if no messages
			//如果在队列上没有消息则等待);
			gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING; // Fail
			// 如果队列管理器停顿则失败;
			gmo.waitInterval = ConfigManager.getIntProperty("waitInterval"); // Sets the time limit for the
			// wait.(设置等待的时间限制)
			//建立消息，MQMessage类压缩了包含实际消息数据的数据缓冲区， 和描述消息的所有MQMD参数
			MQMessage inMsg = new MQMessage(); // Create the message
			// buffer(创建消息缓冲区)
			inMsg.format = MQC.MQFMT_STRING;
			inMsg.correlationId = mq.getRelationid().getBytes();
			log.info("判断连接情况："+(queue == null || !queue.isOpen()));
			if(queue == null || !queue.isOpen()){
				// Connection To the Queue Manager(连接到队列管理器)
				if(qMgr == null || !qMgr.isConnected){
					log.info("判断qMgr："+(qMgr == null || !qMgr.isConnected));
					log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]开始创建MQ连接...",null,null);
					mqConn(mq);//创建连接
					log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]成功创建MQ连接!!!",null,null);
				}
				// Open the queue(打开队列)
				log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]开始打开MQ队列["+qName+"]...",null,null);
				queue = qMgr.accessQueue(qName, openOptions, null, null, null);
				log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]成功打开MQ队列["+qName+"]!!!",null,null);
			}
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]开始接收消息，msgID为["+new String(inMsg.messageId)+"],correlationId为["+new String(inMsg.correlationId)+"]",null,null);
			queue.get(inMsg, gmo);// 从队列到消息缓冲区获取消息
//			String msgString = inMsg.readStringOfByteLength(inMsg.getMessageLength());//从消息读取用户数据
			byte[] xmlData = new byte[inMsg.getDataLength()];   
			inMsg.readFully(xmlData);   
			String msgString = new String(xmlData,"UTF-8");
			//LogUtil.logInfo("[实时交易]接收消息的长度--" + msgString.length(),null,null);
			log.info("[客户端]-[实时交易]-[msgKey:"+mq.getMsgKey()+"]接收消息的长度--" + msgString.length());
			msgString = formatRemsg(msgString);
			//LogUtil.logInfo("[实时交易]接收消息--" + msgString,null,null);
			log.info("[客户端]-[实时交易]-[msgKey:"+mq.getMsgKey()+"]接收消息--" + msgString);
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]消息接收成功，msgID为["+new String(inMsg.messageId)+"],correlationId为["+new String(inMsg.correlationId)+"]",null,null);
			mq.setContext(msgString.getBytes("UTF-8"));
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]开始保存交易信息...",null,null);
			try {
				saveTradInfo(mq);
			}
			catch(Exception ee)
			{
				log.error("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]插入报文流水数据失败："+ee.getMessage(),null,null);
				ee.printStackTrace();
			}
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]成功保存交易对象!!!",null,null);
		} catch (MQException e) {
			// TODO: handle exception
			if(e.getMessage().indexOf("2033") < 0){
				log.error("[客户端]-[msgKey:"+mq.getMsgKey()+"]接收信息出现MQ异常："+e.getMessage(),null,null);
				e.printStackTrace();
			}
		} catch (Exception ex) {
			log.error("[客户端]-[msgKey:"+mq.getMsgKey()+"]接收信息出现未知异常："+ex.getMessage(),null,null);
			ex.printStackTrace();
		}finally{
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]发送信息完成，开始关闭MQ连接...",null,null);
			mqClose();
			log.info("[客户端]-[msgKey:"+mq.getMsgKey()+"]发送信息完成，成功关闭MQ连接!!!",null,null);
		}
		return mq;
	}
	
	/**
	 * 接收队列中的所有消息
	 * @param mqbean
	 * @return
	 * @throws MQException
	 */
	public List<MQbean> receiveAll(MQbean mqbean) throws MQException {
		log.info("[作为服务端开始从队列中接收报文], receiveAll 方法");
		List<MQbean> mqbeanList = new ArrayList<MQbean>();
		BOSFXII rebf = new BOSFXII();
		String qName = ConfigManager.getStringProperty("receiveqname");//设置接收队列名称
		//建立打开选项以便打开用于输出的队列，进一步而言，如果队列管理器是停顿的话，我们也已设置了选项去应对不成功情况。
		//int openOptions = MQC.MQOO_INPUT_SHARED | MQC.MQOO_FAIL_IF_QUIESCING;
		int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
		//Connection To the Queue Manager(连接到队列管理器)
		mqbean.setMsgKey(KeyGenerator.getUUID());
		if(qMgr == null || !qMgr.isConnected){
			log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]开始创建MQ连接...",null,null);
			mqConn(mqbean);
			log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]成功创建MQ连接!!!",null,null);
		}
		// Open the queue(打开队列)
		log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]开始打开MQ队列["+qName+"]...",null,null);
		queue = qMgr.accessQueue(qName, openOptions);
		log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]成功打开MQ队列["+qName+"]!!!",null,null);
		int depth = queue.getCurrentDepth();
		log.info("QUEUE Depth :"+depth);
		try {
			while(depth-->0){
//			if(depth > 0){
				MQbean mq =new MQbean();
				BeanUtils.copyProperties(mq, mqbean);
				mq.setMsgKey(KeyGenerator.getUUID());
				// Set the put message options.(设置放置消息选项)
				MQGetMessageOptions gmo = new MQGetMessageOptions();
				gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT; // Get messages
				//在同步点控制下获取消息;
//				gmo.options = gmo.options + MQC.MQGMO_WAIT; // Wait if no messages
				//如果在队列上没有消息则等待);
//				gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING; // Fail
				// 如果队列管理器停顿则失败;
				//gmo.waitInterval = ConfigManager.getIntProperty("waitInterval"); // Sets the time limit for the
				// wait.(设置等待的时间限制)
				//建立消息，MQMessage类压缩了包含实际消息数据的数据缓冲区， 和描述消息的所有MQMD参数
				try{
					MQMessage inMsg = new MQMessage(); // Create the message
					log.info("inMsg:" +inMsg.toString());
					// buffer(创建消息缓冲区)
					inMsg.format = MQC.MQFMT_STRING;
	//				inMsg.correlationId = mqbean.getRelationid().getBytes();
					inMsg.messageId = ConfigManager.getStringProperty("servermessageID").getBytes();//定义系统消息ID
					log.info("QUEUE :"+queue.toString());
					if(queue == null || !queue.isOpen()){
						//Connection To the Queue Manager(连接到队列管理器)
						if(qMgr == null || !qMgr.isConnected){
							log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]开始创建MQ连接...",null,null);
							mqConn(mq);//创建连接
							log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]成功创建MQ连接!!!",null,null);
						}
						// Open the queue(打开队列)
						log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]开始打开MQ队列["+qName+"]...",null,null);
						queue = qMgr.accessQueue(qName, openOptions);
						log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]成功打开MQ队列["+qName+"]!!!",null,null);
					}
					queue.get(inMsg, gmo);// 从队列到消息缓冲区获取消息
	//				String msgString = inMsg.readStringOfByteLength(inMsg.getMessageLength());//从消息读取用户数据
	//				System.out.println("inMsg---->"+inMsg);
					byte[] xmlData = new byte[inMsg.getDataLength()];   
					inMsg.readFully(xmlData);   
					String msgString = new String(xmlData,"UTF-8");
					log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]接收消息的长度--" + msgString.length());
					log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]接收消息--" + msgString);
	//				msgString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><BOSFXII xmlns=\"http://www.bankofshanghai.com/BOSFX/2010/08\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.bankofshanghai.com/BOSFX/2010/08 BOSFX2.0.xsd\"><CRMSCustInfoInqRq><CommonRqHdr><SPName>T24</SPName><RqUID>4254996460799390188</RqUID><ChannelId>S39</ChannelId><Version>1.0</Version></CommonRqHdr><CustNo>8116213</CustNo></CRMSCustInfoInqRq></BOSFXII>";
					mq.setContext(formatRemsg(msgString).getBytes("UTF-8"));
	//				byte[] msgId = inMsg.messageId;
	//				for (int i = 0; i < msgId.length; i++) {
	//					byte b = msgId[i];
	//					
	//				}
					mq.setRelationid(new String(inMsg.correlationId));
	//				mq.setSpName(new String(inMsg.messageId).trim());
	//				System.out.println("格式化后消息--" + formatRemsg(msgString));
				} catch (MQException e) {
					// TODO: handle exception
					log.error("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]接收信息出现MQ异常："+e.getMessage(),null,null);
					e.printStackTrace();
				}
				log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]开始保存交易信息...",null,null);
				try {
					rebf = (BOSFXII)JAXBUtil.unmarshal(mq.getContext(), BOSFXII.class);
					CommonRqHdr commHdr=getRqHdr(rebf);
					//读取请求报文头内容
					mq.setClearDate(commHdr.clearDate);
					mq.setSpName(commHdr.sPName);
					mq.setTradDate(commHdr.tranDate);
					mq.setTradTime(commHdr.tranTime);
					mq.setRquid(commHdr.rqUID);
					saveTradInfo(mq);
				}catch(Exception ee){
					log.error("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]插入报文流水数据失败："+ee.getMessage(),null,null);
					ee.printStackTrace();
				}
				log.info("[服务端]-[msgKey:"+mq.getMsgKey()+"]成功保存交易对象!!!",null,null);
				mqbeanList.add(mq);
			}

		} catch (Exception ex) {
			log.error("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]接收信息出现未知异常："+ex.getMessage(),null,null);
			ex.printStackTrace();
		}finally{
			log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]发送信息完成，开始关闭MQ连接...",null,null);
			mqClose();//关闭连接
			log.info("[服务端]-[msgKey:"+mqbean.getMsgKey()+"]发送信息完成，成功关闭MQ连接!!!",null,null);
		}
		return mqbeanList;
	}
	
	private CommonRqHdr getRqHdr(BOSFXII bosfxii) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException{
    	Field[] fields = bosfxii.getClass().getDeclaredFields();
    	for(Field field:fields){   
    		if(!"serialVersionUID".equals(field.getName())){
	    		field.setAccessible(true); // 设置些属性是可以访问的
	    		Object o = field.get(bosfxii);
	    		if(o != null){
	    			return (CommonRqHdr)o.getClass().getField("CommonRqHdr").get(o);
	    		}
    		}
    	}
    	return null;
    }
	
	private CommonRsHdr getRsHdr(BOSFXII bosfxii) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException{
    	Field[] fields = bosfxii.getClass().getDeclaredFields();
    	for(Field field:fields){   
    		if(!"serialVersionUID".equals(field.getName())){
	    		field.setAccessible(true); // 设置些属性是可以访问的
	    		Object o = field.get(bosfxii);
	    		if(o != null){
	    			return (CommonRsHdr)o.getClass().getField("CommonRsHdr").get(o);
	    		}
    		}
    	}
    	return null;
    }

	
	/**
	 * 保存交易明细信息
	 * @param mqBean
	 */
	private void saveTradInfo(MQbean mqBean) throws Exception{
		DataObject tradInfo = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubTradInfo");
		tradInfo.set("rquid", mqBean.getRquid().trim());
		tradInfo.set("direction", "GET");
		tradInfo.set("spname", mqBean.getSpName()==null?"":mqBean.getSpName().trim());
		tradInfo.set("cleardate", mqBean.getClearDate()==null?"":mqBean.getClearDate());
		tradInfo.set("traddate", mqBean.getTradDate()==null?"":mqBean.getTradDate());
		tradInfo.set("tradtime", mqBean.getTradTime()==null?"":mqBean.getTradTime());
		tradInfo.set("msginfo", new String(mqBean.getContext()));
		tradInfo.set("createtime", GitUtil.currDateTime());
		tradInfo.set("updatetime", GitUtil.currDateTime());
		log.info("receive[流水记录添加] = "+mqBean.toString());
		DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, tradInfo);
	}
	
	/**
	 * 格式化返回信息，去掉xmlns属性信息
	 * @param remsg
	 * @return
	 */
	private String formatRemsg(String remsg){
		   int countb = remsg.indexOf("xmlns=");
		   int counte = remsg.indexOf("\">");
		   if(countb > 0 && counte > 0){
			   remsg = remsg.substring(0,countb)+remsg.substring(counte+1);
		   }
		   return remsg;
	}
}
