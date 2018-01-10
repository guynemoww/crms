/**
 * 
 */
package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * @author tc
 * 
 */
// 放款申请 SCF->CRMS
public class CRMSLoanAppRq extends SuperBosfxRq {
	@XmlElement(name = "CustomerNo")
	public String CustomerNo;// 客户编号

	@XmlElement(name = "ContractId")
	public String ContractId;// 合同编号

	@XmlElement(name = "SubContractId")
	public String SubContractId;// 担保合同编号

	@XmlElement(name = "Currency")
	public String Currency;// 申请放款闭口币种

	@XmlElement(name = "Amt")
	public Double Amt;// 申请放款闭口金额

	@XmlElement(name = "ApplyDt")
	public String ApplyDt;// 申请日期

	@XmlElement(name = "LoanId")
	public String LoanId;// SCF放款申请号

	@XmlElement(name = "EndDt")
	public String EndDt;// 到期日期

	@XmlElement(name = "RqUID_1")
	public String RqUID_1;// SCF交易流水

	/**
	 * @param amt
	 *            要设置的 amt
	 */
	public void setAmt(Double amt) {
		Amt = amt;
	}

	/**
	 * @param applyDt
	 *            要设置的 applyDt
	 */
	public void setApplyDt(String applyDt) {
		ApplyDt = applyDt;
	}

	/**
	 * @param contractId
	 *            要设置的 contractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}

	/**
	 * @param currency
	 *            要设置的 currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	 * @param customerNo
	 *            要设置的 customerNo
	 */
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}

	/**
	 * @param endDt
	 *            要设置的 endDt
	 */
	public void setEndDt(String endDt) {
		EndDt = endDt;
	}

	/**
	 * @param loanId
	 *            要设置的 loanId
	 */
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}

	/**
	 * @param subContractId
	 *            要设置的 subContractId
	 */
	public void setSubContractId(String subContractId) {
		SubContractId = subContractId;
	}

	@Override
	public String toString() {
		return "CRMSLoanAppRq [CustomerNo=" + CustomerNo + ",ContractId="
				+ ContractId + ",SubContractId=" + SubContractId + ",Currency="
				+ Currency + ",Amt=" + Amt + ",ApplyDt=" + ApplyDt + ",LoanId="
				+ LoanId + ",EndDt=" + EndDt + ",RqUID_1=" + RqUID_1 + "]";
	}

	public void setRqUID_1(String rqUID_1) {
		RqUID_1 = rqUID_1;
	}

}
