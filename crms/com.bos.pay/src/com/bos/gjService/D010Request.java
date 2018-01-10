package com.bos.gjService;

import java.io.Serializable;

public class D010Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -521821136156596002L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D010RequestBody requestBody;
	

	public D010Request() {
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

	public D010RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(D010RequestBody requestBody) {
		this.requestBody = requestBody;
	}


}
