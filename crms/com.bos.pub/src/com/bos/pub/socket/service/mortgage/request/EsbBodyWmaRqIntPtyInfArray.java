package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqIntPtyInfArray   知识产权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqIntPtyInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String intPtyCtfNo;          			//知识产权证书号               	    String(20)       Y
	private  String intPtyCtfIssuOfficNm;			//知识产权证书发证机构名称       	String(80)       Y
	private  String intPtyProtEndDt;				//知识产权保护届满日期                  String(8)        Y
	private  String intPtyAgLmt;					//知识产权年限                                    String(30)       Y
	
	public EsbBodyWmaRqIntPtyInfArray(){
		
	}

	public String getIntPtyCtfNo() {
		return intPtyCtfNo;
	}

	@XmlElement(name = "IntPtyCtfNo")
	public void setIntPtyCtfNo(String intPtyCtfNo) {
		this.intPtyCtfNo = intPtyCtfNo;
	}

	public String getIntPtyCtfIssuOfficNm() {
		return intPtyCtfIssuOfficNm;
	}

	@XmlElement(name = "IntPtyCtfIssuOfficNm")
	public void setIntPtyCtfIssuOfficNm(String intPtyCtfIssuOfficNm) {
		this.intPtyCtfIssuOfficNm = intPtyCtfIssuOfficNm;
	}

	public String getIntPtyProtEndDt() {
		return intPtyProtEndDt;
	}

	@XmlElement(name = "IntPtyProtEndDt")
	public void setIntPtyProtEndDt(String intPtyProtEndDt) {
		this.intPtyProtEndDt = intPtyProtEndDt;
	}

	public String getIntPtyAgLmt() {
		return intPtyAgLmt;
	}

	@XmlElement(name = "IntPtyAgLmt")
	public void setIntPtyAgLmt(String intPtyAgLmt) {
		this.intPtyAgLmt = intPtyAgLmt;
	}

	
}
