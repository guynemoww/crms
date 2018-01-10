package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * SCF接口，保理商信息查询 
 * 相应暴露
 * 
 * 
 */
public class CRMSFactInfoInqRs extends SuperBosfxRs {

	//CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	// 保理商名称
	@XmlElement(name="CorporationName")
	public String CorporationName;

	// 业务类型 
	@XmlElement(name="BizType")
	public String BizType;

	// 国别
	@XmlElement(name="SfCptyCountry")
	public String SfCptyCountry;

	// 省
	@XmlElement(name="Economize")
	public String Economize;

	//地址
	@XmlElement(name="Address")
	public String Address;

	// 邮编
	@XmlElement(name="PostCode")
	public String PostCode;

	//	 传真
	@XmlElement(name="Fax")
	public String Fax;

	//	 网址
	@XmlElement(name="Url")
	public String Url;

	//	 联系人姓名
	@XmlElement(name="LinkmanName")
	public String LinkmanName;

    //	 联系人Email
	@XmlElement(name="LinkmanEmail")
	public String LinkmanEmail;

	//	 联系人电话
	@XmlElement(name="LinkmanPhone")
	public String LinkmanPhone;

	//	是否FCI
	@XmlElement(name="Fci")
	public String Fci;

	//	是否签署协议
	@XmlElement(name="SignContract")
	public String SignContract;
	
	//	备注
	@XmlElement(name="Memo")
	public String Memo;	


	
	
	
	
	public void setAddress(String address) {
		Address = address;
	}






	public void setBizType(String bizType) {
		BizType = bizType;
	}






	public void setCorporationName(String corporationName) {
		CorporationName = corporationName;
	}






	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}






	public void setEconomize(String economize) {
		Economize = economize;
	}






	public void setFax(String fax) {
		Fax = fax;
	}






	public void setFci(String fci) {
		Fci = fci;
	}






	public void setLinkmanEmail(String linkmanEmail) {
		LinkmanEmail = linkmanEmail;
	}






	public void setLinkmanName(String linkmanName) {
		LinkmanName = linkmanName;
	}






	public void setLinkmanPhone(String linkmanPhone) {
		LinkmanPhone = linkmanPhone;
	}






	public void setMemo(String memo) {
		Memo = memo;
	}






	public void setPostCode(String postCode) {
		PostCode = postCode;
	}






	public void setSfCptyCountry(String sfCptyCountry) {
		SfCptyCountry = sfCptyCountry;
	}






	public void setSignContract(String signContract) {
		SignContract = signContract;
	}






	public void setUrl(String url) {
		Url = url;
	}






	@Override
	public String toString() {
		return "ScfFatorCustRs [CustomerNo=" + CustomerNo + ", CorporationName="
				+ CorporationName + ", BizType=" + BizType + ", SfCptyCountry="
				+ SfCptyCountry + ", Economize=" + Economize + ", Address="
				+ Address + ", PostCode=" + PostCode + ", Fax=" + Fax
				+ ", Url=" + Url+ ", LinkmanName=" + LinkmanName+ ", LinkmanEmail=" 
				+ LinkmanEmail+ ", LinkmanPhone=" + LinkmanPhone+ ", Fci=" + Fci
				+ ", SignContract=" + SignContract+ ", Memo=" + Memo;
	}
}
