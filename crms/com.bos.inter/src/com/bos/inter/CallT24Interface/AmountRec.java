package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

/**
 * T24公共接口类--金额
 * 
 * @author liyin_s
 * 
 */
public class AmountRec {
	@XmlElement(name="Amount")
	public String Amount;

	public void setAmount(String amount) {
		Amount = amount;
	}

}
