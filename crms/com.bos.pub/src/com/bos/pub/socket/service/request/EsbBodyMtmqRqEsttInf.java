package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRqEsttInf
 * @Description: 02002000004信贷信息维护 01项目信息建立
 * 
 */
@XmlRootElement(name = "EsttInf")
public class EsbBodyMtmqRqEsttInf implements Serializable{

	private static final long serialVersionUID = 1L;
	private String landKnd;// 土地性质 String(4)
	private String landUseRghtTp;// 土地使用权类型 String(4)
	private String landPpsCd;// 地类用途代码 String(4)
	private String landUseArea;// 土地使用权面积 String(20)
	private String landUseEndDt;// 土地使用权终止日期 String(8)

	public EsbBodyMtmqRqEsttInf() {

	}

	public String getLandKnd() {
		return landKnd;
	}
	@XmlElement(name = "LandKnd")
	public void setLandKnd(String landKnd) {
		this.landKnd = landKnd;
	}

	public String getLandUseRghtTp() {
		return landUseRghtTp;
	}
	@XmlElement(name = "LandUseRghtTp")
	public void setLandUseRghtTp(String landUseRghtTp) {
		this.landUseRghtTp = landUseRghtTp;
	}

	public String getLandPpsCd() {
		return landPpsCd;
	}
	@XmlElement(name = "LandPpsCd")
	public void setLandPpsCd(String landPpsCd) {
		this.landPpsCd = landPpsCd;
	}

	public String getLandUseArea() {
		return landUseArea;
	}
	@XmlElement(name = "LandUseArea")
	public void setLandUseArea(String landUseArea) {
		this.landUseArea = landUseArea;
	}

	public String getLandUseEndDt() {
		return landUseEndDt;
	}
	@XmlElement(name = "LandUseEndDt")
	public void setLandUseEndDt(String landUseEndDt) {
		this.landUseEndDt = landUseEndDt;
	}
	
}
