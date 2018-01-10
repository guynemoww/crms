package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 抵债资产冲销贷款信息输入
 * @author CHENPAN
 *
 */
public class AssetOffRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4897327577475461631L;
	private String telNo;//抵债资产冲销贷款通知书编号
	private String payPrimAcct;//冲销贷款账号
	private String accJson;//json字符串
	public AssetOffRq() {
		super();
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public String getAccJson() {
		return accJson;
	}
	public void setAccJson(String accJson) {
		this.accJson = accJson;
	}
	
}
