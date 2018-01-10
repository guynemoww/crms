package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqVhclInfArray   交通工具
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqVhclInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String purchsCtrNo;          	//购买合同编号               String(20)    N
	private  String invNo;					//发票号       	      String(20)    N
	private  String complnDocNo;			//合格证编号                    String(20)    Y
	private  String engNo;					//发动机编号                    String(20)    Y
	private  String licPltNo;				//车牌号                  	  String(20)    N
	private  String ccy;					//币种                                 String(10)    Y
	private  double buyCostAmt;				//购入价格                       Double(26,8)  Y
	private  String oprCtfNo;				//营运证编号                     String(20)    N
	private  String oprCtfIssuOfficNm;		//营运证发证机关名称    String(200)   N
	private  double cryCpyNum;				//载重数                              Double(16,8)  N
	
	public EsbBodyWmaRqVhclInfArray(){
		
	}

	public String getPurchsCtrNo() {
		return purchsCtrNo;
	}

	@XmlElement(name = "PurchsCtrNo")
	public void setPurchsCtrNo(String purchsCtrNo) {
		this.purchsCtrNo = purchsCtrNo;
	}

	public String getInvNo() {
		return invNo;
	}

	@XmlElement(name = "InvNo")
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getComplnDocNo() {
		return complnDocNo;
	}

	@XmlElement(name = "ComplnDocNo")
	public void setComplnDocNo(String complnDocNo) {
		this.complnDocNo = complnDocNo;
	}

	public String getEngNo() {
		return engNo;
	}

	@XmlElement(name = "EngNo")
	public void setEngNo(String engNo) {
		this.engNo = engNo;
	}

	public String getLicPltNo() {
		return licPltNo;
	}

	@XmlElement(name = "LicPltNo")
	public void setLicPltNo(String licPltNo) {
		this.licPltNo = licPltNo;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getBuyCostAmt() {
		return buyCostAmt;
	}

	@XmlElement(name = "BuyCostAmt")
	public void setBuyCostAmt(double buyCostAmt) {
		this.buyCostAmt = buyCostAmt;
	}

	public String getOprCtfNo() {
		return oprCtfNo;
	}

	@XmlElement(name = "OprCtfNo")
	public void setOprCtfNo(String oprCtfNo) {
		this.oprCtfNo = oprCtfNo;
	}

	public String getOprCtfIssuOfficNm() {
		return oprCtfIssuOfficNm;
	}

	@XmlElement(name = "OprCtfIssuOfficNm")
	public void setOprCtfIssuOfficNm(String oprCtfIssuOfficNm) {
		this.oprCtfIssuOfficNm = oprCtfIssuOfficNm;
	}

	public double getCryCpyNum() {
		return cryCpyNum;
	}

	@XmlElement(name = "CryCpyNum")
	public void setCryCpyNum(double cryCpyNum) {
		this.cryCpyNum = cryCpyNum;
	}
}
