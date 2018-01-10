package com.bos.gjService;

import java.io.Serializable;

import com.bos.gjService.ReqTranHeader;
import com.bos.gjService.RequestHeader;
/**
 * 票据额度恢复---请求对象
 * @author lenovo
 *
 */
public class P001Request implements Serializable{
	private static final long serialVersionUID = 2658598929878237570L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private P001RequestBody requestBody;
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
	public P001RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(P001RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
