package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷出账接口---响应对象体
 * 
 */
public class ZX001ResponseBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932619964929154918L;
	private String flag;//征信发送状态  
	private String fieldRs1;//备用字段1
	private String fieldRs2;//备用字段2
	private String fieldRs3;//备用字段3
	private String fieldRs4;//备用字段4
	private String fieldRs5;//备用字段5
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFieldRs1() {
		return fieldRs1;
	}
	public void setFieldRs1(String fieldRs1) {
		this.fieldRs1 = fieldRs1;
	}
	public String getFieldRs2() {
		return fieldRs2;
	}
	public void setFieldRs2(String fieldRs2) {
		this.fieldRs2 = fieldRs2;
	}
	public String getFieldRs3() {
		return fieldRs3;
	}
	public void setFieldRs3(String fieldRs3) {
		this.fieldRs3 = fieldRs3;
	}
	public String getFieldRs4() {
		return fieldRs4;
	}
	public void setFieldRs4(String fieldRs4) {
		this.fieldRs4 = fieldRs4;
	}
	public String getFieldRs5() {
		return fieldRs5;
	}
	public void setFieldRs5(String fieldRs5) {
		this.fieldRs5 = fieldRs5;
	}
}
