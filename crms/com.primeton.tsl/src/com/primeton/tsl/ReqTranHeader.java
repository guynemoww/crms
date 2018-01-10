package com.primeton.tsl;

import java.io.Serializable;

/**
 * 请求ESB交易报文头(ReqTranHeader)
 * @author shendl
 */
public class ReqTranHeader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151282656161517458L;
	public String HPinSeed;//PIN种子
	public String HOriChnl;//渠道来源
	public String HSecFlag;//加密标志 暂送0  
	public String HPwdFlag;//PIN标志 0-无密码，1-3DES，2-SM4  
	public String HCombFlag;//组合标志 0
	public String HSvcInfo;//主机服务名 交易相关,未知填"zuhejy_01"  
	public String HSecInfoVerNo;//密钥版本号
	public String HSysChnl;//系统渠道号  
	public String HLegaObj;//法人代码 "9999"  
	public String HMsgRefNo;//报文跟踪号 实际值 
	public String HIntOrigMark;//HintOrigMark
	public String HTermNo;//终端号 
	public String HCityCd;//城市代码 发起地，取到则送 
	public String HBrchNo;//机构代码 实际机构号 
	public String HUserID;//交易柜员 实际柜员号 
	public String HTxnCd;//交易代码 实际值 
	public String HTxnMod;//交易模式 暂为空 
	public String HReserveLen;//本交易包长度 暂为空 
	public String HSenderSvcCd;//前台交易码 实际值 
	public String HSenderSeq;//前台流水号 实际值 核心规范 
	public String HSenderDate;//前台日期 实际值 
	public String HAuthUserID;//授权柜员 
	public String HAuthVerfInfo;//授权密码 
	public String HAuthFlag;//授权柜员有无卡标志 暂为空 
	public String HRefSeq;//第三方流水 实际值 
	public String HAuthSeri;//授权柜员卡序号 实际值 
	public String HHostSeq;//后台柜员流水号 实际值 
	public String HRefDt;//第三方日期 实际值 
	public String HSvcVer;//交易接口版本号 暂为空 
	public String HReserveMsg; //上送交易头保留 暂为空
	public String getHPinSeed() {
		return HPinSeed;
	}
	public void setHPinSeed(String hPinSeed) {
		HPinSeed = hPinSeed;
	}
	public String getHOriChnl() {
		return HOriChnl;
	}
	public void setHOriChnl(String hOriChnl) {
		HOriChnl = hOriChnl;
	}
	public String getHSecFlag() {
		return HSecFlag;
	}
	public void setHSecFlag(String hSecFlag) {
		HSecFlag = hSecFlag;
	}
	public String getHPwdFlag() {
		return HPwdFlag;
	}
	public void setHPwdFlag(String hPwdFlag) {
		HPwdFlag = hPwdFlag;
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
	public String getHSysChnl() {
		return HSysChnl;
	}
	public void setHSysChnl(String hSysChnl) {
		HSysChnl = hSysChnl;
	}
	public String getHLegaObj() {
		return HLegaObj;
	}
	public void setHLegaObj(String hLegaObj) {
		HLegaObj = hLegaObj;
	}
	public String getHMsgRefNo() {
		return HMsgRefNo;
	}
	public void setHMsgRefNo(String hMsgRefNo) {
		HMsgRefNo = hMsgRefNo;
	}
	public String getHIntOrigMark() {
		return HIntOrigMark;
	}
	public void setHIntOrigMark(String hIntOrigMark) {
		HIntOrigMark = hIntOrigMark;
	}
	public String getHTermNo() {
		return HTermNo;
	}
	public void setHTermNo(String hTermNo) {
		HTermNo = hTermNo;
	}
	public String getHCityCd() {
		return HCityCd;
	}
	public void setHCityCd(String hCityCd) {
		HCityCd = hCityCd;
	}
	public String getHBrchNo() {
		return HBrchNo;
	}
	public void setHBrchNo(String hBrchNo) {
		HBrchNo = hBrchNo;
	}
	public String getHUserID() {
		return HUserID;
	}
	public void setHUserID(String hUserID) {
		HUserID = hUserID;
	}
	public String getHTxnCd() {
		return HTxnCd;
	}
	public void setHTxnCd(String hTxnCd) {
		HTxnCd = hTxnCd;
	}
	public String getHTxnMod() {
		return HTxnMod;
	}
	public void setHTxnMod(String hTxnMod) {
		HTxnMod = hTxnMod;
	}
	public String getHReserveLen() {
		return HReserveLen;
	}
	public void setHReserveLen(String hReserveLen) {
		HReserveLen = hReserveLen;
	}
	public String getHSenderSvcCd() {
		return HSenderSvcCd;
	}
	public void setHSenderSvcCd(String hSenderSvcCd) {
		HSenderSvcCd = hSenderSvcCd;
	}
	public String getHSenderSeq() {
		return HSenderSeq;
	}
	public void setHSenderSeq(String hSenderSeq) {
		HSenderSeq = hSenderSeq;
	}
	public String getHSenderDate() {
		return HSenderDate;
	}
	public void setHSenderDate(String hSenderDate) {
		HSenderDate = hSenderDate;
	}
	public String getHAuthUserID() {
		return HAuthUserID;
	}
	public void setHAuthUserID(String hAuthUserID) {
		HAuthUserID = hAuthUserID;
	}
	public String getHAuthVerfInfo() {
		return HAuthVerfInfo;
	}
	public void setHAuthVerfInfo(String hAuthVerfInfo) {
		HAuthVerfInfo = hAuthVerfInfo;
	}
	public String getHAuthFlag() {
		return HAuthFlag;
	}
	public void setHAuthFlag(String hAuthFlag) {
		HAuthFlag = hAuthFlag;
	}
	public String getHRefSeq() {
		return HRefSeq;
	}
	public void setHRefSeq(String hRefSeq) {
		HRefSeq = hRefSeq;
	}
	public String getHAuthSeri() {
		return HAuthSeri;
	}
	public void setHAuthSeri(String hAuthSeri) {
		HAuthSeri = hAuthSeri;
	}
	public String getHHostSeq() {
		return HHostSeq;
	}
	public void setHHostSeq(String hHostSeq) {
		HHostSeq = hHostSeq;
	}
	public String getHRefDt() {
		return HRefDt;
	}
	public void setHRefDt(String hRefDt) {
		HRefDt = hRefDt;
	}
	public String getHSvcVer() {
		return HSvcVer;
	}
	public void setHSvcVer(String hSvcVer) {
		HSvcVer = hSvcVer;
	}
	public String getHReserveMsg() {
		return HReserveMsg;
	}
	public void setHReserveMsg(String hReserveMsg) {
		HReserveMsg = hReserveMsg;
	}
	
}
