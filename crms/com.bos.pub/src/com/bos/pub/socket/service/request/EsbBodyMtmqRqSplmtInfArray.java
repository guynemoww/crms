package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRqSplmtInfArray 
 * @Description: 12002000013客户信息开户维护     10对公客户信息维护				
 *
 */
public class EsbBodyMtmqRqSplmtInfArray implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String ctyCd	;//国家编码	String(3)	Y	
	private String provCd	;//省份代码	String(10)	Y	
	private String cityCd;//	城市代码	String(10)	Y	
	private String dstcCd;//	区县代码	String(10)	Y	
	private String strAdr;//	街道地址	String(40)	Y
	
	public EsbBodyMtmqRqSplmtInfArray(){
		
	}
	
	public String getCtyCd() {
		return ctyCd;
	}
	@XmlElement(name = "CtyCd")
	public void setCtyCd(String ctyCd) {
		this.ctyCd = ctyCd;
	}
	
	public String getProvCd() {
		return provCd;
	}
	@XmlElement(name = "ProvCd")
	public void setProvCd(String provCd) {
		this.provCd = provCd;
	}
	
	public String getCityCd() {
		return cityCd;
	}
	@XmlElement(name = "CityCd")
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}
	
	public String getDstcCd() {
		return dstcCd;
	}
	@XmlElement(name = "DstcCd")
	public void setDstcCd(String dstcCd) {
		this.dstcCd = dstcCd;
	}
	
	public String getStrAdr() {
		return strAdr;
	}
	@XmlElement(name = "StrAdr")
	public void setStrAdr(String strAdr) {
		this.strAdr = strAdr;
	}
}
