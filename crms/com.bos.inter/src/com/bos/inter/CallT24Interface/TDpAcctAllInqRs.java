package com.bos.inter.CallT24Interface;
/**
 * @author chenhuan
 */
import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class TDpAcctAllInqRs extends SuperBosfxRs{
	
	@XmlElement(name="AgreeMentAcctNo")
	public String	AgreeMentAcctNo	;         		  		 	//	账号
    @XmlElement(name="CustId")
	public String	CustId	;    		         			  	//	客户号
    @XmlElement(name="CateGory")
	public String CateGory;                     			 	//类别代码
    @XmlElement(name="AcctName")
	public String	AcctName	;         						 //	账户名
    @XmlElement(name="ShortName")
	public String	ShortName	;    		    				//	户名缩写
    @XmlElement(name="Currency")
	public String Currency;                   					 //币种
    @XmlElement(name="AcctOfficerCd")
	public String	AcctOfficerCd	;         		    		//	客户经理
    @XmlElement(name="PostingRestrict")
	public String	PostingRestrict	;    		           		//	过账限制
    @XmlElement(name="AcctType")
	public String AcctType;                     				//账户类型
    @XmlElement(name="CashTxnFlg")
	public String	CashTxnFlg	;         						 //	可否取现标志
    @XmlElement(name="SealPwd")
	public String	SealPwd	;    		    					//	印鉴密码标志
    @XmlElement(name="OpeningDate")
	public String OpeningDate;                   				 //帐户开户日
    @XmlElement(name="ValueDate")
	public String ValueDate;                    				//起息日
    @XmlElement(name="Term")
	public String	Term	;         		    				//	存期
    @XmlElement(name="MaturityDate")
	public String	MaturityDate	;    		          		 //	到期日
    @XmlElement(name="AcCharacter")
	public String AcCharacter;                      				//外币账户类型代码
    @XmlElement(name="CharDiff")
	public String	CharDiff	;         		 				//	外币账户类型代码区分
    @XmlElement(name="ExchangeNo")
	public String	ExchangeNo	;    		   					 //	交换号
    @XmlElement(name="MediumCateg")
	public String MediumCateg;                   			 //介质种类
    @XmlElement(name="PassbookNo")
	public String PassbookNo;                   			 //一本通存折号
    @XmlElement(name="CardNo")
	public String	CardNo	;         		   				 //储蓄卡卡号
    @XmlElement(name="MediumStatus")
	public String	MediumStatus	;    		           //	介质状态
    @XmlElement(name="FcyType")
	public String FcyType;                      			//外币类型(钞|汇)
    @XmlElement(name="DEPProd")
	public String	DEPProd	;         						 //	对私产品类型
    @XmlElement(name="StockNumber")
	public String	StockNumber	;    		   				 //	重控号码
    @XmlElement(name="RtFlag")
	public String RtFlag;                    				//对私标记
    @XmlElement(name="IdType")
	public String IdType;                  				  //开户人证件类型
    @XmlElement(name="IdNo")
	public String IdNo;                    				//开户人证件号码
    @XmlElement(name="InactivMarker")
	public String	InactivMarker	;         		    //不动户标志
    @XmlElement(name="OpenClearedBal")
	public String	OpenClearedBal	;    		           //	日初清算余额
    @XmlElement(name="OnlineActualBal")
	public String OnlineActualBal;                    	  //当前实际余额
    @XmlElement(name="OnLineClearedBal")
	public String	OnLineClearedBal	;         		 //	当前清算余额
    @XmlElement(name="WorkingBal")
	public String	WorkingBal	;    		   			 //	时点余额
    @XmlElement(name="LockedAmt") 
	public String LockedAmt;                          //冻结金额
    @XmlElement(name="AltAcctNo")
	public String	AltAcctNo	;    		           //	原系统账户
    @XmlElement(name="MasterAcctNo")
	public String MasterAcctNo;                      //主账户
    @XmlElement(name="OpenBank")
	public String	OpenBank	;         		     //	开户行
    @XmlElement(name="RolloverFlag")
	public String	RolloverFlag	;    		    //	自动转存标志
    @XmlElement(name="RollOverTerm")
	public String RollOverTerm;                    //转存期限
    @XmlElement(name="IndentityClass")
	public String	IndentityClass	;    		   //	身份类别
    @XmlElement(name="Nationality")
	public String	Nationality	;         		 //	国籍
    @XmlElement(name="CustLevel")
	public String	CustLevel	;    		    //	客户等级
    @Override
	public String toString() {
		return "TDpAcctAllInqRs [AgreeMentAcctNo=" + AgreeMentAcctNo + ", CustId="
				+ CustId + ", CateGory=" + CateGory + ", AcctName="
				+ AcctName + ",ShortName=" + ShortName + ", Currency="
				+ Currency + ", AcctOfficerCd=" + AcctOfficerCd + ",PostingRestrict=" + PostingRestrict + ", AcctType="
				+ AcctType + ", CashTxnFlg=" + CashTxnFlg + ",SealPwd=" + SealPwd + ", OpeningDate="
				+ OpeningDate + ", ValueDate=" + ValueDate + ",Term=" + Term + ", MaturityDate="
				+ MaturityDate + ", AcCharacter=" + AcCharacter + ",CharDiff=" + CharDiff + ", ExchangeNo="
				+ ExchangeNo + ", MediumCateg=" + MediumCateg + ",PassbookNo=" + PassbookNo + ", CardNo="
				+ CardNo + ", MediumStatus=" + MediumStatus + ",FcyType=" + FcyType + ", DEPProd="
				+ DEPProd + ", StockNumber=" + StockNumber + ",RtFlag=" + RtFlag + ", IdType="
				+ IdType + ", IdNo=" + IdNo + ",InactivMarker=" + InactivMarker + ", OpenClearedBal="
				+ OpenClearedBal + ", OnlineActualBal=" + OnlineActualBal + ",OnLineClearedBal=" + OnLineClearedBal + ", WorkingBal="
				+ WorkingBal + ", LockedAmt=" + LockedAmt + ",AltAcctNo=" + AltAcctNo + ", MasterAcctNo="
				+ MasterAcctNo + ", OpenBank=" + OpenBank + ",RolloverFlag=" + RolloverFlag + ", RollOverTerm="
				+ RollOverTerm + ", IndentityClass=" + IndentityClass + ",Nationality=" + Nationality + ", CustLevel="
				+ CustLevel + "]";
    }
    /**
	 * @param AcCharacter 要设置的 AcCharacter
	 */
	public void setAcCharacter(String acCharacter) {
		AcCharacter = acCharacter;
	}
	 /**
	 * @param AcctName 要设置的 AcctName
	 */
	public void setAcctName(String acctName) {
		AcctName = acctName;
	}
	/**
	 * @param AcctOfficerCd 要设置的 AcctOfficerCd
	 */
	public void setAcctOfficerCd(String acctOfficerCd) {
		AcctOfficerCd = acctOfficerCd;
	}
	/**
	 * @param AcctType 要设置的 AcctType
	 */
	public void setAcctType(String acctType) {
		AcctType = acctType;
	}
	/**
	 * @param AgreeMentAcctNo 要设置的 AgreeMentAcctNo
	 */
	public void setAgreeMentAcctNo(String agreeMentAcctNo) {
		AgreeMentAcctNo = agreeMentAcctNo;
	}
	/**
	 * @param AltAcctNo 要设置的 AltAcctNo
	 */
	public void setAltAcctNo(String altAcctNo) {
		AltAcctNo = altAcctNo;
	}
	/**
	 * @param CardNo 要设置的 CardNo
	 */
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	/**
	 * @param CashTxnFlg 要设置的 CashTxnFlg
	 */
	public void setCashTxnFlg(String cashTxnFlg) {
		CashTxnFlg = cashTxnFlg;
	}
	/**
	 * @param CateGory 要设置的 CateGory
	 */
	public void setCateGory(String cateGory) {
		CateGory = cateGory;
	}
	/**
	 * @param CharDiff 要设置的 CharDiff
	 */
	public void setCharDiff(String charDiff) {
		CharDiff = charDiff;
	}
	/**
	 * @param Currency 要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	/**
	 * @param CustId 要设置的 CustId
	 */
	public void setCustId(String custId) {
		CustId = custId;
	}
	/**
	 * @param CustLevel 要设置的 CustLevel
	 */
	public void setCustLevel(String custLevel) {
		CustLevel = custLevel;
	}
	/**
	 * @param DEPProd 要设置的 DEPProd
	 */
	public void setDEPProd(String DEPProd) {
		DEPProd = DEPProd;
	}
	/**
	 * @param ExchangeNo 要设置的 ExchangeNo
	 */
	public void setExchangeNo(String exchangeNo) {
		ExchangeNo = exchangeNo;
	}
	/**
	 * @param FcyType 要设置的 FcyType
	 */
	public void setFcyType(String fcyType) {
		FcyType = fcyType;
	}
	/**
	 * @param IdNo 要设置的 IdNo
	 */
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	/**
	 * @param IdType 要设置的 IdType
	 */
	public void setIdType(String idType) {
		IdType = idType;
	}
	/**
	 * @param InactivMarker 要设置的 InactivMarker
	 */
	public void setInactivMarker(String inactivMarker) {
		InactivMarker = inactivMarker;
	}
	/**
	 * @param IndentityClass 要设置的 IndentityClass
	 */
	public void setIndentityClass(String indentityClass) {
		IndentityClass = indentityClass;
	}
	/**
	 * @param LockedAmt 要设置的 LockedAmt
	 */
	public void setLockedAmt(String lockedAmt) {
		LockedAmt = lockedAmt;
	}
	/**
	 * @param MasterAcctNo 要设置的 MasterAcctNo
	 */
	public void setMasterAcctNo(String masterAcctNo) {
		MasterAcctNo = masterAcctNo;
	}
	/**
	 * @param MaturityDate 要设置的 MaturityDate
	 */
	public void setMaturityDate(String maturityDate) {
		MaturityDate = maturityDate;
	}
	/**
	 * @param MediumCateg 要设置的 MediumCateg
	 */
	public void setMediumCateg(String mediumCateg) {
		MediumCateg = mediumCateg;
	}
	/**
	 * @param MediumStatus 要设置的 MediumStatus
	 */
	public void setMediumStatus(String mediumStatus) {
		MediumStatus = mediumStatus;
	}
	/**
	 * @param Nationality 要设置的 Nationality
	 */
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	/**
	 * @param OnlineActualBal 要设置的 OnlineActualBal
	 */
	public void setOnlineActualBal(String onlineActualBal) {
		OnlineActualBal = onlineActualBal;
	}
	/**
	 * @param OnLineClearedBal 要设置的 OnLineClearedBal
	 */
	public void setOnLineClearedBal(String onLineClearedBal) {
		OnLineClearedBal = onLineClearedBal;
	}
	/**
	 * @param OpenBank 要设置的 OpenBank
	 */
	public void setOpenBank(String openBank) {
		OpenBank = openBank;
	}
	/**
	 * @param OpenClearedBal 要设置的 OpenClearedBal
	 */
	public void setOpenClearedBal(String openClearedBal) {
		OpenClearedBal = openClearedBal;
	}
	/**
	 * @param OpeningDate 要设置的 OpeningDate
	 */
	public void setOpeningDate(String openingDate) {
		OpeningDate = openingDate;
	}
	/**
	 * @param PassbookNo 要设置的 PassbookNo
	 */
	public void setPassbookNo(String passbookNo) {
		PassbookNo = passbookNo;
	}
	/**
	 * @param PostingRestrict 要设置的 PostingRestrict
	 */
	public void setPostingRestrict(String postingRestrict) {
		PostingRestrict = postingRestrict;
	}
	/**
	 * @param RolloverFlag 要设置的 RolloverFlag
	 */
	public void setRolloverFlag(String rolloverFlag) {
		RolloverFlag = rolloverFlag;
	}
	/**
	 * @param RollOverTerm 要设置的 RollOverTerm
	 */
	public void setRollOverTerm(String rollOverTerm) {
		RollOverTerm = rollOverTerm;
	}
	/**
	 * @param RtFlag 要设置的 RtFlag
	 */
	public void setRtFlag(String rtFlag) {
		RtFlag = rtFlag;
	}
	/**
	 * @param SealPwd 要设置的 SealPwd
	 */
	public void setSealPwd(String sealPwd) {
		SealPwd = sealPwd;
	}
	/**
	 * @param ShortName 要设置的 ShortName
	 */
	public void setShortName(String shortName) {
		ShortName = shortName;
	}
	/**
	 * @param StockNumber 要设置的 StockNumber
	 */
	public void setStockNumber(String stockNumber) {
		StockNumber = stockNumber;
	}
	/**
	 * @param Term 要设置的 Term
	 */
	public void setTerm(String term) {
		Term = term;
	}
	/**
	 * @param ValueDate 要设置的 ValueDate
	 */
	public void setValueDate(String valueDate) {
		ValueDate = valueDate;
	}
	/**
	 * @param WorkingBal 要设置的 WorkingBal
	 */
	public void setWorkingBal(String workingBal) {
		WorkingBal = workingBal;
	}
    
    
    
}
