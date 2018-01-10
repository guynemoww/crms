package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
/**
 * 牌价查询接口---响应对象
 * @author shendl
 *
 */
public class GJS01501010000010Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8900893312877873838L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501010000010ResBody gjS01501030000010ResBody;
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
	public GJS01501010000010ResBody getGjS01501030000010ResBody() {
		return gjS01501030000010ResBody;
	}
	public void setGjS01501030000010ResBody(
			GJS01501010000010ResBody gjS01501030000010ResBody) {
		this.gjS01501030000010ResBody = gjS01501030000010ResBody;
	}
}
