package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷业务通知接口---请求对象
 */
public class G004Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7838468631053281389L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G004RequestBody requestBody;
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
	public G004RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G004RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
}
