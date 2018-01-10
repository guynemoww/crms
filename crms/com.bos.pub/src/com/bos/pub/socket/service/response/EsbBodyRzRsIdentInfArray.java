package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyGJ05002000001A01 
 * @Description: 05002000001国际结算业务维护	01信用证开立通知
 *
 */
public class EsbBodyRzRsIdentInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String identTp;				//证件种类	0-港澳通行证、1-台湾通行证、2-护照、3-居民身份证、4-其他
	private String identNo;				//证件号码
	private String identEfftStrtDt;		//证件有效开始日期
	private String identEfftEndDt;		//证件有效截止日期
	
	public EsbBodyRzRsIdentInfArray(){
		
	}

	public String getIdentTp() {
		return identTp;
	}

	@XmlElement(name = "IdentTp")
	public void setIdentTp(String identTp) {
		this.identTp = identTp;
	}

	public String getIdentNo() {
		return identNo;
	}

	@XmlElement(name = "IdentNo")
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}

	public String getIdentEfftStrtDt() {
		return identEfftStrtDt;
	}

	@XmlElement(name = "IdentEfftStrtDt")
	public void setIdentEfftStrtDt(String identEfftStrtDt) {
		this.identEfftStrtDt = identEfftStrtDt;
	}

	public String getIdentEfftEndDt() {
		return identEfftEndDt;
	}

	@XmlElement(name = "IdentEfftEndDt")
	public void setIdentEfftEndDt(String identEfftEndDt) {
		this.identEfftEndDt = identEfftEndDt;
	}
}
