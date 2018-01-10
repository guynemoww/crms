package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

public class D007Response implements Serializable{
	private static final long serialVersionUID = 5298985257114192508L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private List<LoanList> responseBody;
	public D007Response() {
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
	public List<LoanList> getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(List<LoanList> responseBody) {
		this.responseBody = responseBody;
	}
	
}
