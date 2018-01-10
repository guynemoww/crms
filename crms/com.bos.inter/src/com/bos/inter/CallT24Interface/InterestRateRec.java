package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

/**
 * T24公共接口类--利率
 * 
 * @author liyin_s
 * 
 */
public class InterestRateRec {
	@XmlElement(name="InterestRate")
	public String InterestRate;

	public void setInterestRate(String interestRate) {
		InterestRate = interestRate;
	}
}
