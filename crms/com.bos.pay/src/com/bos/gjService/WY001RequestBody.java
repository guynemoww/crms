package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 个贷服务接口请求对象体
 * @author lenovo
 *
 */
public class WY001RequestBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7438299211259901029L;
	private String ecifPartyNum;//ECIF客户号
	private String partyName;//客户名称
	private String orgNum;//经办机构
	private String userNum;//经办用户
	private String bizType;//业务性质
	private String applyDate;//申请日期
	private String bizHappenType;//业务发生性质
	private String applyModeType;//申报模式
	private String productType;//业务品种
	private String guarantyType;//担保方式
	private BigDecimal detailAmt;//申请金额
	private BigDecimal applyXwTerm;//申请期限
	private String currencyCd;//币种
	private String repaymentType;//还款方式
	private String payment;//还款方式
	private String loanUse;//贷款用途
	public String getEcifPartyNum() {
		return ecifPartyNum;
	}
	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}
	public String getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBizHappenType() {
		return bizHappenType;
	}
	public void setBizHappenType(String bizHappenType) {
		this.bizHappenType = bizHappenType;
	}
	public String getApplyModeType() {
		return applyModeType;
	}
	public void setApplyModeType(String applyModeType) {
		this.applyModeType = applyModeType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getGuarantyType() {
		return guarantyType;
	}
	public void setGuarantyType(String guarantyType) {
		this.guarantyType = guarantyType;
	}
	public BigDecimal getDetailAmt() {
		return detailAmt;
	}
	public void setDetailAmt(BigDecimal detailAmt) {
		this.detailAmt = detailAmt;
	}
	public BigDecimal getApplyXwTerm() {
		return applyXwTerm;
	}
	public void setApplyXwTerm(BigDecimal applyXwTerm) {
		this.applyXwTerm = applyXwTerm;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getLoanUse() {
		return loanUse;
	}
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}
