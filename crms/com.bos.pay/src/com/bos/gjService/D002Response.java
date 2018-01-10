package com.bos.gjService;

import java.io.Serializable;

public class D002Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6338780379413613559L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	
	private D002ResponseBody responseBody;

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

	public D002ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(D002ResponseBody responseBody) {
		this.responseBody = responseBody;
	}

}
