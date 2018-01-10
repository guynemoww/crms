package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyGJ05002000001A01 
 * @Description: 05002000001国际结算业务维护	01信用证开立通知
 *
 */
public class EsbBodyGjRqMrgnArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mrgnAcctNo;	//保证金账号
	private String mrgnCcy;		//保证金币种
	private double mrgnAmt;		//保证金金额
	
	public EsbBodyGjRqMrgnArray(){
		
	}

	public String getMrgnAcctNo() {
		return mrgnAcctNo;
	}

	@XmlElement(name = "MrgnAcctNo")
	public void setMrgnAcctNo(String mrgnAcctNo) {
		this.mrgnAcctNo = mrgnAcctNo;
	}

	public String getMrgnCcy() {
		return mrgnCcy;
	}

	@XmlElement(name = "MrgnCcy")
	public void setMrgnCcy(String mrgnCcy) {
		this.mrgnCcy = mrgnCcy;
	}

	public double getMrgnAmt() {
		return mrgnAmt;
	}

	@XmlElement(name = "MrgnAmt")
	public void setMrgnAmt(double mrgnAmt) {
		this.mrgnAmt = mrgnAmt;
	}
}
