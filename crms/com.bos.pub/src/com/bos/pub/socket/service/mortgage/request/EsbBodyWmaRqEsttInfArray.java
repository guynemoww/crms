package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqEsttInfArray   房地产
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqEsttInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String hsProprtCtfSt;          //房产证状态                String(4)    Y
	private  String hsOwnCtfNo;				//房屋所有权证号       String(32)    Y
	private  String rgstDt;					//注册日期                     YYYYMMDD    Y
	private  String ctyNm;					//国家                              String(20)    Y
	private  String provNm;					//省/直辖市                  String(20)    Y
	private  String cityNm;					//城市                              String(20)    Y
	private  String cntyNm;					//区(县)       String(20)    Y
	private  String rgstAdr;				//注册地址                      String(60)    Y
	private  double stcArea;				//建筑面积                     Double(26,8)    Y
	private  String hsStcCd;				//房屋结构                     String(4)    Y
	private  String landUseRghtCtfNo;		//土地使用权证号       String(20)    N
	private  String landKnd;				//土地性质                     String(4)    N
	private  String landGainMth;			//土地获取方式             String(4)    Y
	
	public EsbBodyWmaRqEsttInfArray(){
		
	}

	public String getHsProprtCtfSt() {
		return hsProprtCtfSt;
	}

	@XmlElement(name = "HsProprtCtfSt")
	public void setHsProprtCtfSt(String hsProprtCtfSt) {
		this.hsProprtCtfSt = hsProprtCtfSt;
	}

	public String getHsOwnCtfNo() {
		return hsOwnCtfNo;
	}

	@XmlElement(name = "HsOwnCtfNo")
	public void setHsOwnCtfNo(String hsOwnCtfNo) {
		this.hsOwnCtfNo = hsOwnCtfNo;
	}

	public String getRgstDt() {
		return rgstDt;
	}

	@XmlElement(name = "RgstDt")
	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}

	public String getCtyNm() {
		return ctyNm;
	}

	@XmlElement(name = "CtyNm")
	public void setCtyNm(String ctyNm) {
		this.ctyNm = ctyNm;
	}

	public String getProvNm() {
		return provNm;
	}

	@XmlElement(name = "ProvNm")
	public void setProvNm(String provNm) {
		this.provNm = provNm;
	}

	public String getCityNm() {
		return cityNm;
	}

	@XmlElement(name = "CityNm")
	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	public String getCntyNm() {
		return cntyNm;
	}

	@XmlElement(name = "CntyNm")
	public void setCntyNm(String cntyNm) {
		this.cntyNm = cntyNm;
	}

	public String getRgstAdr() {
		return rgstAdr;
	}

	@XmlElement(name = "RgstAdr")
	public void setRgstAdr(String rgstAdr) {
		this.rgstAdr = rgstAdr;
	}

	public double getStcArea() {
		return stcArea;
	}

	@XmlElement(name = "StcArea")
	public void setStcArea(double stcArea) {
		this.stcArea = stcArea;
	}

	public String getHsStcCd() {
		return hsStcCd;
	}

	@XmlElement(name = "HsStcCd")
	public void setHsStcCd(String hsStcCd) {
		this.hsStcCd = hsStcCd;
	}

	public String getLandUseRghtCtfNo() {
		return landUseRghtCtfNo;
	}

	@XmlElement(name = "LandUseRghtCtfNo")
	public void setLandUseRghtCtfNo(String landUseRghtCtfNo) {
		this.landUseRghtCtfNo = landUseRghtCtfNo;
	}

	public String getLandKnd() {
		return landKnd;
	}

	@XmlElement(name = "LandKnd")
	public void setLandKnd(String landKnd) {
		this.landKnd = landKnd;
	}

	public String getLandGainMth() {
		return landGainMth;
	}

	@XmlElement(name = "LandGainMth")
	public void setLandGainMth(String landGainMth) {
		this.landGainMth = landGainMth;
	}

	
	
}
