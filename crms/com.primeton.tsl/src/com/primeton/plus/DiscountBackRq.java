package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 暂停贴息输入
 * @author CHENPAN
 *
 */
public class DiscountBackRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5346500405038697465L;
	private String dueNum;      //借据编号	    
	public DiscountBackRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
}
