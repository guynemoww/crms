package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class TNbosIlcOpenHLDBean extends SuperBosfxRq {
	@XmlElement(name="ApplicantCustno")
	public String ApplicantCustno	;//	申请人客户号
	@XmlElement(name="Currency")
	public String Currency	;//	信用证币种
	@XmlElement(name="Amount")
	public String Amount	;//	信用证金额
	@XmlElement(name="PercentageCrAmt")
	public String PercentageCrAmt	;//	上浮比率
	@XmlElement(name="PercentageDrAmt")
	public String PercentageDrAmt	;//	下浮比率
	@XmlElement(name="DdApprNo")
	public String DdApprNo	;//	核准单号
	
	
	@XmlElement(name="LimitReference")
	public String LimitReference	;//	额度编号
	@XmlElement(name="FBID")
	public String FBID	;//	中间业务类型
	
	@XmlElement(name="DelimPos")
	public String DelimPos	;//	中间业务类型


	/**
	 * @param delimPos 要设置的 delimPos
	 */
	public void setDelimPos(String delimPos) {
		DelimPos = delimPos;
	}

	/**
	 * @param fbid 要设置的 fBID
	 */
	public void setFBID(String fbid) {
		FBID = fbid;
	}

	/**
	 * @param limitReference 要设置的 limitReference
	 */
	public void setLimitReference(String limitReference) {
		LimitReference = limitReference;
	}

	/**
	 * @param amount 要设置的 amount
	 */
	public void setAmount(String amount) {
		Amount = amount;
	}

	/**
	 * @param applicantCustno 要设置的 applicantCustno
	 */
	public void setApplicantCustno(String applicantCustno) {
		ApplicantCustno = applicantCustno;
	}

	/**
	 * @param currency 要设置的 currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	 * @param ddApprNo 要设置的 ddApprNo
	 */
	public void setDdApprNo(String ddApprNo) {
		DdApprNo = ddApprNo;
	}

	/**
	 * @param percentageCrAmt 要设置的 percentageCrAmt
	 */
	public void setPercentageCrAmt(String percentageCrAmt) {
		PercentageCrAmt = percentageCrAmt;
	}

	/**
	 * @param percentageDrAmt 要设置的 percentageDrAmt
	 */
	public void setPercentageDrAmt(String percentageDrAmt) {
		PercentageDrAmt = percentageDrAmt;
	}

	@Override
	public String toString() {
		return "TNbosIlcOpenHLDBean [ApplicantCustno=" + ApplicantCustno + ", Currency=" 
		       + Currency + ", Amount=" 
		       + Amount + ", PercentageCrAmt=" 
		       + PercentageCrAmt + ", PercentageDrAmt=" 
		       + PercentageDrAmt + ", DdApprNo=" 
		       + DdApprNo + "]";
	}
}
