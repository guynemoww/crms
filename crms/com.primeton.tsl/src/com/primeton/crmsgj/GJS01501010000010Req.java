package com.primeton.crmsgj;

import java.io.Serializable;
/**
 * 牌价查询接口---请求对象
 * @author shendl
 *
 */
public class GJS01501010000010Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5636944125590147523L;
	private String transDate;//交易日期
	private String currency;//币种
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "GJS01501030000010Req [transDate=" + transDate + ", currency="
				+ currency + "]";
	}
}
