package com.primeton.tsl.ecif.port.impl;

import java.text.SimpleDateFormat;

import org.apache.axis2.engine.ListenerManager;
import org.apache.commons.beanutils.BeanUtils;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.exception.EOSException;
import com.primeton.mgrcore.client.DateTools;
import com.primeton.tsl.ecif.S00601120005491ServiceStub;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_IN;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.S00601120005491Response;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_IN;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.S0110101000A011Response;
import com.primeton.tsl.ecif.S0110101000A101ServiceStub;
import com.primeton.tsl.ecif.S0110101000A101ServiceStub.FMT_CRMS_SVR_S0110101000A101_IN;
import com.primeton.tsl.ecif.S0110101000A101ServiceStub.S0110101000A101Response;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub.FMT_CRMS_SVR_S0110101000A102_IN;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub.S0110101000A102Response;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.FMT_CRMS_SVR_S0110101000A107_IN;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.S0110101000A107Response;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub.FMT_CRMS_SVR_S0110101000A108_IN;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub.S0110101000A108Response;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_IN;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.S0110101000A207Response;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_IN;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.S0110101000A208Response;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.FMT_CRMS_SVR_S0110101000A221_IN;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.S0110101000A221Response;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub.FMT_CRMS_SVR_S0110101000A222_IN;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub.S0110101000A222Response;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.S0110102000B011Response;
import com.primeton.tsl.ecif.S0110102000B012ServiceStub;
import com.primeton.tsl.ecif.S0110102000B012ServiceStub.FMT_CRMS_SVR_S0110102000B012_IN;
import com.primeton.tsl.ecif.S0110102000B012ServiceStub.S0110102000B012Response;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub.FMT_CRMS_SVR_S0110102000B014_IN;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub.S0110102000B014Response;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.FMT_CRMS_SVR_S0110102000B101_IN;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.S0110102000B101Response;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub.FMT_CRMS_SVR_S0110102000B110_IN;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub.S0110102000B110Response;
import com.primeton.tsl.ecif.S0110102000B111ServiceStub;
import com.primeton.tsl.ecif.S0110102000B111ServiceStub.FMT_CRMS_SVR_S0110102000B111_IN;
import com.primeton.tsl.ecif.S0110102000B111ServiceStub.S0110102000B111Response;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub.FMT_CRMS_SVR_S0110102000B112_IN;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub.S0110102000B112Response;
import com.primeton.tsl.ecif.S0110102000B113ServiceStub;
import com.primeton.tsl.ecif.S0110102000B113ServiceStub.FMT_CRMS_SVR_S0110102000B113_IN;
import com.primeton.tsl.ecif.S0110102000B113ServiceStub.S0110102000B113Response;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_IN;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.S0110102000B210Response;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.FMT_CRMS_SVR_S0110102000B211_IN;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.S0110102000B211Response;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_IN;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.S0110102000B212Response;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.FMT_CRMS_SVR_S0110102000B213_IN;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.S0110102000B213Response;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub.FMT_CRMS_SVR_S0110102000B221_IN;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub.S0110102000B221Response;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.FMT_CRMS_SVR_S0110102000B222_IN;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.S0110102000B222Response;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub.FMT_CRMS_SVR_S0110102000B223_IN;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub.S0110102000B223Response;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub.FMT_CRMS_SVR_S0110102000B224_IN;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub.S0110102000B224Response;
import com.primeton.tsl.ecif.port.CommReqTranHeader;
import com.primeton.tsl.ecif.port.CommRequestHeader;
import com.primeton.tsl.ecif.port.IcustEcif;

/**
 * @author 唐良强
 *
 * 2017年4月18日
 *对私客户ECIF接口实现
 */
public class CustEcifImpl implements IcustEcif{
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
	private void initESBRequestHeader(CommRequestHeader requestHeader,
			CommReqTranHeader reqTranHeader, String serviceCode, String orgNum,
			String userNum) throws EOSException {
		requestHeader.setVersionNo("ESB0001-0001"); // 版本号
		requestHeader.setReqSysCode("01601"); // 请求方系统代码
		requestHeader.setReqSecCode(""); // 安全节点号
		requestHeader.setTxType("RQ"); // RQ
		requestHeader.setTxMode("0"); // 0-正常 1-冲销2-冲正 3-重发
		requestHeader.setTxCode(serviceCode); // soapenv服务码
		String busidate=GitUtil.getBusiDateYYYYMMDD();
		requestHeader.setReqDate(busidate); // 业务日期
		String time =new SimpleDateFormat("yyyyMMddhhmmss").format(GitUtil.getBusiDate());
		requestHeader.setReqTime(time); // 机器时间戳
		System.out.println("==============机器时间戳:===="+time);
		String reqSeqNo =DateTools.getReqSeqNo();
		System.out.println( "===============请求方交易流水号================="+reqSeqNo);
		requestHeader.setReqSeqNo(reqSeqNo); // 请求方交易流水号
		requestHeader.setChanlNo("13"); // 渠道号（字符）
/*		requestHeader.setBrch("0313"); // 机构编号
*///		requestHeader.setBrch(orgNum); // 机构编号
//		if (StringUtils.isEmpty(orgNum)) {
//			requestHeader.setBrch(GitUtil.getCurrentOrgCd()); // 机构编号
//		}
		requestHeader.setTermNo(""); // 终端号
//		requestHeader.setOper(userNum); // 柜员
		if(orgNum==null||"".equals(orgNum)){
			orgNum=GitUtil.getOrgCode();
		}
		String accOrgCode=GitUtil.getAccOrg(orgNum);
		requestHeader.setBrch(accOrgCode); // 机构编号
		requestHeader.setOper(GitUtil.getNumOrg(accOrgCode)); // 柜员
//		if (StringUtils.isEmpty(userNum)) {
//			requestHeader.setOper(GitUtil.getCurrentPositionCode()); // 柜员
//		}
		requestHeader.setSendFileName(""); // 发送文件名
		requestHeader.setBeginRec(""); // 开始记录数
		requestHeader.setMaxRec(30); // 一次查询最大记录数
		requestHeader.setFileHMac(""); // 文件摘要
		requestHeader.setHMac(""); // 报文摘要

		reqTranHeader.setHPinSeed("");// PIN种子
		reqTranHeader.setHOriChnl("");// 源渠道
		reqTranHeader.setHSecFlag("0");// 安全标志
		reqTranHeader.setHPwdFlag("0");// 加密标志
		reqTranHeader.setHCombFlag("0");// 组合标志
		reqTranHeader.setHSvcInfo("zuhejy_01");// 服务信息
		reqTranHeader.setHSecInfoVerNo("");// 安全信息版本号
		reqTranHeader.setHSysChnl("");// 渠道号
		reqTranHeader.setHLegaObj("9999");// 责任承担者
		reqTranHeader.setHMsgRefNo("");// 消息参考号
		reqTranHeader.setHTermNo("");// 终端号
		reqTranHeader.setHCityCd("");// 城市代码
//		reqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());// 发送方机构ID
//		reqTranHeader.setHUserID(GitUtil.getCurrentUserId());// 服务请求者
		reqTranHeader.setHBrchNo(accOrgCode);// 发送方机构ID
		reqTranHeader.setHUserID(GitUtil.getNumOrg(accOrgCode));// 服务请求者
		reqTranHeader.setHTxnCd("");// 交易代码
		reqTranHeader.setHTxnMod("");// 交易模式
		reqTranHeader.setHReserveLen("");// 保留数量字段
		reqTranHeader.setHSenderSvcCd("");// 发起端服务码
		reqTranHeader.setHSenderSeq(reqSeqNo);// 发起端流水
		reqTranHeader.setHSenderDate(busidate);// 发起端日期
		reqTranHeader.setHAuthUserID("");// 授权服务请求者
		reqTranHeader.setHAuthVerfInfo("");// 授权验证信息
		reqTranHeader.setHAuthFlag("");// 授权标志
		reqTranHeader.setHRefSeq("");// 关联流水
		reqTranHeader.setHAuthSeri("");// 授权序号
		reqTranHeader.setHHostSeq("");// 核心流水号
		reqTranHeader.setHRefDt("");// 关联日期
		reqTranHeader.setHSvcVer("");// 服务版本号
		reqTranHeader.setHreserveMsg("");// 保留信息字段
	}

	public S0110101000A101Response CPsnCustListQuery(
			FMT_CRMS_SVR_S0110101000A101_IN request) throws Exception {
		S0110101000A101ServiceStub.S0110101000A101 req = new S0110101000A101ServiceStub.S0110101000A101();
		S0110101000A101ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A101ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A101ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A101ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A101", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A101";
		String url = getUrl() + zservice;
		S0110101000A101ServiceStub client = new S0110101000A101ServiceStub(url);
		int no=Integer.parseInt(request.getFIRST_NO())+1;
		request.setFIRST_NO(String.valueOf(no));
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		request.setCERT_NO("1");
		request.setRESULT_SIZE("20");
		request.setRESOLVE_TYPE("1");
		request.setCERT_NO("12");
		req.setRequestBody(request);
		S0110101000A101Response response= client.S0110101000A101(req);
		return response;
	}

	public S0110101000A102Response CPsnCustBaseInfoQuery(
			FMT_CRMS_SVR_S0110101000A102_IN request)throws Exception {
		S0110101000A102ServiceStub.S0110101000A102 req = new S0110101000A102ServiceStub.S0110101000A102();
		S0110101000A102ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A102ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A102ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A102ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A102", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A102";
		String url = getUrl() + zservice;
		S0110101000A102ServiceStub client = new S0110101000A102ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A102Response response= client.S0110101000A102(req);
		return response;
	}

	public S0110101000A107Response CPsnCustRelInfoQuery(
			FMT_CRMS_SVR_S0110101000A107_IN request) throws Exception{
		S0110101000A107ServiceStub.S0110101000A107 req = new S0110101000A107ServiceStub.S0110101000A107();
		S0110101000A107ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A107ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A107ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A107ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A107", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A107";
		String url = getUrl() + zservice;
		S0110101000A107ServiceStub client = new S0110101000A107ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A107Response response= client.S0110101000A107(req);
		return response;
	}

	public S0110101000A108Response CPsnRelComQuery(
			FMT_CRMS_SVR_S0110101000A108_IN request) throws Exception {
		S0110101000A108ServiceStub.S0110101000A108 req = new S0110101000A108ServiceStub.S0110101000A108();
		S0110101000A108ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A108ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A108ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A108ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A108", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A108";
		String url = getUrl() + zservice;
		S0110101000A108ServiceStub client = new S0110101000A108ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A108Response response= client.S0110101000A108(req);
		return response;
	}
	public S0110101000A108Response CPsnRelComQuery(
			FMT_CRMS_SVR_S0110101000A108_IN request,String orgNum) throws Exception {
		S0110101000A108ServiceStub.S0110101000A108 req = new S0110101000A108ServiceStub.S0110101000A108();
		S0110101000A108ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A108ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A108ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A108ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A108", orgNum, GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A108";
		String url = getUrl() + zservice;
		S0110101000A108ServiceStub client = new S0110101000A108ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A108Response response= client.S0110101000A108(req);
		return response;
	}

	public S0110102000B101Response CPsnCustBaseInfoMaint(
			FMT_CRMS_SVR_S0110102000B101_IN request) throws Exception  {
		S0110102000B101ServiceStub.S0110102000B101 req = new S0110102000B101ServiceStub.S0110102000B101();
		S0110102000B101ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B101ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B101ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B101ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B101", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B101";
		String url = getUrl() + zservice;
		S0110102000B101ServiceStub client = new S0110102000B101ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B101Response response= client.S0110102000B101(req);
		return response;
	}

	public S0110102000B111Response CPsnCustAddrInfoQuery(
			FMT_CRMS_SVR_S0110102000B111_IN request) throws Exception {
		S0110102000B111ServiceStub.S0110102000B111 req = new S0110102000B111ServiceStub.S0110102000B111();
		S0110102000B111ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B111ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B111ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B111ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B111", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B111";
		String url = getUrl() + zservice;
		S0110102000B111ServiceStub client = new S0110102000B111ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B111Response response= client.S0110102000B111(req);
		return response;
	}

	

	public S0110102000B110Response CPsnRelPsnMaint(
			FMT_CRMS_SVR_S0110102000B110_IN request) throws Exception {
		S0110102000B110ServiceStub.S0110102000B110 req = new S0110102000B110ServiceStub.S0110102000B110();
		S0110102000B110ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B110ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B110ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B110ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B110", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B110";
		String url = getUrl() + zservice;
		S0110102000B110ServiceStub client = new S0110102000B110ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B110Response response= client.S0110102000B110(req);
		return response;
	}
	/**
	 * 
	 * ==============================公用交易接口实现==========================
	 * 
	 */
	public S0110101000A011Response CCustAddrInfoQuery(
			FMT_CRMS_SVR_S0110101000A011_IN request) throws Exception {
		S0110101000A011ServiceStub.S0110101000A011 req = new S0110101000A011ServiceStub.S0110101000A011();
		S0110101000A011ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A011ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A011", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A011";
		String url = getUrl() + zservice;
		S0110101000A011ServiceStub client = new S0110101000A011ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A011Response response= client.S0110101000A011(req);
		return response;
	}
	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.IcustEcif#CCustAddrInfoMaint(com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN)
	 */
	public S0110102000B011Response CCustAddrInfoMaint(
			FMT_CRMS_SVR_S0110102000B011_IN request) throws Exception {
		S0110102000B011ServiceStub.S0110102000B011 req = new S0110102000B011ServiceStub.S0110102000B011();
		S0110102000B011ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B011ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B011", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B011";
		String url = getUrl() + zservice;
		S0110102000B011ServiceStub client = new S0110102000B011ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B011Response response= client.S0110102000B011(req);
		return response;
	}

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.IcustEcif#CCustAddrInfoDel(com.primeton.tsl.ecif.S0110102000B012ServiceStub.FMT_CRMS_SVR_S0110102000B012_IN)
	 */
	public S0110102000B012Response CCustAddrInfoDel(
			FMT_CRMS_SVR_S0110102000B012_IN request) throws Exception {
		S0110102000B012ServiceStub.S0110102000B012 req = new S0110102000B012ServiceStub.S0110102000B012();
		S0110102000B012ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B012ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B012ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B012ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B012", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B012";
		String url = getUrl() + zservice;
		S0110102000B012ServiceStub client = new S0110102000B012ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B012Response response= client.S0110102000B012(req);
		return response;
	}
	public S0110101000A221Response OrgGrpBaseQuery(
			FMT_CRMS_SVR_S0110101000A221_IN request) throws Exception {
			S0110101000A221ServiceStub.S0110101000A221 req = new S0110101000A221ServiceStub.S0110101000A221();
			S0110101000A221ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A221ServiceStub.FMT_SOAP_UTF8_RequestHeader();
			S0110101000A221ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110101000A221ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			CommRequestHeader commRequestHeader = new CommRequestHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A221", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
			String zservice ="/WebService/CRMS_SVR/S0110101000A221";
			String url = getUrl() + zservice;
			S0110101000A221ServiceStub client = new S0110101000A221ServiceStub(url);
			req.setReqTranHeader(reqTranHeader);
			req.setRequestHeader(requestHeader);
			req.setRequestBody(request);
			S0110101000A221Response response= client.S0110101000A221(req);
		return response;
	}
	public S0110102000B221Response OrgGrpCustBaseMaint(
			FMT_CRMS_SVR_S0110102000B221_IN request) throws Exception {
			S0110102000B221ServiceStub.S0110102000B221 req = new S0110102000B221ServiceStub.S0110102000B221();
			S0110102000B221ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B221ServiceStub.FMT_SOAP_UTF8_RequestHeader();
			S0110102000B221ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B221ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			CommRequestHeader commRequestHeader = new CommRequestHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B221", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
			String zservice ="/WebService/CRMS_SVR/S0110102000B221";
			String url = getUrl() + zservice;
			S0110102000B221ServiceStub client = new S0110102000B221ServiceStub(url);
			req.setReqTranHeader(reqTranHeader);
			req.setRequestHeader(requestHeader);
			req.setRequestBody(request);
			S0110102000B221Response response= client.S0110102000B221(req);
		return response;
	}
	public S0110101000A222Response COrgGrpMemRelQuery(
			FMT_CRMS_SVR_S0110101000A222_IN request) throws Exception {
		S0110101000A222ServiceStub.S0110101000A222 req = new S0110101000A222ServiceStub.S0110101000A222();
		S0110101000A222ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A222ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A222ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110101000A222ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A222", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A222";
		String url = getUrl() + zservice;
		S0110101000A222ServiceStub client = new S0110101000A222ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A222Response response= client.S0110101000A222(req);
		return response;
	}
	public S0110102000B222Response OrgGrpMemRelMaint(
			FMT_CRMS_SVR_S0110102000B222_IN request) throws Exception {
		S0110102000B222ServiceStub.S0110102000B222 req = new S0110102000B222ServiceStub.S0110102000B222();
		S0110102000B222ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B222ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B222ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B222ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B222", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B222";
		String url = getUrl() + zservice;
		S0110102000B222ServiceStub client = new S0110102000B222ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B222Response response= client.S0110102000B222(req);
		return response;
	}
	public S0110102000B223Response OrgGrpMemRelDel(
			FMT_CRMS_SVR_S0110102000B223_IN request) throws Exception {
		S0110102000B223ServiceStub.S0110102000B223 req = new S0110102000B223ServiceStub.S0110102000B223();
		S0110102000B223ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B223ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B223ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B223ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B223", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B223";
		String url = getUrl() + zservice;
		S0110102000B223ServiceStub client = new S0110102000B223ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B223Response response= client.S0110102000B223(req);
		return response;
	}

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.IcustEcif#CCustBussOpenMaint(com.primeton.tsl.ecif.S0110102000B014ServiceStub.FMT_CRMS_SVR_S0110102000B014_IN)
	 */
	public S0110102000B014Response CCustBussOpenMaint(
			FMT_CRMS_SVR_S0110102000B014_IN request) throws Exception {
		S0110102000B014ServiceStub.S0110102000B014 req = new S0110102000B014ServiceStub.S0110102000B014();
		S0110102000B014ServiceStub.FMT_RequestHeader requestHeader = new S0110102000B014ServiceStub.FMT_RequestHeader();
		S0110102000B014ServiceStub.FMT_XML_HEAD_ReqTranHeader_IN  reqTranHeader = new S0110102000B014ServiceStub.FMT_XML_HEAD_ReqTranHeader_IN();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B014", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B014";
		String url = getUrl() + zservice;
		S0110102000B014ServiceStub client = new S0110102000B014ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B014Response response= client.S0110102000B014(req);
		return response;
	}
	public S0110101000A207Response COrgRelPsnQuery(
			FMT_CRMS_SVR_S0110101000A207_IN request) throws Exception {
		S0110101000A207ServiceStub.S0110101000A207 req = new S0110101000A207ServiceStub.S0110101000A207();
		S0110101000A207ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A207ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A207ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110101000A207ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A207", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A207";
		String url = getUrl() + zservice;
		S0110101000A207ServiceStub client = new S0110101000A207ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A207Response response= client.S0110101000A207(req);
		return response;
	}
	public S0110101000A208Response OrgRelComQuery(
			FMT_CRMS_SVR_S0110101000A208_IN request) throws Exception {
		S0110101000A208ServiceStub.S0110101000A208 req = new S0110101000A208ServiceStub.S0110101000A208();
		S0110101000A208ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A208ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A208ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110101000A208ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A208", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A208";
		String url = getUrl() + zservice;
		S0110101000A208ServiceStub client = new S0110101000A208ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A208Response response= client.S0110101000A208(req);
		return response;
	}
	public S0110102000B210Response OrgRelPsnMaint(
			FMT_CRMS_SVR_S0110102000B210_IN request) throws Exception {
		S0110102000B210ServiceStub.S0110102000B210 req = new S0110102000B210ServiceStub.S0110102000B210();
		S0110102000B210ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B210ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B210ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B210ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B210", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B210";
		String url = getUrl() + zservice;
		S0110102000B210ServiceStub client = new S0110102000B210ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B210Response response= client.S0110102000B210(req);
		return response;
	}
	public S0110102000B211Response OrgRelPsnDel(
			FMT_CRMS_SVR_S0110102000B211_IN request) throws Exception {
		S0110102000B211ServiceStub.S0110102000B211 req = new S0110102000B211ServiceStub.S0110102000B211();
		S0110102000B211ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B211ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B211ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B211ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B211", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B211";
		String url = getUrl() + zservice;
		S0110102000B211ServiceStub client = new S0110102000B211ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B211Response response= client.S0110102000B211(req);
		return response;
	}
	public S0110102000B212Response OrgRelComMaint(
			FMT_CRMS_SVR_S0110102000B212_IN request) throws Exception {
		S0110102000B212ServiceStub.S0110102000B212 req = new S0110102000B212ServiceStub.S0110102000B212();
		S0110102000B212ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B212ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B212ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B212ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B212", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B212";
		String url = getUrl() + zservice;
		S0110102000B212ServiceStub client = new S0110102000B212ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B212Response response= client.S0110102000B212(req);
		return response;
	}
	public S0110102000B213Response OrgRelComDel(
			FMT_CRMS_SVR_S0110102000B213_IN request) throws Exception {
		S0110102000B213ServiceStub.S0110102000B213 req = new S0110102000B213ServiceStub.S0110102000B213();
		S0110102000B213ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B213ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B213ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B213ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B213", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B213";
		String url = getUrl() + zservice;
		S0110102000B213ServiceStub client = new S0110102000B213ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B213Response response= client.S0110102000B213(req);
		return response;
	}
	public S0110102000B224Response OrgGrpCustLogout(
			FMT_CRMS_SVR_S0110102000B224_IN request) throws Exception {
		S0110102000B224ServiceStub.S0110102000B224 req = new S0110102000B224ServiceStub.S0110102000B224();
		S0110102000B224ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B224ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B224ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B224ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B224", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B224";
		String url = getUrl() + zservice;
		S0110102000B224ServiceStub client = new S0110102000B224ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B224Response response= client.S0110102000B224(req);
		return response;
	}
	public S0110102000B112Response CPsnRelOrgMaint(
			FMT_CRMS_SVR_S0110102000B112_IN request) throws Exception {
		S0110102000B112ServiceStub.S0110102000B112 req = new S0110102000B112ServiceStub.S0110102000B112();
		S0110102000B112ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B112ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B112ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B112ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B112", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B112";
		String url = getUrl() + zservice;
		S0110102000B112ServiceStub client = new S0110102000B112ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B112Response response= client.S0110102000B112(req);
		return response;
	}
	public S0110102000B111Response CPsnRelPsnDel(
			FMT_CRMS_SVR_S0110102000B111_IN request) throws Exception {
		S0110102000B111ServiceStub.S0110102000B111 req = new S0110102000B111ServiceStub.S0110102000B111();
		S0110102000B111ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B111ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B111ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B111ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B111", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B111";
		String url = getUrl() + zservice;
		S0110102000B111ServiceStub client = new S0110102000B111ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B111Response response= client.S0110102000B111(req);
		return response;
	}
	public S0110102000B113Response CPsnRelOrgDel(
			FMT_CRMS_SVR_S0110102000B113_IN request) throws Exception {
		S0110102000B113ServiceStub.S0110102000B113 req = new S0110102000B113ServiceStub.S0110102000B113();
		S0110102000B113ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B113ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B113ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S0110102000B113ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110102000B113", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B113";
		String url = getUrl() + zservice;
		S0110102000B113ServiceStub client = new S0110102000B113ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B113Response response= client.S0110102000B113(req);
		return response;
	}
	public S00601120005491Response CCheckIdentity(
			FMT_CRMS_SVR_S00601120005491_IN request) throws Exception {
		S00601120005491ServiceStub.S00601120005491 req = new S00601120005491ServiceStub.S00601120005491();
		S00601120005491ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S00601120005491ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S00601120005491ServiceStub.FMT_SOAP_UTF8_ReqTranHeader  reqTranHeader = new S00601120005491ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S00601120005491", GitUtil.getOrgCode(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S00601120005491";
		String url = getUrl() + zservice;
		S00601120005491ServiceStub client = new S00601120005491ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S00601120005491Response response= client.S00601120005491(req);
		return response;
	}
	public S0110101000A102Response CPsnCustBaseInfoQuery(
			FMT_CRMS_SVR_S0110101000A102_IN request, String orgNum)
			throws Exception {
		S0110101000A102ServiceStub.S0110101000A102 req = new S0110101000A102ServiceStub.S0110101000A102();
		S0110101000A102ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A102ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A102ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A102ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A102", orgNum, "");
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A102";
		String url = getUrl() + zservice;
		S0110101000A102ServiceStub client = new S0110101000A102ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A102Response response= client.S0110101000A102(req);
		return response;
	}
	public S0110101000A011Response CCustAddrInfoQuery(
			FMT_CRMS_SVR_S0110101000A011_IN request, String orgNum)
			throws Exception {
		S0110101000A011ServiceStub.S0110101000A011 req = new S0110101000A011ServiceStub.S0110101000A011();
		S0110101000A011ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A011ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A011ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A011", orgNum, "");
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A011";
		String url = getUrl() + zservice;
		S0110101000A011ServiceStub client = new S0110101000A011ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A011Response response= client.S0110101000A011(req);
		return response;
	}
	public S0110101000A107Response CPsnCustRelInfoQuery(
			FMT_CRMS_SVR_S0110101000A107_IN request, String orgNum)
			throws Exception {
		S0110101000A107ServiceStub.S0110101000A107 req = new S0110101000A107ServiceStub.S0110101000A107();
		S0110101000A107ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A107ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A107ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A107ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S0110101000A107", orgNum, "");
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A107";
		String url = getUrl() + zservice;
		S0110101000A107ServiceStub client = new S0110101000A107ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A107Response response= client.S0110101000A107(req);
		return response;
	}



}