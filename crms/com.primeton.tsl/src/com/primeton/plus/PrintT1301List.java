package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 凭证补打
 * 
 * @author MJF
 * 
 */
public class PrintT1301List implements Serializable {
	private static final long serialVersionUID = 7620653006262909756L;
	private String rcvDate;// 交易日期
	private String opnDep;// 开户机构
	private String dueNum;// 借据编号
	private String brwName;// 客户名称
	private BigDecimal amt;// 放款/还款金额（元）
	private String payTyp;// 交易类型
	private String begDate;// 发放日期
	private String endDate;// 到期日期
	private BigDecimal padUpPrn;// 还本金额
	private BigDecimal padUpPentPrn;// 提前还本金额
	private BigDecimal padUpNorItrIn;// 归还表内正常利息金额
	private BigDecimal padUpDftItrIn;// 归还表内拖欠利息金额
	private BigDecimal padUpPnsItrIn;// 归还表内罚息
	private BigDecimal padUpCpdItrIn;// 归还表内复利
	private BigDecimal padUpNorItrOut;// 归还表外正常利息金额
	private BigDecimal padUpDftItrOut;// 归还表外拖欠利息金额
	private BigDecimal padUpPnsItrOut;// 归还表外罚息
	private BigDecimal padUpCpdItrOut;// 归还表外复利
	private BigDecimal padUpPentIcm;// 提前还款违约金
	private BigDecimal padUpOftPrn;// 核销收回本金
	private BigDecimal padUpOftItr;// 核销收回利息
	private BigDecimal oftAcrItrBal;// 核销收回应计利息
	private BigDecimal padUpDiscAmt;// 归还贴息金额
	private String voucherType;// 凭证类型
	private String summary;// 摘要
	private Long stan;// 流水号
	private String opr;// 操作员

	public PrintT1301List() {

	}

	public String getRcvDate() {
		return rcvDate;
	}

	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	public String getOpnDep() {
		return opnDep;
	}

	public void setOpnDep(String opnDep) {
		this.opnDep = opnDep;
	}

	public String getDueNum() {
		return dueNum;
	}

	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public String getBrwName() {
		return brwName;
	}

	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getPayTyp() {
		return payTyp;
	}

	public void setPayTyp(String payTyp) {
		this.payTyp = payTyp;
	}

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPadUpPrn() {
		return padUpPrn;
	}

	public void setPadUpPrn(BigDecimal padUpPrn) {
		this.padUpPrn = padUpPrn;
	}

	public BigDecimal getPadUpPentPrn() {
		return padUpPentPrn;
	}

	public void setPadUpPentPrn(BigDecimal padUpPentPrn) {
		this.padUpPentPrn = padUpPentPrn;
	}

	public BigDecimal getPadUpNorItrIn() {
		return padUpNorItrIn;
	}

	public void setPadUpNorItrIn(BigDecimal padUpNorItrIn) {
		this.padUpNorItrIn = padUpNorItrIn;
	}

	public BigDecimal getPadUpDftItrIn() {
		return padUpDftItrIn;
	}

	public void setPadUpDftItrIn(BigDecimal padUpDftItrIn) {
		this.padUpDftItrIn = padUpDftItrIn;
	}

	public BigDecimal getPadUpPnsItrIn() {
		return padUpPnsItrIn;
	}

	public void setPadUpPnsItrIn(BigDecimal padUpPnsItrIn) {
		this.padUpPnsItrIn = padUpPnsItrIn;
	}

	public BigDecimal getPadUpCpdItrIn() {
		return padUpCpdItrIn;
	}

	public void setPadUpCpdItrIn(BigDecimal padUpCpdItrIn) {
		this.padUpCpdItrIn = padUpCpdItrIn;
	}

	public BigDecimal getPadUpNorItrOut() {
		return padUpNorItrOut;
	}

	public void setPadUpNorItrOut(BigDecimal padUpNorItrOut) {
		this.padUpNorItrOut = padUpNorItrOut;
	}

	public BigDecimal getPadUpDftItrOut() {
		return padUpDftItrOut;
	}

	public void setPadUpDftItrOut(BigDecimal padUpDftItrOut) {
		this.padUpDftItrOut = padUpDftItrOut;
	}

	public BigDecimal getPadUpPnsItrOut() {
		return padUpPnsItrOut;
	}

	public void setPadUpPnsItrOut(BigDecimal padUpPnsItrOut) {
		this.padUpPnsItrOut = padUpPnsItrOut;
	}

	public BigDecimal getPadUpCpdItrOut() {
		return padUpCpdItrOut;
	}

	public void setPadUpCpdItrOut(BigDecimal padUpCpdItrOut) {
		this.padUpCpdItrOut = padUpCpdItrOut;
	}

	public BigDecimal getPadUpPentIcm() {
		return padUpPentIcm;
	}

	public void setPadUpPentIcm(BigDecimal padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
	}

	public BigDecimal getPadUpOftPrn() {
		return padUpOftPrn;
	}

	public void setPadUpOftPrn(BigDecimal padUpOftPrn) {
		this.padUpOftPrn = padUpOftPrn;
	}

	public BigDecimal getPadUpOftItr() {
		return padUpOftItr;
	}

	public void setPadUpOftItr(BigDecimal padUpOftItr) {
		this.padUpOftItr = padUpOftItr;
	}

	public BigDecimal getOftAcrItrBal() {
		return oftAcrItrBal;
	}

	public void setOftAcrItrBal(BigDecimal oftAcrItrBal) {
		this.oftAcrItrBal = oftAcrItrBal;
	}

	public BigDecimal getPadUpDiscAmt() {
		return padUpDiscAmt;
	}

	public void setPadUpDiscAmt(BigDecimal padUpDiscAmt) {
		this.padUpDiscAmt = padUpDiscAmt;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getStan() {
		return stan;
	}

	public void setStan(Long stan) {
		this.stan = stan;
	}

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}
}
