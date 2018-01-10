package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 国结业务贷款状态更新输出
 * @author CHENPAN
 *
 */
public class RepayStatusUpdateRs extends SuperBosfxRs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137508070638755420L;
	private String loanId;//放款ID
	private String contractId;//业务合同ID
	private String summaryNum;//借据编号
	private String loanStatus;//出账状态
	public RepayStatusUpdateRs() {
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getSummaryNum() {
		return summaryNum;
	}
	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
}
