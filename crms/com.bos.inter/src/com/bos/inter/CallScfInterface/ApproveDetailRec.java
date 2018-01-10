package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.eos.system.annotation.Bizlet;
public class ApproveDetailRec {
	@XmlElement(name="ProductType")
	public String	ProductType	;//	授信业务品种
	/**
	 * @param ProductType 要设置的 ProductType
	 */
	public void setProductType(String productType) {
		ProductType = productType;
	}
	

}
