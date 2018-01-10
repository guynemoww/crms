package com.bos.csm.custFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.csm.pub.CsmUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

public class CustMaintainImpl implements IBIZProcess {
	public static TraceLogger logger = new TraceLogger(CustMaintainImpl.class);
	 /**
	 * 
	* @Title: executeAfterAbort
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		//撤销流程，examineState=null
		//00--撤销状态，或者新建数据成功后的状态  01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId",list.get(0).toString());
				dataOb.set("examineState","00");
				DatabaseUtil.updateEntity("default", dataOb);
				//流程撤销，返回客户信息的原数据
				ExcuteBeforeRefuseProcess.excuteBeforeRefuseProcess(list.get(0).toString(), processInstId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		

	}

	/**
	 * 
	* @Title: executeAfterCreateFlow
	* @Description: TODO(用于创建流程成功后更新业务表数据)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// 创建流程 examineState=审核中

	}

	/**
	 * 
	* @Title: executeBeforeIntegration
	* @Description: TODO(用于与外围系统交互前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	/**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)---流程中途被否决结束
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// 流程否决时， examineState=被否决
		//01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb.set("examineState","03");
				DatabaseUtil.updateEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "03"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);

				//
				ExcuteBeforeRefuseProcess.excuteBeforeRefuseProcess(list.get(0).toString(), processInstId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		

	}

	/**
	 * 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)--流程下一节点同意，往下一节点走的情况
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// 流程进行时，examineState=审核中
//		01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb.set("examineState","01");
				DatabaseUtil.updateEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "01"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);

		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	/**
	 * 
	* @Title: executeBeforeTerminate
	* @Description: TODO(用于普通流程结束前业务逻辑)---流程正常结束的情况
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// 流程结束时，examineState=已审核
//		01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");

		logger.info("重要信息修改-同意-开始执行业务逻辑");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb.set("examineState","02");
				DatabaseUtil.updateEntity("default", dataOb);
				
				//获得当前客户基本信息
				String partyTypeCd=null;
				DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				party.set("partyId",list.get(0).toString());
				DatabaseUtil.expandEntity("default", party);
				partyTypeCd = (String)party.get("partyTypeCd");
				
				
				//修改字段完成后,查询证件信息,去同步最新的客户信息
				DataObject cert = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCertificateInfo");
				cert.set("partyId",list.get(0).toString());
				cert.set("certificateTypeCd","20001");
				DatabaseUtil.expandEntityByTemplate("default", cert, cert);
				
				//同步 逻辑流的输入参数
				Object[] params = new Object[4];
				params[0] = cert.get("certificateTypeCd");
				params[1] = cert.get("certificateCode");
				params[2] = null;
				params[3] = null;
				
				//
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.csm.inteface.ecif");
				Object[] rs = logicComponent.invoke("queryCustByCert", params);
				
				logger.info("调用逻辑流,同步ECIF");
				
				DataObject reBean = (DataObject)rs[0];
				logger.info("ECIF返回CODE："+(String)reBean.get("reCode"));
				if((String)reBean.get("reCode")=="000000"){
					//将最新的数据装入
					Object[] params2 = new Object[2];
					params2[0] = reBean.get("reObject");
					params2[1] = partyTypeCd;
					
					ILogicComponent logicComponent2 = LogicComponentFactory.create("com.bos.csm.inteface.ecif");
					Object[] rs2 = logicComponent2.invoke("afterFromEcif", params2);
				}
				
				
				//
				CsmUtil.setValueForBps(list.get(0).toString(), processInstId,logger); 
				logger.info("修改此次流程更新的字段,修改完毕");
				
				//查询最新的客户信息
				party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				party.set("partyId", list.get(0).toString());
				DatabaseUtil.expandEntity("default", party);
				DataObject corp = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				corp.set("partyId", list.get(0).toString());
				DatabaseUtil.expandEntityByTemplate("default", corp, corp);
				logger.info("查询最新的客户信息客户,ECIF编号："+party.getString("partyNum"));
				
				//发送与ECIF的维护交易
				Object[] params3 = new Object[3];
				params3[0] = party;
				params3[1] = corp;
				params3[2] = null;
				ILogicComponent logicComponent3 = LogicComponentFactory.create("com.bos.csm.inteface.ecif");
				logicComponent3.invoke("updateCustSendToEcif", params3);
				logger.info("发送与ECIF的维护交易,完毕");
				
				
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "02"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new EOSException(e);
		}

	}

	/**
	 * 
	* @Title: executeBeforeUntread
	* @Description: TODO(用于审批退回前业务逻辑)--流程退回的情况
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		return null;
	}

}
