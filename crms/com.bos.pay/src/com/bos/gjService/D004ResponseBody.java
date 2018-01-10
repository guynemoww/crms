package com.bos.gjService;

import java.io.Serializable;

public class D004ResponseBody implements Serializable{

	private static final long serialVersionUID = -5641008504302999759L;
	private String payPrimName;//还款账户名称
	private String payPrimAcct;//还款账号
	private String padUpAmt;//还款金额
	private String payOrder;//还款顺序
	private String rcvPrn;//应还本金
	private String rcvNorItrIn;//应还正常利息
	private String rcvDftItrIn;//应还拖欠利息
	private String rcvPnsItrIn;//应还罚息
	private String padUpPrn;//实还本金
	private String padUpNorItrIn;//实还正常利息
	private String padUpDftItrIn;//实还拖欠利息
	private String padUpPnsItrIn;//实还罚息
	private String padUpPentIcm;//违约金
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String conNo;//合同编号
	private String begDate;//贷款起期
	private String endDate;//贷款止期
	private String brwName;//客户名称
	private String sts;//贷款状态
	public D004ResponseBody() {
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public String getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(String padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
	public String getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(String rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	public String getRcvNorItrIn() {
		return rcvNorItrIn;
	}
	public void setRcvNorItrIn(String rcvNorItrIn) {
		this.rcvNorItrIn = rcvNorItrIn;
	}
	public String getRcvDftItrIn() {
		return rcvDftItrIn;
	}
	public void setRcvDftItrIn(String rcvDftItrIn) {
		this.rcvDftItrIn = rcvDftItrIn;
	}
	public String getRcvPnsItrIn() {
		return rcvPnsItrIn;
	}
	public void setRcvPnsItrIn(String rcvPnsItrIn) {
		this.rcvPnsItrIn = rcvPnsItrIn;
	}
	public String getPadUpPrn() {
		return padUpPrn;
	}
	public void setPadUpPrn(String padUpPrn) {
		this.padUpPrn = padUpPrn;
	}
	public String getPadUpNorItrIn() {
		return padUpNorItrIn;
	}
	public void setPadUpNorItrIn(String padUpNorItrIn) {
		this.padUpNorItrIn = padUpNorItrIn;
	}
	public String getPadUpDftItrIn() {
		return padUpDftItrIn;
	}
	public void setPadUpDftItrIn(String padUpDftItrIn) {
		this.padUpDftItrIn = padUpDftItrIn;
	}
	public String getPadUpPnsItrIn() {
		return padUpPnsItrIn;
	}
	public void setPadUpPnsItrIn(String padUpPnsItrIn) {
		this.padUpPnsItrIn = padUpPnsItrIn;
	}
	public String getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(String padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
}
