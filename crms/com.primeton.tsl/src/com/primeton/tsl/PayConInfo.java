/*
 * Powered By [easy_project]
 * Since 2010 - 2015
 */

package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author easyloan 
 * @version 1.0
 * @since 1.0
 * PayConInfo  控制信息结构
 */


public class PayConInfo extends SuperBosfxRq implements Serializable {
	
	
	private BaseVO baseVO;
	
    /**
     * 日期        RCV_DATE 
     */	
	private java.lang.String rcvDate;
    /**
     * 法人代码        LEG_PER_COD 
     */	
	private java.lang.String legPerCod;
    /**
     * 区域代码        PRV_COD 
     */	
	private java.lang.String prvCod;
    /**
     * 开户机构        OPN_DEP 
     */	
	private java.lang.String opnDep;
    /**
     * 核算机构        TAL_DEP 
     */	
	private java.lang.String talDep;
    /**
     * 借据编号        DUE_NUM 
     */	
	private java.lang.String dueNum;
    /**
     * 通知书编号        TEL_NO 
     */	
	private java.lang.String telNo;
    /**
     * 事项代码        SPEC_CODE 
     */	
	private java.lang.String specCode;
    /**
     * 抵债资产编号        RPS_NUM 
     */	
	private java.lang.String rpsNum;
    /**
     * 还款款金额        PAY_AMT 
     */	
	private java.math.BigDecimal payAmt;
    /**
     * 解止付编号        FRE_AMT 
     */	
	private java.math.BigDecimal freAmt;
    /**
     * 解止付编号        STOP_PAY_NUM 
     */	
	private java.lang.String stopPayNum;
    /**
     * 还款顺序        PAY_ORDER 
     */	
	private java.lang.String payOrder;
    /**
     * 终止停息收取利息类型        RCV_ITR_TYPE 
     */	
	private java.lang.String rcvItrType;
    /**
     * 是否归还未结记利息标志        PAY_OUT_ITR_FLG 
     */	
	private java.lang.String payOutItrFlg;
    /**
     * 还款账号类型        PAY_PRIM_ACCT_TYP 
     */	
	private java.lang.String payPrimAcctTyp;
    /**
     * 还款账号        PAY_PRIM_ACCT 
     */	
	private java.lang.String payPrimAcct;
    /**
     * 还款账户名称        PAY_PRIM_NAME 
     */	
	private java.lang.String payPrimName;
    /**
     * 还款账户开户机构        PAY_OPEN_DEP 
     */	
	private java.lang.String payOpenDep;
    /**
     * 还款账户卡折标识        PAY_PRIM_ACCT_FLG 
     */	
	private java.lang.String payPrimAcctFlg;
    /**
     * 状态        DEAL_STS 
     */	
	private java.lang.String dealSts;
    /**
     * 新下发还本计划        PRIN_PLAN_FLG 
     */	
	private java.lang.String prinPlanFlg;
    /**
     * 新还款计划表中的期数        PRIN_PLAN_TERM 
     */	
	private java.lang.Integer prinPlanTerm;
    /**
     * 还本计划处理状态        PRIN_PLAN_STS 
     */	
	private java.lang.String prinPlanSts;
    /**
     * 调整贷款利息,调增调减标志        ADJ_ITR_FLG 
     */	
	private java.lang.String adjItrFlg;
    /**
     * 表内正常利息        NOR_ITR_IN 
     */	
	private java.math.BigDecimal norItrIn = new BigDecimal("0.00");;
    /**
     * 表内拖欠利息        DFT_ITR_IN 
     */	
	private java.math.BigDecimal dftItrIn = new BigDecimal("0.00");;
    /**
     * 表内罚息        PNS_ITR_IN 
     */	
	private java.math.BigDecimal pnsItrIn = new BigDecimal("0.00");;
    /**
     * 表外正常利息        NOR_ITR_OUT 
     */	
	private java.math.BigDecimal norItrOut = new BigDecimal("0.00");;
    /**
     * 表外拖欠利息        DFT_ITR_OUT 
     */	
	private java.math.BigDecimal dftItrOut = new BigDecimal("0.00");;
    /**
     * 表外罚息        PNS_ITR_OUT 
     */	
	private java.math.BigDecimal pnsItrOut = new BigDecimal("0.00");;
	/**
     * 1还本息;2提前还本金3结清4结清当前期
     */	
	private java.lang.String amtFlg;
	/**
     * 提前还本金额       PRN_AMt 
     */	
	private java.math.BigDecimal prnAmt;
	
	/**
	 * 是否插入控制表 1---不插入 2--插入
	 */
	private java.lang.String inFlag;
	
	 /**
     * 当前利息       NOR_OTD_ITR ---add lvnan 20160330
     */	
	private java.math.BigDecimal norOtdItr = new BigDecimal("0.00");;
	 /**
     * 未结计罚息       PNS_OTD_ITR ---add lvnan 20160330
     */	
	private java.math.BigDecimal pnsOtdItr = new BigDecimal("0.00");;
	
	
	public PayConInfo(){
		this.setBaseVO(new BaseVO());
	}
	
	/** 
	 * @return rcvDate 
	 */
	public java.lang.String getRcvDate() {
		return rcvDate;
	}
	
	/** 
	 * @param rcvDate 要设置的 rcvDate 
	 */
	@XmlElement(name = "RcvDate")
	public void setRcvDate(java.lang.String rcvDate) {
		this.rcvDate = rcvDate;
	}
	/** 
	 * @return legPerCod 
	 */
	public java.lang.String getLegPerCod() {
		return legPerCod;
	}
	/** 
	 * @param legPerCod 要设置的 legPerCod 
	 */
	@XmlElement(name = "LegPerCod")
	public void setLegPerCod(java.lang.String legPerCod) {
		this.legPerCod = legPerCod;
	}
	/** 
	 * @return prvCod 
	 */
	public java.lang.String getPrvCod() {
		return prvCod;
	}
	/** 
	 * @param prvCod 要设置的 prvCod 
	 */
	@XmlElement(name = "PrvCod")
	public void setPrvCod(java.lang.String prvCod) {
		this.prvCod = prvCod;
	}
	/** 
	 * @return opnDep 
	 */
	public java.lang.String getOpnDep() {
		return opnDep;
	}
	/** 
	 * @param opnDep 要设置的 opnDep 
	 */
	@XmlElement(name = "OpnDep")
	public void setOpnDep(java.lang.String opnDep) {
		this.opnDep = opnDep;
	}
	/** 
	 * @return talDep 
	 */
	public java.lang.String getTalDep() {
		return talDep;
	}
	/** 
	 * @param talDep 要设置的 talDep 
	 */
	@XmlElement(name = "TalDep")
	public void setTalDep(java.lang.String talDep) {
		this.talDep = talDep;
	}
	/** 
	 * @return dueNum 
	 */
	public java.lang.String getDueNum() {
		return dueNum;
	}
	/** 
	 * @param dueNum 要设置的 dueNum 
	 */
	@XmlElement(name = "DueNum")
	public void setDueNum(java.lang.String dueNum) {
		this.dueNum = dueNum;
	}
	/** 
	 * @return telNo 
	 */
	public java.lang.String getTelNo() {
		return telNo;
	}
	/** 
	 * @param telNo 要设置的 telNo 
	 */
	@XmlElement(name = "TelNo")
	public void setTelNo(java.lang.String telNo) {
		this.telNo = telNo;
	}
	/** 
	 * @return specCode 
	 */
	public java.lang.String getSpecCode() {
		return specCode;
	}
	/** 
	 * @param specCode 要设置的 specCode 
	 */
	@XmlElement(name = "SpecCode")
	public void setSpecCode(java.lang.String specCode) {
		this.specCode = specCode;
	}
	/** 
	 * @return rpsNum 
	 */
	public java.lang.String getRpsNum() {
		return rpsNum;
	}
	/** 
	 * @param rpsNum 要设置的 rpsNum 
	 */
	@XmlElement(name = "RpsNum")
	public void setRpsNum(java.lang.String rpsNum) {
		this.rpsNum = rpsNum;
	}
	/** 
	 * @return payAmt 
	 */
	public java.math.BigDecimal getPayAmt() {
		return payAmt;
	}
	/** 
	 * @param payAmt 要设置的 payAmt 
	 */
	@XmlElement(name = "PayAmt")
	public void setPayAmt(java.math.BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	/** 
	 * @return freAmt 
	 */
	public java.math.BigDecimal getFreAmt() {
		return freAmt;
	}
	/** 
	 * @param freAmt 要设置的 freAmt 
	 */
	@XmlElement(name = "FreAmt")
	public void setFreAmt(java.math.BigDecimal freAmt) {
		this.freAmt = freAmt;
	}
	/** 
	 * @return stopPayNum 
	 */
	public java.lang.String getStopPayNum() {
		return stopPayNum;
	}
	/** 
	 * @param stopPayNum 要设置的 stopPayNum 
	 */
	@XmlElement(name = "StopPayNum")
	public void setStopPayNum(java.lang.String stopPayNum) {
		this.stopPayNum = stopPayNum;
	}
	/** 
	 * @return payOrder 
	 */
	public java.lang.String getPayOrder() {
		return payOrder;
	}
	/** 
	 * @param payOrder 要设置的 payOrder 
	 */
	@XmlElement(name = "PayOrder")
	public void setPayOrder(java.lang.String payOrder) {
		this.payOrder = payOrder;
	}
	/** 
	 * @return rcvItrType 
	 */
	public java.lang.String getRcvItrType() {
		return rcvItrType;
	}
	/** 
	 * @param rcvItrType 要设置的 rcvItrType 
	 */
	@XmlElement(name = "RcvItrType")
	public void setRcvItrType(java.lang.String rcvItrType) {
		this.rcvItrType = rcvItrType;
	}
	/** 
	 * @return payOutItrFlg 
	 */
	public java.lang.String getPayOutItrFlg() {
		return payOutItrFlg;
	}
	/** 
	 * @param payOutItrFlg 要设置的 payOutItrFlg 
	 */
	@XmlElement(name = "PayOutItrFlg")
	public void setPayOutItrFlg(java.lang.String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
	}
	/** 
	 * @return payPrimAcctTyp 
	 */
	public java.lang.String getPayPrimAcctTyp() {
		return payPrimAcctTyp;
	}
	/** 
	 * @param payPrimAcctTyp 要设置的 payPrimAcctTyp 
	 */
	@XmlElement(name = "PayPrimAcctTyp")
	public void setPayPrimAcctTyp(java.lang.String payPrimAcctTyp) {
		this.payPrimAcctTyp = payPrimAcctTyp;
	}
	/** 
	 * @return payPrimAcct 
	 */
	public java.lang.String getPayPrimAcct() {
		return payPrimAcct;
	}
	/** 
	 * @param payPrimAcct 要设置的 payPrimAcct 
	 */
	@XmlElement(name = "PayPrimAcct")
	public void setPayPrimAcct(java.lang.String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	/** 
	 * @return payPrimName 
	 */
	public java.lang.String getPayPrimName() {
		return payPrimName;
	}
	/** 
	 * @param payPrimName 要设置的 payPrimName 
	 */
	@XmlElement(name = "PayPrimName")
	public void setPayPrimName(java.lang.String payPrimName) {
		this.payPrimName = payPrimName;
	}
	/** 
	 * @return payOpenDep 
	 */
	public java.lang.String getPayOpenDep() {
		return payOpenDep;
	}
	/** 
	 * @param payOpenDep 要设置的 payOpenDep 
	 */
	@XmlElement(name = "PayOpenDep")
	public void setPayOpenDep(java.lang.String payOpenDep) {
		this.payOpenDep = payOpenDep;
	}
	/** 
	 * @return payPrimAcctFlg 
	 */
	public java.lang.String getPayPrimAcctFlg() {
		return payPrimAcctFlg;
	}
	/** 
	 * @param payPrimAcctFlg 要设置的 payPrimAcctFlg 
	 */
	@XmlElement(name = "PayPrimAcctFlg")
	public void setPayPrimAcctFlg(java.lang.String payPrimAcctFlg) {
		this.payPrimAcctFlg = payPrimAcctFlg;
	}
	/** 
	 * @return dealSts 
	 */
	public java.lang.String getDealSts() {
		return dealSts;
	}
	/** 
	 * @param dealSts 要设置的 dealSts 
	 */
	@XmlElement(name = "DealSts")
	public void setDealSts(java.lang.String dealSts) {
		this.dealSts = dealSts;
	}
	/** 
	 * @return prinPlanFlg 
	 */
	public java.lang.String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	/** 
	 * @param prinPlanFlg 要设置的 prinPlanFlg 
	 */
	@XmlElement(name = "PrinPlanFlg")
	public void setPrinPlanFlg(java.lang.String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	/** 
	 * @return prinPlanTerm 
	 */
	public java.lang.Integer getPrinPlanTerm() {
		return prinPlanTerm;
	}
	/** 
	 * @param prinPlanTerm 要设置的 prinPlanTerm 
	 */
	@XmlElement(name = "PrinPlanTerm")
	public void setPrinPlanTerm(java.lang.Integer prinPlanTerm) {
		this.prinPlanTerm = prinPlanTerm;
	}
	/** 
	 * @return prinPlanSts 
	 */
	public java.lang.String getPrinPlanSts() {
		return prinPlanSts;
	}
	/** 
	 * @param prinPlanSts 要设置的 prinPlanSts 
	 */
	@XmlElement(name = "PrinPlanSts")
	public void setPrinPlanSts(java.lang.String prinPlanSts) {
		this.prinPlanSts = prinPlanSts;
	}
	/** 
	 * @return adjItrFlg 
	 */
	public java.lang.String getAdjItrFlg() {
		return adjItrFlg;
	}
	/** 
	 * @param adjItrFlg 要设置的 adjItrFlg 
	 */
	@XmlElement(name = "AdjItrFlg")
	public void setAdjItrFlg(java.lang.String adjItrFlg) {
		this.adjItrFlg = adjItrFlg;
	}
	/** 
	 * @return norItrIn 
	 */
	public java.math.BigDecimal getNorItrIn() {
		return norItrIn;
	}
	/** 
	 * @param norItrIn 要设置的 norItrIn 
	 */
	@XmlElement(name = "NorItrIn")
	public void setNorItrIn(java.math.BigDecimal norItrIn) {
		this.norItrIn = norItrIn;
	}
	/** 
	 * @return dftItrIn 
	 */
	public java.math.BigDecimal getDftItrIn() {
		return dftItrIn;
	}
	/** 
	 * @param dftItrIn 要设置的 dftItrIn 
	 */
	@XmlElement(name = "DftItrIn")
	public void setDftItrIn(java.math.BigDecimal dftItrIn) {
		this.dftItrIn = dftItrIn;
	}
	/** 
	 * @return pnsItrIn 
	 */
	public java.math.BigDecimal getPnsItrIn() {
		return pnsItrIn;
	}
	/** 
	 * @param pnsItrIn 要设置的 pnsItrIn 
	 */
	@XmlElement(name = "PnsItrIn")
	public void setPnsItrIn(java.math.BigDecimal pnsItrIn) {
		this.pnsItrIn = pnsItrIn;
	}
	/** 
	 * @return norItrOut 
	 */
	public java.math.BigDecimal getNorItrOut() {
		return norItrOut;
	}
	/** 
	 * @param norItrOut 要设置的 norItrOut 
	 */
	@XmlElement(name = "NorItrOut")
	public void setNorItrOut(java.math.BigDecimal norItrOut) {
		this.norItrOut = norItrOut;
	}
	/** 
	 * @return dftItrOut 
	 */
	public java.math.BigDecimal getDftItrOut() {
		return dftItrOut;
	}
	/** 
	 * @param dftItrOut 要设置的 dftItrOut 
	 */
	@XmlElement(name = "DftItrOut")
	public void setDftItrOut(java.math.BigDecimal dftItrOut) {
		this.dftItrOut = dftItrOut;
	}
	/** 
	 * @return pnsItrOut 
	 */
	public java.math.BigDecimal getPnsItrOut() {
		return pnsItrOut;
	}
	/** 
	 * @param pnsItrOut 要设置的 pnsItrOut 
	 */
	@XmlElement(name = "PnsItrOut")
	public void setPnsItrOut(java.math.BigDecimal pnsItrOut) {
		this.pnsItrOut = pnsItrOut;
	}
	
	/** 
	 * @return amtFlg 
	 */
	public java.lang.String getAmtFlg() {
		return amtFlg;
	}

	/** 
	 * @param amtFlg 要设置的 amtFlg 
	 */
	@XmlElement(name = "AmtFlg")
	public void setAmtFlg(java.lang.String amtFlg) {
		this.amtFlg = amtFlg;
	}

	/** 
	 * @return prnAmt 
	 */
	public java.math.BigDecimal getPrnAmt() {
		return prnAmt;
	}

	/** 
	 * @param prnAmt 要设置的 prnAmt 
	 */
	@XmlElement(name = "PrnAmt")
	public void setPrnAmt(java.math.BigDecimal prnAmt) {
		this.prnAmt = prnAmt;
	}

	public java.lang.String getInFlag() {
		return inFlag;
	}
	
	@XmlElement(name = "InFlag")
	public void setInFlag(java.lang.String inFlag) {
		this.inFlag = inFlag;
	}
	
	
	public java.math.BigDecimal getNorOtdItr() {
		return norOtdItr;
	}
	
	@XmlElement(name = "NorOtdItr")
	public void setNorOtdItr(java.math.BigDecimal norOtdItr) {
		this.norOtdItr = norOtdItr;
	}

	public java.math.BigDecimal getPnsOtdItr() {
		return pnsOtdItr;
	}

	@XmlElement(name = "PnsOtdItr")
	public void setPnsOtdItr(java.math.BigDecimal pnsOtdItr) {
		this.pnsOtdItr = pnsOtdItr;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "PayConInfo [adjItrFlg=" + adjItrFlg + ", amtFlg=" + amtFlg
				+ ", baseVO=" + baseVO + ", dealSts=" + dealSts + ", dftItrIn="
				+ dftItrIn + ", dftItrOut=" + dftItrOut + ", dueNum=" + dueNum
				+ ", freAmt=" + freAmt + ", legPerCod=" + legPerCod
				+ ", norItrIn=" + norItrIn + ", norItrOut=" + norItrOut
				+ ", opnDep=" + opnDep + ", payAmt=" + payAmt + ", payOpenDep="
				+ payOpenDep + ", payOrder=" + payOrder + ", payOutItrFlg="
				+ payOutItrFlg + ", payPrimAcct=" + payPrimAcct
				+ ", payPrimAcctFlg=" + payPrimAcctFlg + ", payPrimAcctTyp="
				+ payPrimAcctTyp + ", payPrimName=" + payPrimName
				+ ", pnsItrIn=" + pnsItrIn + ", pnsItrOut=" + pnsItrOut
				+ ", prinPlanFlg=" + prinPlanFlg + ", prinPlanSts="
				+ prinPlanSts + ", prinPlanTerm=" + prinPlanTerm + ", prnAmt="
				+ prnAmt + ", prvCod=" + prvCod + ", rcvDate=" + rcvDate
				+ ", rcvItrType=" + rcvItrType + ", rpsNum=" + rpsNum
				+ ", specCode=" + specCode + ", stopPayNum=" + stopPayNum
				+ ", talDep=" + talDep + ", telNo=" + telNo + "]";
	}

	
}

