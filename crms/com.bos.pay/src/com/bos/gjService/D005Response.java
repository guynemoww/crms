package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

public class D005Response implements Serializable{

	private static final long serialVersionUID = -8362145011064053891L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	//private D005ResponseBody responseBody;
	private List<ConList> responseBody;
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
//	public D005ResponseBody getResponseBody() {
//		return responseBody;
//	}
//	public void setResponseBody(D005ResponseBody responseBody) {
//		this.responseBody = responseBody;
//	}
	public List<ConList> getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(List<ConList> responseBody) {
		this.responseBody = responseBody;
	}
	
}
