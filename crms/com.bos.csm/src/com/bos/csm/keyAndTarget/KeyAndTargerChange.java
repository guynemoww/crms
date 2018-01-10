package com.bos.csm.keyAndTarget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

public class KeyAndTargerChange implements IBIZProcess {

	 /**
	 * 
	* @Title: executeAfterAbort
	* @Description: TODO(用于撤销流程后业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		//撤销流程，status=05
		/*
		01--未审核
		02--审核中
		03--已审核
		04--未通过
		05--被撤销
		*/
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmTargetList");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb = GitUtil.queryEntityByTemplate(dataOb); 
				dataOb.set("status","05");
				DatabaseUtil.updateEntity("default", dataOb);
				
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
		// 流程否决时， status=被否决
		/*
		01--未审核
		02--审核中
		03--已审核
		04--未通过
		05--被撤销
		*/
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmTargetList");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb = GitUtil.queryEntityByTemplate(dataOb); 
				dataOb.set("status","04");
				DatabaseUtil.updateEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "04"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
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
		// 流程进行时，status=审核中
		/*
		01--未审核
		02--审核中
		03--已审核
		04--未通过
		05--被撤销
		*/
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmTargetList");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb = GitUtil.queryEntityByTemplate(dataOb); 
				dataOb.set("status","02");
				DatabaseUtil.updateEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "02"); 
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
		// 流程结束时，status=已审核
		/*
		01--未审核
		02--审核中
		03--已审核
		04--未通过
		05--被撤销
		*/
		String[] xpath={"bizId","bizStatus","bizType"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmTargetList");
		String bizType;
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				bizType = list.get(2).toString();
				//bizType=1 追加   bizType=2 退出
				if("2".equals(bizType)){
					try {
						removeOut(list.get(0).toString());
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}else{
					dataOb.set("partyId", list.get(0).toString());
					dataOb = GitUtil.queryEntityByTemplate(dataOb); 
					dataOb.set("status","03");
					DatabaseUtil.updateEntity("default", dataOb);
					//将修改的状态传回流程中
					relaDatas.put("status", "03"); 
					RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	private void removeOut(String str) {
		String msg = "";
		//	逻辑构件名称
		String componentName = "com.bos.csm.keyAndTarget.keyAndTarget";
		// 逻辑流名称
		String operationName ="delKeyAndTarget";
		
		ILogicComponent logicComponent = LogicComponentFactory
		.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = str;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			msg = (String) result[0];
			if(("").equals(msg)||msg == null){
				
			}else{
				throw new EOSException("移出全行重点及目标客户失败!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
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
