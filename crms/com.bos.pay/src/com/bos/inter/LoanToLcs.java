package com.bos.inter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.biz.MathHelper;
import com.bos.gjService.G002RequestBody;
import com.bos.mq.util.DateHelper;
import com.bos.payInfo.LoanSubject;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.DictContents;
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
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plus.PayOutCheckRq;
import com.primeton.plus.PayOutRq;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepayOldNewRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.PayConInfo;
import com.primeton.tsl.TransferDataUtil;

import commonj.sdo.DataObject;

public class LoanToLcs {
	public static TraceLogger logger = new TraceLogger(LoanToLcs.class);
	//管理向计量中间表插入数据，并调用数据检查
	public void dataInsertCheck(String loanId){
		//删除核算中间表历史数据
				LcsAplusDelete ld = new LcsAplusDelete();
				ld.deleteLoanData(loanId);
				//删除信贷中间表历史数据
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
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfo);
			}
			
			logger.info("---------删除账户信息表数据--------------");
			DataObject tcsuploaninfoacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
			tcsuploaninfoacct.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoacct);
			}
			
			logger.info("---------删除还款协议表数据--------------");
			DataObject tcsuploaninfocalpayplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
			tcsuploaninfocalpayplan.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfocalpayplan);
			}
			
			logger.info("---------删除贴息数据--------------");
			DataObject tcsuploaninfodiscinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscInfo");
			tcsuploaninfodiscinfo.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfodiscinfo);
			}
			
			logger.info("---------删除贴现数据--------------");
			DataObject tcsuploaninfodiscnote = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscNote");
			tcsuploaninfodiscnote.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfodiscnote);
			}
			
			logger.info("---------删除委托贷款数据--------------");
			DataObject tcsuploaninfoentrinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoEntrInfo");
			tcsuploaninfoentrinfo.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoentrinfo);
			}
			
			logger.info("---------删除还息计划数据--------------");
			DataObject tcsuploaninfopayitrplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPayItrPlan");
			tcsuploaninfopayitrplan.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfopayitrplan);
			}
			
			logger.info("---------删除组合还款协议数据--------------");
			DataObject tcsuploaninfoprtpay = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPrtPay");
			tcsuploaninfoprtpay.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoprtpay);
			}
			
			logger.info("---------删除还本计划协议数据--------------");
			DataObject tcsupprinplann = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupPrinPlanN");
			tcsupprinplann.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsupprinplann);
			}
			
			logger.info("---------删除受托支付账户信息--------------");
			DataObject tcsuptrustpayacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupTrustPayAcct");
			tcsuptrustpayacct.set("dueNum", dueNum);
			if(null == dueNum ||"".equals(dueNum)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuptrustpayacct);
			}

			logger.info("---------删除中间表数据--------end------");
			
			String retString = saveLcsInfo(loanId);
			if("2".equals(retString)){
				throw new EOSException("管理数据库插入中间表失败！");
			}
			
			
			//处理转化时空属性转化为0的属性
			DataObject tcSupLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
			tcSupLoanInfo.set("dueNum", dueNum);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfo, tcSupLoanInfo);
			//贴现数据没有宽限期/节假日
			if(null==tcSupLoanInfo.get("gracePrdTyp")){
				tcSupLoanInfo.set("gracePrdTyp", "0");
			}
			if(null==tcSupLoanInfo.get("holidayFlg")){
				tcSupLoanInfo.set("holidayFlg", "0");
			}
			//贴现没有罚息，把垫款利率放到罚息/复利里---垫款出账用
			if("01006001".equals(loanInfo.get("productType"))||"01006002".equals(loanInfo.get("productType"))
					||"01006010".equals(loanInfo.get("productType")) //村镇银行贴现产品
					){
				String contractId = loanInfo.getString("contractId");
				DataObject hptxcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConHptx");
				hptxcon.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", hptxcon, hptxcon);
				if(hptxcon.get("dkll")==null){
					throw new EOSException("无垫款利率！");
				}else{
					BigDecimal dkll =  (BigDecimal)hptxcon.get("dkll");
					dkll = dkll.multiply(new BigDecimal("3.6"));
					tcSupLoanInfo.set("delItrRate", dkll);
					tcSupLoanInfo.set("cpdItrRate", dkll);
					tcSupLoanInfo.set("begDate", tcSupLoanInfo.get("endDate"));//贷款起止期等于放款止期
				}
				//银承借据号在loanSummary里和在loanInfo里不一样
				
			}
			//承兑汇票/保函，把垫款利率放在正常利率、罚息/复利里---垫款出账用
			if("01008001".equals(loanInfo.get("productType"))||"01008002".equals(loanInfo.get("productType"))||
			   "01009001".equals(loanInfo.get("productType"))||"01009002".equals(loanInfo.get("productType"))||"01009010".equals(loanInfo.get("productType"))){
				String contractId = loanInfo.getString("contractId");
				DataObject cdhpcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConKlyhcdhp");
				cdhpcon.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", cdhpcon, cdhpcon);
				if(cdhpcon.get("dkll")==null){
					throw new EOSException("无垫款利率！");
				}else{
					BigDecimal dkll =  (BigDecimal)cdhpcon.get("dkll");
					dkll = dkll.multiply(new BigDecimal("3.6"));
					tcSupLoanInfo.set("delItrRate", dkll);
					tcSupLoanInfo.set("cpdItrRate", dkll);
					tcSupLoanInfo.set("norItrRate", dkll);//银承没有正常利率
					tcSupLoanInfo.set("begDate", tcSupLoanInfo.get("endDate"));//贷款起止期等于放款止期
				}
			}
			
			
//			if(tcSupLoanInfo.get("graPerdFlg").equals("00")){
//				tcSupLoanInfo.set("graPrdTyp","1");
//				tcSupLoanInfo.set("graPrnDays", "0");
//			}
//			if(tcSupLoanInfo.get("graPrdTyp").equals("0")){
//				tcSupLoanInfo.set("graPrdItrWay", null);
//			}
//			if(tcSupLoanInfo.get("hldFlg").equals("0")){
//				tcSupLoanInfo.set("hldItrWay", null);
//			}

			//币种转换
			if(tcSupLoanInfo.get("currCod").equals("CNY")){
				tcSupLoanInfo.set("currCod", "01");
			}else if(tcSupLoanInfo.get("currCod").equals("GBP")){//英镑
				tcSupLoanInfo.set("currCod", "12");
			}else if(tcSupLoanInfo.get("currCod").equals("HKD")){//港币
				tcSupLoanInfo.set("currCod", "13");
			}else if(tcSupLoanInfo.get("currCod").equals("USD")){//美元
				tcSupLoanInfo.set("currCod", "14");
			}else if(tcSupLoanInfo.get("currCod").equals("CHF")){//瑞士法郎
				tcSupLoanInfo.set("currCod", "15");
			}else if(tcSupLoanInfo.get("currCod").equals("JPY")){//日元
				tcSupLoanInfo.set("currCod", "27");
			}else if(tcSupLoanInfo.get("currCod").equals("CAD")){//加拿大元
				tcSupLoanInfo.set("currCod", "28");
			}else if(tcSupLoanInfo.get("currCod").equals("AUD")){//澳洲元
				tcSupLoanInfo.set("currCod", "29");
			}else if(tcSupLoanInfo.get("currCod").equals("SGD")){//新加坡元
				tcSupLoanInfo.set("currCod", "32");
			}else if(tcSupLoanInfo.get("currCod").equals("EUR")){//欧元
				tcSupLoanInfo.set("currCod", "38");
			}else if(tcSupLoanInfo.get("currCod").equals("MOP")){//澳门元
				tcSupLoanInfo.set("currCod", "81");
			}else if(tcSupLoanInfo.get("currCod").equals("FRF")){//法国法郎
				tcSupLoanInfo.set("currCod", "250");
			}else if(tcSupLoanInfo.get("currCod").equals("DEM")){//德国马克
				tcSupLoanInfo.set("currCod", "276");
			}else if(tcSupLoanInfo.get("currCod").equals("ITL")){//意大利里拉
				tcSupLoanInfo.set("currCod", "380");
			}else if(tcSupLoanInfo.get("currCod").equals("KRW")){//韩国元
				tcSupLoanInfo.set("currCod", "410");
			}else if(tcSupLoanInfo.get("currCod").equals("MYR")){//马来西亚币
				tcSupLoanInfo.set("currCod", "458");
			}else if(tcSupLoanInfo.get("currCod").equals("NLG")){//荷兰盾
				tcSupLoanInfo.set("currCod", "528");
			}else if(tcSupLoanInfo.get("currCod").equals("NZD")){//新西兰元 
				tcSupLoanInfo.set("currCod", "554");
			}else if(tcSupLoanInfo.get("currCod").equals("NOK")){//挪威克朗
				tcSupLoanInfo.set("currCod", "578");
			}else if(tcSupLoanInfo.get("currCod").equals("PHP")){//菲律宾比索
				tcSupLoanInfo.set("currCod", "608");
			}else if(tcSupLoanInfo.get("currCod").equals("RUB")){//卢布
				tcSupLoanInfo.set("currCod", "643");
			}else if(tcSupLoanInfo.get("currCod").equals("ESP")){//西班牙比塞塔
				tcSupLoanInfo.set("currCod", "724");
			}else if(tcSupLoanInfo.get("currCod").equals("SEK")){//瑞典克朗
				tcSupLoanInfo.set("currCod", "752");
			}else if(tcSupLoanInfo.get("currCod").equals("THB")){//泰国铢
				tcSupLoanInfo.set("currCod", "764");
			}else if(tcSupLoanInfo.get("currCod").equals("ATS")){//奥地利先令
				tcSupLoanInfo.set("currCod", "040");
			}else if(tcSupLoanInfo.get("currCod").equals("OTHER")){//其他
				tcSupLoanInfo.set("currCod", "999");
			}else if(tcSupLoanInfo.get("currCod").equals("BEF")){//比利时法郎
				tcSupLoanInfo.set("currCod", "056");
			}else if(tcSupLoanInfo.get("currCod").equals("TWD")){//新台湾币
				tcSupLoanInfo.set("currCod", "158");
			}else if(tcSupLoanInfo.get("currCod").equals("DKK")){//丹麦克朗
				tcSupLoanInfo.set("currCod", "208");
			}else if(tcSupLoanInfo.get("currCod").equals("FIM")){//芬兰马克
				tcSupLoanInfo.set("currCod", "246");
			}else{
				throw new EOSException("不支持的币种");
			}
				
			DatabaseUtil.saveEntity("default", tcSupLoanInfo);
			
			
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] params = new Object[2];
			params[0] = telNo;
			params[1] = dueNum;
			Object[] objs = logicComponent.invoke("dataInsertCheck", params);
			boolean retbol = (Boolean)objs[0];
			if(!retbol){
				throw new EOSException("管理向计量插入中间表数据失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	* @Title: dataInsertDkMccb 
	* @Description: 出账时插入核算中间表
	* @param loanId 放款id
	* @param dueNum1 借据编号
	* @param orgCode 垫款记账机构
	* @param dkAmt    垫款金额
	* @return void    返回类型 
	* @throws 
	* @author zxh
	* @date 2017年06月19日
	* @version V1.0
	 */
	public void dataInsertDkMccb(String loanId,String dueNum1,String orgCode,Map temp){
		try {
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			String advOrg = temp.get("ADVORG")+"";//垫款机构
			String dkCurrency = temp.get("CURRENCY")+"";//垫款币种
			//重新赋值业务别
			loanInfo.set("loanSubject1", DictContents.SUBJECT_CD);//承兑垫款业务别
			loanInfo.set("summaryNum", dueNum1);
			loanInfo.set("loanNum", dueNum1);
			loanInfo.set("loanOrg", advOrg);//出账机构---垫款机构
			//这个地方需要确认
			//loanInfo.set("orgNum", orgCode);//经办机构---交易机构
			DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, loanInfo);
			
			//String dueNum = loanInfo.getString("summaryNum");
			
			//贴现没有罚息，直接把正常利率置成垫款利率，罚息率置0 ---
//			if(DictContents.PRODUCT_CD_01006001.equals(loanInfo.get("productType"))
//					||DictContents.PRODUCT_CD_01006002.equals(loanInfo.get("productType"))
//					||DictContents.PRODUCT_CD_01006010.equals(loanInfo.get("productType"))//村镇银行贴现产品
//					){
//				String contractId = loanInfo.getString("contractId");
//				DataObject hptxcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConHptx");
//				hptxcon.set("contractId", contractId);
//				DatabaseUtil.expandEntityByTemplate("default", hptxcon, hptxcon);
//				BigDecimal dkll = new BigDecimal("0");
//				if(hptxcon.get("dkll")==null){
//					throw new EOSException("无垫款利率！");
//				}else{
//					dkll =  (BigDecimal)hptxcon.get("dkll");
//					//dkll = dkll.multiply(new BigDecimal("3.6"));
//				}
//				DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
//				loanRate.set("loanId", loanId);
//				DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
//				loanRate.set("yearRate", dkll);
//				loanRate.set("overdueRateUpProportion", "0");
//				//结息周期---要是没有值  需要给一个值  能通过核算校验
//				if(null==loanRate.getString("interestCollectType")||"".equals(loanRate.getString("interestCollectType"))){
//					loanRate.set("interestCollectType", "1");
//				}
//				DatabaseUtil.saveEntity("default", loanRate);
				//throw new EOSException("贴现没有垫款业务！");
				
//			}
			//承兑汇票，把垫款利率放在正常利率、罚息/复利里---垫款出账用
			if(DictContents.PRODUCT_CD_01008001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008010.equals(loanInfo.get("productType"))){
				String contractId = loanInfo.getString("contractId");
				DataObject cdhpcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConKlyhcdhp");
				cdhpcon.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", cdhpcon, cdhpcon);
				//垫款利率默认都是万分之五  当然页面也可以输入  如果是迁移数据  可能这个值没有  所以使用5
				BigDecimal dkll = new BigDecimal("5");
				//if(cdhpcon.get("dkll")==null){
					//throw new EOSException("无垫款利率！");
				//}else{
					dkll =  (BigDecimal)cdhpcon.get("dkll");
					//dkll = dkll.multiply(new BigDecimal("0.036"));//垫款利率---日利率 万分之
					dkll = dkll.multiply(new BigDecimal("3.6"));//垫款利率---日利率 万分之
				//}
				DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				loanRate.set("loanId", loanId);
				DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
				loanRate.set("yearRate", dkll);
				//loanRate.set("overdueRateUpProportion", "50");
				loanRate.set("overdueRateUpProportion", "0");
				loanRate.set("rateType", "1");//固定利率
				//结息周期---要是没有值  需要给一个值  能通过核算校验
				//if(null==loanRate.getString("interestCollectType")||"".equals(loanRate.getString("interestCollectType"))){
				//loanRate.set("interestCollectType", "9");
				//}
				DatabaseUtil.saveEntity("default", loanRate);
			}
			
			//保函---把垫款利率放在正常利率、罚息/复利里---垫款出账用
			if(DictContents.PRODUCT_CD_01009001.equals(loanInfo.get("productType"))||
			   DictContents.PRODUCT_CD_01009002.equals(loanInfo.get("productType"))||
			   DictContents.PRODUCT_CD_01009010.equals(loanInfo.get("productType"))){
				String contractId = loanInfo.getString("contractId");
				DataObject tbConBh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConBh");
				tbConBh.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", tbConBh, tbConBh);
				//垫款利率默认都是万分之五  当然页面也可以输入  如果是迁移数据  可能这个值没有  所以使用5
				BigDecimal dkll = new BigDecimal("5");
				//if(tbConBh.get("dkll")==null){
					//throw new EOSException("无垫款利率！");
				//}else{
					dkll =  (BigDecimal)tbConBh.get("dkll");
					//dkll = dkll.multiply(new BigDecimal("0.036"));//垫款利率---日利率 万分之
					dkll = dkll.multiply(new BigDecimal("3.6"));//垫款利率---日利率 万分之
				//}
				DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				loanRate.set("loanId", loanId);
				DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
				loanRate.set("yearRate", dkll);
				//loanRate.set("overdueRateUpProportion", "50");
				loanRate.set("overdueRateUpProportion", "0");
				loanRate.set("rateType", "1");//固定利率
				//结息周期---要是没有值  需要给一个值  能通过核算校验
				//if(null==loanRate.getString("interestCollectType")||"".equals(loanRate.getString("interestCollectType"))){
				//loanRate.set("interestCollectType", "9");
				//}
				DatabaseUtil.saveEntity("default", loanRate);
			}
			
			//贸易融资产品垫款---处理利率  垫款利率放在正常利率、罚息/复利里---垫款出账用  垫款利率---日利率
			/** 产品代码--进口代付 */
			if(DictContents.PRODUCT_CD_01007009.equals(loanInfo.get("productType"))||
			   /** 产品代码--国际福费廷 */		
			   DictContents.PRODUCT_CD_01007012.equals(loanInfo.get("productType"))||
			   /** 产品代码--国际保函 */
			   DictContents.PRODUCT_CD_01007014.equals(loanInfo.get("productType"))||
			   /** 产品代码--国际信用证开证 */
			   DictContents.PRODUCT_CD_01007013.equals(loanInfo.get("productType"))||
			   /** 产品代码--提货担保 */
			   DictContents.PRODUCT_CD_01007010.equals(loanInfo.get("productType"))){
			   
			   String contractId = loanInfo.getString("contractId");
			   DataObject obj=null;
			   if(DictContents.PRODUCT_CD_01007009.equals(loanInfo.get("productType"))){//进口代付
				    obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConJkdf");
			   }
			   if(DictContents.PRODUCT_CD_01007012.equals(loanInfo.get("productType"))){//国际福费廷
				    obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGjfft");
			   }
			   if(DictContents.PRODUCT_CD_01007014.equals(loanInfo.get("productType"))){//国际保函
				    obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConJkbh");
			   }
			   if(DictContents.PRODUCT_CD_01007013.equals(loanInfo.get("productType"))){//国际信用证开证
				    obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGjxyzkz");
			   }
			   if(DictContents.PRODUCT_CD_01007010.equals(loanInfo.get("productType"))){//提货担保
				    obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConThdb");
			   }
			   obj.set("contractId", contractId);
			   DatabaseUtil.expandEntityByTemplate("default", obj, obj);
			   //垫款利率默认都是万分之五  当然页面也可以输入  如果是迁移数据  可能这个值没有  所以使用5
			   BigDecimal dkll = new BigDecimal("5");
			   //if(obj.get("dkll")==null){
					//throw new EOSException("无垫款利率！");
			   //}else{
					dkll =  (BigDecimal)obj.get("dkll");
					//dkll = dkll.multiply(new BigDecimal("0.036"));//垫款利率---日利率 万分之
					dkll = dkll.multiply(new BigDecimal("3.6"));//垫款利率---日利率 万分之
			   // }
			   DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
			   loanRate.set("loanId", loanId);
			   DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
			   loanRate.set("yearRate", dkll);
			   //loanRate.set("overdueRateUpProportion", "50");
			   loanRate.set("overdueRateUpProportion", "0");
			   loanRate.set("rateType", "1");//固定利率
			   //结息周期---要是没有值  需要给一个值  能通过核算校验
//			   if(null==loanRate.getString("interestCollectType")||"".equals(loanRate.getString("interestCollectType"))){
//				   loanRate.set("interestCollectType", "1");
//			   }
			   DatabaseUtil.saveEntity("default", loanRate);
			}
			
			//删除管理中间表信息
			delMidTables(dueNum1);
			//写入核算中间表出账信息
			String retString = saveLcsInfo(loanId);
			if("2".equals(retString)){
				throw new EOSException("管理数据库插入中间表失败！");
			}
			//处理转化时空属性转化为0的属性
			DataObject tcSupLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
			tcSupLoanInfo.set("dueNum", dueNum1);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfo, tcSupLoanInfo);
			//贴现数据没有宽限期/节假日
			if(null==tcSupLoanInfo.get("gracePrdTyp")){
				tcSupLoanInfo.set("gracePrdTyp", "0");
			}
			if(null==tcSupLoanInfo.get("holidayFlg")){
				tcSupLoanInfo.set("holidayFlg", "0");
			}
			
			if(tcSupLoanInfo.get("gracePrdTyp").equals("0")||tcSupLoanInfo.get("gracePrdTyp").equals("1")){
				tcSupLoanInfo.set("gracePrdDays", null);
			}
			if(tcSupLoanInfo.get("gracePrdTyp").equals("0")){
				tcSupLoanInfo.set("gracePrdItrWay", null);
			}
			if(tcSupLoanInfo.get("holidayFlg").equals("0")){
				tcSupLoanInfo.set("holidayItrWay", null);
			}
			//币种转换
			//tcSupLoanInfo.set("currCod", retuCurrCod(tcSupLoanInfo.get("currCod")+""));
			tcSupLoanInfo.set("currCod", dkCurrency);//使用核心给的垫款币种
			
			//如果是垫款出账，起止期一样
			if(DictContents.PRODUCT_CD_01008001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007009.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007012.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007013.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007014.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007010.equals(loanInfo.get("productType"))){
				Date date = GitUtil.getBusiDate();
				Date dkdate = DateHelper.calculateDate(date, 0, 0, 1);
				String dkdatesString = DateHelper.formatDateYYYYMMDD(date);
				tcSupLoanInfo.set("begDate", dkdatesString);
				tcSupLoanInfo.set("endDate", dkdatesString);
				tcSupLoanInfo.set("begItrDate", dkdatesString);
			}
			//垫款强制还款方式为12
			tcSupLoanInfo.set("prmPayTyp", "12");
			tcSupLoanInfo.set("astCls", "100");//子类别
			//批扣标志
			tcSupLoanInfo.set("batFlg", "1");

			//垫款机构必须为垫款文件里的机构---已经更新了出账表的loan_org直接从出账表loan_org
			//tcSupLoanInfo.set("prvCod", orgCode.substring(0, 2));
			//tcSupLoanInfo.set("opnDep", advOrg);
			//tcSupLoanInfo.set("trnDep", advOrg);
			
			//垫款金额
			tcSupLoanInfo.set("amt", (BigDecimal)temp.get("ADVAMT"));
			
			tcSupLoanInfo.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			tcSupLoanInfo.set("dueNum", dueNum1);//使用新借据号
			DatabaseUtil.saveEntity("default", tcSupLoanInfo);
			
			DataObject tcSupLoanInfoAcct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
			tcSupLoanInfoAcct.set("dueNum", dueNum1);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfoAcct, tcSupLoanInfoAcct);
			//如果没有录入放还款账号，重新生成，如果录入了，将放还款账号换成结算账号。
			//首先查出录入的结算账号
			//DataObject jszh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
			//jszh.set("loanId", loanId);
			//jszh.set("zhlx", "2");
			//DatabaseUtil.expandEntityByTemplate("default", jszh, jszh);
			//if(jszh.get("id")==null){
				//throw new EOSException("未查询到结算账户---loanId="+loanId);
			//}
			tcSupLoanInfoAcct.set("legPerCod", DictContents.ORG_MCCB);//法人代码
			//tcSupLoanInfoAcct.set("prvCod", tcSupLoanInfo.get("prvCod"));
			//tcSupLoanInfoAcct.set("opnDep", tcSupLoanInfo.get("opnDep"));
			//tcSupLoanInfoAcct.set("talDep", tcSupLoanInfo.get("talDep"));
			
			tcSupLoanInfoAcct.set("primAcctTyp", "0");//0=内部帐1=储蓄账户 2=公司账户
			tcSupLoanInfoAcct.set("primAcctFlg", "1");
			tcSupLoanInfoAcct.set("primAcct", temp.get("ADVACCNUM"));
			tcSupLoanInfoAcct.set("primAcctName", temp.get("ADVACCNAME"));
			tcSupLoanInfoAcct.set("primOpenDep",  temp.get("ADVORG"));
			
			tcSupLoanInfoAcct.set("payPrimAcctTyp", temp.get("PAYACCTYPE"));//0=内部帐1=储蓄账户 2=公司账户
			tcSupLoanInfoAcct.set("payPrimAcctFlg", "1");
			tcSupLoanInfoAcct.set("payPrimAcct", temp.get("PAYACCNUM"));
			tcSupLoanInfoAcct.set("payPrimName", temp.get("PAYACCNAME"));
			tcSupLoanInfoAcct.set("payOpenDep", temp.get("PAYORG"));
			
			tcSupLoanInfoAcct.set("dueNum", dueNum1);//使用新借据号
			tcSupLoanInfoAcct.set("dueNumUn", dueNum1);//使用新借据号
			tcSupLoanInfoAcct.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			DatabaseUtil.saveEntity("default", tcSupLoanInfoAcct);
			
			DataObject tcSupLoanInfoCalPayPlan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
			tcSupLoanInfoCalPayPlan.set("dueNum", dueNum1);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfoCalPayPlan, tcSupLoanInfoCalPayPlan);
			tcSupLoanInfoCalPayPlan.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			tcSupLoanInfoCalPayPlan.set("dueNum", dueNum1);//使用新借据号
			DatabaseUtil.updateEntity(DictContents.DB_NAME_CRMS, tcSupLoanInfoCalPayPlan);
			
			
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] params = new Object[1];
			//params[0] = telNo;
			params[0] = dueNum1;
			logicComponent.invoke("dataInsertCheck", params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		
	}
	
	/**
	 * @Title: delMidTables 
	 * @Description: 删除中间表信息
	 */
	public void delMidTables(String dueNum1 ){
		//先删除记录
		logger.info("---------删除中间表数据--------start------");
		logger.info("---------删除主协议表数据--------------");
		DataObject tcsuploaninfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
		tcsuploaninfo.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfo);
		}
		
		logger.info("---------删除账户信息表数据--------------");
		DataObject tcsuploaninfoacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
		tcsuploaninfoacct.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfoacct);
		}
		
		logger.info("---------删除还款协议表数据--------------");
		DataObject tcsuploaninfocalpayplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
		tcsuploaninfocalpayplan.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfocalpayplan);
		}
		
		logger.info("---------删除贴息数据--------------");
		DataObject tcsuploaninfodiscinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscInfo");
		tcsuploaninfodiscinfo.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfodiscinfo);
		}
		
		logger.info("---------删除贴现数据--------------");
		DataObject tcsuploaninfodiscnote = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscNote");
		tcsuploaninfodiscnote.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfodiscnote);
		}
		
		logger.info("---------删除委托贷款数据--------------");
		DataObject tcsuploaninfoentrinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoEntrInfo");
		tcsuploaninfoentrinfo.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfoentrinfo);
		}
		
		logger.info("---------删除还息计划数据--------------");
		DataObject tcsuploaninfopayitrplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPayItrPlan");
		tcsuploaninfopayitrplan.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfopayitrplan);
		}
		
		logger.info("---------删除组合还款协议数据--------------");
		DataObject tcsuploaninfoprtpay = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPrtPay");
		tcsuploaninfoprtpay.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsuploaninfoprtpay);
		}
		
		logger.info("---------删除还本计划协议数据--------------");
		DataObject tcsupprinplann = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupPrinPlanN");
		tcsupprinplann.set("dueNum", dueNum1);
		if(null == dueNum1 ||"".equals(dueNum1)){
			logger.info("----------借据号为空--------------");
		}else{
			DatabaseUtil.deleteByTemplate(DictContents.DB_NAME_CRMS, tcsupprinplann);
		}
		
		logger.info("---------删除中间表数据--------end------");
		
	}
	
	/**
	 * @Title: retuCurrCod 
	 * @Description: 管理与核算币种转换
	 */
	public String retuCurrCod(String currCod ){
		String curry = "";
		
		if(null == currCod || "".equals(currCod)){
			throw new EOSException("币种不允许为空！");
		}
		
		//币种转换
		if(currCod.equals("CNY")){
			curry = "01";
		}else if(currCod.equals("FRF")){//法国法郎
			curry = "250";
		}else if(currCod.equals("DEM")){//德国马克
			curry = "276";
		}else if(currCod.equals("HKD")){//港币
			curry = "13";
		}else if(currCod.equals("ITL")){//意大利里拉
			curry = "380";
		}else if(currCod.equals("JPY")){//日元
			curry = "27";
		}else if(currCod.equals("KRW")){//韩国元
			curry = "410";
		}else if(currCod.equals("MOP")){//澳门元
			curry = "81";
		}else if(currCod.equals("MYR")){//马来西亚币
			curry = "458";
		}else if(currCod.equals("NLG")){//荷兰盾
			curry = "528";
		}else if(currCod.equals("NZD")){//新西兰元 
			curry = "554";
		}else if(currCod.equals("AUD")){//澳洲元
			curry = "29";
		}else if(currCod.equals("NOK")){//挪威克朗
			curry = "578";
		}else if(currCod.equals("PHP")){//菲律宾比索
			curry = "608";
		}else if(currCod.equals("RUB")){//卢布
			curry = "643";
		}else if(currCod.equals("SGD")){//新加坡元
			curry = "32";
		}else if(currCod.equals("ESP")){//西班牙比塞塔
			curry = "724";
		}else if(currCod.equals("SEK")){//瑞典克朗
			curry = "752";
		}else if(currCod.equals("CHF")){//瑞士法郎
			curry = "15";
		}else if(currCod.equals("THB")){//泰国铢
			curry = "764";
		}else if(currCod.equals("GBP")){//英镑
			curry = "12";
		}else if(currCod.equals("USD")){//美元
			curry = "14";
		}else if(currCod.equals("EUR")){//欧元
			curry = "38";
		}else if(currCod.equals("ATS")){//奥地利先令
			curry = "040";
		}else if(currCod.equals("OTHER")){//其他
			curry = "999";
		}else if(currCod.equals("BEF")){//比利时法郎
			curry = "056";
		}else if(currCod.equals("CAD")){//加拿大元
			curry = "28";
		}else if(currCod.equals("TWD")){//新台湾币
			curry = "158";
		}else if(currCod.equals("DKK")){//丹麦克朗
			curry = "208";
		}else if(currCod.equals("FIM")){//芬兰马克
			curry = "246";
		}else{
			throw new EOSException("不支持的币种");
		}
		
		return curry;
	}		
	/**
	 * 
	* @Title: dataInsertCheckForDk 
	* @Description: 出账插入数据
	* @param loanId
	* @param dueNum1
	* @param orgCode
	* @param dkAmt    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年10月28日 下午6:31:43 
	* @version V1.0
	 */
	public void dataInsertCheckForDk(String loanId,String dueNum1,String orgCode,Double dkAmt){
		try {
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			//重新赋值业务别
			LoanSubject lsLoanSubject = new LoanSubject();
			String loanSubject = lsLoanSubject.getLoanSubject(loanId);
			loanInfo.set("loanSubject1", loanSubject);
			DatabaseUtil.updateEntity("default", loanInfo);
			
			//com.primeton.tsl.TransferDataManager.dataInsertCheck
			String telNo = loanInfo.getString("loanNum");
			String dueNum = loanInfo.getString("summaryNum");
			
			//贴现没有罚息，直接把正常利率置成垫款利率，罚息率置0 ---垫款出账用
			if("01006001".equals(loanInfo.get("productType"))
					||"01006002".equals(loanInfo.get("productType"))
					||"01006010".equals(loanInfo.get("productType")) //村镇银行贴现产品
					){
				String contractId = loanInfo.getString("contractId");
				DataObject hptxcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConHptx");
				hptxcon.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", hptxcon, hptxcon);
				BigDecimal dkll = new BigDecimal("0");
				if(hptxcon.get("dkll")==null){
					throw new EOSException("无垫款利率！");
				}else{
					dkll =  (BigDecimal)hptxcon.get("dkll");
					dkll = dkll.multiply(new BigDecimal("3.6"));
				}
				DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				loanRate.set("loanId", loanId);
				DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
				loanRate.set("yearRate", dkll);
				loanRate.set("overdueRateUpProportion", "0");
				DatabaseUtil.saveEntity("default", loanRate);
				
			}
			//承兑汇票/保函，把垫款利率放在正常利率、罚息/复利里---垫款出账用
			if("01008001".equals(loanInfo.get("productType"))
					||"01008002".equals(loanInfo.get("productType"))
					||"01008010".equals(loanInfo.get("productType"))
					||"01009001".equals(loanInfo.get("productType"))
					||"01009010".equals(loanInfo.get("productType"))
					||"01009002".equals(loanInfo.get("productType"))){
				String contractId = loanInfo.getString("contractId");
				DataObject cdhpcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConKlyhcdhp");
				cdhpcon.set("contractId", contractId);
				DatabaseUtil.expandEntityByTemplate("default", cdhpcon, cdhpcon);
				BigDecimal dkll = new BigDecimal("0");
				if(cdhpcon.get("dkll")==null){
					throw new EOSException("无垫款利率！");
				}else{
					dkll =  (BigDecimal)cdhpcon.get("dkll");
					dkll = dkll.multiply(new BigDecimal("3.6"));
				}
				DataObject loanRate= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				loanRate.set("loanId", loanId);
				DatabaseUtil.expandEntityByTemplate("default", loanRate , loanRate);
				loanRate.set("yearRate", dkll);
				loanRate.set("overdueRateUpProportion", "0");
				DatabaseUtil.saveEntity("default", loanRate);
			}
			
			
			
			//先删除记录
			logger.info("---------删除中间表数据--------start------");
			logger.info("---------删除主协议表数据--------------");
			DataObject tcsuploaninfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
			tcsuploaninfo.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfo);
			}
			
			logger.info("---------删除账户信息表数据--------------");
			DataObject tcsuploaninfoacct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
			tcsuploaninfoacct.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoacct);
			}
			
			logger.info("---------删除还款协议表数据--------------");
			DataObject tcsuploaninfocalpayplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
			tcsuploaninfocalpayplan.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfocalpayplan);
			}
			
			logger.info("---------删除贴息数据--------------");
			DataObject tcsuploaninfodiscinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscInfo");
			tcsuploaninfodiscinfo.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfodiscinfo);
			}
			
			logger.info("---------删除贴现数据--------------");
			DataObject tcsuploaninfodiscnote = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoDiscNote");
			tcsuploaninfodiscnote.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfodiscnote);
			}
			
			logger.info("---------删除委托贷款数据--------------");
			DataObject tcsuploaninfoentrinfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoEntrInfo");
			tcsuploaninfoentrinfo.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoentrinfo);
			}
			
			logger.info("---------删除还息计划数据--------------");
			DataObject tcsuploaninfopayitrplan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPayItrPlan");
			tcsuploaninfopayitrplan.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfopayitrplan);
			}
			
			logger.info("---------删除组合还款协议数据--------------");
			DataObject tcsuploaninfoprtpay = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoPrtPay");
			tcsuploaninfoprtpay.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsuploaninfoprtpay);
			}
			
			logger.info("---------删除还本计划协议数据--------------");
			DataObject tcsupprinplann = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupPrinPlanN");
			tcsupprinplann.set("dueNum", dueNum1);
			if(null == dueNum1 ||"".equals(dueNum1)){
				logger.info("----------借据号为空--------------");
			}else{
				DatabaseUtil.deleteByTemplate("default", tcsupprinplann);
			}
			
			logger.info("---------删除中间表数据--------end------");
			
			String retString = saveLcsInfo(loanId);
			if("2".equals(retString)){
				throw new EOSException("管理数据库插入中间表失败！");
			}
			
			
			//处理转化时空属性转化为0的属性
			DataObject tcSupLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfo");
			tcSupLoanInfo.set("dueNum", dueNum);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfo, tcSupLoanInfo);
			//贴现数据没有宽限期/节假日
			if(null==tcSupLoanInfo.get("gracePrdTyp")){
				tcSupLoanInfo.set("gracePrdTyp", "0");
			}
			if(null==tcSupLoanInfo.get("holidayFlg")){
				tcSupLoanInfo.set("holidayFlg", "0");
			}
			
			if(tcSupLoanInfo.get("gracePrdTyp").equals("0")||tcSupLoanInfo.get("gracePrdTyp").equals("1")){
				tcSupLoanInfo.set("gracePrdDays", null);
			}
			if(tcSupLoanInfo.get("gracePrdTyp").equals("0")){
				tcSupLoanInfo.set("gracePrdItrWay", null);
			}
			if(tcSupLoanInfo.get("holidayFlg").equals("0")){
				tcSupLoanInfo.set("holidayItrWay", null);
			}
			
			//币种转换
			if(tcSupLoanInfo.get("currCod").equals("CNY")){
				tcSupLoanInfo.set("currCod", "01");
			}else if(tcSupLoanInfo.get("currCod").equals("FRF")){//法国法郎
				tcSupLoanInfo.set("currCod", "250");
			}else if(tcSupLoanInfo.get("currCod").equals("DEM")){//德国马克
				tcSupLoanInfo.set("currCod", "276");
			}else if(tcSupLoanInfo.get("currCod").equals("HKD")){//港币
				tcSupLoanInfo.set("currCod", "13");
			}else if(tcSupLoanInfo.get("currCod").equals("ITL")){//意大利里拉
				tcSupLoanInfo.set("currCod", "380");
			}else if(tcSupLoanInfo.get("currCod").equals("JPY")){//日元
				tcSupLoanInfo.set("currCod", "27");
			}else if(tcSupLoanInfo.get("currCod").equals("KRW")){//韩国元
				tcSupLoanInfo.set("currCod", "410");
			}else if(tcSupLoanInfo.get("currCod").equals("MOP")){//澳门元
				tcSupLoanInfo.set("currCod", "446");
			}else if(tcSupLoanInfo.get("currCod").equals("MYR")){//马来西亚币
				tcSupLoanInfo.set("currCod", "458");
			}else if(tcSupLoanInfo.get("currCod").equals("NLG")){//荷兰盾
				tcSupLoanInfo.set("currCod", "528");
			}else if(tcSupLoanInfo.get("currCod").equals("NZD")){//新西兰元 
				tcSupLoanInfo.set("currCod", "554");
			}else if(tcSupLoanInfo.get("currCod").equals("AUD")){//澳洲元
				tcSupLoanInfo.set("currCod", "16");
			}else if(tcSupLoanInfo.get("currCod").equals("NOK")){//挪威克朗
				tcSupLoanInfo.set("currCod", "578");
			}else if(tcSupLoanInfo.get("currCod").equals("PHP")){//菲律宾比索
				tcSupLoanInfo.set("currCod", "608");
			}else if(tcSupLoanInfo.get("currCod").equals("RUB")){//卢布
				tcSupLoanInfo.set("currCod", "643");
			}else if(tcSupLoanInfo.get("currCod").equals("SGD")){//新加坡元
				tcSupLoanInfo.set("currCod", "702");
			}else if(tcSupLoanInfo.get("currCod").equals("ESP")){//西班牙比塞塔
				tcSupLoanInfo.set("currCod", "724");
			}else if(tcSupLoanInfo.get("currCod").equals("SEK")){//瑞典克朗
				tcSupLoanInfo.set("currCod", "752");
			}else if(tcSupLoanInfo.get("currCod").equals("CHF")){//瑞士法郎
				tcSupLoanInfo.set("currCod", "756");
			}else if(tcSupLoanInfo.get("currCod").equals("THB")){//泰国铢
				tcSupLoanInfo.set("currCod", "764");
			}else if(tcSupLoanInfo.get("currCod").equals("GBP")){//英镑
				tcSupLoanInfo.set("currCod", "12");
			}else if(tcSupLoanInfo.get("currCod").equals("USD")){//美元
				tcSupLoanInfo.set("currCod", "14");
			}else if(tcSupLoanInfo.get("currCod").equals("EUR")){//欧元
				tcSupLoanInfo.set("currCod", "33");
			}else if(tcSupLoanInfo.get("currCod").equals("ATS")){//奥地利先令
				tcSupLoanInfo.set("currCod", "040");
			}else if(tcSupLoanInfo.get("currCod").equals("OTHER")){//其他
				tcSupLoanInfo.set("currCod", "999");
			}else if(tcSupLoanInfo.get("currCod").equals("BEF")){//比利时法郎
				tcSupLoanInfo.set("currCod", "056");
			}else if(tcSupLoanInfo.get("currCod").equals("CAD")){//加拿大元
				tcSupLoanInfo.set("currCod", "124");
			}else if(tcSupLoanInfo.get("currCod").equals("TWD")){//新台湾币
				tcSupLoanInfo.set("currCod", "158");
			}else if(tcSupLoanInfo.get("currCod").equals("DKK")){//丹麦克朗
				tcSupLoanInfo.set("currCod", "208");
			}else if(tcSupLoanInfo.get("currCod").equals("FIM")){//芬兰马克
				tcSupLoanInfo.set("currCod", "246");
			}else{
				throw new EOSException("不支持的币种");
			}
			//如果是垫款出账，起止期一样
			if("01006001".equals(loanInfo.get("productType"))
					||"01006002".equals(loanInfo.get("productType"))
					||"01008001".equals(loanInfo.get("productType"))
					||"01008010".equals(loanInfo.get("productType"))
					||"01008002".equals(loanInfo.get("productType"))
					||"01009001".equals(loanInfo.get("productType"))
					||"01009002".equals(loanInfo.get("productType"))	
					||"01009010".equals(loanInfo.get("productType"))	
					||"01006010".equals(loanInfo.get("productType"))//村镇银行贴现产品
					){
				Date date = GitUtil.getBusiDate();
				Date dkdate = DateHelper.calculateDate(date, 0, 0, 1);
				String dkdatesString = DateHelper.formatDateYYYYMMDD(date);
				tcSupLoanInfo.set("begDate", dkdatesString);
				tcSupLoanInfo.set("endDate", dkdatesString);
				tcSupLoanInfo.set("begItrDate", dkdatesString);
			}
			//垫款强制还款方式为12
			tcSupLoanInfo.set("prmPayTyp", "12");
			tcSupLoanInfo.set("astCls", "000");
			//批扣标志
			tcSupLoanInfo.set("batFlg", "1");

			//垫款机构必须为垫款文件里的机构
			tcSupLoanInfo.set("prvCod", orgCode.substring(0, 2));
			tcSupLoanInfo.set("opnDep", orgCode);
			tcSupLoanInfo.set("talDep", orgCode);
			
			//垫款金额
			tcSupLoanInfo.set("amt", new BigDecimal(dkAmt));
			
			tcSupLoanInfo.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			tcSupLoanInfo.set("dueNum", dueNum1);//使用新借据号
			DatabaseUtil.saveEntity("default", tcSupLoanInfo);
			
			DataObject tcSupLoanInfoAcct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
			tcSupLoanInfoAcct.set("dueNum", dueNum);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfoAcct, tcSupLoanInfoAcct);
			//如果没有录入放还款账号，重新生成，如果录入了，将放还款账号换成结算账号。
			//首先查出录入的结算账号
			DataObject jszh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
			jszh.set("loanId", loanId);
			jszh.set("zhlx", "2");
			DatabaseUtil.expandEntityByTemplate("default", jszh, jszh);
			if(jszh.get("id")==null){
				throw new EOSException("未查询到结算账户---loanId="+loanId);
			}
			tcSupLoanInfoAcct.set("legPerCod", "3600");
			tcSupLoanInfoAcct.set("prvCod", tcSupLoanInfo.get("prvCod"));
			tcSupLoanInfoAcct.set("opnDep", tcSupLoanInfo.get("opnDep"));
			tcSupLoanInfoAcct.set("talDep", tcSupLoanInfo.get("talDep"));
			
			tcSupLoanInfoAcct.set("primAcctTyp", jszh.get("zhbs"));
			tcSupLoanInfoAcct.set("primAcctFlg", jszh.get("kzbs"));
			tcSupLoanInfoAcct.set("primAcct", jszh.get("zh"));
			tcSupLoanInfoAcct.set("primAcctName", jszh.get("zhmc"));
			tcSupLoanInfoAcct.set("primOpenDep", jszh.get("zhkhjg"));
			
			tcSupLoanInfoAcct.set("payPrimAcctTyp", jszh.get("zhbs"));
			tcSupLoanInfoAcct.set("payPrimAcctFlg", jszh.get("kzbs"));
			tcSupLoanInfoAcct.set("payPrimAcct", jszh.get("zh"));
			tcSupLoanInfoAcct.set("payPrimName", jszh.get("zhmc"));
			tcSupLoanInfoAcct.set("payOpenDep", jszh.get("zhkhjg"));
			
			Date date = GitUtil.getBusiDate();
			tcSupLoanInfoAcct.set("createTime", date);
			tcSupLoanInfoAcct.set("updateTime", date);
			tcSupLoanInfoAcct.set("truncNo", "1");
			
			tcSupLoanInfoAcct.set("dueNum", dueNum1);//使用新借据号
			tcSupLoanInfoAcct.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			DatabaseUtil.saveEntity("default", tcSupLoanInfoAcct);
			
			DataObject tcSupLoanInfoCalPayPlan = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoCalPayPlan");
			tcSupLoanInfoCalPayPlan.set("dueNum", dueNum);
			DatabaseUtil.expandEntityByTemplate("default", tcSupLoanInfoCalPayPlan, tcSupLoanInfoCalPayPlan);
			tcSupLoanInfoCalPayPlan.set("rcvDate", tcSupLoanInfo.get("endDate"));//登记日期使用tcSupLoanInfo.get("endDate")
			tcSupLoanInfoCalPayPlan.set("dueNum", dueNum1);//使用新借据号
			tcSupLoanInfoCalPayPlan.set("prvCod", tcSupLoanInfo.get("prvCod"));
			tcSupLoanInfoCalPayPlan.set("opnDep", tcSupLoanInfo.get("opnDep"));
			tcSupLoanInfoCalPayPlan.set("talDep", tcSupLoanInfo.get("talDep"));
			DatabaseUtil.saveEntity("default", tcSupLoanInfoCalPayPlan);
			
			
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] params = new Object[1];
			//params[0] = telNo;
			params[0] = dueNum1;
			logicComponent.invoke("dataInsertCheck", params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		
	}
	
	
	/**
	 * 调用核算出账检查接口
	 * @param telNo 通知书编号
	 * @param dueNum 借据编号
	 * @param loanOrg 出账机构
	 * @throws EOSException
	 */
	public void loanToLcs1(String telNo,String dueNum,String loanOrg) throws EOSException{
		try {
			CrmsCallPlusProxy plus = new CrmsCallPlusImpl();
			PayOutCheckRq rq = new PayOutCheckRq();
			BaseVO bvo = new BaseVO();
			rq.setTelNo(telNo);
			rq.setDueNum(dueNum);
			bvo.setTranCod("T1401");
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setOpnDep(loanOrg);
			bvo.setTrnDep(loanOrg);
			bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setOpr(GitUtil.getCurrentUserId());
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod(DictContents.ORG_MCCB);
			rq.setBaseVO(bvo);
			//调用核算接口：出账检查
			PayOutCheckRq rs = plus.executeT1401(rq);
		    String returnCode = rs.getBaseVO().getErrCod();
			if(!"00000".equals(returnCode)){
				throw new EOSException(rs.getBaseVO().getErrMsg());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	
	
	//管理向剂量发送第一次放款接口
	public void loanToLcs1(String loanId) throws EOSException{
		try {
			//与计量系统交互
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			
			//com.primeton.tsl.TransferDataManager.dataInsertCheck
			String telNo = loanInfo.getString("loanNum");
			String dueNum = loanInfo.getString("summaryNum");
			String loanorg = loanInfo.getString("loanOrg");
			loanToLcs1(telNo, dueNum, loanorg);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: loanToLcs1ForDk 
	* @Description: 插入计量数据检查
	* @param loanId
	* @throws EOSException    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年9月27日 下午2:22:43 
	* @version V1.0
	 */
	public void loanToLcs1ForDk(String loanId,String dueNum) throws EOSException{
		try {
			//与计量系统交互
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			
			//com.primeton.tsl.TransferDataManager.dataInsertCheck
			String telNo = loanInfo.getString("loanNum");
			String loanorg = loanInfo.getString("loanOrg");
			Object[] params1 = new Object[2];
			params1[0] = "MA1_1106";
			
			Date dkdate = DateHelper.calculateDate(GitUtil.getBusiDate(), 0, 0, 1);
			String dkdatesString = DateHelper.formatDateYYYYMMDD(GitUtil.getBusiDate());
			
			PayConInfo vo = new PayConInfo();
			vo.getBaseVO().setTranCod("MA1_1106");
			vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());
			vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			vo.getBaseVO().setTrnDep(loanorg);
			vo.getBaseVO().setTranTimes("1");
			vo.setDueNum(dueNum);
			vo.setTelNo(telNo);
			params1[1] = vo;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = null;
			objs = logicComponent.invoke("newDataInsertCheck", params1);
			DataObject vo1 = (DataObject)objs[0];
			BaseVO baseVO = (BaseVO)vo1.get("baseVO");
			String returnCode = (String)baseVO.getErrCod();
			if(!"200".equals(returnCode)){
				throw new EOSException(baseVO.getErrMsg());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	//管理系统第二次放款接口
	public void loanToLcs2(String loanId) throws EOSException{
		//对账流水号
		Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());
		//发送二次交易
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		//loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		//借新还旧和普通放款不是一个交易码，先查是否借新还旧
		Map<String,String> map = new HashMap<String, String>();
		map.put("loanId", loanId);
		Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getIsJxhj", map);
		DataObject isJxhj = (DataObject) objs1[0];
		String bizHappenType = isJxhj.getString("BIZ_HAPPEN_TYPE");
		if("06".equals(bizHappenType)){//借新还旧
			//借新还旧老借据余额更新--20160721------
			logger.info("--------更新老借据的借据余额--------begin------");
			String applyId = isJxhj.getString("APPLY_ID");
			DataObject jxhj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSummary");
			jxhj.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", jxhj, jxhj);
			if(jxhj.get("summaryId")==null||"".equals(jxhj.get("summaryId"))){
				throw new EOSException("未查到借新还旧管理借据信息");
			}
			try {
				String oldSummaryId = (String)jxhj.get("summaryId");
				DataObject oldSummaryInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				oldSummaryInfo.set("summaryId", oldSummaryId);
				DatabaseUtil.expandEntity("default", oldSummaryInfo);
				BigDecimal jjye = oldSummaryInfo.getBigDecimal("jjye");
				Map<String, Object> params0 = new HashMap<String, Object>();
				params0.put("jjye", jjye);//借据余额
				params0.put("loanAmt", loanInfo.getBigDecimal("loanAmt"));//本金
				jjye = MathHelper.expressionBigDecimal("jjye-loanAmt",
						params0);
				
				oldSummaryInfo.set("jjye", jjye);
				DatabaseUtil.updateEntity("default", oldSummaryInfo);
				
				Map<String,String> map2 = new HashMap<String,String>();
				map2.put("partyId", loanInfo.getString("partyId"));
				DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			} catch (Exception e) {
				e.printStackTrace();
				throw new EOSException("更新老借据的借据余额失败");
			}
			logger.info("--------更新老借据的借据余额--------end------");
			String telNo = loanInfo.getString("loanNum");
			String dueNum = loanInfo.getString("summaryNum");
			String loanorg = loanInfo.getString("loanOrg");
			String userNum = loanInfo.getString("userNum");
			Object[] params1 = new Object[2];
			params1[0] = "MA1_5119";
			//一次交易
			logger.info("---------借新还旧一次交易--------begin------");
			//PayConInfo vo = new PayConInfo();
			RepayOldNewRq vo = new RepayOldNewRq();
			BaseVO bvo = new BaseVO();
			bvo.setTranCod("T1115");
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setTrnDep(loanorg);
			bvo.setOpnDep(loanorg);
			bvo.setTranTimes("1");
			bvo.setToCoreSys("0");
			bvo.setRcnStan(lcsStan);
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setOpr(userNum);
			bvo.setTranFrom("47");
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod("9999");
			vo.setBaseVO(bvo);
			vo.setDueNum(dueNum);
			vo.setTelNo(telNo);
			vo.setPayOutItrFlg(loanInfo.getString("payOutFlag"));//借新还旧是否归还利息标志
			params1[1] = vo;
			
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs;
			try {
				objs = logicComponent.invoke("newDataInsertCheck", params1);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
			DataObject db = (DataObject)objs[0];
			BaseVO vo1 = (BaseVO)db.get("baseVO");
			vo.setDueNum(db.getString("dueNum"));
			vo.setBrwName(db.getString("brwName"));
			vo.setConNo(db.getString("conNo"));
			vo.setProdCod(db.getString("prodCod"));
			vo.setPrimAcct(db.getString("primAcct"));
			vo.setPrimAcctName(db.getString("primAcctName"));
			vo.setPayPrimAcct(db.getString("payPrimAcct"));
			vo.setPayPrimName(db.getString("payPrimName"));
			vo.setCurrCod(db.getString("currCod"));
			vo.setPadUpAmt(db.getBigDecimal("padUpAmt"));
			vo.setNorItrRate(db.getBigDecimal("norItrRate"));
			vo.setDelItrRate(db.getBigDecimal("delItrRate"));
			vo.setBegDate(db.getString("begDate"));
			vo.setEndDate(db.getString("endDate"));
			vo.setCurPrmPayTyp(db.getString("curPrmPayTyp"));
			vo.setCurAstPayTyp(db.getString("curAstPayTyp"));
			vo.setCaspan(db.getString("caspan"));
			vo.setPayDate(db.getString("payDate"));
			vo.setNextProvDate(db.getString("nextProvDate"));
			vo.setStgFirstMon(db.getLong("stgFirstMon"));
			vo.setDiscType(db.getString("discType"));
			vo.setTelNo(db.getString("telNo"));
			vo.setDueNumSun(db.getString("dueNumSun"));
			vo.setPayOpenDep(db.getString("payOpenDep"));
			vo.setPayPrimAcctTyp(db.getString("payPrimAcctTyp"));
			vo.setPayOrder(db.getString("payOrder"));
			vo.setRcvPrn(db.getBigDecimal("rcvPrn"));
			vo.setRcvNorItrIn(db.getBigDecimal("rcvNorItrIn"));
			vo.setRcvDftItrIn(db.getBigDecimal("rcvDftItrIn"));
			vo.setRcvPnsItrIn(db.getBigDecimal("rcvPnsItrIn"));
			vo.setRcvCpdItrIn(db.getBigDecimal("rcvCpdItrIn"));
			vo.setPadUpPrn(db.getBigDecimal("padUpPrn"));
			vo.setPadUpNorItrIn(db.getBigDecimal("padUpNorItrIn"));
			vo.setPadUpDftItrIn(db.getBigDecimal("padUpDftItrIn"));
			vo.setPadUpPnsItrIn(db.getBigDecimal("padUpPnsItrIn"));
			vo.setPadUpCpdItrIn(db.getBigDecimal("padUpCpdItrIn"));
			vo.setPadUpPentIcm(db.getBigDecimal("padUpPentIcm"));
			vo.setPrvCod(db.getString("prvCod"));
			vo.setRlsDep(db.getString("rlsDep"));
			vo.setBegItrDate(db.getString("begItrDate"));
			vo.setForwProvDate(db.getString("forwProvDate"));
			vo.setTalDep(db.getString("talDep"));
			vo.setPayOutItrFlg(db.getString("payOutItrFlg"));
			String returnCode = vo1.getErrCod();
			if(!"00000".equals(returnCode)){
				throw new EOSException(vo1.getErrMsg());
			}
			logger.info("---------借新还旧一次交易--------end------");
			//二次交易
			logger.info("---------借新还旧二次交易--------begin------");
			vo1.setTranTimes("2");
			vo.setBaseVO(vo1);
			
			params1[1] = vo;
			try {
				objs = logicComponent.invoke("newDataInsertCheck", params1);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
			db = (DataObject)objs[0];
			vo1 = (BaseVO)db.get("baseVO");
			returnCode = vo1.getErrCod();
			if(!"00000".equals(returnCode)){
				throw new EOSException(vo1.getErrMsg());
			}else{
				try {
					String repayOld = db.getString("accEntJson");
					TransferDataUtil util = new TransferDataUtil();
					FXD011[] fxd011s = util.plusToHx(repayOld);
					for(FXD011 fxd : fxd011s){
						fxd.setChargeBrch(fxd.getChargeBrch());
					}
					String allMoney = util.getAllMoney(fxd011s);
					CrmsMgrCallCoreProxy hxproxy = new CrmsMgrCallCoreImpl();
					OXD011_AccoutingReq requestBody = new OXD011_AccoutingReq();
					requestBody.setChargeSeq(String.valueOf(vo1.getRcnStan()));
					requestBody.setOutSystemDate(vo1.getAccSysDate());
					requestBody.setBusiType1("XDHS");
					requestBody.setAmount(allMoney);
					requestBody.setThridTransCode("XD01");
					requestBody.setRemarkInfo("G");
					requestBody.setRecNum(new BigInteger(fxd011s.length+""));
					requestBody.setFxd011(fxd011s);
					requestBody.setHxorg(loanorg);
					requestBody.setSummaryCode("B00146");//摘要代码
					requestBody.setSummaryDescription("借新还旧");//摘要描述
					OXD012_AccoutingRes res = hxproxy.executeXD01(requestBody);
					if("AAAAAAA".equals(res.getResTranHeader().getHRetCode())){
						vo1.setTranTimes("3");
						vo.setBaseVO(vo1);
						vo.setDueNum(db.getString("dueNum"));
						vo.setBrwName(db.getString("brwName"));
						vo.setConNo(db.getString("conNo"));
						vo.setProdCod(db.getString("prodCod"));
						vo.setPrimAcct(db.getString("primAcct"));
						vo.setPrimAcctName(db.getString("primAcctName"));
						vo.setPayPrimAcct(db.getString("payPrimAcct"));
						vo.setPayPrimName(db.getString("payPrimName"));
						vo.setCurrCod(db.getString("currCod"));
						vo.setPadUpAmt(db.getBigDecimal("padUpAmt"));
						vo.setNorItrRate(db.getBigDecimal("norItrRate"));
						vo.setDelItrRate(db.getBigDecimal("delItrRate"));
						vo.setBegDate(db.getString("begDate"));
						vo.setEndDate(db.getString("endDate"));
						vo.setCurPrmPayTyp(db.getString("curPrmPayTyp"));
						vo.setCurAstPayTyp(db.getString("curAstPayTyp"));
						vo.setCaspan(db.getString("caspan"));
						vo.setPayDate(db.getString("payDate"));
						vo.setNextProvDate(db.getString("nextProvDate"));
						vo.setStgFirstMon(db.getLong("stgFirstMon"));
						vo.setDiscType(db.getString("discType"));
						vo.setTelNo(db.getString("telNo"));
						vo.setDueNumSun(db.getString("dueNumSun"));
						vo.setPayOpenDep(db.getString("payOpenDep"));
						vo.setPayPrimAcctTyp(db.getString("payPrimAcctTyp"));
						vo.setPayOrder(db.getString("payOrder"));
						vo.setRcvPrn(db.getBigDecimal("rcvPrn"));
						vo.setRcvNorItrIn(db.getBigDecimal("rcvNorItrIn"));
						vo.setRcvDftItrIn(db.getBigDecimal("rcvDftItrIn"));
						vo.setRcvPnsItrIn(db.getBigDecimal("rcvPnsItrIn"));
						vo.setRcvCpdItrIn(db.getBigDecimal("rcvCpdItrIn"));
						vo.setPadUpPrn(db.getBigDecimal("padUpPrn"));
						vo.setPadUpNorItrIn(db.getBigDecimal("padUpNorItrIn"));
						vo.setPadUpDftItrIn(db.getBigDecimal("padUpDftItrIn"));
						vo.setPadUpPnsItrIn(db.getBigDecimal("padUpPnsItrIn"));
						vo.setPadUpCpdItrIn(db.getBigDecimal("padUpCpdItrIn"));
						vo.setPadUpPentIcm(db.getBigDecimal("padUpPentIcm"));
						vo.setPrvCod(db.getString("prvCod"));
						vo.setRlsDep(db.getString("rlsDep"));
						vo.setBegItrDate(db.getString("begItrDate"));
						vo.setForwProvDate(db.getString("forwProvDate"));
						vo.setTalDep(db.getString("talDep"));
						vo.setPayOutItrFlg(db.getString("payOutItrFlg"));
						vo.setAccEntJson(repayOld);
						params1[1] = vo;
						objs = logicComponent.invoke("newDataInsertCheck", params1);
						db = (DataObject)objs[0];
						vo1 = (BaseVO)db.get("baseVO");
						returnCode = vo1.getErrCod();
						if(!"00000".equals(returnCode)){
							throw new EOSException(vo1.getErrMsg());
						}
					}else{
						throw new EOSException(res.getResTranHeader().getHRetMsg());
					}
				} catch (Throwable e) {
					throw new EOSException("调用核心异常"+e.getMessage());
				}
			}
		}else{
			String telNo = loanInfo.getString("loanNum");
			String dueNum = loanInfo.getString("summaryNum");
			String loanorg = loanInfo.getString("loanOrg");
			//调用放款接口
			Map<String,String> ap = payLoanToAplus(telNo, dueNum, loanorg, lcsStan,loanId,"");
			if(!"00000".equals(ap.get("resCode"))){
				throw new EOSException(ap.get("msg"));
			}
		}
		Map<String,String> map4 = new HashMap<String,String>();
		map4.put("rcnStan", String.valueOf(lcsStan));
		map4.put("summaryNum", loanInfo.getString("summaryNum"));
		DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateSummaryInfo", map4);
	}
	/**
	 * 调用核算贷款放款接口
	 * @param telNo 通知书编号
	 * @param dueNum 借据编号
	 * @param loanOrg 出账机构
	 * @param lcsStan 对账流水号
	 * @param isDk 是否贷款 (1 是   0否)
	 * @return map 响应码  00000 成功
	 * @throws EOSException
	 */
	public Map<String,String> payLoanToAplus(String telNo,String dueNum,String loanOrg,Long lcsStan,String loanId,String isDk) throws EOSException{
		String trustFlg = "";
		if(!StringUtils.isBlank(loanId)){
			Map<String, String> mapn = new HashMap<String, String>();
			  mapn.put("loanId", loanId);
				Object[] tcSupLoanInfos = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfo", mapn);
				if(null ==tcSupLoanInfos || tcSupLoanInfos.length==0){
					throw new EOSException("-------------未查询到主协议表数据------------------");
				}
				DataObject tcSupLoanInfo = (DataObject)tcSupLoanInfos[0];
				DatabaseUtil.saveEntity("default", tcSupLoanInfo);
				String trusToPayFlg = tcSupLoanInfo.getString("trusToPayFlg");
				if("1".equals(trusToPayFlg)){
					trustFlg="8";//受托支付标志,此标志作为判断用
				}
		}
		Map<String,String> map = new HashMap<String,String>();
		String resCode = DictContents.APLUS_RECODE;
		Map maps = new HashMap();
		maps.put("summaryNum", dueNum);
		Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryLoanOpr", maps);
		Map mapo = (Map) orgArea[0];
		String opr = (String) mapo.get("USERNUM");//操作员和出账保持一致
		String msg = "";
		Object[] params1 = new Object[2];
		params1[0] = "MA1_1101";
		PayOutRq vo = new PayOutRq();
		BaseVO bvo = new BaseVO();
		bvo.setTranCod("T1101");
		bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setOpnDep(loanOrg);
		bvo.setTrnDep(loanOrg);
		bvo.setTranTimes("1");
		bvo.setToCoreSys("0");
		bvo.setTranFrom("47");
		bvo.setRcnStan(lcsStan);
		bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		bvo.setOpr(opr);
		bvo.setOrigFrom("11000");
		bvo.setLegPerCod("9999");
		vo.setTrustFlg(trustFlg);
		vo.setBaseVO(bvo);
		vo.setDueNum(dueNum);
		vo.setTelNo(telNo);
		vo.setIsDk(isDk);
		params1[1] = vo;
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pay.LoanSummary");
		Object[] objs;
		try {
			objs = logicComponent.invoke("loanToLcs2", params1);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		PayOutRq vo1 = (PayOutRq)objs[0];
		
		resCode = vo1.getBaseVO().getErrCod();
		msg = vo1.getBaseVO().getErrMsg();
		map.put("resCode", resCode);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 调用核算贷款放款接口
	 * @param telNo 通知书编号
	 * @param dueNum 借据编号
	 * @param loanOrg 出账机构
	 * @param lcsStan 对账流水号
	 * @return map 响应码  00000 成功
	 * @throws EOSException
	 */
	public Map<String,String> payLoanToAplus(String telNo,String dueNum,String loanOrg,Long lcsStan) throws EOSException{
		return payLoanToAplus(telNo, dueNum, loanOrg, lcsStan, "0","");
	}
	/**
	 * 国结调用信贷放款交易
	 * @param loanId 
	 * @param sequence
	 * @throws EOSException
	 */
	public Map<String,String> loanToLcsForGJ(String loanId, String sequence)
			throws EOSException {
		Map<String,String> map = new HashMap<String,String>();
		// 发送二次交易
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String telNo = loanInfo.getString("loanNum");
		String dueNum = loanInfo.getString("summaryNum");
		String loanorg = loanInfo.getString("loanOrg");
		String resCode = DictContents.APLUS_RECODE;
		Map maps = new HashMap();
		maps.put("summaryNum", dueNum);
		Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryLoanOpr", maps);
		Map mapo = (Map) orgArea[0];
		String opr = (String) mapo.get("USERNUM");//操作员和出账保持一致
		String msg = "";
		Object[] params1 = new Object[2];
		params1[0] = "MA1_1101";
		PayOutRq vo = new PayOutRq();
		BaseVO bvo = new BaseVO();
		bvo.setTranCod("T1101");
		bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());//
		//bvo.setTranDate("20170403");
		bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setOpnDep(loanorg);
		bvo.setTrnDep(loanorg);
		bvo.setTranTimes("1");
		bvo.setToCoreSys("0");
		bvo.setTranFrom("47");
		bvo.setRcnStan(Long.valueOf(sequence));
		bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		bvo.setOpr(GitUtil.getCurrentUserId());
		bvo.setOrigFrom("11000");
		bvo.setLegPerCod("9999");
		vo.setBaseVO(bvo);
		vo.setDueNum(dueNum);
		vo.setTelNo(telNo);
		params1[1] = vo;
		ILogicComponent logicComponent = LogicComponentFactory
				.create("com.bos.pay.LoanSummary");
		Object[] objs;
		try {
			objs = logicComponent.invoke("loanToLcs2", params1);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		PayOutRq vo1 = (PayOutRq)objs[0];
		
		resCode = vo1.getBaseVO().getErrCod();
		msg = vo1.getBaseVO().getErrMsg();
		map.put("resCode", resCode);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 国结调用信贷还款交易
	 * @param loanId 
	 * @param sequence
	 * @throws Throwable 
	 * @throws EOSException
	 */
	public void loanToRepayForGJ(G002RequestBody gjRepaymentRq, String loanId)
			throws Throwable {
		// 发送二次交易
		DataObject loan = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loan.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loan);
		String telNo = loan.getString("loanNum");
		String dueNum = loan.getString("summaryNum");
		String loanorg = loan.getString("loanOrg");
		
		//国结业务还款
		String acctInd=loan.getString("currencyCd");;//货币代码
		
		if("CNY".equals(acctInd)){
			acctInd = "01";
		}else if(acctInd.equals("HKD")){//港币
			acctInd = "13";
		}else if(acctInd.equals("JPY")){//日元
			acctInd = "27";
		}else if(acctInd.equals("MOP")){//澳门元
			acctInd = "81";
		}else if(acctInd.equals("AUD")){//澳洲元
			acctInd = "29";
		}else if(acctInd.equals("SGD")){//新加坡元
			acctInd = "32";
		}else if(acctInd.equals("CHF")){//瑞士法郎
			acctInd = "15";
		}else if(acctInd.equals("GBP")){//英镑
			acctInd = "12";
		}else if(acctInd.equals("USD")){//美元
			acctInd = "14";
		}else if(acctInd.equals("EUR")){//欧元
			acctInd = "38";
		}else if(acctInd.equals("CAD")){//加拿大元
			acctInd = "28";
		}else{
			throw new EOSException("不支持的币种");
		}
		
		/**
		 * 调用核心账户查询接口
		 */
		CrmsMgrCallCoreProxy proxy = new CrmsMgrCallCoreImpl();
		OXD051_AccInfoQryReq req = new OXD051_AccInfoQryReq();
		req.setQryType("1");
		req.setCustAcctNo(gjRepaymentRq.getPayPrimAcct());//账号
		req.setCurrCode(acctInd);
		if("01".equals(acctInd)){
			req.setCashFlag("0");
		}else{
			req.setCashFlag("1");
		}
		req.setQryPwd("1");
		req.setOrgNum(loanorg);
		OXD052_AccInfoQryRes rs = proxy.executeXD05(req);
		BigDecimal avalibAmt = new BigDecimal(rs.getOxd052ResBody().getAvailableAmt());
		BigDecimal padUpAmt = gjRepaymentRq.getPadUpAmt();//还款金额
		if(padUpAmt.doubleValue() > avalibAmt.doubleValue()) {
			throw new EOSException("还款金额大于账户可用余额！");
		}
		//1-1500还款申请
		DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
		zh.set("loanId", loanId);
		if("01008001".equals(loan.getString("productType")) ||"01008002".equals(loan.getString("productType")) 
				||"01008010".equals(loan.getString("productType")) 
				|| "01006001".equals(loan.getString("productType")) || "01006002".equals(loan.getString("productType"))
				|| "01007008".equals(loan.getString("productType")) || "01009001".equals(loan.getString("productType")) 
				|| "01009002".equals(loan.getString("productType")) || "01009010".equals(loan.getString("productType")) || "01011001".equals(loan.getString("productType")) 
				|| "01012001".equals(loan.getString("productType")) || "01004001".equals(loan.getString("productType"))
				|| "01006010".equals(loan.getString("productType")) //村镇银行贴现产品
				) {
			zh.set("zhlx", "2");
		}else {
			zh.set("zhlx", "1");
		}
		DatabaseUtil.expandEntityByTemplate("default", zh, zh);
		
		Object[] params1 = new Object[1];
		//还款控制信息
		RepayControlInfRq vo = new RepayControlInfRq();
		BaseVO bvo = new BaseVO();
		bvo.setTranCod("T1202");
		bvo.setOpr(GitUtil.getCurrentUserId());
		bvo.setAut(GitUtil.getCurrentUserId());
		bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		bvo.setRcnStan(Long.valueOf(gjRepaymentRq.getGjFlowNo()));
		bvo.setTrnDep(loanorg);
		bvo.setTranFrom("47");
		bvo.setOrigFrom("11000");
		bvo.setLegPerCod("9999");
		bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		bvo.setDepCod(GitUtil.getBranchId());
		bvo.setOpnDep(loan.getString("loanOrg"));
		vo.setDueNum(dueNum);//借据编号
		//vo.setTelNo(telNo);//通知书编号
		vo.setTelNo(gjRepaymentRq.getGjFlowNo());//通知书编号---为了不一样  重新取值
		vo.setPayOrder(gjRepaymentRq.getPayOrder());//还款顺序
		//还款账号
		vo.setPayPrimAcct(gjRepaymentRq.getPayPrimAcct());
		//还款账户名称
		vo.setPayPrimName(gjRepaymentRq.getPayPrimName());
		vo.setPayOutItrFlg(gjRepaymentRq.getPayOutItrFlg());//归还未结计利息标志
		vo.setPrinPlanFlg(gjRepaymentRq.getPrinPlanFlg());//下发新的还本/还息计划标志
		vo.setBaseVO(bvo);
		vo.setPadUpAmt(gjRepaymentRq.getPadUpAmt());
		params1[0] = vo;
		//调用核算还款控制信息
		CrmsCallPlusProxy proxy1 = new CrmsCallPlusImpl();
		RepayControlInfRq rectol = proxy1.executeT1202(vo);
		if("00000".equals(rectol.getBaseVO().getErrCod())){
			RepaymentRq rqs = new RepaymentRq();
			rqs.setDueNum(vo.getDueNum());
			rqs.setTelNo(vo.getTelNo());
			rqs.setPayOutItrFlg(vo.getPayOutItrFlg());
			bvo.setTranCod("T1102");
			vo.setBaseVO(bvo);
			rqs.setBaseVO(vo.getBaseVO());
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
	}
	/**
	 * 
	* @Title: loanToLcs2ForDk 
	* @Description: 垫款放款交易
	* @param loanId
	* @param dueNum
	* @throws EOSException    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年9月27日 下午2:29:12 
	* @version V1.0
	 */
	public void loanToLcs2ForDk(String loanId,String dueNum) throws EOSException{
		//发送二次交易
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		//loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String telNo = loanInfo.getString("loanNum");
		String loanorg = loanInfo.getString("loanOrg");
		Object[] params1 = new Object[2];
		params1[0] = "MA1_1106";
		
		Date dkdate = DateHelper.calculateDate(GitUtil.getBusiDate(), 0, 0, 1);
		String dkdatesString = DateHelper.formatDateYYYYMMDD(GitUtil.getBusiDate());
		
		PayConInfo vo = new PayConInfo();
		vo.getBaseVO().setTranCod("MA1_1106");
		vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());
		vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		vo.getBaseVO().setTrnDep(loanorg);
		vo.setDueNum(dueNum);
		vo.setTelNo(telNo);
		vo.getBaseVO().setTranTimes("2");
		vo.getBaseVO().setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
		vo.getBaseVO().setOpr("1892");
		params1[1] = vo;
		
		ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
		Object[] objs;
		try {
			objs = logicComponent.invoke("newDataInsertCheck", params1);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		DataObject vo1 = (DataObject)objs[0];
		BaseVO baseVO = (BaseVO)vo1.get("baseVO");
		String returnCode = (String)baseVO.getErrCod();
		if(!"200".equals(returnCode)){
			throw new EOSException(baseVO.getErrMsg());
		}
	}
	
	/**
	 * 将放款数据插入管理的计量中间表
	 * @param loanId
	 * @return
	 */
	public String saveLcsInfo(String loanId){
		String ret = "0";
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanId", loanId);
		try {
			//查出账信息
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			
			//主协议表数据---------------------------------------
			Object[] tcSupLoanInfos = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfo", map);
			if(null ==tcSupLoanInfos || tcSupLoanInfos.length==0){
				throw new EOSException("-------------未查询到主协议表数据------------------");
			}
			DataObject tcSupLoanInfo = (DataObject)tcSupLoanInfos[0];
			if(DictContents.PRODUCT_CD_01008001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007009.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007012.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007013.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007014.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007010.equals(loanInfo.get("productType"))){
				tcSupLoanInfo.set("hldFlg", "00");//节假日 处理方式
				tcSupLoanInfo.set("caspan", "9");//垫款结息周期---核算自定义结息周期
				tcSupLoanInfo.set("calDays", "1");//垫款结息天数--
				tcSupLoanInfo.set("studPerd","");//核算对应---助学贷款宽限日期---不需要值
				//垫款还款方式核算只接受11
				tcSupLoanInfo.set("curPrmPayTyp", "11");
			}
			if("01007001".equals(loanInfo.get("productType"))
					|| "01007003".equals(loanInfo.get("productType"))
					|| "01007004".equals(loanInfo.get("productType"))
					|| "01007006".equals(loanInfo.get("productType"))
					|| "01007005".equals(loanInfo.get("productType"))
					|| "01007011".equals(loanInfo.get("productType"))){
				//国结的产品---不做批扣
				tcSupLoanInfo.set("batFlg", "0");
				tcSupLoanInfo.set("studPerd","");//核算对应---助学贷款宽限日期---不需要值
			}
			DatabaseUtil.saveEntity("default", tcSupLoanInfo);
			
			//账户信息表--------------------------------------
			if(DictContents.PRODUCT_CD_01008001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01008002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009001.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009002.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01009010.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007009.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007012.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007013.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007014.equals(loanInfo.get("productType"))
					||DictContents.PRODUCT_CD_01007010.equals(loanInfo.get("productType"))){
				//垫款没有账户信息
			}else{
				DataObject tcSupLoanInfoAcct = DataObjectUtil.createDataObject("com.bos.dataset.lcs.TcSupLoanInfoAcct");
				Object[] tcSupLoanInfoAccts = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfoAcct", map);
				if(null ==tcSupLoanInfoAccts || tcSupLoanInfoAccts.length==0){
						throw new EOSException("-------------未查询到账户信息表数据------------------");
				}
				for (int i = 0; i < tcSupLoanInfoAccts.length; i++) {
					
					DataObject tempAcct = (DataObject)tcSupLoanInfoAccts[i];
					if(tempAcct.getString("payPrimAcctTyp").equals("1")){//第一还款账户
						tcSupLoanInfoAcct=tempAcct;
						
					}else if(tempAcct.getString("payPrimAcctTyp").equals("6")){//第二还款账户
						tcSupLoanInfoAcct.set("payPrimAcctTyp1","2" );//tempAcct.getString("payPrimAcct")
						tcSupLoanInfoAcct.set("payPrimAcctFlg1",tempAcct.getString("payPrimAcctFlg"));
						tcSupLoanInfoAcct.set("payPrimAcct1",tempAcct.getString("payPrimAcct"));
						tcSupLoanInfoAcct.set("payOpenDep1",tempAcct.getString("payOpenDep"));
						tcSupLoanInfoAcct.set("payPrimName1",tempAcct.getString("payPrimName"));
					}else if(tempAcct.getString("payPrimAcctTyp").equals("7")){//第三还款账户
						tcSupLoanInfoAcct.set("payPrimAcctTyp2","3" );//tempAcct.getString("payPrimAcct")
						tcSupLoanInfoAcct.set("payPrimAcctFlg2",tempAcct.getString("payPrimAcctFlg"));
						tcSupLoanInfoAcct.set("payPrimAcct2",tempAcct.getString("payPrimAcct"));
						tcSupLoanInfoAcct.set("payOpenDep2",tempAcct.getString("payOpenDep"));
						tcSupLoanInfoAcct.set("payPrimName2",tempAcct.getString("payPrimName"));
					}
				}
				DatabaseUtil.saveEntity("default", tcSupLoanInfoAcct);
			}
			
			
			//还款协议表--------------------------------
			Object[] tcSupLoanInfoCalPayPlans = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfoCalPayPlan", map);
			if(null ==tcSupLoanInfoCalPayPlans || tcSupLoanInfoCalPayPlans.length==0){
				throw new EOSException("-------------未查询到还款协议表数据------------------");
			}
			DataObject tcSupLoanInfoCalPayPlan = (DataObject)tcSupLoanInfoCalPayPlans[0];
			DatabaseUtil.saveEntity("default", tcSupLoanInfoCalPayPlan);
			
			//贴息信息表------------------------------
			//根据loanId取applyId
			Object[] applyIds = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getApplyIdByLoanId", map);
			if(null ==applyIds || applyIds.length==0){
				throw new EOSException("-------------未查询到applyId------------------");
			}
			String applyId = (String)((DataObject)applyIds[0]).get("APPLY_ID");
			map.put("applyId", applyId);
			//查询贴息主体
			Object[] txzts = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTxzt", map);
			if(null ==txzts || txzts.length==0){
				//非贴息贷款
			}else{
				Object[] tcSupLoanInfoDiscInfos = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfoDiscInfo", map);
				if(null ==tcSupLoanInfoDiscInfos || tcSupLoanInfoDiscInfos.length==0){
					throw new EOSException("-------------未查询到贴息主体信息------------------");
				}else{
					DataObject tcSupLoanInfoDiscInfo = (DataObject)tcSupLoanInfoDiscInfos[0];
					for(int i=1;i<txzts.length+1;i++){
						DataObject txzt = (DataObject)txzts[i-1];
						tcSupLoanInfoDiscInfo.set("protNum"+i, "11");  //---协议号1
						if("60".equals(txzt.get("zhlx1"))){
							tcSupLoanInfoDiscInfo.set("discAccTyp"+i, "0");	//---贴息账号类型
						}else if("12".equals(txzt.get("zhlx1"))){
							tcSupLoanInfoDiscInfo.set("discAccTyp"+i, "2");	//---贴息账号类型
						}
						tcSupLoanInfoDiscInfo.set("discAcc"+i, txzt.get("txzh1"));		//贴息主体账户
						tcSupLoanInfoDiscInfo.set("discAccOpnDep"+i, txzt.get("txjg1"));//贴息账号开户机构
						tcSupLoanInfoDiscInfo.set("discAccNm"+i, txzt.get("txzt1"));	//贴息账户名称
						tcSupLoanInfoDiscInfo.set("discRate"+i, txzt.get("txbl"));		//贴息率
						tcSupLoanInfoDiscInfo.set("discSubdExpAmt"+i, (BigDecimal)txzt.get("JE"));	//固定贴息金额期初金额
						tcSupLoanInfoDiscInfo.set("discSubdAmt"+i, (BigDecimal)txzt.get("JE"));		//固定贴息金额余额
					}
					DatabaseUtil.saveEntity("default", tcSupLoanInfoDiscInfo);
				}
			}
			//委贷协议表--------------------------------
			Object[] tcSupLoanInfoEntrInfos = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfoEntrInfo", map);
			if(null ==tcSupLoanInfoEntrInfos || tcSupLoanInfoEntrInfos.length==0){
				//非委贷
			}else{
				DataObject tcSupLoanInfoEntrInfo = (DataObject)tcSupLoanInfoEntrInfos[0];
				DatabaseUtil.saveEntity("default", tcSupLoanInfoEntrInfo);
			}
			
			//还款计划表-----------------------------------
			String repayType = loanInfo.getString("repayType");
			if("1400".equals(repayType)||"1410".equals(repayType)){
				Object[] tcSupPrinPlanNs = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupPrinPlanNs", map);
				if(null ==tcSupPrinPlanNs || tcSupPrinPlanNs.length==0){
					throw new EOSException("-------------未查询到还款计划表表数据------------------");
				}
				DataObject[] plans = new DataObject[tcSupPrinPlanNs.length];
				for(int i=0;i<plans.length;i++){
					plans[i] = (DataObject) tcSupPrinPlanNs[i];
				}
				DatabaseUtil.saveEntities("default", plans);
			}
			//受托支付账号信息-----------------------------------
			String trusToPayFlg = tcSupLoanInfo.getString("trusToPayFlg");
			if("1".equals(trusToPayFlg)){
				Object[] tcsuptrustpayaccts = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupTrustPayAcct", map);
				if(null !=tcsuptrustpayaccts && tcsuptrustpayaccts.length>0){
					DataObject tcSupTrustPayAccts = (DataObject)tcsuptrustpayaccts[0];
					if(null != tcSupTrustPayAccts.get("payAcct")){
						DatabaseUtil.saveEntity("default", tcSupTrustPayAccts);
					}
				}
				Object[] tcsuptrustpayaccts2 = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupTrustPayAcct2", map);
				if(null !=tcsuptrustpayaccts2 && tcsuptrustpayaccts2.length>0){
					DataObject tcSupTrustPayAcct2 = (DataObject)tcsuptrustpayaccts2[0];
					if(null != tcSupTrustPayAcct2.get("payAcct")){
						DatabaseUtil.saveEntity("default", tcSupTrustPayAcct2);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = "2";
		}
		return ret;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
