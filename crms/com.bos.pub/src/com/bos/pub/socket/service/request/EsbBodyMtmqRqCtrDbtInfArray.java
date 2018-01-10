package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRqCtrDbtInfArray 
 * @Description: 02002000003移动信贷公共管理		03催收登记信息提交
 *
 */
public class EsbBodyMtmqRqCtrDbtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrDbtNo;//合同借据号
	
	public EsbBodyMtmqRqCtrDbtInfArray(){
		
	}

	public String getCtrDbtNo() {
		return ctrDbtNo;
	}

	@XmlElement(name = "CtrDbtNo")
	public void setCtrDbtNo(String ctrDbtNo) {
		this.ctrDbtNo = ctrDbtNo;
	}
	
}
