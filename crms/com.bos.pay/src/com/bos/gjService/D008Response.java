package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

public class D008Response implements Serializable{

	private static final long serialVersionUID = -7587718311789623651L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private List<RepayLoanList> responseBody;
	
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
	public List<RepayLoanList> getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(List<RepayLoanList> responseBody) {
		this.responseBody = responseBody;
	}
	
}
