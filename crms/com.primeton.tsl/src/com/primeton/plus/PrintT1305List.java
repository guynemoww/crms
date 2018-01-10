package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结清证明
 * 
 * @author lenovo
 * 
 */
public class PrintT1305List implements Serializable {
	private static final long serialVersionUID = -950337259953696378L;
	private String rcvDate;// 结清日期
	private String opnDep;// 机构号
	private String dueNum;// 借据编号
	private String brwName;// 客户名称
	private BigDecimal amt;// 本金
	private String begDate;// 发放日期
	private String endDate;// 到期日期

	public PrintT1305List() {

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
}
