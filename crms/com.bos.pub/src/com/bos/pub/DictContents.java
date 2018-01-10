package com.bos.pub;

import com.bos.utp.tools.DBUtil;

public class DictContents {
	/** 承兑垫款业务别 */
	public static final String SUBJECT_CD = "131101";
	/** 绵阳商行法人代码 */
	public static final String ORG_MCCB = "9999";
	/** 北川富民法人代码 */
	public static final String ORG_BCFM = "5099";
	/** 平武富民法人代码 */
	public static final String ORG_PWFM = "4099";

	/** crms数据库连接名 */
	@Deprecated
	// 请使用DBUtil。DB_NAME_DEF
	public static final String DB_NAME_CRMS = DBUtil.DB_NAME_DEF;

	// 核算接口成功响应码
	public static final String APLUS_RECODE = "00000";
	// 交易发起渠道
	public static final String APLUS_ORIG_FROM = "11000";
	// 交易接收渠道（A+外围系统）
	public static final String APLUS_TRAN_FROM = "47";

	// 核心接口成功响应码
	public static final String CORE_SUCCESS = "AAAAAAA";

	/** 产品代码--银行承兑汇票贴现 */
	public static final String PRODUCT_CD_01006001 = "01006001";
	/** 产品代码--银行承兑汇票贴现（北川富民村镇银行） */
	public static final String PRODUCT_CD_01006010 = "01006010";
	/** 产品代码--商业承兑汇票贴现 */
	public static final String PRODUCT_CD_01006002 = "01006002";
	/** 产品代码--融资性保函 */
	public static final String PRODUCT_CD_01009001 = "01009001";
	/** 产品代码--银行承兑汇票 */
	public static final String PRODUCT_CD_01008001 = "01008001";
	/** 产品代码--银行承兑汇票 （北川富民） */
	public static final String PRODUCT_CD_01008010 = "01008010";
	public static final String PRODUCT_CD_01008002 = "01008002";
	/** 产品代码--非融资性保函 */
	public static final String PRODUCT_CD_01009002 = "01009002";
	/** 产品代码--非融资性保函(北川富民) */
	public static final String PRODUCT_CD_01009010 = "01009010";
	/** 产品代码--富民微小贷 */
	public static final String PRODUCT_CD_02001058 = "02001058";
	/** 产品代码--进口代付 */
	public static String PRODUCT_CD_01007009 = "01007009";
	/** 产品代码--国际福费廷 */
	public static String PRODUCT_CD_01007012 = "01007012";
	/** 产品代码--国际保函 */
	public static String PRODUCT_CD_01007014 = "01007014";
	/** 产品代码--国际信用证开证 */
	public static String PRODUCT_CD_01007013 = "01007013";
	/** 产品代码--提货担保 */
	public static String PRODUCT_CD_01007010 = "01007010";
	/** 贷款出账状态 */
	public static final String XD_SXCD8003 = "XD_SXCD8003";
	/** 贷款出账状态--未提交 */
	public static final String XD_SXCD8003_01 = "01";
	/** 贷款出账状态 --审批中 */
	public static final String XD_SXCD8003_02 = "02";
	/** 贷款出账状态 --已生效 */
	public static final String XD_SXCD8003_03 = "03";
	/** 贷款出账状态 --已失效 */
	public static final String XD_SXCD8003_04 = "04";
	/** 贷款出账状态 --到期失效 */
	public static final String XD_SXCD8003_05 = "05";
	/** 贷款出账状态 --已删除 */
	public static final String XD_SXCD8003_06 = "06";
	/** 贷款出账状态 --已冻结 */
	public static final String XD_SXCD8003_07 = "07";
	/** 贷款出账状态 --临时失效 */
	public static final String XD_SXCD8003_08 = "08";
	/** 贷款出账状态 --已完成 */
	public static final String XD_SXCD8003_09 = "09";

	/** 借据状态 */
	public static final String XD_SXYW0226 = "XD_SXYW0226";
	/** 借据状态 --未放款 */
	public static final String XD_SXYW0226_01 = "01";
	/** 借据状态 --正常 */
	public static final String XD_SXYW0226_02 = "02";
	/** 借据状态 --逾期/垫款 */
	public static final String XD_SXYW0226_03 = "03";
	/** 借据状态 --结清 */
	public static final String XD_SXYW0226_04 = "04";
	/** 借据状态 --已删除 */
	public static final String XD_SXYW0226_06 = "06";
	/** 借据状态 --已核销 */
	public static final String XD_SXYW0226_07 = "07";

}
