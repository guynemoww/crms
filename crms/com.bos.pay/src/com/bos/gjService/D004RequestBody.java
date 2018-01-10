package com.bos.gjService;

import java.io.Serializable;

public class D004RequestBody implements Serializable{

	private static final long serialVersionUID = -4876302574015188652L;
	private String dueNum;//借据编号
	private String padUpPentIcm;//收取违约金金额--默认0
	private String padUpAmt;//还款金额
	private String payOrder;//还款顺序--00默认序01大本大息序02小本小息序
	private String prinPlanFlg;//新下发还本/还息计划 --第1位下发还本计划标志 0=不下发  1=下发；第2位：下发还息计划标志 0=不下发  1=下发 ；默认00
	private String payOutItrFlg;//归还未结计利息标志--0否 1是 默认0
	private String wdFlowNo;//网贷系统流水号，唯一标识
	
	public D004RequestBody() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(String padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
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
	public String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	public void setPrinPlanFlg(String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	public String getPayOutItrFlg() {
		return payOutItrFlg;
	}
	public void setPayOutItrFlg(String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
	}
	public String getWdFlowNo() {
		return wdFlowNo;
	}
	public void setWdFlowNo(String wdFlowNo) {
		this.wdFlowNo = wdFlowNo;
	}
}
