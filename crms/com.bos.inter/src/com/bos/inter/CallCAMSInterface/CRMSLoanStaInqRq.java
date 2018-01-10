package com.bos.inter.CallCAMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class CRMSLoanStaInqRq extends SuperBosfxRq {
	@XmlElement(name = "LdNum")
	public String LdNum; // LD流水

	@XmlElement(name = "LoanId")
	public String LoanId; // 放款申请号

	@XmlElement(name = "LoanApproveNum")
	public String LoanApproveNum; // 放款核准单号
	
	@XmlElement(name = "SpName")
	public String SpName; // 外围系统简称

	public void setLdNum(String ldNum) {
		LdNum = ldNum;
	}

	public void setLoanApproveNum(String loanApproveNum) {
		LoanApproveNum = loanApproveNum;
	}

	public void setLoanId(String loanId) {
		LoanId = loanId;
	}
	public String toString() {
		return "CRMSLoanStaInqRq [LdNum="+LdNum+ ", LoanId="
		+ LoanId + ", LoanApproveNum=" + LoanApproveNum + ", SpName=" + SpName + "]";
	}

	public void setSpName(String spName) {
		SpName = spName;
	}

}
