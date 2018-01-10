package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 进口保函修改---请求对象
 * @author shendl
 *
 */
public class GJS01501030000005Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5550335841384793725L;
	private String debitNo;//借据号
	private String contractNo;//合同号
	private String dealBrch;//执行机构号
	private String currency;//币种
	private String amt;//金额
	private String grantType;//保函类型
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	private String bondRate;//保证金比例
	private String bondAmt;//保证金金额
	private String matuDat;//到期日期
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getDealBrch() {
		return dealBrch;
	}
	public void setDealBrch(String dealBrch) {
		this.dealBrch = dealBrch;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getBondAcct() {
		return bondAcct;
	}
	public void setBondAcct(String bondAcct) {
		this.bondAcct = bondAcct;
	}
	public String getBondCurr() {
		return bondCurr;
	}
	public void setBondCurr(String bondCurr) {
		this.bondCurr = bondCurr;
	}
	public String getBondRate() {
		return bondRate;
	}
	public void setBondRate(String bondRate) {
		this.bondRate = bondRate;
	}
	public String getBondAmt() {
		return bondAmt;
	}
	public void setBondAmt(String bondAmt) {
		this.bondAmt = bondAmt;
	}
	public String getMatuDat() {
		return matuDat;
	}
	public void setMatuDat(String matuDat) {
		this.matuDat = matuDat;
	}
	@Override
	public String toString() {
		return "GJS01501030000005Req [debitNo=" + debitNo + ", contractNo="
				+ contractNo + ", dealBrch=" + dealBrch + ", currency="
				+ currency + ", amt=" + amt + ", grantType=" + grantType
				+ ", bondAcct=" + bondAcct + ", bondCurr=" + bondCurr
				+ ", bondRate=" + bondRate + ", bondAmt=" + bondAmt
				+ ", matuDat=" + matuDat + "]";
	}
}
