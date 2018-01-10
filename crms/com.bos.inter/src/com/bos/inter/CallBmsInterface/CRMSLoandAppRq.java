package com.bos.inter.CallBmsInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * 
 * @author chenhuan
 * 
 */
// 放款申请 票据->CRMS
public class CRMSLoandAppRq extends SuperBosfxRq {
	@XmlElement(name = "RqUID_1")
	public String RqUID_1;// bms交易流水

	@XmlElement(name = "ApplyId")
	public String ApplyId; // 申请人工号

	@XmlElement(name = "ApplyName")
	public String ApplyName; // 申请人名称

	@XmlElement(name = "ApplyOrg")
	public String ApplyOrg; // 申请人网点

	@XmlElement(name = "ContractId")
	public String ContractId; // 合同编号

	@XmlElement(name = "AcceptNo")
	public String AcceptNo; // 承兑批次号

	@XmlElement(name = "LoanId")
	public String LoanId; // SCF放款申请号

	@XmlElement(name = "LoanAmt")
	public Double LoanAmt; // 申请放款闭口金额

	@XmlElement(name = "Currency")
	public String Currency; // 币种

	@XmlElement(name = "ApplyDt")
	public String ApplyDt; // 申请日期

	@XmlElement(name = "EndDt")
	public String EndDt; // 到期日期

	@XmlElement(name = "BizType")
	public String BizType; // 业务类型

	@XmlElement(name = "CRMSLoandAppRec")
	public List<CRMSLoandAppRec> CRMSLoandAppRec;

	@XmlElement(name = "CRMSLoandRec")
	public List<CRMSLoandRec> CRMSLoandRec;

	public String toString() {
		return "CRMSLoandAppRq [ContractId=" + ContractId + ", AcceptNo="
				+ AcceptNo + ", LoanId=" + LoanId + ", LoanAmt=" + LoanAmt
				+ ",ApplyDt" + ApplyDt + ", EndDt=" + EndDt + ", BizType="
				+ BizType + ", CRMSLoandAppRec=" + CRMSLoandAppRec
				+ ", CRMSLoandRec=" + CRMSLoandRec + ", RqUID_1=" + RqUID_1
				+ ", ApplyId=" + ApplyId + ", ApplyName=" + ApplyName
				+ ", ApplyOrg=" + ApplyOrg + "]";
	}

	/**
	 * @param AcceptNo
	 *            要设置的 AcceptNo
	 */
	public void setAcceptNo(String acceptNo) {
		AcceptNo = acceptNo;
	}

	/**
	 * @param ApplyDt
	 *            要设置的 ApplyDt
	 */
	public void setApplyDt(String applyDt) {
		ApplyDt = applyDt;
	}

	/**
	 * @param BizType
	 *            要设置的 BizType
	 */
	public void setBizType(String bizType) {
		BizType = bizType;
	}

	/**
	 * @param ContractId
	 *            要设置的 ContractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}

	/**
	 * @param CRMSLoandAppRec
	 *            要设置的 CRMSLoandAppRec
	 */
	public void setCRMSLoandAppRec(List<CRMSLoandAppRec> loandAppRec) {
		CRMSLoandAppRec = loandAppRec;
	}

	/**
	 * @param CRMSLoandRec
	 *            要设置的 CRMSLoandRec
	 */
	public void setCRMSLoandRec(List<CRMSLoandRec> loandRec) {
		CRMSLoandRec = loandRec;
	}

	/**
	 * @param Currency
	 *            要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	 * @param EndDt
	 *            要设置的 EndDt
	 */
	public void setEndDt(String endDt) {
		EndDt = endDt;
	}

	/**
	 * @param LoanAmt
	 *            要设置的 LoanAmt
	 */
	public void setLoanAmt(Double loanAmt) {
		LoanAmt = loanAmt;
	}

	/**
	 * @param LoanId
	 *            要设置的 LoanId
	 */
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}

	public void setRqUID_1(String rqUID_1) {
		RqUID_1 = rqUID_1;
	}

	public void setApplyId(String applyId) {
		ApplyId = applyId;
	}

	public void setApplyName(String applyName) {
		ApplyName = applyName;
	}

	public void setApplyOrg(String applyOrg) {
		ApplyOrg = applyOrg;
	}

}
