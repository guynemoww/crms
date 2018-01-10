package com.primeton.mgrcore;

import java.io.Serializable;

public class F76091 implements Serializable {
	private static final long serialVersionUID = -6504062385244591229L;

	private String transDate;// 交易日期
	private String transTime;// 交易时间
	private String transCode;// 交易码
	private String saleBrch;// 营业机构
	private String affairsBrchNo;// 帐务机构号
	private String chnl;// 渠道
	private String termNo;// 终端号
	private String transOper;// 交易柜员
	private String authCter;// 授权柜员
	private String operSeq;// 柜员流水号
	private String transSeqType;// 交易流水类型
	private String frontDate;// 前台日期
	private String frontSeqNo;// 前台流水
	private String agntServNum;// 代理业务号
	private String transReconType;// 交易对帐类型
	private String transName;// 交易名称
	private String correctFlag;// 被冲正标志
	private String origTellerSeq;// 原柜员流水号
	private String hostDate;// 主机日期
	private String time;// 时间
	private String posCode;// POS终端编号
	private String rechkDate;// 复核日期
	private String applyRechkSeq;// 申请复核流水号

	public F76091() {

	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getSaleBrch() {
		return saleBrch;
	}

	public void setSaleBrch(String saleBrch) {
		this.saleBrch = saleBrch;
	}

	public String getAffairsBrchNo() {
		return affairsBrchNo;
	}

	public void setAffairsBrchNo(String affairsBrchNo) {
		this.affairsBrchNo = affairsBrchNo;
	}

	public String getChnl() {
		return chnl;
	}

	public void setChnl(String chnl) {
		this.chnl = chnl;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getTransOper() {
		return transOper;
	}

	public void setTransOper(String transOper) {
		this.transOper = transOper;
	}

	public String getAuthCter() {
		return authCter;
	}

	public void setAuthCter(String authCter) {
		this.authCter = authCter;
	}

	public String getOperSeq() {
		return operSeq;
	}

	public void setOperSeq(String operSeq) {
		this.operSeq = operSeq;
	}

	public String getTransSeqType() {
		return transSeqType;
	}

	public void setTransSeqType(String transSeqType) {
		this.transSeqType = transSeqType;
	}

	public String getFrontDate() {
		return frontDate;
	}

	public void setFrontDate(String frontDate) {
		this.frontDate = frontDate;
	}

	public String getFrontSeqNo() {
		return frontSeqNo;
	}

	public void setFrontSeqNo(String frontSeqNo) {
		this.frontSeqNo = frontSeqNo;
	}

	public String getAgntServNum() {
		return agntServNum;
	}

	public void setAgntServNum(String agntServNum) {
		this.agntServNum = agntServNum;
	}

	public String getTransReconType() {
		return transReconType;
	}

	public void setTransReconType(String transReconType) {
		this.transReconType = transReconType;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}

	public String getOrigTellerSeq() {
		return origTellerSeq;
	}

	public void setOrigTellerSeq(String origTellerSeq) {
		this.origTellerSeq = origTellerSeq;
	}

	public String getHostDate() {
		return hostDate;
	}

	public void setHostDate(String hostDate) {
		this.hostDate = hostDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getRechkDate() {
		return rechkDate;
	}

	public void setRechkDate(String rechkDate) {
		this.rechkDate = rechkDate;
	}

	public String getApplyRechkSeq() {
		return applyRechkSeq;
	}

	public void setApplyRechkSeq(String applyRechkSeq) {
		this.applyRechkSeq = applyRechkSeq;
	}

	@Override
	public String toString() {
		return "F76091 [transDate=" + transDate + ", transTime=" + transTime
				+ ", transCode=" + transCode + ", saleBrch=" + saleBrch
				+ ", affairsBrchNo=" + affairsBrchNo + ", chnl=" + chnl
				+ ", termNo=" + termNo + ", transOper=" + transOper
				+ ", authCter=" + authCter + ", operSeq=" + operSeq
				+ ", transSeqType=" + transSeqType + ", frontDate=" + frontDate
				+ ", frontSeqNo=" + frontSeqNo + ", agntServNum=" + agntServNum
				+ ", transReconType=" + transReconType + ", transName="
				+ transName + ", correctFlag=" + correctFlag
				+ ", origTellerSeq=" + origTellerSeq + ", hostDate=" + hostDate
				+ ", time=" + time + ", posCode=" + posCode + ", rechkDate="
				+ rechkDate + ", applyRechkSeq=" + applyRechkSeq + "]";
	}
}
