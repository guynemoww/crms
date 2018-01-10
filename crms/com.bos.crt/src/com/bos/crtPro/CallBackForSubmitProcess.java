package com.bos.crtPro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.accInfo.ContractSub;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.EosUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.StringUtil;
import com.bos.pub.credit.CreditAmtReckon;
import com.bos.pub.credit.CreditAmtValid;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import com.bos.pub.entity.name.ConTableName;
import com.bos.rule.RuleUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.util.RuleException;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import commonj.sdo.DataObject;

/**
 * 回调逻辑：提交流程，更新业务表数据 01-未提交; 02-审批中; 03-结束; 04-已删除
 * 
 * */
public class CallBackForSubmitProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

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
			int expandEntity = DatabaseUtil.expandEntity("default", tbConContractInfo);
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
			// 票据信息
			DataObject tbBizPjxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
			tbBizPjxxApply.set("contractId", contractId);
			tbBizPjxxApply.set("htbh", tbConContractInfo.getString("contractNum"));
			tbBizPjxxApply.set("amountDetailId", tbConContractInfo.getString("amountDetailId"));
			DataObject[] pjxxs = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
			// 删除票据
			if (null != pjxxs && pjxxs.length > 0) {
				DatabaseUtil.deleteEntityBatch("default", pjxxs);
			}

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
			// 合同明细下附属信息
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
				// //删除担保合同信息表
				// DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
				// subCon.set("subcontractId", conRel.getString("subcontractId"));
				// DatabaseUtil.deleteEntity("default", subCon);

				// 删除担保合同信息表
				DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
				subCon.set("subcontractId", conRel.getString("subcontractId"));
				DatabaseUtil.expandEntityByTemplate("default", subCon, subCon);

				// 保证金协议下附属信息
				if ("03".equals(subCon.getString("subcontractType"))) {
					tbConAttachedInfo.set("contractId", conRel.getString("subcontractId"));
					DatabaseUtil.deleteEntity("default", tbConAttachedInfo);
				}
				if (subCon.getString("ifTopSubcon") == "0") {// 不是最高额担保合同
					// 担保合同与押品关系信息表
					DataObject conSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
					conSubGrtRel.set("subcontractId", conRel.getString("subcontractId"));
					DatabaseUtil.expandEntityByTemplate("default", conSubGrtRel, conSubGrtRel);
					DatabaseUtil.deleteEntity("default", conSubGrtRel);

					// 删除担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
					DataObject bizSubRel = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
					bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
					DatabaseUtil.expandEntityByTemplate("default", bizSubRel, bizSubRel);
					DatabaseUtil.deleteEntity("default", bizSubRel);

					DatabaseUtil.deleteEntity("default", subCon);
				} else {// 最高额担保合同
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("subcontractId", conRel.getString("subcontractId"));
					Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.grt.subContractManage.subContractManage.findsubById", paramMap);
					if (outs.length == 1) { // 如果合同和担保合同关系只有一个就可以删担保合同,合同和担保合同关联记录不止一个 就不删除
						// 担保合同与押品关系信息表
						DataObject conSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
						conSubGrtRel.set("subcontractId", conRel.getString("subcontractId"));
						DatabaseUtil.expandEntityByTemplate("default", conSubGrtRel, conSubGrtRel);
						DatabaseUtil.deleteEntity("default", conSubGrtRel);

						// 删除担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
						DataObject bizSubRel = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
						bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
						DatabaseUtil.expandEntityByTemplate("default", bizSubRel, bizSubRel);
						DatabaseUtil.deleteEntity("default", bizSubRel);

						DatabaseUtil.deleteEntity("default", subCon);
					}
				}

				// 删除担保合同信息与贷款合同信息关系表
				DatabaseUtil.deleteEntity("default", conRel);

			}
			// 业务合同基本信息
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

			// <!--撤销合同 先判断con_status=01 未提交 且为新增的担保合同则删除表
			// TB_CON_SUBCONTRACT_REL/TB_CON_SUBCONTRACT/TB_CON_SUB_GRT_REL 相关数据
			// --!> 钟辉2015-12-03修改

			// 1.先查询合同状态是否是01 且所关联的担保合同是否存在新增的状态 com.bos.grt.conGrt.getTZCon

			// Map map = new HashMap();
			// map.put("contractId", contractId);
			// Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.grt.conGrt.getTZCon", map);
			//
			// for (int i = 0; i < objs.length; i++) {
			// DataObject con = (DataObject) objs[0];
			//
			// String subContractId = con.getString("SUBCONTRACT_ID");
			// // String relationId=con.getString("RELATION_ID");
			//
			// // 删除业务合同和担保合同关联关系
			// String subcontractRel_entityName = "com.bos.dataset.crt.TbConSubcontractRel";
			// DataObject subcontractRel_Con = DataObjectUtil.createDataObject(subcontractRel_entityName);
			// subcontractRel_Con.set("contractId", contractId);
			// subcontractRel_Con.set("subContractId", subContractId);
			// DatabaseUtil.deleteByTemplate("default", subcontractRel_Con);

			// 删除担保合同信息
			/*
			 * String subcontract_entityName="com.bos.crt.crt.TbConSubcontract"; DataObject subcontract_Con = DataObjectUtil.createDataObject(subcontract_entityName); subcontract_Con.set("subContractId", subContractId); DatabaseUtil.deleteEntity("default", subcontract_Con);
			 */

			// 删除担保合同和押品关联关系
			/*
			 * String grtRel_entityName="com.bos.dataset.crt.TbConSubGrtRel"; DataObject grtRel_Con = DataObjectUtil.createDataObject(grtRel_entityName); grtRel_Con.set("relationId", relationId); DatabaseUtil.deleteEntity("default", grtRel_Con);
			 */
			// }

			// <!--修改结束--!>

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
		// 获取贷款id
		final String contractId = EosUtil.getBizId(processInstId);
		DataObject con = getConObj(contractId);
		con.set("contractId", contractId);
		con.set("conStatus", "02");// 合同调整流程提交 业务状态更新为02
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, con);
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
		try {
			final String contractId = EosUtil.getBizId(processInstId);
			DataObject con = getConObj(contractId);
			if (isCredit(con)) {
				validCredit(con);
			} else {
				// 更新保证金记录
				HashMap<String, String> paraMap = new HashMap<String, String>();
				paraMap.put("contractId", contractId);
				updateBzjje(paraMap);
				validContract(con);
				CreditAmtValid.validCanUseApproveAmt(con.getString("amountDetailId"), contractId);
			}
		} catch (RuleException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg == null || msg.isEmpty()) {
				msg = "合同提交时执行规则校验出错";
			}
			return getErrorMap(msg);
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg == null || msg.isEmpty()) {
				msg = "合同提交失败";
			}
			return getErrorMap(msg);
		}
		return getSuccessMap("");
	}

	@SuppressWarnings("unchecked")
	private void updateBzjje(HashMap<String, String> paraMap) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.grt.conGrt.getBzjInfo", paraMap);
		if (datas == null || datas.length == 0) {
			return;
		}
		for (int i = 0; i < datas.length; i++) {
			Map<String, Object> map = (Map<String, Object>) datas[i];
			BigDecimal conAmt = (BigDecimal) map.get("CONTRACT_AMT");
			BigDecimal bzjbl = (BigDecimal) map.get("BZJBL");
			BigDecimal bzjje = (BigDecimal) map.get("BZJJE");
			if (bzjbl == null || bzjbl.compareTo(BigDecimal.ZERO) < 0) {
				throw new RuntimeException("缺失保证金比例数据");
			}
			conAmt = conAmt.setScale(8);
			bzjbl = bzjbl.setScale(8);
			BigDecimal newBzjje = conAmt.multiply(bzjbl.divide(CreditAmtReckon.hundred));
			newBzjje = newBzjje.setScale(2, BigDecimal.ROUND_HALF_UP);
			if (newBzjje.compareTo(bzjje) != 0) {
				// 新保证金金额大于原有保证金金额需要校验保证金是否超额情况,在后续规则判断中有相应校验，当前不做处理
				/*
				 * map.put("NEW_BZJJE", newBzjje); if (newBzjje.compareTo(bzjje) > 0 && DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.grt.conGrt.validBzjAccBalance", map) < 1) { throw new RuntimeException("根据保证金比例[" + bzjbl.setScale(2) + "%]计算出的保证金金额[" + newBzjje + "]大于可用保证金金额"); }
				 */
				DataObject subObj = DataObjectUtil.createDataObject(ConTableName.TB_CON_SUBCONTRACT);
				subObj.set("subcontractId", (String) map.get("SUBCONTRACT_ID"));
				subObj.set("bzjje", newBzjje);
				DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, subObj);
			}
		}
	}

	public DataObject getConObj(String contractId) {
		// 优先查找综合授信协议，没有的情况查找合同信息
		DataObject con = EntityUtil.getEntityById(ConTableName.TB_CON_CREDIT_INFO, false, "contractId", contractId);
		if (con == null) {
			con = EntityUtil.getEntityById(ConTableName.TB_CON_CONTRACT_INFO, "contractId", contractId);
		}
		return con;
	}

	public boolean isCredit(DataObject con) {
		return DataObjectUtil.checkEntityName(con, ConTableName.TB_CON_CREDIT_INFO);
	}

	public void validCredit(DataObject con) throws RuleException {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("contractId", con.getString("contractId"));
		RuleUtil.runRules(paramMap, "RCON_0008"// 协议基本信息保存校验
				, "RCON_0023"// 附属信息校验
				, "RCON_0015"// 抵质押合同必须有基本信息
				, "RCON_0016"// 有抵质押合同，必须有抵押物
				, "RCON_0037"// 所有担保合同下担保物的本次担保金额之和都必须大于等于担保合同金额
				, "RCON_0038"// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
		// ,"RCON_0017"// 保证 注释原因：综合授信协议勾选保证，可能是综合授信添加了信用保险
		// ,"RCON_0018"// 抵押合同
		// ,"RCON_0019"// 质押合同
		);
	}

	public void validContract(DataObject con) throws RuleException {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("contractId", con.getString("contractId"));
		final String productType = (String) con.get("productType");
		if (StringUtil.isNull(productType)) {
			throw new EOSException("未获取到合同的贷种信息！");
		}
		DataObject product = EntityUtil.searchEntity(BizTableName.TB_BIZ_PRODUCT_INFO, "productCd", productType);
		if (product == null) {
			throw new RuntimeException("产品代码[" + productType + "]没有对应的产品信息，请维护");
		}
		String tableName = (String) product.get("tableName");
		tableName = tableName.replace("biz", "con");
		tableName = tableName.replace("_apply", "");
		paramMap.put("tableName", tableName);
		paramMap.put("amountDetailId", (String) con.get("amountDetailId"));
		RuleUtil.runRules(paramMap, "RCON_0003"// 合同基本信息保存校验
				, "RCON_0004"// 明细信息保存校验
				, "RCON_0051"// 分期贷款利率不能为0---20160329
		);
		// 国结的表外如：福费廷 进口代付 是不需要填写账户信息的---不用校验
		if (!GitUtil.contain(productType, new String[] { "01007009", "01007012" })) {
			RuleUtil.runRules(paramMap, "RCON_0005"// 合同账户信息---放款账户
					, "RCON_0020"// 合同账户信息---还款账户
					, "RCON_0021"// 合同账户信息---结算账户
					, "RCON_0022"// 合同账户信息---应收账款回款专户
			);
			String agriculLoans = (String) con.get("agriculLoans");
			if (!"1".equals(agriculLoans)) { // 放款账户和还款账户必须一样
				RuleUtil.runRules(paramMap, "RCON_0036");
			}
		}

		RuleUtil.runRules(paramMap, "RCON_0009"// 保证合同
				, "RCON_0010"// 抵押合同
				, "RCON_0011"// 质押合同
				, "RCON_0012"// 保证金
				, "RCON_0014"// 担保金额和合同金额比较
				// ,"RCON_0013"// 从合同完整性
				, "RCON_0006"// 合同还款计划信息-14还款方式
				, "RCON_0015"// 抵质押合同必须有基本信息
				, "RCON_0016"// 有抵质押合同，必须有抵押物
				, "RCON_0028"// 有最高额保证合同，从合同金额必须小于等于所有关联保证人担保金额
				, "RCON_0029"// 有抵押合同，从合同金额必须小于等于所有关联抵押物担保金额
				, "RCON_0030"// 有质押合同，从合同金额必须小于等于所有关联质押物担保金额
				, "RCON_0031"// 有保证金协议，从合同金额必须小于等于所有关联保证金担保金额
				, "RCON_0032"// 有保证合同，但是没有勾保证
				, "RCON_0033"// 有抵押合同，但是没有勾抵押
				, "RCON_0034"// 有质押合同，但是没有勾质押
				, "RCON_0035"// 有保证金协议，但是没有勾保证金
				, "RCON_0037" // 所有担保合同下担保物的本次担保金额之和都必须大于等于担保合同金额
				, "RCON_0038"// 所有担保合同下担保物本次担保金额都必须小于等于权利价值的可用价值
				, "RCON_0041"// 所有担保合同下保证金的校验 :一个保证金协议金额必须小于等于关联的保证金金额
		);
		// 如果担保方式存在保证+保证金的组合担保方式，业务品种还是银行承兑汇票（'01008001','01008010','01008002'），保函业务（'01009001','01009002'），则保证不用校验金额比担保合同金额大，仅需校验全部担保金额比贷款金额大即可
		if (GitUtil.contain(productType, new String[] { "01008001", "01008010", "01008002", "01009001", "01009002" })) {
			String guaType = con.getString("guarantyType");
			// 判断担保方式是否存在保证+保证金的组合担保方式
			if (guaType.indexOf("04") != -1 && guaType.indexOf("05") != -1) {

			} else {
				// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
				RuleUtil.runRules(paramMap, "RCON_0057"// 合同基本信息
				);
			}
		} else {
			// 一般保证合同下的每一个保证人的可用都必须大于或等于合同金额
			RuleUtil.runRules(paramMap, "RCON_0057"// 合同基本信息
			);
		}
		RuleUtil.runRules(paramMap, "RCON_0062"// 担保合同止期必须大于主合同起期
				, "RCON_0045"// 保证金协议金额之和必须大于等于合同金额*合同明细的保证金比例
				, "RCON_0049"// 保证金协议基本信息中的“保证金类型”应与“保证金关联信息”中的“保证金类型”保持一致 add 20151217
				, "RCON_0061"// bug12533 提前还款基数输入大于合同金额 add 20160720
				, "RCON_0063"// bug12598 提前还款最低金额输入大于合同金额 add 20160725
		);
		String csMsg = new ContractSub().checkMaxSub(con.getString("contractId"));
		if ((null != csMsg) && (!"".equals(csMsg))) {
			throw new RuntimeException(csMsg);
		}
		// add by shendl 20170725---国结产品
		if (GitUtil.contain(productType, new String[] { "01007001", "01007003", "01007004", "01007005", "01007009", "01007010", "01007012", "01007011", "01007006" })) {
			RuleUtil.runRules(paramMap, "RCON_0066"// 当前合同币种应该等于国结建议的合同币种
					, "RCON_0067"// 当前合同金额应该等于国结建议的合同金额
			);
		} else if (GitUtil.contain(productType, new String[] { "01008001", "01008002", "01008010" })) {// 银承
			RuleUtil.runRules(paramMap, "RCON_0065"// 检查是否添加票据明细
					, "RCON_0068"// 检查银承票据总额是否等于合同金额
					, "RCON_0072"// 检查票据明细出票人账号是否和结算账号相同
					, "RCON_0070"// 票据出票日期和到期日期必须包含在合同起始日和到期日之间
					, "RCON_0042"// 保证金账号只能添加一个
					, "RCON_0083"// 检查票据只能添加99张
			);
		} else if (GitUtil.contain(productType, new String[] { "01006001", "01006002", "01006010" })) {// 贴现
			RuleUtil.runRules(paramMap, "RCON_0073"// 检查是否添加贴现明细
					, "RCON_0069"// 检查贴现金额是否等于合同金额
					, "RCON_0071"// 检查贴现利率是否相同
					, "RCON_0084"// 检查票据只能添加99张
			);
		}
		validSubZge(paramMap);
	}

	/**
	 * 检查最高额
	 * 
	 * @param paramMap
	 * @throws RuleException
	 */
	private void validSubZge(Map<String, String> paramMap) throws RuleException {
		System.out.println("校验是否超过最高额担保合同金额开始...");
		// add by shangmf：增加担保合同总金额之和是否超过最高额担保合同的总金额的校验
		Map<String, String> map_zge = new HashMap<String, String>();
		map_zge.put("contractId", paramMap.get("contractId"));
		// 通过合同id查询申请ID，如果申请ID存在
		Object[] objs_jxhj = DatabaseExt.queryByNamedSql("default", "com.bos.conApply.conApply.checkifJxhj", map_zge);
		if (objs_jxhj.length > 0) {// 是借新还旧

		} else {
			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.conApply.conApply.getSubcontractId", map_zge);
			for (int i = 0; i < objs.length; i++) {
				DataObject object = (DataObject) objs[i];
				map_zge.put("subcontractId", object.getString("SUBCONTRACT_ID"));
				RuleUtil.runRules(map_zge, "SUBCON_0008");
			}
		}
		System.out.println("校验是否超过最高额担保合同金额结束!");
	}

	private Map<String, String> getMsgMap(String errorNum, String errorCode, String error) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("errorNum", errorNum);
		resultMap.put("errorCode", errorCode);
		resultMap.put("errorContent", error);
		return resultMap;
	}

	private Map<String, String> getSuccessMap(String success) {
		return getMsgMap("1", "", success);
	}

	private Map<String, String> getErrorMap(String error) {
		return getMsgMap("2", "2", error);
	}
}
