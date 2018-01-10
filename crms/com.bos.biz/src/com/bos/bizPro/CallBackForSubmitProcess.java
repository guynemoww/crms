package com.bos.bizPro;

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
import com.bos.pub.GitUtil;
import com.bos.pub.credit.CreditAmtValid;
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
public class CallBackForSubmitProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

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
			
			
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("applyId", applyId);

			// 查询抵质押的押品编号。如果存在押品编号，则与押品交互，删除业务押品关系
			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.bizApprove.bizApprove.getSXMortgageBasic", map1);
			if (objs.length > 0) {

				// modi by shangmf:业务申请可能关联多笔押品，需要循环，且需要删除信贷本身的关联关系tb_biz_grt_rel
				for (int i = 0; i < objs.length; i++) {

					// DataObject object = (DataObject) objs[0];
					DataObject object = (DataObject) objs[i];
					map1.put("cltNo", object.getString("SURETY_NO"));
					map1.put("suretyId", object.getString("SURETY_ID"));
					// 先删除信贷本地的业务申请和押品关系
					DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.delTbBizGrtRel", map1);

					// 调接口，同步数据
					CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
					CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();

					logger.info("押品与业务关联信息删除------applyId=" + applyId + "------>开始!");
					ObjectMapper mapper = new ObjectMapper();
					map1.put("trans_code", "1114");// 接口交易码
					map1.put("ope_flag", "delapply");// 操作标志
					// Convert object to JSON string
					String ypxxJsonStr = null;
					ypxxJsonStr = mapper.writeValueAsString(map1);
					ser.setIn0(ypxxJsonStr);
					String flag = stub.collServiceCommInter(ser).getOut1();

				}

				logger.info("押品与业务关联信息删除------applyId=" + applyId + "------>结束!");
			}
			logger.info("------3231------>删除贴现票据信息------>开始！ bizId=" + applyId);
			if ("01006001".equals(productType) || "01006002".equals(productType) || "01006010".equals(productType)) {
			 Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.bizApply.bizApply.getCountByApplyId", map1);
				if (objs1.length == 0) {//objs1.length == 0允许删除
					Object[] objs2 = DatabaseExt.queryByNamedSql("default", "com.bos.bizApply.bizApply.getAmountDetailId", map1);
					if (objs2.length >0 ) {
						for (int i = 0; i < objs2.length; i++) {
							 String amountDetailId = (String) objs2[i];
							 DataObject tbBizTxxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTxxxApply");
							 tbBizTxxxApply.set("amountDetailId", amountDetailId);
							 DataObject[] tbBizTxxxApplys = DatabaseUtil.queryEntitiesByTemplate("default", tbBizTxxxApply);
							 if(tbBizTxxxApplys.length>0){
								 DatabaseUtil.deleteEntityBatch("default", tbBizTxxxApplys);
							 }
						}
					}
				}
			}
			 logger.info("------3231------>删除贴现票据信息------>结束！");
			// 20170905该段代码在业务申请时删除的，目前没有用
			// logger.info("------3231------>业务申请撤销流程------>end！");
			// logger.info("------3231------>删除票据信息------>bizId=" + applyId);
			// if ("01006001".equals(productType) || "01006002".equals(productType) || "01008001".equals(productType) || "01008002".equals(productType)
			// || "01008010".equals(productType) || "01006010".equals(productType) //村镇银行贴现产品
			// ) {
			// DataObject tbBizAmountApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			// tbBizAmountApply.set("applyId", applyId);
			// DatabaseUtil.expandEntityByTemplate("default", tbBizAmountApply, tbBizAmountApply);// 获取申请基本信息表 amountId
			// String amountId = tbBizAmountApply.getString("amountId");
			// DataObject tbBizAmountDetailApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
			// tbBizAmountDetailApply.set("amountId", amountId);
			// DatabaseUtil.expandEntityByTemplate("default", tbBizAmountDetailApply, tbBizAmountDetailApply);// 获取申请基本信息表 amountId//获取申请基本信息表相细 amountDetailId
			// String amountDetailId = tbBizAmountDetailApply.getString("amountDetailId");
			// if ("01008001".equals(productType) || "01008002".equals(productType)
			// || "01008010".equals(productType)
			// ) {
			// DataObject tbBizPjxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
			// tbBizPjxxApply.set("amountDetailId", amountDetailId);
			// DataObject[] tbBizPjxxApplys = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
			// DatabaseUtil.deleteEntityBatch("default", tbBizPjxxApplys);
			// }// 删除兑票
			// if ("01006001".equals(productType) || "01006002".equals(productType)
			// || "01006010".equals(productType) //村镇银行贴现产品
			// ) {
			// DataObject tbBizTxxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTxxxApply");
			// tbBizTxxxApply.set("loanid", amountDetailId);
			// DataObject[] tbBizTxxxApplys = DatabaseUtil.queryEntitiesByTemplate("default", tbBizTxxxApply);
			// DatabaseUtil.deleteEntityBatch("default", tbBizTxxxApplys);
			// }// 删除贴现
			// }
			// logger.info("------3231------>删除票据信息------>end！");
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
			// 查询品种明细金额
			HashMap map = new HashMap();
			map.put("applyId", applyId);

			BigDecimal creditAmt = new BigDecimal("0");
			if ("03".equals(bizType)) {
				GroupInfo gi = new GroupInfo();
				creditAmt = gi.getGroupCredit(bizApply1.getString("partyId"), "1");
			} else {
				Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.bizInfo.bizInfo.getAmountAmt", map);
				creditAmt = (BigDecimal) objs[0];
			}

			// 查询基本信息
			DataObject bizInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			bizInfo.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", bizInfo, bizInfo);

			bizInfo.set("creditAmount", creditAmt);
			DatabaseUtil.updateEntity("default", bizInfo);
			// 统计客户单笔单批业务实际占用额度
			CreditAmtValid.validDbdpAmt(bizApply1);
			// 统计单户用户实际占用额度
			CreditAmtValid.validPartyAmt(bizApply1.getString("partyId"));
			// 更新申请表状态
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			bizApply.set("statusType", "02");
			DatabaseUtil.updateEntity("default", bizApply);

			// 综合授信业务在申请提交时 将该客户下所有的非银团、非低批复置为失效(除本笔)
			String partyId = bizApply1.getString("partyId");
			String bizHappenType = bizApply1.getString("bizHappenType");
			if ("01".equals(bizHappenType)) {// 综合授信调整
				if (!StringUtils.isBlank(partyId)) {
					SaveBizInfo sbi = new SaveBizInfo();
					sbi.updateOtherApvBeforSubmit(applyId, partyId);
				}
			}

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
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String applyId = (String) list.get(0);

			DataObject bizApp = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApp.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApp);
			// DataObject bizAmountApp = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			// bizAmountApp.set("applyId", applyId);
			// DatabaseUtil.expandEntity("default", bizAmountApp);
			DataObject bizAmountApp = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			bizAmountApp.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", bizAmountApp, bizAmountApp);

			String partyId = (String) bizApp.get("partyId");
			String bizType = (String) bizApp.get("bizType");// 04小微 01单笔 02综合授信
			String bizHappenType = (String) bizApp.get("bizHappenType");// 04调整 06借新还旧
			String isBankTeamLoan = "0";
			if (bizApp.get("isBankTeamLoan") != null && !"".equals(bizApp.get("isBankTeamLoan"))) {
				isBankTeamLoan = (String) bizApp.get("isBankTeamLoan");
			}

			// 查询品种明细金额
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			map.put("partyId", partyId);
			BigDecimal creditAmt = new BigDecimal("0");
			if ("03".equals(bizType)) {
				GroupInfo gi = new GroupInfo();
				creditAmt = gi.getGroupCredit(partyId, "1");
				if (creditAmt.compareTo(new BigDecimal("0")) == 0) {
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", "统一授信业务金额为0");
					return resultMap;
				}
			}

			RuleService rs = new RuleService();
			Map paramMap = new HashMap();
			paramMap.put("applyId", applyId);
			paramMap.put("partyId", partyId);
			// 基本信息保存校验
			List<MessageObj> msgList = rs.runRule("RBIZ_0003", paramMap);
			String msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			// 明细信息保存校验
			if (!"03".equals((String) bizApp.get("bizType"))) {
				msgList = rs.runRule("RBIZ_0004", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			}

			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				msg = "false";
				if ("02002004".equals(productType) || "02002005".equals(productType)) {
					// 查询担保方式是否以包含抵押，不存在信用
					if (bizAmountApp != null) {
						String guaType = (String) bizAmountApp.get("guarantyType");
						String[] guaArray = guaType.split(",");
						boolean type02 = Arrays.asList(guaArray).contains("02"); // 是否存在抵押
						boolean type01 = Arrays.asList(guaArray).contains("01"); // 是否存在信用
						if (type02 != true || type01 == true) {
							msg = "true";
						}
					}

					if ("true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", "个人住房按揭贷款,个人商用房按揭贷款担保方式必须存在抵押，不存在信用,请检查");
						return resultMap;
					}
				} else if ("02005001".equals(productType)) {
					// 公积金住房委托贷款，控制为，选择保证+抵押或者抵押方式
					if (bizAmountApp != null) {
						String guaType = (String) bizAmountApp.get("guarantyType");
						if ("02,04".equals(guaType) || "02".equals(guaType) || "04,02".equals(guaType)) {
						} else {
							msg = "true";
						}

					}
					if ("true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", "公积金住房委托贷款担保方式只有保证+抵押或者抵押方式,请检查");
						//return resultMap;
					}
				}
			}

			// 明细信息有金额为0的分项校验
			msgList = rs.runRule("RBIZ_0013", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 家庭财务信息校验 根据程钰要求--经营性不校验
			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				if (!productType.startsWith("01") && !productType.startsWith("020050") && !productType.startsWith("020010") && !productType.startsWith("03")) {
					msgList = rs.runRule("RBIZ_0058", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
				}
			}
			// 监管报送
			msgList = rs.runRule("RBIZ_0005", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 银团贷款必须录入银团信息
			if ("1".equals(isBankTeamLoan)) {
				msgList = rs.runRule("RBIZ_0040", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}

			}
			// 以下业务品种在业务申请提交时，需校验录入项目额度信息--此时amount_detail_id 存的applyId
			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				// 个人住房
				if ("02002004".equals(productType) || "02002005".equals(productType) || "02002010".equals(productType) || "02002011".equals(productType) || "02004001".equals(productType) || "02004002".equals(productType) || "02005001".equals(productType) || "02005010".equals(productType) || "03001020".equals(productType) || "02001004".equals(productType)) {
					msgList = rs.runRule("RBIZ_0057", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						/*
						 * resultMap.put("errorNum", "2"); resultMap.put("errorCode", "2"); resultMap.put("errorContent", msg); return resultMap;
						 */
					} // 对私 二手房按揭、汽车消费贷款、公积金贷款 、富民商用房按揭贷款 、富民住房按揭贷款
				}
				if ("02002004".equals(productType) || "02002005".equals(productType)) {
					msgList = rs.runRule("RBIZ_0057", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					} // 对私 二手房按揭、汽车消费贷款、公积金贷款 、富民商用房按揭贷款 、富民住房按揭贷款
				}
				if ("02002003".equals(productType) || "02003012".equals(productType) || "02005001".equals(productType) || "02002010".equals(productType) || "02002011".equals(productType)) {
					List<MessageObj> msgList2 = rs.runRule("RBIZ_0075", paramMap);// 验证是否需要按揭合作商
					String msg2 = convertMsg(msgList2);// 有按揭合作商 返回true
					if (!"true".equals(msg2)) {// 如果是有按揭合作商 那么就校验合作项目额度信息
						msgList = rs.runRule("RBIZ_0057", paramMap);
						msg = convertMsg(msgList);
						if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
							resultMap.put("errorNum", "2");
							resultMap.put("errorCode", "2");
							resultMap.put("errorContent", msg);
							return resultMap;
						}
					}
				}
			}
			// 调整后的批复金额不能大于原批复已用--借新还旧调整bizHappenType还是06 所以用bizApply里的oldapply判断
			if (null != bizApp.get("oldApplyId") && !"".equals(bizApp.get("oldApplyId"))) {
				msgList = rs.runRule("RBIZ_0064", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			}
			// 借新还旧申请额度不得大于借据余额
			if ("06".equals(bizHappenType)) {
				msgList = rs.runRule("RBIZ_0034", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			}

			// 如果担保方式有保证必须输入保证信息--业务提的要求，项目经理要求去掉的
			// 2017-09-11 业务提的变更，如果担保方式有保证必须输入保证人信息 ，保证金不必填。

			msgList = rs.runRule("RBIZ_0006", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			// 如果担保方式有抵押必须输入抵押信息
			msgList = rs.runRule("RBIZ_0007", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				//return resultMap;
			}
			// 如果担保方式有质押必须输入质押信息
			msgList = rs.runRule("RBIZ_0008", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 抵质押物信息必须录完整
			msgList = rs.runRule("RBIZ_0014", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			/*
			 * //抵质押物权利价值与评估价值校验 msgList = rs.runRule("RBIZ_0015", paramMap); msg = convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果 resultMap.put("errorNum", "2"); resultMap.put("errorCode", "2"); resultMap.put("errorContent", msg); return resultMap; }
			 */
			// 委托贷款必须录入委托人账户信息
			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				if ("01013001".equals(productType) || "02005001".equals(productType) || "01013010".equals(productType) || "02005010".equals(productType) || "02005002".equals(productType) || "02005003".equals(productType)) {
					msgList = rs.runRule("RBIZ_0045", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
				}
			}

			// 贴息必输校验--20160526 lpc
			msgList = rs.runRule("RBIZ_0020", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 贴息方式必须一样
			msgList = rs.runRule("RBIZ_0054", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 贴息比例之和不超过100%
			msgList = rs.runRule("RBIZ_0055", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			// 按固定金额贴息：只能支持一个贴息主体
			msgList = rs.runRule("RBIZ_0056", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 流贷要流动资金测算
			msgList = rs.runRule("RBIZ_0021", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			// 银团成员币种必须和明细一致
			msgList = rs.runRule("RBIZ_0022", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 银团结构币种必须和明细一致
			msgList = rs.runRule("RBIZ_0036", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 银团成员金额之和必须和明细一致
			msgList = rs.runRule("RBIZ_0023", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 银团成员金额之和必须和结构贷款总金额一致
			msgList = rs.runRule("RBIZ_0035", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 协办客户经理添加校验
			msgList = rs.runRule("RBIZ_0025", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 集团成员未完成不允许提交
			msgList = rs.runRule("RBIZ_0026", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 有保证信息必须勾选保证
			msgList = rs.runRule("RBIZ_0028", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 有抵押信息必须勾选抵押
			msgList = rs.runRule("RBIZ_0029", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 有质押信息必须勾选质押
			msgList = rs.runRule("RBIZ_0030", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			// 有保证金信息必须勾选保证金
			msgList = rs.runRule("RBIZ_0031", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}

			/*
			 * //集团成员综合授信期限不能大于集团综合授信的期限 if("03".equals(bizType)){ msgList = rs.runRule("RBIZ_0043", paramMap); msg = convertMsg(msgList); if(!"true".equals(msg)){ //校验不成功返回校验失败结果 resultMap.put("errorNum", "2"); resultMap.put("errorCode", "2"); resultMap.put("errorContent", "成员授信期限不得大于统一授信期限！"); return resultMap; } }
			 */

			// 校验该业务下是否存在未价值审核的押品
			msgList = rs.runRule("RBIZ_0067", paramMap);
			msg = convertMsg(msgList);
			if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			
			CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
			CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
			CollServiceImplServiceServiceStub.CollServiceCommInter serQuery = new CollServiceImplServiceServiceStub.CollServiceCommInter();
			ObjectMapper mapper = new ObjectMapper();
			//增加校验。如果业务申请下面存在 暂存 的押品，业务申请不允许通过，通过申请号查询
			String cltStsCdMsg = "该业务申请下存在'暂存'状态的押品，不允许提交!";
			logger.info("------------>调用押品系统查询押品校验接口1115------>开始!");
			Map ypxxMap = new HashMap();
			ypxxMap.put("applyId", applyId);//申请号
			ypxxMap.put("ope_flag","0001");
			ypxxMap.put("trans_code","1115");//押品校验查询接口交易码
			// Convert object to JSON string  
			String ypxxJsonStr = null;
			ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
			System.out.println("押品校验查询接口入参："+ypxxJsonStr);
			ser.setIn0(ypxxJsonStr);
			logger.info("------------>applyId---ypxxJsonStr="+ypxxJsonStr+"---->结束!");
			String queryStr = stub.collServiceCommInter(ser).getOut1();	
			logger.info("------------>applyId结束------queryStr="+queryStr+"------>结束!");
			Map strmap = mapper.readValue(queryStr,HashMap.class);
			logger.info("信贷查回的押品校验详情:"+queryStr);
			if("true".equals(strmap.get("flag"))){
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", cltStsCdMsg);
				return resultMap;
			}
			
			// 贴现业务  	检查贴现金额是否等于申请金额
			if (null != bizApp.get("productType") && !"".equals(bizApp.get("productType"))) {
				String productType = (String) bizApp.get("productType");
				if ("01006001".equals(productType) || "01006002".equals(productType) || "01006010".equals(productType)) {
					
					msgList = rs.runRule("RBIZ_0087", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
					
					msgList = rs.runRule("RBIZ_0086", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
					
					msgList = rs.runRule("RBIZ_0088", paramMap);
					msg = convertMsg(msgList);
					if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
						resultMap.put("errorNum", "2");
						resultMap.put("errorCode", "2");
						resultMap.put("errorContent", msg);
						return resultMap;
					}
					
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
