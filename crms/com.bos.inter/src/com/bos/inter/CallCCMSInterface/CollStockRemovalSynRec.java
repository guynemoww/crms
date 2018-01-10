package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

/**
 * 押品出库
 * 
 * @author ZhuYongLun
 * 
 */
public class CollStockRemovalSynRec {
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

	// 出库日期
	@XmlElement(name = "EndDate")
	public String EndDate;

	// 出库原因
	@XmlElement(name = "Reason")
	public String Reason;

	// 权证出库说明
	@XmlElement(name = "Remark1")
	public String Remark1;

	// 借出预归还日期
	@XmlElement(name = "PosTransDateTime")
	public String PosTransDateTime;

	// 借出人
	@XmlElement(name = "CheckerName")
	public String CheckerName;

	// 权证状态
	@XmlElement(name = "OrderState")
	public String OrderState;

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
	 * @param rejectionCode
	 */
	public void setRejectionCode(String rejectionCode) {
		RejectionCode = rejectionCode;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	/**
	 * @param reason
	 */
	public void setReason(String reason) {
		Reason = reason;
	}

	/**
	 * @param remark1
	 */
	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}

	/**
	 * @param posTransDateTime
	 */
	public void setPosTransDateTime(String posTransDateTime) {
		PosTransDateTime = posTransDateTime;
	}

	/**
	 * @param checkerName
	 */
	public void setCheckerName(String checkerName) {
		CheckerName = checkerName;
	}

	/**
	 * @param orderState
	 */
	public void setOrderState(String orderState) {
		OrderState = orderState;
	}

	@Override
	public String toString() {
		return "CollStockRemovalSynRec [SuretyId=" + SuretyId + ",SortType="
				+ SortType + ",CardType=" + CardType + ",RejectionCode="
				+ RejectionCode + ",EndDate=" + EndDate + ",Reason=" + Reason
				+ ",Remark1=" + Remark1 + ",PosTransDateTime="
				+ PosTransDateTime + ",CheckerName=" + CheckerName
				+ ",OrderState=" + OrderState + "]";
	}
}
