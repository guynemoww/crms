package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000003A02
 * @Description: 02002000003 移动信贷公共管理  02影像补录
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02002000003A02 extends EsbBody implements
		Serializable {

	private static final long serialVersionUID = 1L;
//	private String ReturnCode;// 服务返回代码 String(14)
//	private String ReturnMsg;// 服务返回信息 String(255)
	
	
	
	public EsbBodyMtmqRs02002000003A02() {
		
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
