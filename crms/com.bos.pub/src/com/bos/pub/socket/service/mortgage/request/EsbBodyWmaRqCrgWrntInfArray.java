package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqCrgWrntInfArray   货权类仓单
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqCrgWrntInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String strgInstNm;          	//仓储机构名称               	String(100)      Y
	private  String wrntNo;					//仓单号码       	    String(20)       Y
	private  String strgNm;					//仓库名称                               String(100)      Y
	private  String ccy;					//币种                                        String(10)       Y
	private  double wrntAmt;				//仓单金额                 	Double(26,8)     Y
	private  String gdsTp;					//货物类别                               String(20)       Y
	private  String gdsUnitTp;				//货物单位                               String(20)       Y
	private  String gdsNum;					//货物数量                               String(10)       Y
	private  String inStrgDt;				//入库日期                               String(8)        Y
	
	public EsbBodyWmaRqCrgWrntInfArray(){
		
	}

	public String getStrgInstNm() {
		return strgInstNm;
	}

	@XmlElement(name = "StrgInstNm")
	public void setStrgInstNm(String strgInstNm) {
		this.strgInstNm = strgInstNm;
	}

	public String getWrntNo() {
		return wrntNo;
	}

	@XmlElement(name = "WrntNo")
	public void setWrntNo(String wrntNo) {
		this.wrntNo = wrntNo;
	}

	public String getStrgNm() {
		return strgNm;
	}

	@XmlElement(name = "StrgNm")
	public void setStrgNm(String strgNm) {
		this.strgNm = strgNm;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getWrntAmt() {
		return wrntAmt;
	}

	@XmlElement(name = "WrntAmt")
	public void setWrntAmt(double wrntAmt) {
		this.wrntAmt = wrntAmt;
	}

	public String getGdsTp() {
		return gdsTp;
	}

	@XmlElement(name = "GdsTp")
	public void setGdsTp(String gdsTp) {
		this.gdsTp = gdsTp;
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

	
	public String getInStrgDt() {
		return inStrgDt;
	}

	@XmlElement(name = "InStrgDt")
	public void setInStrgDt(String inStrgDt) {
		this.inStrgDt = inStrgDt;
	}

	
}
