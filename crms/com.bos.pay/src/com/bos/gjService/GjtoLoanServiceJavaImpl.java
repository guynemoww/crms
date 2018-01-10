package com.bos.gjService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.batch.DealAccount;
import com.bos.biz.MathHelper;
import com.bos.inter.LoanToLcs;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.mgrcore.F76091;
import com.primeton.mgrcore.OXD041_SuccessFlagChkReq;
import com.primeton.mgrcore.OXD042ResBody;
import com.primeton.mgrcore.OXD042_SuccessFlagChkRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.mgrcore.client.DateTools;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import commonj.sdo.DataObject;

/**
 * 国结调用信贷接口Java实现方法
 */
@Bizlet("")
public class GjtoLoanServiceJavaImpl {
	private TraceLogger log = new TraceLogger(GjtoLoanServiceImpl.class);

	/**
	 * @param g001Request 
	 * 出账接口 国结调用信贷出账接口 信贷系统做如下处理 
	 * 1 调用核算的出账接口 
	 * 2 更新借据表的状态、余额以及最新的更新时间 
	 * 3保存国结的流水号与信贷借据号的对应关系到信贷 这个流水号可用于到核心查询交易是否成功  相当于一个中间过程当交易出现异常
	 *  比如超时 可以根据相关数据判断是否对该放款做过先关处理
	 * 4 重算客户的额度
	 */
	@Bizlet("")
	public G001Response executeG001(G001Request g001Request) {
		System.out.println("国结调用INTERFACE---G001。。。");
		G001Response rs = new G001Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g001Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g001Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g001Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g001Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g001Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g001Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g001Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g001Request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(g001Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g001Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g001Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G001ResponseBody responseBody = new G001ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			// 判断是否国结的请求方系统代码---01501
			if ("01501".equals(g001Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G001
				if ("G001".equals(g001Request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g001Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g001Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g001Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g001Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g001Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g001Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					log.info("国结调用信贷放款接口数据校验开始---begin to check data");
					// 参数校验---借据号
					if (null == g001Request.getRequestBody().getSummaryNum() || "".equals(g001Request.getRequestBody().getSummaryNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{借据编号}不能为空]");
						return rs;
					}
					// 参数校验---放款金额
					if (null == g001Request.getRequestBody().getPayAmt()) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{放款金额}不能为空]");
						return rs;
					}
					// 参数校验---国结流水
					if (null == g001Request.getRequestBody().getGjFlowNo() || "".equals(g001Request.getRequestBody().getGjFlowNo())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{国结流水号}不能为空]");
						return rs;
					}
					BigDecimal happenAmount = g001Request.getRequestBody().getPayAmt();// 放款金额
					String summaryNum = g001Request.getRequestBody().getSummaryNum();// 借据编号
					String gjFlowNo = g001Request.getRequestBody().getGjFlowNo();// 国结流水号
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (null == loanSummary.get("loanId") || "".equals(loanSummary.get("loanId"))) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的借据信息]");
						return rs;
					}
					// 校验放款金额---放款金额必须等于借据可用金额=借据金额-借据余额
					BigDecimal jjye = loanSummary.getBigDecimal("jjye");//借据余额
					BigDecimal summaryAmt = loanSummary.getBigDecimal("summaryAmt");//借据金额
					BigDecimal bal = summaryAmt.subtract(jjye);//借据可用金额
					if (!(bal.compareTo(happenAmount) == 0)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[放款金额必须等于借据可用金额]");
						return rs;
					}
					log.info("国结调用信贷放款接口数据校验结束---end to check data");

					log.info("国结调用信贷放款接口流水记录第一次插入开始---begin to insert into TbPubTransWater first time");
					// 1 保存国结的流水号到信贷系统---未处理
					DataObject pubTransWater = DataObjectUtil.createDataObject("com.bos.pub.sys.TbPubTransWater");
					pubTransWater.set("waterNum", gjFlowNo);
					pubTransWater.set("summaryNum", summaryNum);
					pubTransWater.set("status", "0");
					pubTransWater.set("debtType", "0");
					pubTransWater.set("debtAmt", happenAmount);
					pubTransWater.set("waterTime", GitUtil.getBusiDate());
					DatabaseUtil.saveEntity("default", pubTransWater);
					log.info("国结调用信贷放款接口流水记录第一次插入结束---end to insert into TbPubTransWater first time");

					log.info("国结调用信贷放款接口，信贷调用核心放款接口开始---begin to execute loanToLcsForGJ");
					// 2 调用核算放款接口
					LoanToLcs tl = new LoanToLcs();
					String loanId = loanSummary.getString("loanId");// 放款ID
					Map map1 = new HashMap<String,String>();
					map1 = tl.loanToLcsForGJ(loanId, gjFlowNo);
					if(null == map1.get("resCode")||!"00000".equals(map1.get("resCode"))){
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[核算系统响应失败，失败原因："+map1.get("msg")+"]");
						return rs;
					}
					log.info("国结调用信贷放款接口，信贷调用核心放款接口结束---end to execute loanToLcsForGJ");

					log.info("国结调用信贷放款接口 ，信贷更新借据表数据，保存相关数据以及调用额度重算开始---begin to handle data");
					//3 更新借据表
					DealAccount.singleSynch(summaryNum);
					
					// 3 更新借据表借据状态、借据余额、最新更新时间
					//HashMap map = new HashMap();
					//map.put("jjye", jjye);
					//map.put("PymtAmt", happenAmount);
					//String gs = "jjye+PymtAmt";
					//jjye = MathHelper.expressionBigDecimal(gs, map);
					//loanSummary.set("jjye", jjye);// 借据余额
					//loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
					//loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
					//DatabaseUtil.saveEntity("default", loanSummary);
					
					// 4 调用额度重算
					String partyId = loanSummary.getString("partyId");
					Map map2 = new HashMap();
					map2.put("partyId", partyId);
					DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
					log.info("国结调用信贷放款接口 ，信贷更新借据表数据，保存相关数据以及调用额度重算结束---end to handle data");

					// 5 保存国结的流水号到信贷系统---已处理
					log.info("国结调用信贷放款接口流水记录更新插入开始---begin to insert into TbPubTransWater 2nd time");
					pubTransWater.set("status", "1");
					DatabaseUtil.updateEntity("default", pubTransWater);
					log.info("国结调用信贷放款接口流水记录第二次更新开始---begin to insert into TbPubTransWater 2nd time");
					
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			e.printStackTrace();
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			e.printStackTrace();
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}

	/**
	 * @param g002Request 
	 * 还款接口 国结调用信贷做还款 信贷系统做如下处理 
	 * 1 调用核算的还款接口 
	 * 2 更新借据表的状态、借据余额以及最新更新时间
	 * 3保存国结的流水号与信贷借据号的对应关系到信贷 这个流水号可用于到核心查询交易是否成功  相当于一个中间过程当交易出现异常
	 *  比如超时 可以根据相关数据判断是否对该放款做过先关处理
	 * 4 重算客户的额度
	 */
	@Bizlet("")
	public G002Response executeG002(G002Request g002Request) {
		System.out.println("ESB 国结方法进来了 G002。。。");
		G002Response rs = new G002Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g002Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g002Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g002Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g002Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g002Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g002Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g002Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g002Request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(g002Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g002Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g002Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G002ResponseBody responseBody = new G002ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			// 判断是否国结的请求方系统代码---01501
			if ("01501".equals(g002Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G002
				if ("G002".equals(g002Request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g002Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g002Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g002Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g002Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g002Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g002Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					log.info("国结调用信贷还款接口数据校验开始---begin to check data");
					// 参数校验---借据号
					if (null == g002Request.getRequestBody().getSummaryNum() || "".equals(g002Request.getRequestBody().getSummaryNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{借据编号}不能为空]");
						return rs;
					}
					// 参数校验---还款金额
					if (null == g002Request.getRequestBody().getPadUpAmt()) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{还款金额}不能为空]");
						return rs;
					}
					// 参数校验---国结流水
					if (null == g002Request.getRequestBody().getGjFlowNo() || "".equals(g002Request.getRequestBody().getGjFlowNo())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{国结流水号}不能为空]");
						return rs;
					}
					// 参数校验---还款顺序
					if (null == g002Request.getRequestBody().getPayOrder() || "".equals(g002Request.getRequestBody().getPayOrder())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{还款顺序}不能为空]");
						return rs;
					}
					BigDecimal happenAmount = g002Request.getRequestBody().getPadUpAmt();// 还款金额
					String summaryNum = g002Request.getRequestBody().getSummaryNum();// 借据编号
					String gjFlowNo = g002Request.getRequestBody().getGjFlowNo();// 国结流水号
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (null == loanSummary.get("loanId") || "".equals(loanSummary.get("loanId"))) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的借据信息]");
						return rs;
					}
					//BigDecimal summaryAmt = loanSummary.getBigDecimal("summaryAmt");// 借据金额
					//HashMap hashMap = new HashMap();
					//hashMap.put("summaryAmt", summaryAmt);
					//hashMap.put("jjye", jjye);
					//String js = "summaryAmt-jjye";
					//BigDecimal yfkje = MathHelper.expressionBigDecimal(js, hashMap);// 借据已放款金额
					// 校验还款金额---还款金额必须小于等于借据已放款金额(借据余额)---有还利息的情况 这个逻辑不要 
					
					//BigDecimal jjye = loanSummary.getBigDecimal("jjye");// 借据余额
					//if (happenAmount.compareTo(jjye) == 1) {
						//rs.getResTranHeader().setHRetCode("BBBBBBB");
						//rs.getResTranHeader().setHRetMsg("交易失败[还款金额必须小于等于借据已放款金额]");
						//return rs;
					//}
					log.info("国结调用信贷还款接口数据校验结束---end to check data");

					log.info("国结调用信贷还款接口流水记录第一次插入开始---begin to insert into TbPubTransWater first time");
					// 1 保存国结的流水号到信贷系统---未处理
					DataObject pubTransWater = DataObjectUtil.createDataObject("com.bos.pub.sys.TbPubTransWater");
					pubTransWater.set("waterNum", gjFlowNo);
					pubTransWater.set("summaryNum", summaryNum);
					pubTransWater.set("status", "0");
					pubTransWater.set("debtType", "1");
					pubTransWater.set("debtAmt", happenAmount);
					pubTransWater.set("waterTime", GitUtil.getBusiDate());
					DatabaseUtil.saveEntity("default", pubTransWater);
					log.info("国结调用信贷还款接口流水记录第一次插入结束---end to insert into TbPubTransWater first time");

					log.info("国结调用信贷还款接口，信贷调用核心还款接口开始---begin to execute gjRepaymentRq");
					// 2 调用核心还款接口
					LoanToLcs tl = new LoanToLcs();
					String loanId = loanSummary.getString("loanId");// 放款ID
					//还款账户信息
					DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
					zh.set("loanId", loanId);//放款ID
					zh.set("zhlx", "1");//还款账户
					DatabaseUtil.expandEntityByTemplate("default", zh, zh);
					if(null == zh.getString("id") || "".equals(zh.getString("id"))){
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的还款账户信息]");
						return rs;
					}
					if(null == zh.getString("zh") || "".equals(zh.getString("zh"))){
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的还款账户账号]");
						return rs;
					}
					if(null == zh.getString("zhmc") || "".equals(zh.getString("zhmc"))){
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的还款账户名称]");
						return rs;
					}
					g002Request.getRequestBody().setPayPrimAcct(zh.getString("zh"));//还款账户
					g002Request.getRequestBody().setPayPrimName(zh.getString("zhmc"));//还款账户名称
					tl.loanToRepayForGJ(g002Request.getRequestBody(), loanId);
					log.info("国结调用信贷还款接口，信贷调用核心还款接口结束---end to execute gjRepaymentRq");

					log.info("国结调用信贷还款接口 ，信贷更新借据表数据，保存相关数据以及调用额度重算开始---begin to handle data");
					
					// 3 更新借据表
					DealAccount.singleSynch(summaryNum);
					//HashMap map = new HashMap();
					//map.put("jjye", jjye);
					//map.put("PymtAmt", happenAmount);
					//String gs = "jjye-PymtAmt";
					//jjye = MathHelper.expressionBigDecimal(gs, map);
					//loanSummary.set("jjye", jjye);// 借据余额
					//部分还款---借据状态 正常  全部还款---借据状态 结清
					//if(jjye.compareTo(BigDecimal.ZERO)==0){
						//loanSummary.set("summaryStatusCd", "04");// 全部还款借据借据状态：正常
					//}else{
						//loanSummary.set("summaryStatusCd", "02");// 部分还款借据借据状态：正常
					//}
					//loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
					//DatabaseUtil.saveEntity("default", loanSummary);

					// 4 调用额度重算
					String partyId = loanSummary.getString("partyId");
					Map map1 = new HashMap();
					map1.put("partyId", partyId);
					DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map1);
					log.info("国结调用信贷还款接口 ，信贷更新借据表数据，保存相关数据以及调用额度重算结束---end to handle end");

					// 5 保存国结的流水号到信贷系统---已处理
					log.info("国结调用信贷还款接口流水记录更新插入开始---begin to insert into TbPubTransWater 2nd time");
					pubTransWater.set("status", "1");
					DatabaseUtil.updateEntity("default", pubTransWater);
					log.info("国结调用信贷还款接口流水记录第二次更新开始---begin to insert into TbPubTransWater 2nd time");
					
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			e.printStackTrace();
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			e.printStackTrace();
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}

	// 本息查询接口
	@Bizlet("")
	public G003Response executeG003(G003Request g003Request) {
		System.out.println("ESB 国结方法进来了 G003。。。");
		G003Response rs = new G003Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g003Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g003Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g003Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g003Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g003Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g003Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g003Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g003Request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(g003Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g003Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g003Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G003ResponseBody responseBody = new G003ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			// 判断是否国结的请求方系统代码---01501
			if ("01501".equals(g003Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G003
				if ("G003".equals(g003Request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g003Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g003Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g003Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g003Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g003Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g003Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					
					log.info("国结调用信贷本息查询接口数据校验开始---begin to check data");
					// 参数校验---借据编号
					if (null == g003Request.getRequestBody().getDueNum() || "".equals(g003Request.getRequestBody().getDueNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{借据编号}不能为空]");
						return rs;
					}
					String summaryNum = g003Request.getRequestBody().getDueNum();// 借据编号
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (null == loanSummary.get("loanId") || "".equals(loanSummary.get("loanId")) ) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的借据信息]");
						return rs;
					}
					log.info("国结调用信贷本息查询接口数据校验结束---end to check data");
					
					log.info("国结调用本息查询接口，信贷调用核算本息查询接口开始---begin to execute executeT1410");
					String dueNum = g003Request.getRequestBody().getDueNum();// 借据编号
					CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
					CrePayQueryRq rq = new CrePayQueryRq();
					rq.setDueNum(dueNum);
					if (null != g003Request.getRequestBody().getRcvPrn()) {
						rq.setRcvPrn(g003Request.getRequestBody().getRcvPrn());
					}
					String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
					String loanId = loanSummary.getString("loanId");// 放款ID
					DataObject loanInfo = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanInfo");
					loanInfo.set("loanId", loanId);
					DatabaseUtil.expandEntity("default", loanInfo);
					String loanorg = loanInfo.getString("loanOrg");
					BaseVO baseVO = new BaseVO();
					baseVO.setTranCod("T1410");//交易代码
					baseVO.setTranDate(bus_date);
					baseVO.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
					baseVO.setTrnDep(loanorg);//交易机构，会校验
					baseVO.setOpnDep(loanorg);//贷款开户机构
					baseVO.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
					baseVO.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
					baseVO.setTranFrom("47");
					baseVO.setOrigFrom("11000");
					baseVO.setLegPerCod("9999");
					baseVO.setOpr(GitUtil.getCurrentUserId());//操作员
					baseVO.setAut(GitUtil.getCurrentUserId());//授权员
					baseVO.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
					baseVO.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));//对账流水号
					rq.setBaseVO(baseVO);
					// 调用核算的本息查询
					CrePayQueryRq queryrs = proxy.executeT1410(rq);
					rs.getResponseBody().setResNor(null==queryrs.getResNor()?BigDecimal.ZERO:queryrs.getResNor());
					rs.getResponseBody().setDftPrnBal(null==queryrs.getDftPrnBal()?BigDecimal.ZERO:queryrs.getDftPrnBal());
					rs.getResponseBody().setRcvNorItrIn(null==queryrs.getRcvNorItrIn()?BigDecimal.ZERO:queryrs.getRcvNorItrIn());
					rs.getResponseBody().setRcvPnsItrIn(null==queryrs.getRcvPnsItrIn()?BigDecimal.ZERO:queryrs.getRcvPnsItrIn());
					rs.getResponseBody().setRcvDftItrIn(null==queryrs.getRcvDftItrIn()?BigDecimal.ZERO:queryrs.getRcvDftItrIn());
					rs.getResponseBody().setAdjOtdPns(null==queryrs.getAdjOtdPns()?BigDecimal.ZERO:queryrs.getAdjOtdPns());
					rs.getResponseBody().setAdjOtdCpd(null==queryrs.getAdjOtdCpd()?BigDecimal.ZERO:queryrs.getAdjOtdCpd());
					rs.getResponseBody().setRcvCpdItrIn(null==queryrs.getRcvCpdItrIn()?BigDecimal.ZERO:queryrs.getRcvCpdItrIn());
					rs.getResponseBody().setCurrPrjPrn(null==queryrs.getCurrPrjPrn()?BigDecimal.ZERO:queryrs.getCurrPrjPrn());
					rs.getResponseBody().setCurrPrjItr(null==queryrs.getCurrPrjItr()?BigDecimal.ZERO:queryrs.getCurrPrjItr());
					rs.getResponseBody().setPadUpAmt(null==queryrs.getPadUpAmt()?BigDecimal.ZERO:queryrs.getPadUpAmt());
					rs.getResponseBody().setTotPrnItr(null==queryrs.getTotPrnItr()?BigDecimal.ZERO:queryrs.getTotPrnItr());
					log.info("国结调用本息查询接口，信贷调用核算本息查询接口开始---end to execute executeT1410");
					
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @param g004Request 
	 * 国结业务通知
	 */
	@Bizlet("")
	public G004Response executeG004(G004Request g004Request) {
		System.out.println("国结调用INTERFACE---G004。。。");
		G004Response rs = new G004Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g004Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g004Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g004Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g004Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g004Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g004Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g004Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g004Request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(g004Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g004Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g004Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G004ResponseBody responseBody = new G004ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			// 判断是否国结的请求方系统代码---01501
			if ("01501".equals(g004Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G004
				if ("G004".equals(g004Request.getReqTranHeader().getHTxnCd())) {
					//本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g004Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g004Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g004Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g004Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g004Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g004Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					// 参数校验---借据号
					if (null == g004Request.getRequestBody().getDueNum() || "".equals(g004Request.getRequestBody().getDueNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{借据编号}不能为空]");
						return rs;
					}
					// 参数校验---通知类型
					if (null == g004Request.getRequestBody().getNoticeType() || "".equals(g004Request.getRequestBody().getNoticeType())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{通知类型}不能为空]");
						return rs;
					}
					// 参数校验---通知状态 成功通知还是失败通知(冲正通知)
					if (null == g004Request.getRequestBody().getStatus() || "".equals(g004Request.getRequestBody().getStatus())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{通知状态}不能为空]");
						return rs;
					}
					// 参数校验---发生金额
					if (null == g004Request.getRequestBody().getHappenAmount()) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{发生金额}不能为空]");
						return rs;
					}
					// 参数校验---是否垫款
					//if (null == g004Request.getRequestBody().getDkOrNot() || "".equals(g004Request.getRequestBody().getDkOrNot())) {
						//rs.getResTranHeader().setHRetCode("BBBBBBB");
						//rs.getResTranHeader().setHRetMsg("交易失败[参数{是否垫款}不能为空]");
						//return rs;
					//}
					//参数校验---垫款金额
					//if("1".equals(g004Request.getRequestBody().getDkOrNot())){//如果垫款
						//if (null == g004Request.getRequestBody().getDkje()) {
							//rs.getResTranHeader().setHRetCode("BBBBBBB");
							//rs.getResTranHeader().setHRetMsg("交易失败[参数{垫款金额}不能为空]");
							//return rs;
						//}
					//}
					String status = g004Request.getRequestBody().getStatus();//状态01 成功
					String summaryNum = g004Request.getRequestBody().getDueNum();// 借据编号
					String noticeType = g004Request.getRequestBody().getNoticeType();// 通知类型
					BigDecimal happenAmount = g004Request.getRequestBody().getHappenAmount();// 发生金额
					String ywhm = g004Request.getRequestBody().getYwhm();// 业务号码
					//String dkOrNot = g004Request.getRequestBody().getDkOrNot();//是否垫款
					//BigDecimal dkje = g004Request.getRequestBody().getDkje();//垫款金额
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (null == loanSummary.get("loanId")) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的借据信息]");
						return rs;
					}
					//String ywbh = loanSummary.getString("ywbh");
					//判断当前这个交易是不是已经做了业务通知---根据业务编号来判断
					//if ( null != ywbh && !"".equals(ywbh)) {
						//rs.getResTranHeader().setHRetCode("AAAAAAA");
						//rs.getResTranHeader().setHRetMsg("交易成功[该笔业务通知信贷已做相关处理]");
						//return rs;
					//}
					//信用证修改成功通知020003---要重算额度而且要更新信用证相关信息
					if("020003".equals(noticeType)){
						if("01".equals(status)){//成功通知
							//变更信息
							DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
							change.set("contractId", loanSummary.getString("contractId"));//变更合同ID
							change.set("summaryId", loanSummary.getString("summaryId"));//变更借据ID
							change.set("partyId", loanSummary.getString("partyId"));//变更客户ID
							change.set("changeStatus", "10");//变更状态---审批中
							change.set("loanChangeType", "12");//业务发生性质---12 信用证修改 13 保函修改
							DatabaseUtil.expandEntityByTemplate("default", change, change);
							//如果没有查询到变更信息
							if(null== change.get("changeId")||"".equals(change.get("changeId"))){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的变更信息]");
								return rs;
							}
							//合同信息
							DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
							tbConContractInfo.set("contractId", loanSummary.getString("contractId"));
							DatabaseUtil.expandEntity("default", tbConContractInfo);
							//如果没有查询到合同信息
							if(null== tbConContractInfo.get("contractId")){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的合同信息]");
								return rs;
							}
							//额度的处理
							BigDecimal oldje = new BigDecimal(0);
							oldje = loanSummary.getBigDecimal("summaryAmt");//修改前借据金额
							BigDecimal ce = new BigDecimal(0);//差额
							BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
							BigDecimal conje = new BigDecimal(0);//修改后合同金额
							BigDecimal conye = new BigDecimal(0);//修改后合同余额
							BigDecimal conYeE = new BigDecimal(0);//合同余额
							BigDecimal hl = new BigDecimal(1);//汇率
							if(null != tbConContractInfo.getBigDecimal("conYuE")) {
								conYeE = tbConContractInfo.getBigDecimal("conYuE");
							}
							if(null != loanSummary.getBigDecimal("exchangeRate")) {
								hl = loanSummary.getBigDecimal("exchangeRate");
							}
							HashMap map = new HashMap();
							map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
							if(oldje.compareTo(change.getBigDecimal("newSummaryamt")) < 0) {//金额增加
								ce = change.getBigDecimal("newSummaryamt").subtract(oldje);
								newjjye = loanSummary.getBigDecimal("jjye").add(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
								conye = conYeE.add(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
							}else {//金额减少
								ce = oldje.subtract(change.getBigDecimal("newSummaryamt"));
								newjjye = loanSummary.getBigDecimal("jjye").subtract(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
								conye = conYeE.subtract(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
							}

							//国际信用证开证
							DataObject tbConGjxyzkz = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGjxyzkz");
							tbConGjxyzkz.set("contractId", change.getString("contractId"));
							DatabaseUtil.expandEntityByTemplate("default", tbConGjxyzkz, tbConGjxyzkz);
							tbConGjxyzkz.set("dqrq", change.get("newXyzDqrq"));//信用证到期日期
							tbConGjxyzkz.set("bzjje", change.get("newBzjje"));//保证金金额
							tbConGjxyzkz.set("bzjblbdy", change.get("newBzjblbdy"));//保证金比例不低于
							tbConGjxyzkz.set("kzje", change.get("newKzje"));//开证金额
							//tbConGjxyzkz.set("jyq", change.getString("newXyzJyq"));//期限类型
							//tbConGjxyzkz.set("yqts", change.getString("newXyzYqts"));//远期天数
							DatabaseUtil.updateEntity("default", tbConGjxyzkz);
							//借据的数据
							//tbLoanSummary.set("summaryTerm", change.getInt("newTerm"));
							loanSummary.set("endDate", change.get("newXyzDqrq"));//借据止期和信用证到期日期一致
							loanSummary.set("summaryAmt", change.getBigDecimal("newSummaryamt"));
							loanSummary.set("jjye", newjjye);
							loanSummary.set("rmbAmt", change.getBigDecimal("newSummaryamt").multiply(hl));
							//信用证的通知只在开证通知的时候保存业务号码---信用证号
							//loanSummary.set("ywbh", ywhm);// 业务号码  
							DatabaseUtil.updateEntity("default", loanSummary);
							
							//贷后变更---信用证修改 不修改合同相关的金额 暂时注释掉代码---后面要修改可以放开
							//tbConContractInfo.set("contractAmt", conje);//不修改合同金额
							//tbConContractInfo.set("conYuE", conye);
							//tbConContractInfo.set("conBalance", tbConContractInfo.getBigDecimal("contractAmt").subtract(conye));
							//DatabaseUtil.updateEntity("default", tbConContractInfo);
							
							//更新变更信息
							change.set("changeStatus","03");//变更状态---已生效
							DatabaseUtil.updateEntity("default", change);
							
						}else{//失败通知---冲正 已经修改过一次  再发冲正---需要更新到修改之前的状态
							//变更信息
							DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
							change.set("contractId", loanSummary.getString("contractId"));//变更合同ID
							change.set("summaryId", loanSummary.getString("summaryId"));//变更借据ID
							change.set("partyId", loanSummary.getString("partyId"));//变更客户ID
							change.set("changeStatus", "03");//变更状态---已生效
							change.set("loanChangeType", "12");//业务发生性质---12 信用证修改 13 保函修改
							change.set("gjFlowNo", g004Request.getReqTranHeader().getHSenderSeq());//对修改做冲正---国结对应的修改流水
							DatabaseUtil.expandEntityByTemplate("default", change, change);
							//如果没有查询到变更信息
							if(null== change.get("changeId")||"".equals(change.get("changeId"))){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的变更信息]");
								return rs;
							}
							
							//合同信息
							DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
							tbConContractInfo.set("contractId", loanSummary.getString("contractId"));
							DatabaseUtil.expandEntity("default", tbConContractInfo);
							//如果没有查询到合同信息
							if(null== tbConContractInfo.get("contractId")){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的合同信息]");
								return rs;
							}
							
							//额度的处理
							BigDecimal oldje = new BigDecimal(0);
							oldje = loanSummary.getBigDecimal("summaryAmt");//修改前借据金额
							BigDecimal ce = new BigDecimal(0);//差额
							BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
							BigDecimal conje = new BigDecimal(0);//修改后合同金额
							BigDecimal conye = new BigDecimal(0);//修改后合同余额
							BigDecimal conYeE = new BigDecimal(0);//合同余额
							BigDecimal hl = new BigDecimal(1);//汇率
							if(null != tbConContractInfo.getBigDecimal("conYuE")) {
								conYeE = tbConContractInfo.getBigDecimal("conYuE");
							}
							if(null != loanSummary.getBigDecimal("exchangeRate")) {
								hl = loanSummary.getBigDecimal("exchangeRate");
							}
							HashMap map = new HashMap();
							map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
							if(oldje.compareTo(change.getBigDecimal("oldSummaryamt")) < 0) {//金额增加
								ce = change.getBigDecimal("oldSummaryamt").subtract(oldje);
								newjjye = loanSummary.getBigDecimal("jjye").add(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
								conye = conYeE.add(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
							}else {//金额减少
								ce = oldje.subtract(change.getBigDecimal("oldSummaryamt"));
								newjjye = loanSummary.getBigDecimal("jjye").subtract(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
								conye = conYeE.subtract(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
							}
							
							//国际信用证开证
							DataObject tbConGjxyzkz = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGjxyzkz");
							tbConGjxyzkz.set("contractId", change.getString("contractId"));
							DatabaseUtil.expandEntityByTemplate("default", tbConGjxyzkz, tbConGjxyzkz);
							tbConGjxyzkz.set("dqrq", change.get("oldXyzDqrq"));//信用证到期日期
							//tbConGjxyzkz.set("jyq", change.getString("oldXyzJyq"));//期限类型
							//tbConGjxyzkz.set("yqts", change.getString("oldXyzYqts"));//远期天数
							tbConGjxyzkz.set("bzjje", change.get("oldBzjje"));//保证金金额
							tbConGjxyzkz.set("bzjblbdy", change.get("oldBzjblbdy"));//保证金比例不低于
							tbConGjxyzkz.set("kzje", change.get("oldKzje"));//开证金额
							DatabaseUtil.updateEntity("default", tbConGjxyzkz);
							
							//借据的数据
							//tbLoanSummary.set("summaryTerm", change.getInt("newTerm"));
							loanSummary.set("endDate", change.get("oldXyzDqrq"));//借据止期和信用证到期日期一致
							loanSummary.set("summaryAmt", change.getBigDecimal("newSummaryamt"));
							loanSummary.set("jjye", newjjye);
							loanSummary.set("rmbAmt", change.getBigDecimal("newSummaryamt").multiply(hl));
							//信用证的通知只在开证通知的时候保存业务号码---信用证号
							//loanSummary.set("ywbh", ywhm);// 业务号码
							DatabaseUtil.updateEntity("default", loanSummary);
							//更新变更信息
							change.set("changeStatus","06");//变更状态---删除
							DatabaseUtil.updateEntity("default", change);
						}
					}
					//保函修改成功通知020026---要重算额度而且要更新保函相关信息
					if("020026".equals(noticeType)){
						if("01".equals(status)){//成功通知
							//变更信息
							DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
							change.set("contractId", loanSummary.getString("contractId"));//变更合同ID
							change.set("summaryId", loanSummary.getString("summaryId"));//变更借据ID
							change.set("partyId", loanSummary.getString("partyId"));//变更客户ID
							change.set("changeStatus", "10");//变更状态---审批中
							change.set("loanChangeType", "13");//业务发生性质---12 信用证修改 13 保函修改
							DatabaseUtil.expandEntityByTemplate("default", change, change);
							//如果没有查询到变更信息
							if(null== change.get("changeId")||"".equals(change.get("changeId"))){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的变更信息]");
								return rs;
							}
							//合同信息
							DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
							tbConContractInfo.set("contractId", loanSummary.getString("contractId"));
							DatabaseUtil.expandEntity("default", tbConContractInfo);
							//如果没有查询到合同信息
							if(null == tbConContractInfo.get("contractId")){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的合同信息]");
								return rs;
							}
							//额度的处理
							BigDecimal oldje = new BigDecimal(0);
							oldje = loanSummary.getBigDecimal("summaryAmt");//修改前借据金额
							BigDecimal ce = new BigDecimal(0);//差额
							BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
							BigDecimal conje = new BigDecimal(0);//修改后合同金额
							BigDecimal conye = new BigDecimal(0);//修改后合同余额
							BigDecimal conYeE = new BigDecimal(0);//合同余额
							BigDecimal hl = new BigDecimal(1);//汇率
							if(null != tbConContractInfo.getBigDecimal("conYuE")) {
								conYeE = tbConContractInfo.getBigDecimal("conYuE");
							}
							if(null != loanSummary.getBigDecimal("exchangeRate")) {
								hl = loanSummary.getBigDecimal("exchangeRate");
							}
							HashMap map = new HashMap();
							map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
							if(oldje.compareTo(change.getBigDecimal("newSummaryamt")) < 0) {//金额增加
								ce = change.getBigDecimal("newSummaryamt").subtract(oldje);
								newjjye = loanSummary.getBigDecimal("jjye").add(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
								conye = conYeE.add(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
							}else {//金额减少
								ce = oldje.subtract(change.getBigDecimal("newSummaryamt"));
								newjjye = loanSummary.getBigDecimal("jjye").subtract(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
								conye = conYeE.subtract(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
							}
							//国际保函的数据
							DataObject tbConJkbh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConJkbh");
							tbConJkbh.set("contractId", change.getString("contractId"));
							DatabaseUtil.expandEntityByTemplate("default", tbConJkbh, tbConJkbh);
							
							tbConJkbh.set("dqrq", change.get("newBhDqrq"));//保函到期日期
							tbConJkbh.set("bhlx", change.get("newBhBhlx"));//保函类型
							tbConJkbh.set("bzjblbdy", change.get("newBhBzjblbdy"));//保证金比例低于
							tbConJkbh.set("bzjje", change.get("newBhBzjje"));//保证金金额
							DatabaseUtil.updateEntity("default", tbConJkbh);
							
							
							//借据表的数据
							//loanSummary.set("summaryTerm", change.getInt("newTerm"));
							loanSummary.set("endDate", change.getDate("newBhDqrq"));//借据止期和保函到期日期一致
							loanSummary.set("summaryAmt", change.getBigDecimal("newSummaryamt"));
							loanSummary.set("jjye", newjjye);
							loanSummary.set("rmbAmt", change.getBigDecimal("newSummaryamt").multiply(hl));
							//保函的通知只在开立通知的时候保存业务号码
							//loanSummary.set("ywbh", ywhm);// 业务号码
							DatabaseUtil.updateEntity("default", loanSummary);
							
							//贷后变更---保函修改不修改合同相关的金额暂时注释掉代码---后面要修改可以放开
							//tbConContractInfo.set("contractAmt", conje);//不修改合同金额
							//tbConContractInfo.set("conYuE", conye);
							//tbConContractInfo.set("conBalance", tbConContractInfo.getBigDecimal("contractAmt").subtract(conye));
							//DatabaseUtil.updateEntity("default", tbConContractInfo);
							
							//更新变更信息
							change.set("changeStatus","03");//变更状态---已生效
							DatabaseUtil.updateEntity("default", change);
						}else{//失败通知---冲正
							//变更信息
							DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
							change.set("contractId", loanSummary.getString("contractId"));//变更合同ID
							change.set("summaryId", loanSummary.getString("summaryId"));//变更借据ID
							change.set("partyId", loanSummary.getString("partyId"));//变更客户ID
							change.set("changeStatus", "03");//变更状态---已生效
							change.set("loanChangeType", "12");//业务发生性质---12 信用证修改 13 保函修改
							change.set("gjFlowNo", g004Request.getReqTranHeader().getHSenderSeq());//对修改做冲正---国结对应的修改流水
							DatabaseUtil.expandEntityByTemplate("default", change, change);
							//如果没有查询到变更信息
							if(null== change.get("changeId")||"".equals(change.get("changeId"))){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的变更信息]");
								return rs;
							}
							
							//合同信息
							DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
							tbConContractInfo.set("contractId", loanSummary.getString("contractId"));
							DatabaseUtil.expandEntity("default", tbConContractInfo);
							//如果没有查询到合同信息
							if(null == tbConContractInfo.get("contractId")){
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的合同信息]");
								return rs;
							}
							//额度的处理
							BigDecimal oldje = new BigDecimal(0);
							oldje = loanSummary.getBigDecimal("summaryAmt");//修改前借据金额
							BigDecimal ce = new BigDecimal(0);//差额
							BigDecimal newjjye = new BigDecimal(0);//修改后借据余额
							BigDecimal conje = new BigDecimal(0);//修改后合同金额
							BigDecimal conye = new BigDecimal(0);//修改后合同余额
							BigDecimal conYeE = new BigDecimal(0);//合同余额
							BigDecimal hl = new BigDecimal(1);//汇率
							if(null != tbConContractInfo.getBigDecimal("conYuE")) {
								conYeE = tbConContractInfo.getBigDecimal("conYuE");
							}
							if(null != loanSummary.getBigDecimal("exchangeRate")) {
								hl = loanSummary.getBigDecimal("exchangeRate");
							}
							HashMap map = new HashMap();
							map.put("amountDetailId", tbConContractInfo.getString("amountDetailId"));
							if(oldje.compareTo(change.getBigDecimal("oldSummaryamt")) < 0) {//金额增加
								ce = change.getBigDecimal("oldSummaryamt").subtract(oldje);
								newjjye = loanSummary.getBigDecimal("jjye").add(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").add(ce);
								conye = conYeE.add(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditSub", map);
							}else {//金额减少
								ce = oldje.subtract(change.getBigDecimal("oldSummaryamt"));
								newjjye = loanSummary.getBigDecimal("jjye").subtract(ce);
								conje = tbConContractInfo.getBigDecimal("contractAmt").subtract(ce);
								conye = conYeE.subtract(ce);
								map.put("ce", ce.multiply(hl));
								DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateCreditAdd", map);
							}
							
							//国际保函的数据
							DataObject tbConJkbh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConJkbh");
							tbConJkbh.set("contractId", change.getString("contractId"));
							DatabaseUtil.expandEntityByTemplate("default", tbConJkbh, tbConJkbh);
							
							tbConJkbh.set("dqrq", change.get("oldBhDqrq"));//保函到期日期
							tbConJkbh.set("bhlx", change.get("oldBhBhlx"));//保函类型
							tbConJkbh.set("bzjblbdy", change.get("oldBhBzjblbdy"));//保证金比例低于
							tbConJkbh.set("bzjje", change.get("oldBhBzjje"));//保证金金额
							DatabaseUtil.updateEntity("default", tbConJkbh);
							
							//借据表的数据
							loanSummary.set("endDate", change.getDate("oldBhDqrq"));//借据止期和保函到期日期一致
							loanSummary.set("summaryAmt", change.getBigDecimal("oldSummaryamt"));
							loanSummary.set("jjye", newjjye);
							loanSummary.set("rmbAmt", change.getBigDecimal("oldSummaryamt").multiply(hl));
							//保函的通知只在开立通知的时候保存业务号码
							//loanSummary.set("ywbh", ywhm);// 业务号码
							DatabaseUtil.updateEntity("default", loanSummary);
							
							//更新变更信息
							change.set("changeStatus","06");//变更状态---已删除
							DatabaseUtil.updateEntity("default", change);
						}
					}
					BigDecimal jjye = loanSummary.getBigDecimal("jjye");// 借据余额
					HashMap map = new HashMap();
					map.put("jjye", jjye);
					map.put("PymtAmt", happenAmount);
					String gs = "";
					// 扣减额度的通知类型
					if ("020001".equals(noticeType) || "020028".equals(noticeType) || "020025".equals(noticeType) || 
						"020006".equals(noticeType) || "020041".equals(noticeType) || "030208".equals(noticeType)) {
						if("01".equals(status)){//成功通知
							gs = "jjye+PymtAmt";
							jjye = MathHelper.expressionBigDecimal(gs, map);
							loanSummary.set("jjye", jjye);// 借据余额
							loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
							loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							//信用证开证通知---业务号码是信用证号   因为其他信用证修改的时候 国结给的业务号码不是信用证号 为了保存信用证号  就只在开证修改的时候保存一次业务号码当做信用证号
							if("020028".equals(noticeType)){
								//信用证的通知只在开证通知的时候保存业务号码---信用证号
							}else{
								loanSummary.set("ywbh", ywhm);// 业务号码
							}
							
							DatabaseUtil.updateEntity("default", loanSummary);
						}else if("02".equals(status)){//失败通知---冲正通知
							gs = "jjye-PymtAmt";
							jjye = MathHelper.expressionBigDecimal(gs, map);
							loanSummary.set("jjye", jjye);// 借据余额
							loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
							loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							//信用证开证通知---业务号码是信用证号   因为其他信用证修改的时候 国结给的业务号码不是信用证号 为了保存信用证号  就只在开证修改的时候保存一次业务号码当做信用证号
							if("020028".equals(noticeType)){
								//信用证的通知只在开证通知的时候保存业务号码---信用证号
							}else{
								loanSummary.set("ywbh", ywhm);// 业务号码
							}
							DatabaseUtil.updateEntity("default", loanSummary);
						}
					} 
					// 释放额度的通知类型
					if ("020014".equals(noticeType) || "020012".equals(noticeType) || "020032".equals(noticeType) || 
						"020038".equals(noticeType) || "020007".equals(noticeType) || "020043".equals(noticeType) || 
						"020042".equals(noticeType) || "020010".equals(noticeType) || "020037".equals(noticeType) ||
						"020029".equals(noticeType) || "030212".equals(noticeType) || "030213".equals(noticeType) || 
						"020050".equals(noticeType) ) {
						if("01".equals(status)){//成功通知
							//款项转让、收汇清算---结清
							//保函/备用信用证撤销确认---借据状态  
							//进口代付还款 020043  释放  同时业务结清
							if("020032".equals(noticeType)||"020014".equals(noticeType)||"020042".equals(noticeType)||
							   "020007".equals(noticeType)||"020012".equals(noticeType)||"020038".equals(noticeType)||
							   "020043".equals(noticeType)||"030212".equals(noticeType)||"030213".equals(noticeType)){
								loanSummary.set("summaryStatusCd", "04");// 借据借据状态：结清
								jjye = BigDecimal.ZERO;
							}else{
								loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
								gs = "jjye-PymtAmt";
								jjye = MathHelper.expressionBigDecimal(gs, map);
							}
							if("020037".equals(noticeType)||"020014".equals(noticeType) || 
							   "020012".equals(noticeType)||"020010".equals(noticeType) ||
							   "020029".equals(noticeType)||"020032".equals(noticeType) ||
							   "020038".equals(noticeType)
									){
								//信用证/保函的通知只在开证通知的时候保存业务号码---信用证号
							}else{
								loanSummary.set("ywbh", ywhm);// 业务号码
							}
							loanSummary.set("jjye", jjye);// 借据余额
							loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							DatabaseUtil.updateEntity("default", loanSummary);
						}if("02".equals(status)){//失败通知---冲正通知
							gs = "jjye+PymtAmt";
							jjye = MathHelper.expressionBigDecimal(gs, map);
							loanSummary.set("jjye", jjye);// 借据余额
							loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
							loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							if("020037".equals(noticeType)||"020014".equals(noticeType) || 
							   "020012".equals(noticeType)||"020010".equals(noticeType) ||
							   "020029".equals(noticeType)||"020032".equals(noticeType) ||
							   "020038".equals(noticeType)){
								//信用证/保函的通知只在开证通知的时候保存业务号码---信用证号
							}else{
								loanSummary.set("ywbh", ywhm);// 业务号码
							}
							DatabaseUtil.updateEntity("default", loanSummary);
						}
					}
					//国结业务会发生垫款的业务：信用证来单付汇---020010 保函/备用信用证付汇---020029 提货担保赔付---020050
					//垫款处理---修改状态和扣减额度---发生垫款
					//if("1".equals(dkOrNot)){//要垫款
						//jjye = loanSummary.getBigDecimal("jjye");// 借据余额
						//HashMap map1 = new HashMap();
						//map.put("jjye", jjye);
						//map.put("PymtAmt", dkje);
						//String gs1 = "";
						//gs1 = "jjye+PymtAmt";
						//jjye = MathHelper.expressionBigDecimal(gs, map1);
						//loanSummary.set("jjye", jjye);// 借据余额
						//loanSummary.set("summaryStatusCd", "03");//垫款/逾期
					//}
					//3更新借据表
					DealAccount.singleSynch(summaryNum);
					
					// 调用额度重算
					String partyId = loanSummary.getString("partyId");
					Map map1 = new HashMap();
					map1.put("partyId", partyId);
					DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map1);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	

	// 放/还款结果查询
	@Bizlet("")
	public G005Response executeG005(G005Request g005Request) {
		System.out.println("国结调用INTERFACE---G005。。。");
		G005Response rs = new G005Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g005Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g005Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g005Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g005Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g005Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g005Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g005Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g005Request.getRequestHeader().getReqTime());
		responseHeader.setReqSeqNo(g005Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g005Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g005Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G005ResponseBody responseBody = new G005ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			if ("01501".equals(g005Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G005
				if ("G005".equals(g005Request.getReqTranHeader().getHTxnCd())) {
					//本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g005Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g005Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g005Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g005Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g005Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g005Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					
					// 参数校验---国结流水
					if (null == g005Request.getRequestBody().getQANTLS() || "".equals(g005Request.getRequestBody().getQANTLS())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败:参数{国结流水号}不能为空");
						return rs;
					}
					
					// 参数校验---发生类型
					if (null == g005Request.getRequestBody().getHappenType() || "".equals(g005Request.getRequestBody().getHappenType())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetMsg("交易失败:参数{发生类型}不能为空");
						return rs;
					}
					// 参数校验---借据号
					if (null == g005Request.getRequestBody().getDueNum() || "".equals(g005Request.getRequestBody().getDueNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetMsg("交易失败:参数{借据编号}不能为空");
						return rs;
					}
					String summaryNum = g005Request.getRequestBody().getDueNum();// 借据编号
					String happenType = g005Request.getRequestBody().getHappenType();// 发生类型
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (null == loanSummary.getString("loanId") || "".equals(loanSummary.getString("loanId"))) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的借据信息]");
						return rs;
					}
					// 查询出账信息
					DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
					loanInfo.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanInfo, loanInfo);
					if (null == loanSummary.getString("loanId") || "".equals(loanSummary.getString("loanId"))) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号对应的出账信息]");
						return rs;
					}
					// 调用核心接口---查询交易是否成功
					CrmsMgrCallCoreProxy crmsMgrCallCoreProxy = new CrmsMgrCallCoreImpl();
					OXD041_SuccessFlagChkReq request = new OXD041_SuccessFlagChkReq();
					request.setTransDate("");// 交易日期---非必输项
					request.setTransCode("");// 交易码---非必输项
					request.setSaleBrch(loanInfo.getString("loanOrg"));//
					request.setTransOper("");// 交易柜员---非必输项
					request.setOperSeq("");// 柜员流水号---非必输项
					request.setIdentifier("");// 标识符---非必输项
					request.setCorrectFlag("");// 被冲正标志---非必输项
					request.setHostDate("");// 主机日期---非必输项
					request.setSearchPrintFlag(g005Request.getRequestBody().getCXDYBZ());// 查询打印标志
					request.setFrontDate(g005Request.getRequestBody().getQANTRQ());// 前台日期
					request.setForegroundSeq(g005Request.getRequestBody().getQANTLS());// 前台流水号
					request.setBegNum(g005Request.getRequestBody().getQISHBS());// 起始笔数
					request.setSearchNum(g005Request.getRequestBody().getCXUNBS());// 查询笔数
					// 调用核心查询
					OXD042_SuccessFlagChkRes response = crmsMgrCallCoreProxy.executeXD04(request);
					if(response.getResTranHeader().getHRetCode().equals("00301BMI0039")){//核心这个错误码 表示：没有查询到相关记录 可以再次放款/还款
						rs.getResponseBody().setSTATUS("02");// 核心未对该笔交易记账---可以再次发起交易
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetCode("AAAAAAA");//这种情况 交易本身是成功的
						rs.getResTranHeader().setHRetMsg("交易成功");
						return rs;
					}else if(!response.getResTranHeader().getHRetCode().equals("AAAAAAA")){//核心返回失败---信贷调用核心失败 通讯异常或者其他异常
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("信贷调用核心出现异常，交易失败:"+response.getResTranHeader().getHRetMsg());
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						return rs;
					}
					//核心返回成功---信贷调用核心成功
					OXD042ResBody oxd042ResBody = response.getOxd042ResBody();
					if (null == oxd042ResBody) {// 没有返回记录
						rs.getResponseBody().setSTATUS("02");// 核心未对该笔交易记账---可以再次发起交易
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetCode("AAAAAAA");
						rs.getResTranHeader().setHRetMsg("交易成功");
						return rs;
					}
					List<F76091> list = response.getOxd042ResBody().getF76091();
					if (null == list || list.isEmpty()) {// 没有返回记录
						rs.getResponseBody().setSTATUS("02");// 核心未对该笔交易记账---可以再次发起交易
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetCode("AAAAAAA");
						rs.getResTranHeader().setHRetMsg("交易成功");
						return rs;
					}
					F76091 f76091 = list.get(0);
					if ("1".equals(f76091.getCorrectFlag())) {// 已冲正
						rs.getResponseBody().setSTATUS("02");// 核心未对该笔交易记账---可以再次发起交易
						rs.getResponseBody().setGUIYLS(g005Request.getRequestBody().getQANTLS());// 前台流水
						rs.getResTranHeader().setHRetCode("AAAAAAA");
						rs.getResTranHeader().setHRetMsg("交易成功");
						return rs;
						
					}
					rs.getResponseBody().setBCHZBZ(f76091.getCorrectFlag());// 被冲正标志
					rs.getResponseBody().setDLIYWH(f76091.getAgntServNum());// 代理业务号
					rs.getResponseBody().setFHGYLS(f76091.getApplyRechkSeq());// 申请复核流水号
					rs.getResponseBody().setFUHERQ(f76091.getRechkDate());// 复核日期
					rs.getResponseBody().setGUIYLS(f76091.getOperSeq());// 柜员流水号
					rs.getResponseBody().setJIAOYM(f76091.getTransCode());// 交易码
					rs.getResponseBody().setJIO1GY(f76091.getTransOper());// 交易柜员
					rs.getResponseBody().setJIOYMC(f76091.getTransName());// 交易名称
					rs.getResponseBody().setJIOYSJ(f76091.getTransTime());// 交易时间
					rs.getResponseBody().setJIOYRQ(f76091.getTransDate());// 交易日期
					rs.getResponseBody().setJYDZLX(f76091.getTransReconType());// 交易对帐类型
					rs.getResponseBody().setJYLSLX(f76091.getTransSeqType());// 交易流水类型
					rs.getResponseBody().setYNGYJG(f76091.getSaleBrch());// 营业机构
					rs.getResponseBody().setYNGYLS(f76091.getOrigTellerSeq());// 原柜员流水号
					rs.getResponseBody().setQUDAOO(f76091.getChnl());// 渠道
					rs.getResponseBody().setSHOQGY(f76091.getAuthCter());// 授权柜员
					rs.getResponseBody().setQANTRQ(f76091.getFrontDate());// 前台日期
					rs.getResponseBody().setQTAILS(f76091.getFrontSeqNo());// 前台流水
					rs.getResponseBody().setSHJIAN(f76091.getTime());// 时间
					rs.getResponseBody().setPOSZBH(f76091.getPosCode());// POS终端编号
					rs.getResponseBody().setZHNGDH(f76091.getTermNo());// 终端号
					rs.getResponseBody().setZHNGJG(f76091.getAffairsBrchNo());// 帐务机构号
					rs.getResponseBody().setZHUJRQ(f76091.getHostDate());// 主机日期
					rs.getResponseBody().setSTATUS("01");// 核心已对该笔交易记账---不可以再次发起交易
					
					// 信贷相关记录更新---包括日志记录 流水更新 以及借据更新和额度重算
					DataObject pubTransWater = DataObjectUtil.createDataObject("com.bos.pub.sys.TbPubTransWater");
					pubTransWater.set("waterNum", g005Request.getRequestBody().getQANTLS());
					DatabaseUtil.expandEntity("default", pubTransWater);
					String status = pubTransWater.getString("status");// 流水状态
					BigDecimal happenAmount = pubTransWater.getBigDecimal("debtAmt");// 流水金额
					if ("0".equals(status)) {// 未处理
						BigDecimal jjye = loanSummary.getBigDecimal("jjye");// 借据余额
						HashMap map = new HashMap();
						map.put("jjye", jjye);
						map.put("PymtAmt", happenAmount);
						String gs = "";
						if ("01".equals(happenType)) {// 放款更新借据
							// 更新借据表
							DealAccount.singleSynch(summaryNum);
							//gs = "jjye-PymtAmt";
							//jjye = MathHelper.expressionBigDecimal(gs, map);
							//loanSummary.set("jjye", jjye);// 借据余额
							//loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
							//loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							//DatabaseUtil.updateEntity("default", loanSummary);

						}
						if ("02".equals(happenType)) {// 还款更新借据
							// 更新借据表
							DealAccount.singleSynch(summaryNum);
							//gs = "jjye+PymtAmt";
							//jjye = MathHelper.expressionBigDecimal(gs, map);
							//loanSummary.set("jjye", jjye);// 借据余额
							//loanSummary.set("summaryStatusCd", "02");// 借据借据状态：正常
							//loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
							//DatabaseUtil.updateEntity("default", loanSummary);
						}
						// 调用额度重算
						String partyId = loanSummary.getString("partyId");
						Map map1 = new HashMap();
						map1.put("partyId", partyId);
						DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map1);
						//同步借据
						DealAccount.singleSynch(summaryNum);
						// 更新本地流水记录
						pubTransWater.set("summaryNum", summaryNum);
						pubTransWater.set("status", "1");
						pubTransWater.set("waterTime", GitUtil.getBusiDate());
						DatabaseUtil.updateEntity("crms", pubTransWater);
					}
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
					
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			e.printStackTrace();
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}

	// 汇率推送
	@Bizlet("")
	public G006Response executeG006(G006Request g006Request) {
		System.out.println("国结调用INTERFACE---G006。。。");
		G006Response rs = new G006Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(g006Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(g006Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(g006Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(g006Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(g006Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(g006Request.getRequestHeader().getTxCode());
		responseHeader.setReqDate(g006Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(g006Request.getRequestHeader().getReqTime());
		responseHeader.setReqSeqNo(g006Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(g006Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(g006Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		G006ResponseBody responseBody = new G006ResponseBody();
		rs.setResponseBody(responseBody);
		
		try {
			if ("01501".equals(g006Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---G006
				if ("G006".equals(g006Request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", g006Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", g006Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", g006Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", g006Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", g006Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", g006Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					
					// 参数校验---汇率信息
					if (null == g006Request.getRequestBody().getG006RequestBodyStubList() || g006Request.getRequestBody().getG006RequestBodyStubList().isEmpty()) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[汇率信息不能为空]");
						return rs;
					}
					List<G006RequestBodyStub> g006RequestBodyStubList = g006Request.getRequestBody().getG006RequestBodyStubList();// 汇率信息
					DataObject[] list = new DataObject[g006RequestBodyStubList.size()];
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					for (int i = 0; i < g006RequestBodyStubList.size(); i++) {
						G006RequestBodyStub g006RequestBodyStub = g006RequestBodyStubList.get(i);
						list[i] = DataObjectUtil.createDataObject("com.bos.dataset.sys.TbSysExchangeRate");
						list[i].set("exchangeRateId", g006RequestBodyStub.getCurrencyCd());
						list[i].set("currencyCd", g006RequestBodyStub.getCurrencyCd());
						list[i].set("discountRateOfRmb", g006RequestBodyStub.getDisRateOfRmb());
						//list[i].set("actualExchangeRate", g006RequestBodyStub.getActualExchangeRate());//真实汇率---国结那边没有这个数据
						list[i].set("discountDate",sdf.parse(g006RequestBodyStub.getDiscountDate()));
						list[i].set("discountUnit", g006RequestBodyStub.getDiscountUnit());
						list[i].set("validityInd", g006RequestBodyStub.getValidityInd());
						list[i].set("unitCurrencyCd", g006RequestBodyStub.getUnitCurrencyCd());
						list[i].set("valuationCurrencyCd", g006RequestBodyStub.getValuationCurrencyCd());
						list[i].set("buyingPrice", g006RequestBodyStub.getBuyingPrice());
						list[i].set("sellingPrice", g006RequestBodyStub.getSellingPrice());
					}
					//DatabaseUtil.updateEntityBatch("default", list);
					DatabaseUtil.saveEntities("default", list);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非国结系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResTranHeader().setHRetMsg("交易失败["+e.getMessage()+"]");
			log.info("GjtoLoanServiceImpl--" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
}