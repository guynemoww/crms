package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * ECIF系统查看客户信息---证件信息
 * 请求
 * @author git
 *
 */
public class CertInfo {
	//证件操作类型
	@XmlElement(name="OpTypeCer")
	public String OpTypeCer = "U";
	
	//	证件类型
	@XmlElement(name="LegalEntTyp")
	public String LegalEntTyp;

	// 证件编号
	@XmlElement(name="LegalIdNo")
	public String LegalIdNo;

	//签发日期
	@XmlElement(name="BeginEffDt")
	public String BeginEffDt;

	// 到期日期
	@XmlElement(name="EndEffDt")
	public String EndEffDt;
	
	//证件用途
	@XmlElement(name="EndEffDt")
	public String CorpIdType;

	
	
	public void setBeginEffDt(String beginEffDt) {
		BeginEffDt = beginEffDt;
	}



	public void setEndEffDt(String endEffDt) {
		EndEffDt = endEffDt;
	}



	public void setLegalEntTyp(String legalEntTyp) {
		LegalEntTyp = legalEntTyp;
	}



	public void setLegalIdNo(String legalIdNo) {
		LegalIdNo = legalIdNo;
	}



	public void setOpTypeCer(String opTypeCer) {
		OpTypeCer = opTypeCer;
	}


	@Override
	public String toString() {
		return "CertInfo [LegalEntTyp=" + LegalEntTyp+",LegalIdNo="+LegalIdNo
		+",BeginEffDt="+BeginEffDt +",EndEffDt="+EndEffDt  ;
	}
	
}
