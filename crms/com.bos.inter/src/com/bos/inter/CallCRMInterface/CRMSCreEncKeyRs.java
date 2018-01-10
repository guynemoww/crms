package com.bos.inter.CallCRMInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
//单点登录身份验证响应报文
public class CRMSCreEncKeyRs extends SuperBosfxRs{
	@XmlElement(name="KeyName")
	public String	KeyName	;       //	url加密KEY
	
	public void setKeyName(String keyName) {
		KeyName = keyName;
	}
	public String toString() {
		return "CRMSCreEncKeyRs [KeyName="+KeyName+ "]";
	}
}
