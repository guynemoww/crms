package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD092_PawnInOutRes implements Serializable {
	private static final long serialVersionUID = -729333345287492012L;
	
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD092ResBody oxd092ResBody;

	public OXD092_PawnInOutRes() {
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

	public OXD092ResBody getOxd092ResBody() {
		return oxd092ResBody;
	}

	public void setOxd092ResBody(OXD092ResBody oxd092ResBody) {
		this.oxd092ResBody = oxd092ResBody;
	}

}
