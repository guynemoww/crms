package com.bos.gjService;

import java.io.Serializable;

public class D005Request implements Serializable{

	private static final long serialVersionUID = 4835474120353118677L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D005RequestBody requestBody;
	public D005Request() {
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
	public D005RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D005RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
