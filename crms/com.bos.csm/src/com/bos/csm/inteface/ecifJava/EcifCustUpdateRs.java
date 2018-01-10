package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class EcifCustUpdateRs extends SuperBosfxRs {
	
	//客户号
	@XmlElement(name="Customer")
	public String Customer;
	
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

	public void setWorkTime(String workTime) {
		WorkTime = workTime;
	}
	
	
}

