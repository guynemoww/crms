package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷本息查询接口---请求对象
 *
 */
public class G003Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1660610228627972982L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G003RequestBody requestBody;
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
	public G003RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G003RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
