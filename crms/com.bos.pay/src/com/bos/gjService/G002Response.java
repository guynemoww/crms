package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷还款接口---响应对象
 *
 */
public class G002Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3851832174682130559L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private G002ResponseBody responseBody;
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
	public G002ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(G002ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
}
