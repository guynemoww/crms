package com.bos.inter.CallT24Interface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class TNbosIlcOpenHLDRs extends SuperBosfxRs {
	//MD流水
	@XmlElement(name="ContractNo")
	public String ContractNo;
	//待授权信息
	@XmlElement(name="OverRideRec")
	public List<OverRideRec> overRideRec;
	
	/**
	 * @param contractNO 要设置的 contractNO
	 */
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}

	/**
	 * @param overRideRec 要设置的 overRideRec
	 */
	public void setOverRideRec(List<OverRideRec> overRideRec) {
		this.overRideRec = overRideRec;
	}

	@Override
	public String toString() {
		return "TNbosIlcOpenHLDRs [commonRsHdr="+commonRsHdr+"contractNo=" + ContractNo + ", overRideRec="
				+ overRideRec  + "]";
	}
}
