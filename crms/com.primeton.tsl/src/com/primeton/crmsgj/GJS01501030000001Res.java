package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
/**
 * 表内融资业务放款交易---响应对象
 * @author shendl
 *
 */
public class GJS01501030000001Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2675278232214502061L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000001ResBody gjS01501030000001ResBody;
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
	public GJS01501030000001ResBody getGjS01501030000001ResBody() {
		return gjS01501030000001ResBody;
	}
	public void setGjS01501030000001ResBody(
			GJS01501030000001ResBody gjS01501030000001ResBody) {
		this.gjS01501030000001ResBody = gjS01501030000001ResBody;
	}
}
