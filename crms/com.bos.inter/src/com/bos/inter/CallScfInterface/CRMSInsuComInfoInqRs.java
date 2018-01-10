package com.bos.inter.CallScfInterface;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * SCF接口，查询授信客户
 * 相应暴露
 * 
 * 
 */
public class CRMSInsuComInfoInqRs extends SuperBosfxRs {

	//	CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	// 保险公司名称
	@XmlElement(name="BXCompName")
	public String BXCompName;

	//公司级别
	@XmlElement(name="CompLevel")
	public String CompLevel;
			
	//	地址
	@XmlElement(name="Address")
	public String Address;

	// 邮编
	@XmlElement(name="PostCode")
	public String PostCode;		 
			 
	//总公司代码
	@XmlElement(name="HeadCompNo")
	public String HeadCompNo;

	//总公司名称
	@XmlElement(name="HeadCompName")
	public String HeadCompName;
			 
	//合作产品类型 
	@XmlElement(name="ProductType")
	public String ProductType;		 
			
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

	// 合作协议编号
	@XmlElement(name="ContactNo")
	public String ContactNo;

	//	协议开始日期
	@XmlElement(name="StartDt")
	public String StartDt;

	//协议结束日期
	@XmlElement(name="EndDate")
	public String EndDate;
	
	
	
	public void setAddress(String address) {
		Address = address;
	}



	public void setBXCompName(String compName) {
		BXCompName = compName;
	}



	public void setCompLevel(String compLevel) {
		CompLevel = compLevel;
	}



	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}



	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}



	public void setEndDate(String endDate) {
		EndDate = endDate;
	}



	public void setFax(String fax) {
		Fax = fax;
	}



	public void setHeadCompName(String headCompName) {
		HeadCompName = headCompName;
	}



	public void setHeadCompNo(String headCompNo) {
		HeadCompNo = headCompNo;
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



	public void setPostCode(String postCode) {
		PostCode = postCode;
	}



	public void setProductType(String productType) {
		ProductType = productType;
	}



	public void setStartDt(String startDt) {
		StartDt = startDt;
	}



	public void setUrl(String url) {
		Url = url;
	}



	@Override
	public String toString() {
		return "ScfInsuranceCustRs [CustomerNo=" + CustomerNo + ", BXCompName="
				+ BXCompName + ", CompLevel=" + CompLevel  + ", Address="
				+ Address + ", PostCode=" + PostCode + ", HeadCompNo=" + HeadCompNo 
				+ ", HeadCompName=" + HeadCompName + ", ProductType=" + ProductType
				+ ", ContactNo=" + ContactNo + ", Fax=" + Fax + ", Url=" + Url
				+ ", LinkmanName=" + LinkmanName+ ", LinkmanEmail=" 
				+ LinkmanEmail+ ", LinkmanPhone=" + LinkmanPhone+ ", StartDt=" + StartDt
				+ ", EndDate=" + EndDate;
	}
}
