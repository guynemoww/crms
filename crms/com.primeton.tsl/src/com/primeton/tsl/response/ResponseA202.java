/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户基本信息查询响应报文			

 */
public class ResponseA202 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2561766120095114756L;
	private String ECIF_CUST_NO;//客户编号
	private String CUST_TYPE;//客户类型
	private String PARTY_NAME;//客户名称
	private String CERT_TYPE;//证件类型
	private String CERT_NO;//证件号码
	private String CERT_ORG_NAT;//发证机关国家
	private String CERT_ORG_AREA;//发证机关所在地区
	private String CERT_ORG_NAME;//发证机关名称
	private String CERT_ISSUE_DATE;//证件签发日期
	private String CERT_DUE_DATE;//证件到期日期
	private String CERT_TYPE_FLAG;//证件类型标识
	private String CERT_IMG_ADDR;//证件影像地址
	private String CERT_DESC;//证件其它说明
	private String CUST_SHTNAME;//客户简称
	private String CUST_ENNAME;//英文名称
	private String CUST_SPNAME;//英文简称
	private String CUST_BNAME;//客户曾用名
	private String GOVN_CERT_NO;//营业执照号码
	private String GOVN_EFFT_DATE;//营业执照生效日期
	private String GOVN_EXPD_DATE;//营业执照有效日期
	private String GOVN_REVIEW_YEAR;//营业执照最新年检年份
	private String ORG_CODE;//组织机构代码
	private String ORG_CODE_ISSU;//组织机构代码证颁发机关
	private String ORG_CODE_ISS_DATE;//组织机构代码证颁发日
	private String ORG_CODE_DUE_DATE;//组织机构代码证到期日
	private String REG_ORG;//注册登记机关
	private String REG_TYPE;//登记注册类型
	private String REG_CHECK_DATE;//注册登记年审到期日
	private String REG_COUNTRY;//注册地国别
	private String REG_PROVINCE;//注册地省别
	private String REG_AREA_CODE;//注册地区代码
	private String REG_DATE;//注册日期(企业成立日期)
	private String REG_CPTL;//注册资本(元)
	private String REG_CPTL_CURR;//注册资本币别
	private String PAID_CPTL;//实收资本(元)
	private String PAID_CPTL_CURR;//实收资本币别
	private String ORG_TYPE;//组织机构类型
	private String CORP_NATURE;//企业性质
	private String CORP_ATTR;//企业属性
	private String MNGMT_ORG_FORM;//经营组织形式
	private String CUST_SUBJ_TYPE;//客户主体类型
	private String TAX_REG_NO;//税务登记编号(国税)
	private String REG_EXPD_DATE;//税务登记编号(国税)有效日期
	private String TAX_REG_ORG;//税务登记机关(国税)
	private String TAX_AREA_NO;//税务登记编号(地税)
	private String AREA_EXPD_DATE;//税务登记编号(地税)有效日期
	private String AREA_REG_ORG;//税务登记机关(地税)
	private String IDVU_TX_NO;//纳税人识别号
	private String LOAN_CARD_FLAG;//贷款卡标识
	private String LOAN_CARD_NO;//贷款卡号
	private String LOAN_CARD_DUE_DATE;//贷款卡有效日期
	private String LOAN_CARD_CHK_DATE;//贷款卡年检日期
	private String UNIT_CREDIT_CODE;//机构信用代码
	private String DEPT_NAT_ECON;//国民经济部门
	private String MANG_DEPT;//上级主管部门
	private String CORP_SUBJ;//企业隶属
	private String INDUSTRY_TYPE;//行业类别
	private String ECON_KIND;//经济类型
	private String BASIC_ACC_LIC_NO;//基本户开户许可证编号
	private String BASIC_ACC_PERMIT_NO;//基本户开户许可证核准号
	private String BASIC_ACC_BANK_NO;//基本账户开户行行号
	private String BASIC_ACC_OPEN_BANK;//基本账户开户行名称
	private String BASIC_ACC_NO;//基本账户账号
	private String BASIC_ACC_NAME;//基本账户户名
	private String BASIC_ACC_OPEN_DATE;//基本户开户日期
	private String BASIC_ACC_STATE;//基本户状态
	private String BUSI_LIC_NO;//经营许可证号
	private String ADMN_TYPE;//主营范围
	private String SIDE_TYPE;//兼营范围
	private String COUNTRY_MNG;//经营地国别
	private String PROVINCE_MNG;//经营地省别
	private String MNG_AREA_CODE;//经营地区代码
	private String MNG_SITUATION;//经营状况
	private String MNG_OPERATE_AREA;//经营场地面积(平方米)
	private String MNG_OPERATE_OWNERSHIP;//经营场地所有权
	private String COMP_SIZE;//企业规模
	private String ASSETS_SIZE;//资产规模
	private String EMP_SIZE;//员工规模
	private String IMPT_SIZE;//进口规模
	private String EXIT_SIZE;//出口规模
	private String SALE_SIZE;//销售规模
	private String EMP_NUM;//员工总数
	private String TOTAL_ASSETS;//总资产
	private String NET_ASSETS;//净资产
	private String SELL_SUM;//年销售额
	private String ANNUAL_INCOME;//年收入
	private String FREE_TAX_FLAG;//免税标志
	private String FREE_TAX_LIMIT;//免税期限
	private String TAX_RATE_COUNTRY;//税率国别
	private String INTEREST_RATE;//利息税率
	private String TAX_RATE_DATE;//税率有效日期
	private String PRIVATE_FLAG;//民营标志
	private String LISTED_FLAG;//上市公司标志
	private String HOLDING_TYPE;//控股类型
	private String ACTUAL_CONTROLLER;//控股股东或实际控制人
	private String COUN_CORP_FLAG;//是否农村企业
	private String TOPAGRI_CORP_FLAG;//农业产业化龙头企业标志
	private String TOPAGRI_CORP_KIND;//农业产业化龙头企业类型
	private String THREE_AGR_FLAG;//是否三农企业
	private String NEW_TECH_CORPORNOT;//是否高新技术企业
	private String SPE_INDUSTRY_FLAG;//特殊行业类型标志
	private String SPE_INDUSTRY_LIC;//特殊行业许可证号码
	private String IMEX_MANA_IND;//自营进出口权标志
	private String AREA_TYPE;//区域标志
	private String FIN_CUST_TYPE;//金融客户类型
	private String FIN_ORG_TYPE;//金融机构类型
	private String SWIFT_NO;//SWIFT编号
	private String PAY_NO;//大额支付号(联行号)
	private String FIN_LIC_NO;//金融许可证编号
	private String FIN_ORG_CD;//金融机构代码
	private String FIN_MANAGE_AREA;//金融机构经营区域范围
	private String FIN_DEPT_FLAG;//同业存放标志
	private String DEPT_FIN_FLAG;//存放同业标志
	private String CUST_FORE_EXCH_ATTR;//客户外汇属性维护标志
	private String NRA_FLAG;//NRA客户标志
	private String SPE_INST_CODE;//特殊机构代码赋码
	private String FORE_CUST_TYPE;//外管客户类型
	private String FORE_EXCH_LIC_NO;//外汇许可证号码
	private String BUSI_SITE_CODE;//营业场所代码
	private String RES_COUNTRY;//常驻国家代码
	private String FORE_BASIC_ACC_BANK;//外币基本账户开户行
	private String FORE_BASIC_ACC;//外币基本账户账号
	private String FORE_INV_COUNTRY;//外方投资国别
	private String FORE_ECON_TRDE_FLAG;//对外经济贸易经营权
	private String SPE_ECON_INST_FLAG;//是否特殊经济区内企业
	private String SPE_ECON_INST_TYPE;//特殊经济区企业类型
	private String PAY_LIS_FLAG;//是否进口付汇名录内
	private String FORE_SAFE_NO;//所属外管局编号
	private String FORE_INDUSTRY_TYPE;//外管行业属性
	private String FORE_ECON_TYPE;//外管经济类型
	private String FORE_FIRST_NAME;//第一外管局注册名称
	private String FORE_SECOND_NAME;//第二外管局注册名称
	private String BANK_REL_FLAG;//与我行关系
	private String SHAREHOLDER_FLAG;//是否本行股东
	private String STOCK_NUM;//持股数
	private String STOCK_ACCT;//股权账号
	private String REL_UNIT_FLAG;//是否为关联单位
	private String CUST_EVAL_LEVEL;//客户综合评估级别
	private String BANK_CREDIT_LEVEL;//本行评估信用等级
	private String BANK_EVALUATE_DATE;//本行评估日期
	private String BANK_ORG_NAME;//本行评级机构名称
	private String BANK_ORG_RESULT;//本行机构评级结果
	private String OTHER_CREDIT_LEVEL;//外部机构评估信用等级
	private String OTHER_EVALUATE_DATE;//外部机构评级日期
	private String OTHER_ORG_NAME;//外部评级机构名称
	private String OTHER_ORG_RESULT;//外部机构评级结果
	private String EVALUATE_LEVEL;//信用等级认定级别
	private String CUAT_RISK_LEVEL;//客户风险级别
	private String CUST_LEVEL;//客户等级
	private String IDE_CHECK_FLAG;//身份核查标志
	private String CUST_MANAGER_NO;//客户经理编号
	private String CUST_MNG_NAME;//客户经理名称
	private String OWN_ORG;//所属机构
	private String OPEN_ORG;//开户机构
	private String OPEN_TELLER;//开户柜员
	private String OPEN_DATE;//开户日期
	private String REAL_FULL_FLAG;//信息完整性标志
	private String CUST_STATUS;//客户有效状态
	private String LAST_SYSTEM_ID;//最新更新渠道
	private String LAST_UPDATED_TS;//最新更新时间
	
	/**
	 * 
	 */
	public ResponseA202() {
		// TODO 自动生成的构造函数存根
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
	 * @return cUST_TYPE
	 */
	public String getCUST_TYPE() {
		return CUST_TYPE;
	}

	/**
	 * @param cUST_TYPE 要设置的 cUST_TYPE
	 */
	public void setCUST_TYPE(String cUST_TYPE) {
		CUST_TYPE = cUST_TYPE;
	}

	/**
	 * @return pARTY_NAME
	 */
	public String getPARTY_NAME() {
		return PARTY_NAME;
	}

	/**
	 * @param pARTY_NAME 要设置的 pARTY_NAME
	 */
	public void setPARTY_NAME(String pARTY_NAME) {
		PARTY_NAME = pARTY_NAME;
	}

	/**
	 * @return cERT_TYPE
	 */
	public String getCERT_TYPE() {
		return CERT_TYPE;
	}

	/**
	 * @param cERT_TYPE 要设置的 cERT_TYPE
	 */
	public void setCERT_TYPE(String cERT_TYPE) {
		CERT_TYPE = cERT_TYPE;
	}

	/**
	 * @return cERT_NO
	 */
	public String getCERT_NO() {
		return CERT_NO;
	}

	/**
	 * @param cERT_NO 要设置的 cERT_NO
	 */
	public void setCERT_NO(String cERT_NO) {
		CERT_NO = cERT_NO;
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
	 * @return cERT_TYPE_FLAG
	 */
	public String getCERT_TYPE_FLAG() {
		return CERT_TYPE_FLAG;
	}

	/**
	 * @param cERT_TYPE_FLAG 要设置的 cERT_TYPE_FLAG
	 */
	public void setCERT_TYPE_FLAG(String cERT_TYPE_FLAG) {
		CERT_TYPE_FLAG = cERT_TYPE_FLAG;
	}

	/**
	 * @return cERT_IMG_ADDR
	 */
	public String getCERT_IMG_ADDR() {
		return CERT_IMG_ADDR;
	}

	/**
	 * @param cERT_IMG_ADDR 要设置的 cERT_IMG_ADDR
	 */
	public void setCERT_IMG_ADDR(String cERT_IMG_ADDR) {
		CERT_IMG_ADDR = cERT_IMG_ADDR;
	}

	/**
	 * @return cERT_DESC
	 */
	public String getCERT_DESC() {
		return CERT_DESC;
	}

	/**
	 * @param cERT_DESC 要设置的 cERT_DESC
	 */
	public void setCERT_DESC(String cERT_DESC) {
		CERT_DESC = cERT_DESC;
	}

	/**
	 * @return cUST_SHTNAME
	 */
	public String getCUST_SHTNAME() {
		return CUST_SHTNAME;
	}

	/**
	 * @param cUST_SHTNAME 要设置的 cUST_SHTNAME
	 */
	public void setCUST_SHTNAME(String cUST_SHTNAME) {
		CUST_SHTNAME = cUST_SHTNAME;
	}

	/**
	 * @return cUST_ENNAME
	 */
	public String getCUST_ENNAME() {
		return CUST_ENNAME;
	}

	/**
	 * @param cUST_ENNAME 要设置的 cUST_ENNAME
	 */
	public void setCUST_ENNAME(String cUST_ENNAME) {
		CUST_ENNAME = cUST_ENNAME;
	}

	/**
	 * @return cUST_SPNAME
	 */
	public String getCUST_SPNAME() {
		return CUST_SPNAME;
	}

	/**
	 * @param cUST_SPNAME 要设置的 cUST_SPNAME
	 */
	public void setCUST_SPNAME(String cUST_SPNAME) {
		CUST_SPNAME = cUST_SPNAME;
	}

	/**
	 * @return cUST_BNAME
	 */
	public String getCUST_BNAME() {
		return CUST_BNAME;
	}

	/**
	 * @param cUST_BNAME 要设置的 cUST_BNAME
	 */
	public void setCUST_BNAME(String cUST_BNAME) {
		CUST_BNAME = cUST_BNAME;
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
	 * @return gOVN_REVIEW_YEAR
	 */
	public String getGOVN_REVIEW_YEAR() {
		return GOVN_REVIEW_YEAR;
	}

	/**
	 * @param gOVN_REVIEW_YEAR 要设置的 gOVN_REVIEW_YEAR
	 */
	public void setGOVN_REVIEW_YEAR(String gOVN_REVIEW_YEAR) {
		GOVN_REVIEW_YEAR = gOVN_REVIEW_YEAR;
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
	 * @return oRG_CODE_ISSU
	 */
	public String getORG_CODE_ISSU() {
		return ORG_CODE_ISSU;
	}

	/**
	 * @param oRG_CODE_ISSU 要设置的 oRG_CODE_ISSU
	 */
	public void setORG_CODE_ISSU(String oRG_CODE_ISSU) {
		ORG_CODE_ISSU = oRG_CODE_ISSU;
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
	 * @return rEG_ORG
	 */
	public String getREG_ORG() {
		return REG_ORG;
	}

	/**
	 * @param rEG_ORG 要设置的 rEG_ORG
	 */
	public void setREG_ORG(String rEG_ORG) {
		REG_ORG = rEG_ORG;
	}

	/**
	 * @return rEG_TYPE
	 */
	public String getREG_TYPE() {
		return REG_TYPE;
	}

	/**
	 * @param rEG_TYPE 要设置的 rEG_TYPE
	 */
	public void setREG_TYPE(String rEG_TYPE) {
		REG_TYPE = rEG_TYPE;
	}

	/**
	 * @return rEG_CHECK_DATE
	 */
	public String getREG_CHECK_DATE() {
		return REG_CHECK_DATE;
	}

	/**
	 * @param rEG_CHECK_DATE 要设置的 rEG_CHECK_DATE
	 */
	public void setREG_CHECK_DATE(String rEG_CHECK_DATE) {
		REG_CHECK_DATE = rEG_CHECK_DATE;
	}

	/**
	 * @return rEG_COUNTRY
	 */
	public String getREG_COUNTRY() {
		return REG_COUNTRY;
	}

	/**
	 * @param rEG_COUNTRY 要设置的 rEG_COUNTRY
	 */
	public void setREG_COUNTRY(String rEG_COUNTRY) {
		REG_COUNTRY = rEG_COUNTRY;
	}

	/**
	 * @return rEG_PROVINCE
	 */
	public String getREG_PROVINCE() {
		return REG_PROVINCE;
	}

	/**
	 * @param rEG_PROVINCE 要设置的 rEG_PROVINCE
	 */
	public void setREG_PROVINCE(String rEG_PROVINCE) {
		REG_PROVINCE = rEG_PROVINCE;
	}

	/**
	 * @return rEG_AREA_CODE
	 */
	public String getREG_AREA_CODE() {
		return REG_AREA_CODE;
	}

	/**
	 * @param rEG_AREA_CODE 要设置的 rEG_AREA_CODE
	 */
	public void setREG_AREA_CODE(String rEG_AREA_CODE) {
		REG_AREA_CODE = rEG_AREA_CODE;
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
	 * @return oRG_TYPE
	 */
	public String getORG_TYPE() {
		return ORG_TYPE;
	}

	/**
	 * @param oRG_TYPE 要设置的 oRG_TYPE
	 */
	public void setORG_TYPE(String oRG_TYPE) {
		ORG_TYPE = oRG_TYPE;
	}

	/**
	 * @return cORP_NATURE
	 */
	public String getCORP_NATURE() {
		return CORP_NATURE;
	}

	/**
	 * @param cORP_NATURE 要设置的 cORP_NATURE
	 */
	public void setCORP_NATURE(String cORP_NATURE) {
		CORP_NATURE = cORP_NATURE;
	}

	/**
	 * @return cORP_ATTR
	 */
	public String getCORP_ATTR() {
		return CORP_ATTR;
	}

	/**
	 * @param cORP_ATTR 要设置的 cORP_ATTR
	 */
	public void setCORP_ATTR(String cORP_ATTR) {
		CORP_ATTR = cORP_ATTR;
	}

	/**
	 * @return mNGMT_ORG_FORM
	 */
	public String getMNGMT_ORG_FORM() {
		return MNGMT_ORG_FORM;
	}

	/**
	 * @param mNGMT_ORG_FORM 要设置的 mNGMT_ORG_FORM
	 */
	public void setMNGMT_ORG_FORM(String mNGMT_ORG_FORM) {
		MNGMT_ORG_FORM = mNGMT_ORG_FORM;
	}

	/**
	 * @return cUST_SUBJ_TYPE
	 */
	public String getCUST_SUBJ_TYPE() {
		return CUST_SUBJ_TYPE;
	}

	/**
	 * @param cUST_SUBJ_TYPE 要设置的 cUST_SUBJ_TYPE
	 */
	public void setCUST_SUBJ_TYPE(String cUST_SUBJ_TYPE) {
		CUST_SUBJ_TYPE = cUST_SUBJ_TYPE;
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
	 * @return rEG_EXPD_DATE
	 */
	public String getREG_EXPD_DATE() {
		return REG_EXPD_DATE;
	}

	/**
	 * @param rEG_EXPD_DATE 要设置的 rEG_EXPD_DATE
	 */
	public void setREG_EXPD_DATE(String rEG_EXPD_DATE) {
		REG_EXPD_DATE = rEG_EXPD_DATE;
	}

	/**
	 * @return tAX_REG_ORG
	 */
	public String getTAX_REG_ORG() {
		return TAX_REG_ORG;
	}

	/**
	 * @param tAX_REG_ORG 要设置的 tAX_REG_ORG
	 */
	public void setTAX_REG_ORG(String tAX_REG_ORG) {
		TAX_REG_ORG = tAX_REG_ORG;
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
	 * @return aREA_EXPD_DATE
	 */
	public String getAREA_EXPD_DATE() {
		return AREA_EXPD_DATE;
	}

	/**
	 * @param aREA_EXPD_DATE 要设置的 aREA_EXPD_DATE
	 */
	public void setAREA_EXPD_DATE(String aREA_EXPD_DATE) {
		AREA_EXPD_DATE = aREA_EXPD_DATE;
	}

	/**
	 * @return aREA_REG_ORG
	 */
	public String getAREA_REG_ORG() {
		return AREA_REG_ORG;
	}

	/**
	 * @param aREA_REG_ORG 要设置的 aREA_REG_ORG
	 */
	public void setAREA_REG_ORG(String aREA_REG_ORG) {
		AREA_REG_ORG = aREA_REG_ORG;
	}

	/**
	 * @return iDVU_TX_NO
	 */
	public String getIDVU_TX_NO() {
		return IDVU_TX_NO;
	}

	/**
	 * @param iDVU_TX_NO 要设置的 iDVU_TX_NO
	 */
	public void setIDVU_TX_NO(String iDVU_TX_NO) {
		IDVU_TX_NO = iDVU_TX_NO;
	}

	/**
	 * @return lOAN_CARD_FLAG
	 */
	public String getLOAN_CARD_FLAG() {
		return LOAN_CARD_FLAG;
	}

	/**
	 * @param lOAN_CARD_FLAG 要设置的 lOAN_CARD_FLAG
	 */
	public void setLOAN_CARD_FLAG(String lOAN_CARD_FLAG) {
		LOAN_CARD_FLAG = lOAN_CARD_FLAG;
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
	 * @return lOAN_CARD_DUE_DATE
	 */
	public String getLOAN_CARD_DUE_DATE() {
		return LOAN_CARD_DUE_DATE;
	}

	/**
	 * @param lOAN_CARD_DUE_DATE 要设置的 lOAN_CARD_DUE_DATE
	 */
	public void setLOAN_CARD_DUE_DATE(String lOAN_CARD_DUE_DATE) {
		LOAN_CARD_DUE_DATE = lOAN_CARD_DUE_DATE;
	}

	/**
	 * @return lOAN_CARD_CHK_DATE
	 */
	public String getLOAN_CARD_CHK_DATE() {
		return LOAN_CARD_CHK_DATE;
	}

	/**
	 * @param lOAN_CARD_CHK_DATE 要设置的 lOAN_CARD_CHK_DATE
	 */
	public void setLOAN_CARD_CHK_DATE(String lOAN_CARD_CHK_DATE) {
		LOAN_CARD_CHK_DATE = lOAN_CARD_CHK_DATE;
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
	 * @return dEPT_NAT_ECON
	 */
	public String getDEPT_NAT_ECON() {
		return DEPT_NAT_ECON;
	}

	/**
	 * @param dEPT_NAT_ECON 要设置的 dEPT_NAT_ECON
	 */
	public void setDEPT_NAT_ECON(String dEPT_NAT_ECON) {
		DEPT_NAT_ECON = dEPT_NAT_ECON;
	}

	/**
	 * @return mANG_DEPT
	 */
	public String getMANG_DEPT() {
		return MANG_DEPT;
	}

	/**
	 * @param mANG_DEPT 要设置的 mANG_DEPT
	 */
	public void setMANG_DEPT(String mANG_DEPT) {
		MANG_DEPT = mANG_DEPT;
	}

	/**
	 * @return cORP_SUBJ
	 */
	public String getCORP_SUBJ() {
		return CORP_SUBJ;
	}

	/**
	 * @param cORP_SUBJ 要设置的 cORP_SUBJ
	 */
	public void setCORP_SUBJ(String cORP_SUBJ) {
		CORP_SUBJ = cORP_SUBJ;
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
	 * @return bASIC_ACC_BANK_NO
	 */
	public String getBASIC_ACC_BANK_NO() {
		return BASIC_ACC_BANK_NO;
	}

	/**
	 * @param bASIC_ACC_BANK_NO 要设置的 bASIC_ACC_BANK_NO
	 */
	public void setBASIC_ACC_BANK_NO(String bASIC_ACC_BANK_NO) {
		BASIC_ACC_BANK_NO = bASIC_ACC_BANK_NO;
	}

	/**
	 * @return bASIC_ACC_OPEN_BANK
	 */
	public String getBASIC_ACC_OPEN_BANK() {
		return BASIC_ACC_OPEN_BANK;
	}

	/**
	 * @param bASIC_ACC_OPEN_BANK 要设置的 bASIC_ACC_OPEN_BANK
	 */
	public void setBASIC_ACC_OPEN_BANK(String bASIC_ACC_OPEN_BANK) {
		BASIC_ACC_OPEN_BANK = bASIC_ACC_OPEN_BANK;
	}

	/**
	 * @return bASIC_ACC_NO
	 */
	public String getBASIC_ACC_NO() {
		return BASIC_ACC_NO;
	}

	/**
	 * @param bASIC_ACC_NO 要设置的 bASIC_ACC_NO
	 */
	public void setBASIC_ACC_NO(String bASIC_ACC_NO) {
		BASIC_ACC_NO = bASIC_ACC_NO;
	}

	/**
	 * @return bASIC_ACC_NAME
	 */
	public String getBASIC_ACC_NAME() {
		return BASIC_ACC_NAME;
	}

	/**
	 * @param bASIC_ACC_NAME 要设置的 bASIC_ACC_NAME
	 */
	public void setBASIC_ACC_NAME(String bASIC_ACC_NAME) {
		BASIC_ACC_NAME = bASIC_ACC_NAME;
	}

	/**
	 * @return bASIC_ACC_OPEN_DATE
	 */
	public String getBASIC_ACC_OPEN_DATE() {
		return BASIC_ACC_OPEN_DATE;
	}

	/**
	 * @param bASIC_ACC_OPEN_DATE 要设置的 bASIC_ACC_OPEN_DATE
	 */
	public void setBASIC_ACC_OPEN_DATE(String bASIC_ACC_OPEN_DATE) {
		BASIC_ACC_OPEN_DATE = bASIC_ACC_OPEN_DATE;
	}

	/**
	 * @return bASIC_ACC_STATE
	 */
	public String getBASIC_ACC_STATE() {
		return BASIC_ACC_STATE;
	}

	/**
	 * @param bASIC_ACC_STATE 要设置的 bASIC_ACC_STATE
	 */
	public void setBASIC_ACC_STATE(String bASIC_ACC_STATE) {
		BASIC_ACC_STATE = bASIC_ACC_STATE;
	}

	/**
	 * @return bUSI_LIC_NO
	 */
	public String getBUSI_LIC_NO() {
		return BUSI_LIC_NO;
	}

	/**
	 * @param bUSI_LIC_NO 要设置的 bUSI_LIC_NO
	 */
	public void setBUSI_LIC_NO(String bUSI_LIC_NO) {
		BUSI_LIC_NO = bUSI_LIC_NO;
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
	 * @return sIDE_TYPE
	 */
	public String getSIDE_TYPE() {
		return SIDE_TYPE;
	}

	/**
	 * @param sIDE_TYPE 要设置的 sIDE_TYPE
	 */
	public void setSIDE_TYPE(String sIDE_TYPE) {
		SIDE_TYPE = sIDE_TYPE;
	}

	/**
	 * @return cOUNTRY_MNG
	 */
	public String getCOUNTRY_MNG() {
		return COUNTRY_MNG;
	}

	/**
	 * @param cOUNTRY_MNG 要设置的 cOUNTRY_MNG
	 */
	public void setCOUNTRY_MNG(String cOUNTRY_MNG) {
		COUNTRY_MNG = cOUNTRY_MNG;
	}

	/**
	 * @return pROVINCE_MNG
	 */
	public String getPROVINCE_MNG() {
		return PROVINCE_MNG;
	}

	/**
	 * @param pROVINCE_MNG 要设置的 pROVINCE_MNG
	 */
	public void setPROVINCE_MNG(String pROVINCE_MNG) {
		PROVINCE_MNG = pROVINCE_MNG;
	}

	/**
	 * @return mNG_AREA_CODE
	 */
	public String getMNG_AREA_CODE() {
		return MNG_AREA_CODE;
	}

	/**
	 * @param mNG_AREA_CODE 要设置的 mNG_AREA_CODE
	 */
	public void setMNG_AREA_CODE(String mNG_AREA_CODE) {
		MNG_AREA_CODE = mNG_AREA_CODE;
	}

	/**
	 * @return mNG_SITUATION
	 */
	public String getMNG_SITUATION() {
		return MNG_SITUATION;
	}

	/**
	 * @param mNG_SITUATION 要设置的 mNG_SITUATION
	 */
	public void setMNG_SITUATION(String mNG_SITUATION) {
		MNG_SITUATION = mNG_SITUATION;
	}

	/**
	 * @return mNG_OPERATE_AREA
	 */
	public String getMNG_OPERATE_AREA() {
		return MNG_OPERATE_AREA;
	}

	/**
	 * @param mNG_OPERATE_AREA 要设置的 mNG_OPERATE_AREA
	 */
	public void setMNG_OPERATE_AREA(String mNG_OPERATE_AREA) {
		MNG_OPERATE_AREA = mNG_OPERATE_AREA;
	}

	/**
	 * @return mNG_OPERATE_OWNERSHIP
	 */
	public String getMNG_OPERATE_OWNERSHIP() {
		return MNG_OPERATE_OWNERSHIP;
	}

	/**
	 * @param mNG_OPERATE_OWNERSHIP 要设置的 mNG_OPERATE_OWNERSHIP
	 */
	public void setMNG_OPERATE_OWNERSHIP(String mNG_OPERATE_OWNERSHIP) {
		MNG_OPERATE_OWNERSHIP = mNG_OPERATE_OWNERSHIP;
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
	 * @return aSSETS_SIZE
	 */
	public String getASSETS_SIZE() {
		return ASSETS_SIZE;
	}

	/**
	 * @param aSSETS_SIZE 要设置的 aSSETS_SIZE
	 */
	public void setASSETS_SIZE(String aSSETS_SIZE) {
		ASSETS_SIZE = aSSETS_SIZE;
	}

	/**
	 * @return eMP_SIZE
	 */
	public String getEMP_SIZE() {
		return EMP_SIZE;
	}

	/**
	 * @param eMP_SIZE 要设置的 eMP_SIZE
	 */
	public void setEMP_SIZE(String eMP_SIZE) {
		EMP_SIZE = eMP_SIZE;
	}

	/**
	 * @return iMPT_SIZE
	 */
	public String getIMPT_SIZE() {
		return IMPT_SIZE;
	}

	/**
	 * @param iMPT_SIZE 要设置的 iMPT_SIZE
	 */
	public void setIMPT_SIZE(String iMPT_SIZE) {
		IMPT_SIZE = iMPT_SIZE;
	}

	/**
	 * @return eXIT_SIZE
	 */
	public String getEXIT_SIZE() {
		return EXIT_SIZE;
	}

	/**
	 * @param eXIT_SIZE 要设置的 eXIT_SIZE
	 */
	public void setEXIT_SIZE(String eXIT_SIZE) {
		EXIT_SIZE = eXIT_SIZE;
	}

	/**
	 * @return sALE_SIZE
	 */
	public String getSALE_SIZE() {
		return SALE_SIZE;
	}

	/**
	 * @param sALE_SIZE 要设置的 sALE_SIZE
	 */
	public void setSALE_SIZE(String sALE_SIZE) {
		SALE_SIZE = sALE_SIZE;
	}

	/**
	 * @return eMP_NUM
	 */
	public String getEMP_NUM() {
		return EMP_NUM;
	}

	/**
	 * @param eMP_NUM 要设置的 eMP_NUM
	 */
	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}

	/**
	 * @return tOTAL_ASSETS
	 */
	public String getTOTAL_ASSETS() {
		return TOTAL_ASSETS;
	}

	/**
	 * @param tOTAL_ASSETS 要设置的 tOTAL_ASSETS
	 */
	public void setTOTAL_ASSETS(String tOTAL_ASSETS) {
		TOTAL_ASSETS = tOTAL_ASSETS;
	}

	/**
	 * @return nET_ASSETS
	 */
	public String getNET_ASSETS() {
		return NET_ASSETS;
	}

	/**
	 * @param nET_ASSETS 要设置的 nET_ASSETS
	 */
	public void setNET_ASSETS(String nET_ASSETS) {
		NET_ASSETS = nET_ASSETS;
	}

	/**
	 * @return sELL_SUM
	 */
	public String getSELL_SUM() {
		return SELL_SUM;
	}

	/**
	 * @param sELL_SUM 要设置的 sELL_SUM
	 */
	public void setSELL_SUM(String sELL_SUM) {
		SELL_SUM = sELL_SUM;
	}

	/**
	 * @return aNNUAL_INCOME
	 */
	public String getANNUAL_INCOME() {
		return ANNUAL_INCOME;
	}

	/**
	 * @param aNNUAL_INCOME 要设置的 aNNUAL_INCOME
	 */
	public void setANNUAL_INCOME(String aNNUAL_INCOME) {
		ANNUAL_INCOME = aNNUAL_INCOME;
	}

	/**
	 * @return fREE_TAX_FLAG
	 */
	public String getFREE_TAX_FLAG() {
		return FREE_TAX_FLAG;
	}

	/**
	 * @param fREE_TAX_FLAG 要设置的 fREE_TAX_FLAG
	 */
	public void setFREE_TAX_FLAG(String fREE_TAX_FLAG) {
		FREE_TAX_FLAG = fREE_TAX_FLAG;
	}

	/**
	 * @return fREE_TAX_LIMIT
	 */
	public String getFREE_TAX_LIMIT() {
		return FREE_TAX_LIMIT;
	}

	/**
	 * @param fREE_TAX_LIMIT 要设置的 fREE_TAX_LIMIT
	 */
	public void setFREE_TAX_LIMIT(String fREE_TAX_LIMIT) {
		FREE_TAX_LIMIT = fREE_TAX_LIMIT;
	}

	/**
	 * @return tAX_RATE_COUNTRY
	 */
	public String getTAX_RATE_COUNTRY() {
		return TAX_RATE_COUNTRY;
	}

	/**
	 * @param tAX_RATE_COUNTRY 要设置的 tAX_RATE_COUNTRY
	 */
	public void setTAX_RATE_COUNTRY(String tAX_RATE_COUNTRY) {
		TAX_RATE_COUNTRY = tAX_RATE_COUNTRY;
	}

	/**
	 * @return iNTEREST_RATE
	 */
	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}

	/**
	 * @param iNTEREST_RATE 要设置的 iNTEREST_RATE
	 */
	public void setINTEREST_RATE(String iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}

	/**
	 * @return tAX_RATE_DATE
	 */
	public String getTAX_RATE_DATE() {
		return TAX_RATE_DATE;
	}

	/**
	 * @param tAX_RATE_DATE 要设置的 tAX_RATE_DATE
	 */
	public void setTAX_RATE_DATE(String tAX_RATE_DATE) {
		TAX_RATE_DATE = tAX_RATE_DATE;
	}

	/**
	 * @return pRIVATE_FLAG
	 */
	public String getPRIVATE_FLAG() {
		return PRIVATE_FLAG;
	}

	/**
	 * @param pRIVATE_FLAG 要设置的 pRIVATE_FLAG
	 */
	public void setPRIVATE_FLAG(String pRIVATE_FLAG) {
		PRIVATE_FLAG = pRIVATE_FLAG;
	}

	/**
	 * @return lISTED_FLAG
	 */
	public String getLISTED_FLAG() {
		return LISTED_FLAG;
	}

	/**
	 * @param lISTED_FLAG 要设置的 lISTED_FLAG
	 */
	public void setLISTED_FLAG(String lISTED_FLAG) {
		LISTED_FLAG = lISTED_FLAG;
	}

	/**
	 * @return hOLDING_TYPE
	 */
	public String getHOLDING_TYPE() {
		return HOLDING_TYPE;
	}

	/**
	 * @param hOLDING_TYPE 要设置的 hOLDING_TYPE
	 */
	public void setHOLDING_TYPE(String hOLDING_TYPE) {
		HOLDING_TYPE = hOLDING_TYPE;
	}

	/**
	 * @return aCTUAL_CONTROLLER
	 */
	public String getACTUAL_CONTROLLER() {
		return ACTUAL_CONTROLLER;
	}

	/**
	 * @param aCTUAL_CONTROLLER 要设置的 aCTUAL_CONTROLLER
	 */
	public void setACTUAL_CONTROLLER(String aCTUAL_CONTROLLER) {
		ACTUAL_CONTROLLER = aCTUAL_CONTROLLER;
	}

	/**
	 * @return cOUN_CORP_FLAG
	 */
	public String getCOUN_CORP_FLAG() {
		return COUN_CORP_FLAG;
	}

	/**
	 * @param cOUN_CORP_FLAG 要设置的 cOUN_CORP_FLAG
	 */
	public void setCOUN_CORP_FLAG(String cOUN_CORP_FLAG) {
		COUN_CORP_FLAG = cOUN_CORP_FLAG;
	}

	/**
	 * @return tOPAGRI_CORP_FLAG
	 */
	public String getTOPAGRI_CORP_FLAG() {
		return TOPAGRI_CORP_FLAG;
	}

	/**
	 * @param tOPAGRI_CORP_FLAG 要设置的 tOPAGRI_CORP_FLAG
	 */
	public void setTOPAGRI_CORP_FLAG(String tOPAGRI_CORP_FLAG) {
		TOPAGRI_CORP_FLAG = tOPAGRI_CORP_FLAG;
	}

	/**
	 * @return tOPAGRI_CORP_KIND
	 */
	public String getTOPAGRI_CORP_KIND() {
		return TOPAGRI_CORP_KIND;
	}

	/**
	 * @param tOPAGRI_CORP_KIND 要设置的 tOPAGRI_CORP_KIND
	 */
	public void setTOPAGRI_CORP_KIND(String tOPAGRI_CORP_KIND) {
		TOPAGRI_CORP_KIND = tOPAGRI_CORP_KIND;
	}

	/**
	 * @return tHREE_AGR_FLAG
	 */
	public String getTHREE_AGR_FLAG() {
		return THREE_AGR_FLAG;
	}

	/**
	 * @param tHREE_AGR_FLAG 要设置的 tHREE_AGR_FLAG
	 */
	public void setTHREE_AGR_FLAG(String tHREE_AGR_FLAG) {
		THREE_AGR_FLAG = tHREE_AGR_FLAG;
	}

	/**
	 * @return nEW_TECH_CORPORNOT
	 */
	public String getNEW_TECH_CORPORNOT() {
		return NEW_TECH_CORPORNOT;
	}

	/**
	 * @param nEW_TECH_CORPORNOT 要设置的 nEW_TECH_CORPORNOT
	 */
	public void setNEW_TECH_CORPORNOT(String nEW_TECH_CORPORNOT) {
		NEW_TECH_CORPORNOT = nEW_TECH_CORPORNOT;
	}

	/**
	 * @return sPE_INDUSTRY_FLAG
	 */
	public String getSPE_INDUSTRY_FLAG() {
		return SPE_INDUSTRY_FLAG;
	}

	/**
	 * @param sPE_INDUSTRY_FLAG 要设置的 sPE_INDUSTRY_FLAG
	 */
	public void setSPE_INDUSTRY_FLAG(String sPE_INDUSTRY_FLAG) {
		SPE_INDUSTRY_FLAG = sPE_INDUSTRY_FLAG;
	}

	/**
	 * @return sPE_INDUSTRY_LIC
	 */
	public String getSPE_INDUSTRY_LIC() {
		return SPE_INDUSTRY_LIC;
	}

	/**
	 * @param sPE_INDUSTRY_LIC 要设置的 sPE_INDUSTRY_LIC
	 */
	public void setSPE_INDUSTRY_LIC(String sPE_INDUSTRY_LIC) {
		SPE_INDUSTRY_LIC = sPE_INDUSTRY_LIC;
	}

	/**
	 * @return iMEX_MANA_IND
	 */
	public String getIMEX_MANA_IND() {
		return IMEX_MANA_IND;
	}

	/**
	 * @param iMEX_MANA_IND 要设置的 iMEX_MANA_IND
	 */
	public void setIMEX_MANA_IND(String iMEX_MANA_IND) {
		IMEX_MANA_IND = iMEX_MANA_IND;
	}

	/**
	 * @return aREA_TYPE
	 */
	public String getAREA_TYPE() {
		return AREA_TYPE;
	}

	/**
	 * @param aREA_TYPE 要设置的 aREA_TYPE
	 */
	public void setAREA_TYPE(String aREA_TYPE) {
		AREA_TYPE = aREA_TYPE;
	}

	/**
	 * @return fIN_CUST_TYPE
	 */
	public String getFIN_CUST_TYPE() {
		return FIN_CUST_TYPE;
	}

	/**
	 * @param fIN_CUST_TYPE 要设置的 fIN_CUST_TYPE
	 */
	public void setFIN_CUST_TYPE(String fIN_CUST_TYPE) {
		FIN_CUST_TYPE = fIN_CUST_TYPE;
	}

	/**
	 * @return fIN_ORG_TYPE
	 */
	public String getFIN_ORG_TYPE() {
		return FIN_ORG_TYPE;
	}

	/**
	 * @param fIN_ORG_TYPE 要设置的 fIN_ORG_TYPE
	 */
	public void setFIN_ORG_TYPE(String fIN_ORG_TYPE) {
		FIN_ORG_TYPE = fIN_ORG_TYPE;
	}

	/**
	 * @return sWIFT_NO
	 */
	public String getSWIFT_NO() {
		return SWIFT_NO;
	}

	/**
	 * @param sWIFT_NO 要设置的 sWIFT_NO
	 */
	public void setSWIFT_NO(String sWIFT_NO) {
		SWIFT_NO = sWIFT_NO;
	}

	/**
	 * @return pAY_NO
	 */
	public String getPAY_NO() {
		return PAY_NO;
	}

	/**
	 * @param pAY_NO 要设置的 pAY_NO
	 */
	public void setPAY_NO(String pAY_NO) {
		PAY_NO = pAY_NO;
	}

	/**
	 * @return fIN_LIC_NO
	 */
	public String getFIN_LIC_NO() {
		return FIN_LIC_NO;
	}

	/**
	 * @param fIN_LIC_NO 要设置的 fIN_LIC_NO
	 */
	public void setFIN_LIC_NO(String fIN_LIC_NO) {
		FIN_LIC_NO = fIN_LIC_NO;
	}

	/**
	 * @return fIN_ORG_CD
	 */
	public String getFIN_ORG_CD() {
		return FIN_ORG_CD;
	}

	/**
	 * @param fIN_ORG_CD 要设置的 fIN_ORG_CD
	 */
	public void setFIN_ORG_CD(String fIN_ORG_CD) {
		FIN_ORG_CD = fIN_ORG_CD;
	}

	/**
	 * @return fIN_MANAGE_AREA
	 */
	public String getFIN_MANAGE_AREA() {
		return FIN_MANAGE_AREA;
	}

	/**
	 * @param fIN_MANAGE_AREA 要设置的 fIN_MANAGE_AREA
	 */
	public void setFIN_MANAGE_AREA(String fIN_MANAGE_AREA) {
		FIN_MANAGE_AREA = fIN_MANAGE_AREA;
	}

	/**
	 * @return fIN_DEPT_FLAG
	 */
	public String getFIN_DEPT_FLAG() {
		return FIN_DEPT_FLAG;
	}

	/**
	 * @param fIN_DEPT_FLAG 要设置的 fIN_DEPT_FLAG
	 */
	public void setFIN_DEPT_FLAG(String fIN_DEPT_FLAG) {
		FIN_DEPT_FLAG = fIN_DEPT_FLAG;
	}

	/**
	 * @return dEPT_FIN_FLAG
	 */
	public String getDEPT_FIN_FLAG() {
		return DEPT_FIN_FLAG;
	}

	/**
	 * @param dEPT_FIN_FLAG 要设置的 dEPT_FIN_FLAG
	 */
	public void setDEPT_FIN_FLAG(String dEPT_FIN_FLAG) {
		DEPT_FIN_FLAG = dEPT_FIN_FLAG;
	}

	/**
	 * @return cUST_FORE_EXCH_ATTR
	 */
	public String getCUST_FORE_EXCH_ATTR() {
		return CUST_FORE_EXCH_ATTR;
	}

	/**
	 * @param cUST_FORE_EXCH_ATTR 要设置的 cUST_FORE_EXCH_ATTR
	 */
	public void setCUST_FORE_EXCH_ATTR(String cUST_FORE_EXCH_ATTR) {
		CUST_FORE_EXCH_ATTR = cUST_FORE_EXCH_ATTR;
	}

	/**
	 * @return nRA_FLAG
	 */
	public String getNRA_FLAG() {
		return NRA_FLAG;
	}

	/**
	 * @param nRA_FLAG 要设置的 nRA_FLAG
	 */
	public void setNRA_FLAG(String nRA_FLAG) {
		NRA_FLAG = nRA_FLAG;
	}

	/**
	 * @return sPE_INST_CODE
	 */
	public String getSPE_INST_CODE() {
		return SPE_INST_CODE;
	}

	/**
	 * @param sPE_INST_CODE 要设置的 sPE_INST_CODE
	 */
	public void setSPE_INST_CODE(String sPE_INST_CODE) {
		SPE_INST_CODE = sPE_INST_CODE;
	}

	/**
	 * @return fORE_CUST_TYPE
	 */
	public String getFORE_CUST_TYPE() {
		return FORE_CUST_TYPE;
	}

	/**
	 * @param fORE_CUST_TYPE 要设置的 fORE_CUST_TYPE
	 */
	public void setFORE_CUST_TYPE(String fORE_CUST_TYPE) {
		FORE_CUST_TYPE = fORE_CUST_TYPE;
	}

	/**
	 * @return fORE_EXCH_LIC_NO
	 */
	public String getFORE_EXCH_LIC_NO() {
		return FORE_EXCH_LIC_NO;
	}

	/**
	 * @param fORE_EXCH_LIC_NO 要设置的 fORE_EXCH_LIC_NO
	 */
	public void setFORE_EXCH_LIC_NO(String fORE_EXCH_LIC_NO) {
		FORE_EXCH_LIC_NO = fORE_EXCH_LIC_NO;
	}

	/**
	 * @return bUSI_SITE_CODE
	 */
	public String getBUSI_SITE_CODE() {
		return BUSI_SITE_CODE;
	}

	/**
	 * @param bUSI_SITE_CODE 要设置的 bUSI_SITE_CODE
	 */
	public void setBUSI_SITE_CODE(String bUSI_SITE_CODE) {
		BUSI_SITE_CODE = bUSI_SITE_CODE;
	}

	/**
	 * @return rES_COUNTRY
	 */
	public String getRES_COUNTRY() {
		return RES_COUNTRY;
	}

	/**
	 * @param rES_COUNTRY 要设置的 rES_COUNTRY
	 */
	public void setRES_COUNTRY(String rES_COUNTRY) {
		RES_COUNTRY = rES_COUNTRY;
	}

	/**
	 * @return fORE_BASIC_ACC_BANK
	 */
	public String getFORE_BASIC_ACC_BANK() {
		return FORE_BASIC_ACC_BANK;
	}

	/**
	 * @param fORE_BASIC_ACC_BANK 要设置的 fORE_BASIC_ACC_BANK
	 */
	public void setFORE_BASIC_ACC_BANK(String fORE_BASIC_ACC_BANK) {
		FORE_BASIC_ACC_BANK = fORE_BASIC_ACC_BANK;
	}

	/**
	 * @return fORE_BASIC_ACC
	 */
	public String getFORE_BASIC_ACC() {
		return FORE_BASIC_ACC;
	}

	/**
	 * @param fORE_BASIC_ACC 要设置的 fORE_BASIC_ACC
	 */
	public void setFORE_BASIC_ACC(String fORE_BASIC_ACC) {
		FORE_BASIC_ACC = fORE_BASIC_ACC;
	}

	/**
	 * @return fORE_INV_COUNTRY
	 */
	public String getFORE_INV_COUNTRY() {
		return FORE_INV_COUNTRY;
	}

	/**
	 * @param fORE_INV_COUNTRY 要设置的 fORE_INV_COUNTRY
	 */
	public void setFORE_INV_COUNTRY(String fORE_INV_COUNTRY) {
		FORE_INV_COUNTRY = fORE_INV_COUNTRY;
	}

	/**
	 * @return fORE_ECON_TRDE_FLAG
	 */
	public String getFORE_ECON_TRDE_FLAG() {
		return FORE_ECON_TRDE_FLAG;
	}

	/**
	 * @param fORE_ECON_TRDE_FLAG 要设置的 fORE_ECON_TRDE_FLAG
	 */
	public void setFORE_ECON_TRDE_FLAG(String fORE_ECON_TRDE_FLAG) {
		FORE_ECON_TRDE_FLAG = fORE_ECON_TRDE_FLAG;
	}

	/**
	 * @return sPE_ECON_INST_FLAG
	 */
	public String getSPE_ECON_INST_FLAG() {
		return SPE_ECON_INST_FLAG;
	}

	/**
	 * @param sPE_ECON_INST_FLAG 要设置的 sPE_ECON_INST_FLAG
	 */
	public void setSPE_ECON_INST_FLAG(String sPE_ECON_INST_FLAG) {
		SPE_ECON_INST_FLAG = sPE_ECON_INST_FLAG;
	}

	/**
	 * @return sPE_ECON_INST_TYPE
	 */
	public String getSPE_ECON_INST_TYPE() {
		return SPE_ECON_INST_TYPE;
	}

	/**
	 * @param sPE_ECON_INST_TYPE 要设置的 sPE_ECON_INST_TYPE
	 */
	public void setSPE_ECON_INST_TYPE(String sPE_ECON_INST_TYPE) {
		SPE_ECON_INST_TYPE = sPE_ECON_INST_TYPE;
	}

	/**
	 * @return pAY_LIS_FLAG
	 */
	public String getPAY_LIS_FLAG() {
		return PAY_LIS_FLAG;
	}

	/**
	 * @param pAY_LIS_FLAG 要设置的 pAY_LIS_FLAG
	 */
	public void setPAY_LIS_FLAG(String pAY_LIS_FLAG) {
		PAY_LIS_FLAG = pAY_LIS_FLAG;
	}

	/**
	 * @return fORE_SAFE_NO
	 */
	public String getFORE_SAFE_NO() {
		return FORE_SAFE_NO;
	}

	/**
	 * @param fORE_SAFE_NO 要设置的 fORE_SAFE_NO
	 */
	public void setFORE_SAFE_NO(String fORE_SAFE_NO) {
		FORE_SAFE_NO = fORE_SAFE_NO;
	}

	/**
	 * @return fORE_INDUSTRY_TYPE
	 */
	public String getFORE_INDUSTRY_TYPE() {
		return FORE_INDUSTRY_TYPE;
	}

	/**
	 * @param fORE_INDUSTRY_TYPE 要设置的 fORE_INDUSTRY_TYPE
	 */
	public void setFORE_INDUSTRY_TYPE(String fORE_INDUSTRY_TYPE) {
		FORE_INDUSTRY_TYPE = fORE_INDUSTRY_TYPE;
	}

	/**
	 * @return fORE_ECON_TYPE
	 */
	public String getFORE_ECON_TYPE() {
		return FORE_ECON_TYPE;
	}

	/**
	 * @param fORE_ECON_TYPE 要设置的 fORE_ECON_TYPE
	 */
	public void setFORE_ECON_TYPE(String fORE_ECON_TYPE) {
		FORE_ECON_TYPE = fORE_ECON_TYPE;
	}

	/**
	 * @return fORE_FIRST_NAME
	 */
	public String getFORE_FIRST_NAME() {
		return FORE_FIRST_NAME;
	}

	/**
	 * @param fORE_FIRST_NAME 要设置的 fORE_FIRST_NAME
	 */
	public void setFORE_FIRST_NAME(String fORE_FIRST_NAME) {
		FORE_FIRST_NAME = fORE_FIRST_NAME;
	}

	/**
	 * @return fORE_SECOND_NAME
	 */
	public String getFORE_SECOND_NAME() {
		return FORE_SECOND_NAME;
	}

	/**
	 * @param fORE_SECOND_NAME 要设置的 fORE_SECOND_NAME
	 */
	public void setFORE_SECOND_NAME(String fORE_SECOND_NAME) {
		FORE_SECOND_NAME = fORE_SECOND_NAME;
	}

	/**
	 * @return bANK_REL_FLAG
	 */
	public String getBANK_REL_FLAG() {
		return BANK_REL_FLAG;
	}

	/**
	 * @param bANK_REL_FLAG 要设置的 bANK_REL_FLAG
	 */
	public void setBANK_REL_FLAG(String bANK_REL_FLAG) {
		BANK_REL_FLAG = bANK_REL_FLAG;
	}

	/**
	 * @return sHAREHOLDER_FLAG
	 */
	public String getSHAREHOLDER_FLAG() {
		return SHAREHOLDER_FLAG;
	}

	/**
	 * @param sHAREHOLDER_FLAG 要设置的 sHAREHOLDER_FLAG
	 */
	public void setSHAREHOLDER_FLAG(String sHAREHOLDER_FLAG) {
		SHAREHOLDER_FLAG = sHAREHOLDER_FLAG;
	}

	/**
	 * @return sTOCK_NUM
	 */
	public String getSTOCK_NUM() {
		return STOCK_NUM;
	}

	/**
	 * @param sTOCK_NUM 要设置的 sTOCK_NUM
	 */
	public void setSTOCK_NUM(String sTOCK_NUM) {
		STOCK_NUM = sTOCK_NUM;
	}

	/**
	 * @return sTOCK_ACCT
	 */
	public String getSTOCK_ACCT() {
		return STOCK_ACCT;
	}

	/**
	 * @param sTOCK_ACCT 要设置的 sTOCK_ACCT
	 */
	public void setSTOCK_ACCT(String sTOCK_ACCT) {
		STOCK_ACCT = sTOCK_ACCT;
	}

	/**
	 * @return rEL_UNIT_FLAG
	 */
	public String getREL_UNIT_FLAG() {
		return REL_UNIT_FLAG;
	}

	/**
	 * @param rEL_UNIT_FLAG 要设置的 rEL_UNIT_FLAG
	 */
	public void setREL_UNIT_FLAG(String rEL_UNIT_FLAG) {
		REL_UNIT_FLAG = rEL_UNIT_FLAG;
	}

	/**
	 * @return cUST_EVAL_LEVEL
	 */
	public String getCUST_EVAL_LEVEL() {
		return CUST_EVAL_LEVEL;
	}

	/**
	 * @param cUST_EVAL_LEVEL 要设置的 cUST_EVAL_LEVEL
	 */
	public void setCUST_EVAL_LEVEL(String cUST_EVAL_LEVEL) {
		CUST_EVAL_LEVEL = cUST_EVAL_LEVEL;
	}

	/**
	 * @return bANK_CREDIT_LEVEL
	 */
	public String getBANK_CREDIT_LEVEL() {
		return BANK_CREDIT_LEVEL;
	}

	/**
	 * @param bANK_CREDIT_LEVEL 要设置的 bANK_CREDIT_LEVEL
	 */
	public void setBANK_CREDIT_LEVEL(String bANK_CREDIT_LEVEL) {
		BANK_CREDIT_LEVEL = bANK_CREDIT_LEVEL;
	}

	/**
	 * @return bANK_EVALUATE_DATE
	 */
	public String getBANK_EVALUATE_DATE() {
		return BANK_EVALUATE_DATE;
	}

	/**
	 * @param bANK_EVALUATE_DATE 要设置的 bANK_EVALUATE_DATE
	 */
	public void setBANK_EVALUATE_DATE(String bANK_EVALUATE_DATE) {
		BANK_EVALUATE_DATE = bANK_EVALUATE_DATE;
	}

	/**
	 * @return bANK_ORG_NAME
	 */
	public String getBANK_ORG_NAME() {
		return BANK_ORG_NAME;
	}

	/**
	 * @param bANK_ORG_NAME 要设置的 bANK_ORG_NAME
	 */
	public void setBANK_ORG_NAME(String bANK_ORG_NAME) {
		BANK_ORG_NAME = bANK_ORG_NAME;
	}

	/**
	 * @return bANK_ORG_RESULT
	 */
	public String getBANK_ORG_RESULT() {
		return BANK_ORG_RESULT;
	}

	/**
	 * @param bANK_ORG_RESULT 要设置的 bANK_ORG_RESULT
	 */
	public void setBANK_ORG_RESULT(String bANK_ORG_RESULT) {
		BANK_ORG_RESULT = bANK_ORG_RESULT;
	}

	/**
	 * @return oTHER_CREDIT_LEVEL
	 */
	public String getOTHER_CREDIT_LEVEL() {
		return OTHER_CREDIT_LEVEL;
	}

	/**
	 * @param oTHER_CREDIT_LEVEL 要设置的 oTHER_CREDIT_LEVEL
	 */
	public void setOTHER_CREDIT_LEVEL(String oTHER_CREDIT_LEVEL) {
		OTHER_CREDIT_LEVEL = oTHER_CREDIT_LEVEL;
	}

	/**
	 * @return oTHER_EVALUATE_DATE
	 */
	public String getOTHER_EVALUATE_DATE() {
		return OTHER_EVALUATE_DATE;
	}

	/**
	 * @param oTHER_EVALUATE_DATE 要设置的 oTHER_EVALUATE_DATE
	 */
	public void setOTHER_EVALUATE_DATE(String oTHER_EVALUATE_DATE) {
		OTHER_EVALUATE_DATE = oTHER_EVALUATE_DATE;
	}

	/**
	 * @return oTHER_ORG_NAME
	 */
	public String getOTHER_ORG_NAME() {
		return OTHER_ORG_NAME;
	}

	/**
	 * @param oTHER_ORG_NAME 要设置的 oTHER_ORG_NAME
	 */
	public void setOTHER_ORG_NAME(String oTHER_ORG_NAME) {
		OTHER_ORG_NAME = oTHER_ORG_NAME;
	}

	/**
	 * @return oTHER_ORG_RESULT
	 */
	public String getOTHER_ORG_RESULT() {
		return OTHER_ORG_RESULT;
	}

	/**
	 * @param oTHER_ORG_RESULT 要设置的 oTHER_ORG_RESULT
	 */
	public void setOTHER_ORG_RESULT(String oTHER_ORG_RESULT) {
		OTHER_ORG_RESULT = oTHER_ORG_RESULT;
	}

	/**
	 * @return eVALUATE_LEVEL
	 */
	public String getEVALUATE_LEVEL() {
		return EVALUATE_LEVEL;
	}

	/**
	 * @param eVALUATE_LEVEL 要设置的 eVALUATE_LEVEL
	 */
	public void setEVALUATE_LEVEL(String eVALUATE_LEVEL) {
		EVALUATE_LEVEL = eVALUATE_LEVEL;
	}

	/**
	 * @return cUAT_RISK_LEVEL
	 */
	public String getCUAT_RISK_LEVEL() {
		return CUAT_RISK_LEVEL;
	}

	/**
	 * @param cUAT_RISK_LEVEL 要设置的 cUAT_RISK_LEVEL
	 */
	public void setCUAT_RISK_LEVEL(String cUAT_RISK_LEVEL) {
		CUAT_RISK_LEVEL = cUAT_RISK_LEVEL;
	}

	/**
	 * @return cUST_LEVEL
	 */
	public String getCUST_LEVEL() {
		return CUST_LEVEL;
	}

	/**
	 * @param cUST_LEVEL 要设置的 cUST_LEVEL
	 */
	public void setCUST_LEVEL(String cUST_LEVEL) {
		CUST_LEVEL = cUST_LEVEL;
	}

	/**
	 * @return iDE_CHECK_FLAG
	 */
	public String getIDE_CHECK_FLAG() {
		return IDE_CHECK_FLAG;
	}

	/**
	 * @param iDE_CHECK_FLAG 要设置的 iDE_CHECK_FLAG
	 */
	public void setIDE_CHECK_FLAG(String iDE_CHECK_FLAG) {
		IDE_CHECK_FLAG = iDE_CHECK_FLAG;
	}

	/**
	 * @return cUST_MANAGER_NO
	 */
	public String getCUST_MANAGER_NO() {
		return CUST_MANAGER_NO;
	}

	/**
	 * @param cUST_MANAGER_NO 要设置的 cUST_MANAGER_NO
	 */
	public void setCUST_MANAGER_NO(String cUST_MANAGER_NO) {
		CUST_MANAGER_NO = cUST_MANAGER_NO;
	}

	/**
	 * @return cUST_MNG_NAME
	 */
	public String getCUST_MNG_NAME() {
		return CUST_MNG_NAME;
	}

	/**
	 * @param cUST_MNG_NAME 要设置的 cUST_MNG_NAME
	 */
	public void setCUST_MNG_NAME(String cUST_MNG_NAME) {
		CUST_MNG_NAME = cUST_MNG_NAME;
	}

	/**
	 * @return oWN_ORG
	 */
	public String getOWN_ORG() {
		return OWN_ORG;
	}

	/**
	 * @param oWN_ORG 要设置的 oWN_ORG
	 */
	public void setOWN_ORG(String oWN_ORG) {
		OWN_ORG = oWN_ORG;
	}

	/**
	 * @return oPEN_ORG
	 */
	public String getOPEN_ORG() {
		return OPEN_ORG;
	}

	/**
	 * @param oPEN_ORG 要设置的 oPEN_ORG
	 */
	public void setOPEN_ORG(String oPEN_ORG) {
		OPEN_ORG = oPEN_ORG;
	}

	/**
	 * @return oPEN_TELLER
	 */
	public String getOPEN_TELLER() {
		return OPEN_TELLER;
	}

	/**
	 * @param oPEN_TELLER 要设置的 oPEN_TELLER
	 */
	public void setOPEN_TELLER(String oPEN_TELLER) {
		OPEN_TELLER = oPEN_TELLER;
	}

	/**
	 * @return oPEN_DATE
	 */
	public String getOPEN_DATE() {
		return OPEN_DATE;
	}

	/**
	 * @param oPEN_DATE 要设置的 oPEN_DATE
	 */
	public void setOPEN_DATE(String oPEN_DATE) {
		OPEN_DATE = oPEN_DATE;
	}

	/**
	 * @return rEAL_FULL_FLAG
	 */
	public String getREAL_FULL_FLAG() {
		return REAL_FULL_FLAG;
	}

	/**
	 * @param rEAL_FULL_FLAG 要设置的 rEAL_FULL_FLAG
	 */
	public void setREAL_FULL_FLAG(String rEAL_FULL_FLAG) {
		REAL_FULL_FLAG = rEAL_FULL_FLAG;
	}

	/**
	 * @return cUST_STATUS
	 */
	public String getCUST_STATUS() {
		return CUST_STATUS;
	}

	/**
	 * @param cUST_STATUS 要设置的 cUST_STATUS
	 */
	public void setCUST_STATUS(String cUST_STATUS) {
		CUST_STATUS = cUST_STATUS;
	}

	/**
	 * @return lAST_SYSTEM_ID
	 */
	public String getLAST_SYSTEM_ID() {
		return LAST_SYSTEM_ID;
	}

	/**
	 * @param lAST_SYSTEM_ID 要设置的 lAST_SYSTEM_ID
	 */
	public void setLAST_SYSTEM_ID(String lAST_SYSTEM_ID) {
		LAST_SYSTEM_ID = lAST_SYSTEM_ID;
	}

	/**
	 * @return lAST_UPDATED_TS
	 */
	public String getLAST_UPDATED_TS() {
		return LAST_UPDATED_TS;
	}

	/**
	 * @param lAST_UPDATED_TS 要设置的 lAST_UPDATED_TS
	 */
	public void setLAST_UPDATED_TS(String lAST_UPDATED_TS) {
		LAST_UPDATED_TS = lAST_UPDATED_TS;
	}
	
}
