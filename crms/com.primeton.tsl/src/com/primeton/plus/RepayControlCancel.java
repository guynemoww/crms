package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 还款控制信息撤销
 * @author ZHOUXU
 *
 */
public class RepayControlCancel extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5493018245816862161L;
	private String dueNum;             //借据编号            
	private String telNo;              //通知书编号   
	
	public RepayControlCancel() {
		// TODO 自动生成的构造函数存根
	}
	
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	

	
}
