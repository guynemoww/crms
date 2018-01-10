package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD061_OutFlushesReq implements Serializable {
	private static final long serialVersionUID = -8300840620287498782L;

	private String oldFrontTransDate;// 原前台交易日期
	private String frontSeqNo;// 前台流水
	private String transCode;// 交易码
	private String nextDaySignFlg;// 隔日抹账允许标志
	private String ynPeriCan;// 是否外围冲正
	private String orgNum;//核心记账机构--必输
	private String tranCode;//交易流水号--核算对账用
	public OXD061_OutFlushesReq() {

	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getOldFrontTransDate() {
		return oldFrontTransDate;
	}

	public void setOldFrontTransDate(String oldFrontTransDate) {
		this.oldFrontTransDate = oldFrontTransDate;
	}

	public String getFrontSeqNo() {
		return frontSeqNo;
	}

	public void setFrontSeqNo(String frontSeqNo) {
		this.frontSeqNo = frontSeqNo;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getNextDaySignFlg() {
		return nextDaySignFlg;
	}

	public void setNextDaySignFlg(String nextDaySignFlg) {
		this.nextDaySignFlg = nextDaySignFlg;
	}

	public String getYnPeriCan() {
		return ynPeriCan;
	}

	public void setYnPeriCan(String ynPeriCan) {
		this.ynPeriCan = ynPeriCan;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	
}
