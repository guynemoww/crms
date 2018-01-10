package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 展期申请控制信息输入
 * @author CHENPAN
 *
 */
public class ExtendTimeAppRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 906709901151200020L;
	private String dueNum;            //借据编号       
	private String busCod;            //贷款业务别    
	private String exdBegDate;        //展期起始日期  
	private String exdEndDate;        //展期到期日期  
	private BigDecimal norItrRate;    //展期后正常利率
	private BigDecimal delItrRate;    //展期后罚息利率
	private BigDecimal cpdItrRate;    //展期后复利利率
	private String discEndDate;       //贴息止期      
	private String prinPlanFlg;       //新还本计划  
	public ExtendTimeAppRq() {
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
	
}
