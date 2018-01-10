package com.bos.pub.socket.service.mortgage.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02002000004A02
 * @Description: 02002000004信贷信息维护   02押品信息建立
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02002000004A02 extends EsbBody implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String ReturnCode;// 服务返回代码 String(14)
	private String ReturnMsg;// 服务返回信息 String(255)
	
	
	
	public EsbBodyMtmqRs02002000004A02() {
		
	}
	
	public String getReturnCode() {
		return ReturnCode;
	}
	@XmlElement(name = "ReturnCode")
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}
	public String getReturnMsg() {
		return ReturnMsg;
	}
	@XmlElement(name = "ReturnMsg")
	public void setReturnMsg(String returnMsg) {
		ReturnMsg = returnMsg;
	}
	
	
}
