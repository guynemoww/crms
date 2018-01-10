package com.bos.inter.CallScfInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.eos.system.annotation.Bizlet;

public class CRMSReplyInfoInqRec {
	@XmlElement(name="CustNo")
	public String	CustNo	;//	ECIF(T24)客户号
	@XmlElement(name="CustomerNo")
	public String	CustomerNo	;//	CRMS客户号
    @XmlElement(name="ApproveId")
	public String	ApproveId	;//	批复编号
    @XmlElement(name="ApproveType")
	public String	ApproveType	;//	业务类型
    @XmlElement(name="BizMode")
	public String	BizMode	;//业务模式
    @XmlElement(name="Currency")
	public String	Currency	;//	币种
    @XmlElement(name="ProductAmt")
	public String	ProductAmt	;//	业务模式金额
    @XmlElement(name="DateBegin")
	public String	DateBegin	;//	起始日期
    @XmlElement(name="LimitEnd")
	public String	LimitEnd	;//	到期日期
    @XmlElement(name="ProductType")
	public String	ProductType	;//批复明细
	/**
	 * @param ApproveId 要设置的 ApproveId
	 */
	public void setApproveId(String approveId) {
		ApproveId = approveId;
	}
	/**
	 * @param ApproveType 要设置的 ApproveType
	 */
	public void setApproveType(String approveType) {
		ApproveType = approveType;
	}
	/**
	 * @param BizMode 要设置的 BizMode
	 */
	public void setBizMode(String bizMode) {
		BizMode = bizMode;
	}
	/**
	 * @param Currency 要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param CustomerNo 要设置的 CustomerNo
	 */
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}
	/**
	 * @param DateBegin 要设置的 DateBegin
	 */
	public void setDateBegin(String dateBegin) {
		DateBegin = dateBegin;
	}
	/**
	 * @param LimitEnd 要设置的 LimitEnd
	 */
	public void setLimitEnd(String limitEnd) {
		LimitEnd = limitEnd;
	}
	/**
	 * @param ProductAmt 要设置的 ProductAmt
	 */
	public void setProductAmt(String productAmt) {
		ProductAmt = productAmt;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public void setProductType(String productType) {
		ProductType = productType;
	}
    
}
