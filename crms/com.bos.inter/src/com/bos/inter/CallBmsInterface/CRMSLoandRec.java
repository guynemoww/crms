package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;

import com.eos.system.annotation.Bizlet;
public class CRMSLoandRec {
	@XmlElement(name="AcctNo")
	public String	AcctNo	;         //	现金等价物账号
    @XmlElement(name="CurrType")
	public String	CurrType	;    //	现金等价物账号币种
    @XmlElement(name="Amt")
	public String Amt;              //现金等价物账号占用金额
    /**
	 * @param AcctNo 要设置的 AcctNo
	 */
	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}
	/**
	 * @param Amt 要设置的 Amt
	 */
	public void setAmt(String amt) {
		Amt = amt;
	}
	/**
	 * @param CurrType 要设置的 CurrType
	 */
	public void setCurrType(String currType) {
		CurrType = currType;
	}
    
}
