package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷放/还款结果查询接口---请求对象
 *
 */
public class G005Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8581062749916857880L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G005RequestBody requestBody;
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
	public G005RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G005RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
