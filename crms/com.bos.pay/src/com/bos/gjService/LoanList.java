package com.bos.gjService;

import java.io.Serializable;

public class LoanList implements Serializable{

	private static final long serialVersionUID = -6432877700912940006L;
	private String loanOrg;//放款机构
	private String dueNum;//借据编号
	private String custName;//客户名称
	private String loanAmt;//放款金额
	private String loanBeginDate;//贷款发放日期
	private String loanEndDate;//贷款到期日期
	private String normalRate;//正常利率（%）
	private String overRate;//罚息利率（%）
	private String paybackWay;//还款方式
	public LoanList() {
	}
	public String getLoanOrg() {
		return loanOrg;
	}
	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanBeginDate() {
		return loanBeginDate;
	}
	public void setLoanBeginDate(String loanBeginDate) {
		this.loanBeginDate = loanBeginDate;
	}
	public String getLoanEndDate() {
		return loanEndDate;
	}
	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public String getNormalRate() {
		return normalRate;
	}
	public void setNormalRate(String normalRate) {
		this.normalRate = normalRate;
	}
	public String getOverRate() {
		return overRate;
	}
	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}
	public String getPaybackWay() {
		return paybackWay;
	}
	public void setPaybackWay(String paybackWay) {
		this.paybackWay = paybackWay;
	}
}
