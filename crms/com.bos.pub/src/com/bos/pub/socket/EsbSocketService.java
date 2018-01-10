package com.bos.pub.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.jaxb.JAXBUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqBillInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqBondInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqConsInPrgsInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqCrgLadBillInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqCrgWrntInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqDepRecptInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqEsttInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqExprtRbtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqFncPdInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqFndInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqFrchsRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqFrstRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqIntPtyInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqInvtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqLandCtrMgtRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqLandUseRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqMchnInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqOthrMrtgAstInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqOthrPftRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqOthrPlgAstInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqRcvbInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqRoadBrdgChrgRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqStkInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqStkRghtInfArray;
import com.bos.pub.socket.service.mortgage.request.EsbBodyWmaRqVhclInfArray;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqEsttInf;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqLandBnkgInf;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.bos.pub.socket.service.response.base.EsbServiceRs;
import com.bos.pub.socket.util.BeanToMapUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.bos.pub.socket.util.EsbSocketMessage;
import com.bos.pub.socket.util.EsbSocketUtil;
import com.bos.pub.socket.util.LinkSocketStartupUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class EsbSocketService {
	private static TraceLogger log = new TraceLogger(EsbSocketService.class);

	private static String IP_ADDR = "127.0.0.1";
	private static int PORT = 10000;

	private static EsbSocketService esbSocketService = null;
	// 报文长度位数(8)：是整个报文体的长度，包括交易代码。
	private static final int MESSAGE_LENGTH_DIGIT = 8;
	// 报文最小长度、最大长度
	private static final int BUSINESS_CODE_DIGIT = 8;

	public static EsbSocketService instance() {
		if (esbSocketService == null) {
			esbSocketService = new EsbSocketService();
		}
		return esbSocketService;
	}

	/**
	 * @param serviceCode
	 *            服务码 - -
	 * @param serviceScene
	 *            服务场景
	 * @param esbBody服务body业务体
	 *            - -
	 * @return 访问ESB系统服务返回的数据对象 一次性读取,讲报文字符串存放在数据库中
	 */
	@SuppressWarnings("unused")
	@Bizlet("socketDataObject")
	public DataObject socketDataObject(String serviceCodeScene, EsbAppHeadRq iEsbAppHeadRq, DataObject dataObject)
			throws Exception {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_ESB_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_ESB_IP;
		String port = EsbSocketConstant.CONTRIBUTION_ESB_PORT;

		IP_ADDR = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String p = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		PORT = Integer.parseInt(p);

		log.info("加载ESB服务端配置：module={0},group={1},ip={2},port={3},esb_ip_port={4}:{5}", new Object[] { module, group, ip, port,
				IP_ADDR, PORT });
		log.info("客户端发送的请求交易代码：{0}", new Object[] { serviceCodeScene });

		// DataObject->EsbBody(Object)->strEsbService->socket->receiveEsbService->receiveEsbBody->DataObject
		DataObject retDataObject = DataFactory.INSTANCE.create("com.bos.pub.meta", "TbPubDate");
		
		Socket socket = null;
		Reader reader = null;
		InputStream in = null;
		OutputStream out = null;
		PrintWriter pw = null;
		try {
			retDataObject.setString(EsbSocketConstant.RETURN_CODE, EsbSocketConstant.SOCKET_SUCCESS);
			return retDataObject;
//			EsbSocketMessage esbSocketMessage = EsbSocketUtil
//					.dataObject2EsbServiceRq(serviceCodeScene, iEsbAppHeadRq, dataObject);
//			String retCode = esbSocketMessage.getRetCode();
//			String retMsg = esbSocketMessage.getRetMsg();
//
//			if (!EsbSocketConstant.SOCKET_SUCCESS.equals(retCode)) {
//				log.error(retMsg);
//				throw new Exception(retMsg);
//			}
//
//			log.info("调用ESB接口，DataObject转换成对应交易的body");
//
//			String strEsbServiceRq = esbSocketMessage.getStrEsbServiceRq();
//
//			socket = new Socket(IP_ADDR, PORT);
//			in = socket.getInputStream();
//			reader = new InputStreamReader(in, EsbSocketConstant.CHARCODE_UTF8);
//			out = socket.getOutputStream();
//			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, EsbSocketConstant.CHARCODE_UTF8);
//			pw = new PrintWriter(outputStreamWriter, true);
//
//			log.info("客户端发送请求报文：" + strEsbServiceRq);
//			pw.println(strEsbServiceRq);
//
//			saveMessageRecord(strEsbServiceRq, serviceCodeScene, "request");
//
//			char[] messageLen = new char[MESSAGE_LENGTH_DIGIT];// 定义一个8位长度的字符数组，用来存放返回报文的前八位
//			int messageLen_ = 0;
//			if (null != reader) {
//				messageLen_ = reader.read(messageLen, 0, MESSAGE_LENGTH_DIGIT);// 将流中的0-8位的报文存放到messageLen中
//			} else if (null != in) {
//				byte[] byteMessageLen = new byte[MESSAGE_LENGTH_DIGIT];// 8位长度的字节数组
//				messageLen_ = in.read(byteMessageLen, 0, MESSAGE_LENGTH_DIGIT);
//				messageLen = new String(byteMessageLen, EsbSocketConstant.CHARCODE_UTF8).toCharArray();
//			}
//
//			if (messageLen_ >= BUSINESS_CODE_DIGIT) {
//				if (!"".equals(new String(messageLen).trim())) {
//					/* 提取公共部分之前读取报文的方法 */
//					// String strlen = new String(messageLen);//
//					// 将报文前8位的字符数组转换成字符串。
//					// int messageLength = Integer.parseInt(strlen);//
//					// 将前8位报文的字符串转换成报文长度int.
//					// log.info("响应报文长度：" + messageLength);
//					// char[] message = new char[messageLength];
//					// int message_ = 0;
//					// String receiveEsbServiceRs = "";
//					// if (null != reader) {
//					//
//					// /*-------老方法，读长度为messageLength的报文-------*
//					// message_ = reader.read(message, 0,
//					// messageLength);//将流中的报文从第0位读到messageLength位存放到message中。
//					// receiveEsbServiceRs = new
//					// String(message);//将字符数组报文转换成字符串。
//					// /*-------老方法，读长度为messageLength的报文-------*/
//					//
//					// /*-------新方法循环读取报文，不取报文中长度-------*/
//					// char[] messageC = new char[512];
//					// int len = 0;
//					// while ((len = reader.read(messageC)) != -1) {
//					// receiveEsbServiceRs += new String(messageC, 0, len);
//					// }
//					// /*-------新方法循环读取报文，不取报文中长度------*/
//					//
//					// } else if (null != in) {
//					//
//					// /*-------新方法循环读取报文，不取报文中长度-------*/
//					// byte[] messageC = new byte[512];
//					// int len = 0;
//					// while ((len = in.read(messageC)) != -1) {
//					// message = new String(messageC,
//					// EsbSocketConstant.CHARCODE_UTF8).toCharArray();
//					// receiveEsbServiceRs += new String(message, 0, len);
//					// }
//					// /*-------新方法循环读取报文，不取报文中长度------*/
//					//
//					// /*-------老方法，读长度为messageLength的报文-------*
//					// byte[] byteMessage = new byte[messageLength];
//					// message_ = in.read(byteMessage, 0, messageLength);
//					// message = new
//					// String(byteMessage,EsbSocketConstant.CHARCODE_UTF8).toCharArray();
//					// receiveEsbServiceRs = new
//					// String(message);//将字符数组报文转换成字符串。
//					// /*-------老方法，读长度为messageLength的报文-------*/
//					//
//					// }
//
//					/* 提取公共部分之后，调用读取报文的公共方法 */
//					String receiveEsbServiceRs = LinkSocketStartupUtil.getMessage(reader, in, messageLen);
//					// String receiveEsbServiceRs = new
//					// String(message);//老方法，将字符数组报文转换成字符串。
//					log.info("客户端收到响应报文：" + receiveEsbServiceRs);
//					EsbServiceRs esbServiceRs = EsbSocketUtil.getEsbServiceRs(serviceCodeScene, receiveEsbServiceRs);
//					log.info("处理后的响应报文：" + new String(messageLen) + JAXBUtil.marshalToStr(esbServiceRs, false));
//					saveMessageRecord(receiveEsbServiceRs, serviceCodeScene, "response");
//
//					String returnCode = esbServiceRs.getEsbAppHead().getReturnCode();
//					String returnMsg = esbServiceRs.getEsbAppHead().getReturnMsg();
//
//					if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)) {
//						// 转换成对应的BODY
//						Object bean = esbServiceRs.getEsbBody();
//						// 把Bean转换成Map
//						if (bean != null) {
//							retDataObject = BeanToMapUtil.convertBean(bean);
//						}
//
//						EsbSocketUtil.setCcyTpByCodeScene("XY", serviceCodeScene, retDataObject);
//
//						retDataObject.set(EsbSocketConstant.RETURN_CODE, returnCode);
//						retDataObject.set(EsbSocketConstant.RETURN_MSG, returnMsg);
//
//						log.info("服务访问成功！");
//					} else {
//						// 把Bean转换成Map
//						retDataObject = BeanToMapUtil.convertBean(esbServiceRs);
//
//						retDataObject.set(EsbSocketConstant.RETURN_CODE, returnCode);
//						retDataObject.set(EsbSocketConstant.RETURN_MSG, returnMsg);
//
//						log.info("服务访问失败：" + returnMsg);
//					}
//				}
//			}
		} catch (Exception ie) {
			log.error(ie.getMessage());
			retDataObject.setString(EsbSocketConstant.RETURN_CODE, EsbSocketConstant.XD_SOCKET_FAILE);
			retDataObject.setString(EsbSocketConstant.RETURN_MSG, ie.getMessage());
		} catch (Throwable e) {
			log.error(e.getMessage());
			retDataObject.setString(EsbSocketConstant.RETURN_CODE, EsbSocketConstant.XD_SOCKET_FAILE);
			retDataObject.setString(EsbSocketConstant.RETURN_MSG, e.getMessage());
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return retDataObject;
	}

	/**
	 * chuaN 将报文保存在记录表中
	 * 
	 * @param message
	 * @param serviceCodeScene
	 * @throws Throwable
	 */
	public void saveMessageRecord(String message, String serviceCodeScene, String messageType) throws Throwable {
		DataObject record = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubInterfaceRecord");
		record.set("messageType", messageType);
		record.set("serviceScene", serviceCodeScene);// 服务场景
		record.set("message", message);// 报文
		if ("response".equals(messageType)) {
			EsbServiceRs esbServiceRs = EsbSocketUtil.getEsbServiceRs(serviceCodeScene, message);
			record.set("returnCode", esbServiceRs.getEsbAppHead().getReturnCode());
			record.set("returnMsg", esbServiceRs.getEsbAppHead().getReturnMsg());
		}
		record.set("createTime", GitUtil.getBusiTimestamp());

		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pub.socket.util.checkData");
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = record;
		logicComponent.invoke("addInterfaceRecord", params);

	}

	/**
	 * chuaN 校验请求报文中的数据长度，是否必输，字典，角色
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */

	@Bizlet("校验报文的合法性")
	public String check(DataObject obj) throws Exception {
		String code = obj.toString();
		if (code.contains("$") || code.contains("#") || code.contains("%") || code.contains("|") || code.contains("||")
				|| code.contains("'")) {
			return "报文包含不合法的字符！";
		}
		// 根据服务场景查询所有要检验的字段
		String serviceCodeScene = obj.getString(EsbSocketConstant.SERVICE_CODE_SCENE);
		DataObject interfaceCol = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubInterfaceCols");
		interfaceCol.set("serviceScene", serviceCodeScene);
		DataObject[] interfaceCols = DatabaseUtil.queryEntitiesByTemplate("default", interfaceCol);

		// 校验角色是否符合要求
		if (interfaceCols.length > 0) {
			String roles = interfaceCols[0].getString("roles");// 角色
			String userNum = obj.getString("cstMgrNo");// 客户经理
			String orgNum = obj.getString("cstMgrInstNo");// 客户经理机构号
			String ittbrId = obj.getString("ittbrId");
			orgNum = null == orgNum ? ittbrId : orgNum;
			if (null != userNum && !"".equals(userNum) && null != orgNum && !"".equals(orgNum)) {
				// 校验角色是否符合权限
				if (null != roles && !"".equals(roles)) {
					HashMap<String, Object> userMap = new HashMap<String, Object>();
					userMap.put("userNum", userNum);
					userMap.put("orgNum", orgNum);
					Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.findUserRoles", userMap);
					if (null == result || result.length == 0) {
						return "用户" + userNum + "，在机构" + orgNum + "下没有角色";
					}
					boolean flag = false;
					for (int k = 0; k < result.length; k++) {
						DataObject resultDataObject = (DataObject) result[k];
						if (roles.contains(resultDataObject.getString("ROLEID"))) {
							flag = true;
							break;
						} else {
							flag = false;
						}
					}
					if (flag == false) {
						return "用户" + userNum + "，在机构" + orgNum + "下角色不符合要求";
					}

				}
			}
		}

		// 校验报文体数据
		for (int i = 0; i < interfaceCols.length; i++) {

			String fieldCode = interfaceCols[i].getString("fieldCode");// 字段物理名
			String fieldName = interfaceCols[i].getString("fieldName");// 字段逻辑名
			String dataType = interfaceCols[i].getString("dataType");// 数据类型
			int dataLength = interfaceCols[i].getInt("dataLength");// 字段长度
			String required = interfaceCols[i].getString("required");// 是否必输
			String dictTypeId = interfaceCols[i].getString("dictTypeId");// 字典代码
			String array = interfaceCols[i].getString("array");// 所在数组

			String checkMsg = null;
			if ("".equals(array) || null == array) {// 如果不是数组中的元素
				checkMsg = check(obj.getString(fieldCode), fieldCode, fieldName, dataLength, required, dictTypeId);
				if (null != checkMsg) {
					return checkMsg;
				}
			} else {// 如果是数组或者结构体中的元素
				if (array.contains("Arrays")) {// 数组
					if (null != obj.getList(array)) {
						List list = obj.getList(array);
						for (int j = 0; j < list.size(); j++) {
							Object rel = (Object) list.get(j);
							Map<String, String> map = getFieldValue(rel);// 将数组对象转换成Map
							String data = map.get(fieldCode);
							checkMsg = check(data, fieldCode, fieldName, dataLength, required, dictTypeId);
						}
					}
				} else if (array.contains("Struct")) {// 结构体
					if (obj.get(array) != null) {
						Object rel = obj.get(array);
						Map<String, String> map = getFieldValue(rel);// 将数组对象转换成Map
						String data = map.get(fieldCode);
						checkMsg = check(data, fieldCode, fieldName, dataLength, required, dictTypeId);
					}
				}
				if (null != checkMsg) {
					return checkMsg;
				}
				if ("Clob".equals(dataType)) {// 大字段
					String arrayString = obj.getString(array);//获取能够表明大字段具体是哪一类
					if (EsbSocketConstant.MtmqRq02002000004BODY02.equals(serviceCodeScene)) {//押品新增
						if (arrayString.startsWith("19") || arrayString.startsWith("17")) {//押品类型传过来是6位，需要截取来判断到底是哪类押品
							arrayString = arrayString.substring(0, 4);
						} else {
							arrayString = obj.getString(array).substring(0, 2);
						}
					}
					// 解析大字段
					DataObject map = getMapByClob(fieldCode, obj.getString(fieldCode), serviceCodeScene, arrayString);
					if (null != map.getString("ReturnMsg") && !"".equals(map.getString("ReturnMsg"))) {
						return map.getString("ReturnMsg");
					}
					// 查找在大字段中的数据,并且循环校验（根据服务码和押品类型来查找大字段中应该要校验的字段）
					interfaceCol.set("serviceScene", serviceCodeScene);
					interfaceCol.set("array", arrayString);
					interfaceCols = DatabaseUtil.queryEntitiesByTemplate("default", interfaceCol);

					for (int m = 0; m < interfaceCols.length; m++) {
						fieldCode = interfaceCols[m].getString("fieldCode");// 字段物理名
						fieldName = interfaceCols[m].getString("fieldName");// 字段逻辑名
						dataType = interfaceCols[m].getString("dataType");// 数据类型
						dataLength = interfaceCols[m].getInt("dataLength");// 字段长度
						required = interfaceCols[m].getString("required");// 是否必输
						dictTypeId = interfaceCols[m].getString("dictTypeId");// 字典代码
						String data = map.getString(fieldCode);
						checkMsg = check(data, fieldCode, fieldName, dataLength, required, dictTypeId);
						if (null != checkMsg) {
							return checkMsg;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 根据报文数据，校验是否合法
	 * 
	 * @param data
	 * @param fieldCode
	 * @param fieldName
	 * @param dataLength
	 * @param required
	 * @param dictTypeId
	 * @return
	 * @throws Exception
	 */
	public String check(String data, String fieldCode, String fieldName, int dataLength, String required, String dictTypeId)
			throws Exception {

		// 校验是否必输
		if ((null == data || "".equals(data)) && "Y".equals(required)) {
			return "报文字段中" + fieldCode + "(" + fieldName + ")" + "应该为必输";
		}
		// 校验字段长度
		if (null != data && data.length() > dataLength) {
			return "报文字段中" + fieldCode + "(" + fieldName + ")最大长度为" + dataLength + ",实际长度为" + data.length();
		}
		// 校验字段是否符合码值
		if (null != dictTypeId && !"".equals(dictTypeId) && null != data && !"".equals(data)) {
			DataObject dict = DataObjectUtil.createDataObject("com.bos.dataset.pub.EosDictEntry");
			dict.set("dicttypeid", dictTypeId);
			dict.set("dictid", data);
			DataObject[] dicts = DatabaseUtil.queryEntitiesByTemplate("default", dict);
			if (dicts.length == 0) {
				return "报文字段中" + fieldCode + "(" + fieldName + "):" + data + ",不符合码值" + dictTypeId + "规范";
			}
		}
		return null;
	}

	/**
	 * 根据报文、服务场景和大字段类型解析
	 * 
	 * @param fieldCode
	 *            大字段名称
	 * @param messageString
	 *            报文
	 * @param serviceCodeScene
	 *            服务场景
	 * @param type
	 *            类型
	 * @return
	 */
	public static DataObject getMapByClob(String fieldCode, String messageString, String serviceCodeScene, String type) {
		DataObject retDataObject = DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");
		try {
			//将大字段构前后加上对应的XML标志
			messageString = "<" + fieldCode.substring(0, 1).toUpperCase() + fieldCode.substring(1, fieldCode.length()) + ">"
					+ messageString + "</" + fieldCode.substring(0, 1).toUpperCase() + fieldCode.substring(1, fieldCode.length())
					+ ">";
			Object object = (Object) JAXBUtil.unmarshal(messageString, getMap(serviceCodeScene + "T" + type));
			retDataObject = BeanToMapUtil.convertBean(object);
		} catch (Exception e) {
			log.error(e.getMessage());
			retDataObject.set("ReturnMsg", "报文中大字段不符合XML规范，或与类型不匹配");
		}
		return retDataObject;
	}

	public static Class<?> getMap(String key) {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put("RQ02002000004BODY01T04", EsbBodyMtmqRqEsttInf.class);
		map.put("RQ02002000004BODY01T05", EsbBodyMtmqRqLandBnkgInf.class);

		map.put("RQ02002000004BODY02T01", EsbBodyWmaRqEsttInfArray.class);
		map.put("RQ02002000004BODY02T02", EsbBodyWmaRqConsInPrgsInfArray.class);
		map.put("RQ02002000004BODY02T03", EsbBodyWmaRqLandUseRghtInfArray.class);
		map.put("RQ02002000004BODY02T04", EsbBodyWmaRqVhclInfArray.class);
		map.put("RQ02002000004BODY02T05", EsbBodyWmaRqMchnInfArray.class);
		map.put("RQ02002000004BODY02T06", EsbBodyWmaRqInvtInfArray.class);
		map.put("RQ02002000004BODY02T07", EsbBodyWmaRqOthrMrtgAstInfArray.class);
		map.put("RQ02002000004BODY02T08", EsbBodyWmaRqDepRecptInfArray.class);
		map.put("RQ02002000004BODY02T09", EsbBodyWmaRqBondInfArray.class);
		map.put("RQ02002000004BODY02T10", EsbBodyWmaRqStkRghtInfArray.class);
		map.put("RQ02002000004BODY02T11", EsbBodyWmaRqStkInfArray.class);
		map.put("RQ02002000004BODY02T12", EsbBodyWmaRqFndInfArray.class);
		map.put("RQ02002000004BODY02T13", EsbBodyWmaRqFncPdInfArray.class);
		map.put("RQ02002000004BODY02T14", EsbBodyWmaRqBillInfArray.class);
		map.put("RQ02002000004BODY02T15", EsbBodyWmaRqRcvbInfArray.class);
		map.put("RQ02002000004BODY02T16", EsbBodyWmaRqExprtRbtInfArray.class);
		map.put("RQ02002000004BODY02T1701", EsbBodyWmaRqCrgWrntInfArray.class);
		map.put("RQ02002000004BODY02T1703", EsbBodyWmaRqCrgLadBillInfArray.class);
		map.put("RQ02002000004BODY02T18", EsbBodyWmaRqIntPtyInfArray.class);
		map.put("RQ02002000004BODY02T1901", EsbBodyWmaRqLandCtrMgtRghtInfArray.class);
		map.put("RQ02002000004BODY02T1902", EsbBodyWmaRqFrchsRghtInfArray.class);
		map.put("RQ02002000004BODY02T1903", EsbBodyWmaRqOthrPftRghtInfArray.class);
		map.put("RQ02002000004BODY02T1904", EsbBodyWmaRqFrstRghtInfArray.class);
		map.put("RQ02002000004BODY02T21", EsbBodyWmaRqRoadBrdgChrgRghtInfArray.class);
		map.put("RQ02002000004BODY02T22", EsbBodyWmaRqOthrPlgAstInfArray.class);

		Class<?> reClass = (Class<?>) map.get(key);
		return reClass;
	}

	public Map<java.lang.String, java.lang.String> getFieldValue(Object obj) throws Exception {

		Map<String, String> mapValue = new HashMap<String, String>();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (name != "serialVersionUID") {
				String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
				Method methodGet = cls.getDeclaredMethod(strGet);
				Object object = methodGet.invoke(obj);
				String value = object != null ? object.toString() : "";
				mapValue.put(name, value);
			}
		}
		return mapValue;
	}
}
