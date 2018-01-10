package com.bos.gjService;

import java.io.Serializable;

/**
 * 国结调用信贷系统汇率推送接口---响应对象
 *
 */
public class G006Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1958433288631672699L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private G006ResponseBody responseBody;
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
	public G006ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(G006ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
