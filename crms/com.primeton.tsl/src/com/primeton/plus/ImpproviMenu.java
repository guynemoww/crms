package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 计提减值清单
 * @author CHENPAN
 *
 */
public class ImpproviMenu  extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6414029136512998933L;
	private String dueNum;   //借据编号
	private String begDate;  //起始日期
	private String endDate;  //终止日期
	private String busCod;// 业务别
	public ImpproviMenu() {
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
	public String getBusCod() {
		return busCod;
	}
	public void setBusCod(String busCod) {
		this.busCod = busCod;
	}

}
