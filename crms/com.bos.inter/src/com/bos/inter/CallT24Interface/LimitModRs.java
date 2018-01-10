
package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class LimitModRs  extends SuperBosfxRs{
//	@XmlElement(name="Limit")
//	public String Limit;//额度编号 
//	/**
//	 * @param contractNo 要设置的 contractNo
//	 */
//	public void setLimit(String limit) {
//		Limit = limit;
//	} 
	@Override
	public String toString() {
		return "LimitInfoRs [[commonRsHdr="+commonRsHdr+ "]";
	}
}

