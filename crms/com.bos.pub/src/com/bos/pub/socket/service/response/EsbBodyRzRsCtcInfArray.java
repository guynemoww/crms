package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyGJ05002000001A01 
 * @Description: 05002000001国际结算业务维护	01信用证开立通知
 *
 */
public class EsbBodyRzRsCtcInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctcMth;		//联系方式	0-家庭电话、1-家庭地址邮编、2-手机号码、3-集团短号、4-qq号、5-微信号、6-email、	
	private String ctcNo;		//联系号码
	
	public EsbBodyRzRsCtcInfArray(){
		
	}

	public String getCtcMth() {
		return ctcMth;
	}

	@XmlElement(name = "CtcMth")
	public void setCtcMth(String ctcMth) {
		this.ctcMth = ctcMth;
	}

	public String getCtcNo() {
		return ctcNo;
	}

	@XmlElement(name = "CtcNo")
	public void setCtcNo(String ctcNo) {
		this.ctcNo = ctcNo;
	}
}
