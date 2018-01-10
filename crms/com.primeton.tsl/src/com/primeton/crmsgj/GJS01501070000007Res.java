package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 放款撤销接口---响应对象
 * @author shendl
 *
 */
public class GJS01501070000007Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7715205673132957760L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501070000007ResBody gjS01501070000007ResBody;
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
	public GJS01501070000007ResBody getGjS01501070000007ResBody() {
		return gjS01501070000007ResBody;
	}
	public void setGjS01501070000007ResBody(
			GJS01501070000007ResBody gjS01501070000007ResBody) {
		this.gjS01501070000007ResBody = gjS01501070000007ResBody;
	}
	
}
