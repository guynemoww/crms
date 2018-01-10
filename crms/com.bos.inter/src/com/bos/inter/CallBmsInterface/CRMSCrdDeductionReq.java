package com.bos.inter.CallBmsInterface;

import java.io.Serializable;

import com.bos.gjService.ReqTranHeader;
import com.bos.gjService.RequestHeader;



/**
 * 额度扣减 请求报文
 * @author shendl
 *
 */
public class CRMSCrdDeductionReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1799049920813753121L;
	public RequestHeader requestHeader;//请求ESB报文头(ResquestHeader)
	public ReqTranHeader reqTranHeader;//请求ESB交易报文头(ReqTranHeader)
	public CRMSCrdDeductionReqBody requestBody;//请求ESB交易报文体(RequestBody)
	
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
	public CRMSCrdDeductionReqBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(CRMSCrdDeductionReqBody requestBody) {
		this.requestBody = requestBody;
	}
}
