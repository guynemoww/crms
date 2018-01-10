package com.bos.gjService;

import java.io.Serializable;

public class D011RequestBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4843094949310280978L;

	private String summaryNum;//借据编号
	private String payPrimAcct;//新第一还款账号
	private String payPrimName;//新第一还款账号名称
	
	public D011RequestBody(){
	}

	public String getSummaryNum() {
		return summaryNum;
	}

	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}

	public String getPayPrimAcct() {
		return payPrimAcct;
	}

	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}

	public String getPayPrimName() {
		return payPrimName;
	}

	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	
}
