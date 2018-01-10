package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: PrnItrVo 
* @Description: 调整利息
* @author GIT-git
* @date 2015-5-12 下午05:33:26 
*
 */
public class PrnItrVo  extends SuperBosfxRq implements Serializable{

	/**
	 * add lvnan 20160330
	 */
	private static final long serialVersionUID = -734126461233599673L;
	private String dueNum;//借据编号
	private String opnDep;//开户机构
	private String brwName;//客户名称
	private BigDecimal resPrn;//剩余正常本金
	private BigDecimal resAllPrn;//剩余本金
	private BigDecimal dftPrn;//拖欠本金
	private BigDecimal currPrn;//当前期本金
	private BigDecimal norItr;//正常利息
	private BigDecimal dftItr;//拖欠利息
	private BigDecimal pnsItr;//罚息	
	private BigDecimal currOtdItr; //未结计正常利息（不含贴心息）
	private BigDecimal pnsOtdItr; //未结计罚息
	private BigDecimal currItrInDisc; //未结计正常利息（含贴心息）
	
	
	public PrnItrVo(){
		setBaseVO(new BaseVO());
	}


	/** 
	 * @return dueNum 
	 */
	public String getDueNum() {
		return dueNum;
	}



	/** 
	 * @param dueNum 要设置的 dueNum 
	 */
	@XmlElement(name = "DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}




	public String getOpnDep() {
		return opnDep;
	}

	@XmlElement(name = "OpnDep")
	public void setOpnDep(String opnDep) {
		this.opnDep = opnDep;
	}


	/** 
	 * @return brwName 
	 */
	
	public String getBrwName() {
		return brwName;
	}




	/** 
	 * @param brwName 要设置的 brwName 
	 */
	@XmlElement(name = "BrwName")
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}



	/** 
	 * @return resPrn 
	 */
	public BigDecimal getResPrn() {
		return resPrn;
	}



	/** 
	 * @param resPrn 要设置的 resPrn 
	 */
	@XmlElement(name = "ResPrn")
	public void setResPrn(BigDecimal resPrn) {
		this.resPrn = resPrn;
	}


	/** 
	 * @return resAllPrn 
	 */
	public BigDecimal getResAllPrn() {
		return resAllPrn;
	}


	/** 
	 * @param resAllPrn 要设置的 resAllPrn 
	 */
	@XmlElement(name = "ResAllPrn")
	public void setResAllPrn(BigDecimal resAllPrn) {
		this.resAllPrn = resAllPrn;
	}


	/** 
	 * @return dftPrn 
	 */
	public BigDecimal getDftPrn() {
		return dftPrn;
	}


	/** 
	 * @param dftPrn 要设置的 dftPrn 
	 */
	@XmlElement(name = "DftPrn")
	public void setDftPrn(BigDecimal dftPrn) {
		this.dftPrn = dftPrn;
	}




	/** 
	 * @return norItr 
	 */
	public BigDecimal getNorItr() {
		return norItr;
	}




	/** 
	 * @param norItr 要设置的 norItr 
	 */
	@XmlElement(name = "NorItr")
	public void setNorItr(BigDecimal norItr) {
		this.norItr = norItr;
	}




	/** 
	 * @return dftItr 
	 */
	public BigDecimal getDftItr() {
		return dftItr;
	}




	/** 
	 * @param dftItr 要设置的 dftItr 
	 */
	@XmlElement(name = "DftItr")
	public void setDftItr(BigDecimal dftItr) {
		this.dftItr = dftItr;
	}




	/** 
	 * @return pnsItr 
	 */
	public BigDecimal getPnsItr() {
		return pnsItr;
	}




	/** 
	 * @param pnsItr 要设置的 pnsItr 
	 */
	@XmlElement(name = "PnsItr")
	public void setPnsItr(BigDecimal pnsItr) {
		this.pnsItr = pnsItr;
	}


	/**
	 * 
	 * @return  pnsOtdItr
	 */

	public BigDecimal getPnsOtdItr() {
		return pnsOtdItr;
	}


	/**
	 * 
	 * @param pnsOtdItr 要设置 pnsOtdItr
	 */
	@XmlElement(name = "PnsOtdItr")
	public void setPnsOtdItr(BigDecimal pnsOtdItr) {
		this.pnsOtdItr = pnsOtdItr;
	}

	/**
	 * 
	 * @return  currPrn
	 */
	public BigDecimal getCurrPrn() {
		return currPrn;
	}


	/**
	 * 
	 * @param currPrn 要设置 currPrn
	 */
	@XmlElement(name = "CurrPrn")
	public void setCurrPrn(BigDecimal currPrn) {
		this.currPrn = currPrn;
	}

	/**
	 * 
	 * @return  currOtdItr
	 */
	public BigDecimal getCurrOtdItr() {
		return currOtdItr;
	}


	/**
	 * 
	 * @param currOtdItr 要设置 currOtdItr
	 */
	@XmlElement(name = "CurrOtdItr")
	public void setCurrOtdItr(BigDecimal currOtdItr) {
		this.currOtdItr = currOtdItr;
	}



	/**
	 * 
	 * @return  currItrInDisc
	 */

	public BigDecimal getCurrItrInDisc() {
		return currItrInDisc;
	}

	/**
	 * 
	 * @param currItrInDisc 要设置 currItrInDisc
	 */
	@XmlElement(name = "CurrItrInDisc")
	public void setCurrItrInDisc(BigDecimal currItrInDisc) {
		this.currItrInDisc = currItrInDisc;
	}


	@Override
	public String toString() {
		return "PrnItrVo [brwName=" + brwName + ", currItrInDisc="
				+ currItrInDisc + ", currOtdItr=" + currOtdItr + ", currPrn="
				+ currPrn + ", dftItr=" + dftItr + ", dftPrn=" + dftPrn
				+ ", dueNum=" + dueNum + ", norItr=" + norItr + ", opnDep="
				+ opnDep + ", pnsItr=" + pnsItr + ", pnsOtdItr=" + pnsOtdItr
				+ ", resAllPrn=" + resAllPrn + ", resPrn=" + resPrn + "]";
	}
	
	
}
