package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

public class D006Response implements Serializable{
	private static final long serialVersionUID = 7794962471681298519L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private D006ResponseBody responseBody;
	public D006Response() {
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
	public D006ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(D006ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
