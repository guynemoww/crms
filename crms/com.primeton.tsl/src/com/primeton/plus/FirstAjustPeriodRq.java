package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 调整阶段性首次还本期数输入
 * @author CHENPAN
 *
 */
public class FirstAjustPeriodRq extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 781929925253398367L;
	private String dueNum;             //借据编号    
	private long stgFirstMon;        //变更后首次还本期数
	public FirstAjustPeriodRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public long getStgFirstMon() {
		return stgFirstMon;
	}
	public void setStgFirstMon(long stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}
	
	
	
}
