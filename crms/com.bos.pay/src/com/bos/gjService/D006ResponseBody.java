package com.bos.gjService;

import java.io.Serializable;

public class D006ResponseBody implements Serializable{

	private static final long serialVersionUID = -1165938434455144624L;
	private String rcvPrn;//提前还本金额
	private String resNor;      //剩余正常本金（含当前期本金）
	private String dftPrnBal;   //贷款拖欠本金                
	private String rcvNorItrIn; //正常利息                    
	private String rcvDftItrIn; //拖欠利息                    
	private String rcvPnsItrIn; //罚息                        
	private String rcvCpdItrIn; //复利                        
	private String adjOtdPns;   //未结计罚息                  
	private String adjOtdCpd;   //未结计复利                  
	private String currPrjPrn;  //当前期本金                  
	private String currPrjItr;  //当前期利息                  
	private String padUpAmt;    //还款金额                    
	private String totPrnItr;   //结清金额                    
	private String devaSts;     //减值状态               
	public D006ResponseBody() {
	}
	public String getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(String rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	public String getResNor() {
		return resNor;
	}
	public void setResNor(String resNor) {
		this.resNor = resNor;
	}
	public String getDftPrnBal() {
		return dftPrnBal;
	}
	public void setDftPrnBal(String dftPrnBal) {
		this.dftPrnBal = dftPrnBal;
	}
	public String getRcvNorItrIn() {
		return rcvNorItrIn;
	}
	public void setRcvNorItrIn(String rcvNorItrIn) {
		this.rcvNorItrIn = rcvNorItrIn;
	}
	public String getRcvDftItrIn() {
		return rcvDftItrIn;
	}
	public void setRcvDftItrIn(String rcvDftItrIn) {
		this.rcvDftItrIn = rcvDftItrIn;
	}
	public String getRcvPnsItrIn() {
		return rcvPnsItrIn;
	}
	public void setRcvPnsItrIn(String rcvPnsItrIn) {
		this.rcvPnsItrIn = rcvPnsItrIn;
	}
	public String getRcvCpdItrIn() {
		return rcvCpdItrIn;
	}
	public void setRcvCpdItrIn(String rcvCpdItrIn) {
		this.rcvCpdItrIn = rcvCpdItrIn;
	}
	public String getAdjOtdPns() {
		return adjOtdPns;
	}
	public void setAdjOtdPns(String adjOtdPns) {
		this.adjOtdPns = adjOtdPns;
	}
	public String getAdjOtdCpd() {
		return adjOtdCpd;
	}
	public void setAdjOtdCpd(String adjOtdCpd) {
		this.adjOtdCpd = adjOtdCpd;
	}
	public String getCurrPrjPrn() {
		return currPrjPrn;
	}
	public void setCurrPrjPrn(String currPrjPrn) {
		this.currPrjPrn = currPrjPrn;
	}
	public String getCurrPrjItr() {
		return currPrjItr;
	}
	public void setCurrPrjItr(String currPrjItr) {
		this.currPrjItr = currPrjItr;
	}
	public String getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(String padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public String getTotPrnItr() {
		return totPrnItr;
	}
	public void setTotPrnItr(String totPrnItr) {
		this.totPrnItr = totPrnItr;
	}
	public String getDevaSts() {
		return devaSts;
	}
	public void setDevaSts(String devaSts) {
		this.devaSts = devaSts;
	}
}
