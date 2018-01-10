package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 国结业务贷款状态更新输入
 * @author CHENPAN
 *
 */
public class RepayStatusUpdateRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7240825626510404402L;
	private String loanId;//放款ID
	private String contractId;//业务合同ID
	private String summaryNum;//借据编号
	private String loanStatus;//出账状态
	public RepayStatusUpdateRq() {
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
