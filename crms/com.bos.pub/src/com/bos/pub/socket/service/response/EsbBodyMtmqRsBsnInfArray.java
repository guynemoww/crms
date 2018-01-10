package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRsBsnInfArray
 * @Description: 02003000004信贷信息查询 04业务申请查询
 * 
 */
public class EsbBodyMtmqRsBsnInfArray implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cstNm;// 客户名称 String(50)
	private String crCstNo;// 客户编号 String(20)
	private String idntTp;// 证件类型 String(10)
	private String identNo;// 证件号码 String(20)
	private String bsnNo;// 业务号码 String(20)
	private String bsnKnd;// 业务性质 String(4)// 01:单笔业务,02:综合授信,03:集团综合授信,04:小企业信贷,05:集团成员授信
	private String bsnTp;// 业务类型 String(10)
	private String imgBsnNo;// 影像业务编号 String(100)
	private String aplySt;// 申请状态 String(4) 01:未提交，02：审批中，03：生效
	
	public EsbBodyMtmqRsBsnInfArray() {
		
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

	public String getBsnNo() {
		return bsnNo;
	}
	@XmlElement(name = "BsnNo")
	public void setBsnNo(String bsnNo) {
		this.bsnNo = bsnNo;
	}

	public String getBsnKnd() {
		return bsnKnd;
	}
	@XmlElement(name = "BsnKnd")
	public void setBsnKnd(String bsnKnd) {
		this.bsnKnd = bsnKnd;
	}

	public String getBsnTp() {
		return bsnTp;
	}
	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}
	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}

	public String getAplySt() {
		return aplySt;
	}
	@XmlElement(name = "AplySt")
	public void setAplySt(String aplySt) {
		this.aplySt = aplySt;
	}
	
}
