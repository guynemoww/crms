package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 核销收回控制信息输出
 * @author CHENAPN
 *
 */
public class PayVerifBackControlRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472431493447214362L;
	private String dueNum;       //借据编号   
	private String telNo;        //通知书编号   
	private BigDecimal padUpAmt; //还款总金额  
	private String payPrimAcct;  //还款账号    
	private String payPrimName;  //还款账户名称
	public PayVerifBackControlRs() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
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
