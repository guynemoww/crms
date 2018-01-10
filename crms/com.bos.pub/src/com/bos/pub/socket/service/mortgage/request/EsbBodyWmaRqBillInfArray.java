package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqBillInfArray   票据
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqBillInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String billNo;          			//票据号码               		String(20)      Y
	private  String drwrAcctNm;					//出票人户名       	    String(100)     Y
	private  String drwrAcctNo;					//出票人账号                          String(35)      Y
	private  String drwrOpenAcctBnkNm;			//出票人开户行行名             String(100)     Y
	private  String issuDt;						//出票日期                               String(8)       Y
	private  String expDt;						//到期日                                   String(8)       Y
	private  String pyeAcctNo;					//收款人账号                          String(35)      Y
	private  String pyeOpenAcctBnkNm;			//收款人开户行行名             String(100)     Y
	private  String ccy;						//币种                                        String(10)      Y
	private  String unintruptEndrsmFlg;			//连续背书标志                      String(4)       Y
	private  double billAmt;				    //票据金额                  	Double(20,8)    Y
	
	public EsbBodyWmaRqBillInfArray(){
		
	}

	public String getBillNo() {
		return billNo;
	}

	@XmlElement(name = "BillNo")
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDrwrAcctNm() {
		return drwrAcctNm;
	}

	@XmlElement(name = "DrwrAcctNm")
	public void setDrwrAcctNm(String drwrAcctNm) {
		this.drwrAcctNm = drwrAcctNm;
	}

	public String getDrwrAcctNo() {
		return drwrAcctNo;
	}

	@XmlElement(name = "DrwrAcctNo")
	public void setDrwrAcctNo(String drwrAcctNo) {
		this.drwrAcctNo = drwrAcctNo;
	}

	public String getDrwrOpenAcctBnkNm() {
		return drwrOpenAcctBnkNm;
	}

	@XmlElement(name = "DrwrOpenAcctBnkNm")
	public void setDrwrOpenAcctBnkNm(String drwrOpenAcctBnkNm) {
		this.drwrOpenAcctBnkNm = drwrOpenAcctBnkNm;
	}

	public String getIssuDt() {
		return issuDt;
	}

	@XmlElement(name = "IssuDt")
	public void setIssuDt(String issuDt) {
		this.issuDt = issuDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
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

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getUnintruptEndrsmFlg() {
		return unintruptEndrsmFlg;
	}

	@XmlElement(name = "UnintruptEndrsmFlg")
	public void setUnintruptEndrsmFlg(String unintruptEndrsmFlg) {
		this.unintruptEndrsmFlg = unintruptEndrsmFlg;
	}

	public double getBillAmt() {
		return billAmt;
	}

	@XmlElement(name = "BillAmt")
	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}

	
}
