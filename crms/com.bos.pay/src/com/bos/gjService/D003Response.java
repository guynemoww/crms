package com.bos.gjService;

import java.io.Serializable;
/**
 * 响应
 * @author chenpan 
 *
 */
public class D003Response implements Serializable{

	private static final long serialVersionUID = 1466883254584475542L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private D003ResponseBody responseBody;
	
	public D003Response() {
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
	public D003ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(D003ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
