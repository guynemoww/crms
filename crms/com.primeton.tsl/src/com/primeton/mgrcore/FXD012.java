package com.primeton.mgrcore;

import java.io.Serializable;

public class FXD012 implements Serializable {
	private static final long serialVersionUID = 2396517190405626605L;
	private String drCrFlag;// 借贷标志
	private String transAmt;// 交易金额
	private String acctFromGo;// 账号来源
	private String acct;// 账号
	private String acctName;// 户名
	private String acctSeq;// 账户序号
	private String chargeBrch;// 记账机构
	private String chargeBusiCode;// 记账业务代号
	private String chargeBusiType;// 记账业务分类
	private String rolloutWriteoffSeq;// 转出销账序号
	private String backup1;// 备用字段1
	private String backup2;// 备用字段2
	private String backup3;// 备用字段3

	public FXD012() {

	}

	public String getDrCrFlag() {
		return drCrFlag;
	}

	public void setDrCrFlag(String drCrFlag) {
		this.drCrFlag = drCrFlag;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getAcctFromGo() {
		return acctFromGo;
	}

	public void setAcctFromGo(String acctFromGo) {
		this.acctFromGo = acctFromGo;
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getAcctSeq() {
		return acctSeq;
	}

	public void setAcctSeq(String acctSeq) {
		this.acctSeq = acctSeq;
	}

	public String getChargeBrch() {
		return chargeBrch;
	}

	public void setChargeBrch(String chargeBrch) {
		this.chargeBrch = chargeBrch;
	}

	public String getChargeBusiCode() {
		return chargeBusiCode;
	}

	public void setChargeBusiCode(String chargeBusiCode) {
		this.chargeBusiCode = chargeBusiCode;
	}

	public String getChargeBusiType() {
		return chargeBusiType;
	}

	public void setChargeBusiType(String chargeBusiType) {
		this.chargeBusiType = chargeBusiType;
	}

	public String getRolloutWriteoffSeq() {
		return rolloutWriteoffSeq;
	}

	public void setRolloutWriteoffSeq(String rolloutWriteoffSeq) {
		this.rolloutWriteoffSeq = rolloutWriteoffSeq;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	@Override
	public String toString() {
		return "OXD012ResRecMsg [drCrFlag=" + drCrFlag + ", transAmt="
				+ transAmt + ", acctFromGo=" + acctFromGo + ", acct=" + acct
				+ ", acctName=" + acctName + ", acctSeq=" + acctSeq
				+ ", chargeBrch=" + chargeBrch + ", chargeBusiCode="
				+ chargeBusiCode + ", chargeBusiType=" + chargeBusiType
				+ ", rolloutWriteoffSeq=" + rolloutWriteoffSeq + ", backup1="
				+ backup1 + ", backup2=" + backup2 + ", backup3=" + backup3
				+ "]";
	}

}
