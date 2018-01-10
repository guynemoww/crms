package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD042_SuccessFlagChkRes implements Serializable {
	private static final long serialVersionUID = 7304987111062316169L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD042ResBody oxd042ResBody;

	public OXD042_SuccessFlagChkRes() {

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

	public OXD042ResBody getOxd042ResBody() {
		return oxd042ResBody;
	}

	public void setOxd042ResBody(OXD042ResBody oxd042ResBody) {
		this.oxd042ResBody = oxd042ResBody;
	}

}
