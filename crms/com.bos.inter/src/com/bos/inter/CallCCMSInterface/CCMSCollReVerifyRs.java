package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRs;

/**
 * 抵押物多头校验JavaBen
 * CRMS系统接受CCMS(押品子系统)的反馈报文
 * @author menglei 2014-5-21
 *
 */
public class CCMSCollReVerifyRs extends SuperBosfxRs{
	@XmlElement(name="retCode")
	public String retCode;
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
	@Override
	public String toString() {
		return "TbGrtGuarantybasicRs [commonRsHdr="+commonRsHdr+"retCode=" + retCode  + "]";

	}
	

}
