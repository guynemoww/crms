package com.bos.grt.cdzykh;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess{
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);
	@Override
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
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
	@Override
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {


		// TODO 自动生成方法存根
		logger.info("--------------->业务申请-质押扣划流程提交------>begin！");
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				// 获取贷款id
				String zykhId = (String) list.get(0);
				if (null == zykhId || "".equals(zykhId)) {
					logger.info("业务申请质押扣划提交流程ID为空！");
					throw new EOSException("业务申请质押扣划提交流程ID为空！");
				}
				logger.info("--------------->业务申请-质押扣划流程提交开始Begin------>zykhId=" + zykhId);
				DataObject tbCdZykhApply = DataObjectUtil.createDataObject("com.bos.dataset.zykh.TbCdZykhApply");
				tbCdZykhApply.set("zykhId", zykhId);
				DatabaseUtil.expandEntity("default", tbCdZykhApply);
				Date busiDate = GitUtil.getBusiDate();//业务时间
				Date applyDate = tbCdZykhApply.getDate("applyDate");//质押扣划业务申请发起日期---用来校验是否当天完成质押扣划
				//校验质押扣划---交易必须当天完成 也就是质押扣划的申请日期
				if(!(applyDate.toString().equals(busiDate.toString()))){
					logger.info("日期非法，质押扣划应该在申请当天完成所有交易！申请日期："+applyDate+"，审批日期"+busiDate);
					throw new EOSException("日期非法，当前系统日期["+busiDate+"]！质押扣划应该在扣划申请当天["+applyDate+"]提交至下一流程。如果仍需做质押扣划，请撤销该流程并重新发起交易！");
				}
				
				tbCdZykhApply.set("applyDate", busiDate);//申请日期---以流程发起的时间为准
				tbCdZykhApply.setString("status", "02");//质押扣划业务申请状态---审批中
				tbCdZykhApply.set("updateTime", busiDate);//最近更新时间
				DatabaseUtil.saveEntity("default", tbCdZykhApply);
				logger.info("--------------->业务申请-质押扣划流程提交结束End------>zykhId=" + zykhId);
			} catch (WFServiceException e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
	}

	@Override
	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}
	/**
	 * 
	 * @Title: executeBeforeSubmit
	 * @Description: TODO(用于审批同意流程撤销前业务逻辑)
	 * @param processInstId
	 *            流程实例ID号
	 * @return void 返回类型
	 * @throws
	 */
	@Override
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {

		// TODO 自动生成方法存根
		logger.info("--------------->业务申请-质押扣划流程撤销------>begin！");
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				// 获取贷款id
				String zykhId = (String) list.get(0);
				if (null == zykhId || "".equals(zykhId)) {
					logger.info("业务申请质押扣划撤销流程ID为空！");
					throw new EOSException("业务申请质押扣划撤销流程ID为空！");
				}
				logger.info("--------------->业务申请-质押扣划流程撤销Begin------>zykhId=" + zykhId);
				DataObject tbCdZykhApply = DataObjectUtil.createDataObject("com.bos.dataset.zykh.TbCdZykhApply");
				tbCdZykhApply.set("zykhId", zykhId);
				DatabaseUtil.expandEntity("default", tbCdZykhApply);
				Date busiDate = GitUtil.getBusiDate();//业务时间
				tbCdZykhApply.setString("status", "06");//质押扣划业务申请状态---已删除
				tbCdZykhApply.set("updateTime", busiDate);//最近更新时间
				DatabaseUtil.saveEntity("default", tbCdZykhApply);
				logger.info("--------------->业务申请-质押扣划流程撤销End------>zykhId=" + zykhId);
			} catch (WFServiceException e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
	}

	@Override
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			
			// 获取贷款id
			String zykhId = (String) list.get(0);
			if (null == zykhId || "".equals(zykhId)) {
				logger.info("------业务申请-质押扣划流程提交数据校验------>质押扣划申请ID为空！");
				throw new EOSException("------业务申请-质押扣划流程提交数据校验------>质押扣划申请ID为空！");
			}
			
			RuleService rs = new RuleService();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("zykhId", zykhId);
			List<MessageObj> msgList = rs.runRule("RBIZ_0089", paramMap);
			String msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorNum", "2");
			resultMap.put("errorCode", "2");
			resultMap.put("errorContent", "执行规则校验出错！");
		} catch (Throwable e) {
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
