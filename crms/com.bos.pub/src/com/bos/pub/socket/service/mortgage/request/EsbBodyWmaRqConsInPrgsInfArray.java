package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqConsInPrgsInfArray 在建工程
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqConsInPrgsInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String prjFileNo;          			//批准立项文件文号                String(20)    Y
	private  String landPlnPrmtNo;					//土地规划许可证号                String(20)    Y
	private  String consEngnPlnPrmtNo;				//建设工程规划许可证号       String(20)    Y
	private  String consEngnWrkPrmtNo;				//建设工程施工许可证号        String(20)    Y
	private  String ccy;							//币种                                            String(10)    Y
	private  double consBdgtCostAmt;				//工程预算造价                          String(20)    Y
	private  String prjStrtDt;						//项目开工日       		 String(8)     Y
	private  String expcEndDt;						//预算完工时间                         String(8)     Y
	private  double expcLandArea;					//预计建筑面积                         Double(26,8)  Y
	private  String ctyNm;							//国家                                           String(20)    Y
	private  String provNm;					        //省/直辖市                             String(20)    Y
	private  String cityNm;							//城市                                         String(20)    Y
	private  String cntyNm;							//区(县)          String(20)    Y
	private  String prjAdr;							//项目地址                                 String(300)   Y
	private  String statOwnLandUseRghtCtfNo;		//国有土地使用权证号          String(20)    Y
	private  String landUseArea;					//土地使用权面积                   String(20)    Y
	private  String landKnd;						//土地性质                                String(4)     Y
	private  String landGainMth;					//土地获取方式                       String(4)     Y
	private  String landUseEndDt;					//土地使用权终止日期        String(8)     Y
	private  String consUnitNm;						//施工单位名称                      String(200)   Y
	
	public EsbBodyWmaRqConsInPrgsInfArray(){
		
	}

	public String getPrjFileNo() {
		return prjFileNo;
	}

	@XmlElement(name = "PrjFileNo")
	public void setPrjFileNo(String prjFileNo) {
		this.prjFileNo = prjFileNo;
	}

	public String getLandPlnPrmtNo() {
		return landPlnPrmtNo;
	}

	@XmlElement(name = "LandPlnPrmtNo")
	public void setLandPlnPrmtNo(String landPlnPrmtNo) {
		this.landPlnPrmtNo = landPlnPrmtNo;
	}

	public String getConsEngnPlnPrmtNo() {
		return consEngnPlnPrmtNo;
	}

	@XmlElement(name = "ConsEngnPlnPrmtNo")
	public void setConsEngnPlnPrmtNo(String consEngnPlnPrmtNo) {
		this.consEngnPlnPrmtNo = consEngnPlnPrmtNo;
	}

	public String getConsEngnWrkPrmtNo() {
		return consEngnWrkPrmtNo;
	}

	@XmlElement(name = "ConsEngnWrkPrmtNo")
	public void setConsEngnWrkPrmtNo(String consEngnWrkPrmtNo) {
		this.consEngnWrkPrmtNo = consEngnWrkPrmtNo;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getConsBdgtCostAmt() {
		return consBdgtCostAmt;
	}

	@XmlElement(name = "ConsBdgtCostAmt")
	public void setConsBdgtCostAmt(double consBdgtCostAmt) {
		this.consBdgtCostAmt = consBdgtCostAmt;
	}

	public String getPrjStrtDt() {
		return prjStrtDt;
	}
	
	@XmlElement(name = "PrjStrtDt")
	public void setPrjStrtDt(String prjStrtDt) {
		this.prjStrtDt = prjStrtDt;
	}

	public String getExpcEndDt() {
		return expcEndDt;
	}

	@XmlElement(name = "ExpcEndDt")
	public void setExpcEndDt(String expcEndDt) {
		this.expcEndDt = expcEndDt;
	}

	public double getExpcLandArea() {
		return expcLandArea;
	}

	@XmlElement(name = "ExpcLandArea")
	public void setExpcLandArea(double expcLandArea) {
		this.expcLandArea = expcLandArea;
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

	public String getPrjAdr() {
		return prjAdr;
	}

	@XmlElement(name = "PrjAdr")
	public void setPrjAdr(String prjAdr) {
		this.prjAdr = prjAdr;
	}

	public String getStatOwnLandUseRghtCtfNo() {
		return statOwnLandUseRghtCtfNo;
	}

	@XmlElement(name = "StatOwnLandUseRghtCtfNo")
	public void setStatOwnLandUseRghtCtfNo(String statOwnLandUseRghtCtfNo) {
		this.statOwnLandUseRghtCtfNo = statOwnLandUseRghtCtfNo;
	}

	public String getLandUseArea() {
		return landUseArea;
	}

	@XmlElement(name = "LandUseArea")
	public void setLandUseArea(String landUseArea) {
		this.landUseArea = landUseArea;
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

	public String getLandUseEndDt() {
		return landUseEndDt;
	}

	@XmlElement(name = "LandUseEndDt")
	public void setLandUseEndDt(String landUseEndDt) {
		this.landUseEndDt = landUseEndDt;
	}

	public String getConsUnitNm() {
		return consUnitNm;
	}

	@XmlElement(name = "ConsUnitNm")
	public void setConsUnitNm(String consUnitNm) {
		this.consUnitNm = consUnitNm;
	}

	
}
