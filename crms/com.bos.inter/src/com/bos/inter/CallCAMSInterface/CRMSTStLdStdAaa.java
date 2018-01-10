package com.bos.inter.CallCAMSInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.inter.CallBmsInterface.CRMSLoandAppRq;
import com.bos.inter.CallT24Interface.AmountRec;
import com.bos.inter.CallT24Interface.InterestRateRec;
import com.bos.inter.CallT24Interface.TStLdRpmAaaRec;
import com.bos.inter.CallT24Interface.TStLdStdAaaRs;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.jaxb.javabean.CommonRqHdr;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.bos.mq.server.MQPoolHelpUtilImpl;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
//一般贷款发放
public class CRMSTStLdStdAaa extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSTStLdStdAaa(Object obj) {
		this.obj = obj;
	}
	@Bizlet("")
	public void execute() throws Exception {
		
		
		// TODO 自动生成方法存根
		DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallCAMSInterface.CamsInterface", "CRMSTStLdStdAaaRq");
		requ.setString("FtTxnType",((CRMSTStLdStdAaaRq)obj).FtTxnType);
		requ.setString("FBID",((CRMSTStLdStdAaaRq)obj).FBID);
		requ.setString("ContractId",((CRMSTStLdStdAaaRq)obj).ContractId);
		requ.setString("LoanId",((CRMSTStLdStdAaaRq)obj).LoanId);
		requ.setString("LoanAgrNo",((CRMSTStLdStdAaaRq)obj).LoanAgrNo);
		requ.setString("CustomerId",((CRMSTStLdStdAaaRq)obj).CustomerId);
		requ.setString("Currency",((CRMSTStLdStdAaaRq)obj).Currency);
		requ.setString("Category",((CRMSTStLdStdAaaRq)obj).Category);
		requ.setString("ProductCode",((CRMSTStLdStdAaaRq)obj).ProductCode);
		requ.setString("LcDrawNo",((CRMSTStLdStdAaaRq)obj).LcDrawNo==null ? "":((CRMSTStLdStdAaaRq)obj).LcDrawNo);
		requ.setString("DrawdownAccount",((CRMSTStLdStdAaaRq)obj).DrawdownAccount);
		requ.setString("PrinLiqAcct",((CRMSTStLdStdAaaRq)obj).PrinLiqAcct==null ? "":((CRMSTStLdStdAaaRq)obj).PrinLiqAcct);
		requ.setString("IntLiqAcct",((CRMSTStLdStdAaaRq)obj).IntLiqAcct==null ? "":((CRMSTStLdStdAaaRq)obj).IntLiqAcct);
		requ.setString("RecAccount",((CRMSTStLdStdAaaRq)obj).RecAccount==null ? "":((CRMSTStLdStdAaaRq)obj).RecAccount);
		requ.setString("ValueDate",((CRMSTStLdStdAaaRq)obj).ValueDate);
		requ.setString("FinMatDate",((CRMSTStLdStdAaaRq)obj).FinMatDate);
		requ.setString("Tenor",((CRMSTStLdStdAaaRq)obj).Tenor);
		requ.setString("AutoScheds",((CRMSTStLdStdAaaRq)obj).AutoScheds==null ? "":((CRMSTStLdStdAaaRq)obj).AutoScheds);
		requ.setString("DefineScheds",((CRMSTStLdStdAaaRq)obj).DefineScheds==null ? "":((CRMSTStLdStdAaaRq)obj).DefineScheds);
		requ.setString("Restructure",((CRMSTStLdStdAaaRq)obj).Restructure==null ? "":((CRMSTStLdStdAaaRq)obj).Restructure);
		requ.setString("LoanPurpose",((CRMSTStLdStdAaaRq)obj).LoanPurpose==null ? "":((CRMSTStLdStdAaaRq)obj).LoanPurpose);
		requ.setString("UltiIndBen",((CRMSTStLdStdAaaRq)obj).UltiIndBen==null ? "":((CRMSTStLdStdAaaRq)obj).UltiIndBen);
		requ.setString("DdApprNo",((CRMSTStLdStdAaaRq)obj).DdApprNo);
		requ.setString("IntRateType",((CRMSTStLdStdAaaRq)obj).IntRateType==null ? "":((CRMSTStLdStdAaaRq)obj).IntRateType);
		requ.setString("VarRateFlag",((CRMSTStLdStdAaaRq)obj).VarRateFlag==null ? "":((CRMSTStLdStdAaaRq)obj).VarRateFlag);
		requ.setString("InterestBasis",((CRMSTStLdStdAaaRq)obj).InterestBasis==null ? "":((CRMSTStLdStdAaaRq)obj).InterestBasis);
		requ.setString("IntPaymtMethod",((CRMSTStLdStdAaaRq)obj).IntPaymtMethod==null ? "":((CRMSTStLdStdAaaRq)obj).IntPaymtMethod);
		requ.setString("BaseRateKey",((CRMSTStLdStdAaaRq)obj).BaseRateKey==null ? "":((CRMSTStLdStdAaaRq)obj).BaseRateKey);
		requ.setString("SpreadRatePer",((CRMSTStLdStdAaaRq)obj).SpreadRatePer==null ? "":((CRMSTStLdStdAaaRq)obj).SpreadRatePer);
		requ.setString("RateChgFreq",((CRMSTStLdStdAaaRq)obj).RateChgFreq==null ? "":((CRMSTStLdStdAaaRq)obj).RateChgFreq);
		requ.setString("InterestKey",((CRMSTStLdStdAaaRq)obj).InterestKey==null ? "":((CRMSTStLdStdAaaRq)obj).InterestKey);
		requ.setString("InterestSpread",((CRMSTStLdStdAaaRq)obj).InterestSpread==null ? "":((CRMSTStLdStdAaaRq)obj).InterestSpread);
		requ.setString("PenSpread",((CRMSTStLdStdAaaRq)obj).PenSpread==null ? "":((CRMSTStLdStdAaaRq)obj).PenSpread);
		requ.setString("MisusePenSpr",((CRMSTStLdStdAaaRq)obj).MisusePenSpr==null ? "":((CRMSTStLdStdAaaRq)obj).MisusePenSpr);
		requ.setString("ChrgLiqAcct",((CRMSTStLdStdAaaRq)obj).ChrgLiqAcct==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgLiqAcct);
		requ.setString("ChrgCode",((CRMSTStLdStdAaaRq)obj).ChrgCode==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgCode);
		requ.setString("ChrgAmount",((CRMSTStLdStdAaaRq)obj).ChrgAmount==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgAmount);
		requ.setString("ChrgClaimDate",((CRMSTStLdStdAaaRq)obj).ChrgClaimDate==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgClaimDate);
		requ.setString("ChrgBookedOn",((CRMSTStLdStdAaaRq)obj).ChrgBookedOn==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgBookedOn);
		requ.setString("ChrgCapitalise",((CRMSTStLdStdAaaRq)obj).ChrgCapitalise==null ? "":((CRMSTStLdStdAaaRq)obj).ChrgCapitalise);
		requ.setString("TotalCapCharge",((CRMSTStLdStdAaaRq)obj).TotalCapCharge==null ? "":((CRMSTStLdStdAaaRq)obj).TotalCapCharge);
		requ.setString("BusDayDefn",((CRMSTStLdStdAaaRq)obj).BusDayDefn==null ? "":((CRMSTStLdStdAaaRq)obj).BusDayDefn);
		requ.setString("LimitReference",((CRMSTStLdStdAaaRq)obj).LimitReference==null ? "":((CRMSTStLdStdAaaRq)obj).LimitReference);
		requ.setString("ScecuredType",((CRMSTStLdStdAaaRq)obj).ScecuredType);
		requ.setString("CountryRisk",((CRMSTStLdStdAaaRq)obj).CountryRisk==null ? "":((CRMSTStLdStdAaaRq)obj).CountryRisk);
		requ.setString("MisAcctOfficer",((CRMSTStLdStdAaaRq)obj).MisAcctOfficer);
		requ.setString("CorrLiabNo",((CRMSTStLdStdAaaRq)obj).CorrLiabNo==null ? "":((CRMSTStLdStdAaaRq)obj).CorrLiabNo);
		requ.setString("LcAdvId",((CRMSTStLdStdAaaRq)obj).LcAdvId==null ? "":((CRMSTStLdStdAaaRq)obj).LcAdvId);
		requ.setString("CfNumber",((CRMSTStLdStdAaaRq)obj).CfNumber==null ? "":((CRMSTStLdStdAaaRq)obj).CfNumber);
		requ.setString("CommitmentNo",((CRMSTStLdStdAaaRq)obj).CommitmentNo==null ? "":((CRMSTStLdStdAaaRq)obj).CommitmentNo);
		requ.setString("MatureAtSod",((CRMSTStLdStdAaaRq)obj).MatureAtSod==null ? "":((CRMSTStLdStdAaaRq)obj).MatureAtSod);
		requ.setString("DealerDesk",((CRMSTStLdStdAaaRq)obj).DealerDesk==null ? "":((CRMSTStLdStdAaaRq)obj).DealerDesk);
		requ.setString("CustomerRef",((CRMSTStLdStdAaaRq)obj).CustomerRef==null ? "":((CRMSTStLdStdAaaRq)obj).CustomerRef);
		requ.setString("CustRemarks",((CRMSTStLdStdAaaRq)obj).CustRemarks==null ? "":((CRMSTStLdStdAaaRq)obj).CustRemarks);
		requ.setString("OurRemarks",((CRMSTStLdStdAaaRq)obj).OurRemarks==null ? "":((CRMSTStLdStdAaaRq)obj).OurRemarks);
		requ.setString("AloneMergeInd",((CRMSTStLdStdAaaRq)obj).AloneMergeInd==null ? "":((CRMSTStLdStdAaaRq)obj).AloneMergeInd);
		requ.setString("FowWardBackward",((CRMSTStLdStdAaaRq)obj).FowWardBackward==null ? "":((CRMSTStLdStdAaaRq)obj).FowWardBackward);
		requ.setString("BaseDateKey",((CRMSTStLdStdAaaRq)obj).BaseDateKey==null ? "":((CRMSTStLdStdAaaRq)obj).BaseDateKey);
		 
		 CommonRqHdr comm=((CRMSTStLdStdAaaRq)obj).CommonRqHdr;
		 DataObject commhd = DataFactory.INSTANCE.create("com.bos.CRMSInterface.CommInterface", "CommonRqHdr");
		 commhd.setString("CompanyCode", comm.companyCode);
		 requ.setDataObject("CommonRqHdr", commhd);
		 
		 
		if (((CRMSTStLdStdAaaRq)obj).AmountRec!=null)
		{
		List<DataObject> AmtData = new ArrayList<DataObject>();
		List<AmountRec> amount =((CRMSTStLdStdAaaRq)obj).AmountRec;
		for(int j=0;j<amount.size();j++){
			DataObject amtInfo = DataFactory.INSTANCE.create("com.bos.inter.CallCAMSInterface.CamsInterface", "AmountRec");
			AmountRec amtRec=(AmountRec)amount.get(j);
			amtInfo.setString("Amount", amtRec.Amount);
			AmtData.add((DataObject)amtInfo);
		}
		requ.setList("AmountRec", AmtData);
		}
		
		if (((CRMSTStLdStdAaaRq)obj).InterestRateRec!=null)
		{
		List<DataObject> rateData = new ArrayList<DataObject>();
		List<InterestRateRec> intRate =((CRMSTStLdStdAaaRq)obj).InterestRateRec;
		for(int j=0;j<intRate.size();j++){
			DataObject rate = DataFactory.INSTANCE.create("com.bos.inter.CallCAMSInterface.CamsInterface", "InterestRateRec");
			InterestRateRec intRec=(InterestRateRec)intRate.get(j);
			rate.setString("InterestRate", intRec.InterestRate);
			rateData.add((DataObject)rate);
		}
		requ.setList("InterestRateRec", rateData);
		}
		
		if (((CRMSTStLdStdAaaRq)obj).TStLdRpmAaaRec!=null)
		{
		List<DataObject> myData = new ArrayList<DataObject>();
		List<TStLdRpmAaaRec> rec =((CRMSTStLdStdAaaRq)obj).TStLdRpmAaaRec;
		for(int i=0;i<rec.size();i++){
			DataObject recInfo = DataFactory.INSTANCE.create("com.bos.inter.CallCAMSInterface.CamsInterface", "TStLdRpmAaaRec");
			TStLdRpmAaaRec temp=(TStLdRpmAaaRec)rec.get(i);
			recInfo.setString("SchType",temp.SchType==null?null:temp.SchType);
			recInfo.setString("BeginDt",temp.BeginDt==null?null:temp.BeginDt);
			recInfo.setString("LsdAmount",temp.LsdAmount==null?null:temp.LsdAmount);
			recInfo.setString("Rate",temp.Rate==null?null:temp.Rate);
			myData.add((DataObject)recInfo);
		}
		requ.setList("TStLdRpmAaaRec", myData);
		}
		
		CRMSTStLdStdAaaRs rs=new CRMSTStLdStdAaaRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallCAMSInterface.CamsMaintain";
		// 逻辑流名称
		String operationName = "CRMSTStLdStdAaa";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[1];
		params[0] =requ;
		BaseMQRequest bmr = new BaseMQRequest();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			TStLdStdAaaRs trs = (TStLdStdAaaRs) result[0];
			trs=(TStLdStdAaaRs) result[0];
			Map<String,String> objs = (Map<String,String>) result[1];
			String reCode=objs.get("StatusCode");
			String reMsg=objs.get("ServerStatusCode");
			if(reCode=="000000"){
				rs.setContractNo(trs.ContractNo);
				rs.setCurrNo(trs.CurrNo);
				rs.setDateTime_1(trs.DateTime_1);
				rs.setInterestRate(trs.InterestRate);
				rs.setPeRate(trs.PeRate);
				rs.setPsRate(trs.PsRate);
				rs.setRecordStatus(trs.RecordStatus);
				rs.setStatus(trs.Status);
				rs.setTotInterestAmt(trs.TotInterestAmt);
				rs.setCommonRsHdr(this.success());
			}else{
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			
			bf.camsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.camsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

}
