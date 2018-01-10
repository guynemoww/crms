package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq12002000013A10 
 * @Description: 12002000013客户信息开户维护     10对公客户信息维护				
 *
 */

@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq12002000013A10 extends EsbBody implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;//	客户经理	String(8)	Y	
	private String cstMgrInstNo;//	客户经理机构号	String(10)	Y	
	private String oprTp;//操作类型	String(3)	Y	"add:新增 update:修改"
	private String crCstNo;//	客户代号	String(10)	N	当修改操作时，此字段必输
	private String cstNm;//客户名称	String(50)	Y	
	private String cstTp;//	客户类型	String(2)	Y	1：企业类，2：事业类，3：个体工商户
	private String dmstInd	;//境内标志	String(4)	Y	"1: 境内 2: 境外"
	private String ctyCd;//	国家编码	String(3)	Y	默认中国CHN
	private String crExnCstFlg;//	授信客户标识	String(4)	Y	默认为否。当为是时，不可改为否。
	private String thrdPartyCstFlg	;//第三方客户标识	String(4)	Y	默认为否
	private String lglPrsnNm	;//法人名称	String(20)	Y	同个体工商的“经营者姓名”
	private String lglPrsnIdntTp	;//法人证件类型	String(10)	Y	同个体工商的“经营者证件类型”
	private String lglPrsnIdentNo;//	法人证件号码	String(20)	Y	同个体工商的“经营者证件号码”
	private String identEfftEndDt	;//证件有效截止日期	String(8)	Y	
	private String bsnLcsNo	;//营业执照号	String(32)	Y	对应的证件类型为201
	private String rgstTp;//	注册类型	String(5)	Y	
	private String bsnLcsRcrdDt;//	营业执照登记日	String(8)	Y	
	private String bsnLcsExpDt;//	营业执照到期日	String(8)	Y	
	private String bsnScop;//	企业经营范围	String(30)	Y	与事业类的“宗旨和业务范围”共用
	private String rgstCcy;//	注册币种	String(3)	Y	
	private double rgstTch;//	注册资金金额	Double(20,2)	Y	
	private String orgInstCd;//	组织机构代码	String(32)	Y	对应的证件类型为202
	private String orgInstCdCtfExpDt	;//组织机构代码证到期日期	String(8)	Y	
	private String oldLoanCardNo;//	中征码	String(32)	N	
	private String natTaxRgstCtfNo;//	国税税务登记证号	String(32)	Y	
	private String lclTaxRgstCtfNo	;//地税税务登记证号	String(32)	Y	
	private String idyCd;//	行业编码	String(5)	Y	
	private String idyBrdHdngCd;//行业大类代码	String(5)	Y	
	private String idScdClCd	;//行业中类代码	String(5)	Y	
	private String idyThrdClCd;//	行业小类代码	String(5)	Y	
	private String tchmSrcDsc;//	资金来源	String(200)	Y	事业类客户特有字段
	private String eCIFCstNo;//eCIFCstNo客户编号
	
	private String imgTplCd;  //影像模板代码
	private String imgBsnNo;  //影像业务编号


	private List<EsbBodyMtmqRqSplmtInfArray> esbBodyMtmqSplmtInfArrays;
	
	public EsbBodyMtmqRq12002000013A10() {
		
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


	public String geteCIFCstNo() {
		return eCIFCstNo;
	}
	@XmlElement(name = "ECIFCstNo")
	public void seteCIFCstNo(String eCIFCstNo) {
		this.eCIFCstNo = eCIFCstNo;
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
	public String getCstNm() {
		return cstNm;
	}
	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}
	public String getCstTp() {
		return cstTp;
	}
	
	@XmlElement(name = "CstTp")
	public void setCstTp(String cstTp) {
		this.cstTp = cstTp;
	}
	public String getDmstInd() {
		return dmstInd;
	}
	@XmlElement(name = "DmstInd")
	public void setDmstInd(String dmstInd) {
		this.dmstInd = dmstInd;
	}
	public String getCtyCd() {
		return ctyCd;
	}
	@XmlElement(name = "CtyCd")
	public void setCtyCd(String ctyCd) {
		this.ctyCd = ctyCd;
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
	public String getLglPrsnNm() {
		return lglPrsnNm;
	}
	@XmlElement(name = "LglPrsnNm")
	public void setLglPrsnNm(String lglPrsnNm) {
		this.lglPrsnNm = lglPrsnNm;
	}
	public String getLglPrsnIdntTp() {
		return lglPrsnIdntTp;
	}
	@XmlElement(name = "LglPrsnIdntTp")
	public void setLglPrsnIdntTp(String lglPrsnIdntTp) {
		this.lglPrsnIdntTp = lglPrsnIdntTp;
	}
	public String getLglPrsnIdentNo() {
		return lglPrsnIdentNo;
	}
	@XmlElement(name = "LglPrsnIdentNo")
	public void setLglPrsnIdentNo(String lglPrsnIdentNo) {
		this.lglPrsnIdentNo = lglPrsnIdentNo;
	}
	public String getIdentEfftEndDt() {
		return identEfftEndDt;
	}
	@XmlElement(name = "IdentEfftEndDt")
	public void setIdentEfftEndDt(String identEfftEndDt) {
		this.identEfftEndDt = identEfftEndDt;
	}
	public String getBsnLcsNo() {
		return bsnLcsNo;
	}
	@XmlElement(name = "BsnLcsNo")
	public void setBsnLcsNo(String bsnLcsNo) {
		this.bsnLcsNo = bsnLcsNo;
	}
	public String getRgstTp() {
		return rgstTp;
	}
	@XmlElement(name = "RgstTp")
	public void setRgstTp(String rgstTp) {
		this.rgstTp = rgstTp;
	}
	public String getBsnLcsRcrdDt() {
		return bsnLcsRcrdDt;
	}
	@XmlElement(name = "BsnLcsRcrdDt")
	public void setBsnLcsRcrdDt(String bsnLcsRcrdDt) {
		this.bsnLcsRcrdDt = bsnLcsRcrdDt;
	}
	public String getBsnLcsExpDt() {
		return bsnLcsExpDt;
	}
	@XmlElement(name = "BsnLcsExpDt")
	public void setBsnLcsExpDt(String bsnLcsExpDt) {
		this.bsnLcsExpDt = bsnLcsExpDt;
	}
	public String getBsnScop() {
		return bsnScop;
	}
	@XmlElement(name = "BsnScop")
	public void setBsnScop(String bsnScop) {
		this.bsnScop = bsnScop;
	}
	public String getRgstCcy() {
		return rgstCcy;
	}
	@XmlElement(name = "RgstCcy")
	public void setRgstCcy(String rgstCcy) {
		this.rgstCcy = rgstCcy;
	}
	public double getRgstTch() {
		return rgstTch;
	}
	@XmlElement(name = "RgstTch")
	public void setRgstTch(double rgstTch) {
		this.rgstTch = rgstTch;
	}
	public String getOrgInstCd() {
		return orgInstCd;
	}
	@XmlElement(name = "OrgInstCd")
	public void setOrgInstCd(String orgInstCd) {
		this.orgInstCd = orgInstCd;
	}
	public String getOrgInstCdCtfExpDt() {
		return orgInstCdCtfExpDt;
	}
	@XmlElement(name = "OrgInstCdCtfExpDt")
	public void setOrgInstCdCtfExpDt(String orgInstCdCtfExpDt) {
		this.orgInstCdCtfExpDt = orgInstCdCtfExpDt;
	}
	public String getOldLoanCardNo() {
		return oldLoanCardNo;
	}
	@XmlElement(name = "OldLoanCardNo")
	public void setOldLoanCardNo(String oldLoanCardNo) {
		this.oldLoanCardNo = oldLoanCardNo;
	}
	public String getNatTaxRgstCtfNo() {
		return natTaxRgstCtfNo;
	}
	@XmlElement(name = "NatTaxRgstCtfNo")
	public void setNatTaxRgstCtfNo(String natTaxRgstCtfNo) {
		this.natTaxRgstCtfNo = natTaxRgstCtfNo;
	}
	public String getLclTaxRgstCtfNo() {
		return lclTaxRgstCtfNo;
	}
	@XmlElement(name = "LclTaxRgstCtfNo")
	public void setLclTaxRgstCtfNo(String lclTaxRgstCtfNo) {
		this.lclTaxRgstCtfNo = lclTaxRgstCtfNo;
	}
	public String getIdyCd() {
		return idyCd;
	}
	@XmlElement(name = "IdyCd")
	public void setIdyCd(String idyCd) {
		this.idyCd = idyCd;
	}
	public String getIdyBrdHdngCd() {
		return idyBrdHdngCd;
	}
	@XmlElement(name = "IdyBrdHdngCd")
	public void setIdyBrdHdngCd(String idyBrdHdngCd) {
		this.idyBrdHdngCd = idyBrdHdngCd;
	}
	public String getIdScdClCd() {
		return idScdClCd;
	}
	@XmlElement(name = "IdScdClCd")
	public void setIdScdClCd(String idScdClCd) {
		this.idScdClCd = idScdClCd;
	}
	public String getIdyThrdClCd() {
		return idyThrdClCd;
	}
	@XmlElement(name = "IdyThrdClCd")
	public void setIdyThrdClCd(String idyThrdClCd) {
		this.idyThrdClCd = idyThrdClCd;
	}
	public String getTchmSrcDsc() {
		return tchmSrcDsc;
	}
	@XmlElement(name = "TchmSrcDsc")
	public void setTchmSrcDsc(String tchmSrcDsc) {
		this.tchmSrcDsc = tchmSrcDsc;
	}
	public List<EsbBodyMtmqRqSplmtInfArray> getEsbBodyMtmqSplmtInfArrays() {
		return esbBodyMtmqSplmtInfArrays;
	}
	@XmlElement(name = "SplmtInfArray")
	public void setEsbBodyMtmqSplmtInfArrays(
			List<EsbBodyMtmqRqSplmtInfArray> esbBodyMtmqSplmtInfArrays) {
		this.esbBodyMtmqSplmtInfArrays = esbBodyMtmqSplmtInfArrays;
	}
}
