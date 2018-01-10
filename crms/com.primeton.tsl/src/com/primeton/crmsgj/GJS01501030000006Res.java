package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 提货担保接口---响应对象
 * @author shendl
 *
 */
public class GJS01501030000006Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4727213713291886178L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000006ResBody gjS01501030000006ResBody;
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
	public GJS01501030000006ResBody getGjS01501030000006ResBody() {
		return gjS01501030000006ResBody;
	}
	public void setGjS01501030000006ResBody(
			GJS01501030000006ResBody gjS01501030000006ResBody) {
		this.gjS01501030000006ResBody = gjS01501030000006ResBody;
	}
	
	
}
