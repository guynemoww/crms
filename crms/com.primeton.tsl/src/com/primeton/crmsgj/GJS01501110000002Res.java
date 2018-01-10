package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
/**
 * 进口信用证开证接口---响应对象
 * @author shendl
 *
 */
public class GJS01501110000002Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5622458130960426989L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501110000002ResBody gjS01501110000002ResBody;
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
	public GJS01501110000002ResBody getGjS01501110000002ResBody() {
		return gjS01501110000002ResBody;
	}
	public void setGjS01501110000002ResBody(
			GJS01501110000002ResBody gjS01501110000002ResBody) {
		this.gjS01501110000002ResBody = gjS01501110000002ResBody;
	}
	
	
	
}
