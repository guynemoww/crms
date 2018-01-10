package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 核销收回控制信息输 入
 * @author CHENAPN
 *
 */
public class PayVerifBackControlRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7528001532181103647L;
	private String dueNum;       //借据编号   
	private String telNo;        //通知书编号   
	private BigDecimal padUpAmt; //还款总金额  
	private String payPrimAcct;  //还款账号    
	private String payPrimName;  //还款账户名称
	private BigDecimal rcvPrn;       //核销本金
	private BigDecimal rcvNorItrIn;  //核销表内利息
	private BigDecimal rcvNorItrOut; //核销表外利息
	private BigDecimal rcvOtdItr;    //核销未结计利息
	public PayVerifBackControlRq() {
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
	public BigDecimal getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(BigDecimal rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	public BigDecimal getRcvNorItrIn() {
		return rcvNorItrIn;
	}
	public void setRcvNorItrIn(BigDecimal rcvNorItrIn) {
		this.rcvNorItrIn = rcvNorItrIn;
	}
	public BigDecimal getRcvNorItrOut() {
		return rcvNorItrOut;
	}
	public void setRcvNorItrOut(BigDecimal rcvNorItrOut) {
		this.rcvNorItrOut = rcvNorItrOut;
	}
	public BigDecimal getRcvOtdItr() {
		return rcvOtdItr;
	}
	public void setRcvOtdItr(BigDecimal rcvOtdItr) {
		this.rcvOtdItr = rcvOtdItr;
	}
	
}
