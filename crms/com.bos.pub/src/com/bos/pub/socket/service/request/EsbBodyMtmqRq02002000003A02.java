package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000003A02
 * @Description: 02002000003 移动信贷公共管理  02影像补录
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02002000003A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;// 客户经理 String(8)
	private String ittbrId;// 起始机构号 String(10)
	private String crCstNo;// 客户代号 String(10)
	private String bsnNo;// 业务号码	String(20)
	private String imgTplCd;// 影像模板代码	String(30)
	private String imgBsnNo;// 影像业务编号	String(100)

	
	
	public EsbBodyMtmqRq02002000003A02() {
		
	}



	public String getCstMgrNo() {
		return cstMgrNo;
	}


	@XmlElement(name = "CstMgrNo")
	public void setCstMgrNo(String cstMgrNo) {
		this.cstMgrNo = cstMgrNo;
	}



	public String getIttbrId() {
		return ittbrId;
	}


	@XmlElement(name = "IttbrId")
	public void setIttbrId(String ittbrId) {
		this.ittbrId = ittbrId;
	}


	public String getCrCstNo() {
		return crCstNo;
	}
	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
	}


	public String getBsnNo() {
		return bsnNo;
	}


	@XmlElement(name = "BsnNo")
	public void setBsnNo(String bsnNo) {
		this.bsnNo = bsnNo;
	}



	public String getImgTplCd() {
		return imgTplCd;
	}


	@XmlElement(name = "ImgTplCd")
	public void setImgTplCd(String imgTplCd) {
		this.imgTplCd = imgTplCd;
	}



	public String getImgBsnNo() {
		return imgBsnNo;
	}


	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}



	
	


	
}
