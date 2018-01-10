package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq07003000001A01 
 * @Description: 07003000001汇率牌价查询		01汇率查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq07003000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ccyTp;	//货币种类
	
	public EsbBodyGjRq07003000001A01(){
		
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}
}
