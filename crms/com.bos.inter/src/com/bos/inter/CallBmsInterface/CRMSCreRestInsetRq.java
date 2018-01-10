package com.bos.inter.CallBmsInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
import com.eos.system.annotation.Bizlet;

/**
 * 
 * @author chenhuan
 *
 */
//额度恢复接口  票据->CRMS
public class CRMSCreRestInsetRq extends SuperBosfxRq{
	@XmlElement(name="CRMSCreRestInsetRec")
	public List<CRMSCreRestInsetRec>	CRMSCreRestInsetRec	;       
	@XmlElement(name="TrnType")
	public String	TrnType	;        //	交易类型
	@XmlElement(name="ContractId")
	public String	ContractId	;        //	合同编号
	
	public String toString() {
		return "CRMSCreRestInsetRq [CRMSCreRestInsetRec="+CRMSCreRestInsetRec+
		", TrnType="+ TrnType + ",ContractId="+ContractId+"]";
	}
	/**
	 * @param ContractId 要设置的 ContractId
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	/**
	 * @param CRMSCreRestInsetRec 要设置的 CRMSCreRestInsetRec
	 */
	public void setCRMSCreRestInsetRec(List<CRMSCreRestInsetRec> creRestInsetRec) {
		CRMSCreRestInsetRec = creRestInsetRec;
	}
	/**
	 * @param TrnType 要设置的 TrnType
	 */
	public void setTrnType(String trnType) {
		TrnType = trnType;
	}
	

}
