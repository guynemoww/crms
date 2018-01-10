package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * 押品出库申请响应CRMS->CCMS
 * @author ZhuYongLun
 *
 */
public class CRMSCollOutRigRs extends SuperBosfxRs {
	@XmlElement(name="retCode")
	public String retCode;
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
	@Override
	public String toString() {
		return "CRMSCollOutRigRs [commonRsHdr=" + commonRsHdr + "retCode=" + retCode  + "]";
	}
}
