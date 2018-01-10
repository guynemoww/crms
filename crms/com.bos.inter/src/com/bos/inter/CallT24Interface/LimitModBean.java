package com.bos.inter.CallT24Interface;
import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class LimitModBean extends SuperBosfxRq  { 
	@XmlElement(name="Limit")
	public String Limit					;//	额度代码
	@XmlElement(name="Amt")
	public String Amt					;//	额度金额 
	@XmlElement(name="Type")
	public String Type					;//	额度交易类型-1:额度增加2:额度减少3.额度信息维护 
	@XmlElement(name="EndDt")
	public String EndDt					;//	到期日    

	@XmlElement(name="Available")
	public String Available					;//	是否可用     
	@XmlElement(name="Num")
	public String Num					;//	工具额度 
	@XmlElement(name="CreditPerid")
	public String CreditPerid;//持续透支期
	@XmlElement(name="FBID")
	public String FBID					;//	业务类型      
	@XmlElement(name="TxnType")
	public String TxnType					;//	本地交易类型
	
	@XmlElement(name="OnlineLimitDate")
	public String OnlineLimitDate;//额度启用日
	
	@Override
	public String toString() {
		return "LimitModBean [Limit=" + Limit + ", Amt="
				+ Amt + ", Type=" + Type + ", EndDt="
				+ EndDt + ", Available=" + Available + ", Num="
				+ Num + ", CreditPerid=" + CreditPerid + ", FBID="
				+ FBID + ", TxnType=" + TxnType
				+ ", FBID=" + FBID + ", TxnType=" + TxnType
				+ ", OnlineLimitDate="+OnlineLimitDate+"]";
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
	 * @param creditPerid 要设置的 creditPerid
	 */
	public void setCreditPerid(String creditPerid) {
		CreditPerid = creditPerid;
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
	 * @param limit 要设置的 limit
	 */
	public void setLimit(String limit) {
		Limit = limit;
	}




	/**
	 * @param num 要设置的 num
	 */
	public void setNum(String num) {
		Num = num;
	}




	/**
	 * @param onlineLimitDate 要设置的 onlineLimitDate
	 */
	public void setOnlineLimitDate(String onlineLimitDate) {
		OnlineLimitDate = onlineLimitDate;
	}




	/**
	 * @param txnType 要设置的 txnType
	 */
	public void setTxnType(String txnType) {
		TxnType = txnType;
	}




	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		Type = type;
	}





}
