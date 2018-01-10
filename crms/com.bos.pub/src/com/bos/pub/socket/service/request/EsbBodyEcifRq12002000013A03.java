package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyEcifRq12002000013A03 
 * @Description: 12002000013客户信息开户维护	03个人客户基本信息维护
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyEcifRq12002000013A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rqsStmId;		//请求系统号		String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cstNo;			//客户代号			String(10)		Y	
	private String cstNm;			//客户名称			String(50)		Y	
	private String formrNm;			//曾用名			String(20)		N	
	private String enNm;			//英文名称			String(100)		N	
	private String gndInd;			//性别标志			String(1)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String nation;			//民族			String(4)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String hshdRgstKnd;		//户籍性质			String(19)		N	
	private String birthDate;		//出生日期			String(8)		N	
	private String ntntyCd;			//国籍代码			String(3)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cstImptLvlCd;	//客户重要级别代码	String(3)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bnkStkhdFlg;		//本行股东标志		String(3)		N	"1是0否-1未知"
	private String bnkSnrMgrFlg;	//本行高管标志		String(3)		N	"1是0否-1未知"
	private String marriageCd;		//婚姻状况代码		String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String hltSt;			//健康状况			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String religion;		//宗教信仰			String(6)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String highEdct;		//最高学历			String(4)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String hsTp;			//住宅类型			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String prsnTaxNo;		//个人纳税号		String(64)		N	
	private String prsnScilScrNo;	//个人社保号		String(64)		N	
	private String strtWrkDate;		//参加工作年份		String(8)		N	
	private String ocpCd;			//职业代码			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String coNm;			//单位名称			String(100)		N	
	private String coKnd;			//单位性质			String(5)		N	
	private String coWrkTrm;		//现单位工作年限		String(2)		N	
	private String idyInvlCd;		//所属行业代码		String(3)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String posCd;			//职务代码			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String wrkBnkStfLvl;	//在职行员等级		String(19)		N	
	private String posTtlCd;		//职称代码			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private double incmAmt;			//月收入金额		Double(20,2)	N	
	private String mainEcnmSrcDsc;	//主要经济来源		String(120)		N	
	private String othrEcnmSrcDsc;	//其他经济来源		String(120)		N	
	private String lngTp;			//语种			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String grdtSchNm;		//毕业院校			String(20)		N	
	private String mjrNm;			//专业			String(20)		N	
	private String grdtDt;			//毕业年月			String(6)		N	
	private String dgrCd;			//学位代码			String(5)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String rsdntFlg;		//居民标志			String(1)		N	"1是0否"
	private String lclRsdntTm;		//本地居住时间		String(2)		N	
	private String rcrdSt;			//记录状态			String(4)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String atchInstNo;		//归属机构号		String(16)		N	
//	private String fmSysInd;		//来源系统标志		String(10)		N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String acctBrId;		//开户机构代号		String(6)		N	
	private String acctDt;			//开户日期			String(8)		N	YYYYMMDD
	private String prospCstFlg;		//潜在客户标志		String(3)		"1 是	0 否	-1 未知"
	private List<EsbBodyEcifRqLoAdrArray> esbBodyEcifRqLoAdrArrays;		//所在地址数组
	private List<EsbBodyEcifRqCtcInfArray> esbBodyEcifRqCtcInfArrays;		//联系信息数组
	
	public EsbBodyEcifRq12002000013A03(){
		
	}

	public String getRqsStmId() {
		return rqsStmId;
	}

	@XmlElement(name = "RqsStmId")
	public void setRqsStmId(String rqsStmId) {
		this.rqsStmId = rqsStmId;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getFormrNm() {
		return formrNm;
	}

	@XmlElement(name = "FormrNm")
	public void setFormrNm(String formrNm) {
		this.formrNm = formrNm;
	}

	public String getEnNm() {
		return enNm;
	}

	@XmlElement(name = "EnNm")
	public void setEnNm(String enNm) {
		this.enNm = enNm;
	}

	public String getGndInd() {
		return gndInd;
	}

	@XmlElement(name = "GndInd")
	public void setGndInd(String gndInd) {
		this.gndInd = gndInd;
	}

	public String getNation() {
		return nation;
	}

	@XmlElement(name = "Nation")
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHshdRgstKnd() {
		return hshdRgstKnd;
	}

	@XmlElement(name = "HshdRgstKnd")
	public void setHshdRgstKnd(String hshdRgstKnd) {
		this.hshdRgstKnd = hshdRgstKnd;
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

	public String getCstImptLvlCd() {
		return cstImptLvlCd;
	}

	@XmlElement(name = "CstImptLvlCd")
	public void setCstImptLvlCd(String cstImptLvlCd) {
		this.cstImptLvlCd = cstImptLvlCd;
	}

	public String getBnkStkhdFlg() {
		return bnkStkhdFlg;
	}

	@XmlElement(name = "BnkStkhdFlg")
	public void setBnkStkhdFlg(String bnkStkhdFlg) {
		this.bnkStkhdFlg = bnkStkhdFlg;
	}

	public String getBnkSnrMgrFlg() {
		return bnkSnrMgrFlg;
	}

	@XmlElement(name = "BnkSnrMgrFlg")
	public void setBnkSnrMgrFlg(String bnkSnrMgrFlg) {
		this.bnkSnrMgrFlg = bnkSnrMgrFlg;
	}

	public String getMarriageCd() {
		return marriageCd;
	}

	@XmlElement(name = "MarriageCd")
	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	public String getHltSt() {
		return hltSt;
	}

	@XmlElement(name = "HltSt")
	public void setHltSt(String hltSt) {
		this.hltSt = hltSt;
	}

	public String getReligion() {
		return religion;
	}

	@XmlElement(name = "Religion")
	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getHighEdct() {
		return highEdct;
	}

	@XmlElement(name = "HighEdct")
	public void setHighEdct(String highEdct) {
		this.highEdct = highEdct;
	}

	public String getHsTp() {
		return hsTp;
	}

	@XmlElement(name = "HsTp")
	public void setHsTp(String hsTp) {
		this.hsTp = hsTp;
	}

	public String getPrsnTaxNo() {
		return prsnTaxNo;
	}

	@XmlElement(name = "PrsnTaxNo")
	public void setPrsnTaxNo(String prsnTaxNo) {
		this.prsnTaxNo = prsnTaxNo;
	}

	public String getPrsnScilScrNo() {
		return prsnScilScrNo;
	}

	@XmlElement(name = "PrsnScilScrNo")
	public void setPrsnScilScrNo(String prsnScilScrNo) {
		this.prsnScilScrNo = prsnScilScrNo;
	}

	public String getStrtWrkDate() {
		return strtWrkDate;
	}

	@XmlElement(name = "StrtWrkDate")
	public void setStrtWrkDate(String strtWrkDate) {
		this.strtWrkDate = strtWrkDate;
	}

	public String getOcpCd() {
		return ocpCd;
	}

	@XmlElement(name = "OcpCd")
	public void setOcpCd(String ocpCd) {
		this.ocpCd = ocpCd;
	}

	public String getCoNm() {
		return coNm;
	}

	@XmlElement(name = "CoNm")
	public void setCoNm(String coNm) {
		this.coNm = coNm;
	}

	public String getCoKnd() {
		return coKnd;
	}

	@XmlElement(name = "CoKnd")
	public void setCoKnd(String coKnd) {
		this.coKnd = coKnd;
	}

	public String getCoWrkTrm() {
		return coWrkTrm;
	}

	@XmlElement(name = "CoWrkTrm")
	public void setCoWrkTrm(String coWrkTrm) {
		this.coWrkTrm = coWrkTrm;
	}

	public String getIdyInvlCd() {
		return idyInvlCd;
	}

	@XmlElement(name = "IdyInvlCd")
	public void setIdyInvlCd(String idyInvlCd) {
		this.idyInvlCd = idyInvlCd;
	}

	public String getPosCd() {
		return posCd;
	}

	@XmlElement(name = "PosCd")
	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	public String getWrkBnkStfLvl() {
		return wrkBnkStfLvl;
	}

	@XmlElement(name = "WrkBnkStfLvl")
	public void setWrkBnkStfLvl(String wrkBnkStfLvl) {
		this.wrkBnkStfLvl = wrkBnkStfLvl;
	}

	public String getPosTtlCd() {
		return posTtlCd;
	}

	@XmlElement(name = "PosTtlCd")
	public void setPosTtlCd(String posTtlCd) {
		this.posTtlCd = posTtlCd;
	}

	public double getIncmAmt() {
		return incmAmt;
	}

	@XmlElement(name = "IncmAmt")
	public void setIncmAmt(double incmAmt) {
		this.incmAmt = incmAmt;
	}

	public String getMainEcnmSrcDsc() {
		return mainEcnmSrcDsc;
	}

	@XmlElement(name = "MainEcnmSrcDsc")
	public void setMainEcnmSrcDsc(String mainEcnmSrcDsc) {
		this.mainEcnmSrcDsc = mainEcnmSrcDsc;
	}

	public String getOthrEcnmSrcDsc() {
		return othrEcnmSrcDsc;
	}

	@XmlElement(name = "OthrEcnmSrcDsc")
	public void setOthrEcnmSrcDsc(String othrEcnmSrcDsc) {
		this.othrEcnmSrcDsc = othrEcnmSrcDsc;
	}

	public String getLngTp() {
		return lngTp;
	}

	@XmlElement(name = "LngTp")
	public void setLngTp(String lngTp) {
		this.lngTp = lngTp;
	}

	public String getGrdtSchNm() {
		return grdtSchNm;
	}

	@XmlElement(name = "GrdtSchNm")
	public void setGrdtSchNm(String grdtSchNm) {
		this.grdtSchNm = grdtSchNm;
	}

	public String getMjrNm() {
		return mjrNm;
	}

	@XmlElement(name = "MjrNm")
	public void setMjrNm(String mjrNm) {
		this.mjrNm = mjrNm;
	}

	public String getGrdtDt() {
		return grdtDt;
	}

	@XmlElement(name = "GrdtDt")
	public void setGrdtDt(String grdtDt) {
		this.grdtDt = grdtDt;
	}

	public String getDgrCd() {
		return dgrCd;
	}

	@XmlElement(name = "DgrCd")
	public void setDgrCd(String dgrCd) {
		this.dgrCd = dgrCd;
	}

	public String getRsdntFlg() {
		return rsdntFlg;
	}

	@XmlElement(name = "RsdntFlg")
	public void setRsdntFlg(String rsdntFlg) {
		this.rsdntFlg = rsdntFlg;
	}

	public String getLclRsdntTm() {
		return lclRsdntTm;
	}

	@XmlElement(name = "LclRsdntTm")
	public void setLclRsdntTm(String lclRsdntTm) {
		this.lclRsdntTm = lclRsdntTm;
	}

	public String getRcrdSt() {
		return rcrdSt;
	}

	@XmlElement(name = "RcrdSt")
	public void setRcrdSt(String rcrdSt) {
		this.rcrdSt = rcrdSt;
	}

	public String getAtchInstNo() {
		return atchInstNo;
	}

	@XmlElement(name = "AtchInstNo")
	public void setAtchInstNo(String atchInstNo) {
		this.atchInstNo = atchInstNo;
	}

/*	public String getFmSysInd() {
		return fmSysInd;
	}

	@XmlElement(name = "FmSysInd")
	public void setFmSysInd(String fmSysInd) {
		this.fmSysInd = fmSysInd;
	}*/

	public String getAcctBrId() {
		return acctBrId;
	}

	@XmlElement(name = "AcctBrId")
	public void setAcctBrId(String acctBrId) {
		this.acctBrId = acctBrId;
	}

	public String getAcctDt() {
		return acctDt;
	}

	@XmlElement(name = "AcctDt")
	public void setAcctDt(String acctDt) {
		this.acctDt = acctDt;
	}

	public List<EsbBodyEcifRqLoAdrArray> getEsbBodyEcifRqLoAdrArrays() {
		return esbBodyEcifRqLoAdrArrays;
	}

	public String getProspCstFlg() {
		return prospCstFlg;
	}

	@XmlElement(name = "ProspCstFlg")
	public void setProspCstFlg(String prospCstFlg) {
		this.prospCstFlg = prospCstFlg;
	}

	@XmlElement(name = "LoAdrArray")
	public void setEsbBodyEcifRqLoAdrArrays(
			List<EsbBodyEcifRqLoAdrArray> esbBodyEcifRqLoAdrArrays) {
		this.esbBodyEcifRqLoAdrArrays = esbBodyEcifRqLoAdrArrays;
	}

	public List<EsbBodyEcifRqCtcInfArray> getEsbBodyEcifRqCtcInfArrays() {
		return esbBodyEcifRqCtcInfArrays;
	}

	@XmlElement(name = "CtcInfArray")
	public void setEsbBodyEcifRqCtcInfArrays(
			List<EsbBodyEcifRqCtcInfArray> esbBodyEcifRqCtcInfArrays) {
		this.esbBodyEcifRqCtcInfArrays = esbBodyEcifRqCtcInfArrays;
	}
}
