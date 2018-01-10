package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 融资展期接口---请求对象
 * @author shendl
 *
 */
public class GJS01501030000009Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3219929601618065852L;
	private String debitNo;//借据号
	private String extendDate;//展期日期
	private String extMatureDate;//展期后到期日
	private String extInterestRate;//展期后利率
	private String oveInterestRate;//新的逾期利率
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getExtendDate() {
		return extendDate;
	}
	public void setExtendDate(String extendDate) {
		this.extendDate = extendDate;
	}
	public String getExtMatureDate() {
		return extMatureDate;
	}
	public void setExtMatureDate(String extMatureDate) {
		this.extMatureDate = extMatureDate;
	}
	public String getExtInterestRate() {
		return extInterestRate;
	}
	public void setExtInterestRate(String extInterestRate) {
		this.extInterestRate = extInterestRate;
	}
	public String getOveInterestRate() {
		return oveInterestRate;
	}
	public void setOveInterestRate(String oveInterestRate) {
		this.oveInterestRate = oveInterestRate;
	}
	
	
}
