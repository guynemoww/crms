package com.primeton.crmsgj;

import java.io.Serializable;
/**
 * 进口保函开立---请求对象
 * @author shendl
 *
 */
public class GJS01501110000004Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 748483235627957994L;
	private String debitNo;//借据号
	private String contractNo;//合同号
	private String dealBrch;//执行机构号
	private String currency;//币种
	private String amt;//金额
	private String grantType;//保函类型
	private String opernDate;//开立日期
	private String matuDat;//到期日期
	private String ioaner;//贷款申请人
	private String ioanBene;//贷款受益人
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	private String bondRate;//保证金比例
	private String bondAmt;//保证金金额
	private String tradeAgreeNo;//贸易合同号
	private String tradeAgreeAmt;//贸易合同金额
	private String certMatuType;//保函信用证付款期限
	private String forwDay;//远期天数
	private String matuType;//期限类型
	
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
	public String getOpernDate() {
		return opernDate;
	}
	public void setOpernDate(String opernDate) {
		this.opernDate = opernDate;
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
	public String getIoanBene() {
		return ioanBene;
	}
	public void setIoanBene(String ioanBene) {
		this.ioanBene = ioanBene;
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
	public String getTradeAgreeNo() {
		return tradeAgreeNo;
	}
	public void setTradeAgreeNo(String tradeAgreeNo) {
		this.tradeAgreeNo = tradeAgreeNo;
	}
	public String getTradeAgreeAmt() {
		return tradeAgreeAmt;
	}
	public void setTradeAgreeAmt(String tradeAgreeAmt) {
		this.tradeAgreeAmt = tradeAgreeAmt;
	}
	
	
	public String getCertMatuType() {
		return certMatuType;
	}
	public void setCertMatuType(String certMatuType) {
		this.certMatuType = certMatuType;
	}
	public String getForwDay() {
		return forwDay;
	}
	public void setForwDay(String forwDay) {
		this.forwDay = forwDay;
	}
	public String getMatuType() {
		return matuType;
	}
	public void setMatuType(String matuType) {
		this.matuType = matuType;
	}
	@Override
	public String toString() {
		return "GJS01501110000004Req [debitNo=" + debitNo + ", contractNo="
				+ contractNo + ", dealBrch=" + dealBrch + ", currency="
				+ currency + ", amt=" + amt + ", grantType=" + grantType
				+ ", opernDate=" + opernDate + ", matuDat=" + matuDat
				+ ", ioaner=" + ioaner + ", ioanBene=" + ioanBene
				+ ", bondAcct=" + bondAcct + ", bondCurr=" + bondCurr
				+ ", bondRate=" + bondRate + ", bondAmt=" + bondAmt
				+ ", tradeAgreeNo=" + tradeAgreeNo + ", tradeAgreeAmt="
				+ tradeAgreeAmt + ", certMatuType=" + certMatuType
				+ ", forwDay=" + forwDay + ", matuType=" + matuType + "]";
	}
	
}
