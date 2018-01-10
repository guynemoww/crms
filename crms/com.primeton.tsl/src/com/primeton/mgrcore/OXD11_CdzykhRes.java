package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD11_CdzykhRes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8166799756923634812L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD11ResBody oxd11ResBody;
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
	public OXD11ResBody getOxd11ResBody() {
		return oxd11ResBody;
	}
	public void setOxd11ResBody(OXD11ResBody oxd11ResBody) {
		this.oxd11ResBody = oxd11ResBody;
	}
	
	
}
