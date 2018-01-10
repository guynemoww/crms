package com.bos.gjService;

import java.io.Serializable;

import com.bos.gjService.ResTranHeader;
import com.bos.gjService.ResponseHeader;

/**
 * 票据额度恢复---请求对象
 * @author lenovo
 *
 */
public class P001Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6942963884753832127L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private P001ResponseBody responseBody;
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
	public P001ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(P001ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
	
}
