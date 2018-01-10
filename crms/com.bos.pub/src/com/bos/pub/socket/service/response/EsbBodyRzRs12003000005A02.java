package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRs12003000005A02 
 * @Description: 12003000005银行基本信息查询	02管理机构信息查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyRzRs12003000005A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String instFullNm;		//机构全称
	private String instShrtNm;		//机构简称
	private String instTp;			//机构类别	1-总行、2-分行、3-直属支行、4-支行、5-社区银行、6-村镇银行、7-小企业信贷中心、8-国际业务中心、9-重点客户中心、10-内设部门、11-虚拟机构
	private String instLvl;			//机构级别	1-总行、2-分行（村镇、中心）、3-一级支行、4-二级支行、5-一级部门、6-二级部门、7-三级部门
	private String instSt;			//机构状态	1-正常、2-撤消、3-筹建
	private String loInf;			//所在地信息	220000.220600.220605代表吉林省.白山市.江源区
	private String offcAdr;			//办公地址	220000.220600.220605.xxx街道【代表吉林省.白山市.江源区.xxx街道】
	private String rgstAdr;			//注册地址	220000.220600.220605.xxx街道【代表吉林省.白山市.江源区.xxx街道】
	private String pstCd;			//邮政编码	
	private String telNo;			//电话号码
	private String faxNo;			//传真号码
	private String permStfNum;		//在编人数
	private String estbDt;			//成立日期	YYYYMMDD
	private String cityCntyFlag;	//城县标志	机构类别为直属支行时输出1-城区、2-县域
	private String instInf;			//机构信息
	private double wrkCptlAmt;		//运营资金	单位：万
	private String dirWrkNo;		//负责人工号
	private String bsnLcsNo;		//营业执照号
	private String bsnLcsIssuOffc;	//营业执照签发机关
	private String fncLcsNo;		//金融许可证机构编码
	private String orgInstCd;		//组织机构代码
	private String orgCdIssuOffc;	//组织机构代码证签发机关
	private String natTaxRgstCtfNo;	//国税税务登记证号
	private String lclTaxRgstCtfNo;	//地税税务登记证号
	private String instBsnScop;		//机构业务经营范围
	private String deptAttrCd;		//部门属性	1-前台、2-中台、3-后台
	private String opnDt;			//开业日期	YYYYMMDD
	private String rgonCd;			//地区代码	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String branchId;		//机构代码	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	
	public EsbBodyRzRs12003000005A02(){
		
	}

	public String getInstFullNm() {
		return instFullNm;
	}

	@XmlElement(name = "InstFullNm")
	public void setInstFullNm(String instFullNm) {
		this.instFullNm = instFullNm;
	}

	public String getInstShrtNm() {
		return instShrtNm;
	}

	@XmlElement(name = "InstShrtNm")
	public void setInstShrtNm(String instShrtNm) {
		this.instShrtNm = instShrtNm;
	}

	public String getInstTp() {
		return instTp;
	}

	@XmlElement(name = "InstTp")
	public void setInstTp(String instTp) {
		this.instTp = instTp;
	}

	public String getInstLvl() {
		return instLvl;
	}

	@XmlElement(name = "InstLvl")
	public void setInstLvl(String instLvl) {
		this.instLvl = instLvl;
	}

	public String getInstSt() {
		return instSt;
	}

	@XmlElement(name = "InstSt")
	public void setInstSt(String instSt) {
		this.instSt = instSt;
	}

	public String getLoInf() {
		return loInf;
	}

	@XmlElement(name = "LoInf")
	public void setLoInf(String loInf) {
		this.loInf = loInf;
	}

	public String getOffcAdr() {
		return offcAdr;
	}

	@XmlElement(name = "OffcAdr")
	public void setOffcAdr(String offcAdr) {
		this.offcAdr = offcAdr;
	}

	public String getRgstAdr() {
		return rgstAdr;
	}

	@XmlElement(name = "RgstAdr")
	public void setRgstAdr(String rgstAdr) {
		this.rgstAdr = rgstAdr;
	}

	public String getPstCd() {
		return pstCd;
	}

	@XmlElement(name = "PstCd")
	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}

	public String getTelNo() {
		return telNo;
	}

	@XmlElement(name = "TelNo")
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	@XmlElement(name = "FaxNo")
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getPermStfNum() {
		return permStfNum;
	}

	@XmlElement(name = "PermStfNum")
	public void setPermStfNum(String permStfNum) {
		this.permStfNum = permStfNum;
	}

	public String getEstbDt() {
		return estbDt;
	}

	@XmlElement(name = "EstbDt")
	public void setEstbDt(String estbDt) {
		this.estbDt = estbDt;
	}

	public String getCityCntyFlag() {
		return cityCntyFlag;
	}

	@XmlElement(name = "CityCntyFlag")
	public void setCityCntyFlag(String cityCntyFlag) {
		this.cityCntyFlag = cityCntyFlag;
	}

	public String getInstInf() {
		return instInf;
	}

	@XmlElement(name = "InstInf")
	public void setInstInf(String instInf) {
		this.instInf = instInf;
	}

	public double getWrkCptlAmt() {
		return wrkCptlAmt;
	}

	@XmlElement(name = "WrkCptlAmt")
	public void setWrkCptlAmt(double wrkCptlAmt) {
		this.wrkCptlAmt = wrkCptlAmt;
	}

	public String getDirWrkNo() {
		return dirWrkNo;
	}

	@XmlElement(name = "DirWrkNo")
	public void setDirWrkNo(String dirWrkNo) {
		this.dirWrkNo = dirWrkNo;
	}

	public String getBsnLcsNo() {
		return bsnLcsNo;
	}

	@XmlElement(name = "BsnLcsNo")
	public void setBsnLcsNo(String bsnLcsNo) {
		this.bsnLcsNo = bsnLcsNo;
	}

	public String getBsnLcsIssuOffc() {
		return bsnLcsIssuOffc;
	}

	@XmlElement(name = "BsnLcsIssuOffc")
	public void setBsnLcsIssuOffc(String bsnLcsIssuOffc) {
		this.bsnLcsIssuOffc = bsnLcsIssuOffc;
	}

	public String getFncLcsNo() {
		return fncLcsNo;
	}

	@XmlElement(name = "FncLcsNo")
	public void setFncLcsNo(String fncLcsNo) {
		this.fncLcsNo = fncLcsNo;
	}

	public String getOrgInstCd() {
		return orgInstCd;
	}

	@XmlElement(name = "OrgInstCd")
	public void setOrgInstCd(String orgInstCd) {
		this.orgInstCd = orgInstCd;
	}

	public String getOrgCdIssuOffc() {
		return orgCdIssuOffc;
	}

	@XmlElement(name = "OrgCdIssuOffc")
	public void setOrgCdIssuOffc(String orgCdIssuOffc) {
		this.orgCdIssuOffc = orgCdIssuOffc;
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

	public String getInstBsnScop() {
		return instBsnScop;
	}

	@XmlElement(name = "InstBsnScop")
	public void setInstBsnScop(String instBsnScop) {
		this.instBsnScop = instBsnScop;
	}

	public String getDeptAttrCd() {
		return deptAttrCd;
	}

	@XmlElement(name = "DeptAttrCd")
	public void setDeptAttrCd(String deptAttrCd) {
		this.deptAttrCd = deptAttrCd;
	}

	public String getOpnDt() {
		return opnDt;
	}

	@XmlElement(name = "OpnDt")
	public void setOpnDt(String opnDt) {
		this.opnDt = opnDt;
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
