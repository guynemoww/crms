package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqLandUseRghtInfArray 土地使用权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqLandUseRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String landUseRghtCtfNo;          			//土地使用权证号                                      String(20)     Y
	private  String landUseRghtCtfIssuOfficNm;			//土地使用权证发证机关名称                String(200)    Y
	private  String landKnd;							//土地性质                                                    String(4)      Y
	private  String landGainMth;						//土地获取方式                                           String(4)      Y
	private  String landUseArea;						//土地使用权面积                                      String(20)     Y
	private  String landUseEndDt;						//土地使用权终止日期                              String(8)      Y
	private  String ctyNm;								//国家                                                              String(20)     Y
	private  String provNm;					            //省/直辖市                                                 String(20)     Y
	private  String cityNm;								//城市                                         	  String(20)     Y
	private  String cntyNm;								//区(县)                String(20)     Y
	private  String landAdr;							//土地坐落位置                                             String(200)    Y
	private  String landGainDt;							//土地取得日期                                             String(8)      Y
	private  String useRghtAgeLmt;						//使用权年限                                                  String(10)     Y
	
	
	public EsbBodyWmaRqLandUseRghtInfArray(){
		
	}


	public String getLandUseRghtCtfNo() {
		return landUseRghtCtfNo;
	}

	@XmlElement(name = "LandUseRghtCtfNo")
	public void setLandUseRghtCtfNo(String landUseRghtCtfNo) {
		this.landUseRghtCtfNo = landUseRghtCtfNo;
	}


	public String getLandUseRghtCtfIssuOfficNm() {
		return landUseRghtCtfIssuOfficNm;
	}

	@XmlElement(name = "LandUseRghtCtfIssuOfficNm")
	public void setLandUseRghtCtfIssuOfficNm(String landUseRghtCtfIssuOfficNm) {
		this.landUseRghtCtfIssuOfficNm = landUseRghtCtfIssuOfficNm;
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


	public String getLandAdr() {
		return landAdr;
	}
	
	@XmlElement(name = "LandAdr")
	public void setLandAdr(String landAdr) {
		this.landAdr = landAdr;
	}


	public String getLandGainDt() {
		return landGainDt;
	}

	@XmlElement(name = "LandGainDt")
	public void setLandGainDt(String landGainDt) {
		this.landGainDt = landGainDt;
	}


	public String getUseRghtAgeLmt() {
		return useRghtAgeLmt;
	}

	@XmlElement(name = "UseRghtAgeLmt")
	public void setUseRghtAgeLmt(String useRghtAgeLmt) {
		this.useRghtAgeLmt = useRghtAgeLmt;
	}

	
}
