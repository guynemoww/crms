package com.bos.gjService;

import java.io.Serializable;

/**
 * 响应ESB头(ResponseHeader)
 * @author shendl
 */
public class ResponseHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8153950192118499633L;
	public String VersionNo;//版本号
	public String ReqSysCode;//请求方系统代码
	public String ReqSecCode;//安全节点号
	public String TxType;//RP
	public String TxMode;//0-正常 1-冲销2-冲正 3-重发
	public String TxCode;//ESB服务码
	public String ReqDate;//请求方交易日期
	public String ReqTime;//请求方交易时间戳
	public String ReqSeqNo;//请求方交易流水号
	public String SvrDate;//服务方交易日期
	public String SvrTime;//服务方交易时间戳
	public String SvrSeqNo;//服务方交易流水号
	public String RecvFileName;//接收文件名
	public int TotNum;//查询总记录数
	public int CurrNum;//当前查询返回记录数
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
	public String getSvrDate() {
		return SvrDate;
	}
	public void setSvrDate(String svrDate) {
		SvrDate = svrDate;
	}
	public String getSvrTime() {
		return SvrTime;
	}
	public void setSvrTime(String svrTime) {
		SvrTime = svrTime;
	}
	public String getSvrSeqNo() {
		return SvrSeqNo;
	}
	public void setSvrSeqNo(String svrSeqNo) {
		SvrSeqNo = svrSeqNo;
	}
	public String getRecvFileName() {
		return RecvFileName;
	}
	public void setRecvFileName(String recvFileName) {
		RecvFileName = recvFileName;
	}
	public int getTotNum() {
		return TotNum;
	}
	public void setTotNum(int totNum) {
		TotNum = totNum;
	}
	public int getCurrNum() {
		return CurrNum;
	}
	public void setCurrNum(int currNum) {
		CurrNum = currNum;
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
