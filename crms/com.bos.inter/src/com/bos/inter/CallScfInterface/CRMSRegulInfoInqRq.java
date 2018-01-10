package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * SCF接口，监管机构信息查询
 * 请求
 * 
 * 
 */
public class CRMSRegulInfoInqRq extends SuperBosfxRq {

	// CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	
	


	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}


	@Override
	public String toString() {
		return "ScfStorageAgencyRq [CustomerNo=" + CustomerNo ;
	}
}
