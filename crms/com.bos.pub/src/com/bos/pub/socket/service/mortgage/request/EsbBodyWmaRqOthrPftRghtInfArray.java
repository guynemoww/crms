package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqOthrPftRghtInfArray   其他收益权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqOthrPftRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String rghtStrtDt;          			//权利起始日              	    String(8)       Y
	private  String rghtEndDt;						//权利终止日       	    	String(8)       Y
	
	public EsbBodyWmaRqOthrPftRghtInfArray(){
		
	}

	public String getRghtStrtDt() {
		return rghtStrtDt;
	}

	@XmlElement(name = "RghtStrtDt")
	public void setRghtStrtDt(String rghtStrtDt) {
		this.rghtStrtDt = rghtStrtDt;
	}

	public String getRghtEndDt() {
		return rghtEndDt;
	}

	@XmlElement(name = "RghtEndDt")
	public void setRghtEndDt(String rghtEndDt) {
		this.rghtEndDt = rghtEndDt;
	}

	
}
