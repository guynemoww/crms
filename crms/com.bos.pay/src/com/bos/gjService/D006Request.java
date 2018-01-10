package com.bos.gjService;

import java.io.Serializable;

public class D006Request implements Serializable{

	private static final long serialVersionUID = 5234200891282542945L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D006RequestBody requestBody;
	public D006Request() {
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
	public D006RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D006RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
