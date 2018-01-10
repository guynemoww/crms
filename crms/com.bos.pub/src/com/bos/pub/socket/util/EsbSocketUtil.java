package com.bos.pub.socket.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.bind.JAXBException;

import com.bos.jaxb.JAXBUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.service.mortgage.request.EsbBodyMtmqRq02002000004A02;
import com.bos.pub.socket.service.mortgage.response.EsbBodyMtmqRs02002000004A02;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A01;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A02;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A03;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A04;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A05;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A06;
import com.bos.pub.socket.service.request.EsbBodyGjRq02001000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq02001000003A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq02002000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000001A02;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000002A03;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000002A04;
import com.bos.pub.socket.service.request.EsbBodyGjRq07003000001A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A05;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq12003000004A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12003000004A10;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000001A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A04;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000003A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A02;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A03;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000004A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000003A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A02;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A03;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A05;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A04;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A06;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A07;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A09;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A10;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A13;
import com.bos.pub.socket.service.request.EsbBodyRzRq12002000012A01;
import com.bos.pub.socket.service.request.EsbBodyRzRq12003000005A02;
import com.bos.pub.socket.service.request.EsbBodyWmaRq03002000011A01;
import com.bos.pub.socket.service.request.EsbBodyWmaRq03002000011A02;
import com.bos.pub.socket.service.request.EsbBodyXdRq02001000002A01;
import com.bos.pub.socket.service.request.EsbBodyXdRq02001000003A02;
import com.bos.pub.socket.service.request.EsbBodyXdRq05002000001A01;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.bos.pub.socket.service.request.base.EsbServiceRq;
import com.bos.pub.socket.service.request.base.EsbSysHeadRq;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A01;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A02;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A04;
import com.bos.pub.socket.service.response.EsbBodyGjRs07003000001A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs01001000002A02;
import com.bos.pub.socket.service.response.EsbBodyHxRs01001000002A03;
import com.bos.pub.socket.service.response.EsbBodyHxRs12003000004A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs12003000004A10;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000001A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000002A04;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A02;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A03;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000004A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A04;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A06;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A07;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A02;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A03;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A05;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A09;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A10;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A13;
import com.bos.pub.socket.service.response.EsbBodyRs;
import com.bos.pub.socket.service.response.EsbBodyRzRs12002000012A01;
import com.bos.pub.socket.service.response.EsbBodyRzRs12003000005A02;
import com.bos.pub.socket.service.response.base.EsbAppHeadRs;
import com.bos.pub.socket.service.response.base.EsbServiceRs;
import com.bos.pub.socket.service.response.base.EsbSysHeadRs;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

@Bizlet("接口公共方法")
public class EsbSocketUtil {
	private static TraceLogger log = new TraceLogger(EsbSocketUtil.class);

	/**
	 * 1、根据服务代码把输入参数转换成对应的报文体
	 * 
	 * @param serviceCodeScene
	 * @param dataObject
	 * @return
	 */
	public static EsbSocketMessage dataObject2EsbServiceRq(String serviceCodeScene, EsbAppHeadRq iEsbAppHeadRq,
			DataObject dataObject) {
		setCcyTpByCodeScene("QQ", serviceCodeScene, dataObject);

		EsbSocketMessage esbSocketMessage = new EsbSocketMessage();

		Class<?> classz = getEsbBodyRqByCodeScene(serviceCodeScene);
		if (classz == null) {
			esbSocketMessage.setRetCode(EsbSocketConstant.XD_SOCKET_FAILE);
			esbSocketMessage.setRetMsg("没有找到您访问的服务！" + serviceCodeScene);
		} else {
			try {
				// 1、DataObject转换成报文体的JavaBean
				Object esbBodyRq = classz.newInstance();
				GitUtil.copyTo(dataObject, esbBodyRq);
				// 2、组织EsbServiceRq转换成字符串
				String strEsbServiceRq = getStrEsbServiceRq(serviceCodeScene, iEsbAppHeadRq, esbBodyRq);
				// 、拼接长度
				strEsbServiceRq = getZeroStr(strEsbServiceRq) + strEsbServiceRq;

				esbSocketMessage.setRetCode(EsbSocketConstant.SOCKET_SUCCESS);
				esbSocketMessage.setStrEsbServiceRq(strEsbServiceRq);
			} catch (Exception e) {
				esbSocketMessage.setRetCode(EsbSocketConstant.XD_SOCKET_FAILE);
				esbSocketMessage.setRetMsg("请求报文体数据转换错误！");
			}
		}
		return esbSocketMessage;
	}

	/**
	 * 所有核心交易的货币种类进行转换
	 * 
	 * @param serviceCodeScene
	 * @param dataObject
	 * @return
	 */
	public static void setCcyTpByCodeScene(String fx, String serviceCodeScene, DataObject dataObject) {
		HashMap<String, String> clsHm = new HashMap<String, String>();
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY02, EsbSocketConstant.HxRq01001000002BODY02);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY03, EsbSocketConstant.HxRq01001000002BODY03);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY05, EsbSocketConstant.HxRq01001000002BODY05);
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY01, EsbSocketConstant.HxRq12003000004BODY01);
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY10, EsbSocketConstant.HxRq12003000004BODY10);
		clsHm.put(EsbSocketConstant.HxRq12005000001BODY01, EsbSocketConstant.HxRq12005000001BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY01, EsbSocketConstant.HxRq12005000002BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY02, EsbSocketConstant.HxRq12005000002BODY02);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY03, EsbSocketConstant.HxRq12005000002BODY03);
		clsHm.put(EsbSocketConstant.HxRq12005000003BODY01, EsbSocketConstant.HxRq12005000003BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY04, EsbSocketConstant.HxRq12005000002BODY04);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY01, EsbSocketConstant.HxRq02002000002BODY01);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY02, EsbSocketConstant.HxRq02002000002BODY02);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY03, EsbSocketConstant.HxRq02002000002BODY03);

		String scs = clsHm.get(serviceCodeScene);
		if (scs != null && scs.length() > 0) {
			String ccyTp = dataObject.getString("ccyTp");
			HashMap<String, String> ccyTpMap = new HashMap<String, String>();

			ccyTpMap.put("CNY", "01");
			ccyTpMap.put("FRF", "250");// 法国法郎
			ccyTpMap.put("DEM", "276");// 德国马克
			ccyTpMap.put("HKD", "13");// 港币
			ccyTpMap.put("ITL", "380");// 意大利里拉
			ccyTpMap.put("JPY", "27");// 日元
			ccyTpMap.put("KRW", "410");// 韩国元
			ccyTpMap.put("MOP", "446");// 澳门元
			ccyTpMap.put("MYR", "458");// 马来西亚币
			ccyTpMap.put("NLG", "528");// 荷兰盾
			ccyTpMap.put("NZD", "554");// 新西兰元
			ccyTpMap.put("AUD", "16");// 澳洲元
			ccyTpMap.put("NOK", "578");// 挪威克朗
			ccyTpMap.put("PHP", "608");// 菲律宾比索
			ccyTpMap.put("RUB", "643");// 卢布
			ccyTpMap.put("SGD", "702");// 新加坡元
			ccyTpMap.put("ESP", "724");// 西班牙比塞塔
			ccyTpMap.put("SEK", "752");// 瑞典克朗
			ccyTpMap.put("CHF", "756");// 瑞士法郎
			ccyTpMap.put("THB", "764");// 泰国铢
			ccyTpMap.put("GBP", "12");// 英镑
			ccyTpMap.put("USD", "14");// 美元
			ccyTpMap.put("EUR", "15");// 欧元
			ccyTpMap.put("ATS", "040");// 奥地利先令
			ccyTpMap.put("BEF", "056");// 比利时法郎
			ccyTpMap.put("CAD", "124");// 加拿大元
			ccyTpMap.put("TWD", "158");// 新台湾币
			ccyTpMap.put("DKK", "208");// 丹麦克朗
			ccyTpMap.put("FIM", "246");// 芬兰马克
			ccyTpMap.put("OTHER", "999");// 其他

			if ("QQ".equals(fx)) {
				String ccyTpM = ccyTpMap.get(ccyTp);
				if (ccyTp != null && ccyTp.length() > 0 && ccyTpM != null && ccyTpM.length() > 0) {
					dataObject.setString("ccyTp", ccyTpM);
				}
			} else if ("XY".equals(fx)) {
				Iterator<String> iterator = ccyTpMap.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = ccyTpMap.get(key);
					if (value.equals(ccyTp)) {
						dataObject.setString("ccyTp", key);
						break;
					}
				}
			}
		}
	}

	private static Class<?> getEsbBodyRqByCodeScene(String serviceCodeScene) {
		HashMap<String, Class<?>> clsHm = new HashMap<String, Class<?>>();
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY01, EsbBodyEcifRq12002000013A01.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY02, EsbBodyEcifRq12002000013A02.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY03, EsbBodyEcifRq12002000013A03.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY04, EsbBodyEcifRq12002000013A04.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY05, EsbBodyEcifRq12002000013A05.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY06, EsbBodyEcifRq12002000013A06.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.GjRq02001000001BODY01, EsbBodyGjRq02001000001A01.class);
		clsHm.put(EsbSocketConstant.GjRq02001000003BODY01, EsbBodyGjRq02001000003A01.class);
		clsHm.put(EsbSocketConstant.GjRq02002000001BODY01, EsbBodyGjRq02002000001A01.class);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY01, EsbBodyGjRq05001000001A01.class);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY02, EsbBodyGjRq05001000001A02.class);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY03, EsbBodyGjRq05001000002A03.class);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY04, EsbBodyGjRq05001000002A04.class);
		clsHm.put(EsbSocketConstant.GjRq07003000001BODY01, EsbBodyGjRq07003000001A01.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY02, EsbBodyHxRq01001000002A02.class);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY03, EsbBodyHxRq01001000002A03.class);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY05, EsbBodyHxRq01001000002A05.class);
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY01, EsbBodyHxRq12003000004A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000001BODY01, EsbBodyHxRq12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY01, EsbBodyHxRq12005000002A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY02, EsbBodyHxRq12005000002A02.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY03, EsbBodyHxRq12005000002A03.class);
		clsHm.put(EsbSocketConstant.HxRq12005000003BODY01, EsbBodyHxRq12005000003A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY04, EsbBodyHxRq12005000002A04.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY01, EsbBodyHxRq02002000002A01.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY02, EsbBodyHxRq02002000002A02.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY03, EsbBodyHxRq02002000002A03.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.RzRq12002000012BODY01, EsbBodyRzRq12002000012A01.class);
		clsHm.put(EsbSocketConstant.RzRq12003000005BODY02, EsbBodyRzRq12003000005A02.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.XdRq02001000002BODY01, EsbBodyXdRq02001000002A01.class);
		clsHm.put(EsbSocketConstant.XdRq02001000003BODY02, EsbBodyXdRq02001000003A02.class);
		clsHm.put(EsbSocketConstant.XdRq05002000001BODY01, EsbBodyXdRq05002000001A01.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY01, EsbBodyWmaRq03002000011A01.class);
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY02, EsbBodyWmaRq03002000011A02.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY10, EsbBodyHxRq12003000004A10.class);

		// --------------------------面谈面签---------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.MtmqRs02003000003BODY01, EsbBodyMtmqRs02003000003A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000003BODY01, EsbBodyMtmqRq02003000003A01.class);
		clsHm.put(EsbSocketConstant.MtmqRs12002000013BODY09, EsbBodyMtmqRs12002000013A09.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY09, EsbBodyMtmqRq12002000013A09.class);
		clsHm.put(EsbSocketConstant.MtmqRs12002000013BODY10, EsbBodyMtmqRs12002000013A10.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY10, EsbBodyMtmqRq12002000013A10.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY13, EsbBodyMtmqRq12002000013A13.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY01, EsbBodyMtmqRq02003000004A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY02, EsbBodyMtmqRq02003000004A02.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY03, EsbBodyMtmqRq02003000004A03.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY01, EsbBodyMtmqRq02002000004A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY02, EsbBodyMtmqRq02002000004A02.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY01, EsbBodyMtmqRq02002000003A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY02, EsbBodyMtmqRq02002000003A02.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY03, EsbBodyMtmqRq02002000003A03.class);

		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY06, EsbBodyMtmqRq02003000004A06.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY07, EsbBodyMtmqRq02003000004A07.class);

		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY05, EsbBodyMtmqRq02003000004A05.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY04, EsbBodyMtmqRq02003000004A04.class);

		return clsHm.get(serviceCodeScene);
	}

	private static String getSourceSysId() {
		String retSourceSysId = "";

		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String sourceSysId = EsbSocketConstant.CONTRIBUTION_APPHEAD_SOURCESYSID;

		retSourceSysId = ConfigurationUtil.getUserConfigSingleValue(module, group, sourceSysId);

		return retSourceSysId;
	}

	private static String getStrEsbServiceRq(String serviceCodeScene, EsbAppHeadRq iEsbAppHeadRq, Object esbBodyRq) {
		String sysId = getSourceSysId();
		// 服务代码、服务场景(例如：serviceCodeScene=02001000001BODY01;serviceCode02001000001;serviceScene=01)
		String[] codeScene = serviceCodeScene.split(EsbSocketConstant.BODY);
		String serviceCode = codeScene[0].substring(2);
		String serviceScene = codeScene[1];

		String ret = "";
		String sourceSysId = sysId;
		String consumerId = sysId;

		EsbServiceRq esbServiceRq = new EsbServiceRq();

		EsbSysHeadRq esbSysHeadRq = getEsbSysHeadRq(sourceSysId, consumerId, serviceCode, serviceScene);

		EsbAppHeadRq esbAppHeadRq = getEsbAppHeadRq(serviceCodeScene, iEsbAppHeadRq);

		esbServiceRq.setEsbSysHead(esbSysHeadRq);
		esbServiceRq.setEsbAppHead(esbAppHeadRq);

		try {
			esbServiceRq.setEsbBody(esbBodyRq);

			String strEsbServiceRq = JAXBUtil.marshalToStr(esbServiceRq, false);
			ret = replaceAll2Body(strEsbServiceRq, serviceCodeScene);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private static EsbSysHeadRq getEsbSysHeadRq(String sourceSysId, String consumerId, String serviceCode,
			String serviceScene) {
		EsbSysHeadRq esbSysHeadRq = new EsbSysHeadRq();
		// MAC字段 String 16 必输
		esbSysHeadRq.setMac("0000000000001");
		// 服务消息ID String 36 必输 使用UUID生成
		esbSysHeadRq.setMsgId(GitUtil.genUUIDString());
		// 服务源发起系统ID String 10 必输
		esbSysHeadRq.setSourceSysId(sourceSysId);
		// 服务调用方系统ID String 10 必输
		esbSysHeadRq.setConsumerId(consumerId);
		// 服务码 String 11 必输
		esbSysHeadRq.setServiceCode(serviceCode);
		// 服务场景 String 2 必输
		esbSysHeadRq.setServiceScene(serviceScene);
		// 服务响应地址 String 128 可选
		esbSysHeadRq.setReplyAdr("");
		// 扩展内容 String 128 可选 保留字段
		esbSysHeadRq.setExtendContent("");

		return esbSysHeadRq;
	}

	private static EsbAppHeadRq getEsbAppHeadRq(String serviceCodeScene, EsbAppHeadRq iEsbAppHeadRq) {
		String jygy = GitUtil.getCoreotjygy();

		String branchId = GitUtil.getBranchId();

		String sysId = getSourceSysId();
		String genUUIDString = GitUtil.genUUIDString();
		String busiDateYYYYMMDD = GitUtil.getBusiDateYYYYMMDD();

		String seqNo = sysId + busiDateYYYYMMDD.substring(2) + genUUIDString.substring(16);

		EsbAppHeadRq esbAppHeadRq = new EsbAppHeadRq();
		// 交易日期 String 8 必输 YYYYMMDD
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getTranDate() == null) {
			esbAppHeadRq.setTranDate(GitUtil.getBusiDateYYYYMMDD());
		} else {
			esbAppHeadRq.setTranDate(iEsbAppHeadRq.getTranDate());
		}
		// 交易时间 String 6 必输 hhmmss
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getTranTime() == null) {
			esbAppHeadRq.setTranTime(DateHelper.formatTime().trim());
		} else {
			esbAppHeadRq.setTranTime(iEsbAppHeadRq.getTranTime());
		}
		// 交易柜员 String 30 必输 有柜员号则填柜员号/无柜员号则填用户ID
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getTranTellerNo() == null) {
			// esbAppHeadRq.setTranTellerNo(jygy);//1892
			// GitUtil.getCurrentUserId()
		} else {
			esbAppHeadRq.setTranTellerNo(iEsbAppHeadRq.getTranTellerNo());
		}
		// 交易流水号 String 42 必输 6位系统ID+6位日期+16位序号
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getTranSeqNo() == null) {
			esbAppHeadRq.setTranSeqNo(seqNo);
		} else {
			esbAppHeadRq.setTranSeqNo(iEsbAppHeadRq.getTranSeqNo());
		}
		// 全局流水号 String 42 可选
		// 6位系统ID+6位日期+16位业务流程初始发起系统全局流水号与流水号一致，非初始发起系统则填原全局流水号
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getGlobalSeqNo() == null) {
			esbAppHeadRq.setGlobalSeqNo(seqNo);
		} else {
			esbAppHeadRq.setGlobalSeqNo(iEsbAppHeadRq.getGlobalSeqNo());
		}
		// 机构代码 String 6 可选
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getBranchId() == null) {
			// esbAppHeadRq.setBranchId("701");//710 GitUtil.getCurrentOrgCd()
		} else {
			esbAppHeadRq.setBranchId(iEsbAppHeadRq.getBranchId());
		}
		// 终端号 String 20 可选
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getTerminalCode() == null) {
			esbAppHeadRq.setTerminalCode("");
		} else {
			esbAppHeadRq.setTerminalCode(iEsbAppHeadRq.getTerminalCode());
		}
		// 城市代码 String 6 可选
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getCityCode() == null) {
			esbAppHeadRq.setCityCode("");
		} else {
			esbAppHeadRq.setCityCode(iEsbAppHeadRq.getCityCode());
		}
		// 授权柜员 String 30 可选
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getAuthrTellerNo() == null) {
			// esbAppHeadRq.setAuthrTellerNo(jygy);
		} else {
			esbAppHeadRq.setAuthrTellerNo(iEsbAppHeadRq.getAuthrTellerNo());
		}
		// 授权密码 String 16 可选 16进制编码
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getAuthrPwd() == null) {
			esbAppHeadRq.setAuthrPwd("");
		} else {
			esbAppHeadRq.setAuthrPwd(iEsbAppHeadRq.getAuthrPwd());
		}
		// 授权柜员有无卡标志 String 1 可选 0-无卡 1-有卡
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getAuthrCardFlag() == null) {
			esbAppHeadRq.setAuthrCardFlag("");
		} else {
			esbAppHeadRq.setAuthrCardFlag(iEsbAppHeadRq.getAuthrCardFlag());
		}
		// 授权柜员卡序号 String 2 可选
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getAuthrCardNo() == null) {
			esbAppHeadRq.setAuthrCardNo("");
		} else {
			esbAppHeadRq.setAuthrCardNo(iEsbAppHeadRq.getAuthrCardNo());
		}
		// 用户语言 String 10 可选 CHINESE-中文（默认）
		if (iEsbAppHeadRq == null || iEsbAppHeadRq.getLangCode() == null) {
			esbAppHeadRq.setLangCode("CHINESE");
		} else {
			esbAppHeadRq.setLangCode(iEsbAppHeadRq.getLangCode());
		}

		return esbAppHeadRq;
	}

	/**
	 * 2、转换成对应的EsbServiceRq
	 * 
	 * @param receiveEsbServiceRq
	 * @return
	 */
	public static EsbServiceRq getEsbServiceRq(String receiveEsbServiceRq) {
		EsbServiceRq esbServiceRq = (EsbServiceRq) JAXBUtil.unmarshal(receiveEsbServiceRq.trim(), EsbServiceRq.class);

		String serviceCodeScene = getServiceCodeScene(esbServiceRq);
		receiveEsbServiceRq = replaceAllBody(receiveEsbServiceRq.trim(), serviceCodeScene);
		esbServiceRq = (EsbServiceRq) JAXBUtil.unmarshal(receiveEsbServiceRq, EsbServiceRq.class);
		return esbServiceRq;
	}

	/**
	 * 
	 * @param serviceCodeScene
	 * @param dataObject
	 * @return
	 */
	public static EsbSocketMessage dataObject2EsbServiceRs(String serviceCodeScene, EsbServiceRq esbServiceRq,
			DataObject dataObject) {
		EsbSocketMessage esbSocketMessage = new EsbSocketMessage();

		Class<?> classz = getEsbBodyRsByCodeScene(serviceCodeScene);
		if (classz == null) {
			esbSocketMessage.setRetCode(EsbSocketConstant.XD_SOCKET_FAILE);
			esbSocketMessage.setRetMsg("没有找到您需要的响应报文体！" + serviceCodeScene);
		} else {
			try {
				// 1、DataObject转换成报文体的JavaBean
				Object esbBodyRs = classz.newInstance();
				GitUtil.copyTo(dataObject, esbBodyRs);
				// 2、组织EsbServiceRq转换成字符串
				String strEsbServiceRs = getStrEsbServiceRs(dataObject, serviceCodeScene, esbServiceRq, esbBodyRs);
				// 、拼接长度
				strEsbServiceRs = getZeroStr(strEsbServiceRs) + strEsbServiceRs;

				esbSocketMessage.setRetCode(EsbSocketConstant.SOCKET_SUCCESS);
				esbSocketMessage.setStrEsbServiceRs(strEsbServiceRs);
			} catch (Exception e) {
				esbSocketMessage.setRetCode(EsbSocketConstant.XD_SOCKET_FAILE);
				esbSocketMessage.setRetMsg("响应报文体数据转换错误！");
			}
		}
		return esbSocketMessage;
	}

	/**
	 * 3、把EsbServiceRs转换成字符串
	 * 
	 * @param esbServiceRq
	 * @param esbBodyRs
	 * @return
	 */
	private static String getStrEsbServiceRs(DataObject dataObject, String serviceCodeScene, EsbServiceRq esbServiceRq,
			Object esbBodyRs) {
		String returnCode = dataObject.getString(EsbSocketConstant.RETURN_CODE);
		String returnMsg = dataObject.getString(EsbSocketConstant.RETURN_MSG);
		// 服务代码、服务场景(例如：serviceCodeScene=02001000001BODY01;serviceCode02001000001;serviceScene=01)
		String strEsbBodyRsByCodeScene = getStrEsbBodyRsByCodeScene(serviceCodeScene);

		String ret = "";
		EsbServiceRs esbServiceRs = new EsbServiceRs();

		EsbSysHeadRs esbSysHeadRs = getEsbSysHeadRs(esbServiceRq.getEsbSysHead());
		EsbAppHeadRs esbAppHeadRs = getEsbAppHeadRs(esbServiceRq.getEsbAppHead());
		esbAppHeadRs.setReturnCode(returnCode);
		esbAppHeadRs.setReturnMsg(returnMsg);

		esbServiceRs.setEsbSysHead(esbSysHeadRs);
		esbServiceRs.setEsbAppHead(esbAppHeadRs);

		try {
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)) {
				esbServiceRs.setEsbBody(esbBodyRs);
			}

			String strEsbServiceRs = JAXBUtil.marshalToStr(esbServiceRs, false);

			ret = replaceAll2Body(strEsbServiceRs, strEsbBodyRsByCodeScene);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private static EsbSysHeadRs getEsbSysHeadRs(EsbSysHeadRq esbSysHeadRq) {
		EsbSysHeadRs esbSysHeadRs = new EsbSysHeadRs();
		// MAC字段 String 16 必输
		esbSysHeadRs.setMac(esbSysHeadRq.getMac());
		// 服务消息ID String 36 必输 使用UUID生成
		esbSysHeadRs.setMsgId(esbSysHeadRq.getMsgId());
		// 服务目标系统ID String 10 必输
		esbSysHeadRs.setTargetSysId(esbSysHeadRq.getSourceSysId());
		// 关联消息ID String 36 可选 异步调用方式保留字段
		esbSysHeadRs.setRelatedMsgId("");
		// 服务码 String 11 必输
		esbSysHeadRs.setServiceCode(esbSysHeadRq.getServiceCode());
		// 服务场景 String 2 必输
		esbSysHeadRs.setServiceScene(esbSysHeadRq.getServiceScene());
		// 扩展内容 String 128 可选 保留字段
		esbSysHeadRs.setExtendContent("");

		return esbSysHeadRs;
	}

	private static EsbAppHeadRs getEsbAppHeadRs(EsbAppHeadRq esbAppHeadRq) {
		String sysId = getSourceSysId();
		String genUUIDString = GitUtil.genUUIDString();
		String busiDateYYYYMMDD = GitUtil.getBusiDateYYYYMMDD();

		String seqNo = sysId + busiDateYYYYMMDD.substring(2) + genUUIDString.substring(16);

		EsbAppHeadRs esbAppHeadRs = new EsbAppHeadRs();
		// 交易日期 String 8 必输 YYYYMMDD
		esbAppHeadRs.setTranDate(esbAppHeadRq.getTranDate());
		// 交易时间 String 6 必输 hhmmss
		esbAppHeadRs.setTranTime(esbAppHeadRq.getTranTime());
		// 服务提供方流水号 String 42 必输 6位系统ID+6位日期+16位序号
		esbAppHeadRs.setBackendSeqNo(seqNo);
		// 服务提供方系统ID String 10 必输 6位系统ID
		esbAppHeadRs.setBackendSysId(sysId);
		// 全局流水号 String 42 可选
		// 6位系统ID+6位日期+16位业务流程初始发起系统全局流水号与流水号一致，非初始发起系统则填原全局流水号
		esbAppHeadRs.setGlobalSeqNo(esbAppHeadRq.getGlobalSeqNo());
		// 服务返回代码 String 12 必输 正常：00000000000000 异常：编码规则参见4.9章节
		esbAppHeadRs.setReturnCode("00000000000000");
		// 服务返回信息 String 255 必输 正常：交易成功 异常：提示信息
		esbAppHeadRs.setReturnMsg("交易成功");
		// 用户语言 String 10 可选 CHINESE-中文（默认）
		esbAppHeadRs.setLangCode(esbAppHeadRq.getLangCode());

		return esbAppHeadRs;
	}

	private static Class<?> getEsbBodyRsByCodeScene(String serviceCodeScene) {
		HashMap<String, Class<?>> clsHm = new HashMap<String, Class<?>>();
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY01, EsbBodyEcifRs12002000013A01.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY02, EsbBodyEcifRs12002000013A02.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY03, EsbBodyEcifRs12002000013A02.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY04, EsbBodyEcifRs12002000013A04.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY05, EsbBodyEcifRs12002000013A02.class);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY06, EsbBodyEcifRs12002000013A02.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.GjRq02001000001BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq02001000003BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq02002000001BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY02, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY03, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY04, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.GjRq07003000001BODY01, EsbBodyGjRs07003000001A01.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY02, EsbBodyHxRs01001000002A02.class);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY03, EsbBodyHxRs01001000002A03.class);
		// -------------------------受托支付7130--------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY05, EsbBodyRs.class);
		
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY01, EsbBodyHxRs12003000004A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000001BODY01, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY01, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY02, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY03, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000003BODY01, EsbBodyHxRs12005000003A01.class);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY04, EsbBodyHxRs12005000002A04.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY01, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY02, EsbBodyHxRs12005000001A01.class);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY03, EsbBodyHxRs12005000001A01.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.RzRq12002000012BODY01, EsbBodyRzRs12002000012A01.class);
		clsHm.put(EsbSocketConstant.RzRq12003000005BODY02, EsbBodyRzRs12003000005A02.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.XdRq02001000002BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.XdRq02001000003BODY02, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.XdRq05002000001BODY01, EsbBodyRs.class);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY01, EsbBodyRs.class);
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY02, EsbBodyRs.class);

		// -------------------------核心账户查询7190--------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY10, EsbBodyHxRs12003000004A10.class);

		// --------------------------面谈面签---------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.MtmqRq02003000003BODY01, EsbBodyMtmqRs02003000003A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY09, EsbBodyMtmqRs12002000013A09.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY10, EsbBodyMtmqRs12002000013A10.class);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY13, EsbBodyMtmqRs12002000013A13.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY01, EsbBodyMtmqRs02002000004A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY02, EsbBodyMtmqRs02002000004A02.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY03, EsbBodyMtmqRs02003000004A03.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY01, EsbBodyMtmqRs02002000003A01.class);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY02, EsbBodyMtmqRs02002000003A02.class);

		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY06, EsbBodyMtmqRs02003000004A06.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY07, EsbBodyMtmqRs02003000004A07.class);

		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY03, EsbBodyMtmqRs02002000003A03.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY02, EsbBodyMtmqRs02003000004A02.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY05, EsbBodyMtmqRs02003000004A05.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY04, EsbBodyMtmqRs02003000004A04.class);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY01, EsbBodyMtmqRs02003000004A01.class);

		return clsHm.get(serviceCodeScene);
	}

	private static String getStrEsbBodyRsByCodeScene(String serviceCodeScene) {
		HashMap<String, String> clsHm = new HashMap<String, String>();
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY01, EsbSocketConstant.EcifRq12002000013BODY01);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY02, EsbSocketConstant.EcifRq12002000013BODY02);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY03, EsbSocketConstant.EcifRq12002000013BODY02);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY04, EsbSocketConstant.EcifRq12002000013BODY04);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY05, EsbSocketConstant.EcifRq12002000013BODY02);
		clsHm.put(EsbSocketConstant.EcifRq12002000013BODY06, EsbSocketConstant.EcifRq12002000013BODY02);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.GjRq02001000001BODY01, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq02001000003BODY01, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq02002000001BODY01, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY01, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq05001000001BODY02, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY03, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq05001000002BODY04, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.GjRq07003000001BODY01, EsbSocketConstant.GjRq07003000001BODY01);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY02, EsbSocketConstant.HxRq01001000002BODY02);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY03, EsbSocketConstant.HxRq01001000002BODY03);
		clsHm.put(EsbSocketConstant.HxRq01001000002BODY05, EsbSocketConstant.HxRq01001000002BODY05);
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY01, EsbSocketConstant.HxRq12003000004BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000001BODY01, EsbSocketConstant.HxRq12005000001BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY01, EsbSocketConstant.HxRq12005000001BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY02, EsbSocketConstant.HxRq12005000001BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY03, EsbSocketConstant.HxRq12005000001BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000003BODY01, EsbSocketConstant.HxRq12005000003BODY01);
		clsHm.put(EsbSocketConstant.HxRq12005000002BODY04, EsbSocketConstant.HxRq12005000002BODY04);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY01, EsbSocketConstant.HxRq02002000002BODY01);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY02, EsbSocketConstant.HxRq02002000002BODY02);
		clsHm.put(EsbSocketConstant.HxRq02002000002BODY03, EsbSocketConstant.HxRq02002000002BODY03);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.RzRq12002000012BODY01, EsbSocketConstant.RzRq12002000012BODY01);
		clsHm.put(EsbSocketConstant.RzRq12003000005BODY02, EsbSocketConstant.RzRq12003000005BODY02);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.XdRq02001000002BODY01, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.XdRq02001000003BODY02, EsbSocketConstant.ESBBODYRS);
		clsHm.put(EsbSocketConstant.XdRq05002000001BODY01, EsbSocketConstant.ESBBODYRS);
		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY01, EsbSocketConstant.WmaRq03002000011BODY01);
		clsHm.put(EsbSocketConstant.WmaRq03002000011BODY02, EsbSocketConstant.WmaRq03002000011BODY02);
		// -------------------------核心账户查询7190--------------------------------------------------------
		clsHm.put(EsbSocketConstant.HxRq12003000004BODY10, EsbSocketConstant.HxRq12003000004BODY10);

		// -----------------------------------------------------------------------------------------------
		clsHm.put(EsbSocketConstant.MtmqRq02003000003BODY01, EsbSocketConstant.MtmqRq02003000003BODY01);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY09, EsbSocketConstant.MtmqRq12002000013BODY09);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY10, EsbSocketConstant.MtmqRq12002000013BODY10);
		clsHm.put(EsbSocketConstant.MtmqRq12002000013BODY13, EsbSocketConstant.MtmqRq12002000013BODY13);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY01, EsbSocketConstant.MtmqRq02003000004BODY01);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY02, EsbSocketConstant.MtmqRq02003000004BODY02);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY03, EsbSocketConstant.MtmqRq02003000004BODY03);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY01, EsbSocketConstant.MtmqRq02002000004BODY01);
		clsHm.put(EsbSocketConstant.MtmqRq02002000004BODY02, EsbSocketConstant.MtmqRq02002000004BODY02);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY01, EsbSocketConstant.MtmqRq02002000003BODY01);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY02, EsbSocketConstant.MtmqRq02002000003BODY02);

		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY06, EsbSocketConstant.MtmqRq02003000004BODY06);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY07, EsbSocketConstant.MtmqRq02003000004BODY07);

		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY05, EsbSocketConstant.MtmqRq02003000004BODY05);
		clsHm.put(EsbSocketConstant.MtmqRq02002000003BODY03, EsbSocketConstant.MtmqRq02002000003BODY03);
		clsHm.put(EsbSocketConstant.MtmqRq02003000004BODY04, EsbSocketConstant.MtmqRq02003000004BODY04);

		return clsHm.get(serviceCodeScene);
	}

	/**
	 * 4、字符串转换成EsbServiceRs
	 * 
	 * @param serviceCodeScene
	 * @param receiveEsbServiceRs
	 * @return
	 */
	public static EsbServiceRs getEsbServiceRs(String serviceCodeScene, String receiveEsbServiceRs) {
		String strEsbBodyRsByKey = getStrEsbBodyRsByCodeScene(serviceCodeScene);
		receiveEsbServiceRs = replaceAllBody(receiveEsbServiceRs, strEsbBodyRsByKey).trim();
		EsbServiceRs esbServiceRs = (EsbServiceRs) JAXBUtil.unmarshal(receiveEsbServiceRs, EsbServiceRs.class);
		return esbServiceRs;
	}

	private static String getZeroStr(String str) {
		int len = 0;
		try {
			len = str.getBytes(EsbSocketConstant.CHARCODE_UTF8).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String ret = "";
		String strlen = String.valueOf(len);

		String p = "0";
		int sum = 8;
		for (int i = 0; i < sum - strlen.length(); i++) {
			ret = ret + p;
		}
		ret = ret + strlen;
		return ret;
	}

	private static String replaceAll2Body(String strEsbServiceRq, String serviceCodeScene) {
		String ret = strEsbServiceRq.replaceAll(serviceCodeScene + ">", EsbSocketConstant.BODY + ">");

		return ret;
	}

	public static String replaceAllBody(String receiveEsbServiceRs, String serviceCodeScene) {
		String ret = receiveEsbServiceRs.replaceAll(EsbSocketConstant.BODY + ">", serviceCodeScene + ">");

		return ret;
	}

	/**
	 * 
	 * @param esbServiceRq
	 * @return
	 */
	public static String getServiceCodeScene(EsbServiceRq esbServiceRq) {
		String serviceCode = esbServiceRq.getEsbSysHead().getServiceCode();// 服务码
		String serviceScene = esbServiceRq.getEsbSysHead().getServiceScene();// 服务场景
		String serviceCodeScene = EsbSocketConstant.RQ + serviceCode + EsbSocketConstant.BODY + serviceScene;

		return serviceCodeScene;
	}

	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("类型转换")
	public static void setDataObject(DataObject dataObject, String arrayName, Object[] array) {
		/*
		 * if("esbBodyHxRqTskArrays".equals(arrayName)) { array = new Object[2];
		 * EsbBodyHxRqTskArray esbBodyHxRqTskArray = new EsbBodyHxRqTskArray();
		 * esbBodyHxRqTskArray.setGdsNm(""); esbBodyHxRqTskArray.setCcyTp("14");
		 * esbBodyHxRqTskArray.setTxnAmt(10000);
		 * esbBodyHxRqTskArray.setRmk("备注"); array[0] = esbBodyHxRqTskArray;
		 * 
		 * esbBodyHxRqTskArray = new EsbBodyHxRqTskArray();
		 * esbBodyHxRqTskArray.setGdsNm(""); esbBodyHxRqTskArray.setCcyTp("12");
		 * esbBodyHxRqTskArray.setTxnAmt(20000);
		 * esbBodyHxRqTskArray.setRmk("备注备注"); array[1] = esbBodyHxRqTskArray;
		 * }else if("esbBodyGjRqMrgnArrays".equals(arrayName)) { array = new
		 * Object[2]; EsbBodyGjRqMrgnArray esbBodyGjRqMrgnArray = new
		 * EsbBodyGjRqMrgnArray();
		 * esbBodyGjRqMrgnArray.setMrgnAcctNo("617011400100005350");
		 * esbBodyGjRqMrgnArray.setMrgnAmt(10000);
		 * esbBodyGjRqMrgnArray.setMrgnCcy("USD"); array[0] =
		 * esbBodyGjRqMrgnArray;
		 * 
		 * esbBodyGjRqMrgnArray = new EsbBodyGjRqMrgnArray();
		 * esbBodyGjRqMrgnArray.setMrgnAcctNo("337010100100038220");
		 * esbBodyGjRqMrgnArray.setMrgnAmt(60000);
		 * esbBodyGjRqMrgnArray.setMrgnCcy("CNY"); array[1] =
		 * esbBodyGjRqMrgnArray; }else
		 * if("esbBodyEcifRqLoAdrArrays".equals(arrayName)) { array = new
		 * Object[3]; EsbBodyEcifRqLoAdrArray esbBodyEcifRqLoAdrArray = new
		 * EsbBodyEcifRqLoAdrArray();
		 * esbBodyEcifRqLoAdrArray.setAdrCd("1041002");
		 * esbBodyEcifRqLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqLoAdrArray.setStrAdr("火车站");
		 * esbBodyEcifRqLoAdrArray.setCtcAdr("绵阳市滨江支行3号");
		 * esbBodyEcifRqLoAdrArray.setDfltAdrFlg("0"); array[0] =
		 * esbBodyEcifRqLoAdrArray;
		 * 
		 * esbBodyEcifRqLoAdrArray = new EsbBodyEcifRqLoAdrArray();
		 * esbBodyEcifRqLoAdrArray.setAdrCd("1041001");
		 * esbBodyEcifRqLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqLoAdrArray.setStrAdr("汽车站");
		 * esbBodyEcifRqLoAdrArray.setCtcAdr("绵阳市滨江支行4号");
		 * esbBodyEcifRqLoAdrArray.setDfltAdrFlg("0"); array[1] =
		 * esbBodyEcifRqLoAdrArray;
		 * 
		 * esbBodyEcifRqLoAdrArray = new EsbBodyEcifRqLoAdrArray();
		 * esbBodyEcifRqLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqLoAdrArray.setStrAdr("汽车站");
		 * esbBodyEcifRqLoAdrArray.setCtcAdr("绵阳市滨江支行5号");
		 * esbBodyEcifRqLoAdrArray.setDfltAdrFlg("1"); array[2] =
		 * esbBodyEcifRqLoAdrArray; }else
		 * if("esbBodyEcifRqCtcInfArrays".equals(arrayName)) { array = new
		 * Object[3]; EsbBodyEcifRqCtcInfArray esbBodyEcifRqCtcInfArray = new
		 * EsbBodyEcifRqCtcInfArray();
		 * esbBodyEcifRqCtcInfArray.setCtcCd("1581003");
		 * esbBodyEcifRqCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqCtcInfArray.setCtcInf("13700000000");
		 * esbBodyEcifRqCtcInfArray.setDfltCtcFlg("01"); array[0] =
		 * esbBodyEcifRqCtcInfArray;
		 * 
		 * esbBodyEcifRqCtcInfArray = new EsbBodyEcifRqCtcInfArray();
		 * esbBodyEcifRqCtcInfArray.setCtcCd("1581004");
		 * esbBodyEcifRqCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqCtcInfArray.setCtcInf("13700000001");
		 * esbBodyEcifRqCtcInfArray.setDfltCtcFlg("0"); array[1] =
		 * esbBodyEcifRqCtcInfArray;
		 * 
		 * esbBodyEcifRqCtcInfArray = new EsbBodyEcifRqCtcInfArray();
		 * esbBodyEcifRqCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqCtcInfArray.setCtcInf("13700000002");
		 * esbBodyEcifRqCtcInfArray.setDfltCtcFlg("1"); array[2] =
		 * esbBodyEcifRqCtcInfArray; }else
		 * if("esbBodyEcifRqIdentInfArrays".equals(arrayName)) { array = new
		 * Object[1]; EsbBodyEcifRqIdentInfArray esbBodyEcifRqIdentInfArray =
		 * new EsbBodyEcifRqIdentInfArray();
		 * esbBodyEcifRqIdentInfArray.setIdntTp("140");
		 * esbBodyEcifRqIdentInfArray.setIdentNo("360701199208110004");
		 * esbBodyEcifRqIdentInfArray.setIssuOffc("绵阳市");
		 * esbBodyEcifRqIdentInfArray.setSgnDt("20001101");
		 * esbBodyEcifRqIdentInfArray.setIdentEfftEndDt("20201101");
		 * esbBodyEcifRqIdentInfArray.setDfltIdentFlg("0"); array[0] =
		 * esbBodyEcifRqIdentInfArray; }else
		 * if("esbBodyEcifRqGLoAdrArrays".equals(arrayName)) { array = new
		 * Object[3]; EsbBodyEcifRqGLoAdrArray esbBodyEcifRqGLoAdrArray = new
		 * EsbBodyEcifRqGLoAdrArray();
		 * esbBodyEcifRqGLoAdrArray.setAdrCd("1041006");
		 * esbBodyEcifRqGLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqGLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqGLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqGLoAdrArray.setStrAdr("火车站");
		 * esbBodyEcifRqGLoAdrArray.setOffcAdr("绵阳市庐山国际4号");
		 * esbBodyEcifRqGLoAdrArray.setDfltAdrFlg("0"); array[0] =
		 * esbBodyEcifRqGLoAdrArray;
		 * 
		 * esbBodyEcifRqGLoAdrArray = new EsbBodyEcifRqGLoAdrArray();
		 * esbBodyEcifRqGLoAdrArray.setAdrCd("1041007");
		 * esbBodyEcifRqGLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqGLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqGLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqGLoAdrArray.setStrAdr("汽车站");
		 * esbBodyEcifRqGLoAdrArray.setOffcAdr("绵阳市庐山国际5号");
		 * esbBodyEcifRqGLoAdrArray.setDfltAdrFlg("0"); array[1] =
		 * esbBodyEcifRqGLoAdrArray;
		 * 
		 * esbBodyEcifRqGLoAdrArray = new EsbBodyEcifRqGLoAdrArray();
		 * esbBodyEcifRqGLoAdrArray.setAdrTp("100");
		 * esbBodyEcifRqGLoAdrArray.setCtyCd("CHN");
		 * esbBodyEcifRqGLoAdrArray.setAdmnRgonCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setPstCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setProvCd("110000");
		 * esbBodyEcifRqGLoAdrArray.setDstcCd("110100");
		 * esbBodyEcifRqGLoAdrArray.setStrAdr("汽车站");
		 * esbBodyEcifRqGLoAdrArray.setOffcAdr("绵阳市庐山国际6号");
		 * esbBodyEcifRqGLoAdrArray.setDfltAdrFlg("1"); array[2] =
		 * esbBodyEcifRqGLoAdrArray; }else
		 * if("esbBodyEcifRqGCtcInfArrays".equals(arrayName)) { array = new
		 * Object[3]; EsbBodyEcifRqGCtcInfArray esbBodyEcifRqGCtcInfArray = new
		 * EsbBodyEcifRqGCtcInfArray();
		 * esbBodyEcifRqGCtcInfArray.setCtcCd("160003");
		 * esbBodyEcifRqGCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqGCtcInfArray.setCtcInf("13800000000");
		 * esbBodyEcifRqGCtcInfArray.setDfltCtcFlg("0"); array[0] =
		 * esbBodyEcifRqGCtcInfArray;
		 * 
		 * esbBodyEcifRqGCtcInfArray = new EsbBodyEcifRqGCtcInfArray();
		 * esbBodyEcifRqGCtcInfArray.setCtcCd("160004");
		 * esbBodyEcifRqGCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqGCtcInfArray.setCtcInf("13800000001");
		 * esbBodyEcifRqGCtcInfArray.setDfltCtcFlg("0"); array[1] =
		 * esbBodyEcifRqGCtcInfArray;
		 * 
		 * esbBodyEcifRqGCtcInfArray = new EsbBodyEcifRqGCtcInfArray();
		 * esbBodyEcifRqGCtcInfArray.setCtcMth("13");
		 * esbBodyEcifRqGCtcInfArray.setCtcInf("13800000002");
		 * esbBodyEcifRqGCtcInfArray.setDfltCtcFlg("1"); array[2] =
		 * esbBodyEcifRqGCtcInfArray; }else
		 * if("esbBodyEcifRqGKeyPrsnInfArrays".equals(arrayName)) { array = new
		 * Object[3]; EsbBodyEcifRqGKeyPrsnInfArray
		 * esbBodyEcifRqGKeyPrsnInfArray = new EsbBodyEcifRqGKeyPrsnInfArray();
		 * esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnCd("50001");
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrTp("1");//2
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrNm("张三1");
		 * esbBodyEcifRqGKeyPrsnInfArray.setNation("01");
		 * esbBodyEcifRqGKeyPrsnInfArray.setGndInd("1"); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setNtntyCd(ntntyCd);
		 * esbBodyEcifRqGKeyPrsnInfArray.setMarriageCd("10");
		 * esbBodyEcifRqGKeyPrsnInfArray.setBirthDate("19900813");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdntTp("101");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdentNo("340101199008130012");
		 * esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnSt("1"); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setFmSysInd("3"); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setOcpCd(ocpCd); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setHighEdct(highEdct); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setCtcAdr(ctcAdr); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setCtcNo(ctcNo); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setPstCd2(pstCd2); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setOffcTel(offcTel); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setPosCd(posCd); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setqQNo(qQNo); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setwChtNo(wChtNo); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setEmail(email); array[0] =
		 * esbBodyEcifRqGKeyPrsnInfArray;
		 * 
		 * esbBodyEcifRqGKeyPrsnInfArray = new EsbBodyEcifRqGKeyPrsnInfArray();
		 * esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnCd("50002");
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrTp("2");
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrNm("李四1");
		 * esbBodyEcifRqGKeyPrsnInfArray.setNation("01");
		 * esbBodyEcifRqGKeyPrsnInfArray.setGndInd("2");
		 * esbBodyEcifRqGKeyPrsnInfArray.setMarriageCd("10");
		 * esbBodyEcifRqGKeyPrsnInfArray.setBirthDate("19890813");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdntTp("101");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdentNo("340101198908130021");
		 * esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnSt("1"); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setFmSysInd("3"); array[1] =
		 * esbBodyEcifRqGKeyPrsnInfArray;
		 * 
		 * esbBodyEcifRqGKeyPrsnInfArray = new EsbBodyEcifRqGKeyPrsnInfArray();
		 * // esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnCd("50002");
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrTp("2");
		 * esbBodyEcifRqGKeyPrsnInfArray.setShrhlrNm("王五1");
		 * esbBodyEcifRqGKeyPrsnInfArray.setNation("01");
		 * esbBodyEcifRqGKeyPrsnInfArray.setGndInd("2");
		 * esbBodyEcifRqGKeyPrsnInfArray.setMarriageCd("10");
		 * esbBodyEcifRqGKeyPrsnInfArray.setBirthDate("19890813");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdntTp("101");
		 * esbBodyEcifRqGKeyPrsnInfArray.setIdentNo("340101198908130021");
		 * esbBodyEcifRqGKeyPrsnInfArray.setKeyPrsnSt("1"); //
		 * esbBodyEcifRqGKeyPrsnInfArray.setFmSysInd("3"); array[2] =
		 * esbBodyEcifRqGKeyPrsnInfArray; }
		 */

		dataObject.set(arrayName, array);
	}
	@Bizlet("")
	public static   HashMap<String, String> checkData(String serviceCodeScene, EsbServiceRq esbServiceRq) throws Throwable{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String returnCode = EsbSocketConstant.SOCKET_SUCCESS;
		String returnMsg = "交易成功";

		// -----------------------------------------------------------------------
		// 验证规则
		// 验证服务是否存在
		String strEsbBodyRsByCodeScene = getStrEsbBodyRsByCodeScene(serviceCodeScene);
		if (strEsbBodyRsByCodeScene == null || strEsbBodyRsByCodeScene.length() <= 0) {
			returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
			returnMsg = "信贷系统不存在此服务！";
		}
		// -----------------------------------------------------------------------
		// 验证系统头
		/*
		 * 1. Mac MAC字段 String 16 必输 2. MsgId 服务消息ID String 36 必输 使用UUID生成 3.
		 * SourceSysId 服务源发起系统ID String 10 必输 4. ConsumerId 服务调用方系统ID String 10
		 * 必输 5. ServiceCode 服务码 String 11 必输 6. ServiceScene 服务场景 String 2 必输
		 */
		EsbSysHeadRq esbSysHead = esbServiceRq.getEsbSysHead();

		if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)) {
			String mac = esbSysHead.getMac();
			String msgId = esbSysHead.getMsgId();
			String sourceSysId = esbSysHead.getSourceSysId();
			String consumerId = esbSysHead.getConsumerId();

			if (mac == null || mac.length() <= 0) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "MAC字段不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (msgId == null || msgId.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "服务消息ID不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)
					&& (sourceSysId == null || sourceSysId.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "服务源发起系统ID不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (consumerId == null || consumerId.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "服务调用方系统ID不能为空！";
			}
		}

		// -----------------------------------------------------------------------
		// 验证应用头
		/*
		 * 1. TranDate 交易日期 String 8 必输 YYYYMMDD 2. TranTime 交易时间 String 6 必输
		 * hhmmss 3. TranTellerNo 交易柜员 String 30 必输 有柜员号则填柜员号 无柜员号则填用户ID 4.
		 * TranSeqNo 交易流水号 String 42 必输 6位系统ID+6位日期+16位序号
		 */
		EsbAppHeadRq esbAppHead = esbServiceRq.getEsbAppHead();

		if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)) {
			String tranDate = esbAppHead.getTranDate();
			String tranTime = esbAppHead.getTranTime();
			String tranTellerNo = esbAppHead.getTranTellerNo();
			String tranSeqNo = esbAppHead.getTranSeqNo();

			if (tranDate == null || tranDate.length() <= 0) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "交易日期不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (tranTime == null || tranTime.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "交易时间不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)
					&& (tranTellerNo == null || tranTellerNo.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "交易柜员不能为空！";
			}
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (tranSeqNo == null || tranSeqNo.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "交易流水号不能为空！";
			}
		}

		// -----------------------------------------------------------------------
		// 验证报文体
		Object esbBody = esbServiceRq.getEsbBody();
	
		// 02001000003贷款信息登记 02贷款放款通知
		/*
		 * CtrNo 合同号 String(20) Y DbtNo 借据号 String(20) Y BsnTp 业务类型 String(10) Y
		 * RmtInstNo 汇款机构号 String(10) Y TxnAmt 交易金额 Double(20,2) Y DbtBal 借据余额
		 * Double(20,2) Y LndAcctNo 放款账号 String(35) Y PyeAcctNo 收款人账号 String(35)
		 * Y
		 */
		if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)
				&& EsbSocketConstant.XdRq02001000003BODY02.equals(serviceCodeScene)) {
			EsbBodyXdRq02001000003A02 esbBodyXdRq02001000003A02 = (EsbBodyXdRq02001000003A02) esbBody;

			String ctrNo = esbBodyXdRq02001000003A02.getCtrNo();
			String dbtNo = esbBodyXdRq02001000003A02.getDbtNo();
			String bsnTp = esbBodyXdRq02001000003A02.getBsnTp();
			String rmtInstNo = esbBodyXdRq02001000003A02.getRmtInstNo();
			double txnAmt = esbBodyXdRq02001000003A02.getTxnAmt();
			double dbtBal = esbBodyXdRq02001000003A02.getDbtBal();
			String lndAcctNo = esbBodyXdRq02001000003A02.getLndAcctNo();
			String pyeAcctNo = esbBodyXdRq02001000003A02.getPyeAcctNo();

			/*
			 * if(ctrNo == null || ctrNo.length() <= 0) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "合同号不能为空！"; }
			 */
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (dbtNo == null || dbtNo.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "借据号不能为空！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (bsnTp
			 * == null || bsnTp.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "业务类型不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * (rmtInstNo == null || rmtInstNo.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "汇款机构号不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && txnAmt
			 * < 0) { returnCode = EsbSocketConstant.SOCKET_FAILE; returnMsg =
			 * "交易金额不能小于0！"; }
			 */
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && dbtBal < 0) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "借据余额不能小于0！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * (lndAcctNo == null || lndAcctNo.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "放款账号不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * (pyeAcctNo == null || pyeAcctNo.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "收款人账号不能为空！"; }
			 */}

		// -----------------------------------------------------------------------
		// 02001000002贷款还款 01贸易融资还款
		/*
		 * CtrNo 合同号 String(20) Y 原始放款合同号 DbtNo 借据号 String(20) N 原始放款借据号 BsnTp
		 * 业务类型 String(10) Y 参数定义见3.4.1 ClrCtrInd 结清合同标志 String(1) CcyTp 货币种类
		 * String(3) Y PymtAmt 还款金额 Double(20,2) Y DbtBal 借据余额 Double(20,2)
		 * InstID 机构ID String(9) Y 还款机构 TlrID 柜员ID String(10) Y 还款柜员 PrePaidInd
		 * 垫款标志 String(1) Y PrePaidAcctNo 垫款账号 String(35) N 当字段 ispaddingfund
		 * 为Y时，必填 PrePaidCcy 垫款币种 String(3) N 参数定义 ， 为信用证币种当字段 ispaddingfund
		 * 为Y时，必填 PrePaidAmt 垫款金额 Double(20,2) N 当字段 ispaddingfund 为Y时，必填
		 */
		if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)
				&& EsbSocketConstant.XdRq02001000002BODY01.equals(serviceCodeScene)) {
			EsbBodyXdRq02001000002A01 esbBodyXdRq02001000002A01 = (EsbBodyXdRq02001000002A01) esbBody;

			String ctrNo = esbBodyXdRq02001000002A01.getCtrNo();
			String dbtNo = esbBodyXdRq02001000002A01.getDbtNo();
			String bsnTp = esbBodyXdRq02001000002A01.getBsnTp();
			String clrCtrInd = esbBodyXdRq02001000002A01.getClrCtrInd();
			String ccyTp = esbBodyXdRq02001000002A01.getCcyTp();
			double pymtAmt = esbBodyXdRq02001000002A01.getPymtAmt();
			double dbtBal = esbBodyXdRq02001000002A01.getDbtBal();
			String instID = esbBodyXdRq02001000002A01.getInstID();
			String tlrID = esbBodyXdRq02001000002A01.getTlrID();
			String prePaidInd = esbBodyXdRq02001000002A01.getPrePaidInd();
			String prePaidAcctNo = esbBodyXdRq02001000002A01.getPrePaidAcctNo();
			String prePaidCcy = esbBodyXdRq02001000002A01.getPrePaidCcy();
			double prePaidAmt = esbBodyXdRq02001000002A01.getPrePaidAmt();

			/*
			 * if(ctrNo == null || ctrNo.length() <= 0) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "合同号不能为空！"; }
			 */if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (dbtNo == null || dbtNo.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "借据号不能为空！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (bsnTp
			 * == null || bsnTp.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "业务类型不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * (clrCtrInd == null || clrCtrInd.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "结清合同标志不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (ccyTp
			 * == null || ccyTp.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "货币种类不能为空！"; }
			 */
			if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && pymtAmt < 0) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "还款金额不能小于0！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && dbtBal
			 * < 0) { returnCode = EsbSocketConstant.SOCKET_FAILE; returnMsg =
			 * "借据余额不能小于0！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (instID
			 * == null || instID.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "机构ID不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (tlrID
			 * == null || tlrID.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "柜员ID不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * (prePaidInd == null || prePaidInd.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "垫款标志不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * "Y".equals(prePaidInd) && (prePaidAcctNo == null ||
			 * prePaidAcctNo.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "垫款账号不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * "Y".equals(prePaidInd) && (prePaidCcy == null ||
			 * prePaidCcy.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "垫款币种不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) &&
			 * "Y".equals(prePaidInd) && prePaidAmt < 0) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "垫款金额不能小于0！"; }
			 */
		}

		// -----------------------------------------------------------------------
		// 05002000001国际结算业务维护 01国际业务撤销/闭卷/取消/冲账
		/*
		 * CtrNo 合同号 String(20) Y DbtNo 借据号 String(20) N BsnFlg 业务标志 String(1) Y
		 * 0.信用证 1.保函、2其他 OvrdAmt 撤销金额 Double(20,2) OprInd 操作标志 String(4) Y
		 * 0.闭卷(该笔业务结束了，强制余额都清0)， 1 .冲账(业务做完了，在尚未发报之前又不做了，以后也不做了，此时已经产生了bizno)，
		 * 2撤销； 以上状态都不可重做或再次从信贷发起同一笔业务，所以状态置注销，还回额度 TlrID 柜员ID String(10) Y
		 * 国结进口来单的复合柜员 BsnTp 业务类型 String(10) Y 参数定义
		 */
		if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)
				&& EsbSocketConstant.XdRq05002000001BODY01.equals(serviceCodeScene)) {
			EsbBodyXdRq05002000001A01 esbBodyXdRq05002000001A01 = (EsbBodyXdRq05002000001A01) esbBody;

			String ctrNo = esbBodyXdRq05002000001A01.getCtrNo();
			String dbtNo = esbBodyXdRq05002000001A01.getDbtNo();
			String bsnFlg = esbBodyXdRq05002000001A01.getBsnFlg();
			double ovrdAmt = esbBodyXdRq05002000001A01.getOvrdAmt();
			String oprInd = esbBodyXdRq05002000001A01.getOprInd();
			String tlrID = esbBodyXdRq05002000001A01.getTlrID();
			String bsnTp = esbBodyXdRq05002000001A01.getBsnTp();

			/*
			 * if(ctrNo == null || ctrNo.length() <= 0) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "合同号不能为空！"; }
			 */if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (dbtNo == null || dbtNo.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "借据号不能为空！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (bsnFlg
			 * == null || bsnFlg.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "业务标志不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && ovrdAmt
			 * < 0) { returnCode = EsbSocketConstant.SOCKET_FAILE; returnMsg =
			 * "撤销金额不能小于0！"; }
			 */if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (oprInd == null || oprInd.length() <= 0)) {
				returnCode = EsbSocketConstant.XD_SOCKET_FAILE;
				returnMsg = "操作标志不能为空！";
			}
			/*
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (tlrID
			 * == null || tlrID.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "柜员ID不能为空！"; }
			 * if(EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode) && (bsnTp
			 * == null || bsnTp.length() <= 0)) { returnCode =
			 * EsbSocketConstant.SOCKET_FAILE; returnMsg = "业务类型不能为空！"; }
			 */}
		// -----------------------------------------------------------------------
		// 03002000011票据信息维护 02银行承兑汇票打印撤销
		/*
		 * CtrNo 合同号 String(20) Y ChrgOffNo 出账号 String(20) Y OprInd 操作标志
		 * String(4) Y 1-撤销， SumNum 总笔数 String(9) Y BsnTp 业务类型 String(10) Y 参数定义
		 */
		
		//校验报文体数据的合法性
		Object[] result = null;
		DataObject obj= BeanToMapUtil.convertBean(esbBody);
		obj.setString(EsbSocketConstant.SERVICE_CODE_SCENE, serviceCodeScene);
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pub.socket.util.checkData");
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = obj;
		result = logicComponent.invoke("checkData", params);
		if (result != null && result.length > 0) {
			if(null!=result[0]){
				returnCode="00000000000002";
				returnMsg=result[0].toString();
			}
		}

		// 验证结束------------------------------------------------
		hashMap.put(EsbSocketConstant.RETURN_CODE, returnCode);
		hashMap.put(EsbSocketConstant.RETURN_MSG, returnMsg);
		return hashMap;
	}

}
