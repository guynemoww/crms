package com.primeton.tsl.ecif.port;

public class CommRequestHeader {
	public String VersionNo; // 版本号
	public String ReqSysCode; // 请求方系统代码
	public String ReqSecCode; // 安全节点号
	public String TxType; // RQ
	public String TxMode; // 0-正常 1-冲销2-冲正 3-重发
	public String TxCode; // soapenv服务码
	public String ReqDate; // 业务日期
	public String ReqTime; // 机器时间戳
	public String ReqSeqNo; // 请求方交易流水号
	public String ChanlNo; // 渠道号（字符）
	public String Brch; // 机构编号
	public String TermNo; // 终端号
	public String Oper; // 柜员
	public String SendFileName; // 发送文件名
	public String BeginRec; // 开始记录数
	public Integer MaxRec; // 一次查询最大记录数
	public String FileHMac; // 文件摘要
	public String HMac; // 报文摘要

	public String getVersionNo() {
		return VersionNo;
	}

	public void setVersionNo(String versionNo) {
		VersionNo = versionNo;
	}

	public String getReqSysCode() {
		return ReqSysCode;
	}

	public void setReqSysCode(String reqSysCode) {
		ReqSysCode = reqSysCode;
	}

	public String getReqSecCode() {
		return ReqSecCode;
	}

	public void setReqSecCode(String reqSecCode) {
		ReqSecCode = reqSecCode;
	}

	public String getTxType() {
		return TxType;
	}

	public void setTxType(String txType) {
		TxType = txType;
	}

	public String getTxMode() {
		return TxMode;
	}

	public void setTxMode(String txMode) {
		TxMode = txMode;
	}

	public String getTxCode() {
		return TxCode;
	}

	public void setTxCode(String txCode) {
		TxCode = txCode;
	}

	public String getReqDate() {
		return ReqDate;
	}

	public void setReqDate(String reqDate) {
		ReqDate = reqDate;
	}

	public String getReqTime() {
		return ReqTime;
	}

	public void setReqTime(String reqTime) {
		ReqTime = reqTime;
	}

	public String getReqSeqNo() {
		return ReqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		ReqSeqNo = reqSeqNo;
	}

	public String getChanlNo() {
		return ChanlNo;
	}

	public void setChanlNo(String chanlNo) {
		ChanlNo = chanlNo;
	}

	public String getBrch() {
		return Brch;
	}

	public void setBrch(String brch) {
		Brch = brch;
	}

	public String getTermNo() {
		return TermNo;
	}

	public void setTermNo(String termNo) {
		TermNo = termNo;
	}

	public String getOper() {
		return Oper;
	}

	public void setOper(String oper) {
		Oper = oper;
	}

	public String getSendFileName() {
		return SendFileName;
	}

	public void setSendFileName(String sendFileName) {
		SendFileName = sendFileName;
	}

	public String getBeginRec() {
		return BeginRec;
	}

	public void setBeginRec(String beginRec) {
		BeginRec = beginRec;
	}

	public Integer getMaxRec() {
		return MaxRec;
	}

	public void setMaxRec(Integer maxRec) {
		MaxRec = maxRec;
	}

	public String getFileHMac() {
		return FileHMac;
	}

	public void setFileHMac(String fileHMac) {
		FileHMac = fileHMac;
	}

	public String getHMac() {
		return HMac;
	}

	public void setHMac(String hMac) {
		HMac = hMac;
	}

}