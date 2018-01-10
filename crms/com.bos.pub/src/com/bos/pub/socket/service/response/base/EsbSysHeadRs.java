package com.bos.pub.socket.service.response.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: EsbSysHead 
* @Description: 系统头说明
*
 */
public class EsbSysHeadRs implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mac;				//MAC字段			String	16	必输
	private String msgId;			//服务消息ID		String	36	必输	使用UUID生成
	private String targetSysId;		//服务目标系统ID		String	10	必输
	private String relatedMsgId;	//关联消息ID		String	36	可选	异步调用方式保留字段
	private String serviceCode;		//服务地址			String	11	可选	异步调用方式保留字段
	private String serviceScene;	//服务方法			String	2	可选	异步调用方式保留字段
	private String extendContent;	//扩展内容			String	128	可选	保留字段
	
	public EsbSysHeadRs()	{
		
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

	public String getTargetSysId() {
		return targetSysId;
	}

	@XmlElement(name = "TargetSysId")
	public void setTargetSysId(String targetSysId) {
		this.targetSysId = targetSysId;
	}

	public String getRelatedMsgId() {
		return relatedMsgId;
	}

	@XmlElement(name = "RelatedMsgId")
	public void setRelatedMsgId(String relatedMsgId) {
		this.relatedMsgId = relatedMsgId;
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

	public String getExtendContent() {
		return extendContent;
	}

	@XmlElement(name = "ExtendContent")
	public void setExtendContent(String extendContent) {
		this.extendContent = extendContent;
	}
}
