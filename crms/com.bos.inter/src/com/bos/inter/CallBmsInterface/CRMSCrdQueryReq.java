package com.bos.inter.CallBmsInterface;

import java.io.Serializable;

import com.bos.gjService.ReqTranHeader;
import com.bos.gjService.RequestHeader;


/**
 * 额度查询  请求报文
 * @author shendl
 */
public class CRMSCrdQueryReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4301501949271893939L;
	public RequestHeader requestHeader;//请求ESB报文头(ResquestHeader)
	public ReqTranHeader reqTranHeader;//请求ESB交易报文头(ReqTranHeader)
	public CRMSCrdQueryReqBody requestBody;//请求ESB交易报文体(RequestBody)
	
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
	public CRMSCrdQueryReqBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(CRMSCrdQueryReqBody requestBody) {
		this.requestBody = requestBody;
	}
}
