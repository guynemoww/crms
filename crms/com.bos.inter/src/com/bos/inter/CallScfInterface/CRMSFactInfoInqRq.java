package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * SCF接口，保理商信息查询
 * 请求
 * 
 * 
 */
public class CRMSFactInfoInqRq extends SuperBosfxRq {

	// CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;

	
	


	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}





	@Override
	public String toString() {
		return "ScfFatorCustRq [CustomerNo=" + CustomerNo ;
	}
}
