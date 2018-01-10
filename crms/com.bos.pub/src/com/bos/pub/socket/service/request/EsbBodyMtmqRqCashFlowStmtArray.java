package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRq12002000013CB008 
 * @Description: 12002000013客户信息开户维护     13对公客户财报信息维护				
 *
 */
public class EsbBodyMtmqRqCashFlowStmtArray implements Serializable{

	private static final long serialVersionUID = 1L;
	private String acctCd				;//	科目代码	String(6)	N	
	private String prdBegNum		;//	期初数	String(20)	N	如果是年报，则为年初数
	private String prdEndNum		;//	期末数	String(20)	N	如果是年报，则为年末数
	
	public EsbBodyMtmqRqCashFlowStmtArray() {
		
	}

	public String getAcctCd() {
		return acctCd;
	}
	@XmlElement(name = "AcctCd")
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public String getPrdBegNum() {
		return prdBegNum;
	}
	@XmlElement(name = "PrdBegNum")
	public void setPrdBegNum(String prdBegNum) {
		this.prdBegNum = prdBegNum;
	}

	public String getPrdEndNum() {
		return prdEndNum;
	}
	@XmlElement(name = "PrdEndNum")
	public void setPrdEndNum(String prdEndNum) {
		this.prdEndNum = prdEndNum;
	}
	

}
