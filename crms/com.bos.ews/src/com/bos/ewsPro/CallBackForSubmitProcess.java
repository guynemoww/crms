package com.bos.ewsPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;
import com.sunyard.TransEngine.util.DataBaseUtil;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------->开始!");

			// 更新客户预警信号状态：1发起；2生效；3关闭；4新增审核；5关闭审核
			// DataObject open =
			// DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewCsmSignalList");
			// open.set("customerWarningUpdateid", applyId);
			// DatabaseUtil.expandEntityByTemplate("default", open, open);
			//
			// open.set("signalStatusCd","2");
			// DatabaseUtil.updateEntity("default", open);

			// 更新客户预警信号状态：1发起；2生效；3关闭；4新增审核；5关闭审核
			// DataObject close =
			// DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewCsmSignalList");
			// close.set("customerWarningClose", applyId);
			// DatabaseUtil.expandEntityByTemplate("default", close, close);
			//
			// close.set("signalStatusCd","3");
			// DatabaseUtil.updateEntity("default", close);

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		String eosEx = "流程提交更新业务状态出错！";
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.ews.flow");
			Object[] params = new Object[1];
			params[0] = applyId;
			Object[] objs = logicComponent.invoke("executeBeforeTerminate", params);
			Object obj = objs[0];
			if (!"1".equals(obj)) {
				throw new EOSException(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(eosEx);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(eosEx);
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------->开始!");

			HashMap map = new HashMap();
			map.put("applyId", applyId);

			// 关闭时不删除，修改预警信号状态
			DatabaseExt.queryByNamedSql("default", "com.bos.ews.queryWarnBizInfo.deleteSignalClose", map);
			// 新增时删除预警信号
			DatabaseExt.queryByNamedSql("default", "com.bos.ews.queryWarnBizInfo.deleteSignalOpen", map);

			// 删除预警变更申请
			DataObject adjust = DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewLevelAdjust");
			adjust.set("levelAdjustId", applyId);
			DatabaseUtil.expandEntity("default", adjust);
			String partyId = "";
			if (null != adjust) {
				if (null != adjust.get("partyId") && !"".equals((String) adjust.get("partyId"))) {
					logger.info("partyId--->" + adjust.get("partyId"));
					partyId = (String) adjust.get("partyId");
				}
				DatabaseUtil.deleteEntity("default", adjust);
			}
			logger.info("partyId--->" + partyId);

			// 删除客户预警情况
			/*
			 * DataObject adjust2 = DataObjectUtil.createDataObject(
			 * "com.bos.dataset.ews.TbRewLevelAdjust"); adjust2.set("partyId",
			 * partyId); DatabaseUtil.expandEntityByTemplate("default", adjust2,
			 * adjust2); if(null != adjust2) { logger.info("不删除客户预警情况"); }else {
			 * logger.info("删除客户预警情况"); DataObject warning =
			 * DataObjectUtil.createDataObject
			 * ("com.bos.dataset.ews.TbRewCsmEarlyWarning");
			 * warning.set("partyId", partyId);
			 * DatabaseUtil.deleteEntity("default", warning); }
			 */

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String levelAdjustId = (String) list.get(0);
			DataObject adjust = DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewLevelAdjust");
			adjust.set("levelAdjustId", levelAdjustId);
			DatabaseUtil.expandEntity("default", adjust);

			DataObject warning = DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewCsmSignalList");
			warning.set("partyId", adjust.getString("partyId"));
			warning.set("signalStatusCd", "5");// 信号待关闭 审核
			DataObject[] objs = DatabaseUtil.queryEntitiesByTemplate("default", warning);
			warning.set("signalStatusCd", "4");// 信号待新增 审核
			DataObject[] objs2 = DatabaseUtil.queryEntitiesByTemplate("default", warning);

			String errorContent = null;
			if ((adjust.getString("earlyWarningLevelCd") == null || adjust.getString("earlyWarningLevelCd") == "0") && objs.length == 0
					&& objs2.length == 0) {
				errorContent = "预警级别或预警信号信息未保存！";
			}

			resultMap.put("errorNum", "2");
			resultMap.put("errorCode", "2");
			resultMap.put("errorContent", errorContent);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return resultMap;
	}

}
