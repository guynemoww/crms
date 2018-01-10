package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqExprtRbtInfArray   出口退税
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqExprtRbtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String astNm;          			//资产名称               		String(50)      Y
	private  String exprtRbtAcctNo;				//出口退税账号       	    String(35)      Y
	private  String ccy;						//币种                                        String(10)      Y
	private  double rbtAmt;				    	//退税金额                 	Double(26,8)    Y
	private  String ExprtGdsDclrtnNo;			//出口货物报关单号             String(20)      Y
	
	
	public EsbBodyWmaRqExprtRbtInfArray(){
		
	}


	public String getAstNm() {
		return astNm;
	}

	@XmlElement(name = "AstNm")
	public void setAstNm(String astNm) {
		this.astNm = astNm;
	}


	public String getExprtRbtAcctNo() {
		return exprtRbtAcctNo;
	}

	@XmlElement(name = "ExprtRbtAcctNo")
	public void setExprtRbtAcctNo(String exprtRbtAcctNo) {
		this.exprtRbtAcctNo = exprtRbtAcctNo;
	}


	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public double getRbtAmt() {
		return rbtAmt;
	}

	@XmlElement(name = "RbtAmt")
	public void setRbtAmt(double rbtAmt) {
		this.rbtAmt = rbtAmt;
	}


	public String getExprtGdsDclrtnNo() {
		return ExprtGdsDclrtnNo;
	}

	@XmlElement(name = "ExprtGdsDclrtnNo")
	public void setExprtGdsDclrtnNo(String exprtGdsDclrtnNo) {
		ExprtGdsDclrtnNo = exprtGdsDclrtnNo;
	}

	
}
