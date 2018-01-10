package com.bos.gjService;

import java.io.Serializable;

public class G006Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -334203420432726530L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G006RequestBody requestBody;
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
	public G006RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G006RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
	
}
