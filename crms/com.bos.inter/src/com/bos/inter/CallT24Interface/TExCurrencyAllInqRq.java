package com.bos.inter.CallT24Interface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

public class TExCurrencyAllInqRq extends SuperBosfxRq {
	@XmlElement(name = "CcyId")
	public String CcyId; // 币种

	@XmlElement(name = "All.Flag")
	public String AllFlag; // 输入"Y"则返回全部币种信息

 
	public String toString() {

		return "TExCurrencyAllInqRq [CcyId=" + CcyId + ", AllFlag=" + AllFlag
				+ "]";
	}


	public void setAllFlag(String allFlag) {
		AllFlag = allFlag;
	}


	public void setCcyId(String ccyId) {
		CcyId = ccyId;
	}
}
