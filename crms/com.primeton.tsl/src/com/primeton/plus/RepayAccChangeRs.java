package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 还款账号变更输出
 * @author CHENPAN
 *
 */
public class RepayAccChangeRs extends SuperBosfxRs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1313097962344115666L;
	private String dueNum;      //借据编号	    
	private String payPrimAcct; //新还款账号	 	
	private String payPrimName; //新还款账户名称
	public RepayAccChangeRs() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	

}
