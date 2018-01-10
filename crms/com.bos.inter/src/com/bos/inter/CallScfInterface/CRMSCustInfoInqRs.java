package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * SCF接口，查询授信客户
 * 相应暴露
 * 
 * 
 */
public class CRMSCustInfoInqRs extends SuperBosfxRs {

	// 组织机构代码
	@XmlElement(name="OrginstCode")
	public String OrginstCode;

	// ECIF(T24)客户号
	@XmlElement(name="CustNo")
	public String CustNo;

	// CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	// 客户名称
	@XmlElement(name="CustomerName")
	public String CustomerName;

	// 经济类型
	@XmlElement(name="Type")
	public String Type;

	// 国标行业分类
	@XmlElement(name="InterSduClass")
	public String InterSduClass;

	// 企业规模
	@XmlElement(name="MedSmEntFlg")
	public String MedSmEntFlg;


	public void setCustNo(String custNo) {
		CustNo = custNo;
	}


	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}


	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}


	public void setInterSduClass(String interSduClass) {
		InterSduClass = interSduClass;
	}


	public void setMedSmEntFlg(String medSmEntFlg) {
		MedSmEntFlg = medSmEntFlg;
	}


	public void setOrginstCode(String orginstCode) {
		OrginstCode = orginstCode;
	}


	public void setType(String type) {
		Type = type;
	}

	@Override
	public String toString() {
		return "ScfCreditCust [OrginstCode=" + OrginstCode + ", CustNo="
				+ CustNo + ", CustomerNo=" + CustomerNo + ", CustomerName="
				+ CustomerName + ", Type=" + Type + ", InterSduClass="
				+ InterSduClass + ", MedSmEntFlg=" + MedSmEntFlg ;
	}
}
