package com.primeton.crmsgj.client;

import org.apache.axis2.engine.ListenerManager;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.crmsgj.GJS01501010000010Req;
import com.primeton.crmsgj.GJS01501010000010Res;
import com.primeton.crmsgj.GJS01501010000011Req;
import com.primeton.crmsgj.GJS01501010000011Res;
import com.primeton.crmsgj.GJS01501030000001Req;
import com.primeton.crmsgj.GJS01501030000001Res;
import com.primeton.crmsgj.GJS01501030000001ResBody;
import com.primeton.crmsgj.GJS01501030000003Req;
import com.primeton.crmsgj.GJS01501030000003Res;
import com.primeton.crmsgj.GJS01501030000003ResBody;
import com.primeton.crmsgj.GJS01501030000005Req;
import com.primeton.crmsgj.GJS01501030000005Res;
import com.primeton.crmsgj.GJS01501030000005ResBody;
import com.primeton.crmsgj.GJS01501030000006Req;
import com.primeton.crmsgj.GJS01501030000006Res;
import com.primeton.crmsgj.GJS01501030000006ResBody;
import com.primeton.crmsgj.GJS01501030000008Req;
import com.primeton.crmsgj.GJS01501030000008Res;
import com.primeton.crmsgj.GJS01501030000008ResBody;
import com.primeton.crmsgj.GJS01501030000009Req;
import com.primeton.crmsgj.GJS01501030000009Res;
import com.primeton.crmsgj.GJS01501030000009ResBody;
import com.primeton.crmsgj.GJS01501070000007Req;
import com.primeton.crmsgj.GJS01501070000007Res;
import com.primeton.crmsgj.GJS01501070000007ResBody;
import com.primeton.crmsgj.GJS01501110000002Req;
import com.primeton.crmsgj.GJS01501110000002Res;
import com.primeton.crmsgj.GJS01501110000002ResBody;
import com.primeton.crmsgj.GJS01501110000004Req;
import com.primeton.crmsgj.GJS01501110000004Res;
import com.primeton.crmsgj.GJS01501110000004ResBody;
import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
import com.primeton.mgrcore.client.DateTools;

@Bizlet("CrmsMgrCallGjImpl")
public class CrmsMgrCallGjImpl implements CrmsMgrCallGjProxy {
	private String getUrl() {
		ListenerManager.defaultConfigurationContext = null;
		String module = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_IP;
		String port = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_PORT;
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		String url = "http://" + zip + ":" + zport;
		return url;
	}

	// 表内融资业务放款交易接口
	@Bizlet("")
	public GJS01501030000001Res executeS01501030000001(GJS01501030000001Req gjS01501030000001Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000001";
		String url = getUrl() + zservice;

		S01501030000001ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000001ReqHeader = new S01501030000001ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000001ReqTranHeader = new S01501030000001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000001ServiceStub.FMT_CRMS_SVR_S01501030000001_IN s01501030000001ReqBody = new S01501030000001ServiceStub.FMT_CRMS_SVR_S01501030000001_IN();

		s01501030000001ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000001ReqHeader.setReqSysCode("01601");
		s01501030000001ReqHeader.setReqSecCode("");
		s01501030000001ReqHeader.setTxType("RQ");
		s01501030000001ReqHeader.setTxMode("0");
		s01501030000001ReqHeader.setTxCode("S01501030000001");
		s01501030000001ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000001ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000001ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000001ReqHeader.setChanlNo("48");
		s01501030000001ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000001ReqHeader.setTermNo("");
		s01501030000001ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000001ReqHeader.setSendFileName("");
		s01501030000001ReqHeader.setBeginRec("");
		s01501030000001ReqHeader.setMaxRec(null);
		s01501030000001ReqHeader.setFileHMac("");
		s01501030000001ReqHeader.setHMac("");

		// s01501030000001ReqTranHeader.setHMsgMac("");
		// s01501030000001ReqTranHeader.setHMacBrch("");
		s01501030000001ReqTranHeader.setHPinSeed("");
		s01501030000001ReqTranHeader.setHOriChnl("");
		// s01501030000001ReqTranHeader.setHAimCd("301");
		s01501030000001ReqTranHeader.setHSecFlag("0");
		s01501030000001ReqTranHeader.setHPwdFlag("0");
		s01501030000001ReqTranHeader.setHCombFlag("0");
		s01501030000001ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000001ReqTranHeader.setHEndFlag("");
		// s01501030000001ReqTranHeader.setHMsgNo("");
		// s01501030000001ReqTranHeader.setHVerfFlag("");
		s01501030000001ReqTranHeader.setHSecInfoVerNo("");
		s01501030000001ReqTranHeader.setHSysChnl("48");
		// s01501030000001ReqTranHeader.setHoffsetInfo("");
		s01501030000001ReqTranHeader.setHLegaObj("9999");
		s01501030000001ReqTranHeader.setHMsgRefNo("");
		// s01501030000001ReqTranHeader.setHCommType("");
		// s01501030000001ReqTranHeader.setHDeviceNo("");
		// s01501030000001ReqTranHeader.setHSuperFlag("");
		// s01501030000001ReqTranHeader.setHChkFlag("");
		// s01501030000001ReqTranHeader.setHChkTxnCd("");
		// s01501030000001ReqTranHeader.setHVerfCd("");
		// s01501030000001ReqTranHeader.setHCommitFlag("");
		// s01501030000001ReqTranHeader.setHTranRes("");
		s01501030000001ReqTranHeader.setHTermNo("");
		s01501030000001ReqTranHeader.setHCityCd("");
		s01501030000001ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000001ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000001ReqTranHeader.setHTxnCd("000001");
		// s01501030000001ReqTranHeader.setHSubTxnCd("");
		s01501030000001ReqTranHeader.setHTxnMod("");
		// s01501030000001ReqTranHeader.setHTxnSeq("");
		s01501030000001ReqTranHeader.setHReserveLen("");
		s01501030000001ReqTranHeader.setHSenderSvcCd("");
		s01501030000001ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000001ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000001ReqTranHeader.setHAuthUserID("");
		s01501030000001ReqTranHeader.setHAuthVerfInfo("");
		s01501030000001ReqTranHeader.setHAuthFlag("");
		s01501030000001ReqTranHeader.setHRefSeq("");
		s01501030000001ReqTranHeader.setHAuthSeri("");
		s01501030000001ReqTranHeader.setHHostSeq("");
		s01501030000001ReqTranHeader.setHRefDt("");
		s01501030000001ReqTranHeader.setHSvcVer("");
		s01501030000001ReqTranHeader.setHreserveMsg("");
		// s01501030000001ReqTranHeader.setDataLength(null);
		// s01501030000001ReqTranHeader.setHintVerNo(null);
		s01501030000001ReqTranHeader.setHintOrigMark(null);
		// s01501030000001ReqTranHeader.setHintDestMark(null);
		// s01501030000001ReqTranHeader.setHTranVer("");
		// s01501030000001ReqTranHeader.setHOrigMark("");
		// s01501030000001ReqTranHeader.setHDestMark("");
		// s01501030000001ReqTranHeader.setHIdentFlag("");

		s01501030000001ReqBody.setAcctBrch(gjS01501030000001Req.getAcctBrch());
		//s01501030000001ReqBody.setAcctBrch("0200");//86测试用
		s01501030000001ReqBody.setAgreeAmt(gjS01501030000001Req.getAgreeAmt()); 
		s01501030000001ReqBody.setAgreeSeqNo(gjS01501030000001Req.getAgreeSeqNo());
		s01501030000001ReqBody.setBusiCode(gjS01501030000001Req.getBusiCode());
		s01501030000001ReqBody.setContractNo(gjS01501030000001Req.getContractNo());
		s01501030000001ReqBody.setCurrency(gjS01501030000001Req.getCurrency());
		s01501030000001ReqBody.setCustNo(gjS01501030000001Req.getCustNo());
		s01501030000001ReqBody.setDateOfValue(gjS01501030000001Req.getDateOfValue());
		s01501030000001ReqBody.setDealRate(gjS01501030000001Req.getDealRate());
		s01501030000001ReqBody.setDebAcct(gjS01501030000001Req.getDebAcct());
		s01501030000001ReqBody.setDebAmt(gjS01501030000001Req.getDebAmt());
		s01501030000001ReqBody.setDebitNo(gjS01501030000001Req.getDebitNo());
		s01501030000001ReqBody.setMatuDat(gjS01501030000001Req.getMatuDat());
		s01501030000001ReqBody.setOverRate(gjS01501030000001Req.getOverRate());
		s01501030000001ReqBody.setPaySeqnNo(gjS01501030000001Req.getPaySeqnNo());
		s01501030000001ReqBody.setPrdCode(gjS01501030000001Req.getPrdCode());
		s01501030000001ReqBody.setProSubTp(gjS01501030000001Req.getProSubTp());

		S01501030000001ServiceStub.S01501030000001Response s01501030000001Response = new S01501030000001ServiceStub.S01501030000001Response();
		GJS01501030000001Res s01501030000001Res = new GJS01501030000001Res();
		GJS01501030000001ResBody s01501030000001ResBody = new GJS01501030000001ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000001ServiceStub stub = new S01501030000001ServiceStub(url);
			S01501030000001ServiceStub.S01501030000001 service = new S01501030000001ServiceStub.S01501030000001();
			service.setRequestHeader(s01501030000001ReqHeader);
			service.setReqTranHeader(s01501030000001ReqTranHeader);
			service.setRequestBody(s01501030000001ReqBody);
			s01501030000001Response = stub.S01501030000001(service);
			
			responseHeader.setVersionNo(s01501030000001Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000001Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000001Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000001Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000001Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000001Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000001Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000001Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000001Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000001Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000001Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000001Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000001Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000001Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000001Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000001Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000001Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000001Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000001Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000001Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000001Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000001Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000001Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000001Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000001Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000001Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000001Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000001Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000001Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000001Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000001Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000001Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000001Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000001Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000001Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000001Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000001Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000001Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000001Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000001Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000001Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000001Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000001Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000001Response.getResTranHeader().getHWarnMsg());

			s01501030000001ResBody.setDebitNo(s01501030000001Response.getResponseBody().getDebitNo());
			s01501030000001ResBody.setErrMsg(s01501030000001Response.getResponseBody().getErrMsg());
			s01501030000001ResBody.setKnotSeqNo(s01501030000001Response.getResponseBody().getKnotSeqNo());
			s01501030000001ResBody.setTransStat(s01501030000001Response.getResponseBody().getTransStat());

			s01501030000001Res.setResHeader(responseHeader);
			s01501030000001Res.setResTranHeader(resTranHeader);
			s01501030000001Res.setGjS01501030000001ResBody(s01501030000001ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000001Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000001】表内融资业务放款交易接口成功，流水号为: " + s01501030000001ReqHeader.getReqSeqNo());

			} else {// 失败
				
				System.out.println("调用【S01501030000001】表内融资业务放款交易接口失败，流水号为: " + s01501030000001ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000001Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000001】表内融资业务放款交易接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000001ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000001】表内融资业务放款交易接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000001ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000001Res;
	}

	// 进口信用证开证接口
	@Bizlet("")
	public GJS01501110000002Res executeS01501110000002(GJS01501110000002Req gjS01501110000002Req) {
		String zservice = "/WebService/CRMS_SVR/S01501110000002";
		String url = getUrl() + zservice;
		S01501110000002ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501110000002ReqHeader = new S01501110000002ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501110000002ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501110000002ReqTranHeader = new S01501110000002ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501110000002ServiceStub.FMT_CRMS_SVR_S01501110000002_IN s01501110000002ReqBody = new S01501110000002ServiceStub.FMT_CRMS_SVR_S01501110000002_IN();

		s01501110000002ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501110000002ReqHeader.setReqSysCode("01601");
		s01501110000002ReqHeader.setReqSecCode("");
		s01501110000002ReqHeader.setTxType("RQ");
		s01501110000002ReqHeader.setTxMode("0");
		s01501110000002ReqHeader.setTxCode("S01501110000002");
		s01501110000002ReqHeader.setReqDate(DateTools.getTime8());
		s01501110000002ReqHeader.setReqTime(DateTools.getTime20());
		s01501110000002ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501110000002ReqHeader.setChanlNo("48");
		s01501110000002ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501110000002ReqHeader.setTermNo("");
		s01501110000002ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501110000002ReqHeader.setSendFileName("");
		s01501110000002ReqHeader.setBeginRec("");
		s01501110000002ReqHeader.setMaxRec(null);
		s01501110000002ReqHeader.setFileHMac("");
		s01501110000002ReqHeader.setHMac("");

		// s01501110000002ReqTranHeader.setHMsgMac("");
		// s01501110000002ReqTranHeader.setHMacBrch("");
		s01501110000002ReqTranHeader.setHPinSeed("");
		s01501110000002ReqTranHeader.setHOriChnl("");
		// s01501110000002ReqTranHeader.setHAimCd("301");
		s01501110000002ReqTranHeader.setHSecFlag("0");
		s01501110000002ReqTranHeader.setHPwdFlag("0");
		s01501110000002ReqTranHeader.setHCombFlag("0");
		s01501110000002ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501110000002ReqTranHeader.setHEndFlag("");
		// s01501110000002ReqTranHeader.setHMsgNo("");
		// s01501110000002ReqTranHeader.setHVerfFlag("");
		s01501110000002ReqTranHeader.setHSecInfoVerNo("");
		s01501110000002ReqTranHeader.setHSysChnl("48");
		// s01501110000002ReqTranHeader.setHoffsetInfo("");
		s01501110000002ReqTranHeader.setHLegaObj("9999");
		s01501110000002ReqTranHeader.setHMsgRefNo("");
		// s01501110000002ReqTranHeader.setHCommType("");
		// s01501110000002ReqTranHeader.setHDeviceNo("");
		// s01501110000002ReqTranHeader.setHSuperFlag("");
		// s01501110000002ReqTranHeader.setHChkFlag("");
		// s01501110000002ReqTranHeader.setHChkTxnCd("");
		// s01501110000002ReqTranHeader.setHVerfCd("");
		// s01501110000002ReqTranHeader.setHCommitFlag("");
		// s01501110000002ReqTranHeader.setHTranRes("");
		s01501110000002ReqTranHeader.setHTermNo("");
		s01501110000002ReqTranHeader.setHCityCd("");
		s01501110000002ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501110000002ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501110000002ReqTranHeader.setHTxnCd("000002");
		// s01501110000002ReqTranHeader.setHSubTxnCd("");
		s01501110000002ReqTranHeader.setHTxnMod("");
		// s01501110000002ReqTranHeader.setHTxnSeq("");
		s01501110000002ReqTranHeader.setHReserveLen("");
		s01501110000002ReqTranHeader.setHSenderSvcCd("");
		s01501110000002ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501110000002ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501110000002ReqTranHeader.setHAuthUserID("");
		s01501110000002ReqTranHeader.setHAuthVerfInfo("");
		s01501110000002ReqTranHeader.setHAuthFlag("");
		s01501110000002ReqTranHeader.setHRefSeq("");
		s01501110000002ReqTranHeader.setHAuthSeri("");
		s01501110000002ReqTranHeader.setHHostSeq("");
		s01501110000002ReqTranHeader.setHRefDt("");
		s01501110000002ReqTranHeader.setHSvcVer("");
		s01501110000002ReqTranHeader.setHreserveMsg("");
		// s01501110000002ReqTranHeader.setDataLength(null);
		// s01501110000002ReqTranHeader.setHintVerNo(null);
		s01501110000002ReqTranHeader.setHintOrigMark(null);
		// s01501110000002ReqTranHeader.setHintDestMark(null);
		// s01501110000002ReqTranHeader.setHTranVer("");
		// s01501110000002ReqTranHeader.setHOrigMark("");
		// s01501110000002ReqTranHeader.setHDestMark("");
		// s01501110000002ReqTranHeader.setHIdentFlag("");

		s01501110000002ReqBody.setBondAcct(gjS01501110000002Req.getBondAcct());
		s01501110000002ReqBody.setBondAmt(gjS01501110000002Req.getBondAmt());
		s01501110000002ReqBody.setBondCurr(gjS01501110000002Req.getBondCurr());
		s01501110000002ReqBody.setBondRate(gjS01501110000002Req.getBondRate());
		s01501110000002ReqBody.setContractNo(gjS01501110000002Req.getContractNo());
		s01501110000002ReqBody.setCurrency(gjS01501110000002Req.getCurrency());
		s01501110000002ReqBody.setDealBrch(gjS01501110000002Req.getDealBrch());
		s01501110000002ReqBody.setDebitNo(gjS01501110000002Req.getDebitNo());
		s01501110000002ReqBody.setForwDay(gjS01501110000002Req.getForwDay());
		s01501110000002ReqBody.setIoaner(gjS01501110000002Req.getIoaner());
		s01501110000002ReqBody.setIssAmt(gjS01501110000002Req.getIssAmt());
		s01501110000002ReqBody.setLetOfCreDate(gjS01501110000002Req.getLetOfCreDate());
		s01501110000002ReqBody.setMatuDat(gjS01501110000002Req.getMatuDat());
		s01501110000002ReqBody.setMatuType(gjS01501110000002Req.getMatuType());
		s01501110000002ReqBody.setShipPro(gjS01501110000002Req.getShipPro());
		s01501110000002ReqBody.setStateVout(gjS01501110000002Req.getStateVout());
		s01501110000002ReqBody.setStornPro(gjS01501110000002Req.getStornPro());
		s01501110000002ReqBody.setTradeAgreeAmt(gjS01501110000002Req.getTradeAgreeAmt());
		s01501110000002ReqBody.setTradeAgreeNo(gjS01501110000002Req.getTradeAgreeNo());

		S01501110000002ServiceStub.S01501110000002Response s01501110000002Response = new S01501110000002ServiceStub.S01501110000002Response();
		GJS01501110000002Res s01501110000002Res = new GJS01501110000002Res();
		GJS01501110000002ResBody s01501110000002ResBody = new GJS01501110000002ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501110000002ServiceStub stub = new S01501110000002ServiceStub(url);
			S01501110000002ServiceStub.S01501110000002 service = new S01501110000002ServiceStub.S01501110000002();
			service.setRequestHeader(s01501110000002ReqHeader);
			service.setReqTranHeader(s01501110000002ReqTranHeader);
			service.setRequestBody(s01501110000002ReqBody);
			s01501110000002Response = stub.S01501110000002(service);
			
			responseHeader.setVersionNo(s01501110000002Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501110000002Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501110000002Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501110000002Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501110000002Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501110000002Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501110000002Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501110000002Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501110000002Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501110000002Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501110000002Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501110000002Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501110000002Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501110000002Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501110000002Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501110000002Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501110000002Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501110000002Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501110000002Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501110000002Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501110000002Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501110000002Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501110000002Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501110000002Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501110000002Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501110000002Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501110000002Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501110000002Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501110000002Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501110000002Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501110000002Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501110000002Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501110000002Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501110000002Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501110000002Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501110000002Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501110000002Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501110000002Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501110000002Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501110000002Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501110000002Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501110000002Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501110000002Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501110000002Response.getResTranHeader().getHWarnMsg());

			s01501110000002ResBody.setDebitNo(s01501110000002Response.getResponseBody().getDebitNo());
			s01501110000002ResBody.setErrMsg(s01501110000002Response.getResponseBody().getErrMsg());
			s01501110000002ResBody.setKnotSeqNo(s01501110000002Response.getResponseBody().getKnotSeqNo());
			s01501110000002ResBody.setTransStat(s01501110000002Response.getResponseBody().getTransStat());

			s01501110000002Res.setResHeader(responseHeader);
			s01501110000002Res.setResTranHeader(resTranHeader);
			s01501110000002Res.setGjS01501110000002ResBody(s01501110000002ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501110000002Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501110000002】进口信用证开证接口成功，流水号为: " + s01501110000002ReqHeader.getReqSeqNo());

			} else {// 失败
				
				System.out.println("调用【S01501110000002】进口信用证开证接口失败，流水号为: " + s01501110000002ReqHeader.getReqSeqNo() + "失败原因:" + s01501110000002Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501110000002】进口信用证开证接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501110000002ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501110000002】进口信用证开证接口失败：" + e.getMessage() + "ESB流水号: " + s01501110000002ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501110000002Res;
	}

	// 进口信用证开证修改接口
	@Bizlet("")
	public GJS01501030000003Res executeS01501030000003(GJS01501030000003Req gjS01501030000003Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000003";
		String url = getUrl() + zservice;

		S01501030000003ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000003ReqHeader = new S01501030000003ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000003ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000003ReqTranHeader = new S01501030000003ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000003ServiceStub.FMT_CRMS_SVR_S01501030000003_IN s01501030000003ReqBody = new S01501030000003ServiceStub.FMT_CRMS_SVR_S01501030000003_IN();

		s01501030000003ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000003ReqHeader.setReqSysCode("01601");
		s01501030000003ReqHeader.setReqSecCode("");
		s01501030000003ReqHeader.setTxType("RQ");
		s01501030000003ReqHeader.setTxMode("0");
		s01501030000003ReqHeader.setTxCode("S01501030000003");
		s01501030000003ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000003ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000003ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000003ReqHeader.setChanlNo("48");
		s01501030000003ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000003ReqHeader.setTermNo("");
		s01501030000003ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000003ReqHeader.setSendFileName("");
		s01501030000003ReqHeader.setBeginRec("");
		s01501030000003ReqHeader.setMaxRec(null);
		s01501030000003ReqHeader.setFileHMac("");
		s01501030000003ReqHeader.setHMac("");

		// s01501030000003ReqTranHeader.setHMsgMac("");
		// s01501030000003ReqTranHeader.setHMacBrch("");
		s01501030000003ReqTranHeader.setHPinSeed("");
		s01501030000003ReqTranHeader.setHOriChnl("");
		// s01501030000003ReqTranHeader.setHAimCd("301");
		s01501030000003ReqTranHeader.setHSecFlag("0");
		s01501030000003ReqTranHeader.setHPwdFlag("0");
		s01501030000003ReqTranHeader.setHCombFlag("0");
		s01501030000003ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000003ReqTranHeader.setHEndFlag("");
		// s01501030000003ReqTranHeader.setHMsgNo("");
		// s01501030000003ReqTranHeader.setHVerfFlag("");
		s01501030000003ReqTranHeader.setHSecInfoVerNo("");
		s01501030000003ReqTranHeader.setHSysChnl("48");
		// s01501030000003ReqTranHeader.setHoffsetInfo("");
		s01501030000003ReqTranHeader.setHLegaObj("9999");
		s01501030000003ReqTranHeader.setHMsgRefNo("");
		// s01501030000003ReqTranHeader.setHCommType("");
		// s01501030000003ReqTranHeader.setHDeviceNo("");
		// s01501030000003ReqTranHeader.setHSuperFlag("");
		// s01501030000003ReqTranHeader.setHChkFlag("");
		// s01501030000003ReqTranHeader.setHChkTxnCd("");
		// s01501030000003ReqTranHeader.setHVerfCd("");
		// s01501030000003ReqTranHeader.setHCommitFlag("");
		// s01501030000003ReqTranHeader.setHTranRes("");
		// s01501030000003ReqTranHeader.setHTermNo("");
		s01501030000003ReqTranHeader.setHCityCd("");
		s01501030000003ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000003ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000003ReqTranHeader.setHTxnCd("000003");
		// s01501030000003ReqTranHeader.setHSubTxnCd("");
		s01501030000003ReqTranHeader.setHTxnMod("");
		// s01501030000003ReqTranHeader.setHTxnSeq("");
		s01501030000003ReqTranHeader.setHReserveLen("");
		s01501030000003ReqTranHeader.setHSenderSvcCd("");
		s01501030000003ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000003ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000003ReqTranHeader.setHAuthUserID("");
		s01501030000003ReqTranHeader.setHAuthVerfInfo("");
		s01501030000003ReqTranHeader.setHAuthFlag("");
		s01501030000003ReqTranHeader.setHRefSeq("");
		s01501030000003ReqTranHeader.setHAuthSeri("");
		s01501030000003ReqTranHeader.setHHostSeq("");
		s01501030000003ReqTranHeader.setHRefDt("");
		s01501030000003ReqTranHeader.setHSvcVer("");
		s01501030000003ReqTranHeader.setHreserveMsg("");
		// s01501030000003ReqTranHeader.setDataLength(null);
		// s01501030000003ReqTranHeader.setHintVerNo(null);
		s01501030000003ReqTranHeader.setHintOrigMark(null);
		// s01501030000003ReqTranHeader.setHintDestMark(null);
		// s01501030000003ReqTranHeader.setHTranVer("");
		// s01501030000003ReqTranHeader.setHOrigMark("");
		// s01501030000003ReqTranHeader.setHDestMark("");
		// s01501030000003ReqTranHeader.setHIdentFlag("");

		s01501030000003ReqBody.setAfModiAmt(gjS01501030000003Req.getAfModiAmt());
		s01501030000003ReqBody.setBondAcct(gjS01501030000003Req.getBondAcct());
		s01501030000003ReqBody.setBondAmt(gjS01501030000003Req.getBondAmt());
		s01501030000003ReqBody.setBondCurr(gjS01501030000003Req.getBondCurr());
		s01501030000003ReqBody.setBondRate(gjS01501030000003Req.getBondRate());
		s01501030000003ReqBody.setContractNo(gjS01501030000003Req.getContractNo());
		s01501030000003ReqBody.setCredNo(gjS01501030000003Req.getCredNo());
		s01501030000003ReqBody.setDealBrch(gjS01501030000003Req.getDealBrch());
		s01501030000003ReqBody.setDebitNo(gjS01501030000003Req.getDebitNo());
		s01501030000003ReqBody.setForwDay(gjS01501030000003Req.getForwDay());
		s01501030000003ReqBody.setIoaner(gjS01501030000003Req.getIoaner());
		s01501030000003ReqBody.setLetOfCreDate(gjS01501030000003Req.getLetOfCreDate());
		s01501030000003ReqBody.setMatuType(gjS01501030000003Req.getMatuType());
		s01501030000003ReqBody.setNewExp(gjS01501030000003Req.getNewExp());

		S01501030000003ServiceStub.S01501030000003Response s01501030000003Response = new S01501030000003ServiceStub.S01501030000003Response();
		GJS01501030000003Res s01501030000003Res = new GJS01501030000003Res();
		GJS01501030000003ResBody s01501030000003ResBody = new GJS01501030000003ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000003ServiceStub stub = new S01501030000003ServiceStub(url);
			S01501030000003ServiceStub.S01501030000003 service = new S01501030000003ServiceStub.S01501030000003();
			service.setRequestHeader(s01501030000003ReqHeader);
			service.setReqTranHeader(s01501030000003ReqTranHeader);
			service.setRequestBody(s01501030000003ReqBody);
			s01501030000003Response = stub.S01501030000003(service);
			
			responseHeader.setVersionNo(s01501030000003Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000003Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000003Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000003Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000003Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000003Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000003Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000003Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000003Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000003Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000003Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000003Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000003Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000003Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000003Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000003Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000003Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000003Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000003Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000003Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000003Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000003Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000003Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000003Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000003Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000003Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000003Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000003Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000003Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000003Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000003Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000003Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000003Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000003Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000003Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000003Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000003Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000003Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000003Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000003Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000003Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000003Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000003Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000003Response.getResTranHeader().getHWarnMsg());

			s01501030000003ResBody.setDebitNo(s01501030000003Response.getResponseBody().getDebitNo());
			s01501030000003ResBody.setErrMsg(s01501030000003Response.getResponseBody().getErrMsg());
			s01501030000003ResBody.setKnotSeqNo(s01501030000003Response.getResponseBody().getKnotSeqNo());
			s01501030000003ResBody.setTransStat(s01501030000003Response.getResponseBody().getTransStat());

			s01501030000003Res.setResHeader(responseHeader);
			s01501030000003Res.setResTranHeader(resTranHeader);
			s01501030000003Res.setGjS01501030000003ResBody(s01501030000003ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000003Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000003】进口信用证开证修改接口成功，流水号为: " + s01501030000003ReqHeader.getReqSeqNo());
				
			} else {// 失败
				
				System.out.println("调用【S01501030000003】进口信用证开证修改接口失败，流水号为: " + s01501030000003ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000003Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000003】进口信用证开证修改接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000003ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000003】进口信用证开证修改接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000003ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000003Res;
	}

	// 进口保函开立接口
	@Bizlet("")
	public GJS01501110000004Res executeS01501110000004(GJS01501110000004Req gjS01501110000004Req) {
		String zservice = "/WebService/CRMS_SVR/S01501110000004";
		String url = getUrl() + zservice;

		S01501110000004ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501110000004ReqHeader = new S01501110000004ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501110000004ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501110000004ReqTranHeader = new S01501110000004ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501110000004ServiceStub.FMT_CRMS_SVR_S01501110000004_IN s01501110000004ReqBody = new S01501110000004ServiceStub.FMT_CRMS_SVR_S01501110000004_IN();

		s01501110000004ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501110000004ReqHeader.setReqSysCode("01601");
		s01501110000004ReqHeader.setReqSecCode("");
		s01501110000004ReqHeader.setTxType("RQ");
		s01501110000004ReqHeader.setTxMode("0");
		s01501110000004ReqHeader.setTxCode("S01501110000004");
		s01501110000004ReqHeader.setReqDate(DateTools.getTime8());
		s01501110000004ReqHeader.setReqTime(DateTools.getTime20());
		s01501110000004ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501110000004ReqHeader.setChanlNo("48");
		s01501110000004ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501110000004ReqHeader.setTermNo("");
		s01501110000004ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501110000004ReqHeader.setSendFileName("");
		s01501110000004ReqHeader.setBeginRec("");
		s01501110000004ReqHeader.setMaxRec(null);
		s01501110000004ReqHeader.setFileHMac("");
		s01501110000004ReqHeader.setHMac("");

		// s01501110000004ReqTranHeader.setHMsgMac("");
		// s01501110000004ReqTranHeader.setHMacBrch("");
		s01501110000004ReqTranHeader.setHPinSeed("");
		s01501110000004ReqTranHeader.setHOriChnl("");
		// s01501110000004ReqTranHeader.setHAimCd("301");
		s01501110000004ReqTranHeader.setHSecFlag("0");
		s01501110000004ReqTranHeader.setHPwdFlag("0");
		s01501110000004ReqTranHeader.setHCombFlag("0");
		s01501110000004ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501110000004ReqTranHeader.setHEndFlag("");
		// s01501110000004ReqTranHeader.setHMsgNo("");
		// s01501110000004ReqTranHeader.setHVerfFlag("");
		s01501110000004ReqTranHeader.setHSecInfoVerNo("");
		s01501110000004ReqTranHeader.setHSysChnl("48");
		// s01501110000004ReqTranHeader.setHoffsetInfo("");
		s01501110000004ReqTranHeader.setHLegaObj("9999");
		s01501110000004ReqTranHeader.setHMsgRefNo("");
		// s01501110000004ReqTranHeader.setHCommType("");
		// s01501110000004ReqTranHeader.setHDeviceNo("");
		// s01501110000004ReqTranHeader.setHSuperFlag("");
		// s01501110000004ReqTranHeader.setHChkFlag("");
		// s01501110000004ReqTranHeader.setHChkTxnCd("");
		// s01501110000004ReqTranHeader.setHVerfCd("");
		// s01501110000004ReqTranHeader.setHCommitFlag("");
		// s01501110000004ReqTranHeader.setHTranRes("");
		s01501110000004ReqTranHeader.setHTermNo("");
		s01501110000004ReqTranHeader.setHCityCd("");
		s01501110000004ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501110000004ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501110000004ReqTranHeader.setHTxnCd("000004");
		// s01501110000004ReqTranHeader.setHSubTxnCd("");
		s01501110000004ReqTranHeader.setHTxnMod("");
		// s01501110000004ReqTranHeader.setHTxnSeq("");
		s01501110000004ReqTranHeader.setHReserveLen("");
		s01501110000004ReqTranHeader.setHSenderSvcCd("");
		s01501110000004ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501110000004ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501110000004ReqTranHeader.setHAuthUserID("");
		s01501110000004ReqTranHeader.setHAuthVerfInfo("");
		s01501110000004ReqTranHeader.setHAuthFlag("");
		s01501110000004ReqTranHeader.setHRefSeq("");
		s01501110000004ReqTranHeader.setHAuthSeri("");
		s01501110000004ReqTranHeader.setHHostSeq("");
		s01501110000004ReqTranHeader.setHRefDt("");
		s01501110000004ReqTranHeader.setHSvcVer("");
		s01501110000004ReqTranHeader.setHreserveMsg("");
		// s01501110000004ReqTranHeader.setDataLength(null);
		// s01501110000004ReqTranHeader.setHintVerNo(null);
		s01501110000004ReqTranHeader.setHintOrigMark(null);
		// s01501110000004ReqTranHeader.setHintDestMark(null);
		// s01501110000004ReqTranHeader.setHTranVer("");
		// s01501110000004ReqTranHeader.setHOrigMark("");
		// s01501110000004ReqTranHeader.setHDestMark("");
		// s01501110000004ReqTranHeader.setHIdentFlag("");

		s01501110000004ReqBody.setAmt(gjS01501110000004Req.getAmt());
		s01501110000004ReqBody.setBondAcct(gjS01501110000004Req.getBondAcct());
		s01501110000004ReqBody.setBondAmt(gjS01501110000004Req.getBondAmt());
		s01501110000004ReqBody.setBondCurr(gjS01501110000004Req.getBondCurr());
		s01501110000004ReqBody.setBondRate(gjS01501110000004Req.getBondRate());
		s01501110000004ReqBody.setCertMatuType(gjS01501110000004Req.getCertMatuType());
		s01501110000004ReqBody.setContractNo(gjS01501110000004Req.getContractNo());
		s01501110000004ReqBody.setCurrency(gjS01501110000004Req.getCurrency());
		s01501110000004ReqBody.setDealBrch(gjS01501110000004Req.getDealBrch());
		//s01501110000004ReqBody.setDealBrch("0200");//86测试用
		s01501110000004ReqBody.setDebitNo(gjS01501110000004Req.getDebitNo());
		s01501110000004ReqBody.setForwDay(gjS01501110000004Req.getForwDay());
		s01501110000004ReqBody.setGrantType(gjS01501110000004Req.getGrantType());
		s01501110000004ReqBody.setIoanBene(gjS01501110000004Req.getIoanBene());
		s01501110000004ReqBody.setIoaner(gjS01501110000004Req.getIoaner());
		s01501110000004ReqBody.setMatuDat(gjS01501110000004Req.getMatuDat());
		s01501110000004ReqBody.setMatuType(gjS01501110000004Req.getMatuType());
		s01501110000004ReqBody.setOpernDate(gjS01501110000004Req.getOpernDate());
		s01501110000004ReqBody.setTradeAgreeAmt(gjS01501110000004Req.getTradeAgreeAmt());
		s01501110000004ReqBody.setTradeAgreeNo(gjS01501110000004Req.getTradeAgreeNo());

		S01501110000004ServiceStub.S01501110000004Response s01501110000004Response = new S01501110000004ServiceStub.S01501110000004Response();
		GJS01501110000004Res s01501110000004Res = new GJS01501110000004Res();
		GJS01501110000004ResBody s01501110000004ResBody = new GJS01501110000004ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501110000004ServiceStub stub = new S01501110000004ServiceStub(url);
			S01501110000004ServiceStub.S01501110000004 service = new S01501110000004ServiceStub.S01501110000004();
			service.setRequestHeader(s01501110000004ReqHeader);
			service.setReqTranHeader(s01501110000004ReqTranHeader);
			service.setRequestBody(s01501110000004ReqBody);
			s01501110000004Response = stub.S01501110000004(service);
			
			responseHeader.setVersionNo(s01501110000004Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501110000004Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501110000004Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501110000004Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501110000004Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501110000004Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501110000004Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501110000004Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501110000004Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501110000004Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501110000004Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501110000004Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501110000004Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501110000004Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501110000004Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501110000004Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501110000004Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501110000004Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501110000004Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501110000004Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501110000004Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501110000004Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501110000004Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501110000004Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501110000004Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501110000004Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501110000004Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501110000004Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501110000004Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501110000004Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501110000004Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501110000004Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501110000004Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501110000004Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501110000004Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501110000004Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501110000004Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501110000004Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501110000004Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501110000004Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501110000004Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501110000004Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501110000004Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501110000004Response.getResTranHeader().getHWarnMsg());

			s01501110000004ResBody.setDebitNo(s01501110000004Response.getResponseBody().getDebitNo());
			s01501110000004ResBody.setErrMsg(s01501110000004Response.getResponseBody().getErrMsg());
			s01501110000004ResBody.setKnotSeqNo(s01501110000004Response.getResponseBody().getKnotSeqNo());
			s01501110000004ResBody.setTransStat(s01501110000004Response.getResponseBody().getTransStat());

			s01501110000004Res.setResHeader(responseHeader);
			s01501110000004Res.setResTranHeader(resTranHeader);
			s01501110000004Res.setGjS01501110000004ResBody(s01501110000004ResBody);
			
			// 成功
			if ("AAAAAAA".equals(s01501110000004Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501110000004】进口保函开立接口成功，流水号为: " + s01501110000004ReqHeader.getReqSeqNo());
			} else {// 失败
				System.out.println("调用【S01501110000004】进口保函开立接口失败，流水号为: " + s01501110000004ReqHeader.getReqSeqNo() + 
						"失败原因:" + s01501110000004Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501110000004】进口保函开立接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501110000004ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501110000004】进口保函开立接口失败：" + e.getMessage() + "ESB流水号: " + s01501110000004ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501110000004Res;
	}

	// 进口保函修改接口
	@Bizlet("")
	public GJS01501030000005Res executeS01501030000005(GJS01501030000005Req gjS01501030000005Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000005";
		String url = getUrl() + zservice;

		S01501030000005ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000005ReqHeader = new S01501030000005ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000005ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000005ReqTranHeader = new S01501030000005ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000005ServiceStub.FMT_CRMS_SVR_S01501030000005_IN s01501030000005ReqBody = new S01501030000005ServiceStub.FMT_CRMS_SVR_S01501030000005_IN();

		s01501030000005ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000005ReqHeader.setReqSysCode("01601");
		s01501030000005ReqHeader.setReqSecCode("");
		s01501030000005ReqHeader.setTxType("RQ");
		s01501030000005ReqHeader.setTxMode("0");
		s01501030000005ReqHeader.setTxCode("S01501030000005");
		s01501030000005ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000005ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000005ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000005ReqHeader.setChanlNo("48");
		s01501030000005ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000005ReqHeader.setTermNo("");
		s01501030000005ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000005ReqHeader.setSendFileName("");
		s01501030000005ReqHeader.setBeginRec("");
		s01501030000005ReqHeader.setMaxRec(null);
		s01501030000005ReqHeader.setFileHMac("");
		s01501030000005ReqHeader.setHMac("");
		
		// s01501030000005ReqTranHeader.setHMsgMac("");
		// s01501030000005ReqTranHeader.setHMacBrch("");
		s01501030000005ReqTranHeader.setHPinSeed("");
		s01501030000005ReqTranHeader.setHOriChnl("");
		// s01501030000005ReqTranHeader.setHAimCd("301");
		s01501030000005ReqTranHeader.setHSecFlag("0");
		s01501030000005ReqTranHeader.setHPwdFlag("0");
		s01501030000005ReqTranHeader.setHCombFlag("0");
		s01501030000005ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000005ReqTranHeader.setHEndFlag("");
		// s01501030000005ReqTranHeader.setHMsgNo("");
		// s01501030000005ReqTranHeader.setHVerfFlag("");
		s01501030000005ReqTranHeader.setHSecInfoVerNo("");
		s01501030000005ReqTranHeader.setHSysChnl("48");
		// s01501030000005ReqTranHeader.setHoffsetInfo("");
		s01501030000005ReqTranHeader.setHLegaObj("9999");
		s01501030000005ReqTranHeader.setHMsgRefNo("");
		// s01501030000005ReqTranHeader.setHCommType("");
		// s01501030000005ReqTranHeader.setHDeviceNo("");
		// s01501030000005ReqTranHeader.setHSuperFlag("");
		// s01501030000005ReqTranHeader.setHChkFlag("");
		// s01501030000005ReqTranHeader.setHChkTxnCd("");
		// s01501030000005ReqTranHeader.setHVerfCd("");
		// s01501030000005ReqTranHeader.setHCommitFlag("");
		// s01501030000005ReqTranHeader.setHTranRes("");
		s01501030000005ReqTranHeader.setHTermNo("");
		s01501030000005ReqTranHeader.setHCityCd("");
		s01501030000005ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000005ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000005ReqTranHeader.setHTxnCd("000005");
		// s01501030000005ReqTranHeader.setHSubTxnCd("");
		s01501030000005ReqTranHeader.setHTxnMod("");
		// s01501030000005ReqTranHeader.setHTxnSeq("");
		s01501030000005ReqTranHeader.setHReserveLen("");
		s01501030000005ReqTranHeader.setHSenderSvcCd("");
		s01501030000005ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000005ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000005ReqTranHeader.setHAuthUserID("");
		s01501030000005ReqTranHeader.setHAuthVerfInfo("");
		s01501030000005ReqTranHeader.setHAuthFlag("");
		s01501030000005ReqTranHeader.setHRefSeq("");
		s01501030000005ReqTranHeader.setHAuthSeri("");
		s01501030000005ReqTranHeader.setHHostSeq("");
		s01501030000005ReqTranHeader.setHRefDt("");
		s01501030000005ReqTranHeader.setHSvcVer("");
		s01501030000005ReqTranHeader.setHreserveMsg("");
		// s01501030000005ReqTranHeader.setDataLength(null);
		// s01501030000005ReqTranHeader.setHintVerNo(null);
		s01501030000005ReqTranHeader.setHintOrigMark(null);
		// s01501030000005ReqTranHeader.setHintDestMark(null);
		// s01501030000005ReqTranHeader.setHTranVer("");
		// s01501030000005ReqTranHeader.setHOrigMark("");
		// s01501030000005ReqTranHeader.setHDestMark("");
		// s01501030000005ReqTranHeader.setHIdentFlag("");

		s01501030000005ReqBody.setAmt(gjS01501030000005Req.getAmt());
		s01501030000005ReqBody.setBondAcct(gjS01501030000005Req.getBondAcct());
		s01501030000005ReqBody.setBondAmt(gjS01501030000005Req.getBondAmt());
		s01501030000005ReqBody.setBondCurr(gjS01501030000005Req.getBondCurr());
		s01501030000005ReqBody.setBondRate(gjS01501030000005Req.getBondRate());
		s01501030000005ReqBody.setContractNo(gjS01501030000005Req.getContractNo());
		s01501030000005ReqBody.setCurrency(gjS01501030000005Req.getCurrency());
		s01501030000005ReqBody.setDealBrch(gjS01501030000005Req.getDealBrch());
		//s01501030000005ReqBody.setDealBrch("0200");//86测试用
		s01501030000005ReqBody.setDebitNo(gjS01501030000005Req.getDebitNo());
		s01501030000005ReqBody.setGrantType(gjS01501030000005Req.getGrantType());
		s01501030000005ReqBody.setMatuDat(gjS01501030000005Req.getMatuDat());

		S01501030000005ServiceStub.S01501030000005Response s01501030000005Response = new S01501030000005ServiceStub.S01501030000005Response();
		GJS01501030000005Res s01501030000005Res = new GJS01501030000005Res();
		GJS01501030000005ResBody s01501030000005ResBody = new GJS01501030000005ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000005ServiceStub stub = new S01501030000005ServiceStub(url);
			S01501030000005ServiceStub.S01501030000005 service = new S01501030000005ServiceStub.S01501030000005();
			service.setRequestHeader(s01501030000005ReqHeader);
			service.setReqTranHeader(s01501030000005ReqTranHeader);
			service.setRequestBody(s01501030000005ReqBody);
			s01501030000005Response = stub.S01501030000005(service);
			
			responseHeader.setVersionNo(s01501030000005Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000005Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000005Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000005Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000005Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000005Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000005Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000005Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000005Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000005Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000005Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000005Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000005Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000005Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000005Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000005Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000005Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000005Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000005Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000005Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000005Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000005Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000005Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000005Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000005Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000005Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000005Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000005Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000005Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000005Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000005Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000005Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000005Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000005Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000005Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000005Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000005Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000005Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000005Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000005Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000005Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000005Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000005Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000005Response.getResTranHeader().getHWarnMsg());

			s01501030000005ResBody.setDebitNo(s01501030000005Response.getResponseBody().getDebitNo());
			s01501030000005ResBody.setErrMsg(s01501030000005Response.getResponseBody().getErrMsg());
			s01501030000005ResBody.setKnotSeqNo(s01501030000005Response.getResponseBody().getKnotSeqNo());
			s01501030000005ResBody.setTransStat(s01501030000005Response.getResponseBody().getTransStat());

			s01501030000005Res.setResHeader(responseHeader);
			s01501030000005Res.setResTranHeader(resTranHeader);
			s01501030000005Res.setGjS01501030000005ResBody(s01501030000005ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000005Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000005】进口保函修改接口成功，流水号为: " + s01501030000005ReqHeader.getReqSeqNo());
				
			} else {// 失败
				
				System.out.println("调用【S01501030000005】进口保函修改接口失败，流水号为: " + s01501030000005ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000005Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000005】进口保函修改接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000005ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000005】进口保函修改接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000005ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000005Res;
	}

	// 提货担保接口
	@Bizlet("")
	public GJS01501030000006Res executeS01501030000006(GJS01501030000006Req gjS01501030000006Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000006";
		String url = getUrl() + zservice;

		S01501030000006ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000006ReqHeader = new S01501030000006ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000006ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000006ReqTranHeader = new S01501030000006ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000006ServiceStub.FMT_CRMS_SVR_S01501030000006_IN s01501030000006ReqBody = new S01501030000006ServiceStub.FMT_CRMS_SVR_S01501030000006_IN();

		s01501030000006ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000006ReqHeader.setReqSysCode("01601");
		s01501030000006ReqHeader.setReqSecCode("");
		s01501030000006ReqHeader.setTxType("RQ");
		s01501030000006ReqHeader.setTxMode("0");
		s01501030000006ReqHeader.setTxCode("S01501030000006");
		s01501030000006ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000006ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000006ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000006ReqHeader.setChanlNo("48");
		s01501030000006ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000006ReqHeader.setTermNo("");
		s01501030000006ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000006ReqHeader.setSendFileName("");
		s01501030000006ReqHeader.setBeginRec("");
		s01501030000006ReqHeader.setMaxRec(null);
		s01501030000006ReqHeader.setFileHMac("");
		s01501030000006ReqHeader.setHMac("");

		// s01501030000006ReqTranHeader.setHMsgMac("");
		// s01501030000006ReqTranHeader.setHMacBrch("");
		s01501030000006ReqTranHeader.setHPinSeed("");
		s01501030000006ReqTranHeader.setHOriChnl("");
		// s01501030000006ReqTranHeader.setHAimCd("301");
		s01501030000006ReqTranHeader.setHSecFlag("0");
		s01501030000006ReqTranHeader.setHPwdFlag("0");
		s01501030000006ReqTranHeader.setHCombFlag("0");
		s01501030000006ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000006ReqTranHeader.setHEndFlag("");
		// s01501030000006ReqTranHeader.setHMsgNo("");
		// s01501030000006ReqTranHeader.setHVerfFlag("");
		s01501030000006ReqTranHeader.setHSecInfoVerNo("");
		s01501030000006ReqTranHeader.setHSysChnl("48");
		// s01501030000006ReqTranHeader.setHoffsetInfo("");
		s01501030000006ReqTranHeader.setHLegaObj("9999");
		s01501030000006ReqTranHeader.setHMsgRefNo("");
		// s01501030000006ReqTranHeader.setHCommType("");
		// s01501030000006ReqTranHeader.setHDeviceNo("");
		// s01501030000006ReqTranHeader.setHSuperFlag("");
		// s01501030000006ReqTranHeader.setHChkFlag("");
		// s01501030000006ReqTranHeader.setHChkTxnCd("");
		// s01501030000006ReqTranHeader.setHVerfCd("");
		// s01501030000006ReqTranHeader.setHCommitFlag("");
		// s01501030000006ReqTranHeader.setHTranRes("");
		s01501030000006ReqTranHeader.setHTermNo("");
		s01501030000006ReqTranHeader.setHCityCd("");
		s01501030000006ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000006ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000006ReqTranHeader.setHTxnCd("000006");
		// s01501030000006ReqTranHeader.setHSubTxnCd("");
		s01501030000006ReqTranHeader.setHTxnMod("");
		// s01501030000006ReqTranHeader.setHTxnSeq("");
		s01501030000006ReqTranHeader.setHReserveLen("");
		s01501030000006ReqTranHeader.setHSenderSvcCd("");
		s01501030000006ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000006ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000006ReqTranHeader.setHAuthUserID("");
		s01501030000006ReqTranHeader.setHAuthVerfInfo("");
		s01501030000006ReqTranHeader.setHAuthFlag("");
		s01501030000006ReqTranHeader.setHRefSeq("");
		s01501030000006ReqTranHeader.setHAuthSeri("");
		s01501030000006ReqTranHeader.setHHostSeq("");
		s01501030000006ReqTranHeader.setHRefDt("");
		s01501030000006ReqTranHeader.setHSvcVer("");
		s01501030000006ReqTranHeader.setHreserveMsg("");
		// s01501030000006ReqTranHeader.setDataLength(null);
		// s01501030000006ReqTranHeader.setHintVerNo(null);
		s01501030000006ReqTranHeader.setHintOrigMark(null);
		// s01501030000006ReqTranHeader.setHintDestMark(null);
		// s01501030000006ReqTranHeader.setHTranVer("");
		// s01501030000006ReqTranHeader.setHOrigMark("");
		// s01501030000006ReqTranHeader.setHDestMark("");
		// s01501030000006ReqTranHeader.setHIdentFlag("");

		s01501030000006ReqBody.setAmt(gjS01501030000006Req.getAmt());
		s01501030000006ReqBody.setBondAcct(gjS01501030000006Req.getBondAcct());
		s01501030000006ReqBody.setBondAmt(gjS01501030000006Req.getBondAmt());
		s01501030000006ReqBody.setBondCurr(gjS01501030000006Req.getBondCurr());
		s01501030000006ReqBody.setBondRate(gjS01501030000006Req.getBondRate());
		s01501030000006ReqBody.setContractNo(gjS01501030000006Req.getContractNo());
		s01501030000006ReqBody.setCurrency(gjS01501030000006Req.getCurrency());
		s01501030000006ReqBody.setDealBrch(gjS01501030000006Req.getDealBrch());
		//s01501030000006ReqBody.setDealBrch("0200");//86测试用
		s01501030000006ReqBody.setDebitNo(gjS01501030000006Req.getDebitNo());
		s01501030000006ReqBody.setMatuDat(gjS01501030000006Req.getMatuDat());
		s01501030000006ReqBody.setBillDate(gjS01501030000006Req.getBillDate());
		s01501030000006ReqBody.setBillNo(gjS01501030000006Req.getBillNo());
		s01501030000006ReqBody.setCredNo(gjS01501030000006Req.getCredNo());
		s01501030000006ReqBody.setIoaner(gjS01501030000006Req.getIoaner());

		S01501030000006ServiceStub.S01501030000006Response s01501030000006Response = new S01501030000006ServiceStub.S01501030000006Response();
		GJS01501030000006Res s01501030000006Res = new GJS01501030000006Res();
		GJS01501030000006ResBody s01501030000006ResBody = new GJS01501030000006ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000006ServiceStub stub = new S01501030000006ServiceStub(url);
			S01501030000006ServiceStub.S01501030000006 service = new S01501030000006ServiceStub.S01501030000006();
			service.setRequestHeader(s01501030000006ReqHeader);
			service.setReqTranHeader(s01501030000006ReqTranHeader);
			service.setRequestBody(s01501030000006ReqBody);
			s01501030000006Response = stub.S01501030000006(service);
			
			responseHeader.setVersionNo(s01501030000006Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000006Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000006Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000006Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000006Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000006Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000006Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000006Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000006Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000006Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000006Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000006Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000006Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000006Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000006Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000006Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000006Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000006Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000006Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000006Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000006Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000006Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000006Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000006Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000006Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000006Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000006Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000006Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000006Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000006Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000006Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000006Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000006Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000006Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000006Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000006Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000006Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000006Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000006Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000006Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000006Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000006Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000006Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000006Response.getResTranHeader().getHWarnMsg());

			s01501030000006ResBody.setDebitNo(s01501030000006Response.getResponseBody().getDebitNo());
			s01501030000006ResBody.setErrMsg(s01501030000006Response.getResponseBody().getErrMsg());
			s01501030000006ResBody.setKnotSeqNo(s01501030000006Response.getResponseBody().getKnotSeqNo());
			s01501030000006ResBody.setTransStat(s01501030000006Response.getResponseBody().getTransStat());

			s01501030000006Res.setResHeader(responseHeader);
			s01501030000006Res.setResTranHeader(resTranHeader);
			s01501030000006Res.setGjS01501030000006ResBody(s01501030000006ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000006Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000006】提货担保接口成功，流水号为: " + s01501030000006ReqHeader.getReqSeqNo());
				
			} else {// 失败
				
				System.out.println("调用【S01501030000006】提货担保接口失败，流水号为: " + s01501030000006ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000006Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000006】提货担保接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000006ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000006】提货担保接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000006ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000006Res;
	}

	// 放款撤销接口
	@Bizlet("")
	public GJS01501070000007Res executeS01501070000007(GJS01501070000007Req gjS01501070000007Req) {
		String zservice = "/WebService/CRMS_SVR/S01501070000007";
		String url = getUrl() + zservice;

		S01501070000007ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501070000007ReqHeader = new S01501070000007ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501070000007ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501070000007ReqTranHeader = new S01501070000007ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501070000007ServiceStub.FMT_CRMS_SVR_S01501070000007_IN s01501070000007ReqBody = new S01501070000007ServiceStub.FMT_CRMS_SVR_S01501070000007_IN();

		s01501070000007ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501070000007ReqHeader.setReqSysCode("01601");
		s01501070000007ReqHeader.setReqSecCode("");
		s01501070000007ReqHeader.setTxType("RQ");
		s01501070000007ReqHeader.setTxMode("0");
		s01501070000007ReqHeader.setTxCode("S01501070000007");
		s01501070000007ReqHeader.setReqDate(DateTools.getTime8());
		s01501070000007ReqHeader.setReqTime(DateTools.getTime20());
		s01501070000007ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501070000007ReqHeader.setChanlNo("48");
		s01501070000007ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501070000007ReqHeader.setTermNo("");
		s01501070000007ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501070000007ReqHeader.setSendFileName("");
		s01501070000007ReqHeader.setBeginRec("");
		s01501070000007ReqHeader.setMaxRec(null);
		s01501070000007ReqHeader.setFileHMac("");
		s01501070000007ReqHeader.setHMac("");

		// s01501070000007ReqTranHeader.setHMsgMac("");
		// s01501070000007ReqTranHeader.setHMacBrch("");
		s01501070000007ReqTranHeader.setHPinSeed("");
		s01501070000007ReqTranHeader.setHOriChnl("");
		// s01501070000007ReqTranHeader.setHAimCd("301");
		s01501070000007ReqTranHeader.setHSecFlag("0");
		s01501070000007ReqTranHeader.setHPwdFlag("0");
		s01501070000007ReqTranHeader.setHCombFlag("0");
		s01501070000007ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501070000007ReqTranHeader.setHEndFlag("");
		// s01501070000007ReqTranHeader.setHMsgNo("");
		// s01501070000007ReqTranHeader.setHVerfFlag("");
		s01501070000007ReqTranHeader.setHSecInfoVerNo("");
		s01501070000007ReqTranHeader.setHSysChnl("48");
		// s01501070000007ReqTranHeader.setHoffsetInfo("");
		s01501070000007ReqTranHeader.setHLegaObj("9999");
		s01501070000007ReqTranHeader.setHMsgRefNo("");
		// s01501070000007ReqTranHeader.setHCommType("");
		// s01501070000007ReqTranHeader.setHDeviceNo("");
		// s01501070000007ReqTranHeader.setHSuperFlag("");
		// s01501070000007ReqTranHeader.setHChkFlag("");
		// s01501070000007ReqTranHeader.setHChkTxnCd("");
		// s01501070000007ReqTranHeader.setHVerfCd("");
		// s01501070000007ReqTranHeader.setHCommitFlag("");
		// s01501070000007ReqTranHeader.setHTranRes("");
		s01501070000007ReqTranHeader.setHTermNo("");
		s01501070000007ReqTranHeader.setHCityCd("");
		s01501070000007ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501070000007ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501070000007ReqTranHeader.setHTxnCd("000007");
		// s01501070000007ReqTranHeader.setHSubTxnCd("");
		s01501070000007ReqTranHeader.setHTxnMod("");
		// s01501070000007ReqTranHeader.setHTxnSeq("");
		s01501070000007ReqTranHeader.setHReserveLen("");
		s01501070000007ReqTranHeader.setHSenderSvcCd("");
		s01501070000007ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501070000007ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501070000007ReqTranHeader.setHAuthUserID("");
		s01501070000007ReqTranHeader.setHAuthVerfInfo("");
		s01501070000007ReqTranHeader.setHAuthFlag("");
		s01501070000007ReqTranHeader.setHRefSeq("");
		s01501070000007ReqTranHeader.setHAuthSeri("");
		s01501070000007ReqTranHeader.setHHostSeq("");
		s01501070000007ReqTranHeader.setHRefDt("");
		s01501070000007ReqTranHeader.setHSvcVer("");
		s01501070000007ReqTranHeader.setHreserveMsg("");
		// s01501070000007ReqTranHeader.setDataLength(null);
		// s01501070000007ReqTranHeader.setHintVerNo(null);
		s01501070000007ReqTranHeader.setHintOrigMark(null);
		// s01501070000007ReqTranHeader.setHintDestMark(null);
		// s01501070000007ReqTranHeader.setHTranVer("");
		// s01501070000007ReqTranHeader.setHOrigMark("");
		// s01501070000007ReqTranHeader.setHDestMark("");
		// s01501070000007ReqTranHeader.setHIdentFlag("");

		s01501070000007ReqBody.setDebitNo(gjS01501070000007Req.getDebitNo());
		s01501070000007ReqBody.setExtenAgrNo(gjS01501070000007Req.getExtenAgrNo());
		s01501070000007ReqBody.setKnotTradeNo(gjS01501070000007Req.getKnotTradeNo());

		S01501070000007ServiceStub.S01501070000007Response s01501070000007Response = new S01501070000007ServiceStub.S01501070000007Response();
		GJS01501070000007Res s01501070000007Res = new GJS01501070000007Res();
		GJS01501070000007ResBody s01501070000007ResBody = new GJS01501070000007ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501070000007ServiceStub stub = new S01501070000007ServiceStub(url);
			S01501070000007ServiceStub.S01501070000007 service = new S01501070000007ServiceStub.S01501070000007();
			service.setRequestHeader(s01501070000007ReqHeader);
			service.setReqTranHeader(s01501070000007ReqTranHeader);
			service.setRequestBody(s01501070000007ReqBody);
			s01501070000007Response = stub.S01501070000007(service);
			
			responseHeader.setVersionNo(s01501070000007Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501070000007Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501070000007Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501070000007Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501070000007Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501070000007Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501070000007Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501070000007Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501070000007Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501070000007Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501070000007Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501070000007Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501070000007Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501070000007Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501070000007Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501070000007Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501070000007Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501070000007Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501070000007Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501070000007Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501070000007Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501070000007Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501070000007Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501070000007Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501070000007Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501070000007Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501070000007Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501070000007Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501070000007Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501070000007Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501070000007Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501070000007Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501070000007Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501070000007Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501070000007Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501070000007Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501070000007Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501070000007Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501070000007Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501070000007Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501070000007Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501070000007Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501070000007Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501070000007Response.getResTranHeader().getHWarnMsg());

			s01501070000007ResBody.setDebitNo(s01501070000007Response.getResponseBody().getDebitNo());
			s01501070000007ResBody.setErrMsg(s01501070000007Response.getResponseBody().getErrMsg());
			s01501070000007ResBody.setKnotSeqNo(s01501070000007Response.getResponseBody().getKnotSeqNo());
			s01501070000007ResBody.setTransStat(s01501070000007Response.getResponseBody().getTransStat());

			s01501070000007Res.setResHeader(responseHeader);
			s01501070000007Res.setResTranHeader(resTranHeader);
			s01501070000007Res.setGjS01501070000007ResBody(s01501070000007ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501070000007Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501070000007】放款撤销接口成功，流水号为: " + s01501070000007ReqHeader.getReqSeqNo());
				
			} else {// 失败
				
				System.out.println("调用【S01501070000007】放款撤销接口失败，流水号为: " + s01501070000007ReqHeader.getReqSeqNo() + "失败原因:" + s01501070000007Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501070000007】放款撤销接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501070000007ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501070000007】放款撤销接口失败：" + e.getMessage() + "ESB流水号: " + s01501070000007ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501070000007Res;
	}

	// 编号校验接口
	@Bizlet("")
	public GJS01501030000008Res executeS01501030000008(GJS01501030000008Req gjS01501030000008Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000008";
		String url = getUrl() + zservice;

		S01501030000008ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000008ReqHeader = new S01501030000008ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000008ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000008ReqTranHeader = new S01501030000008ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000008ServiceStub.FMT_CRMS_SVR_S01501030000008_IN s01501030000008ReqBody = new S01501030000008ServiceStub.FMT_CRMS_SVR_S01501030000008_IN();

		s01501030000008ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000008ReqHeader.setReqSysCode("01601");
		s01501030000008ReqHeader.setReqSecCode("");
		s01501030000008ReqHeader.setTxType("RQ");
		s01501030000008ReqHeader.setTxMode("0");
		s01501030000008ReqHeader.setTxCode("S01501030000008");
		s01501030000008ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000008ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000008ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000008ReqHeader.setChanlNo("48");
		s01501030000008ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000008ReqHeader.setTermNo("");
		s01501030000008ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000008ReqHeader.setSendFileName("");
		s01501030000008ReqHeader.setBeginRec("");
		s01501030000008ReqHeader.setMaxRec(null);
		s01501030000008ReqHeader.setFileHMac("");
		s01501030000008ReqHeader.setHMac("");

		// s01501030000008ReqTranHeader.setHMsgMac("");
		// s01501030000008ReqTranHeader.setHMacBrch("");
		s01501030000008ReqTranHeader.setHPinSeed("");
		s01501030000008ReqTranHeader.setHOriChnl("");
		// s01501030000008ReqTranHeader.setHAimCd("301");
		s01501030000008ReqTranHeader.setHSecFlag("0");
		s01501030000008ReqTranHeader.setHPwdFlag("0");
		s01501030000008ReqTranHeader.setHCombFlag("0");
		s01501030000008ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000008ReqTranHeader.setHEndFlag("");
		// s01501030000008ReqTranHeader.setHMsgNo("");
		// s01501030000008ReqTranHeader.setHVerfFlag("");
		s01501030000008ReqTranHeader.setHSecInfoVerNo("");
		s01501030000008ReqTranHeader.setHSysChnl("48");
		// s01501030000008ReqTranHeader.setHoffsetInfo("");
		s01501030000008ReqTranHeader.setHLegaObj("9999");
		s01501030000008ReqTranHeader.setHMsgRefNo("");
		// s01501030000008ReqTranHeader.setHCommType("");
		// s01501030000008ReqTranHeader.setHDeviceNo("");
		// s01501030000008ReqTranHeader.setHSuperFlag("");
		// s01501030000008ReqTranHeader.setHChkFlag("");
		// s01501030000008ReqTranHeader.setHChkTxnCd("");
		// s01501030000008ReqTranHeader.setHVerfCd("");
		// s01501030000008ReqTranHeader.setHCommitFlag("");
		// s01501030000008ReqTranHeader.setHTranRes("");
		s01501030000008ReqTranHeader.setHTermNo("");
		s01501030000008ReqTranHeader.setHCityCd("");
		s01501030000008ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000008ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000008ReqTranHeader.setHTxnCd("000008");
		// s01501030000008ReqTranHeader.setHSubTxnCd("");
		s01501030000008ReqTranHeader.setHTxnMod("");
		// s01501030000008ReqTranHeader.setHTxnSeq("");
		s01501030000008ReqTranHeader.setHReserveLen("");
		s01501030000008ReqTranHeader.setHSenderSvcCd("");
		s01501030000008ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000008ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000008ReqTranHeader.setHAuthUserID("");
		s01501030000008ReqTranHeader.setHAuthVerfInfo("");
		s01501030000008ReqTranHeader.setHAuthFlag("");
		s01501030000008ReqTranHeader.setHRefSeq("");
		s01501030000008ReqTranHeader.setHAuthSeri("");
		s01501030000008ReqTranHeader.setHHostSeq("");
		s01501030000008ReqTranHeader.setHRefDt("");
		s01501030000008ReqTranHeader.setHSvcVer("");
		s01501030000008ReqTranHeader.setHreserveMsg("");
		// s01501030000008ReqTranHeader.setDataLength(null);
		// s01501030000008ReqTranHeader.setHintVerNo(null);
		s01501030000008ReqTranHeader.setHintOrigMark(null);
		// s01501030000008ReqTranHeader.setHintDestMark(null);
		// s01501030000008ReqTranHeader.setHTranVer("");
		// s01501030000008ReqTranHeader.setHOrigMark("");
		// s01501030000008ReqTranHeader.setHDestMark("");
		// s01501030000008ReqTranHeader.setHIdentFlag("");

		s01501030000008ReqBody.setBondAcct(gjS01501030000008Req.getBondAcct());
		s01501030000008ReqBody.setBondCurr(gjS01501030000008Req.getBondCurr());
		s01501030000008ReqBody.setCustNo(gjS01501030000008Req.getCustNo());
		s01501030000008ReqBody.setKnotBusiNo(gjS01501030000008Req.getKnotBusiNo());
		s01501030000008ReqBody.setKnotTradeTp(gjS01501030000008Req.getKnotTradeTp());
		s01501030000008ReqBody.setProSubTp(gjS01501030000008Req.getProSubTp());

		S01501030000008ServiceStub.S01501030000008Response s01501030000008Response = new S01501030000008ServiceStub.S01501030000008Response();
		GJS01501030000008Res s01501030000008Res = new GJS01501030000008Res();
		GJS01501030000008ResBody s01501030000008ResBody = new GJS01501030000008ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000008ServiceStub stub = new S01501030000008ServiceStub(url);
			S01501030000008ServiceStub.S01501030000008 service = new S01501030000008ServiceStub.S01501030000008();
			service.setRequestHeader(s01501030000008ReqHeader);
			service.setReqTranHeader(s01501030000008ReqTranHeader);
			service.setRequestBody(s01501030000008ReqBody);
			s01501030000008Response = stub.S01501030000008(service);
			
			responseHeader.setVersionNo(s01501030000008Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000008Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000008Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000008Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000008Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000008Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000008Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000008Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000008Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000008Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000008Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000008Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000008Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000008Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000008Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000008Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000008Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000008Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000008Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000008Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000008Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000008Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000008Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000008Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000008Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000008Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000008Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000008Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000008Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000008Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000008Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000008Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000008Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000008Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000008Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000008Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000008Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000008Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000008Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000008Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000008Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000008Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000008Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000008Response.getResTranHeader().getHWarnMsg());

			s01501030000008ResBody.setErrMsg(s01501030000008Response.getResponseBody().getErrMsg());
			s01501030000008ResBody.setTransStat(s01501030000008Response.getResponseBody().getTransStat());
			s01501030000008ResBody.setKnotBusiNo(s01501030000008Response.getResponseBody().getKnotBusiNo());
			s01501030000008ResBody.setKnotMsg(s01501030000008Response.getResponseBody().getKnotMsg());
			s01501030000008ResBody.setLoanAmt(s01501030000008Response.getResponseBody().getLoanAmt());
			s01501030000008ResBody.setLoanCur(s01501030000008Response.getResponseBody().getLoanCur());
			s01501030000008ResBody.setLoanDay(s01501030000008Response.getResponseBody().getLoanDay());

			s01501030000008Res.setResHeader(responseHeader);
			s01501030000008Res.setResTranHeader(resTranHeader);
			s01501030000008Res.setGjS01501030000008ResBody(s01501030000008ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000008Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000008】编号校验接口成功，流水号为: " + s01501030000008ReqHeader.getReqSeqNo());
				
			} else {// 失败
				
				System.out.println("调用【S01501030000008】编号校验接口失败，流水号为: " + s01501030000008ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000008Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000008】编号校验接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000008ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000008】编号校验接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000008ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000008Res;
	}

	// 融资展期接口
	@Bizlet("")
	public GJS01501030000009Res executeS01501030000009(GJS01501030000009Req gjS01501030000009Req) {
		String zservice = "/WebService/CRMS_SVR/S01501030000009";
		String url = getUrl() + zservice;

		S01501030000009ServiceStub.FMT_SOAP_UTF8_RequestHeader s01501030000009ReqHeader = new S01501030000009ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01501030000009ServiceStub.FMT_SOAP_UTF8_ReqTranHeader s01501030000009ReqTranHeader = new S01501030000009ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01501030000009ServiceStub.FMT_CRMS_SVR_S01501030000009_IN s01501030000009ReqBody = new S01501030000009ServiceStub.FMT_CRMS_SVR_S01501030000009_IN();

		s01501030000009ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		s01501030000009ReqHeader.setReqSysCode("01601");
		s01501030000009ReqHeader.setReqSecCode("");
		s01501030000009ReqHeader.setTxType("RQ");
		s01501030000009ReqHeader.setTxMode("0");
		s01501030000009ReqHeader.setTxCode("S01501030000009");
		s01501030000009ReqHeader.setReqDate(DateTools.getTime8());
		s01501030000009ReqHeader.setReqTime(DateTools.getTime20());
		s01501030000009ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		s01501030000009ReqHeader.setChanlNo("48");
		s01501030000009ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		s01501030000009ReqHeader.setTermNo("");
		s01501030000009ReqHeader.setOper(GitUtil.getCurrentPositionCode());
		s01501030000009ReqHeader.setSendFileName("");
		s01501030000009ReqHeader.setBeginRec("");
		s01501030000009ReqHeader.setMaxRec(null);
		s01501030000009ReqHeader.setFileHMac("");
		s01501030000009ReqHeader.setHMac("");

		// s01501030000009ReqTranHeader.setHMsgMac("");
		// s01501030000009ReqTranHeader.setHMacBrch("");
		s01501030000009ReqTranHeader.setHPinSeed("");
		s01501030000009ReqTranHeader.setHOriChnl("");
		// s01501030000009ReqTranHeader.setHAimCd("301");
		s01501030000009ReqTranHeader.setHSecFlag("0");
		s01501030000009ReqTranHeader.setHPwdFlag("0");
		s01501030000009ReqTranHeader.setHCombFlag("0");
		s01501030000009ReqTranHeader.setHSvcInfo("zuhejy_01");
		// s01501030000009ReqTranHeader.setHEndFlag("");
		// s01501030000009ReqTranHeader.setHMsgNo("");
		// s01501030000009ReqTranHeader.setHVerfFlag("");
		s01501030000009ReqTranHeader.setHSecInfoVerNo("");
		s01501030000009ReqTranHeader.setHSysChnl("48");
		// s01501030000009ReqTranHeader.setHoffsetInfo("");
		s01501030000009ReqTranHeader.setHLegaObj("9999");
		s01501030000009ReqTranHeader.setHMsgRefNo("");
		// s01501030000009ReqTranHeader.setHCommType("");
		// s01501030000009ReqTranHeader.setHDeviceNo("");
		// s01501030000009ReqTranHeader.setHSuperFlag("");
		// s01501030000009ReqTranHeader.setHChkFlag("");
		// s01501030000009ReqTranHeader.setHChkTxnCd("");
		// s01501030000009ReqTranHeader.setHVerfCd("");
		// s01501030000009ReqTranHeader.setHCommitFlag("");
		// s01501030000009ReqTranHeader.setHTranRes("");
		s01501030000009ReqTranHeader.setHTermNo("");
		s01501030000009ReqTranHeader.setHCityCd("");
		s01501030000009ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		s01501030000009ReqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());
		s01501030000009ReqTranHeader.setHTxnCd("000009");
		// s01501030000009ReqTranHeader.setHSubTxnCd("");
		s01501030000009ReqTranHeader.setHTxnMod("");
		// s01501030000009ReqTranHeader.setHTxnSeq("");
		s01501030000009ReqTranHeader.setHReserveLen("");
		s01501030000009ReqTranHeader.setHSenderSvcCd("");
		s01501030000009ReqTranHeader.setHSenderSeq(DateTools.getTime8() + "00000001");
		s01501030000009ReqTranHeader.setHSenderDate(DateTools.getTime8());
		s01501030000009ReqTranHeader.setHAuthUserID("");
		s01501030000009ReqTranHeader.setHAuthVerfInfo("");
		s01501030000009ReqTranHeader.setHAuthFlag("");
		s01501030000009ReqTranHeader.setHRefSeq("");
		s01501030000009ReqTranHeader.setHAuthSeri("");
		s01501030000009ReqTranHeader.setHHostSeq("");
		s01501030000009ReqTranHeader.setHRefDt("");
		s01501030000009ReqTranHeader.setHSvcVer("");
		s01501030000009ReqTranHeader.setHreserveMsg("");
		// s01501030000009ReqTranHeader.setDataLength(null);
		// s01501030000009ReqTranHeader.setHintVerNo(null);
		s01501030000009ReqTranHeader.setHintOrigMark(null);
		// s01501030000009ReqTranHeader.setHintDestMark(null);
		// s01501030000009ReqTranHeader.setHTranVer("");
		// s01501030000009ReqTranHeader.setHOrigMark("");
		// s01501030000009ReqTranHeader.setHDestMark("");
		// s01501030000009ReqTranHeader.setHIdentFlag("");
		
		s01501030000009ReqBody.setDebitNo(gjS01501030000009Req.getDebitNo());
		s01501030000009ReqBody.setExtendDate(gjS01501030000009Req.getExtendDate());
		s01501030000009ReqBody.setExtInterestRate(gjS01501030000009Req.getExtInterestRate());
		s01501030000009ReqBody.setExtMatureDate(gjS01501030000009Req.getExtMatureDate());
		s01501030000009ReqBody.setOveInterestRate(gjS01501030000009Req.getOveInterestRate());
		
		S01501030000009ServiceStub.S01501030000009Response s01501030000009Response = new S01501030000009ServiceStub.S01501030000009Response();
		GJS01501030000009Res s01501030000009Res = new GJS01501030000009Res();
		GJS01501030000009ResBody s01501030000009ResBody = new GJS01501030000009ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S01501030000009ServiceStub stub = new S01501030000009ServiceStub(url);
			S01501030000009ServiceStub.S01501030000009 service = new S01501030000009ServiceStub.S01501030000009();
			service.setRequestHeader(s01501030000009ReqHeader);
			service.setReqTranHeader(s01501030000009ReqTranHeader);
			service.setRequestBody(s01501030000009ReqBody);
			s01501030000009Response = stub.S01501030000009(service);
			
			responseHeader.setVersionNo(s01501030000009Response.getResponseHeader().getVersionNo());
			responseHeader.setReqSysCode(s01501030000009Response.getResponseHeader().getReqSysCode());
			responseHeader.setReqSecCode(s01501030000009Response.getResponseHeader().getReqSecCode());
			responseHeader.setTxType(s01501030000009Response.getResponseHeader().getTxType());
			responseHeader.setTxMode(s01501030000009Response.getResponseHeader().getTxMode());
			responseHeader.setTxCode(s01501030000009Response.getResponseHeader().getTxCode());
			responseHeader.setReqDate(s01501030000009Response.getResponseHeader().getReqDate());
			responseHeader.setReqTime(s01501030000009Response.getResponseHeader().getReqTime());
			responseHeader.setReqSeqNo(s01501030000009Response.getResponseHeader().getReqSeqNo());
			responseHeader.setSvrDate(s01501030000009Response.getResponseHeader().getSvrDate());
			responseHeader.setSvrTime(s01501030000009Response.getResponseHeader().getSvrTime());
			responseHeader.setSvrSeqNo(s01501030000009Response.getResponseHeader().getSvrSeqNo());
			responseHeader.setRecvFileName(s01501030000009Response.getResponseHeader().getRecvFileName());
			responseHeader.setTotNum(s01501030000009Response.getResponseHeader().getTotNum());
			responseHeader.setCurrNum(s01501030000009Response.getResponseHeader().getCurrNum());
			responseHeader.setFileHMac(s01501030000009Response.getResponseHeader().getFileHMac());
			responseHeader.setHmac(s01501030000009Response.getResponseHeader().getHMac());

			resTranHeader.setHSecFlag(s01501030000009Response.getResTranHeader().getHSecFlag());
			resTranHeader.setHCombFlag(s01501030000009Response.getResTranHeader().getHCombFlag());
			resTranHeader.setHSvcInfo(s01501030000009Response.getResTranHeader().getHSvcInfo());
			resTranHeader.setHSecInfoVerNo(s01501030000009Response.getResTranHeader().getHSecInfoVerNo());
			resTranHeader.setHMsgRefNo(s01501030000009Response.getResTranHeader().getHMsgRefNo());
			resTranHeader.setHIdentFlag(s01501030000009Response.getResTranHeader().getHIdentFlag());
			resTranHeader.setHSuperFlag(s01501030000009Response.getResTranHeader().getHSuperFlag());
			resTranHeader.setHChkFlag(s01501030000009Response.getResTranHeader().getHChkFlag());
			resTranHeader.setHChkTxnCd(s01501030000009Response.getResTranHeader().getHChkTxnCd());
			resTranHeader.setHVerfCd(s01501030000009Response.getResTranHeader().getHVerfCd());
			resTranHeader.setHTranRes(s01501030000009Response.getResTranHeader().getHTranRes());
			resTranHeader.setHRefTxnCd(s01501030000009Response.getResTranHeader().getHRefTxnCd());
			resTranHeader.setHServerDt(s01501030000009Response.getResTranHeader().getHServerDt());
			resTranHeader.setHServerTm(s01501030000009Response.getResTranHeader().getHServerTm());
			resTranHeader.setHServerSeq(s01501030000009Response.getResTranHeader().getHServerSeq());
			resTranHeader.setHAcountDt(s01501030000009Response.getResTranHeader().getHAcountDt());
			resTranHeader.setHRefSeq(s01501030000009Response.getResTranHeader().getHRefSeq());
			resTranHeader.setHRefDt(s01501030000009Response.getResTranHeader().getHRefDt());
			resTranHeader.setHNextStep(s01501030000009Response.getResTranHeader().getHNextStep());
			resTranHeader.setHVchChk(s01501030000009Response.getResTranHeader().getHVchChk());
			resTranHeader.setHRetResInfo(s01501030000009Response.getResTranHeader().getHRetResInfo());
			resTranHeader.setHErrTranNo(s01501030000009Response.getResTranHeader().getHErrTranNo());
			resTranHeader.setHAssiInfo(s01501030000009Response.getResTranHeader().getHAssiInfo());
			resTranHeader.setHRetCode(s01501030000009Response.getResTranHeader().getHRetCode());
			resTranHeader.setHRetNo(s01501030000009Response.getResTranHeader().getHRetNo());
			resTranHeader.setHRetMsg(s01501030000009Response.getResTranHeader().getHRetMsg());
			resTranHeader.setHWarnMsg(s01501030000009Response.getResTranHeader().getHWarnMsg());

			s01501030000009ResBody.setErrMsg(s01501030000009Response.getResponseBody().getErrMsg());
			s01501030000009ResBody.setTransStat(s01501030000009Response.getResponseBody().getTransStat());
			s01501030000009ResBody.setKnotBusiNo(s01501030000009Response.getResponseBody().getKnotBusiNo());

			s01501030000009Res.setResHeader(responseHeader);
			s01501030000009Res.setResTranHeader(resTranHeader);
			s01501030000009Res.setGjS01501030000009ResBody(s01501030000009ResBody);
			// 成功
			if ("AAAAAAA".equals(s01501030000009Response.getResTranHeader().getHRetCode())) {
				System.out.println("调用【S01501030000009】融资展期接口成功，流水号为: " + s01501030000009ReqHeader.getReqSeqNo());
			
			} else {// 失败
				
				System.out.println("调用【S01501030000009】融资展期接口失败，流水号为: " + s01501030000009ReqHeader.getReqSeqNo() + "失败原因:" + s01501030000009Response.getResTranHeader().getHRetMsg());
			}
		} catch (Exception e) {// 异常
			try {
				if (e.getMessage().equals("The input stream for an incoming message is null.")) {
					throw new Exception("调用【S01501030000009】融资展期接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + s01501030000009ReqHeader.getReqSeqNo());
				} else {
					throw new Exception("调用【S01501030000009】融资展期接口失败：" + e.getMessage() + "ESB流水号: " + s01501030000009ReqHeader.getReqSeqNo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return s01501030000009Res;
	}

	// 牌价查询接口---暂时不做
	@Bizlet("")
	public GJS01501010000010Res executeS01501010000010(GJS01501010000010Req gjS01501010000010Req) {
		return null;
	}

	// 国结业务表外业务放款状态查询接口---暂时不做
	@Bizlet("")
	public GJS01501010000011Res executeS01501010000011(GJS01501010000011Req gjS01501010000011Req) {
		return null;
	}
}
