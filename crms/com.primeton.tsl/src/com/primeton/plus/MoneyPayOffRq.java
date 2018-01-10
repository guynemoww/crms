package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 本金归还完成后调整累计利息标志输入
 * @author CHENPAN
 *
 */
public class MoneyPayOffRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2040939524265305736L;
	private String dueNum;//借据编号
	public MoneyPayOffRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
}
