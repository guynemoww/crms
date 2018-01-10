package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 贷款撤销输入
 * @author CHENPAN
 *
 */
public class LoanCancelRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 77655948899850441L;
	private String telNo;   //撤销通知书编号
	private String dueNum;  //借据编号      
	private Long revStan; //撤销流水号    
	private String accEntJson; //核心记账接口结构
	public LoanCancelRq() {
	}
	
	public String getAccEntJson() {
		return accEntJson;
	}

	public void setAccEntJson(String accEntJson) {
		this.accEntJson = accEntJson;
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

	public Long getRevStan() {
		return revStan;
	}

	public void setRevStan(Long revStan) {
		this.revStan = revStan;
	}
	
	
}
