package com.bos.gjService;
/**
 * 网贷调用信贷接口----请求对象
 */
import java.io.Serializable;


public class D002Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 228593497097600019L;
	
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	
	private D002RequestBody requestBody;

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

	public D002RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(D002RequestBody requestBody) {
		this.requestBody = requestBody;
	}


}
