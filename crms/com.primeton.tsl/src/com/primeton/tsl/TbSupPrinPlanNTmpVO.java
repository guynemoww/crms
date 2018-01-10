package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class TbSupPrinPlanNTmpVO implements Serializable{
	
	private String rcvDate;//登记日期    
	private String legPerCod;//客户名称
	private String dueNumTmp;//临时借据号
	private int currPeri;//期次号    
	private String begDate;//本期起始日期
	private String endDate;// 本期终止日期
	private BigDecimal rcvPrn;//本期应还本金额
	private String prnProcFlg;//本金处理标志
	
	public String getRcvDate() {
		return rcvDate;
	}

	@XmlElement(name = "RcvDate")
	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	public String getLegPerCod() {
		return legPerCod;
	}

	@XmlElement(name = "LegPerCod")
	public void setLegPerCod(String legPerCod) {
		this.legPerCod = legPerCod;
	}

	public String getDueNumTmp() {
		return dueNumTmp;
	}

	@XmlElement(name = "DueNumTmp")
	public void setDueNumTmp(String dueNumTmp) {
		this.dueNumTmp = dueNumTmp;
	}

	public int getCurrPeri() {
		return currPeri;
	}

	@XmlElement(name = "CurrPeri")
	public void setCurrPeri(int currPeri) {
		this.currPeri = currPeri;
	}

	public String getBegDate() {
		return begDate;
	}

	@XmlElement(name = "BegDate")
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@XmlElement(name = "EndDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public BigDecimal getRcvPrn(){
		return rcvPrn;
	}
	
	@XmlElement(name = "RcvPrn")
	public void setRcvPrn(BigDecimal rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	
	public String getPrnProcFlg(){
		return prnProcFlg;
	}
	
	@XmlElement(name = "PrnProcFlg")
	public void setPrnProcFlg(String prnProcFlg){
		this.prnProcFlg = prnProcFlg;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "PayOutVo [rcvDate=" + this.getRcvDate() + "legPerCod=" + this.getLegPerCod() 
				+ "dueNumTmp=" + this.getDueNumTmp() + "currPeri=" + this.getCurrPeri()
				+ ", begDate=" + begDate + ", endDate=" + this.getEndDate() + ", rcvPrn=" + this.getRcvPrn()
				+ ", prnProcFlg=" + this.getPrnProcFlg() + "]";
	}
}
