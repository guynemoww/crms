/**
 * 
 */
package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
import com.eos.system.annotation.Bizlet;

/**
 * @author tc
 *
 */
//放款余额查询 SCF->CRMS
public class CRMSLoanBalInqRs extends SuperBosfxRs{
	@XmlElement(name="LoanSummaryId")
	public String	LoanSummaryId	;//	借据编号
    @XmlElement(name="OverDueFlag")
	public String	OverDueFlag	;//	逾期状态
    @XmlElement(name="Currency")
	public String Currency;//币种
    @XmlElement(name="LoanAmt")
	public String	LoanAmt	;//	借据金额
    @XmlElement(name="LoanBal")
	public String	LoanBal	;//	借据余额
    @XmlElement(name="MarginBal")
	public String	MarginBal	;//	现金等价物占用余额
    @XmlElement(name="IsStData")
	public String	IsStData	;//	是否存量数据
	/**
	 * @param currency 要设置的 currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param loanAmt 要设置的 loanAmt
	 */
	public void setLoanAmt(String loanAmt) {
		LoanAmt = loanAmt;
	}
	/**
	 * @param loanBal 要设置的 loanBal
	 */
	public void setLoanBal(String loanBal) {
		LoanBal = loanBal;
	}
	/**
	 * @param loanSummaryId 要设置的 loanSummaryId
	 */
	public void setLoanSummaryId(String loanSummaryId) {
		LoanSummaryId = loanSummaryId;
	}
	/**
	 * @param overDueFlag 要设置的 overDueFlag
	 */
	public void setOverDueFlag(String overDueFlag) {
		OverDueFlag = overDueFlag;
	}
	@Override
	public String toString() {
		return "CRMSLoanBalInqRs [commonRsHdr="+commonRsHdr+ ", LoanSummaryId="
		+ LoanSummaryId + ", OverDueFlag=" + OverDueFlag + ", Currency="
		+ Currency +",LoanBal="+LoanBal+",MarginBal="+MarginBal+",IsStData="+IsStData+"]";
	}
	public void setIsStData(String isStData) {
		IsStData = isStData;
	}
	public void setMarginBal(String marginBal) {
		MarginBal = marginBal;
	}

}
