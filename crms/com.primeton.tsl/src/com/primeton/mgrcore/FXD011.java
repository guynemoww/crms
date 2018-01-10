package com.primeton.mgrcore;

import java.io.Serializable;

public class FXD011 implements Serializable {
	private static final long serialVersionUID = 8151447951676686018L;
	private String dealType;// 处理类型
	private String drCrFlag;// 借贷标志
	private String currCode;// 货币代号
	private String cashFlag;// 钞汇标志
	private String transAmt;// 交易金额
	private String acctFromGo;// 账号来源/去向
	private String acct;// 账号
	private String acctName;// 户名
	private String acctSeq;// 账户序号
	private String chargeBrch;// 记账机构
	private String chargeBusiCode;// 记账业务代号
	private String chargeBusiType;// 记账业务分类
	private String rolloutWriteoffSeq;// 转出销账序号
	private String cshProCode;// 现金项目代码
	private String pwdKind;// 密码种类
	private String transPassWord;// 交易密码
	private String vchKind;// 凭证种类
	private String vchBatNo;// 凭证批号
	private String vchSerialNo;// 凭证序号
	private String payPwd;// 支付密码
	private String drawDate;// 出票日期
	private String issueBankNo;// 签发行行号
	private String sndTrak;// 2磁道信息
	private String thrTrak;// 3磁道信息
	private String signPassFlag;// 验印通过标志
	private String certType;// 证件种类
	private String certNo;// 证件号码
	private String vertLastboxSignFlag;// 核销尾箱凭证标志
	private String vertSignBelongOper;// 核销凭证所属柜员
	private String vchKind1;// 凭证种类1
	private String vchNo;// 凭证号码
	private String vchBatNo1;// 凭证批号1
	private String vchSerialNo1;// 凭证序号1
	private String otherAcct;// 对方账号
	private String otherNam;// 对方户名
	private String organNam;// 对方机构名称
	private String othBankBrchType;// 对方金融机构类型
	private String othBankBrchCode;// 对方金融机构代码
	private String agentName;// 代理人姓名
	private String agentPaperType;// 代理人证件类型
	private String agntCertNum;// 代理人证件号码
	private String feePayType;// 手续费收取标志
	private String origBusiNo;// 原业务编号
	private String origTxDate;// 原交易日期
	private String oldOperSeq;// 原柜员流水
	private String poundageAmtFrom;// 手续费资金来源
	private String feeAcct;// 收费账号
	private String chargeAcctSeq;// 收费账户序号
	private String feeEveNo;// 收费事件编号
	private String chargeAmt;// 收费金额
	private String enoughFlag;// 足额标志

	public FXD011() {

	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getDrCrFlag() {
		return drCrFlag;
	}

	public void setDrCrFlag(String drCrFlag) {
		this.drCrFlag = drCrFlag;
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

	public String getCshProCode() {
		return cshProCode;
	}

	public void setCshProCode(String cshProCode) {
		this.cshProCode = cshProCode;
	}

	public String getPwdKind() {
		return pwdKind;
	}

	public void setPwdKind(String pwdKind) {
		this.pwdKind = pwdKind;
	}

	public String getTransPassWord() {
		return transPassWord;
	}

	public void setTransPassWord(String transPassWord) {
		this.transPassWord = transPassWord;
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

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}

	public String getIssueBankNo() {
		return issueBankNo;
	}

	public void setIssueBankNo(String issueBankNo) {
		this.issueBankNo = issueBankNo;
	}

	public String getSndTrak() {
		return sndTrak;
	}

	public void setSndTrak(String sndTrak) {
		this.sndTrak = sndTrak;
	}

	public String getThrTrak() {
		return thrTrak;
	}

	public void setThrTrak(String thrTrak) {
		this.thrTrak = thrTrak;
	}

	public String getSignPassFlag() {
		return signPassFlag;
	}

	public void setSignPassFlag(String signPassFlag) {
		this.signPassFlag = signPassFlag;
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

	public String getVertLastboxSignFlag() {
		return vertLastboxSignFlag;
	}

	public void setVertLastboxSignFlag(String vertLastboxSignFlag) {
		this.vertLastboxSignFlag = vertLastboxSignFlag;
	}

	public String getVertSignBelongOper() {
		return vertSignBelongOper;
	}

	public void setVertSignBelongOper(String vertSignBelongOper) {
		this.vertSignBelongOper = vertSignBelongOper;
	}

	public String getVchKind1() {
		return vchKind1;
	}

	public void setVchKind1(String vchKind1) {
		this.vchKind1 = vchKind1;
	}

	public String getVchNo() {
		return vchNo;
	}

	public void setVchNo(String vchNo) {
		this.vchNo = vchNo;
	}

	public String getVchBatNo1() {
		return vchBatNo1;
	}

	public void setVchBatNo1(String vchBatNo1) {
		this.vchBatNo1 = vchBatNo1;
	}

	public String getVchSerialNo1() {
		return vchSerialNo1;
	}

	public void setVchSerialNo1(String vchSerialNo1) {
		this.vchSerialNo1 = vchSerialNo1;
	}

	public String getOtherAcct() {
		return otherAcct;
	}

	public void setOtherAcct(String otherAcct) {
		this.otherAcct = otherAcct;
	}

	public String getOtherNam() {
		return otherNam;
	}

	public void setOtherNam(String otherNam) {
		this.otherNam = otherNam;
	}

	public String getOrganNam() {
		return organNam;
	}

	public void setOrganNam(String organNam) {
		this.organNam = organNam;
	}

	public String getOthBankBrchType() {
		return othBankBrchType;
	}

	public void setOthBankBrchType(String othBankBrchType) {
		this.othBankBrchType = othBankBrchType;
	}

	public String getOthBankBrchCode() {
		return othBankBrchCode;
	}

	public void setOthBankBrchCode(String othBankBrchCode) {
		this.othBankBrchCode = othBankBrchCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentPaperType() {
		return agentPaperType;
	}

	public void setAgentPaperType(String agentPaperType) {
		this.agentPaperType = agentPaperType;
	}

	public String getAgntCertNum() {
		return agntCertNum;
	}

	public void setAgntCertNum(String agntCertNum) {
		this.agntCertNum = agntCertNum;
	}

	public String getFeePayType() {
		return feePayType;
	}

	public void setFeePayType(String feePayType) {
		this.feePayType = feePayType;
	}

	public String getOrigBusiNo() {
		return origBusiNo;
	}

	public void setOrigBusiNo(String origBusiNo) {
		this.origBusiNo = origBusiNo;
	}

	public String getOrigTxDate() {
		return origTxDate;
	}

	public void setOrigTxDate(String origTxDate) {
		this.origTxDate = origTxDate;
	}

	public String getOldOperSeq() {
		return oldOperSeq;
	}

	public void setOldOperSeq(String oldOperSeq) {
		this.oldOperSeq = oldOperSeq;
	}

	public String getPoundageAmtFrom() {
		return poundageAmtFrom;
	}

	public void setPoundageAmtFrom(String poundageAmtFrom) {
		this.poundageAmtFrom = poundageAmtFrom;
	}

	public String getFeeAcct() {
		return feeAcct;
	}

	public void setFeeAcct(String feeAcct) {
		this.feeAcct = feeAcct;
	}

	public String getChargeAcctSeq() {
		return chargeAcctSeq;
	}

	public void setChargeAcctSeq(String chargeAcctSeq) {
		this.chargeAcctSeq = chargeAcctSeq;
	}

	public String getFeeEveNo() {
		return feeEveNo;
	}

	public void setFeeEveNo(String feeEveNo) {
		this.feeEveNo = feeEveNo;
	}

	public String getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getEnoughFlag() {
		return enoughFlag;
	}

	public void setEnoughFlag(String enoughFlag) {
		this.enoughFlag = enoughFlag;
	}

	@Override
	public String toString() {
		return "OXD011ReqRecMsg [dealType=" + dealType + ", drCrFlag="
				+ drCrFlag + ", currCode=" + currCode + ", cashFlag="
				+ cashFlag + ", transAmt=" + transAmt + ", acctFromGo="
				+ acctFromGo + ", acct=" + acct + ", acctName=" + acctName
				+ ", acctSeq=" + acctSeq + ", chargeBrch=" + chargeBrch
				+ ", chargeBusiCode=" + chargeBusiCode + ", chargeBusiType="
				+ chargeBusiType + ", rolloutWriteoffSeq=" + rolloutWriteoffSeq
				+ ", cshProCode=" + cshProCode + ", pwdKind=" + pwdKind
				+ ", transPassWord=" + transPassWord + ", vchKind=" + vchKind
				+ ", vchBatNo=" + vchBatNo + ", vchSerialNo=" + vchSerialNo
				+ ", payPwd=" + payPwd + ", drawDate=" + drawDate
				+ ", issueBankNo=" + issueBankNo + ", sndTrak=" + sndTrak
				+ ", thrTrak=" + thrTrak + ", signPassFlag=" + signPassFlag
				+ ", certType=" + certType + ", certNo=" + certNo
				+ ", vertLastboxSignFlag=" + vertLastboxSignFlag
				+ ", vertSignBelongOper=" + vertSignBelongOper + ", vchKind1="
				+ vchKind1 + ", vchNo=" + vchNo + ", vchBatNo1=" + vchBatNo1
				+ ", vchSerialNo1=" + vchSerialNo1 + ", otherAcct=" + otherAcct
				+ ", otherNam=" + otherNam + ", organNam=" + organNam
				+ ", othBankBrchType=" + othBankBrchType + ", othBankBrchCode="
				+ othBankBrchCode + ", agentName=" + agentName
				+ ", agentPaperType=" + agentPaperType + ", agntCertNum="
				+ agntCertNum + ", feePayType=" + feePayType + ", origBusiNo="
				+ origBusiNo + ", origTxDate=" + origTxDate + ", oldOperSeq="
				+ oldOperSeq + ", poundageAmtFrom=" + poundageAmtFrom
				+ ", feeAcct=" + feeAcct + ", chargeAcctSeq=" + chargeAcctSeq
				+ ", feeEveNo=" + feeEveNo + ", chargeAmt=" + chargeAmt
				+ ", enoughFlag=" + enoughFlag + "]";
	}
}
