package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 进口保函开立---响应对象
 * @author shendl
 *
 */
public class GJS01501110000004Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5283870429501419684L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501110000004ResBody gjS01501110000004ResBody;
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
	public GJS01501110000004ResBody getGjS01501110000004ResBody() {
		return gjS01501110000004ResBody;
	}
	public void setGjS01501110000004ResBody(
			GJS01501110000004ResBody gjS01501110000004ResBody) {
		this.gjS01501110000004ResBody = gjS01501110000004ResBody;
	}
	
}
