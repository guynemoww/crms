package com.bos.crdPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {

	}

	/**
	 * 流程结束前处理 项目额度：项目额度可以同时多个生效，如果是【申请】则将当前状态置为生效03，如果是【调整】还要将旧额度置为失效04。更新关联的业务申请
	 * 担保额度：担保额度只能有一个生效，将当前状态置为生效03，并将之前状态置为失效04
	 */
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String eosEx = "额度申请流程异常！";
		try {
			// 获取相关信息
			String[] xpath = { "bizId" };
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String limitId = (String) list.get(0);
			DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			partyLimit.set("limitId", limitId);
			eosEx += ">> limitId = " + limitId;
			DatabaseUtil.expandEntity("default", partyLimit);
			String oldLimitId = partyLimit.getString("oldLimitId");
			String limitType = partyLimit.getString("limitType");

			// 额度更新实体
			DataObject limitUpdData = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			DataObject oldLimitUpdData = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");

			// 生效
			limitUpdData.setString("limitId", limitId);
			limitUpdData.setString("statusCd", "03");
			// 担保额度只录入了到期日，需要计算开始日和期限月
			if ("08".equals(limitType)) {
				Date endDate = partyLimit.getDate("endDate");
				Date beginDate = GitUtil.getBusiDate();
				int guarantTerm = (int) DateUtil.getIntervalMonths(beginDate, endDate);
				limitUpdData.setInt("guarantTerm", guarantTerm);
				limitUpdData.setDate("beginDate", beginDate);
			}
			DatabaseUtil.updateEntity("default", limitUpdData);
			
			// 如果是额度调整，则需要将原来额度置为失效
			if(oldLimitId != null){
				oldLimitUpdData.setString("limitId", oldLimitId);
				oldLimitUpdData.setString("statusCd", "04");
				DatabaseUtil.updateEntity("default", oldLimitUpdData);
				
				// 更新业务申请关联的项目额度
				if("09".equals(limitType)){
					DataObject bizUpdData = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
					DataObject bizTemplate = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
					bizUpdData.setString("projectId", limitId);
					bizTemplate.setString("projectId", oldLimitId);
					DatabaseUtil.updateEntityByTemplate("default", bizUpdData, bizTemplate);
				}
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

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String limitId = (String) list.get(0);
			if (null == limitId || "".equals(limitId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			DataObject thirdPartyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			thirdPartyLimit.set("limitId", limitId);
			thirdPartyLimit.set("statusCd", "06");
			DatabaseUtil.updateEntity("default", thirdPartyLimit);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String limitId = (String) list.get(0);
			String partyLimitType = "01";

			DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			partyLimit.set("limitId", limitId);
			DatabaseUtil.expandEntity("default", partyLimit);
			partyLimitType = partyLimit.getString("limitType");

			// 规则校验
			RuleService rs = new RuleService();
			Map paramMap = new HashMap();
			paramMap.put("limitId", limitId);
			if ("01".equals(partyLimitType) || "05".equals(partyLimitType)) {
				// 额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0001", paramMap);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			} else if ("08".equals(partyLimitType)) {
				// 额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0002", paramMap);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			} else if ("09".equals(partyLimitType)) {
				// 额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0003", paramMap);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
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
