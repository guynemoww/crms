package com.bos.pub.socket.service.request.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbSysHead
 * @Description: 系统头说明
 * 
 */
public class EsbSysHeadRq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mac; // MAC字段 String 16 必输
	private String msgId; // 服务消息ID String 36 必输 使用UUID生成
	private String sourceSysId; // 服务源发起系统ID String 10 必输
	private String consumerId; // 服务调用方系统ID String 10 必输
	private String serviceCode; // 服务码 String 11 必输
	private String serviceScene; // 服务场景 String 2 必输
	private String replyAdr; // 服务响应地址 String 128 可选
	private String extendContent; // 扩展内容 String 128 可选 保留字段

	public EsbSysHeadRq() {

	}

	public String getMac() {
		return mac;
	}

	@XmlElement(name = "Mac")
	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSourceSysId() {
		return sourceSysId;
	}

	@XmlElement(name = "SourceSysId")
	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}

	public String getConsumerId() {
		return consumerId;
	}

	@XmlElement(name = "ConsumerId")
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	@XmlElement(name = "ServiceCode")
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceScene() {
		return serviceScene;
	}

	@XmlElement(name = "ServiceScene")
	public void setServiceScene(String serviceScene) {
		this.serviceScene = serviceScene;
	}

	public String getReplyAdr() {
		return replyAdr;
	}

	@XmlElement(name = "ReplyAdr")
	public void setReplyAdr(String replyAdr) {
		this.replyAdr = replyAdr;
	}

	public String getExtendContent() {
		return extendContent;
	}

	@XmlElement(name = "ExtendContent")
	public void setExtendContent(String extendContent) {
		this.extendContent = extendContent;
	}

	@Override
	public String toString() {
		return "EsbSysHeadRq [mac=" + mac + ",msgId=" + msgId + ",sourceSysId="
				+ sourceSysId + ",consumerId=" + consumerId + ",serviceCode="
				+ serviceCode + ",serviceScene=" + serviceScene + ",replyAdr="
				+ replyAdr + ",extendContent=" + extendContent + "]";
	}
}
