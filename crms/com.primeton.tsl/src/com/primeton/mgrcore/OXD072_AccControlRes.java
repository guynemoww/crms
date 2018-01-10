package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD07】账户控制及维护(止付/解止付)
 * 
 * @author MJF
 * 
 *         输出
 */
public class OXD072_AccControlRes implements Serializable {
	private static final long serialVersionUID = 7233084649160338101L;

	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD072ResBody oxd072ResBody;

	public OXD072_AccControlRes() {

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

	public OXD072ResBody getOxd072ResBody() {
		return oxd072ResBody;
	}

	public void setOxd072ResBody(OXD072ResBody oxd072ResBody) {
		this.oxd072ResBody = oxd072ResBody;
	}

}
