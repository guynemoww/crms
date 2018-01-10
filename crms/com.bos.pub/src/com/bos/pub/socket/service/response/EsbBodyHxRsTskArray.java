package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyHxRsTskArray 
 * @Description: 01001000002通用核心记账		03表外账记账
 *
 */
public class EsbBodyHxRsTskArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ccyTp;		//货币种类		String(3)			
	private String ccy;			//币种		String(10)			
	private double oBSDbAmt;	//表外借方金额	Double(15,2)
	
	public EsbBodyHxRsTskArray(){
		
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getoBSDbAmt() {
		return oBSDbAmt;
	}

	@XmlElement(name = "OBSDbAmt")
	public void setoBSDbAmt(double oBSDbAmt) {
		this.oBSDbAmt = oBSDbAmt;
	}
}
