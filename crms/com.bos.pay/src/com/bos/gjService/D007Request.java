package com.bos.gjService;

import java.io.Serializable;

public class D007Request implements Serializable{
	private static final long serialVersionUID = 2185122558933401440L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D007RequestBody requestBody;
	public D007Request() {
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
	public D007RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D007RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
