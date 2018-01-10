package com.bos.inter.CallCCMSInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * SCF新增保证金请求
 * 
 * @author menglei
 * 
 */
public class CRMSCollCashSynRq extends SuperBosfxRq {
	@XmlElement(name = "RqUID_1")
	public String RqUID_1;// SCF交易流水

	@XmlElement(name = "CRMSCollCashSynRec")
	public List<CRMSCollCashSynRec> CRMSCollCashSynRec;

	public void setRqUID_1(String rqUID_1) {
		RqUID_1 = rqUID_1;
	}

	public void setCRMSCollCashSynRec(List<CRMSCollCashSynRec> collCashSynRec) {
		CRMSCollCashSynRec = collCashSynRec;
	}

	@Override
	public String toString() {
		return "SCFGuaranteeMoneyRq [RqUID_1=" + RqUID_1
				+ ",CRMSCollCashSynRec=" + CRMSCollCashSynRec + " ]";
	}

}
