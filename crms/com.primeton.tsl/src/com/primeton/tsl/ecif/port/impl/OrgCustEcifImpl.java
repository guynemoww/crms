/**
 * 
 */
package com.primeton.tsl.ecif.port.impl;

import org.apache.axis2.engine.ListenerManager;
import org.apache.commons.beanutils.BeanUtils;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.FMT_CRMS_SVR_S0110101000A202_IN;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.S0110101000A202Response;
import com.primeton.tsl.ecif.S0110101000A204ServiceStub;
import com.primeton.tsl.ecif.S0110101000A204ServiceStub.FMT_CRMS_SVR_S0110101000A204_IN;
import com.primeton.tsl.ecif.S0110101000A204ServiceStub.S0110101000A204Response;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_IN;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.S0110101000A207Response;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_IN;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.S0110101000A208Response;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.FMT_CRMS_SVR_S0110102000B201_IN;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.S0110102000B201Response;
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
import com.primeton.tsl.ecif.port.CommReqTranHeader;
import com.primeton.tsl.ecif.port.CommRequestHeader;
import com.primeton.tsl.ecif.port.OrgCustEcif;
import com.primeton.tsl.ecif.port.ReqHeaderAndTranHeaderUtil;

/**
 *@author zhouxu
 * 对公客户交易与维护 所有接口实现
 */
public class OrgCustEcifImpl implements OrgCustEcif {
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
	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgCustBaseQuery(com.primeton.tsl.ecif.S0110101000A202ServiceStub.FMT_CRMS_SVR_S0110101000A202_IN)
	 */
	public S0110101000A202Response COrgCustBaseQuery(
			FMT_CRMS_SVR_S0110101000A202_IN request) throws Exception {
		S0110101000A202ServiceStub.S0110101000A202 req = new S0110101000A202ServiceStub.S0110101000A202();
		S0110101000A202ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A202ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A202ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A202ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110101000A202", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A202";
		String url = getUrl() + zservice;
		S0110101000A202ServiceStub client = new S0110101000A202ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A202Response response= client.S0110101000A202(req);
		return response;
	}

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgCustIpoInfoQuery(com.primeton.tsl.ecif.S0110101000A204ServiceStub.FMT_CRMS_SVR_S0110101000A204_IN)
	 */
	public S0110101000A204Response COrgCustIpoInfoQuery(
			FMT_CRMS_SVR_S0110101000A204_IN request) throws Exception {
		S0110101000A204ServiceStub.S0110101000A204 req = new S0110101000A204ServiceStub.S0110101000A204();
		S0110101000A204ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A204ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A204ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A204ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110101000A204", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110101000A204";
		String url = getUrl() + zservice;
		S0110101000A204ServiceStub client = new S0110101000A204ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110101000A204Response response= client.S0110101000A204(req);
		return response;
	}

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgRelPsnQuery(com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_IN)
	 */
	public S0110101000A207Response COrgRelPsnQuery(
			FMT_CRMS_SVR_S0110101000A207_IN request) throws Exception {
		S0110101000A207ServiceStub.S0110101000A207 req = new S0110101000A207ServiceStub.S0110101000A207();
		S0110101000A207ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A207ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A207ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A207ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110101000A207", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
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

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgRelComQuery(com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_IN)
	 */
	public S0110101000A208Response COrgRelComQuery(
			FMT_CRMS_SVR_S0110101000A208_IN request) throws Exception {
		S0110101000A208ServiceStub.S0110101000A208 req = new S0110101000A208ServiceStub.S0110101000A208();
		S0110101000A208ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110101000A208ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110101000A208ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110101000A208ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110101000A208", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
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

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgCustBaseMaint(com.primeton.tsl.ecif.S0110102000B201ServiceStub.FMT_CRMS_SVR_S0110102000B201_IN)
	 */
	public S0110102000B201Response COrgCustBaseMaint(
			FMT_CRMS_SVR_S0110102000B201_IN request) throws Exception {
		S0110102000B201ServiceStub.S0110102000B201 req = new S0110102000B201ServiceStub.S0110102000B201();
		S0110102000B201ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B201ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B201ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B201ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110102000B201", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B201";
		String url = getUrl() + zservice;
		S0110102000B201ServiceStub client = new S0110102000B201ServiceStub(url);
		req.setReqTranHeader(reqTranHeader);
		req.setRequestHeader(requestHeader);
		req.setRequestBody(request);
		S0110102000B201Response response= client.S0110102000B201(req);
		return response;
	}

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#CustAddrInfoQuery(com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_IN)
	 */
	public S0110102000B210Response CCustAddrInfoQuery(
			FMT_CRMS_SVR_S0110102000B210_IN request) throws Exception {
		S0110102000B210ServiceStub.S0110102000B210 req = new S0110102000B210ServiceStub.S0110102000B210();
		S0110102000B210ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B210ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B210ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B210ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110102000B210", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
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

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgRelPsnDel(com.primeton.tsl.ecif.S0110102000B211ServiceStub.FMT_CRMS_SVR_S0110102000B211_IN)
	 */
	public S0110102000B211Response COrgRelPsnDel(
			FMT_CRMS_SVR_S0110102000B211_IN request) throws Exception {
		S0110102000B211ServiceStub.S0110102000B211 req = new S0110102000B211ServiceStub.S0110102000B211();
		S0110102000B211ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B211ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B211ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B211ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110102000B211", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
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

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgRelComMaint(com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_IN)
	 */
	public S0110102000B212Response COrgRelComMaint(
			FMT_CRMS_SVR_S0110102000B212_IN request) throws Exception {
		S0110102000B212ServiceStub.S0110102000B212 req = new S0110102000B212ServiceStub.S0110102000B212();
		S0110102000B212ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B212ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B212ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B212ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110102000B212", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
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

	/* （非 Javadoc）
	 * @see com.primeton.tsl.ecif.port.OrgCustEcif#OrgRelComDel(com.primeton.tsl.ecif.S0110102000B213ServiceStub.FMT_CRMS_SVR_S0110102000B213_IN)
	 */
	public S0110102000B213Response COrgRelComDel(
			FMT_CRMS_SVR_S0110102000B213_IN request) throws Exception {
		S0110102000B213ServiceStub.S0110102000B213 req = new S0110102000B213ServiceStub.S0110102000B213();
		S0110102000B213ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S0110102000B213ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S0110102000B213ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S0110102000B213ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		ReqHeaderAndTranHeaderUtil.InitReqHeaderAndTranHeader(commRequestHeader, commReqTranHeader, "S0110102000B213", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		String zservice ="/WebService/CRMS_SVR/S0110102000B213";
		String url = getUrl() + zservice;
		S0110102000B213ServiceStub client = new S0110102000B213ServiceStub(url);
		req.setRequestBody(request);
		S0110102000B213Response response= client.S0110102000B213(req);
		return response;
	}

}
