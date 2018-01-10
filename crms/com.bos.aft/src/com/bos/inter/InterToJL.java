package com.bos.inter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.common.utils.DateUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.IXD15AccountInfo;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.OXD15AccountInfo;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plus.ChangeInterControlRq;
import com.primeton.plus.ChangeIntrRq;
import com.primeton.plus.CredPeriodChangeRq;
import com.primeton.plus.DelayTime;
import com.primeton.plus.DiscountBackRq;
import com.primeton.plus.DiscountStopRq;
import com.primeton.plus.ExtendTimeAppRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepayPlanChangeRq;
import com.primeton.plus.RepayWayChangeRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plus.StopControlRq;
import com.primeton.plus.StopItrRq;
import com.primeton.plus.StopStopControlRq;
import com.primeton.plus.StopStopItrRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.CancelDelVo;
import com.primeton.tsl.ChgPaySettAcctVo;

import commonj.sdo.DataObject;

public class InterToJL {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);

	// 管理向剂量发送第一次放款接口
	public void aftToLcs1(DataObject change) throws EOSException {
		try {
			
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);
			String loanOrg = loan.getString("loanOrg");//出账机构
			
			if("04".equals(change.getString("loanChangeType"))) {//还款账号变更
				
				DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
				zh.set("loanId", summary.getString("loanId"));
				if("01008001".equals(loan.getString("productType")) || "01006001".equals(loan.getString("productType")) 
						|| "01006002".equals(loan.getString("productType"))
						|| "01008010".equals(loan.getString("productType")) || "01006010".equals(loan.getString("productType"))//村镇银行贴现产品
						|| "01007008".equals(loan.getString("productType")) || "01009001".equals(loan.getString("productType")) 
						|| "01009002".equals(loan.getString("productType")) || "01009010".equals(loan.getString("productType")) || "01011001".equals(loan.getString("productType")) 
						|| "01012001".equals(loan.getString("productType")) || "01004001".equals(loan.getString("productType"))
						|| "01008002".equals(loan.getString("productType"))) {
					zh.set("zhlx", "2");
				}else {
					zh.set("zhlx", "1");
				}
				DatabaseUtil.expandEntityByTemplate("default", zh, zh);
				
				Object[] params1 = new Object[2];
				params1[0] = "MA1_1401";
				
				RepayAccChangeRq vo = new RepayAccChangeRq();
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1402");//交易代码
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setAut(GitUtil.getCurrentUserId());//授权员
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));//对账流水号
				bvo.setTrnDep(loanOrg);//交易机构，会校验
				bvo.setTranFrom("47");
				bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				bvo.setOpnDep(loanOrg);//贷款开户机构
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
//				vo.getBaseVO().setTranCod("MA1_1401");//交易代码
//				vo.getBaseVO().setOpr("HX001");//操作员
//				vo.getBaseVO().setAut("HX001");//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
				vo.setDueNum(summary.getString("summaryNum"));////借据编号
//				vo.setTelNo(change.getString("changeNum"));//通知书编号
				vo.setPayPrimAcct(change.getString("newRepayAccount"));//还款账号
				vo.setPayPrimName(change.getString("newZhmc"));//还款账户名称
//				vo.setPayOpenDep(change.getString("newZhkhjg"));//还款账号开户机构
				vo.setBaseVO(bvo);
				params1[1] = vo;
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"00000".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}

			}else if("17".equals(change.getString("loanChangeType"))){ //委托人收本收息账号变更
 				
				
				Object[] params1 = new Object[2];
				params1[0] = "MA1_1416";
				
				ChgPaySettAcctVo vo = new ChgPaySettAcctVo();
				vo.getBaseVO().setTranCod("MA1_1416");//交易代码
				vo.getBaseVO().setOpr("HX001");//操作员
				vo.getBaseVO().setAut("HX001");//授权员
				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
				vo.getBaseVO().setTrnDep(loanOrg);//交易机构，会校验
				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				//贷款开户机构修改为放款表中的Loanorg
				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构

				vo.setDueNum(summary.getString("summaryNum"));////借据编号
				vo.setTelNo(change.getString("changeNum"));//通知书编号
				vo.setPrnSettAcct(change.getString("newWtrhbzh"));
				vo.setPrnSettAcctName(change.getString("wtrnm"));
				vo.setPrnSettAcctTyp("");
				vo.setItrSettAcct(change.getString("newWtrhxzh"));
				vo.setItrSettAcctName(change.getString("wtrnm"));
				vo.setItrSettAcctTyp("");
				
				params1[1] = vo;
				
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"200".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}

			
				
			}else if("02".equals(change.getString("loanChangeType")) || "03".equals(change.getString("loanChangeType"))) {//还款方式变更、扣款日变更
				
				Object[] params1 = new Object[2];
				params1[0] = "MA1_5110";
				
//				PayTypVo vo = new PayTypVo();
//				vo.getBaseVO().setTranCod("MA1_1404");//交易代码
//				vo.getBaseVO().setOpr("HX001");//操作员
//				vo.getBaseVO().setAut("HX001");//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
//				vo.setDueNum(summary.getString("summaryNum"));//借据编号
//				vo.setTelNo(change.getString("changeNum"));//通知书编号
				RepayWayChangeRq vo = new RepayWayChangeRq();
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1405");//交易代码
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setAut(GitUtil.getCurrentUserId());//授权员
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				bvo.setRcnStan(lcsStan);//对账流水号
				bvo.setTrnDep(loanOrg);//交易机构，会校验
				bvo.setTranFrom("47");//业务渠道来源 001-信贷系统
				bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				bvo.setOpnDep(loanOrg);//贷款开户机构
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				vo.setBaseVO(bvo);
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
				
				if("02".equals(change.getString("loanChangeType"))) {
					if("5".equals(change.getString("newInterestCollectType")) || "6".equals(change.getString("newInterestCollectType"))) {
						vo.setCaspan("1");//变更后计息周期
					}else {
						vo.setCaspan(change.getString("newInterestCollectType"));//变更后计息周期
					}
					vo.setPayDate(change.getString("oldRepayDay"));//修改后指定还款日
					vo.setCurPrmPayTyp(change.getString("newRepayWay").substring(0, 2));//变更后主还款方式
					vo.setCurAstPayTyp(change.getString("newRepayWay").substring(2, 4));//变更后子还款方式
					log.info("newRepayWay--->" + change.getString("newRepayWay"));
					if("1400".equals(change.getString("newRepayWay")) || "1410".equals(change.getString("newRepayWay"))) {
						
						HashMap map = new HashMap();
						map.put("changeId", change.getString("changeId"));
						Object[] obj = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.getPeriods", map);
						int count = 0;
						if(obj.length>0){
							DataObject db = (DataObject)obj[0];
							count = db.getInt("PERI");
						}
						log.info("count--->" + count);
						
						vo.setStgFirstMon(change.getInt("firstPeriods"));//阶段性首次还本期数
						vo.setCusPayPlanType("0");//客户指定还息计划标志
//						vo.setChgPrinPlanTerm(count);//还本总期数
						vo.setPrinPlanFlg("1");//下发新的还本计划标志
						vo.setPayItrPlanFlg("0");//下发还息计划标志
					}else if("0300".equals(change.getString("newRepayWay")) || "0400".equals(change.getString("newRepayWay"))) {
						log.info("firstPeriods--->" + change.getInt("firstPeriods"));
						vo.setStgFirstMon(change.getInt("firstPeriods"));//阶段性首次还本期数
						//vo.setChgPrinPlanTerm(0);//还本总期数
						vo.setPrinPlanFlg("0");//下发新的还本计划标志
						vo.setCusPayPlanType("0");//客户指定还息计划标志
						vo.setPayItrPlanFlg("0");//下发还息计划标志
					}else {
						log.info("===========================");
						//vo.setChgStgFirstMon(change.getInt("firstPeriods"));//阶段性首次还本期数
						//vo.setChgPrinPlanTerm(0);//还本总期数
						vo.setPrinPlanFlg("0");//下发新的还本计划标志
						vo.setCusPayPlanType("0");//客户指定还息计划标志
						vo.setPayItrPlanFlg("0");//下发还息计划标志
					}
					log.info("阶段性首次还本期数--->" + vo.getStgFirstMon());
//					log.info("还本总期数--->" + vo.getPrinPlanTerm());
					log.info("下发新的还本计划标志--->" + vo.getPrinPlanFlg());
					
				}else if("03".equals(change.getString("loanChangeType"))) {
					if("5".equals(change.getString("oldInterestCollectType")) || "6".equals(change.getString("oldInterestCollectType"))) {
						vo.setCaspan("1");//变更后计息周期
					}else {
						vo.setCaspan(change.getString("oldInterestCollectType"));//变更后计息周期
					}
					vo.setPayDate(change.getString("newRepayDay"));//修改后指定还款日
					vo.setCurPrmPayTyp(change.getString("oldRepayWay").substring(0, 2));//变更后主还款方式
					vo.setCurAstPayTyp(change.getString("oldRepayWay").substring(2, 4));//变更后子还款方式
					vo.setCusPayPlanType("0");//客户指定还息计划标志
					vo.setPrinPlanFlg("0");//下发新的还本计划标志
					vo.setPayItrPlanFlg("0");//下发还息计划标志
				}
				
				vo.setItrRateWay("1");//修改后利率依赖方式
				
				params1[1] = vo;
				
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"00000".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}
			}else if("06".equals(change.getString("loanChangeType"))) {//期限变更
				
				Object[] params1 = new Object[2];
				params1[0] = "MA1_1405";
				
				CredPeriodChangeRq vo = new CredPeriodChangeRq();
//				vo.getBaseVO().setTranCod("MA1_1405");//交易代码
//				vo.getBaseVO().setOpr("HX001");//操作员
//				vo.getBaseVO().setAut("HX001");//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1406");//交易代码
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);
				bvo.setTrnDep(loanOrg);//交易机构，会校验
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				bvo.setRcnStan(lcsStan);//对账流水号
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setTranFrom("47");
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				bvo.setAut(GitUtil.getCurrentUserId());
				bvo.setToCoreSys("0");
				bvo.setTranTimes("1");
				bvo.setOpnDep(loan.getString("loanOrg"));
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
				vo.setBusCod(loan.getString("loanSubject1"));//业务别
				vo.setEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//贷款期限变更-到期日期
				vo.setDiscEndDate(loan.getString("txzq"));
				vo.setBaseVO(bvo);
				params1[1] = vo;
				
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"00000".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}
			}else if("09".equals(change.getString("loanChangeType"))) {//贴息、暂停贴息
				
				if("02".equals(change.getString("newTiexiStatus"))) {//暂停贴息
					
					Object[] params1 = new Object[2];
					params1[0] = "MA1_5103";
					
//					StopDiscVo vo = new StopDiscVo();
//					vo.getBaseVO().setTranCod("MA1_1402");//交易代码
//					vo.getBaseVO().setOpr("HX001");//操作员
//					vo.getBaseVO().setAut("HX001");//授权员
//					vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//					vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					//贷款开户机构修改为放款表中的Loanorg
//					vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
					DiscountStopRq vo = new DiscountStopRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1403");
					bvo.setOpr(GitUtil.getCurrentUserId());
					bvo.setAut(GitUtil.getCurrentUserId());
					bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo.setRcnStan(lcsStan);
					bvo.setTrnDep(loanOrg);
					bvo.setTranFrom("47");
					bvo.setOrigFrom("11000");
					bvo.setLegPerCod("9999");
					bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setDepCod(GitUtil.getBranchId());
					bvo.setToCoreSys("0");
					bvo.setTranTimes("1");
					bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setOpnDep(loan.getString("loanOrg"));
					vo.setDueNum(summary.getString("summaryNum"));//借据编号
					vo.setBaseVO(bvo);
					params1[1] = vo;
					ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					DataObject vo1 = (DataObject) objs[0];
					BaseVO baseVO = (BaseVO) vo1.get("baseVO");
					String returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}
				}else if("01".equals(change.getString("newTiexiStatus"))) {//恢复贴息
					
					Object[] params1 = new Object[2];
					params1[0] = "MA1_5104";
					
//					StopDiscVo vo = new StopDiscVo();
//					vo.getBaseVO().setTranCod("MA1_1403");//交易代码
//					vo.getBaseVO().setOpr("HX001");//操作员
//					vo.getBaseVO().setAut("HX001");//授权员
//					vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//					vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					//贷款开户机构修改为放款表中的Loanorg
//					vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
					DiscountBackRq vo = new DiscountBackRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1404");
					bvo.setOpr(GitUtil.getCurrentUserId());
					bvo.setAut(GitUtil.getCurrentUserId());
					bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo.setRcnStan(lcsStan);
					bvo.setTrnDep(loanOrg);
					bvo.setTranFrom("47");
					bvo.setOrigFrom("11000");
					bvo.setLegPerCod("9999");
					bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setDepCod(GitUtil.getBranchId());
					bvo.setToCoreSys("0");
					bvo.setTranTimes("1");
					bvo.setOpnDep(loan.getString("loanOrg"));
					vo.setDueNum(summary.getString("summaryNum"));//借据编号
					vo.setBaseVO(bvo);
					params1[1] = vo;
					ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					DataObject vo1 = (DataObject) objs[0];
					BaseVO baseVO = (BaseVO) vo1.get("baseVO");
					String returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}
				}
				
			}else if("16".equals(change.getString("loanChangeType"))) {//利息调整
				//利息申请
				Object[] params1 = new Object[2];
				params1[0] = "MA1_5109";
				
//				PayConInfo vo = new PayConInfo();
//				vo.getBaseVO().setTranCod("MA1_1503");//交易代码
//				vo.getBaseVO().setOpr("HX001");//操作员
//				vo.getBaseVO().setAut("HX001");//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
// 				vo.setDueNum(summary.getString("summaryNum"));//借据编号
// 				vo.setTelNo(change.getString("changeNum"));
// 				vo.setAdjItrFlg(change.getString("adjItrFlg"));
// 				vo.setNorItrIn(change.getBigDecimal("norItrIn"));
// 				vo.setDftItrIn(change.getBigDecimal("dftItrIn"));
// 				vo.setPnsItrIn(change.getBigDecimal("pnsItrIn"));
// 				vo.setNorOtdItr(change.getBigDecimal("norOtdItr"));
// 				vo.setPnsOtdItr(change.getBigDecimal("pnsOtdItr"));
// 				vo.setOpnDep(loan.getString("loanOrg"));
				ChangeInterControlRq vo = new ChangeInterControlRq();
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1205");
				bvo.setOpr(GitUtil.getCurrentUserId());
				bvo.setAut(GitUtil.getCurrentUserId());
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo.setRcnStan(lcsStan);
				bvo.setTrnDep(loanOrg);
				bvo.setTranFrom("47");
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setDepCod(GitUtil.getBranchId());
				bvo.setToCoreSys("0");
				bvo.setTranTimes("1");
				bvo.setOpnDep(loan.getString("loanOrg"));
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
 				vo.setTelNo(change.getString("changeNum"));
 				vo.setAdjItrFlg(change.getString("adjItrFlg"));
 				vo.setRcvNorItrIn(change.getBigDecimal("norItrIn"));
 				vo.setRcvDftItrIn(change.getBigDecimal("dftItrIn"));
 				vo.setRcvPnsItrIn(change.getBigDecimal("pnsItrIn"));
 				vo.setRcvCpdItrIn(change.getBigDecimal("otdItrIn"));
 				vo.setAdjOtdCpd(change.getBigDecimal("otdCpd"));
 				vo.setAdjOtdItr(change.getBigDecimal("norOtdItr"));
 				vo.setAdjOtdPns(change.getBigDecimal("pnsOtdItr"));
// 				vo.setPadUpNorItrIn(change.getBigDecimal("norItrIn"));
// 				vo.setPadUpDftItrIn(change.getBigDecimal("dftItrIn"));
// 				vo.setPadUpPnsItrIn(change.getBigDecimal("pnsItrIn"));
// 				vo.setPadUpCpdItrIn(change.getBigDecimal("otdItrIn"));
// 				vo.setPadUpAdjOtdItr(change.getBigDecimal("norOtdItr"));
// 				vo.setPadUpAdjOtdPns(change.getBigDecimal("pnsOtdItr"));
// 				vo.setPadUpAdjOtdCpd(change.getBigDecimal("otdCpd"));
 				vo.setBaseVO(bvo);
				params1[1] = vo;
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"00000".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}else{
					params1[0] = "MA1_5109";
					ChangeInterControlRq vo0 = new ChangeInterControlRq();
					BaseVO bvo0 = vo.getBaseVO();
					bvo0.setTranTimes("2");//交易次数标志 1次交易后填2，二次交易后填3
					bvo0.setTranCod("T1205");//交易代码
					vo0.setDueNum(summary.getString("summaryNum"));//借据编号
	 				vo0.setTelNo(change.getString("changeNum"));
	 				vo0.setAdjItrFlg(change.getString("adjItrFlg"));
//	 				vo0.setPadUpNorItrIn(change.getBigDecimal("norItrIn"));
//	 				vo0.setPadUpDftItrIn(change.getBigDecimal("dftItrIn"));
//	 				vo0.setPadUpPnsItrIn(change.getBigDecimal("pnsItrIn"));
//	 				vo0.setPadUpCpdItrIn(change.getBigDecimal("otdItrIn"));
//	 				vo0.setPadUpAdjOtdItr(change.getBigDecimal("norOtdItr"));
//	 				vo0.setPadUpAdjOtdPns(change.getBigDecimal("pnsOtdItr"));
//	 				vo0.setPadUpAdjOtdCpd(change.getBigDecimal("otdCpd"));
	 				vo0.setRcvNorItrIn(change.getBigDecimal("norItrIn"));
	 				vo0.setRcvDftItrIn(change.getBigDecimal("dftItrIn"));
	 				vo0.setRcvPnsItrIn(change.getBigDecimal("pnsItrIn"));
	 				vo0.setRcvCpdItrIn(change.getBigDecimal("otdItrIn"));
	 				vo0.setAdjOtdCpd(change.getBigDecimal("otdCpd"));
	 				vo0.setAdjOtdItr(change.getBigDecimal("norOtdItr"));
	 				vo0.setAdjOtdPns(change.getBigDecimal("pnsOtdItr"));
	 				vo0.setRcvNorItrIn(vo1.getBigDecimal("rcvNorItrIn"));
	 				vo0.setRcvDftItrIn(vo1.getBigDecimal("rcvDftItrIn"));
	 				vo0.setRcvPnsItrIn(vo1.getBigDecimal("rcvPnsItrIn"));
	 				vo0.setRcvCpdItrIn(vo1.getBigDecimal("rcvCpdItrIn"));
	 				vo0.setRcvNorItrOut(vo1.getBigDecimal("rcvNorItrOut"));
	 				vo0.setRcvDftItrOut(vo1.getBigDecimal("rcvDftItrOut"));
	 				vo0.setRcvPnsItrOut(vo1.getBigDecimal("rcvPnsItrOut"));
	 				vo0.setRcvCpdItrOut(vo1.getBigDecimal("rcvCpdItrOut"));
	 				vo0.setAdjOtdItr(vo1.getBigDecimal("adjOtdItr"));
	 				vo0.setAdjOtdPns(vo1.getBigDecimal("adjOtdPns"));
	 				vo0.setBaseVO(bvo0);
					params1[1] = vo0;
					 logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					 objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					 vo1 = (DataObject) objs[0];
					 baseVO = (BaseVO) vo1.get("baseVO");
					 returnCode = (String) baseVO.getErrCod();
				 if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}else{
					//利息申请
					params1[0] = "MA1_5116";
					ChangeIntrRq vo2 = new ChangeIntrRq();
					BaseVO bvo1 = vo.getBaseVO();
					bvo1.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
					bvo1.setTranCod("T1105");//交易代码
					vo2.setDueNum(summary.getString("summaryNum"));//借据编号
	 				vo2.setTelNo(change.getString("changeNum"));
	 				vo2.setBaseVO(bvo1);
					params1[1] = vo2;
					 logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					 objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					 vo1 = (DataObject) objs[0];
					 baseVO = (BaseVO) vo1.get("baseVO");
					 returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}else{
						params1[0] = "MA1_5116";
						ChangeIntrRq vo3 = new ChangeIntrRq();
						BaseVO bvo2 = vo.getBaseVO();
						bvo2.setTranTimes("2");//交易次数标志 1次交易后填2，二次交易后填3
						bvo2.setTranCod("T1105");//交易代码
						vo3.setDueNum(summary.getString("summaryNum"));//借据编号
		 				vo3.setTelNo(change.getString("changeNum"));
		 				vo3.setBaseVO(bvo2);
						params1[1] = vo3;
						 logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
						 objs = null;
						objs = logicComponent.invoke("newDataInsertCheck", params1);
						 vo1 = (DataObject) objs[0];
						 baseVO = (BaseVO) vo1.get("baseVO");
						 returnCode = (String) baseVO.getErrCod();
						 if (!"00000".equals(returnCode)) {
								throw new EOSException(baseVO.getErrMsg());
							}
					}
				}
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	// 展期申请控制信息
	public void aftTo1414(DataObject change, DataObject tbLoanInfo, String type) throws EOSException {
		try {
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
		
			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);
			//绵阳商行不适用
//			DataObject tbLoanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
//			tbLoanLoanrate.set("loanId", summary.getString("loanId"));
//			DatabaseUtil.expandEntityByTemplate("default", tbLoanLoanrate, tbLoanLoanrate);
			
			
			Object[] params1 = new Object[2];
			ExtendTimeAppRq vo = new ExtendTimeAppRq();
			DelayTime vo1 = new DelayTime();
			String str = change.getString("newOverdueRate");//罚息率
			if("04".equals(type)){//逾期展期
				params1[0] = "MA1_5130";
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1419");//交易代码
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setAut(GitUtil.getCurrentUserId());//授权员
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				bvo.setRcnStan(lcsStan);//对账流水号
				bvo.setTrnDep(loan.getString("loanOrg"));//交易机构，会校验
				bvo.setTranFrom("47");//业务渠道来源 001-信贷系统
				bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				bvo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				vo1.setDueNum(summary.getString("summaryNum"));//借据编号
				vo1.setBusCod(loan.getString("loanSubject1"));//业务别
				vo1.setNorItrRate(change.getBigDecimal("newYearRate"));//展期后正常利率
				vo1.setExdBegDate(GitUtil.getBusiDateYYYYMMDD());//展期起始日期
				vo1.setExdEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//展期到期日期
				BigDecimal n = new BigDecimal(str);
				BigDecimal a1 = new BigDecimal(100);
				BigDecimal a2 = new BigDecimal(1);
				BigDecimal res = new BigDecimal(0);
				res = change.getBigDecimal("newYearRate").multiply(n.divide(a1).add(a2));
				vo1.setDelItrRate(res);//展期后罚息利率
				vo1.setCpdItrRate(res);//展期后复利利率
				//目前绵阳商行没有贴息贷款不送此日期
				//vo1.setDiscEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//贴息到期日
				vo1.setDiscEndDate(null);
				vo1.setBaseVO(bvo);
				params1[1] = vo1;
			}else if("03".equals(type)){//正常展期
				params1[0] = "MA1_1414";
				
				
//				vo.getBaseVO().setTranCod("MA1_1414");//交易代码
//				vo.getBaseVO().setOpr("HX001");//操作员
//				vo.getBaseVO().setAut("HX001");//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1412");//交易代码
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setAut(GitUtil.getCurrentUserId());//授权员
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				bvo.setRcnStan(lcsStan);//对账流水号
				bvo.setTrnDep(loan.getString("loanOrg"));//交易机构，会校验
				bvo.setTranFrom("47");//业务渠道来源 001-信贷系统
				bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				bvo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
				vo.setBusCod(loan.getString("loanSubject1"));//业务别
//				String ls = "";
//				InterToJL sub = new InterToJL();
//				ls = sub.getLoanSubject(tbLoanInfo.getString("loanId"),change.getString("changeId"));
//				vo.setBusCod(ls);//业务别
				//vo.setBusCod(loan.getString("loanSubject"));//业务别
//				log.info("endDate--->" + DateUtil.format(summary.getDate("endDate"), "yyyyMMdd"));
//				vo.setBegDate(DateUtil.format(change.getDate("oldEndDate"), "yyyyMMdd"));//展期起始日期
//				log.info("getBegDate--->" + vo.getBegDate());
//				vo.setEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//展期到期日期
				vo.setNorItrRate(change.getBigDecimal("newYearRate"));//展期后正常利率
				vo.setExdBegDate(DateUtil.format(change.getDate("oldEndDate"), "yyyyMMdd"));//展期起始日期
				vo.setExdEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//展期到期日期
//				vo.setNorItrRate(change.getBigDecimal("newYearRate"));//展期后正常利率
				BigDecimal n = new BigDecimal(str);
				BigDecimal a1 = new BigDecimal(100);
				BigDecimal a2 = new BigDecimal(1);
				BigDecimal res = new BigDecimal(0);
				//res = tbLoanLoanrate.getBigDecimal("yearRate").multiply(n.divide(a1).add(a2));
				res = change.getBigDecimal("newYearRate").multiply(n.divide(a1).add(a2));
				log.info("res--->" + res);
				vo.setDelItrRate(res);//展期后罚息利率
				//vo.setDelItrRate(n);//展期后罚息利率
				vo.setCpdItrRate(res);//展期后复利利率
				//目前绵阳商行没有贴息贷款不送此日期
				//vo.setDiscEndDate(DateUtil.format(change.getDate("newEndDate"), "yyyyMMdd"));//贴息到期日
				vo.setDiscEndDate(null);
				//vo.setSts("");//下发还本计划标志
				//vo.setDiscEndDate(loan.getString("txzq"));
//				vo.setPrinPlanFlg("");
//				vo.setCpdItrRate(res);
				vo.setBaseVO(bvo);
				params1[1] = vo;
			}else{
				throw new EOSException("无对应的展期信息");
			}
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = null;
			objs = logicComponent.invoke("newDataInsertCheck", params1);
			DataObject vo2 = (DataObject) objs[0];
			BaseVO baseVO = (BaseVO) vo2.get("baseVO");
			String returnCode = (String) baseVO.getErrCod();
			if (!"00000".equals(returnCode)) {
				throw new EOSException(baseVO.getErrMsg());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	//直接插入数据
	public void insertJL(DataObject change) throws EOSException {
		try {
			
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			if("01".equals(change.getString("loanChangeType"))) {//利率变更
				
				//log.info("dueNum=="+dueNum);
				//String CRMS_DS_NAME = "crms";
				String CRMS_DS_NAME = GitUtil.DEFAULT_DS_NAME;
				//String SDP_DS_NAME = "sdp";
				String SDP_DS_NAME = "aplus";
				HashMap<String, Object> paramHM = new HashMap<String, Object>();
				paramHM.put("changeId", change.getString("changeId"));
				
				//利率变更
				Object[] TbSupIntrRateAdjust = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,"com.primeton.tsl.transferData.queryTbSupIntrRateAdjust", paramHM);
				if(TbSupIntrRateAdjust!=null && TbSupIntrRateAdjust.length>0)
				{
					for(Object obj:TbSupIntrRateAdjust)
					{
						HashMap hm = (HashMap)obj;
//						Object ct = hm.get("CREATE_TIME");
//						Object ut = hm.get("UPDATE_TIME");
//						if(ct != null)
//						{
//							TIMESTAMP tct = (TIMESTAMP)ct;
//							Timestamp timestampValueC = tct.timestampValue();
//							hm.put("CREATE_TIME", timestampValueC);
//						}
//						else
//						{
//							hm.put("CREATE_TIME", GitUtil.currDateTime());
//						}
//						if(ut != null)
//						{
//							TIMESTAMP tut = (TIMESTAMP)ut;
//							Timestamp timestampValueU = tut.timestampValue();
//							hm.put("UPDATE_TIME", timestampValueU);
//						}
//						else
//						{
//							hm.put("UPDATE_TIME", GitUtil.currDateTime());
//						}
//						hm.put("UUID", GitUtil.genUUIDString());
//						hm.put("ITR_DATE", GitUtil.getBusiDateYYYYMMDD());
						hm.put("RCV_DATE", GitUtil.getBusiDateYYYYMMDD());
						Object ct = hm.get("ITR_DATE");
						if(ct != null){
							TIMESTAMP tct = (TIMESTAMP)ct;
							Timestamp timestampValueC = tct.timestampValue();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							hm.put("ITR_DATE", sdf.format(timestampValueC));
						}else{
							hm.put("ITR_DATE", GitUtil.getBusiDateYYYYMMDD());
						}
					}
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME,"com.primeton.tsl.transferData.insertTbSupIntrRateAdjust", TbSupIntrRateAdjust);
				}
					
			}else if("08".equals(change.getString("loanChangeType"))) {//贴息主体变更
				
				//log.info("dueNum=="+dueNum);
				//String CRMS_DS_NAME = "crms";
				String CRMS_DS_NAME = GitUtil.DEFAULT_DS_NAME;
				//String SDP_DS_NAME = "sdp";
				String SDP_DS_NAME = "aplus";
				HashMap<String, Object> paramHM = new HashMap<String, Object>();
				paramHM.put("changeId", change.getString("changeId"));
				
				//贴息主体变更
				Object[] TbSupDiscInfoTmpM = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,"com.primeton.tsl.transferData.queryTbSupDiscInfoTmpM", paramHM);
				if(TbSupDiscInfoTmpM!=null && TbSupDiscInfoTmpM.length>0)
				{
					for(Object obj:TbSupDiscInfoTmpM)
					{
						HashMap hm = (HashMap)obj;
 						hm.put("RCV_DATE", GitUtil.getBusiDateYYYYMMDD());

 						DatabaseExt.executeNamedSql(SDP_DS_NAME,"com.primeton.tsl.transferData.insertTbSupDiscInfoTmpM", hm);
					}
				}
				
			}else if("10".equals(change.getString("loanChangeType")) || "11".equals(change.getString("loanChangeType")) || "02".equals(change.getString("loanChangeType"))
					|| "15".equals(change.getString("loanChangeType")) || "18".equals(change.getString("loanChangeType")) ) {//还本计划变更
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
						Object df = hm.get("DEAL_FLG");
						Object da = hm.get("RCV_DATE");
						if(df==null||"".equals(df)){
							hm.put("DEAL_FLG", 0);
						}
						if(da != null)
						{
							TIMESTAMP tut = (TIMESTAMP)da;
							Timestamp timestampValueU = tut.timestampValue();
							hm.put("RCV_DATE", timestampValueU);
						}
						else
						{
							hm.put("RCV_DATE", GitUtil.getBusiDateYYYYMMDD());
						}
						if("10".equals(change.getString("loanChangeType")) ) {hm.put("REPAYPLAN_TYPE", "03");}
						if("02".equals(change.getString("loanChangeType")) ) {hm.put("REPAYPLAN_TYPE", "05");}
						if("11".equals(change.getString("loanChangeType")) ||"15".equals(change.getString("loanChangeType")) ||"18".equals(change.getString("loanChangeType")) ) {hm.put("REPAYPLAN_TYPE", "06");}

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
					map.put("changeNum", change.getString("changeNum"));
					if("10".equals(change.getString("loanChangeType")) ) {map.put("delType","03") ;}
					if("02".equals(change.getString("loanChangeType")) ) {map.put("delType","05") ;}
					if("11".equals(change.getString("loanChangeType")) ||"15".equals(change.getString("loanChangeType")) ||"18".equals(change.getString("loanChangeType"))  ) {map.put("delType","06") ;}
					//delrepayPlanFromJl(map);
					DatabaseExt.executeNamedSql(SDP_DS_NAME,"com.primeton.tsl.transferData.deleteTcSupPrinPlanM", map);
					log.info("------------再插入-------------");
					
					Object[] TcSupPrinPlanMExist = DatabaseExt.queryByNamedSql(SDP_DS_NAME,"com.primeton.tsl.transferData.queryTcSupPrinPlanMExist", paramHM);
					if(TcSupPrinPlanMExist!=null && TcSupPrinPlanMExist.length>0) {
						log.info("------------已存在不插入-------------");
					}else {
						log.info("------------向计量系统插入数据-------------");
						DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME,"com.primeton.tsl.transferData.insertTcSupPrinPlanM", TcSupPrinPlanM);
					}
					log.info("------------查询还款计划end-------------");
				}else {
					log.info("------------无还款计划-------------");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	
	public DataObject delrepayPlanFromJl(Map map) {
		DataObject vo1=null;
 		try {
	
 			  String telNo=(String)map.get("changeNum");//通知书编号
			  String dueNum=(String)map.get("dueNum");//借据编号
			  String busDate=(String)map.get("rcvDate");//营业日期
			  String delType=(String)map.get("delType");//删除类型

			Object[] params1 = new Object[2];
			params1[0] = "MDB_2002";

			CancelDelVo vo = new CancelDelVo();
			vo.getBaseVO().setTranCod("MDB_2002");
			vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());
//			vo.getBaseVO().setAccSysDate("20160327");
//			vo.getBaseVO().setTranDate("20160327");

			 
			vo.setBusDate(busDate);
//			vo.setAccSysDate("20160327");
			vo.setDelType(delType);
			vo.setDueNum(dueNum);
			vo.setTelNo(telNo);

			params1[1] = vo;
			ILogicComponent logicComponent = LogicComponentFactory
					.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = logicComponent
					.invoke("newDataInsertCheck", params1);
			  vo1 = (DataObject) objs[0];
			 
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return vo1;
	}
	
	
	
	public void inter10(DataObject change) throws EOSException {
		try {
			
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);
			
			if("10".equals(change.getString("loanChangeType"))) {//还本计划变更
				
	/*			Object[] params1 = new Object[2];
				params1[0] = "MA1_1506";
				
				PayConInfo vo = new PayConInfo();
				vo.getBaseVO().setTranCod("MA1_1506");//交易代码
				vo.getBaseVO().setOpr("HX001");//操作员
				vo.getBaseVO().setAut("HX001");//授权员
				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
				vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
				vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
				vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
				//贷款开户机构修改为放款表中的Loanorg
				vo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				//vo.setOpnDep(summary.getString("orgNum"));//贷款开户机构
				vo.setDueNum(summary.getString("summaryNum"));////借据编号
				vo.setTelNo(change.getString("changeNum"));//通知书编号
				
			 			
				params1[1] = vo;
				
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				if (!"200".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}*/
				
				//还本计划变更 MDA_2001(DBMChgPlan)
				Object[] params2 = new Object[2];
				params2[0] = "DBMChgPlan";
				
				RepayPlanChangeRq repayPlanChangeRq = new RepayPlanChangeRq();
				BaseVO vo=new BaseVO();
				vo.setTranCod("T1415");//交易代码
				vo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				vo.setTranDate(bus_date);
				vo.setTrnDep(loan.getString("loanOrg"));//交易机构，会校验
				vo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
				vo.setRcnStan(lcsStan);//对账流水号
				vo.setOpr(GitUtil.getCurrentUserId());//操作员
				vo.setTranFrom("47");
				vo.setOrigFrom("11000");
				vo.setLegPerCod("9999");
				vo.setOpnDep(loan.getString("loanOrg"));
				vo.setToCoreSys("0");
				vo.setTranTimes("1");
/*				PayConInfovo.getBaseVO().setAut("HX001");//授权员
				PayConInfovo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
				PayConInfovo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
				PayConInfovo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				PayConInfovo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
*/				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
				//贷款开户机构修改为放款表中的Loanorg
//				PayConInfovo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				//vo.setOpnDep(summary.getString("orgNum"));//贷款开户机构
//				PayConInfovo.setDueNum(summary.getString("summaryNum"));////借据编号
				repayPlanChangeRq.setTelNo(change.getString("changeNum"));//通知书编号
				repayPlanChangeRq.setDueNum(summary.getString("summaryNum"));////借据编号
				repayPlanChangeRq.setPrinPlanFlg("1");//下发标志
				repayPlanChangeRq.setBaseVO(vo);
			 			
				params2[1] = repayPlanChangeRq;
				
				ILogicComponent logicComponent2 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs2 = null;
				objs2 = logicComponent2.invoke("newDataInsertCheck", params2);
				DataObject vo2 = (DataObject) objs2[0];
				BaseVO baseVO2 = (BaseVO) vo2.get("baseVO");
				String returnCode2 = (String) baseVO2.getErrCod();
				if (!"00000".equals(returnCode2)) {
					throw new EOSException(baseVO2.getErrMsg());
				}
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	public void aftToLcs2(DataObject change) throws EOSException {
		try {
			
			String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			
			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);
			
			DataObject InAccount = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubRepayAccount");
			InAccount.set("repayAccountOrgNum", loan.getString("loanOrg"));
			if("18".equals(change.getString("loanChangeType"))){
			DatabaseUtil.expandEntityByTemplate("default", InAccount, InAccount);
			}
			
			
			if("11".equals(change.getString("loanChangeType")) || "15".equals(change.getString("loanChangeType")) || "18".equals(change.getString("loanChangeType"))) {//提前还款
				
				//校验账户余额
				Object[] res = null;
				Object[] paramsRepay = new Object[1];
				String acctInd="";
				if("11".equals(change.getString("loanChangeType")) ){
				  acctInd = change.getString("oldRepayAccount");
				}else if ("15".equals(change.getString("loanChangeType")) ){
					acctInd = change.getString("hzszh");
				}else if ("18".equals(change.getString("loanChangeType")) ){
					acctInd = InAccount.getString("repayAccount");
				}
//				paramsRepay[0] = acctInd;
//				ILogicComponent logicComponentRepay = LogicComponentFactory.create("com.bos.accInfo.accInfo");
//				res = logicComponentRepay.invoke("queryAccNew", paramsRepay);
//				DataObject dobj = (DataObject)res[1];
//				DataObject hxRq12003000004BODY01 = DataObjectUtil.createDataObject("com.bos.pub.sys.HxRq12003000004BODY01");
//				hxRq12003000004BODY01 = dobj;
				
//				if(change.getBigDecimal("yhze").doubleValue() > hxRq12003000004BODY01.getBigDecimal("avlBal").doubleValue()) {
//					throw new EOSException("还款金额大于账户可用余额！");
//				}
				/**
				 * 调用核心账户查询接口
				 */
				String loanOrg = loan.getString("loanOrg");//出账机构
				String currencyCd = loan.getString("currencyCd");
				CrmsMgrCallCoreProxy proxy = new CrmsMgrCallCoreImpl();
				if("8".equals(acctInd.substring(4, 5)) || "9".equals(acctInd.substring(4, 5))){
					IXD15AccountInfo req = new IXD15AccountInfo();
					req.setAcctNo(acctInd);
					req.setOrgNum(loanOrg);
					if("CNY".equals(currencyCd)){//人民币
						req.setCurrCode("01");
					}else if("GBP".equals(currencyCd)){//英镑
						req.setCurrCode("12");
					}else if("HKD".equals(currencyCd)){//港币
						req.setCurrCode("13");
					}else if("USD".equals(currencyCd)){//美元
						req.setCurrCode("14");
					}else if("CHF".equals(currencyCd)){//瑞士法郎
						req.setCurrCode("15");
					}else if("JPY".equals(currencyCd)){//日元
						req.setCurrCode("27");
					}else if("CAD".equals(currencyCd)){//加拿大元
						req.setCurrCode("28");
					}else if("AUD".equals(currencyCd)){//澳洲元
						req.setCurrCode("29");
					}else if("SGD".equals(currencyCd)){//新加坡元
						req.setCurrCode("32");
					}else if("EUR".equals(currencyCd)){//欧元
						req.setCurrCode("38");
					}else if("MOP".equals(currencyCd)){//澳门元
						req.setCurrCode("81");
					}
				 proxy.executeXD15(req);
					
				}else{
					OXD051_AccInfoQryReq req = new OXD051_AccInfoQryReq();
					req.setOrgNum(loanOrg);
					req.setQryType("1");
					req.setCustAcctNo(acctInd);
					if("CNY".equals(currencyCd)){//人民币
						req.setCurrCode("01");
						req.setCashFlag("0");
					}else if("GBP".equals(currencyCd)){//英镑
						req.setCurrCode("12");
						req.setCashFlag("1");
					}else if("HKD".equals(currencyCd)){//港币
						req.setCurrCode("13");
						req.setCashFlag("1");
					}else if("USD".equals(currencyCd)){//美元
						req.setCurrCode("14");
						req.setCashFlag("1");
					}else if("CHF".equals(currencyCd)){//瑞士法郎
						req.setCurrCode("15");
						req.setCashFlag("1");
					}else if("JPY".equals(currencyCd)){//日元
						req.setCurrCode("27");
						req.setCashFlag("1");
					}else if("CAD".equals(currencyCd)){//加拿大元
						req.setCurrCode("28");
						req.setCashFlag("1");
					}else if("AUD".equals(currencyCd)){//澳洲元
						req.setCurrCode("29");
						req.setCashFlag("1");
					}else if("SGD".equals(currencyCd)){//新加坡元
						req.setCurrCode("32");
						req.setCashFlag("1");
					}else if("EUR".equals(currencyCd)){//欧元
						req.setCurrCode("38");
						req.setCashFlag("1");
					}else if("MOP".equals(currencyCd)){//澳门元
						req.setCurrCode("81");
						req.setCashFlag("1");
					}
					req.setQryPwd("1");
					OXD052_AccInfoQryRes rs = proxy.executeXD05(req);
					BigDecimal avalibAmt = new BigDecimal(rs.getOxd052ResBody().getAvailableAmt());
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
						if(null != change.getBigDecimal("padUpAdjOtdItr")){
							yhze = yhze.add(change.getBigDecimal("padUpAdjOtdItr"));
						}
						if(null != change.getBigDecimal("padUpAdjOtdPns")){
							yhze = yhze.add(change.getBigDecimal("padUpAdjOtdPns"));
						}
						if(null != change.getBigDecimal("padUpAdjOtdCpd")){
							yhze = yhze.add(change.getBigDecimal("padUpAdjOtdCpd"));
						}
						if(yhze.doubleValue() > avalibAmt.doubleValue()) {
							throw new EOSException("还款金额大于账户可用余额！");
						}
					}else{
						BigDecimal yhze = change.getBigDecimal("yhze");
						if(yhze.doubleValue() > avalibAmt.doubleValue()) {
							throw new EOSException("还款金额大于账户可用余额！");
						}
					}
				}
				
				//1-1500还款申请
				DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
				zh.set("loanId", summary.getString("loanId"));
				if("01008001".equals(loan.getString("productType")) || "01006001".equals(loan.getString("productType"))
						|| "01006002".equals(loan.getString("productType"))
						|| "01008010".equals(loan.getString("productType")) || "01006010".equals(loan.getString("productType"))//村镇银行贴现产品
						|| "01007008".equals(loan.getString("productType")) || "01009001".equals(loan.getString("productType")) 
						|| "01009002".equals(loan.getString("productType")) || "01009010".equals(loan.getString("productType")) || "01011001".equals(loan.getString("productType")) 
						|| "01012001".equals(loan.getString("productType")) || "01004001".equals(loan.getString("productType"))
						|| "01008002".equals(loan.getString("productType"))) {
					zh.set("zhlx", "2");
				}else {
					zh.set("zhlx", "1");
				}
				DatabaseUtil.expandEntityByTemplate("default", zh, zh);
				
				Object[] params1 = new Object[1];
////				params1[0] = "MA1_1500";
//				
//				PayConInfo vo = new PayConInfo();
//				vo.getBaseVO().setTranCod("MA1_1500");//交易代码
//				vo.getBaseVO().setOpr(GitUtil.getCorehkjygy());//操作员
//				vo.getBaseVO().setAut(GitUtil.getCorehkjygy());//授权员
//				vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//				//vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//				//vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
////				vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//				vo.getBaseVO().setTrnDep(loan.getString("loanOrg"));//交易机构，会校验
//
//				vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//				vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());//营业日期 检查该机构在机构表中是否存在
//				vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());//接入系统营业日期 
//				vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//				vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//				//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
//				vo.getBaseVO().setDepCod(GitUtil.getBranchId());
//				vo.setSpecCode("0001");
//				//控制信息
//				vo.setInFlag("2");//是否插入控制表 1---不插入 2--插入
//				vo.setDueNum(summary.getString("summaryNum"));////借据编号
//				vo.setTelNo(change.getString("changeNum"));//通知书编号
//				//贷款开户机构修改为放款表中的Loanorg
//				vo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//				//vo.setOpnDep(summary.getString("orgNum"));
//				//vo.setPayAmt(change.getBigDecimal("repayAmt"));
//				vo.setPayOrder(change.getString("repayOrder"));//放款顺序：默认00
//				vo.setDealSts("0");
//				if("11".equals(change.getString("loanChangeType")) ){
//				vo.setPayPrimAcct(zh.getString("zh"));
//				}else if("15".equals(change.getString("loanChangeType"))){
//					vo.setPayPrimAcct(change.getString("hzszh"));
//
//				}else if("18".equals(change.getString("loanChangeType"))){
//					vo.setPayPrimAcct(InAccount.getString("repayAccount"));
//
//				}
//				vo.setPayPrimAcctFlg("B");
//				vo.setRcvItrType(change.getString("newTiexiStatus"));
//				if("11".equals(change.getString("loanChangeType")) ){
//					vo.setPayPrimName(zh.getString("zhmc"));
//					}else if("15".equals(change.getString("loanChangeType"))){
// 						vo.setPayPrimName(change.getString("hzsnm"));
//					}else if("18".equals(change.getString("loanChangeType"))){
// 						vo.setPayPrimName(InAccount.getString("repayAccountName"));
//
//
//					}
//				if(null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan"))) {
//					vo.setPrinPlanFlg("1");//下发新的还本计划标志
//				}else {
//					vo.setPrinPlanFlg("0");//下发新的还本计划标志
//				}
//				if("1".equals(change.getString("isSettle"))) {
//					vo.setAmtFlg("3");//1还本息;2提前还本金3结清4结清当前期
//					//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//					vo.setPayAmt(change.getBigDecimal("yhze"));
//				}else if("0".equals(change.getString("isSettle"))) {
//					if("01".equals(change.getString("repayType"))) {
//						vo.setAmtFlg("1");//1还本息;2提前还本金3结清4结清当前期
//						//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//						vo.setPayAmt(change.getBigDecimal("yhze"));
//					}else if("02".equals(change.getString("repayType"))) {
//						vo.setAmtFlg("2");//1还本息;2提前还本金3结清4结清当前期
//						vo.setPrnAmt(change.getBigDecimal("repayCapital"));//提前还本金额
//						vo.setPayAmt(change.getBigDecimal("yhze"));
//					}else if("03".equals(change.getString("repayType"))) {
//						vo.setAmtFlg("4");//1还本息;2提前还本金3结清4结清当前期
//						//vo.setPrnAmt(change.getBigDecimal("yhbj"));//提前还本金额
//						vo.setPayAmt(change.getBigDecimal("yhze"));
//					}
//				}
				Map maps = new HashMap();
				maps.put("summaryNum", summary.getString("summaryNum"));
				Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryLoanOpr", maps);
				Map mapo = (Map) orgArea[0];
				String opr = (String) mapo.get("USERNUM");//操作员和出账保持一致
				
				//还款控制信息
				RepayControlInfRq vo = new RepayControlInfRq();
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
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
				vo.setTelNo(change.getString("changeNum"));//通知书编号
				vo.setPayOrder(change.getString("repayOrder"));//还款顺序
				//还款账号
				if("11".equals(change.getString("loanChangeType")) ){
					vo.setPayPrimAcct(zh.getString("zh"));
					}else if("15".equals(change.getString("loanChangeType"))){
						vo.setPayPrimAcct(change.getString("hzszh"));

					}else if("18".equals(change.getString("loanChangeType"))){
						vo.setPayPrimAcct(InAccount.getString("repayAccount"));

					}
				//还款账户名称
				if("11".equals(change.getString("loanChangeType")) ){
					vo.setPayPrimName(zh.getString("zhmc"));
					}else if("15".equals(change.getString("loanChangeType"))){
 						vo.setPayPrimName(change.getString("hzsnm"));
					}else if("18".equals(change.getString("loanChangeType"))){
 						vo.setPayPrimName(InAccount.getString("repayAccountName"));


					}
				vo.setPayOutItrFlg("1");//归还未结计利息标志--后续修改
				if(null != change.getString("isModifyPlan") && "1".equals(change.getString("isModifyPlan"))) {
					vo.setPrinPlanFlg("1");//下发新的还本计划标志
				}else {
					vo.setPrinPlanFlg("0");//下发新的还本计划标志
				}
				if("1".equals(change.getString("isSettle"))){
					vo.setPadUpAmt(change.getBigDecimal("yhze"));//还款金额
					vo.setPadUpPrn(change.getBigDecimal("yhbj"));//实收本金金额
					vo.setPadUpNorItrIn(change.getBigDecimal("yhzclx"));//正常利息金额
					vo.setPadUpDftItrIn(change.getBigDecimal("yhtqlx"));//拖欠利息金额
					vo.setPadUpPnsItrIn(change.getBigDecimal("yhfx"));//罚息金额
					//vo.setPadUpCpdItrIn(new BigDecimal(33));//复利金额--需要修改
				}else{
					vo.setPadUpAmt(change.getBigDecimal("yhze"));//还款金额
					vo.setPadUpPrn(change.getBigDecimal("yhbj"));//实收本金金额
					vo.setPadUpNorItrIn(change.getBigDecimal("yhzclx"));//正常利息金额
					vo.setPadUpDftItrIn(change.getBigDecimal("yhtqlx"));//拖欠利息金额
					vo.setPadUpPnsItrIn(change.getBigDecimal("yhfx"));//罚息金额
					//vo.setPadUpCpdItrIn(new BigDecimal(33));//复利金额--需要修改
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
					if(null != change.getBigDecimal("padUpAdjOtdItr")){
						yhze = yhze.add(change.getBigDecimal("padUpAdjOtdItr"));
					}
					if(null != change.getBigDecimal("padUpAdjOtdPns")){
						yhze = yhze.add(change.getBigDecimal("padUpAdjOtdPns"));
					}
					if(null != change.getBigDecimal("padUpAdjOtdCpd")){
						yhze = yhze.add(change.getBigDecimal("padUpAdjOtdCpd"));
					}
					vo.setPadUpAmt(yhze);//还款金额
					vo.setPadUpPrn(change.getBigDecimal("shbj"));//实收本金金额
					vo.setPadUpNorItrIn(change.getBigDecimal("shzclx"));//正常利息金额
					vo.setPadUpDftItrIn(change.getBigDecimal("shtqlx"));//拖欠利息金额
					vo.setPadUpPnsItrIn(change.getBigDecimal("shfx"));//罚息金额
					vo.setPadUpCpdItrIn(change.getBigDecimal("shfl"));//复利金额
					vo.setPadUpAdjOtdItr(change.getBigDecimal("padUpAdjOtdItr"));//未结计正常利息
					vo.setPadUpAdjOtdPns(change.getBigDecimal("padUpAdjOtdPns"));//未结计罚息
					vo.setPadUpAdjOtdCpd(change.getBigDecimal("padUpAdjOtdCpd"));//未结计复利
				}
				vo.setBaseVO(bvo);
				params1[0] = vo;
				//调用核算还款控制信息
				CrmsCallPlusProxy proxy1 = new CrmsCallPlusImpl();
				RepayControlInfRq rectol = proxy1.executeT1202(vo);
				if("00000".equals(rectol.getBaseVO().getErrCod())){
					RepaymentRq rqs = new RepaymentRq();
					rqs.setDueNum(vo.getDueNum());
					rqs.setTelNo(vo.getTelNo());
					rqs.setPayOutItrFlg(vo.getPayOutItrFlg());
					rqs.setProductType(loan.getString("productType"));
					BaseVO bvo1 = vo.getBaseVO();
					bvo1.setTranCod("T1102");
					bvo1.setTranTimes("1");
					bvo1.setToCoreSys("0");
					bvo1.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo1.setRcnStan(lcsStan);
					bvo1.setOpr(opr);
					rqs.setBaseVO(bvo1);
					params1[0] = rqs;
					ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("retPaymentEasyLcs", params1);
					RepaymentRq epaymentRq = (RepaymentRq)objs[0];
					BaseVO baseVO = epaymentRq.getBaseVO();
					String returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}
				}else{
					throw new EOSException(rectol.getBaseVO().getErrMsg());
				}
				summary.set("rcnStan", lcsStan);
				DatabaseUtil.updateEntity("default", summary);
				
//				Map map2 = new HashMap();
//				map2.put("partyId", summary.get("partyId"));
//				DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
//				
//				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
//				Object[] objs = null;
//				objs = logicComponent.invoke("retPaymentEasyLcs", params1);
////				DataObject vo1 = (DataObject) objs[0];
////				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
//				RetPaymentInfoVo retPaymentInfoVo = (RetPaymentInfoVo)objs[0];
//				BaseVO baseVO = retPaymentInfoVo.getBaseVO();
//				String returnCode = (String) baseVO.getErrCod();
//				if (!"200".equals(returnCode)) {
//					throw new EOSException(baseVO.getErrMsg());
//				}
				
				

			}else if("14".equals(change.getString("loanChangeType"))) {//停息、终止停息
				
				if("01".equals(change.getString("newTingxiStatus"))) {//停息
					
					//1-1501停息申请
					Object[] params1 = new Object[2];
					params1[0] = "MA1_5105";
//					PayConInfo vo = new PayConInfo();
//					vo.getBaseVO().setTranCod("MA1_1501");//交易代码
//					vo.getBaseVO().setOpr("HX001");//操作员
//					vo.getBaseVO().setAut("HX001");//授权员
//					vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//					vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
					//vo.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
					//贷款开户机构修改为放款表中的Loanorg
//					vo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					//vo.setOpnDep(summary.getString("orgNum"));//贷款开户机构
					StopControlRq vo = new StopControlRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1203");
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
					bvo.setToCoreSys("0");
					bvo.setTranTimes("1");
					bvo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					vo.setBaseVO(bvo);
					vo.setDueNum(summary.getString("summaryNum"));////借据编号
					vo.setTelNo(change.getString("changeNum"));//通知书编号
					params1[1] = vo;
					ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					DataObject vo1 = (DataObject) objs[0];
					BaseVO baseVO = (BaseVO) vo1.get("baseVO");
					String returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}
					
					
					//2-1103停息，一次交易
					Object[] params2 = new Object[2];
					params2[0] = "MA1_5106";
					
//					PayConInfo vo2 = new PayConInfo();
//					vo2.getBaseVO().setTranCod("MA1_1103");//交易代码
//					vo2.getBaseVO().setOpr("HX001");//操作员
//					vo2.getBaseVO().setAut("HX001");//授权员
//					vo2.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo2.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo2.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo2.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo2.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo2.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo2.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo2.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//					vo2.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					//vo2.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
//					//贷款开户机构修改为放款表中的Loanorg
//					vo2.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo2.setOpnDep(summary.getString("orgNum"));//贷款开户机构
					//停息第一次交易
					StopItrRq vo2 = new StopItrRq();
					BaseVO bvo2 = new BaseVO();
					bvo2.setTranCod("T1103");
					bvo2.setOpr(GitUtil.getCurrentUserId());
					bvo2.setAut(GitUtil.getCurrentUserId());
					bvo2.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo2.setRcnStan(lcsStan);
					bvo2.setTrnDep(loan.getString("loanOrg"));
					bvo2.setTranFrom("47");
					bvo2.setOrigFrom("11000");
					bvo2.setLegPerCod("9999");
					bvo2.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo2.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo2.setDepCod(GitUtil.getBranchId());
					bvo2.setToCoreSys("0");
					bvo2.setTranTimes("1");
					bvo2.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					vo2.setBaseVO(bvo2);
					vo2.setDueNum(summary.getString("summaryNum"));////借据编号
					vo2.setTelNo(change.getString("changeNum"));//通知书编号
					params2[1] = vo2;
					ILogicComponent logicComponent2 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs2 = null;
					objs2 = logicComponent2.invoke("newDataInsertCheck", params2);
					DataObject vo22 = (DataObject) objs2[0];
					BaseVO baseVO2 = (BaseVO) vo22.get("baseVO");
					String returnCode2 = (String) baseVO2.getErrCod();
					if (!"00000".equals(returnCode2)) {
						throw new EOSException(baseVO2.getErrMsg());
					}else{
						//停息第二次交易
						StopItrRq vo3 = new StopItrRq();
						vo3.setBaseVO((BaseVO)vo22.get("baseVO"));
						vo3.setTelNo(vo22.getString("telNo"));
						vo3.setDueNum(vo22.getString("dueNum"));
						vo3.setBrwName(vo22.getString("brwName"));
						vo3.setRcvPrn(vo22.getBigDecimal("rcvPrn"));
						vo3.setRcvNorItrIn(vo22.getBigDecimal("rcvNorItrIn"));
						vo3.setRcvDftItrIn(vo22.getBigDecimal("rcvDftItrIn"));
						vo3.setRcvPnsItrIn(vo22.getBigDecimal("rcvPnsItrIn"));
						vo3.setRcvCpdItrIn(vo22.getBigDecimal("rcvCpdItrIn"));
						vo3.setConNo(vo22.getString("conNo"));
						vo3.setBegDate(vo22.getString("begDate"));
						vo3.setEndDate(vo22.getString("endDate"));
						BaseVO bvo3 = vo3.getBaseVO();
						bvo3.setTranTimes("2");
						vo3.setBaseVO(bvo3);
						Object[] params3 = new Object[2];
						params3[0] = "MA1_5106";
						params3[1] = vo3;
						
						ILogicComponent logicComponent3 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
						Object[] objs3 = null;
						objs3 = logicComponent3.invoke("newDataInsertCheck", params3);
						DataObject vo33 = (DataObject) objs3[0];
						BaseVO baseVO3 = (BaseVO) vo33.get("baseVO");
						String returnCode3 = (String) baseVO3.getErrCod();
						if (!"00000".equals(returnCode3)) {
							throw new EOSException(baseVO3.getErrMsg());
						}
					}
					
//					//2-1103停息，二次交易
//					Object[] params3 = new Object[2];
//					params3[0] = "MA1_1103";
//					
//					PayConInfo vo3 = new PayConInfo();
//					vo3.getBaseVO().setTranCod("MA1_1103");//交易代码
//					vo3.getBaseVO().setOpr("HX001");//操作员
//					vo3.getBaseVO().setAut("HX001");//授权员
//					vo3.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo3.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo3.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo3.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo3.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo3.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo3.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo3.getBaseVO().setTranTimes("2");//交易次数标志 1次交易后填2，二次交易后填3
//					vo3.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					//vo2.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
//					//贷款开户机构修改为放款表中的Loanorg
//					vo3.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo3.setOpnDep(summary.getString("orgNum"));//贷款开户机构
//					vo3.setDueNum(summary.getString("summaryNum"));////借据编号
//					vo3.setTelNo(change.getString("changeNum"));//通知书编号
//					
//					params3[1] = vo3;
//					
//					ILogicComponent logicComponent3 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
//					Object[] objs3 = null;
//					objs3 = logicComponent3.invoke("newDataInsertCheck", params3);
//					DataObject vo33 = (DataObject) objs3[0];
//					BaseVO baseVO3 = (BaseVO) vo33.get("baseVO");
//					String returnCode3 = (String) baseVO3.getErrCod();
//					if (!"200".equals(returnCode3)) {
//						throw new EOSException(baseVO3.getErrMsg());
//					}
					
				}else if("02".equals(change.getString("newTingxiStatus"))) {//终止停息
					
					//1-1502终止停息
					Object[] params1 = new Object[2];
					params1[0] = "MA1_5107";
					
//					PayConInfo vo = new PayConInfo();
//					vo.getBaseVO().setTranCod("MA1_1502");//交易代码
//					vo.getBaseVO().setOpr("HX001");//操作员
//					vo.getBaseVO().setAut("HX001");//授权员
//					vo.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
//					vo.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					vo.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//贷款开户机构修改为放款表中的Loanorg
//					vo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo.setOpnDep(summary.getString("orgNum"));//贷款开户机构
					StopStopControlRq vo = new StopStopControlRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1204");
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
					bvo.setToCoreSys("0");
					bvo.setTranTimes("2");
					bvo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					vo.setBaseVO(bvo);
					vo.setDueNum(summary.getString("summaryNum"));//借据编号
					vo.setTelNo(change.getString("changeNum"));//通知书编号
					vo.setRcvItrYype(change.getString("stopRateType"));
					vo.setRcvNorItrIn(change.getBigDecimal("stopZcRate"));
					vo.setRcvDftItrIn(change.getBigDecimal("stopTqRate"));
					vo.setRcvPnsItrIn(change.getBigDecimal("stopFx"));
					vo.setRcvCpdItrIn(change.getBigDecimal("stopFl"));
					/*log.info("type--->" + change.getString("stopRateType"));
					String[] str = change.getString("stopRateType").split(",");
					String base = "000";
					char[] ch = base.toCharArray();
					for(int i=0;i<str.length;i++) {
						log.info("str--->" + str[i]);
						if("1".equals(str[i])) {//正常利息
							ch[0] = '1';
						}
						if("2".equals(str[i])) {//拖欠利息
							ch[1] = '1';			
						}
						if("3".equals(str[i])) {//罚息
							ch[2] = '1';
						}
					}
					String str2 = new String(ch);
					log.info("str2--->" + str2);
					vo.setRcvItrType(str2);//终止停息收取利息类型
*/					
//					vo.setRcvItrType(change.getString("stopRateType"));//终止停息收取利息类型
//					vo.setNorItrIn(change.getBigDecimal("stopZcRate"));
//					vo.setDftItrIn(change.getBigDecimal("stopTqRate"));
//					vo.setPnsItrIn(change.getBigDecimal("stopFx"));
					/*vo.setNorItrOut(change.getBigDecimal("stopZcRateOut"));
					vo.setDftItrOut(change.getBigDecimal("stopTqRateOut"));
					vo.setPnsItrOut(change.getBigDecimal("stopFxOut"));*/
					
					
					params1[1] = vo;
					
					ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					DataObject vo1 = (DataObject) objs[0];
					BaseVO baseVO = (BaseVO) vo1.get("baseVO");
					String returnCode = (String) baseVO.getErrCod();
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}
					
					
					//2-1104终止停息，一次交易
					/*Object[] params2 = new Object[2];
					params2[0] = "MA1_1104";
					
					PayConInfo vo2 = new PayConInfo();
					vo2.getBaseVO().setTranCod("MA1_1104");//交易代码
					vo2.getBaseVO().setOpr("HX001");//操作员
					vo2.getBaseVO().setAut("HX001");//授权员
					vo2.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
					vo2.getBaseVO().setRcnStan(lcsStan);//对账流水号
					vo2.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
					vo2.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
					vo2.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
					vo2.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
					vo2.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
					vo2.getBaseVO().setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
					vo2.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
					//vo2.getBaseVO().setOpnDep(summary.getString("orgNum"));//贷款开户机构
					//贷款开户机构修改为放款表中的Loanorg
					vo2.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					//vo2.setOpnDep(summary.getString("orgNum"));//贷款开户机构
					vo2.setDueNum(summary.getString("summaryNum"));////借据编号
					vo2.setTelNo(change.getString("changeNum"));//通知书编号
					
					params2[1] = vo2;
					
					ILogicComponent logicComponent2 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs2 = null;
					objs2 = logicComponent2.invoke("newDataInsertCheck", params2);
					DataObject vo22 = (DataObject) objs2[0];
					BaseVO baseVO2 = (BaseVO) vo22.get("baseVO");
					String returnCode2 = (String) baseVO2.getErrCod();
					if (!"200".equals(returnCode2)) {
						throw new EOSException(baseVO2.getErrMsg());
					}*/
					
					//1-1104终止停息，一次交易
					Object[] params3 = new Object[2];
					params3[0] = "MA1_5108";
					StopStopItrRq vo3 = new StopStopItrRq();
//					PayConInfo vo3 = new PayConInfo();
//					vo3.getBaseVO().setTranCod("MA1_1104");//交易代码
//					vo3.getBaseVO().setOpr("HX001");//操作员
//					vo3.getBaseVO().setAut("HX001");//授权员
//					vo3.getBaseVO().setAcsMethStan(lcsStan);//接入系统流水号
//					vo3.getBaseVO().setRcnStan(lcsStan);//对账流水号
//					vo3.getBaseVO().setSupStan(lcsStan);//自动生成9位放款流水号
//					vo3.getBaseVO().setTrnDep(summary.getString("orgNum"));//交易机构，会校验
//					vo3.getBaseVO().setTranFrom("001");//业务渠道来源 001-信贷系统
//					vo3.getBaseVO().setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
//					vo3.getBaseVO().setTranDate(bus_date);//接入系统营业日期 
//					vo3.getBaseVO().setTranTimes("2");//交易次数标志 1次交易后填2，二次交易后填3
//					vo3.getBaseVO().setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
//					vo3.getBaseVO().setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//贷款开户机构修改为放款表中的Loanorg
//					vo3.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
//					//vo3.setOpnDep(summary.getString("orgNum"));//贷款开户机构
					BaseVO bvo3 = new BaseVO();
					bvo3.setTranCod("T1104");
					bvo3.setOpr(GitUtil.getCurrentUserId());
					bvo3.setAut(GitUtil.getCurrentUserId());
					bvo3.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo3.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo3.setTrnDep(loan.getString("loanOrg"));
					bvo3.setTranFrom("47");
					bvo3.setOrigFrom("11000");
					bvo3.setLegPerCod("9999");
					bvo3.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo3.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo3.setDepCod(GitUtil.getBranchId());
					bvo3.setToCoreSys("0");
					bvo3.setTranTimes("1");
					bvo3.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
					vo3.setBaseVO(bvo3);
					vo3.setDueNum(summary.getString("summaryNum"));////借据编号
					vo3.setTelNo(change.getString("changeNum"));//通知书编号
					
					params3[1] = vo3;
					
					ILogicComponent logicComponent3 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs3 = null;
					objs3 = logicComponent3.invoke("newDataInsertCheck", params3);
					DataObject vo33 = (DataObject) objs3[0];
					BaseVO baseVO3 = (BaseVO) vo33.get("baseVO");
					String returnCode3 = (String) baseVO3.getErrCod();
					if (!"00000".equals(returnCode3)) {
						throw new EOSException(baseVO3.getErrMsg());
					}
					//2-1104终止停息，二次交易
					Object[] params4 = new Object[2];
					params4[0] = "MA1_5108";
					StopStopItrRq vo4 = new StopStopItrRq();
					vo4.setBrwName((String)vo33.get("brwName"));
					vo4.setRcvPrn((BigDecimal)vo33.get("rcvPrn"));
					vo4.setRcvNorItrIn((BigDecimal)vo33.get("rcvNorItrIn"));
					vo4.setRcvDftItrIn((BigDecimal)vo33.get("rcvDftItrIn"));
					vo4.setRcvPnsItrIn((BigDecimal)vo33.get("rcvPnsItrIn"));
					vo4.setCeasDate((String)vo33.get("ceasDate"));
					vo4.setRcvItrType((String)vo33.get("rcvItrType"));
					vo4.setPadUpNorItrIn((BigDecimal)vo33.get("padUpNorItrIn"));
					vo4.setPadUpDftItrIn((BigDecimal)vo33.get("padUpDftItrIn"));
					vo4.setPadUpPnsItrIn((BigDecimal)vo33.get("padUpPnsItrIn"));
					vo4.setPadUpCpdItrIn((BigDecimal)vo33.get("padUpCpdItrIn"));
					vo4.setTotItr((BigDecimal)vo33.get("totItr"));
					vo4.setTelNo((String)vo33.get("telNo"));
					vo4.setDueNum((String)vo33.get("dueNum"));
					vo4.setConNo((String)vo33.get("conNo"));
					vo4.setBegDate((String)vo33.get("begDate"));
					vo4.setEndDate((String)vo33.get("endDate"));
					vo4.setBaseVO(baseVO3);
					params4[1] = vo4;
					
					ILogicComponent logicComponent4 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
					Object[] objs4 = null;
					objs4 = logicComponent4.invoke("newDataInsertCheck", params4);
					DataObject vo44 = (DataObject) objs4[0];
					BaseVO baseVO4 = (BaseVO) vo44.get("baseVO");
					String returnCode4 = (String) baseVO4.getErrCod();
					if (!"00000".equals(returnCode4)) {
						throw new EOSException(baseVO4.getErrMsg());
					}
				}
				
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	@Bizlet("")
	public String getLoanSubject(String loanId,String changeId) {
//		//业务别
//		String ls;
//		DataObject loanInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
//		loanInfo.set("loanId", loanId);
//		DatabaseUtil.expandEntity("default", loanInfo);
//
//		//业务别第一到六位
//		String ls1to6;
//		String productType = (String)loanInfo.get("productType");
//		
//		DataObject productInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
//		productInfo.set("productCd", productType);
//		DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
//		
//		if(null== productInfo.get("bizDon")){
//			ls1to6 = "000000";
//		}else{
//			ls1to6 = productInfo.get("bizDon").toString();
//		}
//		HashMap map = new HashMap();
//		map.put("loanId", loanId);
//		if ("01002002".equals(productType)) {//普通个人住房、商业用房开发贷款根据项目类型区分个人还是商用
//			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.LoanSubject.getZkflx", map);
//			DataObject ms = (DataObject) objs[0];
//			int m = ms.getInt("C");
//			//9632 根据房地产开发贷款业务明细中录入的项目信息中的“主开发类型”来区分，
//			//如果关联多个项目信息，主开发类型既有住房开发，又有商用户开发，以住房开发优先
//			if(m>0){//只要有一个符合住房开发就算住房开发
//				ls1to6 = "110202";
//			}else{
//				ls1to6 = "110203";
//			}
//		}
//		//业务别第七位
//		String ls7 = "1";
//		String contractId = loanInfo.getString("contractId");
//		DataObject conInfo = DataObjectUtil
//				.createDataObject("com.bos.dataset.crt.TbConContractInfo");
//		conInfo.set("contractId", contractId);
//		DatabaseUtil.expandEntity("default", conInfo);
//		String mainGuarantyType = conInfo.getString("mainGuarantyType");
//		/**
//		 * 01-信用
//		 * 02-抵押
//		 * 03-质押
//		 * 04-保证
//		 * 05-保证金
//		 * */
//		if ("01".equals(mainGuarantyType)) {//信用
//			ls7 = "1";
//		} else if ("02".equals(mainGuarantyType)) {//抵押
//			ls7 = "3";
//		} else if ("03".equals(mainGuarantyType)) {//质押
//			ls7 = "4";
//		} else if ("04".equals(mainGuarantyType)) {//保证
//			ls7 = "2";
//		}
//
//		//业务别第八位(取新到期日)
//		String ls8;
//		 map = new HashMap();
//		map.put("changeId", changeId);
//		Object[] objs = DatabaseExt.queryByNamedSql("default",
//				"com.bos.aft.conLoanChange.getLoanSummary", map);
//		DataObject ms = (DataObject) objs[0];
//		BigDecimal m = ms.getBigDecimal("M");
//		//if(aviAmt.compareTo(loanAmt)>=0)
//		if (m.compareTo(new BigDecimal("12")) <= 0) {
//			ls8 = "1";
//		} else if (m.compareTo(new BigDecimal("12")) > 0
//				&& m.compareTo(new BigDecimal("60")) <= 0) {
//			ls8 = "2";
//		} else {
//			ls8 = "3";
//		}
//
//		//业务别
//		ls = ls1to6 + ls7 + ls8;
//		return ls;
		return "";
	}
	
}
