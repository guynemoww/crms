package com.bos.inter;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class LcsAplusDelete {
	public static TraceLogger logger = new TraceLogger(LoanToLcs.class);
	String SDP_DS_NAME = "aplus";
	//删除核算临时中间表
	public void deleteLoanData(String loanId){
		try {
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			
			//com.primeton.tsl.TransferDataManager.dataInsertCheck
			String telNo = loanInfo.getString("loanNum");
			String dueNum = loanInfo.getString("summaryNum");
			
			//先删除记录
			logger.info("---------删除中间表数据--------start------");
			logger.info("---------删除主协议表数据--------------");
			DataObject tcsuploaninfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
			tcsuploaninfo.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfo);
			}
			
			logger.info("---------删除账户信息表数据--------------");
			DataObject tcsuploaninfoacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
			tcsuploaninfoacct.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfoacct);
			}
			
			logger.info("---------删除还款协议表数据--------------");
			DataObject tcsuploaninfocalpayplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
			tcsuploaninfocalpayplan.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfocalpayplan);
			}
			
			logger.info("---------删除贴息数据--------------");
			DataObject tcsuploaninfodiscinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscInfo");
			tcsuploaninfodiscinfo.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfodiscinfo);
			}
			
			logger.info("---------删除贴现数据--------------");
			DataObject tcsuploaninfodiscnote = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscNote");
			tcsuploaninfodiscnote.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfodiscnote);
			}
			
			logger.info("---------删除委托贷款数据--------------");
			DataObject tcsuploaninfoentrinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoEntrInfo");
			tcsuploaninfoentrinfo.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfoentrinfo);
			}
			
			logger.info("---------删除还息计划数据--------------");
			DataObject tcsuploaninfopayitrplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPayItrPlan");
			tcsuploaninfopayitrplan.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfopayitrplan);
			}
			
			logger.info("---------删除组合还款协议数据--------------");
			DataObject tcsuploaninfoprtpay = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPrtPay");
			tcsuploaninfoprtpay.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuploaninfoprtpay);
			}
			
			logger.info("---------删除还本计划协议数据--------------");
			DataObject tcsupprinplann = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupPrinPlanN");
			tcsupprinplann.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsupprinplann);
			}
			
			logger.info("---------删除受托支付账户信息--------------");
			DataObject tcsuptrustpayacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupTrustPayAcct");
			tcsuptrustpayacct.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate(SDP_DS_NAME, tcsuptrustpayacct); 
			}

			logger.info("---------删除中间表数据--------end------");
		}catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
}
