/**
 * 
 */
package com.bos.inter.CallBmsInterface;


import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * @author shenglong
 * @date 2014-06-09 15:11:41
 *
 */
public class CRMSContrListInqRq extends SuperBosfxRq{
	@XmlElement(name="CustNo")
	public String	CustNo	;       //	ECIF(T24)客户号
	@XmlElement(name="ApproveId")
	public String	ApproveId	;       //	批复编号
	@XmlElement(name="ContractId")
	public String	ContractId	;       //	合同编号
	
	public void setApproveId(String approveId) {
		ApproveId = approveId;
	}
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
}
