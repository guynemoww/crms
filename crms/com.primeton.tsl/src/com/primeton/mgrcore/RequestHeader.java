package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 请求ESB报文头(ResquestHeader)
 * 
 * @author MJF
 */
public class RequestHeader implements Serializable {
	private static final long serialVersionUID = -927446600079956194L;

	private String versionNo;// 版本号
	private String reqSysCode;// 请求方系统代码
	private String reqSecCode;// 安全节点号
	private String txType;// rQ
	private String txMode;// 0-正常 1-冲销2-冲正 3-重发
	private String txCode;// soapenv服务码
	private String reqDate;// 业务日期
	private String reqTime;// 机器时间戳
	private String reqSeqNo;// 请求方交易流水号
	private String chanlNo;// 渠道号（字符）
	private String brch;// 机构编号
	private String termNo;// 终端号
	private String oper;// 柜员
	private String sendFileName;// 发送文件名
	private String beginRec;// 开始记录数
	private BigInteger maxRec;// 一次查询最大记录数
	private String fileHMac;// 文件摘要
	private String hmac;// 报文摘要

	public RequestHeader() {

	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getReqSysCode() {
		return reqSysCode;
	}

	public void setReqSysCode(String reqSysCode) {
		this.reqSysCode = reqSysCode;
	}

	public String getReqSecCode() {
		return reqSecCode;
	}

	public void setReqSecCode(String reqSecCode) {
		this.reqSecCode = reqSecCode;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public String getTxMode() {
		return txMode;
	}

	public void setTxMode(String txMode) {
		this.txMode = txMode;
	}

	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getReqSeqNo() {
		return reqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public String getChanlNo() {
		return chanlNo;
	}

	public void setChanlNo(String chanlNo) {
		this.chanlNo = chanlNo;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getSendFileName() {
		return sendFileName;
	}

	public void setSendFileName(String sendFileName) {
		this.sendFileName = sendFileName;
	}

	public String getBeginRec() {
		return beginRec;
	}

	public void setBeginRec(String beginRec) {
		this.beginRec = beginRec;
	}

	public BigInteger getMaxRec() {
		return maxRec;
	}

	public void setMaxRec(BigInteger maxRec) {
		this.maxRec = maxRec;
	}

	public String getFileHMac() {
		return fileHMac;
	}

	public void setFileHMac(String fileHMac) {
		this.fileHMac = fileHMac;
	}

	public String getHmac() {
		return hmac;
	}

	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

}
