package com.bos.mq.rq;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import javax.xml.bind.JAXBException;
import com.bos.jaxb.JAXBUtil;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.jaxb.javabean.CommonRqHdr;
import com.bos.jaxb.javabean.SuperBosfxRq;
import com.bos.mq.client.BaseMQ;
import com.bos.mq.client.MQbean;
import com.bos.mq.client.Receiver;
import com.bos.mq.client.Sender;
import com.bos.mq.server.CrmsMsg;
import com.bos.mq.util.KeyGenerator;
import com.eos.system.annotation.Bizlet;
import com.eos.system.log.LogFactory;
import com.eos.system.logging.Logger;
import com.git.easyetl.threadpool.common.TaskBean;
import com.ibm.mq.MQException;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;
import commonj.sdo.DataObject;

public class BaseMQRequest<E> extends BaseMQ {

	Logger log = LogFactory.getLogger(BaseMQRequest.class);

	/**
	 * 发送请求并接收相应
	 * 
	 * @param bosfxii
	 * @return
	 * @throws JAXBException
	 * @throws MQException
	 * @throws UnsupportedEncodingException
	 */
	@Bizlet("发送请求并接收相应")
	public BOSFXII mqRequest(DataObject tradInfo, BOSFXII bosfxii) throws JAXBException, MQException, UnsupportedEncodingException {
		byte[] data = JAXBUtil.marshal(bosfxii);
		System.out.println("data:" + new String(data));
		log.info("交易 " + tradInfo.getString("spname"));
		log.info("交易 id = " + tradInfo.getString("rquid"));
		MQbean mqbean = getMQParameter(data);
		mqbean.setSpName(tradInfo.getString("spname"));
		mqbean.setRquid(tradInfo.getString("rquid"));
		mqbean.setClearDate(tradInfo.getString("cleardate"));
		mqbean.setTradDate(tradInfo.getString("traddate"));
		mqbean.setTradTime(tradInfo.getString("tradtime"));
		mqbean.setMsgKey("[客户端]-[msgkey："+KeyGenerator.getUUID());
		Sender sender = new Sender();
		mqbean = sender.clientSend(mqbean);
		BOSFXII rebf = new BOSFXII();
		Receiver receiver = new Receiver();
		byte[] redata = ((MQbean) receiver.receive(mqbean)).getContext();
		rebf = (BOSFXII) JAXBUtil.unmarshal(redata, BOSFXII.class);
		return rebf;
	}

	@Bizlet("根据系统类型为BOSFXII赋值")
	public BOSFXII initBOSFXII(Object bizBean, CommonRqHdr rqhr, String spName) {
		BOSFXII rebf = new BOSFXII();
		if ("T24".equals(spName)) {
			rebf.t24BosfxRq = (SuperBosfxRq) bizBean;
			rebf.t24BosfxRq.CommonRqHdr = rqhr;
		} else if ("ECIF".equals(spName)) {
			rebf.ecifBosfxRq = (SuperBosfxRq)bizBean;
			rebf.ecifBosfxRq.CommonRqHdr = rqhr;
		} else if ("CCMS".equals(spName)) {
			rebf.ccmsBosfxRq = (SuperBosfxRq)bizBean;
			rebf.ccmsBosfxRq.CommonRqHdr = rqhr;
		} else if ("SCF".equals(spName)) {
			rebf.scfBosfxRq = (SuperBosfxRq)bizBean;
			rebf.scfBosfxRq.CommonRqHdr = rqhr;
		}else if ("BMS".equals(spName)) {
			rebf.bmsBosfxRq = (SuperBosfxRq)bizBean;
			rebf.bmsBosfxRq.CommonRqHdr = rqhr;
		}else if ("CAMS".equals(spName)) {
			rebf.camsBosfxRq = (SuperBosfxRq)bizBean;
			rebf.camsBosfxRq.CommonRqHdr = rqhr;
		}else if ("CRM".equals(spName)) {
			rebf.crmBosfxRq = (SuperBosfxRq)bizBean;
			rebf.crmBosfxRq.CommonRqHdr = rqhr;
		}
		return rebf;
	}

	@Bizlet("MQ相应请求")
	public DataObject mqRespone(BOSFXII rebf, String spName) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		DataObject sdo = new DataObjectImpl(new DataObjectType());
		Object bosfxii = getBosfxii(rebf);
		if ("T24".equals(spName)) {
			if (rebf.t24BosfxRs != null) {
				sdo.set("reCode", rebf.t24BosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.t24BosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.t24BosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
		} else if ("ECIF".equals(spName)) {
			if (rebf.ecifBosfxRs != null) {
				sdo.set("reCode", rebf.ecifBosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.ecifBosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.ecifBosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
		} else if ("CCMS".equals(spName)) {
			if (rebf.ccmsBosfxRs != null) {
				sdo.set("reCode", rebf.ccmsBosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.ccmsBosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.ccmsBosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
		} else if ("SCF".equals(spName)) {
			if (rebf.scfBosfxRs != null) {
				sdo.set("reCode", rebf.scfBosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.scfBosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.scfBosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
			
		}else if ("BMS".equals(spName)) {
			if (rebf.bmsBosfxRs != null) {
				sdo.set("reCode", rebf.bmsBosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.bmsBosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.bmsBosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
			
		} else if ("CRM".equals(spName)) {
			if (rebf.crmBosfxRs != null) {
				sdo.set("reCode", rebf.crmBosfxRs.commonRsHdr.StatusCode);
				sdo.set("reMsg", rebf.crmBosfxRs.commonRsHdr.ServerStatusCode);
				sdo.set("reObject", rebf.crmBosfxRs);
			} else {
				sdo.set("reCode", CrmsMsg._EXCEPTION.value());
				sdo.set("reMsg", CrmsMsg._EXCEPTION_MSG.value());
			}
			
		}else {
		}
		return sdo;
	}

	/**
	 * 服务端相应请求
	 * 
	 * @param bosfxii
	 * @throws JAXBException
	 * @throws MQException
	 */
	public void mqSend(BOSFXII bosfxii, TaskBean taskBean) throws JAXBException, MQException {
		log.info("CRMS send taskBean.getUuid() = " + taskBean.getUuid());
		byte[] data = JAXBUtil.marshal(bosfxii);
		// System.out.println("data:"+new String(data));
		MQbean mqbean = getMQParameter(data);
		mqbean.setRelationid(taskBean.getUuid());
		mqbean.setRquid(taskBean.getTaskId());
		mqbean.setSpName(taskBean.getTaskType());
		mqbean.setMsgKey("[服务端]-[msgkey："+KeyGenerator.getUUID());
		Sender sender = new Sender();
		mqbean = sender.serverSend(mqbean);
	}
	/**
	 * 服务端相应请求(解决实时交易出现异常情况再次发起请求)
	 * 
	 * @param bosfxii
	 * @throws JAXBException
	 * @throws MQException
	 */
	public void mqSends(BOSFXII bosfxii, TaskBean taskBean,String bizNumber) throws JAXBException, MQException {
		byte[] data = JAXBUtil.marshal(bosfxii);
		// System.out.println("data:"+new String(data));
		MQbean mqbean = getMQParameter(data);
		mqbean.setRelationid(taskBean.getUuid());
		mqbean.setRquid(taskBean.getTaskId());
		mqbean.setSpName(taskBean.getTaskType());
		mqbean.setBizNumber(bizNumber);
		Sender sender = new Sender();
		mqbean = sender.serverSend(mqbean);
	}
	/**
	 * 服务端发送历史报文
	 * 
	 * @param data
	 * @param taskBean
	 * @throws JAXBException
	 * @throws MQException
	 * @throws UnsupportedEncodingException
	 */
	public void mqSendHis(byte[] data, TaskBean taskBean) throws JAXBException, MQException, UnsupportedEncodingException {
		MQbean mqbean = getMQParameter(data);
		mqbean.setRelationid(taskBean.getUuid());
		mqbean.setRquid(taskBean.getTaskId());
		mqbean.setSpName(taskBean.getTaskType());
		mqbean.setIsHisMsg(true);
		Sender sender = new Sender();
		mqbean = sender.serverSend(mqbean);
		System.out.println("发送历史报文:"+new String(data,"UTF-8"));
		log.info("发送历史报文:"+new String(data,"UTF-8"));
	}

	/**
	 * 根据报文对象获取对应系统的响应报文报文对象
	 * 
	 * @param bosfxii
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private Object getBosfxii(BOSFXII bosfxii) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException{
		Field[] fields = bosfxii.getClass().getDeclaredFields();
		for(Field field:fields){   
			if(!"serialVersionUID".equals(field.getName())){
				field.setAccessible(true); // 设置些属性是可以访问的
				Object o = field.get(bosfxii);
				if(o != null){
					return o;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
