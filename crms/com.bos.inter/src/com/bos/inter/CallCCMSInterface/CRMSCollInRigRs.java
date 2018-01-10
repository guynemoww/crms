package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * 押品入库申请响应CRMS->CCMS
 * @author ZhuYongLun
 *
 */
public class CRMSCollInRigRs extends SuperBosfxRs {
	
	@Override
	public String toString() {
		return "CRMSCollInRigRs [CommonRsHdr=" + commonRsHdr + "]";
	}
}
