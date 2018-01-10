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
//放款余额查询 SCF->CRMS
public class CRMSLoanBalInqRq extends SuperBosfxRq {
	@XmlElement(name="ContractId")
	public String	ContractId	;//	合同编号
    @XmlElement(name="LoanApproveNum")
	public String	LoanApproveNum	;//	放款核准单号
    @XmlElement(name="BizMode")
	public String	BizMode	;//	业务模式
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
    @Override
	public String toString() {
		return "CRMSLoanBalInqRq [ContractId=" + ContractId  + ", LoanApproveNum="
				+ LoanApproveNum + ", BizMode="
				+ BizMode + "]";
    }
	public void setBizMode(String bizMode) {
		BizMode = bizMode;
	}
}
