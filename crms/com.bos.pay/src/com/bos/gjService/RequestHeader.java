package com.bos.gjService;

import java.io.Serializable;

/**
 * 请求ESB报文头(ResquestHeader)
 * @author shendl
 */
public class RequestHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7272006027837886504L;
	public String VersionNo;//版本号
	public String ReqSysCode;//请求方系统代码
	public String ReqSecCode;//安全节点号
	public String TxType;//RQ
	public String TxMode;//0-正常 1-冲销2-冲正 3-重发
	public String TxCode;//ESB服务码
	public String ReqDate;//业务日期
	public String ReqTime;//机器时间戳---Yyyymmddhhnnssuuuuuu
	public String ReqSeqNo;//请求方交易流水号
	public String ChanlNo;//渠道号（字符）
	public String Brch;//机构编号
	public String TermNo;//终端号
	public String Oper;//柜员
	public String SendFileName; //发送文件名
	public int BeginRec;//开始记录数
	public int MaxRec;//一次查询最大记录数
	public String FileHMac;//文件摘要
	public String Hmac;//报文摘要
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
	public int getBeginRec() {
		return BeginRec;
	}
	public void setBeginRec(int beginRec) {
		BeginRec = beginRec;
	}
	public int getMaxRec() {
		return MaxRec;
	}
	public void setMaxRec(int maxRec) {
		MaxRec = maxRec;
	}
	public String getFileHMac() {
		return FileHMac;
	}
	public void setFileHMac(String fileHMac) {
		FileHMac = fileHMac;
	}
	public String getHmac() {
		return Hmac;
	}
	public void setHmac(String hmac) {
		Hmac = hmac;
	}
}
