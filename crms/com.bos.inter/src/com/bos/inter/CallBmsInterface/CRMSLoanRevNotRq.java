/**
 * 
 */
package com.bos.inter.CallBmsInterface;


import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * @author shenglong
 * @date 2014-06-05 11:51:19
 *
 */
//放款审核通知 CRMS->票据系统
public class CRMSLoanRevNotRq extends SuperBosfxRq {
	@XmlElement(name="ContractId")
	public String	ContractId	;       //	合同编号
	@XmlElement(name="AcceptNo")
	public String	AcceptNo	;        //	承兑批次号
	@XmlElement(name="Amt")
	public String	Amt	;              //	放款金额
	@XmlElement(name="Currency")
	public String	Currency	;      //放款币种
	@XmlElement(name="LoanApproveNum")
	public String	LoanApproveNum	;  //	放款核准单号
	@XmlElement(name="YearRate")
	public String	YearRate	;    //	执行年利率
	@XmlElement(name="LoanApplyDt")
	public String	LoanApplyDt	;   //	申请日期
	@XmlElement(name="LoanEndDt")
	public String	LoanEndDt	;  //	到期日期
	@XmlElement(name="LoanDt")
	public String	LoanDt	;    //	放款日
	@XmlElement(name="State")
	public String	State	;   //	审核状态
	/**
	 * @param AcceptNo 要设置的 AcceptNo
	 */
	public void setAcceptNo(String acceptNo) {
		AcceptNo = acceptNo;
	}
	/**
	 * @param Amt 要设置的 Amt
	 */
	public void setAmt(String i) {
		Amt = i;
	}
	/**
	 * @param ContractId 要设置的 ContractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	/**
	 * @param Currency 要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param LoanApplyDt 要设置的 LoanApplyDt
	 */
	public void setLoanApplyDt(String loanApplyDt) {
		LoanApplyDt = loanApplyDt;
	}
	/**
	 * @param LoanApproveNum 要设置的 LoanApproveNum
	 */
	public void setLoanApproveNum(String loanApproveNum) {
		LoanApproveNum = loanApproveNum;
	}
	/**
	 * @param LoanDt 要设置的 LoanDt
	 */
	public void setLoanDt(String loanDt) {
		LoanDt = loanDt;
	}
	/**
	 * @param State 要设置的 State
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * @param YearRate 要设置的 YearRate
	 */
	public void setYearRate(String yearRate) {
		YearRate = yearRate;
	}
	/**
	 * @param LoanEndDt 要设置的 LoanEndDt
	 */
	public void setLoanEndDt(String loanEndDt) {
		LoanEndDt = loanEndDt;
	}
	public String toString() {
		return "CRMSLoanRevNotRq [ContractId="+ContractId+ ", AcceptNo="
		+ AcceptNo + ", Amt=" + Amt + ", Currency="
		+ Currency +",LoanApproveNum="+LoanApproveNum+", YearRate=" 
		+ YearRate + ", LoanApplyDt=" + LoanApplyDt + ", LoanEndDt="
		+ LoanEndDt + ", LoanDt=" + LoanDt + ", State=" + State + "]";
	}
}
