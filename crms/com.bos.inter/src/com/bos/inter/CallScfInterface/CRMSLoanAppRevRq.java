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
// 放款申请撤销 CRMS->SCF
public class CRMSLoanAppRevRq extends SuperBosfxRq{
	@XmlElement(name="ContractId")
	public String	ContractId	;//	合同编号
    @XmlElement(name="LoanId")
	public String	LoanId	;//	SCF放款申请号
	/**
	 * @param contractId 要设置的 contractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	/**
	 * @param loanId 要设置的 loanId
	 */
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}
    @Override
	public String toString() {
		return "CRMSLoanAppRevRq [ContractId=" + ContractId 
				 + ", LoanId=" + LoanId + "]";
    }
}
