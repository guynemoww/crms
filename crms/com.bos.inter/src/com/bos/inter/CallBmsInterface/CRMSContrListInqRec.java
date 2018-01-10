package com.bos.inter.CallBmsInterface;
import javax.xml.bind.annotation.XmlElement;

public class CRMSContrListInqRec {
	@XmlElement(name="ContractId")
	public String	ContractId	;       //	合同编号
	@XmlElement(name="ContractType")
	public String	ContractType	;        //	业务类型
	@XmlElement(name="ContractTerm")
	public String	ContractTerm	;       //	合同期限
	@XmlElement(name="Ccy")
	public String	Ccy	;       			//	币种
	@XmlElement(name="ContractAmt")
	public String	ContractAmt	;        //	合同金额
	@XmlElement(name="Type")
	public String	Type	;       //	合同类型
	@XmlElement(name="Mode")
	public String	Mode	;       //	担保方式
	@XmlElement(name="PartyName")
	public String	PartyName	;        //	借款人名称
	@XmlElement(name="T24Id")
	public String	T24Id	;       //	借款人编号
	@XmlElement(name="ContractAvl")
	public String	ContractAvl	;        //	合同可用金额
	@XmlElement(name="ContractBal")
	public String	ContractBal	;        //	合同余额
	@XmlElement(name="ContractRate")
	public String	ContractRate	;        //	合同利率
	@XmlElement(name="StartDate")
	public String	StartDate	;        //	起始日期
	@XmlElement(name="EndDate")
	public String	EndDate	;        //	到期日期
	@XmlElement(name="Flag")
	public String	Flag	;        //	代理贴现标志
	@XmlElement(name="AcctNo")
	public String	AcctNo	;        //	入账账户
	@XmlElement(name="PayeeAccNo")
	public String	PayeeAccNo	;        //	代理人T24客户号
	@XmlElement(name="Name1")
	public String	Name1	;        //	代理人名称
	
	public void setCcy(String ccy) {
		Ccy = ccy;	
	}
	public void setContractAmt(String contractAmt) {
		ContractAmt = contractAmt;
	}
	public void setContractAvl(String contractAvl) {
		ContractAvl = contractAvl;
	}
	public void setContractBal(String contractBal) {
		ContractBal = contractBal;
	}
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	public void setContractRate(String contractRate) {
		ContractRate = contractRate;
	}
	public void setContractTerm(String contractTerm) {
		ContractTerm = contractTerm;
	}
	public void setContractType(String contractType) {
		ContractType = contractType;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public void setPartyName(String partyName) {
		PartyName = partyName;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public void setT24Id(String id) {
		T24Id = id;
	}
	public void setType(String type) {
		Type = type;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public void setName1(String name1) {
		Name1 = name1;
	}
	public void setPayeeAccNo(String payeeAccNo) {
		PayeeAccNo = payeeAccNo;
	}
	
}
