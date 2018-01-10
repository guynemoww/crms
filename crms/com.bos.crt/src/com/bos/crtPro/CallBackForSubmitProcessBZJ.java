package com.bos.crtPro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.accInfo.ContractSub;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

/**
 * 回调逻辑：提交流程，更新业务表数据 01-未提交; 02-审批中; 03-结束; 04-已删除
 * 
 * */
public class CallBackForSubmitProcessBZJ implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcessBZJ.class);

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// 撤销流程 业务状态更新为06
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String contractId = (String) list.get(0);
			if (null == contractId || "".equals(contractId)) {
				logger.info("合同流程撤销时，bizID为空！");
				throw new EOSException("合同流程撤销时，bizID为空！");
			}

			logger.info("------3231------>合同流程撤销，开始业务状态为06------bizId=" + contractId + "------->开始!");
			if (null == contractId || "".equals(contractId)) {
				throw new EOSException("合同ID为空");
			}

			// 业务合同基本信息
			DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			tbConContractInfo.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", tbConContractInfo);
			// 合同利率信息
			DataObject tbConLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConLoanrate");
			tbConLoanrate.set("contractId", contractId);
			DatabaseUtil.expandEntityByTemplate("default", tbConLoanrate, tbConLoanrate);
			// 用款计划
			DataObject tbConPayoutPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConPayoutPlan");
			tbConPayoutPlan.set("contractDetailId", contractId);
			DataObject[] tbConPayoutPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConPayoutPlan);
			// 还款计划
			DataObject tbConRepayPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConRepayPlan");
			tbConRepayPlan.set("contractId", contractId);
			DataObject[] tbConRepayPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConRepayPlan);
			// 账户信息
			DataObject tbConZh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
			tbConZh.set("contractId", contractId);
			DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", tbConZh);
			// 费用信息
			DataObject tbConFee = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFee");
			tbConFee.set("contractDetailId", contractId);
			DataObject[] tbConFees = DatabaseUtil.queryEntitiesByTemplate("default", tbConFee);
			// 标志信息
			DataObject tbConFlagInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFlagInfo");
			tbConFlagInfo.set("contractId", contractId);
			DatabaseUtil.expandEntityByTemplate("default", tbConFlagInfo, tbConFlagInfo);
			// 附属信息
			DataObject tbConAttachedInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConAttachedInfo");
			tbConAttachedInfo.set("contractId", contractId);
			DatabaseUtil.expandEntityByTemplate("default", tbConAttachedInfo, tbConAttachedInfo);
			// 贸易合同信息 --20151016----
			DataObject myht = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizMyhtxxApply");
			myht.set("amountDetailId", contractId);// 贸易合同信息就是存合同id
			DataObject[] myhts = DatabaseUtil.queryEntitiesByTemplate("default", myht);

			// 20160607 二期需求
			// 通知和文书送达
			DataObject noticeAddrs = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConNoticeAddrs");
			noticeAddrs.set("contractId", contractId);
			DatabaseUtil.expandEntityByTemplate("default", noticeAddrs, noticeAddrs);
			
			// 明细信息
			DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
			productInfo.set("productCd", tbConContractInfo.get("productType"));
			DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);

			String entityName = productInfo.getString("entityName");
			// com.bos.dataset.biz.TbBizLdzjApply
			// com.bos.dataset.crt.TbConLdzj
			entityName = entityName.replace("biz", "crt");
			entityName = entityName.replace("Biz", "Con");
			entityName = entityName.replace("Apply", "");
			DataObject productDetail = DataObjectUtil.createDataObject(entityName);
			productDetail.set("contractId", contractId);
			DatabaseUtil.expandEntityByTemplate("default", productDetail, productDetail);
			
			// 合同利率信息
			if (null != tbConLoanrate.get("loanrateId")) {
				tbConLoanrate.set("contractId", contractId);
				DatabaseUtil.deleteEntity("default", tbConLoanrate);
			}
			// 标志信息
			if (null != tbConFlagInfo.get("flagId")) {
				tbConFlagInfo.set("contractId", contractId);
				DatabaseUtil.deleteEntity("default", tbConFlagInfo);
			}
			// 通知书文书送达
			if (null != noticeAddrs.get("uuid")) {
				noticeAddrs.set("contractId", contractId);
				DatabaseUtil.deleteEntity("default", noticeAddrs);
			}
			// 附属信息
			if (null != tbConAttachedInfo.get("attached")) {
				tbConAttachedInfo.set("contractId", contractId);
				DatabaseUtil.deleteEntity("default", tbConAttachedInfo);
			}
			// 用款计划
			for (int i = 0; i <= tbConPayoutPlans.length - 1; i++) {
				tbConPayoutPlan = tbConPayoutPlans[i];
				if (null != tbConPayoutPlan.get("payoutPlanId")) {
					tbConPayoutPlan.set("contractDetailId", contractId);
					DatabaseUtil.deleteEntity("default", tbConPayoutPlan);
				}
			}
			// 还款计划
			for (int i = 0; i <= tbConRepayPlans.length - 1; i++) {
				tbConRepayPlan = tbConRepayPlans[i];
				if (null != tbConRepayPlan.get("repayPlanId")) {
					tbConRepayPlan.set("contractId", contractId);
					DatabaseUtil.deleteEntity("default", tbConRepayPlan);
				}
			}
			// 账户信息
			for (int i = 0; i <= tbConZhs.length - 1; i++) {
				tbConZh = tbConZhs[i];
				if (null != tbConZh.get("id")) {
					tbConZh.set("contractId", contractId);
					DatabaseUtil.deleteEntity("default", tbConZh);
				}
			}
			// 费用信息
			for (int i = 0; i <= tbConFees.length - 1; i++) {
				tbConFee = tbConFees[i];
				if (null != tbConFee.get("feeId")) {
					tbConFee.set("contractDetailId", contractId);
					DatabaseUtil.deleteEntity("default", tbConFee);
				}
			}
			// 贸易合同信息-----20151016--
			for (int i = 0; i <= myhts.length - 1; i++) {
				myht = myhts[i];
				if (null != myht.get("htId")) {
					myht.set("amountDetailId", contractId);
					DatabaseUtil.deleteEntity("default", myht);
				}
			}

			// 明细信息
			if (null != productDetail.get("applyDetailId")) {
				productDetail.set("contractId", contractId);
				DatabaseUtil.deleteEntity("default", productDetail);
			}
			
			// 担保合同信息 com.bos.dataset.crt.TbConSubcontractRel
			DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
			conRel.set("contractId", contractId);
			DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
			for (int i = 0; i < conRels.length; i++) {
				conRel = conRels[i];
				//删除担保合同信息表
				DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
				subCon.set("subcontractId", conRel.getString("subcontractId"));
				DatabaseUtil.deleteEntity("default", subCon); 
				
				//担保合同与押品关系信息表 
				DataObject conSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
				conSubGrtRel.set("subcontractId", conRel.getString("subcontractId"));
				DatabaseUtil.deleteEntity("default", conSubGrtRel); 
				
				//删除担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
				DataObject bizSubRel= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
				bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
				DatabaseUtil.deleteEntity("default", bizSubRel);
				
				//删除担保合同信息与贷款合同信息关系表
				DatabaseUtil.deleteEntity("default", conRel);
				
			}
			// <!--修改结束--!>
			String entityName1 = "com.bos.dataset.crt.TbConCreditInfo";
			DataObject con = DataObjectUtil.createDataObject(entityName1);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			if (null == con.get("partyId")) {
				entityName1 = "com.bos.dataset.crt.TbConContractInfo";
				con = DataObjectUtil.createDataObject(entityName1);
			}
			con.set("contractId", contractId);
			con.set("conStatus", "06");
			DatabaseUtil.updateEntity("default", con);
			logger.info("------3231------>合同流程撤销，开始业务状态为06------bizId=" + contractId + "------>结束!");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>客户评级流程提交状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
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
		// 合同调整流程提交 业务状态更新为02
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String contractId = (String) list.get(0);
			if (null == contractId || "".equals(contractId)) {
				logger.info("合同调整流程提交时，bizID为空！");
				throw new EOSException("合同调整流程提交时，bizID为空！");
			}

			logger.info("------3231------>合同调整流程提交，开始业务状态为02------bizId=" + contractId + "------->开始!");
			if (null == contractId || "".equals(contractId)) {
				throw new EOSException("合同ID为空");
			}
			String entityName = "com.bos.dataset.crt.TbConCreditInfo";
			DataObject con = DataObjectUtil.createDataObject(entityName);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			if (null == con.get("partyId")) {
				entityName = "com.bos.dataset.crt.TbConContractInfo";
				con = DataObjectUtil.createDataObject(entityName);
			}
			con.set("contractId", contractId);
			con.set("conStatus", "02");
			DatabaseUtil.updateEntity("default", con);
			logger.info("------3231------>合同调整流程提交，开始业务状态为02------bizId=" + contractId + "------>结束!");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("合同调整流程提交时出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("合同调整流程提交时出错");
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
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			String conFlagString = "cre";// 标志区分合同和综合授信协议 con-合同 cre-综合授信协议
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String contractId = (String) list.get(0);
			if (null == contractId || "".equals(contractId)) {
				logger.info("------3231------>合同申请ID为空！");
				throw new EOSException("------3231------>合同申请ID为空！");
			}

			String entityName = "com.bos.dataset.crt.TbConCreditInfo";
			DataObject con = DataObjectUtil.createDataObject(entityName);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			if (null == con.get("partyId")) {
				conFlagString = "con";
				entityName = "com.bos.dataset.crt.TbConContractInfo";
				con = DataObjectUtil.createDataObject(entityName);
				con.set("contractId", contractId);
				DatabaseUtil.expandEntity("default", con);
			}
			RuleService rs = new RuleService();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("contractId", contractId);

			if (conFlagString.equals("cre")) {// 综合授信协议校验
				// 协议基本信息保存校验
				List<MessageObj> msgList = rs.runRule("RCON_0008", paramMap);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 附属信息校验
				msgList = rs.runRule("RCON_0023", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 抵质押合同必须有基本信息
				msgList = rs.runRule("RCON_0015", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有抵质押合同，必须有抵押物
				msgList = rs.runRule("RCON_0016", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 所有担保合同下担保物的本次担保金额之和都必须大于等于担保合同金额
				msgList = rs.runRule("RCON_0037", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
				msgList = rs.runRule("RCON_0038", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				/*
				 * //保证 注释原因：综合授信协议勾选保证，可能是综合授信添加了信用保险 msgList =
				 * rs.runRule("RCON_0017", paramMap); msg = convertMsg(msgList);
				 * if(!"true".equals(msg)){ //校验不成功返回校验失败结果
				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
				 * //抵押合同 msgList = rs.runRule("RCON_0018", paramMap); msg =
				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
				 * //质押合同 msgList = rs.runRule("RCON_0019", paramMap); msg =
				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
				 */
			} else if (conFlagString.equals("con")) {// 合同校验
				String productType = (String) con.get("productType");
				DataObject product = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
				product.set("productCd", productType);
				DatabaseUtil.expandEntityByTemplate("default", product, product);
				if (null == productType || "".equals(productType)) {
					logger.info("------3231------>未查出贷种相关信息！");
					throw new EOSException("------3231------>未查出贷种相关信息！");
				}
				String tableName = (String) product.get("tableName");
				tableName = tableName.replace("biz", "con");
				tableName = tableName.replace("_apply", "");
				String amountDetailId = (String) con.get("amountDetailId");
				paramMap.put("tableName", tableName);
				paramMap.put("amountDetailId", amountDetailId);

				// 合同基本信息保存校验
				List<MessageObj> msgList = rs.runRule("RCON_0003", paramMap);
				String msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 明细信息保存校验
				msgList = rs.runRule("RCON_0004", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 分期贷款利率不能为0---20160329
				msgList = rs.runRule("RCON_0051", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 合同账户信息---放款账户
				msgList = rs.runRule("RCON_0005", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 合同账户信息---还款账户
				msgList = rs.runRule("RCON_0020", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 合同账户信息---结算账户
				msgList = rs.runRule("RCON_0021", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 合同账户信息---应收账款回款专户
				msgList = rs.runRule("RCON_0022", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

				// 放款账户和还款账户必须一样
				msgList = rs.runRule("RCON_0036", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

				// 保证合同
				msgList = rs.runRule("RCON_0009", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 抵押合同
				msgList = rs.runRule("RCON_0010", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 质押合同
				msgList = rs.runRule("RCON_0011", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 保证金
				msgList = rs.runRule("RCON_0012", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 担保金额和合同金额比较
				msgList = rs.runRule("RCON_0014", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 从合同完整性
				/*
				 * msgList = rs.runRule("RCON_0013", paramMap); msg =
				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
				 */
				// 合同还款计划信息-14还款方式
				// if(!productType.startsWith("03001")){
				msgList = rs.runRule("RCON_0006", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// }

				// 抵质押合同必须有基本信息
				msgList = rs.runRule("RCON_0015", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有抵质押合同，必须有抵押物
				msgList = rs.runRule("RCON_0016", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

				// -------------20151015----------add 合同调整校验
				// 有最高额保证合同，从合同金额必须小于等于所有关联保证人担保金额
				msgList = rs.runRule("RCON_0028", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有抵押合同，从合同金额必须小于等于所有关联抵押物担保金额
				msgList = rs.runRule("RCON_0029", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有质押合同，从合同金额必须小于等于所有关联质押物担保金额
				msgList = rs.runRule("RCON_0030", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有保证金协议，从合同金额必须小于等于所有关联保证金担保金额
				/*msgList = rs.runRule("RCON_0031", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}*/
				// 有保证合同，但是没有勾保证
				msgList = rs.runRule("RCON_0032", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有抵押合同，但是没有勾抵押
				msgList = rs.runRule("RCON_0033", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有质押合同，但是没有勾质押
				msgList = rs.runRule("RCON_0034", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 有保证金协议，但是没有勾保证金
				msgList = rs.runRule("RCON_0035", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 所有担保合同下担保物的本次担保金额之和都必须大于等于担保合同金额
				msgList = rs.runRule("RCON_0037", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
				msgList = rs.runRule("RCON_0038", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 所有担保合同下保证金的校验 :一个保证金协议金额必须小于等于关联的保证金金额
				/*msgList = rs.runRule("RCON_0041", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}*/
				
				//校验保证金追加金额
				msgList = rs.runRule("APPAMT_0001", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

//				// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
//				msgList = rs.runRule("RCON_0057", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
				
				//如果担保方式存在保证+保证金的组合担保方式，业务品种还是银行承兑汇票（'01008001','01008010','01008002'），保函业务（'01009001','01009002'），则保证不用校验金额比担保合同金额大，仅需校验全部担保金额比贷款金额大即可
				DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
				tbConContractInfo.set("contractId", paramMap.get("contractId"));
				DatabaseUtil.expandEntity("default", tbConContractInfo);
				if("01008001".equals(tbConContractInfo.getString("productType")) || "01008010".equals(tbConContractInfo.getString("productType"))
						|| "01008002".equals(tbConContractInfo.getString("productType")) || "01009001".equals(tbConContractInfo.getString("productType")) ||
						"01009002".equals(tbConContractInfo.getString("productType"))){
					//判断担保方式是否存在保证+保证金的组合担保方式
					String guaType = tbConContractInfo.getString("guarantyType");
					if(guaType.indexOf("04") != -1 && guaType.indexOf("05") != -1){
						
						
					}else{
						// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
						msgList = rs.runRule("RCON_0057", paramMap);
						msg = convertMsg(msgList);
						if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
							resultMap.put("errorNum", "2");
							resultMap.put("errorCode", "2");
							resultMap.put("errorContent", msg);
							return resultMap;
						}
					}
				}else{
					// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
					msgList = rs.runRule("RCON_0057", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
				}
				
				// 担保合同止期必须大于主合同起期
				msgList = rs.runRule("RCON_0062", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 保证金协议金额之和必须大于等于合同金额*合同明细的保证金比例
				msgList = rs.runRule("RCON_0045", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

				// 保证金协议基本信息中的“保证金类型”应与“保证金关联信息”中的“保证金类型”保持一致 add 20151217
				msgList = rs.runRule("RCON_0049", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// bug12533 提前还款基数输入大于合同金额 add 20160720
				msgList = rs.runRule("RCON_0061", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// bug12598 提前还款最低金额输入大于合同金额 add 20160725
				msgList = rs.runRule("RCON_0063", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

				ContractSub cs = new ContractSub();
				String csMsg = cs.checkMaxSub(contractId);
				if ((null != csMsg) && (!"".equals(csMsg))) {
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", csMsg);
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
