package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 进口信用证开证修改接口---响应对象
 * @author shendl
 *
 */
public class GJS01501030000003Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 678072174206658925L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
	private GJS01501030000003ResBody gjS01501030000003ResBody;
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
	public GJS01501030000003ResBody getGjS01501030000003ResBody() {
		return gjS01501030000003ResBody;
	}
	public void setGjS01501030000003ResBody(
			GJS01501030000003ResBody gjS01501030000003ResBody) {
		this.gjS01501030000003ResBody = gjS01501030000003ResBody;
	}
	
	
}
