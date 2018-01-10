package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

/**
 * 押品出入库撤销
 * 
 * @author ZhuYongLun
 * 
 */
public class CollCancelSynRec {
	// 担保品ID
	@XmlElement(name = "SuretyId")
	public String SuretyId;

	// 抵质押物类型
	@XmlElement(name = "SortType")
	public String SortType;

	// 权证类型
	@XmlElement(name = "CardType")
	public String CardType;

	// 权利登记证明号
	@XmlElement(name = "RejectionCode")
	public String RejectionCode;

	/**
	 * @param suretyId
	 */
	public void setSuretyId(String suretyId) {
		SuretyId = suretyId;
	}

	/**
	 * @param sortType
	 */
	public void setSortType(String sortType) {
		SortType = sortType;
	}

	/**
	 * @param cardType
	 */
	public void setCardType(String cardType) {
		CardType = cardType;
	}

	/**
	 * @param registerCertino
	 */
	public void setRejectionCode(String rejectionCode) {
		RejectionCode = rejectionCode;
	}

	@Override
	public String toString() {
		return "CollCancelSynRec [SuretyId=" + SuretyId + ",SortType="
				+ SortType + ",CardType=" + CardType + ",RejectionCode="
				+ RejectionCode + "]";
	}
}
