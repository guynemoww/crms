package com.primeton.tsl.response;

import java.sql.Date;

public class ResponseB101 {
	@Override
	public String toString() {
		return "ReqestB101 [CustNo=" + CustNo + ", CustName=" + CustName
				+ ", CertKind=" + CertKind + ", CertNo=" + CertNo
				+ ", CertifiInstituNaturn=" + CertifiInstituNaturn
				+ ", CERT_ORG_AREA=" + CERT_ORG_AREA + ", CERT_ORG_NAME="
				+ CERT_ORG_NAME + ", CERT_ISSUE_DATE=" + CERT_ISSUE_DATE
				+ ", CERT_DUE_DATE=" + CERT_DUE_DATE + ", CERT_EDIT_NO="
				+ CERT_EDIT_NO + ", CERT_ORG_NO=" + CERT_ORG_NO
				+ ", CERT_TYPE_FLAG=" + CERT_TYPE_FLAG + ", EnName=" + EnName
				+ ", CUST_SPNAME=" + CUST_SPNAME + ", CUST_BNAME=" + CUST_BNAME
				+ ", CUST_CALL=" + CUST_CALL + ", Sex=" + Sex + ", Nation="
				+ Nation + ", Birthday=" + Birthday + ", BIRTH_PLACE="
				+ BIRTH_PLACE + ", HEALTH_STATE=" + HEALTH_STATE
				+ ", MARITAL_STAT=" + MARITAL_STAT + ", CHILDREN_STATE="
				+ CHILDREN_STATE + ", NAT_CODE=" + NAT_CODE
				+ ", NationalAllName=" + NationalAllName + ", NATIVE_AREA="
				+ NATIVE_AREA + ", RGSTER_AREA=" + RGSTER_AREA
				+ ", LANGUAGE_CODE=" + LANGUAGE_CODE + ", HOBB_INTRST="
				+ HOBB_INTRST + ", RELIG_CODE=" + RELIG_CODE + ", POLIT_STAT="
				+ POLIT_STAT + ", EDU_STATE=" + EDU_STATE + ", HIGHEST_DEGREE="
				+ HIGHEST_DEGREE + ", GRADUATION_SCHOLL=" + GRADUATION_SCHOLL
				+ ", SPECIALTY=" + SPECIALTY + ", GRAD_DATE=" + GRAD_DATE
				+ ", RSDT_TYPE=" + RSDT_TYPE + ", RSDT_ATTR=" + RSDT_ATTR
				+ ", DOM_FORGN_CD=" + DOM_FORGN_CD + ", PMT_RSDT_FLAG="
				+ PMT_RSDT_FLAG + ", COUNTRY_CODE=" + COUNTRY_CODE
				+ ", LIVE_AREA_CODE=" + LIVE_AREA_CODE + ", GenAreaCode="
				+ GenAreaCode + ", RESIDE_START_TIME=" + RESIDE_START_TIME
				+ ", FARMER_FLAG=" + FARMER_FLAG + ", CUST_SUBJ_TYPE="
				+ CUST_SUBJ_TYPE + ", PRIVATE_CAR_FLAG=" + PRIVATE_CAR_FLAG
				+ ", LIVG_CONDIT=" + LIVG_CONDIT + ", RESDT_TYPE=" + RESDT_TYPE
				+ ", IDVU_SCL_INSURS_NO=" + IDVU_SCL_INSURS_NO
				+ ", SOCIALSB_NO=" + SOCIALSB_NO + ", SOCIALSB_NAME="
				+ SOCIALSB_NAME + ", IDVU_TX_NO=" + IDVU_TX_NO + ", YNDuty="
				+ YNDuty + ", StaTax=" + StaTax + ", INTEREST_RATE="
				+ INTEREST_RATE + ", TaxEffFlg=" + TaxEffFlg
				+ ", MONTH_INCOME=" + MONTH_INCOME + ", YEAR_SALARY="
				+ YEAR_SALARY + ", ECON_RESUR=" + ECON_RESUR
				+ ", PSN_ASSET_TYPE=" + PSN_ASSET_TYPE + ", NUM_DEPEND="
				+ NUM_DEPEND + ", FAM_MONTH=" + FAM_MONTH + ", FAM_YEAR="
				+ FAM_YEAR + ", FAM_ASSETS=" + FAM_ASSETS + ", FAM_MEMB_TOTAL="
				+ FAM_MEMB_TOTAL + ", UNIT_NAME=" + UNIT_NAME + ", UNIT_TYPE="
				+ UNIT_TYPE + ", INDUSTRY_TYPE=" + INDUSTRY_TYPE
				+ ", Perfessional=" + Perfessional + ", JOB_LEVEL=" + JOB_LEVEL
				+ ", position=" + position + ", TitleOfTechnical="
				+ TitleOfTechnical + ", WORK_STAT=" + WORK_STAT
				+ ", QUALFT_STAT=" + QUALFT_STAT + ", WORK_START_DATE="
				+ WORK_START_DATE + ", UNIT_START_YEAR=" + UNIT_START_YEAR
				+ ", BANK_REL_CODE=" + BANK_REL_CODE + ", SHAREHOLDER_FLAG="
				+ SHAREHOLDER_FLAG + ", STOCK_NUM=" + STOCK_NUM
				+ ", STOCK_ACCT=" + STOCK_ACCT + ", CUST_FORE_EXCH_ATTR="
				+ CUST_FORE_EXCH_ATTR + ", OutCredLvl=" + OutCredLvl
				+ ", CustRiskScale=" + CustRiskScale + ", CREDIT_LEVEL="
				+ CREDIT_LEVEL + ", GRADE_DATE=" + GRADE_DATE
				+ ", GRADE_DUE_DATE=" + GRADE_DUE_DATE + ", CustComEvalLevel="
				+ CustComEvalLevel + ", CUST_LEVEL=" + CUST_LEVEL
				+ ", IdChkFlg=" + IdChkFlg + ", IDE_CHECK_RESULT="
				+ IDE_CHECK_RESULT + ", IDE_FALSE_REASON=" + IDE_FALSE_REASON
				+ ", CUST_MANAGER_NO=" + CUST_MANAGER_NO + ", CustMngrName="
				+ CustMngrName + "]";
	}

	String CustNo; //客户编号
	
	String CustName; //客户名称

	String CertKind; //证件类型

	String CertNo; //证件号码

	String CertifiInstituNaturn; //发证机关国家

	String CERT_ORG_AREA; //发证机关所在地区

	String CERT_ORG_NAME; //发证机关名称

	String CERT_ISSUE_DATE; //证件签发日期

	String CERT_DUE_DATE; //证件到期日期

	String CERT_EDIT_NO; //证件版本号

	String CERT_ORG_NO; //当次申请受理机关代码

	String CERT_TYPE_FLAG; //证件类型标识

	String EnName; //英文名称

	String CUST_SPNAME; //拼音名称

	String CUST_BNAME; //客户曾用名

	String CUST_CALL; //客户称谓

	String Sex; //性别

	String Nation; //民族

	String Birthday; //出生日期

	String BIRTH_PLACE; //出生地点

	String HEALTH_STATE; //健康状况

	String MARITAL_STAT; //婚姻状态

	String CHILDREN_STATE; //子女状况

	String NAT_CODE; //国籍

	String NationalAllName; //国籍全称1

	String NATIVE_AREA; //籍贯

	String RGSTER_AREA; //户籍所在地

	String LANGUAGE_CODE; //语言习惯

	String HOBB_INTRST; //兴趣爱好

	String RELIG_CODE; //宗教信仰

	String POLIT_STAT; //政治面貌

	String EDU_STATE; //教育程度

	String HIGHEST_DEGREE; //最高学位

	String GRADUATION_SCHOLL; //毕业院校

	String SPECIALTY; //所学专业

	String GRAD_DATE; //毕业时间

	String RSDT_TYPE; //居民性质

	String RSDT_ATTR; //居民属性

	String DOM_FORGN_CD; //境内外标识

	String PMT_RSDT_FLAG; //是否永久居住

	String COUNTRY_CODE; //居住国家代码

	String LIVE_AREA_CODE; //居住地行政区划代码

	String GenAreaCode; //所属地区代码

	String RESIDE_START_TIME; //本地居住开始时间

	String FARMER_FLAG; //是否农户

	String CUST_SUBJ_TYPE; //客户主体类型

	String PRIVATE_CAR_FLAG; //是否有私家车

	String LIVG_CONDIT; //居住状况

	String RESDT_TYPE; //住宅类型

	String IDVU_SCL_INSURS_NO; //个人社会保险卡号

	String SOCIALSB_NO; //社保局编号

	String SOCIALSB_NAME; //社保局名称

	String IDVU_TX_NO; //个人纳税号

	String YNDuty; //是否免税

	String StaTax; //税率国别

	String INTEREST_RATE; //利息税率

	String TaxEffFlg; //税率有效日期

	String MONTH_INCOME; //个人月收入

	String YEAR_SALARY; //个人年收入

	String ECON_RESUR; //个人主要经济来源

	String PSN_ASSET_TYPE; //个人资产规模

	String NUM_DEPEND; //抚养人数

	String FAM_MONTH; //家庭月收入

	String FAM_YEAR; //家庭年收入

	String FAM_ASSETS; //家庭总资产

	String FAM_MEMB_TOTAL; //家庭成员总数

	String UNIT_NAME; //所在单位名称

	String UNIT_TYPE; //单位性质

	String INDUSTRY_TYPE; //从事行业类型

	String Perfessional; //职业

	String JOB_LEVEL; //职级

	String position; //职务

	String TitleOfTechnical; //职称

	String WORK_STAT; //从业状况代码

	String QUALFT_STAT; //执业资格状况

	String WORK_START_DATE; //参加工作时间

	String UNIT_START_YEAR; //本单位工作起始年份

	String BANK_REL_CODE; //与我行关系

	String SHAREHOLDER_FLAG; //是否本行股东

	String STOCK_NUM; //持股数

	String STOCK_ACCT; //股权账号

	String CUST_FORE_EXCH_ATTR; //客户外汇属性维护标志

	String OutCredLvl; //外部信用等级

	String CustRiskScale; //客户风险级别

	String CREDIT_LEVEL; //信用评级

	String GRADE_DATE; //评级日期

	String GRADE_DUE_DATE; //评级有效日期

	String CustComEvalLevel; //客户综合评估级别

	String CUST_LEVEL; //客户等级

	String IdChkFlg; //身份核查标志

	String IDE_CHECK_RESULT; //公民身份联网核查结果

	String IDE_FALSE_REASON; //公民身份无法核实原因

	String CUST_MANAGER_NO; //客户经理代码

	String CustMngrName; //客户经理名称
	public String getCustNo() {
		return CustNo;
	}

	public void setCustNo(String custNo) {
		CustNo = custNo;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		CustName = custName;
	}

	public String getCertKind() {
		return CertKind;
	}

	public void setCertKind(String certKind) {
		CertKind = certKind;
	}

	public String getCertNo() {
		return CertNo;
	}

	public void setCertNo(String certNo) {
		CertNo = certNo;
	}

	public String getCertifiInstituNaturn() {
		return CertifiInstituNaturn;
	}

	public void setCertifiInstituNaturn(String certifiInstituNaturn) {
		CertifiInstituNaturn = certifiInstituNaturn;
	}

	public String getCERT_ORG_AREA() {
		return CERT_ORG_AREA;
	}

	public void setCERT_ORG_AREA(String cERT_ORG_AREA) {
		CERT_ORG_AREA = cERT_ORG_AREA;
	}

	public String getCERT_ORG_NAME() {
		return CERT_ORG_NAME;
	}

	public void setCERT_ORG_NAME(String cERT_ORG_NAME) {
		CERT_ORG_NAME = cERT_ORG_NAME;
	}

	public String getCERT_ISSUE_DATE() {
		return CERT_ISSUE_DATE;
	}

	public void setCERT_ISSUE_DATE(String cERT_ISSUE_DATE) {
		CERT_ISSUE_DATE = cERT_ISSUE_DATE;
	}

	public String getCERT_DUE_DATE() {
		return CERT_DUE_DATE;
	}

	public void setCERT_DUE_DATE(String cERT_DUE_DATE) {
		CERT_DUE_DATE = cERT_DUE_DATE;
	}

	public String getCERT_EDIT_NO() {
		return CERT_EDIT_NO;
	}

	public void setCERT_EDIT_NO(String cERT_EDIT_NO) {
		CERT_EDIT_NO = cERT_EDIT_NO;
	}

	public String getCERT_ORG_NO() {
		return CERT_ORG_NO;
	}

	public void setCERT_ORG_NO(String cERT_ORG_NO) {
		CERT_ORG_NO = cERT_ORG_NO;
	}

	public String getCERT_TYPE_FLAG() {
		return CERT_TYPE_FLAG;
	}

	public void setCERT_TYPE_FLAG(String cERT_TYPE_FLAG) {
		CERT_TYPE_FLAG = cERT_TYPE_FLAG;
	}

	public String getEnName() {
		return EnName;
	}

	public void setEnName(String enName) {
		EnName = enName;
	}

	public String getCUST_SPNAME() {
		return CUST_SPNAME;
	}

	public void setCUST_SPNAME(String cUST_SPNAME) {
		CUST_SPNAME = cUST_SPNAME;
	}

	public String getCUST_BNAME() {
		return CUST_BNAME;
	}

	public void setCUST_BNAME(String cUST_BNAME) {
		CUST_BNAME = cUST_BNAME;
	}

	public String getCUST_CALL() {
		return CUST_CALL;
	}

	public void setCUST_CALL(String cUST_CALL) {
		CUST_CALL = cUST_CALL;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getBIRTH_PLACE() {
		return BIRTH_PLACE;
	}

	public void setBIRTH_PLACE(String bIRTH_PLACE) {
		BIRTH_PLACE = bIRTH_PLACE;
	}

	public String getHEALTH_STATE() {
		return HEALTH_STATE;
	}

	public void setHEALTH_STATE(String hEALTH_STATE) {
		HEALTH_STATE = hEALTH_STATE;
	}

	public String getMARITAL_STAT() {
		return MARITAL_STAT;
	}

	public void setMARITAL_STAT(String mARITAL_STAT) {
		MARITAL_STAT = mARITAL_STAT;
	}

	public String getCHILDREN_STATE() {
		return CHILDREN_STATE;
	}

	public void setCHILDREN_STATE(String cHILDREN_STATE) {
		CHILDREN_STATE = cHILDREN_STATE;
	}

	public String getNAT_CODE() {
		return NAT_CODE;
	}

	public void setNAT_CODE(String nAT_CODE) {
		NAT_CODE = nAT_CODE;
	}

	public String getNationalAllName() {
		return NationalAllName;
	}

	public void setNationalAllName(String nationalAllName) {
		NationalAllName = nationalAllName;
	}

	public String getNATIVE_AREA() {
		return NATIVE_AREA;
	}

	public void setNATIVE_AREA(String nATIVE_AREA) {
		NATIVE_AREA = nATIVE_AREA;
	}

	public String getRGSTER_AREA() {
		return RGSTER_AREA;
	}

	public void setRGSTER_AREA(String rGSTER_AREA) {
		RGSTER_AREA = rGSTER_AREA;
	}

	public String getLANGUAGE_CODE() {
		return LANGUAGE_CODE;
	}

	public void setLANGUAGE_CODE(String lANGUAGE_CODE) {
		LANGUAGE_CODE = lANGUAGE_CODE;
	}

	public String getHOBB_INTRST() {
		return HOBB_INTRST;
	}

	public void setHOBB_INTRST(String hOBB_INTRST) {
		HOBB_INTRST = hOBB_INTRST;
	}

	public String getRELIG_CODE() {
		return RELIG_CODE;
	}

	public void setRELIG_CODE(String rELIG_CODE) {
		RELIG_CODE = rELIG_CODE;
	}

	public String getPOLIT_STAT() {
		return POLIT_STAT;
	}

	public void setPOLIT_STAT(String pOLIT_STAT) {
		POLIT_STAT = pOLIT_STAT;
	}

	public String getEDU_STATE() {
		return EDU_STATE;
	}

	public void setEDU_STATE(String eDU_STATE) {
		EDU_STATE = eDU_STATE;
	}

	public String getHIGHEST_DEGREE() {
		return HIGHEST_DEGREE;
	}

	public void setHIGHEST_DEGREE(String hIGHEST_DEGREE) {
		HIGHEST_DEGREE = hIGHEST_DEGREE;
	}

	public String getGRADUATION_SCHOLL() {
		return GRADUATION_SCHOLL;
	}

	public void setGRADUATION_SCHOLL(String gRADUATION_SCHOLL) {
		GRADUATION_SCHOLL = gRADUATION_SCHOLL;
	}

	public String getSPECIALTY() {
		return SPECIALTY;
	}

	public void setSPECIALTY(String sPECIALTY) {
		SPECIALTY = sPECIALTY;
	}

	public String getGRAD_DATE() {
		return GRAD_DATE;
	}

	public void setGRAD_DATE(String gRAD_DATE) {
		GRAD_DATE = gRAD_DATE;
	}

	public String getRSDT_TYPE() {
		return RSDT_TYPE;
	}

	public void setRSDT_TYPE(String rSDT_TYPE) {
		RSDT_TYPE = rSDT_TYPE;
	}

	public String getRSDT_ATTR() {
		return RSDT_ATTR;
	}

	public void setRSDT_ATTR(String rSDT_ATTR) {
		RSDT_ATTR = rSDT_ATTR;
	}

	public String getDOM_FORGN_CD() {
		return DOM_FORGN_CD;
	}

	public void setDOM_FORGN_CD(String dOM_FORGN_CD) {
		DOM_FORGN_CD = dOM_FORGN_CD;
	}

	public String getPMT_RSDT_FLAG() {
		return PMT_RSDT_FLAG;
	}

	public void setPMT_RSDT_FLAG(String pMT_RSDT_FLAG) {
		PMT_RSDT_FLAG = pMT_RSDT_FLAG;
	}

	public String getCOUNTRY_CODE() {
		return COUNTRY_CODE;
	}

	public void setCOUNTRY_CODE(String cOUNTRY_CODE) {
		COUNTRY_CODE = cOUNTRY_CODE;
	}

	public String getLIVE_AREA_CODE() {
		return LIVE_AREA_CODE;
	}

	public void setLIVE_AREA_CODE(String lIVE_AREA_CODE) {
		LIVE_AREA_CODE = lIVE_AREA_CODE;
	}

	public String getGenAreaCode() {
		return GenAreaCode;
	}

	public void setGenAreaCode(String genAreaCode) {
		GenAreaCode = genAreaCode;
	}

	public String getRESIDE_START_TIME() {
		return RESIDE_START_TIME;
	}

	public void setRESIDE_START_TIME(String rESIDE_START_TIME) {
		RESIDE_START_TIME = rESIDE_START_TIME;
	}

	public String getFARMER_FLAG() {
		return FARMER_FLAG;
	}

	public void setFARMER_FLAG(String fARMER_FLAG) {
		FARMER_FLAG = fARMER_FLAG;
	}

	public String getCUST_SUBJ_TYPE() {
		return CUST_SUBJ_TYPE;
	}

	public void setCUST_SUBJ_TYPE(String cUST_SUBJ_TYPE) {
		CUST_SUBJ_TYPE = cUST_SUBJ_TYPE;
	}

	public String getPRIVATE_CAR_FLAG() {
		return PRIVATE_CAR_FLAG;
	}

	public void setPRIVATE_CAR_FLAG(String pRIVATE_CAR_FLAG) {
		PRIVATE_CAR_FLAG = pRIVATE_CAR_FLAG;
	}

	public String getLIVG_CONDIT() {
		return LIVG_CONDIT;
	}

	public void setLIVG_CONDIT(String lIVG_CONDIT) {
		LIVG_CONDIT = lIVG_CONDIT;
	}

	public String getRESDT_TYPE() {
		return RESDT_TYPE;
	}

	public void setRESDT_TYPE(String rESDT_TYPE) {
		RESDT_TYPE = rESDT_TYPE;
	}

	public String getIDVU_SCL_INSURS_NO() {
		return IDVU_SCL_INSURS_NO;
	}

	public void setIDVU_SCL_INSURS_NO(String iDVU_SCL_INSURS_NO) {
		IDVU_SCL_INSURS_NO = iDVU_SCL_INSURS_NO;
	}

	public String getSOCIALSB_NO() {
		return SOCIALSB_NO;
	}

	public void setSOCIALSB_NO(String sOCIALSB_NO) {
		SOCIALSB_NO = sOCIALSB_NO;
	}

	public String getSOCIALSB_NAME() {
		return SOCIALSB_NAME;
	}

	public void setSOCIALSB_NAME(String sOCIALSB_NAME) {
		SOCIALSB_NAME = sOCIALSB_NAME;
	}

	public String getIDVU_TX_NO() {
		return IDVU_TX_NO;
	}

	public void setIDVU_TX_NO(String iDVU_TX_NO) {
		IDVU_TX_NO = iDVU_TX_NO;
	}

	public String getYNDuty() {
		return YNDuty;
	}

	public void setYNDuty(String yNDuty) {
		YNDuty = yNDuty;
	}

	public String getStaTax() {
		return StaTax;
	}

	public void setStaTax(String staTax) {
		StaTax = staTax;
	}

	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}

	public void setINTEREST_RATE(String iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}

	public String getTaxEffFlg() {
		return TaxEffFlg;
	}

	public void setTaxEffFlg(String taxEffFlg) {
		TaxEffFlg = taxEffFlg;
	}

	public String getMONTH_INCOME() {
		return MONTH_INCOME;
	}

	public void setMONTH_INCOME(String mONTH_INCOME) {
		MONTH_INCOME = mONTH_INCOME;
	}

	public String getYEAR_SALARY() {
		return YEAR_SALARY;
	}

	public void setYEAR_SALARY(String yEAR_SALARY) {
		YEAR_SALARY = yEAR_SALARY;
	}

	public String getECON_RESUR() {
		return ECON_RESUR;
	}

	public void setECON_RESUR(String eCON_RESUR) {
		ECON_RESUR = eCON_RESUR;
	}

	public String getPSN_ASSET_TYPE() {
		return PSN_ASSET_TYPE;
	}

	public void setPSN_ASSET_TYPE(String pSN_ASSET_TYPE) {
		PSN_ASSET_TYPE = pSN_ASSET_TYPE;
	}

	public String getNUM_DEPEND() {
		return NUM_DEPEND;
	}

	public void setNUM_DEPEND(String nUM_DEPEND) {
		NUM_DEPEND = nUM_DEPEND;
	}

	public String getFAM_MONTH() {
		return FAM_MONTH;
	}

	public void setFAM_MONTH(String fAM_MONTH) {
		FAM_MONTH = fAM_MONTH;
	}

	public String getFAM_YEAR() {
		return FAM_YEAR;
	}

	public void setFAM_YEAR(String fAM_YEAR) {
		FAM_YEAR = fAM_YEAR;
	}

	public String getFAM_ASSETS() {
		return FAM_ASSETS;
	}

	public void setFAM_ASSETS(String fAM_ASSETS) {
		FAM_ASSETS = fAM_ASSETS;
	}

	public String getFAM_MEMB_TOTAL() {
		return FAM_MEMB_TOTAL;
	}

	public void setFAM_MEMB_TOTAL(String fAM_MEMB_TOTAL) {
		FAM_MEMB_TOTAL = fAM_MEMB_TOTAL;
	}

	public String getUNIT_NAME() {
		return UNIT_NAME;
	}

	public void setUNIT_NAME(String uNIT_NAME) {
		UNIT_NAME = uNIT_NAME;
	}

	public String getUNIT_TYPE() {
		return UNIT_TYPE;
	}

	public void setUNIT_TYPE(String uNIT_TYPE) {
		UNIT_TYPE = uNIT_TYPE;
	}

	public String getINDUSTRY_TYPE() {
		return INDUSTRY_TYPE;
	}

	public void setINDUSTRY_TYPE(String iNDUSTRY_TYPE) {
		INDUSTRY_TYPE = iNDUSTRY_TYPE;
	}

	public String getPerfessional() {
		return Perfessional;
	}

	public void setPerfessional(String perfessional) {
		Perfessional = perfessional;
	}

	public String getJOB_LEVEL() {
		return JOB_LEVEL;
	}

	public void setJOB_LEVEL(String jOB_LEVEL) {
		JOB_LEVEL = jOB_LEVEL;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTitleOfTechnical() {
		return TitleOfTechnical;
	}

	public void setTitleOfTechnical(String titleOfTechnical) {
		TitleOfTechnical = titleOfTechnical;
	}

	public String getWORK_STAT() {
		return WORK_STAT;
	}

	public void setWORK_STAT(String wORK_STAT) {
		WORK_STAT = wORK_STAT;
	}

	public String getQUALFT_STAT() {
		return QUALFT_STAT;
	}

	public void setQUALFT_STAT(String qUALFT_STAT) {
		QUALFT_STAT = qUALFT_STAT;
	}

	public String getWORK_START_DATE() {
		return WORK_START_DATE;
	}

	public void setWORK_START_DATE(String wORK_START_DATE) {
		WORK_START_DATE = wORK_START_DATE;
	}

	public String getUNIT_START_YEAR() {
		return UNIT_START_YEAR;
	}

	public void setUNIT_START_YEAR(String uNIT_START_YEAR) {
		UNIT_START_YEAR = uNIT_START_YEAR;
	}

	public String getBANK_REL_CODE() {
		return BANK_REL_CODE;
	}

	public void setBANK_REL_CODE(String bANK_REL_CODE) {
		BANK_REL_CODE = bANK_REL_CODE;
	}

	public String getSHAREHOLDER_FLAG() {
		return SHAREHOLDER_FLAG;
	}

	public void setSHAREHOLDER_FLAG(String sHAREHOLDER_FLAG) {
		SHAREHOLDER_FLAG = sHAREHOLDER_FLAG;
	}

	public String getSTOCK_NUM() {
		return STOCK_NUM;
	}

	public void setSTOCK_NUM(String sTOCK_NUM) {
		STOCK_NUM = sTOCK_NUM;
	}

	public String getSTOCK_ACCT() {
		return STOCK_ACCT;
	}

	public void setSTOCK_ACCT(String sTOCK_ACCT) {
		STOCK_ACCT = sTOCK_ACCT;
	}

	public String getCUST_FORE_EXCH_ATTR() {
		return CUST_FORE_EXCH_ATTR;
	}

	public void setCUST_FORE_EXCH_ATTR(String cUST_FORE_EXCH_ATTR) {
		CUST_FORE_EXCH_ATTR = cUST_FORE_EXCH_ATTR;
	}

	public String getOutCredLvl() {
		return OutCredLvl;
	}

	public void setOutCredLvl(String outCredLvl) {
		OutCredLvl = outCredLvl;
	}

	public String getCustRiskScale() {
		return CustRiskScale;
	}

	public void setCustRiskScale(String custRiskScale) {
		CustRiskScale = custRiskScale;
	}

	public String getCREDIT_LEVEL() {
		return CREDIT_LEVEL;
	}

	public void setCREDIT_LEVEL(String cREDIT_LEVEL) {
		CREDIT_LEVEL = cREDIT_LEVEL;
	}

	public String getGRADE_DATE() {
		return GRADE_DATE;
	}

	public void setGRADE_DATE(String gRADE_DATE) {
		GRADE_DATE = gRADE_DATE;
	}

	public String getGRADE_DUE_DATE() {
		return GRADE_DUE_DATE;
	}

	public void setGRADE_DUE_DATE(String gRADE_DUE_DATE) {
		GRADE_DUE_DATE = gRADE_DUE_DATE;
	}

	public String getCustComEvalLevel() {
		return CustComEvalLevel;
	}

	public void setCustComEvalLevel(String custComEvalLevel) {
		CustComEvalLevel = custComEvalLevel;
	}

	public String getCUST_LEVEL() {
		return CUST_LEVEL;
	}

	public void setCUST_LEVEL(String cUST_LEVEL) {
		CUST_LEVEL = cUST_LEVEL;
	}

	public String getIdChkFlg() {
		return IdChkFlg;
	}

	public void setIdChkFlg(String idChkFlg) {
		IdChkFlg = idChkFlg;
	}

	public String getIDE_CHECK_RESULT() {
		return IDE_CHECK_RESULT;
	}

	public void setIDE_CHECK_RESULT(String iDE_CHECK_RESULT) {
		IDE_CHECK_RESULT = iDE_CHECK_RESULT;
	}

	public String getIDE_FALSE_REASON() {
		return IDE_FALSE_REASON;
	}

	public void setIDE_FALSE_REASON(String iDE_FALSE_REASON) {
		IDE_FALSE_REASON = iDE_FALSE_REASON;
	}

	public String getCUST_MANAGER_NO() {
		return CUST_MANAGER_NO;
	}

	public void setCUST_MANAGER_NO(String cUST_MANAGER_NO) {
		CUST_MANAGER_NO = cUST_MANAGER_NO;
	}

	public String getCustMngrName() {
		return CustMngrName;
	}

	public void setCustMngrName(String custMngrName) {
		CustMngrName = custMngrName;
	}


}
