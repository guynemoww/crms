package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.eos.system.annotation.Bizlet;
public class CRMSGuarConInqRec {
	@XmlElement(name="ContractNo")
	public String	ContractNo	;//	纸质合同编号
    @XmlElement(name="ContractId")
	public String	ContractId	;//	合同编号
    @XmlElement(name="SubContractId")
	public String	SubContractId	;//	担保合同编号
    @XmlElement(name="SubContractManualNum")
	public String	SubContractManualNum	;//	纸质担保合同编号
    @XmlElement(name="Currency")
	public String	Currency	;//	币种
    @XmlElement(name="Amt")
	public String	Amt	;//	担保债项金额
    @XmlElement(name="PeopleNo")
	public String	PeopleNo	;//	抵质押/保证人编号
    @XmlElement(name="PeopleName")
	public String	PeopleName	;//	抵质押/保证人名称
    @XmlElement(name="DateBegin")
	public String	DateBegin	;//	起始日期
    @XmlElement(name="EndDate")
	public String	EndDate	;//	到期日期
    @XmlElement(name="GuaranteeRate")
	public String	GuaranteeRate	;//	担保比例
    /**
	 * @param Amt 要设置的 Amt
	 */
	public void setAmt(String amt) {
		Amt = amt;
	}
	 /**
	 * @param ContractId 要设置的 ContractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	 /**
	 * @param ContractNo 要设置的 ContractNo
	 */
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
	 /**
	 * @param Currency 要设置的 Currency
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}
	 /**
	 * @param DateBegin 要设置的 DateBegin
	 */
	public void setDateBegin(String dateBegin) {
		DateBegin = dateBegin;
	}
	 /**
	 * @param EndDate 要设置的 EndDate
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	 /**
	 * @param PeopleName 要设置的 PeopleName
	 */
	public void setPeopleName(String peopleName) {
		PeopleName = peopleName;
	}
	 /**
	 * @param PeopleNo 要设置的 PeopleNo
	 */
	public void setPeopleNo(String peopleNo) {
		PeopleNo = peopleNo;
	}
	 /**
	 * @param SubContractId 要设置的 SubContractId
	 */
	public void setSubContractId(String subContractId) {
		SubContractId = subContractId;
	}
	 /**
	 * @param SubContractManualNum 要设置的 SubContractManualNum
	 */
	public void setSubContractManualNum(String subContractManualNum) {
		SubContractManualNum = subContractManualNum;
	}
	public void setGuaranteeRate(String guaranteeRate) {
		GuaranteeRate = guaranteeRate;
	}

}
