package com.bos.inter.CallCAMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class CRMSLoanStaInqRs extends SuperBosfxRs {
	@XmlElement(name = "LdNum")
	public String LdNum; // LD流水

	@XmlElement(name = "LoanId")
	public String LoanId; // 放款申请号

	@XmlElement(name = "LoanApproveNum")
	public String LoanApproveNum; // 放款核准单号

	@XmlElement(name = "TransState")
	public String TransState; // 放款核准单号

	public void setLdNum(String ldNum) {
		LdNum = ldNum;
	}

	public void setLoanApproveNum(String loanApproveNum) {
		LoanApproveNum = loanApproveNum;
	}

	public void setLoanId(String loanId) {
		LoanId = loanId;
	}

	public void setTransState(String transState) {
		TransState = transState;
	}

	public String toString() {
		return "CRMSLoanStaInqRq [LdNum=" + LdNum + ", LoanId=" + LoanId
				+ ", LoanApproveNum=" + LoanApproveNum + ", TransState="
				+ TransState + "]";
	}

}
