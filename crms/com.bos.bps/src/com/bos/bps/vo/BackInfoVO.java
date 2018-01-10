package com.bos.bps.vo;

import java.util.List;


public class BackInfoVO {
	
	
	private String errCode;
	
	private String errDesc;
	
	private List  list;
	
	private int backSize;


	public int getBackSize() {
		return backSize;
	}

	public void setBackSize(int backSize) {
		this.backSize = backSize;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	

	
}
