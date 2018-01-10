package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq0200300000401
 * @Description: 02003000004信贷信息查询     01信贷资料目录树查询			
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02003000004A01 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
	private String imgTplCd	 		;//	影像模板代码 	String(30)	Y	
	
	public EsbBodyMtmqRq02003000004A01() {
		
	}

	public String getImgTplCd() {
		return imgTplCd;
	}


	@XmlElement(name = "ImgTplCd")
	public void setImgTplCd(String imgTplCd) {
		this.imgTplCd = imgTplCd;
	}
	
}
