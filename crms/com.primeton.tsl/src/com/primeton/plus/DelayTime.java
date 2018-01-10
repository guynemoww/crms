package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;

/**
 * 逾期展期
 * @author CHENPAN
 *
 */
public class DelayTime   extends SuperBosfxRq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4398136072134954034L;
	private String dueNum;          //借据编号      
	private String busCod;          //贷款业务别    
	private String exdBegDate;      //展期起始日期  
	private String exdEndDate;      //展期到期日期  
	private BigDecimal norItrRate;      //展期后正常利率
	private BigDecimal delItrRate;      //展期后罚息利率
	private BigDecimal cpdItrRate;      //展期后复利利率
	private String discEndDate;     //贴息止期      
	private String prinPlanFlg;     //新还本计划    
	private String payItrPlanFlg;   //新还息计划    
	public DelayTime() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getBusCod() {
		return busCod;
	}
	public void setBusCod(String busCod) {
		this.busCod = busCod;
	}
	public String getExdBegDate() {
		return exdBegDate;
	}
	public void setExdBegDate(String exdBegDate) {
		this.exdBegDate = exdBegDate;
	}
	public String getExdEndDate() {
		return exdEndDate;
	}
	public void setExdEndDate(String exdEndDate) {
		this.exdEndDate = exdEndDate;
	}
	public BigDecimal getNorItrRate() {
		return norItrRate;
	}
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}
	public BigDecimal getDelItrRate() {
		return delItrRate;
	}
	public void setDelItrRate(BigDecimal delItrRate) {
		this.delItrRate = delItrRate;
	}
	public BigDecimal getCpdItrRate() {
		return cpdItrRate;
	}
	public void setCpdItrRate(BigDecimal cpdItrRate) {
		this.cpdItrRate = cpdItrRate;
	}
	public String getDiscEndDate() {
		return discEndDate;
	}
	public void setDiscEndDate(String discEndDate) {
		this.discEndDate = discEndDate;
	}
	public String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	public void setPrinPlanFlg(String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	public String getPayItrPlanFlg() {
		return payItrPlanFlg;
	}
	public void setPayItrPlanFlg(String payItrPlanFlg) {
		this.payItrPlanFlg = payItrPlanFlg;
	}

}
