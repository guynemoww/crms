package com.primeton.mgrcore;

import java.io.Serializable;
import com.primeton.mgrcore.ResponseHeader;
import com.primeton.mgrcore.ResTranHeader;

public class OXD012_AccoutingRes implements Serializable {
	private static final long serialVersionUID = 3724679716186591584L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD012ResBody oxd012ResBody;

	public OXD012_AccoutingRes() {

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

	public OXD012ResBody getOxd012ResBody() {
		return oxd012ResBody;
	}

	public void setOxd012ResBody(OXD012ResBody oxd012ResBody) {
		this.oxd012ResBody = oxd012ResBody;
	}
}
