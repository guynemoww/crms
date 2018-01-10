package com.bos.inter.CallBmsInterface;

import java.io.Serializable;

import com.bos.gjService.ResTranHeader;
import com.bos.gjService.ResponseHeader;


/**
 * 额度扣减 响应报文
 * @author shendl
 *
 */
public class CRMSCrdDeductionRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1088831069886106617L;
	public ResponseHeader responseHeader;//响应ESB头(ResponseHeader)
	public ResTranHeader resTranHeader;//ESB交易响应头（ResTranHeader）
	public CRMSCrdDeductionResBody responseBody;//ESB交易响应体（ResponseBody）
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public ResTranHeader getResTranHeader() {
		return resTranHeader;
	}
	public void setResTranHeader(ResTranHeader resTranHeader) {
		this.resTranHeader = resTranHeader;
	}
	public CRMSCrdDeductionResBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(CRMSCrdDeductionResBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
}
