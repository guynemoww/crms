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
//放款申请取消 SCF->CRMS
public class CRMSLoanAppCancelRq extends SuperBosfxRq{
	@XmlElement(name="ContractId")
	public String	ContractId	;//	合同编号
    @XmlElement(name="AcceptNo")
	public String	AcceptNo	;//	承兑批次号
    @XmlElement(name="LoanId")
	public String LoanId;//SCF放款申请号
    @XmlElement(name="CrmsLoanId")
	public String	CrmsLoanId	;//	CRMS放款号
    
    
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
	 * @param crmsLoanId 要设置的 crmsLoanId
	 */
	public void setCrmsLoanId(String crmsLoanId) {
		CrmsLoanId = crmsLoanId;
	}


	/**
	 * @param loanId 要设置的 loanId
	 */
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}


	@Override
	public String toString() {
		return "CRMSLoanAppCancelRq [ContractId=" + ContractId + ", AcceptNo="
				+ AcceptNo + ", LoanId=" + LoanId + ", CrmsLoanId="
				+ CrmsLoanId + "]";
    }
}
