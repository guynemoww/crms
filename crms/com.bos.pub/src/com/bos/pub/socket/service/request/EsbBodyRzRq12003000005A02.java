package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRq12003000005A02 
 * @Description: 12003000005银行基本信息查询	02管理机构信息查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyRzRq12003000005A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rgonCd;	//地区代码
	private String brId;	//机构代号
	
	public EsbBodyRzRq12003000005A02(){
		
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}
	
	public String getBrId() {
		return brId;
	}

	@XmlElement(name = "BrId")
	public void setBrId(String brId) {
		this.brId = brId;
	}
}
