package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 出账放款检查输入
 * @author CHENPAN
 *
 */
public class PayOutCheckRq extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1619400786842747622L;
	private String telNo;//通知书编号
	private String dueNum;//借据编号
	
	public PayOutCheckRq() {
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
}
