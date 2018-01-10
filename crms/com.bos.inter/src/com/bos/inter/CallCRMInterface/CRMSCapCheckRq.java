package com.bos.inter.CallCRMInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
//单点登录请求报文
public class CRMSCapCheckRq extends SuperBosfxRq{
	@XmlElement(name="UserId")
	public String	UserId	;       //	用户编号
	@XmlElement(name="TokenPwd")
	public String	TokenPwd	;       //	令牌
	
	public void setTokenPwd(String tokenPwd) {
		TokenPwd = tokenPwd;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}


	public String toString() {
		return "CRMSCapCheckRq [UserId="+UserId+ ", TokenPwd="
		+ TokenPwd + "]";
	}

	
}
