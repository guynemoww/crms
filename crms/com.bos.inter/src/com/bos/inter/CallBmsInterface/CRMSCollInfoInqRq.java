package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;
//押品信息查询 票据->CRMS				

public class CRMSCollInfoInqRq extends SuperBosfxRq{
	@XmlElement(name="T24Id")
	public String	T24Id	;         		//	T24客户号
    @XmlElement(name="ContractId")
	public String	ContractId	;    		//	贷款合同号
    @XmlElement(name="SubContractId")
	public String SubContractId;              //担保合同号
    @XmlElement(name="ApproveId")
	public String	ApproveId	;         		//	批复编号
    @XmlElement(name="ProductType")
	public String	ProductType	;    		//	授信业务品种
    @XmlElement(name="GuarantyType")
	public String GuarantyType;              //抵质押物类型
    public String toString() {
		return "CRMSCollInfoInqRq [T24Id="+T24Id+
		", ContractId="+ ContractId + ",SubContractId="+SubContractId+",ApproveId="+ApproveId+
		", ProductType="+ ProductType + ",GuarantyType="+GuarantyType+"]";
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
	 * @param GuarantyType 要设置的 GuarantyType
	 */
	public void setGuarantyType(String guarantyType) {
		GuarantyType = guarantyType;
	}
	/**
	 * @param ProductType 要设置的 ProductType
	 */
	public void setProductType(String productType) {
		ProductType = productType;
	}
	/**
	 * @param SubContractId 要设置的 SubContractId
	 */
	public void setSubContractId(String subContractId) {
		SubContractId = subContractId;
	}
	/**
	 * @param T24Id 要设置的 T24Id
	 */
	public void setT24Id(String id) {
		T24Id = id;
	}

}
