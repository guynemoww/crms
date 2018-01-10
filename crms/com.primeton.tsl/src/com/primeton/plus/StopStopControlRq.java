package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 终止停息控制信息输入
 * @author CHENPAN
 *
 */
public class StopStopControlRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -394842523839806525L;
	private String dueNum;       //借据编号          
	private String telNo;        //终止停息通知书编号
	private String rcvItrYype;   //补计利息标志      
	private BigDecimal rcvNorItrIn;  //补计正常利息金额  
	private BigDecimal rcvDftItrIn;  //补计拖欠利息金额  
	private BigDecimal rcvPnsItrIn;  //补计罚息          
	private BigDecimal rcvCpdItrIn;  //补记复利    
	public StopStopControlRq() {
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
	public String getRcvItrYype() {
		return rcvItrYype;
	}
	public void setRcvItrYype(String rcvItrYype) {
		this.rcvItrYype = rcvItrYype;
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
	
}
