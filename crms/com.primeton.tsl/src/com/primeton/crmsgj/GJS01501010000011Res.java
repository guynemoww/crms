package com.primeton.crmsgj;

import java.io.Serializable;

import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;

/**
 * 国结业务表外业务放款状态查询---响应对象
 * @author shendl
 *
 */
public class GJS01501010000011Res implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4123112182329040666L;
	private ResponseHeader resHeader;
	private ResTranHeader resTranHeader;
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
}
