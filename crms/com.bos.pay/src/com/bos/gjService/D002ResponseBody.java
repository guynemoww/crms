package com.bos.gjService;

import java.io.Serializable;

public class D002ResponseBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2613412712026303322L;
	
	private String ecifPartyNum;//客户编号
	private String bizNum;//批复编号
	private String contractNum;//合同编号
	
	public D002ResponseBody() {
	}

	public String getEcifPartyNum() {
		return ecifPartyNum;
	}

	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}

	public String getBizNum() {
		return bizNum;
	}

	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

}
