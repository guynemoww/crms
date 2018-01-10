package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 调整阶段性首次还本期数输出
 * @author CHENPAN
 *
 */
public class FirstAjustPeriodRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6503749846968544273L;
	private String dueNum;             //借据编号    
	private String stgFirstMon;        //变更后首次还本期数
	public FirstAjustPeriodRs() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getStgFirstMon() {
		return stgFirstMon;
	}
	public void setStgFirstMon(String stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}
	
}
