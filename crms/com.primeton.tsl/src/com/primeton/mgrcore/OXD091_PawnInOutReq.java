package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;

public class OXD091_PawnInOutReq implements Serializable {
	private static final long serialVersionUID = 7918609456322050833L;

	private String operFlag;// 操作标志
	private String chargeBrch;// 入账机构
	private String ynFlag;// 是否标志
	private String prdCode;// 产品代码
	private String collateralWay;// 抵质押方式
	private String actualValue;// 实际价值
	private String currCode;// 货币代号
	private String summaryCode;// 摘要代码
	private String summary;// 摘要内容
	private String custNo;// 客户号
	private String backup2;// 备用字段2
	private String backup1;// 备用字段1
	private String reserveMark1;// 备用标志1
	private String reserveMark2;// 备用标志2
	private String backupAmt;// 备用金额
	private BigInteger recNum;// 循环记录数
	private FXD091[] fxd091;// 循环字段
	private String orgNum;//核心记账机构--必输
	public OXD091_PawnInOutReq() {
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

	public String getChargeBrch() {
		return chargeBrch;
	}

	public void setChargeBrch(String chargeBrch) {
		this.chargeBrch = chargeBrch;
	}

	public String getYnFlag() {
		return ynFlag;
	}

	public void setYnFlag(String ynFlag) {
		this.ynFlag = ynFlag;
	}

	public String getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public String getCollateralWay() {
		return collateralWay;
	}

	public void setCollateralWay(String collateralWay) {
		this.collateralWay = collateralWay;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getSummaryCode() {
		return summaryCode;
	}

	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public BigInteger getRecNum() {
		return recNum;
	}

	public void setRecNum(BigInteger recNum) {
		this.recNum = recNum;
	}

	public FXD091[] getFxd091() {
		return fxd091;
	}

	public void setFxd091(FXD091[] fxd091) {
		this.fxd091 = fxd091;
	}

}
