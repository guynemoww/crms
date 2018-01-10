package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs0200300000401 
 * @Description: 02003000004信贷信息查询     01信贷资料目录树查询			
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A01 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String imgTplCd	 		;//	影像模板代码 	String(30)	Y	
	
	
	private List<EsbBodyMtmqRsRvlvNodeArray> esbBodyMtmqRsRvlvNodeArrays;   
	
	public String getImgTplCd() {
		return imgTplCd;
	}


	@XmlElement(name = "ImgTplCd")
	public void setImgTplCd(String imgTplCd) {
		this.imgTplCd = imgTplCd;
	}


	public List<EsbBodyMtmqRsRvlvNodeArray> getEsbBodyMtmqRsRvlvNodeArrays() {
		return esbBodyMtmqRsRvlvNodeArrays;
	}

	@XmlElement(name = "RvlvNodeArray")
	public void setEsbBodyMtmqRsRvlvNodeArrays(List<EsbBodyMtmqRsRvlvNodeArray> esbBodyMtmqRsRvlvNodeArrays) {
		this.esbBodyMtmqRsRvlvNodeArrays = esbBodyMtmqRsRvlvNodeArrays;
	}
	
	
	
	public EsbBodyMtmqRs02003000004A01() {
		
	}
	
	
}
