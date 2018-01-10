package com.primeton.ecds.client;

import commonj.sdo.DataObject;

public class Credit021013IN {

	/* 目前银承中不能使用合同ID */
	private String contractId;// 合同ID
	private String contractNum;// 合同编号

	private DataObject pageCond; // 分页信息
	private String amountDetailId;// 全局标识
	private String custNo;// 客户编号
	private String busiType;// 业务类型：1-承兑签发2-贴现3-质押

	private String acceptorBankNo;// 承兑行行号
	private String remitterAcctNo;// 出票人账号
	private String acctNo;// 贴入人账号(业务发起人账号)
	private String acptDt;// 出票日期
	private String aoAcctNo;// 入账账号
	private String aoBankNo;// 入账行号
	private String toBankNo;// 业务接收方行号

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public DataObject getPageCond() {
		return pageCond;
	}

	public void setPageCond(DataObject pageCond) {
		this.pageCond = pageCond;
	}

	public String getAmountDetailId() {
		return amountDetailId;
	}

	public void setAmountDetailId(String amountDetailId) {
		this.amountDetailId = amountDetailId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}

	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}

	public String getRemitterAcctNo() {
		return remitterAcctNo;
	}

	public void setRemitterAcctNo(String remitterAcctNo) {
		this.remitterAcctNo = remitterAcctNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcptDt() {
		return acptDt;
	}

	public void setAcptDt(String acptDt) {
		this.acptDt = acptDt;
	}

	public String getAoAcctNo() {
		return aoAcctNo;
	}

	public void setAoAcctNo(String aoAcctNo) {
		this.aoAcctNo = aoAcctNo;
	}

	public String getAoBankNo() {
		return aoBankNo;
	}

	public void setAoBankNo(String aoBankNo) {
		this.aoBankNo = aoBankNo;
	}

	public String getToBankNo() {
		return toBankNo;
	}

	public void setToBankNo(String toBankNo) {
		this.toBankNo = toBankNo;
	}

}
