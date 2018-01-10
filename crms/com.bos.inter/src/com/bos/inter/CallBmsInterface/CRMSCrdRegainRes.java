package com.bos.inter.CallBmsInterface;

import java.io.Serializable;

import com.bos.gjService.ResTranHeader;
import com.bos.gjService.ResponseHeader;



/**
 * 额度恢复响应报文
 * @author shendl
 *
 */
public class CRMSCrdRegainRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8806044521758902167L;
	public ResponseHeader responseHeader;//响应ESB头(ResponseHeader)
	public ResTranHeader resTranHeader;//ESB交易响应头（ResTranHeader）
	public CRMSCrdRegainResBody responseBody;//ESB交易响应体（ResponseBody）
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
	public CRMSCrdRegainResBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(CRMSCrdRegainResBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
