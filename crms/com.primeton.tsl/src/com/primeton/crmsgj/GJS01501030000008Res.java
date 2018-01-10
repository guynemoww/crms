package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 编号校验接口---响应对象
 * @author shendl
 *
 */
public class GJS01501030000008Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8613529449629716279L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000008ResBody gjS01501030000008ResBody;
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
	public GJS01501030000008ResBody getGjS01501030000008ResBody() {
		return gjS01501030000008ResBody;
	}
	public void setGjS01501030000008ResBody(
			GJS01501030000008ResBody gjS01501030000008ResBody) {
		this.gjS01501030000008ResBody = gjS01501030000008ResBody;
	}
}
