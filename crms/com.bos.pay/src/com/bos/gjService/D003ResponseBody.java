package com.bos.gjService;

import java.io.Serializable;
/**
 * 放款响应体
 * @author chenpan
 *
 */
public class D003ResponseBody implements Serializable{
	private static final long serialVersionUID = 8609613787038955152L;
	private String loanNum;//出账编号
	private String summaryNum;//借据编号
	
	public D003ResponseBody() {
	}
	
	public String getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}
	public String getSummaryNum() {
		return summaryNum;
	}
	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}
	
	
}
