package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD08】客户账户信息查询(1232)
 * 
 * @author MJF
 * 
 *         输出
 */
public class OXD082_CustAccInfoQryRes implements Serializable {
	private static final long serialVersionUID = 4621113292407231283L;

	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private OXD082ResBody oxd082ResBody;

	public OXD082_CustAccInfoQryRes() {
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

	public OXD082ResBody getOxd082ResBody() {
		return oxd082ResBody;
	}

	public void setOxd082ResBody(OXD082ResBody oxd082ResBody) {
		this.oxd082ResBody = oxd082ResBody;
	}

}