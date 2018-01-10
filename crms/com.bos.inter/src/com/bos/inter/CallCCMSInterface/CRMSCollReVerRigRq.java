package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * 多头校验实时接口
 * @author ZhuYongLun
 *
 */
public class CRMSCollReVerRigRq extends SuperBosfxRq {
	// 抵质押物类型
	@XmlElement(name = "SortType")
	public String SortType;

	// 押品识别号1
	@XmlElement(name = "IdentifyNo1")
	public String IdentifyNo1;

	// 押品识别号2
	@XmlElement(name = "IdentifyNo2")
	public String IdentifyNo2;

	// 押品识别名称
	@XmlElement(name = "PrdName")
	public String PrdName;

	// 创建人
	@XmlElement(name = "UserNum")
	public String UserNum;

	// 创建机构
	@XmlElement(name = "OrgNum")
	public String OrgNum;

	// 系统来源
	@XmlElement(name = "SystemSource")
	public String SystemSource;

	/**
	 * @param sortType
	 */
	public void setSortType(String sortType) {
		SortType = sortType;
	}

	/**
	 * @param identifyNo1
	 */
	public void setIdentifyNo1(String identifyNo1) {
		IdentifyNo1 = identifyNo1;
	}

	/**
	 * @param identifyNo2
	 */
	public void setIdentifyNo2(String identifyNo2) {
		IdentifyNo2 = identifyNo2;
	}

	/**
	 * @param prdName
	 */
	public void setPrdName(String prdName) {
		PrdName = prdName;
	}

	/**
	 * @param userNum
	 */
	public void setUserNum(String userNum) {
		UserNum = userNum;
	}

	/**
	 * @param orgNum
	 */
	public void setOrgNum(String orgNum) {
		OrgNum = orgNum;
	}

	/**
	 * @param systemSource
	 */
	public void setSystemSource(String systemSource) {
		SystemSource = systemSource;
	}

	@Override
	public String toString() {
		return "CRMSCollReVerRigRq [SortType=" + SortType + ",IdentifyNo1="
				+ IdentifyNo1 + ",IdentifyNo2=" + IdentifyNo2 + ",PrdName="
				+ PrdName + ",UserNum=" + UserNum + ",OrgNum=" + OrgNum
				+ ",SystemSource=" + SystemSource + "]";
	}

}
