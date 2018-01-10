package com.bos.gjService;

import java.io.Serializable;
/**
 *  国结调用信贷放/还款结果查询接口---响应对象
 *
 */
public class G005Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5062527713374076887L;
	private G005ResponseBody responseBody;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
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
	public G005ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(G005ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
	
}
