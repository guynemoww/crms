package com.bos.gjService;

import java.io.Serializable;

import com.bos.gjService.ResTranHeader;
import com.bos.gjService.ResponseHeader;
/**
 * 个贷服务接口响应对象
 * @author lenovo
 *
 */
public class WY001Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1743405581697924971L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private WY001ResponseBody responseBody;
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
	public WY001ResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(WY001ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
