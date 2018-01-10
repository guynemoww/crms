package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 调整利息控制信息输出
 * @author CHENPAN
 *
 */
public class ChangeInterControlRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4371021590210218791L;
	private String dueNum;       //借据编号                  
	private String telNo;        //通知书编号                
	private String adjItrFlg;    //调整贷款利息标志          
	private BigDecimal rcvNorItrIn;  //表内正常利息              
	private BigDecimal rcvDftItrIn;  //表内拖欠利息              
	private BigDecimal rcvPnsItrIn;  //表内罚息                  
	private BigDecimal rcvCpdItrIn;  //表内复利                  
	private BigDecimal rcvNorItrOut; //表外正常利息              
	private BigDecimal rcvDftItrOut; //表外拖欠利息              
	private BigDecimal rcvPnsItrOut; //表外罚息                  
	private BigDecimal rcvCpdItrOut; //表外复利                  
	private BigDecimal adjOtdItr;    //调整贷款利息未结计正常利息
	private BigDecimal adjOtdPns;    //调整贷款利息未结计罚息    
	private BigDecimal adjOtdCpd;    //调整贷款利息未结计复利   
	public ChangeInterControlRs() {
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
	public String getAdjItrFlg() {
		return adjItrFlg;
	}
	public void setAdjItrFlg(String adjItrFlg) {
		this.adjItrFlg = adjItrFlg;
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
	public BigDecimal getRcvNorItrOut() {
		return rcvNorItrOut;
	}
	public void setRcvNorItrOut(BigDecimal rcvNorItrOut) {
		this.rcvNorItrOut = rcvNorItrOut;
	}
	public BigDecimal getRcvDftItrOut() {
		return rcvDftItrOut;
	}
	public void setRcvDftItrOut(BigDecimal rcvDftItrOut) {
		this.rcvDftItrOut = rcvDftItrOut;
	}
	public BigDecimal getRcvPnsItrOut() {
		return rcvPnsItrOut;
	}
	public void setRcvPnsItrOut(BigDecimal rcvPnsItrOut) {
		this.rcvPnsItrOut = rcvPnsItrOut;
	}
	public BigDecimal getRcvCpdItrOut() {
		return rcvCpdItrOut;
	}
	public void setRcvCpdItrOut(BigDecimal rcvCpdItrOut) {
		this.rcvCpdItrOut = rcvCpdItrOut;
	}
	public BigDecimal getAdjOtdItr() {
		return adjOtdItr;
	}
	public void setAdjOtdItr(BigDecimal adjOtdItr) {
		this.adjOtdItr = adjOtdItr;
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
}
