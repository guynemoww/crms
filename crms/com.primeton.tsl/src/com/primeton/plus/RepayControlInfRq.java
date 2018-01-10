package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 还款控制信息输入
 * @author CHENPAN
 *
 */
public class RepayControlInfRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8719717939727521974L;
	private String dueNum;             //借据编号            
	private String telNo;              //通知书编号          
	private BigDecimal padUpPentIcm;   //收取违约金金额      
	private BigDecimal padUpAmt;       //还款金额            
	private String stopPayNum;         //解止付编号          
	private String payOrder;           //还款顺序            
	private String payPrimAcct;        //还款账号            
	private String payPrimName;        //还款账户名称        
	private String prinPlanFlg;        //新下发还本/还息计划 
	private Integer prinPlanTerm;      //新还款计划表中的期数
	private String payOutItrFlg;       //归还未结计利息标志  
	private BigDecimal padUpPrn;       //实收本金金额        
	private BigDecimal padUpNorItrIn;  //正常利息金额        
	private BigDecimal padUpDftItrIn;  //拖欠利息金额        
	private BigDecimal padUpPnsItrIn;  //罚息金额            
	private BigDecimal padUpCpdItrIn;  //复利金额 
	private BigDecimal padUpAdjOtdItr;//未结计正常利息
	private BigDecimal padUpAdjOtdPns;//未结计罚息
	private BigDecimal padUpAdjOtdCpd;//未结计复利
	public RepayControlInfRq() {
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
	public BigDecimal getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(BigDecimal padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public String getStopPayNum() {
		return stopPayNum;
	}
	public void setStopPayNum(String stopPayNum) {
		this.stopPayNum = stopPayNum;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
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
	public String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	public void setPrinPlanFlg(String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	public Integer getPrinPlanTerm() {
		return prinPlanTerm;
	}
	public void setPrinPlanTerm(Integer prinPlanTerm) {
		this.prinPlanTerm = prinPlanTerm;
	}
	public String getPayOutItrFlg() {
		return payOutItrFlg;
	}
	public void setPayOutItrFlg(String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
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
	public BigDecimal getPadUpAdjOtdItr() {
		return padUpAdjOtdItr;
	}
	public void setPadUpAdjOtdItr(BigDecimal padUpAdjOtdItr) {
		this.padUpAdjOtdItr = padUpAdjOtdItr;
	}
	public BigDecimal getPadUpAdjOtdPns() {
		return padUpAdjOtdPns;
	}
	public void setPadUpAdjOtdPns(BigDecimal padUpAdjOtdPns) {
		this.padUpAdjOtdPns = padUpAdjOtdPns;
	}
	public BigDecimal getPadUpAdjOtdCpd() {
		return padUpAdjOtdCpd;
	}
	public void setPadUpAdjOtdCpd(BigDecimal padUpAdjOtdCpd) {
		this.padUpAdjOtdCpd = padUpAdjOtdCpd;
	}
	
}
