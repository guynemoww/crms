package com.bos.pub.socket.service.response;

import javax.xml.bind.annotation.XmlElement;

public class EsbBodyMtmqRsdaycheckArray {
	
private String  crCstNo;	 //客户代号	String(10)
private String  cstNm;	 //客户名称	String(50)
private String  idntTp;	 //证件类型	String(10)
private String  identNo; //证件号码	String(20)
private String  chkNo;	 //检查编号	String(20)
private String  crExnBal;	//授信余额	Double(20,2)
private String  lastChkDt;	//上次检查日期	String(8)
private String  imgBsnNo;	//影像业务编号	String(100)

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
public String getChkNo() {
	return chkNo;
}
@XmlElement(name = "ChkNo")
public void setChkNo(String chkNo) {
	this.chkNo = chkNo;
}
public String getCrExnBal() {
	return crExnBal;
}
@XmlElement(name = "CrExnBal")
public void setCrExnBal(String crExnBal) {
	this.crExnBal = crExnBal;
}
public String getLastChkDt() {
	return lastChkDt;
}
@XmlElement(name = "LastChkDt")
public void setLastChkDt(String lastChkDt) {
	this.lastChkDt = lastChkDt;
}
public String getImgBsnNo() {
	return imgBsnNo;
}
@XmlElement(name = "ImgBsnNo")
public void setImgBsnNo(String imgBsnNo) {
	this.imgBsnNo = imgBsnNo;
}



}
