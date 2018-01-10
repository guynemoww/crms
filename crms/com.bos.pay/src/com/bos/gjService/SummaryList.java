package com.bos.gjService;

import java.io.Serializable;

public class SummaryList implements Serializable{

	private static final long serialVersionUID = 6745707352414398591L;
	private String ecifPartyNum;//客户编号
	private String contractNum;//合同编号
	private String custName;//客户名称
	private String bizNum;//借据编号
	private String loanAmt;//借据金额
	private String loanBalance;//借据余额
	private String loanStartDate;//借据开始日期
	private String loanEndDate;//借据结束日期
	private String loanStatus;//借据状态
	private String firstPayBackZhmc;//第一还款账户账号
	private String secondPayBackZhmc;//第二还款账户账号
	private String thirdPayBackZhzh;//第三还款账户账号
	public SummaryList() {
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBizNum() {
		return bizNum;
	}
	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
	}
	public String getLoanStartDate() {
		return loanStartDate;
	}
	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}
	public String getLoanEndDate() {
		return loanEndDate;
	}
	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public String getFirstPayBackZhmc() {
		return firstPayBackZhmc;
	}
	public void setFirstPayBackZhmc(String firstPayBackZhmc) {
		this.firstPayBackZhmc = firstPayBackZhmc;
	}
	public String getSecondPayBackZhmc() {
		return secondPayBackZhmc;
	}
	public void setSecondPayBackZhmc(String secondPayBackZhmc) {
		this.secondPayBackZhmc = secondPayBackZhmc;
	}
	public String getThirdPayBackZhzh() {
		return thirdPayBackZhzh;
	}
	public void setThirdPayBackZhzh(String thirdPayBackZhzh) {
		this.thirdPayBackZhzh = thirdPayBackZhzh;
	}
	
}
