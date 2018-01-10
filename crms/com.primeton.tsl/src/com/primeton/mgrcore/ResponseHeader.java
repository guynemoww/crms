package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 响应ESB头(ResponseHeader)
 * 
 * @author MJF
 */
public class ResponseHeader implements Serializable {
	private static final long serialVersionUID = 3738544944405644098L;

	private String versionNo;// 版本号
	private String reqSysCode;// 请求方系统代码
	private String reqSecCode;// 安全节点号
	private String txType;// RP
	private String txMode;// 0-正常 1-冲销 2-冲正 3-重发
	private String txCode;// ESB服务码
	private String reqDate;// 请求方交易日期
	private String reqTime;// 请求方交易时间戳
	private String reqSeqNo;// 请求方交易流水号
	private String svrDate;// 服务方交易日期
	private String svrTime;// 服务方交易时间戳
	private String svrSeqNo;// 服务方交易流水号
	private String recvFileName;// 接收文件各
	private String totNum;// 查询总记录数
	private String currNum;// 当前查询返回记录数
	private String fileHMac;// 文件摘要
	private String hmac;// 报文摘要

	public ResponseHeader() {

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

	public String getSvrDate() {
		return svrDate;
	}

	public void setSvrDate(String svrDate) {
		this.svrDate = svrDate;
	}

	public String getSvrTime() {
		return svrTime;
	}

	public void setSvrTime(String svrTime) {
		this.svrTime = svrTime;
	}

	public String getSvrSeqNo() {
		return svrSeqNo;
	}

	public void setSvrSeqNo(String svrSeqNo) {
		this.svrSeqNo = svrSeqNo;
	}

	public String getRecvFileName() {
		return recvFileName;
	}

	public void setRecvFileName(String recvFileName) {
		this.recvFileName = recvFileName;
	}

	public String getTotNum() {
		return totNum;
	}

	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}

	public String getCurrNum() {
		return currNum;
	}

	public void setCurrNum(String currNum) {
		this.currNum = currNum;
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

	@Override
	public String toString() {
		return "ResponseHeader [versionNo=" + versionNo + ", reqSysCode="
				+ reqSysCode + ", reqSecCode=" + reqSecCode + ", txType="
				+ txType + ", txMode=" + txMode + ", txCode=" + txCode
				+ ", reqDate=" + reqDate + ", reqTime=" + reqTime
				+ ", reqSeqNo=" + reqSeqNo + ", svrDate=" + svrDate
				+ ", svrTime=" + svrTime + ", svrSeqNo=" + svrSeqNo
				+ ", recvFileName=" + recvFileName + ", totNum=" + totNum
				+ ", currNum=" + currNum + ", fileHMac=" + fileHMac + ", hmac="
				+ hmac + "]";
	}

}
