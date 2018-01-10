package com.bos.gjService;

import java.io.Serializable;
/**
 * 征信查询反回信贷---请求对象
 * @author 唐良强
 *
 */
public class ZX001Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5533195698794058139L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private ZX001RequestBody requestBody;
	
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
	public ReqTranHeader getReqTranHeader() {
		return reqTranHeader;
	}
	public void setReqTranHeader(ReqTranHeader reqTranHeader) {
		this.reqTranHeader = reqTranHeader;
	}
	public ZX001RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(ZX001RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
	
}
