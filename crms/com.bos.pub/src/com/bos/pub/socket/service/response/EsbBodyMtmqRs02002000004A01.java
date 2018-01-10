package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02002000004A01
 * @Description: 02002000004信贷信息维护   01项目信息建立
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02002000004A01 extends EsbBody implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String returnCode;// 服务返回代码 String(14)
	private String returnMsg;// 服务返回信息 String(255)
	
	
	
	public EsbBodyMtmqRs02002000004A01() {
		
	}
	
	public String getReturnCode() {
		return returnCode;
	}
	@XmlElement(name = "ReturnCode")
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	@XmlElement(name = "ReturnMsg")
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	
}
