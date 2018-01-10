package com.bos.aftPro;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.inter.InterAft;
import com.bos.inter.InterToJL;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.util.RuleException;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.crmsgj.GJS01501030000003Req;
import com.primeton.crmsgj.GJS01501030000003Res;
import com.primeton.crmsgj.GJS01501030000005Req;
import com.primeton.crmsgj.GJS01501030000005Res;
import com.primeton.crmsgj.GJS01501030000009Req;
import com.primeton.crmsgj.GJS01501030000009Res;
import com.primeton.crmsgj.client.CrmsMgrCallGjImpl;
import com.primeton.crmsgj.client.CrmsMgrCallGjProxy;
import com.primeton.workflow.api.WFServiceException;
import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		
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
			
			if(null != change && null != change.getString("changeStatus") && !"04".equals(change.getString("changeStatus"))) {
				
				if(null != change && null != change.getString("loanChangeType")) {
					
					DataObject tbLoanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					tbLoanSummary.set("summaryId", change.getString("summaryId"));
					DatabaseUtil.expandEntity("default", tbLoanSummary);
					DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
					tbLoanInfo.set("loanId", tbLoanSummary.getString("loanId"));
					DatabaseUtil.expandEntity("default", tbLoanInfo);
					
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
					}
					
					//调接口
					/*InterAft inter = new InterAft();
					try {
						inter.interChose(change, tbLoanInfo);
					} catch (Exception e) {
						e.printStackTrace();
						throw new EOSException(e.getMessage());
					}*/
					
					//1、修改管理数据
					change.set("changeStatus", "03");
					if("01".equals(change.get("loanChangeType"))) {//修改借据利率信息
						
						DataObject tbLoanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
						tbLoanLoanrate.set("loanId", tbLoanSummary.getString("loanId"));
						DatabaseUtil.expandEntityByTemplate("default", tbLoanLoanrate, tbLoanLoanrate);
						
						tbLoanLoanrate.set("rateType", change.getString("newRateType"));
						//tbLoanLoanrate.set("floatType", change.getString("newFloatType"));
						tbLoanLoanrate.set("floatWay", change.getString("newFloatWay"));
						tbLoanLoanrate.set("baseRateValue", change.getString("newBaseRateValue"));
						tbLoanLoanrate.set("rateFloatProportion", change.getBigDecimal("newRateFloatProportion"));
						tbLoanLoanrate.set("yearRate", change.getBigDecimal("newYearRate"));
						tbLoanLoanrate.set("irUpdateFrequency", change.getString("newIrUpdateFrequency"));
						//tbLoanLoanrate.set("interestCollectType", change.getString("newInterestCollectType"));
						DatabaseUtil.updateEntity("default", tbLoanLoanrate);
						
					}else if("02".equals(change.get("loanChangeType"))) {//修改借据还款方式
						
						tbLoanInfo.set("repayType", change.getString("newRepayWay"));
						DatabaseUtil.updateEntity("default", tbLoanInfo);
						
						DataObject tbLoanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
						tbLoanLoanrate.set("loanId", tbLoanSummary.getString("loanId"));
						DatabaseUtil.expandEntityByTemplate("default", tbLoanLoanrate, tbLoanLoanrate);
						
						tbLoanLoanrate.set("interestCollectType", change.getString("newInterestCollectType"));
						DatabaseUtil.updateEntity("default", tbLoanLoanrate);
						
						if("1".equals(change.getString("isSmall")) && ("1400".equals(change.getString("newRepayWay")) || "1410".equals(change.getString("newRepayWay")))) {//小贷
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
							
						}else if("0".equals(change.getString("isSmall")) && ("1400".equals(change.getString("newRepayWay")) || "1410".equals(change.getString("newRepayWay")))) {
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
						
					}else if("03".equals(change.get("loanChangeType"))) {//修改借据还款日
						
						tbLoanInfo.set("specPaymentDate", change.getInt("newRepayDay"));
						DatabaseUtil.updateEntity("default", tbLoanInfo);
						
					}else if("04".equals(change.get("loanChangeType"))) {//修改借据还款账号
						
						DataObject tbLoanZh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
						tbLoanZh.set("loanId", tbLoanSummary.getString("loanId"));
						if("01008001".equals(tbLoanInfo.getString("productType")) || "01006001".equals(tbLoanInfo.getString("productType"))
								|| "01006002".equals(tbLoanInfo.getString("productType"))
								|| "01008010".equals(tbLoanInfo.getString("productType")) || "01006010".equals(tbLoanInfo.getString("productType"))//村镇银行贴现产品
								|| "01007008".equals(tbLoanInfo.getString("productType")) || "01009001".equals(tbLoanInfo.getString("productType")) 
								|| "01009002".equals(tbLoanInfo.getString("productType")) || "01009010".equals(tbLoanInfo.getString("productType")) || "01011001".equals(tbLoanInfo.getString("productType")) 
								|| "01012001".equals(tbLoanInfo.getString("productType")) || "01004001".equals(tbLoanInfo.getString("productType"))
								|| "01008002".equals(tbLoanInfo.getString("productType"))) {
							tbLoanZh.set("zhlx", "2");
						}else {
							tbLoanZh.set("zhlx", "1");
						}
						DatabaseUtil.expandEntityByTemplate("default", tbLoanZh, tbLoanZh);
						
						tbLoanZh.set("zh", change.getString("newRepayAccount"));
						tbLoanZh.set("zhmc", change.getString("newZhmc"));
						tbLoanZh.set("zhkhjg", change.getString("newZhkhjg"));
						DatabaseUtil.updateEntity("default", tbLoanZh);
						
					}else if("06".equals(change.get("loanChangeType")) || "19".equals(change.get("loanChangeType"))) {//修改借据期限
						
						//String ls = "";
						InterToJL sub = new InterToJL();
						//ls = sub.getLoanSubject(tbLoanInfo.getString("loanId"),change.getString("changeId"));
						//logger.info("ls--->" + ls);
						//协议编号  机构号(4位)+业务属性（2位）+年份(4位)+顺序号(6位)
						String periodNum = "ZQ"+GitUtil.getOrgCode()+"06"+GitUtil.getBusiDateYYYYMMDD().substring(0, 4)+BizNumGenerator.genSysTrcNo().substring(2);
						//更新利率表---新的利率信息
						if("03".equals(change.getString("termChangeWay"))) {//正常展期

							DataObject tbLoanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
							tbLoanLoanrate.set("loanId", tbLoanSummary.getString("loanId"));
							DatabaseUtil.expandEntityByTemplate("default", tbLoanLoanrate, tbLoanLoanrate);
							
							tbLoanLoanrate.set("rateType", change.getString("newRateType"));
							tbLoanLoanrate.set("floatWay", change.getString("newFloatWay"));
							tbLoanLoanrate.set("baseRateValue", change.getString("newBaseRateValue"));
							tbLoanLoanrate.set("rateFloatProportion", change.getBigDecimal("newRateFloatProportion"));
							tbLoanLoanrate.set("yearRate", change.getBigDecimal("newYearRate"));
							DatabaseUtil.updateEntity("default", tbLoanLoanrate);
							tbLoanSummary.set("periodFlag", "1");//展期标志
							DatabaseUtil.updateEntity("default", tbLoanSummary);
							change.set("periodNum", periodNum);
							change.set("periodState", "02");//展期后的日期 02-未执行
						}else if("04".equals(change.getString("termChangeWay"))) {//逾期展期

							DataObject tbLoanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
							tbLoanLoanrate.set("loanId", tbLoanSummary.getString("loanId"));
							DatabaseUtil.expandEntityByTemplate("default", tbLoanLoanrate, tbLoanLoanrate);
							tbLoanLoanrate.set("rateType", change.getString("newRateType"));
							tbLoanLoanrate.set("floatWay", change.getString("newFloatWay"));
							tbLoanLoanrate.set("baseRateValue", change.getString("newBaseRateValue"));
							tbLoanLoanrate.set("rateFloatProportion", change.getBigDecimal("newRateFloatProportion"));
							tbLoanLoanrate.set("yearRate", change.getBigDecimal("newYearRate"));
							DatabaseUtil.updateEntity("default", tbLoanLoanrate);
							Map mp = new HashMap();
							mp.put("summaryId", tbLoanSummary.getString("summaryId"));
							Object[] queryChageId = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.utp.rights.funds.queryChangeId",mp);
							if(queryChageId.length>0){
								Map maps = (Map) queryChageId[0];
								String changesId = (String) maps.get("CHANGE_ID");
								DataObject changes = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
								changes.set("changeId", changesId);
								changes.set("periodState", "03");
								DatabaseUtil.updateEntity("default", changes);
							}
							change.set("periodNum", periodNum);
							change.set("periodState", "01");//展期后的日期 01-执行
							tbLoanSummary.set("periodFlag", "1");//展期标志
							tbLoanSummary.set("exextFlg", "03");//展期相对后一次是否失效
							//更新借据表---新的到期日
							//tbLoanSummary.set("summaryTerm", change.getInt("newTerm"));
							tbLoanSummary.set("endDate", change.getDate("newEndDate"));//新的借据结束日期
							//tbLoanSummary.set("periodNum", periodNum);//协议编号
							DatabaseUtil.updateEntity("default", tbLoanSummary);
							//更新出账表---新的到期日
							//tbLoanInfo.set("loanTerm", change.getInt("newTerm"));
							tbLoanInfo.set("endDate", change.getDate("newEndDate"));
							//tbLoanInfo.set("loanSubject1", ls);
							DatabaseUtil.updateEntity("default", tbLoanInfo);
						}else{
							tbLoanSummary.set("endDate", change.getDate("newEndDate"));
							tbLoanSummary.set("periodNum", periodNum);//协议编号
							DatabaseUtil.updateEntity("default", tbLoanSummary);
							tbLoanInfo.set("endDate", change.getDate("newEndDate"));
							DatabaseUtil.updateEntity("default", tbLoanInfo);
						}
						//国结的期限变更---要发送到国结系统
						if("01007001".equals(tbLoanInfo.getString("productType")) || "01007003".equals(tbLoanInfo.getString("productType"))|| 
						   "01007004".equals(tbLoanInfo.getString("productType")) || "01007009".equals(tbLoanInfo.getString("productType"))|| 
						   "01007012".equals(tbLoanInfo.getString("productType")) || "01007011".equals(tbLoanInfo.getString("productType"))|| 
						   "01007006".equals(tbLoanInfo.getString("productType")) || "01007005".equals(tbLoanInfo.getString("productType"))|| 
						   "01007014".equals(tbLoanInfo.getString("productType")) || "01007013".equals(tbLoanInfo.getString("productType"))||
						   "01007010".equals(tbLoanInfo.getString("productType"))){
							//调用国结---进口信用证修改接口add by shendl
							CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
							GJS01501030000009Req gjS01501030000009Req = new GJS01501030000009Req();
							gjS01501030000009Req.setDebitNo(tbLoanSummary.getString("summaryNum"));//借据号
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							try {
								gjS01501030000009Req.setExtendDate(sdf.format(change.getDate("changeDate")));//展期日期 
								gjS01501030000009Req.setExtMatureDate(sdf.format(change.getDate("newEndDate")));
							} catch (Exception e) {
								e.printStackTrace();
								throw new EOSException("调用国结系统[融资展期接口]发生异常！");
							}//展期后到期日
							gjS01501030000009Req.setExtInterestRate(change.getString("newYearRate"));//展期后利率
							String str = change.getString("newOverdueRate");
							BigDecimal n = new BigDecimal(str);
							BigDecimal a1 = new BigDecimal(100);
							BigDecimal a2 = new BigDecimal(1);
							BigDecimal res = new BigDecimal(0);
							res = change.getBigDecimal("newYearRate").multiply(n.divide(a1).add(a2));
							gjS01501030000009Req.setOveInterestRate(res.toString());//新的逾期利率
							GJS01501030000009Res gjS01501030000009Res = new GJS01501030000009Res();
							gjS01501030000009Res = crmsMgrCallGjImpl.executeS01501030000009(gjS01501030000009Req);
							String returnCode = gjS01501030000009Res.getGjS01501030000009ResBody().getTransStat();
							if(null==returnCode){
								throw new EOSException("调用国结系统[融资展期接口]发生异常！");
							}
							if(!"0000".equals(returnCode)){
								throw new EOSException(gjS01501030000009Res.getGjS01501030000009ResBody().getErrMsg());
							}
						}
					}else if("08".equals(change.get("loanChangeType"))) {//贴息主体变更
						
						DataObject bizTx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTx");
						DataObject conLoanTx = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanTx");
						conLoanTx.set("changeId", applyId);
						conLoanTx.set("bglx", "2");
						DataObject[] conLoanTxs = DatabaseUtil.queryEntitiesByTemplate("default", conLoanTx);
						bizTx.set("applyId", conLoanTxs[0].getString("applyId"));
 						DatabaseUtil.deleteByTemplate("default",bizTx);

						for (int i = 0; i < conLoanTxs.length; i++) {
							  conLoanTx = conLoanTxs[i];
							  DataObject bizTxs = DataObjectUtil.convertDataObject(conLoanTx,"com.bos.dataset.biz.TbBizTx", true);
 							DatabaseUtil.insertEntity("default", bizTxs);


						}
						
						
					}else if("09".equals(change.get("loanChangeType"))) {//贴息、暂停贴息
						
						tbLoanSummary.set("tiexiStatus", change.getString("newTiexiStatus"));
						DatabaseUtil.updateEntity("default", tbLoanSummary);
						
					}else if("17".equals(change.get("loanChangeType"))) {// 收本收息账号变更
						
						tbLoanSummary.set("entrustReturnPrincipalAcc", change.getString("newWtrhbzh"));
						tbLoanSummary.set("entrustReturnInterestAcc", change.getString("newWtrhxzh"));

						DatabaseUtil.updateEntity("default", tbLoanSummary);
						
					}else if("10".equals(change.get("loanChangeType"))) {//修改还本计划
						
						if("1".equals(change.getString("isSmall"))) {//小贷
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
							
						}else {
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
						
					}else if("11".equals(change.get("loanChangeType")) ||"15".equals(change.getString("loanChangeType")) ||"18".equals(change.getString("loanChangeType"))) {//提前还款
						
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
							
						}else if("0".equals(change.getString("isSmall")) && "1".equals(change.getString("isModifyPlan"))) {
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
						
						
					}else if("12".equals(change.get("loanChangeType"))) {//信用证修改
						//合同信息
						DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
						tbConContractInfo.set("contractId", tbLoanInfo.getString("contractId"));
						DatabaseUtil.expandEntity("default", tbConContractInfo);
						/**额度的处理---先不做处理待国结反馈处理成功以后再做相关处理
						BigDecimal oldje = new BigDecimal(0);
						oldje = tbLoanSummary.getBigDecimal("summaryAmt");//修改前借据金额
						logger.info("oldje--->" + oldje);
						BigDecimal ce = new BigDecimal(0);//差额
						BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
						BigDecimal conje = new BigDecimal(0);//修改后合同金额
						BigDecimal conye = new BigDecimal(0);//修改后合同余额
						BigDecimal conYeE = new BigDecimal(0);//合同余额
						BigDecimal hl = new BigDecimal(1);//汇率
						if(null != tbConContractInfo.getBigDecimal("conYuE")) {
							conYeE = tbConContractInfo.getBigDecimal("conYuE");
						}
						if(null != tbLoanSummary.getBigDecimal("exchangeRate")) {
							hl = tbLoanSummary.getBigDecimal("exchangeRate");
						}
						
						HashMap map = new HashMap();
						map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
						
						if(oldje.compareTo(change.getBigDecimal("newSummaryamt")) < 0) {//金额增加
							logger.info("金额增加--->");
							ce = change.getBigDecimal("newSummaryamt").subtract(oldje);
							newjjye = tbLoanSummary.getBigDecimal("jjye").add(ce);
							//conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
							conye = conYeE.add(ce);
							
							map.put("ce", ce.multiply(hl));
							DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
						}else {//金额减少
							logger.info("金额减少--->" + oldje);
							ce = oldje.subtract(change.getBigDecimal("newSummaryamt"));
							newjjye = tbLoanSummary.getBigDecimal("jjye").subtract(ce);
							//conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
							conye = conYeE.subtract(ce);
							
							map.put("ce", ce.multiply(hl));
							DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
						}
						logger.info("--->" + ce + "--->" + newjjye+"--->" + conje+"--->" + conye);
						*/
						//国际信用证开证---新的数据
						DataObject tbConGjxyzkz = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGjxyzkz");
						tbConGjxyzkz.set("contractId", change.getString("contractId"));
						DatabaseUtil.expandEntityByTemplate("default", tbConGjxyzkz, tbConGjxyzkz);
						/**信用证的数据先不做修改 待国结处理以后做修改
						tbConGjxyzkz.set("dqrq", change.getDate("newXyzDqrq"));
						DatabaseUtil.updateEntity("default", tbConGjxyzkz);
						*/
						//tbLoanSummary.set("summaryTerm", change.getInt("newTerm"));
						//tbLoanSummary.set("endDate", change.getDate("newEndDate"));//不修改借据表，只修改信用证表信用证有郊期
						/**借据的数据待国结反馈成功以后再做处理
						tbLoanSummary.set("summaryAmt", change.getBigDecimal("newSummaryamt"));
						tbLoanSummary.set("jjye", newjjye);
						tbLoanSummary.set("rmbAmt", change.getBigDecimal("newSummaryamt").multiply(hl));
						DatabaseUtil.updateEntity("default", tbLoanSummary);
						*/
						//tbConContractInfo.set("contractAmt", conje);//不修改合同金额
						//tbConContractInfo.set("conYuE", conye);
						//tbConContractInfo.set("conBalance", tbConContractInfo.getBigDecimal("contractAmt").subtract(conye));
						//DatabaseUtil.updateEntity("default", tbConContractInfo);
						//logger.info("--->");
						
						//调用国结---进口信用证修改接口add by shendl
						CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
						GJS01501030000003Req gjS01501030000003Req = new GJS01501030000003Req();
						gjS01501030000003Req.setAfModiAmt(change.getString("newKzje"));//修改后的金额
						gjS01501030000003Req.setDealBrch(change.getString("orgNum"));//执行机构号
						gjS01501030000003Req.setDebitNo(tbLoanSummary.getString("summaryNum"));//借据号
						gjS01501030000003Req.setContractNo(tbConContractInfo.getString("contractNum"));//合同编号
						gjS01501030000003Req.setBondAcct(tbConGjxyzkz.getString("bzjzh"));//保证金账号
						BigDecimal newBzjje = change.getBigDecimal("newBzjje");
						BigDecimal oldBzjje = change.getBigDecimal("oldBzjje");
						BigDecimal ce = BigDecimal.ZERO;
						if(newBzjje!=null&&oldBzjje!=null){
							ce = newBzjje.subtract(oldBzjje);
						}
						if(ce==BigDecimal.ZERO){
							gjS01501030000003Req.setBondAmt("");
						}else{
							gjS01501030000003Req.setBondAmt(ce.toString());//保证金金额---差额
						}
						gjS01501030000003Req.setBondCurr(tbConGjxyzkz.getString("bzjbz"));//保证金币种
						gjS01501030000003Req.setBondRate(change.getString("newBzjblbdy"));//保证金比例
						gjS01501030000003Req.setForwDay(tbConGjxyzkz.getString("yqts"));//远期天数
						//即期 远期处理 期限类型处理
						if("1".equals(gjS01501030000003Req.getLetOfCreDate())){//即期
							gjS01501030000003Req.setLetOfCreDate("0");
							gjS01501030000003Req.setMatuType("001");
						}else if("2".equals(gjS01501030000003Req.getLetOfCreDate())){//远期
							gjS01501030000003Req.setLetOfCreDate("1");
							gjS01501030000003Req.setMatuType("002");
						}
						String dqrq = change.getString("newXyzDqrq").substring(0,10);
						dqrq = dqrq.replace("-", "");
						gjS01501030000003Req.setNewExp(dqrq);//新到期日期
						gjS01501030000003Req.setCredNo(tbLoanSummary.getString("ywbh"));//信用证号
						DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
						String partyId = tbConContractInfo.getString("partyId"); 
						party.set("partyId", partyId);
						DatabaseUtil.expandEntity("default", party);
						gjS01501030000003Req.setIoaner(party.getString("ecifPartyNum"));//贷款申请人
						
						//保证金币种
						if(null != gjS01501030000003Req.getBondCurr() && !"".equals(gjS01501030000003Req.getBondCurr())){
							if(gjS01501030000003Req.getBondCurr().equals("CNY")){
								gjS01501030000003Req.setBondCurr("01");
							}else if(gjS01501030000003Req.getBondCurr().equals("FRF")){//法国法郎
								gjS01501030000003Req.setBondCurr("250");
							}else if(gjS01501030000003Req.getBondCurr().equals("DEM")){//德国马克
								gjS01501030000003Req.setBondCurr("276");
							}else if(gjS01501030000003Req.getBondCurr().equals("HKD")){//港币
								gjS01501030000003Req.setBondCurr("13");
							}else if(gjS01501030000003Req.getBondCurr().equals("ITL")){//意大利里拉
								gjS01501030000003Req.setBondCurr("380");
							}else if(gjS01501030000003Req.getBondCurr().equals("JPY")){//日元
								gjS01501030000003Req.setBondCurr("27");
							}else if(gjS01501030000003Req.getBondCurr().equals("KRW")){//韩国元
								gjS01501030000003Req.setBondCurr("410");
							}else if(gjS01501030000003Req.getBondCurr().equals("MOP")){//澳门元
								gjS01501030000003Req.setBondCurr("81");
							}else if(gjS01501030000003Req.getBondCurr().equals("MYR")){//马来西亚币
								gjS01501030000003Req.setBondCurr("458");
							}else if(gjS01501030000003Req.getBondCurr().equals("NLG")){//荷兰盾
								gjS01501030000003Req.setBondCurr("528");
							}else if(gjS01501030000003Req.getBondCurr().equals("NZD")){//新西兰元 
								gjS01501030000003Req.setBondCurr("554");
							}else if(gjS01501030000003Req.getBondCurr().equals("AUD")){//澳洲元
								gjS01501030000003Req.setBondCurr("29");
							}else if(gjS01501030000003Req.getBondCurr().equals("NOK")){//挪威克朗
								gjS01501030000003Req.setBondCurr("578");
							}else if(gjS01501030000003Req.getBondCurr().equals("PHP")){//菲律宾比索
								gjS01501030000003Req.setBondCurr("608");
							}else if(gjS01501030000003Req.getBondCurr().equals("RUB")){//卢布
								gjS01501030000003Req.setBondCurr("643");
							}else if(gjS01501030000003Req.getBondCurr().equals("SGD")){//新加坡元
								gjS01501030000003Req.setBondCurr("32");
							}else if(gjS01501030000003Req.getBondCurr().equals("ESP")){//西班牙比塞塔
								gjS01501030000003Req.setBondCurr("724");
							}else if(gjS01501030000003Req.getBondCurr().equals("SEK")){//瑞典克朗
								gjS01501030000003Req.setBondCurr("752");
							}else if(gjS01501030000003Req.getBondCurr().equals("CHF")){//瑞士法郎
								gjS01501030000003Req.setBondCurr("15");
							}else if(gjS01501030000003Req.getBondCurr().equals("THB")){//泰国铢
								gjS01501030000003Req.setBondCurr("764");
							}else if(gjS01501030000003Req.getBondCurr().equals("GBP")){//英镑
								gjS01501030000003Req.setBondCurr("12");
							}else if(gjS01501030000003Req.getBondCurr().equals("USD")){//美元
								gjS01501030000003Req.setBondCurr("14");
							}else if(gjS01501030000003Req.getBondCurr().equals("EUR")){//欧元
								gjS01501030000003Req.setBondCurr("38");
							}else if(gjS01501030000003Req.getBondCurr().equals("ATS")){//奥地利先令
								gjS01501030000003Req.setBondCurr("040");
							}else if(gjS01501030000003Req.getBondCurr().equals("OTHER")){//其他
								gjS01501030000003Req.setBondCurr("999");
							}else if(gjS01501030000003Req.getBondCurr().equals("BEF")){//比利时法郎
								gjS01501030000003Req.setBondCurr("056");
							}else if(gjS01501030000003Req.getBondCurr().equals("CAD")){//加拿大元
								gjS01501030000003Req.setBondCurr("28");
							}else if(gjS01501030000003Req.getBondCurr().equals("TWD")){//新台湾币
								gjS01501030000003Req.setBondCurr("158");
							}else if(gjS01501030000003Req.getBondCurr().equals("DKK")){//丹麦克朗
								gjS01501030000003Req.setBondCurr("208");
							}else if(gjS01501030000003Req.getBondCurr().equals("FIM")){//芬兰马克
								gjS01501030000003Req.setBondCurr("246");
							}else{
								throw new EOSException("不支持的币种");
							}
						}
						GJS01501030000003Res gjS01501030000003Res = new GJS01501030000003Res();
						gjS01501030000003Res = crmsMgrCallGjImpl.executeS01501030000003(gjS01501030000003Req);
						String returnCode = gjS01501030000003Res.getGjS01501030000003ResBody().getTransStat();
						if(null==returnCode){
							throw new EOSException("调用国结系统[信用证修改接口]发生异常！");
						}
						if(!"0000".equals(returnCode)){
							throw new EOSException(gjS01501030000003Res.getGjS01501030000003ResBody().getErrMsg());
						}
						//贷后变更表的变更状态更新为审批中02---相当于交给国结审批
						change.set("changeStatus", "10");//审批中
						//贷后变更信用证修改---发送给国结以后  国结会返回一个修改流水  如果国结需要冲正该笔修改  需要用到该流水
						change.set("gjFlowNo", gjS01501030000003Res.getGjS01501030000003ResBody().getKnotSeqNo());
						DatabaseUtil.updateEntity("default", change);
					}else if("13".equals(change.get("loanChangeType"))) {//保函修改
						//合同信息
						DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
						tbConContractInfo.set("contractId", tbLoanInfo.getString("contractId"));
						DatabaseUtil.expandEntity("default", tbConContractInfo);
						
						/**额度的处理---待国结反馈消息以后再做先关处理
						BigDecimal oldje = new BigDecimal(0);
						oldje = tbLoanSummary.getBigDecimal("summaryAmt");//修改前借据金额
						logger.info("oldje--->" + oldje);
						BigDecimal ce = new BigDecimal(0);//差额
						BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
						BigDecimal conje = new BigDecimal(0);//修改后合同金额
						BigDecimal conye = new BigDecimal(0);//修改后合同余额
						BigDecimal conYeE = new BigDecimal(0);//合同余额
						BigDecimal hl = new BigDecimal(1);//汇率
						if(null != tbConContractInfo.getBigDecimal("conYuE")) {
							conYeE = tbConContractInfo.getBigDecimal("conYuE");
						}
						if(null != tbLoanSummary.getBigDecimal("exchangeRate")) {
							hl = tbLoanSummary.getBigDecimal("exchangeRate");
						}
						
						HashMap map = new HashMap();
						map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
						
						if(oldje.compareTo(change.getBigDecimal("newSummaryamt")) < 0) {//金额增加
							logger.info("金额增加--->");
							ce = change.getBigDecimal("newSummaryamt").subtract(oldje);
							newjjye = tbLoanSummary.getBigDecimal("jjye").add(ce);
							//conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
							conye = conYeE.add(ce);
							
							map.put("ce", ce.multiply(hl));
							DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
						}else {//金额减少
							logger.info("金额减少--->" + oldje);
							ce = oldje.subtract(change.getBigDecimal("newSummaryamt"));
							newjjye = tbLoanSummary.getBigDecimal("jjye").subtract(ce);
							//conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
							conye = conYeE.subtract(ce);
							
							map.put("ce", ce.multiply(hl));
							DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
						}
						logger.info("--->" + ce + "--->" + newjjye+"--->" + conje+"--->" + conye);
						*/
						
						DataObject tbConJkbh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConJkbh");
						tbConJkbh.set("contractId", change.getString("contractId"));
						DatabaseUtil.expandEntityByTemplate("default", tbConJkbh, tbConJkbh);
						/**国际保函的数据---先不做真正的修改  修改数据先保存到变更表 待国结确认以后做修改
						tbConJkbh.set("dqrq", change.getDate("newBhDqrq"));
						tbConJkbh.set("bzjblbdy", change.get("newBhBzjblbdy"));
						tbConJkbh.set("bzjje", change.get("newBhBzjje"));
						DatabaseUtil.updateEntity("default", tbConJkbh);
						*/
						//tbLoanSummary.set("summaryTerm", change.getInt("newTerm"));
						//tbLoanSummary.set("endDate", change.getDate("newEndDate"));//不修改借据表，只修改信用证表信用证有郊期
						/**借据表的数据---先不做真正的修改  修改数据先保存到变更表 待国结确认以后做修改
						tbLoanSummary.set("summaryAmt", change.getBigDecimal("newSummaryamt"));
						tbLoanSummary.set("jjye", newjjye);
						tbLoanSummary.set("rmbAmt", change.getBigDecimal("newSummaryamt").multiply(hl));
						DatabaseUtil.updateEntity("default", tbLoanSummary);
						*/
						//tbConContractInfo.set("contractAmt", conje);//不修改合同金额
						//tbConContractInfo.set("conYuE", conye);
						//tbConContractInfo.set("conBalance", tbConContractInfo.getBigDecimal("contractAmt").subtract(conye));
						//DatabaseUtil.updateEntity("default", tbConContractInfo);
						//logger.info("--->");
						
						//调用国结---保函修改接口 add by shendl 将修改信息发送给国结系统
						CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
						GJS01501030000005Req gjS01501030000005Req = new GJS01501030000005Req();
						gjS01501030000005Req.setDebitNo(tbLoanSummary.getString("summaryNum"));//借据号
						gjS01501030000005Req.setContractNo(tbConContractInfo.getString("contractNum"));//合同编号
						gjS01501030000005Req.setCurrency(tbLoanSummary.getString("summaryCurrencyCd"));//币种
						gjS01501030000005Req.setBondAcct(tbConJkbh.getString("bzjzh"));//保证金账号
						gjS01501030000005Req.setBondCurr(tbConJkbh.getString("bzjbz"));//保证金币种
						gjS01501030000005Req.setDealBrch(change.getString("orgNum"));//执行机构号
						gjS01501030000005Req.setAmt(change.getString("newSummaryamt"));//金额
						gjS01501030000005Req.setGrantType(change.getString("newBhBhlx"));//保函类型
						
						BigDecimal newBzjje = change.getBigDecimal("newBhBzjje");
						BigDecimal oldBzjje = change.getBigDecimal("oldBhBzjje");
						BigDecimal ce = BigDecimal.ZERO;
						if(newBzjje!=null&&oldBzjje!=null){
							ce = newBzjje.subtract(oldBzjje);
						}
						if(ce==BigDecimal.ZERO){
							gjS01501030000005Req.setBondAmt("");
						}else{
							gjS01501030000005Req.setBondAmt(ce.toString());//保证金金额---差额
						}
						gjS01501030000005Req.setBondRate(change.getString("newBhBzjblbdy"));//保证金比例
						String dqrq = change.getString("newBhDqrq").substring(0,10);
						dqrq = dqrq.replace("-", "");
						gjS01501030000005Req.setMatuDat(dqrq);//新到期日期
						//币种转换
						if(gjS01501030000005Req.getCurrency().equals("CNY")){
							gjS01501030000005Req.setCurrency("01");
						}else if(gjS01501030000005Req.getCurrency().equals("FRF")){//法国法郎
							gjS01501030000005Req.setCurrency("250");
						}else if(gjS01501030000005Req.getCurrency().equals("DEM")){//德国马克
							gjS01501030000005Req.setCurrency("276");
						}else if(gjS01501030000005Req.getCurrency().equals("HKD")){//港币
							gjS01501030000005Req.setCurrency("13");
						}else if(gjS01501030000005Req.getCurrency().equals("ITL")){//意大利里拉
							gjS01501030000005Req.setCurrency("380");
						}else if(gjS01501030000005Req.getCurrency().equals("JPY")){//日元
							gjS01501030000005Req.setCurrency("27");
						}else if(gjS01501030000005Req.getCurrency().equals("KRW")){//韩国元
							gjS01501030000005Req.setCurrency("410");
						}else if(gjS01501030000005Req.getCurrency().equals("MOP")){//澳门元
							gjS01501030000005Req.setCurrency("81");
						}else if(gjS01501030000005Req.getCurrency().equals("MYR")){//马来西亚币
							gjS01501030000005Req.setCurrency("458");
						}else if(gjS01501030000005Req.getCurrency().equals("NLG")){//荷兰盾
							gjS01501030000005Req.setCurrency("528");
						}else if(gjS01501030000005Req.getCurrency().equals("NZD")){//新西兰元 
							gjS01501030000005Req.setCurrency("554");
						}else if(gjS01501030000005Req.getCurrency().equals("AUD")){//澳洲元
							gjS01501030000005Req.setCurrency("29");
						}else if(gjS01501030000005Req.getCurrency().equals("NOK")){//挪威克朗
							gjS01501030000005Req.setCurrency("578");
						}else if(gjS01501030000005Req.getCurrency().equals("PHP")){//菲律宾比索
							gjS01501030000005Req.setCurrency("608");
						}else if(gjS01501030000005Req.getCurrency().equals("RUB")){//卢布
							gjS01501030000005Req.setCurrency("643");
						}else if(gjS01501030000005Req.getCurrency().equals("SGD")){//新加坡元
							gjS01501030000005Req.setCurrency("32");
						}else if(gjS01501030000005Req.getCurrency().equals("ESP")){//西班牙比塞塔
							gjS01501030000005Req.setCurrency("724");
						}else if(gjS01501030000005Req.getCurrency().equals("SEK")){//瑞典克朗
							gjS01501030000005Req.setCurrency("752");
						}else if(gjS01501030000005Req.getCurrency().equals("CHF")){//瑞士法郎
							gjS01501030000005Req.setCurrency("15");
						}else if(gjS01501030000005Req.getCurrency().equals("THB")){//泰国铢
							gjS01501030000005Req.setCurrency("764");
						}else if(gjS01501030000005Req.getCurrency().equals("GBP")){//英镑
							gjS01501030000005Req.setCurrency("12");
						}else if(gjS01501030000005Req.getCurrency().equals("USD")){//美元
							gjS01501030000005Req.setCurrency("14");
						}else if(gjS01501030000005Req.getCurrency().equals("EUR")){//欧元
							gjS01501030000005Req.setCurrency("38");
						}else if(gjS01501030000005Req.getCurrency().equals("ATS")){//奥地利先令
							gjS01501030000005Req.setCurrency("040");
						}else if(gjS01501030000005Req.getCurrency().equals("OTHER")){//其他
							gjS01501030000005Req.setCurrency("999");
						}else if(gjS01501030000005Req.getCurrency().equals("BEF")){//比利时法郎
							gjS01501030000005Req.setCurrency("056");
						}else if(gjS01501030000005Req.getCurrency().equals("CAD")){//加拿大元
							gjS01501030000005Req.setCurrency("28");
						}else if(gjS01501030000005Req.getCurrency().equals("TWD")){//新台湾币
							gjS01501030000005Req.setCurrency("158");
						}else if(gjS01501030000005Req.getCurrency().equals("DKK")){//丹麦克朗
							gjS01501030000005Req.setCurrency("208");
						}else if(gjS01501030000005Req.getCurrency().equals("FIM")){//芬兰马克
							gjS01501030000005Req.setCurrency("246");
						}else{
							throw new EOSException("不支持的币种");
						}
						//保证金币种
						if(null != gjS01501030000005Req.getBondCurr() && !"".equals(gjS01501030000005Req.getBondCurr())){
							if(gjS01501030000005Req.getBondCurr().equals("CNY")){
								gjS01501030000005Req.setBondCurr("01");
							}else if(gjS01501030000005Req.getBondCurr().equals("FRF")){//法国法郎
								gjS01501030000005Req.setBondCurr("250");
							}else if(gjS01501030000005Req.getBondCurr().equals("DEM")){//德国马克
								gjS01501030000005Req.setBondCurr("276");
							}else if(gjS01501030000005Req.getBondCurr().equals("HKD")){//港币
								gjS01501030000005Req.setBondCurr("13");
							}else if(gjS01501030000005Req.getBondCurr().equals("ITL")){//意大利里拉
								gjS01501030000005Req.setBondCurr("380");
							}else if(gjS01501030000005Req.getBondCurr().equals("JPY")){//日元
								gjS01501030000005Req.setBondCurr("27");
							}else if(gjS01501030000005Req.getBondCurr().equals("KRW")){//韩国元
								gjS01501030000005Req.setBondCurr("410");
							}else if(gjS01501030000005Req.getBondCurr().equals("MOP")){//澳门元
								gjS01501030000005Req.setBondCurr("81");
							}else if(gjS01501030000005Req.getBondCurr().equals("MYR")){//马来西亚币
								gjS01501030000005Req.setBondCurr("458");
							}else if(gjS01501030000005Req.getBondCurr().equals("NLG")){//荷兰盾
								gjS01501030000005Req.setBondCurr("528");
							}else if(gjS01501030000005Req.getBondCurr().equals("NZD")){//新西兰元 
								gjS01501030000005Req.setBondCurr("554");
							}else if(gjS01501030000005Req.getBondCurr().equals("AUD")){//澳洲元
								gjS01501030000005Req.setBondCurr("29");
							}else if(gjS01501030000005Req.getBondCurr().equals("NOK")){//挪威克朗
								gjS01501030000005Req.setBondCurr("578");
							}else if(gjS01501030000005Req.getBondCurr().equals("PHP")){//菲律宾比索
								gjS01501030000005Req.setBondCurr("608");
							}else if(gjS01501030000005Req.getBondCurr().equals("RUB")){//卢布
								gjS01501030000005Req.setBondCurr("643");
							}else if(gjS01501030000005Req.getBondCurr().equals("SGD")){//新加坡元
								gjS01501030000005Req.setBondCurr("32");
							}else if(gjS01501030000005Req.getBondCurr().equals("ESP")){//西班牙比塞塔
								gjS01501030000005Req.setBondCurr("724");
							}else if(gjS01501030000005Req.getBondCurr().equals("SEK")){//瑞典克朗
								gjS01501030000005Req.setBondCurr("752");
							}else if(gjS01501030000005Req.getBondCurr().equals("CHF")){//瑞士法郎
								gjS01501030000005Req.setBondCurr("15");
							}else if(gjS01501030000005Req.getBondCurr().equals("THB")){//泰国铢
								gjS01501030000005Req.setBondCurr("764");
							}else if(gjS01501030000005Req.getBondCurr().equals("GBP")){//英镑
								gjS01501030000005Req.setBondCurr("12");
							}else if(gjS01501030000005Req.getBondCurr().equals("USD")){//美元
								gjS01501030000005Req.setBondCurr("14");
							}else if(gjS01501030000005Req.getBondCurr().equals("EUR")){//欧元
								gjS01501030000005Req.setBondCurr("38");
							}else if(gjS01501030000005Req.getBondCurr().equals("ATS")){//奥地利先令
								gjS01501030000005Req.setBondCurr("040");
							}else if(gjS01501030000005Req.getBondCurr().equals("OTHER")){//其他
								gjS01501030000005Req.setBondCurr("999");
							}else if(gjS01501030000005Req.getBondCurr().equals("BEF")){//比利时法郎
								gjS01501030000005Req.setBondCurr("056");
							}else if(gjS01501030000005Req.getBondCurr().equals("CAD")){//加拿大元
								gjS01501030000005Req.setBondCurr("28");
							}else if(gjS01501030000005Req.getBondCurr().equals("TWD")){//新台湾币
								gjS01501030000005Req.setBondCurr("158");
							}else if(gjS01501030000005Req.getBondCurr().equals("DKK")){//丹麦克朗
								gjS01501030000005Req.setBondCurr("208");
							}else if(gjS01501030000005Req.getBondCurr().equals("FIM")){//芬兰马克
								gjS01501030000005Req.setBondCurr("246");
							}else{
								throw new EOSException("不支持的币种");
							}
						}
						GJS01501030000005Res gjS01501030000005Res = new GJS01501030000005Res();
						gjS01501030000005Res = crmsMgrCallGjImpl.executeS01501030000005(gjS01501030000005Req);
						String returnCode = gjS01501030000005Res.getGjS01501030000005ResBody().getTransStat();
						if(null==returnCode){
							throw new EOSException("调用国结系统[进口保函修改接口]发生异常！");
						}
						if(!"0000".equals(returnCode)){
							throw new EOSException(gjS01501030000005Res.getGjS01501030000005ResBody().getErrMsg());
						}
						//贷后变更表的变更状态更新为审批中10---相当于交给国结审批
						change.set("changeStatus", "10");//国结审批中
						//贷后变更信用证修改---发送给国结以后  国结会返回一个修改流水  如果国结需要冲正该笔修改  需要用到该流水
						change.set("gjFlowNo", gjS01501030000005Res.getGjS01501030000005ResBody().getKnotSeqNo());
						DatabaseUtil.updateEntity("default", change);
						
					}else if("14".equals(change.get("loanChangeType"))) {//停息、终止停息
						
						tbLoanSummary.set("tingxiStatus", change.getString("newTingxiStatus"));
						DatabaseUtil.updateEntity("default", tbLoanSummary);
						
					}
					DatabaseUtil.updateEntity("default", change);
					//国结的信用证修改和保函修改 都需要调用国结的接口以及他们的反馈  再反馈等待的过程中 就相当于他们在审批这样的贷后业务
					if("12".equals(change.get("loanChangeType"))||"13".equals(change.get("loanChangeType"))||//信用证修改、保函修改
					  ("06".equals(change.get("loanChangeType"))&&("01007001".equals(tbLoanInfo.getString("productType")) || 
						"01007003".equals(tbLoanInfo.getString("productType"))|| "01007004".equals(tbLoanInfo.getString("productType")) || 
						"01007009".equals(tbLoanInfo.getString("productType"))|| "01007012".equals(tbLoanInfo.getString("productType")) || 
						"01007011".equals(tbLoanInfo.getString("productType"))|| "01007006".equals(tbLoanInfo.getString("productType")) || 
						"01007005".equals(tbLoanInfo.getString("productType"))|| "01007014".equals(tbLoanInfo.getString("productType")) || 
						"01007013".equals(tbLoanInfo.getString("productType"))||"01007010".equals(tbLoanInfo.getString("productType"))))) {//期限变更--而且是国结产品
						//国结产品---贷后变更需要国结返回消息以后才能修改变更状态  还是审批中---相当于国结审批中
						//change.set("changeStatus", "10");
						//DatabaseUtil.updateEntity("default", change);
					}else{
						//2、调计量接口
						InterAft inter = new InterAft();
						try {
							inter.interChose(change, tbLoanInfo, change.getString("termChangeWay"));
						} catch (Exception e) {
							e.printStackTrace();
							throw new EOSException(e.getMessage());
						}
					}
					
				}else {
					throw new EOSException("变更类型为空，请联系系统管理员！");
				}
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
			
		/*} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}*/
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		
		//审批不通过
		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");

			// 更新合同借据变更表状态
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			change.set("changeStatus", "04");
			DatabaseUtil.updateEntity("default", change);
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "04");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			normal.set("checkStatus", "04");
			DatabaseUtil.updateEntity("default", normal);
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "04");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "04");
			DatabaseUtil.updateEntity("default", expire);

			logger.info("流程提交，开始处理业务数据------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}
		
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
			Map workitem) throws EOSException, RuleException {
		Map<String, String> resultMap = new HashMap<String, String>();
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
		
		// 更新合同借据变更表状态   
		DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
		change.set("changeId", applyId);
		DatabaseUtil.expandEntity("default", change);
		
		return null;
	}
}
