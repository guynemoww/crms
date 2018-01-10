package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyEcifRqCtcInfArray 
 * @Description: 客户信息开户维护	联系信息数组
 *
 */
public class EsbBodyEcifRqGKeyPrsnInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String keyPrsnCd;	//关键人编号	String(16)		关键标识ID，后台返回，信息修改时，原值上传；新增为空
	private String shrhlrTp;	//股东类型		String(1)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String shrhlrNm;	//股东姓名		String(50)	N	
	private String nation;		//民族		String(4)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String gndInd;		//性别标志		String(1)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ntntyCd;		//国籍代码		String(3)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String marriageCd;	//婚姻状况代码	String(5)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String birthDate;	//出生日期		String(8)	N	yyyymmdd
	private String idntTp;		//证件类型		String(10)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String identNo;		//证件号码		String(20)	N	
	private String ocpCd;		//职业代码		String(5)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String highEdct;	//最高学历		String(4)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ctcAdr;		//联系地址		String(30)	N	
	private String ctcNo;		//联系号码		String(100)	N	
	private String pstCd2;		//邮编		String(6)	N	
	private String offcTel;		//办公电话		String(30)	N	
	private String posCd;		//职务代码		String(5)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String qQNo;		//QQ		String(30)	N	
	private String wChtNo;		//微信		String(30)	N	
	private String email;		//电子邮箱		String(30)	N	
	private String keyPrsnSt;	//关键人状态	String(5)		" 01 正常(默认) 08 删除"
//	private String fmSysInd;	//来源系统标志	String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	
	public EsbBodyEcifRqGKeyPrsnInfArray(){
		
	}

	public String getKeyPrsnCd() {
		return keyPrsnCd;
	}
	
	@XmlElement(name = "KeyPrsnCd")
	public void setKeyPrsnCd(String keyPrsnCd) {
		this.keyPrsnCd = keyPrsnCd;
	}

	public String getShrhlrTp() {
		return shrhlrTp;
	}

	@XmlElement(name = "ShrhlrTp")
	public void setShrhlrTp(String shrhlrTp) {
		this.shrhlrTp = shrhlrTp;
	}

	public String getShrhlrNm() {
		return shrhlrNm;
	}

	@XmlElement(name = "ShrhlrNm")
	public void setShrhlrNm(String shrhlrNm) {
		this.shrhlrNm = shrhlrNm;
	}

	public String getNation() {
		return nation;
	}

	@XmlElement(name = "Nation")
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGndInd() {
		return gndInd;
	}

	@XmlElement(name = "GndInd")
	public void setGndInd(String gndInd) {
		this.gndInd = gndInd;
	}

	public String getNtntyCd() {
		return ntntyCd;
	}

	@XmlElement(name = "NtntyCd")
	public void setNtntyCd(String ntntyCd) {
		this.ntntyCd = ntntyCd;
	}

	public String getMarriageCd() {
		return marriageCd;
	}

	@XmlElement(name = "MarriageCd")
	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	public String getBirthDate() {
		return birthDate;
	}

	@XmlElement(name = "BirthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public String getOcpCd() {
		return ocpCd;
	}

	@XmlElement(name = "OcpCd")
	public void setOcpCd(String ocpCd) {
		this.ocpCd = ocpCd;
	}

	public String getHighEdct() {
		return highEdct;
	}

	@XmlElement(name = "HighEdct")
	public void setHighEdct(String highEdct) {
		this.highEdct = highEdct;
	}

	public String getCtcAdr() {
		return ctcAdr;
	}

	@XmlElement(name = "CtcAdr")
	public void setCtcAdr(String ctcAdr) {
		this.ctcAdr = ctcAdr;
	}

	public String getCtcNo() {
		return ctcNo;
	}

	@XmlElement(name = "CtcNo")
	public void setCtcNo(String ctcNo) {
		this.ctcNo = ctcNo;
	}

	public String getPstCd2() {
		return pstCd2;
	}

	@XmlElement(name = "PstCd2")
	public void setPstCd2(String pstCd2) {
		this.pstCd2 = pstCd2;
	}

	public String getOffcTel() {
		return offcTel;
	}

	@XmlElement(name = "OffcTel")
	public void setOffcTel(String offcTel) {
		this.offcTel = offcTel;
	}

	public String getPosCd() {
		return posCd;
	}

	@XmlElement(name = "PosCd")
	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	public String getqQNo() {
		return qQNo;
	}

	@XmlElement(name = "QQNo")
	public void setqQNo(String qQNo) {
		this.qQNo = qQNo;
	}

	public String getwChtNo() {
		return wChtNo;
	}

	@XmlElement(name = "WChtNo")
	public void setwChtNo(String wChtNo) {
		this.wChtNo = wChtNo;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement(name = "Email")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeyPrsnSt() {
		return keyPrsnSt;
	}

	@XmlElement(name = "KeyPrsnSt")
	public void setKeyPrsnSt(String keyPrsnSt) {
		this.keyPrsnSt = keyPrsnSt;
	}

/*	public String getFmSysInd() {
		return fmSysInd;
	}

	@XmlElement(name = "FmSysInd")
	public void setFmSysInd(String fmSysInd) {
		this.fmSysInd = fmSysInd;
	}*/
}
