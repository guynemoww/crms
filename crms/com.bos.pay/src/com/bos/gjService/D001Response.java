package com.bos.gjService;

import java.io.Serializable;

public class D001Response implements Serializable{


	private static final long serialVersionUID = 6384787527910399753L;

	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	
	private D001ResponseBody responseBody;

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

	public D001ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(D001ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
