package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 贷款本息查询输入
 * @author CHENPAN
 *
 */
public class CrePayQueryRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5391634899528263275L;
	private String dueNum;//借据编号
	private BigDecimal rcvPrn;//提前还本金额
	private BigDecimal resNor;      //剩余正常本金（含当前期本金）
	private BigDecimal dftPrnBal;   //贷款拖欠本金                
	private BigDecimal rcvNorItrIn; //正常利息                    
	private BigDecimal rcvDftItrIn; //拖欠利息                    
	private BigDecimal rcvPnsItrIn; //罚息                        
	private BigDecimal rcvCpdItrIn; //复利      
	private BigDecimal adjOtdPns;   //未结计罚息                  
	private BigDecimal adjOtdCpd;   //未结计复利                  
	private BigDecimal currPrjPrn;  //当前期本金                  
	private BigDecimal currPrjItr;  //当前期利息                  
	private BigDecimal padUpAmt;    //还款金额                    
	private BigDecimal totPrnItr;   //结清金额                    
	private String devaSts;     //减值状态               
	private BigDecimal  padUpAdjOtdItr; // 未结计正常利息
	public CrePayQueryRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public BigDecimal getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(BigDecimal rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	public BigDecimal getResNor() {
		return resNor;
	}
	public void setResNor(BigDecimal resNor) {
		this.resNor = resNor;
	}
	public BigDecimal getDftPrnBal() {
		return dftPrnBal;
	}
	public void setDftPrnBal(BigDecimal dftPrnBal) {
		this.dftPrnBal = dftPrnBal;
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
	public BigDecimal getAdjOtdPns() {
		return adjOtdPns;
	}
	public void setAdjOtdPns(BigDecimal adjOtdPns) {
		this.adjOtdPns = adjOtdPns;
	}
	public BigDecimal getAdjOtdCpd() {
		return adjOtdCpd;
	}
	public void setAdjOtdCpd(BigDecimal adjOtdCpd) {
		this.adjOtdCpd = adjOtdCpd;
	}
	public BigDecimal getCurrPrjPrn() {
		return currPrjPrn;
	}
	public void setCurrPrjPrn(BigDecimal currPrjPrn) {
		this.currPrjPrn = currPrjPrn;
	}
	public BigDecimal getCurrPrjItr() {
		return currPrjItr;
	}
	public void setCurrPrjItr(BigDecimal currPrjItr) {
		this.currPrjItr = currPrjItr;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public BigDecimal getTotPrnItr() {
		return totPrnItr;
	}
	public void setTotPrnItr(BigDecimal totPrnItr) {
		this.totPrnItr = totPrnItr;
	}
	public String getDevaSts() {
		return devaSts;
	}
	public void setDevaSts(String devaSts) {
		this.devaSts = devaSts;
	}
	public BigDecimal getPadUpAdjOtdItr() {
		return padUpAdjOtdItr;
	}
	public void setPadUpAdjOtdItr(BigDecimal padUpAdjOtdItr) {
		this.padUpAdjOtdItr = padUpAdjOtdItr;
	}
}
