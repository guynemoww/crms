package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;

public class CRMSCollInfoInqRec {
	
	@XmlElement(name="SubContractId")
	public String	SubContractId	;         		//	担保合同编号
    @XmlElement(name="SubContractNum")
	public String	SubContractNum	;    		//	纸质担保合同编号
    @XmlElement(name="SubContractType")
	public String SubContractType;              //担保类型
    @XmlElement(name="GuarantyId")
	public String	GuarantyId	;         		//	抵质押物编号
    @XmlElement(name="GuarantyName")
	public String	GuarantyName	;    		//	抵质押物名称
    @XmlElement(name="GuarantyType")
	public String GuarantyType;              //抵质押物类型
    @XmlElement(name="GuarantyAvl")
	public String	GuarantyAvl	;    		//	可用金额
    @XmlElement(name="MarginAmt")
	public String MarginAmt;              //抵质押物金额
    @XmlElement(name="MarginAcc")
	public String	MarginAcc	;    		//	账户
    @XmlElement(name="EndDt")
	public String EndDt;              //定期账户到期日（T24账户信息批量接口）
    @XmlElement(name="MarginAccType")
	public String MarginAccType;              //保证金账户类型
    /**
	 * @param EndDt 要设置的 EndDt
	 */
	public void setEndDt(String endDt) {
		EndDt = endDt;
	}
	 /**
	 * @param GuarantyAvl 要设置的 GuarantyAvl
	 */
	public void setGuarantyAvl(String guarantyAvl) {
		GuarantyAvl = guarantyAvl;
	}
	 /**
	 * @param GuarantyId 要设置的 GuarantyId
	 */
	public void setGuarantyId(String guarantyId) {
		GuarantyId = guarantyId;
	}
	 /**
	 * @param GuarantyName 要设置的 GuarantyName
	 */
	public void setGuarantyName(String guarantyName) {
		GuarantyName = guarantyName;
	}
	 /**
	 * @param GuarantyType 要设置的 GuarantyType
	 */
	public void setGuarantyType(String guarantyType) {
		GuarantyType = guarantyType;
	}
	 /**
	 * @param MarginAcc 要设置的 MarginAcc
	 */
	public void setMarginAcc(String marginAcc) {
		MarginAcc = marginAcc;
	}
	 /**
	 * @param MarginAccType 要设置的 MarginAccType
	 */
	public void setMarginAccType(String marginAccType) {
		MarginAccType = marginAccType;
	}
	 /**
	 * @param MarginAmt 要设置的 MarginAmt
	 */
	public void setMarginAmt(String marginAmt) {
		MarginAmt = marginAmt;
	}

	 /**
	 * @param SubContractId 要设置的 SubContractId
	 */
	public void setSubContractId(String subContractId) {
		SubContractId = subContractId;
	}
	 /**
	 * @param SubContractNum 要设置的 SubContractNum
	 */
	public void setSubContractNum(String subContractNum) {
		SubContractNum = subContractNum;
	}
	 /**
	 * @param SubContractType 要设置的 SubContractType
	 */
	public void setSubContractType(String subContractType) {
		SubContractType = subContractType;
	}
	 /**
	 * @param Num 要设置的 Num
	 */
	
	
    


}
