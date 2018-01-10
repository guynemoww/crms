package com.bos.inter.CallCCMSInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * 押品出入库撤销 CRMS->CCMS
 * @author ZhuYongLun
 *
 */
public class CRMSCollInBackRigRq extends SuperBosfxRq {
	@XmlElement(name = "SystemSource")
	public String SystemSource;

	@XmlElement(name = "OperateType")
	public String OperateType;

	@XmlElement(name = "CollCancelSynRec")
	public List<CollCancelSynRec> CollCancelSynRec;

	/**
	 * @param collCancelSynRec
	 */
	public void setCollCancelSynRec(List<CollCancelSynRec> collCancelSynRec) {
		CollCancelSynRec = collCancelSynRec;
	}

	/**
	 * @param systemSource
	 */
	public void setSystemSource(String systemSource) {
		SystemSource = systemSource;
	}
	
	/**
	 * @param operateType
	 */
	public void setOperateType(String operateType) {
		OperateType = operateType;
	}

	@Override
	public String toString() {
		return "CollCancelSynRq [CollCancelSynRec=" + CollCancelSynRec
				+ ",SystemSource=" + SystemSource + ",OperateType="
				+ OperateType + "]";
	}
	
}
