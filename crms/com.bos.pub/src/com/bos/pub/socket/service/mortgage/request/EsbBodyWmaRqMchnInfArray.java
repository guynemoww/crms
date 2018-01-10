package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqMchnInfArray   机械设备信息
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqMchnInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String mchnNo;          	//机器设备编号              String(20)    Y
	private  String mchnTp;				//设备型号     	  String(20)    Y
	private  String pdnDt;				//出厂时间                        String(8)     N
	private  String ccy;				//币种                                 String(10)    Y
	private  double buyCostAmt;			//购入价格                        Double(26,8)  Y
	
	
	public EsbBodyWmaRqMchnInfArray(){
		
	}
	

	public String getMchnNo() {
		return mchnNo;
	}

	@XmlElement(name = "MchnNo")
	public void setMchnNo(String mchnNo) {
		this.mchnNo = mchnNo;
	}


	public String getMchnTp() {
		return mchnTp;
	}

	@XmlElement(name = "MchnTp")
	public void setMchnTp(String mchnTp) {
		this.mchnTp = mchnTp;
	}


	public String getPdnDt() {
		return pdnDt;
	}

	@XmlElement(name = "PdnDt")
	public void setPdnDt(String pdnDt) {
		this.pdnDt = pdnDt;
	}


	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public double getBuyCostAmt() {
		return buyCostAmt;
	}

	@XmlElement(name = "BuyCostAmt")
	public void setBuyCostAmt(double buyCostAmt) {
		this.buyCostAmt = buyCostAmt;
	}
}
