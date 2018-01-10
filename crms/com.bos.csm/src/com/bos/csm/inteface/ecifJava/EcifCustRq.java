package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * ECIF系统查看客户信息
 * 请求
 * @author git
 *
 */
@Bizlet(value="")
public class EcifCustRq extends SuperBosfxRq{

	// ECIF客户号
	@XmlElement(name="Customer")
	public String Customer;

	//证件类型
	@XmlElement(name="PrsnLegalType")
	public String PrsnLegalType;
	
	//证件号码
	@XmlElement(name="IdCode")
	public String IdCode;

	//SWIFT编号
	@XmlElement(name="SwiftBusId")
	public String SwiftBusId;
	
	
	


	public void setCustomer(String customer) {
		Customer = customer;
	}





	public void setIdCode(String idCode) {
		IdCode = idCode;
	}





	public void setPrsnLegalType(String prsnLegalType) {
		PrsnLegalType = prsnLegalType;
	}





	public void setSwiftBusId(String swiftBusId) {
		SwiftBusId = swiftBusId;
	}





	@Override
	public String toString() {
		return "EcifCustRq [Customer=" + Customer+",PrsnLegalType="+PrsnLegalType+",IdCode="+IdCode+ ",SwiftBusId="+SwiftBusId;
	}

}
