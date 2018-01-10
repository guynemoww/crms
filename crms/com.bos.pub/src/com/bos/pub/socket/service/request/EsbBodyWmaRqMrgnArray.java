package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqMrgnArray 
 * @Description: 03002000011票据信息维护		01银行承兑汇票打印发起	 保证金数组
 *
 */
public class EsbBodyWmaRqMrgnArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mrgnAcctNo;				//保证金账号	String(35)
	private double mrgnPct;					//保证金比例	Double(6,2)
	
	public EsbBodyWmaRqMrgnArray(){
		
	}

	public String getMrgnAcctNo() {
		return mrgnAcctNo;
	}


	@XmlElement(name = "MrgnAcctNo")
	public void setMrgnAcctNo(String mrgnAcctNo) {
		this.mrgnAcctNo = mrgnAcctNo;
	}



	public double getMrgnPct() {
		return mrgnPct;
	}


	@XmlElement(name = "MrgnPct")
	public void setMrgnPct(double mrgnPct) {
		this.mrgnPct = mrgnPct;
	}
	
}
