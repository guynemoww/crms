package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class EcifNaturalUpdateRs extends SuperBosfxRs{
	//客户号
	@XmlElement(name="Customer")
	public String Customer;
	
	//CRMS客户号
	@XmlElement(name="InBenCustomer")
	public String InBenCustomer;
	
	//网点号
	@XmlElement(name="CompanyCode")
	public String CompanyCode;
	
	//交易时间
	@XmlElement(name="WorkTime")
	public String WorkTime;

	
	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public void setInBenCustomer(String inBenCustomer) {
		InBenCustomer = inBenCustomer;
	}

	public void setWorkTime(String workTime) {
		WorkTime = workTime;
	}
	
	

}
