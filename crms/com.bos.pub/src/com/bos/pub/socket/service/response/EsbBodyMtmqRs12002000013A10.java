package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq12002000013A10 
 * @Description: 12002000013客户信息开户维护     10对公客户信息维护				
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs12002000013A10 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
	private String crCstNo;//	客户代号	String(10)		
	private String returnCode;//	服务返回代码	String(14)		正常：00000000000000；异常：
	private String returnMsg;//	服务返回信息	String(255)		正常：交易成功，异常：提示信息
	
	
	
	public EsbBodyMtmqRs12002000013A10() {
		
	}
	
	public String getCrCstNo() {
		return crCstNo;
	}
	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
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
