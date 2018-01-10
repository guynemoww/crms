package com.bos.inter.CallCAMSInterface;
/**
 * @author chenhuan
 */


import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.inter.CallT24Interface.AmountRec;
import com.bos.inter.CallT24Interface.InterestRateRec;
import com.bos.inter.CallT24Interface.TStLdRpmAaaRec;
import com.bos.jaxb.javabean.SuperBosfxRq;
//一般贷款发放
public class CRMSTStLdStdAaaRq extends SuperBosfxRq{
	@XmlElement(name="FtTxnType")
	public String	FtTxnType	;         		    //	本地交易类型
    @XmlElement(name="FBID")
	public String	FBID	;    		           //	业务类型
    @XmlElement(name="ContractId")
	public String	ContractId	;  					//贷款合同号
    @XmlElement(name="LoanId")
	public String	LoanId	;  					//放款申请编号
    @XmlElement(name="LoanAgrNo")
	public String LoanAgrNo;                      //贷款纸质合同号
    @XmlElement(name="CustomerId")
	public String	CustomerId	;         		 //	客户号
    @XmlElement(name="Currency")
	public String	Currency	;    		    //	币种
    @XmlElement(name="Category")
	public String Category;                    //门类代码
    @XmlElement(name="ProductCode")
	public String	ProductCode	;         	  //产品代码
    @XmlElement(name="LcDrawNo") 
	public String	LcDrawNo	;    		 //	信用证放款编号
    @XmlElement(name="AmountRec")
	public List<AmountRec> AmountRec;                 
    @XmlElement(name="DrawdownAccount")
	public String	DrawdownAccount	;      //放款账户
    @XmlElement(name="PrinLiqAcct")
	public String	PrinLiqAcct	;    	  //本金还款账户
    @XmlElement(name="IntLiqAcct")
	public String IntLiqAcct;            //利息还款账户
    @XmlElement(name="RecAccount")
	public String	RecAccount	;       //收款账户
    @XmlElement(name="ValueDate") 
	public String	ValueDate	;    	//	起息日
    @XmlElement(name="FinMatDate")
	public String FinMatDate;           // 到期日
    @XmlElement(name="Tenor")
	public String	Tenor	;           //期限
    @XmlElement(name="AutoScheds")
	public String	AutoScheds	;    	//自动生成还款计划
    @XmlElement(name="DefineScheds")
	public String DefineScheds;          //自定义还款计划
    @XmlElement(name="Restructure")
	public String Restructure;            //借新还款原放款号
    @XmlElement(name="LoanPurpose")
	public String	LoanPurpose	;         //贷款用途
    @XmlElement(name="UltiIndBen")
	public String	UltiIndBen	;    	  //资金流向行业
    @XmlElement(name="DdApprNo")
	public String DdApprNo;               //放款核准单号
    @XmlElement(name="IntRateType")
	public String	IntRateType	;          //利率类型
    @XmlElement(name="VarRateFlag") 
	public String	VarRateFlag	;    	    //	是否可变利率
    @XmlElement(name="InterestBasis")
	public String InterestBasis;           // 利率基础
    @XmlElement(name="IntPaymtMethod")
	public String	IntPaymtMethod	;      //利息收取方式
    @XmlElement(name="BaseRateKey")
	public String	BaseRateKey	;    	  //基准利率代码
    @XmlElement(name="SpreadRatePer")
	public String SpreadRatePer;          //上下浮动百分比
    @XmlElement(name="RateChgFreq")
	public String	RateChgFreq	;         //利率更新频率
    @XmlElement(name="InterestKey")
	public String	InterestKey	;    	  //基础利率值
    @XmlElement(name="InterestSpread")
	public String InterestSpread;         //上下浮率
    @XmlElement(name="InterestRateRec")
	public List<InterestRateRec> InterestRateRec;           //生效利率
    @XmlElement(name="PenSpread") 
	public String	PenSpread	;    	   //	逾期罚息上浮百分比
    @XmlElement(name="MisusePenSpr")
	public String MisusePenSpr;           // 挪用罚息上浮百分比
    @XmlElement(name="ChrgLiqAcct")
	public String	ChrgLiqAcct	;         //费用还款账户
    @XmlElement(name="ChrgCode")
	public String	ChrgCode	;    	  //费用代码
    @XmlElement(name="ChrgAmount")
	public String ChrgAmount;          //费用金额
    @XmlElement(name="ChrgClaimDate")
	public String	ChrgClaimDate	;   //费用收取日
    @XmlElement(name="ChrgBookedOn") 
	public String	ChrgBookedOn	;    //费用收取记账日
    @XmlElement(name="ChrgCapitalise")
	public String ChrgCapitalise;          // 费用是否计复利
    @XmlElement(name="TotalCapCharge")
	public String	TotalCapCharge	;         //计复利的费用总额
    @XmlElement(name="BusDayDefn")
	public String	BusDayDefn	;    	  //工作日国别
    @XmlElement(name="LimitReference")
	public String LimitReference;          //额度编号
    @XmlElement(name="ScecuredType")
	public String	ScecuredType	;    	  //担保类型
    @XmlElement(name="CountryRisk")
	public String CountryRisk;           //国别
    @XmlElement(name="MisAcctOfficer")
	public String	MisAcctOfficer	;   //客户经理
    @XmlElement(name="CorrLiabNo") 
	public String	CorrLiabNo	;    //债务编号
    @XmlElement(name="LcAdvId")
	public String LcAdvId;          //信用证通知
    @XmlElement(name="CfNumber")
	public String	CfNumber	;         //综合授信协议号
    @XmlElement(name="CommitmentNo")
	public String	CommitmentNo	;    	  //承诺费编号
    @XmlElement(name="MatureAtSod")
	public String MatureAtSod;          //是否日初批处理
    @XmlElement(name="DealerDesk")
	public String DealerDesk;          //交易柜台
    @XmlElement(name="CustomerRef")
	public String	CustomerRef	;    	  //通知单长期备注
    @XmlElement(name="CustRemarks")
	public String CustRemarks;           //通知单临时备注
    @XmlElement(name="OurRemarks")
	public String OurRemarks;           //内部备注
    @XmlElement(name="AloneMergeInd")
	public String	AloneMergeInd	;   //合同或单独打印客户备注
    @XmlElement(name="FowWardBackward") 
	public String	FowWardBackward	;    //前置后置标志
    @XmlElement(name="BaseDateKey")
	public String BaseDateKey;          //基准日期值
    @XmlElement(name="TStLdRpmAaaRec")
	public List<TStLdRpmAaaRec>	TStLdRpmAaaRec	;
    @Override
	public String toString() {
		return "CRMSTStLdStdAaaRq [FtTxnType=" + FtTxnType + ", FBID="
				+ FBID + ", LoanAgrNo=" + LoanAgrNo + ", CustomerId="
				+ CustomerId + ",Currency=" + Currency + ", Category="
				+ Category + ", ProductCode=" + ProductCode + ",LcDrawNo=" + LcDrawNo + ", AmountRec="
				+ AmountRec + ", DrawdownAccount=" + DrawdownAccount + ",PrinLiqAcct=" + PrinLiqAcct + ", IntLiqAcct="
				+ IntLiqAcct + ", RecAccount=" + RecAccount + ",ValueDate=" + ValueDate + ", FinMatDate="
				+ FinMatDate + ", Tenor=" + Tenor + ",AutoScheds=" + AutoScheds + ", DefineScheds="
				+ DefineScheds + ", Restructure=" + Restructure + ",LoanPurpose=" + LoanPurpose + ", UltiIndBen="
				+ UltiIndBen + ", DdApprNo=" + DdApprNo + ",IntRateType=" + IntRateType + ", VarRateFlag="
				+ VarRateFlag + ", InterestBasis=" + InterestBasis + ",IntPaymtMethod=" + IntPaymtMethod + ", BaseRateKey="
				+ BaseRateKey + ", SpreadRatePer=" + SpreadRatePer + ",RateChgFreq=" + RateChgFreq + ", InterestKey="
				+ InterestKey + ", InterestSpread=" + InterestSpread + ",InterestRateRec=" + InterestRateRec + ", PenSpread="
				+ PenSpread + ", MisusePenSpr=" + MisusePenSpr + ",ChrgLiqAcct=" + ChrgLiqAcct + ", ChrgCode="
				+ ChrgCode + ", ChrgAmount=" + ChrgAmount + ",ChrgClaimDate=" + ChrgClaimDate + ", ChrgBookedOn="
				+ ChrgBookedOn + ", ChrgCapitalise=" + ChrgCapitalise + ",TotalCapCharge=" + TotalCapCharge + ", BusDayDefn="
				+ BusDayDefn + ", LimitReference=" + LimitReference + ",ScecuredType=" + ScecuredType + ", CountryRisk="
				+ CountryRisk + ", MisAcctOfficer=" + MisAcctOfficer + ",CorrLiabNo=" + CorrLiabNo + ", LcAdvId="
				+ LcAdvId + ", CfNumber=" + CfNumber + ",CommitmentNo=" + CommitmentNo + ", MatureAtSod="
				+ MatureAtSod + ", DealerDesk=" + DealerDesk + ",CustomerRef=" + CustomerRef + ", CustRemarks="
				+ CustRemarks + ", OurRemarks=" + OurRemarks + ",AloneMergeInd=" + AloneMergeInd + ", FowWardBackward="
				+ FowWardBackward + ", BaseDateKey=" + BaseDateKey + ",TStLdRpmAaaRec=" + TStLdRpmAaaRec + ",ContractId=" + ContractId + ",LoanId=" + LoanId + "]";
    }
    /**
	 * @param AloneMergeInd 要设置的 AloneMergeInd
	 */
	public void setAloneMergeInd(String aloneMergeInd) {
		AloneMergeInd = aloneMergeInd;
	}
	
	/**
	 * @param AutoScheds 要设置的 AutoScheds
	 */
	public void setAutoScheds(String autoScheds) {
		AutoScheds = autoScheds;
	}
	/**
	 * @param BaseDateKey 要设置的 BaseDateKey
	 */
	public void setBaseDateKey(String baseDateKey) {
		BaseDateKey = baseDateKey;
	}
	/**
	 * @param BaseRateKey 要设置的 BaseRateKey
	 */
	public void setBaseRateKey(String baseRateKey) {
		BaseRateKey = baseRateKey;
	}
	/**
	 * @param BusDayDefn 要设置的 BusDayDefn
	 */
	public void setBusDayDefn(String busDayDefn) {
		BusDayDefn = busDayDefn;
	}
	/**
	 * @param Category 要设置的 Category
	 */
	public void setCategory(String category) {
		Category = category;
	}
	/**
	 * @param CfNumber 要设置的 CfNumber
	 */
	public void setCfNumber(String cfNumber) {
		CfNumber = cfNumber;
	}
	/**
	 * @param ChrgAmount 要设置的 ChrgAmount
	 */
	public void setChrgAmount(String chrgAmount) {
		ChrgAmount = chrgAmount;
	}
	/**
	 * @param ChrgBookedOn 要设置的 ChrgBookedOn
	 */
	public void setChrgBookedOn(String chrgBookedOn) {
		ChrgBookedOn = chrgBookedOn;
	}
	/**
	 * @param ChrgCapitalise 要设置的 ChrgCapitalise
	 */
	public void setChrgCapitalise(String chrgCapitalise) {
		ChrgCapitalise = chrgCapitalise;
	}
	/**
	 * @param ChrgClaimDate 要设置的 ChrgClaimDate
	 */
	public void setChrgClaimDate(String chrgClaimDate) {
		ChrgClaimDate = chrgClaimDate;
	}
	/**
	 * @param ChrgCode 要设置的 ChrgCode
	 */
	public void setChrgCode(String chrgCode) {
		ChrgCode = chrgCode;
	}
	/**
	 * @param ChrgLiqAcct 要设置的 ChrgLiqAcct
	 */
	public void setChrgLiqAcct(String chrgLiqAcct) {
		ChrgLiqAcct = chrgLiqAcct;
	}
	/**
	 * @param CommitmentNo 要设置的 CommitmentNo
	 */
	public void setCommitmentNo(String commitmentNo) {
		CommitmentNo = commitmentNo;
	}
	/**
	 * @param CorrLiabNo 要设置的 CorrLiabNo
	 */
	public void setCorrLiabNo(String corrLiabNo) {
		CorrLiabNo = corrLiabNo;
	}
	/**
	 * @param CountryRisk 要设置的 CountryRisk
	 */
	public void setCountryRisk(String countryRisk) {
		CountryRisk = countryRisk;
	}
	/**
	 * @param Currency 要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param CustomerId 要设置的 CustomerId
	 */
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	/**
	 * @param CustomerRef 要设置的 CustomerRef
	 */
	public void setCustomerRef(String customerRef) {
		CustomerRef = customerRef;
	}
	/**
	 * @param DdApprNo 要设置的 DdApprNo
	 */
	public void setDdApprNo(String ddApprNo) {
		DdApprNo = ddApprNo;
	}
	/**
	 * @param DealerDesk 要设置的 DealerDesk
	 */
	public void setDealerDesk(String dealerDesk) {
		DealerDesk = dealerDesk;
	}
	/**
	 * @param DefineScheds 要设置的 DefineScheds
	 */
	public void setDefineScheds(String defineScheds) {
		DefineScheds = defineScheds;
	}
	/**
	 * @param DrawdownAccount 要设置的 DrawdownAccount
	 */
	public void setDrawdownAccount(String drawdownAccount) {
		DrawdownAccount = drawdownAccount;
	}
	/**
	 * @param FBID 要设置的 FBID
	 */
	public void setFBID(String fbid) {
		FBID = fbid;
	}
	/**
	 * @param FinMatDate 要设置的 FinMatDate
	 */
	public void setFinMatDate(String finMatDate) {
		FinMatDate = finMatDate;
	}
	/**
	 * @param FowWardBackward 要设置的 FowWardBackward
	 */
	public void setFowWardBackward(String fowWardBackward) {
		FowWardBackward = fowWardBackward;
	}
	/**
	 * @param FtTxnType 要设置的 FtTxnType
	 */
	public void setFtTxnType(String ftTxnType) {
		FtTxnType = ftTxnType;
	}
	/**
	 * @param InterestBasis 要设置的 InterestBasis
	 */
	public void setInterestBasis(String interestBasis) {
		InterestBasis = interestBasis;
	}
	/**
	 * @param InterestKey 要设置的 InterestKey
	 */
	public void setInterestKey(String interestKey) {
		InterestKey = interestKey;
	}
	public void setInterestRateRec(List<InterestRateRec> interestRateRec) {
		InterestRateRec = interestRateRec;
	}
	/**
	 * @param InterestSpread 要设置的 InterestSpread
	 */
	public void setInterestSpread(String interestSpread) {
		InterestSpread = interestSpread;
	}
	/**
	 * @param IntLiqAcct 要设置的 IntLiqAcct
	 */
	public void setIntLiqAcct(String intLiqAcct) {
		IntLiqAcct = intLiqAcct;
	}
	/**
	 * @param IntPaymtMethod 要设置的 IntPaymtMethod
	 */
	public void setIntPaymtMethod(String intPaymtMethod) {
		IntPaymtMethod = intPaymtMethod;
	}
	/**
	 * @param IntRateType 要设置的 IntRateType
	 */
	public void setIntRateType(String intRateType) {
		IntRateType = intRateType;
	}
	/**
	 * @param LcAdvId 要设置的 LcAdvId
	 */
	public void setLcAdvId(String lcAdvId) {
		LcAdvId = lcAdvId;
	}
	/**
	 * @param LcDrawNo 要设置的 LcDrawNo
	 */
	public void setLcDrawNo(String lcDrawNo) {
		LcDrawNo = lcDrawNo;
	}
	/**
	 * @param LimitReference 要设置的 LimitReference
	 */
	public void setLimitReference(String limitReference) {
		LimitReference = limitReference;
	}
	/**
	 * @param LoanAgrNo 要设置的 LoanAgrNo
	 */
	public void setLoanAgrNo(String loanAgrNo) {
		LoanAgrNo = loanAgrNo;
	}
	/**
	 * @param LoanPurpose 要设置的 LoanPurpose
	 */
	public void setLoanPurpose(String loanPurpose) {
		LoanPurpose = loanPurpose;
	}
	/**
	 * @param MatureAtSod 要设置的 MatureAtSod
	 */
	public void setMatureAtSod(String matureAtSod) {
		MatureAtSod = matureAtSod;
	}
	/**
	 * @param MisAcctOfficer 要设置的 MisAcctOfficer
	 */
	public void setMisAcctOfficer(String misAcctOfficer) {
		MisAcctOfficer = misAcctOfficer;
	}
	/**
	 * @param MisusePenSpr 要设置的 MisusePenSpr
	 */
	public void setMisusePenSpr(String misusePenSpr) {
		MisusePenSpr = misusePenSpr;
	}
	/**
	 * @param OurRemarks 要设置的 OurRemarks
	 */
	public void setOurRemarks(String ourRemarks) {
		OurRemarks = ourRemarks;
	}
	/**
	 * @param PenSpread 要设置的 PenSpread
	 */
	public void setPenSpread(String penSpread) {
		PenSpread = penSpread;
	}
	/**
	 * @param PrinLiqAcct 要设置的 PrinLiqAcct
	 */
	public void setPrinLiqAcct(String prinLiqAcct) {
		PrinLiqAcct = prinLiqAcct;
	}
	/**
	 * @param ProductCode 要设置的 ProductCode
	 */
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	/**
	 * @param RateChgFreq 要设置的 RateChgFreq
	 */
	public void setRateChgFreq(String rateChgFreq) {
		RateChgFreq = rateChgFreq;
	}
	/**
	 * @param RecAccount 要设置的 RecAccount
	 */
	public void setRecAccount(String recAccount) {
		RecAccount = recAccount;
	}
	/**
	 * @param Restructure 要设置的 Restructure
	 */
	public void setRestructure(String restructure) {
		Restructure = restructure;
	}
	/**
	 * @param ScecuredType 要设置的 ScecuredType
	 */
	public void setScecuredType(String scecuredType) {
		ScecuredType = scecuredType;
	}
	/**
	 * @param SpreadRatePer 要设置的 SpreadRatePer
	 */
	public void setSpreadRatePer(String spreadRatePer) {
		SpreadRatePer = spreadRatePer;
	}
	/**
	 * @param Tenor 要设置的 Tenor
	 */
	public void setTenor(String tenor) {
		Tenor = tenor;
	}
	/**
	 * @param TotalCapCharge 要设置的 TotalCapCharge
	 */
	public void setTotalCapCharge(String totalCapCharge) {
		TotalCapCharge = totalCapCharge;
	}
	/**
	 * @param TStLdRpmAaaRec 要设置的 TStLdRpmAaaRec
	 */
	public void setTStLdRpmAaaRec(List<TStLdRpmAaaRec> stLdRpmAaaRec) {
		TStLdRpmAaaRec = stLdRpmAaaRec;
	}
	/**
	 * @param UltiIndBen 要设置的 UltiIndBen
	 */
	public void setUltiIndBen(String ultiIndBen) {
		UltiIndBen = ultiIndBen;
	}
	/**
	 * @param ValueDate 要设置的 ValueDate
	 */
	public void setValueDate(String valueDate) {
		ValueDate = valueDate;
	}
	/**
	 * @param VarRateFlag 要设置的 VarRateFlag
	 */
	public void setVarRateFlag(String varRateFlag) {
		VarRateFlag = varRateFlag;
	}
	/**
	 * @param CustRemarks 要设置的 CustRemarks
	 */
	public void setCustRemarks(String custRemarks) {
		CustRemarks = custRemarks;
	}
	
	public void setAmountRec(List<AmountRec> amountRec) {
		AmountRec = amountRec;
	}
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}       
    
}

