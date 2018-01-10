package com.bos.gjService;

import java.io.Serializable;

public class ConList implements Serializable{

	private static final long serialVersionUID = -4127802343256409876L;
	private String ecifPartyNum;//客户编号
	private String contractNum;//合同编号
	private String custName;//客户名称
	private String term;//期限
	private String conStartDate;//合同起始日期
	private String conEndDate;//合同到期日期
	private String repaymentType;//还款方式
	private String conStatus;//合同状态
	private String rate;//利率
	private String totalLimit;//总额度
	private String conUsedLimit;//已用额度
	private String aviLimit;//可用额度
	private String payAccNo;//放款账号
//	public D005ResponseBodyItem() {
//	}
	public ConList() {
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
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getConStartDate() {
		return conStartDate;
	}
	public void setConStartDate(String conStartDate) {
		this.conStartDate = conStartDate;
	}
	public String getConEndDate() {
		return conEndDate;
	}
	public void setConEndDate(String conEndDate) {
		this.conEndDate = conEndDate;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getConStatus() {
		return conStatus;
	}
	public void setConStatus(String conStatus) {
		this.conStatus = conStatus;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTotalLimit() {
		return totalLimit;
	}
	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}
	public String getConUsedLimit() {
		return conUsedLimit;
	}
	public void setConUsedLimit(String conUsedLimit) {
		this.conUsedLimit = conUsedLimit;
	}
	public String getAviLimit() {
		return aviLimit;
	}
	public void setAviLimit(String aviLimit) {
		this.aviLimit = aviLimit;
	}
	public String getPayAccNo() {
		return payAccNo;
	}
	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}
}
