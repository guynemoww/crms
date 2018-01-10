/**
 * 
 */
package com.bos.mq.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import com.bos.inter.CallBmsInterface.CRMSLoanRevNotRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInBackRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollOutRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollReVerRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollReVerRigRs;
import com.bos.inter.CallCCMSInterface.CollCancelSynRec;
import com.bos.inter.CallCCMSInterface.CollLaidUpSynRec;
import com.bos.inter.CallCCMSInterface.CollStockRemovalSynRec;
import com.bos.inter.CallCRMInterface.CRMSCapCheckRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppRevRq;
import com.bos.inter.CallScfInterface.CRMSLoanBackRq;
import com.bos.inter.CallScfInterface.CRMSLoanBalInqRq;
import com.bos.inter.CallScfInterface.CRMSLoanConfirRq;
import com.bos.inter.CallScfInterface.NoteInfoRec;
import com.bos.inter.CallT24Interface.AmountRec;
import com.bos.inter.CallT24Interface.InterestRateRec;
import com.bos.inter.CallT24Interface.TDpAcctAllInqRq;
import com.bos.inter.CallT24Interface.TExCurrencyAllInqRq;
import com.bos.inter.CallT24Interface.TExTsaAllAaaRq;
import com.bos.inter.CallT24Interface.TStLdRpmAaaRec;
import com.bos.inter.CallT24Interface.TStLdStdAaaRq;
import com.bos.inter.CallT24Interface.TStLdStdAaaRs;
import com.eos.system.annotation.Bizlet;
import com.ibm.mq.MQException;
import com.primeton.ext.data.sdo.collection.ContainerAwareList;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

/**
 * @author shenglong
 * @date 2014-06-19 16:51:48
 *
 */
@Bizlet("")
public class SendObjUtil {
	
	private static final SimpleDateFormat dateTemp=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 票据放款审核通知接口
	 * 对象数据转换
	 */
	@Bizlet("转换审核通知对象")
	public CRMSLoanRevNotRq converBMSLoanConfirm(DataObject notice)throws JAXBException, MQException
	{
		CRMSLoanRevNotRq rq=new CRMSLoanRevNotRq();
		rq.setContractId(notice.getString("ContractId"));
		rq.setAcceptNo(notice.getString("AcceptNo"));
		rq.setAmt(FormatUtil.formatDouble(notice.getDouble("Amt"),2));
		rq.setCurrency(notice.getString("Currency"));
		rq.setLoanApproveNum(notice.getString("LoanApproveNum"));
		rq.setYearRate(notice.getString("YearRate"));
		rq.setLoanApplyDt(dateTemp.format(notice.getDate("LoanApplyDt")));
		rq.setLoanEndDt(dateTemp.format(notice.getDate("LoanEndDt")));
		rq.setLoanDt(dateTemp.format(notice.getDate("LoanDt")));
		rq.setState(notice.getString("State"));
		return rq;
	}
	
	/**
	 * 供应链放款确认通知接口
	 * 对象数据转换
	 */
	@Bizlet("转换放款确认通知对象")
	public CRMSLoanConfirRq converSCFLoanConfirm(DataObject notice)throws JAXBException, MQException
	{
		CRMSLoanConfirRq rq=new CRMSLoanConfirRq();
		rq.setContractId(notice.getString("ContractId"));
		rq.setAcceptNo(notice.getString("AcceptNo"));
		rq.setLoanId(notice.getString("LoanId"));
		rq.setAmt(FormatUtil.formatDouble(notice.getDouble("Amt"),2));
		rq.setCurrency(notice.getString("Currency"));
		rq.setLoanApproveNum(notice.getString("LoanApproveNum"));
		rq.setYearRate(notice.getString("YearRate"));
		rq.setLoanApplyDt(dateTemp.format(notice.getDate("LoanApplyDt")));
		rq.setLoanEndDt(dateTemp.format(notice.getDate("LoanEndDt")));
		List<NoteInfoRec> rec = new ArrayList<NoteInfoRec>();
		
		if(notice.getList("NoteInfoRec").equals("")||notice.getList("NoteInfoRec")==null){
			rq.setNoteInfoRec(null);
		}
		else{
			
			ContainerAwareList temp=(ContainerAwareList)notice.getList("NoteInfoRec");
			for(int i=0;i<temp.size();i++){
				NoteInfoRec noteRec=new NoteInfoRec();
				DataObject recTemp =(DataObject)temp.get(i);
				noteRec.setValueDt(dateTemp.format(recTemp.getDate("ValueDt")));
				noteRec.setDraftId(recTemp.getString("DraftId"));
				noteRec.setAmount(FormatUtil.formatDouble(recTemp.getDouble("Amount"),2));
				noteRec.setMargin(FormatUtil.formatDouble(recTemp.getDouble("Margin"),2));
				noteRec.setDueDate(dateTemp.format(recTemp.getDate("DueDate")));
				noteRec.setDraftCcy(recTemp.getString("DraftCcy"));
				rec.add(noteRec);
			}
			rq.setNoteInfoRec(rec);
		}
		return rq;
	}
	/**
	 * 供应链放款申请撤销通知接口
	 * 对象数据转换
	 */
	@Bizlet("转换放款申请撤销对象")
	public CRMSLoanAppRevRq converSCFCRMSLoanAppRev(DataObject notice)throws JAXBException, MQException
	{
		CRMSLoanAppRevRq rq=new CRMSLoanAppRevRq();
		rq.setContractId(notice.getString("ContractId"));
		rq.setLoanId(notice.getString("LoanId"));
		return rq;
	}
	
	/**
	 * 供应链放款回退通知接口
	 * 对象数据转换
	 */
	@Bizlet("转换放款回退通知接口对象")
	public CRMSLoanBackRq converSCFBackNotice(DataObject notice)throws JAXBException, MQException
	{
		CRMSLoanBackRq rq=new CRMSLoanBackRq();
		rq.setContractId(notice.getString("ContractId"));
		rq.setLoanApproveNum(notice.getString("LoanApproveNum"));
		rq.setLoanId(notice.getString("LoanId"));
		rq.setAcceptNo(notice.getString("AcceptNo"));
		return rq;
	}
	/**
	 * 供应链放款余额查询通知接口
	 * 对象数据转换
	 */
	@Bizlet("转换放款余额查询对象")
	public CRMSLoanBalInqRq converSCFCRMSLoanBalInq(DataObject notice)throws JAXBException, MQException
	{
		CRMSLoanBalInqRq rq=new CRMSLoanBalInqRq();
		rq.setContractId(notice.getString("ContractId"));
		rq.setLoanApproveNum(notice.getString("LoanApproveNum"));
		return rq;
	}
	
	/**
	 * 转换T24放款申请接口
	 * 对象数据转换
	 */
	@Bizlet("转换现金管理放款申请对象")
	public TStLdStdAaaRq converCAMSLoan(DataObject notice)throws JAXBException, MQException
	{
		TStLdStdAaaRq rq=new TStLdStdAaaRq();
		rq.setFtTxnType(notice.getString("FtTxnType"));
		rq.setFBID(notice.getString("FBID"));
		rq.setLoanAgrNo(notice.getString("LoanAgrNo"));
		rq.setCustomerId(notice.getString("CustomerId"));
		rq.setCurrency(notice.getString("Currency"));
		rq.setCategory(notice.getString("Category"));
		rq.setProductCode(notice.getString("ProductCode"));
		rq.setLcDrawNo(notice.getString("LcDrawNo"));
		rq.setDrawdownAccount(notice.getString("DrawdownAccount"));
		rq.setPrinLiqAcct(notice.getString("PrinLiqAcct"));
		rq.setIntLiqAcct(notice.getString("IntLiqAcct"));
		rq.setRecAccount(notice.getString("RecAccount"));
		rq.setValueDate(notice.getString("ValueDate"));
		rq.setFinMatDate(notice.getString("FinMatDate"));
		rq.setTenor(notice.getString("Tenor"));
		rq.setAutoScheds(notice.getString("AutoScheds"));
		rq.setDefineScheds(notice.getString("DefineScheds"));
		rq.setRestructure(notice.getString("Restructure"));
		rq.setLoanPurpose(notice.getString("LoanPurpose"));
		rq.setUltiIndBen(notice.getString("UltiIndBen"));
		rq.setDdApprNo(notice.getString("DdApprNo"));
		rq.setIntRateType(notice.getString("IntRateType"));
		rq.setVarRateFlag(notice.getString("VarRateFlag"));
		rq.setInterestBasis(notice.getString("InterestBasis"));
		rq.setIntPaymtMethod(notice.getString("IntPaymtMethod"));
		rq.setBaseRateKey(notice.getString("BaseRateKey"));
		rq.setSpreadRatePer(notice.getString("SpreadRatePer"));
		rq.setRateChgFreq(notice.getString("RateChgFreq"));
		rq.setInterestKey(notice.getString("InterestKey"));
		rq.setInterestSpread(notice.getString("InterestSpread"));
		rq.setPenSpread(notice.getString("PenSpread"));
		rq.setMisusePenSpr(notice.getString("MisusePenSpr"));
		rq.setChrgLiqAcct(notice.getString("ChrgLiqAcct"));
		rq.setChrgCode(notice.getString("ChrgCode"));
		rq.setChrgAmount(notice.getString("ChrgAmount"));
		rq.setChrgClaimDate(notice.getString("ChrgClaimDate"));
		rq.setChrgBookedOn(notice.getString("ChrgBookedOn"));
		rq.setChrgCapitalise(notice.getString("ChrgCapitalise"));
		rq.setTotalCapCharge(notice.getString("TotalCapCharge"));
		rq.setBusDayDefn(notice.getString("BusDayDefn"));
		rq.setLimitReference(notice.getString("LimitReference"));
		rq.setScecuredType(notice.getString("ScecuredType"));
		rq.setCountryRisk(notice.getString("CountryRisk"));
		rq.setMisAcctOfficer(notice.getString("MisAcctOfficer"));
		rq.setCorrLiabNo(notice.getString("CorrLiabNo"));
		rq.setLcAdvId(notice.getString("LcAdvId"));
		rq.setCfNumber(notice.getString("CfNumber"));
		rq.setCommitmentNo(notice.getString("CommitmentNo"));
		rq.setMatureAtSod(notice.getString("MatureAtSod"));
		rq.setDealerDesk(notice.getString("DealerDesk"));
		rq.setCustomerRef(notice.getString("CustomerRef"));
		rq.setCustRemarks(notice.getString("CustRemarks"));
		rq.setOurRemarks(notice.getString("OurRemarks"));
		rq.setAloneMergeInd(notice.getString("AloneMergeInd"));
		rq.setFowWardBackward(notice.getString("FowWardBackward"));
		rq.setBaseDateKey(notice.getString("BaseDateKey"));
		List<AmountRec> amt = new ArrayList<AmountRec>();
		//金额
		if(notice.getList("AmountRec").equals("")||notice.getList("AmountRec")==null){
			rq.setAmountRec(null);
		}
		else{
			ContainerAwareList temp=(ContainerAwareList)notice.getList("AmountRec");
			for(int i=0;i<temp.size();i++){
				AmountRec rec=new AmountRec();
				DataObject recTemp =(DataObject)temp.get(i);
				rec.setAmount(FormatUtil.formatDouble(recTemp.getDouble("Amount"),2));
				amt.add(rec);
			}
			rq.setAmountRec(amt);
		}
		
		//利率
		List<InterestRateRec> rate = new ArrayList<InterestRateRec>();
		if(notice.getList("InterestRateRec").equals("")||notice.getList("InterestRateRec")==null){
			rq.setInterestRateRec(null);
		}
		else{
			ContainerAwareList temp1=(ContainerAwareList)notice.getList("InterestRateRec");
			for(int i=0;i<temp1.size();i++){
				InterestRateRec re=new InterestRateRec();
				DataObject rateTemp =(DataObject)temp1.get(i);
				re.setInterestRate(rateTemp.getString("InterestRate"));
				rate.add(re);
			}
			rq.setInterestRateRec(rate);
		}
		//还款计划
		List<TStLdRpmAaaRec> Aaa = new ArrayList<TStLdRpmAaaRec>();
		if(notice.getList("TStLdRpmAaaRec")==null){
			rq.setTStLdRpmAaaRec(null);
		}
		else{
			ContainerAwareList tempAaa=(ContainerAwareList)notice.getList("TStLdRpmAaaRec");
			for(int i=0;i<tempAaa.size();i++){
				TStLdRpmAaaRec recAaa=new TStLdRpmAaaRec();
				DataObject AaaTemp =(DataObject)tempAaa.get(i);
				recAaa.setBeginDt(dateTemp.format(AaaTemp.getDate("BeginDt")));
				recAaa.setLsdAmount(FormatUtil.formatDouble(AaaTemp.getDouble("LsdAmount"),2));
				recAaa.setRate(AaaTemp.getString("Rate"));
				recAaa.setSchType(AaaTemp.getString("SchType"));
				Aaa.add(recAaa);
			}
			rq.setTStLdRpmAaaRec(Aaa);
		}
		return rq;
	}
	
	
	/**
	 * 转换T24放款申请接口
	 * 对象数据转换
	 */
	@Bizlet("转换现金管理放款申请对象")
	public TStLdStdAaaRs converCAMSReturnMsg(DataObject notice,String LdNum)throws JAXBException, MQException
	{
		TStLdStdAaaRs rs=new TStLdStdAaaRs();
		rs.setContractNo(LdNum);
		rs.setPeRate(notice.getString("peRate"));
		rs.setPsRate(notice.getString("psRate"));
		rs.setInterestRate(notice.getString("yearRate"));
		return rs;
		
	}
	/**
	 * 转换T24账户查询接口
	 * 对象数据转换
	 */
	@Bizlet("转换账户查询")
	public TDpAcctAllInqRq sendInfoToT24(String notice)throws JAXBException, MQException{
		
		TDpAcctAllInqRq rq=new TDpAcctAllInqRq();
		rq.setAcctNo(notice);
		rq.setMediumType("A");
		return rq;
		}
	/**
	 * 转换T24币种接口
	 * 对象数据转换
	 */
	@Bizlet("转换币种汇率")
	public TExCurrencyAllInqRq sendCurrInfoToT24(String ccyId,String allFlag)throws JAXBException, MQException{
		
		TExCurrencyAllInqRq rq=new TExCurrencyAllInqRq();
		rq.setCcyId(ccyId);
		rq.setAllFlag(allFlag);
		return rq;
		}
	/**
	 * 转换业务品种为指定字符串
	 * 对象数据转换
	 */
	@Bizlet("转换业务品种为指定字符串")
	public String convertApproveDetailToString(DataObject subList[])
	{
		String listStr="";
		for (int i=0;i<subList.length;i++)
		{
			String temp=subList[i].getString("ProductType");
			listStr +=temp+"@!@";
		}
		int index=listStr.lastIndexOf("@!@");
		listStr=listStr.substring(0, index);
		
		return listStr;
	}
	
	/**
	 * 入库实时接口
	 * 对象数据转换
	 */
	@Bizlet("转换入库申请实时接口数据")
	public CRMSCollInRigRq converCRMSCollInRig(DataObject notice)throws JAXBException, MQException
	{
		CRMSCollInRigRq rq=new CRMSCollInRigRq();
		rq.setSystemSource(notice.getString("SystemSource"));
		List<CollLaidUpSynRec> rec = new ArrayList<CollLaidUpSynRec>();
		ContainerAwareList temp=(ContainerAwareList)notice.getList("CollLaidUpSynRec");
		for (int i = 0; i < temp.size(); i++) {
			CollLaidUpSynRec collRec=new CollLaidUpSynRec();
			DataObject recTemp =(DataObject)temp.get(i);
			if(recTemp.getDate("BeginDate")!=null){
				collRec.setBeginDate(dateTemp.format(recTemp.getDate("BeginDate")));
			}
			collRec.setBreakValue(FormatUtil.formatDouble(recTemp.getDouble("BreakValue"),2));
			collRec.setCardType(recTemp.getString("CardType"));
			if(recTemp.getDate("EndDate")!=null){
				collRec.setEndDate(dateTemp.format(recTemp.getDate("EndDate")));
			}
			
			collRec.setInvoiceOrgan(recTemp.getString("InvoiceOrgan"));
			collRec.setIssueOffice(recTemp.getString("IssueOffice"));
			collRec.setOrderState(recTemp.getString("OrderState"));
			collRec.setOrganAddress(recTemp.getString("OrganAddress"));
			if(recTemp.getDate("RegDate")!=null){
				collRec.setRegDate(dateTemp.format(recTemp.getDate("RegDate")));
			}
			
			collRec.setRegType(recTemp.getString("RegType"));
			collRec.setRejectionCode(recTemp.getString("RejectionCode"));
			collRec.setRelCusName(recTemp.getString("RelCusName"));
			collRec.setSortType(recTemp.getString("SortType"));
			collRec.setSuretyId(recTemp.getString("SuretyId"));
			collRec.setTransferCode(recTemp.getString("TransferCode"));
			rec.add(collRec);
		}
		rq.setCollLaidUpSynRec(rec);
		return rq;
	}
	
	/**
	 * 出库实时接口
	 * 对象数据转换
	 */
	@Bizlet("转换出库申请实时接口数据")
	public CRMSCollOutRigRq converCRMSCollOutRig(DataObject notice)throws JAXBException, MQException
	{
		CRMSCollOutRigRq rq=new CRMSCollOutRigRq();
		rq.setSystemSource(notice.getString("SystemSource"));
		List<CollStockRemovalSynRec> rec = new ArrayList<CollStockRemovalSynRec>();
		ContainerAwareList temp=(ContainerAwareList)notice.get("CollStockRemovalSynRec");
		for (int i = 0; i < temp.size(); i++) {
			CollStockRemovalSynRec collRec=new CollStockRemovalSynRec();
			DataObject recTemp =(DataObject)temp.get(i);
			collRec.setCardType(recTemp.getString("CardType"));
			collRec.setCheckerName(recTemp.getString("CheckerName"));
			collRec.setEndDate(dateTemp.format(recTemp.getDate("EndDate")));
			collRec.setOrderState(recTemp.getString("OrderState"));
			if(null!=recTemp.getDate("PosTransDateTime")){
				collRec.setPosTransDateTime(dateTemp.format(recTemp.getDate("PosTransDateTime")));
			}
			collRec.setReason(recTemp.getString("Reason"));
			collRec.setRejectionCode(recTemp.getString("RejectionCode"));
			collRec.setRemark1(recTemp.getString("Remark1"));
			collRec.setSortType(recTemp.getString("SortType"));
			collRec.setSuretyId(recTemp.getString("SuretyId"));
			rec.add(collRec);
		}
		rq.setCollStockRemovalSynRec(rec);
		return rq;
	}
	
	/**
	 * 押品出入库撤销实时接口
	 * 对象数据转换
	 */
	@Bizlet("转换押品出入库撤销实时接口数据")
	public CRMSCollInBackRigRq converCRMSCollInBackRig(DataObject notice)throws JAXBException, MQException
	{
		CRMSCollInBackRigRq rq=new CRMSCollInBackRigRq();
		rq.setSystemSource(notice.getString("SystemSource"));
		rq.setOperateType(notice.getString("OperateType"));
		List<CollCancelSynRec> rec = new ArrayList<CollCancelSynRec>();
		ContainerAwareList temp=(ContainerAwareList)notice.get("CollCancelSynRec");
		for (int i = 0; i < temp.size(); i++) {
			CollCancelSynRec collRec=new CollCancelSynRec();
			DataObject recTemp =(DataObject)temp.get(i);
			collRec.setCardType(recTemp.getString("CardType"));
			collRec.setRejectionCode(recTemp.getString("RejectionCode"));
			collRec.setSortType(recTemp.getString("SortType"));
			collRec.setSuretyId(recTemp.getString("SuretyId"));
			rec.add(collRec);
		}
		rq.setCollCancelSynRec(rec);
		return rq;
	}
	
	/**
	 * 多头校验实时接口请求数据
	 * 对象数据转换
	 */
	@Bizlet("转换多头校验实时接口数据")
	public CRMSCollReVerRigRq converCRMSCollReVerRig(DataObject notice)throws JAXBException, MQException
	{
		CRMSCollReVerRigRq rq=new CRMSCollReVerRigRq();
		rq.setSortType(notice.getString("SortType"));
		rq.setIdentifyNo1(notice.getString("IdentifyNo1"));
		rq.setIdentifyNo2(notice.getString("IdentifyNo2"));
		rq.setPrdName(notice.getString("PrdName"));
		rq.setUserNum(notice.getString("UserNum"));
		rq.setOrgNum(notice.getString("OrgNum"));
		rq.setSystemSource(notice.getString("SystemSource"));
		return rq;
	}
	
	/**
	 * 多头校验实时接口返回数据
	 * 对象数据转换
	 */
	@Bizlet("转换多头校验实时接口返回数据")
	public DataObject converCRMSCollReVerRigRs(Object obj)throws JAXBException, MQException{
		DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallCCMSInterface.CcmsInterface", "CRMSCollReVerRigRs");
		requ.setString("SuretyId",((CRMSCollReVerRigRs)obj).SuretyId);
		requ.setString("RetCode",((CRMSCollReVerRigRs)obj).RetCode);
		return requ;
	}
	@Bizlet("单点登录接口数据")
	public CRMSCapCheckRq  converCRMSCapCheck(DataObject notice)throws JAXBException, MQException
	{
		CRMSCapCheckRq rq=new CRMSCapCheckRq();
		rq.setUserId(notice.getString("UserId"));
		rq.setTokenPwd(notice.getString("TokenPwd"));
		return rq;
	}
	
	 /**
	 * TSA自动触发接口
	 * 
	 */
	@Bizlet("TSA自动触发接口")
	public TExTsaAllAaaRq converT24TSA(DataObject notice)throws JAXBException, MQException
	{
		TExTsaAllAaaRq rq=new TExTsaAllAaaRq();
		rq.setFbNo(notice.getString("FbNo"));
		rq.setTsaName(notice.getString("TsaName"));
		rq.setTxnType(notice.getString("TxnType"));
		return rq;
	}
	
}