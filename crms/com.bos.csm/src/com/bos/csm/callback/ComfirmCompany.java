package com.bos.csm.callback;

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
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class ComfirmCompany implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.company.company";
			// 逻辑流名称
			String operationName = "UpdateCompanyIng";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "02";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.company.company";
			// 逻辑流名称
			String operationName = "updateCompanyOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "03";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.company.company";
			// 逻辑流名称
			String operationName = "UpdateCompanyIng";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "01";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			String processId = processInstId; // 流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.company.company";
			// 逻辑流名称
			String operationName = "updateCompanyOK";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[3];
			params[0] = applyId;
			params[1] = processId;
			params[2] = "01";
			logicComponent.invoke(operationName, params);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	/**
	 * 撤销集团客户
	 */
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String groupPartyId = list.get(0).toString();
			// 查询是否有认定成员
			DataObject mb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
			mb.set("groupPartyId", groupPartyId);
			DataObject[] mbs = DatabaseUtil.queryEntitiesByTemplate(GitUtil.DEFAULT_DS_NAME, mb);
			// 存在已认定的成员
			boolean del_flag = true;// 删除集团、参与者标志
			if (null != mbs && mbs.length > 0) {
				for (int i = 0; i < mbs.length; i++) {
					DataObject temp = mbs[i];
					String memberStatus = temp.getString("memberStatusCd");// 成员状态
					String operType = temp.getString("operType");// 变更方式
					if ("03".equals(memberStatus)) {// 成员如果已认定，说明是重新认定
						del_flag = false;// 有认定成员，不能删除集团，要还原集团状态为已认定
						if ("02".equals(operType)) {// 成员状态为删除
							temp.setString("operType", "01");
							DatabaseUtil.updateEntity(GitUtil.DEFAULT_DS_NAME, temp);
						}
					} else {// 对于未认定或认定中的，统统删除
						DatabaseUtil.deleteEntity(GitUtil.DEFAULT_DS_NAME, temp);
					}
				}
			}

			// 集团、参与者处理
			DataObject gb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupCompany");
			gb.setString("partyId", groupPartyId);
			DataObject cp = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			cp.setString("partyId", groupPartyId);
			DataObject gm = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
			gm.setString("partyId", groupPartyId);
			if (del_flag) {
				// 删除集团，删除参与者
				DatabaseUtil.deleteEntity(GitUtil.DEFAULT_DS_NAME, gb);
				DatabaseUtil.deleteByTemplate(GitUtil.DEFAULT_DS_NAME, gm);
				DatabaseUtil.deleteEntity(GitUtil.DEFAULT_DS_NAME, cp);
			} else {//重新认定的情况
				// 还原集团状态为已认定
				gb.set("status", "03");
				DatabaseUtil.updateEntity(GitUtil.DEFAULT_DS_NAME, gb);
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String errorNum = "";
		String errorContent = "";
		String errorCode = "1";
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// 获取流程中的业务数据
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取申请记录id
			String applyId = list.get(0).toString(); // 业务ID
			// String processId = processInstId; //流程实例ID
			// 逻辑构件名称
			String componentName = "com.bos.csm.company.company";
			// 逻辑流名称
			String operationName = "getCompanyInfo";
			ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
			// 逻辑流的输入参数
			Object[] params = new Object[2];
			DataObject dataObject = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			dataObject.setString("partyId", applyId);
			params[0] = dataObject;
			params[1] = processInstId;
			Object os[] = logicComponent.invoke(operationName, params);
			String memberNum = os[2].toString();
			int num = Integer.parseInt(memberNum);
			if (num >= 2) {
				errorNum = "1";
			} else {
				errorNum = "2";
				errorContent = "统一授信客户的成员不得少于2个";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorNum = "2";
			errorContent = "数据异常,联系管理员.";
		} catch (Throwable e) {
			e.printStackTrace();
			errorNum = "2";
			errorContent = "数据异常,联系管理员.";
		}
		resultMap.put("errorNum", errorNum);
		resultMap.put("errorContent", errorContent);
		resultMap.put("errorCode", errorCode);
		return resultMap;
	}

}
