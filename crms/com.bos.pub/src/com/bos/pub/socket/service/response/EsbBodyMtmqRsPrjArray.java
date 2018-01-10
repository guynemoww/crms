package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRq0200300000401
 * @Description: 02003000004信贷信息查询 02项目信息查询
 * 
 */
public class EsbBodyMtmqRsPrjArray implements Serializable {

	private static final long serialVersionUID = 1L;
	private String crCstNo;// 客户代号String(10)
	private String cstNm;// 客户名称String(50)
	private String idntTp;// 证件类型String(10)
	private String identNo;// 证件号码String(20)
	private String imgBsnNo; // 影像业务编号 String(100)
	private String prjNo; // 项目编号 String(32)
	private String prjNm; // 项目名称 String(100)
	private String crtDt; // 创建日期 String(8)

	public EsbBodyMtmqRsPrjArray() {
	}

	public String getCrCstNo() {
		return crCstNo;
	}

	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getIdntTp() {
		return idntTp;
	}

	@XmlElement(name = "IdntTp")
	public void setIdntTp(String idntTp) {
		this.idntTp = idntTp;
	}

	public String getIdentNo() {
		return identNo;
	}

	@XmlElement(name = "IdentNo")
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}

	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}

	public String getPrjNo() {
		return prjNo;
	}

	@XmlElement(name = "PrjNo")
	public void setPrjNo(String prjNo) {
		this.prjNo = prjNo;
	}

	public String getPrjNm() {
		return prjNm;
	}

	@XmlElement(name = "PrjNm")
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}

	public String getCrtDt() {
		return crtDt;
	}

	@XmlElement(name = "CrtDt")
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

}
