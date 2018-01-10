package com.bos.inter.CallCRMInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
//单点登录身份验证请求报文
public class CRMSCreEncKeyRq extends SuperBosfxRq {
	@XmlElement(name="ChannelId")
	public String	ChannelId	;       //	渠道ID
	@XmlElement(name="UserId")
	public String	UserId	;       //	用户编号
	public void setChannelId(String channelId) {
		ChannelId = channelId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String toString() {
		return "CRMSCreEncKeyRq [ChannelId="+ChannelId+ ", UserId="
		+ UserId + "]";
	}
}
