package com.bos.crtPro;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.engine.ListenerManager;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.primeton.mgrcore.F11231;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052ResBody;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
import com.primeton.mgrcore.S0030101000XD05ServiceStub;
import com.primeton.mgrcore.S0030101000XD05ServiceStub.FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231;
import com.primeton.mgrcore.client.DateTools;

public class QueryAccountInfo {
	private static String getUrl(){
		ListenerManager.defaultConfigurationContext = null;
		String module = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_IP;
		String port = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_PORT;
		//String wservice = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_SERVICE;
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		//String zservice = ConfigurationUtil.getUserConfigSingleValue(module, group, wservice);
		String url = "http://" + zip + ":" + zport;
		return url;
	}
	
	public static OXD052_AccInfoQryRes executeXD05(OXD051_AccInfoQryReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030101000XD05";
		String url = getUrl() + zservice;
		S0030101000XD05ServiceStub.FMT_SOAP_UTF8_RequestHeader XD05ReqHeader = new S0030101000XD05ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030101000XD05ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD05ReqTranHeader = new S0030101000XD05ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030101000XD05ServiceStub.FMT_CRMS_SVR_S0030101000XD05_IN XD05ReqBody = new S0030101000XD05ServiceStub.FMT_CRMS_SVR_S0030101000XD05_IN();
		
		XD05ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD05ReqHeader.setReqSysCode("01601");
		XD05ReqHeader.setReqSecCode("");
		XD05ReqHeader.setTxType("RQ");
		XD05ReqHeader.setTxMode("0");
		XD05ReqHeader.setTxCode("S0030101000XD05");
		XD05ReqHeader.setReqDate(DateTools.getTime8());
		XD05ReqHeader.setReqTime(DateTools.getTime20());
		XD05ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD05ReqHeader.setChanlNo("48");
		XD05ReqHeader.setBrch(requestBody.getOrgNum());
		XD05ReqHeader.setTermNo("");
		XD05ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD05ReqHeader.setSendFileName("");
		XD05ReqHeader.setBeginRec("");
		XD05ReqHeader.setMaxRec(null);
		XD05ReqHeader.setFileHMac("");
		XD05ReqHeader.setHMac("");
	
		XD05ReqTranHeader.setHPinSeed("null");
		XD05ReqTranHeader.setHOriChnl("601");
		XD05ReqTranHeader.setHSecFlag("0");
		XD05ReqTranHeader.setHPwdFlag("0");
		XD05ReqTranHeader.setHCombFlag("0");
		XD05ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD05ReqTranHeader.setHSecInfoVerNo("null");
		XD05ReqTranHeader.setHSysChnl("48");
		XD05ReqTranHeader.setHLegaObj("9999");
		XD05ReqTranHeader.setHMsgRefNo("");
		XD05ReqTranHeader.setHTermNo("");
		XD05ReqTranHeader.setHCityCd("");
		XD05ReqTranHeader.setHBrchNo(requestBody.getOrgNum());
		XD05ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD05ReqTranHeader.setHTxnCd("XD05");
		XD05ReqTranHeader.setHTxnMod("");
		XD05ReqTranHeader.setHReserveLen("");
		XD05ReqTranHeader.setHSenderSvcCd("");
		XD05ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD05ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD05ReqTranHeader.setHAuthUserID("");
		XD05ReqTranHeader.setHAuthVerfInfo("");
		XD05ReqTranHeader.setHAuthFlag("");
		XD05ReqTranHeader.setHRefSeq("");
		XD05ReqTranHeader.setHAuthSeri("");
		XD05ReqTranHeader.setHHostSeq("");
		XD05ReqTranHeader.setHRefDt("");
		XD05ReqTranHeader.setHSvcVer("");
		XD05ReqTranHeader.setHreserveMsg("");
		XD05ReqTranHeader.setHintOrigMark(null);

		XD05ReqBody.setQryType(requestBody.getQryType());
		XD05ReqBody.setCustAcctNo(requestBody.getCustAcctNo());
		XD05ReqBody.setSubAcctSeri(requestBody.getSubAcctSeri());
		XD05ReqBody.setGroupAcctSeri(requestBody.getGroupAcctSeri());
		XD05ReqBody.setCurrCode(requestBody.getCurrCode());
		XD05ReqBody.setCashFlag(requestBody.getCashFlag());
		XD05ReqBody.setQryPwd(requestBody.getQryPwd());
		
		S0030101000XD05ServiceStub.S0030101000XD05Response XD05Response = new S0030101000XD05ServiceStub.S0030101000XD05Response();
		OXD052_AccInfoQryRes OXD052Res = new OXD052_AccInfoQryRes();
		OXD052ResBody OXD052ResBody = new OXD052ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030101000XD05ServiceStub stub = new S0030101000XD05ServiceStub(url);
			S0030101000XD05ServiceStub.S0030101000XD05 service = new S0030101000XD05ServiceStub.S0030101000XD05();
			service.setRequestHeader(XD05ReqHeader);
			service.setReqTranHeader(XD05ReqTranHeader);
			service.setRequestBody(XD05ReqBody);
			XD05Response = stub.S0030101000XD05(service);
			GitUtil.getNumTimes(requestBody.getOrgNum());
			if (XD05Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD05】账户信息查询接口成功，ESB流水号： " + XD05ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD05】账户信息查询接口成功，核心前台流水号： " + XD05ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD05Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD05Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD05Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD05Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD05Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD05Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD05Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD05Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD05Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD05Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD05Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD05Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD05Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD05Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD05Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD05Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD05Response.getResponseHeader().getHMac());

				resTranHeader.setHSecFlag(XD05Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD05Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD05Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD05Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD05Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD05Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD05Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD05Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD05Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD05Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD05Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD05Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD05Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD05Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD05Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD05Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD05Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD05Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD05Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD05Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD05Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD05Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD05Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD05Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD05Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD05Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD05Response.getResTranHeader().getHWarnMsg());
				
				OXD052ResBody.setCustNo(XD05Response.getResponseBody().getCustNo());
				OXD052ResBody.setCustAcctNo(XD05Response.getResponseBody().getCustAcctNo());
				OXD052ResBody.setCustName(XD05Response.getResponseBody().getCustName());
				OXD052ResBody.setSysAcct(XD05Response.getResponseBody().getSysAcct());
				OXD052ResBody.setBrcnName(XD05Response.getResponseBody().getBrcnName());
				OXD052ResBody.setBalanceBrchName(XD05Response.getResponseBody().getBalanceBrchName());
				OXD052ResBody.setSubAcctSeri(XD05Response.getResponseBody().getSubAcctSeri());
				OXD052ResBody.setContractNo(XD05Response.getResponseBody().getContractNo());
				if("01".equals(XD05Response.getResponseBody().getCurrCode())){//人民币
					OXD052ResBody.setCurrCode("CNY");
				}else if("12".equals(XD05Response.getResponseBody().getCurrCode())){//英镑
					OXD052ResBody.setCurrCode("GBP");
				}else if("13".equals(XD05Response.getResponseBody().getCurrCode())){//港币
					OXD052ResBody.setCurrCode("HKD");
				}else if("14".equals(XD05Response.getResponseBody().getCurrCode())){//美元
					OXD052ResBody.setCurrCode("USD");
				}else if("15".equals(XD05Response.getResponseBody().getCurrCode())){//瑞士法郎
					OXD052ResBody.setCurrCode("CHF");
				}else if("27".equals(XD05Response.getResponseBody().getCurrCode())){//日元
					OXD052ResBody.setCurrCode("JPY");
				}else if("28".equals(XD05Response.getResponseBody().getCurrCode())){//加拿大元
					OXD052ResBody.setCurrCode("CAD");
				}else if("29".equals(XD05Response.getResponseBody().getCurrCode())){//澳洲元
					OXD052ResBody.setCurrCode("AUD");
				}else if("32".equals(XD05Response.getResponseBody().getCurrCode())){//新加坡元
					OXD052ResBody.setCurrCode("SGD");
				}else if("38".equals(XD05Response.getResponseBody().getCurrCode())){//欧元
					OXD052ResBody.setCurrCode("EUR");
				}else if("81".equals(XD05Response.getResponseBody().getCurrCode())){//澳门元
					OXD052ResBody.setCurrCode("MOP");
				}
				OXD052ResBody.setCashFlag(XD05Response.getResponseBody().getCashFlag());
				OXD052ResBody.setPrdNo(XD05Response.getResponseBody().getPrdNo());
				OXD052ResBody.setReletype(XD05Response.getResponseBody().getReletype());
				OXD052ResBody.setAcctType(XD05Response.getResponseBody().getAcctType());
				OXD052ResBody.setCateAtrb(XD05Response.getResponseBody().getCateAtrb());
				OXD052ResBody.setVchBatNo(XD05Response.getResponseBody().getVchBatNo());
				OXD052ResBody.setVchKind(XD05Response.getResponseBody().getVchKind());
				OXD052ResBody.setVchSerialNo(XD05Response.getResponseBody().getVchSerialNo());
				OXD052ResBody.setPayCondiditon(XD05Response.getResponseBody().getPayCondiditon());
				OXD052ResBody.setIsAllowExChg(XD05Response.getResponseBody().getIsAllowExChg());
				OXD052ResBody.setUswFlag(XD05Response.getResponseBody().getUswFlag());
				OXD052ResBody.setUswAre(XD05Response.getResponseBody().getUswAre());
				OXD052ResBody.setCashExchgFlag(XD05Response.getResponseBody().getCashExchgFlag());
				OXD052ResBody.setTransExchFlag(XD05Response.getResponseBody().getTransExchFlag());
				OXD052ResBody.setOpenAcctDate(XD05Response.getResponseBody().getOpenAcctDate());
				OXD052ResBody.setCanAccDate(XD05Response.getResponseBody().getCanAccDate());
				OXD052ResBody.setAcctStat(XD05Response.getResponseBody().getAcctStat());
				OXD052ResBody.setIntrRat(XD05Response.getResponseBody().getIntrRat());
				OXD052ResBody.setRangeValue(XD05Response.getResponseBody().getRangeValue());
				OXD052ResBody.setRateFloatRatio(XD05Response.getResponseBody().getRateFloatRatio());
				OXD052ResBody.setAccrrestAmt(XD05Response.getResponseBody().getAccrrestAmt());
				OXD052ResBody.setAvailableAmt(XD05Response.getResponseBody().getAvailableAmt());
				OXD052ResBody.setPendSettlementAmt(XD05Response.getResponseBody().getPendSettlementAmt());
				OXD052ResBody.setAcctPendSettlemenAmt(XD05Response.getResponseBody().getAcctPendSettlemenAmt());
				OXD052ResBody.setLatlyBusiDate(XD05Response.getResponseBody().getLatlyBusiDate());
				OXD052ResBody.setLostFlag(XD05Response.getResponseBody().getLostFlag());
				OXD052ResBody.setSingleVchStat(XD05Response.getResponseBody().getSingleVchStat());
				OXD052ResBody.setYnFlag(XD05Response.getResponseBody().getYNFrozen());
				OXD052ResBody.setFrzAmt(XD05Response.getResponseBody().getFrzAmt());
				OXD052ResBody.setCtrlFlg(XD05Response.getResponseBody().getCtrlFlg());
				OXD052ResBody.setCtrlAmt(XD05Response.getResponseBody().getCtrlAmt());
				OXD052ResBody.setInterestTotAmt(XD05Response.getResponseBody().getInterestTotAmt());
				OXD052ResBody.setLstCul(XD05Response.getResponseBody().getLstCul());
				OXD052ResBody.setThisYearTotCul(XD05Response.getResponseBody().getThisYearTotCul());
				OXD052ResBody.setUnsignRate(XD05Response.getResponseBody().getUnsignRate());
				OXD052ResBody.setLsttotCul(XD05Response.getResponseBody().getLsttotCul());
				OXD052ResBody.setLatlyChgRateDate(XD05Response.getResponseBody().getLatlyChgRateDate());
				OXD052ResBody.setLowNum(XD05Response.getResponseBody().getLowNum());
				OXD052ResBody.setDepositTerm(XD05Response.getResponseBody().getDepositTerm());
				OXD052ResBody.setTrsferInmode(XD05Response.getResponseBody().getTrsferInmode());
				OXD052ResBody.setTransferTime(XD05Response.getResponseBody().getTransferTime());
				OXD052ResBody.setReseOnAmt(XD05Response.getResponseBody().getReseOnAmt());
				OXD052ResBody.setInCustAcct(XD05Response.getResponseBody().getInCustAcct());
				OXD052ResBody.setPaymentInterval(XD05Response.getResponseBody().getPaymentInterval());
				OXD052ResBody.setOutAcctPro(XD05Response.getResponseBody().getOutAcctPro());
				OXD052ResBody.setCredLimt(XD05Response.getResponseBody().getCredLimt());
				OXD052ResBody.setChappenFlag(XD05Response.getResponseBody().getCHappenFlag());
				OXD052ResBody.setCreditRelationFlag(XD05Response.getResponseBody().getCreditRelationFlag());
				OXD052ResBody.setCrAcct(XD05Response.getResponseBody().getCrAcct());
				OXD052ResBody.setCanBuyNoteNum(XD05Response.getResponseBody().getCanBuyNoteNum());
				OXD052ResBody.setAllowNouseRate(XD05Response.getResponseBody().getAllowNouseRate());
				OXD052ResBody.setIstogetherFlag(XD05Response.getResponseBody().getIstogetherFlag());
				OXD052ResBody.setBackupAmt(XD05Response.getResponseBody().getBackupAmt());
				OXD052ResBody.setWorkTime(XD05Response.getResponseBody().getWorkTime());
				OXD052ResBody.setYnFlag(XD05Response.getResponseBody().getYNFlag());
				OXD052ResBody.setAddr(XD05Response.getResponseBody().getAddr());
				OXD052ResBody.setShopContactPhone(XD05Response.getResponseBody().getShopContactPhone());
				OXD052ResBody.setAcctOpenSignNo(XD05Response.getResponseBody().getAcctOpenSignNo());
				OXD052ResBody.setBusinessWorkDate(XD05Response.getResponseBody().getBusinessWorkDate());
				OXD052ResBody.setChkProjectNo(XD05Response.getResponseBody().getChkProjectNo());
				OXD052ResBody.setAcct(XD05Response.getResponseBody().getAcct());
				OXD052ResBody.setCustNo(XD05Response.getResponseBody().getCustNo());
				OXD052ResBody.setDepositType(XD05Response.getResponseBody().getDepositType());
				OXD052ResBody.setAcctStat1(XD05Response.getResponseBody().getAcctStat1());
				OXD052ResBody.setYnRecon(XD05Response.getResponseBody().getYNRecon());
				OXD052ResBody.setBalanceRange(XD05Response.getResponseBody().getBalanceRange());
				OXD052ResBody.setLatlyBalanceDate(XD05Response.getResponseBody().getLatlyBalanceDate());
				OXD052ResBody.setAcctChkFlag(XD05Response.getResponseBody().getAcctChkFlag());
				OXD052ResBody.setLatlyAcctChkDate(XD05Response.getResponseBody().getLatlyAcctChkDate());
				OXD052ResBody.setExChkFlg(XD05Response.getResponseBody().getExChkFlg());
				OXD052ResBody.setRestandTotSyn(XD05Response.getResponseBody().getRestandTotSyn());
				OXD052ResBody.setGroupProductNo(XD05Response.getResponseBody().getGroupProductNo());
				OXD052ResBody.setGroupAcctNo(XD05Response.getResponseBody().getGroupAcctNo());
				OXD052ResBody.setGetCashFlag(XD05Response.getResponseBody().getGetCashFlag());
				OXD052ResBody.setMaturityWay(XD05Response.getResponseBody().getMaturityWay());
				OXD052ResBody.setNormalAcctiscostFlag(XD05Response.getResponseBody().getNormalAcctiscostFlag());
				OXD052ResBody.setUnmoveAcctIsCostFlag(XD05Response.getResponseBody().getUnmoveAcctIsCostFlag());
				OXD052ResBody.setIsValid(XD05Response.getResponseBody().getIsValid());
				OXD052ResBody.setDateOfValue(XD05Response.getResponseBody().getDateOfValue());
				OXD052ResBody.setMatuDat(XD05Response.getResponseBody().getMatuDat());
				OXD052ResBody.setAmt(XD05Response.getResponseBody().getAmt());
				OXD052ResBody.setFreezeBal(XD05Response.getResponseBody().getFreezeBal());
				OXD052ResBody.setRecNum(XD05Response.getResponseBody().getRecNum());
				FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231[] f11231SubArray;
				if (null == XD05Response.getResponseBody().getRecNum() || (XD05Response.getResponseBody().getRecNum()).intValue() == 0) {
					f11231SubArray = new FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231[1];
					System.out.println("调用【S0030101000XD05】响应中[F11231]循环记录数为0");
				}else {
					f11231SubArray = new FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231[(XD05Response.getResponseBody().getRecNum()).intValue()];
				}
				
				if(null == XD05Response.getResponseBody().getRecMsg()){
					FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231 f11231 = new FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231();
					f11231SubArray[0] = f11231;
				}else{
					f11231SubArray = XD05Response.getResponseBody().getRecMsg();
				}
				List<F11231> f11231List = new ArrayList<F11231>();
				for (int i = 0; i < f11231SubArray.length; i++) {
					FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231 f11231Sub = new FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231();
					f11231Sub = f11231SubArray[i];
					F11231 f11231 = new F11231();
					f11231.setRataKind(f11231Sub.getRataKind());
					f11231.setRataNme(f11231Sub.getRataNme());
					f11231.setYnFlag1(f11231Sub.getYNFlag1());
					f11231List.add(f11231);
				}
				OXD052ResBody.setF11231(f11231List);
				
				OXD052ResBody.setBelongObject(XD05Response.getResponseBody().getBelongObject());
				OXD052ResBody.setLibProType(XD05Response.getResponseBody().getLibProType());
				OXD052ResBody.setOpenBrch(XD05Response.getResponseBody().getOpenBrch());
				OXD052ResBody.setTransOpponentAcct(XD05Response.getResponseBody().getTransOpponentAcct());
				OXD052ResBody.setRecvAcctName(XD05Response.getResponseBody().getRecvAcctName());
				OXD052ResBody.setBrchNo(XD05Response.getResponseBody().getBrchNo());
				OXD052ResBody.setOtherBankName(XD05Response.getResponseBody().getOtherBankName());
				OXD052ResBody.setPeopleBankNo(XD05Response.getResponseBody().getPeopleBankNo());
				OXD052ResBody.setAcctNoCode(XD05Response.getResponseBody().getAcctNoCode());
				OXD052ResBody.setBackup2(XD05Response.getResponseBody().getBackup2());
				OXD052ResBody.setBackup1(XD05Response.getResponseBody().getBackup1());
				OXD052ResBody.setFixedPayAmt(XD05Response.getResponseBody().getFixedPayAmt());
				OXD052ResBody.setOpenAcctName(XD05Response.getResponseBody().getOpenAcctName());
				OXD052ResBody.setAcctShortCode3(XD05Response.getResponseBody().getAcctShortCode3());
				OXD052ResBody.setPrdDesc(XD05Response.getResponseBody().getPrdDesc());
				OXD052ResBody.setEveryTimeGetAmt(XD05Response.getResponseBody().getEveryTimeGetAmt());
				OXD052ResBody.setIsLinkCycleLoan(XD05Response.getResponseBody().getIsLinkCycleLoan());
				OXD052ResBody.setLoanNoteNo(XD05Response.getResponseBody().getLoanNoteNo());
				OXD052ResBody.setGrantedAmt(XD05Response.getResponseBody().getGrantedAmt());
				OXD052ResBody.setFrzGrantableAmt(XD05Response.getResponseBody().getFrzGrantableAmt());
				OXD052ResBody.setGrantableAmt(XD05Response.getResponseBody().getGrantableAmt());
				OXD052ResBody.setConnectCostBatFlag(XD05Response.getResponseBody().getConnectCostBatFlag());
				OXD052ResBody.setBatLatterRate(XD05Response.getResponseBody().getBatLatterRate());
				OXD052ResBody.setYnExchSup(XD05Response.getResponseBody().getYNExchSup());
				OXD052ResBody.setFixedDepositAmt(XD05Response.getResponseBody().getFixedDepositAmt());
				OXD052ResBody.setFlgDefault(XD05Response.getResponseBody().getFlgDefault());
				OXD052ResBody.setAgnt(XD05Response.getResponseBody().getAgnt());
				OXD052ResBody.setCertKind(XD05Response.getResponseBody().getCertKind());
				OXD052ResBody.setAgntCertNum(XD05Response.getResponseBody().getAgntCertNum());
				OXD052ResBody.setAgentNation(XD05Response.getResponseBody().getAgentNation());
				OXD052ResBody.setContactPhone(XD05Response.getResponseBody().getContactPhone());

				OXD052Res.setResHeader(responseHeader);
				OXD052Res.setResTranHeader(resTranHeader);
				OXD052Res.setOxd052ResBody(OXD052ResBody);
			} else {
				resTranHeader.setHRetCode(XD05Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD05Response.getResTranHeader().getHRetMsg());
				OXD052Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD05】账户信息查询接口处理出错，处理结果： " + XD05Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD05】账户信息查询接口处理出错，ESB响应码： " + XD05Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD05】账户信息查询接口处理出错，ESB流水号： " + XD05ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD05】账户信息查询接口处理出错，核心前台流水号： " + XD05ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030503000XD05】账户信息查询口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD05ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD05ReqTranHeader.getHSenderSeq());
			}else {
				throw new Exception("调用【S0030503000XD05】账户信息查询口失败：" + e.getMessage() + "ESB流水号: " + XD05ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD05ReqTranHeader.getHSenderSeq());
			}		
		}
		return OXD052Res;
	}
}
