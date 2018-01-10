package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 放款撤销接口---响应对象
 * @author shendl
 *
 */
public class GJS01501070000007ResBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1607678953264673977L;
	private String debitNo;//借据号
	private String knotSeqNo;//国结系统流水号
	private String transStat;//交易状态
	private String errMsg;//错误消息
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getKnotSeqNo() {
		return knotSeqNo;
	}
	public void setKnotSeqNo(String knotSeqNo) {
		this.knotSeqNo = knotSeqNo;
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
		return "GJS01501030000007ResBody [debitNo=" + debitNo + ", knotSeqNo="
				+ knotSeqNo + ", transStat=" + transStat + ", errMsg=" + errMsg
				+ "]";
	}
	
	
}	
