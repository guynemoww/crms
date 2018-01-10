package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

/**
 *  一般贷款发放相应报文属性
 * @author liyin_s
 *
 */
public class OverRideRec {
	@XmlElement(name="OverRide")
	public String overRide;
	
	public void setOverRide(String overRide) {
		this.overRide = overRide;
	}

}
