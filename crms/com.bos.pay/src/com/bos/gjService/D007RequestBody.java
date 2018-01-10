package com.bos.gjService;

import java.io.Serializable;

public class D007RequestBody implements Serializable{

	private static final long serialVersionUID = 6004902512782140970L;
	private String dueNum;//借据编号
	private String begDate;//开始日期
	private String endDate;//终止日期
	public D007RequestBody() {
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
