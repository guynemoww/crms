package com.bos.gjService;

import java.io.Serializable;
/**
 * 额度查询请求信息
 * @author chenpan
 *
 */
public class D005RequestBody implements Serializable{

	private static final long serialVersionUID = 3754767175558906286L;
	private String ecifPartyNum;//客户号
	public D005RequestBody() {
	}
	public String getEcifPartyNum() {
		return ecifPartyNum;
	}
	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}
	
}
