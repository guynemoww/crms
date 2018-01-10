package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

public class TStLdRpmAaaRec {
	@XmlElement(name="SchType")
	public String SchType;            //计划类型
    @XmlElement(name="BeginDt")
	public String	BeginDt	;    	  //日期
    @XmlElement(name="LsdAmount")
	public String LsdAmount;           //金额
    @XmlElement(name="Rate")
	public String	Rate	;          //利率
    /**
	 * @param BeginDt 要设置的 BeginDt
	 */
	public void setBeginDt(String beginDt) {
		BeginDt = beginDt;
	}
	/**
	 * @param LsdAmount 要设置的 LsdAmount
	 */
	public void setLsdAmount(String lsdAmount) {
		LsdAmount = lsdAmount;
	}
	/**
	 * @param Rate 要设置的 Rate
	 */
	public void setRate(String rate) {
		Rate = rate;
	}
	/**
	 * @param SchType 要设置的 SchType
	 */
	public void setSchType(String schType) {
		SchType = schType;
	}
}
