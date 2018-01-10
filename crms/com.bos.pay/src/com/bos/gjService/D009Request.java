package com.bos.gjService;

import java.io.Serializable;

public class D009Request implements Serializable{
	private static final long serialVersionUID = 4336046713070678922L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D009RequestBody requestBody;
	public D009Request() {
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
	public D009RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D009RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
