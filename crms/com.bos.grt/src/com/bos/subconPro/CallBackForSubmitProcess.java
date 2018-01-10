package com.bos.subconPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.BeanToMapUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.DateHelper;
import com.git.easyrule.util.EngineConstants;
import com.ibm.db2.jcc.c.r;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	// 流程提交的方法
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String bizId = (String) list.get(0);
			if (null == bizId || "".equals(bizId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			DataObject subcontractT = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractT");
			subcontractT.set("id", bizId);
			subcontractT.set("status", "02");
			DatabaseUtil.updateEntity("default", subcontractT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	// 流程提交结束
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		String errorMsg=null;
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String bizId = (String) list.get(0);
			if (null == bizId || "".equals(bizId)) {
				logger.info("流程返回的申请ID为空！");
				errorMsg="流程返回的申请ID为空";
				throw new EOSException("流程返回的申请ID为空");
			}
			Object[] result = null;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.grt.subContractManage.subContract");
			// 逻辑流的输入参数
			Object[] params = new Object[1];
			params[0] = bizId;
			result = logicComponent.invoke("updateSubContractTOK", params);
			if (result != null && result.length > 0) {
				if (null != result[0] && !"".equals(result[0].toString())) {
					errorMsg=result[0].toString();
					throw new EOSException(result[0].toString());
				}
			}
		} catch (Exception e) {
			throw new EOSException(errorMsg);
		} catch (Throwable e) {
			throw new EOSException(errorMsg);
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {

	}

	// 流程被拒绝
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String bizId = (String) list.get(0);
			if (null == bizId || "".equals(bizId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			DataObject subcontractT = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractT");
			subcontractT.set("id", bizId);
			subcontractT.set("status", "06");
			DatabaseUtil.updateEntity("default", subcontractT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}

	}

	// 撤销流程
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String bizId = (String) list.get(0);
			if (null == bizId || "".equals(bizId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			// 删除担保合同备份表数据
			DataObject subcontractT = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractT");
			subcontractT.set("id", bizId);
			DatabaseUtil.deleteEntity("default", subcontractT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list;
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);

			String bizId = (String) list.get(0);
			if (null == bizId || "".equals(bizId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			// 如果是退回，不用校验
			if (null != workitem.get("conclusion")) {
				String conclusion = (String) workitem.get("conclusion");// 结论
				if (("99").equals(conclusion)) {
					resultMap.put("errorNum", "1");
					resultMap.put("errorCode", "");
					resultMap.put("errorContent", "");
					return resultMap;
				}
			}
			DataObject subcontractT = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractT");
			subcontractT.set("id", bizId);
			DatabaseUtil.expandEntity("default", subcontractT);

			RuleService rs = new RuleService();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("id", bizId);

			// 判断抵押品为担保合同担保是否足值
			List<MessageObj> msgList = rs.runRule("SUBCON_0003", paramMap);
			String msg = convertMsg(msgList);
			if (!"true".equals(msg)) {
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 判断担保合同为主合同担保是否足值
			msgList = rs.runRule("SUBCON_0005", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			// 判断担保合同止期不能小于所关联的任何主合同的起始日期
			msgList = rs.runRule("SUBCON_0006", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			resultMap.put("errorNum", "1");
			resultMap.put("errorCode", "");
			resultMap.put("errorContent", "");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorNum", "2");
			resultMap.put("errorCode", "2");
			resultMap.put("errorContent", "执行规则校验出错！");
		}

		return resultMap;
	}

	private String convertMsg(List<MessageObj> msgList) {
		StringBuffer sf = new StringBuffer();
		if (msgList != null && !msgList.isEmpty()) {
			for (int i = 0; i < msgList.size(); i++) {
				MessageObj t = msgList.get(i);
				if (EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())) {
					sf.append("[(" + (i + 1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
				}
			}
		}
		if (sf.length() > 0) {
			return sf.toString();
		}
		return "true";
	}
}
