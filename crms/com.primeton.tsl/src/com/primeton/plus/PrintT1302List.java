package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 放款清单
 * 
 * @author MJF
 * 
 */
public class PrintT1302List implements Serializable {
	private static final long serialVersionUID = -7445965905574729935L;
	private String dueNum;// 借据编号
	private String rlsDep;// 放款机构
	private String brwName;// 客户名称
	private BigDecimal amt;// 放款金额
	private String begDate;// 贷款发放日期
	private String endDate;// 贷款到期日期
	private BigDecimal norItrRate;// 正常利率（%）
	private BigDecimal delItrRate;// 罚息利率（%）
	private String curPrmPayTyp;// 还款方式

	public PrintT1302List() {

	}

	public String getDueNum() {
		return dueNum;
	}

	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public String getRlsDep() {
		return rlsDep;
	}

	public void setRlsDep(String rlsDep) {
		this.rlsDep = rlsDep;
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

	public BigDecimal getNorItrRate() {
		return norItrRate;
	}

	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}

	public BigDecimal getDelItrRate() {
		return delItrRate;
	}

	public void setDelItrRate(BigDecimal delItrRate) {
		this.delItrRate = delItrRate;
	}

	public String getCurPrmPayTyp() {
		return curPrmPayTyp;
	}

	public void setCurPrmPayTyp(String curPrmPayTyp) {
		this.curPrmPayTyp = curPrmPayTyp;
	}

	@Override
	public String toString() {
		return "PrintT1302List [dueNum=" + dueNum + ", rlsDep=" + rlsDep
				+ ", brwName=" + brwName + ", amt=" + amt + ", begDate="
				+ begDate + ", endDate=" + endDate + ", norItrRate="
				+ norItrRate + ", delItrRate=" + delItrRate + ", curPrmPayTyp="
				+ curPrmPayTyp + "]";
	}

}
