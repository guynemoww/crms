package com.primeton.tsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SuperBosfxRs {
	@XmlElement(name="CommonRsHdr")
	public CommonRsHdr commonRsHdr;
	
	/**
	 * @param commonRsHdr 要设置的 commonRsHdr
	 */
	public void setCommonRsHdr(CommonRsHdr commonRsHdr) {
		this.commonRsHdr = commonRsHdr;
	}

	@Override
	public String toString() {
		return "SuperBosfx [CommonRsHdr=" + commonRsHdr + "]";
	}
	
}
