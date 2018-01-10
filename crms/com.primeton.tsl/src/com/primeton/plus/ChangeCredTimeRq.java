package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 调整贴息到期日输入
 * @author CHENPAN
 *
 */
public class ChangeCredTimeRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1654254102217306638L;
	private String dueNum;//借据编号
	private String discEndDate;//新贴息到期日
	public ChangeCredTimeRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getDiscEndDate() {
		return discEndDate;
	}
	public void setDiscEndDate(String discEndDate) {
		this.discEndDate = discEndDate;
	}

}
