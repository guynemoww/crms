package com.bos.gjService;

import java.io.Serializable;

public class D006RequestBody implements Serializable{

	private static final long serialVersionUID = 2796307953060833210L;
	private String dueNum;//借据编号
	public D006RequestBody() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
}
