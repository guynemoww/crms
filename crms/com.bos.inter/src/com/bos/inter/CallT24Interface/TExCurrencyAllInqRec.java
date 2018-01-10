package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

public class TExCurrencyAllInqRec {
	@XmlElement(name = "GbCcyName")
	public String GbCcyName; // 币种英文名

	@XmlElement(name = "CnCcyName")
	public String CnCcyName; // 币种中文名

	@XmlElement(name = "BoardBuyRate")
	public String BoardBuyRate; // 路透牌价买入价

	@XmlElement(name = "BoardSellRate")
	public String BoardSellRate; // 路透牌价卖出价

	@XmlElement(name = "BoardMidRate")
	public String BoardMidRate; // 路透牌价中间价

	@XmlElement(name = "BanknoteBuyRate")
	public String BanknoteBuyRate; // 我行牌价买入价

	@XmlElement(name = "BanknoteSellRate")
	public String BanknoteSellRate; // 我行牌价卖出价

	@XmlElement(name = "BanknoteMidRate")
	public String BanknoteMidRate; // 我行牌价中间价

	@XmlElement(name = "SafeBuyRate")
	public String SafeBuyRate; // 内部折算率买入价

	@XmlElement(name = "SafeSellRate")
	public String SafeSellRate; // 内部折算率卖出价

	@XmlElement(name = "SafeMidRate")
	public String SafeMidRate; // 内部折算率中间价

	@XmlElement(name = "QuotationCode")
	public String QuotationCode; // 单位

	@XmlElement(name = "NoOfDecimals")
	public String NoOfDecimals; // 小数点位数

	@XmlElement(name = "AcPrefFcy")
	public String AcPrefFcy; // 币种代码

	@XmlElement(name = "UpdateDate")
	public String UpdateDate; // 牌价日期

	@XmlElement(name = "UpdateTime")
	public String UpdateTime; // 牌价时间

	@XmlElement(name = "CcyId")
	public String CcyId; // 币种

	@Override
	public String toString() {
		return "TExCurrencyAllInqRec [GbCcyName=" + GbCcyName
				+ ", BoardBuyRate=" + BoardBuyRate + ", BoardSellRate="
				+ BoardSellRate + ", BoardMidRate=" + BoardMidRate
				+ ",BanknoteBuyRate=" + BanknoteBuyRate + ", BanknoteSellRate="
				+ BanknoteSellRate + ", BanknoteMidRate=" + BanknoteMidRate
				+ ",SafeBuyRate=" + SafeBuyRate + ", SafeSellRate="
				+ SafeSellRate + ", SafeSellRate=" + SafeSellRate
				+ ",SafeMidRate=" + SafeMidRate + ", QuotationCode="
				+ QuotationCode + ", NoOfDecimals=" + NoOfDecimals
				+ ",AcPrefFcy=" + AcPrefFcy + ", UpdateDate=" + UpdateDate
				+ ", UpdateTime=" + UpdateTime + ",CcyId=" + CcyId + "]";
	}

	public void setAcPrefFcy(String acPrefFcy) {
		AcPrefFcy = acPrefFcy;
	}

	public void setBanknoteBuyRate(String banknoteBuyRate) {
		BanknoteBuyRate = banknoteBuyRate;
	}

	public void setBanknoteMidRate(String banknoteMidRate) {
		BanknoteMidRate = banknoteMidRate;
	}

	public void setBanknoteSellRate(String banknoteSellRate) {
		BanknoteSellRate = banknoteSellRate;
	}

	public void setBoardBuyRate(String boardBuyRate) {
		BoardBuyRate = boardBuyRate;
	}

	public void setBoardMidRate(String boardMidRate) {
		BoardMidRate = boardMidRate;
	}

	public void setBoardSellRate(String boardSellRate) {
		BoardSellRate = boardSellRate;
	}

	public void setCcyId(String ccyId) {
		CcyId = ccyId;
	}

	public void setCnCcyName(String cnCcyName) {
		CnCcyName = cnCcyName;
	}

	public void setGbCcyName(String gbCcyName) {
		GbCcyName = gbCcyName;
	}

	public void setNoOfDecimals(String noOfDecimals) {
		NoOfDecimals = noOfDecimals;
	}

	public void setQuotationCode(String quotationCode) {
		QuotationCode = quotationCode;
	}

	public void setSafeBuyRate(String safeBuyRate) {
		SafeBuyRate = safeBuyRate;
	}

	public void setSafeMidRate(String safeMidRate) {
		SafeMidRate = safeMidRate;
	}

	public void setSafeSellRate(String safeSellRate) {
		SafeSellRate = safeSellRate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

}
