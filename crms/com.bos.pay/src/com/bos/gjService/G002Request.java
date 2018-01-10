package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷还款接口---请求对象
 * 
 */
public class G002Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4303489095372555570L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G002RequestBody requestBody;
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
	public G002RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G002RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
	
	
	
}
