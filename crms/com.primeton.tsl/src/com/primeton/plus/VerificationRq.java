package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 核销输入
 * @author CHENPAN
 *
 */
public class VerificationRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7869953693401460832L;
	private String telNo;            //核销通知书编号
	private String dueNum;           //借据编号      
	private String brwName;          //客户名称      
	private String begDate;          //贷款起期      
	private String endDate;          //贷款止期      
	private BigDecimal rcvPrn;       //本金          
	private BigDecimal rcvNorItrIn;  //正常利息      
	private BigDecimal rcvDftItrIn;  //拖欠利息      
	private BigDecimal rcvPnsItrIn;  //罚息          
	private BigDecimal rcvCpdItrIn;  //复利          
	private BigDecimal totPrnItr;    //核销金额合计 
	private String accJson;//json字符串
	
	public VerificationRq() {
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
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
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
	public String getAccJson() {
		return accJson;
	}
	public void setAccJson(String accJson) {
		this.accJson = accJson;
	}
	
	
}
