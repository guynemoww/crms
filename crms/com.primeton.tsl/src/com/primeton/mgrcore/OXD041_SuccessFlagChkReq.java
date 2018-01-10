package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD041_SuccessFlagChkReq implements Serializable {
	private static final long serialVersionUID = 7304987111062316169L;

	private String searchPrintFlag;// 查询打印标志
	private String transDate;// 交易日期
	private String transCode;// 交易码
	private String saleBrch;// 营业机构
	private String transOper;// 交易柜员
	private String identifier;// 标识符
	private String operSeq;// 柜员流水号
	private String frontDate;// 前台日期
	private String foregroundSeq;// 前台流水号
	private String correctFlag;// 被冲正标志
	private String hostDate;// 主机日期
	private String begNum;// 起始笔数
	private String searchNum;// 查询笔数
	private String orgNum;//核心记账机构--必输

	public String getSearchPrintFlag() {
		return searchPrintFlag;
	}

	public void setSearchPrintFlag(String searchPrintFlag) {
		this.searchPrintFlag = searchPrintFlag;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
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

	public String getTransOper() {
		return transOper;
	}

	public void setTransOper(String transOper) {
		this.transOper = transOper;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getOperSeq() {
		return operSeq;
	}

	public void setOperSeq(String operSeq) {
		this.operSeq = operSeq;
	}

	public String getFrontDate() {
		return frontDate;
	}

	public void setFrontDate(String frontDate) {
		this.frontDate = frontDate;
	}

	public String getForegroundSeq() {
		return foregroundSeq;
	}

	public void setForegroundSeq(String foregroundSeq) {
		this.foregroundSeq = foregroundSeq;
	}

	public String getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}

	public String getHostDate() {
		return hostDate;
	}

	public void setHostDate(String hostDate) {
		this.hostDate = hostDate;
	}

	public String getBegNum() {
		return begNum;
	}

	public void setBegNum(String begNum) {
		this.begNum = begNum;
	}

	public String getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(String searchNum) {
		this.searchNum = searchNum;
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public OXD041_SuccessFlagChkReq() {

	}

}
