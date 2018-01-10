package com.bos.gjService;

import java.io.Serializable;

public class D011Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1088759816016417540L;

	
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	
	private D011ResponseBody responseBody;
	
	public D011Response(){
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

	public D011ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(D011ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
