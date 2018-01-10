package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyHxRqZhkArray 
 * @Description: 01001000002通用核心记账	05中间业务多笔单边记账
 *
 */
public class EsbBodyHxRqZhArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String crDbFlg;		//借贷标志	String(1)	0-借 1-贷
	private String cashTrfFlg;	//现转标志	String(1)	0-现金 1-转账
	private String acctInd;		//账户代号	String(18)
	private String txnAmt;	//交易金额	Double(20,2)
	private double smyCd;	//摘要代码	String(6)
	private String cshItmCd;		//现金项目代号	String(10)
	private String vchrId2;		//凭证编号2	String(50)
	private String bckInf;		//备用信息	String(20)
	
	public EsbBodyHxRqZhArray(){
		
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



	public String getAcctInd() {
		return acctInd;
	}


	@XmlElement(name = "AcctInd")
	public void setAcctInd(String acctInd) {
		this.acctInd = acctInd;
	}



	public String getTxnAmt() {
		return txnAmt;
	}


	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}



	public double getSmyCd() {
		return smyCd;
	}


	@XmlElement(name = "SmyCd")
	public void setSmyCd(double smyCd) {
		this.smyCd = smyCd;
	}



	public String getCshItmCd() {
		return cshItmCd;
	}


	@XmlElement(name = "CshItmCd")
	public void setCshItmCd(String cshItmCd) {
		this.cshItmCd = cshItmCd;
	}



	public String getVchrId2() {
		return vchrId2;
	}


	@XmlElement(name = "VchrId2")
	public void setVchrId2(String vchrId2) {
		this.vchrId2 = vchrId2;
	}



	public String getBckInf() {
		return bckInf;
	}


	@XmlElement(name = "BckInf")
	public void setBckInf(String bckInf) {
		this.bckInf = bckInf;
	}

}
