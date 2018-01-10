package com.bos.gjService;

import java.io.Serializable;
/**
 * 出账接口---请求对象
 * @author shendl
 *
 */
public class G001Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5533195698794058139L;
	private RequestHeader requestHeader;
	private ReqTranHeader reqTranHeader;
	private G001RequestBody requestBody;
	
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
	public G001RequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(G001RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
	
}
