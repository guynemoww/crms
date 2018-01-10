package com.bos.crtPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.SaveBizInfo;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.conInfo.SubProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * 回调逻辑：结束流程，更新业务表数据 01-未提交; 02-审批中; 03-结束; 04-已删除
 * 
 * */
public class CallBackForEndProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

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
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	/**
	 * 同意
	 * */
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath = { "bizId" };// 获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");// 结论
		try {

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
				entityName = "com.bos.dataset.crt.TbConContractInfo";
				con = DataObjectUtil.createDataObject(entityName);
			}
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			String conStatus = (String) con.get("conStatus");
			if (!"06".equals(conStatus)) {
				logger.info("流程结束，开始处理业务数据------bizId=" + contractId + "------->开始!");
				SubProcess sp = new SubProcess();
				sp.submitPro(contractId);
			}

			Map map2 = new HashMap();
			map2.put("partyId", con.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			
			
			//增加调用合同逻辑流 ，同步至押品系统：add by shangmf：传入contractId
			// 逻辑构件名称 :com.bos.csm.callback.moveBusiness           
			String componentName = "com.bos.conApply.conSynToCollByWebService";
			// 逻辑流名称 
			String operationName = "conSynColl";
			ILogicComponent logicComponent = LogicComponentFactory
			.create(componentName);
            //逻辑流的输入参数
			Object[] params = new Object[1];
			params[0] = contractId;
			logicComponent.invoke(operationName, params);
			
			
			// 补足保证金额度重算
			// 更新从合同状态
//			DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
//			if (con.get("oldContractId") == null || "".equals(con.get("oldContractId"))) {
//				return;
//
//			} else {
//
//				conRel.set("contractId", con.get("oldContractId"));
//				DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
//				for (int i = 0; i < conRels.length; i++) {
//					conRel = conRels[i];
//					DataObject conSubcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
//					conSubcon.set("subcontractId", conRel.get("subcontractId"));
//					if (conSubcon.get("subcontractId") == null || "".equals(conSubcon.get("subcontractId"))) {
//						continue;
//					} else {
//						DatabaseUtil.expandEntity("default", conSubcon);
//						if ("03".equals(conSubcon.getString("subcontractType"))) {
//							conSubcon.set("subcontractStatus", "04");
//							DatabaseUtil.updateEntity("default", conSubcon);
//						}
//					}
//				}
//			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("合同申请同意流程更新业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("合同申请同意流程更新业务状态出错");
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	/**
	 * 否决
	 * */
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

			// 担保合同信息 com.bos.dataset.crt.TbConSubcontractRel
			DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
			conRel.set("contractId", contractId);
			DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
			for (int i = 0; i < conRels.length; i++) {
				conRel = conRels[i];
				//删除担保合同信息表
				DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
				subCon.set("subcontractId", conRel.getString("subcontractId"));
				DatabaseUtil.expandEntityByTemplate("default", subCon, subCon);
				if(subCon.getString("ifTopSubcon") == "0"){//不是最高额担保合同
					//担保合同与押品关系信息表 
					DataObject conSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
					conSubGrtRel.set("subcontractId", conRel.getString("subcontractId"));
					DatabaseUtil.expandEntityByTemplate("default", conSubGrtRel, conSubGrtRel);
					DatabaseUtil.deleteEntity("default", conSubGrtRel); 
					
					//删除担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
					DataObject bizSubRel= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
					bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
					DatabaseUtil.expandEntityByTemplate("default", bizSubRel, bizSubRel);
					DatabaseUtil.deleteEntity("default", bizSubRel);
					
					DatabaseUtil.deleteEntity("default", subCon); 
				}else{//最高额担保合同
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("subcontractId", conRel.getString("subcontractId"));
					Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
							"com.bos.grt.subContractManage.subContractManage.findsubById", paramMap);
					if (outs.length==1) { //如果合同和担保合同关系只有一个就可以删担保合同,合同和担保合同关联记录不止一个 就不删除
						//担保合同与押品关系信息表 
						DataObject conSubGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
						conSubGrtRel.set("subcontractId", conRel.getString("subcontractId"));
						DatabaseUtil.expandEntityByTemplate("default", conSubGrtRel, conSubGrtRel);
						DatabaseUtil.deleteEntity("default", conSubGrtRel); 
						
						//删除担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
						DataObject bizSubRel= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
						bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
						DatabaseUtil.expandEntityByTemplate("default", bizSubRel, bizSubRel);
						DatabaseUtil.deleteEntity("default", bizSubRel);
						
						DatabaseUtil.deleteEntity("default", subCon);
					}
				}
				
				//删除担保合同信息与贷款合同信息关系表
				DatabaseUtil.deleteEntity("default", conRel);
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
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>合同否决状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>合同否决状态出错！");
		}
	}

	/**
	 * 撤销
	 * */
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
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>客户评级流程提交状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			String conFlagString = "cre";// 标志区分合同和综合授信协议 con-合同 cre-综合授信协议
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);

			// 如果是退回，不用校验
			if (null != workitem.get("conclusion")) {
				String conclusion = (String) workitem.get("conclusion");// 结论
				if (("99").equals(conclusion)||"2".equals(conclusion)) {
					resultMap.put("errorNum", "1");
					resultMap.put("errorCode", "");
					resultMap.put("errorContent", "");
					return resultMap;
				}
			}

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
				//国结的表外如：福费廷 进口代付  是不需要填写账户信息的---不用校验
				if("01007009".equals(productType)||"01007012".equals(productType)){
				}else{
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
					String agriculLoans = (String) con.get("agriculLoans");
					if(!"1".equals(agriculLoans)){
					// 放款账户和还款账户必须一样
					msgList = rs.runRule("RCON_0036", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
				   }
				}
				// 保方式选择保证时必须签署保证合同
				msgList = rs.runRule("RCON_0009", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 担保方式选择抵押时必须签署抵押合同
				msgList = rs.runRule("RCON_0010", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 担保方式选择质押时必须签署质押合同
				msgList = rs.runRule("RCON_0011", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				// 担保方式选择保证金时必须签署保证金协议
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
				/*
				 * //从合同完整性 msgList = rs.runRule("RCON_0013", paramMap); msg =
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
				msgList = rs.runRule("RCON_0031", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
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
				msgList = rs.runRule("RCON_0041", paramMap);
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

				// //所有担保合同下保证金的校验 :该笔合同下的保证金账号只能有一个账号
				// msgList = rs.runRule("RCON_0042", paramMap);
				// msg = convertMsg(msgList);
				// if(!"true".equals(msg)){ //校验不成功返回校验失败结果
				// resultMap.put("errorNum", "2");
				// resultMap.put("errorCode", "2");
				// resultMap.put("errorContent", msg);
				// return resultMap;
				// }
				// 保证金协议金额之和必须大于等于合同金额*合同明细的保证金比例
				msgList = rs.runRule("RCON_0045", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				
				
				System.out.println("校验是否超过最高额担保合同金额开始...");
				//add by shangmf：增加担保合同总金额之和是否超过最高额担保合同的总金额的校验
				Map map_zge = new HashMap();
				map_zge.put("contractId", paramMap.get("contractId"));
				//通过合同id查询申请ID，如果申请ID存在
				Object[] objs_jxhj = DatabaseExt.queryByNamedSql("default","com.bos.conApply.conApply.checkifJxhj", map_zge);
				if(objs_jxhj.length>0){//是借新还旧
					
				}else{
					Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.conApply.getSubcontractId", map_zge);
					if(objs.length>0){
						
						for(int i = 0; i < objs.length; i++ ){
							DataObject object = (DataObject) objs[i];
							map_zge.put("subcontractId", object.getString("SUBCONTRACT_ID"));
							
							List<MessageObj> msgList_1 = rs.runRule("SUBCON_0008", map_zge);
							String msg1 = convertMsg(msgList_1);
							if (!"true".equals(msg1)) { // 校验不成功返回校验失败结果
								resultMap.put("errorNum", "2");
								resultMap.put("errorCode", "2");
								resultMap.put("errorContent", msg1);
								return resultMap;
							}
						}				
						
					}
					System.out.println("校验是否超过最高额担保合同金额结束!");
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
