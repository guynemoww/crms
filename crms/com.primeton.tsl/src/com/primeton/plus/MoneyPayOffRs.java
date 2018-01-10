package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 本金归还完成后调整累计利息标志输出
 * @author CHENPAN
 *
 */
public class MoneyPayOffRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2695884792763255756L;
	private String dueNum;//借据编号
	public MoneyPayOffRs() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
}
