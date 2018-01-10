package com.primeton.plusclient;


import org.apache.axis2.engine.ListenerManager;
import org.apache.commons.beanutils.BeanUtils;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.plus.AssetOffControlRq;
import com.primeton.plus.AssetOffRq;
import com.primeton.plus.BusiOrgSplitRq;
import com.primeton.plus.ChangeCredTimeRq;
import com.primeton.plus.ChangeInterControlRq;
import com.primeton.plus.ChangeIntrRq;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plus.CredMenu;
import com.primeton.plus.CredPeriodChangeRq;
import com.primeton.plus.DelayTime;
import com.primeton.plus.DiscountBackRq;
import com.primeton.plus.DiscountStopRq;
import com.primeton.plus.DuePlanChangeRq;
import com.primeton.plus.ExtendTimeAppRq;
import com.primeton.plus.FirstAjustPeriodRq;
import com.primeton.plus.ImpproviMenu;
import com.primeton.plus.InterSettMenu;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.plus.LoanCancelRq;
import com.primeton.plus.MoneyPayOffRq;
import com.primeton.plus.OffCaculate;
import com.primeton.plus.PayInterMenu;
import com.primeton.plus.PayOutCheckRq;
import com.primeton.plus.PayOutRq;
import com.primeton.plus.PayVerifBackControlRq;
import com.primeton.plus.PayVerifControlRq;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayCancel;
import com.primeton.plus.RepayControlCancel;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepayMenu;
import com.primeton.plus.RepayOldNewRq;
import com.primeton.plus.RepayPlanChangeRq;
import com.primeton.plus.RepayWayChangeRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plus.SettleMenu;
import com.primeton.plus.StopControlRq;
import com.primeton.plus.StopItrRq;
import com.primeton.plus.StopStopControlRq;
import com.primeton.plus.StopStopItrRq;
import com.primeton.plus.VerificationBackRq;
import com.primeton.plus.VerificationRq;
import com.primeton.plus.VouComMenu;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteB1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteB1101Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteB1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteB1102Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1100;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1100Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1101Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1102Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1103;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1103Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1104;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1104Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1105;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1105Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1106;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1106Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1107;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1107Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1108;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1108Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1110;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1110Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1115;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1115Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1202;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1202Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1203;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1203Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1204;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1204Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1205;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1205Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1206;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1206Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1207;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1207Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1208;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1208Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1210;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1210Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1301;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1301Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1302;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1302Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1303;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1303Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1304;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1304Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1305;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1305Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1306;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1306Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1308;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1308Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1401;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1401Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1402;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1402Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1403;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1403Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1404;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1404Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1405;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1405Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1406;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1406Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1407;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1407Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1408;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1408Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1410;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1410Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1411;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1411Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1412;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1412Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1413;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1413Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1415;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1415Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1417;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1417Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1418;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1418Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1419;
import com.primeton.plusclient.WebServiceGlToHsImplStub.ExecuteT1419Response;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoB1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoB1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1100;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1103;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1104;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1105;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1106;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1107;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1108;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1110;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1115;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1202;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1203;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1204;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1205;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1206;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1207;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1208;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1210;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1301;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1302;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1303;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1304;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1305;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1306;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1308;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1401;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1402;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1403;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1404;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1405;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1406;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1407;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1408;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1410;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1411;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1412;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1413;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1415;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1417;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1418;
import com.primeton.plusclient.WebServiceGlToHsImplStub.InVoT1419;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoB1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoB1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1100;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1101;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1102;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1103;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1104;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1105;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1106;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1107;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1108;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1110;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1115;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1202;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1203;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1204;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1205;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1206;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1207;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1208;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1210;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1301;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1302;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1303;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1304;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1305;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1306;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1308;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1401;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1402;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1403;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1404;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1405;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1406;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1407;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1408;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1410;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1411;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1412;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1413;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1415;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1417;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1418;
import com.primeton.plusclient.WebServiceGlToHsImplStub.OutVoT1419;
import com.primeton.plusclient.WebServiceGlToHsImplStub.PubMessage;
import com.primeton.tsl.BaseVO;

/**
 * 管理调核算的所有接口
 * @author CHENPAN
 *
 */
@Bizlet("管理调核算的所有接口")
public class CrmsCallPlusImpl implements CrmsCallPlusProxy {
	private static  WebServiceGlToHsImplStub stub = null;
	private String getUrl(){
		ListenerManager.defaultConfigurationContext = null;
		String module = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_IP;
		String port = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_PORT;
		String wservice = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_SERVICE;
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		String zservice = ConfigurationUtil.getUserConfigSingleValue(module, group, wservice);
		String url = "http://" + zip + ":" + zport + zservice;
		return url;
	}
	
	/*
	 * 放款 
	 */
	@Bizlet("放款 ")
	public PayOutRq executeT1101(PayOutRq payOutRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1101 t1101 = new ExecuteT1101();
			InVoT1101 pub = new InVoT1101();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, payOutRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setTelNo(payOutRq.getTelNo());
			pub.setDueNum(payOutRq.getDueNum());
			
			pub.setBrwName(payOutRq.getBrwName());
			pub.setConNo(payOutRq.getConNo());
			pub.setProdCod(payOutRq.getProdCod());
			pub.setPrimAcct(payOutRq.getPrimAcct());
			pub.setPrimAcctName(payOutRq.getPrimAcctName());
			pub.setPayPrimAcct(payOutRq.getPayPrimAcct());
			pub.setPayPrimName(payOutRq.getPayPrimName());
			pub.setCurrCod(payOutRq.getCurrCod());
			pub.setPadUpAmt(payOutRq.getPadUpAmt());
			pub.setNorItrRate(payOutRq.getNorItrRate());
			pub.setDelItrRate(payOutRq.getDelItrRate());
			pub.setBegDate(payOutRq.getBegDate());
			pub.setEndDate(payOutRq.getEndDate());
			pub.setCurPrmPayTyp(payOutRq.getCurPrmPayTyp());
			pub.setCurAstPayTyp(payOutRq.getCurAstPayTyp());
			pub.setCaspan(payOutRq.getCaspan());
			pub.setPayDate(payOutRq.getPayDate());
			pub.setNextProvDate(payOutRq.getNextProvDate());
			pub.setDiscType(payOutRq.getDiscType());
			pub.setDueNumSun(payOutRq.getDueNumSun());
			pub.setAgyBusAccName(payOutRq.getAgyBusAccName());
			pub.setRcvPrn(payOutRq.getRcvPrn());
			pub.setRcvNorItrIn(payOutRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(payOutRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(payOutRq.getRcvPnsItrIn());
			pub.setAccEntJson(payOutRq.getAccEntJson());
			t1101.setArg0(pub);
			
			ExecuteT1101Response response =  stub.executeT1101(t1101);
			OutVoT1101 out1101 = response.get_return();
			PayOutRq rs = new PayOutRq();
			BaseVO bvo = new BaseVO();
			rs.setDueNum(out1101.getDueNum());
			rs.setTelNo(out1101.getTelNo());
			rs.setBrwName(out1101.getBrwName());
			rs.setConNo(out1101.getConNo());
			rs.setProdCod(out1101.getProdCod());
			rs.setPrimAcct(out1101.getPrimAcct());
			rs.setPrimAcctName(out1101.getPrimAcctName());
			rs.setPayPrimAcct(out1101.getPayPrimAcct());
			rs.setPayPrimName(out1101.getPayPrimName());
			rs.setCurrCod(out1101.getCurrCod());
			rs.setPadUpAmt(out1101.getPadUpAmt());
			rs.setNorItrRate(out1101.getNorItrRate());
			rs.setDelItrRate(out1101.getDelItrRate());
			rs.setBegDate(out1101.getBegDate());
			rs.setEndDate(out1101.getEndDate());
			rs.setCurPrmPayTyp(out1101.getCurPrmPayTyp());
			rs.setCurAstPayTyp(out1101.getCurAstPayTyp());
			rs.setCaspan(out1101.getCaspan());
			rs.setPayDate(out1101.getPayDate());
			rs.setNextProvDate(out1101.getNextProvDate());
			rs.setStgFirstMon(out1101.getStgFirstMon());
			rs.setDiscType(out1101.getDiscType());
			rs.setDueNumSun(out1101.getDueNumSun());
			rs.setAgyBusAccName(out1101.getAgyBusAccName());
			rs.setRcvPrn(out1101.getRcvPrn());
			rs.setRcvNorItrIn(out1101.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1101.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1101.getRcvPnsItrIn());
			rs.setAccEntJson(out1101.getAccEntJson());
			PubMessage bizHeader = out1101.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1101.getDealResultCod());
			bvo.setErrMsg(out1101.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口executeT1101失败：" + e.getMessage());
		}
		
	}
	/*
	 * 还款 
	 */
	@Bizlet("还款")
	public RepaymentRq executeT1102(RepaymentRq repaymentRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1102 t1102 = new ExecuteT1102();
			InVoT1102 pub = new InVoT1102();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, repaymentRq.getBaseVO());
			pub.setPayPrimName(repaymentRq.getPayPrimName());
			pub.setPayPrimAcct(repaymentRq.getPayPrimAcct());
			pub.setPadUpAmt(repaymentRq.getPadUpAmt());
			pub.setPayOrder(repaymentRq.getPayOrder());
			pub.setRcvPrn(repaymentRq.getRcvPrn());
			pub.setRcvNorItrIn(repaymentRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(repaymentRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(repaymentRq.getRcvPnsItrIn());
			pub.setPadUpPrn(repaymentRq.getPadUpPrn());
			pub.setPadUpNorItrIn(repaymentRq.getPadUpNorItrIn());
			pub.setPadUpDftItrIn(repaymentRq.getPadUpDftItrIn());
			pub.setPadUpPnsItrIn(repaymentRq.getPadUpPnsItrIn());
			pub.setPadUpCpdItrIn(repaymentRq.getPadUpCpdItrIn());
			pub.setPadUpPentIcm(repaymentRq.getPadUpPentIcm());
			pub.setDueNum(repaymentRq.getDueNum());
			pub.setTelNo(repaymentRq.getTelNo());
			pub.setConNo(repaymentRq.getConNo());
			pub.setBegDate(repaymentRq.getBegDate());
			pub.setEndDate(repaymentRq.getEndDate());
			pub.setBrwName(repaymentRq.getBrwName());
			pub.setSts(repaymentRq.getSts());
			pub.setPayOutItrFlg(repaymentRq.getPayOutItrFlg());
			pub.setAccEntJson(repaymentRq.getAccEntJson());
			pub.setAssetTransferFlg(repaymentRq.getAssetTransferFlg());
			pub.setPledgeDeductFlg(repaymentRq.getPledgeDeductFlg());
			pub.setBizHeader(message);
			t1102.setArg0(pub);
			ExecuteT1102Response response = stub.executeT1102(t1102);
			OutVoT1102 out1102 = response.get_return();
			RepaymentRq rs = new RepaymentRq();
			BaseVO bvo = new BaseVO();
			rs.setPayPrimName(out1102.getPayPrimName());
			rs.setPayPrimAcct(out1102.getPayPrimAcct());
			rs.setPadUpAmt(out1102.getPadUpAmt());
			rs.setPayOrder(out1102.getPayOrder());
			rs.setRcvPrn(out1102.getRcvPrn());
			rs.setRcvNorItrIn(out1102.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1102.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1102.getRcvPnsItrIn());
			rs.setPadUpPrn(out1102.getPadUpPrn());
			rs.setPadUpDftItrIn(out1102.getPadUpDftItrIn());
			rs.setPadUpPnsItrIn(out1102.getPadUpPnsItrIn());
			rs.setPadUpPentIcm(out1102.getPadUpPentIcm());
			rs.setPadUpNorItrIn(out1102.getPadUpNorItrIn());
			rs.setPadUpCpdItrIn(out1102.getPadUpCpdItrIn());
			rs.setDueNum(out1102.getDueNum());
			rs.setTelNo(out1102.getTelNo());
			rs.setConNo(out1102.getConNo());
			rs.setBegDate(out1102.getBegDate());
			rs.setEndDate(out1102.getEndDate());
			rs.setBrwName(out1102.getBrwName());
			rs.setAccEntJson(out1102.getAccEntJson());
			rs.setPayOutItrFlg(out1102.getPayOutItrFlg());
			rs.setSts(out1102.getSts());
			rs.setAssetTransferFlg(out1102.getAssetTransferFlg());
			rs.setPledgeDeductFlg(out1102.getPledgeDeductFlg());
			PubMessage bizHeader = out1102.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1102.getDealResultCod());
			bvo.setErrMsg(out1102.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 停息 
	 */
	@Bizlet("停息")
	public StopItrRq executeT1103(StopItrRq stopItrRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1103 t1103 = new ExecuteT1103();
			InVoT1103 pub = new InVoT1103();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, stopItrRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setBrwName(stopItrRq.getBrwName());
			pub.setRcvPrn(stopItrRq.getRcvPrn());
			pub.setRcvNorItrIn(stopItrRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(stopItrRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(stopItrRq.getRcvPnsItrIn());
			pub.setTelNo(stopItrRq.getTelNo());
			pub.setDueNum(stopItrRq.getDueNum());
			pub.setConNo(stopItrRq.getConNo());
			pub.setBegDate(stopItrRq.getBegDate());
			pub.setEndDate(stopItrRq.getEndDate());
			t1103.setArg0(pub);
			ExecuteT1103Response response = stub.executeT1103(t1103);
			OutVoT1103 out1103 = response.get_return();
			StopItrRq rs = new StopItrRq();
			BaseVO bvo = new BaseVO();
			rs.setBrwName(out1103.getBrwName());
			rs.setRcvPrn(out1103.getRcvPrn());
			rs.setRcvNorItrIn(out1103.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1103.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1103.getRcvPnsItrIn());
			rs.setTelNo(out1103.getTelNo());
			rs.setDueNum(out1103.getDueNum());
			rs.setConNo(out1103.getConNo());
			rs.setBegDate(out1103.getBegDate());
			rs.setEndDate(out1103.getEndDate());
			PubMessage bizHeader = out1103.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1103.getDealResultCod());
			bvo.setErrMsg(out1103.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 终止停息 
	 */
	@Bizlet("终止停息")
	public StopStopItrRq executeT1104(StopStopItrRq stopStopItrRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1104 t1104 = new ExecuteT1104();
			InVoT1104 pub = new InVoT1104();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, stopStopItrRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setTelNo(stopStopItrRq.getTelNo());
			pub.setDueNum(stopStopItrRq.getDueNum());
			
			pub.setBrwName(stopStopItrRq.getBrwName());
			pub.setRcvPrn(stopStopItrRq.getRcvPrn());
			pub.setRcvNorItrIn(stopStopItrRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(stopStopItrRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(stopStopItrRq.getRcvPnsItrIn());
			pub.setCeasDate(stopStopItrRq.getCeasDate());
			pub.setRcvItrType(stopStopItrRq.getRcvItrType());
			pub.setPadUpNorItrIn(stopStopItrRq.getPadUpNorItrIn());
			pub.setPadUpDftItrIn(stopStopItrRq.getPadUpDftItrIn());
			pub.setPadUpPnsItrIn(stopStopItrRq.getPadUpPnsItrIn());
			pub.setPadUpCpdItrIn(stopStopItrRq.getPadUpCpdItrIn());
			pub.setTotItr(stopStopItrRq.getTotItr());
			pub.setConNo(stopStopItrRq.getConNo());
			pub.setBegDate(stopStopItrRq.getBegDate());
			pub.setEndDate(stopStopItrRq.getEndDate());
			
			t1104.setArg0(pub);
			ExecuteT1104Response response = stub.executeT1104(t1104);
			OutVoT1104 out1104 = response.get_return();
			StopStopItrRq rs = new StopStopItrRq();
			rs.setBrwName(out1104.getBrwName());
			rs.setRcvPrn(out1104.getRcvPrn());
			rs.setRcvNorItrIn(out1104.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1104.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1104.getRcvPnsItrIn());
			rs.setCeasDate(out1104.getCeasDate());
			rs.setRcvItrType(out1104.getRcvItrType());
			rs.setPadUpNorItrIn(out1104.getPadUpNorItrIn());
			rs.setPadUpDftItrIn(out1104.getPadUpDftItrIn());
			rs.setPadUpPnsItrIn(out1104.getPadUpPnsItrIn());
			rs.setPadUpCpdItrIn(out1104.getPadUpCpdItrIn());
			rs.setTotItr(out1104.getTotItr());
			rs.setTelNo(out1104.getTelNo());
			rs.setDueNum(out1104.getDueNum());
			rs.setConNo(out1104.getConNo());
			rs.setBegDate(out1104.getBegDate());
			rs.setEndDate(out1104.getEndDate());
			BaseVO bvo = new BaseVO();
			PubMessage bizHeader = out1104.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1104.getDealResultCod());
			bvo.setErrMsg(out1104.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 调整利息 
	 */
	@Bizlet("调整利息")
	public ChangeIntrRq executeT1105(ChangeIntrRq changeIntrRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1105 t1105 = new ExecuteT1105();
			InVoT1105 pub = new InVoT1105();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, changeIntrRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setTelNo(changeIntrRq.getTelNo());
			pub.setDueNum(changeIntrRq.getDueNum());
			
			pub.setBrwName(changeIntrRq.getBrwName());
			pub.setRcvNorItrIn(changeIntrRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(changeIntrRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(changeIntrRq.getRcvPnsItrIn());
			pub.setRcvCpdItrIn(changeIntrRq.getRcvCpdItrIn());
			pub.setAdjOtdItr(changeIntrRq.getAdjOtdItr());
			pub.setAdjOtdPns(changeIntrRq.getAdjOtdPns());
			pub.setAdjOtdCpd(changeIntrRq.getAdjOtdCpd());
			pub.setPadUpNorItrIn(changeIntrRq.getPadUpNorItrIn());
			pub.setPadUpDftItrIn(changeIntrRq.getPadUpDftItrIn());
			pub.setPadUpPnsItrIn(changeIntrRq.getPadUpPnsItrIn());
			pub.setPadUpCpdItrIn(changeIntrRq.getPadUpCpdItrIn());
			pub.setPadUpAdjOtdItr(changeIntrRq.getPadUpAdjOtdItr());
			pub.setPadUpAdjOtdPns(changeIntrRq.getPadUpAdjOtdPns());
			pub.setPadUpAdjOtdCpd(changeIntrRq.getPadUpAdjOtdCpd());
			pub.setAdjItrFlg(changeIntrRq.getAdjItrFlg());
			
			t1105.setArg0(pub);
			ExecuteT1105Response response = stub.executeT1105(t1105);
			OutVoT1105 out1105 = response.get_return();
			ChangeIntrRq rs = new ChangeIntrRq();
			rs.setTelNo(out1105.getTelNo());
			rs.setDueNum(out1105.getDueNum());
			rs.setBrwName(out1105.getBrwName());
			rs.setRcvNorItrIn(out1105.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1105.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1105.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(out1105.getRcvCpdItrIn());
			rs.setAdjOtdItr(out1105.getAdjOtdItr());
			rs.setAdjOtdPns(out1105.getAdjOtdPns());
			rs.setAdjOtdCpd(out1105.getAdjOtdCpd());
			rs.setPadUpNorItrIn(out1105.getPadUpNorItrIn());
			rs.setPadUpDftItrIn(out1105.getPadUpDftItrIn());
			rs.setPadUpPnsItrIn(out1105.getPadUpPnsItrIn());
			rs.setPadUpCpdItrIn(out1105.getPadUpCpdItrIn());
			rs.setPadUpAdjOtdItr(out1105.getPadUpAdjOtdItr());
			rs.setPadUpAdjOtdPns(out1105.getPadUpAdjOtdPns());
			rs.setPadUpAdjOtdCpd(out1105.getPadUpAdjOtdCpd());
			rs.setAdjItrFlg(out1105.getAdjItrFlg());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1105.getDealResultCod());
			bvo.setErrMsg(out1105.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 核销 
	 */
	@Bizlet("核销")
	public VerificationRq executeT1107(VerificationRq verificationRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1107 t1107 = new ExecuteT1107();
			InVoT1107 pub = new InVoT1107();
			PubMessage message = new PubMessage();
			pub.setTelNo(verificationRq.getTelNo());
			pub.setDueNum(verificationRq.getDueNum());
			
			pub.setBrwName(verificationRq.getBrwName());
			pub.setBegDate(verificationRq.getBegDate());
			pub.setEndDate(verificationRq.getEndDate());
			pub.setRcvPrn(verificationRq.getRcvPrn());
			pub.setRcvNorItrIn(verificationRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(verificationRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(verificationRq.getRcvPnsItrIn());
			pub.setRcvCpdItrIn(verificationRq.getRcvCpdItrIn());
			pub.setTotPrnItr(verificationRq.getTotPrnItr());
			
			BeanUtils.copyProperties(message, verificationRq.getBaseVO());
			pub.setBizHeader(message);
			t1107.setArg0(pub);
			ExecuteT1107Response response = stub.executeT1107(t1107);
			OutVoT1107 out1107 = response.get_return();
			VerificationRq rs = new VerificationRq();
			rs.setTelNo(out1107.getTelNo());
			rs.setDueNum(out1107.getDueNum());
			rs.setBrwName(out1107.getBrwName());
			rs.setBegDate(out1107.getBegDate());
			rs.setEndDate(out1107.getEndDate());
			rs.setRcvPrn(out1107.getRcvPrn());
			rs.setRcvNorItrIn(out1107.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1107.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1107.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(out1107.getRcvCpdItrIn());
			rs.setTotPrnItr(out1107.getTotPrnItr());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1107.getDealResultCod());
			bvo.setErrMsg(out1107.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}

	}
	/*
	 * 核销收回 
	 */
	@Bizlet("核销收回")
	public VerificationBackRq executeT1108(VerificationBackRq verificationBackRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1108 t1108 = new ExecuteT1108();
			InVoT1108 pub = new InVoT1108();
			PubMessage message = new PubMessage();
			pub.setTelNo(verificationBackRq.getTelNo());
			pub.setDueNum(verificationBackRq.getDueNum());
			
			pub.setBrwName(verificationBackRq.getBrwName());
			pub.setBegDate(verificationBackRq.getBegDate());
			pub.setEndDate(verificationBackRq.getEndDate());
			pub.setRcvPrn(verificationBackRq.getRcvPrn());
			pub.setRcvNorItrIn(verificationBackRq.getRcvNorItrIn());
			pub.setRcvNorItrOut(verificationBackRq.getRcvNorItrOut());
			pub.setRcvOtdItr(verificationBackRq.getRcvOtdItr());
			pub.setPadUpPrn(verificationBackRq.getPadUpPrn());
			pub.setPadUpNorItrIn(verificationBackRq.getPadUpNorItrIn());
			pub.setPadUpNorItrOut(verificationBackRq.getPadUpNorItrOut());
			pub.setPadUpAmt(verificationBackRq.getPadUpAmt());
			pub.setPayPrimAcct(verificationBackRq.getPayPrimAcct());
			pub.setPayPrimName(verificationBackRq.getPayPrimName());
			pub.setAccEntJson(verificationBackRq.getAccJson());
			BeanUtils.copyProperties(message, verificationBackRq.getBaseVO());
			pub.setBizHeader(message);
			t1108.setArg0(pub);
			ExecuteT1108Response response = stub.executeT1108(t1108);
			OutVoT1108 out1108 = response.get_return();
			VerificationBackRq rs = new VerificationBackRq();
			rs.setTelNo(out1108.getTelNo());
			rs.setDueNum(out1108.getDueNum());
			rs.setBrwName(out1108.getBrwName());
			rs.setBegDate(out1108.getBegDate());
			rs.setEndDate(out1108.getEndDate());
			rs.setRcvPrn(out1108.getRcvPrn());
			rs.setRcvNorItrIn(out1108.getRcvNorItrIn());
			rs.setRcvNorItrOut(out1108.getRcvNorItrOut());
			rs.setRcvOtdItr(out1108.getRcvOtdItr());
			rs.setPadUpPrn(out1108.getPadUpPrn());
			rs.setPadUpNorItrIn(out1108.getPadUpNorItrIn());
			rs.setPadUpNorItrOut(out1108.getPadUpNorItrOut());
			rs.setPadUpAmt(out1108.getPadUpAmt());
			rs.setPayPrimAcct(out1108.getPayPrimAcct());
			rs.setPayPrimName(out1108.getPayPrimName());
			rs.setAccJson(out1108.getAccEntJson());
			BaseVO bvo = new BaseVO();
			PubMessage bizHeader = out1108.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1108.getDealResultCod());
			bvo.setErrMsg(out1108.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 撤销 
	 */
	@Bizlet("撤销")
	public LoanCancelRq executeT1110(LoanCancelRq loanCancelRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1110 t1110 = new ExecuteT1110();
			InVoT1110 pub = new InVoT1110();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, loanCancelRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setTelNo(loanCancelRq.getTelNo());
			pub.setDueNum(loanCancelRq.getDueNum());
			pub.setRevStan(loanCancelRq.getRevStan());
			t1110.setArg0(pub);
			ExecuteT1110Response response = stub.executeT1110(t1110);
			OutVoT1110 out1110 = response.get_return();
			LoanCancelRq rs = new LoanCancelRq();
			BaseVO bvo = new BaseVO();
			PubMessage bizHeader = out1110.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1110.getDealResultCod());
			bvo.setErrMsg(out1110.getDealResultMsg());
			rs.setAccEntJson(out1110.getAccEntJson());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 还款控制信息 
	 */
	@Bizlet("还款控制信息")
	public RepayControlInfRq executeT1202(RepayControlInfRq repayControlInfRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1202 t1202 = new ExecuteT1202();
			InVoT1202 pub = new InVoT1202();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, repayControlInfRq.getBaseVO());
			pub.setTelNo(repayControlInfRq.getTelNo());
			pub.setDueNum(repayControlInfRq.getDueNum());
			pub.setPadUpPentIcm(repayControlInfRq.getPadUpPentIcm());
			pub.setPadUpAmt(repayControlInfRq.getPadUpAmt());
			pub.setStopPayNum(repayControlInfRq.getStopPayNum());
			pub.setPayOrder(repayControlInfRq.getPayOrder());
			pub.setPayPrimAcct(repayControlInfRq.getPayPrimAcct());
			pub.setPayPrimName(repayControlInfRq.getPayPrimName());
			pub.setPrinPlanFlg(repayControlInfRq.getPrinPlanFlg());
			if(null != repayControlInfRq.getPrinPlanTerm()){
				pub.setPrinPlanTerm(repayControlInfRq.getPrinPlanTerm());
			}
			pub.setPayOutItrFlg(repayControlInfRq.getPayOutItrFlg());
			pub.setPadUpPrn(repayControlInfRq.getPadUpPrn());
			pub.setPadUpNorItrIn(repayControlInfRq.getPadUpNorItrIn());
			pub.setPadUpDftItrIn(repayControlInfRq.getPadUpDftItrIn());
			pub.setPadUpPnsItrIn(repayControlInfRq.getPadUpPnsItrIn());
			pub.setPadUpCpdItrIn(repayControlInfRq.getPadUpCpdItrIn());
			pub.setPadUpAdjOtdItr(repayControlInfRq.getPadUpAdjOtdItr());
			pub.setPadUpAdjOtdPns(repayControlInfRq.getPadUpAdjOtdPns());
			pub.setPadUpAdjOtdCpd(repayControlInfRq.getPadUpAdjOtdCpd());
			pub.setBizHeader(message);
			t1202.setArg0(pub);
			ExecuteT1202Response response = stub.executeT1202(t1202);
			OutVoT1202 out1202 = response.get_return();
			RepayControlInfRq rs = new RepayControlInfRq();
			BaseVO vo = new BaseVO();
			rs.setDueNum(out1202.getDueNum());
			rs.setTelNo(out1202.getTelNo());
			rs.setPadUpPentIcm(out1202.getPadUpPentIcm());
			rs.setPadUpAmt(out1202.getPadUpAmt());
			rs.setStopPayNum(out1202.getStopPayNum());
			rs.setPayOrder(out1202.getPayOrder());
			rs.setPayPrimAcct(out1202.getPayPrimAcct());
			rs.setPayPrimName(out1202.getPayPrimName());
			rs.setPrinPlanFlg(out1202.getPrinPlanFlg());
			String val = out1202.getPrinPlanTerm()+"";
			if(null != val || !"null".equals(val)){
				rs.setPrinPlanTerm((int)out1202.getPrinPlanTerm());
			}
			
			rs.setPayOutItrFlg(out1202.getPayOutItrFlg());
			rs.setPadUpPrn(out1202.getPadUpPrn());
			rs.setPadUpNorItrIn(out1202.getPadUpNorItrIn());
			rs.setPadUpDftItrIn(out1202.getPadUpDftItrIn());
			rs.setPadUpPnsItrIn(out1202.getPadUpPnsItrIn());
			rs.setPadUpCpdItrIn(out1202.getPadUpCpdItrIn());
			vo.setErrCod(out1202.getDealResultCod());
			vo.setErrMsg(out1202.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 停息控制信息 
	 */
	@Bizlet("停息控制信息")
	public StopControlRq executeT1203(StopControlRq stopControlRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1203 t1203 = new ExecuteT1203();
			InVoT1203 pub = new InVoT1203();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, stopControlRq.getBaseVO());
			pub.setTelNo(stopControlRq.getTelNo());
			pub.setDueNum(stopControlRq.getDueNum());
			pub.setBizHeader(message);
			t1203.setArg0(pub);
			ExecuteT1203Response response = stub.executeT1203(t1203);
			OutVoT1203 out1203 = response.get_return();
			StopControlRq rs = new StopControlRq();
			rs.setDueNum(out1203.getDueNum());
			rs.setTelNo(out1203.getTelNo());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1203.getDealResultCod());
			vo.setErrMsg(out1203.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}

	}
	/*
	 * 终止停息控制信息
	 */
	@Bizlet("终止停息控制信息")
	public StopStopControlRq executeT1204(StopStopControlRq stopStopControlRr)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1204 t1204 = new ExecuteT1204();
			InVoT1204 pub = new InVoT1204();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, stopStopControlRr.getBaseVO());
			pub.setTelNo(stopStopControlRr.getTelNo());
			pub.setDueNum(stopStopControlRr.getDueNum());
			pub.setRcvItrYype(stopStopControlRr.getRcvItrYype());
			pub.setRcvNorItrIn(stopStopControlRr.getRcvNorItrIn());
			pub.setRcvDftItrIn(stopStopControlRr.getRcvDftItrIn());
			pub.setRcvPnsItrIn(stopStopControlRr.getRcvPnsItrIn());
			pub.setRcvCpdItrIn(stopStopControlRr.getRcvCpdItrIn());
			pub.setBizHeader(message);
			t1204.setArg0(pub);
			ExecuteT1204Response response = stub.executeT1204(t1204);
			OutVoT1204 out1204 = response.get_return();
			StopStopControlRq rs = new StopStopControlRq();
			BaseVO vo = new BaseVO();
			rs.setRcvNorItrIn(out1204.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1204.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1204.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(out1204.getRcvCpdItrIn());
			vo.setErrCod(out1204.getDealResultCod());
			vo.setErrMsg(out1204.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 调整利息控制信息
	 */
	@Bizlet("调整利息控制信息")
	public ChangeInterControlRq executeT1205(
			ChangeInterControlRq changeInterControlRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1205 t1205 = new ExecuteT1205();
			InVoT1205 pub = new InVoT1205();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, changeInterControlRq.getBaseVO());
//			private BigDecimal padUpNorItrIn;       //调整正常率利息
//			private BigDecimal padUpDftItrIn;    	//调整拖欠利息
//			private BigDecimal padUpPnsItrIn;    	//调整罚息
//			private BigDecimal padUpCpdItrIn;    	//调整复利
//			private BigDecimal padUpAdjOtdItr;    	//调整未结计正常利息
//			private BigDecimal padUpAdjOtdPns;    	//调整未结计罚息
//			private BigDecimal padUpAdjOtdCpd;    	//调整未结计复利
			pub.setTelNo(changeInterControlRq.getTelNo());
			pub.setDueNum(changeInterControlRq.getDueNum());
			pub.setAdjItrFlg(changeInterControlRq.getAdjItrFlg());
			pub.setRcvNorItrIn(changeInterControlRq.getRcvNorItrIn());
			pub.setRcvDftItrIn(changeInterControlRq.getRcvDftItrIn());
			pub.setRcvPnsItrIn(changeInterControlRq.getRcvPnsItrIn());
			pub.setRcvCpdItrIn(changeInterControlRq.getRcvCpdItrIn());
			pub.setRcvNorItrOut(changeInterControlRq.getRcvNorItrOut());
			pub.setRcvDftItrOut(changeInterControlRq.getRcvDftItrOut());
			pub.setRcvPnsItrOut(changeInterControlRq.getRcvPnsItrOut());
			pub.setRcvCpdItrOut(changeInterControlRq.getRcvCpdItrOut());
			pub.setAdjOtdCpd(changeInterControlRq.getAdjOtdCpd());
			pub.setAdjOtdItr(changeInterControlRq.getAdjOtdItr());
			pub.setAdjOtdPns(changeInterControlRq.getAdjOtdPns());
			pub.setPadUpNorItrIn(changeInterControlRq.getPadUpNorItrIn());
			pub.setPadUpDftItrIn(changeInterControlRq.getPadUpDftItrIn());
			pub.setPadUpPnsItrIn(changeInterControlRq.getPadUpPnsItrIn());
			pub.setPadUpCpdItrIn(changeInterControlRq.getPadUpCpdItrIn());
			pub.setPadUpAdjOtdItr(changeInterControlRq.getPadUpAdjOtdItr());
			pub.setPadUpAdjOtdPns(changeInterControlRq.getPadUpAdjOtdPns());
			pub.setPadUpAdjOtdCpd(changeInterControlRq.getPadUpAdjOtdCpd());
			pub.setBizHeader(message);
			t1205.setArg0(pub);
			ExecuteT1205Response response = stub.executeT1205(t1205);
			OutVoT1205 out1205 = response.get_return();
			ChangeInterControlRq rs = new ChangeInterControlRq();
			rs.setDueNum(out1205.getDueNum());
			rs.setTelNo(out1205.getTelNo());
			rs.setAdjItrFlg(out1205.getAdjItrFlg());
			rs.setRcvNorItrIn(out1205.getRcvNorItrOut());
			rs.setRcvDftItrIn(out1205.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1205.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(out1205.getRcvCpdItrIn());
			rs.setRcvNorItrOut(out1205.getRcvNorItrOut());
			rs.setRcvDftItrOut(out1205.getRcvDftItrOut());
			rs.setRcvCpdItrOut(out1205.getRcvCpdItrOut());
			rs.setRcvCpdItrOut(out1205.getRcvCpdItrOut());
			rs.setAdjOtdItr(out1205.getAdjOtdItr());
			rs.setAdjOtdPns(out1205.getAdjOtdPns());
			rs.setAdjOtdCpd(out1205.getAdjOtdCpd());
			rs.setPadUpNorItrIn(out1205.getPadUpNorItrIn());
			rs.setPadUpDftItrIn(out1205.getPadUpDftItrIn());
			rs.setPadUpPnsItrIn(out1205.getPadUpPnsItrIn());
			rs.setPadUpCpdItrIn(out1205.getPadUpCpdItrIn());
			rs.setPadUpAdjOtdItr(out1205.getPadUpAdjOtdItr());
			rs.setPadUpAdjOtdPns(out1205.getPadUpAdjOtdPns());
			rs.setPadUpAdjOtdCpd(out1205.getPadUpAdjOtdCpd());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1205.getDealResultCod());
			vo.setErrMsg(out1205.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 核销控制信息
	 */
	@Bizlet("核销控制信息")
	public PayVerifControlRq executeT1207(PayVerifControlRq payVerifControlRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1207 t1207 = new ExecuteT1207();
			InVoT1207 pub = new InVoT1207();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, payVerifControlRq.getBaseVO());
			pub.setTelNo(payVerifControlRq.getTelNo());
			pub.setDueNum(payVerifControlRq.getDueNum());
			pub.setBizHeader(message);
			t1207.setArg0(pub);
			ExecuteT1207Response response = stub.executeT1207(t1207);
			OutVoT1207 out1207 = response.get_return();
			PayVerifControlRq rs = new PayVerifControlRq();
			rs.setDueNum(out1207.getDueNum());
			rs.setTelNo(out1207.getTelNo());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1207.getDealResultCod());
			vo.setErrMsg(out1207.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 核销收回控制信息
	 */
	@Bizlet("核销收回控制信息")
	public PayVerifBackControlRq executeT1208(
			PayVerifBackControlRq payVerifBackControlRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1208 t1208 = new ExecuteT1208();
			InVoT1208 pub = new InVoT1208();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, payVerifBackControlRq.getBaseVO());
			pub.setTelNo(payVerifBackControlRq.getTelNo());
			pub.setDueNum(payVerifBackControlRq.getDueNum());
			pub.setPadUpAmt(payVerifBackControlRq.getPadUpAmt());
			pub.setPayPrimAcct(payVerifBackControlRq.getPayPrimAcct());
			pub.setPayPrimName(payVerifBackControlRq.getPayPrimName());
			pub.setBizHeader(message);
			t1208.setArg0(pub);
			ExecuteT1208Response response = stub.executeT1208(t1208);
			OutVoT1208 out1208 = response.get_return();
			PayVerifBackControlRq rs = new PayVerifBackControlRq();
			rs.setDueNum(out1208.getDueNum());
			rs.setTelNo(out1208.getTelNo());
			rs.setPadUpAmt(out1208.getPadUpAmt());
			rs.setPayPrimAcct(out1208.getPayPrimAcct());
			rs.setPayPrimName(out1208.getPayPrimName());
			rs.setRcvPrn(out1208.getRcvPrn());
			rs.setRcvNorItrIn(out1208.getRcvNorItrIn());
			rs.setRcvNorItrOut(out1208.getRcvNorItrOut());
			rs.setRcvOtdItr(out1208.getRcvOtdItr());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1208.getDealResultCod());
			vo.setErrMsg(out1208.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 抵债资产冲销贷款控制信息 
	 */
	@Bizlet("抵债资产冲销贷款控制信息")
	public AssetOffControlRq executeT1206(AssetOffControlRq assetOffControlRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1206 t1206 = new ExecuteT1206();
			InVoT1206 pub = new InVoT1206();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, assetOffControlRq.getBaseVO());
			pub.setTelNo(assetOffControlRq.getTelNo());
			pub.setPayPrimAcct(assetOffControlRq.getPayPrimAcct());
			pub.setPadUpAmt(assetOffControlRq.getPadUpAmt());
			pub.setBizHeader(message);
			t1206.setArg0(pub);
			ExecuteT1206Response response = stub.executeT1206(t1206);
			OutVoT1206 out1206 = response.get_return();
			AssetOffControlRq rs = new AssetOffControlRq();
			BaseVO vo = new BaseVO();
			rs.setPayPrimAcct(out1206.getPayPrimAcct());
			rs.setPadUpAmt(out1206.getPadUpAmt());
			rs.setTelNo(out1206.getTelNo());
			vo.setErrCod(out1206.getDealResultCod());
			vo.setErrMsg(out1206.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 撤销控制信息 
	 */
	@Bizlet("撤销控制信息")
	public LoanCancelControlRq executeT1210(
			LoanCancelControlRq loanCancelControlRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1210 t1210 = new ExecuteT1210();
			InVoT1210 pub = new InVoT1210();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, loanCancelControlRq.getBaseVO());
			pub.setTelNo(loanCancelControlRq.getTelNo());
			pub.setDueNum(loanCancelControlRq.getDueNum());
			pub.setRevStan(loanCancelControlRq.getRevStan());
			pub.setBizHeader(message);
			t1210.setArg0(pub);
			ExecuteT1210Response response = stub.executeT1210(t1210);
			OutVoT1210 out1210 = response.get_return();
			LoanCancelControlRq rs = new LoanCancelControlRq();
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1210.getDealResultCod());
			vo.setErrMsg(out1210.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 贷款出账检查 
	 */
	@Bizlet("贷款出账检查")
	public PayOutCheckRq executeT1401(PayOutCheckRq payOutCheckRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1401 t1401 = new ExecuteT1401();
			InVoT1401 pub = new InVoT1401();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, payOutCheckRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setTelNo(payOutCheckRq.getTelNo());
			pub.setDueNum(payOutCheckRq.getDueNum());
			t1401.setArg0(pub);
			ExecuteT1401Response response = stub.executeT1401(t1401);
			OutVoT1401 out1401 = response.get_return();
			PayOutCheckRq rs = new PayOutCheckRq();
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1401.getDealResultCod());
			bvo.setErrMsg(out1401.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口executeT1401失败：" + e.getMessage());
		}
	
	}
	/*
	 * 还款账号变更 (请求报文对不上)
	 */
	@Bizlet("还款账号变更")
	public RepayAccChangeRq executeT1402(RepayAccChangeRq repayAccChangeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1402 t1402 = new ExecuteT1402();
			InVoT1402 pub = new InVoT1402();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, repayAccChangeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(repayAccChangeRq.getDueNum());
			pub.setPayPrimAcct(repayAccChangeRq.getPayPrimAcct());
			pub.setPayPrimName(repayAccChangeRq.getPayPrimName());
			t1402.setArg0(pub);
			ExecuteT1402Response response = stub.executeT1402(t1402);
			OutVoT1402 out1402 = response.get_return();
			RepayAccChangeRq rs = new RepayAccChangeRq();
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1402.getDealResultCod());
			bvo.setErrMsg(out1402.getDealResultMsg());
			rs.setDueNum(out1402.getDueNum());
			rs.setPayPrimAcct(out1402.getPayPrimAcct());
			rs.setPayPrimName(out1402.getPayPrimName());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}

	}
	/*
	 * 暂停贴息 
	 */
	@Bizlet("暂停贴息")
	public DiscountStopRq executeT1403(DiscountStopRq discountStopRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1403 t1403 = new ExecuteT1403();
			InVoT1403 pub = new InVoT1403();
			PubMessage message = new PubMessage();
			pub.setDueNum(discountStopRq.getDueNum());
			BeanUtils.copyProperties(message, discountStopRq.getBaseVO());
			pub.setBizHeader(message);
			t1403.setArg0(pub);
			ExecuteT1403Response response = stub.executeT1403(t1403);
			OutVoT1403 out1403 = response.get_return();
			DiscountStopRq rs = new DiscountStopRq();
			rs.setDueNum(out1403.getDueNum());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1403.getDealResultCod());
			bvo.setErrMsg(out1403.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 恢复贴息 
	 */
	@Bizlet("恢复贴息")
	public DiscountBackRq executeT1404(DiscountBackRq discountBackRs)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1404 t1404 = new ExecuteT1404();
			InVoT1404 pub = new InVoT1404();
			PubMessage message = new PubMessage();
			pub.setDueNum(discountBackRs.getDueNum());
			BeanUtils.copyProperties(message, discountBackRs.getBaseVO());
			pub.setBizHeader(message);
			t1404.setArg0(pub);
			ExecuteT1404Response response = stub.executeT1404(t1404);
			OutVoT1404 out1404 = response.get_return();
			DiscountBackRq rs = new DiscountBackRq();
			rs.setDueNum(out1404.getDueNum());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1404.getDealResultCod());
			bvo.setErrMsg(out1404.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 还款方式变更(请求报文对不上)
	 */
	@Bizlet("还款方式变更")
	public RepayWayChangeRq executeT1405(RepayWayChangeRq repayWayChangeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1405 t1405 = new ExecuteT1405();
			InVoT1405 pub = new InVoT1405();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, repayWayChangeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setMidTermWay("0");//所有期利息计算方式--除首期、尾期外的各期次0=按照期利率（月/季/半年/年/半月)来计算  ；1=当期按照实际天数来计算(目前传值为"0"，后续有需要就修改)
			pub.setDueNum(repayWayChangeRq.getDueNum());
			pub.setDueNum(repayWayChangeRq.getDueNum());
			pub.setCaspan(repayWayChangeRq.getCaspan());
			pub.setPayDate(repayWayChangeRq.getPayDate());
			pub.setCurPrmPayTyp(repayWayChangeRq.getCurPrmPayTyp());
			pub.setCurAstPayTyp(repayWayChangeRq.getCurAstPayTyp());
			pub.setItrRateWay(repayWayChangeRq.getItrRateWay());
			if(null != repayWayChangeRq.getStgFirstMon()){
				pub.setStgFirstMon((long)repayWayChangeRq.getStgFirstMon());
			}
			pub.setBallMthEndPerd(repayWayChangeRq.getBallMthEndPerd());
			pub.setFrePayMethPayAmt(repayWayChangeRq.getFrePayMethPayAmt());
			if(null != repayWayChangeRq.getFrePayMethDay()){
				pub.setFrePayMethDay((long)repayWayChangeRq.getFrePayMethDay());
			}
			if(null != repayWayChangeRq.getCalDays()){
				pub.setCalDays(repayWayChangeRq.getCalDays());
			}
			pub.setCusPayPlanType(repayWayChangeRq.getCusPayPlanType());
			pub.setPrinPlanFlg(repayWayChangeRq.getPrinPlanFlg());
			pub.setPayItrPlanFlg(repayWayChangeRq.getPayItrPlanFlg());
			t1405.setArg0(pub);
			ExecuteT1405Response response = stub.executeT1405(t1405);
			OutVoT1405 out1405 = response.get_return();
			RepayWayChangeRq rs = new RepayWayChangeRq();
			rs.setDueNum(out1405.getDueNum());
			rs.setCaspan(out1405.getCaspan());
			rs.setPayDate(out1405.getPayDate());
			rs.setCurPrmPayTyp(out1405.getCurPrmPayTyp());
			rs.setCurAstPayTyp(out1405.getCurAstPayTyp());
			rs.setItrRateWay(out1405.getItrRateWay());
			String val = out1405.getStgFirstMon()+"";
			if(null != val || !"null".equals(val)){
			rs.setStgFirstMon((int)out1405.getStgFirstMon());
			}
			rs.setBallMthEndPerd(out1405.getBallMthEndPerd());
			rs.setFrePayMethPayAmt(out1405.getFrePayMethPayAmt());
			String val1 = out1405.getFrePayMethDay()+"";
			if(null != val1 || !"null".equals(val1)){
			rs.setFrePayMethDay((int)out1405.getFrePayMethDay());
			}
			String val2 = out1405.getCalDays()+"";
			if(null != val2 || !"null".equals(val2)){
			rs.setCalDays((int)out1405.getCalDays());
			}
			rs.setCusPayPlanType(out1405.getCusPayPlanType());
			rs.setPrinPlanFlg(out1405.getPrinPlanFlg());
			rs.setPayItrPlanFlg(out1405.getPayItrPlanFlg());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1405.getDealResultCod());
			bvo.setErrMsg(out1405.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 贷款期限变更 (请求报文对不上)
	 */
	@Bizlet("贷款期限变更")
	public CredPeriodChangeRq executeT1406(CredPeriodChangeRq credPeriodChangeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1406 t1406 = new ExecuteT1406();
			InVoT1406 pub = new InVoT1406();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, credPeriodChangeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(credPeriodChangeRq.getDueNum());
			pub.setDueNum(credPeriodChangeRq.getDueNum());
			pub.setEndDate(credPeriodChangeRq.getEndDate());
			pub.setBallMthEndPerd(credPeriodChangeRq.getBallMthEndPerd());
			pub.setBusCod(credPeriodChangeRq.getBusCod());
			pub.setDiscEndDate(credPeriodChangeRq.getDiscEndDate());
			t1406.setArg0(pub);
			ExecuteT1406Response response = stub.executeT1406(t1406);
			OutVoT1406 out1406 = response.get_return();
			CredPeriodChangeRq rs = new CredPeriodChangeRq();
			rs.setDueNum(out1406.getDueNum());
			rs.setEndDate(out1406.getEndDate());
			rs.setBallMthEndPerd(out1406.getBallMthEndPerd());
			rs.setBusCod(out1406.getBusCod());
			rs.setDiscEndDate(out1406.getDiscEndDate());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1406.getDealResultCod());
			bvo.setErrMsg(out1406.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 调整阶段性首次还本期数(请求报文对不上)
	 */
	@Bizlet("调整阶段性首次还本期数")
	public FirstAjustPeriodRq executeT1407(FirstAjustPeriodRq firstAjustPeriodRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1407 t1407 = new ExecuteT1407();
			InVoT1407 pub = new InVoT1407();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, firstAjustPeriodRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(firstAjustPeriodRq.getDueNum());
			pub.setStgFirstMon(firstAjustPeriodRq.getStgFirstMon());
			t1407.setArg0(pub);
			ExecuteT1407Response response = stub.executeT1407(t1407);
			OutVoT1407 out1407 = response.get_return();
			FirstAjustPeriodRq rs = new FirstAjustPeriodRq();
			rs.setDueNum(out1407.getDueNum());
			rs.setStgFirstMon(out1407.getStgFirstMon());
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 调整贴息到期日(请求报文对不上) 
	 */
	@Bizlet("调整贴息到期日")
	public ChangeCredTimeRq executeT1408(ChangeCredTimeRq changeCredTimeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1408 t1408 = new ExecuteT1408();
			InVoT1408 pub = new InVoT1408();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, changeCredTimeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(changeCredTimeRq.getDueNum());
			pub.setDiscEndDate(changeCredTimeRq.getDiscEndDate());
			t1408.setArg0(pub);
			ExecuteT1408Response response = stub.executeT1408(t1408);
			OutVoT1408 out1408 = response.get_return();
			ChangeCredTimeRq rs = new ChangeCredTimeRq();
			rs.setDueNum(out1408.getDueNum());
			rs.setDiscEndDate(out1408.getDiscEndDate());
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 贷款本息查询
	 */
	@Bizlet("贷款本息查询")
	public CrePayQueryRq executeT1410(CrePayQueryRq crePayQueryRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1410 t1410 = new ExecuteT1410();
			InVoT1410 pub = new InVoT1410();
			PubMessage message = new PubMessage();
			pub.setDueNum(crePayQueryRq.getDueNum());
			pub.setToCoreAmt(crePayQueryRq.getRcvPrn());
			BeanUtils.copyProperties(message, crePayQueryRq.getBaseVO());
			pub.setBizHeader(message);
			t1410.setArg0(pub);
			ExecuteT1410Response response = stub.executeT1410(t1410);
			OutVoT1410 out1410 = response.get_return();
			CrePayQueryRq rs = new CrePayQueryRq();
			rs.setResNor(out1410.getResNor());
			rs.setDftPrnBal(out1410.getDftPrnBal());
			rs.setRcvNorItrIn(out1410.getRcvNorItrIn());
			rs.setRcvDftItrIn(out1410.getRcvDftItrIn());
			rs.setRcvPnsItrIn(out1410.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(out1410.getRcvCpdItrIn());
			rs.setAdjOtdPns(out1410.getAdjOtdPns());
			rs.setAdjOtdCpd(out1410.getAdjOtdCpd());
			rs.setCurrPrjPrn(out1410.getCurrPrjPrn());
			rs.setCurrPrjItr(out1410.getCurrPrjItr());
			rs.setPadUpAmt(out1410.getPadUpAmt());
			rs.setTotPrnItr(out1410.getTotPrnItr());
			rs.setDevaSts(out1410.getDevaSts());
			rs.setRcvPrn(out1410.getRcvPrn());
			rs.setPadUpAdjOtdItr(out1410.getPadUpAdjOtdItr());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1410.getDealResultCod());
			vo.setErrMsg(out1410.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}

	}
	/*
	 * 业务拆分申请及机构拆分申请 
	 */
	@Bizlet("业务拆分申请及机构拆分申请")
	public BusiOrgSplitRq executeT1411(BusiOrgSplitRq busiOrgSplitRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1411 t1411 = new ExecuteT1411();
			InVoT1411 pub = new InVoT1411();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, busiOrgSplitRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setExiFlg(busiOrgSplitRq.getExiFlg());
			pub.setExdBegDate(busiOrgSplitRq.getExdBegDate());
			t1411.setArg0(pub);
			ExecuteT1411Response response = stub.executeT1411(t1411);
			OutVoT1411 out1411 = response.get_return();
			BusiOrgSplitRq rs = new BusiOrgSplitRq();
			rs.setExiFlg(out1411.getExiFlg());
			rs.setExdBegDate(out1411.getExdBegDate());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1411.getDealResultCod());
			vo.setErrMsg(out1411.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 展期申请控制信息(请求报文对不上) 
	 */
	@Bizlet("展期申请控制信息")
	public ExtendTimeAppRq executeT1412(ExtendTimeAppRq extendTimeAppRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1412 t1412 = new ExecuteT1412();
			InVoT1412 pub = new InVoT1412();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, extendTimeAppRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(extendTimeAppRq.getDueNum());
			pub.setBusCod(extendTimeAppRq.getBusCod());
			pub.setExdBegDate(extendTimeAppRq.getExdBegDate());
			pub.setExdEndDate(extendTimeAppRq.getExdEndDate());
			pub.setNorItrRate(extendTimeAppRq.getNorItrRate());
			pub.setDelItrRate(extendTimeAppRq.getDelItrRate());
			pub.setCpdItrRate(extendTimeAppRq.getCpdItrRate());
			pub.setDiscEndDate(extendTimeAppRq.getDiscEndDate());
			pub.setPrinPlanFlg(extendTimeAppRq.getPrinPlanFlg());
			t1412.setArg0(pub);
			ExecuteT1412Response response = stub.executeT1412(t1412);
			OutVoT1412 out1412 = response.get_return();
			ExtendTimeAppRq rs = new ExtendTimeAppRq();
			rs.setDueNum(out1412.getDueNum());
			rs.setBusCod(out1412.getBusCod());
			rs.setExdBegDate(out1412.getExdBegDate());
			rs.setExdEndDate(out1412.getExdEndDate());
			rs.setNorItrRate(out1412.getNorItrRate());
			rs.setDelItrRate(out1412.getDelItrRate());
			rs.setCpdItrRate(out1412.getCpdItrRate());
			rs.setDiscEndDate(out1412.getDiscEndDate());
			rs.setPrinPlanFlg(out1412.getPrinPlanFlg());
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1412.getDealResultCod());
			vo.setErrMsg(out1412.getDealResultMsg());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 查询贷款还款计划表 (请求报文对不上和报文体都不对) 
	 */
	@Bizlet("查询贷款还款计划表")
	public QueryCredPayPlanRq executeT1413(QueryCredPayPlanRq queryCredPayPlanRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1413 t1413 = new ExecuteT1413();
			InVoT1413 pub = new InVoT1413();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, queryCredPayPlanRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(queryCredPayPlanRq.getDueNum());
			pub.setAmt(queryCredPayPlanRq.getAmt());
			pub.setBegDate(queryCredPayPlanRq.getBegDate());
			pub.setEndDate(queryCredPayPlanRq.getEndDate());
			pub.setNorItrRate(queryCredPayPlanRq.getNorItrRate());
			pub.setCaspan(queryCredPayPlanRq.getCaspan());
			pub.setItrCalRule(queryCredPayPlanRq.getItrCalRule());
			pub.setPayDate(queryCredPayPlanRq.getPayDate());
			pub.setCurPrmPayTyp(queryCredPayPlanRq.getCurPrmPayTyp());
			pub.setCurAstPayTyp(queryCredPayPlanRq.getCurAstPayTyp());
			Integer aa=queryCredPayPlanRq.getStgFirstMon();
			if(aa!=null){
				String stg_first_mon=aa.toString();
			pub.setStgFirstMon(Long.parseLong(stg_first_mon));
			}
			
			pub.setItrRateWay(queryCredPayPlanRq.getItrRateWay());
			pub.setExiFlg(queryCredPayPlanRq.getExiFlg());
			pub.setPrmCls(queryCredPayPlanRq.getPrmCls());
			pub.setAstCls(queryCredPayPlanRq.getAstCls());
			t1413.setArg0(pub);
			ExecuteT1413Response response = stub.executeT1413(t1413);
			OutVoT1413 out1413 = response.get_return();
			QueryCredPayPlanRq rs = new QueryCredPayPlanRq();
			BaseVO vo = new BaseVO();
			vo.setErrCod(out1413.getDealResultCod());
			vo.setErrMsg(out1413.getDealResultMsg());
		    PubMessage pubm=out1413.getBizHeader();
		    rs.setRltFile(pubm.getReqFile());
		    rs.setRltFileDir(pubm.getReqFileDir());
			rs.setBaseVO(vo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 还本计划变更控制信息 (请求报文对不上) 
	 */
	@Bizlet("还本计划变更控制信息")
	public RepayPlanChangeRq executeT1415(RepayPlanChangeRq repayPlanChangeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1415 t1415 = new ExecuteT1415();
			InVoT1415 pub = new InVoT1415();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, repayPlanChangeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(repayPlanChangeRq.getDueNum());
			pub.setTelNo(repayPlanChangeRq.getTelNo());
			pub.setPrinPlanFlg(repayPlanChangeRq.getPrinPlanFlg());
			t1415.setArg0(pub);
			ExecuteT1415Response response = stub.executeT1415(t1415);
			OutVoT1415 out1415 = response.get_return();
			RepayPlanChangeRq rs = new RepayPlanChangeRq();
			rs.setPrinPlanFlg(out1415.getPrinPlanFlg());
			BaseVO bvo = new BaseVO();
			bvo.setErrCod(out1415.getDealResultCod());
			bvo.setErrMsg(out1415.getDealResultMsg());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	/*
	 * 还息计划变更控制信息 
	 */
	@Bizlet("还息计划变更控制信息")
	public DuePlanChangeRq executeT1417(DuePlanChangeRq duePlanChangeRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1417 t1417 = new ExecuteT1417();
			InVoT1417 pub = new InVoT1417();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, duePlanChangeRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(duePlanChangeRq.getDueNum());
			pub.setTelNo(duePlanChangeRq.getTelNo());
			pub.setPrinPlanFlg(duePlanChangeRq.getPrinPlanFlg());
			t1417.setArg0(pub);
			ExecuteT1417Response response = stub.executeT1417(t1417);
			OutVoT1417 out1417 = response.get_return();
			DuePlanChangeRq rs = new DuePlanChangeRq();
			rs.setDueNum(out1417.getDueNum());
			rs.setTelNo(out1417.getTelNo());
			rs.setPrinPlanFlg(out1417.getPrinPlanFlg());
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	
	}
	/*
	 * 本金归还完成后调整累计利息标志 
	 */
	@Bizlet("本金归还完成后调整累计利息标志")
	public MoneyPayOffRq executeT1418(MoneyPayOffRq moneyPayOffRq)
			throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1418 t1418 = new ExecuteT1418();
			InVoT1418 pub = new InVoT1418();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, moneyPayOffRq.getBaseVO());
			pub.setBizHeader(message);
			pub.setDueNum(moneyPayOffRq.getDueNum());
			t1418.setArg0(pub);
			ExecuteT1418Response response = stub.executeT1418(t1418);
			OutVoT1418 out1418 = response.get_return();
			MoneyPayOffRq rs = new MoneyPayOffRq();
			rs.setDueNum(out1418.getDueNum());
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
		
	}
	
	/*
	 * 通用记账
	 */
	@Bizlet("通用记账")
	public OXD012_AccoutingRes executeXD01(OXD011_AccoutingReq requestBody) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();
		OXD012_AccoutingRes oxd012Res = new OXD012_AccoutingRes();
		oxd012Res = impl.executeXD01(requestBody);
		return oxd012Res;
	}

	//结清试算
	@Bizlet("结清试算")
	public OffCaculate executeT1100(OffCaculate offCaculate) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1100  t1100 = new ExecuteT1100();
			InVoT1100 invo1100 = new InVoT1100();
			PubMessage message = new PubMessage();
			BeanUtils.copyProperties(message, offCaculate.getBaseVO());
			invo1100.setBizHeader(message);
			invo1100.setDueNum(offCaculate.getDueNum());
			t1100.setArg0(invo1100);
			ExecuteT1100Response response = stub.executeT1100(t1100);
			OutVoT1100 outvo1100 = response.get_return();
			OffCaculate rs = new OffCaculate();
			rs.setBrwName(outvo1100.getBrwName());
			rs.setNorItrRate(outvo1100.getNorItrRate());
			rs.setDelItrRate(outvo1100.getDelItrRate());
			rs.setBegDate(outvo1100.getBegDate());
			rs.setEndDate(outvo1100.getEndDate());
			rs.setRcvPrn(outvo1100.getRcvPrn());
			rs.setRcvNorItrIn(outvo1100.getRcvNorItrIn());
			rs.setRcvDftItrIn(outvo1100.getRcvDftItrIn());
			rs.setRcvPnsItrIn(outvo1100.getRcvPnsItrIn());
			rs.setRcvCpdItrIn(outvo1100.getRcvCpdItrIn());
			rs.setTotPrnItr(outvo1100.getTotPrnItr());
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口executeT1100失败：" + e.getMessage());
		}
		
	}
	//借新还旧
	@Bizlet("借新还旧")
	public RepayOldNewRq executeT1115(RepayOldNewRq repayOldNewRq)
			throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1115  t1115 = new ExecuteT1115();
		InVoT1115 pub = new InVoT1115();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, repayOldNewRq.getBaseVO());
		pub.setBizHeader(message);
		pub.setDueNum(repayOldNewRq.getDueNum());
		pub.setTelNo(repayOldNewRq.getTelNo());
		pub.setBrwName(repayOldNewRq.getBrwName());
		pub.setConNo(repayOldNewRq.getConNo());
		pub.setProdCod(repayOldNewRq.getProdCod());
		pub.setPrimAcct(repayOldNewRq.getPrimAcct());
		pub.setPrimAcctName(repayOldNewRq.getPrimAcctName());
		pub.setPayPrimAcct(repayOldNewRq.getPayPrimAcct());
		pub.setPayPrimName(repayOldNewRq.getPayPrimName());
		pub.setCurrCod(repayOldNewRq.getCurrCod());
		pub.setPadUpAmt(repayOldNewRq.getPadUpAmt());
		pub.setNorItrRate(repayOldNewRq.getNorItrRate());
		pub.setDelItrRate(repayOldNewRq.getDelItrRate());
		pub.setBegDate(repayOldNewRq.getBegDate());
		pub.setEndDate(repayOldNewRq.getEndDate());
		pub.setCurPrmPayTyp(repayOldNewRq.getCurPrmPayTyp());
		pub.setCurAstPayTyp(repayOldNewRq.getCurAstPayTyp());
		pub.setCaspan(repayOldNewRq.getCaspan());
		pub.setPayDate(repayOldNewRq.getPayDate());
		pub.setNextProvDate(repayOldNewRq.getNextProvDate());
		if(null != repayOldNewRq.getStgFirstMon()){
			pub.setStgFirstMon(repayOldNewRq.getStgFirstMon());
		}
		pub.setDiscType(repayOldNewRq.getDiscType());
		pub.setDueNumSun(repayOldNewRq.getDueNumSun());
		pub.setPayOpenDep(repayOldNewRq.getPayOpenDep());
		pub.setPayPrimAcctTyp(repayOldNewRq.getPayPrimAcctTyp());
		pub.setPayOrder(repayOldNewRq.getPayOrder());
		pub.setRcvPrn(repayOldNewRq.getRcvPrn());
		pub.setRcvNorItrIn(repayOldNewRq.getRcvNorItrIn());
		pub.setRcvDftItrIn(repayOldNewRq.getRcvDftItrIn());
		pub.setRcvPnsItrIn(repayOldNewRq.getRcvPnsItrIn());
		pub.setRcvCpdItrIn(repayOldNewRq.getRcvCpdItrIn());
		pub.setPadUpPrn(repayOldNewRq.getPadUpPrn());
		pub.setPadUpNorItrIn(repayOldNewRq.getPadUpNorItrIn());
		pub.setPadUpDftItrIn(repayOldNewRq.getPadUpDftItrIn());
		pub.setPadUpPnsItrIn(repayOldNewRq.getPadUpPnsItrIn());
		pub.setPadUpCpdItrIn(repayOldNewRq.getPadUpCpdItrIn());
		pub.setPadUpPentIcm(repayOldNewRq.getPadUpPentIcm());
		pub.setPrvCod(repayOldNewRq.getPrvCod());
		pub.setRlsDep(repayOldNewRq.getRlsDep());
		pub.setBegItrDate(repayOldNewRq.getBegItrDate());
		pub.setForwProvDate(repayOldNewRq.getForwProvDate());
		pub.setTalDep(repayOldNewRq.getTalDep());
		pub.setPayOutItrFlg(repayOldNewRq.getPayOutItrFlg());
		t1115.setArg0(pub);
		ExecuteT1115Response response = stub.executeT1115(t1115);
		OutVoT1115 outvo1115 = response.get_return();
		RepayOldNewRq rs = new RepayOldNewRq();
		rs.setDueNum(outvo1115.getDueNum());
		rs.setTelNo(outvo1115.getTelNo());
		rs.setBrwName(outvo1115.getBrwName());
		rs.setConNo(outvo1115.getConNo());
		rs.setProdCod(outvo1115.getProdCod());
		rs.setPrimAcct(outvo1115.getPrimAcct());
		rs.setPrimAcctName(outvo1115.getPrimAcctName());
		rs.setPayPrimAcct(outvo1115.getPayPrimAcct());
		rs.setPayPrimName(outvo1115.getPayPrimName());
		rs.setCurrCod(outvo1115.getCurrCod());
		rs.setPadUpAmt(outvo1115.getPadUpAmt());
		rs.setNorItrRate(outvo1115.getNorItrRate());
		rs.setDelItrRate(outvo1115.getDelItrRate());
		rs.setBegDate(outvo1115.getBegDate());
		rs.setEndDate(outvo1115.getEndDate());
		rs.setCurPrmPayTyp(outvo1115.getCurPrmPayTyp());
		rs.setCurAstPayTyp(outvo1115.getCurAstPayTyp());
		rs.setCaspan(outvo1115.getCaspan());
		rs.setPayDate(outvo1115.getPayDate());
		rs.setNextProvDate(outvo1115.getNextProvDate());
		rs.setStgFirstMon(outvo1115.getStgFirstMon());
		rs.setDiscType(outvo1115.getDiscType());
		rs.setDueNumSun(outvo1115.getDueNumSun());
		rs.setPayOpenDep(outvo1115.getPayOpenDep());
		rs.setPayPrimAcctTyp(outvo1115.getPayPrimAcctTyp());
		rs.setPayOrder(outvo1115.getPayOrder());
		rs.setRcvPrn(outvo1115.getRcvPrn());
		rs.setRcvNorItrIn(outvo1115.getRcvNorItrIn());
		rs.setRcvDftItrIn(outvo1115.getRcvDftItrIn());
		rs.setRcvPnsItrIn(outvo1115.getRcvPnsItrIn());
		rs.setRcvCpdItrIn(outvo1115.getRcvCpdItrIn());
		rs.setPadUpPrn(outvo1115.getPadUpPrn());
		rs.setPadUpNorItrIn(outvo1115.getPadUpNorItrIn());
		rs.setPadUpDftItrIn(outvo1115.getPadUpDftItrIn());
		rs.setPadUpPnsItrIn(outvo1115.getPadUpPnsItrIn());
		rs.setPadUpCpdItrIn(outvo1115.getPadUpCpdItrIn());
		rs.setPadUpPentIcm(outvo1115.getPadUpPentIcm());
		rs.setPrvCod(outvo1115.getPrvCod());
		rs.setRlsDep(outvo1115.getRlsDep());
		rs.setBegItrDate(outvo1115.getBegItrDate());
		rs.setForwProvDate(outvo1115.getForwProvDate());
		rs.setTalDep(outvo1115.getTalDep());
		rs.setAccEntJson(outvo1115.getAccEntJson());
		rs.setPayOutItrFlg(outvo1115.getPayOutItrFlg());
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1115.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1115.getDealResultCod());
		bvo.setErrMsg(outvo1115.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//逾期展期
	@Bizlet("逾期展期")
	public DelayTime executeT1419(DelayTime delayTime) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1419  t1419 = new ExecuteT1419();
		InVoT1419 pub = new InVoT1419();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, delayTime.getBaseVO());
		pub.setBizHeader(message);
		pub.setDueNum(delayTime.getDueNum());
		pub.setBusCod(delayTime.getBusCod());
		pub.setExdBegDate(delayTime.getExdBegDate());
		pub.setExdEndDate(delayTime.getExdEndDate());
		pub.setNorItrRate(delayTime.getNorItrRate());
		pub.setDelItrRate(delayTime.getDelItrRate());
		pub.setCpdItrRate(delayTime.getCpdItrRate());
		pub.setDiscEndDate(delayTime.getDiscEndDate());
		pub.setPrinPlanFlg(delayTime.getPrinPlanFlg());
		pub.setPayItrPlanFlg(delayTime.getPayItrPlanFlg());
		t1419.setArg0(pub);
		ExecuteT1419Response response = stub.executeT1419(t1419);
		OutVoT1419 outvo1419 = response.get_return();
		DelayTime rs = new DelayTime();
		rs.setDueNum(outvo1419.getDueNum());
		rs.setBusCod(outvo1419.getBusCod());
		rs.setExdBegDate(outvo1419.getExdBegDate());
		rs.setExdEndDate(outvo1419.getExdEndDate());
		rs.setNorItrRate(outvo1419.getNorItrRate());
		rs.setDelItrRate(outvo1419.getDelItrRate());
		rs.setCpdItrRate(outvo1419.getCpdItrRate());
		rs.setDiscEndDate(outvo1419.getDiscEndDate());
		rs.setPrinPlanFlg(outvo1419.getPrinPlanFlg());
		rs.setPayItrPlanFlg(outvo1419.getPayItrPlanFlg());
		BaseVO bvo = new BaseVO();
		bvo.setErrCod(outvo1419.getDealResultCod());
		bvo.setErrMsg(outvo1419.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//放款清单
	@Bizlet("放款清单")
	public CredMenu executeT1302(CredMenu credMenu) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1302  t1302 = new ExecuteT1302();
		InVoT1302 pub = new InVoT1302();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, credMenu.getBaseVO());
		pub.setDueNum(credMenu.getDueNum());
		pub.setBegDate(credMenu.getBegDate());
		pub.setEndDate(credMenu.getEndDate());
		pub.setBizHeader(message);
		t1302.setArg0(pub);
		ExecuteT1302Response response = stub.executeT1302(t1302);
		OutVoT1302 outvo1302 = response.get_return();
		CredMenu rs = new CredMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1302.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1302.getDealResultCod());
		bvo.setErrMsg(outvo1302.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//还款清单
	@Bizlet("还款清单")
	public RepayMenu executeT1303(RepayMenu repayMenu) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1303  t1303 = new ExecuteT1303();
		InVoT1303 pub = new InVoT1303();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, repayMenu.getBaseVO());
		pub.setDueNum(repayMenu.getDueNum());
		pub.setBegDate(repayMenu.getBegDate());
		pub.setEndDate(repayMenu.getEndDate());
		pub.setBizHeader(message);
		t1303.setArg0(pub);
		ExecuteT1303Response response = stub.executeT1303(t1303);
		OutVoT1303 outvo1303 = response.get_return();
		RepayMenu rs = new RepayMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1303.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1303.getDealResultCod());
		bvo.setErrMsg(outvo1303.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//结息清单
	@Bizlet("结息清单")
	public InterSettMenu executeT1304(InterSettMenu interSettMenu)
			throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1304  t1304 = new ExecuteT1304();
		InVoT1304 pub = new InVoT1304();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, interSettMenu.getBaseVO());
		pub.setDueNum(interSettMenu.getDueNum());
		pub.setBegDate(interSettMenu.getBegDate());
		pub.setEndDate(interSettMenu.getEndDate());
		pub.setBizHeader(message);
		t1304.setArg0(pub);
		ExecuteT1304Response response = stub.executeT1304(t1304);
		OutVoT1304 outvo1304 = response.get_return();
		InterSettMenu rs = new InterSettMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1304.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1304.getDealResultCod());
		bvo.setErrMsg(outvo1304.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//结清证明结果
	@Bizlet("结清证明结果")
	public SettleMenu executeT1305(SettleMenu settleMenu) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1305  t1305 = new ExecuteT1305();
		InVoT1305 pub = new InVoT1305();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, settleMenu.getBaseVO());
		pub.setDueNum(settleMenu.getDueNum());
		pub.setBegDate(settleMenu.getBegDate());
		pub.setEndDate(settleMenu.getEndDate());
		pub.setBizHeader(message);
		t1305.setArg0(pub);
		ExecuteT1305Response response = stub.executeT1305(t1305);
		OutVoT1305 outvo1305 = response.get_return();
		SettleMenu rs = new SettleMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1305.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1305.getDealResultCod());
		bvo.setErrMsg(outvo1305.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//计提减值结果清单
	@Bizlet("计提减值结果清单")
	public ImpproviMenu executeT1306(ImpproviMenu impproviMenu)
			throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1306  t1306 = new ExecuteT1306();
		InVoT1306 pub = new InVoT1306();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, impproviMenu.getBaseVO());
		pub.setDueNum(impproviMenu.getDueNum());
		pub.setBegDate(impproviMenu.getBegDate());
		pub.setEndDate(impproviMenu.getEndDate());
		pub.setBusCod(impproviMenu.getBusCod());
		pub.setBizHeader(message);
		t1306.setArg0(pub);
		ExecuteT1306Response response = stub.executeT1306(t1306);
		OutVoT1306 outvo1306 = response.get_return();
		ImpproviMenu rs = new ImpproviMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1306.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1306.getDealResultCod());
		bvo.setErrMsg(outvo1306.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//贴息明细清单
	@Bizlet("贴息明细清单")
	public PayInterMenu executeT1308(PayInterMenu payInterMenu)
			throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1308  t1308 = new ExecuteT1308();
		InVoT1308 pub = new InVoT1308();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, payInterMenu.getBaseVO());
		pub.setDueNum(payInterMenu.getDueNum());
		pub.setBegDate(payInterMenu.getBegDate());
		pub.setEndDate(payInterMenu.getEndDate());
		pub.setBizHeader(message);
		t1308.setArg0(pub);
		ExecuteT1308Response response = stub.executeT1308(t1308);
		OutVoT1308 outvo1308 = response.get_return();
		PayInterMenu rs = new PayInterMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1308.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1308.getDealResultCod());
		bvo.setErrMsg(outvo1308.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//凭证补打
	@Bizlet("凭证补打")
	public VouComMenu executeT1301(VouComMenu vouComMenu) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteT1301  t1301 = new ExecuteT1301();
		InVoT1301 pub = new InVoT1301();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, vouComMenu.getBaseVO());
		pub.setDueNum(vouComMenu.getDueNum());
		pub.setBegDate(vouComMenu.getBegDate());
		pub.setEndDate(vouComMenu.getEndDate());
		pub.setPrnTyp(vouComMenu.getPrnTyp());
		pub.setBizHeader(message);
		t1301.setArg0(pub);
		ExecuteT1301Response response = stub.executeT1301(t1301);
		OutVoT1301 outvo1301 = response.get_return();
		VouComMenu rs = new VouComMenu();
		BaseVO bvo = new BaseVO();
		PubMessage bizHeader = outvo1301.getBizHeader();
		if(null != bizHeader){
			bvo.setTranCod(bizHeader.getTranCod());
			bvo.setLegPerCod(bizHeader.getLegPerCod());
			bvo.setOpnDep(bizHeader.getOpnDep());
			bvo.setTrnDep(bizHeader.getTrnDep());
			bvo.setDepCod(bizHeader.getDepCod());
			bvo.setOrigFrom(bizHeader.getOrigFrom());
			bvo.setTranFrom(bizHeader.getTranFrom());
			bvo.setTranDate(bizHeader.getTranDate());
			bvo.setAccSysDate(bizHeader.getAccSysDate());
			bvo.setAcsMethStan(bizHeader.getAcsMethStan());
			bvo.setSupStan(bizHeader.getSupStan());
			bvo.setRcnStan(bizHeader.getRcnStan());
			bvo.setOrigStan(bizHeader.getOrigStan());
			bvo.setTranTimes(bizHeader.getTranTimes());
			bvo.setToCoreSys(bizHeader.getToCoreSys());
			bvo.setReqCnt(bizHeader.getReqCnt());
			bvo.setReqFile(bizHeader.getReqFile());
			bvo.setReqFileDir(bizHeader.getReqFileDir());
			bvo.setRltCnt(bizHeader.getRltCnt());
			bvo.setRltFile(bizHeader.getRltFile());
			bvo.setRltFileDir(bizHeader.getRltFileDir());
			bvo.setOpr(bizHeader.getOpr());
			bvo.setAut(bizHeader.getAut());
		}
		bvo.setErrCod(outvo1301.getDealResultCod());
		bvo.setErrMsg(outvo1301.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	//放款撤销
	public RepayCancel executeB1101(RepayCancel repayCancel) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteB1101  t1101 = new ExecuteB1101();
		InVoB1101 pub = new InVoB1101();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, repayCancel.getBaseVO());
		pub.setDueNum(repayCancel.getDueNum());
		pub.setOrigStan(repayCancel.getOrigStan());
		pub.setBizHeader(message);
		t1101.setArg0(pub);
		ExecuteB1101Response response = stub.executeB1101(t1101);
		OutVoB1101 outvoB1101 = response.get_return();
		RepayCancel rs = new RepayCancel();
		BaseVO bvo = new BaseVO();
		bvo.setErrCod(outvoB1101.getDealResultCod());
		bvo.setErrMsg(outvoB1101.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	
	//还款控制信息撤销
	@Bizlet("还款控制信息撤销")
	public RepayControlCancel executeB1102(RepayControlCancel repayControlCancel) throws Exception {
		stub = new WebServiceGlToHsImplStub(getUrl());
		ExecuteB1102  t1102 = new ExecuteB1102();
		InVoB1102 pub = new InVoB1102();
		PubMessage message = new PubMessage();
		BeanUtils.copyProperties(message, repayControlCancel.getBaseVO());
		pub.setDueNum(repayControlCancel.getDueNum());
		pub.setTelNo(repayControlCancel.getTelNo());
		pub.setBizHeader(message);
		t1102.setArg0(pub);
		ExecuteB1102Response response = stub.executeB1102(t1102);
		OutVoB1102 outvoB1102 = response.get_return();
		RepayControlCancel rs = new RepayControlCancel();
		BaseVO bvo = new BaseVO();
		bvo.setErrCod(outvoB1102.getDealResultCod());
		bvo.setErrMsg(outvoB1102.getDealResultMsg());
		rs.setBaseVO(bvo);
		return rs;
	}
	
	
	//抵债资产冲销
	public AssetOffRq executeT1106(AssetOffRq assetOffRq) throws Exception {
		try {
			stub = new WebServiceGlToHsImplStub(getUrl());
			ExecuteT1106 t1106 = new ExecuteT1106();
			InVoT1106 pub = new InVoT1106();
			PubMessage message = new PubMessage();
			pub.setTelNO(assetOffRq.getTelNo());
			pub.setPayPrimAcct(assetOffRq.getPayPrimAcct());
			pub.setAccEntJson(assetOffRq.getAccJson());
			BeanUtils.copyProperties(message, assetOffRq.getBaseVO());
			pub.setBizHeader(message);
			t1106.setArg0(pub);
			ExecuteT1106Response response = stub.executeT1106(t1106);
			OutVoT1106 out1106 = response.get_return();
			AssetOffRq rs = new AssetOffRq();
			BaseVO bvo = new BaseVO();
			PubMessage bizHeader = out1106.getBizHeader();
			if(null != bizHeader){
				bvo.setTranCod(bizHeader.getTranCod());
				bvo.setLegPerCod(bizHeader.getLegPerCod());
				bvo.setOpnDep(bizHeader.getOpnDep());
				bvo.setTrnDep(bizHeader.getTrnDep());
				bvo.setDepCod(bizHeader.getDepCod());
				bvo.setOrigFrom(bizHeader.getOrigFrom());
				bvo.setTranFrom(bizHeader.getTranFrom());
				bvo.setTranDate(bizHeader.getTranDate());
				bvo.setAccSysDate(bizHeader.getAccSysDate());
				bvo.setAcsMethStan(bizHeader.getAcsMethStan());
				bvo.setSupStan(bizHeader.getSupStan());
				bvo.setRcnStan(bizHeader.getRcnStan());
				bvo.setOrigStan(bizHeader.getOrigStan());
				bvo.setTranTimes(bizHeader.getTranTimes());
				bvo.setToCoreSys(bizHeader.getToCoreSys());
				bvo.setReqCnt(bizHeader.getReqCnt());
				bvo.setReqFile(bizHeader.getReqFile());
				bvo.setReqFileDir(bizHeader.getReqFileDir());
				bvo.setRltCnt(bizHeader.getRltCnt());
				bvo.setRltFile(bizHeader.getRltFile());
				bvo.setRltFileDir(bizHeader.getRltFileDir());
				bvo.setOpr(bizHeader.getOpr());
				bvo.setAut(bizHeader.getAut());
			}
			bvo.setErrCod(out1106.getDealResultCod());
			bvo.setErrMsg(out1106.getDealResultMsg());
			rs.setAccJson(out1106.getAccEntJson());
			rs.setBaseVO(bvo);
			return rs;
		} catch (Exception e) {
			throw new Exception("调用核算接口失败：" + e.getMessage());
		}
	}
	
	
	public static BaseVO createAplusBaseVO(String tranCod, String orgNum, Long rcnStan) {
		BaseVO bvo = new BaseVO();
		bvo.setTranCod(tranCod.toString());
		bvo.setTranFrom(DictContents.APLUS_TRAN_FROM);
		bvo.setOrigFrom(DictContents.APLUS_ORIG_FROM);
		bvo.setLegPerCod(DictContents.ORG_MCCB);
		bvo.setToCoreSys("0");
		bvo.setTranTimes("1");

		bvo.setOpr(GitUtil.getCurrentUserId());
		bvo.setAut(GitUtil.getCurrentUserId());
		bvo.setDepCod(orgNum);
		bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
		if (rcnStan == null) {
			rcnStan = Long.valueOf(BizNumGenerator.getLcsStan());
		}
		bvo.setAcsMethStan(rcnStan);
		bvo.setRcnStan(rcnStan);
		bvo.setTrnDep(orgNum);
		bvo.setOpnDep(orgNum);
		return bvo;
	}
}
