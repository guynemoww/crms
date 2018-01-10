package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

public class CollLaidUpSynRec {
	// 担保品ID
	@XmlElement(name = "SuretyId")
	public String SuretyId;

	// 抵质押物类型
	@XmlElement(name = "SortType")
	public String SortType;

	// 权证类型
	@XmlElement(name = "CardType")
	public String CardType;

	// 发证部门
	@XmlElement(name = "IssueOffice")
	public String IssueOffice;

	// 权利登记证明号
	@XmlElement(name = "RejectionCode")
	public String RejectionCode;

	// 登记机构类型
	@XmlElement(name = "RegType")
	public String RegType;

	// 登记机构名称
	@XmlElement(name = "RelCusName")
	public String RelCusName;

	// 登记机构地址
	@XmlElement(name = "OrganAddress")
	public String OrganAddress;

	// 权证登记日期
	@XmlElement(name = "RegDate")
	public String RegDate;

	// 登记到期日
	@XmlElement(name = "EndDate")
	public String EndDate;

	// 入库日期
	@XmlElement(name = "BeginDate")
	public String BeginDate;

	// 入库价值
	@XmlElement(name = "BreakValue")
	public String BreakValue;

	// 保管机构
	@XmlElement(name = "InvoiceOrgan")
	public String InvoiceOrgan;

	// 封包编号
	@XmlElement(name = "TransferCode")
	public String TransferCode;

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
	 * @param issueOffice
	 */
	public void setIssueOffice(String issueOffice) {
		IssueOffice = issueOffice;
	}

	/**
	 * @param rejectionCode
	 */
	public void setRejectionCode(String rejectionCode) {
		RejectionCode = rejectionCode;
	}

	/**
	 * @param regType
	 */
	public void setRegType(String regType) {
		RegType = regType;
	}

	/**
	 * @param relCusName
	 */
	public void setRelCusName(String relCusName) {
		RelCusName = relCusName;
	}

	/**
	 * @param organAddress
	 */
	public void setOrganAddress(String organAddress) {
		OrganAddress = organAddress;
	}

	/**
	 * @param regDate
	 */
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	/**
	 * @param beginDate
	 */
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	/**
	 * @param breakValue
	 */
	public void setBreakValue(String breakValue) {
		BreakValue = breakValue;
	}

	/**
	 * @param invoiceOrgan
	 */
	public void setInvoiceOrgan(String invoiceOrgan) {
		InvoiceOrgan = invoiceOrgan;
	}

	/**
	 * @param transferCode
	 */
	public void setTransferCode(String transferCode) {
		TransferCode = transferCode;
	}

	/**
	 * @param orderState
	 */
	public void setOrderState(String orderState) {
		OrderState = orderState;
	}

	@Override
	public String toString() {
		return "CollLaidUpSynRec [SuretyId=" + SuretyId + ",SortType="
				+ SortType + ",CardType=" + CardType + ",IssueOffice="
				+ IssueOffice + ",RejectionCode=" + RejectionCode + ",RegType="
				+ RegType + ",RelCusName=" + RelCusName + ",OrganAddress="
				+ OrganAddress + ",RegDate=" + RegDate + ",EndDate=" + EndDate
				+ ",BeginDate=" + BeginDate + ",BreakValue=" + BreakValue
				+ ",InvoiceOrgan=" + InvoiceOrgan + ",TransferCode="
				+ TransferCode + ",OrderState=" + OrderState + "]";
	}
}
