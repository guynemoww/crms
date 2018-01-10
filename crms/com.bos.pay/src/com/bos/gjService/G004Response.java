package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷业务通知接口---响应对象
 *
 */
public class G004Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103681883578908825L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private G004ResponseBody responseBody;
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
	public G004ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(G004ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
	
}
