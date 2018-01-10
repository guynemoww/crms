package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * ESB交易响应头（ResTranHeader）
 * 
 * @author MJF
 * 
 */
public class ResTranHeader implements Serializable {
	private static final long serialVersionUID = -2582455511385952685L;

	private String HSecFlag;// 加密标志
	private String HCombFlag;// 组合标志
	private String HSvcInfo;// 主机服务名
	private String HSecInfoVerNo;// 密钥版本号
	private String HMsgRefNo;// 报文跟踪号
	private String HIdentFlag;// 验印标志
	private String HSuperFlag;// 是否外汇监管标志
	private String HChkFlag;// 监督复核标志
	private String HChkTxnCd;// 复核交易码
	private String HVerfCd;// 凭证校验码
	private String HTranRes;// 系统保留
	private String HRefTxnCd;// 联动交易码
	private String HServerDt;// 交易日期
	private String HServerTm;// 交易时间
	private String HServerSeq;// 柜员流水号
	private String HAcountDt;// 会计日期
	private String HRefSeq;// 第三方流水号
	private String HRefDt;// 第三方日期
	private String HNextStep;// 下一交易码
	private String HVchChk;// 凭证校验
	private String HRetResInfo;// 返回交易头保留
	private String HErrTranNo;// 出错交易序号
	private String HAssiInfo;// 通知前台不打印列表
	private String HRetCode;// 错误代号
	private String HRetNo;// 错误编号
	private String HRetMsg;// 错误信息
	private String HWarnMsg;// 警告信息

	public ResTranHeader() {

	}

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

	public String getHSvcInfo() {
		return HSvcInfo;
	}

	public void setHSvcInfo(String hSvcInfo) {
		HSvcInfo = hSvcInfo;
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

	@Override
	public String toString() {
		return "ResTranHeader [HSecFlag=" + HSecFlag + ", HCombFlag="
				+ HCombFlag + ", HSvcInfo=" + HSvcInfo + ", HSecInfoVerNo="
				+ HSecInfoVerNo + ", HMsgRefNo=" + HMsgRefNo + ", HIdentFlag="
				+ HIdentFlag + ", HSuperFlag=" + HSuperFlag + ", HChkFlag="
				+ HChkFlag + ", HChkTxnCd=" + HChkTxnCd + ", HVerfCd="
				+ HVerfCd + ", HTranRes=" + HTranRes + ", HRefTxnCd="
				+ HRefTxnCd + ", HServerDt=" + HServerDt + ", HServerTm="
				+ HServerTm + ", HServerSeq=" + HServerSeq + ", HAcountDt="
				+ HAcountDt + ", HRefSeq=" + HRefSeq + ", HRefDt=" + HRefDt
				+ ", HNextStep=" + HNextStep + ", HVchChk=" + HVchChk
				+ ", HRetResInfo=" + HRetResInfo + ", HErrTranNo=" + HErrTranNo
				+ ", HAssiInfo=" + HAssiInfo + ", HRetCode=" + HRetCode
				+ ", HRetNo=" + HRetNo + ", HRetMsg=" + HRetMsg + ", HWarnMsg="
				+ HWarnMsg + "]";
	}

}
