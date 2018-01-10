package com.bos.gjService;

import java.io.Serializable;
/**
 * 放款请求体
 * @author chenpan
 *
 */
public class D003RequestBody implements Serializable{
	private static final long serialVersionUID = -5520027073791410704L;
	
	private String contractNum;//合同编号
	private String loanAmt;//放款金额
	private String loanTerm;//放款期限
	
	public D003RequestBody() {
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	
}
