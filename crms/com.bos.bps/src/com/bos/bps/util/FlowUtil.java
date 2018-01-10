package com.bos.bps.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.bos.bps.dao.ProcessInstMappingDAO;
import com.bos.bps.dao.ProcessInstanceDAO;
import com.bos.bps.dao.WorkItemInstanceDAO;
import com.bos.bps.dao.WorkItemMappingDAO;
import com.bos.bps.service.ActivityInstManagerService;
import com.bos.bps.service.ProcessInstManagerService;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.service.WFDefinitionQueryManagerService;
import com.bos.bps.service.WorkItemManangerService;
import com.bos.bps.vo.BackInfoVO;
import com.bos.comm.util.RuleProcessUtil;
import com.bos.pub.DecisionUtil;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.PositionUtil;
import com.bos.rule.RuleUtil;
import com.bos.utp.dataset.organization.OrgTreeNode;
import com.bos.utp.tools.DBUtil;
import com.bos.utp.tools.IconCls;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.eos.workflow.data.WFActivityDefine;
import com.eos.workflow.data.WFActivityInst;
import com.eos.workflow.data.WFConnector;
import com.eos.workflow.data.WFProcessInst;
import com.eos.workflow.data.WFWorkItem;
import com.eos.workflow.omservice.WFParticipant;
import com.git.easyrule.util.RuleException;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

@Bizlet("")
public class FlowUtil {

	public static TraceLogger logger = new TraceLogger(FlowUtil.class);

	public static enum FLOW_STATUS {
		// 申请 ，运行，撤销，不同意，完成
		APPLY("00"), RUN("10"), CANCEL("20"), DENIAL("30"), COMPLETE("99");
		private String key;

		FLOW_STATUS(String key) {
			this.key = key;
		}

		public static FLOW_STATUS get(String key) {
			FLOW_STATUS[] temps = values();
			for (FLOW_STATUS s : temps) {
				if (s.value().equals(key)) {
					return s;
				}
			}
			return null;
		}

		public String toString() {
			return key;
		}

		public String value() {
			return key;
		}
	}

	/**
	 * 获取节点的流程相关信息
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 * @throws Exception
	 */
	@Bizlet("获取节点的流程相关信息")
	public static Map<String, String> getNodeConfigInfo(String processInstID) throws Exception {

		logger.info("------>执行[获取节点的流程相关信息]开始");
		Map<String, String> map = new HashMap<String, String>();
		// 获取活动实例信息
		WFActivityInst ait = ActivityInstManagerService.getCurrentActivityInstByProcessInstID(Long.valueOf(processInstID));
		if (null != ait) {

			long activityInstId = ait.getActivityInstID();
			String activityInstName = ait.getActivityInstName();
			// 根据活动实例ID,获取工作项信息
			List<WFWorkItem> wis = WorkItemManangerService.queryWorkItemsByActivityInstID(activityInstId);
			IUserObject user = CommonUtil.getIUserObject();
			String userid = user.getUserId();
			String startTime = null;
			if (null != wis && wis.size() > 0) {
				long workItemID = wis.get(0).getWorkItemID();
				for (int i = 0; i < wis.size(); i++) {
					WFWorkItem item = wis.get(i);
					if (userid.equals(item.getParticipant())) {

						workItemID = item.getWorkItemID();
						startTime = item.getStartTime();
					}
				}
				String processDefName = wis.get(0).getProcessDefName();
				String activityDefID = wis.get(0).getActivityDefID();
				// 获取节点配置信息
				DataObject obj = WorkItemMappingDAO.getWorkItemMapping(processDefName, activityDefID);

				map.put("selectType", obj.getString("selectType"));
				map.put("conclusion", obj.getString("finalJudge"));
				map.put("ruleID", obj.getString("ruleId"));
				map.put("activityInstId", String.valueOf(activityInstId));
				map.put("activityInstName", activityInstName);
				map.put("workItemId", String.valueOf(workItemID));
				map.put("processInstId", processInstID);
				map.put("processDefName", processDefName);
				map.put("activityDefId", activityDefID);
				map.put("workitemMappingId", obj.getString("workitemMappingId"));
				map.put("templateVersion", obj.getString("templateVersion"));
				map.put("startTime", CommonUtil.formatDate(startTime));
				map.put("doUrl", obj.getString("doUrl"));

			} else {
				logger.info("------>[CBE005]获取节点配置信息时，未查到工作项");
				throw new EOSException("CBE005", "未查到工作项");
			}
		} else {
			logger.info("------>[CBE004]获取节点配置信息时，未查到活动实例");
			throw new EOSException("CBE004", "未查到活动实例");
		}
		logger.info("------>执行[获取节点的流程相关信息]结束");
		return map;
	}

	/**
	 * 获取有效的退回岗位列表
	 * 
	 * @param processInstId
	 * @param currentActInstID
	 * @throws Exception
	 */
	@Bizlet("获取有效的退回岗位列表")
	public static BackInfoVO getValidPreviousWorkInfo(String processInstId, String currentActInstID) throws Exception {

		BackInfoVO backVO = new BackInfoVO();
		int backsize = 0;
		List<WFActivityDefine> atDefs = ActivityInstManagerService.getPreviousWorkInfo(processInstId, currentActInstID, "startActivity");
		if (null != atDefs && atDefs.size() > 0) {

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (WFActivityDefine define : atDefs) {

				backsize++;
				String id = define.getId();
				String name = define.getName();
				// 过滤掉不能退回的节点，以及开始节点
				if (id.indexOf("P_") != -1 || "startActivity".equals(id)) {

					continue;
				} else {

					if (!"route".equals(define.getType())) {

						// 根据活动定义ID，查询出最近一次活动实例信息
						WFActivityInst ai = ActivityInstManagerService.findLastActivityInstByActivityID(processInstId, id);
						// 根据活动实例ID,获取工作项
						List<WFWorkItem> wis = WorkItemManangerService.queryWorkItemsByActivityInstID(ai.getActivityInstID());
						// 获取参与者名称
						String partiId = wis.get(0).getParticipant();
						String partiname = wis.get(0).getPartiName();
						Map<String, String> map = new HashMap<String, String>();
						map.put("dactivityInstId", id);
						map.put("dactivityInstName", name + "[" + partiname + "(" + partiId + ")]");
						list.add(map);
					}
				}

			}
			backVO.setList(list);
		} else {
			backVO.setErrCode("E001");
			backVO.setErrDesc("获取退回岗位列表失败");
		}
		backVO.setBackSize(backsize);

		return backVO;
	}

	/**
	 * 根据审批结论，提交,回退流程至下一岗或结束流程
	 * 
	 * @param className
	 *            回调类路径
	 * @param it
	 *            工作项信息
	 * @param xid
	 *            事务ID
	 * @throws Exception
	 * @throws Exception
	 */
	@Bizlet("根据审批结论，提交,回退流程至下一岗或结束流程")
	public static void submitProcessToNext(String className, Map<String, String> it) throws Exception {

		logger.info("------>[submitProcessToNext]根据审批结论，提交,回退流程至下一岗或结束流程开始");
		// 审批结论
		String conclusion = it.get("conclusion");
		// 流程实例ID
		String processInstId = it.get("processInstId");
		// 当前活动实例ID
		String currentActInstID = it.get("activityInstId");
		// 当前活动定义ID
		String currentActDefId = it.get("activityDefId");
		// 工作项实例ID
		String workItemId = it.get("workItemId");
		// 活动图元类型
		String activityType = it.get("activityType");
		logger.info("------>流程编号为:" + processInstId + ",审批结论的值为：" + conclusion);
		if (null == conclusion || "".equals(conclusion)) {
			// 默认为同意
			conclusion = "1";
			it.put("conclusion", conclusion);
		}
		try {
			/**
			 * 退回操作或再议操作
			 */
			if (FlowConstants.APPROVE_99.equals(conclusion) || FlowConstants.APPROVE_997.equals(conclusion)) {

				// 获取退回目标节点定义ID号
				String destActDefId = it.get("dactivityInstId");

				logger.info("------>执行退回，当前活动节点为" + currentActDefId + ",退回节点为：" + destActDefId);

				// 更新补录操作退回岗位ID
				Map<String, Object> relaDatas = new HashMap<String, Object>();
				// 每次提交时，将流程阅读状态置为未读
				relaDatas.put("wfReadStatus", "1");
				// 每次提交时，将补充材料标志清空
				relaDatas.put("wfBackOperPositionId", "");
				relaDatas.put("postId", "");
				String posicode = "P1" + destActDefId.substring(2);
				if ("P1001".equals(posicode)) {
					relaDatas.put("wfApproNum", "0");
				} else {
					relaDatas.put("wfApproNum", "1");
				}
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
				// 获取上一次工作项信息
				DataObject obj = WorkItemInstanceDAO.getWorkItemByProcessIdAndActivityId(processInstId, destActDefId);
				it.put("nextUsersNum", obj.getString("userNum"));
				it.put("nextUsersName", obj.getString("userName"));
				it.put("nextOrgName", obj.getString("orgName"));
				it.put("nextOrgCd", obj.getString("orgNum"));
				it.put("nextPostName", obj.getString("postName"));
				it.put("nextPostCd", obj.getString("postCd"));

				// 保存工作项
				WorkItemInstanceDAO.insertWorkItemInstance(it);
				// 更新流程实例
				ProcessInstanceDAO.updateProcessInstance(it);

				// 调用接口，回退流程
				ActivityInstManagerService.backActivity(currentActInstID, destActDefId);

				// 回调退回业务函数
				logger.info("------>回调退回业务函数--开始");
				callbankBeforeUntreadMetchod(className, processInstId, it);
				logger.info("------>回调退回业务函数--结束");

				/**
				 * 补录材料:与退回操作相同，唯一不同的是，需要设置退回操作岗标识，用于补录完成后，直接提交至该岗
				 */
			} else if (FlowConstants.APPROVE_98.equals(conclusion)) {

				// 获取退回目标节点定义ID号
				String destActDefId = "P3001";// it.get("dactivityInstId");

				logger.info("------>执行补充材料，当前活动节点为" + currentActDefId + ",退回节点为：" + destActDefId);

				// 更新补录操作退回岗位ID
				Map<String, Object> relaDatas = new HashMap<String, Object>();
				// 记录退回岗ID
				relaDatas.put("wfBackOperPositionId", currentActDefId);
				// 每次提交时，将流程阅读状态置为未读
				relaDatas.put("wfReadStatus", "1");
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
				// 获取上一次工作项信息
				DataObject obj = WorkItemInstanceDAO.getWorkItemByProcessIdAndActivityId(processInstId, destActDefId);
				it.put("nextUsersNum", obj.getString("userNum"));
				it.put("nextUsersName", obj.getString("userName"));
				it.put("nextOrgName", obj.getString("orgName"));
				it.put("nextOrgCd", obj.getString("orgNum"));
				it.put("nextPostName", obj.getString("postName"));
				it.put("nextPostCd", obj.getString("postCd"));

				// 保存工作项
				WorkItemInstanceDAO.insertWorkItemInstance(it);
				// 更新流程实例
				ProcessInstanceDAO.updateProcessInstance(it);

				// 调用接口，回退流程
				ActivityInstManagerService.backActivity(currentActInstID, destActDefId);

				// 回调退回业务函数
				logger.info("------>回调补充材料业务函数--开始");
				callbankBeforeUntreadMetchod(className, processInstId, it);
				logger.info("------>回调补充材料业务函数--结束");

				/**
				 * 结束流程操作：具有否决权限的岗位，其它选择不同意，则直接提交给下一岗。
				 */
			} else if (FlowConstants.APPROVE_999.equals(conclusion) || FlowConstants.APPROVE_2.equals(conclusion)) {

				logger.info("------>执行流程否决操作开始");

				// 更新流程实例
				int wnum = WorkItemManangerService.getWorkingItemNumByActivityInstId(Long.valueOf(currentActInstID));
				if (wnum <= 1) {

					// 当真正结束时，无任何下一岗信息
					WorkItemInstanceDAO.updateWorkItemInstanceByReject(it);
					// 更新流程实例
					ProcessInstanceDAO.updateProcessInstanceStatus(it);
					// 结束流程
					ProcessInstManagerService.terminateProcessInstance(processInstId);
				} else {
					// 保存工作项
					WorkItemInstanceDAO.insertWorkItemInstance(it);
					// 完成工作项
					WorkItemManangerService.finishWorkItem(workItemId);
				}
				// 回调结束业务函数
				logger.info("------>回调否决业务函数--开始");
				callbankBeforeRejectMetchod(className, processInstId, it);
				logger.info("------>回调否决业务函数--结束");

				/**
				 * 其它情况，不管选择同意，有条件同意，不同意等，都正常提交（需要特殊处理结束图元，如果是最后一岗时，回调结束业务函数） 对于补充材料后再提交的情况，需要设置默认线的值成立，并重新创建目标节点工作项
				 */
			} else {

				logger.info("------>流程正常提交执行开始，活动图元类型为：" + activityType);

				// 如果是人工选择的岗位，需要把岗位ID设置到相关数据中
				if (FlowConstants.ACTIVITYPE_MORE.equals(activityType)) {

					Map<String, Object> relaDatas = new HashMap<String, Object>();
					relaDatas.put("postId", it.get("nextActivityDefId"));
					RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
					// 修改下一参与人变量值
					it.put("userVariable", "next_" + it.get("nextActivityDefId"));
					logger.info("------>活动图元类型为more，需要人工选择时，改变参与人变量值为：" + it.get("userVariable"));
				}

				// 获取退回岗标志与下一审批人信息，用于判断是否是补充材料后，再提交，以及控制指定审批人
				String[] xpath = { "wfBackOperPositionId", "participants", "wfApproNum" };
				List<Object> strs = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				String backOperPositionId = (String) strs.get(0);
				String nextParticipants = (String) strs.get(1);
				String wfApproNum = (String) strs.get(2);
				if (null == wfApproNum || "".equals(wfApproNum)) {

					wfApproNum = "0";
				}
				logger.info("------>正常流程提交时，退回岗标志：" + backOperPositionId + ",指定参与人值：" + nextParticipants);

				// 为了实现根据机构过滤，后面拼接了机构号+"_"+it.get("nextOrgCd")
				// String partit = it.get("nextUsersNum")+"_"+it.get("nextUsersName")+"_"+it.get("nextOrgCd");
				String partit = it.get("nextUsersNum") + "_" + it.get("nextUsersName");
				// 如果是拼接了多个人员，则需要转换成符合参与者格式的字符串11111_张三|22222_李四
				if (null != it.get("nextUsersNum") && it.get("nextUsersNum").indexOf(",") != -1) {

					partit = CommonUtil.getStrBySplitWithArray(it.get("nextUsersNum").split(","), it.get("nextUsersName").split(","));
					// 同时设置审批人个数
					int num = it.get("nextUsersNum").split(",").length;
					wfApproNum = String.valueOf(num + Integer.valueOf(wfApproNum));
				} else {

					// 同时设置审批人个数
					wfApproNum = String.valueOf(Integer.valueOf(wfApproNum) + 1);
				}

				// 如果不是最后结束的节点，并且没有选择
				if (!FlowConstants.ACTIVITYPE_FINISH.equals(activityType)) {
					// 并且未选择下一审批人，则从相关数据中获取
					if (null == it.get("nextUsersNum") || "".equals(it.get("nextUsersNum"))) {
						partit = nextParticipants;
					}
				}
				logger.info("------>正常流程提交时，下一参与人变量值：" + it.get("userVariable") + ",下一参与人值：" + partit);

				if (null != it.get("userVariable") && !"".equals(it.get("userVariable"))) {
					WFParticipant[] bpsParticipant = createParticipant(partit, "person");
					// 设置下一岗参与者
					RelativeDataManagerService.setRelativeDataForParticipant(Long.valueOf(processInstId), it.get("userVariable"), bpsParticipant);
				}

				// 查询当前活动节点的工作项个数
				int wnum = WorkItemManangerService.getWorkingItemNumByActivityInstId(Long.valueOf(currentActInstID));

				// 随完成工作项，一起提交的map
				Map<String, Object> pmap = new HashMap<String, Object>();
				// 每次提交时，如果指定了参与人，用过之后，则清空，以防止下一节点仍使用该参与人
				pmap.put("participants", "");
				// 每次提交时，清空部门过虑
				pmap.put("wfDepartmentFilter", "");
				// 每次提交时，清空修改标志！
				pmap.put("wfModifySaveFlag", "true");
				/**
				 * 每次提交时，将值转移给判断条件变量(考虑到退回标志不清空，会出现当退回到正好可以补充材料的岗，会把退回当补充材料操作)， 然后退回岗标志也清空,转移是为了兼容，当补充材料的节点，正好是手工选择下一岗位操作，这时需要合并参数变量
				 */
				if (null != backOperPositionId && !"".equals(backOperPositionId)) {

					pmap.put("postId", backOperPositionId);
				}
				pmap.put("wfBackOperPositionId", "");
				// 每次提交时，将流程阅读状态置为未读
				pmap.put("wfReadStatus", "1");
				// 每次提交时，存储当前节点审批结论(只针对业务申请使用)
				pmap.put(currentActDefId + "_conclusion", conclusion);

				// 提交流程至下一节点（补充材料的处理方式与正常提交一致）
				WorkItemManangerService.sumitProcessToNext(workItemId, pmap);
				// 设置审批人数
				WFProcessInst pi = ProcessInstManagerService.queryProcessInstDetail(Long.valueOf(processInstId));
				int status = pi.getCurrentState();
				Map<String, Object> temp = new HashMap<String, Object>();
				if (status == 2) {

					temp.put("wfApproNum", wfApproNum);
					temp.put("postId", "");
					RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), temp);
				}

				// 保存工作项
				WorkItemInstanceDAO.insertWorkItemInstance(it);

				// 已经审批到末尾，流程结束
				if (FlowConstants.ACTIVITYPE_FINISH.equals(activityType)) {

					// 更新流程实例
					if (wnum <= 1) {

						// 更新流程实例
						ProcessInstanceDAO.updateProcessInstanceStatus(it);
						// 回调结束业务函数
						logger.info("------>回调结束业务函数--开始");
						callbankBeforeTerminateMetchod(className, processInstId, it);
						logger.info("------>回调结束业务函数--结束");
					}

				} else {

					// 更新流程实例
					ProcessInstanceDAO.updateProcessInstance(it);
					// 回调提交流程前业务函数
					logger.info("------>回调提交流程前业务函数--开始");
					callbankBeforeSubmitMetchod(className, processInstId, it);
					logger.info("------>回调提交流程前业务函数--结束");

				}
			}

			logger.info("------>[submitProcessToNext]根据审批结论，提交,回退流程至下一岗或结束流程结束，事务提交。");
		} catch (Exception e) {
			logger.info("------>[submitProcessToNext]" + e);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 创建参与人，支持多个
	 * 
	 * @param userNum
	 * @param type
	 * @return
	 */
	@Bizlet("创建参与人")
	public static WFParticipant[] createParticipant(String userNum, String type) {

		String[] users = userNum.split("\\|");
		WFParticipant[] wps = new WFParticipant[users.length];
		for (int i = 0; i < users.length; i++) {
			if (users[i].indexOf("_") > 0) {
				String[] u = users[i].split("_");
				if (u.length >= 3) {

					WFParticipant wpt = new WFParticipant();
					wpt.setId(u[0]);
					wpt.setTypeCode(type);
					wpt.setName(u[1]);
					wps[i] = wpt;
				} else {
					WFParticipant wpt = new WFParticipant();
					wpt.setId(u[0]);
					wpt.setTypeCode(type);
					wpt.setName(u[1]);
					wps[i] = wpt;
				}
			} else {
				WFParticipant wpt = new WFParticipant();
				wpt.setId(users[i]);
				wpt.setTypeCode(type);
				wpt.setName(users[i]);
				wps[i] = wpt;
			}
		}
		return wps;
	}

	/**
	 * 创建代理例外工作项（流程操作权限 ALL：全部 STARTPROC：启动流程 EXEWI：执行工作项） 任务类型（活动|流程） PROCESS：流程 ACTIVITY：活动
	 * 
	 * @param agentItem
	 * @return
	 */
	/*
	 * @Bizlet("创建代理工作项") public static BPSAgentItem[] createBpsAgentItem(String agentItem){
	 * 
	 * BPSAgentItem[] ais = null;
	 * 
	 * if(null != agentItem){
	 * 
	 * if(agentItem.indexOf(",")!=-1){
	 * 
	 * String[] strs = agentItem.split(","); ais = new BPSAgentItem[strs.length]; for (int i = 0; i < strs.length; i++) { BPSAgentItem ai = new BPSAgentItem(); String otherAgItem = strs[i]; ai.setItemID(otherAgItem); ai.setAccessType("ALL"); ai.setItemType("PROCESS"); ais[i] = ai; } }else{ ais = new BPSAgentItem[1]; BPSAgentItem ai = new BPSAgentItem(); ai.setItemID(agentItem); ai.setAccessType("ALL"); ai.setItemType("PROCESS"); ais[0]=ai; } } return ais; }
	 */

	/**
	 * 设置流程中相关数据的[修改保存标识]
	 * 
	 * @param viewUrl
	 *            业务信息展示页
	 * @param processInstId
	 *            流程实例ID
	 * @throws Exception
	 */
	@Bizlet("设置流程中相关数据的[修改保存标识]")
	public static void chengeWfModifySaveFlag(String viewUrl, String processInstId) throws Exception {

		Map<String, Object> relaDatas = new HashMap<String, Object>();

		// 截取页面类型
		String pageType = viewUrl.substring(viewUrl.lastIndexOf("_") + 1, viewUrl.indexOf(".jsp"));
		// 查看页面
		if (FlowConstants.PAGETYPE_VIEW.equals(pageType)) {

			relaDatas.put("wfModifySaveFlag", "true");

			// 编辑页面
		} else if (FlowConstants.PAGETYPE_EDIT.equals(pageType)) {

			relaDatas.put("wfModifySaveFlag", "false");
		} else {// 默认为true
			relaDatas.put("wfModifySaveFlag", "true");
		}

		// 因为后续业务发展要求，虽然修改标志，暂时没用，但现在需要增加阅读标志记录。
		relaDatas.put("wfReadStatus", "2");

		RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);

	}

	/**
	 * 获取下一节点信息
	 * 
	 * @param it
	 *            流程基本数据
	 * @param para
	 *            流程业务参数数据
	 * @return 下一节点信息
	 * @throws Exception
	 */
	@Bizlet("获取下一节点信息")
	public Map<String, Object> getNextNodeExtendAttibute(Map<String, Object> it, Map<String, String> para) throws Exception {

		logger.info("------>[getNextNodeExtendAttibute]获取下一节点信息开始");
		Map<String, Object> map = new HashMap<String, Object>();

		// 流程实例ID
		String processInstId = (String) it.get("processInstId");
		// 审批结论
		String conclusion = (String) it.get("conclusion");
		// 规则ID
		String ruleId = (String) it.get("ruleID");
		// 选择方式
		String selectType = (String) it.get("selectType");
		// 活动定义ID
		String activityDefId = (String) it.get("activityDefId");

		// 获取相关数据信息
		String[] xpath = { "wfBackOperPositionId", "wfCreateOrgCode", "participants", "wfOtherOrgCode", "isWfOtherOrg", "bizId", "wfDepartmentFilter", "authAmt", "wfCreateUserId", "wfCreateUserName", "wfCreateOrgName", "sureOrgCd" };
		List<Object> strs = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);

		if (null == strs || strs.size() < 0) {

			throw new EOSException("CBE003", "没有获取到相关数据");
		}
		logger.info("------>[participants]人工指定下一审批人员值：" + strs.get(2));

		// 获取流程定义ID
		WFProcessInst pi = ProcessInstManagerService.queryProcessInstDetail(Long.valueOf(processInstId));
		long processDefID = pi.getProcessDefID();

		// 存储相关数据
		Map<String, Object> relaDatas = new HashMap<String, Object>();

		// 是否选择审批结论，默认为同意1
		if (null == conclusion || "".equals(conclusion)) {

			conclusion = "1";
		}
		relaDatas.put("conclusion", conclusion);
		// 合并相关数据map
		relaDatas.putAll(para);

		logger.info("------>[para]动态配置流程业务参数值：" + para.toString() + ",节点选择类型：" + selectType);

		// 获取session里的机构级别串
		IUserObject user = CommonUtil.getIUserObject();
		Map<String, Object> attmap = user.getAttributes();
		String parentorglevels = (String) attmap.get("parentorglevels");
		// 取上级机构(当前用户的上级机构)
		String upOrglv = getUpOrgLv(parentorglevels, pi.getProcessDefName());
		logger.info("======>机构串为：" + parentorglevels + "计算的上级机构级别为：" + upOrglv + ",流程模板是：" + pi.getProcessDefName());
		relaDatas.put("wfUpOrgLv", upOrglv);

		// relaDatas.put("signMode", "1");
		// add by shangmf
		logger.info("org_num：" + strs.get(1));
		String org_num = String.valueOf(strs.get(1));
		String orgArea = GitUtil.getOrgArea(org_num);
		relaDatas.put("orgArea", orgArea);
		// 取创建机构的上级机构
		relaDatas.put("wfCreateUpOrgLv", GitUtil.getParentLevel(org_num));
		if ("com.bos.bps.biz.single_biz_apply_all_mccb".equals(pi.getProcessDefName()) || "com.bos.bps.biz.biz_apply_member_mccb".equals(pi.getProcessDefName()) || "com.bos.bps.biz.group_cust_biz_apply_mccb".equals(pi.getProcessDefName()) || "com.bos.bps.biz.Single_biz_apply_lowrisk_mccb".equals(pi.getProcessDefName())) {// 业务申请流程设置业务条线
			HashMap ap = (HashMap) getProductInfo(String.valueOf(strs.get(5)));
			if (!"000000".equals(ap.get("errCode"))) {
				return ap;
			} else {
				relaDatas.put("isMyrz", ap.get("isMyrz"));
				relaDatas.put("bizLine", ap.get("bizLine"));
			}
		}
		if ("com.bos.bps.crt.crt_sign_mccb".equals(pi.getProcessDefName())) {// 合同签约流程设置业务条线
			String isCurrency = getContractInfo(String.valueOf(strs.get(5)));
			relaDatas.put("isCurrency", isCurrency);
		}
		// 计算一级支行是否配置放款支付审核岗
		PositionUtil pu = new PositionUtil();
		String[] rules = ruleId.split(",");
		for (int i = 0; i < rules.length; i++) {
			String r = rules[i];

			String isNotConf = pu.checkPositionExist(r, String.valueOf(strs.get(1)));
			relaDatas.put(r, isNotConf);
		}
		// 多法人-北川富民-计算业务申请计算授权规则
		if (DictContents.ORG_BCFM.equals(GitUtil.getLegorg()) && pi.getProcessDefName().indexOf("com.bos.bps.biz") > -1) {
			String singleMode = excuteBcfmRuleAuth(processInstId);
			relaDatas.put("singleMode", singleMode);
		}

		// 调用授权，计算终批机构以及岗位，以及审批官级别
		Map<String, Object> rmap = excuteRuleAuth(processInstId, ruleId);

		Object rule_desc = rmap.get("errDesc");
		if (null != rule_desc && !"".equals(rule_desc) && FlowConstants.BPS_RULEID_SQ.equals(ruleId)) {
			// 当调用授权的时候，没有找到规则，则返回错误信息
			return rmap;
		}

		relaDatas.putAll(rmap);

		// 设置相关数据
		RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);

		// 获取连接线
		List<WFConnector> connector = WFDefinitionQueryManagerService.queryNextTransition(processDefID, activityDefId);

		map.put("createrOrgCd", strs.get(1));
		map.put("participants", strs.get(2));
		map.put("wfOtherOrgCode", strs.get(3));// 跨机构（非上下级机构）的目标机构号
		map.put("isWfOtherOrg", strs.get(4));// 是否跨机构
		map.put("wfDepartmentFilter", strs.get(6));// 部门过滤strs[6]
		map.put("processDefName", pi.getProcessDefName());
		map.put("templateVersion", it.get("templateVersion").toString());
		map.put("processInstId", processInstId);
		map.put("authAmt", strs.get(7));// 小贷申请金额
		map.put("wfCreateUserId", strs.get(8));// 流程发起人
		map.put("sureOrgCd", strs.get(11));// 指定机构
		map.put("activityDefId", activityDefId);// 当前运行节点定义
		// 获取下一节点定义信息,选择方式 1：正常/默认 2：自主选岗,3,手工选人
		if ("1".equals(selectType)) {

			WFActivityDefine def = checkNextActive((String) strs.get(0), connector, processInstId, processDefID);
			logger.info("------>[getNextNodeExtendAttibute]下一节点定义ID：" + def.getId() + ",定议名称：" + def.getName());
			// 获取节点配置信息
			map.put("WFActivityDefineId", def.getId());// def.getId()
			map.put("WFActivityDefineParticipantType", def.getParticipantType());
			map.put("processDefName", pi.getProcessDefName());
			;
			map.put("templateVersion", it.get("templateVersion").toString());
			map.put("type", def.getType());
			// 获取参与者名单
			if (!"finish".equals(def.getType())) {

				String sql = generateQueryUserSql(map, "oracle");// oracle生成oracle数据库可执行sql语句，DB2生成DB2数据库可执行sql语句
				map.put("sql", sql);
				Object[] users = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.tools.common.common_select", map);
				if (null != users && users.length > 0) {
					DataObject data = (DataObject) users[0];
					map.put("nextOrgCd", data.get("ORGCODE"));
					map.put("nextOrgName", data.get("ORGNAME"));
					map.put("nextPostCd", data.get("POSICODE"));
					map.put("nextPostName", data.get("POSINAME"));
					String nextUserId = "";
					String nextUserName = "";
					for (int i = 0; i < users.length; i++) {
						DataObject temp = (DataObject) users[i];
						if (i == users.length - 1) {
							nextUserId += temp.getString("USERID");
							nextUserName += temp.getString("OPERATORNAME");
						} else {
							nextUserId += temp.getString("USERID") + ",";
							nextUserName += temp.getString("OPERATORNAME") + ",";
						}
					}
					map.put("nextUserId", nextUserId);
					map.put("nextUserName", nextUserName);
				}
			}

			// 单独处理返回发起岗确认，支持最后审批通过后，返回任何发起岗
			String ptps = setParticipantOfBackPosition(pi.getProcessDefName(), it.get("templateVersion").toString(), def.getId(), strs);
			map.put("participants", ptps);

		} else if ("2".equals(selectType)) {

			// 存放需要人工选择的节点信息
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < connector.size(); i++) {
				WFConnector conn = connector.get(i);

				// 存入岗位信息
				Map<String, String> temp = new HashMap<String, String>();
				// 获取节点配置信息
				DataObject dataobj = WorkItemMappingDAO.getWorkItempMappingByVersion(pi.getProcessDefName(), it.get("templateVersion").toString(), conn.getDestActID());
				temp.put("activityDefineId", conn.getDestActID());// conn.getDestActID()
				temp.put("activityDefineName", conn.getDestActName());
				temp.put("orglevel", dataobj.getString("orgLvCd"));
				list.add(temp);

			}
			map.put("type", "more");
			map.put("WFActivityDefineId", list);
			map.put("WFActivityDefineParticipantType", "relevantdata");
		} else if ("3".equals(selectType)) {

			WFActivityDefine def = checkNextActive(String.valueOf(strs.get(0)), connector, processInstId, processDefID);
			logger.info("------>[getNextNodeExtendAttibute]下一节点定义ID：" + def.getId() + ",定议名称：" + def.getName());
			// 获取节点配置信息
			DataObject dataobj = WorkItemMappingDAO.getWorkItempMappingByVersion(pi.getProcessDefName(), it.get("templateVersion").toString(), def.getId());
			map.put("orglevel", dataobj.getString("orgLvCd"));
			map.put("type", def.getType());
			map.put("WFActivityDefineId", def.getId());// def.getId()
			map.put("WFActivityDefineParticipantType", def.getParticipantType());
			map.put("processDefName", pi.getProcessDefName());
			;
			map.put("templateVersion", it.get("templateVersion").toString());

			String orgdegree = (String) attmap.get("orgdegree");
			if (!"7".equals(conclusion) && !"P1216".equals("P1" + activityDefId.substring(2))) {

				// 获取参与者名单
				if (!"finish".equals(def.getType())) {

					String sql = generateQueryUserSql(map, "oracle");// oracle生成oracle数据库可执行sql语句，DB2生成DB2数据库可执行sql语句
					map.put("sql", sql);
					Object[] users = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.tools.common.common_select", map);
					if (null != users && users.length > 0) {
						DataObject data = (DataObject) users[0];
						map.put("nextOrgCd", data.get("ORGCODE"));
						map.put("nextOrgName", data.get("ORGNAME"));
						map.put("nextPostCd", data.get("POSICODE"));
						map.put("nextPostName", data.get("POSINAME"));
						String nextUserId = "";
						String nextUserName = "";
						for (int i = 0; i < users.length; i++) {
							DataObject temp = (DataObject) users[i];
							if (i == users.length - 1) {
								nextUserId += temp.getString("USERID");
								nextUserName += temp.getString("OPERATORNAME");
							} else {
								nextUserId += temp.getString("USERID") + ",";
								nextUserName += temp.getString("OPERATORNAME") + ",";
							}
						}
						map.put("nextUserId", nextUserId);
						map.put("nextUserName", nextUserName);
					}
				}
			}

			// 单独处理返回发起岗确认，支持最后审批通过后，返回任何发起岗
			String ptps = setParticipantOfBackPosition(pi.getProcessDefName(), it.get("templateVersion").toString(), def.getId(), strs);
			map.put("participants", ptps);

		} else {// 更多的选择方式，待处理

		}

		return map;
	}

	/**
	 * 如果流程最后一个岗，返回发起岗确认，则设置流程发起参与者
	 * 
	 * @param processDefName
	 * @param version
	 * @param activityDefId
	 */
	private String setParticipantOfBackPosition(String processDefName, String version, String activityDefId, List<Object> strs) {
		// 获取下一节点配置信息
		DataObject dataobj = WorkItemMappingDAO.getWorkItempMappingByVersion(processDefName, version, activityDefId);
		String reTarget = dataobj.getString("reTarget");
		if (null != reTarget && "B".equals(reTarget)) {

			return strs.get(8) + "_" + strs.get(9) + "_" + strs.get(1) + "_" + strs.get(10);
		}
		return null;
	}

	/**
	 * 调用规则（授权），获取规则计算结果，将其设置至相关数据域中--作废
	 * 
	 * @param processInstId
	 * @param ruleId
	 * @throws Exception
	 */
	@Bizlet("调用规则（授权），获取规则计算结果，将其设置至相关数据域中")
	public static void excuteRule(String processInstId, String ruleId) throws Exception {

		logger.info("------>调用规则，规则Id为：" + ruleId);

		// 判断是否有规则，有则调用规则，将结果设置给相关数据
		if (null != ruleId && !"".equals(ruleId) && !"null".equals(ruleId)) {

			logger.info("***********************************调用规则【" + processInstId + "】开始**********************************************");
			Map<String, Object> relaDatas = new HashMap<String, Object>();
			String[] xpath = { "bizId" };
			List<Object> strs = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);

			HashMap<String, Object> ruleMap = new HashMap<String, Object>();// RuleProcessUtil.initAuthorizationDatas((String)strs.get(0));
			logger.info("======>授权输入参数：" + ruleMap.toString());
			// 这里调用规则，计算结果
			DecisionUtil du = new DecisionUtil();
			// 配置的规则是转授权
			if (FlowConstants.BPS_RULEID_SQ.equals(ruleId)) {

				Map<String, Object> temp = du.bizFlowGrant(ruleMap, ruleMap, processInstId);
				// 获取规则执行结果
				Map resultMap = (Map) temp.get("result");

				logger.info("======>授权输出参数：" + resultMap.toString());
				// 如果不为空，则表明正常执行，则获取有权限的机构级别，否则，抛出异常
				if (null == temp.get("msg")) {

					relaDatas.put("ruleResult", String.valueOf(resultMap.get("orglevel")));
				} else {
					throw new EOSException("CBE005", "根据参数值，计算规则结果失败，错误信息为：" + temp.get("msg"));
				}
				// 配置的是其它规则
			} else {

				// 获取规则所需参数
				FlowConstants.initRuleMap();
				String[] ruleKeys = FlowConstants.getRuleParameterArrays(ruleId);
				// 从相关数据域中获取对应的值
				HashMap<String, Object> ruleMap2 = null;
				List<Object> values = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), ruleKeys);
				// 合并keys数组，values数组为map类型
				ruleMap2 = (HashMap<String, Object>) CommonUtil.stringArrayToMap(ruleKeys, (String[]) values.toArray());

				logger.info("======>普通规则输入参数：" + ruleMap2.toString());
				HashMap<String, Object> rmap = new HashMap<String, Object>();
				rmap.put("rind", ruleId);
				Map temp2 = du.execRule(rmap, ruleMap2);
				// 获取规则执行结果
				Object obj = temp2.get("result");
				logger.info("======>普通规则输出参数：" + obj.toString());
				// 如果不为空，则表明正常执行，则获取规则返回值，否则，抛出异常
				if (null != obj && !"".equals(obj)) {

					relaDatas.put("ruleResult", String.valueOf(obj));
				} else {
					throw new EOSException("CBE005", "根据参数值，计算规则结果失败，错误信息为：" + temp2.get("msg"));
				}
			}

			// 设置相关数据域
			RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
			logger.info("***********************************调用规则【" + processInstId + "】结束**********************************************");
		}

	}

	/**
	 * 单签：singleMode 信用≤10万 保证≤50万 抵押≤100万，计算路由规则
	 * 
	 * @param processInstId
	 *            流程编号
	 * @return
	 * @throws NumberFormatException
	 * @throws WFServiceException
	 * @throws RuleException
	 */
	public static String excuteBcfmRuleAuth(String processInstId) throws WFServiceException, NumberFormatException, RuleException {
		String[] xpath = { "guarType", "authAmt" };
		List<Object> lts = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		String guarType = (String) lts.get(0);// 1-信用，2-优质，3-普通，4-保证
		BigDecimal authAmt = new BigDecimal(lts.get(1) + "");
		String singleMode = "1";// 单双签模式 默认1单签
		String[] gurs = guarType.split(",");
		for (int i = 0; i < gurs.length; i++) {
			if ("1".equals(gurs[i])) {
				if (authAmt.compareTo(new BigDecimal(100000)) > 0) {
					singleMode = "2";
					break;
				}
			} else if ("2".equals(gurs[i])) {
				if (authAmt.compareTo(new BigDecimal(1000000)) > 0) {
					singleMode = "2";
					break;
				}
			} else if ("3".equals(gurs[i])) {
				if (authAmt.compareTo(new BigDecimal(1000000)) > 0) {
					singleMode = "2";
					break;
				}
			} else if ("4".equals(gurs[i])) {
				if (authAmt.compareTo(new BigDecimal(500000)) > 0) {
					singleMode = "2";
					break;
				}
			}

		}

		return singleMode;
	}

	/**
	 * 根据授权规则，计算终批机构，岗位，审批官级别
	 * 
	 * @param processInstId
	 *            流程编号
	 * @param ruleId
	 *            规则id
	 * @return
	 * @throws NumberFormatException
	 * @throws WFServiceException
	 * @throws RuleException
	 */
	public static Map<String, Object> excuteRuleAuth(String processInstId, String ruleId) throws WFServiceException, NumberFormatException, RuleException {

		logger.info("***********************************调用规则【" + processInstId + "】开始**********************************************");
		Map<String, Object> rmap = new HashMap<String, Object>();
		if (null != ruleId && ruleId.indexOf(FlowConstants.BPS_RULEID_SQ) != -1) {
			String[] xpath = { "custId", "wfCreateOrgCode", "productType", "isLow", "guarType", "authAmt", "authAmt_tz" };
			List<Object> lts = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String custId = (String) lts.get(0);// 客户编号
			// 查询授权表
			Map<String, String> temp = new HashMap<String, String>();
			temp.put("orgcode", (String) lts.get(1));// 创建机构
			temp.put("productType", (String) lts.get(2));// 授信品种
			temp.put("isLow", (String) lts.get(3));// 是否低
			temp.put("guarType", (String) lts.get(4) + ",5");// 担保方式

			String authAmt = String.valueOf(lts.get(6));
			if (null == authAmt || "null".equals(authAmt) || "".equals(authAmt)) {
				authAmt = String.valueOf(lts.get(5));
				if (null == authAmt || "null".equals(authAmt) || "".equals(authAmt)) {
					authAmt = "0";
				}
			}
			temp.put("authAmt", authAmt);// 申请额度
			temp.put("authAmt_", String.valueOf(lts.get(5)));// 原有申请额度
			temp.put("authAmt_tz", String.valueOf(lts.get(6)));// 调整合同申请额度
			Map<String, String> ruleMap = RuleProcessUtil.initAuthorizationDatas(temp);
			if (null != ruleMap && ruleMap.size() > 0) {
				rmap.put("rOrglevel", ruleMap.get("authLv"));
				rmap.put("rPositionCd", ruleMap.get("posicode"));
				rmap.put("rPersonLv", ruleMap.get("personLv"));
				rmap.put("rZero", "");// 清空零权限标志
			} else {
				// 无授权配置，默认提示
				rmap.put("errDesc", "未找到授权配置,请联系管理员配置授权规则!");
			}
			logger.info("---------->授权计算：[custId]=" + custId + ",[计算参数]=" + temp.toString() + "[计算结果]=" + rmap.toString());
		}
		logger.info("***********************************调用规则【" + processInstId + "】结束**********************************************");
		return rmap;
	}

	/*
	 * 零授权 在 绵阳商行 不使用 public static Map<String,Object> excuteRuleAuth(String processInstId,String ruleId) throws WFServiceException, NumberFormatException, RuleException{
	 * 
	 * logger.info("***********************************调用规则【"+processInstId+"】开始**********************************************"); Map<String,Object> rmap = new HashMap<String,Object>(); if(null!=ruleId&&ruleId.indexOf(FlowConstants.BPS_RULEID_SQ)!=-1){
	 * 
	 * String[] xpath={"custId","wfCreateOrgCode","productType","isLow","guarType","authAmt","jgFlag","ytFlag","ydFlag"}; List<Object> lts= RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath); String custId = (String)lts.get(0);//客户编号 String orgcode = (String)lts.get(1);//创建机构 String productType =(String)lts.get(2);//授信品种 String isLow = (String)lts.get(3);//是否低 String guarType = (String)lts.get(4)+",5";;//担保方式 String authAmt = String.valueOf(lts.get(5));//申请额度
	 * String jgFlag = (String)lts.get(6);//监管标志 String ytFlag = (String)lts.get(7);//银团标志 String ydFlag = (String)lts.get(8);//异地标志
	 * 
	 * logger.info("---------->授权计算参数：[custId]="+custId+",[orgcode]="+orgcode+",[productType]="+productType+",[isLow]="+isLow+",[guarType]="+ guarType+",[authAmt]="+authAmt+",[jgFlag]="+jgFlag+",[ytFlag]="+ytFlag);
	 * 
	 * //是否零权限 String msgdesc = null; Map<String,Object> map = new HashMap<String,Object>(); map.put("partyNum", custId); map.put("orgcode", orgcode); map.put("jgFlag", jgFlag); map.put("ydFlag", ydFlag); map.put("productType", productType); map.put("ytFlag", ytFlag);
	 * 
	 * //查询集团客户成员编号 Object[] db = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.process.processQuery.queryPartyNumOfGroupM", custId); if(null != db && db.length>0){
	 * 
	 * for (int i = 0; i < db.length; i++) { Object object = db[i]; map.put("partyNum", object); msgdesc = isZeroAuth(map); if(msgdesc!=null){ break; } } //非集团客户 }else{
	 * 
	 * msgdesc = isZeroAuth(map); }
	 * 
	 * //查询授权表 Map<String,String> temp = new HashMap<String,String>(); temp.put("orgcode", orgcode); temp.put("productType", productType); temp.put("isLow", isLow); temp.put("guarType", guarType); temp.put("authAmt", authAmt);
	 * 
	 * if(msgdesc!=null && !"1".equals(isLow)){//零权限,并且不是低
	 * 
	 * rmap.put("rZero", "1:"+msgdesc.substring(0, msgdesc.length()-1));//零权限标志,截取是为了去掉后面的回车符 Map<String,String> ruleMap = RuleProcessUtil.getZeroGrantData(temp); if(null !=ruleMap && ruleMap.size()>0){
	 * 
	 * rmap.put("rOrglevel", ruleMap.get("authLv")); rmap.put("rPositionCd",ruleMap.get("posicode")); rmap.put("rPersonLv",ruleMap.get("personLv")); }else{ //无授权配置，提示 rmap.put("errDesc", "未找到零权限授权配置,请联系管理员配置授权规则!"); }
	 * 
	 * }else{
	 * 
	 * Map<String,String> ruleMap = RuleProcessUtil.initAuthorizationDatas(temp); if(null !=ruleMap && ruleMap.size()>0){
	 * 
	 * rmap.put("rOrglevel", ruleMap.get("authLv")); rmap.put("rPositionCd",ruleMap.get("posicode")); rmap.put("rPersonLv",ruleMap.get("personLv")); rmap.put("rZero", "");//清空零权限标志 }else{ //无授权配置，默认提示 rmap.put("errDesc", "未找到授权配置,请联系管理员配置授权规则!"); } } logger.info("****************》授权表计算结果："+rmap.toString()); } logger.info("***********************************调用规则【"+processInstId+"】结束**********************************************"); return rmap; }
	 */

	/**
	 * 零权限计算
	 * 
	 * @param map
	 * @return
	 * @throws RuleException
	 */
	private static String isZeroAuth(Map<String, Object> map) throws RuleException {
		
		String jgFlag = (String) map.get("jgFlag");
		String ydFlag = (String) map.get("ydFlag");
		String productType = (String) map.get("productType");
		String ytFlag = (String) map.get("ytFlag");

		// 零权限行业判断
		String msgdesc = null;// 零权限描述
		String msg = null;
		if (!"01001003".equals(productType)) {// 不是财园信贷通
			msg = RuleUtil.runRule("RGRANT_0003", map);
			if (null != msg && !"".equals(msg)) {// 属于零权限行业
				logger.info("---------->零权限【行业】校验：" + msg);
				msgdesc = msg;
			}
		}
		/*
		 * //融资平台校验 msg = ru.runRule("RGRANT_0004", map); if(null != msg && !"".equals(msg)){ msgdesc = msg; logger.info("---------->零权限【融资平台】校验："+msg); }
		 */
		// 两年内连续欠息3次以上或累计6次
		msg = RuleUtil.runRule("RGRANT_0005", map);
		if (null != msg && !"".equals(msg)) {
			msgdesc = msg;
			logger.info("---------->零权限【两年欠息2次以上】校验：" + msg);
		}
		/*
		 * //本金逾期或承兑垫款校验 msg = ru.runRule("RGRANT_0006", map); if(null != msg && !"".equals(msg)){ msgdesc = msg; logger.info("---------->零权限【本金逾期或承兑垫款】校验："+msg); }
		 */
		// 监管校验（高）
		if ("1".equals(jgFlag)) {
			msgdesc = "高行业";
			logger.info("---------->零权限【高行业-监管】校验：" + jgFlag);
		}
		// 银团校验
		if ("1".equals(ytFlag)) {
			msgdesc = "银团业务";
			logger.info("---------->零权限【银团】校验：" + jgFlag);
		}
		// 多投校验
		msg = RuleUtil.runRule("RGRANT_0008", map);
		if (null != msg && !"".equals(msg)) {
			msgdesc = msg;
			logger.info("---------->零权限【多头授信】校验：" + msg);
		}
		// 地区校验
		if ("1".equals(ydFlag)) {
			msgdesc = "异地贷款";
			logger.info("---------->零权限【异地贷款】校验：" + ydFlag);
		}

		return msgdesc;
	}

	/**
	 * 获取当前机构的上级机构级别
	 * 
	 * @param parentorglevels
	 * @return
	 */
	public static String getUpOrgLv(String parentorglevels, String processDefName) {

		String parentlv = "";
		if (null != parentorglevels && !"".equals(parentorglevels)) {

			String[] levls = parentorglevels.split(",");
			if (levls.length == 1) {

				parentlv = levls[0];
			} else {

				for (int i = 1; i < levls.length; i++) {
					String string = levls[i];
					if (levls[0].equals(string)) {
						parentlv = string;
						continue;
					} else {

						parentlv = string;
						break;
					}
				}
			}
		}
		return parentlv;
	}

	/**
	 * 获取下一个活动图元
	 * 
	 * @param backOperPositionId
	 *            退回操作岗ID
	 * @param connector
	 *            连接线集
	 * @param processInstId
	 *            流程实例ID
	 * @param processDefID
	 *            流程定义ID
	 * @return
	 * @throws Exception
	 */
	public static WFActivityDefine checkNextActive(String backOperPositionId, List<WFConnector> connector, String processInstId, long processDefID) throws Exception {

		WFActivityDefine def = null;

		// 如果不为空，则表明有退回补充材料操作，并且提交时，必须提交给该岗
		if (null != backOperPositionId && !"".equals(backOperPositionId) && !"null".equals(backOperPositionId)) {

			// 获取节点图元定义信息。
			def = WFDefinitionQueryManagerService.getActivity(processDefID, backOperPositionId);

		} else {
			// 当只有一条连线时
			if (null != connector && connector.size() == 1) {
				// 直接获取下一个活动图元信息
				def = getActiveByConnector(connector.get(0), processDefID);
				// 如果下一个图元是路由，则通过递归，重新获取
				if ("route".equals(def.getType())) {
					List<WFConnector> conns = WFDefinitionQueryManagerService.queryNextTransition(processDefID, def.getId());
					def = checkNextActive(backOperPositionId, conns, processInstId, processDefID);
				}
				// 当包含有多条连线时
			} else {

				if (null != connector && connector.size() > 0) {

					// 循环连线
					for (WFConnector connector2 : connector) {
						// 默认连线
						if (true == connector2.isDefault()) {

							if (null == def) {

								def = getActiveByConnector(connector2, processDefID);
							}

							// 左值右值相等判断
						} else if (("EQ".equals(connector2.getOperator()) || "equal".equals(connector2.getOperator())) && !"".equals(connector2.getLeftValue())) {

							// 获取左值变量对应的value值
							List<Object> strs = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), new String[] { connector2.getLeftValue() });

							// 得到左右值
							String leftValue = String.valueOf(strs.get(0));
							String rightValue = connector2.getRightValue();
							logger.info("------>[checkNextActive]两值相等判断，左值：" + leftValue + ",右值：" + rightValue);
							// 左右相等
							if (rightValue.equals(leftValue)) {

								def = getActiveByConnector(connector2, processDefID);
								// 如果下一个图元是路由，则通过递归，重新获取
								if ("route".equals(def.getType())) {
									List<WFConnector> conns = WFDefinitionQueryManagerService.queryNextTransition(processDefID, def.getId());
									def = checkNextActive(backOperPositionId, conns, processInstId, processDefID);
								} else {
									break;
								}
							}

							// 复杂的表达式
						} else if (StringUtils.isNotEmpty(connector2.getComplexExpression())) {

							// 获取表达式
							String complexExpression = connector2.getComplexExpression();
							logger.info("------>[checkNextActive]表达式判断，表达式：" + complexExpression);
							Map<String, Object> map = new HashMap<String, Object>();
							if (complexExpression.contains("context")) {
								String[] keys = CommonUtil.evalKey(complexExpression);
								List<Object> values = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), keys);
								Map<String, String> context = new HashMap<String, String>();
								for (int i = 0; i < keys.length && i < values.size(); i++) {
									context.put(keys[i], (String) values.get(i));
								}
								map.put("context", context);
							}
							boolean isExpre = CommonUtil.checkComplexExpression(complexExpression, map);
							if (isExpre) {
								def = getActiveByConnector(connector2, processDefID);
								// 如果下一个图元是路由，则通过递归，重新获取
								if ("route".equals(def.getType())) {
									List<WFConnector> conns = WFDefinitionQueryManagerService.queryNextTransition(processDefID, def.getId());
									def = checkNextActive(backOperPositionId, conns, processInstId, processDefID);
								} else {
									break;
								}
							}

						} else {

							throw new Exception("连线表达式：[" + connector2.getLeftValue() + "]" + connector2.getOperator() + "[" + connector2.getRightValue() + "]判断未支持");
						}
					}
				}
			}
		}
		return def;
	}

	/**
	 * 根据连接线，获取活动图元
	 * 
	 * @param userId
	 * @param connectors
	 * @param processDefID
	 * @return
	 * @throws Exception
	 */
	private static WFActivityDefine getActiveByConnector(WFConnector connectors, long processDefID) throws Exception {
		String destActID = connectors.getDestActID();
		WFActivityDefine ad = WFDefinitionQueryManagerService.getActivity(processDefID, destActID);
		return ad;
	}

	/**
	 * 获取下一节点选人树。
	 * 
	 * @param map
	 *            节点定义信息与配置信息
	 * @return
	 * @throws EOSException
	 */
	@Bizlet("获取下一节点选人树")
	public DataObject[] queryUserSelectTree(Map<String, String> map) throws EOSException {
		DataObject[] arr = new DataObject[0];

		if (map.containsKey("createrOrgCd") == false) {
			// 流程发起机构
			return arr;
		}
		String sql = generateQueryUserSql(map, "oracle");// oracle生成oracle数据库可执行sql语句，DB2生成DB2数据库可执行sql语句
		map.put("sql", sql);

		try {
			Object[] users = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.tools.common.common_select", map);

			// 获取部门过滤值
			Object wfDepartmentFilter = map.get("wfDepartmentFilter");
			// 如果有值，则判断根据部门过虑出来的人是否有值，如果没值，则再次拿所在机构下面所有人
			if (null != wfDepartmentFilter && !"".equals(wfDepartmentFilter)) {

				if (null != users && users.length > 0) {

					// 有值，不做任何处理
				} else {
					// 没值，则清空部门过滤，取所在机构的全部人员
					map.put("wfDepartmentFilter", null);
					String all_sql = generateQueryUserSql(map, "oracle");
					map.put("sql", all_sql);
					users = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.tools.common.common_select", map);
				}
			}

			// 下面处理查询结果
			List<DataObject> results = new ArrayList<DataObject>();
			// 存放已处理过的机构
			Set<String> set = new HashSet<String>();

			for (Object x : users) {
				DataObject obj = (DataObject) x;
				// 机构存在，表明处量过，则循环下一条
				if (set.contains(obj.get("ORGCODE"))) {
					continue;

				}
				// 将机构设置到集合中，用于重复判断
				set.add(obj.get("ORGCODE").toString());
				// 创建树节点
				OrgTreeNode node = OrgTreeNode.FACTORY.create();
				node.setNodeId(obj.getString("ORGCODE"));
				node.setNodeType("OrgOrganization");
				node.setNodeName(obj.getString("ORGNAME"));
				node.setIconCls(IconCls.ORGANIZATION);
				node.set("isLeaf", false);
				node.set("expanded", true);
				node.set("orglevel", obj.get("ORGLEVEL"));
				results.add(node);
				// 处理岗位
				List<DataObject> pchildren = new ArrayList<DataObject>();
				Set<String> pset = new HashSet<String>();
				for (Object p : users) {
					DataObject post = (DataObject) p;
					if (pset.contains(post.get("POSICODE"))) {
						continue;
					}
					// 如果当前岗位的机构，与父节点不同，则跳过
					if (post.get("ORGCODE").equals(node.getNodeId()) == false) {
						continue;
					}
					// 将岗位设置到集合中，用于重复判断
					pset.add(post.getString("POSICODE"));
					// 创建岗位节点
					OrgTreeNode pnode = OrgTreeNode.FACTORY.create();
					pnode.setNodeId(post.getString("POSICODE"));
					pnode.setNodeType("Position");
					pnode.setNodeName(post.getString("POSINAME"));
					pnode.setIconCls(IconCls.POSITION);
					pnode.set("isLeaf", false);
					pnode.set("expanded", true);
					pchildren.add(pnode);

					// 处理人员
					List<DataObject> children = new ArrayList<DataObject>();
					for (Object x1 : users) {
						DataObject emp = (DataObject) x1;
						if (emp.get("POSINAME").equals(pnode.getNodeName()) == false) {
							continue;
						}
						// 创建人员节点
						OrgTreeNode user = OrgTreeNode.FACTORY.create();
						user.setNodeId(emp.getString("ORGCODE") + "," + emp.get("USERID"));
						user.setNodeType("OrgEmployee");
						if (null != emp.getString("BUNO") && "1".equals(emp.getString("BUNO"))) {

							user.setNodeName(emp.getString("OPERATORNAME") + "_" + emp.getString("DEPARTNAME"));
						} else {

							user.setNodeName(emp.getString("OPERATORNAME"));
						}
						user.setIconCls(IconCls.EMPLOYEE);
						user.set("isLeaf", false);
						user.set("expanded", true);
						user.set("ORGCODE", emp.get("ORGCODE"));
						user.set("ORGNAME", emp.get("ORGNAME"));
						user.set("DEPARTCODE", emp.get("DEPARTCODE"));
						user.set("DEPARTNAME", emp.get("DEPARTNAME"));
						user.set("POSICODE", emp.get("POSICODE"));
						user.set("POSINAME", emp.get("POSINAME"));
						user.set("USERID", emp.get("USERID"));
						user.set("OPERATORNAME", emp.get("OPERATORNAME"));
						children.add(user);
					}
					pnode.set("children", children.toArray(new DataObject[children.size()]));
				}
				node.set("children", pchildren.toArray(new DataObject[pchildren.size()]));
			}
			return results.toArray(new DataObject[results.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	/**
	 * 根据不同的数据库，生成相应的查询语句
	 * 
	 * @param map
	 *            节点属性值
	 * @param databaseType
	 *            数据库类型
	 * @return
	 * @throws EOSException
	 */
	private String generateQueryUserSql(Map map, String databaseType) throws EOSException {

		StringBuffer sql = new StringBuffer();
		StringBuffer roleids = new StringBuffer();
		String orgcode = "";
		// 岗位级别，1或1,2,3,4
		String orglevel = "";
		// 处理节点机构级别配置，+降级，-升级
		// 处理机构配置
		if (map.containsKey("orgcode")) {
			orgcode = map.get("orgcode").toString();
		}
		// 处理角色配置（暂时不用）
		if (map.containsKey("roles")) {
			String[] roles = map.get("roles").toString().split("\\.");
			for (String role : roles) {
				if (roleids.length() > 0) {
					roleids.append(",");
				}
				roleids.append("'");
				roleids.append(role);
				roleids.append("'");
			}
		}
		// 获取创建流程机构，当存在指定机构sureOrgCd 使用顶替创建机构
		String createorg = map.get("sureOrgCd") + "";
		if (createorg == null || "".equals(createorg) || "null".equals(createorg)) {
			createorg = map.get("createrOrgCd") + "";
		}
		// 获取活动定义ID,查询对应的岗位
		String activityDefineId = map.get("WFActivityDefineId").toString();
		DataObject dataobj = WorkItemMappingDAO.getWorkItempMappingByVersion(map.get("processDefName").toString(), map.get("templateVersion").toString(), activityDefineId);
		String postId = dataobj.getString("postNum");
		orglevel = dataobj.getString("orgLvCd");
		/*
		 * if(null == postId || "".equals(postId)){ throw new EOSException("CBE0004","岗位ID号(活动定义)不能为空"); }
		 */

		if ("DB2".equals(databaseType)) {

			sql.append("WITH orgtmp(orgid,orgname,parentorgid,orglevel,orgcode,buno,orgtype) AS");
			sql.append("(SELECT omorg.orgid,omorg.orgname,omorg.parentorgid,omorg.orglevel,omorg.orgcode,omorg.buno,omorg.orgtype FROM OM_ORGANIZATION omorg WHERE omorg.orgcode ='");
			// 获取跨机构标识
			logger.info("------>跨机构标识：" + map.get("isWfOtherOrg") + "，跨机构号：" + map.get("wfOtherOrgCode"));

			Object isWfOtherOrg = map.get("isWfOtherOrg");
			if (null != isWfOtherOrg && !"".equals(isWfOtherOrg) && "1".equals(isWfOtherOrg)) {

				String wfOtherOrgCode = map.get("wfOtherOrgCode").toString();
				sql.append(wfOtherOrgCode).append("' ").append("union all ");
			} else {

				sql.append(createorg).append("' ").append("union all ");
			}
			sql.append("SELECT ch.orgid,ch.orgname,ch.parentorgid,ch.orglevel,ch.orgcode,ch.buno,ch.orgtype FROM orgtmp par,OM_ORGANIZATION ch WHERE par.parentorgid = ch.orgid) ");
			sql.append("SELECT t.orgid,t.parentorgid,t.buno,t.orgcode as departcode,t.orgname as departname,t.orglevel,t.orgtype,p.posicode,p.posiname,oe.empname operatorname,oe.userid,");
			sql.append("(SELECT  orgcode  FROM  orgtmp WHERE buno='0' AND orglevel='").append(orglevel).append("') as orgcode,");
			sql.append("(SELECT  orgname  FROM  orgtmp WHERE buno='0' AND orglevel='").append(orglevel).append("') as orgname ");
			sql.append("FROM OM_ORGANIZATION t,om_empposition ep,om_position p,om_employee oe,ac_operator ac ");
			sql.append("WHERE t.orgseq LIKE (SELECT '%'||orgid||'%' FROM orgtmp WHERE buno='0' AND orglevel='").append(orglevel).append("') ");
			sql.append("AND t.orglevel='").append(orglevel).append("' AND t.orgid=ep.orgid AND oe.operatorid=ep.empid AND ep.positionid=p.positionid AND oe.userid=ac.userid ");
			sql.append("and ac.status in('running','init')");
			sql.append("and p.posicode in(").append(CommonUtil.formatPosition(postId)).append(") ");
			// 获取过滤部门字段,多部门“,”分隔
			String wfDepartmentFilter = (String) map.get("wfDepartmentFilter");
			// 在值两边增加单引号
			wfDepartmentFilter = CommonUtil.formatDepartment(wfDepartmentFilter);
			if (null != wfDepartmentFilter && !"".equals(wfDepartmentFilter)) {

				sql.append("and t.orgtype in (").append(wfDepartmentFilter).append(") ");
			}
			sql.append("order by t.buno,departname");
		} else {

			Object authAmt = map.get("authAmt");// 授权金额
			Object wfCreateUserId = map.get("wfCreateUserId");// 发起人
			// 获取登陆系统机构类型
			IUserObject user = CommonUtil.getIUserObject();
			Map<String, Object> attmap = user.getAttributes();
			String orgdegree = (String) attmap.get("orgdegree");

			String[] xpath = { "rOrglevel", "rPersonLv", "rPositionCd" };
			List<Object> strs = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(map.get("processInstId").toString()), xpath);
			String rOrglevel = "";
			if (null != strs.get(0)) {

				rOrglevel = String.valueOf(strs.get(0));// 机构权限级别
			}
			String rPersonLv = "";
			if (null != strs.get(1)) {

				rPersonLv = String.valueOf(strs.get(1));// 审批官级别
				// if(null==rPersonLv||"".equals(rPersonLv)||"null".equals(rPersonLv)){

				// rPersonLv = "1";//如果未取到审批级别，默认赋1
				// }
			}
			String rPositionCd = "";
			if (null != strs.get(2)) {

				rPositionCd = String.valueOf(strs.get(2));// 审批岗位
			}
			// 拼接授权计算的下一节点定义ID
			String targetP = "";
			if (null != rOrglevel && !"".equals(rOrglevel) && null != rPositionCd && !"".equals(rPositionCd)) {

				targetP = "P" + rOrglevel + rPositionCd.substring(2);
			}
			sql.append("with sup_orgs as (select * from OM_ORGANIZATION t start with t.orgcode='");
			sql.append(createorg);
			sql.append("' ");
			sql.append("connect by prior t.parentorgid=t.orgid) ");
			sql.append("select distinct s.orgcode,s.orgname,s.orglevel,o.operatorname,o.userid,p.posicode,p.posiname ");
			// 小贷授权过滤
			if (null != authAmt && !"".equals(authAmt) && !"null".equals(authAmt) && "2".equals(orgdegree)) {
				sql.append("from sup_orgs s,om_empposition ep, om_position p, ac_operator o  ,tb_grant_mapping_m m ");
				sql.append("where ep.orgid = s.orgid and ep.positionid = p.positionid and ep.empid = o.operatorid and o.userid=m.user_id ");
				sql.append("and m.max_amt*10000>=").append(authAmt);
				// 如果下一节点与授权计算结果相等，则表明流程已到权限岗位，需要过滤权限
			} else if (targetP.equals(activityDefineId)) {

				sql.append("from sup_orgs s,om_empposition ep, om_position p, ac_operator o,om_employee e ");
				sql.append("where ep.orgid = s.orgid and ep.positionid = p.positionid and ep.empid = o.operatorid and o.userid=e.userid ");
				if (null != rPersonLv && !"".equals(rPersonLv) && !"null".equals(rPersonLv)) {

					sql.append("and e.emplevel<=").append(rPersonLv);
				}

			} else {
				sql.append("from sup_orgs s,om_empposition ep, om_position p, ac_operator o ");
				sql.append("where ep.orgid = s.orgid and ep.positionid = p.positionid and ep.empid = o.operatorid ");

			}
			sql.append(" and s.orglevel = '").append(orglevel).append("' ");
			sql.append(" and p.posicode = ").append(CommonUtil.formatPosition(postId));
			sql.append(" and o.status in('running','init','unlock')");
			sql.append(" and o.userid<>'").append(wfCreateUserId).append("'");
			sql.append(" order by s.orglevel,o.operatorname ");

		}
		// 打印查询用户sql语句
		System.out.println("====>查询提交审批人员sql语句：" + sql.toString());
		logger.info("------>查询提交审批人员sql语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 根据岗位ID,查询其下的参与人员
	 * 
	 * @param map
	 * @param activityDefId
	 * @return
	 * @throws EOSException
	 */
	@Bizlet("根据岗位ID,查询其下的参与人员")
	public Object[] queryBpsUsersByPosition(Map<String, Object> map, String activityDefId, PageCond page) throws EOSException {

		// 获取岗位对象集合
		Object[] ac_list = (Object[]) map.get("WFActivityDefineId");
		// 岗位对象
		Map<String, String> ptmap = new HashMap<String, String>();
		// 根据岗位ID，从集合中获取岗位对象，进而从中获取对应的级别
		for (int i = 0; i < ac_list.length; i++) {
			Map<String, String> temp = (Map<String, String>) ac_list[i];
			String aDefId = temp.get("activityDefineId");
			if (null != aDefId && aDefId.equals(activityDefId)) {
				ptmap.put("orglevel", temp.get("orglevel"));
				break;
			} else {
				continue;
			}

		}
		ptmap.put("WFActivityDefineId", activityDefId);
		ptmap.put("createrOrgCd", (String) map.get("createrOrgCd"));
		ptmap.put("processDefName", (String) map.get("processDefName"));
		ptmap.put("templateVersion", (String) map.get("templateVersion"));
		ptmap.put("wfDepartmentFilter", (String) map.get("wfDepartmentFilter"));
		ptmap.put("authAmt", (String) map.get("authAmt"));
		ptmap.put("rPersonlevel", (String) map.get("rPersonlevel"));
		ptmap.put("rOrglevel", (String) map.get("rOrglevel"));
		ptmap.put("wfCreateUserId", (String) map.get("wfCreateUserId"));
		ptmap.put("processInstId", (String) map.get("processInstId"));
		// 当前运行节点
		ptmap.put("activityDefId", (String) map.get("activityDefId"));

		if (map.containsKey("createrOrgCd") == false) {
			// 流程发起机构
			return null;
		}

		String sql = generateQueryUserSql(ptmap, "oracle");// oracle生成oracle数据库可执行sql语句，DB2生成DB2数据库可执行sql语句
		ptmap.put("sql", sql);
		Object[] users = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.utp.tools.common.common_select", ptmap);

		return users;

	}

	/**
	 * 临时保存审批意见
	 * 
	 * @param it
	 *            工作项信息
	 * @return
	 * @throws EOSException
	 */
	public void updateSingelWork(Map<String, String> it) throws EOSException {
		WorkItemInstanceDAO.insertWorkItemInstance(it);
	}

	/**
	 * 根据机构级别获取起始活动定义ID
	 * 
	 * @return
	 * @throws EOSException
	 */
	@Bizlet("根据机构级别获取起始活动定义ID")
	public Map<String, String> getActivityDefIdWithOrgLv(String processDefName, String orglv) throws EOSException {

		Map<String, String> rp = new HashMap<String, String>();

		Object[] data = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bps.dataset.query.queryStartPsotion", processDefName);
		if (null != data && data.length > 0) {
			rp = (Map) data[0];
		} else {
			throw new EOSException("没有找到流程模板起始岗位");
		}

		return rp;
	}

	/**
	 * 回调创建流程后的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws Exception
	 */
	@Bizlet("回调创建流程后的方法")
	public static void callbankAfterCreateMetchod(long processInstId, String processDefName) throws Exception {

		// 获取流程实例ID
		WFActivityInst ait = ActivityInstManagerService.getCurrentActivityInstByProcessInstID(processInstId);
		String activityDefId = ait.getActivityDefID();
		// 获取流程节点配置信息
		DataObject data = WorkItemMappingDAO.getWorkItemMapping(processDefName, activityDefId);
		String className = data.getString("doUrl");
		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeAfterCreateFlow(String.valueOf(processInstId), null);
		}

	}

	/**
	 * 回调提交流程前的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bizlet("回调提交流程前的方法")
	public static void callbankBeforeSubmitMetchod(String className, String processInstId, Map workitem) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeBeforeSubmit(processInstId, workitem);
		}
	}

	/**
	 * 回调流程结束前的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bizlet("回调流程结束前的方法")
	public static void callbankBeforeTerminateMetchod(String className, String processInstId, Map workitem) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeBeforeTerminate(processInstId, workitem);
		}
	}

	/**
	 * 回调流程退回前的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bizlet("回调流程退回前的方法")
	public static void callbankBeforeUntreadMetchod(String className, String processInstId, Map workitem) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeBeforeUntread(processInstId, workitem);
		}
	}

	/**
	 * 回调流程否决前的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bizlet("回调流程否决前的方法")
	public static void callbankBeforeRejectMetchod(String className, String processInstId, Map workitem) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeBeforeReject(processInstId, workitem);
		}
	}

	/**
	 * 回调撤销流程后的方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bizlet("回调撤销流程后的方法")
	public static void callbankAfterAbortMetchod(String processInstId, String processDefName, String activityDefId, String templateVersion) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataObject wim = WorkItemMappingDAO.getWorkItempMappingByVersion(processDefName, templateVersion, activityDefId);
		String className = wim.getString("doUrl");
		if (null != className && !"".equals(className)) {

			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.executeAfterAbort(processInstId, null);
		}
	}

	/**
	 * 校验数据是否完整正确方法
	 * 
	 * @param className
	 *            类名称
	 * @param processInstId
	 *            流程实例ID号
	 * @throws Exception
	 */
	@Bizlet("校验数据是否完整正确方法")
	public static Map<String, String> callbankDataCheck(String processInstId, String className, String conclusion, String activityDefId) throws Exception {
		if (null != className && !"".equals(className) && !"null".equals(className)) {
			IBIZProcess bizpro = (IBIZProcess) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			Map<String, Object> workitem = new HashMap<String, Object>();
			workitem.put("conclusion", conclusion);
			workitem.put("activityDefId", activityDefId);
			Map<String, String> checkmap = bizpro.executeDataCheck(processInstId, workitem);
			if (checkmap != null) {
				return checkmap;
			}
		}
		return null;
	}

	/**
	 * 回调结束业务方法，将业务置为失效
	 * 
	 * @param processId
	 * @param bizId
	 * @param processDefName
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Bizlet("回调结束业务方法，将业务置为失效")
	public static void callbankWhenStopFlow(String processDefName, String processId, String bizId) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		logger.info("----->终止的流程模板是：" + processDefName + ",流程实例编号：" + processId + ",业务主键：" + bizId);
		Map map = FlowConstants.getBusMap();
		// 获取业务类型
		String[] flowTypeCds = processDefName.split("\\.");
		int len = 0;
		// 如果是客户模块或评级违约认定重生或担保客户贷后检查，下标减1
		if (processDefName.indexOf("csm") != -1) {

			len = flowTypeCds.length - 1;
		} else {

			len = flowTypeCds.length - 2;
		}

		String flowTypeCd = flowTypeCds[len];
		// 获取回调业务类路径
		String className = (String) map.get(flowTypeCd);
		logger.info("----->获取到的业务回调路径：" + className + ",key:" + flowTypeCd);
		if (null != className && !"".equals(className)) {
			IBusiness bizpro = (IBusiness) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
			bizpro.updateBizDataWhenStopFlow(processId, bizId);
		}
	}

	/**
	 * 根据传入的流程模板编号，获取对应的流程定义名称以及实例描述
	 * 
	 * @param flowNum
	 *            流程编号
	 * @return
	 * @throws Exception
	 */
	@Bizlet("根据传入的流程模板编号，获取对应的流程定义名称以及实例描述")
	public static String[] getWorkFlowTemplate(String templateName, String modelType) throws Exception {

		String[] flows = new String[2];
		if (null == templateName || "".equals(templateName)) {
			String legorg = GitUtil.getLegorg();
			throw new Exception("未获取到对应法人的[" + legorg + "]流程模板，请联系管理员！" + "[" + templateName + "]");
		}
		// 获取流程模板配置信息
		DataObject data = ProcessInstMappingDAO.getProcessMappingByType(templateName, modelType);
		flows[0] = data.getString("templateName");
		flows[1] = data.getString("productType");
		// flows[0] = ConfigurationUtil.getContributionConfig(FlowConstants.CONTRIBUTION_NAME,
		// FlowConstants.BPS_MODULE_NAME_TEMPLATE,flowNum,FlowConstants.BPS_KEY_NAME);

		// flows[1] = ConfigurationUtil.getContributionConfig(FlowConstants.CONTRIBUTION_NAME,
		// FlowConstants.BPS_MODULE_NAME_TEMPLATE,flowNum,FlowConstants.BPS_KEY_DESC);

		return flows;
	}

	/**
	 * 判断是否是直属支行
	 * 
	 * @param wfCreateOrgCode
	 *            发起机构
	 * @return 直属标志
	 * @throws RuleException
	 */
	@Bizlet("判断是否是直属支行")
	public static String isBranchUnderBank(String wfCreateOrgCode) throws RuleException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wfCreateOrgCode", wfCreateOrgCode);
		String msg = RuleUtil.runRule("PUB_0029", map);
		if (null != msg && !"".equals(msg)) {
			return "0";// 不是直属支行
		} else {
			return "1";// 是直属支行
		}
	}

	/**
	 * 获取业务条线和是否包含贸易融资业务
	 * 
	 * @param bizId
	 *            业务编号
	 * @return bizLine 1 公司业务部，2 小企业信贷中心 ，3 金融市场部，4 国际业务部 ，5 个人贷款中心
	 */
	public Map<String, String> getProductInfo(String bizId) {
		String bizLine = "";// 业务条线
		String errDesc = "";// 提示信息
		String errCode = "000000";// 合规代码
		String isMyrz = "";// 是否包含贸易融资业务
		Map<String, String> map = new HashMap<String, String>();
		String bizLine_back = "";// 记录上次业务条线
		String bizLines = "";
		// 业务明细信息
		Object[] bizDetails = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.process.processQuery.queryProductDepentInfo", bizId);
		if (null != bizDetails && bizDetails.length > 0) {
			for (int i = 0; i < bizDetails.length; i++) {
				Map mp = (Map) bizDetails[i];
				bizLine = mp.get("PRODUCTDEP") + "";
				if (null == bizLine || "".equals(bizLine)) {
					errCode = "BPS_E0001";
					errDesc = "贷款产品未配置业务条线，请联系管理员配置！";
					break;
				} else {
					if ("4".equals(bizLine)) {// 包含贸易融资业务
						isMyrz = "1";
					}
				}

				if ("".equals(bizLine_back)) {
					bizLine_back = bizLine;
					bizLines = bizLine;
				} else {
					if (!bizLine_back.equals(bizLine)) {
						bizLines += "," + bizLine;
					}
				}
			}

			// 判断业务条线发起情况
			String[] str = bizLines.split(",");
			if (str.length > 1) {
				if (bizLines.indexOf("2") > -1) {
					// errCode = "BPS_E0001"; //业务提交时 不再校验综合授信里有小微条线业务
					// errDesc = "该笔申请包含小企业条线业务，只允许做单笔单批申请！";
				}

				if (bizLines.indexOf("3") > -1) {
					// errCode = "BPS_E0001";
					// errDesc = "该笔申请包含金融市场部条线业务，只允许做单笔单批申请！";
				}
			}
		} else {
			errCode = "BPS_E0001";
			errDesc = "未获取到该申请[" + bizId + "]的业务明细信息，请检查！";
		}

		map.put("isMyrz", isMyrz);
		map.put("errCode", errCode);
		map.put("errDesc", errDesc);
		map.put("bizLine", bizLines);
		return map;
	}

	/**
	 * 获取合同签约流程是否包含外币及非贸易融资业务
	 */
	public String getContractInfo(String bizId) {
		String currency_cd = null;
		String isMyrz = null;
		String isCurrency = "0";

		Connection conn = DBUtil.getConnection();
		ResultSet rso = null;
		Statement statement = null;
		String userOpersql = "select a.CURRENCY_CD, b.PRODUCT_DEPARTMENT from tb_con_contract_info a left join tb_sys_product b on a.product_type = b.product_cd where a.CONTRACT_ID='" + bizId + "'";
		try {
			statement = conn.createStatement();
			rso = statement.executeQuery(userOpersql);
			while (rso.next()) {
				currency_cd = rso.getString("CURRENCY_CD");
				isMyrz = rso.getString("PRODUCT_DEPARTMENT");
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, new Statement[] { statement }, new ResultSet[] { rso });
		}
		if (currency_cd != null && isMyrz != null && !"CNY".equals(currency_cd) && !"4".equals(isMyrz)) {// 外币及非贸易融资业务
			isCurrency = "1";
		}
		return isCurrency;
	}
}
