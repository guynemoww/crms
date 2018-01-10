package com.bos.aft;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;

import oracle.sql.TIMESTAMP;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.RetPaymentInfoVo;

import commonj.sdo.DataObject;

@Bizlet("插入数据到计量系统")
public class InsertIntoJL {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);
	
	@Bizlet("插入还款计划到计量系统")
	public static void insert10(DataObject change) {
		try {
			
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			log.info("loanChangeType--->" + change.get("loanChangeType"));
			//log.info("dueNum=="+dueNum);
			//String CRMS_DS_NAME = "crms";
			String CRMS_DS_NAME = GitUtil.DEFAULT_DS_NAME;
			//String SDP_DS_NAME = "sdp";
			String SDP_DS_NAME = "aplus";
			HashMap<String, Object> paramHM = new HashMap<String, Object>();
			paramHM.put("changeId", change.getString("changeId"));
			paramHM.put("dueNum", summary.getString("summaryNum"));
			paramHM.put("rcvDate", GitUtil.getBusiDateYYYYMMDD());
			
			log.info("------------查询还款计划begin-------------");
			Object[] TcSupPrinPlanM = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,"com.primeton.tsl.transferData.queryTcSupPrinPlanM", paramHM);
			log.info("------------查询还款计划-------------");
			if(TcSupPrinPlanM!=null && TcSupPrinPlanM.length>0)
			{
				log.info("------------有还款计划-------------" + TcSupPrinPlanM.length);
				for(Object obj:TcSupPrinPlanM)
				{
					HashMap hm = (HashMap)obj;
					Object ct = hm.get("CREATE_TIME");
					Object ut = hm.get("UPDATE_TIME");
					if(ct != null)
					{
						TIMESTAMP tct = (TIMESTAMP)ct;
						Timestamp timestampValueC = tct.timestampValue();
						hm.put("CREATE_TIME", timestampValueC);
					}
					else
					{
						hm.put("CREATE_TIME", GitUtil.currDateTime());
					}
					if(ut != null)
					{
						TIMESTAMP tut = (TIMESTAMP)ut;
						Timestamp timestampValueU = tut.timestampValue();
						hm.put("UPDATE_TIME", timestampValueU);
					}
					else
					{
						hm.put("UPDATE_TIME", GitUtil.currDateTime());
					}
					hm.put("UUID", GitUtil.genUUIDString());
					hm.put("RCV_DATE", GitUtil.getBusiDateYYYYMMDD());
					
					if(((BigDecimal)hm.get("CURR_PERI")).doubleValue() == 1.00) {
						log.info("第1期--->" + GitUtil.getBusiDateYYYYMMDD());
						hm.put("BEG_DATE", GitUtil.getBusiDateYYYYMMDD());
					}
					
					if(((BigDecimal)hm.get("CURR_PERI")).intValue() == TcSupPrinPlanM.length) {
						log.info("最后1期--->" + (String)hm.get("LOAN_END"));
						hm.put("END_DATE", (String)hm.get("LOAN_END"));
					}
					
				}
				log.info("------------====================-------------");
				log.info("------------先删除-------------");
				HashMap map = new HashMap();
				map.put("dueNum", summary.getString("summaryNum"));
				map.put("rcvDate", GitUtil.getBusiDateYYYYMMDD());
				DatabaseExt.executeNamedSql(SDP_DS_NAME,"com.primeton.tsl.transferData.deleteTcSupPrinPlanM", map);
				log.info("------------再插入-------------");
				DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME,"com.primeton.tsl.transferData.insertTcSupPrinPlanM", TcSupPrinPlanM);
				/*Object[] TcSupPrinPlanMExist = DatabaseExt.queryByNamedSql(SDP_DS_NAME,"com.primeton.tsl.transferData.queryTcSupPrinPlanMExist", paramHM);
				if(TcSupPrinPlanMExist!=null && TcSupPrinPlanMExist.length>0) {
					log.info("------------已存在不插入-------------");
				}else {
					log.info("------------向计量系统插入数据-------------");
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME,"com.primeton.tsl.transferData.insertTcSupPrinPlanM", TcSupPrinPlanM);
				}*/
				log.info("------------查询还款计划end-------------");
			}else {
				log.info("------------无还款计划-------------");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	@Bizlet("向计量发还款控制信息")
	public static String insertAccountRepay(DataObject change) throws EOSException {
		String msg = "1";
		try {
			
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			log.info("summaryId--->" + change.getString("summaryId"));
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			log.info("loanId--->" + summary.getString("loanId"));
			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);
			
//			//添加--新还款计划
//			log.info("changeId--->" + change.getString("changeId"));
//			DataObject replan = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
//			loan.set("changeId", change.getString("changeId"));
//			DatabaseUtil.expandEntity("default", replan);
			
			//1-1500还款申请
			DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
			zh.set("loanId", summary.getString("loanId"));
			if("01008001".equals(loan.getString("productType")) || "01006001".equals(loan.getString("productType"))
					|| "01008010".equals(loan.getString("productType")) || "01006010".equals(loan.getString("productType"))//村镇银行贴现产品
					|| "01006002".equals(loan.getString("productType"))
					|| "01007008".equals(loan.getString("productType")) || "01009001".equals(loan.getString("productType")) 
					|| "01009002".equals(loan.getString("productType")) || "01009010".equals(loan.getString("productType")) || "01011001".equals(loan.getString("productType")) 
					|| "01012001".equals(loan.getString("productType")) || "01004001".equals(loan.getString("productType"))
					|| "01008002".equals(loan.getString("productType"))) {
				zh.set("zhlx", "2");
			}else {
				zh.set("zhlx", "1");
			}
			DatabaseUtil.expandEntityByTemplate("default", zh, zh);
			
			log.info("---控制信息------");
			Object[] params1 = new Object[1];
//			params1[0] = "MA1_1500";
			
//			PayConInfo vo = new PayConInfo();
//			vo.getBaseVO().setTranCod("MA1_1500");//交易代码
//			vo.getBaseVO().setOpr(GitUtil.getCorehkjygy());//操作员
//			vo.getBaseVO().setAut(GitUtil.getCorehkjygy());//授权员
//			vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//			//vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//			//vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//			vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//			vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//			vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());//营业日期 检查该机构在机构表中是否存在
//			vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());//接入系统营业日期 
//			vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//			vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//			//贷款开户机构修改为放款表中的Loanorg
//			vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//			//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
//			vo.getBaseVO().setDepCod(GitUtil.getBranchId());
//			vo.setSpecCode("0001");
//			vo.setInFlag("2");//是否插入控制表 1---不插入 2--插入
//			//控制信息
//			vo.setDueNum(summary.getString("summaryNum"));////借据编号
//			vo.setTelNo(change.getString("changeNum"));//通知书编号
//			//贷款开户机构修改为放款表中的Loanorg
//			vo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//			//vo.setOpnDep(summary.getString("orgNum"));
//			//vo.setPayAmt(change.getBigDecimal("repayAmt"));
//			vo.setPayOrder(change.getString("repayOrder"));//放款顺序：默认00
//			vo.setDealSts("0");
//			vo.setPayPrimAcct(change.getString("newRepayAccount"));
//			vo.setPayPrimAcctFlg("B");
//			vo.setPayPrimName(change.getString("newZhmc"));
//			if(null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan"))) {
//				vo.setPrinPlanFlg("1");//下发新的还本计划标志
//			}else {
//				vo.setPrinPlanFlg("0");//下发新的还本计划标志
//			}
//			if("1".equals(change.getString("isSettle"))) {
//				vo.setAmtFlg("3");//1还本息;2提前还本金3结清4结清当前期
//				//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//				vo.setPayAmt(change.getBigDecimal("yhze"));
//			}else if("0".equals(change.getString("isSettle"))) {
//				if("01".equals(change.getString("repayType"))) {
//					vo.setAmtFlg("1");//1还本息;2提前还本金3结清4结清当前期
//					//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//					vo.setPayAmt(change.getBigDecimal("yhze"));
//				}else if("02".equals(change.getString("repayType"))) {
//					vo.setAmtFlg("2");//1还本息;2提前还本金3结清4结清当前期
//					vo.setPrnAmt(change.getBigDecimal("repayCapital"));//提前还本金额
//					vo.setPayAmt(change.getBigDecimal("yhze"));
//				}else if("03".equals(change.getString("repayType"))) {
//					vo.setAmtFlg("4");//1还本息;2提前还本金3结清4结清当前期
//					//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//					vo.setPayAmt(change.getBigDecimal("yhze"));
//				}
//			}
			RepayControlInfRq rq = new RepayControlInfRq();
			BaseVO bvo = new BaseVO();
			bvo.setTranCod("T1202");
			bvo.setOpr(GitUtil.getCurrentUserId());
			bvo.setAut(GitUtil.getCurrentUserId());
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setTrnDep(loan.getString("loanOrg"));
			bvo.setTranFrom("47");
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod("9999");
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setDepCod(GitUtil.getBranchId());
			bvo.setOpnDep(loan.getString("loanOrg"));
			rq.setDueNum(summary.getString("summaryNum"));//借据编号
			rq.setTelNo(change.getString("changeNum"));//通知书编号
			rq.setPayOrder(change.getString("repayOrder"));//还款顺序
			rq.setPayPrimAcct(change.getString("newRepayAccount"));//还款账号
			rq.setPayPrimName(change.getString("newZhmc"));//还款账户名称
			rq.setPayOutItrFlg("1");//归还未结计利息标志--后续修改
//			rq.setPadUpPentIcm(new BigDecimal(33));//收取违约金金额--后续修改
//			rq.setStopPayNum("");//解止付编号--后续修改
			if(null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan"))) {
//				rq.setPrinPlanTerm(Integer.valueOf(replan.getString("newPeriodsNum")));//新还款计划表中的期数
				//新下发还本/还息计划,第1位下发还本计划标志 0=不下发  1=下发；第2位：下发还息计划标志 0=不下发  1=下发 ；
				rq.setPrinPlanFlg("10");//下发新的还本计划标志--需要修改
			}else {
//				rq.setPrinPlanTerm(Integer.valueOf(replan.getString("oldPeriodsNum")));//新还款计划表中的期数
				//新下发还本/还息计划,第1位下发还本计划标志 0=不下发  1=下发；第2位：下发还息计划标志 0=不下发  1=下发 ；
				rq.setPrinPlanFlg("00");//下发新的还本计划标志--需要修改
			}
			if("1".equals(change.getString("isSettle"))){
				rq.setPadUpAmt(change.getBigDecimal("yhze"));//还款金额
				rq.setPadUpPrn(change.getBigDecimal("yhbj"));//实收本金金额
				rq.setPadUpNorItrIn(change.getBigDecimal("yhzclx"));//正常利息金额
				rq.setPadUpDftItrIn(change.getBigDecimal("yhtqlx"));//拖欠利息金额
				rq.setPadUpPnsItrIn(change.getBigDecimal("yhfx"));//罚息金额
				//rq.setPadUpCpdItrIn(new BigDecimal(33));//复利金额--需要修改
			}else{
				rq.setPadUpAmt(change.getBigDecimal("yhze"));//还款金额
				rq.setPadUpPrn(change.getBigDecimal("yhbj"));//实收本金金额
				rq.setPadUpNorItrIn(change.getBigDecimal("yhzclx"));//正常利息金额
				rq.setPadUpDftItrIn(change.getBigDecimal("yhtqlx"));//拖欠利息金额
				rq.setPadUpPnsItrIn(change.getBigDecimal("yhfx"));//罚息金额
				//rq.setPadUpCpdItrIn(new BigDecimal(33));//复利金额--需要修改
			}
			if("03".equals(change.getString("repayOrder"))){
				BigDecimal yhze=new BigDecimal("0");
				if(null != change.getBigDecimal("shbj")){
					yhze = yhze.add(change.getBigDecimal("shbj"));
				}
				if(null != change.getBigDecimal("shzclx")){
					yhze = yhze.add(change.getBigDecimal("shzclx"));
				}
				if(null != change.getBigDecimal("shtqlx")){
					yhze = yhze.add(change.getBigDecimal("shtqlx"));
				}
				if(null != change.getBigDecimal("shfx")){
					yhze = yhze.add(change.getBigDecimal("shfx"));
				}
				if(null != change.getBigDecimal("shfl")){
					yhze = yhze.add(change.getBigDecimal("shfl"));
				}
				rq.setPadUpAmt(yhze);//还款金额
				rq.setPadUpPrn(change.getBigDecimal("shbj"));//实收本金金额
				rq.setPadUpNorItrIn(change.getBigDecimal("shzclx"));//正常利息金额
				rq.setPadUpDftItrIn(change.getBigDecimal("shtqlx"));//拖欠利息金额
				rq.setPadUpPnsItrIn(change.getBigDecimal("shfx"));//罚息金额
				rq.setPadUpCpdItrIn(change.getBigDecimal("shfl"));//复利金额
			}
			rq.setBaseVO(bvo);
			//调用核算还款控制信息
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			RepayControlInfRq rectol = proxy.executeT1202(rq);
			if("00000".equals(rectol.getBaseVO().getErrCod())){
				RepaymentRq rqs = new RepaymentRq();
				rqs.setDueNum(rq.getDueNum());
				rqs.setTelNo(rq.getTelNo());
				rqs.setPayOutItrFlg(rq.getPayOutItrFlg());
				BaseVO bvo1 = rq.getBaseVO();
				bvo1.setTranCod("T1102");
				bvo1.setOpr(GitUtil.getCurrentUserId());
				bvo1.setAut(GitUtil.getCurrentUserId());
				bvo1.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo1.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo1.setTrnDep(loan.getString("loanOrg"));
				bvo1.setTranFrom("47");
				bvo1.setOrigFrom("11000");
				bvo1.setLegPerCod("9999");
				bvo1.setTranDate(GitUtil.getBusiDateYYYYMMDD());
				bvo1.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
				bvo1.setDepCod(GitUtil.getBranchId());
				bvo1.setOpnDep(loan.getString("loanOrg"));
				rqs.setDueNum(summary.getString("summaryNum"));//借据编号
				rqs.setTelNo(change.getString("changeNum"));//通知书编号
				rqs.setPayOutItrFlg("1");//归还未结计利息标志--后续修改
				rqs.setBaseVO(bvo1);
				params1[0] = rqs;
				log.info("---调用放款接口--==========");
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("retPaymentEasyLcs", params1);
//				DataObject vo1 = (DataObject) objs[0];
//				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
//				RetPaymentInfoVo retPaymentInfoVo = (RetPaymentInfoVo)objs[0];
//				BaseVO baseVO = retPaymentInfoVo.getBaseVO();
//				String returnCode = (String) baseVO.getErrCod();
				RepaymentRq epaymentRq = (RepaymentRq)objs[0];
				BaseVO baseVO = epaymentRq.getBaseVO();
				String returnCode = (String) baseVO.getErrCod();
				log.info("returnCode--->" + returnCode);
				if (!"00000".equals(returnCode)) {
					log.info("getRpsMsg--->" + baseVO.getErrMsg());
					msg = baseVO.getErrMsg();
					//throw new EOSException(baseVO.getRpsMsg());
				}
			}else{
				msg=rectol.getBaseVO().getErrMsg();
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		log.info("msg--->" + msg);
		return msg;
	}
	
	@Bizlet("更新管理数据")
	public static void updateJJYE(DataObject change) {
		try {
			log.info("summaryId==========" + change.getString("summaryId"));
			DataObject tbLoanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			tbLoanSummary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", tbLoanSummary);
			
			log.info("loanId==========" + tbLoanSummary.getString("loanId"));
			DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			tbLoanInfo.set("loanId", tbLoanSummary.getString("loanId"));
			DatabaseUtil.expandEntity("default", tbLoanInfo);
			
			if("1".equals(change.getString("isSmall")) && "1".equals(change.getString("isModifyPlan"))) {//小贷
				log.info("==========小贷==========");
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
				log.info("==========小贷==========end");
			}else if("0".equals(change.getString("isSmall")) && "1".equals(change.getString("isModifyPlan"))) {
				log.info("==========公贷==========");
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
				log.info("==========公贷==========end");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

}
