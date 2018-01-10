package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqStkInfArray   股票
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqStkInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String stkNm;          			//股票名称               		String(20)      Y
	private  String stkCd;						//股票代码       	  	String(20)      Y
	private  String issuEntpNm;					//发行企业名称                      String(100)     Y
	private  String pblsDt;						//发行日期                                String(8)       Y
	private  String stkTp;						//股票类型                                String(4)       Y
	private  String stkClCd;					//股票类别                                String(4)       Y
	private  String stkRghtCrclCd;				//股权流通情况代码              String(4)       Y
	private  double shrhldNum;					//持股数量                                Double(26,8)    Y
	private  String ccy;						//币种                                         String(10)      Y
	
	public EsbBodyWmaRqStkInfArray(){
		
	}

	public String getStkNm() {
		return stkNm;
	}

	@XmlElement(name = "StkNm")
	public void setStkNm(String stkNm) {
		this.stkNm = stkNm;
	}

	public String getStkCd() {
		return stkCd;
	}

	@XmlElement(name = "StkCd")
	public void setStkCd(String stkCd) {
		this.stkCd = stkCd;
	}

	public String getIssuEntpNm() {
		return issuEntpNm;
	}

	@XmlElement(name = "IssuEntpNm")
	public void setIssuEntpNm(String issuEntpNm) {
		this.issuEntpNm = issuEntpNm;
	}

	public String getPblsDt() {
		return pblsDt;
	}

	@XmlElement(name = "PblsDt")
	public void setPblsDt(String pblsDt) {
		this.pblsDt = pblsDt;
	}

	public String getStkTp() {
		return stkTp;
	}

	@XmlElement(name = "StkTp")
	public void setStkTp(String stkTp) {
		this.stkTp = stkTp;
	}

	public String getStkClCd() {
		return stkClCd;
	}

	@XmlElement(name = "StkClCd")
	public void setStkClCd(String stkClCd) {
		this.stkClCd = stkClCd;
	}

	public String getStkRghtCrclCd() {
		return stkRghtCrclCd;
	}

	@XmlElement(name = "StkRghtCrclCd")
	public void setStkRghtCrclCd(String stkRghtCrclCd) {
		this.stkRghtCrclCd = stkRghtCrclCd;
	}

	public double getShrhldNum() {
		return shrhldNum;
	}

	@XmlElement(name = "ShrhldNum")
	public void setShrhldNum(double shrhldNum) {
		this.shrhldNum = shrhldNum;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	
}
