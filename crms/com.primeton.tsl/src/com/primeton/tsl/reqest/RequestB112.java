/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户关系企业信息创建与维护请求报文				

 */
public class RequestB112 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114918516654268803L;
	private String RESOLVE_TYPE;//客户识别方式
	private String ECIF_CUST_NO;//客户编号
	private String PAR_SEQ_ID;//关联关系记录编号
	private String RELATION_TYPE;//关联关系类型
	private String RELATION_ID;//关系人ID
	private String REL_NAME;//关系人名称
	private String REL_CERT_TYPE;//关系人证件类型
	private String REL_CERT_NO;//关系人证件号码
	private String OTHER_DESC;//其它说明
	private String CERT_ORG_NAT;//发证机关国家
	private String CERT_ORG_AREA;//发证机关所在地
	private String CERT_ORG_NAME;//发证机关名称
	private String CERT_ISSUE_DATE;//证件签发日期
	private String CERT_DUE_DATE;//证件到期日期
	private String GOVN_CERT_NO;//营业执照号码
	private String GOVN_EFFT_DATE;//营业执照生效日期
	private String GOVN_EXPD_DATE;//营业执照有效日期
	private String BASIC_ACC_LIC_NO;//基本户开户许可证编号
	private String BASIC_ACC_PERMIT_NO;//基本户开户许可证核准号
	private String LOAN_CARD_NO;//贷款卡号
	private String ORG_CODE;//组织机构代码
	private String ORG_CODE_ISS_DATE;//组织机构代码证颁发日
	private String ORG_CODE_DUE_DATE;//组织机构代码证到期日
	private String UNIT_CREDIT_CODE;//机构信用代码
	private String REG_DATE;//注册日期(企业成立日期)
	private String REG_CPTL;//注册资本(元)
	private String REG_CPTL_CURR;//注册资本币别
	private String PAID_CPTL;//实收资本(元)
	private String PAID_CPTL_CURR;//实收资本币别
	private String COMP_SIZE;//企业规模
	private String REGISTER_ADDR;//注册地址
	private String ADMN_ADDR;//经营地址
	private String COMP_ATTR;//企业性质
	private String UNIT_TYPE;//单位类型
	private String INDUSTRY_TYPE;//行业类别
	private String ECON_KIND;//经济类型
	private String ADMN_TYPE;//经营范围
	private String TAX_REG_NO;//税务登记编号(国税)
	private String TAX_AREA_NO;//税务登记编号(地税)
	private String LEGAL_NAME;//法定代表人姓名
	private String LEGAL_CERT_TYPE;//法人证件种类
	private String LEGAL_CERT_NO;//法人证件号码
	private String LEGAL_CERT_EXPD_DATE;//法人证件到期日
	private String POST_CD;//邮政编码
	private String REGION_CODE;//所在行政区域
	private String OFFICE_TEL;//联系电话
	private String OFFICE_FAX;//传真号码
	private String WEB_ADDR;//公司网址
	private String EMAIL_ADDR;//公司邮件地址
	private String COM_ADDR;//公司地址
	private String REMARK;//备注
	
	/**
	 * 
	 */
	public RequestB112() {
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
	 * @return gOVN_CERT_NO
	 */
	public String getGOVN_CERT_NO() {
		return GOVN_CERT_NO;
	}

	/**
	 * @param gOVN_CERT_NO 要设置的 gOVN_CERT_NO
	 */
	public void setGOVN_CERT_NO(String gOVN_CERT_NO) {
		GOVN_CERT_NO = gOVN_CERT_NO;
	}

	/**
	 * @return gOVN_EFFT_DATE
	 */
	public String getGOVN_EFFT_DATE() {
		return GOVN_EFFT_DATE;
	}

	/**
	 * @param gOVN_EFFT_DATE 要设置的 gOVN_EFFT_DATE
	 */
	public void setGOVN_EFFT_DATE(String gOVN_EFFT_DATE) {
		GOVN_EFFT_DATE = gOVN_EFFT_DATE;
	}

	/**
	 * @return gOVN_EXPD_DATE
	 */
	public String getGOVN_EXPD_DATE() {
		return GOVN_EXPD_DATE;
	}

	/**
	 * @param gOVN_EXPD_DATE 要设置的 gOVN_EXPD_DATE
	 */
	public void setGOVN_EXPD_DATE(String gOVN_EXPD_DATE) {
		GOVN_EXPD_DATE = gOVN_EXPD_DATE;
	}

	/**
	 * @return bASIC_ACC_LIC_NO
	 */
	public String getBASIC_ACC_LIC_NO() {
		return BASIC_ACC_LIC_NO;
	}

	/**
	 * @param bASIC_ACC_LIC_NO 要设置的 bASIC_ACC_LIC_NO
	 */
	public void setBASIC_ACC_LIC_NO(String bASIC_ACC_LIC_NO) {
		BASIC_ACC_LIC_NO = bASIC_ACC_LIC_NO;
	}

	/**
	 * @return bASIC_ACC_PERMIT_NO
	 */
	public String getBASIC_ACC_PERMIT_NO() {
		return BASIC_ACC_PERMIT_NO;
	}

	/**
	 * @param bASIC_ACC_PERMIT_NO 要设置的 bASIC_ACC_PERMIT_NO
	 */
	public void setBASIC_ACC_PERMIT_NO(String bASIC_ACC_PERMIT_NO) {
		BASIC_ACC_PERMIT_NO = bASIC_ACC_PERMIT_NO;
	}

	/**
	 * @return lOAN_CARD_NO
	 */
	public String getLOAN_CARD_NO() {
		return LOAN_CARD_NO;
	}

	/**
	 * @param lOAN_CARD_NO 要设置的 lOAN_CARD_NO
	 */
	public void setLOAN_CARD_NO(String lOAN_CARD_NO) {
		LOAN_CARD_NO = lOAN_CARD_NO;
	}

	/**
	 * @return oRG_CODE
	 */
	public String getORG_CODE() {
		return ORG_CODE;
	}

	/**
	 * @param oRG_CODE 要设置的 oRG_CODE
	 */
	public void setORG_CODE(String oRG_CODE) {
		ORG_CODE = oRG_CODE;
	}

	/**
	 * @return oRG_CODE_ISS_DATE
	 */
	public String getORG_CODE_ISS_DATE() {
		return ORG_CODE_ISS_DATE;
	}

	/**
	 * @param oRG_CODE_ISS_DATE 要设置的 oRG_CODE_ISS_DATE
	 */
	public void setORG_CODE_ISS_DATE(String oRG_CODE_ISS_DATE) {
		ORG_CODE_ISS_DATE = oRG_CODE_ISS_DATE;
	}

	/**
	 * @return oRG_CODE_DUE_DATE
	 */
	public String getORG_CODE_DUE_DATE() {
		return ORG_CODE_DUE_DATE;
	}

	/**
	 * @param oRG_CODE_DUE_DATE 要设置的 oRG_CODE_DUE_DATE
	 */
	public void setORG_CODE_DUE_DATE(String oRG_CODE_DUE_DATE) {
		ORG_CODE_DUE_DATE = oRG_CODE_DUE_DATE;
	}

	/**
	 * @return uNIT_CREDIT_CODE
	 */
	public String getUNIT_CREDIT_CODE() {
		return UNIT_CREDIT_CODE;
	}

	/**
	 * @param uNIT_CREDIT_CODE 要设置的 uNIT_CREDIT_CODE
	 */
	public void setUNIT_CREDIT_CODE(String uNIT_CREDIT_CODE) {
		UNIT_CREDIT_CODE = uNIT_CREDIT_CODE;
	}

	/**
	 * @return rEG_DATE
	 */
	public String getREG_DATE() {
		return REG_DATE;
	}

	/**
	 * @param rEG_DATE 要设置的 rEG_DATE
	 */
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}

	/**
	 * @return rEG_CPTL
	 */
	public String getREG_CPTL() {
		return REG_CPTL;
	}

	/**
	 * @param rEG_CPTL 要设置的 rEG_CPTL
	 */
	public void setREG_CPTL(String rEG_CPTL) {
		REG_CPTL = rEG_CPTL;
	}

	/**
	 * @return rEG_CPTL_CURR
	 */
	public String getREG_CPTL_CURR() {
		return REG_CPTL_CURR;
	}

	/**
	 * @param rEG_CPTL_CURR 要设置的 rEG_CPTL_CURR
	 */
	public void setREG_CPTL_CURR(String rEG_CPTL_CURR) {
		REG_CPTL_CURR = rEG_CPTL_CURR;
	}

	/**
	 * @return pAID_CPTL
	 */
	public String getPAID_CPTL() {
		return PAID_CPTL;
	}

	/**
	 * @param pAID_CPTL 要设置的 pAID_CPTL
	 */
	public void setPAID_CPTL(String pAID_CPTL) {
		PAID_CPTL = pAID_CPTL;
	}

	/**
	 * @return pAID_CPTL_CURR
	 */
	public String getPAID_CPTL_CURR() {
		return PAID_CPTL_CURR;
	}

	/**
	 * @param pAID_CPTL_CURR 要设置的 pAID_CPTL_CURR
	 */
	public void setPAID_CPTL_CURR(String pAID_CPTL_CURR) {
		PAID_CPTL_CURR = pAID_CPTL_CURR;
	}

	/**
	 * @return cOMP_SIZE
	 */
	public String getCOMP_SIZE() {
		return COMP_SIZE;
	}

	/**
	 * @param cOMP_SIZE 要设置的 cOMP_SIZE
	 */
	public void setCOMP_SIZE(String cOMP_SIZE) {
		COMP_SIZE = cOMP_SIZE;
	}

	/**
	 * @return rEGISTER_ADDR
	 */
	public String getREGISTER_ADDR() {
		return REGISTER_ADDR;
	}

	/**
	 * @param rEGISTER_ADDR 要设置的 rEGISTER_ADDR
	 */
	public void setREGISTER_ADDR(String rEGISTER_ADDR) {
		REGISTER_ADDR = rEGISTER_ADDR;
	}

	/**
	 * @return aDMN_ADDR
	 */
	public String getADMN_ADDR() {
		return ADMN_ADDR;
	}

	/**
	 * @param aDMN_ADDR 要设置的 aDMN_ADDR
	 */
	public void setADMN_ADDR(String aDMN_ADDR) {
		ADMN_ADDR = aDMN_ADDR;
	}

	/**
	 * @return cOMP_ATTR
	 */
	public String getCOMP_ATTR() {
		return COMP_ATTR;
	}

	/**
	 * @param cOMP_ATTR 要设置的 cOMP_ATTR
	 */
	public void setCOMP_ATTR(String cOMP_ATTR) {
		COMP_ATTR = cOMP_ATTR;
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
	 * @return eCON_KIND
	 */
	public String getECON_KIND() {
		return ECON_KIND;
	}

	/**
	 * @param eCON_KIND 要设置的 eCON_KIND
	 */
	public void setECON_KIND(String eCON_KIND) {
		ECON_KIND = eCON_KIND;
	}

	/**
	 * @return aDMN_TYPE
	 */
	public String getADMN_TYPE() {
		return ADMN_TYPE;
	}

	/**
	 * @param aDMN_TYPE 要设置的 aDMN_TYPE
	 */
	public void setADMN_TYPE(String aDMN_TYPE) {
		ADMN_TYPE = aDMN_TYPE;
	}

	/**
	 * @return tAX_REG_NO
	 */
	public String getTAX_REG_NO() {
		return TAX_REG_NO;
	}

	/**
	 * @param tAX_REG_NO 要设置的 tAX_REG_NO
	 */
	public void setTAX_REG_NO(String tAX_REG_NO) {
		TAX_REG_NO = tAX_REG_NO;
	}

	/**
	 * @return tAX_AREA_NO
	 */
	public String getTAX_AREA_NO() {
		return TAX_AREA_NO;
	}

	/**
	 * @param tAX_AREA_NO 要设置的 tAX_AREA_NO
	 */
	public void setTAX_AREA_NO(String tAX_AREA_NO) {
		TAX_AREA_NO = tAX_AREA_NO;
	}

	/**
	 * @return lEGAL_NAME
	 */
	public String getLEGAL_NAME() {
		return LEGAL_NAME;
	}

	/**
	 * @param lEGAL_NAME 要设置的 lEGAL_NAME
	 */
	public void setLEGAL_NAME(String lEGAL_NAME) {
		LEGAL_NAME = lEGAL_NAME;
	}

	/**
	 * @return lEGAL_CERT_TYPE
	 */
	public String getLEGAL_CERT_TYPE() {
		return LEGAL_CERT_TYPE;
	}

	/**
	 * @param lEGAL_CERT_TYPE 要设置的 lEGAL_CERT_TYPE
	 */
	public void setLEGAL_CERT_TYPE(String lEGAL_CERT_TYPE) {
		LEGAL_CERT_TYPE = lEGAL_CERT_TYPE;
	}

	/**
	 * @return lEGAL_CERT_NO
	 */
	public String getLEGAL_CERT_NO() {
		return LEGAL_CERT_NO;
	}

	/**
	 * @param lEGAL_CERT_NO 要设置的 lEGAL_CERT_NO
	 */
	public void setLEGAL_CERT_NO(String lEGAL_CERT_NO) {
		LEGAL_CERT_NO = lEGAL_CERT_NO;
	}

	/**
	 * @return lEGAL_CERT_EXPD_DATE
	 */
	public String getLEGAL_CERT_EXPD_DATE() {
		return LEGAL_CERT_EXPD_DATE;
	}

	/**
	 * @param lEGAL_CERT_EXPD_DATE 要设置的 lEGAL_CERT_EXPD_DATE
	 */
	public void setLEGAL_CERT_EXPD_DATE(String lEGAL_CERT_EXPD_DATE) {
		LEGAL_CERT_EXPD_DATE = lEGAL_CERT_EXPD_DATE;
	}

	/**
	 * @return pOST_CD
	 */
	public String getPOST_CD() {
		return POST_CD;
	}

	/**
	 * @param pOST_CD 要设置的 pOST_CD
	 */
	public void setPOST_CD(String pOST_CD) {
		POST_CD = pOST_CD;
	}

	/**
	 * @return rEGION_CODE
	 */
	public String getREGION_CODE() {
		return REGION_CODE;
	}

	/**
	 * @param rEGION_CODE 要设置的 rEGION_CODE
	 */
	public void setREGION_CODE(String rEGION_CODE) {
		REGION_CODE = rEGION_CODE;
	}

	/**
	 * @return oFFICE_TEL
	 */
	public String getOFFICE_TEL() {
		return OFFICE_TEL;
	}

	/**
	 * @param oFFICE_TEL 要设置的 oFFICE_TEL
	 */
	public void setOFFICE_TEL(String oFFICE_TEL) {
		OFFICE_TEL = oFFICE_TEL;
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
	 * @return wEB_ADDR
	 */
	public String getWEB_ADDR() {
		return WEB_ADDR;
	}

	/**
	 * @param wEB_ADDR 要设置的 wEB_ADDR
	 */
	public void setWEB_ADDR(String wEB_ADDR) {
		WEB_ADDR = wEB_ADDR;
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
	 * @return cOM_ADDR
	 */
	public String getCOM_ADDR() {
		return COM_ADDR;
	}

	/**
	 * @param cOM_ADDR 要设置的 cOM_ADDR
	 */
	public void setCOM_ADDR(String cOM_ADDR) {
		COM_ADDR = cOM_ADDR;
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
