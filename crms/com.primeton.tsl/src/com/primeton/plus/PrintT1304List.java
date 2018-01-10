package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结息清单
 * 
 * @author MJF
 * 
 */
public class PrintT1304List implements Serializable {
	private static final long serialVersionUID = -2671220694998526602L;
	private String rcvDate;// 还款日期
	private String dueNum;// 借据编号
	private String brwName;// 客户名称
	private String currCod;// 币种
	private BigDecimal rcvNorItrIn;// 表内正常利息
	private BigDecimal rcvDftItrIn;// 表内拖欠利息
	private BigDecimal rcvPnsItrIn;// 表内罚息
	private BigDecimal rcvCpdItrIn;// 表内复利
	private BigDecimal rcvNorItrOut;// 表外正常利息
	private BigDecimal rcvDftItrOut;// 表外拖欠利息
	private BigDecimal rcvPnsItrOut;// 表外罚息
	private BigDecimal rcvCpdItrOut;// 表外复利
	private BigDecimal oftAcrItrBal;// 核销收回应计利息

	public PrintT1304List() {

	}

	public String getRcvDate() {
		return rcvDate;
	}

	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
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

	public String getCurrCod() {
		return currCod;
	}

	public void setCurrCod(String currCod) {
		this.currCod = currCod;
	}

	public BigDecimal getRcvNorItrIn() {
		return rcvNorItrIn;
	}

	public void setRcvNorItrIn(BigDecimal rcvNorItrIn) {
		this.rcvNorItrIn = rcvNorItrIn;
	}

	public BigDecimal getRcvDftItrIn() {
		return rcvDftItrIn;
	}

	public void setRcvDftItrIn(BigDecimal rcvDftItrIn) {
		this.rcvDftItrIn = rcvDftItrIn;
	}

	public BigDecimal getRcvPnsItrIn() {
		return rcvPnsItrIn;
	}

	public void setRcvPnsItrIn(BigDecimal rcvPnsItrIn) {
		this.rcvPnsItrIn = rcvPnsItrIn;
	}

	public BigDecimal getRcvCpdItrIn() {
		return rcvCpdItrIn;
	}

	public void setRcvCpdItrIn(BigDecimal rcvCpdItrIn) {
		this.rcvCpdItrIn = rcvCpdItrIn;
	}

	public BigDecimal getRcvNorItrOut() {
		return rcvNorItrOut;
	}

	public void setRcvNorItrOut(BigDecimal rcvNorItrOut) {
		this.rcvNorItrOut = rcvNorItrOut;
	}

	public BigDecimal getRcvDftItrOut() {
		return rcvDftItrOut;
	}

	public void setRcvDftItrOut(BigDecimal rcvDftItrOut) {
		this.rcvDftItrOut = rcvDftItrOut;
	}

	public BigDecimal getRcvPnsItrOut() {
		return rcvPnsItrOut;
	}

	public void setRcvPnsItrOut(BigDecimal rcvPnsItrOut) {
		this.rcvPnsItrOut = rcvPnsItrOut;
	}

	public BigDecimal getRcvCpdItrOut() {
		return rcvCpdItrOut;
	}

	public void setRcvCpdItrOut(BigDecimal rcvCpdItrOut) {
		this.rcvCpdItrOut = rcvCpdItrOut;
	}

	public BigDecimal getOftAcrItrBal() {
		return oftAcrItrBal;
	}

	public void setOftAcrItrBal(BigDecimal oftAcrItrBal) {
		this.oftAcrItrBal = oftAcrItrBal;
	}
}
