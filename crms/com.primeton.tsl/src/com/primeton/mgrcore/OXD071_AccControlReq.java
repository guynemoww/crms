package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD07】账户控制及维护(止付/解止付) Y：必输 N：非必输
 * 
 * @author MJF
 * 
 *         输入
 */
public class OXD071_AccControlReq implements Serializable {
	private static final long serialVersionUID = -1782763773194758735L;

	private String operFlag;// 操作标志
	private String freezeOperFlag;// 冻结操作标志
	private String frzNum;// 冻结编号
	private String freezeType;// 冻结种类
	private String custNo;// 客户账号
	private String acctname;// 账户名称
	private String subAcctSeri;// 子账户序号
	private String currCode;// 货币代号
	private String cashFlag;// 钞汇标志
	private String labtAcctNum;// 负债账号
	private String freezeBal;// 可冻余额
	private String freezeAmt;// 需冻结金额
	private String amt;// 金额
	private String frzCase;// 冻结原因
	private String freezeEnsureFileType;// 冻结证明文书类别
	private String freezeNotifyNo;// 冻结通知书编号
	private String approver;// 审批人
	private String flgValue;// 标志值
	private String vchKind;// 凭证种类
	private String vchBatNo;// 凭证批号
	private String vchSerialNo;// 凭证序号
	private String operFlag1;// 操作标志1
	private String freezeEndDate;// 冻结终止日期
	private String orgNum;//核心记账机构--必输
	public OXD071_AccControlReq() {

	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getOperFlag() {
		return operFlag;
	}

	public void setOperFlag(String operFlag) {
		this.operFlag = operFlag;
	}

	public String getFreezeOperFlag() {
		return freezeOperFlag;
	}

	public void setFreezeOperFlag(String freezeOperFlag) {
		this.freezeOperFlag = freezeOperFlag;
	}

	public String getFrzNum() {
		return frzNum;
	}

	public void setFrzNum(String frzNum) {
		this.frzNum = frzNum;
	}

	public String getFreezeType() {
		return freezeType;
	}

	public void setFreezeType(String freezeType) {
		this.freezeType = freezeType;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

	public String getSubAcctSeri() {
		return subAcctSeri;
	}

	public void setSubAcctSeri(String subAcctSeri) {
		this.subAcctSeri = subAcctSeri;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getCashFlag() {
		return cashFlag;
	}

	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	public String getLabtAcctNum() {
		return labtAcctNum;
	}

	public void setLabtAcctNum(String labtAcctNum) {
		this.labtAcctNum = labtAcctNum;
	}

	public String getFreezeBal() {
		return freezeBal;
	}

	public void setFreezeBal(String freezeBal) {
		this.freezeBal = freezeBal;
	}

	public String getFreezeAmt() {
		return freezeAmt;
	}

	public void setFreezeAmt(String freezeAmt) {
		this.freezeAmt = freezeAmt;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getFrzCase() {
		return frzCase;
	}

	public void setFrzCase(String frzCase) {
		this.frzCase = frzCase;
	}

	public String getFreezeEnsureFileType() {
		return freezeEnsureFileType;
	}

	public void setFreezeEnsureFileType(String freezeEnsureFileType) {
		this.freezeEnsureFileType = freezeEnsureFileType;
	}

	public String getFreezeNotifyNo() {
		return freezeNotifyNo;
	}

	public void setFreezeNotifyNo(String freezeNotifyNo) {
		this.freezeNotifyNo = freezeNotifyNo;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getFlgValue() {
		return flgValue;
	}

	public void setFlgValue(String flgValue) {
		this.flgValue = flgValue;
	}

	public String getVchKind() {
		return vchKind;
	}

	public void setVchKind(String vchKind) {
		this.vchKind = vchKind;
	}

	public String getVchBatNo() {
		return vchBatNo;
	}

	public void setVchBatNo(String vchBatNo) {
		this.vchBatNo = vchBatNo;
	}

	public String getVchSerialNo() {
		return vchSerialNo;
	}

	public void setVchSerialNo(String vchSerialNo) {
		this.vchSerialNo = vchSerialNo;
	}

	public String getOperFlag1() {
		return operFlag1;
	}

	public void setOperFlag1(String operFlag1) {
		this.operFlag1 = operFlag1;
	}

	public String getFreezeEndDate() {
		return freezeEndDate;
	}

	public void setFreezeEndDate(String freezeEndDate) {
		this.freezeEndDate = freezeEndDate;
	}

	@Override
	public String toString() {
		return "OXD071_AccControlReq [operFlag=" + operFlag
				+ ", freezeOperFlag=" + freezeOperFlag + ", frzNum=" + frzNum
				+ ", freezeType=" + freezeType + ", custNo=" + custNo
				+ ", acctname=" + acctname + ", subAcctSeri=" + subAcctSeri
				+ ", currCode=" + currCode + ", cashFlag=" + cashFlag
				+ ", labtAcctNum=" + labtAcctNum + ", freezeBal=" + freezeBal
				+ ", freezeAmt=" + freezeAmt + ", amt=" + amt + ", frzCase="
				+ frzCase + ", freezeEnsureFileType=" + freezeEnsureFileType
				+ ", freezeNotifyNo=" + freezeNotifyNo + ", approver="
				+ approver + ", flgValue=" + flgValue + ", vchKind=" + vchKind
				+ ", vchBatNo=" + vchBatNo + ", vchSerialNo=" + vchSerialNo
				+ ", operFlag1=" + operFlag1 + ", freezeEndDate="
				+ freezeEndDate + "]";
	}

}