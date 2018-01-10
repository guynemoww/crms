package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 放款撤销接口---请求对象
 * @author shendl
 *
 */
public class GJS01501070000007Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503153632594024500L;
	private String debitNo;//借据号
	private String extenAgrNo;//展期协议号
	private String knotTradeNo;//国结交易号
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getExtenAgrNo() {
		return extenAgrNo;
	}
	public void setExtenAgrNo(String extenAgrNo) {
		this.extenAgrNo = extenAgrNo;
	}
	public String getKnotTradeNo() {
		return knotTradeNo;
	}
	public void setKnotTradeNo(String knotTradeNo) {
		this.knotTradeNo = knotTradeNo;
	}
	@Override
	public String toString() {
		return "GJS01501030000007Req [debitNo=" + debitNo + ", extenAgrNo="
				+ extenAgrNo + ", knotTradeNo=" + knotTradeNo + "]";
	}
	
	
}
