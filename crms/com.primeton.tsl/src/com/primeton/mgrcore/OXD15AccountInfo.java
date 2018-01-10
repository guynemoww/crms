package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 资金账户信息组合查询输出
 * @author chenpan
 *
 */
public class OXD15AccountInfo implements Serializable{
	
	private static final long serialVersionUID = -8810648242935400206L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD015ResBody oxd015ResBody;
	
	public OXD15AccountInfo() {
	}
	
	public ResponseHeader getResHeader() {
		return resHeader;
	}

	public void setResHeader(ResponseHeader resHeader) {
		this.resHeader = resHeader;
	}

	public ResTranHeader getResTranHeader() {
		return resTranHeader;
	}

	public void setResTranHeader(ResTranHeader resTranHeader) {
		this.resTranHeader = resTranHeader;
	}

	public OXD015ResBody getOxd015ResBody() {
		return oxd015ResBody;
	}

	public void setOxd015ResBody(OXD015ResBody oxd015ResBody) {
		this.oxd015ResBody = oxd015ResBody;
	}
}
