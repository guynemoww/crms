package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 进口信用证开证修改接口---请求对象
 * @author shendl
 *
 */
public class GJS01501030000003Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7598902335976831601L;
	private String debitNo;//借据号
	private String contractNo;//合同编号
	private String dealBrch;//执行机构号
	private String credNo;//信用证号
	private String afModiAmt;//修改后金额
	private String letOfCreDate;//信用证付款期限
	private String forwDay;//远期天数
	private String matuType;//期限类型
	private String newExp;//新到期日期
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	private String bondRate;//保证金比例
	private String bondAmt;//保证金金额
	private String ioaner;//贷款申请人
	public String getAfModiAmt() {
		return afModiAmt;
	}
	public void setAfModiAmt(String afModiAmt) {
		this.afModiAmt = afModiAmt;
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
	public String getNewExp() {
		return newExp;
	}
	public void setNewExp(String newExp) {
		this.newExp = newExp;
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
	public String getIoaner() {
		return ioaner;
	}
	public void setIoaner(String ioaner) {
		this.ioaner = ioaner;
	}
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
	public String getCredNo() {
		return credNo;
	}
	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}
	public String getMatuType() {
		return matuType;
	}
	public void setMatuType(String matuType) {
		this.matuType = matuType;
	}
	@Override
	public String toString() {
		return "GJS01501030000003Req [debitNo=" + debitNo + ", contractNo="
				+ contractNo + ", dealBrch=" + dealBrch + ", credNo=" + credNo
				+ ", afModiAmt=" + afModiAmt + ", letOfCreDate=" + letOfCreDate
				+ ", forwDay=" + forwDay + ", matuType=" + matuType
				+ ", newExp=" + newExp + ", bondAcct=" + bondAcct
				+ ", bondCurr=" + bondCurr + ", bondRate=" + bondRate
				+ ", bondAmt=" + bondAmt + ", ioaner=" + ioaner + "]";
	}
	
}
