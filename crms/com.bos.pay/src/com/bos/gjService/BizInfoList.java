package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

public class BizInfoList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2309177383189886696L;
	private String ecifPartyNum;//客户编号
	private String applyId;//申请编号
	private String userName;//管户人
	private String orgName;//管户机构
	private String applyDate;//申请日期
	private BigDecimal applyRate;//申请利率
	private String applyXwTerm;//申请期限
	private BigDecimal applyAmt;//申请金额
	private String repaymentType;//还款方式
	private String zhzh;//放款账号
	private String firstPayBackZhzh;//第一还款账号
	private String secondPayBackZhzh;//第二还款账号
	private String thirdPayBackZhzh;//第三还款账号
	private String phoneNum;//手机号码
	private String loanUse;//贷款用途
	private String payment;//还款来源
	private String applyStatus;//申请状态
	private String errorMsg;//征信描述
	
	
	public BizInfoList() {
	}


	public String getEcifPartyNum() {
		return ecifPartyNum;
	}


	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}


	public String getApplyId() {
		return applyId;
	}


	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}


	public BigDecimal getApplyRate() {
		return applyRate;
	}


	public void setApplyRate(BigDecimal applyRate) {
		this.applyRate = applyRate;
	}


	public String getApplyXwTerm() {
		return applyXwTerm;
	}


	public void setApplyXwTerm(String applyXwTerm) {
		this.applyXwTerm = applyXwTerm;
	}


	public BigDecimal getApplyAmt() {
		return applyAmt;
	}


	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}


	public String getRepaymentType() {
		return repaymentType;
	}


	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}


	public String getZhzh() {
		return zhzh;
	}


	public void setZhzh(String zhzh) {
		this.zhzh = zhzh;
	}


	public String getFirstPayBackZhzh() {
		return firstPayBackZhzh;
	}


	public void setFirstPayBackZhzh(String firstPayBackZhzh) {
		this.firstPayBackZhzh = firstPayBackZhzh;
	}


	public String getSecondPayBackZhzh() {
		return secondPayBackZhzh;
	}


	public void setSecondPayBackZhzh(String secondPayBackZhzh) {
		this.secondPayBackZhzh = secondPayBackZhzh;
	}


	public String getThirdPayBackZhzh() {
		return thirdPayBackZhzh;
	}


	public void setThirdPayBackZhzh(String thirdPayBackZhzh) {
		this.thirdPayBackZhzh = thirdPayBackZhzh;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getLoanUse() {
		return loanUse;
	}


	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getApplyStatus() {
		return applyStatus;
	}


	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
