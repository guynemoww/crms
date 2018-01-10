/**
 * 
 */
package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * @author tc
 *
 */
//放款回退通知 CRMS->SCF
public class CRMSLoanBackRq extends SuperBosfxRq{
	@XmlElement(name="ContractId")
	public String	ContractId	;//	合同编号
    @XmlElement(name="AcceptNo")
	public String	AcceptNo	;//	承兑批次号
    @XmlElement(name="LoanId")
	public String LoanId;//SCF放款申请号
    @XmlElement(name="LoanApproveNum")
	public String	LoanApproveNum	;//	放款核准单号
    
    
    @Override
	public String toString() {
		return "CRMSLoanBackRq [ContractId=" + ContractId + ", AcceptNo="
				+ AcceptNo + ", LoanId=" + LoanId + ", LoanApproveNum="
				+ LoanApproveNum + "]";
    }


	/**
	 * @param acceptNo 要设置的 acceptNo
	 */
	public void setAcceptNo(String acceptNo) {
		AcceptNo = acceptNo;
	}


	/**
	 * @param contractId 要设置的 contractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}


	/**
	 * @param loanApproveNum 要设置的 loanApproveNum
	 */
	public void setLoanApproveNum(String loanApproveNum) {
		LoanApproveNum = loanApproveNum;
	}


	/**
	 * @param loanId 要设置的 loanId
	 */
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}
}
