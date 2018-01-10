package com.bos.gjService;

import java.io.Serializable;

/**
 * ESB交易响应头（ResTranHeader）
 * @author shendl
 *
 */
public class ResTranHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3245222371129955759L;
	public String HSecFlag;//安全标志
	public String HCombFlag;//组合标志
	public String HSecInfoVerNo;//安全信息版本号
	public String HMsgRefNo;//消息参考号
	public String HIdentFlag;//认证标志
	public String HSuperFlag;//监管标志
	public String HChkFlag;//复核标志
	public String HChkTxnCd;//发起端复核交易码
	public String HVerfCd;//校验码
	public String HTranRes;//交易保留字段
	public String HRefTxnCd;//关联交易码
	public String HServerDt;//服务日期
	public String HServerTm;//服务时间
	public String HServerSeq;//服务流水
	public String HAcountDt;//会计日期
	public String HRefSeq;//关联流水
	public String HRefDt;//关联日期
	public String HNextStep;//下一步信息
	public String HVchChk;//凭证校验
	public String HRetResInfo;//返回保留信息
	public String HErrTranNo;//出错交易序号
	public String HAssiInfo;//辅助信息
	public String HRetCode;//返回代码
	public String HRetNo;//返回编号---响应码，7个A表示成功
	public String HRetMsg;//返回信息---响应信息
	public String HWarnMsg;//警告信息
	public String getHSecFlag() {
		return HSecFlag;
	}
	public void setHSecFlag(String hSecFlag) {
		HSecFlag = hSecFlag;
	}
	public String getHCombFlag() {
		return HCombFlag;
	}
	public void setHCombFlag(String hCombFlag) {
		HCombFlag = hCombFlag;
	}
	public String getHSecInfoVerNo() {
		return HSecInfoVerNo;
	}
	public void setHSecInfoVerNo(String hSecInfoVerNo) {
		HSecInfoVerNo = hSecInfoVerNo;
	}
	public String getHMsgRefNo() {
		return HMsgRefNo;
	}
	public void setHMsgRefNo(String hMsgRefNo) {
		HMsgRefNo = hMsgRefNo;
	}
	public String getHIdentFlag() {
		return HIdentFlag;
	}
	public void setHIdentFlag(String hIdentFlag) {
		HIdentFlag = hIdentFlag;
	}
	public String getHSuperFlag() {
		return HSuperFlag;
	}
	public void setHSuperFlag(String hSuperFlag) {
		HSuperFlag = hSuperFlag;
	}
	public String getHChkFlag() {
		return HChkFlag;
	}
	public void setHChkFlag(String hChkFlag) {
		HChkFlag = hChkFlag;
	}
	public String getHChkTxnCd() {
		return HChkTxnCd;
	}
	public void setHChkTxnCd(String hChkTxnCd) {
		HChkTxnCd = hChkTxnCd;
	}
	public String getHVerfCd() {
		return HVerfCd;
	}
	public void setHVerfCd(String hVerfCd) {
		HVerfCd = hVerfCd;
	}
	public String getHTranRes() {
		return HTranRes;
	}
	public void setHTranRes(String hTranRes) {
		HTranRes = hTranRes;
	}
	public String getHRefTxnCd() {
		return HRefTxnCd;
	}
	public void setHRefTxnCd(String hRefTxnCd) {
		HRefTxnCd = hRefTxnCd;
	}
	public String getHServerDt() {
		return HServerDt;
	}
	public void setHServerDt(String hServerDt) {
		HServerDt = hServerDt;
	}
	public String getHServerTm() {
		return HServerTm;
	}
	public void setHServerTm(String hServerTm) {
		HServerTm = hServerTm;
	}
	public String getHServerSeq() {
		return HServerSeq;
	}
	public void setHServerSeq(String hServerSeq) {
		HServerSeq = hServerSeq;
	}
	public String getHAcountDt() {
		return HAcountDt;
	}
	public void setHAcountDt(String hAcountDt) {
		HAcountDt = hAcountDt;
	}
	public String getHRefSeq() {
		return HRefSeq;
	}
	public void setHRefSeq(String hRefSeq) {
		HRefSeq = hRefSeq;
	}
	public String getHRefDt() {
		return HRefDt;
	}
	public void setHRefDt(String hRefDt) {
		HRefDt = hRefDt;
	}
	public String getHNextStep() {
		return HNextStep;
	}
	public void setHNextStep(String hNextStep) {
		HNextStep = hNextStep;
	}
	public String getHVchChk() {
		return HVchChk;
	}
	public void setHVchChk(String hVchChk) {
		HVchChk = hVchChk;
	}
	public String getHRetResInfo() {
		return HRetResInfo;
	}
	public void setHRetResInfo(String hRetResInfo) {
		HRetResInfo = hRetResInfo;
	}
	public String getHErrTranNo() {
		return HErrTranNo;
	}
	public void setHErrTranNo(String hErrTranNo) {
		HErrTranNo = hErrTranNo;
	}
	public String getHAssiInfo() {
		return HAssiInfo;
	}
	public void setHAssiInfo(String hAssiInfo) {
		HAssiInfo = hAssiInfo;
	}
	public String getHRetCode() {
		return HRetCode;
	}
	public void setHRetCode(String hRetCode) {
		HRetCode = hRetCode;
	}
	public String getHRetNo() {
		return HRetNo;
	}
	public void setHRetNo(String hRetNo) {
		HRetNo = hRetNo;
	}
	public String getHRetMsg() {
		return HRetMsg;
	}
	public void setHRetMsg(String hRetMsg) {
		HRetMsg = hRetMsg;
	}
	public String getHWarnMsg() {
		return HWarnMsg;
	}
	public void setHWarnMsg(String hWarnMsg) {
		HWarnMsg = hWarnMsg;
	}
	
	
}
