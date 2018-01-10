package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 资金账户信息组合查询输入
 * @author chenpan
 *
 */
public class IXD15AccountInfo implements Serializable{

	private static final long serialVersionUID = 1292219277483197367L;
	private String acctNo;//账号
	private String acctBusiKind;//账户业务种类
	private String busiNo;//业务代号
	private String busiClass;//业务分类
	private String businessBrch;//营业机构
	private String currCode;//货币代号
	private String begDate;//起始日期
	private String enddate;//终止日期
	private String fundAcctStat;//资金账户状态
	private String linkFlg;//是否联动标志
	private String begNum;//起始笔数
	private String qryNum;//查询笔数
	private String acctChnName;//账户中文名
	private String lastExpDate;//上次到期日期
	private String expireDate;//到期日期
	private String transAmt1;//交易金额1
	private String transAmt2;//交易金额2
	private String smIdyCd;//同业机构代号
	private String orgNum;////核心记账机构--必输
	public IXD15AccountInfo() {
	}
	
	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctBusiKind() {
		return acctBusiKind;
	}
	public void setAcctBusiKind(String acctBusiKind) {
		this.acctBusiKind = acctBusiKind;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiClass() {
		return busiClass;
	}
	public void setBusiClass(String busiClass) {
		this.busiClass = busiClass;
	}
	public String getBusinessBrch() {
		return businessBrch;
	}
	public void setBusinessBrch(String businessBrch) {
		this.businessBrch = businessBrch;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getFundAcctStat() {
		return fundAcctStat;
	}
	public void setFundAcctStat(String fundAcctStat) {
		this.fundAcctStat = fundAcctStat;
	}
	public String getLinkFlg() {
		return linkFlg;
	}
	public void setLinkFlg(String linkFlg) {
		this.linkFlg = linkFlg;
	}
	public String getBegNum() {
		return begNum;
	}
	public void setBegNum(String begNum) {
		this.begNum = begNum;
	}
	public String getQryNum() {
		return qryNum;
	}
	public void setQryNum(String qryNum) {
		this.qryNum = qryNum;
	}
	public String getAcctChnName() {
		return acctChnName;
	}
	public void setAcctChnName(String acctChnName) {
		this.acctChnName = acctChnName;
	}
	public String getLastExpDate() {
		return lastExpDate;
	}
	public void setLastExpDate(String lastExpDate) {
		this.lastExpDate = lastExpDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getTransAmt1() {
		return transAmt1;
	}
	public void setTransAmt1(String transAmt1) {
		this.transAmt1 = transAmt1;
	}
	public String getTransAmt2() {
		return transAmt2;
	}
	public void setTransAmt2(String transAmt2) {
		this.transAmt2 = transAmt2;
	}
	public String getSmIdyCd() {
		return smIdyCd;
	}
	public void setSmIdyCd(String smIdyCd) {
		this.smIdyCd = smIdyCd;
	}
	
}
