package com.bos.inter.CallBmsInterface;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 同业客户额度信息
 * @author shendl
 *
 */
public class CRMSCrdQueryTyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7494064986890785788L;
	public String limitId;//额度ID
	public BigDecimal crdTotalAmount;//授信总金额
	public BigDecimal crdUsedAmount;//授信已用金额
	public BigDecimal crdAviAmount;//授信可用金额
	public String getLimitId() {
		return limitId;
	}
	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}
	public BigDecimal getCrdTotalAmount() {
		return crdTotalAmount;
	}
	public void setCrdTotalAmount(BigDecimal crdTotalAmount) {
		this.crdTotalAmount = crdTotalAmount;
	}
	public BigDecimal getCrdUsedAmount() {
		return crdUsedAmount;
	}
	public void setCrdUsedAmount(BigDecimal crdUsedAmount) {
		this.crdUsedAmount = crdUsedAmount;
	}
	public BigDecimal getCrdAviAmount() {
		return crdAviAmount;
	}
	public void setCrdAviAmount(BigDecimal crdAviAmount) {
		this.crdAviAmount = crdAviAmount;
	}
	
	
}
