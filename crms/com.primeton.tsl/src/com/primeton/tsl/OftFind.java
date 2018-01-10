package com.primeton.tsl;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
* @ClassName: OftFind 
* @Description: 查询信息项结构
* @author GIT-lvnan
* @date 2015-5-7 下午10:43:29 
*
 */
public class OftFind extends SuperBosfxRq implements java.io.Serializable{
	
	private static final long serialVersionUID = 7971537586248856317L;

	private BaseVO baseVO;
	private String dueNum;//借据编号
	
	/**
     * 剩余本金 
     */	
	
	private java.math.BigDecimal prnBal = new BigDecimal("0.00");
	
	/**
	 * 正常利息
	 */
	private java.math.BigDecimal norItr = new BigDecimal("0.00");

	/**
	 * 拖欠利息
	 */
	private java.math.BigDecimal dftItr = new BigDecimal("0.00");

	/**
	 * 罚息
	 */
	private java.math.BigDecimal pnsItr = new BigDecimal("0.00");

	/**
	 * 减值准备
	 */
	private java.math.BigDecimal acrItr = new BigDecimal("0.00");

	
	public BaseVO getBaseVO() {
		return baseVO;
	}

	public void setBaseVO(BaseVO baseVO) {
		this.baseVO = baseVO;
	}

	public String getDueNum() {
		return dueNum;
	}

	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	

	public OftFind(){
	}

	public java.math.BigDecimal getPrnBal() {
		return prnBal;
	}

	public void setPrnBal(java.math.BigDecimal prnBal) {
		this.prnBal = prnBal;
	}

	public java.math.BigDecimal getNorItr() {
		return norItr;
	}

	public void setNorItr(java.math.BigDecimal norItr) {
		this.norItr = norItr;
	}

	public java.math.BigDecimal getDftItr() {
		return dftItr;
	}

	public void setDftItr(java.math.BigDecimal dftItr) {
		this.dftItr = dftItr;
	}

	public java.math.BigDecimal getPnsItr() {
		return pnsItr;
	}

	public void setPnsItr(java.math.BigDecimal pnsItr) {
		this.pnsItr = pnsItr;
	}

	public java.math.BigDecimal getAcrItr() {
		return acrItr;
	}

	public void setAcrItr(java.math.BigDecimal acrItr) {
		this.acrItr = acrItr;
	}

	@Override
	public String toString() {
		return "OftFind [acrItr=" + acrItr + ", baseVO=" + baseVO + ", dftItr="
				+ dftItr + ", dueNum=" + dueNum + ", norItr=" + norItr
				+ ", pnsItr=" + pnsItr + ", prnBal=" + prnBal  + "]";
	}

	
}
