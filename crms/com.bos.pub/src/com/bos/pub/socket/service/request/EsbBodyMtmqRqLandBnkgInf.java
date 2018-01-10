package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRqLandBnkgInf
 * @Description: 02002000004信贷信息维护 01项目信息建立
 * 
 */
public class EsbBodyMtmqRqLandBnkgInf implements Serializable {

	private static final long serialVersionUID = 1L;
	private String landBnkgPlnReplyFileNo;// 土地储备计划批复文号 String(20)
	private String landBnkgPlnReplyOfficNm;// 土地储备计划批复单位名称 String(200)
	private String totArea;// 总面积 String(20)

	public EsbBodyMtmqRqLandBnkgInf() {

	}

	public String getLandBnkgPlnReplyFileNo() {
		return landBnkgPlnReplyFileNo;
	}
	@XmlElement(name = "LandBnkgPlnReplyFileNo")
	public void setLandBnkgPlnReplyFileNo(String landBnkgPlnReplyFileNo) {
		this.landBnkgPlnReplyFileNo = landBnkgPlnReplyFileNo;
	}

	public String getLandBnkgPlnReplyOfficNm() {
		return landBnkgPlnReplyOfficNm;
	}
	@XmlElement(name = "LandBnkgPlnReplyOfficNm")
	public void setLandBnkgPlnReplyOfficNm(String landBnkgPlnReplyOfficNm) {
		this.landBnkgPlnReplyOfficNm = landBnkgPlnReplyOfficNm;
	}

	public String getTotArea() {
		return totArea;
	}
	@XmlElement(name = "TotArea")
	public void setTotArea(String totArea) {
		this.totArea = totArea;
	}
	
}
