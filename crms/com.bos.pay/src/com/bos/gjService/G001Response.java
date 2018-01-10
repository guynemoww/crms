package com.bos.gjService;

import java.io.Serializable;
/**
 * 国结调用信贷出账接口---响应对象
 * 
 */
public class G001Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6705722694214479195L;
	private ResponseHeader responseHeader;
	private ResTranHeader resTranHeader;
	private G001ResponseBody responseBody;
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

	public G001ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(G001ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
}
