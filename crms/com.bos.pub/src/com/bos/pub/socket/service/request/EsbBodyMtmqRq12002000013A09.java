package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq12002000013A09
 * @Description: 12002000013客户信息开户维护 09对私/微贷客户信息维护
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq12002000013A09 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cstMgrNo; // 客户经理 String(8) Y
	private String cstMgrInstNo; // 客户经理机构号 String(10) Y
	private String oprTp; // 操作类型 String(3) Y "add:新增 update:修改"
	private String crCstNo; // 客户代号 String(10) N 当修改操作时，此字段必输
	private String idntTp; // 证件类型 String(10) Y
	private String identNo; // 证件号码 String(20) Y 如果是身份证，临时身份证需要校验合法性
	private String cstNm; // 客户名称 String(50) Y
	private String crExnCstFlg; // 授信客户标识 String(4) Y 默认否，当为是时，不可修改
	private String thrdPartyCstFlg; // 第三方客户标识 String(4) Y 默认值0，自然人客户特有字段
	private String gndInd; // 性别标志 String(4) Y
	private String birthDate; // 出生日期 String(8) Y YYYYMMDD
	private String ntntyCd; // 国籍代码 String(3) Y
	private String nation; // 民族 String(4) Y
	private String marriageCd; // 婚姻状况代码 String(5) Y
	private String domcKnd; // 户籍性质 String(4) Y
	private String domcLo; // 户籍所在地 String(200) Y
	private String lclPolcStnNm; // 街道派出所名称 String(200) Y
	private String ocpCd; // 职业代码 String(5) Y
	private String posTtlCd; // 职称代码 String(5) Y
	private String posCd; // 职务代码 String(5) Y
	private String pstKnd; // 岗位性质 String(4) Y
	private String coWrkTrm; // 现单位工作年限 String(2) Y 取值范围：1~99999
	private String famAdr; // 家庭住址 String(600) Y
	private String mblNo; // 移动号码 String(30) Y 最大11位，最小11位
	private String corpNm; // 工作单位 String(30) Y
	private String offcTel; // 办公电话 String(30) Y
	private String eCIFCstNo;// ECIF客户代号
	private String frmrFlg; // 农户标识 String(4) Y 小贷客户特有字段
	private String hltSt; // 健康状况 String(5) Y 小贷客户特有字段
	private String imgTplCd;  //影像模板代码
	private String imgBsnNo;  //影像业务编号
	
	
	public EsbBodyMtmqRq12002000013A09() {

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

	public String getCstMgrNo() {
		return cstMgrNo;
	}

	@XmlElement(name = "CstMgrNo")
	public void setCstMgrNo(String cstMgrNo) {
		this.cstMgrNo = cstMgrNo;
	}

	public String getCstMgrInstNo() {
		return cstMgrInstNo;
	}

	@XmlElement(name = "CstMgrInstNo")
	public void setCstMgrInstNo(String cstMgrInstNo) {
		this.cstMgrInstNo = cstMgrInstNo;
	}

	public String getOprTp() {
		return oprTp;
	}

	@XmlElement(name = "OprTp")
	public void setOprTp(String oprTp) {
		this.oprTp = oprTp;
	}

	public String getCrCstNo() {
		return crCstNo;
	}

	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
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

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getCrExnCstFlg() {
		return crExnCstFlg;
	}

	@XmlElement(name = "CrExnCstFlg")
	public void setCrExnCstFlg(String crExnCstFlg) {
		this.crExnCstFlg = crExnCstFlg;
	}

	public String getThrdPartyCstFlg() {
		return thrdPartyCstFlg;
	}

	@XmlElement(name = "ThrdPartyCstFlg")
	public void setThrdPartyCstFlg(String thrdPartyCstFlg) {
		this.thrdPartyCstFlg = thrdPartyCstFlg;
	}

	public String getGndInd() {
		return gndInd;
	}

	@XmlElement(name = "GndInd")
	public void setGndInd(String gndInd) {
		this.gndInd = gndInd;
	}

	public String getBirthDate() {
		return birthDate;
	}

	@XmlElement(name = "BirthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getNtntyCd() {
		return ntntyCd;
	}

	@XmlElement(name = "NtntyCd")
	public void setNtntyCd(String ntntyCd) {
		this.ntntyCd = ntntyCd;
	}

	public String getNation() {
		return nation;
	}

	@XmlElement(name = "Nation")
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMarriageCd() {
		return marriageCd;
	}

	@XmlElement(name = "MarriageCd")
	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	public String getDomcKnd() {
		return domcKnd;
	}

	@XmlElement(name = "DomcKnd")
	public void setDomcKnd(String domcKnd) {
		this.domcKnd = domcKnd;
	}

	public String getDomcLo() {
		return domcLo;
	}

	@XmlElement(name = "DomcLo")
	public void setDomcLo(String domcLo) {
		this.domcLo = domcLo;
	}

	public String getLclPolcStnNm() {
		return lclPolcStnNm;
	}

	@XmlElement(name = "LclPolcStnNm")
	public void setLclPolcStnNm(String lclPolcStnNm) {
		this.lclPolcStnNm = lclPolcStnNm;
	}

	public String getOcpCd() {
		return ocpCd;
	}

	@XmlElement(name = "OcpCd")
	public void setOcpCd(String ocpCd) {
		this.ocpCd = ocpCd;
	}

	public String getPosTtlCd() {
		return posTtlCd;
	}

	@XmlElement(name = "PosTtlCd")
	public void setPosTtlCd(String posTtlCd) {
		this.posTtlCd = posTtlCd;
	}

	public String getPosCd() {
		return posCd;
	}

	@XmlElement(name = "PosCd")
	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	public String getPstKnd() {
		return pstKnd;
	}

	@XmlElement(name = "PstKnd")
	public void setPstKnd(String pstKnd) {
		this.pstKnd = pstKnd;
	}

	public String getCoWrkTrm() {
		return coWrkTrm;
	}

	@XmlElement(name = "CoWrkTrm")
	public void setCoWrkTrm(String coWrkTrm) {
		this.coWrkTrm = coWrkTrm;
	}

	public String getFamAdr() {
		return famAdr;
	}

	@XmlElement(name = "FamAdr")
	public void setFamAdr(String famAdr) {
		this.famAdr = famAdr;
	}

	public String getMblNo() {
		return mblNo;
	}

	@XmlElement(name = "MblNo")
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getCorpNm() {
		return corpNm;
	}

	@XmlElement(name = "CorpNm")
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}

	public String getOffcTel() {
		return offcTel;
	}

	@XmlElement(name = "OffcTel")
	public void setOffcTel(String offcTel) {
		this.offcTel = offcTel;
	}

	public String getFrmrFlg() {
		return frmrFlg;
	}

	@XmlElement(name = "FrmrFlg")
	public void setFrmrFlg(String frmrFlg) {
		this.frmrFlg = frmrFlg;
	}

	public String getHltSt() {
		return hltSt;
	}

	@XmlElement(name = "HltSt")
	public void setHltSt(String hltSt) {
		this.hltSt = hltSt;
	}

	public String geteCIFCstNo() {
		return eCIFCstNo;
	}

	@XmlElement(name = "ECIFCstNo")
	public void seteCIFCstNo(String eCIFCstNo) {
		this.eCIFCstNo = eCIFCstNo;
	}
}
