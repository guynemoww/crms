package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD072ResBody implements Serializable {
	private static final long serialVersionUID = 979182202017870032L;

	private String custNo;// 客户账号
	private String acctname;// 账户名称
	private String freezeAmt;// 需冻结金额
	private String frzCase;// 冻结原因
	private String freezeEnsureFileType;// 冻结证明文书类别
	private String freezeNotifyNo;// 冻结通知书编号
	private String approver;// 审批人
	private String frzNum;// 冻结编号
	private String accrrestAmt;// 账户余额

	public OXD072ResBody() {

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

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getFrzNum() {
		return frzNum;
	}

	public void setFrzNum(String frzNum) {
		this.frzNum = frzNum;
	}

	public String getAccrrestAmt() {
		return accrrestAmt;
	}

	public void setAccrrestAmt(String accrrestAmt) {
		this.accrrestAmt = accrrestAmt;
	}

	@Override
	public String toString() {
		return "OXD072ResBody [custNo=" + custNo + ", acctname=" + acctname
				+ ", freezeAmt=" + freezeAmt + ", frzCase=" + frzCase
				+ ", freezeEnsureFileType=" + freezeEnsureFileType
				+ ", freezeNotifyNo=" + freezeNotifyNo + ", approver="
				+ approver + ", frzNum=" + frzNum + ", accrrestAmt="
				+ accrrestAmt + "]";
	}

}
