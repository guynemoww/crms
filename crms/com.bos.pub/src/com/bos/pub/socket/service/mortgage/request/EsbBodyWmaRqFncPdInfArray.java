package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqFncPdInfArray   理财产品
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqFncPdInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String pdNm;          			//产品名称               		String(40)      Y
	private  String fncAgrmNo;				//理财协议编号       	  	String(20)      Y
	private  String ittDt;					//起始日期                               String(8)       Y
	private  String expDt;					//到期日                                   String(8)       Y
	private  double expcPftRate;			//预期收益率                          Double(16,8)    Y
	private  String ccy;					//币种                                        String(10)      Y
	private  double buyAmt;				    //购买金额                  	Double(20,8)    Y
	private  String incmTp;					//收益类型                               String(4)       Y
	private  String fncAcctNo;				//理财账户代号                      String(35)      Y
	private  String pdRskLvl;				//产品等级                      String(1)       Y
	
	public EsbBodyWmaRqFncPdInfArray(){
		
	}

	public String getPdNm() {
		return pdNm;
	}

	@XmlElement(name = "PdNm")
	public void setPdNm(String pdNm) {
		this.pdNm = pdNm;
	}

	public String getFncAgrmNo() {
		return fncAgrmNo;
	}

	@XmlElement(name = "FncAgrmNo")
	public void setFncAgrmNo(String fncAgrmNo) {
		this.fncAgrmNo = fncAgrmNo;
	}

	public String getIttDt() {
		return ittDt;
	}

	@XmlElement(name = "IttDt")
	public void setIttDt(String ittDt) {
		this.ittDt = ittDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public double getExpcPftRate() {
		return expcPftRate;
	}

	@XmlElement(name = "ExpcPftRate")
	public void setExpcPftRate(double expcPftRate) {
		this.expcPftRate = expcPftRate;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getBuyAmt() {
		return buyAmt;
	}

	@XmlElement(name = "BuyAmt")
	public void setBuyAmt(double buyAmt) {
		this.buyAmt = buyAmt;
	}

	public String getIncmTp() {
		return incmTp;
	}

	@XmlElement(name = "IncmTp")
	public void setIncmTp(String incmTp) {
		this.incmTp = incmTp;
	}

	public String getFncAcctNo() {
		return fncAcctNo;
	}

	@XmlElement(name = "FncAcctNo")
	public void setFncAcctNo(String fncAcctNo) {
		this.fncAcctNo = fncAcctNo;
	}

	public String getPdRskLvl() {
		return pdRskLvl;
	}

	@XmlElement(name = "PdRskLvl")
	public void setPdRskLvl(String pdRskLvl) {
		this.pdRskLvl = pdRskLvl;
	}

	
}
