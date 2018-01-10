package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷本息查询接口---响应对象
 *
 */
public class G003Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7214972421766443675L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	
	private G003ResponseBody responseBody;

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

	public G003ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(G003ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
}
