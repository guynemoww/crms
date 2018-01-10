package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class LimitInfoBean extends SuperBosfxRq  {
	@XmlElement(name="Customer")
	public String Customer					;//	客户号  
	@XmlElement(name="LimitCode")
	public String LimitCode					;//	额度代码
	@XmlElement(name="Currency")
	public String Currency					;//	额度币种   
	@XmlElement(name="Amt")
	public String Amt					;//	额度金额 
	@XmlElement(name="BeginDt")
	public String BeginDt					;//	审批日  
	@XmlElement(name="EndDt")
	public String EndDt					;//	到期日    
	@XmlElement(name="ProposalDt")
	public String ProposalDt					;//	额度申请提交日
	
	@XmlElement(name="CreditPerid")
	public String CreditPerid;//持续透支期
	
	@XmlElement(name="Available")
	public String Available					;//	是否可用        
	@XmlElement(name="ReviewFrequency")
	public String ReviewFrequency		;//	审核频率
	@XmlElement(name="FBID")
	public String FBID					;//	业务类型      
	@XmlElement(name="TxnType")
	public String TxnType					;//	本地交易类型
	
	@XmlElement(name="OnlineLimitDate")
	public String OnlineLimitDate;//额度启用日
	
	
	 
	/**
	 * @param onlineLimitDate 要设置的 onlineLimitDate
	 */
	public void setOnlineLimitDate(String onlineLimitDate) {
		OnlineLimitDate = onlineLimitDate;
	}
	/**
	 * @param creditPerid 要设置的 creditPerid
	 */
	public void setCreditPerid(String creditPerid) {
		CreditPerid = creditPerid;
	}
	/**
	 * @param reviewFrequency 要设置的 reviewFrequency
	 */
	public void setReviewFrequency(String reviewFrequency) {
		ReviewFrequency = reviewFrequency;
	}
	/**
	 * @param amt 要设置的 amt
	 */
	public void setAmt(String amt) {
		Amt = amt;
	}
	/**
	 * @param available 要设置的 available
	 */
	public void setAvailable(String available) {
		Available = available;
	}
	/**
	 * @param beginDt 要设置的 beginDt
	 */
	public void setBeginDt(String beginDt) {
		BeginDt = beginDt;
	}
	/**
	 * @param currency 要设置的 currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param customer 要设置的 customer
	 */
	public void setCustomer(String customer) {
		Customer = customer;
	}
	/**
	 * @param endDt 要设置的 endDt
	 */
	public void setEndDt(String endDt) {
		EndDt = endDt;
	}
	/**
	 * @param fbid 要设置的 fBID
	 */
	public void setFBID(String fbid) {
		FBID = fbid;
	}
	/**
	 * @param limitCode 要设置的 limitCode
	 */
	public void setLimitCode(String limitCode) {
		LimitCode = limitCode;
	}
	/**
	 * @param proposalDt 要设置的 proposalDt
	 */
	public void setProposalDt(String proposalDt) {
		ProposalDt = proposalDt;
	}

	/**
	 * @param txnType 要设置的 txnType
	 */
	public void setTxnType(String txnType) {
		TxnType = txnType;
	}
		@Override
		public String toString() {
			return "LimitInfoBean [Customer=" + Customer + ", LimitCode="
					+ LimitCode + ", Currency=" + Currency + ", Amt="
					+ Amt + ", BeginDt=" + BeginDt + ", EndDt="
					+ EndDt + ", ProposalDt=" + ProposalDt + ", Available="
					+ Available + ", REVIEW_FREQUENCY=" + ReviewFrequency
					+ ", FBID=" + FBID + ", TxnType=" + TxnType
					+ ", OnlineLimitDate="+OnlineLimitDate+"]";
		}
}
