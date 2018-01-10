package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * 
 * @author chenhuan
 *
 */
//担保合同查询
public class CRMSGuarConInqRq extends SuperBosfxRq{
	@XmlElement(name="CustomerNo")
	public String	CustomerNo	;//	CRMS客户号
    @XmlElement(name="CustNo")
	public String	CustNo	;//	ECIF(T24)客户号
    @XmlElement(name="ApproveId")
	public String	ApproveId	;//	批复编号
    @XmlElement(name="ProductType")
	public String	ProductType	;//	授信业务品种
    @XmlElement(name="ContractId")
	public String	ContractId	;//	贷款合同编号（承兑协议）
    public String toString() {
		return "CRMSGuarConInqRq [CustomerNo=" + CustomerNo  + ", CustNo="
				+ CustNo + ",ApproveId=" + ApproveId  + ", ProductType="
				+ ProductType + ",ContractId=" + ContractId  + "]";
    }
    /**
	 * @param ApproveId 要设置的 ApproveId
	 */
	public void setApproveId(String approveId) {
		ApproveId = approveId;
	}
	/**
	 * @param ContractId 要设置的 ContractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	/**
	 * @param CustNo 要设置的 CustNo
	 */
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	/**
	 * @param CustomerNo 要设置的 CustomerNo
	 */
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}
	/**
	 * @param ProductType 要设置的 ProductType
	 */
	public void setProductType(String productType) {
		ProductType = productType;
	}
    

}
