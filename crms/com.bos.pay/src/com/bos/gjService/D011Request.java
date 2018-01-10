package com.bos.gjService;

import java.io.Serializable;

public class D011Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7600542675230729222L;

	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D011RequestBody requestBody;
	
	
	public D011Request() {
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


	public D011RequestBody getRequestBody() {
		return requestBody;
	}


	public void setRequestBody(D011RequestBody requestBody) {
		this.requestBody = requestBody;
	} 
	
}
