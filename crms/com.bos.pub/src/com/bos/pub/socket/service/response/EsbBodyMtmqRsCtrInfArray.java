package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyMtmqRsCtrInfArray
 * @Description: 02003000004信贷信息查询 05合同信息查询
 * 
 */
public class EsbBodyMtmqRsCtrInfArray implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cstNm;// 客户名称 String(50)
	private String idntTp;// 证件类型 String(10)
	private String identNo;// 证件号码 String(20)
	private String ctrNo;// 合同号 String(20)
	private String bsnTp;// 业务类型 String(10)
	private String ctrIttDt;// 合同起始日期 String(8) YYYYMMDD
	private String ctrExpDt;// 合同到期日 String(8) YYYYMMDD
	private String ctrAmt;// 合同金额 Double(20,2)
	private String imgBsnNo;// 影像业务编号 String(100)
	private String ctrSt;// 合同状态 String(4) 01:未提交，02：审批中，03：已生效
	

	public EsbBodyMtmqRsCtrInfArray() {
		
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

	public String getCtrNo() {
		return ctrNo;
	}
	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getBsnTp() {
		return bsnTp;
	}
	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getCtrIttDt() {
		return ctrIttDt;
	}
	@XmlElement(name = "CtrIttDt")
	public void setCtrIttDt(String ctrIttDt) {
		this.ctrIttDt = ctrIttDt;
	}

	public String getCtrExpDt() {
		return ctrExpDt;
	}
	@XmlElement(name = "CtrExpDt")
	public void setCtrExpDt(String ctrExpDt) {
		this.ctrExpDt = ctrExpDt;
	}

	public String getCtrAmt() {
		return ctrAmt;
	}
	@XmlElement(name = "CtrAmt")
	public void setCtrAmt(String ctrAmt) {
		this.ctrAmt = ctrAmt;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}
	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}

	public String getCtrSt() {
		return ctrSt;
	}
	@XmlElement(name = "CtrSt")
	public void setCtrSt(String ctrSt) {
		this.ctrSt = ctrSt;
	}

}
