package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 终止停息输入
 * @author CHENPAN
 *
 */
public class StopStopItrRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765184649275302776L;
	private String brwName;        //客户名称          
	private BigDecimal rcvPrn;         //贷款剩余本金      
	private BigDecimal rcvNorItrIn;    //终止停息前正常利息
	private BigDecimal rcvDftItrIn;    //终止停息前拖欠利息
	private BigDecimal rcvPnsItrIn;    //终止停息前罚息    
	private String ceasDate;       //停息日            
	private String rcvItrType;     //补计利息标识      
	private BigDecimal padUpNorItrIn;  //补计正常利息      
	private BigDecimal padUpDftItrIn;  //补计拖欠利息      
	private BigDecimal padUpPnsItrIn;  //补计罚息          
	private BigDecimal padUpCpdItrIn;  //补计复利          
	private BigDecimal totItr;         //补计利息合计      
	private String telNo;          //终止停息通知书编号
	private String dueNum;         //借据编号          
	private String conNo;          //合同编号          
	private String begDate;        //贷款起期          
	private String endDate;        //贷款止期          
	public StopStopItrRq() {
	}
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
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
	public String getCeasDate() {
		return ceasDate;
	}
	public void setCeasDate(String ceasDate) {
		this.ceasDate = ceasDate;
	}
	public String getRcvItrType() {
		return rcvItrType;
	}
	public void setRcvItrType(String rcvItrType) {
		this.rcvItrType = rcvItrType;
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
	public BigDecimal getTotItr() {
		return totItr;
	}
	public void setTotItr(BigDecimal totItr) {
		this.totItr = totItr;
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

}
