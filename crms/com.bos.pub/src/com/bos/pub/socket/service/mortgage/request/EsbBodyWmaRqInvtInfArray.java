package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqInvtInfArray   存货
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqInvtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String invtListNo;          	//存货单号              String(20)      Y
	private  double invtNum;				//存货数量              Double(16,8)    Y
	private  String invtUnitNm;				//存货单位              String(20)      Y
	private  String buyDt;				    //购入日期               String(8)       N
	private  String ccy;					//币种                       String(10)      Y
	private  double buyCostAmt;				//购入价格               Double(26,8)    Y
	private  String regInstNm;				//监管机构名称      String(200)     Y
	
	public EsbBodyWmaRqInvtInfArray(){
		
	}
	

	
	public String getInvtListNo() {
		return invtListNo;
	}


	@XmlElement(name = "InvtListNo")
	public void setInvtListNo(String invtListNo) {
		this.invtListNo = invtListNo;
	}



	public double getInvtNum() {
		return invtNum;
	}


	@XmlElement(name = "InvtNum")
	public void setInvtNum(double invtNum) {
		this.invtNum = invtNum;
	}



	public String getInvtUnitNm() {
		return invtUnitNm;
	}


	@XmlElement(name = "InvtUnitNm")
	public void setInvtUnitNm(String invtUnitNm) {
		this.invtUnitNm = invtUnitNm;
	}



	public String getBuyDt() {
		return buyDt;
	}


	@XmlElement(name = "BuyDt")
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}



	public String getCcy() {
		return ccy;
	}


	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}



	public String getRegInstNm() {
		return regInstNm;
	}


	@XmlElement(name = "RegInstNm")
	public void setRegInstNm(String regInstNm) {
		this.regInstNm = regInstNm;
	}



	public double getBuyCostAmt() {
		return buyCostAmt;
	}


	@XmlElement(name = "BuyCostAmt")
	public void setBuyCostAmt(double buyCostAmt) {
		this.buyCostAmt = buyCostAmt;
	}
	

}
