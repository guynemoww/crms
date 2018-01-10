package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD06_OutFlushesRes implements Serializable {
	private static final long serialVersionUID = -8300840620287498782L;

	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD06ResBody xd06ResBody;

	public OXD06_OutFlushesRes() {

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

	public OXD06ResBody getXd06ResBody() {
		return xd06ResBody;
	}

	public void setXd06ResBody(OXD06ResBody xd06ResBody) {
		this.xd06ResBody = xd06ResBody;
	}

}
