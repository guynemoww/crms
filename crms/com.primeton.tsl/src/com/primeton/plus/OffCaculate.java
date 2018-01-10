package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 结清试算
 * @author CHENPAN
 *
 */
public class OffCaculate extends SuperBosfxRq  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846911088592581854L;
	private String dueNum;       //借据编号
	private String brwName;      //客户名称     
	private BigDecimal norItrRate;   //正常利率（%）
	private BigDecimal delItrRate;   //罚息利率（%）
	private String begDate;      //起始日期     
	private String endDate;      //终止日期     
	private BigDecimal rcvPrn;       //本金         
	private BigDecimal rcvNorItrIn;  //正常利息     
	private BigDecimal rcvDftItrIn;  //拖欠利息     
	private BigDecimal rcvPnsItrIn;  //罚息         
	private BigDecimal rcvCpdItrIn;  // 复利        
	private BigDecimal totPrnItr;    //合计金额  
	public OffCaculate() {
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
	public BigDecimal getTotPrnItr() {
		return totPrnItr;
	}
	public void setTotPrnItr(BigDecimal totPrnItr) {
		this.totPrnItr = totPrnItr;
	}
	
}
