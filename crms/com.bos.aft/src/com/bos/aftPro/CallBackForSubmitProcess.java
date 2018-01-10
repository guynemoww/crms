package com.bos.aftPro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.inter.InterAft;
import com.bos.inter.InterToJL;
import com.bos.pub.GitUtil;
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

public class CallBackForSubmitProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

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
			
			// 更新合同借据变更表状态
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			
			//logger.info("changeDate--->" + change.getDate("changeDate").toString().substring(0, 10));
			logger.info("busDate--->" + GitUtil.getBusiDateStr());
			if("11".equals(change.getString("loanChangeType")) || "15".equals(change.getString("loanChangeType")) || "18".equals(change.getString("loanChangeType"))) {
				//提前还款流程须在同一交易日内完成，否则流程失效
				if(!GitUtil.getBusiDateStr().equals(change.getDate("changeDate").toString().substring(0, 10))) {
					throw new EOSException("提前还款流程须在同一交易日内完成，请退回撤销该笔后重新再发起！");
				}else {
					logger.info("same day--->11");
				}
			}else if("02".equals(change.getString("loanChangeType")) 
					&& ("1400".equals(change.getString("newRepayWay")) || "1410".equals(change.getString("newRepayWay")))) {
				//还款方式变更为带还本计划的流程须在同一交易日内完成，否则流程失效
				if(!GitUtil.getBusiDateStr().equals(change.getDate("changeDate").toString().substring(0, 10))) {
					throw new EOSException("还款方式变更为带还本计划的流程须在同一交易日内完成，请退回撤销该笔后重新再发起！");
				}else {
					logger.info("same day--->02");
				}
			}else if("10".equals(change.getString("loanChangeType"))) {
				//还本计划变更的流程须在同一交易日内完成，否则流程失效
				if(!GitUtil.getBusiDateStr().equals(change.getDate("changeDate").toString().substring(0, 10))) {
					throw new EOSException("还本计划变更流程须在同一交易日内完成，请退回撤销该笔后重新再发起！");
				}else {
					logger.info("same day--->10");
				}
			}else if("16".equals(change.getString("loanChangeType"))) {
				//利息调整的流程须在同一交易日内完成，否则流程失效
				if(!GitUtil.getBusiDateStr().equals(change.getDate("changeDate").toString().substring(0, 10))) {
					throw new EOSException("利息调整流程须在同一交易日内完成，请退回撤销该笔后重新再发起！");
				}else {
					logger.info("same day--->10");
				}
			}else if("14".equals(change.getString("loanChangeType"))) {
				//判断是否保存了新的停息信息
				System.out.println(change.getString("newTingxiStatus"));
				if(change.getString("newTingxiStatus")==null) {
					throw new EOSException("请先点击停息或终止停息按钮！");
				}else {
					logger.info("same day--->10");
				}
			}else if("08".equals(change.getString("loanChangeType"))){
			DataObject conLoanTx = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanTx");
			conLoanTx.set("changeId", applyId);
			conLoanTx.set("bglx", "2");
			DataObject[] conLoanTxs = DatabaseUtil.queryEntitiesByTemplate("default", conLoanTx);
			float txbl=0;
 			for (int i = 0; i < conLoanTxs.length; i++) {
				 conLoanTx = conLoanTxs[i];
				 txbl=txbl+conLoanTx.getFloat("txbl");
				
			}
			if(txbl>100){
				throw new EOSException("变更后贴息比例之和要小于100%！");

			}


				
			}

			// 更新合同借据变更表状态
			//DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			//change.set("changeId", applyId);
			change.set("changeStatus", "02");
			DatabaseUtil.updateEntity("default", change);
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			DatabaseUtil.expandEntity("default", normal);
			if(null != normal && null != normal.getString("isFinish")) {
				if("1".equals(normal.getString("isFinish"))) {
					normal.set("checkStatus", "02");
					DatabaseUtil.updateEntity("default", normal);
				}else {
					throw new EOSException("请填写日常检查信息！");
				}
			}
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", expire);

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
		//审批通过
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			
			if(null != change && null != change.getString("loanChangeType")) {
				
				DataObject tbLoanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				tbLoanSummary.set("summaryId", change.getString("summaryId"));
				DatabaseUtil.expandEntity("default", tbLoanSummary);
				Date summaryDate = (Date) tbLoanSummary.get("endDate");
				DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				tbLoanInfo.set("loanId", tbLoanSummary.getString("loanId"));
				DatabaseUtil.expandEntity("default", tbLoanInfo);
				
				//logger.info("changeDate--->" + change.getDate("changeDate").toString().substring(0, 10));
				logger.info("busDate--->" + GitUtil.getBusiDateStr());
				if("11".equals(change.getString("loanChangeType")) || "15".equals(change.getString("loanChangeType")) || "18".equals(change.getString("loanChangeType"))) {
					//提前还款流程须在同一交易日内完成，否则流程失效
					if(!GitUtil.getBusiDateStr().equals(change.getDate("changeDate").toString().substring(0, 10))) {
						throw new EOSException("提前还款流程须在同一交易日内完成，请撤销该笔后重新再发起！");
					}else {
						logger.info("same day--->11");
					}
				}
				
				/*InterAft inter = new InterAft();
				try {
					inter.interChose(change, tbLoanInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new EOSException(e.getMessage());
				}*/
				
				//1、修改管理数据
				change.set("changeStatus", "03");
				DatabaseUtil.updateEntity("default", change);
				
				if("11".equals(change.get("loanChangeType")) ||"15".equals(change.get("loanChangeType")) ||"18".equals(change.get("loanChangeType"))) {//提前还款
					
					if("1".equals(change.getString("isSmall")) && "1".equals(change.getString("isModifyPlan"))) {//小贷
						//先删除原还款计划
						DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
						tbConContractInfo.set("contractId", tbLoanInfo.getString("contractId"));
						DatabaseUtil.expandEntity("default", tbConContractInfo);
						
						DataObject tbBizXwHkjh = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXwHkjh");
						tbBizXwHkjh.set("amountDetailId", tbConContractInfo.getString("amountDetailId"));
						DatabaseUtil.deleteByTemplate("default", tbBizXwHkjh);
						
						//再插入新的还款计划
						DataObject tbConRepayplanChange = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
						tbConRepayplanChange.set("changeId", change.get("changeId"));
						tbConRepayplanChange.set("newOrOld", "2");
						DataObject[] plan = DatabaseUtil.queryEntitiesByTemplate("default", tbConRepayplanChange);
						for(int i = 0;i<plan.length;i++){
							DataObject result = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXwHkjh");
							result.set("qc", plan[i].getBigDecimal("newPeriodsNum").intValue());
							result.set("uuid", GitUtil.genUUIDString());
							result.set("endDate", plan[i].getDate("newRepayDate"));
							result.set("amt", plan[i].getBigDecimal("newRepayAmt"));
							result.set("bj", plan[i].getBigDecimal("newBj"));
							result.set("lx", plan[i].getBigDecimal("newLx"));
							result.set("days", plan[i].getBigDecimal("newDays").intValue());
							result.set("sybj", plan[i].getBigDecimal("newSybj"));
							result.set("amountDetailId", tbConContractInfo.getString("amountDetailId"));
							result.set("bz1", plan[i].getString("xgbz1"));
							result.set("bz2", plan[i].getString("xgbz2"));
							result.set("bz3", plan[i].getString("xgbz3"));
							DatabaseUtil.insertEntity("default", result);
						}
						
					}else if("0".equals(change.getString("isSmall")) && "1".equals(change.getString("isModifyPlan"))){
						//先删除原还款计划
						DataObject tbLoanRepayPlan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanRepayPlan");
						tbLoanRepayPlan.set("loanId", tbLoanSummary.getString("loanId"));
						DatabaseUtil.deleteByTemplate("default", tbLoanRepayPlan);
						
						//再插入新的还款计划
						DataObject tbConRepayplanChange = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
						tbConRepayplanChange.set("changeId", change.get("changeId"));
						tbConRepayplanChange.set("newOrOld", "2");
						DataObject[] plan = DatabaseUtil.queryEntitiesByTemplate("default", tbConRepayplanChange);
						for(int i = 0;i<plan.length;i++){
							DataObject result = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanRepayPlan");
							result.set("repayPlanId", GitUtil.genUUIDString());
							result.set("repayDate", plan[i].getDate("newRepayDate"));
							result.set("repayAmt", plan[i].getBigDecimal("newRepayAmt"));
							result.set("periodsNumber", plan[i].getBigDecimal("newPeriodsNum"));
							result.set("contractId", change.get("contractId"));
							result.set("loanId", tbLoanSummary.getString("loanId"));
							result.set("summaryId", tbLoanSummary.getString("summaryId"));
							result.set("createTime", GitUtil.getBusiTimestamp());
							result.set("updateTime", GitUtil.getBusiTimestamp());
							DatabaseUtil.insertEntity("default", result);
						}
					}
					
				}
				
				//2、调计量接口
				InterAft inter = new InterAft();
				try {
					inter.interChose(change, tbLoanInfo, "");
				} catch (Exception e) {
					e.printStackTrace();
					throw new EOSException(e.getMessage());
				}
				
				
				
			}else {
				throw new EOSException("变更类型为空，请联系系统管理员！");
			}
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			normal.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", normal);
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", expire);

			logger.info("流程提交，开始处理业务数据------bizId=" + applyId + "------>结束!");

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			
			// 删除贷后变更记录
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			if(null != change) {
				logger.info("loanChangeType--->" + change.get("loanChangeType"));
				logger.info("isModifyPlan--->" + change.get("isModifyPlan"));
				if(null != change.get("loanChangeType") && ("09".equals(change.get("loanChangeType")) || "08".equals(change.get("loanChangeType")))) {//贴息
					DataObject tx = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanTx");
					tx.set("changeId", applyId);
					DatabaseUtil.deleteByTemplate("default", tx);
				}
				if((null != change.get("loanChangeType") && "10".equals(change.get("loanChangeType"))) 
						|| (null != change.get("isModifyPlan") && "1".equals(change.get("isModifyPlan")))
						|| (null != change.get("loanChangeType") && "02".equals(change.get("loanChangeType")))) {//还款计划	
					DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.deleteRepayplanCancel", map);
				}
				DatabaseUtil.deleteEntity("default", change);
			}
			
			// 删除首次检查记录
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			DatabaseUtil.expandEntity("default", first);
			if(null != first) {
				DatabaseUtil.deleteEntity("default", first);
			}
			
			// 删除日常检查记录
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			DatabaseUtil.expandEntity("default", normal);
			if(null != normal && null != normal.get("checkType")) {
				logger.info("checkType--->" + normal.get("checkType"));
				if("01".equals(normal.get("checkType"))) {//个人授信后跟踪检查表
					DataObject trackPerson = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftTrackPerson");
					trackPerson.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", trackPerson);
				}
				if("02".equals(normal.get("checkType"))) {//房地产项目跟踪检查表
					DataObject trackHouse = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftTrackHouse");
					trackHouse.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", trackHouse);
				}
				if("03".equals(normal.get("checkType"))) {//贸易型企业跟踪检查表
					DataObject trackTrade = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftTrackTrade");
					trackTrade.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", trackTrade);
				}
				if("04".equals(normal.get("checkType"))) {//生产型企业跟踪检查表
					DataObject trackProduct = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftTrackProduct");
					trackProduct.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", trackProduct);
				}
				if("05".equals(normal.get("checkType"))) {//学校、医院贷款跟踪检查表
					DataObject trackSchhos = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftTrackSchhos");
					trackSchhos.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", trackSchhos);
				}
				if("06".equals(normal.get("checkType"))) {//贸易型企业日常检查报告
					DataObject normalTrade = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalTrade");
					normalTrade.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", normalTrade);
				}
				if("07".equals(normal.get("checkType"))) {//生产型企业日常检查报告
					DataObject normalProduct = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalProduct");
					normalProduct.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", normalProduct);
				}
				if("08".equals(normal.get("checkType"))) {//项目贷款类日常检查报告
					DataObject normalProject = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalProject");
					normalProject.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", normalProject);
				}
				if("09".equals(normal.get("checkType"))) {//委托贷款类日常检查报告
					DataObject normalTrust = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalTrust");
					normalTrust.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", normalTrust);
				}
				if("10".equals(normal.get("checkType"))) {//个人经营类日常检查报告
					DataObject normalPerson = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalPerson");
					normalPerson.set("normalCheckId", applyId);
					DatabaseUtil.deleteByTemplate("default", normalPerson);
				}
				
				//授信条件及落实情况
				DatabaseExt.queryByNamedSql("default", "com.bos.aft.normalCheck.deleteRequireCancel", map);
				
				//设备购置情况
				DatabaseExt.queryByNamedSql("default", "com.bos.aft.normalCheck.deleteEquipmentCancel", map);
				
				//各家银行授信情况
				DatabaseExt.queryByNamedSql("default", "com.bos.aft.normalCheck.deleteCreditConditionCancel", map);

				DatabaseUtil.deleteEntity("default", normal);
			}
			
			// 删除重点检查记录
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			DatabaseUtil.expandEntity("default", point);
			if(null != point) {
				DatabaseUtil.deleteEntity("default", point);
			}
			
			// 删除授信到期前检查记录
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			DatabaseUtil.expandEntity("default", expire);
			if(null != expire) {
				DatabaseUtil.deleteEntity("default", expire);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程撤销删除记录出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程撤销删除记录出错！");
		}


	}
	
	

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {

		Map<String, String> resultMap = new HashMap<String, String>();
		
		
		
		//审批通过
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			// 客户经理提交时向计量系统插入还本计划
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
		    RuleService rs = new RuleService();
			Map paramMap = new HashMap();
		    paramMap.put("changeId", change.getString("changeId"));
		    List<MessageObj> msgList = rs.runRule("RCHA_0001", paramMap);
			  String msg = convertMsg(msgList);
		      //贷后变更意见提交校验是否保存变更信息
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
			 
			String yhze =change.getString("yhze");
			BigDecimal yhbj=change.getBigDecimal("yhbj");
			String yhzclx=change.getString("yhzclx");
			String yhtqlx=change.getString("yhtqlx");
			String yhfx=change.getString("yhfx");
			BigDecimal repayCapital=change.getBigDecimal("repayCapital");
			String repayType=change.getString("repayType");
			String newRepayWay=change.getString("newRepayWay");
			String newInterestCollectType=change.getString("newInterestCollectType");
			String firstPeriods=change.getString("firstPeriods");
			
			if("02".equals(change.get("loanChangeType"))){

			HashMap map = new HashMap();
			map.put("changeId", applyId);
			Object[] objs = DatabaseExt.queryByNamedSql("default",
					"com.bos.aft.conLoanChange.getLoanSummary", map);
			DataObject ms = (DataObject) objs[0];
			BigDecimal m = ms.getBigDecimal("M").setScale(0, BigDecimal.ROUND_CEILING);
			int ys=m.intValue();
			int maxqc=0;
			//System.out.println(ys+"!!"+m+"!!"+newRepayWay+"!!"+newInterestCollectType+"!!"+firstPeriods);
				
				if("0300".equals(newRepayWay) ||"0400".equals(newRepayWay)){
					if("1".equals(newInterestCollectType)){ //  结息周期--月
						maxqc=ys/1;
					}
					if("2".equals(newInterestCollectType)){//  结息周期--季
						maxqc=ys/3;
					}
					
					if("3".equals(newInterestCollectType)){//  结息周期--半年
						maxqc=ys/6;

					}
					
					if("4".equals(newInterestCollectType)){//  结息周期--年
						maxqc=ys/12;

					}
					
					if( (Integer.parseInt(firstPeriods))>maxqc ){
						resultMap.put("errorNum", "2");
				    	resultMap.put("errorCode", "2");
				    	resultMap.put("errorContent", "首次还本期次不能超过还款总期次！");
						
					}
					
				}
			}
 
			
			if(null != change && null != change.getString("loanChangeType")) {
				
				logger.info("=================begin================");
				
				if("10".equals(change.getString("loanChangeType")) 
						|| ("11".equals(change.getString("loanChangeType")) && null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan")))
						|| ("15".equals(change.getString("loanChangeType")) && null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan")))
						|| ("18".equals(change.getString("loanChangeType")) && null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan")))
						|| ("02".equals(change.getString("loanChangeType")) && ("1400".equals(change.getString("newRepayWay")) || "1410".equals(change.getString("newRepayWay"))))) {
					
					logger.info("========>" + change.get("loanChangeType"));
					//  if("1400".equals(change.getString("newRepayWay"))||"1410".equals(change.getString("newRepayWay"))) {
						 msgList = rs.runRule("RCON_0086", paramMap);
						 msg = convertMsg(msgList);
					      //还款计划不同期次还款日期不能一样
					        msg = convertMsg(msgList);
					        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
					        	resultMap.put("errorNum", "2");
					        	resultMap.put("errorCode", "2");
					        	resultMap.put("errorContent", msg);
					        	return resultMap;
					        }
				       // }
					InterToJL jl = new InterToJL();
					jl.insertJL(change);
					
					logger.info("=================end================");
					
				}
				
				resultMap.put("loanChangeType", change.getString("loanChangeType"));
			}
			

			if("11".equals(change.getString("loanChangeType")) || "15".equals(change.getString("loanChangeType")) || "18".equals(change.getString("loanChangeType"))){
			//	resultMap.put("errorNum", "3");
			//	resultMap.put("errorCode", "3");
				//if("02".equals(repayType)){
				//resultMap.put("errorContent", "本次提前还款总额为"+yhze+"元，其中归还本金"+repayCapital.add(yhbj)+"元、正常利息"+yhzclx+"元、拖欠利息"+yhtqlx+"元、罚息"+yhfx+"元");
				//}else{
				//resultMap.put("errorContent", "本次提前还款总额为"+yhze+"元，其中归还本金"+yhbj+"元、正常利息"+yhzclx+"元、拖欠利息"+yhtqlx+"元、罚息"+yhfx+"元");
				//resultMap.put("errorContent", "本次提前还款总额为"+yhze+"元");
				//}
			}
			
			//重点检查校验
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			DatabaseUtil.expandEntity("default", point);
			String problemDes=point.getString("problemDes");
			String bankLeader=point.getString("bankLeader");
			String workLeader=point.getString("workLeader");
			if(null != point.getString("partyId")) {
				if(null==problemDes || null==bankLeader || null==workLeader){
					resultMap.put("errorNum", "2");
			    	resultMap.put("errorCode", "2");
			    	resultMap.put("errorContent", "请将内容填写完整！");
					
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorNum", "2");
	    	resultMap.put("errorCode", "2");
	    	resultMap.put("errorContent", e.getMessage());
		} 
		
		return resultMap;
	}
	private String convertMsg(List<MessageObj> msgList){
        StringBuffer sf = new StringBuffer();
        if(msgList != null && !msgList.isEmpty()){
            for (int i = 0; i < msgList.size(); i++) {
                MessageObj t = msgList.get(i);
                if(EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())){
                    sf.append("[(" + (i+1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
                }
            }
        }
        if (sf.length() > 0) {
            return sf.toString();
        }
        return "true";
    }
}
