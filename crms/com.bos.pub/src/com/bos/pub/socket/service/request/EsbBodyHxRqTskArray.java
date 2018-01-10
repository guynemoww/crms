package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyHxRqTskArray 
 * @Description: 01001000002通用核心记账		03表外账记账
 *
 */
public class EsbBodyHxRqTskArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String s9;		//串9	String(10)
	private String gdsNm;	//物品名称	String(12)
	private String s10;		//串10	String(10)
	private String ccyTp;	//货币种类	String(3)
	private double txnAmt;	//交易金额	Double(20,2)
	private String rmk;		//备注	String(100)
	
	public EsbBodyHxRqTskArray(){
		
	}

	public String getS9() {
		return s9;
	}

	@XmlElement(name = "S9")
	public void setS9(String s9) {
		this.s9 = s9;
	}

	public String getGdsNm() {
		return gdsNm;
	}

	@XmlElement(name = "GdsNm")
	public void setGdsNm(String gdsNm) {
		this.gdsNm = gdsNm;
	}

	public String getS10() {
		return s10;
	}

	@XmlElement(name = "S10")
	public void setS10(String s10) {
		this.s10 = s10;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getRmk() {
		return rmk;
	}

	@XmlElement(name = "Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
}
