package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * SCF接口，仓库信息查询 
 * 相应暴露
 * 
 * 
 */
public class CRMSWareInfoInqRs extends SuperBosfxRs {

//	CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	// 仓库名称
	@XmlElement(name="CorporationName")
	public String CorporationName;

	// 上级监管机构代码
	@XmlElement(name="HeadOfficeNum")
	public String HeadOfficeNum;

	// 监管机构名称
	@XmlElement(name="SuperOrgenName")
	public String SuperOrgenName;

	//地址
	@XmlElement(name="Address")
	public String Address;
	
	//	合作产品类型
	@XmlElement(name="ProductType")
	public String ProductType;

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
	
	//	合作协议编号
	@XmlElement(name="ContactNo")
	public String ContactNo;

	//协议开始日期
	@XmlElement(name="StartDt")
	public String StartDt;
	
	//	协议结束日期
	@XmlElement(name="EndDate")
	public String EndDate;

	//	协议形式
	@XmlElement(name="ContractWayCd")
	public String ContractWayCd;
	
	//	备注
	@XmlElement(name="Memo")
	public String Memo;	
	
	public void setAddress(String address) {
		Address = address;
	}

	
	
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}



	public void setContractWayCd(String contractWayCd) {
		ContractWayCd = contractWayCd;
	}



	public void setCorporationName(String corporationName) {
		CorporationName = corporationName;
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



	public void setHeadOfficeNum(String headOfficeNum) {
		HeadOfficeNum = headOfficeNum;
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



	public void setProductType(String productType) {
		ProductType = productType;
	}



	public void setStartDt(String startDt) {
		StartDt = startDt;
	}



	public void setSuperOrgenName(String superOrgenName) {
		SuperOrgenName = superOrgenName;
	}



	public void setUrl(String url) {
		Url = url;
	}



	@Override
	public String toString() {
		return "ScfStorageInfoRs [CustomerNo=" + CustomerNo +",CorporationName="+CorporationName
		+",HeadOfficeNum="+HeadOfficeNum +",SuperOrgenName="+SuperOrgenName +",Address="+Address
		+",ProductType="+ProductType+",PostCode="+PostCode+",Fax="+Fax+",Url="+Url+",LinkmanName="
		+LinkmanName+",LinkmanEmail="+LinkmanEmail+",LinkmanPhone="+LinkmanPhone+",ContactNo="
		+ContactNo+",StartDt="+StartDt+",EndDate="+EndDate+",ContractWayCd="+ContractWayCd
		+",Memo="+Memo
		;
	}
}
