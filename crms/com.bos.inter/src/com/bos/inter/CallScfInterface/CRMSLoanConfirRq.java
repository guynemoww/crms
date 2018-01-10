/**
 * 
 */
package com.bos.inter.CallScfInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * @author tc
 *
 */
 //放款确认通知 CRMS->SCF
public class CRMSLoanConfirRq extends SuperBosfxRq{
	    @XmlElement(name="ContractId")
		public String	ContractId	;//	合同编号
	    @XmlElement(name="AcceptNo")
		public String	AcceptNo	;//	承兑批次号
	    @XmlElement(name="LoanId")
		public String LoanId;//SCF放款申请号
	    @XmlElement(name="Amt")
		public String	Amt	;//	放款金额
	    @XmlElement(name="Currency")
		public String	Currency	;//	放款币种
	    @XmlElement(name="LoanApproveNum")
		public String	LoanApproveNum	;//	放款核准单号
	    @XmlElement(name="CrmsLdNo")
		public String	CrmsLdNo	;//	CRMS出账流水号
	    @XmlElement(name="YearRate")
		public String	YearRate	;//	执行年利率
	    @XmlElement(name="LoanApplyDt")
		public String	LoanApplyDt	;//	申请日期
	    @XmlElement(name="LoanEndDt")
		public String	LoanEndDt	;//	到期日期
	    @XmlElement(name="NoteInfoRec")
		public List<NoteInfoRec>  NoteInfoRec;//票据信息
	    
	    @Override
		public String toString() {
			return "CRMSLoanConfirRq [ContractId=" + ContractId + ", AcceptNo="
					+ AcceptNo + ", LoanId=" + LoanId + ", Amt="
					+ Amt + ", Currency=" + Currency + ", LoanApproveNum="
					+ LoanApproveNum + ", CrmsLdNo=" + CrmsLdNo + ", YearRate="
					+ YearRate + ", LoanApplyDt=" + LoanApplyDt
					+ ", LoanEndDt=" + LoanEndDt + ", NoteInfo=" + NoteInfoRec
					+ "]";
	    }
		/**
		 * @param acceptNo 要设置的 acceptNo
		 */
		public void setAcceptNo(String acceptNo) {
			AcceptNo = acceptNo;
		}
		/**
		 * @param amt 要设置的 amt
		 */
		public void setAmt(String amt) {
			Amt = amt;
		}
		/**
		 * @param contractId 要设置的 contractId
		 */
		public void setContractId(String contractId) {
			ContractId = contractId;
		}
		/**
		 * @param crmsLdNo 要设置的 crmsLdNo
		 */
		public void setCrmsLdNo(String crmsLdNo) {
			CrmsLdNo = crmsLdNo;
		}
		/**
		 * @param currency 要设置的 currency
		 */
		public void setCurrency(String currency) {
			Currency = currency;
		}
		/**
		 * @param loanApplyDt 要设置的 loanApplyDt
		 */
		public void setLoanApplyDt(String loanApplyDt) {
			LoanApplyDt = loanApplyDt;
		}
		/**
		 * @param loanApproveNum 要设置的 loanApproveNum
		 */
		public void setLoanApproveNum(String loanApproveNum) {
			LoanApproveNum = loanApproveNum;
		}
		/**
		 * @param loanEndDt 要设置的 loanEndDt
		 */
		public void setLoanEndDt(String loanEndDt) {
			LoanEndDt = loanEndDt;
		}
		/**
		 * @param loanId 要设置的 loanId
		 */
		public void setLoanId(String loanId) {
			LoanId = loanId;
		}
		/**
		 * @param yearRate 要设置的 yearRate
		 */
		public void setYearRate(String yearRate) {
			YearRate = yearRate;
		}
		public void setNoteInfoRec(List<NoteInfoRec> noteInfoRec) {
			NoteInfoRec = noteInfoRec;
		}

}
