package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * SCF接口，交易对手信息查询
 * 相应暴露
 * 
 * 
 */
public class CRMSCountInfoInqRs extends SuperBosfxRs {

	//CRMS客户号
	@XmlElement(name="CustomerNo")
	public String CustomerNo;
	
	//组织机构代码
	@XmlElement(name="OrginstCode")
	public String OrginstCode;
	
	// 客户名称（中）
	@XmlElement(name="CorporationName")
	public String CorporationName;

	// 经济类型
	@XmlElement(name="Type")
	public String Type;

	// 国标行业分类
	@XmlElement(name="InterSduClass")
	public String InterSduClass;

	// 企业规模
	@XmlElement(name="MedSmEntFlg")
	public String MedSmEntFlg;

	//	 联系人姓名
	@XmlElement(name="LinkmanName")
	public String LinkmanName;

    //	 联系人Email
	@XmlElement(name="LinkmanEmail")
	public String LinkmanEmail;

	//	 联系人电话
	@XmlElement(name="LinkmanPhone")
	public String LinkmanPhone;

	//	是否是我行客户
	@XmlElement(name="IsMyCustomer")
	public String IsMyCustomer;

	//	Edi
	@XmlElement(name="EDI")
	public String EDI;
	
	//	企业注册码
	@XmlElement(name="Code")
	public String Code;
	
	//	代理商名称
	@XmlElement(name="AgentName")
	public String AgentName;
	
	//	备注
	@XmlElement(name="Memo")
	public String Memo;	


	
	
	
	
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}






	public void setCode(String code) {
		Code = code;
	}






	public void setCorporationName(String corporationName) {
		CorporationName = corporationName;
	}






	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}






	public void setEDI(String edi) {
		EDI = edi;
	}






	public void setInterSduClass(String interSduClass) {
		InterSduClass = interSduClass;
	}






	public void setIsMyCustomer(String isMyCustomer) {
		IsMyCustomer = isMyCustomer;
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






	public void setMedSmEntFlg(String medSmEntFlg) {
		MedSmEntFlg = medSmEntFlg;
	}






	public void setMemo(String memo) {
		Memo = memo;
	}






	public void setOrginstCode(String orginstCode) {
		OrginstCode = orginstCode;
	}






	public void setType(String type) {
		Type = type;
	}






	@Override
	public String toString() {
		return "ScfTraderCustRs [CustomerNo=" + CustomerNo + ", OrginstCode="
				+ OrginstCode + ", CorporationName="+ CorporationName 
				+ ", Type=" + Type + ", InterSduClass="
				+ InterSduClass + ", MedSmEntFlg=" + MedSmEntFlg +", LinkmanName=" 
				+ LinkmanName+ ", LinkmanEmail=" + LinkmanEmail+ ", LinkmanPhone=" 
				+ LinkmanPhone+ ", IsMyCustomer=" + IsMyCustomer+ ", EDI=" + EDI
				+ ", Code=" + Code+ ", AgentName=" + AgentName+", Memo=" + Memo;
	}
}
