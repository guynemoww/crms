package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷还款接口---请求对象体
 */
public class G002RequestBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9049756565423290419L;
	private String summaryNum;//借据编号
	private BigDecimal padUpAmt;//还款金额
	private BigDecimal padUpPentIcm;//收取违约金金额
	private String payOrder;//还款顺序
	private String payPrimAcct;//还款账号
	private String payPrimName;//还款账户名
	private String prinPlanFlg;//新下发还本/还息计划
	private String payOutItrFlg;//归还未结计利息标志
	private String gjFlowNo;//国结流水号
	private String fieldRq1;//备用字段1
	private String fieldRq2;//备用字段2
	private String fieldRq3;//备用字段3
	private String fieldRq4;//备用字段4
	private String fieldRq5;//备用字段5
	public G002RequestBody() {
	}
	public String getSummaryNum() {
		return summaryNum;
	}
	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}
	
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public BigDecimal getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(BigDecimal padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
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
	
	public String getGjFlowNo() {
		return gjFlowNo;
	}
	public void setGjFlowNo(String gjFlowNo) {
		this.gjFlowNo = gjFlowNo;
	}
	public String getFieldRq1() {
		return fieldRq1;
	}
	public void setFieldRq1(String fieldRq1) {
		this.fieldRq1 = fieldRq1;
	}
	public String getFieldRq2() {
		return fieldRq2;
	}
	public void setFieldRq2(String fieldRq2) {
		this.fieldRq2 = fieldRq2;
	}
	public String getFieldRq3() {
		return fieldRq3;
	}
	public void setFieldRq3(String fieldRq3) {
		this.fieldRq3 = fieldRq3;
	}
	public String getFieldRq4() {
		return fieldRq4;
	}
	public void setFieldRq4(String fieldRq4) {
		this.fieldRq4 = fieldRq4;
	}
	public String getFieldRq5() {
		return fieldRq5;
	}
	public void setFieldRq5(String fieldRq5) {
		this.fieldRq5 = fieldRq5;
	}
	
}
