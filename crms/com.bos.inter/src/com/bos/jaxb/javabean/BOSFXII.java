package com.bos.jaxb.javabean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.csm.inteface.ecifJava.EcifCustRq;
import com.bos.csm.inteface.ecifJava.EcifCustRs;
import com.bos.csm.inteface.ecifJava.EcifCustUpdateRq;
import com.bos.csm.inteface.ecifJava.EcifCustUpdateRs;
import com.bos.csm.inteface.ecifJava.EcifNaturalUpdateRq;
import com.bos.csm.inteface.ecifJava.EcifNaturalUpdateRs;
import com.bos.inter.CallBmsInterface.CRMSCollInfoInqRq;
import com.bos.inter.CallBmsInterface.CRMSCollInfoInqRs;
import com.bos.inter.CallBmsInterface.CRMSContrListInqRq;
import com.bos.inter.CallBmsInterface.CRMSContrListInqRs;
import com.bos.inter.CallBmsInterface.CRMSCreRestInsetRq;
import com.bos.inter.CallBmsInterface.CRMSCreRestInsetRs;
import com.bos.inter.CallBmsInterface.CRMSLoanBackNotRq;
import com.bos.inter.CallBmsInterface.CRMSLoanBackNotRs;
import com.bos.inter.CallBmsInterface.CRMSLoanRevNotRq;
import com.bos.inter.CallBmsInterface.CRMSLoanRevNotRs;
import com.bos.inter.CallBmsInterface.CRMSLoandAppRq;
import com.bos.inter.CallBmsInterface.CRMSLoandAppRs;
import com.bos.inter.CallCAMSInterface.CRMSLoanStaInqRq;
import com.bos.inter.CallCAMSInterface.CRMSLoanStaInqRs;
import com.bos.inter.CallCAMSInterface.CRMSTStLdStdAaaRq;
import com.bos.inter.CallCAMSInterface.CRMSTStLdStdAaaRs;
import com.bos.inter.CallCCMSInterface.CRMSCollCashSynRq;
import com.bos.inter.CallCCMSInterface.CRMSCollCashSynRs;
import com.bos.inter.CallCCMSInterface.CRMSCollInBackRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInBackRigRs;
import com.bos.inter.CallCCMSInterface.CRMSCollInRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInRigRs;
import com.bos.inter.CallCCMSInterface.CRMSCollOutRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollOutRigRs;
import com.bos.inter.CallCCMSInterface.CRMSCollReVerRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollReVerRigRs;
import com.bos.inter.CallCCMSInterface.CRMSCollSynRq;
import com.bos.inter.CallCCMSInterface.CRMSCollSynRs;
import com.bos.inter.CallCRMInterface.CRMSCapCheckRq;
import com.bos.inter.CallCRMInterface.CRMSCapCheckRs;
import com.bos.inter.CallCRMInterface.CRMSCreEncKeyRq;
import com.bos.inter.CallCRMInterface.CRMSCreEncKeyRs;
import com.bos.inter.CallScfInterface.CRMSCountInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSCountInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSCustInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSCustInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSFactInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSFactInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSGuarConInqRq;
import com.bos.inter.CallScfInterface.CRMSGuarConInqRs;
import com.bos.inter.CallScfInterface.CRMSInsuComInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSInsuComInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSLoanAppCancelRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppCancelRs;
import com.bos.inter.CallScfInterface.CRMSLoanAppRevRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppRevRs;
import com.bos.inter.CallScfInterface.CRMSLoanAppRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppRs;
import com.bos.inter.CallScfInterface.CRMSLoanBackRq;
import com.bos.inter.CallScfInterface.CRMSLoanBackRs;
import com.bos.inter.CallScfInterface.CRMSLoanBalInqRq;
import com.bos.inter.CallScfInterface.CRMSLoanBalInqRs;
import com.bos.inter.CallScfInterface.CRMSLoanConfirRq;
import com.bos.inter.CallScfInterface.CRMSLoanConfirRs;
import com.bos.inter.CallScfInterface.CRMSRegulInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSRegulInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRs;
import com.bos.inter.CallScfInterface.CRMSWareInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSWareInfoInqRs;
import com.bos.inter.CallT24Interface.LimitInfoBean;
import com.bos.inter.CallT24Interface.LimitInfoRs;
import com.bos.inter.CallT24Interface.LimitModBean;
import com.bos.inter.CallT24Interface.LimitModRs;
import com.bos.inter.CallT24Interface.TDpAcctAllInqRq;
import com.bos.inter.CallT24Interface.TDpAcctAllInqRs;
import com.bos.inter.CallT24Interface.TExCurrencyAllInqRq;
import com.bos.inter.CallT24Interface.TExCurrencyAllInqRs;
import com.bos.inter.CallT24Interface.TExTsaAllAaaRq;
import com.bos.inter.CallT24Interface.TExTsaAllAaaRs;
import com.bos.inter.CallT24Interface.TNbosIlcOpenHLDBean;
import com.bos.inter.CallT24Interface.TNbosIlcOpenHLDRs;
import com.bos.inter.CallT24Interface.TStLdStdAaaRq;
import com.bos.inter.CallT24Interface.TStLdStdAaaRs;

@XmlRootElement(name = "BOSFXII")
public class BOSFXII implements Serializable {

	private static final long serialVersionUID = -7885264454080737832L;
	
	/*
	 * 声明T24请求报文体，每一个交易都用@XmlElement声明，中间用“,”分割，
	 * 属性name为交易名称，type为交易对应的javabean
	 * 注：交易名称和javabean不能重复，否则无法识别需要解析为什么形式
	 * */
	@XmlElements({
		//@XmlElement(name = "TNbosGenInPutRq", type = CommonLoan.class),//一般贷款发放     
		//@XmlElement(name = "TNbosMglInpRq", type = MortgateBean.class),//法人按揭贷款发放 
		//@XmlElement(name = "TNbosFacInpRq", type = CommonLoan.class),//保理款发放
		//@XmlElement(name = "TNbosGenExtnRq", type = LoanExtendsBean.class),//贷款展期  
		//@XmlElement(name = "TNbosGenAmdRq", type = LoanUpdateBean.class),//贷款修改  
		//@XmlElement(name = "TNbosSyndRq", type = BankGroupBean.class),//银团基本信息录入 
		//@XmlElement(name = "TNbosDetailRq", type = BankGrCreditBean.class),//银团授信维护     
		//@XmlElement(name = "TNbosDrawRq", type = BankGrPayoutBean.class),//银团放款及维护   
		//@XmlElement(name = "TNbosLcyGteeIssueRq", type = LcGuaranteeBean.class),//本币保函签发     
		//@XmlElement(name = "TNbosLcyGteeAmendRq", type = GuarteePaperBean.class),//本币保函维护     
		//@XmlElement(name = "TNbosBaAmendRq", type = CommonLoan.class),//承兑信息修改     
		//@XmlElement(name = "TNbosELInpRq", type = TrusteeBean.class),//委托贷款发放     
		//@XmlElement(name = "TNbosELAmdRq", type = TrusteeUpdateBean.class),//委托贷款修改     
		//@XmlElement(name = "TNbosELextnRq", type = TrusteeExtendBean.class),//委托贷款展期     
		//@XmlElement(name = "TNbosBtypeRq", type = TNbosBtypeBean.class),//B类承诺函签发    
		//@XmlElement(name = "TNbosBtypeAmendRq", type = TNbosBtypeAmendR.class),//B类承诺函维护    
		//@XmlElement(name = "TDpAcctOvdftModRq", type = OverAccountBean.class),//法人透支账户维护 
		//@XmlElement(name = "TDpAcctAdiAaaRq", type = CommonLoan.class),//法人透支账户结息频率设置
		//@XmlElement(name = "TDpAcctCapAaaRq", type = OverAccountSeqSetBean.class),//法人透支账户结息频率设置
		@XmlElement(name = "TExLimitAllAddRq", type = LimitInfoBean.class),//额度信息建立 TExLimitAllMod 
		@XmlElement(name = "TExLimitAllModRq", type = LimitModBean.class),//额度信息
		//@XmlElement(name = "TNbosGenInputHLDRq", type = ForeignLoan.class),//外币一般贷款     
		//@XmlElement(name = "TNbosGenAmdHLDRq", type = ForeignUpdateBean.class),//外币一般贷款修改        
		//@XmlElement(name = "TStLdRpmamtAaaRq", type = TStLdRpmamtAaaRq.class),//外币一般贷款提前还款    
		//@XmlElement(name = "TNbosGenFcyInputHLDRq", type = TNbosGenFcyInputHLDRq.class),//外币自行代付            
		//@XmlElement(name = "TNbosDisFcyInputHLDRq", type = TNbosDisFcyInputHLDRq.class),//是福费庭                
		//@XmlElement(name = "TNbosGenDfInputHLDRq", type = TNbosGenDfInputHLDRq.class),//进口海外代付            
		//@XmlElement(name = "TNbosGenEdfInputHLDRq", type = GenEdfInputHLDBean.class),//出口海外代付            
		//@XmlElement(name = "TNbosGteeIssueHLDRq", type = TNbosGteeIssueHLD.class),//保函开立                
		//@XmlElement(name = "TNbosGteeAmendHLDRq", type = TNbosGteeAmendHLDBean.class),//保函注销                
		//@XmlElement(name = "TNbosGenDfAmdHLDRq", type = TNbosGenDfAmdHLDRq.class),//进口海外代付修改        
		//@XmlElement(name = "TNbosGenDfRpmHLDRq", type = CommonLoan.class),//进口海外代付提前还款    
		//@XmlElement(name = "TNbosGenEDfAmdHLDRq", type = GenEDfAmdHLDRq.class),//出口海外修改
		//@XmlElement(name = "TNbosGenFcyAmdHLDRq", type = TNbosGenFcyAmdHLDRq.class),//自行代付修改
		//@XmlElement(name = "TNbosGenFcyRpmHLDRq", type = CommonLoan.class)//福费庭提前还款
		//@XmlElement(name = "TStLdStatusAaaRq", type = TStLdStatusAaaRq.class),//通知
		@XmlElement(name = "TStLdStdAaaRq", type = TStLdStdAaaRq.class),//一般贷款（现金管理穿透）
		@XmlElement(name = "TNbosIlcOpenHLDRq", type = TNbosIlcOpenHLDBean.class),//信用证	
		@XmlElement(name = "TDpAcctAllInqRq", type = TDpAcctAllInqRq.class),//账户信息查询
		@XmlElement(name = "TExTsaAllAaaRq", type = TExTsaAllAaaRq.class),//TSA自动触发接口
		@XmlElement(name = "TExCurrencyAllInqRq", type = TExCurrencyAllInqRq.class)//币种汇率
		
	})
	public SuperBosfxRq t24BosfxRq;
	
	/*
	 * 声明T24相应报文体，每一个交易都用@XmlElement声明，中间用“,”分割，
	 * 属性name为交易名称，type为交易对应的javabean
	 * 注：交易名称和javabean不能重复，否则无法识别需要解析为什么形式
	 * */
	@XmlElements({
		//@XmlElement(name = "TNbosGenInPutRs", type = CommonLoanRs.class),//一般贷款发放            
		//@XmlElement(name = "TNbosMglInpRs", type = MortgateRs.class),//法人按揭贷款发放        
		//@XmlElement(name = "TNbosFacInpRs", type = CommonLoanRs.class),//保理款发放              
		//@XmlElement(name = "TNbosGenExtnRs", type = LoanExtendsRs.class),//贷款展期                
		//@XmlElement(name = "TNbosGenAmdRs", type = LoanUpateRs.class),//贷款修改                
		//@XmlElement(name = "TNbosSyndRs", type = BankGroupRs.class),//银团基本信息录入        
		//@XmlElement(name = "TNbosDetailRs", type = BankGrCreditRs.class),//银团授信维护            
		//@XmlElement(name = "TNbosDrawRs", type = BankGrPayoutRs.class),//银团放款及维护          
		//@XmlElement(name = "TNbosLcyGteeIssueRs", type = LcGuaranteeRs.class),//本币保函签发            
		//@XmlElement(name = "TNbosLcyGteeAmendRs", type = GuarteePaperRs.class),//本币保函维护            
		//@XmlElement(name = "TNbosBaAmendRs", type = CommonLoanRs.class),//承兑信息修改            
		//@XmlElement(name = "TNbosELInpRs", type = TrusteePayoutRs.class),//委托贷款发放     
		//@XmlElement(name = "TNbosELAmdRs", type = TrusteeUpateRs.class),//委托贷款修改     
		//@XmlElement(name = "TNbosELextnRs", type = TrusteeExtendsRs.class),//委托贷款展期     
		//@XmlElement(name = "TNbosBtypeRs", type = TNbosBtypeRs.class),//B类承诺函签发    
		//@XmlElement(name = "TNbosBtypeAmendRs", type = TNbosBtypeAmendRs.class),//B类承诺函维护    
		//@XmlElement(name = "TDpAcctOvdftModRs", type = OverAccountRs.class),//法人透支账户维护 
		//@XmlElement(name = "TDpAcctAdiAaaRs", type = CommonLoanRs.class),//法人透支账户结息频率设置
		//@XmlElement(name = "TDpAcctCapAaaRs", type = OverAccountSeqSetRs.class),//法人透支账户结息频率设置
		@XmlElement(name = "TExLimitAllAddRs", type = LimitInfoRs.class),//额度信息建立LimitModRs 
		@XmlElement(name = "TExLimitAllModRs", type = LimitModRs.class),//额度信息
		//@XmlElement(name = "TNbosGenInputHLDRs", type = ForeignLoanRs.class),//外币一般贷款     
		//@XmlElement(name = "TNbosGenAmdHLDRs", type = ForeignUpdateRs.class),//外币一般贷款修改 
		//@XmlElement(name = "TStLdRpmamtAaaRs", type = TStLdRpmamtAaaRs.class),//外币一般贷款提前还款     
		//@XmlElement(name = "TNbosGenFcyInputHLDRs", type = TNbosGenFcyInputHLDRs.class),//外币自行代付     
		//@XmlElement(name = "TNbosDisFcyInputHLDRs", type = TNbosDisFcyInputHLDRs.class),//是福费庭  
		//@XmlElement(name = "TNbosGenDfInputHLDRs", type = TNbosGenDfInputHLDRs.class),//进口海外代付     
		//@XmlElement(name = "TNbosGenEdfInputHLDRs", type = GenEdfInputHldRs.class),//出口海外代付     
		//@XmlElement(name = "TNbosGteeIssueHLDRs", type = TNbosGteeIssueHLDRqR.class),//保函开立  
		//@XmlElement(name = "TNbosGteeAmendHLDRs", type = TNbosGteeAmendHLDRs.class),//保函注销  
		//@XmlElement(name = "TNbosGenDfAmdHLDRs", type = TNbosGenDfAmdHLDRs.class),//进口海外代付修改 
		//@XmlElement(name = "TNbosGenDfRpmHLDRs", type = CommonLoanRs.class),//进口海外代付提前还款    
		//@XmlElement(name = "TNbosGenEDfAmdHLDRs", type = GenEDfAmdHLDRs.class),//出口海外修改     
		//@XmlElement(name = "TNbosGenFcyAmdHLDRs", type = TNbosGenFcyAmdHLDRs.class),//自行代付修改     
		//@XmlElement(name = "TNbosGenFcyRpmHLDRs", type = CommonLoanRs.class)//福费庭提前还款
		//@XmlElement(name = "TStLdStatusAaaRs", type = TStLdStatusAaaRs.class),//通知
		@XmlElement(name = "TStLdStdAaaRs", type = TStLdStdAaaRs.class),//一般贷款（现金管理穿透）
		@XmlElement(name = "TNbosIlcOpenHLDRs", type = TNbosIlcOpenHLDRs.class),//信用证
		@XmlElement(name = "TDpAcctAllInqRs", type = TDpAcctAllInqRs.class),//账户信息查询
		@XmlElement(name = "TExTsaAllAaaRs", type = TExTsaAllAaaRs.class),//TSA自动触发接口
		@XmlElement(name = "TExCurrencyAllInqRs", type = TExCurrencyAllInqRs.class)//币种汇率
		
	})
	public SuperBosfxRs t24BosfxRs;
	

	//SCF供应链系统交易配置
	@XmlElements({
		@XmlElement(name="CRMSCustInfoInqRq", type=CRMSCustInfoInqRq.class), //授信客户信息查询 
		@XmlElement(name="CRMSInsuComInfoInqRq", type=CRMSInsuComInfoInqRq.class),//保险公司信息查询 
		@XmlElement(name="CRMSRegulInfoInqRq", type=CRMSRegulInfoInqRq.class),//监管机构信息查询 
		@XmlElement(name="CRMSWareInfoInqRq", type=CRMSWareInfoInqRq.class),//仓库信息查询 
		@XmlElement(name="CRMSFactInfoInqRq", type=CRMSFactInfoInqRq.class),//保理商信息查询
		@XmlElement(name="CRMSCountInfoInqRq", type=CRMSCountInfoInqRq.class),//交易对手信息查询
		@XmlElement(name="CRMSReplyInfoInqRq", type=CRMSReplyInfoInqRq.class),//批复信息查询 
		@XmlElement(name="CRMSGuarConInqRq", type=CRMSGuarConInqRq.class),//担保合同查询
		@XmlElement(name="CRMSLoanAppRq", type=CRMSLoanAppRq.class),//放款申请
		@XmlElement(name="CRMSCollSynRq", type=CRMSCollSynRq.class),//押品同步
		@XmlElement(name="CRMSCollCashSynRq", type=CRMSCollCashSynRq.class),//现金等价物同步
		@XmlElement(name="CRMSLoanConfirRq", type=CRMSLoanConfirRq.class),//放款确认通知
		@XmlElement(name="CRMSLoanBackRq", type=CRMSLoanBackRq.class),//放款回退通知
		@XmlElement(name="CRMSLoanAppCancelRq", type=CRMSLoanAppCancelRq.class),//放款申请取消
		@XmlElement(name="CRMSLoanAppRevRq", type=CRMSLoanAppRevRq.class),//放款申请撤销
		@XmlElement(name="CRMSLoanBalInqRq", type=CRMSLoanBalInqRq.class),//放款余额查询 
	})
	public SuperBosfxRq scfBosfxRq;
	@XmlElements({
		@XmlElement(name="CRMSCustInfoInqRs", type=CRMSCustInfoInqRs.class),//授信客户信息查询 
		@XmlElement(name="CRMSInsuComInfoInqRs", type=CRMSInsuComInfoInqRs.class),//保险公司信息查询 
		@XmlElement(name="CRMSRegulInfoInqRs", type=CRMSRegulInfoInqRs.class),//监管机构信息查询 
		@XmlElement(name="CRMSWareInfoInqRs", type=CRMSWareInfoInqRs.class),//仓库信息查询 
		@XmlElement(name="CRMSFactInfoInqRs", type=CRMSFactInfoInqRs.class),//保理商信息查询
		@XmlElement(name="CRMSCountInfoInqRs", type=CRMSCountInfoInqRs.class),//交易对手信息查询
		@XmlElement(name="CRMSReplyInfoInqRs", type=CRMSReplyInfoInqRs.class),//批复信息查询 
		@XmlElement(name="CRMSGuarConInqRs", type=CRMSGuarConInqRs.class),//担保合同查询
		@XmlElement(name="CRMSLoanAppRs", type=CRMSLoanAppRs.class),//放款申请
		@XmlElement(name="CRMSCollSynRs", type=CRMSCollSynRs.class),//押品同步
		@XmlElement(name="CRMSCollCashSynRs", type=CRMSCollCashSynRs.class),//现金等价物同步
		@XmlElement(name="CRMSLoanConfirRs", type=CRMSLoanConfirRs.class),//放款确认通知
		@XmlElement(name="CRMSLoanBackRs", type=CRMSLoanBackRs.class),//放款回退通知
		@XmlElement(name="CRMSLoanAppCancelRs", type=CRMSLoanAppCancelRs.class),//放款申请取消
		@XmlElement(name="CRMSLoanAppRevRs", type=CRMSLoanAppRevRs.class),//放款申请撤销
		@XmlElement(name="CRMSLoanBalInqRs", type=CRMSLoanBalInqRs.class),//放款余额查询 
	})
	public SuperBosfxRs scfBosfxRs;
//	现金管理平台
	@XmlElements({
		@XmlElement(name="CRMSTStLdStdAaaRq", type=CRMSTStLdStdAaaRq.class), //一般贷款发放
		@XmlElement(name="CRMSLoanStaInqRq", type=CRMSLoanStaInqRq.class), //放款状态查询
	})
	public SuperBosfxRq camsBosfxRq;
	@XmlElements({
		@XmlElement(name="CRMSTStLdStdAaaRs", type=CRMSTStLdStdAaaRs.class), //一般贷款发放
		@XmlElement(name="CRMSLoanStaInqRs", type=CRMSLoanStaInqRs.class), //放款状态查询
	})
	public SuperBosfxRs camsBosfxRs;
	// CRM系统交易配置
	@XmlElements({
		@XmlElement(name="CRMSCreEncKeyRq", type=CRMSCreEncKeyRq.class), //单点登录
		@XmlElement(name="CRMSCapCheckRq", type=CRMSCapCheckRq.class), //身份验证
	})
	public SuperBosfxRq crmBosfxRq;
	@XmlElements({
		@XmlElement(name="CRMSCreEncKeyRs", type=CRMSCreEncKeyRs.class), //单点登录
		@XmlElement(name="CRMSCapCheckRs", type=CRMSCapCheckRs.class), //身份验证
	})
	public SuperBosfxRs crmBosfxRs;

	//ECIF系统交易配置
	@XmlElements({
		@XmlElement(name="C201OrgNtlCusEcifRq", type=EcifNaturalUpdateRq.class),//自然人客户维护-CRMS
		@XmlElement(name="C201OrgCusInqRq", type=EcifCustRq.class),//按组织机构代码证或客户号查询客户信息
		@XmlElement(name="C201OrgCusEcifRq", type=EcifCustUpdateRq.class)//对公客户维护
	})
	public SuperBosfxRq ecifBosfxRq;
	@XmlElements({
		@XmlElement(name="C201OrgNtlCusEcifRs", type=EcifNaturalUpdateRs.class),//自然人客户维护-CRMS
		@XmlElement(name="C201OrgCusInqRs", type=EcifCustRs.class),//按组织机构代码证或客户号查询客户信息
		@XmlElement(name="C201OrgCusEcifRs", type=EcifCustUpdateRs.class)//对公客户维护
	})
	public SuperBosfxRs ecifBosfxRs;
	
	//票据系统交易配置
	@XmlElements({
	@XmlElement(name="CRMSContrListInqRq", type=CRMSContrListInqRq.class),//合同清单查询
	@XmlElement(name="CRMSCollInfoInqRq", type=CRMSCollInfoInqRq.class),//押品信息查询
	@XmlElement(name="CRMSLoandAppRq", type=CRMSLoandAppRq.class),//放款申请 
	@XmlElement(name="CRMSLoanRevNotRq", type=CRMSLoanRevNotRq.class),//放款审核通知
	@XmlElement(name="CRMSLoanBackNotRq", type=CRMSLoanBackNotRq.class),//放款回退通知
	@XmlElement(name="CRMSCreRestInsetRq", type=CRMSCreRestInsetRq.class),//额度恢复 
	})
	public SuperBosfxRq	bmsBosfxRq;
	
	@XmlElements({
	@XmlElement(name="CRMSContrListInqRs", type=CRMSContrListInqRs.class),//合同清单查询
	@XmlElement(name="CRMSCollInfoInqRs", type=CRMSCollInfoInqRs.class),//押品信息查询
	@XmlElement(name="CRMSLoandAppRs", type=CRMSLoandAppRs.class),//放款申请 
	@XmlElement(name="CRMSLoanRevNotRs", type=CRMSLoanRevNotRs.class),//放款审核通知
	@XmlElement(name="CRMSLoanBackNotRs", type=CRMSLoanBackNotRs.class),//放款回退通知 
	@XmlElement(name="CRMSCreRestInsetRs", type=CRMSCreRestInsetRs.class),//额度恢复
	})
	public SuperBosfxRs bmsBosfxRs;
	
	//CCMS押品系统交易配置
	@XmlElements({
		@XmlElement(name="CRMSCollInRigRq", type=CRMSCollInRigRq.class),//押品入库
		@XmlElement(name="CRMSCollOutRigRq", type=CRMSCollOutRigRq.class),//押品出库
		@XmlElement(name="CRMSCollInBackRigRq", type=CRMSCollInBackRigRq.class),//押品出入库撤销 
		@XmlElement(name="CRMSCollReVerRigRq", type=CRMSCollReVerRigRq.class),//多头校验
	})
	public SuperBosfxRq ccmsBosfxRq;
	@XmlElements({
		@XmlElement(name="CRMSCollInRigRs", type=CRMSCollInRigRs.class),//押品入库
		@XmlElement(name="CRMSCollOutRigRs", type=CRMSCollOutRigRs.class),//押品出库
		@XmlElement(name="CRMSCollInBackRigRs", type=CRMSCollInBackRigRs.class),//押品出入库撤销 
		@XmlElement(name="CRMSCollReVerRigRs", type=CRMSCollReVerRigRs.class),//多头校验
	})
	public SuperBosfxRs ccmsBosfxRs;
	

	@Override
	public String toString() {
		return "BOSFXII [t24BosfxRq=" + t24BosfxRq + ",t24BosfxRs="+t24BosfxRs+"]";
	}
}