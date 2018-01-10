package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class TStLdStdAaaRs extends SuperBosfxRs{
	@XmlElement(name="ContractNo")
	public String	ContractNo	;         		 //	LD流水
    @XmlElement(name="InterestRate")
	public String	InterestRate	;    		//	生效利率
    @XmlElement(name="TotInterestAmt")
	public String TotInterestAmt;              //利息金额
    @XmlElement(name="PsRate")
	public String	PsRate	;         		 //	生效本金罚息率
    @XmlElement(name="PeRate")
	public String	PeRate	;    		    //	生效利息罚息率
    @XmlElement(name="Status")
	public String Status;                  //贷款状态
    @XmlElement(name="RecordStatus")
	public String	RecordStatus	;     //记录状态
    @XmlElement(name="CurrNo") 
	public String	CurrNo	;    		 //	当前版本号
    @XmlElement(name="DateTime_1")
	public String DateTime_1;           //日期时间
    @Override
	public String toString() {
		return "TStLdStdAaaRs [ContractNo=" + ContractNo + ", InterestRate="
				+ InterestRate + ", TotInterestAmt=" + TotInterestAmt + ", PsRate="
				+ PsRate + ",PeRate=" + PeRate + ", Status="
				+ Status + ", RecordStatus=" + RecordStatus + ",CurrNo=" + CurrNo + ",DateTime_1=" + DateTime_1 + "]";
    }
    /**
	 * @param ContractNo 要设置的 ContractNo
	 */
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
	 /**
	 * @param CurrNo 要设置的 CurrNo
	 */
	public void setCurrNo(String currNo) {
		CurrNo = currNo;
	}
	 /**
	 * @param DateTime_1 要设置的 DateTime_1
	 */
	public void setDateTime_1(String dateTime_1) {
		DateTime_1 = dateTime_1;
	}
	 /**
	 * @param InterestRate 要设置的 InterestRate
	 */
	public void setInterestRate(String interestRate) {
		InterestRate = interestRate;
	}
	 /**
	 * @param PeRate 要设置的 PeRate
	 */
	public void setPeRate(String peRate) {
		PeRate = peRate;
	}
	 /**
	 * @param PsRate 要设置的 PsRate
	 */
	public void setPsRate(String psRate) {
		PsRate = psRate;
	}
	 /**
	 * @param RecordStatus 要设置的 RecordStatus
	 */
	public void setRecordStatus(String recordStatus) {
		RecordStatus = recordStatus;
	}
	 /**
	 * @param Status 要设置的 Status
	 */
	public void setStatus(String status) {
		Status = status;
	}
	 /**
	 * @param TotInterestAmt 要设置的 TotInterestAmt
	 */
	public void setTotInterestAmt(String totInterestAmt) {
		TotInterestAmt = totInterestAmt;
	}
    
}
