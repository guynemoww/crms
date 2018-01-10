package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 还款账号变更输入
 * @author CHENPAN
 *
 */
public class RepayAccChangeRq extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1726814049563877173L;
	private String dueNum;      //借据编号	    
	private String payPrimAcct; //新还款账号	 	
	private String payPrimName; //新还款账户名称
	public RepayAccChangeRq() {
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
