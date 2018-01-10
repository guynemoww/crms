package com.bos.gjService;

import java.io.Serializable;

public class D004Request implements Serializable{
	private static final long serialVersionUID = 6978880814303968504L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D004RequestBody requestBody;
	public D004Request() {
	}
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
	public ReqTranHeader getReqTranHeader() {
		return reqTranHeader;
	}
	public void setReqTranHeader(ReqTranHeader reqTranHeader) {
		this.reqTranHeader = reqTranHeader;
	}
	public D004RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D004RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
