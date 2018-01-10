package com.bos.gjService;

import java.io.Serializable;

public class D008Request implements Serializable{
	private static final long serialVersionUID = -933185971671781567L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D008RequestBody requestBody;
	public D008Request() {
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
	public D008RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D008RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
