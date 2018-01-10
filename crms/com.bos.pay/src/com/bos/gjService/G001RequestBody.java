package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷出账接口---请求对象体
 * 
 */
public class G001RequestBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812505036428189203L;
	private String summaryNum;//借据编号
	private String gjFlowNo;//国结流水号
	private BigDecimal payAmt;//交易金额
	private String fieldRq1;//备用字段1
	private String fieldRq2;//备用字段2
	private String fieldRq3;//备用字段3
	private String fieldRq4;//备用字段4
	private String fieldRq5;//备用字段5
	public G001RequestBody() {
	}
	public String getSummaryNum() {
		return summaryNum;
	}
	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}
	
	public String getGjFlowNo() {
		return gjFlowNo;
	}
	public void setGjFlowNo(String gjFlowNo) {
		this.gjFlowNo = gjFlowNo;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
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
