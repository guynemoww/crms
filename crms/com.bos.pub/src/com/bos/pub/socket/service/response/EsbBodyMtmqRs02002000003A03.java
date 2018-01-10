package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02002000003A03 
 * @Description: 02002000003移动信贷公共管理     03催收登记信息提交			
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02002000003A03 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ReturnCode;//	服务返回代码	String(14)		正常：00000000000000；异常：
	private String ReturnMsg;//	服务返回信息	String(255)		正常：交易成功，异常：提示信息
	
	
	
	public EsbBodyMtmqRs02002000003A03() {
		
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
