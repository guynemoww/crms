package com.primeton.mgrcore;

import java.io.Serializable;

public class FXD091 implements Serializable {
	private static final long serialVersionUID = -836474187935278263L;

	private String ynFlag1;// 是否标志
	private String frzNum;// 冻结编号
	private String custAcct;// 客户账号
	private String acctname;// 账户名称
	private String subAcctSeri;// 子账户序号
	private String currCode;// 货币代号
	private String cashFlag;// 钞汇标志
	private String freezeType;// 冻结种类
	private String freezeEndDate;// 冻结终止日期
	private String freezeAmt;// 需冻结金额
	private String frzCase;// 冻结原因
	private String freezeEnsureFileType;// 冻结证明文书类别
	private String freezeNotifyNo;// 冻结通知书编号
	private String ynFlag2;// 是否标志
	private String backup2;// 备用字段2
	private String backup1;// 备用字段1
	private String reserveMark1;// 备用标志1
	private String reserveMark2;// 备用标志2
	private String backupAmt;// 备用金额

	public FXD091() {

	}

	public String getYnFlag1() {
		return ynFlag1;
	}

	public void setYnFlag1(String ynFlag1) {
		this.ynFlag1 = ynFlag1;
	}

	public String getFrzNum() {
		return frzNum;
	}

	public void setFrzNum(String frzNum) {
		this.frzNum = frzNum;
	}

	public String getCustAcct() {
		return custAcct;
	}

	public void setCustAcct(String custAcct) {
		this.custAcct = custAcct;
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

	public String getFreezeType() {
		return freezeType;
	}

	public void setFreezeType(String freezeType) {
		this.freezeType = freezeType;
	}

	public String getFreezeEndDate() {
		return freezeEndDate;
	}

	public void setFreezeEndDate(String freezeEndDate) {
		this.freezeEndDate = freezeEndDate;
	}

	public String getFreezeAmt() {
		return freezeAmt;
	}

	public void setFreezeAmt(String freezeAmt) {
		this.freezeAmt = freezeAmt;
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

	public String getYnFlag2() {
		return ynFlag2;
	}

	public void setYnFlag2(String ynFlag2) {
		this.ynFlag2 = ynFlag2;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getReserveMark1() {
		return reserveMark1;
	}

	public void setReserveMark1(String reserveMark1) {
		this.reserveMark1 = reserveMark1;
	}

	public String getReserveMark2() {
		return reserveMark2;
	}

	public void setReserveMark2(String reserveMark2) {
		this.reserveMark2 = reserveMark2;
	}

	public String getBackupAmt() {
		return backupAmt;
	}

	public void setBackupAmt(String backupAmt) {
		this.backupAmt = backupAmt;
	}

}
