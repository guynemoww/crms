package com.bos.gjService;

import java.io.Serializable;

import java.util.List;

public class D010Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171226404588326542L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	
	private List<BizInfoList> responseBody;

	public D010Response() {
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

	public List<BizInfoList> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(List<BizInfoList> responseBody) {
		this.responseBody = responseBody;
	}

}
