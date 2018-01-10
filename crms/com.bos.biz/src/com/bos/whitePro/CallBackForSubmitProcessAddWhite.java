package com.bos.whitePro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.bos.biz.SaveBizInfo;
import com.bos.bizApply.GroupInfo;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.RuleException;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 回调逻辑：提交流程，更新业务表数据 01-未提交; 02-审批中; 03-结束; 04-已删除
 * 
 * */
public class CallBackForSubmitProcessAddWhite implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcessAddWhite.class);

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

		// TODO 自动生成方法存根
		logger.info("------3231------>业务申请撤销流程------>begin！");
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("业务申请撤销流程ID为空！");
				throw new EOSException("业务申请撤销流程ID为空！");
			}
			// 如果成员有在途的，不让撤
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			String partyId = bizApply.getString("partyId");
			String bizType = bizApply.getString("bizType");
			String productType = bizApply.getString("productType");
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			map.put("partyId", partyId);
			if ("03".equals(bizApply.getString("bizType"))) {// 统一授信
				RuleService rs = new RuleService();
				List<MessageObj> msgList = rs.runRule("RBIZ_0062", map);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					throw new EOSException(msg);
				}
				// 撤销掉成员09状态的业务

				DatabaseExt.executeNamedSql("default", "com.bos.bizApply.bizApply.updateMemberApply09", map);
				DatabaseExt.executeNamedSql("default", "com.bos.bizApply.bizApply.updateMemberApprove09", map);
			}

			logger.info("------3231------>业务申请撤销流程------>bizId=" + applyId);
			DataObject bizApply2 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply2.set("applyId", applyId);
			bizApply2.set("oldApplyId", null);
			bizApply2.set("statusType", "06");
			DatabaseUtil.updateEntity("default", bizApply2);
			//删除白名单
			if(applyId!=null&&!"".equals(applyId)){
				DataObject white = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmWhiteCustomer");
				white.set("batchNumber", applyId);
				DataObject[] whites= DatabaseUtil.queryEntitiesByTemplate("default", white);
				if(whites!=null){
					for (DataObject dataObject : whites) {
						dataObject.set("cusStatus", "05");
						DatabaseUtil.updateEntity("default", dataObject);
					}
				}
				
			}
		} catch (WFServiceException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (RuleException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	/**
	 * 
	 * @Title: executeBeforeSubmit
	 * @Description: TODO(用于审批同意流程提交前业务逻辑)
	 * @param processInstId
	 *            流程实例ID号
	 * @return void 返回类型
	 * @throws
	 */
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("业务申请流程提交，开始更新业务状态------bizId=" + applyId + "------->开始!");
			DataObject bizApply1 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply1.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply1);
			String bizType = bizApply1.getString("bizType");
           
			// 更新申请表状态
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			bizApply.set("statusType", "02");
			DatabaseUtil.updateEntity("default", bizApply);
			logger.info("业务申请流程提交，开始更新业务状态------bizId=" + applyId + "------>结束!");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg == null || msg.isEmpty()) {
				msg = "业务申请流程提交时，更新业务状态出错！";
			}
			throw new EOSException(msg);
		}
	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根

	}

	/**
	 * 客户经理提交流程前，进行数据完整性校验（只校验申请数据）
	 * */

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
	
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
