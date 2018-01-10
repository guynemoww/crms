package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqFndInfArray   基金
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqFndInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String fndNm;          			//基金名称               		String(20)      Y
	private  String fndCd;						//基金代码       	  	String(20)      Y
	private  String issuEntpNm;					//发行企业名称                      String(100)     Y
	private  String pblsDt;						//发行日期                               String(8)       N
	private  String fndClCd;					//基金类别                               String(4)       Y
	private  String fndTp;						//基金类型                  	String(4)       Y
	private  String crclFlag;					//流通标志                               String(4)       Y
	private  String ccy;					    //币种                                        String(10)      Y
	
	public EsbBodyWmaRqFndInfArray(){
		
	}

	public String getFndNm() {
		return fndNm;
	}


	@XmlElement(name = "FndNm")
	public void setFndNm(String fndNm) {
		this.fndNm = fndNm;
	}

	public String getFndCd() {
		return fndCd;
	}

	@XmlElement(name = "FndCd")
	public void setFndCd(String fndCd) {
		this.fndCd = fndCd;
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

	public String getFndClCd() {
		return fndClCd;
	}

	@XmlElement(name = "FndClCd")
	public void setFndClCd(String fndClCd) {
		this.fndClCd = fndClCd;
	}

	public String getFndTp() {
		return fndTp;
	}

	@XmlElement(name = "FndTp")
	public void setFndTp(String fndTp) {
		this.fndTp = fndTp;
	}

	public String getCrclFlag() {
		return crclFlag;
	}

	@XmlElement(name = "CrclFlag")
	public void setCrclFlag(String crclFlag) {
		this.crclFlag = crclFlag;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	
}
