package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12003000004A01 
 * @Description: 12003000004账户信息查询		01根据账号查询账户信息
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12003000004A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y	3182
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String oprInd;		//操作标志		String(4)		"0－查询账户，需要凭证检查
	private String acctInd;		//账户代号		String(18)
	private String vchrId;		//凭证编号		String(50)
	private String wthdwMth;	//取款方式		String(1)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String pswd;		//密码		String(30)
	private String ccyTp;		//货币种类		String(3)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bnkNtRmtInd;	//钞汇标志		String(2)		"0 现金户(RMB)/现钞(外币)1 非现金户(RMB)/现汇(外币)"	
	private String smlNo;		//小序号		String(3)
	private String bsnTp;		//业务类型		String(10)
	private String frzInd;		//冻解标志		String(1)		0－不冻结/解冻；1－冻结；2－解冻
	private String pfwDt;		//提出日期		String(8)
	private String sysTrcNo;	//系统跟踪号	String(8)		djbj=2时为冻结交易的系统跟踪号。
	private double txnAmt;		//交易金额		Double(20,2)	djbj为1和2时必须有值

	public EsbBodyHxRq12003000004A01(){
		
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

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}

	public String getAcctInd() {
		return acctInd;
	}

	@XmlElement(name = "AcctInd")
	public void setAcctInd(String acctInd) {
		this.acctInd = acctInd;
	}

	public String getVchrId() {
		return vchrId;
	}

	@XmlElement(name = "VchrId")
	public void setVchrId(String vchrId) {
		this.vchrId = vchrId;
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

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public String getBnkNtRmtInd() {
		return bnkNtRmtInd;
	}

	@XmlElement(name = "BnkNtRmtInd")
	public void setBnkNtRmtInd(String bnkNtRmtInd) {
		this.bnkNtRmtInd = bnkNtRmtInd;
	}

	public String getSmlNo() {
		return smlNo;
	}

	@XmlElement(name = "SmlNo")
	public void setSmlNo(String smlNo) {
		this.smlNo = smlNo;
	}

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getFrzInd() {
		return frzInd;
	}

	@XmlElement(name = "FrzInd")
	public void setFrzInd(String frzInd) {
		this.frzInd = frzInd;
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

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}
}
