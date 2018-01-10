package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;
/**
 * 
 * @author chenhuan
 *
 */
//放款回退通知 票据系统->CRMS
public class CRMSLoanBackNotRq extends SuperBosfxRq{
	@XmlElement(name="LoanApproveNum")
	public String	LoanApproveNum	;       //	放款核准单号
	@XmlElement(name="AcceptNo")
	public String	AcceptNo	;        //	承兑批次号
	/**
	 * @param AcceptNo 要设置的 AcceptNo
	 */
	public void setAcceptNo(String acceptNo) {
		AcceptNo = acceptNo;
	}
	/**
	 * @param LoanApproveNum 要设置的 LoanApproveNum
	 */
	public void setLoanApproveNum(String loanApproveNum) {
		LoanApproveNum = loanApproveNum;
	}
	public String toString() {
		return "CRMSLoanBackNotRq [LoanApproveNum="+LoanApproveNum+
		", AcceptNo="+ AcceptNo + "]";
	}
}
