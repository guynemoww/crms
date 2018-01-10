package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqDbtArray 
 * @Description: 03002000011票据信息维护		01银行承兑汇票打印发起	
 *
 */
public class EsbBodyWmaRqDbtArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pyeAcctNm;				//收款人户名	String(100)
	private String pyeAcctNo;				//收款人账号	String(35)
	private String pyeOpenAcctBnkNm;		//收款人开户行行名	String(100)
	private double issuAmt;					//出票金额	Double(20,2)
	private String drftExpDt;				//汇票到期日	String(8)
	private String issuDt;					//出票日期	String(8)
	private String dbtNo;					//借据号	String(20)
	
	public EsbBodyWmaRqDbtArray(){
		
	}

	public String getPyeAcctNm() {
		return pyeAcctNm;
	}

	@XmlElement(name = "PyeAcctNm")
	public void setPyeAcctNm(String pyeAcctNm) {
		this.pyeAcctNm = pyeAcctNm;
	}

	public String getPyeAcctNo() {
		return pyeAcctNo;
	}

	@XmlElement(name = "PyeAcctNo")
	public void setPyeAcctNo(String pyeAcctNo) {
		this.pyeAcctNo = pyeAcctNo;
	}

	public String getPyeOpenAcctBnkNm() {
		return pyeOpenAcctBnkNm;
	}

	@XmlElement(name = "PyeOpenAcctBnkNm")
	public void setPyeOpenAcctBnkNm(String pyeOpenAcctBnkNm) {
		this.pyeOpenAcctBnkNm = pyeOpenAcctBnkNm;
	}

	public double getIssuAmt() {
		return issuAmt;
	}

	@XmlElement(name = "IssuAmt")
	public void setIssuAmt(double issuAmt) {
		this.issuAmt = issuAmt;
	}

	public  String getDrftExpDt() {
		return drftExpDt;
	}

	@XmlElement(name = "DrftExpDt")
	public void setDrftExpDt(String drftExpDt) {
		this.drftExpDt = drftExpDt;
	}

	public String getIssuDt() {
		return issuDt;
	}

	@XmlElement(name = "IssuDt")
	public void setIssuDt(String issuDt) {
		this.issuDt = issuDt;
	}

	public String getDbtNo() {
		return dbtNo;
	}

	@XmlElement(name = "DbtNo")
	public void setDbtNo(String dbtNo) {
		this.dbtNo = dbtNo;
	}
	
}
