package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRs12002000012A01 
 * @Description: 12002000012员工信息验证		01员工编号信息查询验证
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyRzRs12002000012A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String empeWrkNo;		//员工工号
	private String empeTp;			//员工类别	0-培训期 1-试用期 2-派遣 3-正式	
	private String empeSt;			//员工状态	0-在职 1-离职 2-内退 3-退休
	private String rgonBr;			//地区机构
	private String branchId;		//机构代码
	private String name;			//姓名
	private String gndInd;			//性别标志	0-男 1-女
	private String domcLo;			//户籍所在地
	private String famAdr;			//家庭住址
	private String nation;			//民族	00-未知、01-汉族、02-蒙古族、03-回族、04-藏族、05-维吾尔族、06-苗族、07-彝族、08-壮族、09-布依族、10-朝鲜族、11-满族、12-侗族、13-瑶族、14-白族、15-土家族、16-哈尼族、17-哈萨克族、18-傣族、19-黎族、20-傈僳族、21-佤族、22-畲族、23-高山族、24-拉祜族、25-水族、26-东乡族、27-纳西族、28-景颇族、29-柯尔克孜族、30-土族、31-达斡尔族、32-仫佬族、33-羌族、34-布朗族、35-撒拉族、36-毛南族、37-仡佬族、38-锡伯族、39-阿昌族、40-普米族、41-塔吉克族、42-怒族、43-乌孜别克族、44-俄罗斯族、45-鄂温克族、46-德昂族、47-保安族、48-裕固族、49-京族、50-塔塔尔族、51-独龙族、52-鄂伦春族、53-赫哲族、54-门巴族、55-珞巴族、56-基诺族、57-其它、58-外国血统中国籍人士
	private String natPlc;			//籍贯	例：220000.220600.220605代表吉林省.白山市.江源区
	private String birthDate;		//出生日期
	private String identInfRcrdNum;	//证件信息记录数
	private String ctcInfRcrdNum;	//联系信息记录数
	private EsbBodyRzRsIdentInfArray[] esbBodyRzRqIdentInfArray;//证件信息数组
	private EsbBodyRzRsCtcInfArray[] esbBodyRzRqCtcInfArray;	//联系信息数组
	
	public EsbBodyRzRs12002000012A01(){
		
	}

	public String getEmpeWrkNo() {
		return empeWrkNo;
	}

	@XmlElement(name = "EmpeWrkNo")
	public void setEmpeWrkNo(String empeWrkNo) {
		this.empeWrkNo = empeWrkNo;
	}

	public String getEmpeTp() {
		return empeTp;
	}

	@XmlElement(name = "EmpeTp")
	public void setEmpeTp(String empeTp) {
		this.empeTp = empeTp;
	}

	public String getEmpeSt() {
		return empeSt;
	}

	@XmlElement(name = "EmpeSt")
	public void setEmpeSt(String empeSt) {
		this.empeSt = empeSt;
	}

	public String getRgonBr() {
		return rgonBr;
	}

	@XmlElement(name = "RgonBr")
	public void setRgonBr(String rgonBr) {
		this.rgonBr = rgonBr;
	}

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	public String getGndInd() {
		return gndInd;
	}

	@XmlElement(name = "GndInd")
	public void setGndInd(String gndInd) {
		this.gndInd = gndInd;
	}

	public String getDomcLo() {
		return domcLo;
	}

	@XmlElement(name = "DomcLo")
	public void setDomcLo(String domcLo) {
		this.domcLo = domcLo;
	}

	public String getFamAdr() {
		return famAdr;
	}

	@XmlElement(name = "FamAdr")
	public void setFamAdr(String famAdr) {
		this.famAdr = famAdr;
	}

	public String getNation() {
		return nation;
	}

	@XmlElement(name = "Nation")
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNatPlc() {
		return natPlc;
	}

	@XmlElement(name = "NatPlc")
	public void setNatPlc(String natPlc) {
		this.natPlc = natPlc;
	}

	public String getBirthDate() {
		return birthDate;
	}

	@XmlElement(name = "BirthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getIdentInfRcrdNum() {
		return identInfRcrdNum;
	}

	@XmlElement(name = "IdentInfRcrdNum")
	public void setIdentInfRcrdNum(String identInfRcrdNum) {
		this.identInfRcrdNum = identInfRcrdNum;
	}

	public String getCtcInfRcrdNum() {
		return ctcInfRcrdNum;
	}

	@XmlElement(name = "CtcInfRcrdNum")
	public void setCtcInfRcrdNum(String ctcInfRcrdNum) {
		this.ctcInfRcrdNum = ctcInfRcrdNum;
	}

	public EsbBodyRzRsIdentInfArray[] getEsbBodyRzRqIdentInfArray() {
		return esbBodyRzRqIdentInfArray;
	}

	@XmlElement(name = "IdentInfArray")
	public void setEsbBodyRzRqIdentInfArray(
			EsbBodyRzRsIdentInfArray[] esbBodyRzRqIdentInfArray) {
		this.esbBodyRzRqIdentInfArray = esbBodyRzRqIdentInfArray;
	}

	public EsbBodyRzRsCtcInfArray[] getEsbBodyRzRqCtcInfArray() {
		return esbBodyRzRqCtcInfArray;
	}

	@XmlElement(name = "CtcInfArray")
	public void setEsbBodyRzRqCtcInfArray(
			EsbBodyRzRsCtcInfArray[] esbBodyRzRqCtcInfArray) {
		this.esbBodyRzRqCtcInfArray = esbBodyRzRqCtcInfArray;
	}
}
