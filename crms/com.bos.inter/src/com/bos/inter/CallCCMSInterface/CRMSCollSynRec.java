package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Administrator
 *
 */
public class CRMSCollSynRec {
	@XmlElement(name="SortType")
	public String SortType;//担保品类型
	
	@XmlElement(name="OutSortType")
	public String OutSortType;//外围系统自身的押品类型
 
	public void setOutSortType(String OutSortType) {
		this.OutSortType = OutSortType;
	}
	
	public void setSortType(String SortType) {
		this.SortType = SortType;
	}
	
	@Override
	public String toString() {
		return "CRMSCollSynRec[SortType=" + SortType + ",OutSortType=" + OutSortType;
	}

}
