package com.bos.pub.socket.service.response;

import javax.xml.bind.annotation.XmlElement;

public class EsbBodyMtmqRsyqSummaryArray {
	
private String  crCstNo;	 //客户代号	String(10)
private String  cstNm;	 //客户名称	String(50)
private String  bsnTp;	 //证件类型	String(10)
private String  ctrDbtNo;	//合同借据号	String(20)
private String  ctrDbtAmt;	//合同借据金额	Double(20,8)
private String  oduePrePaidPnpAmt;	//逾期垫款本金金额	Double(20,8)
private String  odueIntAmt;	//逾期利息金额	Double(20,8)
private String  intPnyAmt;	//罚息金额	Double(20,8)
private String  oduePrePaidDayNum;	//逾期垫款天数	String(10)
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
public String getBsnTp() {
	return bsnTp;
}
@XmlElement(name = "BsnTp")

public void setBsnTp(String bsnTp) {
	this.bsnTp = bsnTp;
}
public String getCtrDbtNo() {
	return ctrDbtNo;
}
@XmlElement(name = "CtrDbtNo")

public void setCtrDbtNo(String ctrDbtNo) {
	this.ctrDbtNo = ctrDbtNo;
}
public String getCtrDbtAmt() {
	return ctrDbtAmt;
}
@XmlElement(name = "CtrDbtAmt")

public void setCtrDbtAmt(String ctrDbtAmt) {
	this.ctrDbtAmt = ctrDbtAmt;
}
public String getOduePrePaidPnpAmt() {
	return oduePrePaidPnpAmt;
}
@XmlElement(name = "OduePrePaidPnpAmt")

public void setOduePrePaidPnpAmt(String oduePrePaidPnpAmt) {
	this.oduePrePaidPnpAmt = oduePrePaidPnpAmt;
}
public String getOdueIntAmt() {
	return odueIntAmt;
}
@XmlElement(name = "OdueIntAmt")

public void setOdueIntAmt(String odueIntAmt) {
	this.odueIntAmt = odueIntAmt;
}
public String getIntPnyAmt() {
	return intPnyAmt;
}
@XmlElement(name = "IntPnyAmt")

public void setIntPnyAmt(String intPnyAmt) {
	this.intPnyAmt = intPnyAmt;
}
public String getOduePrePaidDayNum() {
	return oduePrePaidDayNum;
}
@XmlElement(name = "OduePrePaidDayNum")

public void setOduePrePaidDayNum(String oduePrePaidDayNum) {
	this.oduePrePaidDayNum = oduePrePaidDayNum;
}
public String getImgBsnNo() {
	return imgBsnNo;
}
@XmlElement(name = "ImgBsnNo")

public void setImgBsnNo(String imgBsnNo) {
	this.imgBsnNo = imgBsnNo;
}

}
