package com.bos.pub.socket.util;

public class EsbSocketMessage {
	private String retCode = "";
	private String retMsg = "";
	private String strEsbServiceRq = "";
	private String strEsbServiceRs = "";
	
	public String getRetCode() {
		return retCode;
	}
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
	public String getRetMsg() {
		return retMsg;
	}
	
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getStrEsbServiceRq() {
		return strEsbServiceRq;
	}

	public void setStrEsbServiceRq(String strEsbServiceRq) {
		this.strEsbServiceRq = strEsbServiceRq;
	}

	public String getStrEsbServiceRs() {
		return strEsbServiceRs;
	}

	public void setStrEsbServiceRs(String strEsbServiceRs) {
		this.strEsbServiceRs = strEsbServiceRs;
	}
}
