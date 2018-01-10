package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepayLoanList implements Serializable{

	private static final long serialVersionUID = 6120553054600361888L;
	private String repaymentDate;//放款日期
	private String orgNum;//机构
	private String dueNum;//借据编号
	private String custName;//客户名称
	private String repaymentAccount;//还款账号
	private String repaymentAccountName;//还款账号名称
	private String repaymentType;//还款类型
	private String loanBeginDate;//贷款起期
	private String loanEndDate;//贷款止期
	private BigDecimal realCapitalAmt;;//实收本金金额
	private BigDecimal advanceCapitalAmt;//提前还本金额
	private BigDecimal realBnNorInterest;//实收表内正常利息金额
	private BigDecimal realBnDelInterest;//实收表内拖欠利息金额
	private BigDecimal realBnOverInterest;//实收表内罚息
	private BigDecimal realBnComInterest;//实收表内复利
	private BigDecimal realBwNorInterest;//实收表外正常利息金额
	private BigDecimal realBwDelInterest;//实收表外拖欠利息金额
	private BigDecimal realBwOverInterest;//实收表外罚息
	private BigDecimal realBwComInterest;//实收表外复利
	private BigDecimal penaltyForAdv;//提前还款违约金
	private BigDecimal verificationAmt;//核销收回本金
	private BigDecimal verificationInterest;//核销收回利息
	private BigDecimal verificationYjInterest;//核销收回应计利息
	
	public RepayLoanList(){
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
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

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public String getRepaymentAccountName() {
		return repaymentAccountName;
	}

	public void setRepaymentAccountName(String repaymentAccountName) {
		this.repaymentAccountName = repaymentAccountName;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
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

	public BigDecimal getRealCapitalAmt() {
		return realCapitalAmt;
	}

	public void setRealCapitalAmt(BigDecimal realCapitalAmt) {
		this.realCapitalAmt = realCapitalAmt;
	}

	public BigDecimal getAdvanceCapitalAmt() {
		return advanceCapitalAmt;
	}

	public void setAdvanceCapitalAmt(BigDecimal advanceCapitalAmt) {
		this.advanceCapitalAmt = advanceCapitalAmt;
	}

	public BigDecimal getRealBnNorInterest() {
		return realBnNorInterest;
	}

	public void setRealBnNorInterest(BigDecimal realBnNorInterest) {
		this.realBnNorInterest = realBnNorInterest;
	}

	public BigDecimal getRealBnDelInterest() {
		return realBnDelInterest;
	}

	public void setRealBnDelInterest(BigDecimal realBnDelInterest) {
		this.realBnDelInterest = realBnDelInterest;
	}

	public BigDecimal getRealBnOverInterest() {
		return realBnOverInterest;
	}

	public void setRealBnOverInterest(BigDecimal realBnOverInterest) {
		this.realBnOverInterest = realBnOverInterest;
	}

	public BigDecimal getRealBnComInterest() {
		return realBnComInterest;
	}

	public void setRealBnComInterest(BigDecimal realBnComInterest) {
		this.realBnComInterest = realBnComInterest;
	}

	public BigDecimal getRealBwNorInterest() {
		return realBwNorInterest;
	}

	public void setRealBwNorInterest(BigDecimal realBwNorInterest) {
		this.realBwNorInterest = realBwNorInterest;
	}

	public BigDecimal getRealBwDelInterest() {
		return realBwDelInterest;
	}

	public void setRealBwDelInterest(BigDecimal realBwDelInterest) {
		this.realBwDelInterest = realBwDelInterest;
	}

	public BigDecimal getRealBwOverInterest() {
		return realBwOverInterest;
	}

	public void setRealBwOverInterest(BigDecimal realBwOverInterest) {
		this.realBwOverInterest = realBwOverInterest;
	}

	public BigDecimal getRealBwComInterest() {
		return realBwComInterest;
	}

	public void setRealBwComInterest(BigDecimal realBwComInterest) {
		this.realBwComInterest = realBwComInterest;
	}

	public BigDecimal getPenaltyForAdv() {
		return penaltyForAdv;
	}

	public void setPenaltyForAdv(BigDecimal penaltyForAdv) {
		this.penaltyForAdv = penaltyForAdv;
	}

	public BigDecimal getVerificationAmt() {
		return verificationAmt;
	}

	public void setVerificationAmt(BigDecimal verificationAmt) {
		this.verificationAmt = verificationAmt;
	}

	public BigDecimal getVerificationInterest() {
		return verificationInterest;
	}

	public void setVerificationInterest(BigDecimal verificationInterest) {
		this.verificationInterest = verificationInterest;
	}

	public BigDecimal getVerificationYjInterest() {
		return verificationYjInterest;
	}

	public void setVerificationYjInterest(BigDecimal verificationYjInterest) {
		this.verificationYjInterest = verificationYjInterest;
	}
	
	
}
