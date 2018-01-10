package com.bos.inter.CallCRMInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
//单点登录响应报文
public class CRMSCapCheckRs extends SuperBosfxRs{
	@XmlElement(name="Flag")
	public String	Flag	;       //	有效标志

	
	public void setFlag(String flag) {
		Flag = flag;
	}


	public String toString() {
		return "CRMSCapCheckRs [Flag="+Flag+ "]";
	}

}
