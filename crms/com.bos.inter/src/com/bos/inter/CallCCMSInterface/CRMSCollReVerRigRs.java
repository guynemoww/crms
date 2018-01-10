package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class CRMSCollReVerRigRs extends SuperBosfxRs {
	// 押品系统的押品编号
	@XmlElement(name = "SuretyId")
	public String SuretyId;

	// 校验结果标志
	@XmlElement(name = "RetCode")
	public String RetCode;

	/**
	 * @param suretyId
	 */
	public void setSuretyId(String suretyId) {
		SuretyId = suretyId;
	}

	/**
	 * @param retCode
	 */
	public void setRetCode(String retCode) {
		RetCode = retCode;
	}

	@Override
	public String toString() {
		return "CRMSCollReVerRigRs [SuretyId=" + SuretyId + ",RetCode="
				+ RetCode + "]";
	}

}
