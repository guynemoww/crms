package com.primeton.tsl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SuperBosfxRq {
	private BaseVO baseVO;
	private String operateAuth;
	private String transSeqNo;
	private String oldSPRsUID;

	public BaseVO getBaseVO() {
		return baseVO;
	}

	@XmlElement(name = "CommonRqHdr")
	public void setBaseVO(BaseVO baseVO) {
		this.baseVO = baseVO;
	}

	public String getOperateAuth() {
		return operateAuth;
	}

	@XmlElement(name = "OperateAuth")
	public void setOperateAuth(String operateAuth) {
		this.operateAuth = operateAuth;
	}

	public String getTransSeqNo() {
		return transSeqNo;
	}

	@XmlElement(name = "TransSeqNo")
	public void setTransSeqNo(String transSeqNo) {
		this.transSeqNo = transSeqNo;
	}

	public String getOldSPRsUID() {
		return oldSPRsUID;
	}

	@XmlElement(name = "OldSPRsUID")
	public void setOldSPRsUID(String oldSPRsUID) {
		this.oldSPRsUID = oldSPRsUID;
	}

	@Override
	public String toString() {
		return "SuperBosfx [baseVO=" + baseVO + "]";
	}
}
