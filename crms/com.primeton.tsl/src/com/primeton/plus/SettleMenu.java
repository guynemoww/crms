package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;

/**
 * 结清清单
 * @author CHENPAN
 *
 */
public class SettleMenu  extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5035307587831105416L;
	private String dueNum;   //借据编号
	private String begDate;  //起始日期
	private String endDate;  //终止日期
	public SettleMenu() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
