package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 核销收回输出 
 * @author CHENPAN
 *
 */
public class VerificationBackRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2129353909305002241L;
	private String telNo;          //核销收回通知书编号    
	private String dueNum;         //借据编号              
	private String brwName;        //客户名称              
	private String begDate;        //贷款起期              
	private String endDate;        //贷款止期              
	private BigDecimal rcvPrn;         //应还本金              
	private BigDecimal rcvNorItrIn;    //应还表内核销利息      
	private BigDecimal rcvNorItrOut;   //应还表外核销利息      
	private BigDecimal rcvOtdItr;      //应还核销本金产生的利息
	private BigDecimal padUpPrn;       //实还本金              
	private BigDecimal padUpNorItrIn;  //实还表内核销利息      
	private BigDecimal padUpNorItrOut; //实还表外核销利息      
	private BigDecimal padUpOtdItr;    //实还核销本金产生的利息
	private BigDecimal padUpAmt;       //核销收回金额          
	private String payPrimAcct;    //还款账户名称          
	private String payPrimName;    //还款账户 
	public VerificationBackRs() {
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public BigDecimal getPadUpPrn() {
		return padUpPrn;
	}
	public void setPadUpPrn(BigDecimal padUpPrn) {
		this.padUpPrn = padUpPrn;
	}
	public BigDecimal getPadUpNorItrIn() {
		return padUpNorItrIn;
	}
	public void setPadUpNorItrIn(BigDecimal padUpNorItrIn) {
		this.padUpNorItrIn = padUpNorItrIn;
	}
	public BigDecimal getPadUpNorItrOut() {
		return padUpNorItrOut;
	}
	public void setPadUpNorItrOut(BigDecimal padUpNorItrOut) {
		this.padUpNorItrOut = padUpNorItrOut;
	}
	public BigDecimal getPadUpOtdItr() {
		return padUpOtdItr;
	}
	public void setPadUpOtdItr(BigDecimal padUpOtdItr) {
		this.padUpOtdItr = padUpOtdItr;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	
}
