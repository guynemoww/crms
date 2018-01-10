package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqOthrMrtgAstInfArray   其他抵押资产
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqOthrMrtgAstInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  double astNum;						//资产数量                       Double(16,8)     N
	private  String astNumUnitNm;				//资产数量单位              String(20)       N
	private  String astAdr;				    	//资产所在地                   String(200)      N
	
	
	public EsbBodyWmaRqOthrMrtgAstInfArray(){
		
	}
	
	public double getAstNum() {
		return astNum;
	}

	@XmlElement(name = "AstNum")
	public void setAstNum(double astNum) {
		this.astNum = astNum;
	}

	public String getAstNumUnitNm() {
		return astNumUnitNm;
	}

	@XmlElement(name = "AstNumUnitNm")
	public void setAstNumUnitNm(String astNumUnitNm) {
		this.astNumUnitNm = astNumUnitNm;
	}

	public String getAstAdr() {
		return astAdr;
	}

	@XmlElement(name = "AstAdr")
	public void setAstAdr(String astAdr) {
		this.astAdr = astAdr;
	}

}
