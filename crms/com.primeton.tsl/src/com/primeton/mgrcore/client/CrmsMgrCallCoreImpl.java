package com.primeton.mgrcore.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.engine.ListenerManager;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.mgrcore.F11231;
import com.primeton.mgrcore.F76091;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.FXD012;
import com.primeton.mgrcore.FXD091;
import com.primeton.mgrcore.FXD092;
import com.primeton.mgrcore.FXD151;
import com.primeton.mgrcore.IXD15AccountInfo;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012ResBody;
import com.primeton.mgrcore.OXD012ResInfo;
import com.primeton.mgrcore.OXD012ResInfoRec;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.OXD015ResBody;
import com.primeton.mgrcore.OXD041_SuccessFlagChkReq;
import com.primeton.mgrcore.OXD042Info;
import com.primeton.mgrcore.OXD042ResBody;
import com.primeton.mgrcore.OXD042_SuccessFlagChkRes;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052ResBody;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.OXD061_OutFlushesReq;
import com.primeton.mgrcore.OXD06ResBody;
import com.primeton.mgrcore.OXD06_OutFlushesRes;
import com.primeton.mgrcore.OXD071_AccControlReq;
import com.primeton.mgrcore.OXD072ResBody;
import com.primeton.mgrcore.OXD072_AccControlRes;
import com.primeton.mgrcore.OXD081_CustAccInfoQryReq;
import com.primeton.mgrcore.OXD082ResBody;
import com.primeton.mgrcore.OXD082_CustAccInfoQryRes;
import com.primeton.mgrcore.OXD091_PawnInOutReq;
import com.primeton.mgrcore.OXD092ResBody;
import com.primeton.mgrcore.OXD092_PawnInOutRes;
import com.primeton.mgrcore.OXD11ResBody;
import com.primeton.mgrcore.OXD11_CdzykhReq;
import com.primeton.mgrcore.OXD11_CdzykhRes;
import com.primeton.mgrcore.OXD15AccountInfo;
import com.primeton.mgrcore.ResTranHeader;
import com.primeton.mgrcore.ResponseHeader;
import com.primeton.mgrcore.S0030101000XD05ServiceStub;
import com.primeton.mgrcore.S0030101000XD05ServiceStub.FMT_CRMS_SVR_S0030101000XD05_OUT_SUB_F11231;
import com.primeton.mgrcore.S0030101000XD06ServiceStub;
import com.primeton.mgrcore.S0030101000XD07ServiceStub;
import com.primeton.mgrcore.S0030101000XD08ServiceStub;
import com.primeton.mgrcore.S0030101000XD15ServiceStub;
import com.primeton.mgrcore.S0030101000XD15ServiceStub.FMT_CRMS_SVR_S0030101000XD15_OUT_SUB;
import com.primeton.mgrcore.S0030103000XD01ServiceStub;
import com.primeton.mgrcore.S0030103000XD01ServiceStub.FMT_CRMS_SVR_S0030103000XD01_IN_SUB;
import com.primeton.mgrcore.S0030103000XD01ServiceStub.FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB;
import com.primeton.mgrcore.S0030103000XD01ServiceStub.FMT_CRMS_SVR_S0030103000XD01_OUT_SUB;
import com.primeton.mgrcore.S0030103000XD11ServiceStub;
import com.primeton.mgrcore.S0030199000XD04ServiceStub;
import com.primeton.mgrcore.S0030199000XD04ServiceStub.FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091;
import com.primeton.mgrcore.S0030199000XD09ServiceStub;
import com.primeton.mgrcore.S0030199000XD09ServiceStub.FMT_CRMS_SVR_S0030199000XD09_IN_FXD091;
import com.primeton.mgrcore.S0030199000XD09ServiceStub.FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092;

/**
 * 信贷管理调用核心接口
 * （RecNum、InfoNum为请求/响应对象中循环字段体的计数）
 * @author MJF
 *
 */
@Bizlet("CrmsMgrCallCoreImpl")
public class CrmsMgrCallCoreImpl implements CrmsMgrCallCoreProxy {
	

	private String getUrl(){
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
	
	/** 【XD01】通用记账(8661) */
	@Bizlet("通用记账")
	public OXD012_AccoutingRes executeXD01(OXD011_AccoutingReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030103000XD01";
		String url = getUrl() + zservice;
		S0030103000XD01ServiceStub.FMT_SOAP_UTF8_RequestHeader XD01ReqHeader = new S0030103000XD01ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030103000XD01ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD01ReqTranHeader = new S0030103000XD01ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030103000XD01ServiceStub.FMT_CRMS_SVR_S0030103000XD01_IN XD01ReqBody = new S0030103000XD01ServiceStub.FMT_CRMS_SVR_S0030103000XD01_IN();
		// ESB对应版本号填写规范，哪个环境就填哪个值：测试环境ESB_SIT  ESB_UAT    开发环境ESB_DEV   验证环境ESB_VERIFY   上线填写值ESB_ONLINE
		XD01ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD01ReqHeader.setReqSysCode("01601");
		XD01ReqHeader.setReqSecCode("");
		XD01ReqHeader.setTxType("RQ");
		// 0-正常 1-冲销 2-冲正 3-重发
		XD01ReqHeader.setTxMode("0");
		XD01ReqHeader.setTxCode("S0030103000XD01");
		XD01ReqHeader.setReqDate(DateTools.getTime8());
		XD01ReqHeader.setReqTime(DateTools.getTime20());
		XD01ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD01ReqHeader.setChanlNo("48");
		XD01ReqHeader.setBrch(requestBody.getHxorg());
		XD01ReqHeader.setTermNo("");
		XD01ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getHxorg()));
		XD01ReqHeader.setSendFileName("");
		XD01ReqHeader.setBeginRec("");
		XD01ReqHeader.setMaxRec(null);
		XD01ReqHeader.setFileHMac("");
		XD01ReqHeader.setHMac("");
	
		XD01ReqTranHeader.setHPinSeed("null");
		XD01ReqTranHeader.setHOriChnl("601");
		XD01ReqTranHeader.setHSecFlag("0");
		XD01ReqTranHeader.setHPwdFlag("0");
		XD01ReqTranHeader.setHCombFlag("0");
		XD01ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD01ReqTranHeader.setHSecInfoVerNo("null");
		XD01ReqTranHeader.setHSysChnl("48");
		XD01ReqTranHeader.setHLegaObj("9999");
		XD01ReqTranHeader.setHMsgRefNo("");
		XD01ReqTranHeader.setHTermNo("");
		XD01ReqTranHeader.setHCityCd("");
		XD01ReqTranHeader.setHBrchNo(requestBody.getHxorg());
		XD01ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getHxorg()));
		XD01ReqTranHeader.setHTxnCd("XD01");
		XD01ReqTranHeader.setHTxnMod("");
		XD01ReqTranHeader.setHReserveLen("");
		XD01ReqTranHeader.setHSenderSvcCd("");
		XD01ReqTranHeader.setHSenderSeq(requestBody.getChargeSeq());
		XD01ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD01ReqTranHeader.setHAuthUserID("");
		XD01ReqTranHeader.setHAuthVerfInfo("");
		XD01ReqTranHeader.setHAuthFlag("");
		XD01ReqTranHeader.setHRefSeq("");
		XD01ReqTranHeader.setHAuthSeri("");
		XD01ReqTranHeader.setHHostSeq("");
		XD01ReqTranHeader.setHRefDt("");
		XD01ReqTranHeader.setHSvcVer("");
		XD01ReqTranHeader.setHreserveMsg("");
		XD01ReqTranHeader.setHintOrigMark(null);
		
		XD01ReqBody.setChargeSeq(requestBody.getChargeSeq());
		XD01ReqBody.setOutSystemDate(requestBody.getOutSystemDate()); 
		XD01ReqBody.setBusiType1(requestBody.getBusiType1()); 
		XD01ReqBody.setUnitNo(requestBody.getUnitNo()); 
		XD01ReqBody.setLotNum(requestBody.getLotNum()); 
		XD01ReqBody.setAmount(requestBody.getAmount()); 
		XD01ReqBody.setThridTransCode(requestBody.getThridTransCode()); 
		XD01ReqBody.setRecNum(requestBody.getRecNum()); 
		XD01ReqBody.setSummaryCode(requestBody.getSummaryCode()); 
		XD01ReqBody.setSummaryDescription(requestBody.getSummaryDescription()); 
		XD01ReqBody.setRemarkInfo(requestBody.getRemarkInfo()); 
		FMT_CRMS_SVR_S0030103000XD01_IN_SUB[] fxd011SubArray;
		if (requestBody.getRecNum() == null || (requestBody.getRecNum()).intValue() <= 0) {
			fxd011SubArray = new FMT_CRMS_SVR_S0030103000XD01_IN_SUB[1];
			System.out.println("调用【S0030103000XD01】请求中分录数[RecNum]为0");
		} else {
			fxd011SubArray = new FMT_CRMS_SVR_S0030103000XD01_IN_SUB[(XD01ReqBody.getRecNum()).intValue()];
		}
		for (int i = 0; i < fxd011SubArray.length; i++) {
			FMT_CRMS_SVR_S0030103000XD01_IN_SUB fxd011Sub = new FMT_CRMS_SVR_S0030103000XD01_IN_SUB();
			FXD011[] fxd011s = requestBody.getFxd011();
			FXD011 fxd011 = fxd011s[i];
			fxd011Sub.setDealType(fxd011.getDealType());
			fxd011Sub.setDrCrFlag(fxd011.getDrCrFlag());
			fxd011Sub.setCurrCode(fxd011.getCurrCode());
			fxd011Sub.setCashFlag(fxd011.getCashFlag());
			fxd011Sub.setTransAmt(fxd011.getTransAmt());
			fxd011Sub.setAcctFromGo(fxd011.getAcctFromGo());
			fxd011Sub.setAcct(fxd011.getAcct());
			fxd011Sub.setAcctName(fxd011.getAcctName());
			fxd011Sub.setAcctSeq(fxd011.getAcctSeq());
			fxd011Sub.setChargeBrch(fxd011.getChargeBrch());
			fxd011Sub.setChargeBusiCode(fxd011.getChargeBusiCode());
			fxd011Sub.setChargeBusiType(fxd011.getChargeBusiType());
			fxd011Sub.setRolloutWriteoffSeq(fxd011.getRolloutWriteoffSeq());
			fxd011Sub.setCshProCode(fxd011.getCshProCode());
			fxd011Sub.setPwdKind(fxd011.getPwdKind());
			fxd011Sub.setTransPassWord(fxd011.getTransPassWord());
			fxd011Sub.setVchKind(fxd011.getVchKind());
			fxd011Sub.setVchBatNo(fxd011.getVchBatNo());
			fxd011Sub.setVchSerialNo(fxd011.getVchSerialNo());
			fxd011Sub.setPayPwd(fxd011.getPayPwd());
			fxd011Sub.setDrawDate(fxd011.getDrawDate());
			fxd011Sub.setIssueBankNo(fxd011.getIssueBankNo());
			fxd011Sub.setSndTrak(fxd011.getSndTrak());
			fxd011Sub.setThrTrak(fxd011.getThrTrak());
			fxd011Sub.setSignPassFlag(fxd011.getSignPassFlag());
			fxd011Sub.setCertType(fxd011.getCertType());
			fxd011Sub.setCertNo(fxd011.getCertNo());
			fxd011Sub.setVertLastboxSignFlag(fxd011.getVertLastboxSignFlag());
			fxd011Sub.setVertSignBelongOper(fxd011.getVertSignBelongOper());
			fxd011Sub.setVchKind1(fxd011.getVchKind1());
			fxd011Sub.setVchNo(fxd011.getVchNo());
			fxd011Sub.setVchBatNo1(fxd011.getVchBatNo1());
			fxd011Sub.setVchSerialNo1(fxd011.getVchSerialNo1());
			fxd011Sub.setOtherAcct(fxd011.getOtherAcct());
			fxd011Sub.setOtherNam(fxd011.getOtherNam());
			fxd011Sub.setOrganNam(fxd011.getOrganNam());
			fxd011Sub.setOthBankBrchType(fxd011.getOthBankBrchType());
			fxd011Sub.setOthBankBrchCode(fxd011.getOthBankBrchCode());
			fxd011Sub.setAgentName(fxd011.getAgentName());
			fxd011Sub.setAgentPaperType(fxd011.getAgentPaperType());
			fxd011Sub.setAgntCertNum(fxd011.getAgntCertNum());
			fxd011Sub.setFeePayType(fxd011.getFeePayType());
			fxd011Sub.setOrigBusiNo(fxd011.getOrigBusiNo());
			fxd011Sub.setOrigTxDate(fxd011.getOrigTxDate());
			fxd011Sub.setOldOperSeq(fxd011.getOldOperSeq());
			fxd011Sub.setPoundageAmtFrom(fxd011.getPoundageAmtFrom());
			fxd011Sub.setFeeAcct(fxd011.getFeeAcct());
			fxd011Sub.setChargeAcctSeq(fxd011.getChargeAcctSeq());
			fxd011Sub.setFeeEveNo(fxd011.getFeeEveNo());
			fxd011Sub.setChargeAmt(fxd011.getChargeAmt());
			fxd011Sub.setEnoughFlag(fxd011.getEnoughFlag());
			fxd011SubArray[i] = fxd011Sub;
		}
		XD01ReqBody.setRecMsg(fxd011SubArray);
		
		S0030103000XD01ServiceStub.S0030103000XD01Response XD01Response = new S0030103000XD01ServiceStub.S0030103000XD01Response();
		OXD012_AccoutingRes OXD012Res = new OXD012_AccoutingRes();
		OXD012ResBody OXD012ResBody = new OXD012ResBody();
		OXD012ResInfo oxd012ResInfo= new OXD012ResInfo();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030103000XD01ServiceStub stub = new S0030103000XD01ServiceStub(url);
			S0030103000XD01ServiceStub.S0030103000XD01 service = new S0030103000XD01ServiceStub.S0030103000XD01();
			service.setRequestHeader(XD01ReqHeader);
			service.setReqTranHeader(XD01ReqTranHeader);
			service.setRequestBody(XD01ReqBody);
			System.out.println("发送核心流水号:"+requestBody.getChargeSeq()+"-对账文件类型:"+requestBody.getRemarkInfo()+"--发生额:"+requestBody.getAmount());
			XD01Response = stub.S0030103000XD01(service);
			GitUtil.getNumTimes(requestBody.getHxorg());
			if (XD01Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030103000XD01】通用记账接口成功，流水号： " + XD01ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030103000XD01】通用记账接口成功，核心前台流水号： " + XD01ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD01Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD01Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD01Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD01Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD01Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD01Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD01Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD01Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD01Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD01Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD01Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD01Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD01Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD01Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD01Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD01Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD01Response.getResponseHeader().getHMac());

				resTranHeader.setHSecFlag(XD01Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD01Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD01Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD01Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD01Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD01Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD01Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD01Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD01Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD01Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD01Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD01Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD01Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD01Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD01Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD01Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD01Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD01Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD01Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD01Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD01Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD01Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD01Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD01Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD01Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD01Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD01Response.getResTranHeader().getHWarnMsg());
				
				//响应体赋值
				OXD012ResBody.setHostTransDate(XD01Response.getResponseBody().getHostTransDate());
				OXD012ResBody.setHostOperSeq(XD01Response.getResponseBody().getHostOperSeq());
				OXD012ResBody.setHostTransCode(XD01Response.getResponseBody().getHostTransCode());
				OXD012ResBody.setBusiCode(XD01Response.getResponseBody().getBusiCode());
				OXD012ResBody.setRolloutWrtOffSeq(XD01Response.getResponseBody().getRolloutWrtOffSeq());
				OXD012ResBody.setPrmMsg(XD01Response.getResponseBody().getPrmMsg());
				OXD012ResBody.setRecNum(XD01Response.getResponseBody().getRecNum());
				//响应体中RecMsg循环对象赋值
				FMT_CRMS_SVR_S0030103000XD01_OUT_SUB[] fxd012SubArray;
				if (XD01Response.getResponseBody().getRecNum() == null || (XD01Response.getResponseBody().getRecNum()).intValue() <= 0) {
					fxd012SubArray = new FMT_CRMS_SVR_S0030103000XD01_OUT_SUB[1];
					System.out.println("调用【S0030103000XD01】响应中[RecMsg]循环记录数为空");
				}else{
					fxd012SubArray = new FMT_CRMS_SVR_S0030103000XD01_OUT_SUB[(XD01Response.getResponseBody().getRecNum()).intValue()];
				}
				if(null == XD01Response.getResponseBody().getRecMsg()){
					FMT_CRMS_SVR_S0030103000XD01_OUT_SUB s003 = new FMT_CRMS_SVR_S0030103000XD01_OUT_SUB();
					fxd012SubArray[0] = s003;
				}else{
					fxd012SubArray = XD01Response.getResponseBody().getRecMsg();
				}
			
				List<FXD012> fxd012List = new ArrayList<FXD012>();
				for (int i = 0; i < fxd012SubArray.length; i++) {
					FMT_CRMS_SVR_S0030103000XD01_OUT_SUB fxd012Sub = new FMT_CRMS_SVR_S0030103000XD01_OUT_SUB();
					fxd012Sub = fxd012SubArray[i];
					FXD012 fxd012 = new FXD012();
					fxd012.setDrCrFlag(fxd012Sub.getDrCrFlag());
					fxd012.setTransAmt(fxd012Sub.getTransAmt());
					fxd012.setAcctFromGo(fxd012Sub.getAcctFromGo());
					fxd012.setAcct(fxd012Sub.getAcct());
					fxd012.setAcctName(fxd012Sub.getAcctName());
					fxd012.setAcctSeq(fxd012Sub.getAcctSeq());
					fxd012.setChargeBrch(fxd012Sub.getChargeBrch());
					fxd012.setChargeBusiCode(fxd012Sub.getChargeBusiCode());
					fxd012.setChargeBusiType(fxd012Sub.getChargeBusiType());
					fxd012.setRolloutWriteoffSeq(fxd012Sub.getRolloutWriteoffSeq());
					fxd012.setBackup1(fxd012Sub.getBackup1());
					fxd012.setBackup2(fxd012Sub.getBackup2());
					fxd012.setBackup3(fxd012Sub.getBackup3());
					fxd012List.add(fxd012);
				}
				OXD012ResBody.setFxd012(fxd012List);
				//响应体中Info循环对象赋值
				if(null !=XD01Response.getResponseBody().getInfo()){
					oxd012ResInfo.setTransName(XD01Response.getResponseBody().getInfo().getTransName());
					oxd012ResInfo.setCustNo(XD01Response.getResponseBody().getInfo().getCustNo());
					oxd012ResInfo.setAcct(XD01Response.getResponseBody().getInfo().getAcct());
					oxd012ResInfo.setCustChnName(XD01Response.getResponseBody().getInfo().getCustChnName());
					oxd012ResInfo.setFeeCny(XD01Response.getResponseBody().getInfo().getFeeCny());
					oxd012ResInfo.setCostPayFlg(XD01Response.getResponseBody().getInfo().getCostPayFlg());
					oxd012ResInfo.setTotFee(XD01Response.getResponseBody().getInfo().getTotFee());
					oxd012ResInfo.setCshOrTran(XD01Response.getResponseBody().getInfo().getCshOrTran());
					oxd012ResInfo.setSummaryDescription(XD01Response.getResponseBody().getInfo().getSummaryDescription());
					oxd012ResInfo.setRemarkInfo(XD01Response.getResponseBody().getInfo().getRemarkInfo());
					oxd012ResInfo.setInfoNum(XD01Response.getResponseBody().getInfo().getInfoNum());
					//Info循环对象中InfoRec循环对象赋值
					FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB[] oxd012ResInfoRecSubArray;
					if (null == XD01Response.getResponseBody().getInfo().getInfoNum() || (XD01Response.getResponseBody().getInfo().getInfoNum()).intValue() <= 0) {
						oxd012ResInfoRecSubArray = new FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB[1];
						System.out.println("调用【S0030103000XD01】响应中[InfoRec]循环记录数为0");
					} else {
						oxd012ResInfoRecSubArray = new FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB[(XD01Response.getResponseBody().getInfo().getInfoNum()).intValue()];
					}
					oxd012ResInfoRecSubArray = XD01Response.getResponseBody().getInfo().getInfoRec() ;
					List<OXD012ResInfoRec> oxd012ResInfoRecList = new ArrayList<OXD012ResInfoRec>();
					for (int i = 0; i < oxd012ResInfoRecSubArray.length; i++) {
						FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB resInfoRecSub = new FMT_CRMS_SVR_S0030103000XD01_OUT1_SUB();
						resInfoRecSub = oxd012ResInfoRecSubArray[i];
						OXD012ResInfoRec oxd012ResInfoRec = new OXD012ResInfoRec();
						oxd012ResInfoRec.setRataKind(resInfoRecSub.getRataKind());
						oxd012ResInfoRec.setRataNme(resInfoRecSub.getRataNme());
						oxd012ResInfoRec.setFeeAmt(resInfoRecSub.getFeeAmt());
						oxd012ResInfoRec.setCostPayFlg(resInfoRecSub.getCostPayFlg());
						oxd012ResInfoRec.setPatRecFlg(resInfoRecSub.getPatRecFlg());
						oxd012ResInfoRec.setOweAmt(resInfoRecSub.getOweAmt());
						oxd012ResInfoRec.setEveMarkNo(resInfoRecSub.getEveMarkNo());
						oxd012ResInfoRec.setCostCode(resInfoRecSub.getCostCode());
						oxd012ResInfoRec.setCostExps(resInfoRecSub.getCostExps());
						oxd012ResInfoRec.setBusiNoNm(resInfoRecSub.getBusiNoNm());
						oxd012ResInfoRec.setSubjectNo(resInfoRecSub.getSubjectNo());
						oxd012ResInfoRecList.add(oxd012ResInfoRec);
					}
					oxd012ResInfo.setOxd012ResInfoRec(oxd012ResInfoRecList);
				}
				
				
				OXD012ResBody.setOxd012ResInfo(oxd012ResInfo);

				OXD012Res.setResHeader(responseHeader);
				OXD012Res.setResTranHeader(resTranHeader);
				OXD012Res.setOxd012ResBody(OXD012ResBody);
				
				
			} else {
				resTranHeader.setHRetCode(XD01Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD01Response.getResTranHeader().getHRetMsg());
				OXD012Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030103000XD01】通用记账接口处理出错，出错信息： " + XD01Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030103000XD01】通用记账接口处理出错，ESB响应码： " + XD01Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030103000XD01】通用记账接口处理出错，ESB流水号： " + XD01ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030103000XD01】通用记账接口处理出错，核心前台流水号： " + XD01ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030103000XD01】通用记账接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD01ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD01ReqTranHeader.getHSenderSeq());
			}else {
				throw new Exception("调用【S0030103000XD01】通用记账接口失败：" + e.getMessage() + "ESB流水号: " + XD01ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD01ReqTranHeader.getHSenderSeq());
			}
		}
		return OXD012Res;
	}
	
	/** 【XD04】交易状态成功性检查接口OXD041 */
	public OXD042_SuccessFlagChkRes executeXD04(OXD041_SuccessFlagChkReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030199000XD04";
		String url = getUrl() + zservice;
		S0030199000XD04ServiceStub.FMT_SOAP_UTF8_RequestHeader XD04ReqHeader = new S0030199000XD04ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030199000XD04ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD04ReqTranHeader = new S0030199000XD04ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030199000XD04ServiceStub.FMT_CRMS_SVR_S0030199000XD04_IN XD04ReqBody = new S0030199000XD04ServiceStub.FMT_CRMS_SVR_S0030199000XD04_IN();
		
		XD04ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD04ReqHeader.setReqSysCode("01601");
		XD04ReqHeader.setReqSecCode("");
		XD04ReqHeader.setTxType("RQ");
		XD04ReqHeader.setTxMode("0");
		XD04ReqHeader.setTxCode("S0030101000XD04");
		XD04ReqHeader.setReqDate(DateTools.getTime8());
		XD04ReqHeader.setReqTime(DateTools.getTime20());
		XD04ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD04ReqHeader.setChanlNo("48");
		XD04ReqHeader.setBrch(requestBody.getSaleBrch());
		XD04ReqHeader.setTermNo("");
		XD04ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getSaleBrch()));
		
		XD04ReqHeader.setSendFileName("");
		XD04ReqHeader.setBeginRec("");
		XD04ReqHeader.setMaxRec(null);
		XD04ReqHeader.setFileHMac("");
		XD04ReqHeader.setHMac("");
	
		XD04ReqTranHeader.setHPinSeed("null");
		XD04ReqTranHeader.setHOriChnl("601");
		XD04ReqTranHeader.setHSecFlag("0");
		XD04ReqTranHeader.setHPwdFlag("0");
		XD04ReqTranHeader.setHCombFlag("0");
		XD04ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD04ReqTranHeader.setHSecInfoVerNo("null");
		XD04ReqTranHeader.setHSysChnl("48");
		XD04ReqTranHeader.setHLegaObj("9999");
		XD04ReqTranHeader.setHMsgRefNo("");
		XD04ReqTranHeader.setHTermNo("");
		XD04ReqTranHeader.setHCityCd("");
		XD04ReqTranHeader.setHBrchNo(requestBody.getSaleBrch());
		XD04ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getSaleBrch()));
		
		XD04ReqTranHeader.setHTxnCd("XD04");
		XD04ReqTranHeader.setHTxnMod("");
		XD04ReqTranHeader.setHReserveLen("");
		XD04ReqTranHeader.setHSenderSvcCd("");
		XD04ReqTranHeader.setHSenderSeq(requestBody.getForegroundSeq());
		XD04ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD04ReqTranHeader.setHAuthUserID("");
		XD04ReqTranHeader.setHAuthVerfInfo("");
		XD04ReqTranHeader.setHAuthFlag("");
		XD04ReqTranHeader.setHRefSeq("");
		XD04ReqTranHeader.setHAuthSeri("");
		XD04ReqTranHeader.setHHostSeq("");
		XD04ReqTranHeader.setHRefDt("");
		XD04ReqTranHeader.setHSvcVer("");
		XD04ReqTranHeader.setHreserveMsg("");
		XD04ReqTranHeader.setHintOrigMark(null);
		
		XD04ReqBody.setSearchPrintFlag(requestBody.getSearchPrintFlag());
		XD04ReqBody.setTransDate(requestBody.getTransDate());
		XD04ReqBody.setTransCode(requestBody.getTransCode());
		XD04ReqBody.setTransOper(requestBody.getTransOper());
		XD04ReqBody.setIdentifier(requestBody.getIdentifier());
		XD04ReqBody.setOperSeq(requestBody.getOperSeq());
		XD04ReqBody.setFrontDate(requestBody.getFrontDate());
		XD04ReqBody.setForegroundSeq(requestBody.getForegroundSeq());
		XD04ReqBody.setCorrectFlag(requestBody.getCorrectFlag());
		XD04ReqBody.setHostDate(requestBody.getHostDate());
		XD04ReqBody.setBegNum(requestBody.getBegNum());
		XD04ReqBody.setSearchNum(requestBody.getSearchNum());     
		
		S0030199000XD04ServiceStub.S0030199000XD04Response XD04Response = new S0030199000XD04ServiceStub.S0030199000XD04Response();
		OXD042_SuccessFlagChkRes OXD042Res = new OXD042_SuccessFlagChkRes();
		OXD042ResBody OXD042ResBody = new OXD042ResBody();
		OXD042Info info = new OXD042Info();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030199000XD04ServiceStub stub = new S0030199000XD04ServiceStub(url);
			S0030199000XD04ServiceStub.S0030199000XD04 service = new S0030199000XD04ServiceStub.S0030199000XD04();
			service.setRequestHeader(XD04ReqHeader);
			service.setReqTranHeader(XD04ReqTranHeader);
			service.setRequestBody(XD04ReqBody);
			XD04Response = stub.S0030199000XD04(service);
			GitUtil.getNumTimes(requestBody.getSaleBrch());
			if (XD04Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口成功，ESB流水号： " + XD04ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口成功，核心前台流水号： " + XD04ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD04Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD04Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD04Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD04Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD04Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD04Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD04Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD04Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD04Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD04Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD04Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD04Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD04Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD04Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD04Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD04Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD04Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD04Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD04Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD04Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD04Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD04Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD04Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD04Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD04Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD04Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD04Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD04Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD04Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD04Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD04Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD04Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD04Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD04Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD04Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD04Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD04Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD04Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD04Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD04Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD04Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD04Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD04Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD04Response.getResTranHeader().getHWarnMsg());

				OXD042ResBody.setTxCount(XD04Response.getResponseBody().getTxCount());
				FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091[] fxd042SubArray;
				if (XD04Response.getResponseBody().getTxCount() == null || (Integer.parseInt(XD04Response.getResponseBody().getTxCount()) <= 0)) {
					fxd042SubArray = new FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091[1];
					System.out.println("调用【S0030199000XD04】响应中[TxCount]笔数为0");
				}else {
					fxd042SubArray = new FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091[Integer.parseInt(XD04Response.getResponseBody().getTxCount())];
				}
				fxd042SubArray = XD04Response.getResponseBody().getRecMsg();
				List<F76091> f76091List = new ArrayList<F76091>();
				for (int i = 0; i < fxd042SubArray.length; i++) {
					FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091 fxd042Sub = new FMT_CRMS_SVR_S0030199000XD04_OUT_SUB_F76091();
					fxd042Sub = fxd042SubArray[i];
					F76091 f76091 = new F76091();
					f76091.setTransDate(fxd042Sub.getTransDate());
					f76091.setTransTime(fxd042Sub.getTransTime());
					f76091.setTransCode(fxd042Sub.getTransCode());
					f76091.setSaleBrch(fxd042Sub.getSaleBrch());
					f76091.setAffairsBrchNo(fxd042Sub.getAffairsBrchNo());
					f76091.setChnl(fxd042Sub.getChnl());
					f76091.setTermNo(fxd042Sub.getTermNo());
					f76091.setTransOper(fxd042Sub.getTransOper());
					f76091.setAuthCter(fxd042Sub.getAuthCter());
					f76091.setOperSeq(fxd042Sub.getOperSeq());
					f76091.setTransSeqType(fxd042Sub.getTransSeqType());
					f76091.setFrontDate(fxd042Sub.getFrontDate());
					f76091.setFrontSeqNo(fxd042Sub.getFrontSeqNo());
					f76091.setAgntServNum(fxd042Sub.getAgntServNum());
					f76091.setTransReconType(fxd042Sub.getTransReconType());
					f76091.setTransName(fxd042Sub.getTransName());
					f76091.setCorrectFlag(fxd042Sub.getCorrectFlag());
					f76091.setOrigTellerSeq(fxd042Sub.getOrigTellerSeq());
					f76091.setHostDate(fxd042Sub.getHostDate());
					f76091.setTime(fxd042Sub.getTime());
					f76091.setPosCode(fxd042Sub.getPosCode());
					f76091.setRechkDate(fxd042Sub.getRechkDate());
					f76091.setApplyRechkSeq(fxd042Sub.getApplyRechkSeq());
					f76091List.add(f76091);
				}
				OXD042ResBody.setF76091(f76091List);
				info.setReportPath(XD04Response.getResponseBody().getInfo().getReportPath());
				OXD042ResBody.setInfo(info);
				
				OXD042Res.setResHeader(responseHeader);
				OXD042Res.setResTranHeader(resTranHeader);
				OXD042Res.setOxd042ResBody(OXD042ResBody);
			} else {
				resTranHeader.setHRetCode(XD04Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD04Response.getResTranHeader().getHRetMsg());
				OXD042Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口处理出错，处理结果： " + XD04Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口处理出错，ESB响应码： " + XD04Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口处理出错，ESB流水号： " + XD04ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030199000XD04】交易状态成功性检查接口处理出错，核心前台流水号： " + XD04ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030199000XD04】交易状态成功性检查接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD04ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD04ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030199000XD04】交易状态成功性检查接口失败："+ e.getMessage() + "ESB流水号: "+ XD04ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD04ReqTranHeader.getHSenderSeq());
			}
		}
		return OXD042Res;
	}
	
	/** 【XD05】账户信息查询(1224) 输出接口OXD052 */
	@Bizlet("账户信息查询")
	public OXD052_AccInfoQryRes executeXD05(OXD051_AccInfoQryReq requestBody) throws Exception {
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

	/** 【XD06】外围冲正(对应撤销) 输出接口OOTHER */
	@Bizlet("外围冲正")
	public OXD06_OutFlushesRes executeXD06(OXD061_OutFlushesReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030101000XD06";
		String url = getUrl() + zservice;
		S0030101000XD06ServiceStub.FMT_SOAP_UTF8_RequestHeader XD06ReqHeader = new S0030101000XD06ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030101000XD06ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD06ReqTranHeader = new S0030101000XD06ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030101000XD06ServiceStub.FMT_CRMS_SVR_S0030101000XD06_IN XD06ReqBody = new S0030101000XD06ServiceStub.FMT_CRMS_SVR_S0030101000XD06_IN();
		
		XD06ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD06ReqHeader.setReqSysCode("01601");
		XD06ReqHeader.setReqSecCode("");
		XD06ReqHeader.setTxType("RQ");
		XD06ReqHeader.setTxMode("2");
		XD06ReqHeader.setTxCode("S0030101000XD06");
		XD06ReqHeader.setReqDate(DateTools.getTime8());
		XD06ReqHeader.setReqTime(DateTools.getTime20());
		XD06ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD06ReqHeader.setChanlNo("48");
		XD06ReqHeader.setBrch(requestBody.getOrgNum());
		XD06ReqHeader.setTermNo("");
		XD06ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD06ReqHeader.setSendFileName("");
		XD06ReqHeader.setBeginRec("");
		XD06ReqHeader.setMaxRec(null);
		XD06ReqHeader.setFileHMac("");
		XD06ReqHeader.setHMac("");
	
		XD06ReqTranHeader.setHPinSeed("null");
		XD06ReqTranHeader.setHOriChnl("601");
		XD06ReqTranHeader.setHSecFlag("0");
		XD06ReqTranHeader.setHPwdFlag("0");
		XD06ReqTranHeader.setHCombFlag("0");
		XD06ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD06ReqTranHeader.setHSecInfoVerNo("null");
		XD06ReqTranHeader.setHSysChnl("48");
		XD06ReqTranHeader.setHLegaObj("9999");
		XD06ReqTranHeader.setHMsgRefNo("");
		XD06ReqTranHeader.setHTermNo("");
		XD06ReqTranHeader.setHCityCd("");
		XD06ReqTranHeader.setHBrchNo(requestBody.getOrgNum());
		XD06ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD06ReqTranHeader.setHTxnCd("XD06");
		XD06ReqTranHeader.setHTxnMod("");
		XD06ReqTranHeader.setHReserveLen("");
		XD06ReqTranHeader.setHSenderSvcCd("");
		XD06ReqTranHeader.setHSenderSeq(requestBody.getTranCode());
		XD06ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD06ReqTranHeader.setHAuthUserID("");
		XD06ReqTranHeader.setHAuthVerfInfo("");
		XD06ReqTranHeader.setHAuthFlag("");
		XD06ReqTranHeader.setHRefSeq("");
		XD06ReqTranHeader.setHAuthSeri("");
		XD06ReqTranHeader.setHHostSeq("");
		XD06ReqTranHeader.setHRefDt("");
		XD06ReqTranHeader.setHSvcVer("");
		XD06ReqTranHeader.setHreserveMsg("");
		XD06ReqTranHeader.setHintOrigMark(null);
		
		XD06ReqBody.setOldFrontTransDate(requestBody.getOldFrontTransDate());
		XD06ReqBody.setFrontSeqNo(requestBody.getFrontSeqNo());
		XD06ReqBody.setTransCode(requestBody.getTransCode());
		XD06ReqBody.setNextDaySignFlg(requestBody.getNextDaySignFlg());
		XD06ReqBody.setYNPeriCan(requestBody.getYnPeriCan());
		
		S0030101000XD06ServiceStub.S0030101000XD06Response XD06Response = new S0030101000XD06ServiceStub.S0030101000XD06Response();
		OXD06_OutFlushesRes oxd06Res = new OXD06_OutFlushesRes();
		OXD06ResBody oxd06ResBody = new OXD06ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030101000XD06ServiceStub stub = new S0030101000XD06ServiceStub(url);
			S0030101000XD06ServiceStub.S0030101000XD06 service = new S0030101000XD06ServiceStub.S0030101000XD06();
			service.setRequestHeader(XD06ReqHeader);
			service.setReqTranHeader(XD06ReqTranHeader);
			service.setRequestBody(XD06ReqBody);
			XD06Response = stub.S0030101000XD06(service);
			GitUtil.getNumTimes(requestBody.getOrgNum());
			if (XD06Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD06】外围冲正接口成功，ESB流水号： " + XD06ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD06】外围冲正接口成功，核心前台流水号： " + XD06ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD06Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD06Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD06Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD06Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD06Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD06Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD06Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD06Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD06Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD06Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD06Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD06Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD06Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD06Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD06Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD06Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD06Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD06Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD06Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD06Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD06Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD06Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD06Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD06Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD06Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD06Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD06Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD06Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD06Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD06Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD06Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD06Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD06Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD06Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD06Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD06Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD06Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD06Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD06Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD06Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD06Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD06Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD06Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD06Response.getResTranHeader().getHWarnMsg());
				
				oxd06ResBody.setRemarkInfo(XD06Response.getResponseBody().getRemarkInfo());
				
				oxd06Res.setResHeader(responseHeader);
				oxd06Res.setResTranHeader(resTranHeader);
				oxd06Res.setXd06ResBody(oxd06ResBody);
			} else {
				resTranHeader.setHRetCode(XD06Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD06Response.getResTranHeader().getHRetMsg());
				oxd06Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD06】外围冲正接口处理出错，处理结果： " + XD06Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD06】外围冲正接口处理出错，ESB响应码： " + XD06Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD06】外围冲正接口处理出错，ESB流水号： " + XD06ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD06】外围冲正接口处理出错，核心前台流水号： " + XD06ReqTranHeader.getHSenderSeq());

			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030603000XD06】外围冲正接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD06ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD06ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030603000XD06】外围冲正接口失败："+ e.getMessage() + "ESB流水号: "+ XD06ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD06ReqTranHeader.getHSenderSeq());
			}
		}
		return oxd06Res;
	}
	
	/** 【XD07】账户控制及维护(止付/解止付)(1204) 输出接口OXD072 */
	@Bizlet("账户控制及维护(止付/解止付)")
	public OXD072_AccControlRes executeXD07(OXD071_AccControlReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030101000XD07";
		String url = getUrl() + zservice;
		S0030101000XD07ServiceStub.FMT_SOAP_UTF8_RequestHeader XD07ReqHeader = new S0030101000XD07ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030101000XD07ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD07ReqTranHeader = new S0030101000XD07ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030101000XD07ServiceStub.FMT_CRMS_SVR_S0030101000XD07_IN XD07ReqBody = new S0030101000XD07ServiceStub.FMT_CRMS_SVR_S0030101000XD07_IN();

		XD07ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD07ReqHeader.setReqSysCode("01601");
		XD07ReqHeader.setReqSecCode("");
		XD07ReqHeader.setTxType("RQ");
		XD07ReqHeader.setTxMode("0");
		XD07ReqHeader.setTxCode("S0030101000XD07");
		XD07ReqHeader.setReqDate(DateTools.getTime8());
		XD07ReqHeader.setReqTime(DateTools.getTime20());
		XD07ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD07ReqHeader.setChanlNo("48");
		XD07ReqHeader.setBrch(requestBody.getOrgNum());
		XD07ReqHeader.setTermNo("");
		XD07ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD07ReqHeader.setSendFileName("");
		XD07ReqHeader.setBeginRec("");
		XD07ReqHeader.setMaxRec(null);
		XD07ReqHeader.setFileHMac("");
		XD07ReqHeader.setHMac("");
	
		XD07ReqTranHeader.setHPinSeed("null");
		XD07ReqTranHeader.setHOriChnl("601");
		XD07ReqTranHeader.setHSecFlag("0");
		XD07ReqTranHeader.setHPwdFlag("0");
		XD07ReqTranHeader.setHCombFlag("0");
		XD07ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD07ReqTranHeader.setHSecInfoVerNo("null");
		XD07ReqTranHeader.setHSysChnl("48");
		XD07ReqTranHeader.setHLegaObj("9999");
		XD07ReqTranHeader.setHMsgRefNo("");
		XD07ReqTranHeader.setHTermNo("");
		XD07ReqTranHeader.setHCityCd("");
		XD07ReqTranHeader.setHBrchNo(requestBody.getOrgNum());
		XD07ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD07ReqTranHeader.setHTxnCd("XD07");
		XD07ReqTranHeader.setHTxnMod("");
		XD07ReqTranHeader.setHReserveLen("");
		XD07ReqTranHeader.setHSenderSvcCd("");
		XD07ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD07ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD07ReqTranHeader.setHAuthUserID("");
		XD07ReqTranHeader.setHAuthVerfInfo("");
		XD07ReqTranHeader.setHAuthFlag("");
		XD07ReqTranHeader.setHRefSeq("");
		XD07ReqTranHeader.setHAuthSeri("");
		XD07ReqTranHeader.setHHostSeq("");
		XD07ReqTranHeader.setHRefDt("");
		XD07ReqTranHeader.setHSvcVer("");
		XD07ReqTranHeader.setHreserveMsg("");
		XD07ReqTranHeader.setHintOrigMark(null);

		XD07ReqBody.setOperFlag(requestBody.getOperFlag());
		XD07ReqBody.setFreezeOperFlag(requestBody.getFreezeOperFlag());
		XD07ReqBody.setFrzNum(requestBody.getFrzNum());
		XD07ReqBody.setFreezeType(requestBody.getFreezeType());
		XD07ReqBody.setCustNo(requestBody.getCustNo());
		XD07ReqBody.setAcctname(requestBody.getAcctname());
		XD07ReqBody.setSubAcctSeri(requestBody.getSubAcctSeri());
		XD07ReqBody.setCurrCode(requestBody.getCurrCode());
		XD07ReqBody.setCashFlag(requestBody.getCashFlag());
		XD07ReqBody.setLabtAcctNum(requestBody.getLabtAcctNum());
		XD07ReqBody.setFreezeBal(requestBody.getFreezeBal());
		XD07ReqBody.setFreezeAmt(requestBody.getFreezeAmt());
		XD07ReqBody.setAmt(requestBody.getAmt());
		XD07ReqBody.setFrzCase(requestBody.getFrzCase());
		XD07ReqBody.setFreezeEnsureFileType(requestBody.getFreezeEnsureFileType());
		XD07ReqBody.setFreezeNotifyNo(requestBody.getFreezeNotifyNo());
		XD07ReqBody.setApprover(requestBody.getApprover());
		XD07ReqBody.setFlgValue(requestBody.getFlgValue());
		XD07ReqBody.setVchKind(requestBody.getVchKind());
		XD07ReqBody.setVchBatNo(requestBody.getVchBatNo());
		XD07ReqBody.setVchSerialNo(requestBody.getVchSerialNo());
		XD07ReqBody.setOperFlag1(requestBody.getOperFlag1());
		XD07ReqBody.setFreezeEndDate(requestBody.getFreezeEndDate());

		S0030101000XD07ServiceStub.S0030101000XD07Response XD07Response = new S0030101000XD07ServiceStub.S0030101000XD07Response();
		OXD072_AccControlRes OXD072Res = new OXD072_AccControlRes();
		OXD072ResBody OXD072ResBody = new OXD072ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030101000XD07ServiceStub stub = new S0030101000XD07ServiceStub(url);
			S0030101000XD07ServiceStub.S0030101000XD07 service = new S0030101000XD07ServiceStub.S0030101000XD07();
			service.setRequestHeader(XD07ReqHeader);
			service.setReqTranHeader(XD07ReqTranHeader);
			service.setRequestBody(XD07ReqBody);
			XD07Response = stub.S0030101000XD07(service);
			GitUtil.getNumTimes(requestBody.getOrgNum());
			if (XD07Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口成功，ESB流水号： " + XD07ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口成功，核心前台流水号： " + XD07ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD07Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD07Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD07Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD07Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD07Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD07Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD07Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD07Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD07Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD07Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD07Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD07Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD07Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD07Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD07Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD07Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD07Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD07Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD07Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD07Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD07Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD07Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD07Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD07Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD07Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD07Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD07Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD07Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD07Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD07Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD07Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD07Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD07Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD07Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD07Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD07Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD07Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD07Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD07Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD07Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD07Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD07Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD07Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD07Response.getResTranHeader().getHWarnMsg());
				
				OXD072ResBody.setCustNo(XD07Response.getResponseBody().getCustNo());
				OXD072ResBody.setAcctname(XD07Response.getResponseBody().getAcctname());
				OXD072ResBody.setFreezeAmt(XD07Response.getResponseBody().getFreezeAmt());
				OXD072ResBody.setFrzCase(XD07Response.getResponseBody().getFrzCase());
				OXD072ResBody.setFreezeEnsureFileType(XD07Response.getResponseBody().getFreezeEnsureFileType());
				OXD072ResBody.setFreezeNotifyNo(XD07Response.getResponseBody().getFreezeNotifyNo());
				OXD072ResBody.setApprover(XD07Response.getResponseBody().getApprover());
				OXD072ResBody.setFrzNum(XD07Response.getResponseBody().getFrzNum());
				OXD072ResBody.setAccrrestAmt(XD07Response.getResponseBody().getAccrrestAmt());

				OXD072Res.setResHeader(responseHeader);
				OXD072Res.setResTranHeader(resTranHeader);
				OXD072Res.setOxd072ResBody(OXD072ResBody);
			} else {
				resTranHeader.setHRetCode(XD07Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD07Response.getResTranHeader().getHRetMsg());
				OXD072Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口处理出错，处理结果： " + XD07Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口处理出错，ESB响应码： " + XD07Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口处理出错，ESB流水号： " + XD07ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD07】账户控制及维护(止付/解止付)接口处理出错，核心前台流水号： " + XD07ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030703000XD07】账户控制及维护(止付/解止付)接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD07ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD07ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030703000XD07】账户控制及维护(止付/解止付)接口失败："+ e.getMessage() + "ESB流水号: "+ XD07ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD07ReqTranHeader.getHSenderSeq());
			}
		}
		return OXD072Res;
	}

	/** 【XD08】客户账户信息查询(1232) 输出接口OXD082 */
	@Bizlet("客户账户信息查询")
	public OXD082_CustAccInfoQryRes executeXD08(OXD081_CustAccInfoQryReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030101000XD08";
		String url = getUrl() + zservice;
		S0030101000XD08ServiceStub.FMT_SOAP_UTF8_RequestHeader XD08ReqHeader = new S0030101000XD08ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030101000XD08ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD08ReqTranHeader = new S0030101000XD08ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030101000XD08ServiceStub.FMT_CRMS_SVR_S0030101000XD08_IN XD08ReqBody = new S0030101000XD08ServiceStub.FMT_CRMS_SVR_S0030101000XD08_IN();
		
		XD08ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD08ReqHeader.setReqSysCode("01601");
		XD08ReqHeader.setReqSecCode("");
		XD08ReqHeader.setTxType("RQ");
		XD08ReqHeader.setTxMode("0");
		XD08ReqHeader.setTxCode("S0030101000XD08");
		XD08ReqHeader.setReqDate(DateTools.getTime8());
		XD08ReqHeader.setReqTime(DateTools.getTime20());
		XD08ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD08ReqHeader.setChanlNo("48");
		XD08ReqHeader.setBrch(requestBody.getOrgNum());
		XD08ReqHeader.setTermNo("");
		XD08ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD08ReqHeader.setSendFileName("");
		XD08ReqHeader.setBeginRec("");
		XD08ReqHeader.setMaxRec(null);
		XD08ReqHeader.setFileHMac("");
		XD08ReqHeader.setHMac("");
	
		XD08ReqTranHeader.setHPinSeed("null");
		XD08ReqTranHeader.setHOriChnl("601");
		XD08ReqTranHeader.setHSecFlag("0");
		XD08ReqTranHeader.setHPwdFlag("0");
		XD08ReqTranHeader.setHCombFlag("0");
		XD08ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD08ReqTranHeader.setHSecInfoVerNo("null");
		XD08ReqTranHeader.setHSysChnl("48");
		XD08ReqTranHeader.setHLegaObj("9999");
		XD08ReqTranHeader.setHMsgRefNo("");
		XD08ReqTranHeader.setHTermNo("");
		XD08ReqTranHeader.setHCityCd("");
		XD08ReqTranHeader.setHBrchNo(requestBody.getOrgNum());
		XD08ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD08ReqTranHeader.setHTxnCd("XD08");
		XD08ReqTranHeader.setHTxnMod("");
		XD08ReqTranHeader.setHReserveLen("");
		XD08ReqTranHeader.setHSenderSvcCd("");
		XD08ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD08ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD08ReqTranHeader.setHAuthUserID("");
		XD08ReqTranHeader.setHAuthVerfInfo("");
		XD08ReqTranHeader.setHAuthFlag("");
		XD08ReqTranHeader.setHRefSeq("");
		XD08ReqTranHeader.setHAuthSeri("");
		XD08ReqTranHeader.setHHostSeq("");
		XD08ReqTranHeader.setHRefDt("");
		XD08ReqTranHeader.setHSvcVer("");
		XD08ReqTranHeader.setHreserveMsg("");
		XD08ReqTranHeader.setHintOrigMark(null);

		XD08ReqBody.setCustNo(requestBody.getCustNo());
		XD08ReqBody.setDealMode(requestBody.getDealMode());

		S0030101000XD08ServiceStub.S0030101000XD08Response XD08Response = new S0030101000XD08ServiceStub.S0030101000XD08Response();
		OXD082_CustAccInfoQryRes OXD082Res = new OXD082_CustAccInfoQryRes();
		OXD082ResBody OXD082ResBody = new OXD082ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030101000XD08ServiceStub stub = new S0030101000XD08ServiceStub(url);
			S0030101000XD08ServiceStub.S0030101000XD08 service = new S0030101000XD08ServiceStub.S0030101000XD08();
			service.setRequestHeader(XD08ReqHeader);
			service.setReqTranHeader(XD08ReqTranHeader);
			service.setRequestBody(XD08ReqBody);
			XD08Response = stub.S0030101000XD08(service);
			GitUtil.getNumTimes(requestBody.getOrgNum());
			if (XD08Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口成功，ESB流水号： " + XD08ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口成功，核心前台流水号： " + XD08ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD08Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD08Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD08Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD08Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD08Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD08Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD08Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD08Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD08Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD08Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD08Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD08Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD08Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD08Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD08Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD08Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD08Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD08Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD08Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD08Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD08Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD08Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD08Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD08Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD08Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD08Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD08Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD08Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD08Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD08Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD08Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD08Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD08Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD08Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD08Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD08Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD08Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD08Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD08Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD08Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD08Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD08Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD08Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD08Response.getResTranHeader().getHWarnMsg());

				OXD082ResBody.setCustNo(XD08Response.getResponseBody().getCustNo());
				OXD082ResBody.setCustNoType(XD08Response.getResponseBody().getCustNoType());
				OXD082ResBody.setCustType(XD08Response.getResponseBody().getCustType());
				OXD082ResBody.setUswFlag(XD08Response.getResponseBody().getUswFlag());
				OXD082ResBody.setPayCondiditon(XD08Response.getResponseBody().getPayCondiditon());
				OXD082ResBody.setVchNo(XD08Response.getResponseBody().getVchNo());
				OXD082ResBody.setCustAcctName(XD08Response.getResponseBody().getCustAcctName());
				OXD082ResBody.setCustName(XD08Response.getResponseBody().getCustName());
				OXD082ResBody.setEnName(XD08Response.getResponseBody().getEnName());
				OXD082ResBody.setOpenBrch(XD08Response.getResponseBody().getOpenBrch());
				OXD082ResBody.setFlgValue(XD08Response.getResponseBody().getFlgValue());
				OXD082ResBody.setYnFlag(XD08Response.getResponseBody().getYNFlag());
				OXD082ResBody.setVchKind(XD08Response.getResponseBody().getVchKind());
				OXD082ResBody.setVchBatNo(XD08Response.getResponseBody().getVchBatNo());
				OXD082ResBody.setVchSerialNo(XD08Response.getResponseBody().getVchSerialNo());
				OXD082ResBody.setNxtLine(XD08Response.getResponseBody().getNxtLine());
				OXD082ResBody.setCashFlag(XD08Response.getResponseBody().getCashFlag());
				OXD082ResBody.setCurrCode(XD08Response.getResponseBody().getCurrCode());
				OXD082ResBody.setSubAcctSeri(XD08Response.getResponseBody().getSubAcctSeri());
				OXD082ResBody.setCertType(XD08Response.getResponseBody().getCertType());
				OXD082ResBody.setCertNo(XD08Response.getResponseBody().getCertNo());
				OXD082ResBody.setFlgDefault(XD08Response.getResponseBody().getFlgDefault());
				OXD082ResBody.setIsNoteAcctFlag(XD08Response.getResponseBody().getIsNoteAcctFlag());
				OXD082ResBody.setPublNoteAcctPwdFlag(XD08Response.getResponseBody().getPublNoteAcctPwdFlag());
				OXD082ResBody.setUswAre(XD08Response.getResponseBody().getUswAre());
				OXD082ResBody.setCashExchgFlag(XD08Response.getResponseBody().getCashExchgFlag());
				OXD082ResBody.setTransExchgFlag(XD08Response.getResponseBody().getTransExchgFlag());
				OXD082ResBody.setLinkAcctFlg(XD08Response.getResponseBody().getLinkAcctFlg());
				OXD082ResBody.setPrdNo(XD08Response.getResponseBody().getPrdNo());
				OXD082ResBody.setPrdDesc(XD08Response.getResponseBody().getPrdDesc());
				OXD082ResBody.setLabtAcctNum(XD08Response.getResponseBody().getLabtAcctNum());
				OXD082ResBody.setAccrrestAmt(XD08Response.getResponseBody().getAccrrestAmt());
				OXD082ResBody.setAcctDeoststTerm(XD08Response.getResponseBody().getAcctDeoststTerm());
				OXD082ResBody.setAcctDeathDate(XD08Response.getResponseBody().getAcctDeathDate());
				OXD082ResBody.setDepositType(XD08Response.getResponseBody().getDepositType());
				OXD082ResBody.setBrchChnName(XD08Response.getResponseBody().getBrchChnName());
				OXD082ResBody.setInCustAcct(XD08Response.getResponseBody().getInCustAcct());
				OXD082ResBody.setUnitOpenAcctBankNo(XD08Response.getResponseBody().getUnitOpenAcctBankNo());
				OXD082ResBody.setTransOpponentAcct(XD08Response.getResponseBody().getTransOpponentAcct());
				OXD082ResBody.setGroupAcctSeri(XD08Response.getResponseBody().getGroupAcctSeri());
				OXD082ResBody.setBackup1(XD08Response.getResponseBody().getBackup1());
				OXD082ResBody.setGroupProductCode(XD08Response.getResponseBody().getGroupProductCode());
				OXD082ResBody.setAcctOpenDate(XD08Response.getResponseBody().getAcctOpenDate());
				OXD082ResBody.setValidDate(XD08Response.getResponseBody().getValidDate());
				
				OXD082Res.setResHeader(responseHeader);
				OXD082Res.setResTranHeader(resTranHeader);
				OXD082Res.setOxd082ResBody(OXD082ResBody);
			}else{
				resTranHeader.setHRetCode(XD08Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD08Response.getResTranHeader().getHRetMsg());
				OXD082Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口处理出错，处理结果： " + XD08Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口处理出错，ESB响应码： " + XD08Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口处理出错，ESB流水号： " + XD08ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD08】客户账户信息查询接口处理出错，核心前台流水号： " + XD08ReqTranHeader.getHSenderSeq());

			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030803000XD08】客户账户信息查询接口失败：请检查输入字段或联系ESB人员。ESB流水号： " + XD08ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD08ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030803000XD08】客户账户信息查询接口失败："+ e.getMessage() + "ESB流水号： "+ XD08ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD08ReqTranHeader.getHSenderSeq());
			}
		}
		return OXD082Res;
	}

	/** 【XD09】信贷抵质押物表外记账 输出接口OXD092  */
	@Bizlet("信贷抵质押物表外记账")
	public OXD092_PawnInOutRes executeXD09(OXD091_PawnInOutReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030199000XD09";
		String url = getUrl() + zservice;
		S0030199000XD09ServiceStub.FMT_SOAP_UTF8_RequestHeader XD09ReqHeader = new S0030199000XD09ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030199000XD09ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD09ReqTranHeader = new S0030199000XD09ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030199000XD09ServiceStub.FMT_CRMS_SVR_S0030199000XD09_IN XD09ReqBody = new S0030199000XD09ServiceStub.FMT_CRMS_SVR_S0030199000XD09_IN();

		XD09ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD09ReqHeader.setReqSysCode("01601");
		XD09ReqHeader.setReqSecCode("");
		XD09ReqHeader.setTxType("RQ");
		XD09ReqHeader.setTxMode("0");
		XD09ReqHeader.setTxCode("S0030199000XD09");
		XD09ReqHeader.setReqDate(DateTools.getTime8());
		XD09ReqHeader.setReqTime(DateTools.getTime20());
		XD09ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD09ReqHeader.setChanlNo("48");
		//modi by shangmf:送核心应该送账务机构
		// 去掉 XD09ReqHeader.setBrch(requestBody.getChargeBrch());
		//String orgCode=GitUtil.getOrgCode();
		String accOrgCode = "";
		if("o".equals(requestBody.getReserveMark1())){
			accOrgCode = requestBody.getChargeBrch();
		}else{
			accOrgCode=GitUtil.getAccOrgOffBalance(requestBody.getChargeBrch());
		}
		XD09ReqHeader.setBrch(accOrgCode); // 机构编号
		
		XD09ReqHeader.setTermNo("");
		
		//modi by shangmf:
		//去掉 XD09ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getChargeBrch()));
		XD09ReqHeader.setOper(GitUtil.getNumOrgOffBalance(accOrgCode)); // 柜员
		
		XD09ReqHeader.setSendFileName("");
		XD09ReqHeader.setBeginRec("");
		XD09ReqHeader.setMaxRec(null);
		XD09ReqHeader.setFileHMac("");
		XD09ReqHeader.setHMac("");
	
		XD09ReqTranHeader.setHPinSeed("null");
		XD09ReqTranHeader.setHOriChnl("601");
		XD09ReqTranHeader.setHSecFlag("0");
		XD09ReqTranHeader.setHPwdFlag("0");
		XD09ReqTranHeader.setHCombFlag("0");
		XD09ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD09ReqTranHeader.setHSecInfoVerNo("null");
		XD09ReqTranHeader.setHSysChnl("48");
		XD09ReqTranHeader.setHLegaObj("9999");
		XD09ReqTranHeader.setHMsgRefNo("");
		XD09ReqTranHeader.setHTermNo("");
		XD09ReqTranHeader.setHCityCd("");
		//改为账务机构
		XD09ReqTranHeader.setHBrchNo(accOrgCode);
		//赋虚拟柜员 modi by shangmf
		XD09ReqTranHeader.setHUserID(GitUtil.getNumOrgOffBalance(accOrgCode));
		
		XD09ReqTranHeader.setHTxnCd("XD09");
		XD09ReqTranHeader.setHTxnMod("");
		XD09ReqTranHeader.setHReserveLen("");
		XD09ReqTranHeader.setHSenderSvcCd("");
		XD09ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD09ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD09ReqTranHeader.setHAuthUserID("");
		XD09ReqTranHeader.setHAuthVerfInfo("");
		XD09ReqTranHeader.setHAuthFlag("");
		XD09ReqTranHeader.setHRefSeq("");
		XD09ReqTranHeader.setHAuthSeri("");
		XD09ReqTranHeader.setHHostSeq("");
		XD09ReqTranHeader.setHRefDt("");
		XD09ReqTranHeader.setHSvcVer("");
		XD09ReqTranHeader.setHreserveMsg("");
		XD09ReqTranHeader.setHintOrigMark(null);
		
		XD09ReqBody.setOperFlag(requestBody.getOperFlag()); 
		//modi by shangmf:改为账务机构
		XD09ReqBody.setChargeBrch(accOrgCode); 
		XD09ReqBody.setYNFlag(requestBody.getYnFlag()); 
		XD09ReqBody.setPrdCode(requestBody.getPrdCode()); 
		XD09ReqBody.setCollateralWay(requestBody.getCollateralWay()); 
		XD09ReqBody.setActualValue(requestBody.getActualValue()); 
		XD09ReqBody.setCurrCode(requestBody.getCurrCode()); 
		XD09ReqBody.setSummaryCode(requestBody.getSummaryCode()); 
		XD09ReqBody.setSummary(requestBody.getSummary()); 
		XD09ReqBody.setCustNo(requestBody.getCustNo()); 
		XD09ReqBody.setBackup2(requestBody.getBackup2()); 
		XD09ReqBody.setBackup1(requestBody.getBackup1()); 
		XD09ReqBody.setReserveMark1(requestBody.getReserveMark1()); 
		XD09ReqBody.setReserveMark2(requestBody.getReserveMark2()); 
		XD09ReqBody.setBackupAmt(requestBody.getBackupAmt()); 
		XD09ReqBody.setRecNum(requestBody.getRecNum());
		FMT_CRMS_SVR_S0030199000XD09_IN_FXD091[] fxd091SubArray;
		if (requestBody.getRecNum() == null || (requestBody.getRecNum()).intValue() <= 0) {
			fxd091SubArray = new FMT_CRMS_SVR_S0030199000XD09_IN_FXD091[1];
			FMT_CRMS_SVR_S0030199000XD09_IN_FXD091 fxd091Sub = new FMT_CRMS_SVR_S0030199000XD09_IN_FXD091();
			fxd091Sub.setYNFlag1("");  
			fxd091Sub.setFrzNum("");  
			fxd091Sub.setCustAcct("");  
			fxd091Sub.setAcctname("");  
			fxd091Sub.setSubAcctSeri("");  
			fxd091Sub.setCurrCode("");  
			fxd091Sub.setCashFlag("");  
			fxd091Sub.setFreezeType("");  
			fxd091Sub.setFreezeEndDate("");  
			fxd091Sub.setFreezeAmt("");  
			fxd091Sub.setFrzCase("");  
			fxd091Sub.setFreezeEnsureFileType("");  
			fxd091Sub.setFreezeNotifyNo("");  
			fxd091Sub.setYNFlag2("");  
			fxd091Sub.setBackup2("");  
			fxd091Sub.setBackup1("");  
			fxd091Sub.setReserveMark1("");  
			fxd091Sub.setReserveMark2("");  
			fxd091Sub.setBackupAmt("");  
			fxd091SubArray[0] = fxd091Sub;
			
			System.out.println("调用【S0030103000XD01】请求中循环记录数[RecNum]为0");
		} else {
			fxd091SubArray = new FMT_CRMS_SVR_S0030199000XD09_IN_FXD091[(XD09ReqBody.getRecNum()).intValue()];
		}
		for (int i = 0; i < fxd091SubArray.length; i++) {
			FMT_CRMS_SVR_S0030199000XD09_IN_FXD091 fxd091Sub = new FMT_CRMS_SVR_S0030199000XD09_IN_FXD091();
			FXD091[] fxd091s = requestBody.getFxd091();
			if(fxd091s != null && fxd091s.length != 0){
				FXD091 fxd091 = fxd091s[i];
				fxd091Sub.setYNFlag1(fxd091.getYnFlag1());  
				fxd091Sub.setFrzNum(fxd091.getFrzNum());  
				fxd091Sub.setCustAcct(fxd091.getCustAcct());  
				fxd091Sub.setAcctname(fxd091.getAcctname());  
				fxd091Sub.setSubAcctSeri(fxd091.getSubAcctSeri());  
				fxd091Sub.setCurrCode(fxd091.getCurrCode());  
				fxd091Sub.setCashFlag(fxd091.getCashFlag());  
				fxd091Sub.setFreezeType(fxd091.getFreezeType());  
				fxd091Sub.setFreezeEndDate(fxd091.getFreezeEndDate());  
				fxd091Sub.setFreezeAmt(fxd091.getFreezeAmt());  
				fxd091Sub.setFrzCase(fxd091.getFrzCase());  
				fxd091Sub.setFreezeEnsureFileType(fxd091.getFreezeEnsureFileType());  
				fxd091Sub.setFreezeNotifyNo(fxd091.getFreezeNotifyNo());  
				fxd091Sub.setYNFlag2(fxd091.getYnFlag2());  
				fxd091Sub.setBackup2(fxd091.getBackup2());  
				fxd091Sub.setBackup1(fxd091.getBackup1());  
				fxd091Sub.setReserveMark1(fxd091.getReserveMark1());  
				fxd091Sub.setReserveMark2(fxd091.getReserveMark2());  
				fxd091Sub.setBackupAmt(fxd091.getBackupAmt());  
				fxd091SubArray[i] = fxd091Sub;
			}
		}
		XD09ReqBody.setRecMsg(fxd091SubArray);

		S0030199000XD09ServiceStub.S0030199000XD09Response XD09Response = new S0030199000XD09ServiceStub.S0030199000XD09Response();
		OXD092_PawnInOutRes OXD092Res = new OXD092_PawnInOutRes();
		OXD092ResBody OXD092ResBody = new OXD092ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030199000XD09ServiceStub stub = new S0030199000XD09ServiceStub(url);
			S0030199000XD09ServiceStub.S0030199000XD09 service = new S0030199000XD09ServiceStub.S0030199000XD09();
			service.setRequestHeader(XD09ReqHeader);
			service.setReqTranHeader(XD09ReqTranHeader);
			service.setRequestBody(XD09ReqBody);
			XD09Response = stub.S0030199000XD09(service);
			System.out.println(XD09ReqHeader.getReqSeqNo());
			//modi by shangmf:修改虚拟柜员修改次数传入参数为会计机构
			GitUtil.getNumTimes(accOrgCode);
			if (XD09Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口成功，ESB流水号： " + XD09ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口成功，核心前台流水号： " + XD09ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD09Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD09Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD09Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD09Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD09Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD09Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD09Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD09Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD09Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD09Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD09Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD09Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD09Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD09Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD09Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD09Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD09Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD09Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD09Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD09Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD09Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD09Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD09Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD09Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD09Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD09Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD09Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD09Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD09Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD09Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD09Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD09Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD09Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD09Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD09Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD09Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD09Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD09Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD09Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD09Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD09Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD09Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD09Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD09Response.getResTranHeader().getHWarnMsg());

				OXD092ResBody.setRemarkInfo(XD09Response.getResponseBody().getRemarkInfo());
				FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092[] fxd092SubArray;
				if (XD09Response.getResponseBody().getRecMsg() == null || XD09Response.getResponseBody().getRecMsg().length == 0) {
					FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092 sub = new FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092();
					fxd092SubArray = new FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092[1];
					fxd092SubArray[0] = sub; 
					System.out.println("调用【S0030199000XD09】响应中[RecNum]循环记录数为0");
				}else {
					fxd092SubArray = XD09Response.getResponseBody().getRecMsg();
					List<FXD092> fxd092List = new ArrayList<FXD092>();
					for (int i = 0; i < fxd092SubArray.length; i++) {
						FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092 fxd092Sub = new FMT_CRMS_SVR_S0030199000XD09_OUT_FXD092();
						fxd092Sub = fxd092SubArray[i];
						FXD092 fxd092 = new FXD092();
						fxd092.setOperFlag(fxd092Sub.getOperFlag());
						fxd092.setFrzNum(fxd092Sub.getFrzNum());
						fxd092.setCustAcct(fxd092Sub.getCustAcct());
						fxd092.setSubAcctSeri(fxd092Sub.getSubAcctSeri());
						fxd092.setFreezeType(fxd092Sub.getFreezeType());
						fxd092.setFreezeAmt(fxd092Sub.getFreezeAmt());
						fxd092.setFrzCase(fxd092Sub.getFrzCase());
						fxd092.setFreezeEnsureFileType(fxd092Sub.getFreezeEnsureFileType());
						fxd092.setFreezeNotifyNo(fxd092Sub.getFreezeNotifyNo());
						fxd092.setBackup2(fxd092Sub.getBackup2());
						fxd092.setBackup1(fxd092Sub.getBackup1());
						fxd092.setReserveMark1(fxd092Sub.getReserveMark1());
						fxd092.setReserveMark2(fxd092Sub.getReserveMark2());
						fxd092.setBackupAmt(fxd092Sub.getBackupAmt());
						fxd092List.add(fxd092);
					}
					OXD092ResBody.setFxd092(fxd092List);
					
					OXD092Res.setResHeader(responseHeader);
					OXD092Res.setResTranHeader(resTranHeader);
					OXD092Res.setOxd092ResBody(OXD092ResBody);
				}
				
			} else {
				resTranHeader.setHRetCode(XD09Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD09Response.getResTranHeader().getHRetMsg());
				OXD092Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口处理出错，处理结果： " + XD09Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口处理出错，ESB响应码： " + XD09Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口处理出错，ESB流水号： " + XD09ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030199000XD09】信贷抵质押物表外记账接口处理出错，核心前台流水号： " + XD09ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030199000XD09】信贷抵质押物表外记账接口失败：请检查输入字段或联系ESB人员。ESB流水号： " + XD09ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD09ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030199000XD09】信贷抵质押物表外记账接口失败："+ e.getMessage() + "ESB流水号： "+ XD09ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD09ReqTranHeader.getHSenderSeq());
			}
		}
		return OXD092Res;
	}
	/** 【XD15】资金账户信息组合查询 */
	@Bizlet("资金账户信息组合查询")
	public OXD15AccountInfo executeXD15(IXD15AccountInfo requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030101000XD15";
		String url = getUrl() + zservice;
		S0030101000XD15ServiceStub.FMT_SOAP_UTF8_RequestHeader XD15ReqHeader = new S0030101000XD15ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030101000XD15ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD15ReqTranHeader = new S0030101000XD15ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030101000XD15ServiceStub.FMT_CRMS_SVR_S0030101000XD15_IN XD15ReqBody = new S0030101000XD15ServiceStub.FMT_CRMS_SVR_S0030101000XD15_IN();
		
		XD15ReqHeader.setVersionNo(DateTools.getVersionNo("2"));
		XD15ReqHeader.setReqSysCode("01601");
		XD15ReqHeader.setReqSecCode("");
		XD15ReqHeader.setTxType("RQ");
		XD15ReqHeader.setTxMode("0");
		XD15ReqHeader.setTxCode("S0030101000XD15");
		XD15ReqHeader.setReqDate(DateTools.getTime8());
		XD15ReqHeader.setReqTime(DateTools.getTime20());
		XD15ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD15ReqHeader.setChanlNo("48");
		XD15ReqHeader.setBrch(requestBody.getOrgNum());
		XD15ReqHeader.setTermNo("");
		XD15ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD15ReqHeader.setSendFileName("");
		XD15ReqHeader.setBeginRec("");
		XD15ReqHeader.setMaxRec(null);
		XD15ReqHeader.setFileHMac("");
		XD15ReqHeader.setHMac("");
	
		XD15ReqTranHeader.setHPinSeed("null");
		XD15ReqTranHeader.setHOriChnl("601");
		XD15ReqTranHeader.setHSecFlag("0");
		XD15ReqTranHeader.setHPwdFlag("0");
		XD15ReqTranHeader.setHCombFlag("0");
		XD15ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD15ReqTranHeader.setHSecInfoVerNo("null");
		XD15ReqTranHeader.setHSysChnl("48");
		XD15ReqTranHeader.setHLegaObj("9999");
		XD15ReqTranHeader.setHMsgRefNo("");
		XD15ReqTranHeader.setHTermNo("");
		XD15ReqTranHeader.setHCityCd("");
		XD15ReqTranHeader.setHBrchNo(requestBody.getOrgNum());
		XD15ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getOrgNum()));
		XD15ReqTranHeader.setHTxnCd("XD15");
		XD15ReqTranHeader.setHTxnMod("");
		XD15ReqTranHeader.setHReserveLen("");
		XD15ReqTranHeader.setHSenderSvcCd("");
		XD15ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD15ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD15ReqTranHeader.setHAuthUserID("");
		XD15ReqTranHeader.setHAuthVerfInfo("");
		XD15ReqTranHeader.setHAuthFlag("");
		XD15ReqTranHeader.setHRefSeq("");
		XD15ReqTranHeader.setHAuthSeri("");
		XD15ReqTranHeader.setHHostSeq("");
		XD15ReqTranHeader.setHRefDt("");
		XD15ReqTranHeader.setHSvcVer("");
		XD15ReqTranHeader.setHreserveMsg("");
		XD15ReqTranHeader.setHintOrigMark(null);
		
		XD15ReqBody.setAcctNo(requestBody.getAcctNo());
		XD15ReqBody.setAcctBusiKind(requestBody.getAcctBusiKind());
		XD15ReqBody.setBusiNo(requestBody.getBusiNo());
		XD15ReqBody.setBusiClass(requestBody.getBusiClass());
		XD15ReqBody.setBusinessBrch(requestBody.getBusinessBrch());
		XD15ReqBody.setCurrCode(requestBody.getCurrCode());
		XD15ReqBody.setBegDate(requestBody.getBegDate());
		XD15ReqBody.setEnddate(requestBody.getEnddate());
		XD15ReqBody.setFundAcctStat("0");
		XD15ReqBody.setLinkFlg("1");//是否联动标志:0-是 1-否
		XD15ReqBody.setBegNum("1");
		XD15ReqBody.setQryNum("1");
		XD15ReqBody.setAcctChnName(requestBody.getAcctChnName());
		XD15ReqBody.setLastExpDate(requestBody.getLastExpDate());
		XD15ReqBody.setExpireDate(requestBody.getExpireDate());
		XD15ReqBody.setTransAmt1(requestBody.getTransAmt1());
		XD15ReqBody.setTransAmt2(requestBody.getTransAmt2());
		XD15ReqBody.setSmIdyCd(requestBody.getSmIdyCd());
		
		S0030101000XD15ServiceStub.S0030101000XD15Response XD15Response = new S0030101000XD15ServiceStub.S0030101000XD15Response();
		OXD15AccountInfo OXD152Res = new OXD15AccountInfo();
		OXD015ResBody OXD152ResBody = new OXD015ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030101000XD15ServiceStub stub = new S0030101000XD15ServiceStub(url);
			S0030101000XD15ServiceStub.S0030101000XD15 service = new S0030101000XD15ServiceStub.S0030101000XD15();
			service.setRequestHeader(XD15ReqHeader);
			service.setReqTranHeader(XD15ReqTranHeader);
			service.setRequestBody(XD15ReqBody);
			XD15Response = stub.S0030101000XD15(service);
			GitUtil.getNumTimes(requestBody.getOrgNum());
			if (XD15Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD15】账户信息查询接口成功，ESB流水号： " + XD15ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD15】账户信息查询接口成功，核心前台流水号： " + XD15ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD15Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD15Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD15Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD15Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD15Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD15Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD15Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD15Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD15Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD15Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD15Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD15Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD15Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD15Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD15Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD15Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD15Response.getResponseHeader().getHMac());

				resTranHeader.setHSecFlag(XD15Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD15Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD15Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD15Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD15Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD15Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD15Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD15Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD15Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD15Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD15Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD15Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD15Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD15Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD15Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD15Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD15Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD15Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD15Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD15Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD15Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD15Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD15Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD15Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD15Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD15Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD15Response.getResTranHeader().getHWarnMsg());
				FMT_CRMS_SVR_S0030101000XD15_OUT_SUB[] outSub = XD15Response.getResponseBody().getRecMsg();
				List<FXD151> listfxd = new ArrayList<FXD151>();
				FXD151 fxd151 = null;
				for(int i= 0; i< outSub.length; i++){
					fxd151 = new FXD151();
					fxd151.setAcctBusiKind(outSub[i].getAcctBusiKind());
					fxd151.setAcctNo(outSub[i].getAcctNo());
					fxd151.setAcctChnName(outSub[i].getAcctChnName());
					fxd151.setBusinessBrch(outSub[i].getBusinessBrch());
					fxd151.setSupBrch(outSub[i].getSupBrch());
					fxd151.setPrdNo(outSub[i].getPrdNo());
					fxd151.setBusiNo(outSub[i].getBusiNo());
					fxd151.setBusiClass(outSub[i].getBusiClass());
					fxd151.setBothOpenAcctFlg(outSub[i].getBothOpenAcctFlg());
					fxd151.setOverDraftFlg(outSub[i].getOverDraftFlg());
					fxd151.setOppoAcctNo(outSub[i].getOppoAcctNo());
					fxd151.setOpenBrch(outSub[i].getOpenBrch());
					fxd151.setSmIdyCd(outSub[i].getSmIdyCd());
					fxd151.setCountyBorderFlg(outSub[i].getCountyBorderFlg());
					fxd151.setBankBicNo(outSub[i].getBankBicNo());
					fxd151.setToChkToClgSupsBal(outSub[i].getToChkToClgSupsBal());
					fxd151.setIsCount(outSub[i].getIsCount());
					fxd151.setAccrualCycle(outSub[i].getAccrualCycle());
					fxd151.setIsInterest(outSub[i].getIsInterest());
					fxd151.setCountBalanceCycle(outSub[i].getCountBalanceCycle());
					if("01".equals(outSub[i].getCurrCode())){//人民币
						fxd151.setCurrCode("CNY");
					}else if("12".equals(outSub[i].getCurrCode())){//英镑
						fxd151.setCurrCode("GBP");
					}else if("13".equals(outSub[i].getCurrCode())){//港币
						fxd151.setCurrCode("HKD");
					}else if("14".equals(outSub[i].getCurrCode())){//美元
						fxd151.setCurrCode("USD");
					}else if("15".equals(outSub[i].getCurrCode())){//瑞士法郎
						fxd151.setCurrCode("CHF");
					}else if("27".equals(outSub[i].getCurrCode())){//日元
						fxd151.setCurrCode("JPY");
					}else if("28".equals(outSub[i].getCurrCode())){//加拿大元
						fxd151.setCurrCode("CAD");
					}else if("29".equals(outSub[i].getCurrCode())){//澳洲元
						fxd151.setCurrCode("AUD");
					}else if("32".equals(outSub[i].getCurrCode())){//新加坡元
						fxd151.setCurrCode("SGD");
					}else if("38".equals(outSub[i].getCurrCode())){//欧元
						fxd151.setCurrCode("EUR");
					}else if("81".equals(outSub[i].getCurrCode())){//澳门元
						fxd151.setCurrCode("MOP");
					}
					fxd151.setCustNo(outSub[i].getCustNo());
					fxd151.setDepstTerm(outSub[i].getDepstTerm());
					fxd151.setStartIntDate(outSub[i].getStartIntDate());
					fxd151.setExpireDate(outSub[i].getExpireDate());
					fxd151.setBalance(outSub[i].getBalance());
					fxd151.setEveryDayAcmInt(outSub[i].getEveryDayAcmInt());
					fxd151.setAcmRetnInt(outSub[i].getAcmRetnInt());
					fxd151.setRateNo(outSub[i].getRateNo());
					fxd151.setYearOrMonthRate(outSub[i].getYearOrMonthRate());
					fxd151.setRate(outSub[i].getRate());
					fxd151.setOverDraftIntRate(outSub[i].getOverDraftIntRate());
					fxd151.setExpYrMoIntRate(outSub[i].getExpYrMoIntRate());
					fxd151.setExpIntRateNo(outSub[i].getExpIntRateNo());
					fxd151.setExpIntRate(outSub[i].getExpIntRate());
					fxd151.setStructDepositFLg(outSub[i].getStructDepositFLg());
					fxd151.setCollIntAcct(outSub[i].getCollIntAcct());
					fxd151.setCollIntPeriod(outSub[i].getCollIntPeriod());
					fxd151.setCollIntWarnDays(outSub[i].getCollIntWarnDays());
					fxd151.setHadRcvIntTotAmt(outSub[i].getHadRcvIntTotAmt());
					fxd151.setReservesRate(outSub[i].getReservesRate());
					fxd151.setReservesRateNo(outSub[i].getReservesRateNo());
					fxd151.setReservesYMRate(outSub[i].getReservesYMRate());
					fxd151.setReservesRate1(outSub[i].getReservesRate1());
					fxd151.setPrvnAcrInt(outSub[i].getPrvnAcrInt());
					fxd151.setReservesAcrRate(outSub[i].getReservesAcrRate());
					fxd151.setTrsfInFncPeriod(outSub[i].getTrsfInFncPeriod());
					fxd151.setFncDepRatePct(outSub[i].getFncDepRatePct());
					fxd151.setOpenAcctDate(outSub[i].getOpenAcctDate());
					fxd151.setOpenAcctTeller(outSub[i].getOpenAcctTeller());
					fxd151.setBalaNature(outSub[i].getBalaNature());
					fxd151.setAcctSeri(outSub[i].getAcctSeri());
					fxd151.setInterAccrualWay(outSub[i].getInterAccrualWay());
					fxd151.setFundAcctStat(outSub[i].getFundAcctStat());
					fxd151.setLastDayActBal(outSub[i].getLastDayActBal());
					fxd151.setCanActTeller(outSub[i].getCanActTeller());
					fxd151.setCanActDate(outSub[i].getCanActDate());
					fxd151.setBalaDirection(outSub[i].getBalaDirection());
					fxd151.setBgnofprdBalDir(outSub[i].getBgnofprdBalDir());
					listfxd.add(fxd151);
				}
				OXD152ResBody.setfXD151(listfxd);
				

				OXD152Res.setResHeader(responseHeader);
				OXD152Res.setResTranHeader(resTranHeader);
				OXD152Res.setOxd015ResBody(OXD152ResBody);
			} else {
				resTranHeader.setHRetCode(XD15Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD15Response.getResTranHeader().getHRetMsg());
				OXD152Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD15】账户信息查询接口处理出错，处理结果： " + XD15Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD15】账户信息查询接口处理出错，ESB响应码： " + XD15Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD15】账户信息查询接口处理出错，ESB流水号： " + XD15ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD15】账户信息查询接口处理出错，核心前台流水号： " + XD15ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030101000XD15】账户信息查询口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD15ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD15ReqTranHeader.getHSenderSeq());
			}else {
				throw new Exception("调用【S0030101000XD15】账户信息查询口失败：" + e.getMessage() + "ESB流水号: " + XD15ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD15ReqTranHeader.getHSenderSeq());
			}		
		}
		return OXD152Res;
	}
	/** 【XD11】质押扣划OXD11 */
	@Override
	@Bizlet("存单质押扣划")
	public OXD11_CdzykhRes executeXD11(OXD11_CdzykhReq requestBody) throws Exception {
		String zservice ="/WebService/CRMS_SVR/S0030103000XD11";
		String url = getUrl() + zservice;
		S0030103000XD11ServiceStub.FMT_SOAP_UTF8_RequestHeader XD11ReqHeader = new S0030103000XD11ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0030103000XD11ServiceStub.FMT_SOAP_UTF8_ReqTranHeader XD11ReqTranHeader = new S0030103000XD11ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S0030103000XD11ServiceStub.FMT_CRMS_SVR_S0030103000XD11_IN XD11ReqBody = new S0030103000XD11ServiceStub.FMT_CRMS_SVR_S0030103000XD11_IN();
		
		XD11ReqHeader.setVersionNo(DateTools.getVersionNo("1"));
		XD11ReqHeader.setReqSysCode("01601");
		XD11ReqHeader.setReqSecCode("");
		XD11ReqHeader.setTxType("RQ");
		XD11ReqHeader.setTxMode("2");
		XD11ReqHeader.setTxCode("S0030103000XD11");
		XD11ReqHeader.setReqDate(DateTools.getTime8());
		XD11ReqHeader.setReqTime(DateTools.getTime20());
		XD11ReqHeader.setReqSeqNo(DateTools.getReqSeqNo());
		XD11ReqHeader.setChanlNo("48");
		//XD11ReqHeader.setBrch(GitUtil.getCurrentOrgCd());
		XD11ReqHeader.setTermNo("");
		//XD11ReqHeader.setOper(GitUtil.getCurrentUserId());
		XD11ReqHeader.setBrch(requestBody.getHxOrgNum());//经办机构
		XD11ReqHeader.setOper(GitUtil.getNumOrg(requestBody.getHxorg()));//出账机构获取柜员号
		XD11ReqHeader.setSendFileName("");
		XD11ReqHeader.setBeginRec("");
		XD11ReqHeader.setMaxRec(null);
		XD11ReqHeader.setFileHMac("");
		XD11ReqHeader.setHMac("");
	
		XD11ReqTranHeader.setHPinSeed("null");
		XD11ReqTranHeader.setHOriChnl("601");
		XD11ReqTranHeader.setHSecFlag("0");
		XD11ReqTranHeader.setHPwdFlag("0");
		XD11ReqTranHeader.setHCombFlag("0");
		XD11ReqTranHeader.setHSvcInfo("zuhejy_01");
		XD11ReqTranHeader.setHSecInfoVerNo("null");
		XD11ReqTranHeader.setHSysChnl("48");
		XD11ReqTranHeader.setHLegaObj("9999");
		XD11ReqTranHeader.setHMsgRefNo("");
		XD11ReqTranHeader.setHTermNo("");
		XD11ReqTranHeader.setHCityCd("");
		
		XD11ReqTranHeader.setHBrchNo(requestBody.getHxOrgNum());//经办机构
		XD11ReqTranHeader.setHUserID(GitUtil.getNumOrg(requestBody.getHxorg()));//出账机构获取柜员号
		//XD11ReqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());
		//XD11ReqTranHeader.setHUserID(GitUtil.getCurrentUserId());
		
		XD11ReqTranHeader.setHTxnCd("XD11");
		XD11ReqTranHeader.setHTxnMod("");
		XD11ReqTranHeader.setHReserveLen("");
		XD11ReqTranHeader.setHSenderSvcCd("");
		//XD11ReqTranHeader.setHSenderSeq(DateTools.getReqSeqNo().substring(0, 16));
		XD11ReqTranHeader.setHSenderSeq(requestBody.getFlowNo());//流水
		XD11ReqTranHeader.setHSenderDate(DateTools.getTime8());
		XD11ReqTranHeader.setHAuthUserID("");
		XD11ReqTranHeader.setHAuthVerfInfo("");
		XD11ReqTranHeader.setHAuthFlag("");
		XD11ReqTranHeader.setHRefSeq("");
		XD11ReqTranHeader.setHAuthSeri("");
		XD11ReqTranHeader.setHHostSeq("");
		XD11ReqTranHeader.setHRefDt("");
		XD11ReqTranHeader.setHSvcVer("");
		XD11ReqTranHeader.setHreserveMsg("");
		XD11ReqTranHeader.setHintOrigMark(null);
		
		XD11ReqBody.setFrzNo(requestBody.getFrzNo());
		XD11ReqBody.setApprovalDepart(requestBody.getApprovalDepart());
		XD11ReqBody.setApprover(requestBody.getApprover());
		XD11ReqBody.setCustAcctNo(requestBody.getCustAcctNo());
		XD11ReqBody.setDeductedAmt(requestBody.getDeductedAmt());
		XD11ReqBody.setSummaryCode(requestBody.getSummaryCode());
		XD11ReqBody.setSummaryDesc(requestBody.getSummaryDesc());
		XD11ReqBody.setTrsfInAcctNo(requestBody.getTrsfInAcctNo());
		
		S0030103000XD11ServiceStub.S0030103000XD11Response XD11Response = new S0030103000XD11ServiceStub.S0030103000XD11Response();
		OXD11_CdzykhRes oxd11Res = new OXD11_CdzykhRes();
		OXD11ResBody oxd11ResBody = new OXD11ResBody();
		ResponseHeader responseHeader = new ResponseHeader();
		ResTranHeader resTranHeader = new ResTranHeader();
		try {
			S0030103000XD11ServiceStub stub = new S0030103000XD11ServiceStub(url);
			S0030103000XD11ServiceStub.S0030103000XD11 service = new S0030103000XD11ServiceStub.S0030103000XD11();
			service.setRequestHeader(XD11ReqHeader);
			service.setReqTranHeader(XD11ReqTranHeader);
			service.setRequestBody(XD11ReqBody);
			XD11Response = stub.S0030103000XD11(service);
			GitUtil.getNumTimes(GitUtil.getCurrentOrgCd());
			if (XD11Response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				System.out.println("调用【S0030101000XD11】质押扣划接口成功，ESB流水号： " + XD11ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD11】质押扣划接口成功，核心前台流水号： " + XD11ReqTranHeader.getHSenderSeq());
				responseHeader.setVersionNo(XD11Response.getResponseHeader().getVersionNo());
				responseHeader.setReqSysCode(XD11Response.getResponseHeader().getReqSysCode());
				responseHeader.setReqSecCode(XD11Response.getResponseHeader().getReqSecCode());
				responseHeader.setTxType(XD11Response.getResponseHeader().getTxType());
				responseHeader.setTxMode(XD11Response.getResponseHeader().getTxMode());
				responseHeader.setTxCode(XD11Response.getResponseHeader().getTxCode());
				responseHeader.setReqDate(XD11Response.getResponseHeader().getReqDate());
				responseHeader.setReqTime(XD11Response.getResponseHeader().getReqTime());
				responseHeader.setReqSeqNo(XD11Response.getResponseHeader().getReqSeqNo());
				responseHeader.setSvrDate(XD11Response.getResponseHeader().getSvrDate());
				responseHeader.setSvrTime(XD11Response.getResponseHeader().getSvrTime());
				responseHeader.setSvrSeqNo(XD11Response.getResponseHeader().getSvrSeqNo());
				responseHeader.setRecvFileName(XD11Response.getResponseHeader().getRecvFileName());
				responseHeader.setTotNum(XD11Response.getResponseHeader().getTotNum());
				responseHeader.setCurrNum(XD11Response.getResponseHeader().getCurrNum());
				responseHeader.setFileHMac(XD11Response.getResponseHeader().getFileHMac());
				responseHeader.setHmac(XD11Response.getResponseHeader().getHMac());
				
				resTranHeader.setHSecFlag(XD11Response.getResTranHeader().getHSecFlag());
				resTranHeader.setHCombFlag(XD11Response.getResTranHeader().getHCombFlag());
				resTranHeader.setHSvcInfo(XD11Response.getResTranHeader().getHSvcInfo());
				resTranHeader.setHSecInfoVerNo(XD11Response.getResTranHeader().getHSecInfoVerNo());
				resTranHeader.setHMsgRefNo(XD11Response.getResTranHeader().getHMsgRefNo());
				resTranHeader.setHIdentFlag(XD11Response.getResTranHeader().getHIdentFlag());
				resTranHeader.setHSuperFlag(XD11Response.getResTranHeader().getHSuperFlag());
				resTranHeader.setHChkFlag(XD11Response.getResTranHeader().getHChkFlag());
				resTranHeader.setHChkTxnCd(XD11Response.getResTranHeader().getHChkTxnCd());
				resTranHeader.setHVerfCd(XD11Response.getResTranHeader().getHVerfCd());
				resTranHeader.setHTranRes(XD11Response.getResTranHeader().getHTranRes());
				resTranHeader.setHRefTxnCd(XD11Response.getResTranHeader().getHRefTxnCd());
				resTranHeader.setHServerDt(XD11Response.getResTranHeader().getHServerDt());
				resTranHeader.setHServerTm(XD11Response.getResTranHeader().getHServerTm());
				resTranHeader.setHServerSeq(XD11Response.getResTranHeader().getHServerSeq());
				resTranHeader.setHAcountDt(XD11Response.getResTranHeader().getHAcountDt());
				resTranHeader.setHRefSeq(XD11Response.getResTranHeader().getHRefSeq());
				resTranHeader.setHRefDt(XD11Response.getResTranHeader().getHRefDt());
				resTranHeader.setHNextStep(XD11Response.getResTranHeader().getHNextStep());
				resTranHeader.setHVchChk(XD11Response.getResTranHeader().getHVchChk());
				resTranHeader.setHRetResInfo(XD11Response.getResTranHeader().getHRetResInfo());
				resTranHeader.setHErrTranNo(XD11Response.getResTranHeader().getHErrTranNo());
				resTranHeader.setHAssiInfo(XD11Response.getResTranHeader().getHAssiInfo());
				resTranHeader.setHRetCode(XD11Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetNo(XD11Response.getResTranHeader().getHRetNo());
				resTranHeader.setHRetMsg(XD11Response.getResTranHeader().getHRetMsg());
				resTranHeader.setHWarnMsg(XD11Response.getResTranHeader().getHWarnMsg());
				
				oxd11ResBody.setFrzNo(XD11Response.getResponseBody().getFrzNo());
				oxd11ResBody.setDeductedAmt(XD11Response.getResponseBody().getDeductedAmt());
				oxd11ResBody.setCustAcctNo(XD11Response.getResponseBody().getCustAcctNo());
				
				oxd11Res.setResHeader(responseHeader);
				oxd11Res.setResTranHeader(resTranHeader);
				oxd11Res.setOxd11ResBody(oxd11ResBody);
			} else {
				resTranHeader.setHRetCode(XD11Response.getResTranHeader().getHRetCode());
				resTranHeader.setHRetMsg("【核心报错】"+XD11Response.getResTranHeader().getHRetMsg());
				oxd11Res.setResTranHeader(resTranHeader);
				System.out.println("调用【S0030101000XD11】质押扣划接口处理出错，处理结果： " + XD11Response.getResTranHeader().getHRetMsg());
				System.out.println("调用【S0030101000XD11】质押扣划接口处理出错，ESB响应码： " + XD11Response.getResTranHeader().getHRetCode());
				System.out.println("调用【S0030101000XD11】质押扣划接口处理出错，ESB流水号： " + XD11ReqHeader.getReqSeqNo());
				System.out.println("调用【S0030101000XD11】质押扣划接口处理出错，核心前台流水号： " + XD11ReqTranHeader.getHSenderSeq());
			}
		} catch (Exception e) {
			if (e.getMessage().equals("The input stream for an incoming message is null.")) {
				throw new Exception("调用【S0030101000XD11】质押扣划接口失败：请检查输入字段或联系ESB人员。ESB流水号: " + XD11ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD11ReqTranHeader.getHSenderSeq());
			} else {
				throw new Exception("调用【S0030101000XD11】质押扣划接口失败："+ e.getMessage() + "ESB流水号: "+ XD11ReqHeader.getReqSeqNo() + "核心前台流水号： " + XD11ReqTranHeader.getHSenderSeq());
			}
		}
		return oxd11Res;
	}
}