package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

public class D009Response implements Serializable{

	private static final long serialVersionUID = 6537719809683766100L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private List<SummaryList> responseBody;
	public D009Response() {
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
	public List<SummaryList> getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(List<SummaryList> responseBody) {
		this.responseBody = responseBody;
	}
}
