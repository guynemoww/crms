package com.primeton.crmsgj;

import java.io.Serializable;
/**
 * 提货担保接口---请求对象
 * @author shendl
 *
 */
public class GJS01501030000006Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3295933605735128441L;
	private String debitNo;//借据号
	private String contractNo;//合同号
	private String dealBrch;//执行机构号
	private String currency;//币种
	private String amt;//金额
	private String billNo;//提单号码
	private String credNo;//信用证号码
	private String billDate;//提单日期
	private String matuDat;//到期日期
	private String ioaner;//贷款申请人
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	private String bondRate;//保证金比例
	private String bondAmt;//保证金金额
	
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getCredNo() {
		return credNo;
	}
	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getMatuDat() {
		return matuDat;
	}
	public void setMatuDat(String matuDat) {
		this.matuDat = matuDat;
	}
	public String getIoaner() {
		return ioaner;
	}
	public void setIoaner(String ioaner) {
		this.ioaner = ioaner;
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
	@Override
	public String toString() {
		return "GJS01501030000006Req [debitNo=" + debitNo + ", contractNo="
				+ contractNo + ", dealBrch=" + dealBrch + ", currency="
				+ currency + ", amt=" + amt + ", billNo=" + billNo
				+ ", credNo=" + credNo + ", billDate=" + billDate
				+ ", matuDat=" + matuDat + ", ioaner=" + ioaner + ", bondAcct="
				+ bondAcct + ", bondCurr=" + bondCurr + ", bondRate="
				+ bondRate + ", bondAmt=" + bondAmt + "]";
	}
}
