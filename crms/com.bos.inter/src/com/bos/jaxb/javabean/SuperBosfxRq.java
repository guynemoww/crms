package com.bos.jaxb.javabean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SuperBosfxRq {
	@XmlElement(name = "CommonRqHdr")
	public CommonRqHdr CommonRqHdr;

	@XmlElement(name = "OperateAuth")
	public String OperateAuth;

	@XmlElement(name = "TransSeqNo")
	public String TransSeqNo;

	@XmlElement(name = "OldSPRsUID")
	public String OldSPRsUID;

	public void setCommonRqHdr(CommonRqHdr CommonRqHdr) {
		this.CommonRqHdr = CommonRqHdr;
	}

	/**
	 * @param oldSPRsUID
	 *            要设置的 oldSPRsUID
	 */
	public void setOldSPRsUID(String oldSPRsUID) {
		OldSPRsUID = oldSPRsUID;
	}

	/**
	 * @param operateAuth
	 *            要设置的 operateAuth
	 */
	public void setOperateAuth(String operateAuth) {
		OperateAuth = operateAuth;
	}

	/**
	 * @param transSeqNo
	 *            要设置的 transSeqNo
	 */
	public void setTransSeqNo(String transSeqNo) {
		TransSeqNo = transSeqNo;
	}

	@Override
	public String toString() {
		return "SuperBosfx [CommonRqHdr=" + CommonRqHdr + "]";
	}
}
