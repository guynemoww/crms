package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;
/**
 * @author chenhuan
 *
 */
//批复信息查询(请求方) 
public class CRMSReplyInfoInqRq extends SuperBosfxRq{
	@XmlElement(name="CustomerNo")
	public String	CustomerNo	;//	CRMS客户号
    @XmlElement(name="BizMode")
	public String	BizMode	;//	业务模式
    public String toString() {
		return "CRMSReplyInfoInqRq [CustomerNo=" + CustomerNo  + ", BizMode="
				+ BizMode + "]";
    }
    /**
	 * @param BizMode 要设置的 BizMode
	 */
	public void setBizMode(String bizMode) {
		BizMode = bizMode;
	}
	/**
	 * @param CustomerNo 要设置的 CustomerNo
	 */
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}

}
