/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *	对私客户基本信息查询响应报文实体类		
 */
public class ResponseA102 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5974122997180513348L;
	private String ECIF_CUST_NO;//客户编号
	private String PARTY_NAME;//客户名称
	private String CERT_TYPE;//证件类型
	private String CERT_NO;//证件号码
	private String CERT_ORG_NAT;//发证机关国家
	private String CERT_ORG_AREA;//发证机关所在地区
	private String CERT_ORG_NAME;//发证机关名称
	private String CERT_ISSUE_DATE;//证件签发日期
	private String CERT_DUE_DATE;//证件到期日期
	private String CERT_EDIT_NO;//证件版本号
	private String CERT_ORG_NO;//当次申请受理机关代码
	private String CERT_TYPE_FLAG;//证件类型标识
	private String CERT_IMG_ADDR;//证件影像地址
	private String CERT_DESC;//证件其它说明
	private String CUST_ENNAME;//英文名称
	private String CUST_SPNAME;//拼音名称
	private String CUST_BNAME;//客户曾用名
	private String CUST_CALL;//客户称谓
	private String GENDER_CODE;//性别
	private String PEOPLE_CODE;//民族
	private String BIRTH_DATE;//出生日期
	private String BIRTH_PLACE;//出生地点
	private String HEALTH_STATE;//健康状况
	private String MARITAL_STAT;//婚姻状态
	private String CHILDREN_STATE;//子女状况
	private String NAT_CODE;//国籍
	private String NAT_FULL;//国籍全称
	private String NATIVE_AREA;//籍贯
	private String RGSTER_AREA;//户籍所在地
	private String LANGUAGE_CODE;//语言习惯
	private String HOBB_INTRST;//兴趣爱好
	private String RELIG_CODE;//宗教信仰
	private String POLIT_STAT;//政治面貌
	private String EDU_STATE;//教育程度
	private String HIGHEST_DEGREE;//最高学位
	private String GRADUATION_SCHOLL;//毕业院校
	private String SPECIALTY;//所学专业
	private String GRAD_DATE;//毕业时间
	private String RSDT_TYPE;//居民性质
	private String RSDT_ATTR;//居民属性
	private String DOM_FORGN_CD;//境内外标识
	private String PMT_RSDT_FLAG;//是否永久居住
	private String COUNTRY_CODE;//居住国家代码
	private String LIVE_AREA_CODE;//居住地行政区划代码
	private String AREA_CODE;//所属地区代码
	private String RESIDE_START_TIME;//本地居住开始时间
	private String FARMER_FLAG;//是否农户
	private String CUST_SUBJ_TYPE;//客户主体类型
	private String PRIVATE_CAR_FLAG;//是否有私家车
	private String LIVG_CONDIT;//居住状况
	private String RESDT_TYPE;//住宅类型
	private String IDVU_SCL_INSURS_NO;//个人社会保险卡号
	private String SOCIALSB_NO;//社保局编号
	private String SOCIALSB_NAME;//社保局名称
	private String IDVU_TX_NO;//个人纳税号
	private String DUTY_FREE_FLAG;//是否免税
	private String TAX_RATE_COUNTRY;//税率国别
	private String INTEREST_RATE;//利息税率
	private String TAX_RATE_DATE;//税率有效日期
	private String MONTH_INCOME;//个人月收入
	private String YEAR_SALARY;//个人年收入
	private String ECON_RESUR;//个人主要经济来源
	private String PSN_ASSET_TYPE;//个人资产规模
	private String NUM_DEPEND;//抚养人数
	private String FAM_MONTH;//家庭月收入
	private String FAM_YEAR;//家庭年收入
	private String FAM_ASSETS;//家庭总资产
	private String FAM_MEMB_TOTAL;//家庭成员总数
	private String UNIT_NAME;//所在单位名称
	private String UNIT_TYPE;//单位性质
	private String INDUSTRY_TYPE;//从事行业类型
	private String PROFESSION_CODE;//职业
	private String JOB_LEVEL;//职级
	private String UNIT_POSITION;//职务
	private String TECH_TITLE;//职称
	private String WORK_STAT;//从业状况代码
	private String QUALFT_STAT;//执业资格状况
	private String WORK_START_DATE;//参加工作时间
	private String UNIT_START_YEAR;//本单位工作起始年份
	private String BANK_REL_CODE;//与我行关系
	private String SHAREHOLDER_FLAG;//是否本行股东
	private String STOCK_NUM;//持股数
	private String STOCK_ACCT;//股权账号
	private String CUST_FORE_EXCH_ATTR;//客户外汇属性维护标志
	private String EXT_CREDIT_LEVEL;//外部信用等级
	private String CUAT_RISK_LEVEL;//客户风险级别
	private String CREDIT_LEVEL;//信用评级
	private String GRADE_DATE;//评级日期
	private String GRADE_DUE_DATE;//评级有效日期
	private String CUST_EVAL_LEVEL;//客户综合评估级别
	private String CUST_LEVEL;//客户等级
	private String IDE_CHECK_FLAG;//身份核查标志
	private String IDE_CHECK_RESULT;//公民身份联网核查结果
	private String IDE_FALSE_REASON;//公民身份无法核实原因
	private String CUST_MANAGER_NO;//客户经理代码
	private String CUST_MNG_NAME;//客户经理名称
	private String OWN_ORG;//所属机构
	private String OPEN_ORG;//开户机构
	private String OPEN_TELLER;//开户柜员
	private String OPEN_DATE;//开户日期
	private String REAL_FULL_FLAG;//实名制完整性标志
	private String BAT_CUST_FLAG;//批量客户标志
	private String CUST_STATUS;//客户有效状态
	private String LAST_SYSTEM_ID;//最新更新渠道
	private String LAST_UPDATED_TS;//最新更新时间
	
	/**
	 * 
	 */
	public ResponseA102() {
		// TODO 自动生成的构造函数存根
	}
	
	public String getECIF_CUST_NO() {
		return ECIF_CUST_NO;
	}
	public void setECIF_CUST_NO(String eCIF_CUST_NO) {
		ECIF_CUST_NO = eCIF_CUST_NO;
	}
	public String getPARTY_NAME() {
		return PARTY_NAME;
	}
	public void setPARTY_NAME(String pARTY_NAME) {
		PARTY_NAME = pARTY_NAME;
	}
	public String getCERT_TYPE() {
		return CERT_TYPE;
	}
	public void setCERT_TYPE(String cERT_TYPE) {
		CERT_TYPE = cERT_TYPE;
	}
	public String getCERT_NO() {
		return CERT_NO;
	}
	public void setCERT_NO(String cERT_NO) {
		CERT_NO = cERT_NO;
	}
	public String getCERT_ORG_NAT() {
		return CERT_ORG_NAT;
	}
	public void setCERT_ORG_NAT(String cERT_ORG_NAT) {
		CERT_ORG_NAT = cERT_ORG_NAT;
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
	public String getCERT_IMG_ADDR() {
		return CERT_IMG_ADDR;
	}
	public void setCERT_IMG_ADDR(String cERT_IMG_ADDR) {
		CERT_IMG_ADDR = cERT_IMG_ADDR;
	}
	public String getCERT_DESC() {
		return CERT_DESC;
	}
	public void setCERT_DESC(String cERT_DESC) {
		CERT_DESC = cERT_DESC;
	}
	public String getCUST_ENNAME() {
		return CUST_ENNAME;
	}
	public void setCUST_ENNAME(String cUST_ENNAME) {
		CUST_ENNAME = cUST_ENNAME;
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
	public String getGENDER_CODE() {
		return GENDER_CODE;
	}
	public void setGENDER_CODE(String gENDER_CODE) {
		GENDER_CODE = gENDER_CODE;
	}
	public String getPEOPLE_CODE() {
		return PEOPLE_CODE;
	}
	public void setPEOPLE_CODE(String pEOPLE_CODE) {
		PEOPLE_CODE = pEOPLE_CODE;
	}
	public String getBIRTH_DATE() {
		return BIRTH_DATE;
	}
	public void setBIRTH_DATE(String bIRTH_DATE) {
		BIRTH_DATE = bIRTH_DATE;
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
	public String getNAT_FULL() {
		return NAT_FULL;
	}
	public void setNAT_FULL(String nAT_FULL) {
		NAT_FULL = nAT_FULL;
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
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
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
	public String getDUTY_FREE_FLAG() {
		return DUTY_FREE_FLAG;
	}
	public void setDUTY_FREE_FLAG(String dUTY_FREE_FLAG) {
		DUTY_FREE_FLAG = dUTY_FREE_FLAG;
	}
	public String getTAX_RATE_COUNTRY() {
		return TAX_RATE_COUNTRY;
	}
	public void setTAX_RATE_COUNTRY(String tAX_RATE_COUNTRY) {
		TAX_RATE_COUNTRY = tAX_RATE_COUNTRY;
	}
	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}
	public void setINTEREST_RATE(String iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
	public String getTAX_RATE_DATE() {
		return TAX_RATE_DATE;
	}
	public void setTAX_RATE_DATE(String tAX_RATE_DATE) {
		TAX_RATE_DATE = tAX_RATE_DATE;
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
	public String getPROFESSION_CODE() {
		return PROFESSION_CODE;
	}
	public void setPROFESSION_CODE(String pROFESSION_CODE) {
		PROFESSION_CODE = pROFESSION_CODE;
	}
	public String getJOB_LEVEL() {
		return JOB_LEVEL;
	}
	public void setJOB_LEVEL(String jOB_LEVEL) {
		JOB_LEVEL = jOB_LEVEL;
	}
	public String getUNIT_POSITION() {
		return UNIT_POSITION;
	}
	public void setUNIT_POSITION(String uNIT_POSITION) {
		UNIT_POSITION = uNIT_POSITION;
	}
	public String getTECH_TITLE() {
		return TECH_TITLE;
	}
	public void setTECH_TITLE(String tECH_TITLE) {
		TECH_TITLE = tECH_TITLE;
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
	public String getEXT_CREDIT_LEVEL() {
		return EXT_CREDIT_LEVEL;
	}
	public void setEXT_CREDIT_LEVEL(String eXT_CREDIT_LEVEL) {
		EXT_CREDIT_LEVEL = eXT_CREDIT_LEVEL;
	}
	public String getCUAT_RISK_LEVEL() {
		return CUAT_RISK_LEVEL;
	}
	public void setCUAT_RISK_LEVEL(String cUAT_RISK_LEVEL) {
		CUAT_RISK_LEVEL = cUAT_RISK_LEVEL;
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
	public String getCUST_EVAL_LEVEL() {
		return CUST_EVAL_LEVEL;
	}
	public void setCUST_EVAL_LEVEL(String cUST_EVAL_LEVEL) {
		CUST_EVAL_LEVEL = cUST_EVAL_LEVEL;
	}
	public String getCUST_LEVEL() {
		return CUST_LEVEL;
	}
	public void setCUST_LEVEL(String cUST_LEVEL) {
		CUST_LEVEL = cUST_LEVEL;
	}
	public String getIDE_CHECK_FLAG() {
		return IDE_CHECK_FLAG;
	}
	public void setIDE_CHECK_FLAG(String iDE_CHECK_FLAG) {
		IDE_CHECK_FLAG = iDE_CHECK_FLAG;
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
	public String getCUST_MNG_NAME() {
		return CUST_MNG_NAME;
	}
	public void setCUST_MNG_NAME(String cUST_MNG_NAME) {
		CUST_MNG_NAME = cUST_MNG_NAME;
	}
	public String getOWN_ORG() {
		return OWN_ORG;
	}
	public void setOWN_ORG(String oWN_ORG) {
		OWN_ORG = oWN_ORG;
	}
	public String getOPEN_ORG() {
		return OPEN_ORG;
	}
	public void setOPEN_ORG(String oPEN_ORG) {
		OPEN_ORG = oPEN_ORG;
	}
	public String getOPEN_TELLER() {
		return OPEN_TELLER;
	}
	public void setOPEN_TELLER(String oPEN_TELLER) {
		OPEN_TELLER = oPEN_TELLER;
	}
	public String getOPEN_DATE() {
		return OPEN_DATE;
	}
	public void setOPEN_DATE(String oPEN_DATE) {
		OPEN_DATE = oPEN_DATE;
	}
	public String getREAL_FULL_FLAG() {
		return REAL_FULL_FLAG;
	}
	public void setREAL_FULL_FLAG(String rEAL_FULL_FLAG) {
		REAL_FULL_FLAG = rEAL_FULL_FLAG;
	}
	public String getBAT_CUST_FLAG() {
		return BAT_CUST_FLAG;
	}
	public void setBAT_CUST_FLAG(String bAT_CUST_FLAG) {
		BAT_CUST_FLAG = bAT_CUST_FLAG;
	}
	public String getCUST_STATUS() {
		return CUST_STATUS;
	}
	public void setCUST_STATUS(String cUST_STATUS) {
		CUST_STATUS = cUST_STATUS;
	}
	public String getLAST_SYSTEM_ID() {
		return LAST_SYSTEM_ID;
	}
	public void setLAST_SYSTEM_ID(String lAST_SYSTEM_ID) {
		LAST_SYSTEM_ID = lAST_SYSTEM_ID;
	}
	public String getLAST_UPDATED_TS() {
		return LAST_UPDATED_TS;
	}
	public void setLAST_UPDATED_TS(String lAST_UPDATED_TS) {
		LAST_UPDATED_TS = lAST_UPDATED_TS;
	}
	
	
}
