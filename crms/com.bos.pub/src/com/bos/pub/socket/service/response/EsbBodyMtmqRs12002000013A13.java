package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs12002000013A13 
 * @Description: 12002000013客户信息开户维护     13对公客户财报信息维护				
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs12002000013A13 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
//	private String ReturnCode;//	服务返回代码	String(14)		正常：00000000000000；异常：
//	private String ReturnMsg;//	服务返回信息	String(255)		正常：交易成功，异常：提示信息
	
	
	
	public EsbBodyMtmqRs12002000013A13() {
		
	}
	
//	public String getReturnCode() {
//		return ReturnCode;
//	}
//	@XmlElement(name = "ReturnCode")
//	public void setReturnCode(String returnCode) {
//		ReturnCode = returnCode;
//	}
//	public String getReturnMsg() {
//		return ReturnMsg;
//	}
//	@XmlElement(name = "ReturnMsg")
//	public void setReturnMsg(String returnMsg) {
//		ReturnMsg = returnMsg;
//	}

}
