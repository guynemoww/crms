package com.bos.gjService;

import java.io.Serializable;

import com.bos.gjService.ReqTranHeader;
import com.bos.gjService.RequestHeader;
/**
 * 个贷服务接口请求对象
 * @author lenovo
 *
 */
public class WY001Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2658598929878237570L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private WY001RequestBody requestBody;
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
	public WY001RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(WY001RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
