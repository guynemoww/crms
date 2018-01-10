/**
 * 
 */
package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000003A01 
 * @Description: 02002000003移动信贷公共管理
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02002000003A01 extends EsbBody implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String returnCode;
	private String returnMsg;
	
	public EsbBodyMtmqRs02002000003A01() {
		
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
