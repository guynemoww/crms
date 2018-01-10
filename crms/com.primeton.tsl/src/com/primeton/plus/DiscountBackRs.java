package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 暂停贴息输出
 * @author CHENPAN
 *
 */
public class DiscountBackRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668763795517902887L;
	private String dueNum;
	
	public DiscountBackRs() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}      //借据编号	    
}
