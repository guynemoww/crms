package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD05】账户信息查询(1224)
 * 
 * @author MJF
 * 
 *         输出
 */
public class OXD052_AccInfoQryRes implements Serializable {
	private static final long serialVersionUID = 3071584246832629701L;

	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD052ResBody oxd052ResBody;

	public OXD052_AccInfoQryRes() {
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

	public OXD052ResBody getOxd052ResBody() {
		return oxd052ResBody;
	}

	public void setOxd052ResBody(OXD052ResBody oxd052ResBody) {
		this.oxd052ResBody = oxd052ResBody;
	}

}