package com.bos.csm.inteface.ecifJava;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class EcifNaturalUpdateRq extends SuperBosfxRq {
	
//	流水号
	@XmlElement(name="TransSeqNo")
	public String TransSeqNo;
	
	//客户号
	@XmlElement(name="Customer")
	public String Customer;
	
	//参与人名称
	@XmlElement(name="SPName")
	public String SPName;

	
	//性别代码
	@XmlElement(name="Sex")
	public String Sex;
	
	//出生日期
	@XmlElement(name="Birthday")
	public String Birthday;
	
	//国籍代码
	@XmlElement(name="Nationality")
	public String Nationality;
	
	//婚姻状况
	@XmlElement(name="Marriage")
	public String Marriage;
	
	//学位代码
	@XmlElement(name="DegreeCode")
	public String DegreeCode;
	
	//学历代码
	@XmlElement(name="DegreeCode_1")
	public String DegreeCode_1;
	
	//民族
	@XmlElement(name="EthnicGp")
	public String EthnicGp;
	
	//配偶姓名
	@XmlElement(name="SpouseName")
	public String SpouseName;
	
	//配偶姓名
	@XmlElement(name="ReLtTyCd")
	public String ReLtTyCd;

	//证件信息
	@XmlElement(name="C201OrgCusEfLglfRec")
	public List<CertInfo> CertInfoList;

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public void setCertInfoList(List<CertInfo> certInfoList) {
		CertInfoList = certInfoList;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public void setDegreeCode(String degreeCode) {
		DegreeCode = degreeCode;
	}

	public void setDegreeCode_1(String degreeCode_1) {
		DegreeCode_1 = degreeCode_1;
	}

	public void setEthnicGp(String ethnicGp) {
		EthnicGp = ethnicGp;
	}

	public void setMarriage(String marriage) {
		Marriage = marriage;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public void setSPName(String name) {
		SPName = name;
	}

	public void setSpouseName(String spouseName) {
		SpouseName = spouseName;
	}

	public void setTransSeqNo(String transSeqNo) {
		TransSeqNo = transSeqNo;
	}

	public void setReLtTyCd(String reLtTyCd) {
		ReLtTyCd = reLtTyCd;
	}

	
	
	
	
	
	
	
}
