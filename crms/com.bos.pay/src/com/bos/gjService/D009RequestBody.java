package com.bos.gjService;

import java.io.Serializable;

public class D009RequestBody implements Serializable{

	private static final long serialVersionUID = -1710673224409171703L;
	private String ecifPartyNum;//客户编号
	private String contractNum;//合同编号
	
	public D009RequestBody() {
	}
	public String getEcifPartyNum() {
		return ecifPartyNum;
	}
	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
}
