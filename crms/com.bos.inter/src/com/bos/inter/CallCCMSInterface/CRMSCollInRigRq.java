package com.bos.inter.CallCCMSInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * 入库申请请求CRMS->CCMS
 * @author ZhuYongLun
 *
 */
public class CRMSCollInRigRq extends SuperBosfxRq {
	@XmlElement(name="SystemSource")
	public String SystemSource;
	
	@XmlElement(name="CollLaidUpSynRec")
	public List<CollLaidUpSynRec> CollLaidUpSynRec;
	
	/**
	 * @param collLaidUpSynRec
	 */
	public void setCollLaidUpSynRec(List<CollLaidUpSynRec> collLaidUpSynRec) {
		CollLaidUpSynRec = collLaidUpSynRec;
	}
	
	/**
	 * @param systemSource
	 */
	public void setSystemSource(String systemSource) {
		this.SystemSource = systemSource;
	}
	
	@Override
	public String toString() {
		return "CollLaidUpSynRq [CollLaidUpSynRec=" + CollLaidUpSynRec + ",SystemSource=" + SystemSource + "]";
	}
}
