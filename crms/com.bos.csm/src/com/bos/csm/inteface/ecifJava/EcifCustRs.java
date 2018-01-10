package com.bos.csm.inteface.ecifJava;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
/**
 * ECIF系统查看客户信息
 * 相应
 * @author git
 *
 */
public class EcifCustRs extends SuperBosfxRs {

//	 ECIF客户号
	@XmlElement(name="Customer")
	public String Customer;
	
//	客户名称   
	@XmlElement(name="SPName")
	public String SPName;

//	客户英文名
	@XmlElement(name="GBShortName")
	public String GBShortName;

//	国家或地区
	@XmlElement(name="Nationality")
	public String Nationality;

//	贷款卡号
	@XmlElement(name="LoanCardNo")
	public String LoanCardNo;

//	商业客户类型
	@XmlElement(name="ApplyType")
	public String ApplyType;

//	所在国家（地区）
	@XmlElement(name="ResidenceCountryCd")
	public String ResidenceCountryCd;

//	企业经济成分
	@XmlElement(name="EcgncSctCode")
	public String EcgncSctCode;

//	企业经济类型
	@XmlElement(name="EcgncCtgCode")
	public String EcgncCtgCode;

//	反洗钱评级结果
	@XmlElement(name="IndustrialTypeCd")
	public String IndustrialTypeCd;

//	行业类型
	@XmlElement(name="Mertype")
	public String Mertype;

//	是否上市公司
	@XmlElement(name="ListedCmp")
	public String ListedCmp;

//	企业成立日期
	@XmlElement(name="EstabDate")
	public String EstabDate;

//	注册资本
	@XmlElement(name="CapReg")
	public String CapReg;

//	注册资本币种
	@XmlElement(name="Currency")
	public String Currency;

//	从业人数
	@XmlElement(name="Number")
	public String Number;

//	营业收入
	@XmlElement(name="InputAmt")
	public String InputAmt;

//	资产总额
	@XmlElement(name="TranTotalAmt")
	public String TranTotalAmt;

//	主营业务
	@XmlElement(name="BusiTypeName")
	public String BusiTypeName;

//	企业年检结果
	@XmlElement(name="AnnualRst")
	public String AnnualRst;

//	贷款卡首次申领日期
	@XmlElement(name="BeginDt")
	public String BeginDt;

//	贷款卡年检标识
	@XmlElement(name="IndexNo")
	public String IndexNo;

//	贷款卡年检时间
	@XmlElement(name="TranTime")
	public String TranTime;

//	公司E-Mail
	@XmlElement(name="Email")
	public String Email;

//	公司网址
	@XmlElement(name="CompanyUrl")
	public String CompanyUrl;

//	联系电话
	@XmlElement(name="Telephone")
	public String Telephone;

//	传真电话
	@XmlElement(name="FaxPhone")
	public String FaxPhone;

//	财务部联系电话
	@XmlElement(name="MobilePhone")
	public String MobilePhone;

//	税务登记号（国税）
	@XmlElement(name="CntyRegId")
	public String CntyRegId;

//	税务登记证号（地税）
	@XmlElement(name="CityRegId")
	public String CityRegId;

//	与我行建立信贷关系时间
	@XmlElement(name="AppTime")
	public String AppTime;

//	有无进出口经营权
	@XmlElement(name="ImpExpFlg")
	public String ImpExpFlg;

//	是否科技型企业
	@XmlElement(name="CorpFlg")
	public String CorpFlg;

//	经营场地面积（平方米）
	@XmlElement(name="CorpArea")
	public String CorpArea;

//	经营场地所有权
	@XmlElement(name="CorpAreaOwn")
	public String CorpAreaOwn;

//	企业控股类型
	@XmlElement(name="CorpHoldType")
	public String CorpHoldType;

//	是否关停企业
	@XmlElement(name="CorpCloseFlg")
	public String CorpCloseFlg;

//	是否规模以上企业
	@XmlElement(name="CorpDespSizeFlg")
	public String CorpDespSizeFlg;

//	是否限额以上企业
	@XmlElement(name="CorpLmtSizeFlg")
	public String CorpLmtSizeFlg;

//	是否全行及重点目标客户
	@XmlElement(name="CorpBnkImptFlg")
	public String CorpBnkImptFlg;

//	是否小企业认定通过
	@XmlElement(name="CorpPassPeatFlg")
	public String CorpPassPeatFlg;

//	是否授信客户
	@XmlElement(name="CorpCreditFlg")
	public String CorpCreditFlg;

//	是否专项客户
	@XmlElement(name="CorpSpclFlg")
	public String CorpSpclFlg;

//	代理商名称
	@XmlElement(name="AgentName")
	public String AgentName;

//	是否重点客户
	@XmlElement(name="CorpImportFlg")
	public String CorpImportFlg;

//	是否Vip客户
	@XmlElement(name="CorpVipFlg")
	public String CorpVipFlg;

//	注册日期
	@XmlElement(name="CM_sEdDay")
	public String CM_sEdDay;

//	注册资本到位率
	@XmlElement(name="RegtAssRate")
	public String RegtAssRate;

//	法人证书号码
	@XmlElement(name="IdNo")
	public String IdNo;

//	主管部门
	@XmlElement(name="MainDept")
	public String MainDept;

//	开办资金
	@XmlElement(name="InitAmt")
	public String InitAmt;

//	经费来源
	@XmlElement(name="FinaResources")
	public String FinaResources;

//	举办单位
	@XmlElement(name="CompanyName")
	public String CompanyName;

//	客户方联系人
	@XmlElement(name="ContactPerson")
	public String ContactPerson;

//	财务部联系人
	@XmlElement(name="ContactPhone")
	public String ContactPhone;

//	法人证书有效期
	@XmlElement(name="LegalCrtftEnDt")
	public String LegalCrtftEnDt;

//	宗旨和业务范围
	@XmlElement(name="PurposeScope")
	public String PurposeScope;

//	登记机关
	@XmlElement(name="RegOrg")
	public String RegOrg;

//	客户历史沿革管理水平简介
	@XmlElement(name="ReMark")
	public String ReMark;

//	经营状况说明
	@XmlElement(name="Usage")
	public String Usage;

//	主要产品情况
	@XmlElement(name="ProdtRemark")
	public String ProdtRemark;

//	开户核准号
	@XmlElement(name="OpenAcctNo")
	public String OpenAcctNo;

//	行政区划
	@XmlElement(name="AdminDiv")
	public String AdminDiv;

//	是否在我行有结算帐户
	@XmlElement(name="ClrAcctFlg")
	public String ClrAcctFlg;

//	是否有出口退税帐户
	@XmlElement(name="DrawBackAcctFlg")
	public String DrawBackAcctFlg;

//	有无优惠政策
	@XmlElement(name="PerfAcctFlg")
	public String PerfAcctFlg;

//	是否出口收汇关注企业
	@XmlElement(name="EnterpFlg")
	public String EnterpFlg;

//	四部委企业规模-自动计算
	@XmlElement(name="FurzEnterpSize")
	public String FurzEnterpSize;

//	四部委企业规模-人工输入
	@XmlElement(name="FurrEnterpSize")
	public String FurrEnterpSize;

//	金融机构类型
	@XmlElement(name="BankType")
	public String BankType;

//	是否黑名单客户
	@XmlElement(name="BlackLstFlg")
	public String BlackLstFlg;

//	黑名单进入原因
	@XmlElement(name="BlklstRson")
	public String BlklstRson;

//	是否集团客户
	@XmlElement(name="GrpRelCutFlg")
	public String GrpRelCutFlg;

//	集团成员关系种类代码
	@XmlElement(name="GrpRelCutCode")
	public String GrpRelCutCode;

//	开办资金币种
	@XmlElement(name="OpenCurrecy")
	public String OpenCurrecy;

//	登记注册类型
	@XmlElement(name="RegType")
	public String RegType;

//	是否自贸区客户
	@XmlElement(name="FtaCsmFlg")
	public String FtaCsmFlg;

//	组织机构登记日期
	@XmlElement(name="InputDt")
	public String InputDt;

//	同业类型
	@XmlElement(name="FinanceInstType")
	public String FinanceInstType;

//	swift BIC码
	@XmlElement(name="SwiftBicCode")
	public String SwiftBicCode;

//	客户主体类型
	@XmlElement(name="SubjectType")
	public String SubjectType;

//	工商年检日期
	@XmlElement(name="InspectionDate")
	public String InspectionDate;

//	金融许可证机构编码
	@XmlElement(name="CollectDepCode")
	public String CollectDepCode;

//	客户信贷政策
	@XmlElement(name="CreditPolicy")
	public String CreditPolicy;

//	资产总额币种
	@XmlElement(name="TotalCurry")
	public String TotalCurry;

//	区域类型-境内外
	@XmlElement(name="AreaType")
	public String AreaType;

//	贷款卡是否有效
	@XmlElement(name="CommiStatus")
	public String CommiStatus;

//	是否从事房地产开发
	@XmlElement(name="EstateFlag")
	public String EstateFlag;

//	是否高新技术企业
	@XmlElement(name="TechCompFlag")
	public String TechCompFlag;

//	金交所客户代码
	@XmlElement(name="GoldCustId")
	public String GoldCustId;

//	中资标志
	@XmlElement(name="CountryFinFlg")
	public String CountryFinFlg;

//	备注1
	@XmlElement(name="Remark_1")
	public String Remark_1;

//	备注2
	@XmlElement(name="Remark_2")
	public String Remark_2;

//	备注3
	@XmlElement(name="Remark_3")
	public String Remark_3;

//	证件类型
	@XmlElement(name="C201OrgCusEfLglfRec")
	public List<CertInfo> certs;

//	地址
	@XmlElement(name="AddrAppRec")
	public List<AddressInfoForECIF> adds;
	
//	其他地址
	@XmlElement(name="NetAddrRec")
	public List<AddressInfo2> adds2;

	
	
	public void setAdminDiv(String adminDiv) {
		AdminDiv = adminDiv;
	}




	public void setAgentName(String agentName) {
		AgentName = agentName;
	}




	public void setAnnualRst(String annualRst) {
		AnnualRst = annualRst;
	}




	public void setApplyType(String applyType) {
		ApplyType = applyType;
	}




	public void setAppTime(String appTime) {
		AppTime = appTime;
	}




	public void setAreaType(String areaType) {
		AreaType = areaType;
	}




	public void setBankType(String bankType) {
		BankType = bankType;
	}




	public void setBeginDt(String beginDt) {
		BeginDt = beginDt;
	}




	public void setBlackLstFlg(String blackLstFlg) {
		BlackLstFlg = blackLstFlg;
	}




	public void setBlklstRson(String blklstRson) {
		BlklstRson = blklstRson;
	}




	public void setBusiTypeName(String busiTypeName) {
		BusiTypeName = busiTypeName;
	}




	public void setCapReg(String capReg) {
		CapReg = capReg;
	}




	public void setCityRegId(String cityRegId) {
		CityRegId = cityRegId;
	}




	public void setClrAcctFlg(String clrAcctFlg) {
		ClrAcctFlg = clrAcctFlg;
	}




	public void setCM_sEdDay(String edDay) {
		CM_sEdDay = edDay;
	}




	public void setCntyRegId(String cntyRegId) {
		CntyRegId = cntyRegId;
	}




	public void setCollectDepCode(String collectDepCode) {
		CollectDepCode = collectDepCode;
	}




	public void setCommiStatus(String commiStatus) {
		CommiStatus = commiStatus;
	}




	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}




	public void setCompanyUrl(String companyUrl) {
		CompanyUrl = companyUrl;
	}




	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}




	public void setContactPhone(String contactPhone) {
		ContactPhone = contactPhone;
	}




	public void setCorpArea(String corpArea) {
		CorpArea = corpArea;
	}




	public void setCorpAreaOwn(String corpAreaOwn) {
		CorpAreaOwn = corpAreaOwn;
	}




	public void setCorpBnkImptFlg(String corpBnkImptFlg) {
		CorpBnkImptFlg = corpBnkImptFlg;
	}




	public void setCorpCloseFlg(String corpCloseFlg) {
		CorpCloseFlg = corpCloseFlg;
	}




	public void setCorpCreditFlg(String corpCreditFlg) {
		CorpCreditFlg = corpCreditFlg;
	}




	public void setCorpDespSizeFlg(String corpDespSizeFlg) {
		CorpDespSizeFlg = corpDespSizeFlg;
	}




	public void setCorpFlg(String corpFlg) {
		CorpFlg = corpFlg;
	}




	public void setCorpHoldType(String corpHoldType) {
		CorpHoldType = corpHoldType;
	}




	public void setCorpImportFlg(String corpImportFlg) {
		CorpImportFlg = corpImportFlg;
	}




	public void setCorpLmtSizeFlg(String corpLmtSizeFlg) {
		CorpLmtSizeFlg = corpLmtSizeFlg;
	}




	public void setCorpPassPeatFlg(String corpPassPeatFlg) {
		CorpPassPeatFlg = corpPassPeatFlg;
	}




	public void setCorpSpclFlg(String corpSpclFlg) {
		CorpSpclFlg = corpSpclFlg;
	}




	public void setCorpVipFlg(String corpVipFlg) {
		CorpVipFlg = corpVipFlg;
	}




	public void setCountryFinFlg(String countryFinFlg) {
		CountryFinFlg = countryFinFlg;
	}




	public void setCreditPolicy(String creditPolicy) {
		CreditPolicy = creditPolicy;
	}




	public void setCurrency(String currency) {
		Currency = currency;
	}




	public void setDrawBackAcctFlg(String drawBackAcctFlg) {
		DrawBackAcctFlg = drawBackAcctFlg;
	}




	public void setEcgncCtgCode(String ecgncCtgCode) {
		EcgncCtgCode = ecgncCtgCode;
	}




	public void setEcgncSctCode(String ecgncSctCode) {
		EcgncSctCode = ecgncSctCode;
	}




	public void setEmail(String email) {
		Email = email;
	}




	public void setEnterpFlg(String enterpFlg) {
		EnterpFlg = enterpFlg;
	}




	public void setEstabDate(String estabDate) {
		EstabDate = estabDate;
	}




	public void setEstateFlag(String estateFlag) {
		EstateFlag = estateFlag;
	}




	public void setFaxPhone(String faxPhone) {
		FaxPhone = faxPhone;
	}




	public void setFinanceInstType(String financeInstType) {
		FinanceInstType = financeInstType;
	}




	public void setFinaResources(String finaResources) {
		FinaResources = finaResources;
	}




	public void setFtaCsmFlg(String ftaCsmFlg) {
		FtaCsmFlg = ftaCsmFlg;
	}




	public void setFurrEnterpSize(String furrEnterpSize) {
		FurrEnterpSize = furrEnterpSize;
	}




	public void setFurzEnterpSize(String furzEnterpSize) {
		FurzEnterpSize = furzEnterpSize;
	}




	public void setGBShortName(String shortName) {
		GBShortName = shortName;
	}




	public void setGoldCustId(String goldCustId) {
		GoldCustId = goldCustId;
	}




	public void setGrpRelCutCode(String grpRelCutCode) {
		GrpRelCutCode = grpRelCutCode;
	}




	public void setGrpRelCutFlg(String grpRelCutFlg) {
		GrpRelCutFlg = grpRelCutFlg;
	}




	public void setIdNo(String idNo) {
		IdNo = idNo;
	}




	public void setImpExpFlg(String impExpFlg) {
		ImpExpFlg = impExpFlg;
	}




	public void setIndexNo(String indexNo) {
		IndexNo = indexNo;
	}




	public void setIndustrialTypeCd(String industrialTypeCd) {
		IndustrialTypeCd = industrialTypeCd;
	}




	public void setInitAmt(String initAmt) {
		InitAmt = initAmt;
	}




	public void setInputAmt(String inputAmt) {
		InputAmt = inputAmt;
	}




	public void setInputDt(String inputDt) {
		InputDt = inputDt;
	}




	public void setInspectionDate(String inspectionDate) {
		InspectionDate = inspectionDate;
	}




	public void setLegalCrtftEnDt(String legalCrtftEnDt) {
		LegalCrtftEnDt = legalCrtftEnDt;
	}




	public void setListedCmp(String listedCmp) {
		ListedCmp = listedCmp;
	}




	public void setLoanCardNo(String loanCardNo) {
		LoanCardNo = loanCardNo;
	}




	public void setMainDept(String mainDept) {
		MainDept = mainDept;
	}




	public void setMertype(String mertype) {
		Mertype = mertype;
	}




	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}




	public void setNationality(String nationality) {
		Nationality = nationality;
	}




	public void setNumber(String number) {
		Number = number;
	}




	public void setOpenAcctNo(String openAcctNo) {
		OpenAcctNo = openAcctNo;
	}




	public void setOpenCurrecy(String openCurrecy) {
		OpenCurrecy = openCurrecy;
	}




	public void setPerfAcctFlg(String perfAcctFlg) {
		PerfAcctFlg = perfAcctFlg;
	}




	public void setProdtRemark(String prodtRemark) {
		ProdtRemark = prodtRemark;
	}




	public void setPurposeScope(String purposeScope) {
		PurposeScope = purposeScope;
	}




	public void setRegOrg(String regOrg) {
		RegOrg = regOrg;
	}




	public void setRegtAssRate(String regtAssRate) {
		RegtAssRate = regtAssRate;
	}




	public void setRegType(String regType) {
		RegType = regType;
	}




	public void setReMark(String reMark) {
		ReMark = reMark;
	}




	public void setRemark_1(String remark_1) {
		Remark_1 = remark_1;
	}




	public void setRemark_2(String remark_2) {
		Remark_2 = remark_2;
	}




	public void setRemark_3(String remark_3) {
		Remark_3 = remark_3;
	}




	public void setResidenceCountryCd(String residenceCountryCd) {
		ResidenceCountryCd = residenceCountryCd;
	}




	public void setSPName(String name) {
		SPName = name;
	}




	public void setSubjectType(String subjectType) {
		SubjectType = subjectType;
	}




	public void setSwiftBicCode(String swiftBicCode) {
		SwiftBicCode = swiftBicCode;
	}




	public void setTechCompFlag(String techCompFlag) {
		TechCompFlag = techCompFlag;
	}




	public void setTelephone(String telephone) {
		Telephone = telephone;
	}




	public void setTotalCurry(String totalCurry) {
		TotalCurry = totalCurry;
	}




	public void setTranTime(String tranTime) {
		TranTime = tranTime;
	}




	public void setTranTotalAmt(String tranTotalAmt) {
		TranTotalAmt = tranTotalAmt;
	}




	public void setUsage(String usage) {
		Usage = usage;
	}



	public void setAdds(List<AddressInfoForECIF> adds) {
		this.adds = adds;
	}




	public void setCerts(List<CertInfo> certs) {
		this.certs = certs;
	}




	public void setCustomer(String customer) {
		Customer = customer;
	}




	@Override
	public String toString() {
		return "EcifCustRs [SPName=" + SPName + ", GBShortName="
		+ GBShortName + ", Nationality=" + Nationality + ", LoanCardNo="
		+ LoanCardNo  ;
	}


}
