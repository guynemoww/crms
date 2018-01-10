package com.bos.gjService;

public class D010RequestBody {
	
	private String ecifPartyNum;//客户编号
	private String applyStatus;//申请状态
	
	public D010RequestBody() {
	}

	public String getEcifPartyNum() {
		return ecifPartyNum;
	}

	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	
}
