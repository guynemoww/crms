package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class AbnormalTran extends SuperBosfxRq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y	3181
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bsnTp;		//业务类型		String(10)	Y
	private String oprInd;		//操作标志		String(4)	Y	"0-根据接口传入的TxnAmt,TxnAmt1,和借贷标志进行处理(适用现金交易和银银平台取款交易)
	private String crDbFlg;		//借贷标志		String(1)	Y	"0-借1-贷xzbz=1时必须为0"
	private String cashTrfFlg;	//现转标志		String(1)	Y	"0现金1转账"
	private String vchrId;		//凭证编号		String(50)	N	空
	private String acctInd;		//账户代号		String(18)	Y	送全账号
	private BigDecimal txnAmt;		//交易金额		Double(20,2)Y
	private BigDecimal txnAmt1;		//交易金额1	Double(20,2)Y	手续费	
	private BigDecimal txnAmt2;		//交易金额2	Double(20,2)N	暂时不使用
	private String pyeAcctNo;	//收款人账号	String(35)	N	xzbz=0时为空
	private String smyCd;		//摘要代码		String(6)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cshItmCd;	//现金项目代号	String(10)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String wthdwMth;	//取款方式		String(1)	Y	不检查密码送空,个人客户检查密码取1
	private String pswd;		//密码		String(30)	N
	private String pfwDt;		//提出日期		String(8)	Y	前置日期
	private String sysTrcNo;	//系统跟踪号	String(8)	Y	前置系统跟踪号
	private String rmk;//备注
	
	public AbnormalTran(){
		this.setBaseVO(new BaseVO());
	}

	public String getTxnCd() {
		return txnCd;
	}

	@XmlElement(name = "TxnCd")
	public void setTxnCd(String txnCd) {
		this.txnCd = txnCd;
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}

	public String getCrDbFlg() {
		return crDbFlg;
	}

	@XmlElement(name = "CrDbFlg")
	public void setCrDbFlg(String crDbFlg) {
		this.crDbFlg = crDbFlg;
	}

	public String getCashTrfFlg() {
		return cashTrfFlg;
	}

	@XmlElement(name = "CashTrfFlg")
	public void setCashTrfFlg(String cashTrfFlg) {
		this.cashTrfFlg = cashTrfFlg;
	}

	public String getVchrId() {
		return vchrId;
	}

	@XmlElement(name = "VchrId")
	public void setVchrId(String vchrId) {
		this.vchrId = vchrId;
	}

	public String getAcctInd() {
		return acctInd;
	}

	@XmlElement(name = "AcctInd")
	public void setAcctInd(String acctInd) {
		this.acctInd = acctInd;
	}

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}

	public BigDecimal getTxnAmt1() {
		return txnAmt1;
	}

	@XmlElement(name = "TxnAmt1")
	public void setTxnAmt1(BigDecimal txnAmt1) {
		this.txnAmt1 = txnAmt1;
	}

	public BigDecimal getTxnAmt2() {
		return txnAmt2;
	}

	@XmlElement(name = "TxnAmt2")
	public void setTxnAmt2(BigDecimal txnAmt2) {
		this.txnAmt2 = txnAmt2;
	}

	public String getPyeAcctNo() {
		return pyeAcctNo;
	}

	@XmlElement(name = "PyeAcctNo")
	public void setPyeAcctNo(String pyeAcctNo) {
		this.pyeAcctNo = pyeAcctNo;
	}

	public String getSmyCd() {
		return smyCd;
	}

	@XmlElement(name = "SmyCd")
	public void setSmyCd(String smyCd) {
		this.smyCd = smyCd;
	}

	public String getCshItmCd() {
		return cshItmCd;
	}

	@XmlElement(name = "CshItmCd")
	public void setCshItmCd(String cshItmCd) {
		this.cshItmCd = cshItmCd;
	}

	public String getWthdwMth() {
		return wthdwMth;
	}

	@XmlElement(name = "WthdwMth")
	public void setWthdwMth(String wthdwMth) {
		this.wthdwMth = wthdwMth;
	}

	public String getPswd() {
		return pswd;
	}

	@XmlElement(name = "Pswd")
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPfwDt() {
		return pfwDt;
	}

	@XmlElement(name = "PfwDt")
	public void setPfwDt(String pfwDt) {
		this.pfwDt = pfwDt;
	}

	public String getSysTrcNo() {
		return sysTrcNo;
	}

	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
	}
	
	public String getRmk() {
		return rmk;
	}
	
	@XmlElement(name="Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
}
