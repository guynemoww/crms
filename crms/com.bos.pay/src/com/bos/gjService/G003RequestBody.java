package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *国结调用信贷本息查询接口---请求对象体
 *
 */
public class G003RequestBody implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6593490589903691097L;
	private String dueNum;//借据编号
	private BigDecimal rcvPrn;//还款金额
	private String fieldRq1;//备用字段1
	private String fieldRq2;//备用字段2
	private String fieldRq3;//备用字段3
	private String fieldRq4;//备用字段4
	private String fieldRq5;//备用字段5
	public G003RequestBody() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
	public BigDecimal getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(BigDecimal rcvPrn) {
		this.rcvPrn = rcvPrn;
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
