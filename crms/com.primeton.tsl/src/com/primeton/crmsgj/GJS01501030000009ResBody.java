package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 融资展期接口---响应对象体
 * @author shendl
 *
 */
public class GJS01501030000009ResBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6655258337454217253L;
	private String knotBusiNo;//国结业务编号
	private String transStat;//交易状态
	private String errMsg;//错误消息
	public String getKnotBusiNo() {
		return knotBusiNo;
	}
	public void setKnotBusiNo(String knotBusiNo) {
		this.knotBusiNo = knotBusiNo;
	}
	public String getTransStat() {
		return transStat;
	}
	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "GJS01501030000009ResBody [knotBusiNo=" + knotBusiNo
				+ ", transStat=" + transStat + ", errMsg=" + errMsg + "]";
	}
}
