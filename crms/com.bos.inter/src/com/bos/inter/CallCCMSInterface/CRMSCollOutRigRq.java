package com.bos.inter.CallCCMSInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * 出库申请CRMS->CCMS
 * @author ZhuYongLun
 *
 */
public class CRMSCollOutRigRq extends SuperBosfxRq {
	@XmlElement(name="SystemSource")
	public String SystemSource;
	
	@XmlElement(name="CollStockRemovalSynRec")
	public List<CollStockRemovalSynRec> CollStockRemovalSynRec;
	
	/**
	 * @param collStockRemovalSynRec
	 */
	public void setCollStockRemovalSynRec(
			List<CollStockRemovalSynRec> collStockRemovalSynRec) {
		CollStockRemovalSynRec = collStockRemovalSynRec;
	}
	
	/**
	 * @param systemSource
	 */
	public void setSystemSource(String systemSource) {
		this.SystemSource = systemSource;
	}
	
	@Override
	public String toString() {
		return "CRMSCollOutRigRq [CollStockRemovalSynRec=" + CollStockRemovalSynRec + ",SystemSource=" + SystemSource + "]";
	}
}
