package com.bos.gjService;

import java.io.Serializable;

public class D001Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886128497513300985L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	
	private D001RequestBody requestBody;

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

	public D001RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(D001RequestBody requestBody) {
		this.requestBody = requestBody;
	}

}
