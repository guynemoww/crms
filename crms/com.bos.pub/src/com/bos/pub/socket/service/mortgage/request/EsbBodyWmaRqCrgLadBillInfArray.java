package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqCrgWrntInfArray   货权类提单
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqCrgLadBillInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String ladBillNo;          	//提单号码               	    String(20)       Y
	private  String gdsNm;					//物品名称       	    String(12)       Y
	private  String gdsUnitTp;				//货物单位                               String(20)       Y
	private  String gdsNum;					//货物数量                               String(10)       Y
	private  String ccy;					//币种                                        String(10)       Y
	private  double ladBillAmt;				//提单金额                 	Double(26,8)     Y
	private  String dlvDt;					//发货日期                               String(8)        Y
	private  String pckUpDt;				//提货日期                               String(8)        Y
	private  String fwdrNm;					//提货人名称                           String(30)       Y
	private  String ivntAdr;				//存货地址                                String(100)      Y
	
	public EsbBodyWmaRqCrgLadBillInfArray(){
		
	}

	public String getLadBillNo() {
		return ladBillNo;
	}

	@XmlElement(name = "LadBillNo")
	public void setLadBillNo(String ladBillNo) {
		this.ladBillNo = ladBillNo;
	}

	public String getGdsNm() {
		return gdsNm;
	}

	@XmlElement(name = "GdsNm")
	public void setGdsNm(String gdsNm) {
		this.gdsNm = gdsNm;
	}

	public String getGdsUnitTp() {
		return gdsUnitTp;
	}

	@XmlElement(name = "GdsUnitTp")
	public void setGdsUnitTp(String gdsUnitTp) {
		this.gdsUnitTp = gdsUnitTp;
	}

	public String getGdsNum() {
		return gdsNum;
	}

	@XmlElement(name = "GdsNum")
	public void setGdsNum(String gdsNum) {
		this.gdsNum = gdsNum;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getLadBillAmt() {
		return ladBillAmt;
	}

	@XmlElement(name = "LadBillAmt")
	public void setLadBillAmt(double ladBillAmt) {
		this.ladBillAmt = ladBillAmt;
	}

	public String getDlvDt() {
		return dlvDt;
	}

	@XmlElement(name = "DlvDt")
	public void setDlvDt(String dlvDt) {
		this.dlvDt = dlvDt;
	}

	public String getPckUpDt() {
		return pckUpDt;
	}

	@XmlElement(name = "PckUpDt")
	public void setPckUpDt(String pckUpDt) {
		this.pckUpDt = pckUpDt;
	}

	public String getFwdrNm() {
		return fwdrNm;
	}

	@XmlElement(name = "FwdrNm")
	public void setFwdrNm(String fwdrNm) {
		this.fwdrNm = fwdrNm;
	}

	public String getIvntAdr() {
		return ivntAdr;
	}

	@XmlElement(name = "IvntAdr")
	public void setIvntAdr(String ivntAdr) {
		this.ivntAdr = ivntAdr;
	}

	
}
