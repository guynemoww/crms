package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * SCF接口，查询授信客户
 * 请求
 * 
 * 
 */
public class CRMSCustInfoInqRq extends SuperBosfxRq {

	// ECIF(T24)客户号
	@XmlElement(name="CustNo")
	public String CustNo;

	
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}


	@Override
	public String toString() {
		return "ScfCreditCustRq [CustNo=" + CustNo ;
	}
}
