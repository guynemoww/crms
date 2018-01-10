package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqOthrPlgAstInfArray   其他质押资产
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqOthrPlgAstInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String astNm;						//资产名称                      String(50)       Y
	private  String ccy;						//币种                                String(10)       Y
	private  double AstValAmt;					//资产价值                       Double(26,8)     Y
	private  String astNumUnitNm;				//资产数量单位              String(20)       Y
	private  double astNum;						//资产数量                       Double(16,8)     Y
	
	
	public EsbBodyWmaRqOthrPlgAstInfArray(){
		
	}


	public String getAstNm() {
		return astNm;
	}

	@XmlElement(name = "AstNm")
	public void setAstNm(String astNm) {
		this.astNm = astNm;
	}


	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public double getAstValAmt() {
		return AstValAmt;
	}

	@XmlElement(name = "AstValAmt")
	public void setAstValAmt(double astValAmt) {
		AstValAmt = astValAmt;
	}


	public String getAstNumUnitNm() {
		return astNumUnitNm;
	}

	@XmlElement(name = "AstNumUnitNm")
	public void setAstNumUnitNm(String astNumUnitNm) {
		this.astNumUnitNm = astNumUnitNm;
	}


	public double getAstNum() {
		return astNum;
	}

	@XmlElement(name = "AstNum")
	public void setAstNum(double astNum) {
		this.astNum = astNum;
	}
	
	
}
