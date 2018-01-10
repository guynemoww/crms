package com.bos.payback;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.plus.LoanCancelRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess{
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		
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
		//获取贷款id
		String loanId=(String)list.get(0);
		if(null==loanId||"".equals(loanId)){
			logger.info("流程返回的申请ID为空！");
			throw new EOSException("流程返回的申请ID为空");
		}
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String summaryNum = loanInfo.getString("summaryNum");//借据编号
		String telNo = loanInfo.getString("loanNum");//通知书编号
		String loanorg = loanInfo.getString("loanOrg");//出账机构
		Map<String,String> map = new HashMap<String,String>();
		map.put("summaryNum", summaryNum);
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.querySummaryInfo", map);
		Map maps = (Map) summaryInfo[0];
		String rcnStan = (String) maps.get("RCN_STAN");
		Date date = (Date) maps.get("BEGIN_DATE");
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		String beginDate = formater.format(date);
		String summaryId = (String) maps.get("SUMMARY_ID");
		Map mapn = new HashMap();
		mapn.put("summaryNum", summaryNum);
		Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryLoanOpr", mapn);
		Map mapo = (Map) orgArea[0];
		String opr = (String) mapo.get("USERNUM");//操作员和出账保持一致
		//调用撤销接口
		LoanCancelRq rq = new LoanCancelRq();
		rq.setDueNum(summaryNum);
		rq.setTelNo(telNo);
		rq.setRevStan(Long.valueOf(rcnStan));
		BaseVO bvo = new BaseVO();
		bvo.setTranCod("T1110");
		bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setOpnDep(loanorg);
		bvo.setTrnDep(loanorg);
		bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		bvo.setOpr(opr);
		bvo.setOrigFrom("11000");
		bvo.setLegPerCod("9999");
		bvo.setTranFrom("47");
		bvo.setOrigStan(Long.valueOf(rcnStan));
		rq.setBaseVO(bvo);
		Object[] params1 = new Object[2];
		params1[0]=rq;
		params1[1]=beginDate;
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pay.LoanSummary");
		Object[] objs;
		try {
			objs = logicComponent.invoke("allBussBackout", params1);
			DataObject vo1 = (DataObject)objs[0];
			BaseVO baseVO = (BaseVO)vo1.get("baseVO");
			String returnCode = (String)baseVO.getErrCod();
			if(!"00000".equals(returnCode)){
				throw new EOSException(baseVO.getErrMsg());
			}
			DataObject loanSummary = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanSummary.set("summaryId", summaryId);
			loanSummary.set("summaryStatusCd", "09");
			loanSummary.set("backCd", "09");
			DatabaseUtil.updateEntity("default", loanSummary);
			loanInfo.set("loanStatus", "04");
			DatabaseUtil.updateEntity("default", loanInfo);
			Map map2 = new HashMap();
			map2.put("partyId", loanInfo.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			if("01013".equals(loanInfo.getString("productType").substring(0, 5)) || "02005".equals(loanInfo.getString("productType").substring(0, 5))){
				//委托贷款撤销借贷标志相反
				Map map1 = new HashMap();
				map1.put("loanId", loanInfo.get("loanId"));
				Object[] entAccs = (Object[]) DatabaseExt.queryByNamedSql(
						"default", "com.bos.payInfo.queryForHx.queryEntAcc", map1);
				DataObject entacc = (DataObject) entAccs[0];
				String currencyCd = loanInfo.getString("currencyCd");//货币
				CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
				OXD011_AccoutingReq req = new OXD011_AccoutingReq();
				req.setHxorg(loanInfo.getString("loanOrg"));
				req.setAmount(loanInfo.getString("loanAmt"));
				req.setRecNum(new BigInteger("2"));
				FXD011[] msg = new FXD011[2];
				FXD011 msgenty = new FXD011();
				FXD011 msgenty1 = new FXD011();
				req.setChargeSeq(String.valueOf(BizNumGenerator.getLcsStan()));
				req.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
				req.setRecNum(new BigInteger("2"));
				req.setSummaryCode("B00150");//摘要代码
				req.setSummaryDescription("委托贷款撤销");//摘要描述
				msgenty.setDealType("0");
				msgenty.setDrCrFlag("1");//借贷标志--0-借/收  1-贷/付
				msgenty.setAcct(entacc.getString("CZH"));
				if("CNY".equals(currencyCd)){
					msgenty.setCurrCode("01");
					msgenty.setCashFlag("0");
				}else{
					if(currencyCd.equals("HKD")){//港币
						msgenty.setCurrCode("13");
					}else if(currencyCd.equals("JPY")){//日元
						msgenty.setCurrCode("27");
					}else if(currencyCd.equals("MOP")){//澳门元
						msgenty.setCurrCode("81");
					}else if(currencyCd.equals("AUD")){//澳洲元
						msgenty.setCurrCode("29");
					}else if(currencyCd.equals("SGD")){//新加坡元
						msgenty.setCurrCode("32");
					}else if(currencyCd.equals("CHF")){//瑞士法郎
						msgenty.setCurrCode("15");
					}else if(currencyCd.equals("GBP")){//英镑
						msgenty.setCurrCode("12");
					}else if(currencyCd.equals("USD")){//美元
						msgenty.setCurrCode("14");
					}else if(currencyCd.equals("EUR")){//欧元
						msgenty.setCurrCode("38");
					}else if(currencyCd.equals("CAD")){//加拿大元
						msgenty.setCurrCode("28");
					}else{
						throw new EOSException("不支持的币种");
					}
					msgenty.setCashFlag("1");
				}
				msgenty.setTransAmt(loanInfo.getString("loanAmt"));
				msgenty.setAcctFromGo("0");
				msgenty.setPwdKind("00");
				msgenty.setSignPassFlag("1");
				msgenty.setVertLastboxSignFlag("0");
				msgenty.setFeePayType("0");
				msg[0]=msgenty;
				msgenty1.setDealType("0");
				msgenty1.setDrCrFlag("0");//借贷标志--0-借/收  1-贷/付
				msgenty1.setAcct(entacc.getString("RZH"));
				if("CNY".equals(currencyCd)){
					msgenty1.setCurrCode("01");
					msgenty1.setCashFlag("0");
				}else{
					if(currencyCd.equals("HKD")){//港币
						msgenty1.setCurrCode("13");
					}else if(currencyCd.equals("JPY")){//日元
						msgenty1.setCurrCode("27");
					}else if(currencyCd.equals("MOP")){//澳门元
						msgenty1.setCurrCode("81");
					}else if(currencyCd.equals("AUD")){//澳洲元
						msgenty1.setCurrCode("29");
					}else if(currencyCd.equals("SGD")){//新加坡元
						msgenty1.setCurrCode("32");
					}else if(currencyCd.equals("CHF")){//瑞士法郎
						msgenty1.setCurrCode("15");
					}else if(currencyCd.equals("GBP")){//英镑
						msgenty1.setCurrCode("12");
					}else if(currencyCd.equals("USD")){//美元
						msgenty1.setCurrCode("14");
					}else if(currencyCd.equals("EUR")){//欧元
						msgenty1.setCurrCode("38");
					}else if(currencyCd.equals("CAD")){//加拿大元
						msgenty1.setCurrCode("28");
					}else{
						throw new EOSException("不支持的币种");
					}
					msgenty1.setCashFlag("1");
				}
				msgenty1.setTransAmt(loanInfo.getString("loanAmt"));
				msgenty1.setAcctFromGo("0");
				msgenty1.setPwdKind("00");
				msgenty1.setSignPassFlag("1");
				msgenty1.setVertLastboxSignFlag("0");
				msgenty1.setFeePayType("0");
				msg[1]=msgenty1;
				req.setFxd011(msg);
				OXD012_AccoutingRes rs = proxy.executeXD01(req);
				if(!"AAAAAAA".equals(rs.getResTranHeader().getHRetCode())){
					throw new EOSException(rs.getResTranHeader().getHRetMsg());
				}
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		
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
		//获取贷款id
		String loanId=(String)list.get(0);
		if(null==loanId||"".equals(loanId)){
			logger.info("流程返回的申请ID为空！");
			throw new EOSException("流程返回的申请ID为空");
		}
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String summaryNum = loanInfo.getString("summaryNum");//借据编号
		Map<String,String> map = new HashMap<String,String>();
		map.put("summaryNum", summaryNum);
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.querySummaryInfo", map);
		Map maps = (Map) summaryInfo[0];
		String summaryId = (String) maps.get("SUMMARY_ID");
		DataObject loanSummary = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		loanSummary.set("summaryId", summaryId);
		loanSummary.set("summaryStatusCd", "02");
		loanSummary.set("backCd", "02");
		DatabaseUtil.updateEntity("default", loanSummary);
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
