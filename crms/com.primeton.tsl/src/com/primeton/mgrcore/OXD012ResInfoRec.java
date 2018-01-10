package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD012ResInfoRec implements Serializable {
	private static final long serialVersionUID = -4295298516139908544L;
	private String rataKind;// 费率种类
	private String rataNme;// 费率名称
	private String feeAmt;// 手续费金额
	private String costPayFlg;// 费用收付标志
	private String patRecFlg;// 收讫标志
	private String oweAmt;// 欠费金额
	private String eveMarkNo;// 事件登记序号
	private String costCode;// 费用业务代号
	private String costExps;// 费用业务分类
	private String busiNoNm;// 业务代号名
	private String subjectNo;// 科目号

	public String getRataKind() {
		return rataKind;
	}

	public void setRataKind(String rataKind) {
		this.rataKind = rataKind;
	}

	public String getRataNme() {
		return rataNme;
	}

	public void setRataNme(String rataNme) {
		this.rataNme = rataNme;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getCostPayFlg() {
		return costPayFlg;
	}

	public void setCostPayFlg(String costPayFlg) {
		this.costPayFlg = costPayFlg;
	}

	public String getPatRecFlg() {
		return patRecFlg;
	}

	public void setPatRecFlg(String patRecFlg) {
		this.patRecFlg = patRecFlg;
	}

	public String getOweAmt() {
		return oweAmt;
	}

	public void setOweAmt(String oweAmt) {
		this.oweAmt = oweAmt;
	}

	public String getEveMarkNo() {
		return eveMarkNo;
	}

	public void setEveMarkNo(String eveMarkNo) {
		this.eveMarkNo = eveMarkNo;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getCostExps() {
		return costExps;
	}

	public void setCostExps(String costExps) {
		this.costExps = costExps;
	}

	public String getBusiNoNm() {
		return busiNoNm;
	}

	public void setBusiNoNm(String busiNoNm) {
		this.busiNoNm = busiNoNm;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public OXD012ResInfoRec() {

	}

	@Override
	public String toString() {
		return "OXD012ResInfoRec [rataKind=" + rataKind + ", rataNme="
				+ rataNme + ", feeAmt=" + feeAmt + ", costPayFlg=" + costPayFlg
				+ ", patRecFlg=" + patRecFlg + ", oweAmt=" + oweAmt
				+ ", eveMarkNo=" + eveMarkNo + ", costCode=" + costCode
				+ ", costExps=" + costExps + ", busiNoNm=" + busiNoNm
				+ ", subjectNo=" + subjectNo + "]";
	}
}
