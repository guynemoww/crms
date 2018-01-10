package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
/**
 * 融资展期接口---响应对象
 * @author shendl
 *
 */
public class GJS01501030000009Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1904356539597052215L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000009ResBody gjS01501030000009ResBody;
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
	public GJS01501030000009ResBody getGjS01501030000009ResBody() {
		return gjS01501030000009ResBody;
	}
	public void setGjS01501030000009ResBody(
			GJS01501030000009ResBody gjS01501030000009ResBody) {
		this.gjS01501030000009ResBody = gjS01501030000009ResBody;
	}
	
}
