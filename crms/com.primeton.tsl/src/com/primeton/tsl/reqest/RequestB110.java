/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户关系个人信息创建与维护请求报文				

 */
public class RequestB110 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7287867629796717644L;
	private String RESOLVE_TYPE;//客户识别方式  必填 客户识别方式：1、使用客户编号识别；
	private String ECIF_CUST_NO;//客户编号  必填
	private String PAR_SEQ_ID;//关联关系记录编号
	private String RELATION_TYPE;//关联关系类型 必填
	private String RELATION_ID;//关系人ID
	private String REL_NAME;//关系人名称 必填
	private String REL_CERT_TYPE;//关系人证件类型
	private String REL_CERT_NO;//关系人证件号码
	private String OTHER_DESC;//其它说明
	private String CERT_ORG_NAT;//发证机关国家
	private String CERT_ORG_AREA;//发证机关所在地
	private String CERT_ORG_NAME;//发证机关名称
	private String CERT_ISSUE_DATE;//证件签发日期
	private String CERT_DUE_DATE;//证件到期日期
	private String NAT_CODE;//国籍
	private String GENDER_CODE;//性别
	private String BIRTH_DATE;//出生日期
	private String MARITAL_STAT;//婚姻状态
	private String RSDT_ATTR;//居民性质
	private String EDU_STATE;//教育程度
	private String ECON_RESUR;//主要经济来源
	private String WORK_CORP;//工作单位
	private String WORK_ADDR;//单位地址
	private String UNIT_PHONE;//单位电话
	private String OFFICE_FAX;//传真号码
	private String UNIT_TYPE;//单位分类
	private String INDUSTRY_TYPE;//从事行业类型
	private String PROFESSION_CODE;//职业
	private String UNIT_POSITION;//职务
	private String TECH_TITLE;//职称
	private String CITY_CODE;//所在城市
	private String AREA_CODE;//所属地区代码
	private String YEAR_SALARY;//个人年收入
	private String FAM_SALARY;//家庭年收入
	private String FAM_ADDR;//家庭地址
	private String FAM_POST;//家庭邮编
	private String FIND_ADDR;//联系地址
	private String FINDTEL_NO;//联系电话
	private String MOBILE_NO;//手机号码
	private String HOME_ADDR;//住宅地址
	private String HOME_POST;//住宅邮编
	private String HOMETEL_NO;//住宅电话
	private String EMAIL_ADDR;//电子邮件
	private String COMMUNICA_ADD;//通讯地址
	private String COMMUNICA_POST;//通讯邮编
	private String CENSUS_ADDR;//户籍地址
	private String URL_ADDR;//个人网址
	private String OICQ_NO;//QQ号码
	private String MSG_ADDR;//微信号码
	private String FANCY_DESC;//个人爱好
	private String REMARK;//备注
	
	/**
	 * 
	 */
	public RequestB110() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @return rESOLVE_TYPE
	 */
	public String getRESOLVE_TYPE() {
		return RESOLVE_TYPE;
	}

	/**
	 * @param rESOLVE_TYPE 要设置的 rESOLVE_TYPE
	 */
	public void setRESOLVE_TYPE(String rESOLVE_TYPE) {
		RESOLVE_TYPE = rESOLVE_TYPE;
	}

	/**
	 * @return eCIF_CUST_NO
	 */
	public String getECIF_CUST_NO() {
		return ECIF_CUST_NO;
	}

	/**
	 * @param eCIF_CUST_NO 要设置的 eCIF_CUST_NO
	 */
	public void setECIF_CUST_NO(String eCIF_CUST_NO) {
		ECIF_CUST_NO = eCIF_CUST_NO;
	}

	/**
	 * @return pAR_SEQ_ID
	 */
	public String getPAR_SEQ_ID() {
		return PAR_SEQ_ID;
	}

	/**
	 * @param pAR_SEQ_ID 要设置的 pAR_SEQ_ID
	 */
	public void setPAR_SEQ_ID(String pAR_SEQ_ID) {
		PAR_SEQ_ID = pAR_SEQ_ID;
	}

	/**
	 * @return rELATION_TYPE
	 */
	public String getRELATION_TYPE() {
		return RELATION_TYPE;
	}

	/**
	 * @param rELATION_TYPE 要设置的 rELATION_TYPE
	 */
	public void setRELATION_TYPE(String rELATION_TYPE) {
		RELATION_TYPE = rELATION_TYPE;
	}

	/**
	 * @return rELATION_ID
	 */
	public String getRELATION_ID() {
		return RELATION_ID;
	}

	/**
	 * @param rELATION_ID 要设置的 rELATION_ID
	 */
	public void setRELATION_ID(String rELATION_ID) {
		RELATION_ID = rELATION_ID;
	}

	/**
	 * @return rEL_NAME
	 */
	public String getREL_NAME() {
		return REL_NAME;
	}

	/**
	 * @param rEL_NAME 要设置的 rEL_NAME
	 */
	public void setREL_NAME(String rEL_NAME) {
		REL_NAME = rEL_NAME;
	}

	/**
	 * @return rEL_CERT_TYPE
	 */
	public String getREL_CERT_TYPE() {
		return REL_CERT_TYPE;
	}

	/**
	 * @param rEL_CERT_TYPE 要设置的 rEL_CERT_TYPE
	 */
	public void setREL_CERT_TYPE(String rEL_CERT_TYPE) {
		REL_CERT_TYPE = rEL_CERT_TYPE;
	}

	/**
	 * @return rEL_CERT_NO
	 */
	public String getREL_CERT_NO() {
		return REL_CERT_NO;
	}

	/**
	 * @param rEL_CERT_NO 要设置的 rEL_CERT_NO
	 */
	public void setREL_CERT_NO(String rEL_CERT_NO) {
		REL_CERT_NO = rEL_CERT_NO;
	}

	/**
	 * @return oTHER_DESC
	 */
	public String getOTHER_DESC() {
		return OTHER_DESC;
	}

	/**
	 * @param oTHER_DESC 要设置的 oTHER_DESC
	 */
	public void setOTHER_DESC(String oTHER_DESC) {
		OTHER_DESC = oTHER_DESC;
	}

	/**
	 * @return cERT_ORG_NAT
	 */
	public String getCERT_ORG_NAT() {
		return CERT_ORG_NAT;
	}

	/**
	 * @param cERT_ORG_NAT 要设置的 cERT_ORG_NAT
	 */
	public void setCERT_ORG_NAT(String cERT_ORG_NAT) {
		CERT_ORG_NAT = cERT_ORG_NAT;
	}

	/**
	 * @return cERT_ORG_AREA
	 */
	public String getCERT_ORG_AREA() {
		return CERT_ORG_AREA;
	}

	/**
	 * @param cERT_ORG_AREA 要设置的 cERT_ORG_AREA
	 */
	public void setCERT_ORG_AREA(String cERT_ORG_AREA) {
		CERT_ORG_AREA = cERT_ORG_AREA;
	}

	/**
	 * @return cERT_ORG_NAME
	 */
	public String getCERT_ORG_NAME() {
		return CERT_ORG_NAME;
	}

	/**
	 * @param cERT_ORG_NAME 要设置的 cERT_ORG_NAME
	 */
	public void setCERT_ORG_NAME(String cERT_ORG_NAME) {
		CERT_ORG_NAME = cERT_ORG_NAME;
	}

	/**
	 * @return cERT_ISSUE_DATE
	 */
	public String getCERT_ISSUE_DATE() {
		return CERT_ISSUE_DATE;
	}

	/**
	 * @param cERT_ISSUE_DATE 要设置的 cERT_ISSUE_DATE
	 */
	public void setCERT_ISSUE_DATE(String cERT_ISSUE_DATE) {
		CERT_ISSUE_DATE = cERT_ISSUE_DATE;
	}

	/**
	 * @return cERT_DUE_DATE
	 */
	public String getCERT_DUE_DATE() {
		return CERT_DUE_DATE;
	}

	/**
	 * @param cERT_DUE_DATE 要设置的 cERT_DUE_DATE
	 */
	public void setCERT_DUE_DATE(String cERT_DUE_DATE) {
		CERT_DUE_DATE = cERT_DUE_DATE;
	}

	/**
	 * @return nAT_CODE
	 */
	public String getNAT_CODE() {
		return NAT_CODE;
	}

	/**
	 * @param nAT_CODE 要设置的 nAT_CODE
	 */
	public void setNAT_CODE(String nAT_CODE) {
		NAT_CODE = nAT_CODE;
	}

	/**
	 * @return gENDER_CODE
	 */
	public String getGENDER_CODE() {
		return GENDER_CODE;
	}

	/**
	 * @param gENDER_CODE 要设置的 gENDER_CODE
	 */
	public void setGENDER_CODE(String gENDER_CODE) {
		GENDER_CODE = gENDER_CODE;
	}

	/**
	 * @return bIRTH_DATE
	 */
	public String getBIRTH_DATE() {
		return BIRTH_DATE;
	}

	/**
	 * @param bIRTH_DATE 要设置的 bIRTH_DATE
	 */
	public void setBIRTH_DATE(String bIRTH_DATE) {
		BIRTH_DATE = bIRTH_DATE;
	}

	/**
	 * @return mARITAL_STAT
	 */
	public String getMARITAL_STAT() {
		return MARITAL_STAT;
	}

	/**
	 * @param mARITAL_STAT 要设置的 mARITAL_STAT
	 */
	public void setMARITAL_STAT(String mARITAL_STAT) {
		MARITAL_STAT = mARITAL_STAT;
	}

	/**
	 * @return rSDT_ATTR
	 */
	public String getRSDT_ATTR() {
		return RSDT_ATTR;
	}

	/**
	 * @param rSDT_ATTR 要设置的 rSDT_ATTR
	 */
	public void setRSDT_ATTR(String rSDT_ATTR) {
		RSDT_ATTR = rSDT_ATTR;
	}

	/**
	 * @return eDU_STATE
	 */
	public String getEDU_STATE() {
		return EDU_STATE;
	}

	/**
	 * @param eDU_STATE 要设置的 eDU_STATE
	 */
	public void setEDU_STATE(String eDU_STATE) {
		EDU_STATE = eDU_STATE;
	}

	/**
	 * @return eCON_RESUR
	 */
	public String getECON_RESUR() {
		return ECON_RESUR;
	}

	/**
	 * @param eCON_RESUR 要设置的 eCON_RESUR
	 */
	public void setECON_RESUR(String eCON_RESUR) {
		ECON_RESUR = eCON_RESUR;
	}

	/**
	 * @return wORK_CORP
	 */
	public String getWORK_CORP() {
		return WORK_CORP;
	}

	/**
	 * @param wORK_CORP 要设置的 wORK_CORP
	 */
	public void setWORK_CORP(String wORK_CORP) {
		WORK_CORP = wORK_CORP;
	}

	/**
	 * @return wORK_ADDR
	 */
	public String getWORK_ADDR() {
		return WORK_ADDR;
	}

	/**
	 * @param wORK_ADDR 要设置的 wORK_ADDR
	 */
	public void setWORK_ADDR(String wORK_ADDR) {
		WORK_ADDR = wORK_ADDR;
	}

	/**
	 * @return uNIT_PHONE
	 */
	public String getUNIT_PHONE() {
		return UNIT_PHONE;
	}

	/**
	 * @param uNIT_PHONE 要设置的 uNIT_PHONE
	 */
	public void setUNIT_PHONE(String uNIT_PHONE) {
		UNIT_PHONE = uNIT_PHONE;
	}

	/**
	 * @return oFFICE_FAX
	 */
	public String getOFFICE_FAX() {
		return OFFICE_FAX;
	}

	/**
	 * @param oFFICE_FAX 要设置的 oFFICE_FAX
	 */
	public void setOFFICE_FAX(String oFFICE_FAX) {
		OFFICE_FAX = oFFICE_FAX;
	}

	/**
	 * @return uNIT_TYPE
	 */
	public String getUNIT_TYPE() {
		return UNIT_TYPE;
	}

	/**
	 * @param uNIT_TYPE 要设置的 uNIT_TYPE
	 */
	public void setUNIT_TYPE(String uNIT_TYPE) {
		UNIT_TYPE = uNIT_TYPE;
	}

	/**
	 * @return iNDUSTRY_TYPE
	 */
	public String getINDUSTRY_TYPE() {
		return INDUSTRY_TYPE;
	}

	/**
	 * @param iNDUSTRY_TYPE 要设置的 iNDUSTRY_TYPE
	 */
	public void setINDUSTRY_TYPE(String iNDUSTRY_TYPE) {
		INDUSTRY_TYPE = iNDUSTRY_TYPE;
	}

	/**
	 * @return pROFESSION_CODE
	 */
	public String getPROFESSION_CODE() {
		return PROFESSION_CODE;
	}

	/**
	 * @param pROFESSION_CODE 要设置的 pROFESSION_CODE
	 */
	public void setPROFESSION_CODE(String pROFESSION_CODE) {
		PROFESSION_CODE = pROFESSION_CODE;
	}

	/**
	 * @return uNIT_POSITION
	 */
	public String getUNIT_POSITION() {
		return UNIT_POSITION;
	}

	/**
	 * @param uNIT_POSITION 要设置的 uNIT_POSITION
	 */
	public void setUNIT_POSITION(String uNIT_POSITION) {
		UNIT_POSITION = uNIT_POSITION;
	}

	/**
	 * @return tECH_TITLE
	 */
	public String getTECH_TITLE() {
		return TECH_TITLE;
	}

	/**
	 * @param tECH_TITLE 要设置的 tECH_TITLE
	 */
	public void setTECH_TITLE(String tECH_TITLE) {
		TECH_TITLE = tECH_TITLE;
	}

	/**
	 * @return cITY_CODE
	 */
	public String getCITY_CODE() {
		return CITY_CODE;
	}

	/**
	 * @param cITY_CODE 要设置的 cITY_CODE
	 */
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}

	/**
	 * @return aREA_CODE
	 */
	public String getAREA_CODE() {
		return AREA_CODE;
	}

	/**
	 * @param aREA_CODE 要设置的 aREA_CODE
	 */
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}

	/**
	 * @return yEAR_SALARY
	 */
	public String getYEAR_SALARY() {
		return YEAR_SALARY;
	}

	/**
	 * @param yEAR_SALARY 要设置的 yEAR_SALARY
	 */
	public void setYEAR_SALARY(String yEAR_SALARY) {
		YEAR_SALARY = yEAR_SALARY;
	}

	/**
	 * @return fAM_SALARY
	 */
	public String getFAM_SALARY() {
		return FAM_SALARY;
	}

	/**
	 * @param fAM_SALARY 要设置的 fAM_SALARY
	 */
	public void setFAM_SALARY(String fAM_SALARY) {
		FAM_SALARY = fAM_SALARY;
	}

	/**
	 * @return fAM_ADDR
	 */
	public String getFAM_ADDR() {
		return FAM_ADDR;
	}

	/**
	 * @param fAM_ADDR 要设置的 fAM_ADDR
	 */
	public void setFAM_ADDR(String fAM_ADDR) {
		FAM_ADDR = fAM_ADDR;
	}

	/**
	 * @return fAM_POST
	 */
	public String getFAM_POST() {
		return FAM_POST;
	}

	/**
	 * @param fAM_POST 要设置的 fAM_POST
	 */
	public void setFAM_POST(String fAM_POST) {
		FAM_POST = fAM_POST;
	}

	/**
	 * @return fIND_ADDR
	 */
	public String getFIND_ADDR() {
		return FIND_ADDR;
	}

	/**
	 * @param fIND_ADDR 要设置的 fIND_ADDR
	 */
	public void setFIND_ADDR(String fIND_ADDR) {
		FIND_ADDR = fIND_ADDR;
	}

	/**
	 * @return fINDTEL_NO
	 */
	public String getFINDTEL_NO() {
		return FINDTEL_NO;
	}

	/**
	 * @param fINDTEL_NO 要设置的 fINDTEL_NO
	 */
	public void setFINDTEL_NO(String fINDTEL_NO) {
		FINDTEL_NO = fINDTEL_NO;
	}

	/**
	 * @return mOBILE_NO
	 */
	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	/**
	 * @param mOBILE_NO 要设置的 mOBILE_NO
	 */
	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}

	/**
	 * @return hOME_ADDR
	 */
	public String getHOME_ADDR() {
		return HOME_ADDR;
	}

	/**
	 * @param hOME_ADDR 要设置的 hOME_ADDR
	 */
	public void setHOME_ADDR(String hOME_ADDR) {
		HOME_ADDR = hOME_ADDR;
	}

	/**
	 * @return hOME_POST
	 */
	public String getHOME_POST() {
		return HOME_POST;
	}

	/**
	 * @param hOME_POST 要设置的 hOME_POST
	 */
	public void setHOME_POST(String hOME_POST) {
		HOME_POST = hOME_POST;
	}

	/**
	 * @return hOMETEL_NO
	 */
	public String getHOMETEL_NO() {
		return HOMETEL_NO;
	}

	/**
	 * @param hOMETEL_NO 要设置的 hOMETEL_NO
	 */
	public void setHOMETEL_NO(String hOMETEL_NO) {
		HOMETEL_NO = hOMETEL_NO;
	}

	/**
	 * @return eMAIL_ADDR
	 */
	public String getEMAIL_ADDR() {
		return EMAIL_ADDR;
	}

	/**
	 * @param eMAIL_ADDR 要设置的 eMAIL_ADDR
	 */
	public void setEMAIL_ADDR(String eMAIL_ADDR) {
		EMAIL_ADDR = eMAIL_ADDR;
	}

	/**
	 * @return cOMMUNICA_ADD
	 */
	public String getCOMMUNICA_ADD() {
		return COMMUNICA_ADD;
	}

	/**
	 * @param cOMMUNICA_ADD 要设置的 cOMMUNICA_ADD
	 */
	public void setCOMMUNICA_ADD(String cOMMUNICA_ADD) {
		COMMUNICA_ADD = cOMMUNICA_ADD;
	}

	/**
	 * @return cOMMUNICA_POST
	 */
	public String getCOMMUNICA_POST() {
		return COMMUNICA_POST;
	}

	/**
	 * @param cOMMUNICA_POST 要设置的 cOMMUNICA_POST
	 */
	public void setCOMMUNICA_POST(String cOMMUNICA_POST) {
		COMMUNICA_POST = cOMMUNICA_POST;
	}

	/**
	 * @return cENSUS_ADDR
	 */
	public String getCENSUS_ADDR() {
		return CENSUS_ADDR;
	}

	/**
	 * @param cENSUS_ADDR 要设置的 cENSUS_ADDR
	 */
	public void setCENSUS_ADDR(String cENSUS_ADDR) {
		CENSUS_ADDR = cENSUS_ADDR;
	}

	/**
	 * @return uRL_ADDR
	 */
	public String getURL_ADDR() {
		return URL_ADDR;
	}

	/**
	 * @param uRL_ADDR 要设置的 uRL_ADDR
	 */
	public void setURL_ADDR(String uRL_ADDR) {
		URL_ADDR = uRL_ADDR;
	}

	/**
	 * @return oICQ_NO
	 */
	public String getOICQ_NO() {
		return OICQ_NO;
	}

	/**
	 * @param oICQ_NO 要设置的 oICQ_NO
	 */
	public void setOICQ_NO(String oICQ_NO) {
		OICQ_NO = oICQ_NO;
	}

	/**
	 * @return mSG_ADDR
	 */
	public String getMSG_ADDR() {
		return MSG_ADDR;
	}

	/**
	 * @param mSG_ADDR 要设置的 mSG_ADDR
	 */
	public void setMSG_ADDR(String mSG_ADDR) {
		MSG_ADDR = mSG_ADDR;
	}

	/**
	 * @return fANCY_DESC
	 */
	public String getFANCY_DESC() {
		return FANCY_DESC;
	}

	/**
	 * @param fANCY_DESC 要设置的 fANCY_DESC
	 */
	public void setFANCY_DESC(String fANCY_DESC) {
		FANCY_DESC = fANCY_DESC;
	}

	/**
	 * @return rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * @param rEMARK 要设置的 rEMARK
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
}
