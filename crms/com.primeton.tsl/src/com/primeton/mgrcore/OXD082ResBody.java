package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD082ResBody implements Serializable {
	private static final long serialVersionUID = -8820152428253912659L;

	private String custNo;// 客户号
	private String custNoType;// 客户账号类型
	private String custType;// 客户类型
	private String uswFlag;// 通存通兑标志
	private String payCondiditon;// 支付条件
	private String vchNo;// 凭证号
	private String custAcctName;// 客户账户名称
	private String custName;// 客户名称
	private String enName;// 英文名称
	private String openBrch;// 开户机构
	private String flgValue;// 标志值
	private String ynFlag;// 是否标志
	private String vchKind;// 凭证种类
	private String vchBatNo;// 凭证批号
	private String vchSerialNo;// 凭证序号
	private String nxtLine;// 下一打印行数
	private String cashFlag;// 钞汇标志
	private String currCode;// 货币代号
	private String subAcctSeri;// 子账户序号
	private String certType;// 证件种类
	private String certNo;// 证件号码
	private String flgDefault;// 标志缺省值
	private String isNoteAcctFlag;// 是否支票户标志
	private String publNoteAcctPwdFlag;// 对公支票户预留密码标志
	private String uswAre;// 通兑范围
	private String cashExchgFlag;// 现金通兑标志
	private String transExchgFlag;// 转账通兑标志
	private String linkAcctFlg;// 联名账户标志
	private String prdNo;// 产品代号
	private String prdDesc;// 产品说明
	private String labtAcctNum;// 负债账号
	private String accrrestAmt;// 账户余额
	private String acctDeoststTerm;// 账户存款期限
	private String acctDeathDate;// 账户到期日
	private String depositType;// 存款种类
	private String brchChnName;// 机构中文名称
	private String inCustAcct;// 转入客户账号
	private String unitOpenAcctBankNo;// 单位开户行行号
	private String transOpponentAcct;// 交易对手账号
	private String groupAcctSeri;// 组合账户序号
	private String backup1;// 备用字段1
	private String groupProductCode;// 组合产品代号
	private String acctOpenDate;// 账户开户日
	private String validDate;// 起息日

	public OXD082ResBody() {

	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustNoType() {
		return custNoType;
	}

	public void setCustNoType(String custNoType) {
		this.custNoType = custNoType;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getUswFlag() {
		return uswFlag;
	}

	public void setUswFlag(String uswFlag) {
		this.uswFlag = uswFlag;
	}

	public String getPayCondiditon() {
		return payCondiditon;
	}

	public void setPayCondiditon(String payCondiditon) {
		this.payCondiditon = payCondiditon;
	}

	public String getVchNo() {
		return vchNo;
	}

	public void setVchNo(String vchNo) {
		this.vchNo = vchNo;
	}

	public String getCustAcctName() {
		return custAcctName;
	}

	public void setCustAcctName(String custAcctName) {
		this.custAcctName = custAcctName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getOpenBrch() {
		return openBrch;
	}

	public void setOpenBrch(String openBrch) {
		this.openBrch = openBrch;
	}

	public String getFlgValue() {
		return flgValue;
	}

	public void setFlgValue(String flgValue) {
		this.flgValue = flgValue;
	}

	public String getYnFlag() {
		return ynFlag;
	}

	public void setYnFlag(String ynFlag) {
		this.ynFlag = ynFlag;
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

	public String getNxtLine() {
		return nxtLine;
	}

	public void setNxtLine(String nxtLine) {
		this.nxtLine = nxtLine;
	}

	public String getCashFlag() {
		return cashFlag;
	}

	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getSubAcctSeri() {
		return subAcctSeri;
	}

	public void setSubAcctSeri(String subAcctSeri) {
		this.subAcctSeri = subAcctSeri;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getFlgDefault() {
		return flgDefault;
	}

	public void setFlgDefault(String flgDefault) {
		this.flgDefault = flgDefault;
	}

	public String getIsNoteAcctFlag() {
		return isNoteAcctFlag;
	}

	public void setIsNoteAcctFlag(String isNoteAcctFlag) {
		this.isNoteAcctFlag = isNoteAcctFlag;
	}

	public String getPublNoteAcctPwdFlag() {
		return publNoteAcctPwdFlag;
	}

	public void setPublNoteAcctPwdFlag(String publNoteAcctPwdFlag) {
		this.publNoteAcctPwdFlag = publNoteAcctPwdFlag;
	}

	public String getUswAre() {
		return uswAre;
	}

	public void setUswAre(String uswAre) {
		this.uswAre = uswAre;
	}

	public String getCashExchgFlag() {
		return cashExchgFlag;
	}

	public void setCashExchgFlag(String cashExchgFlag) {
		this.cashExchgFlag = cashExchgFlag;
	}

	public String getTransExchgFlag() {
		return transExchgFlag;
	}

	public void setTransExchgFlag(String transExchgFlag) {
		this.transExchgFlag = transExchgFlag;
	}

	public String getLinkAcctFlg() {
		return linkAcctFlg;
	}

	public void setLinkAcctFlg(String linkAcctFlg) {
		this.linkAcctFlg = linkAcctFlg;
	}

	public String getPrdNo() {
		return prdNo;
	}

	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}

	public String getPrdDesc() {
		return prdDesc;
	}

	public void setPrdDesc(String prdDesc) {
		this.prdDesc = prdDesc;
	}

	public String getLabtAcctNum() {
		return labtAcctNum;
	}

	public void setLabtAcctNum(String labtAcctNum) {
		this.labtAcctNum = labtAcctNum;
	}

	public String getAccrrestAmt() {
		return accrrestAmt;
	}

	public void setAccrrestAmt(String accrrestAmt) {
		this.accrrestAmt = accrrestAmt;
	}

	public String getAcctDeoststTerm() {
		return acctDeoststTerm;
	}

	public void setAcctDeoststTerm(String acctDeoststTerm) {
		this.acctDeoststTerm = acctDeoststTerm;
	}

	public String getAcctDeathDate() {
		return acctDeathDate;
	}

	public void setAcctDeathDate(String acctDeathDate) {
		this.acctDeathDate = acctDeathDate;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getBrchChnName() {
		return brchChnName;
	}

	public void setBrchChnName(String brchChnName) {
		this.brchChnName = brchChnName;
	}

	public String getInCustAcct() {
		return inCustAcct;
	}

	public void setInCustAcct(String inCustAcct) {
		this.inCustAcct = inCustAcct;
	}

	public String getUnitOpenAcctBankNo() {
		return unitOpenAcctBankNo;
	}

	public void setUnitOpenAcctBankNo(String unitOpenAcctBankNo) {
		this.unitOpenAcctBankNo = unitOpenAcctBankNo;
	}

	public String getTransOpponentAcct() {
		return transOpponentAcct;
	}

	public void setTransOpponentAcct(String transOpponentAcct) {
		this.transOpponentAcct = transOpponentAcct;
	}

	public String getGroupAcctSeri() {
		return groupAcctSeri;
	}

	public void setGroupAcctSeri(String groupAcctSeri) {
		this.groupAcctSeri = groupAcctSeri;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getGroupProductCode() {
		return groupProductCode;
	}

	public void setGroupProductCode(String groupProductCode) {
		this.groupProductCode = groupProductCode;
	}

	public String getAcctOpenDate() {
		return acctOpenDate;
	}

	public void setAcctOpenDate(String acctOpenDate) {
		this.acctOpenDate = acctOpenDate;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	@Override
	public String toString() {
		return "OXD082ResBody [custNo=" + custNo + ", custNoType=" + custNoType
				+ ", custType=" + custType + ", uswFlag=" + uswFlag
				+ ", payCondiditon=" + payCondiditon + ", vchNo=" + vchNo
				+ ", custAcctName=" + custAcctName + ", custName=" + custName
				+ ", enName=" + enName + ", openBrch=" + openBrch
				+ ", flgValue=" + flgValue + ", ynFlag=" + ynFlag
				+ ", vchKind=" + vchKind + ", vchBatNo=" + vchBatNo
				+ ", vchSerialNo=" + vchSerialNo + ", nxtLine=" + nxtLine
				+ ", cashFlag=" + cashFlag + ", currCode=" + currCode
				+ ", subAcctSeri=" + subAcctSeri + ", certType=" + certType
				+ ", certNo=" + certNo + ", flgDefault=" + flgDefault
				+ ", isNoteAcctFlag=" + isNoteAcctFlag
				+ ", publNoteAcctPwdFlag=" + publNoteAcctPwdFlag + ", uswAre="
				+ uswAre + ", cashExchgFlag=" + cashExchgFlag
				+ ", transExchgFlag=" + transExchgFlag + ", linkAcctFlg="
				+ linkAcctFlg + ", prdNo=" + prdNo + ", prdDesc=" + prdDesc
				+ ", labtAcctNum=" + labtAcctNum + ", accrrestAmt="
				+ accrrestAmt + ", acctDeoststTerm=" + acctDeoststTerm
				+ ", acctDeathDate=" + acctDeathDate + ", depositType="
				+ depositType + ", brchChnName=" + brchChnName
				+ ", inCustAcct=" + inCustAcct + ", unitOpenAcctBankNo="
				+ unitOpenAcctBankNo + ", transOpponentAcct="
				+ transOpponentAcct + ", groupAcctSeri=" + groupAcctSeri
				+ ", backup1=" + backup1 + ", groupProductCode="
				+ groupProductCode + ", acctOpenDate=" + acctOpenDate
				+ ", validDate=" + validDate + "]";
	}

}
