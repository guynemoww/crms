package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 调整利息输入
 * @author CHENPAN
 *
 */
public class ChangeIntrRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -797525455026544489L;
	private String telNo;          //调整贷款利息通知书编号
	private String dueNum;         //借据编号              
	private String brwName;        //客户名称              
	private BigDecimal rcvNorItrIn;    //正常利息              
	private BigDecimal rcvDftItrIn;    //拖欠利息              
	private BigDecimal rcvPnsItrIn;    //罚息                  
	private BigDecimal rcvCpdItrIn;    //复利                  
	private BigDecimal adjOtdItr;      //未结计正常利息        
	private BigDecimal adjOtdPns;      //未结计罚息            
	private BigDecimal adjOtdCpd;      //未结计复利            
	private BigDecimal padUpNorItrIn;  //调整正常利息          
	private BigDecimal padUpDftItrIn;  //调整拖欠利息          
	private BigDecimal padUpPnsItrIn;  //调整罚息              
	private BigDecimal padUpCpdItrIn;  //调整复利              
	private BigDecimal padUpAdjOtdItr; //调整未结计正常利息    
	private BigDecimal padUpAdjOtdPns; //调整未结计罚息        
	private BigDecimal padUpAdjOtdCpd; //调整未结计复利        
	private String adjItrFlg;      //调整类型       
	public ChangeIntrRq() {
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
	public BigDecimal getPadUpAdjOtdItr() {
		return padUpAdjOtdItr;
	}
	public void setPadUpAdjOtdItr(BigDecimal padUpAdjOtdItr) {
		this.padUpAdjOtdItr = padUpAdjOtdItr;
	}
	public BigDecimal getPadUpAdjOtdPns() {
		return padUpAdjOtdPns;
	}
	public void setPadUpAdjOtdPns(BigDecimal padUpAdjOtdPns) {
		this.padUpAdjOtdPns = padUpAdjOtdPns;
	}
	public BigDecimal getPadUpAdjOtdCpd() {
		return padUpAdjOtdCpd;
	}
	public void setPadUpAdjOtdCpd(BigDecimal padUpAdjOtdCpd) {
		this.padUpAdjOtdCpd = padUpAdjOtdCpd;
	}
	public String getAdjItrFlg() {
		return adjItrFlg;
	}
	public void setAdjItrFlg(String adjItrFlg) {
		this.adjItrFlg = adjItrFlg;
	}
	
}
