package com.bos.gjService;

import java.io.Serializable;

public class D004Response implements Serializable{
	private static final long serialVersionUID = 3703497485444225140L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private D004ResponseBody responseBody;
	public D004Response() {
	}
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public ResTranHeader getResTranHeader() {
		return resTranHeader;
	}
	public void setResTranHeader(ResTranHeader resTranHeader) {
		this.resTranHeader = resTranHeader;
	}
	public D004ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(D004ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
