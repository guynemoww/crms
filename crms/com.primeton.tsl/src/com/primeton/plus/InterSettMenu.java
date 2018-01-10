package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;

/**
 * 结息清单
 * @author CHENPAN
 *
 */
public class InterSettMenu  extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520179745339445083L;
	private String dueNum;   //借据编号
	private String begDate;  //起始日期
	private String endDate;  //终止日期
    public InterSettMenu() {
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
