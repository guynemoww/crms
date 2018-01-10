package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 还款输出
 * @author CHENPAN
 *
 */
public class RepaymentRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3270371509991999092L;
	private String payOutItrFlg; //归还未结计利息标志
	private String payPrimName;   //还款账户名称
	private String payPrimAcct;   //还款账号    
	private BigDecimal padUpAmt;  //还款金额    
	private String payOrder;      //还款顺序    
	private BigDecimal rcvPrn;        //应还本金    
	private BigDecimal rcvNorItrIn;   //应还正常利息
	private BigDecimal rcvDftItrIn;   //应还拖欠利息
	private BigDecimal rcvPnsItrIn;   //应还罚息    
	private BigDecimal rcvCpdItrIn;   //应还复利    
	private BigDecimal padUpPrn;      //实还本金    
	private BigDecimal padUpNorItrIn; //实还正常利息
	private BigDecimal padUpDftItrIn; //实还拖欠利息
	private BigDecimal padUpPnsItrIn; //实还罚息    
	private BigDecimal padUpCpdItrIn; //实还复利    
	private BigDecimal padUpPentIcm;  //违约金      
	private String dueNum;        //借据编号    
	private String telNo;         //通知书编号  
	private String conNo;         //合同编号    
	private String begDate;       //贷款起期    
	private String endDate;       //贷款止期    
	private String brwName;       //客户名称    
	private String sts;           //贷款状态   
	public RepaymentRs() {
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
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
	public BigDecimal getRcvDftItrIn() {
		return rcvDftItrIn;
	}
	public void setRcvDftItrIn(BigDecimal rcvDftItrIn) {
		this.rcvDftItrIn = rcvDftItrIn;
	}
	public BigDecimal getRcvPnsItrIn() {
		return rcvPnsItrIn;
	}
	public void setRcvPnsItrIn(BigDecimal rcvPnsItrIn) {
		this.rcvPnsItrIn = rcvPnsItrIn;
	}
	public BigDecimal getRcvCpdItrIn() {
		return rcvCpdItrIn;
	}
	public void setRcvCpdItrIn(BigDecimal rcvCpdItrIn) {
		this.rcvCpdItrIn = rcvCpdItrIn;
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
	public BigDecimal getPadUpDftItrIn() {
		return padUpDftItrIn;
	}
	public void setPadUpDftItrIn(BigDecimal padUpDftItrIn) {
		this.padUpDftItrIn = padUpDftItrIn;
	}
	public BigDecimal getPadUpPnsItrIn() {
		return padUpPnsItrIn;
	}
	public void setPadUpPnsItrIn(BigDecimal padUpPnsItrIn) {
		this.padUpPnsItrIn = padUpPnsItrIn;
	}
	public BigDecimal getPadUpCpdItrIn() {
		return padUpCpdItrIn;
	}
	public void setPadUpCpdItrIn(BigDecimal padUpCpdItrIn) {
		this.padUpCpdItrIn = padUpCpdItrIn;
	}
	public BigDecimal getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(BigDecimal padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
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
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public String getPayOutItrFlg() {
		return payOutItrFlg;
	}
	public void setPayOutItrFlg(String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
	}
	
}
