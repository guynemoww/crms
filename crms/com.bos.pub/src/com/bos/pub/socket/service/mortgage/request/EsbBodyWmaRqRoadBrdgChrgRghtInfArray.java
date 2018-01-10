package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqRoadBrdgChrgRghtInfArray   路桥等收费权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqRoadBrdgChrgRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String chrgInstNm;          			//收费机构名称                   String(80)       Y
	private  String chrgInstEntpKnd;				//收费机构企业性质          String(10)       Y
	private  String ctrNo;							//合同号                                String(20)       Y
	private  String rghtStrtDt;						//权利起始日                       String(8)        Y
	private  String rghtEndDt;						//权利终止日                       String(8)        Y
	
	public EsbBodyWmaRqRoadBrdgChrgRghtInfArray(){
		
	}

	public String getChrgInstNm() {
		return chrgInstNm;
	}

	@XmlElement(name = "ChrgInstNm")
	public void setChrgInstNm(String chrgInstNm) {
		this.chrgInstNm = chrgInstNm;
	}

	public String getChrgInstEntpKnd() {
		return chrgInstEntpKnd;
	}

	@XmlElement(name = "ChrgInstEntpKnd")
	public void setChrgInstEntpKnd(String chrgInstEntpKnd) {
		this.chrgInstEntpKnd = chrgInstEntpKnd;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getRghtStrtDt() {
		return rghtStrtDt;
	}

	@XmlElement(name = "RghtStrtDt")
	public void setRghtStrtDt(String rghtStrtDt) {
		this.rghtStrtDt = rghtStrtDt;
	}

	public String getRghtEndDt() {
		return rghtEndDt;
	}

	@XmlElement(name = "RghtEndDt")
	public void setRghtEndDt(String rghtEndDt) {
		this.rghtEndDt = rghtEndDt;
	}
	
}
