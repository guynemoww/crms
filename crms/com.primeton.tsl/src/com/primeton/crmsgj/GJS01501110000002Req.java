package com.primeton.crmsgj;

import java.io.Serializable;
/**
 * 进口信用证开证接口---请求对象
 * @author shendl
 *
 */
public class GJS01501110000002Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6358084763289886931L;
	private String debitNo;//借据号
	private String contractNo;//合同编号
	private String dealBrch;//执行机构号
	private String currency;//开证币种
	private String issAmt;//开证金额
	private String shipPro;//溢装比例
	private String stornPro;//短装比例
	private String letOfCreDate;//信用证付款期限
	private String forwDay;//远期天数
	private String matuType;//期限类型
	private String matuDat;//到期日期
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	private String bondRate;//保证金比例
	private String bondAmt;//保证金金额
	private String tradeAgreeNo;//贸易合同号
	private String ioaner;//贷款申请人
	private String tradeAgreeAmt;//贸易合同金额
	private String stateVout;//国内证/国际证
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
	public String getIssAmt() {
		return issAmt;
	}
	public void setIssAmt(String issAmt) {
		this.issAmt = issAmt;
	}
	public String getShipPro() {
		return shipPro;
	}
	public void setShipPro(String shipPro) {
		this.shipPro = shipPro;
	}
	public String getStornPro() {
		return stornPro;
	}
	public void setStornPro(String stornPro) {
		this.stornPro = stornPro;
	}
	public String getLetOfCreDate() {
		return letOfCreDate;
	}
	public void setLetOfCreDate(String letOfCreDate) {
		this.letOfCreDate = letOfCreDate;
	}
	public String getForwDay() {
		return forwDay;
	}
	public void setForwDay(String forwDay) {
		this.forwDay = forwDay;
	}
	public String getMatuDat() {
		return matuDat;
	}
	public void setMatuDat(String matuDat) {
		this.matuDat = matuDat;
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
	public String getIoaner() {
		return ioaner;
	}
	public void setIoaner(String ioaner) {
		this.ioaner = ioaner;
	}
	public String getTradeAgreeAmt() {
		return tradeAgreeAmt;
	}
	public void setTradeAgreeAmt(String tradeAgreeAmt) {
		this.tradeAgreeAmt = tradeAgreeAmt;
	}
	public String getStateVout() {
		return stateVout;
	}
	public void setStateVout(String stateVout) {
		this.stateVout = stateVout;
	}
	public String getMatuType() {
		return matuType;
	}
	public void setMatuType(String matuType) {
		this.matuType = matuType;
	}
	@Override
	public String toString() {
		return "GJS01501030000002Req [debitNo=" + debitNo + ", contractNo="
				+ contractNo + ", dealBrch=" + dealBrch + ", currency="
				+ currency + ", issAmt=" + issAmt + ", shipPro=" + shipPro
				+ ", stornPro=" + stornPro + ", letOfCreDate=" + letOfCreDate
				+ ", forwDay=" + forwDay + ", matuType=" + matuType
				+ ", matuDat=" + matuDat + ", bondAcct=" + bondAcct
				+ ", bondCurr=" + bondCurr + ", bondRate=" + bondRate
				+ ", bondAmt=" + bondAmt + ", tradeAgreeNo=" + tradeAgreeNo
				+ ", ioaner=" + ioaner + ", tradeAgreeAmt=" + tradeAgreeAmt
				+ ", stateVout=" + stateVout + "]";
	}
}
