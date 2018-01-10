package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 *放款自动撤销 
 * @author CHENPAN
 *
 */
public class RepayCancel extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8643302258970618918L;
	private String dueNum;//借据编号
	private String origStan;//原借据编号
	public RepayCancel() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getOrigStan() {
		return origStan;
	}
	public void setOrigStan(String origStan) {
		this.origStan = origStan;
	}
	
}
