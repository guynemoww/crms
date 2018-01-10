package com.primeton.crmsgj;

import java.io.Serializable;
/**
 * 表内融资业务放款交易---请求对象
 * @author shendl
 *
 */
public class GJS01501030000001Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7351095210986748497L;
	private String paySeqnNo;//出账流水号
	private String debitNo;//借据号
	private String agreeSeqNo;//合同流水号
	private String prdCode;//产品代码
	private String proSubTp;//产品子类型
	private String busiCode;//业务编号
	private String acctBrch;//入账机构
	private String custNo;//客户号
	private String debAcct;//贷款账号
	private String contractNo;//合同编号
	private String currency;//借据币种
	private String debAmt;//借据金额
	private String dateOfValue;//起息日期
	private String matuDat;//到期日期
	private String dealRate;//执行利率
	private String overRate;//逾期利率
	private String agreeAmt;//合同金额
	public String getPaySeqnNo() {
		return paySeqnNo;
	}
	public void setPaySeqnNo(String paySeqnNo) {
		this.paySeqnNo = paySeqnNo;
	}
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getAgreeSeqNo() {
		return agreeSeqNo;
	}
	public void setAgreeSeqNo(String agreeSeqNo) {
		this.agreeSeqNo = agreeSeqNo;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getProSubTp() {
		return proSubTp;
	}
	public void setProSubTp(String proSubTp) {
		this.proSubTp = proSubTp;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getAcctBrch() {
		return acctBrch;
	}
	public void setAcctBrch(String acctBrch) {
		this.acctBrch = acctBrch;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getDebAcct() {
		return debAcct;
	}
	public void setDebAcct(String debAcct) {
		this.debAcct = debAcct;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDebAmt() {
		return debAmt;
	}
	public void setDebAmt(String debAmt) {
		this.debAmt = debAmt;
	}
	public String getDateOfValue() {
		return dateOfValue;
	}
	public void setDateOfValue(String dateOfValue) {
		this.dateOfValue = dateOfValue;
	}
	public String getMatuDat() {
		return matuDat;
	}
	public void setMatuDat(String matuDat) {
		this.matuDat = matuDat;
	}
	public String getDealRate() {
		return dealRate;
	}
	public void setDealRate(String dealRate) {
		this.dealRate = dealRate;
	}
	public String getOverRate() {
		return overRate;
	}
	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}
	public String getAgreeAmt() {
		return agreeAmt;
	}
	public void setAgreeAmt(String agreeAmt) {
		this.agreeAmt = agreeAmt;
	}
	@Override
	public String toString() {
		return "GJS01501030000001Req [paySeqnNo=" + paySeqnNo + ", debitNo="
				+ debitNo + ", agreeSeqNo=" + agreeSeqNo + ", prdCode="
				+ prdCode + ", proSubTp=" + proSubTp + ", busiCode=" + busiCode
				+ ", acctBrch=" + acctBrch + ", custNo=" + custNo
				+ ", debAcct=" + debAcct + ", contractNo=" + contractNo
				+ ", currency=" + currency + ", debAmt=" + debAmt
				+ ", dateOfValue=" + dateOfValue + ", matuDat=" + matuDat
				+ ", dealRate=" + dealRate + ", overRate=" + overRate
				+ ", agreeAmt=" + agreeAmt + "]";
	}

}
