package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqFrchsRghtInfArray   特许经营权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqFrchsRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String frchsStrtDt;          			//特许经营起始日              	    String(8)       Y
	private  String frchsEndDt;						//特许经营终止日       	    String(8)       Y
	private  String taxiBsnCtfNo;					//出租车经营许可证号                       String(20)      Y
	private  String hghwyPsngrTransptnCtfNo;		//公路客运线路经营许可证              String(20)      Y
	private  String frchsRghtDsc;					//经营权信息描述                                String(200)     Y
	
	public EsbBodyWmaRqFrchsRghtInfArray(){
		
	}

	public String getFrchsStrtDt() {
		return frchsStrtDt;
	}

	@XmlElement(name = "FrchsStrtDt")
	public void setFrchsStrtDt(String frchsStrtDt) {
		this.frchsStrtDt = frchsStrtDt;
	}

	public String getFrchsEndDt() {
		return frchsEndDt;
	}

	@XmlElement(name = "FrchsEndDt")
	public void setFrchsEndDt(String frchsEndDt) {
		this.frchsEndDt = frchsEndDt;
	}

	public String getTaxiBsnCtfNo() {
		return taxiBsnCtfNo;
	}

	@XmlElement(name = "TaxiBsnCtfNo")
	public void setTaxiBsnCtfNo(String taxiBsnCtfNo) {
		this.taxiBsnCtfNo = taxiBsnCtfNo;
	}

	public String getHghwyPsngrTransptnCtfNo() {
		return hghwyPsngrTransptnCtfNo;
	}

	@XmlElement(name = "HghwyPsngrTransptnCtfNo")
	public void setHghwyPsngrTransptnCtfNo(String hghwyPsngrTransptnCtfNo) {
		this.hghwyPsngrTransptnCtfNo = hghwyPsngrTransptnCtfNo;
	}

	public String getFrchsRghtDsc() {
		return frchsRghtDsc;
	}

	@XmlElement(name = "FrchsRghtDsc")
	public void setFrchsRghtDsc(String frchsRghtDsc) {
		this.frchsRghtDsc = frchsRghtDsc;
	}
}
