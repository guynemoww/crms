package com.bos.mq.client;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MQbean {
	public MQbean() {
	}

	private String hostname;

	private int port;

	private String channel;

	private int ccsid;

	private String qManager;

	private String Queuename;

	private byte[] context;

	private String relationid;
	
	private String spName;
	
	private String rquid;
	
	private String clearDate;
	
	private String tradDate;
	
	private String tradTime;
	
	private boolean isHisMsg;
	
	private String bizNumber;//业务流水号
	
	private String msgKey;

	/**
	 * @return rquid
	 */
	public String getRquid() {
		return rquid;
	}

	/**
	 * @param rquid 要设置的 rquid
	 */
	public void setRquid(String rquid) {
		this.rquid = rquid;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getCcsid() {
		return ccsid;
	}

	public void setCcsid(int ccsid) {
		this.ccsid = ccsid;
	}

	public String getQManager() {
		return qManager;
	}

	public void setQManager(String qManager) {
		this.qManager = qManager;
	}

	public String getQueuename() {
		return Queuename;
	}

	public void setQueuename(String Queuename) {
		this.Queuename = Queuename;
	}

	public byte[] getContext() {
		return context;
	}

	public void setContext(byte[] context) {
		this.context = context;
	}

	public String getRelationid() {
		return relationid;
	}

	public void setRelationid(String relationid) {
		this.relationid = relationid;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	/**
	 * @return clearDate
	 */
	public String getClearDate() {
		return clearDate;
	}

	/**
	 * @param clearDate 要设置的 clearDate
	 */
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	/**
	 * @return tradDate
	 */
	public String getTradDate() {
		return tradDate;
	}

	/**
	 * @param tradDate 要设置的 tradDate
	 */
	public void setTradDate(String tradDate) {
		this.tradDate = tradDate;
	}

	/**
	 * @return tradTime
	 */
	public String getTradTime() {
		return tradTime;
	}

	/**
	 * @param tradTime 要设置的 tradTime
	 */
	public void setTradTime(String tradTime) {
		this.tradTime = tradTime;
	}

	/**
	 * @return isHisMsg
	 */
	public boolean getIsHisMsg() {
		return isHisMsg;
	}

	/**
	 * @param isHisMsg 要设置的 isHisMsg
	 */
	public void setIsHisMsg(boolean isHisMsg) {
		this.isHisMsg = isHisMsg;
	}


	/**
	 * @return msgKey
	 */
	public String getMsgKey() {
		return msgKey;
	}

	/**
	 * @param msgKey 要设置的 msgKey
	 */
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	
	public String toString() {
		try {
			return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
					.append("hostname",getHostname())
					.append("port",getPort())
					.append("channel",getChannel())
					.append("ccsid",getCcsid())
					.append("qManager",getQManager())
					.append("context",new String(getContext(),"UTF-8"))
					.append("relationid",getRelationid())
					.append("spName",getSpName())
					.append("isHisMsg",getIsHisMsg())
					.append("bizNumber",getBizNumber())
					.append("msgKey",getMsgKey())
					.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return null;
		}
	}

	public String getBizNumber() {
		return bizNumber;
	}

	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}
}
