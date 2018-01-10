package com.primeton.crmsgj;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 进口保函修改---响应对象
 * @author shendl
 *
 */
public class GJS01501030000005Res {
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000005ResBody gjS01501030000005ResBody;
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
	public GJS01501030000005ResBody getGjS01501030000005ResBody() {
		return gjS01501030000005ResBody;
	}
	public void setGjS01501030000005ResBody(
			GJS01501030000005ResBody gjS01501030000005ResBody) {
		this.gjS01501030000005ResBody = gjS01501030000005ResBody;
	}
	
	
}
