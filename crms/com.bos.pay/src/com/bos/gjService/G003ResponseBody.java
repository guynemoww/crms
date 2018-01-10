package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷本息查询接口---响应对象体
 *
 */
public class G003ResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75715131798334625L;
	private BigDecimal resNor;        //剩余正常本金（含当前期本金）
	private BigDecimal dftPrnBal;     //贷款拖欠本金                
	private BigDecimal rcvNorItrIn;  //正常利息                    
	private BigDecimal rcvDftItrIn;  //拖欠利息                    
	private BigDecimal rcvPnsItrIn;   //罚息                        
	private BigDecimal adjOtdPns;     //未结计罚息                  
	private BigDecimal currPrjPrn;    //当前期本金                  
	private BigDecimal currPrjItr;    //当前期利息                  
	private BigDecimal padUpAmt;      //还款金额                    
	private BigDecimal totPrnItr;     //结清金额                    
	private BigDecimal devaSts;       //减值状态     
	private BigDecimal rcvCpdItrIn;   //复利
	private BigDecimal adjOtdCpd;     //未结计复利
	private String fieldRs1;//备用字段1
	private String fieldRs2;//备用字段2
	private String fieldRs3;//备用字段3
	private String fieldRs4;//备用字段4
	private String fieldRs5;//备用字段5
	public G003ResponseBody() {
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
	public BigDecimal getAdjOtdPns() {
		return adjOtdPns;
	}
	public void setAdjOtdPns(BigDecimal adjOtdPns) {
		this.adjOtdPns = adjOtdPns;
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
	
	public BigDecimal getDevaSts() {
		return devaSts;
	}
	public void setDevaSts(BigDecimal devaSts) {
		this.devaSts = devaSts;
	}
	public String getFieldRs1() {
		return fieldRs1;
	}
	public void setFieldRs1(String fieldRs1) {
		this.fieldRs1 = fieldRs1;
	}
	public String getFieldRs2() {
		return fieldRs2;
	}
	public void setFieldRs2(String fieldRs2) {
		this.fieldRs2 = fieldRs2;
	}
	public String getFieldRs3() {
		return fieldRs3;
	}
	public void setFieldRs3(String fieldRs3) {
		this.fieldRs3 = fieldRs3;
	}
	public String getFieldRs4() {
		return fieldRs4;
	}
	public void setFieldRs4(String fieldRs4) {
		this.fieldRs4 = fieldRs4;
	}
	public String getFieldRs5() {
		return fieldRs5;
	}
	public void setFieldRs5(String fieldRs5) {
		this.fieldRs5 = fieldRs5;
	}
	public BigDecimal getRcvCpdItrIn() {
		return rcvCpdItrIn;
	}
	public void setRcvCpdItrIn(BigDecimal rcvCpdItrIn) {
		this.rcvCpdItrIn = rcvCpdItrIn;
	}
	public BigDecimal getAdjOtdCpd() {
		return adjOtdCpd;
	}
	public void setAdjOtdCpd(BigDecimal adjOtdCpd) {
		this.adjOtdCpd = adjOtdCpd;
	}
	
}
