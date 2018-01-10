package com.bos.crtPro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.conInfo.SubProcess;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ecds.client.ECDSClient;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.workflow.api.WFServiceException;
import commonj.sdo.DataObject;

/**
 * 回调逻辑：结束流程，更新业务表数据 01-未提交; 02-审批中; 03-结束; 04-已删除
 */
@SuppressWarnings("rawtypes")
public class CallBackForEndProcessBZJ implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForEndProcessBZJ.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizId" };// 获取相关数据的数组
		// String conclusion = (String) workitem.get("conclusion");// 结论
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (WFServiceException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		// 获取贷款id
		String contractId = (String) list.get(0);
		if (null == contractId || "".equals(contractId)) {
			logger.info("合同ID为空！");
			throw new EOSException("合同ID为空！");
		}
		String entityName = "com.bos.dataset.crt.TbConCreditInfo";
		DataObject con = DataObjectUtil.createDataObject(entityName);
		con.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", con);
		if (StringUtils.isEmpty(con.getString("partyId"))) {
			entityName = "com.bos.dataset.crt.TbConContractInfo";
			con = DataObjectUtil.createDataObject(entityName);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
		}

		// 更新逻辑
		if (!"06".equals(con.get("conStatus"))) {
			logger.info("流程结束，开始处理业务数据------bizId=" + contractId + "------->开始!");
			SubProcess sp = new SubProcess();
			sp.submitPro(contractId);
		} else {
			throw new EOSException("合同状态错误！");
		}

		try {
			DataObject subcontractRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
			subcontractRel.set("contractId", contractId);
			DataObject[] subcontractRels = DatabaseUtil.queryEntitiesByTemplate("default", subcontractRel);
			for (int i = 0; i < subcontractRels.length; i++) {
				DataObject subcontractRel_temp = subcontractRels[i];
				String subcontractId = subcontractRel_temp.getString("subcontractId");
				if (StringUtils.isNotEmpty(subcontractId)) {
					DataObject subcontract = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");// 担保合同信息
					DataObject tbConSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");// 担保合同与押品关系
					DataObject tbGrtMargin = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMargin");// 押品保证金
					subcontract.set("subcontractId", subcontractId);
					DatabaseUtil.expandEntityByTemplate("default", subcontract, subcontract);// 担保合同信息
					tbConSubGrtRel.set("subcontractId", subcontractId);
					DatabaseUtil.expandEntityByTemplate("default", tbConSubGrtRel, tbConSubGrtRel);// 担保合同与押品关系
					tbGrtMargin.set("suretyId", tbConSubGrtRel.getString("suretyId"));
					DatabaseUtil.expandEntityByTemplate("default", tbGrtMargin, tbGrtMargin);// 押品保证金

					Object[] arrays = null;
					BigDecimal appendAmt = BigDecimal.ZERO;
					// 补足保证金修改保证金金额，保证金比例。
					// 追加标志1是0否
					if ("1".equals(tbGrtMargin.getString("appendFlag")) && null != tbGrtMargin.getString("appendAmt") && null != tbGrtMargin.getString("marginAccount")) {

						// 校验保证金账户可用余额大于追加金额
						String marginAccount = tbGrtMargin.getString("marginAccount");
						BigDecimal appendAmt_1 = new BigDecimal(tbGrtMargin.getString("appendAmt"));
						compareBondAmt(marginAccount, appendAmt_1);

						DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
						tbLoanInfo.set("contractId", contractId);
						tbLoanInfo.set("loanStatus", "03");// 已生效放款
						DataObject[] loanInfos = DatabaseUtil.queryEntitiesByTemplate("default", tbLoanInfo); // 查询数据
						if (null != loanInfos && loanInfos.length != 0) {
							for (int j = 0; j < loanInfos.length; j++) {
								tbLoanInfo = loanInfos[j];
								// 查询已生效的票据总金额
								HashMap<String, String> paraMap = new HashMap<String, String>();
								paraMap.put("loanId", tbLoanInfo.getString("loanId"));
								arrays = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryHPLoanAmt", paraMap);
								if (null != arrays && arrays.length != 0) {
									DataObject tempData = (DataObject) arrays[0];
									if (null == tempData.get("SUMMARY_AMT")) {
										throw new EOSException("已生效的票据总金额为空");
									} else {
										BigDecimal countAmt = (BigDecimal) tempData.get("SUMMARY_AMT");// 票据总金额
										BigDecimal amt = subcontract.getBigDecimal("bzjje");// 追加前

										// added 2017/11/14
										BigDecimal bzjjeTotal = (BigDecimal) tempData.get("BZJJE");// 所有有效票据对应的保证金金额总和
										// 追加生效前要判断追加流程中是否有未用退回---判断依据：所有有效票据对应的保证金金额总和必须要等于担保合同的保证金金额
										if (null != amt && null !=bzjjeTotal && amt.compareTo(bzjjeTotal) != 0) {
											throw new EOSException("追加的过程中发生了票据未用退回，请撤销该流程，重新发起保证金追加！");
										}

										appendAmt = amt.add(tbGrtMargin.getBigDecimal("appendAmt"));// 追加后保证金金额
										BigDecimal appendBL = appendAmt.divide(countAmt, 4, BigDecimal.ROUND_HALF_EVEN);// 追加后保证金比例
										subcontract.set("bzjje", appendAmt);
										subcontract.set("bzjbl", appendBL.multiply(new BigDecimal(100)));
										DatabaseUtil.updateEntity("default", subcontract);

										// 调用保证金追加接口
										String loanId = tbLoanInfo.getString("loanId");
										String contractNum = con.getString("contractNum");
										BigDecimal appendAmt1 = tbGrtMargin.getBigDecimal("appendAmt");
										new ECDSClient().S01001030021010(loanId, contractNum, appendAmt1, appendBL, marginAccount, appendAmt);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("票据【追加保证金】接口出错！" + e.getMessage());
		} finally {
			// 补足保证金额度重算
			String party_id = (String) con.get("partyId");
			if (StringUtils.isNotEmpty(party_id)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("partyId", party_id);
				DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map);
			}
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {

	}

	/**
	 * 否决
	 */
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// 流程否决 业务状态更新为06
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取贷款id
			String contractId = (String) list.get(0);
			if (null == contractId || "".equals(contractId)) {
				logger.info("合同流程否决时，bizID为空！");
				throw new EOSException("合同流程否决时，bizID为空！");
			}

			logger.info("------3231------>合同流程否决，开始业务状态为06------bizId=" + contractId + "------->开始!");
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
			con.set("conStatus", "06");
			DatabaseUtil.updateEntity("default", con);
			logger.info("------3231------>合同流程否决，开始业务状态为06------bizId=" + contractId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("------3231------>合同否决状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("------3231------>合同否决状态出错！");
		}
	}

	/**
	 * 撤销
	 */
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
			String entityName = "com.bos.dataset.crt.TbConCreditInfo";
			DataObject con = DataObjectUtil.createDataObject(entityName);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			if (null == con.get("partyId")) {
				entityName = "com.bos.dataset.crt.TbConContractInfo";
				con = DataObjectUtil.createDataObject(entityName);
			}
			con.set("contractId", contractId);
			con.set("conStatus", "06");
			DatabaseUtil.updateEntity("default", con);
			logger.info("------3231------>合同流程撤销，开始业务状态为06------bizId=" + contractId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("------3231------>客户评级流程提交状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		
// 保证金追加不需要进行该校验.
		
//		try {
//			String[] xpath = { "bizId" };// 获取相关数据的数组
//			String conFlagString = "cre";// 标志区分合同和综合授信协议 con-合同 cre-综合授信协议
//			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
//
//			// 如果是退回，不用校验
//			if (null != workitem.get("conclusion")) {
//				String conclusion = (String) workitem.get("conclusion");// 结论
//				if (("99").equals(conclusion)) {
//					resultMap.put("errorNum", "1");
//					resultMap.put("errorCode", "");
//					resultMap.put("errorContent", "");
//					return resultMap;
//				}
//			}
//
//			// 获取贷款id
//			String contractId = (String) list.get(0);
//			if (null == contractId || "".equals(contractId)) {
//				logger.info("------3231------>合同申请ID为空！");
//				throw new EOSException("------3231------>合同申请ID为空！");
//			}
//
//			String entityName = "com.bos.dataset.crt.TbConCreditInfo";
//			DataObject con = DataObjectUtil.createDataObject(entityName);
//			con.set("contractId", contractId);
//			DatabaseUtil.expandEntity("default", con);
//			if (null == con.get("partyId")) {
//				conFlagString = "con";
//				entityName = "com.bos.dataset.crt.TbConContractInfo";
//				con = DataObjectUtil.createDataObject(entityName);
//				con.set("contractId", contractId);
//				DatabaseUtil.expandEntity("default", con);
//			}
//			RuleService rs = new RuleService();
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("contractId", contractId);
//
//			if (conFlagString.equals("cre")) {// 综合授信协议校验
//				// 协议基本信息保存校验
//				List<MessageObj> msgList = rs.runRule("RCON_0008", paramMap);
//				String msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 附属信息校验
//				msgList = rs.runRule("RCON_0023", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 抵质押合同必须有基本信息
//				msgList = rs.runRule("RCON_0015", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有抵质押合同，必须有抵押物
//				msgList = rs.runRule("RCON_0016", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 所有担保合同下担保物的本次担保金额之和都必须大于等于担保合同金额
//				msgList = rs.runRule("RCON_0037", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
//				msgList = rs.runRule("RCON_0038", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				/*
//				 * //保证 注释原因：综合授信协议勾选保证，可能是综合授信添加了信用保险 msgList =
//				 * rs.runRule("RCON_0017", paramMap); msg = convertMsg(msgList);
//				 * if(!"true".equals(msg)){ //校验不成功返回校验失败结果
//				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
//				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
//				 * //抵押合同 msgList = rs.runRule("RCON_0018", paramMap); msg =
//				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
//				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
//				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
//				 * //质押合同 msgList = rs.runRule("RCON_0019", paramMap); msg =
//				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
//				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
//				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
//				 */
//			} else if (conFlagString.equals("con")) {// 合同校验
//				String productType = (String) con.get("productType");
//				DataObject product = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
//				product.set("productCd", productType);
//				DatabaseUtil.expandEntityByTemplate("default", product, product);
//				if (null == productType || "".equals(productType)) {
//					logger.info("------3231------>未查出贷种相关信息！");
//					throw new EOSException("------3231------>未查出贷种相关信息！");
//				}
//				String tableName = (String) product.get("tableName");
//				tableName = tableName.replace("biz", "con");
//				tableName = tableName.replace("_apply", "");
//				String amountDetailId = (String) con.get("amountDetailId");
//				paramMap.put("tableName", tableName);
//				paramMap.put("amountDetailId", amountDetailId);
//
//				// 合同基本信息保存校验
//				List<MessageObj> msgList = rs.runRule("RCON_0003", paramMap);
//				String msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 明细信息保存校验
//				msgList = rs.runRule("RCON_0004", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 分期贷款利率不能为0---20160329
//				msgList = rs.runRule("RCON_0051", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 合同账户信息---放款账户
//				msgList = rs.runRule("RCON_0005", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 合同账户信息---还款账户
//				msgList = rs.runRule("RCON_0020", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 合同账户信息---结算账户
//				msgList = rs.runRule("RCON_0021", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 合同账户信息---应收账款回款专户
//				msgList = rs.runRule("RCON_0022", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 放款账户和还款账户必须一样
//				msgList = rs.runRule("RCON_0036", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//
//				// 保方式选择保证时必须签署保证合同
//				msgList = rs.runRule("RCON_0009", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 担保方式选择抵押时必须签署抵押合同
//				msgList = rs.runRule("RCON_0010", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 担保方式选择质押时必须签署质押合同
//				msgList = rs.runRule("RCON_0011", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 担保方式选择保证金时必须签署保证金协议
//				msgList = rs.runRule("RCON_0012", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 担保金额和合同金额比较
//				msgList = rs.runRule("RCON_0014", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				/*
//				 * //从合同完整性 msgList = rs.runRule("RCON_0013", paramMap); msg =
//				 * convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果
//				 * resultMap.put("errorNum", "2"); resultMap.put("errorCode",
//				 * "2"); resultMap.put("errorContent", msg); return resultMap; }
//				 */
//				// 合同还款计划信息-14还款方式
//				// if(!productType.startsWith("03001")){
//				msgList = rs.runRule("RCON_0006", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// }
//
//				// 抵质押合同必须有基本信息
//				msgList = rs.runRule("RCON_0015", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有抵质押合同，必须有抵押物
//				msgList = rs.runRule("RCON_0016", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有最高额保证合同，从合同金额必须小于等于所有关联保证人担保金额
//				msgList = rs.runRule("RCON_0028", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有抵押合同，从合同金额必须小于等于所有关联抵押物担保金额
//				msgList = rs.runRule("RCON_0029", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有质押合同，从合同金额必须小于等于所有关联质押物担保金额
//				msgList = rs.runRule("RCON_0030", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有保证金协议，从合同金额必须小于等于所有关联保证金担保金额
//				msgList = rs.runRule("RCON_0031", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有保证合同，但是没有勾保证
//				msgList = rs.runRule("RCON_0032", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有抵押合同，但是没有勾抵押
//				msgList = rs.runRule("RCON_0033", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有质押合同，但是没有勾质押
//				msgList = rs.runRule("RCON_0034", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 有保证金协议，但是没有勾保证金
//				msgList = rs.runRule("RCON_0035", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
//				msgList = rs.runRule("RCON_0038", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// 所有担保合同下保证金的校验 :一个保证金协议金额必须小于等于关联的保证金金额
//				msgList = rs.runRule("RCON_0041", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				// // 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
//				// msgList = rs.runRule("RCON_0057", paramMap);
//				// msg = convertMsg(msgList);
//				// if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//				// resultMap.put("errorNum", "2");
//				// resultMap.put("errorCode", "2");
//				// resultMap.put("errorContent", msg);
//				// return resultMap;
//				// }
//
//				// 如果担保方式存在保证+保证金的组合担保方式，业务品种还是银行承兑汇票（'01008001','01008010','01008002'），保函业务（'01009001','01009002'），则保证不用校验金额比担保合同金额大，仅需校验全部担保金额比贷款金额大即可
//				DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
//				tbConContractInfo.set("contractId", paramMap.get("contractId"));
//				DatabaseUtil.expandEntity("default", tbConContractInfo);
//				if ("01008001".equals(tbConContractInfo.getString("productType")) || "01008010".equals(tbConContractInfo.getString("productType"))
//						|| "01008002".equals(tbConContractInfo.getString("productType")) || "01009001".equals(tbConContractInfo.getString("productType"))
//						|| "01009002".equals(tbConContractInfo.getString("productType"))) {
//					// 判断担保方式是否存在保证+保证金的组合担保方式
//					String guaType = tbConContractInfo.getString("guarantyType");
//					if (guaType.indexOf("04") != -1 && guaType.indexOf("05") != -1) {
//
//					} else {
//						// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
//						msgList = rs.runRule("RCON_0057", paramMap);
//						msg = convertMsg(msgList);
//						if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//							resultMap.put("errorNum", "2");
//							resultMap.put("errorCode", "2");
//							resultMap.put("errorContent", msg);
//							return resultMap;
//						}
//					}
//				} else {
//					// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
//					msgList = rs.runRule("RCON_0057", paramMap);
//					msg = convertMsg(msgList);
//					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//						resultMap.put("errorNum", "2");
//						resultMap.put("errorCode", "2");
//						resultMap.put("errorContent", msg);
//						return resultMap;
//					}
//				}
//				// 担保合同止期必须大于主合同起期
//				msgList = rs.runRule("RCON_0062", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//
//				// //所有担保合同下保证金的校验 :该笔合同下的保证金账号只能有一个账号
//				// msgList = rs.runRule("RCON_0042", paramMap);
//				// msg = convertMsg(msgList);
//				// if(!"true".equals(msg)){ //校验不成功返回校验失败结果
//				// resultMap.put("errorNum", "2");
//				// resultMap.put("errorCode", "2");
//				// resultMap.put("errorContent", msg);
//				// return resultMap;
//				// }
//				// 保证金协议金额之和必须大于等于合同金额*合同明细的保证金比例
//				msgList = rs.runRule("RCON_0045", paramMap);
//				msg = convertMsg(msgList);
//				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//			}
//			resultMap.put("errorNum", "1");
//			resultMap.put("errorCode", "");
//			resultMap.put("errorContent", "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("errorNum", "2");
//			resultMap.put("errorCode", "2");
//			resultMap.put("errorContent", "执行规则校验出错！");
//		} catch (Throwable e) {
//			e.printStackTrace();
//			resultMap.put("errorNum", "2");
//			resultMap.put("errorCode", "2");
//			resultMap.put("errorContent", "执行规则校验出错！");
//		}

		resultMap.put("errorNum", "1");
		resultMap.put("errorCode", "");
		resultMap.put("errorContent", "");
		
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

	/**
	 * 判断可用金额是否足够
	 * @param marginAccount
	 * @param appendAmt
	 * @throws Exception
	 */
	private void compareBondAmt(String marginAccount, BigDecimal appendAmt) throws Exception {
		logger.info("保证金可用余额检查------保证金账号：" + marginAccount + "------->开始!");
		OXD051_AccInfoQryReq requestBody = new OXD051_AccInfoQryReq();
		requestBody.setQryType("1");
		requestBody.setCustAcctNo(marginAccount);
		requestBody.setCurrCode("01");
		requestBody.setCashFlag("1");
		requestBody.setQryPwd("1");
		requestBody.setOrgNum("0001");
		OXD052_AccInfoQryRes acctInfo = QueryAccountInfo.executeXD05(requestBody);
		BigDecimal avaiAmt = new BigDecimal(acctInfo.getOxd052ResBody().getAvailableAmt());
		if (avaiAmt.compareTo(appendAmt) < 0) {
			logger.error("保证金账户可用余额不足！追加金额：" + appendAmt + " ,可用余额:" + avaiAmt);
			throw new EOSException("保证金账户可用余额不足！追加金额：" + appendAmt + " ,可用余额:" + avaiAmt);
		}
	}

}
