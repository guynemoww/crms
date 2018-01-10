package com.primeton.mgrcore;

import java.io.Serializable;

public class FXD092 implements Serializable {
	private static final long serialVersionUID = -7266627823944982669L;

	private String operFlag;// 操作标志
	private String frzNum;// 冻结编号
	private String custAcct;// 客户账号
	private String subAcctSeri;// 子账户序号
	private String freezeType;// 冻结种类
	private String freezeAmt;// 需冻结金额
	private String frzCase;// 冻结原因
	private String freezeEnsureFileType;// 冻结证明文书类别
	private String freezeNotifyNo;// 冻结通知书编号
	private String backup2;// 备用字段2
	private String backup1;// 备用字段1
	private String reserveMark1;// 备用标志1
	private String reserveMark2;// 备用标志2
	private String backupAmt;// 备用金额

	public FXD092() {

	}

	public String getOperFlag() {
		return operFlag;
	}

	public void setOperFlag(String operFlag) {
		this.operFlag = operFlag;
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

	public String getSubAcctSeri() {
		return subAcctSeri;
	}

	public void setSubAcctSeri(String subAcctSeri) {
		this.subAcctSeri = subAcctSeri;
	}

	public String getFreezeType() {
		return freezeType;
	}

	public void setFreezeType(String freezeType) {
		this.freezeType = freezeType;
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

	@Override
	public String toString() {
		return "FXD092 [operFlag=" + operFlag + ", frzNum=" + frzNum
				+ ", custAcct=" + custAcct + ", subAcctSeri=" + subAcctSeri
				+ ", freezeType=" + freezeType + ", freezeAmt=" + freezeAmt
				+ ", frzCase=" + frzCase + ", freezeEnsureFileType="
				+ freezeEnsureFileType + ", freezeNotifyNo=" + freezeNotifyNo
				+ ", backup2=" + backup2 + ", backup1=" + backup1
				+ ", reserveMark1=" + reserveMark1 + ", reserveMark2="
				+ reserveMark2 + ", backupAmt=" + backupAmt + "]";
	}

}
