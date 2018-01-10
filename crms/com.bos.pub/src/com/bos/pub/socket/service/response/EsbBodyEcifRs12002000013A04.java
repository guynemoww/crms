package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.EsbBodyEcifRqCtcInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGCtcInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGKeyPrsnInfArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqGLoAdrArray;
import com.bos.pub.socket.service.request.EsbBodyEcifRqLoAdrArray;
import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyEcifRs12002000013A04 
 * @Description: 12002000013客户信息开户维护		04公司客户基本信息开户
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyEcifRs12002000013A04 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cstNo;				//客户代号			String(10)		
	private String fullNm;				//全称			String(128)		
	private String shrtNm;				//简称			String(20)		
	private String enNm;				//英文名称			String(100)		
	private String coNo;				//企业编号			String(21)		
	private String idyCd;				//行业编码			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String sprDeptNm;			//上级主管部门名称	String(66)		
	private String tchCmptntDeptNm;		//资金主管部门名称	String(42)		
	private String acctBrId;			//开户机构代号		String(6)		
	private String acctTlrCd;			//开户柜员			String(4)		
	private String acctDt;				//开户日期			String(8)		YYYYMMDD
	private String coChar;				//企业性质			String(2)		
	private String corpOwnTp;			//企业所有制类型		String(6)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String corpEcnmTp;			//企业单位经济类型	String(19)		
	private String corpOrgFm;			//企业单位组织形式	String(6)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bondZoneFlg;			//保税区标志		String(1)		"0:非保税区1:保税区"
	private String bsnScop;				//企业经营范围		String(30)		
	private String bsnTrm;				//经营期限			String(8)		YYYYMMDD
	private String idyInvlCd;			//所属行业代码		String(3)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String entpMgtSclCd;		//企业经营规模代码	String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String coLvl;				//企业级别			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String oprTp;				//经营种类			String(19)		
	private String empeNum;				//员工人数			String(19)		
	private String natTaxRgstCtfNo;		//国税税务登记证号	String(32)		
	private String lclTaxRgstCtfNo;		//地税税务登记证号	String(32)		
	private String grpCstFlg;			//集团客户标志		String(4)		"1是0否-1未知"
	private String fllwCstFlg;			//同业客户标志		String(4)		"1是0否-1未知"
	private String listCoFlg;			//上市公司标志		String(4)		"1是0否-1未知"
	private String leadBnkNm;			//主办银行名称		String(12)		
	private String antiMnyLndRsltCd;	//反洗钱评估结果代码	String(4)		
	private String coAnulInsptRsltCd;	//企业年检结果代码	String(19)		
	private String imptCstFlg;			//重点客户标志		String(4)		"1是0否-1未知"
	private String imprExprtRghtFlg;	//进出口经营权标志	String(4)		"1是0否-1未知"
	private String sciTechEntpFlg;		//科技型企业标志		String(4)		"1是0否-1未知"
	private String esttDvlpmFlg;		//从事房地产开发标志	String(4)		"1是0否-1未知"
	private String highNewTechEntpFlg;	//高新技术企业标志	String(4)		"1是0否-1未知"
	private String vIPFlg;				//Vip客户标志		String(4)		"1是0否-1未知"
	private String rrlEntpFlg;			//农村企业标志		String(4)		"1是0否-1未知"
	private String fTACstFlg;			//自贸区客户标志		String(3)		"1是0否-1未知"
	private String oldLoanCardNo;		//中征码			String(32)		
	private String rcrdSt;				//记录状态			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bnkHshFlg;			//我行股东标志		String(3)	N	"1是	0否	-1未知"
	private String cstTp;				//客户类型			String(1)	N	
	private String fncPltfmFlg;			//融资平台标志		String(3)	N	"1是	0否	-1未知"
	private String rmk;					//备注			String(100)		
	private double rgstTch;				//注册资金金额		Double(20,2)		
	private String rgstCcy;				//注册币种			String(3)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String orgInstCd;			//组织机构代码		String(32)		
	private String bsnLcsNo;			//营业执照号		String(32)		
	private String bsnLcsRcrdDt;		//营业执照登记日		String(8)		YYYYMMDD
	private String bsnLcsExpDt;			//营业执照到期日		String(8)		YYYYMMDD
	private String bsnLcsRgstAdr;		//营业执照注册地址	String(66)		
	private String bsnLcsIssuOffcNm;	//营业执照签发机关名称	String(66)		
	private String rgstTp;				//注册类型			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String acctPrmtNo;			//开户许可证号码		String(20)		
	private double rMBCnvrCptlAmt;		//人民币折算资本金额	Double(20,2)		
	private String cptlTp;				//资本类型			String(5)		
	private String rgstDt;				//注册日期			String(8)		YYYYMMDD
	private double paidInCptlAmt;		//实收资本金额		Double(20,2)		
	private String paidInCptlCcy;		//实收资本币种		String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private double oprRecptAmt;			//营业收入			Double(20,2)		
	private double totAstAmt;			//资产总额			Double(20,2)		
	private String totAstCcy;			//资产总额币种		String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String mainBsnDsc;			//主营业务			String(166)		
	private String sdlnBsnDsc;			//兼营业务			String(166)		
	private String oprSteDsc;			//经营状况说明		String(333)		
	private String mainPdDsc;			//主要产品说明		String(333)		
	private double oprSiteAreaNum;		//经营面积			Double(22,6)		平方米
	private String oprSiteOwnCd;		//经营场地所有权		String(6)		
/*	private String shrhlrTp;			//股东类型			String(1)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cstNm;				//客户名称			String(50)		
	private String nation;				//民族			String(4)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String gndInd;				//性别标志			String(1)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ntntyCd;				//国籍代码			String(3)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String marriageCd;			//婚姻状况代码		String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String birthDate;			//出生日期			String(8)		yyyymmdd
	private String idntTp;				//证件类型			String(10)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String identNo;				//证件号码			String(20)	N	
	private String ocpCd;				//职业代码			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String highEdct;			//最高学历			String(4)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ctcAdr;				//联系地址			String(30)		
	private String ctcNo;				//联系号码			String(100)		
	private String pstCd2;				//邮编			String(6)		
	private String offcTel;				//办公电话			String(30)		
	private String posCd;				//职务代码			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String qQNo;				//QQ			String(30)		
	private String wChtNo;				//微信			String(30)		
	private String email;				//电子邮箱			String(30)		
*/	private String atchInstNo;			//归属机构号		String(16)		客户经理与客户的关系
	private String cstMgrNo;			//客户经理			String(8)		
	private String cstMgrNm;			//客户经理名称		String(10)		
	private String fllwCstTp;			//同业客户类型		String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String dmstInd;				//境内标志			String(4)		"1:境内2:境外"
	private String frgnCptlInd;			//外资标志			String(4)		"1:中资2:外资3:合资"
	private String agntFlg;				//代理标志			String(4)		"1是0否-1未知"
	private String insdOutSdStmInd;		//系统内外标志		String(5)		"0 系统内外标志:系统内1 系统内外标志:系统外"
	private String fncPrmtInstNo;		//金融许可证机构编码	String(32)		
	private String ownLnNo;				//本行联行号		String(32)		
	private String eCctnLnNo;			//电联行号			String(32)		
	private String pymtBnkNo;			//支付行行号		String(32)		
	private String clrgCd;				//清算代码			String(32)		
	private String clrgAcctNo;			//清算账号			String(35)		
	private String swiftCd;				//银行国际代码		String(32)		
	private String hldgTp;				//控股类型			String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String fncCstTp;			//金融客户类别		String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private List<EsbBodyEcifRqGLoAdrArray> esbBodyEcifRqGLoAdrArrays;			//所在地址数组
	private List<EsbBodyEcifRqGCtcInfArray> esbBodyEcifRqGCtcInfArrays;			//联系信息数组
	private List<EsbBodyEcifRqGKeyPrsnInfArray> esbBodyEcifRqGKeyPrsnInfArrays;	//关键人信息数组

	public EsbBodyEcifRs12002000013A04(){
		
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getFullNm() {
		return fullNm;
	}

	@XmlElement(name = "FullNm")
	public void setFullNm(String fullNm) {
		this.fullNm = fullNm;
	}

	public String getShrtNm() {
		return shrtNm;
	}

	@XmlElement(name = "ShrtNm")
	public void setShrtNm(String shrtNm) {
		this.shrtNm = shrtNm;
	}

	public String getEnNm() {
		return enNm;
	}

	@XmlElement(name = "EnNm")
	public void setEnNm(String enNm) {
		this.enNm = enNm;
	}

	public String getCoNo() {
		return coNo;
	}

	@XmlElement(name = "CoNo")
	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	public String getIdyCd() {
		return idyCd;
	}

	@XmlElement(name = "IdyCd")
	public void setIdyCd(String idyCd) {
		this.idyCd = idyCd;
	}

	public String getSprDeptNm() {
		return sprDeptNm;
	}

	@XmlElement(name = "SprDeptNm")
	public void setSprDeptNm(String sprDeptNm) {
		this.sprDeptNm = sprDeptNm;
	}

	public String getTchCmptntDeptNm() {
		return tchCmptntDeptNm;
	}

	@XmlElement(name = "TchCmptntDeptNm")
	public void setTchCmptntDeptNm(String tchCmptntDeptNm) {
		this.tchCmptntDeptNm = tchCmptntDeptNm;
	}

	public String getAcctBrId() {
		return acctBrId;
	}

	@XmlElement(name = "AcctBrId")
	public void setAcctBrId(String acctBrId) {
		this.acctBrId = acctBrId;
	}

	public String getAcctTlrCd() {
		return acctTlrCd;
	}

	@XmlElement(name = "AcctTlrCd")
	public void setAcctTlrCd(String acctTlrCd) {
		this.acctTlrCd = acctTlrCd;
	}

	public String getAcctDt() {
		return acctDt;
	}

	@XmlElement(name = "AcctDt")
	public void setAcctDt(String acctDt) {
		this.acctDt = acctDt;
	}

	public String getCoChar() {
		return coChar;
	}

	@XmlElement(name = "CoChar")
	public void setCoChar(String coChar) {
		this.coChar = coChar;
	}

	public String getCorpOwnTp() {
		return corpOwnTp;
	}

	@XmlElement(name = "CorpOwnTp")
	public void setCorpOwnTp(String corpOwnTp) {
		this.corpOwnTp = corpOwnTp;
	}

	public String getCorpEcnmTp() {
		return corpEcnmTp;
	}

	@XmlElement(name = "CorpEcnmTp")
	public void setCorpEcnmTp(String corpEcnmTp) {
		this.corpEcnmTp = corpEcnmTp;
	}

	public String getCorpOrgFm() {
		return corpOrgFm;
	}

	@XmlElement(name = "CorpOrgFm")
	public void setCorpOrgFm(String corpOrgFm) {
		this.corpOrgFm = corpOrgFm;
	}

	public String getBondZoneFlg() {
		return bondZoneFlg;
	}

	@XmlElement(name = "BondZoneFlg")
	public void setBondZoneFlg(String bondZoneFlg) {
		this.bondZoneFlg = bondZoneFlg;
	}

	public String getBsnScop() {
		return bsnScop;
	}

	@XmlElement(name = "BsnScop")
	public void setBsnScop(String bsnScop) {
		this.bsnScop = bsnScop;
	}

	public String getBsnTrm() {
		return bsnTrm;
	}

	@XmlElement(name = "BsnTrm")
	public void setBsnTrm(String bsnTrm) {
		this.bsnTrm = bsnTrm;
	}

	public String getIdyInvlCd() {
		return idyInvlCd;
	}

	@XmlElement(name = "IdyInvlCd")
	public void setIdyInvlCd(String idyInvlCd) {
		this.idyInvlCd = idyInvlCd;
	}

	public String getEntpMgtSclCd() {
		return entpMgtSclCd;
	}

	@XmlElement(name = "EntpMgtSclCd")
	public void setEntpMgtSclCd(String entpMgtSclCd) {
		this.entpMgtSclCd = entpMgtSclCd;
	}

	public String getCoLvl() {
		return coLvl;
	}

	@XmlElement(name = "CoLvl")
	public void setCoLvl(String coLvl) {
		this.coLvl = coLvl;
	}

	public String getOprTp() {
		return oprTp;
	}

	@XmlElement(name = "OprTp")
	public void setOprTp(String oprTp) {
		this.oprTp = oprTp;
	}

	public String getEmpeNum() {
		return empeNum;
	}

	@XmlElement(name = "EmpeNum")
	public void setEmpeNum(String empeNum) {
		this.empeNum = empeNum;
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

	public String getGrpCstFlg() {
		return grpCstFlg;
	}

	@XmlElement(name = "GrpCstFlg")
	public void setGrpCstFlg(String grpCstFlg) {
		this.grpCstFlg = grpCstFlg;
	}

	public String getFllwCstFlg() {
		return fllwCstFlg;
	}

	@XmlElement(name = "FllwCstFlg")
	public void setFllwCstFlg(String fllwCstFlg) {
		this.fllwCstFlg = fllwCstFlg;
	}

	public String getListCoFlg() {
		return listCoFlg;
	}

	@XmlElement(name = "ListCoFlg")
	public void setListCoFlg(String listCoFlg) {
		this.listCoFlg = listCoFlg;
	}

	public String getLeadBnkNm() {
		return leadBnkNm;
	}

	@XmlElement(name = "LeadBnkNm")
	public void setLeadBnkNm(String leadBnkNm) {
		this.leadBnkNm = leadBnkNm;
	}

	public String getAntiMnyLndRsltCd() {
		return antiMnyLndRsltCd;
	}

	@XmlElement(name = "AntiMnyLndRsltCd")
	public void setAntiMnyLndRsltCd(String antiMnyLndRsltCd) {
		this.antiMnyLndRsltCd = antiMnyLndRsltCd;
	}

	public String getCoAnulInsptRsltCd() {
		return coAnulInsptRsltCd;
	}

	@XmlElement(name = "CoAnulInsptRsltCd")
	public void setCoAnulInsptRsltCd(String coAnulInsptRsltCd) {
		this.coAnulInsptRsltCd = coAnulInsptRsltCd;
	}

	public String getImptCstFlg() {
		return imptCstFlg;
	}

	@XmlElement(name = "ImptCstFlg")
	public void setImptCstFlg(String imptCstFlg) {
		this.imptCstFlg = imptCstFlg;
	}

	public String getImprExprtRghtFlg() {
		return imprExprtRghtFlg;
	}

	@XmlElement(name = "ImprExprtRghtFlg")
	public void setImprExprtRghtFlg(String imprExprtRghtFlg) {
		this.imprExprtRghtFlg = imprExprtRghtFlg;
	}

	public String getSciTechEntpFlg() {
		return sciTechEntpFlg;
	}

	@XmlElement(name = "SciTechEntpFlg")
	public void setSciTechEntpFlg(String sciTechEntpFlg) {
		this.sciTechEntpFlg = sciTechEntpFlg;
	}

	public String getEsttDvlpmFlg() {
		return esttDvlpmFlg;
	}

	@XmlElement(name = "EsttDvlpmFlg")
	public void setEsttDvlpmFlg(String esttDvlpmFlg) {
		this.esttDvlpmFlg = esttDvlpmFlg;
	}

	public String getHighNewTechEntpFlg() {
		return highNewTechEntpFlg;
	}

	@XmlElement(name = "HighNewTechEntpFlg")
	public void setHighNewTechEntpFlg(String highNewTechEntpFlg) {
		this.highNewTechEntpFlg = highNewTechEntpFlg;
	}

	public String getvIPFlg() {
		return vIPFlg;
	}

	@XmlElement(name = "VIPFlg")
	public void setvIPFlg(String vIPFlg) {
		this.vIPFlg = vIPFlg;
	}

	public String getRrlEntpFlg() {
		return rrlEntpFlg;
	}

	@XmlElement(name = "RrlEntpFlg")
	public void setRrlEntpFlg(String rrlEntpFlg) {
		this.rrlEntpFlg = rrlEntpFlg;
	}

	public String getfTACstFlg() {
		return fTACstFlg;
	}

	@XmlElement(name = "FTACstFlg")
	public void setfTACstFlg(String fTACstFlg) {
		this.fTACstFlg = fTACstFlg;
	}

	public String getOldLoanCardNo() {
		return oldLoanCardNo;
	}

	@XmlElement(name = "OldLoanCardNo")
	public void setOldLoanCardNo(String oldLoanCardNo) {
		this.oldLoanCardNo = oldLoanCardNo;
	}

	public String getRcrdSt() {
		return rcrdSt;
	}

	@XmlElement(name = "RcrdSt")
	public void setRcrdSt(String rcrdSt) {
		this.rcrdSt = rcrdSt;
	}

	public String getBnkHshFlg() {
		return bnkHshFlg;
	}

	@XmlElement(name = "BnkHshFlg")
	public void setBnkHshFlg(String bnkHshFlg) {
		this.bnkHshFlg = bnkHshFlg;
	}

	public String getCstTp() {
		return cstTp;
	}

	@XmlElement(name = "CstTp")
	public void setCstTp(String cstTp) {
		this.cstTp = cstTp;
	}

	public String getFncPltfmFlg() {
		return fncPltfmFlg;
	}

	@XmlElement(name = "FncPltfmFlg")
	public void setFncPltfmFlg(String fncPltfmFlg) {
		this.fncPltfmFlg = fncPltfmFlg;
	}

	public String getRmk() {
		return rmk;
	}

	@XmlElement(name = "Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public double getRgstTch() {
		return rgstTch;
	}

	@XmlElement(name = "RgstTch")
	public void setRgstTch(double rgstTch) {
		this.rgstTch = rgstTch;
	}

	public String getRgstCcy() {
		return rgstCcy;
	}

	@XmlElement(name = "RgstCcy")
	public void setRgstCcy(String rgstCcy) {
		this.rgstCcy = rgstCcy;
	}

	public String getOrgInstCd() {
		return orgInstCd;
	}

	@XmlElement(name = "OrgInstCd")
	public void setOrgInstCd(String orgInstCd) {
		this.orgInstCd = orgInstCd;
	}

	public String getBsnLcsNo() {
		return bsnLcsNo;
	}

	@XmlElement(name = "BsnLcsNo")
	public void setBsnLcsNo(String bsnLcsNo) {
		this.bsnLcsNo = bsnLcsNo;
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

	public String getBsnLcsRgstAdr() {
		return bsnLcsRgstAdr;
	}

	@XmlElement(name = "BsnLcsRgstAdr")
	public void setBsnLcsRgstAdr(String bsnLcsRgstAdr) {
		this.bsnLcsRgstAdr = bsnLcsRgstAdr;
	}

	public String getBsnLcsIssuOffcNm() {
		return bsnLcsIssuOffcNm;
	}

	@XmlElement(name = "BsnLcsIssuOffcNm")
	public void setBsnLcsIssuOffcNm(String bsnLcsIssuOffcNm) {
		this.bsnLcsIssuOffcNm = bsnLcsIssuOffcNm;
	}

	public String getRgstTp() {
		return rgstTp;
	}

	@XmlElement(name = "RgstTp")
	public void setRgstTp(String rgstTp) {
		this.rgstTp = rgstTp;
	}

	public String getAcctPrmtNo() {
		return acctPrmtNo;
	}

	@XmlElement(name = "AcctPrmtNo")
	public void setAcctPrmtNo(String acctPrmtNo) {
		this.acctPrmtNo = acctPrmtNo;
	}

	public double getrMBCnvrCptlAmt() {
		return rMBCnvrCptlAmt;
	}

	@XmlElement(name = "RMBCnvrCptlAmt")
	public void setrMBCnvrCptlAmt(double rMBCnvrCptlAmt) {
		this.rMBCnvrCptlAmt = rMBCnvrCptlAmt;
	}

	public String getCptlTp() {
		return cptlTp;
	}

	@XmlElement(name = "CptlTp")
	public void setCptlTp(String cptlTp) {
		this.cptlTp = cptlTp;
	}

	public String getRgstDt() {
		return rgstDt;
	}

	@XmlElement(name = "RgstDt")
	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}

	public double getPaidInCptlAmt() {
		return paidInCptlAmt;
	}

	@XmlElement(name = "PaidInCptlAmt")
	public void setPaidInCptlAmt(double paidInCptlAmt) {
		this.paidInCptlAmt = paidInCptlAmt;
	}

	public String getPaidInCptlCcy() {
		return paidInCptlCcy;
	}

	@XmlElement(name = "PaidInCptlCcy")
	public void setPaidInCptlCcy(String paidInCptlCcy) {
		this.paidInCptlCcy = paidInCptlCcy;
	}

	public double getOprRecptAmt() {
		return oprRecptAmt;
	}

	@XmlElement(name = "OprRecptAmt")
	public void setOprRecptAmt(double oprRecptAmt) {
		this.oprRecptAmt = oprRecptAmt;
	}

	public double getTotAstAmt() {
		return totAstAmt;
	}

	@XmlElement(name = "TotAstAmt")
	public void setTotAstAmt(double totAstAmt) {
		this.totAstAmt = totAstAmt;
	}

	public String getTotAstCcy() {
		return totAstCcy;
	}

	@XmlElement(name = "TotAstCcy")
	public void setTotAstCcy(String totAstCcy) {
		this.totAstCcy = totAstCcy;
	}

	public String getMainBsnDsc() {
		return mainBsnDsc;
	}

	@XmlElement(name = "MainBsnDsc")
	public void setMainBsnDsc(String mainBsnDsc) {
		this.mainBsnDsc = mainBsnDsc;
	}

	public String getSdlnBsnDsc() {
		return sdlnBsnDsc;
	}

	@XmlElement(name = "SdlnBsnDsc")
	public void setSdlnBsnDsc(String sdlnBsnDsc) {
		this.sdlnBsnDsc = sdlnBsnDsc;
	}

	public String getOprSteDsc() {
		return oprSteDsc;
	}

	@XmlElement(name = "OprSteDsc")
	public void setOprSteDsc(String oprSteDsc) {
		this.oprSteDsc = oprSteDsc;
	}

	public String getMainPdDsc() {
		return mainPdDsc;
	}

	@XmlElement(name = "MainPdDsc")
	public void setMainPdDsc(String mainPdDsc) {
		this.mainPdDsc = mainPdDsc;
	}

	public double getOprSiteAreaNum() {
		return oprSiteAreaNum;
	}

	@XmlElement(name = "OprSiteAreaNum")
	public void setOprSiteAreaNum(double oprSiteAreaNum) {
		this.oprSiteAreaNum = oprSiteAreaNum;
	}

	public String getOprSiteOwnCd() {
		return oprSiteOwnCd;
	}

	@XmlElement(name = "OprSiteOwnCd")
	public void setOprSiteOwnCd(String oprSiteOwnCd) {
		this.oprSiteOwnCd = oprSiteOwnCd;
	}

/*	public String getShrhlrTp() {
		return shrhlrTp;
	}

	@XmlElement(name = "ShrhlrTp")
	public void setShrhlrTp(String shrhlrTp) {
		this.shrhlrTp = shrhlrTp;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
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
	}*/

	public String getAtchInstNo() {
		return atchInstNo;
	}

	@XmlElement(name = "AtchInstNo")
	public void setAtchInstNo(String atchInstNo) {
		this.atchInstNo = atchInstNo;
	}

	public String getCstMgrNo() {
		return cstMgrNo;
	}

	@XmlElement(name = "CstMgrNo")
	public void setCstMgrNo(String cstMgrNo) {
		this.cstMgrNo = cstMgrNo;
	}

	public String getCstMgrNm() {
		return cstMgrNm;
	}

	@XmlElement(name = "CstMgrNm")
	public void setCstMgrNm(String cstMgrNm) {
		this.cstMgrNm = cstMgrNm;
	}

	public String getFllwCstTp() {
		return fllwCstTp;
	}

	@XmlElement(name = "FllwCstTp")
	public void setFllwCstTp(String fllwCstTp) {
		this.fllwCstTp = fllwCstTp;
	}

	public String getDmstInd() {
		return dmstInd;
	}

	@XmlElement(name = "DmstInd")
	public void setDmstInd(String dmstInd) {
		this.dmstInd = dmstInd;
	}

	public String getFrgnCptlInd() {
		return frgnCptlInd;
	}

	@XmlElement(name = "FrgnCptlInd")
	public void setFrgnCptlInd(String frgnCptlInd) {
		this.frgnCptlInd = frgnCptlInd;
	}

	public String getAgntFlg() {
		return agntFlg;
	}

	@XmlElement(name = "AgntFlg")
	public void setAgntFlg(String agntFlg) {
		this.agntFlg = agntFlg;
	}

	public String getInsdOutSdStmInd() {
		return insdOutSdStmInd;
	}

	@XmlElement(name = "InsdOutSdStmInd")
	public void setInsdOutSdStmInd(String insdOutSdStmInd) {
		this.insdOutSdStmInd = insdOutSdStmInd;
	}

	public String getFncPrmtInstNo() {
		return fncPrmtInstNo;
	}

	@XmlElement(name = "FncPrmtInstNo")
	public void setFncPrmtInstNo(String fncPrmtInstNo) {
		this.fncPrmtInstNo = fncPrmtInstNo;
	}

	public String getOwnLnNo() {
		return ownLnNo;
	}

	@XmlElement(name = "OwnLnNo")
	public void setOwnLnNo(String ownLnNo) {
		this.ownLnNo = ownLnNo;
	}

	public String geteCctnLnNo() {
		return eCctnLnNo;
	}

	@XmlElement(name = "ECctnLnNo")
	public void seteCctnLnNo(String eCctnLnNo) {
		this.eCctnLnNo = eCctnLnNo;
	}

	public String getPymtBnkNo() {
		return pymtBnkNo;
	}

	@XmlElement(name = "PymtBnkNo")
	public void setPymtBnkNo(String pymtBnkNo) {
		this.pymtBnkNo = pymtBnkNo;
	}

	public String getClrgCd() {
		return clrgCd;
	}

	@XmlElement(name = "ClrgCd")
	public void setClrgCd(String clrgCd) {
		this.clrgCd = clrgCd;
	}

	public String getClrgAcctNo() {
		return clrgAcctNo;
	}

	@XmlElement(name = "ClrgAcctNo")
	public void setClrgAcctNo(String clrgAcctNo) {
		this.clrgAcctNo = clrgAcctNo;
	}

	public String getSwiftCd() {
		return swiftCd;
	}

	@XmlElement(name = "SwiftCd")
	public void setSwiftCd(String swiftCd) {
		this.swiftCd = swiftCd;
	}

	public String getHldgTp() {
		return hldgTp;
	}

	@XmlElement(name = "HldgTp")
	public void setHldgTp(String hldgTp) {
		this.hldgTp = hldgTp;
	}

	public String getFncCstTp() {
		return fncCstTp;
	}

	@XmlElement(name = "FncCstTp")
	public void setFncCstTp(String fncCstTp) {
		this.fncCstTp = fncCstTp;
	}

	public List<EsbBodyEcifRqGLoAdrArray> getEsbBodyEcifRqGLoAdrArrays() {
		return esbBodyEcifRqGLoAdrArrays;
	}

	@XmlElement(name = "LoAdrArray")
	public void setEsbBodyEcifRqGLoAdrArrays(
			List<EsbBodyEcifRqGLoAdrArray> esbBodyEcifRqGLoAdrArrays) {
		this.esbBodyEcifRqGLoAdrArrays = esbBodyEcifRqGLoAdrArrays;
	}

	public List<EsbBodyEcifRqGCtcInfArray> getEsbBodyEcifRqGCtcInfArrays() {
		return esbBodyEcifRqGCtcInfArrays;
	}

	@XmlElement(name = "CtcInfArray")
	public void setEsbBodyEcifRqGCtcInfArrays(
			List<EsbBodyEcifRqGCtcInfArray> esbBodyEcifRqGCtcInfArrays) {
		this.esbBodyEcifRqGCtcInfArrays = esbBodyEcifRqGCtcInfArrays;
	}

	public List<EsbBodyEcifRqGKeyPrsnInfArray> getEsbBodyEcifRqGKeyPrsnInfArrays() {
		return esbBodyEcifRqGKeyPrsnInfArrays;
	}

	@XmlElement(name = "KeyPrsnInfArray")
	public void setEsbBodyEcifRqGKeyPrsnInfArrays(
			List<EsbBodyEcifRqGKeyPrsnInfArray> esbBodyEcifRqGKeyPrsnInfArrays) {
		this.esbBodyEcifRqGKeyPrsnInfArrays = esbBodyEcifRqGKeyPrsnInfArrays;
	}
}
