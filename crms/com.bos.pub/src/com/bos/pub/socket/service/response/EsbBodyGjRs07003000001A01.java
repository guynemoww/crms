package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRs07003000001A01 
 * @Description: 07003000001汇率牌价查询		01汇率查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRs07003000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ccyTp;	  //货币种类
	private double CrnExcRate;//当前汇率
	
	public EsbBodyGjRs07003000001A01(){
		
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getCrnExcRate() {
		return CrnExcRate;
	}

	@XmlElement(name = "CrnExcRate")
	public void setCrnExcRate(double crnExcRate) {
		CrnExcRate = crnExcRate;
	}
}
