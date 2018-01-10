package com.bos.gjService;

import java.io.Serializable;
/**
 * 请求
 * @author chenpan
 *
 */
public class D003Request implements Serializable{

	private static final long serialVersionUID = -8719446974915431444L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private D003RequestBody requestBody;
	public D003Request() {
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
	public D003RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(D003RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
