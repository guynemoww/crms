/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户上市信息查询响应报文			

 */
public class ResponseA204 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5671930858969496048L;
	private String FIRST_NO;//起始笔数
	private String RESULT_SIZE;//查询笔数
	private String ALL_RESULT_SIZE;//总笔数
	private String ECIF_CUST_NO;//客户编号
	private String IPO_ID;//上市信息ID
	private String SERIAL_NO;//股票情况记录编号
	private String STOCK_KIND;//股票分类
	private String IPO_DATE;//上市日期
	private String IPO_PLACE;//上市地点
	private String BOURSE_CODE;//交易所代码
	private String BOURSE_NAME;//交易所名称
	private String STOCK_CODE;//股票代码
	private String STOCK_NAME;//股票名称
	private String STOCK_TYPE;//股票种类
	private String CUR_STOCK_NUM;//当前股本总量
	private String FLOW_STOCK_NUM;//流通股数
	private String ISSUE_STOCK_PRICE;//发行价格
	private String EVAL_VALUE;//股票评估价
	private String ISSUE_DATE;//发行日期
	private String ISSUE_KIND;//发行方式
	private String ISSUE_STOCK_NUM;//发行数量
	private String ISSUE_ACCO_TYPE;//发行依据
	private String UNDER_WRITER;//承销商
	private String UNDER_KIND;//承销方式
	private String REP_DATE;//市场表现报告日期
	private String REMARK;//备注
	
	/**
	 * 
	 */
	public ResponseA204() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @return fIRST_NO
	 */
	public String getFIRST_NO() {
		return FIRST_NO;
	}

	/**
	 * @param fIRST_NO 要设置的 fIRST_NO
	 */
	public void setFIRST_NO(String fIRST_NO) {
		FIRST_NO = fIRST_NO;
	}

	/**
	 * @return rESULT_SIZE
	 */
	public String getRESULT_SIZE() {
		return RESULT_SIZE;
	}

	/**
	 * @param rESULT_SIZE 要设置的 rESULT_SIZE
	 */
	public void setRESULT_SIZE(String rESULT_SIZE) {
		RESULT_SIZE = rESULT_SIZE;
	}

	/**
	 * @return aLL_RESULT_SIZE
	 */
	public String getALL_RESULT_SIZE() {
		return ALL_RESULT_SIZE;
	}

	/**
	 * @param aLL_RESULT_SIZE 要设置的 aLL_RESULT_SIZE
	 */
	public void setALL_RESULT_SIZE(String aLL_RESULT_SIZE) {
		ALL_RESULT_SIZE = aLL_RESULT_SIZE;
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
	 * @return iPO_ID
	 */
	public String getIPO_ID() {
		return IPO_ID;
	}

	/**
	 * @param iPO_ID 要设置的 iPO_ID
	 */
	public void setIPO_ID(String iPO_ID) {
		IPO_ID = iPO_ID;
	}

	/**
	 * @return sERIAL_NO
	 */
	public String getSERIAL_NO() {
		return SERIAL_NO;
	}

	/**
	 * @param sERIAL_NO 要设置的 sERIAL_NO
	 */
	public void setSERIAL_NO(String sERIAL_NO) {
		SERIAL_NO = sERIAL_NO;
	}

	/**
	 * @return sTOCK_KIND
	 */
	public String getSTOCK_KIND() {
		return STOCK_KIND;
	}

	/**
	 * @param sTOCK_KIND 要设置的 sTOCK_KIND
	 */
	public void setSTOCK_KIND(String sTOCK_KIND) {
		STOCK_KIND = sTOCK_KIND;
	}

	/**
	 * @return iPO_DATE
	 */
	public String getIPO_DATE() {
		return IPO_DATE;
	}

	/**
	 * @param iPO_DATE 要设置的 iPO_DATE
	 */
	public void setIPO_DATE(String iPO_DATE) {
		IPO_DATE = iPO_DATE;
	}

	/**
	 * @return iPO_PLACE
	 */
	public String getIPO_PLACE() {
		return IPO_PLACE;
	}

	/**
	 * @param iPO_PLACE 要设置的 iPO_PLACE
	 */
	public void setIPO_PLACE(String iPO_PLACE) {
		IPO_PLACE = iPO_PLACE;
	}

	/**
	 * @return bOURSE_CODE
	 */
	public String getBOURSE_CODE() {
		return BOURSE_CODE;
	}

	/**
	 * @param bOURSE_CODE 要设置的 bOURSE_CODE
	 */
	public void setBOURSE_CODE(String bOURSE_CODE) {
		BOURSE_CODE = bOURSE_CODE;
	}

	/**
	 * @return bOURSE_NAME
	 */
	public String getBOURSE_NAME() {
		return BOURSE_NAME;
	}

	/**
	 * @param bOURSE_NAME 要设置的 bOURSE_NAME
	 */
	public void setBOURSE_NAME(String bOURSE_NAME) {
		BOURSE_NAME = bOURSE_NAME;
	}

	/**
	 * @return sTOCK_CODE
	 */
	public String getSTOCK_CODE() {
		return STOCK_CODE;
	}

	/**
	 * @param sTOCK_CODE 要设置的 sTOCK_CODE
	 */
	public void setSTOCK_CODE(String sTOCK_CODE) {
		STOCK_CODE = sTOCK_CODE;
	}

	/**
	 * @return sTOCK_NAME
	 */
	public String getSTOCK_NAME() {
		return STOCK_NAME;
	}

	/**
	 * @param sTOCK_NAME 要设置的 sTOCK_NAME
	 */
	public void setSTOCK_NAME(String sTOCK_NAME) {
		STOCK_NAME = sTOCK_NAME;
	}

	/**
	 * @return sTOCK_TYPE
	 */
	public String getSTOCK_TYPE() {
		return STOCK_TYPE;
	}

	/**
	 * @param sTOCK_TYPE 要设置的 sTOCK_TYPE
	 */
	public void setSTOCK_TYPE(String sTOCK_TYPE) {
		STOCK_TYPE = sTOCK_TYPE;
	}

	/**
	 * @return cUR_STOCK_NUM
	 */
	public String getCUR_STOCK_NUM() {
		return CUR_STOCK_NUM;
	}

	/**
	 * @param cUR_STOCK_NUM 要设置的 cUR_STOCK_NUM
	 */
	public void setCUR_STOCK_NUM(String cUR_STOCK_NUM) {
		CUR_STOCK_NUM = cUR_STOCK_NUM;
	}

	/**
	 * @return fLOW_STOCK_NUM
	 */
	public String getFLOW_STOCK_NUM() {
		return FLOW_STOCK_NUM;
	}

	/**
	 * @param fLOW_STOCK_NUM 要设置的 fLOW_STOCK_NUM
	 */
	public void setFLOW_STOCK_NUM(String fLOW_STOCK_NUM) {
		FLOW_STOCK_NUM = fLOW_STOCK_NUM;
	}

	/**
	 * @return iSSUE_STOCK_PRICE
	 */
	public String getISSUE_STOCK_PRICE() {
		return ISSUE_STOCK_PRICE;
	}

	/**
	 * @param iSSUE_STOCK_PRICE 要设置的 iSSUE_STOCK_PRICE
	 */
	public void setISSUE_STOCK_PRICE(String iSSUE_STOCK_PRICE) {
		ISSUE_STOCK_PRICE = iSSUE_STOCK_PRICE;
	}

	/**
	 * @return eVAL_VALUE
	 */
	public String getEVAL_VALUE() {
		return EVAL_VALUE;
	}

	/**
	 * @param eVAL_VALUE 要设置的 eVAL_VALUE
	 */
	public void setEVAL_VALUE(String eVAL_VALUE) {
		EVAL_VALUE = eVAL_VALUE;
	}

	/**
	 * @return iSSUE_DATE
	 */
	public String getISSUE_DATE() {
		return ISSUE_DATE;
	}

	/**
	 * @param iSSUE_DATE 要设置的 iSSUE_DATE
	 */
	public void setISSUE_DATE(String iSSUE_DATE) {
		ISSUE_DATE = iSSUE_DATE;
	}

	/**
	 * @return iSSUE_KIND
	 */
	public String getISSUE_KIND() {
		return ISSUE_KIND;
	}

	/**
	 * @param iSSUE_KIND 要设置的 iSSUE_KIND
	 */
	public void setISSUE_KIND(String iSSUE_KIND) {
		ISSUE_KIND = iSSUE_KIND;
	}

	/**
	 * @return iSSUE_STOCK_NUM
	 */
	public String getISSUE_STOCK_NUM() {
		return ISSUE_STOCK_NUM;
	}

	/**
	 * @param iSSUE_STOCK_NUM 要设置的 iSSUE_STOCK_NUM
	 */
	public void setISSUE_STOCK_NUM(String iSSUE_STOCK_NUM) {
		ISSUE_STOCK_NUM = iSSUE_STOCK_NUM;
	}

	/**
	 * @return iSSUE_ACCO_TYPE
	 */
	public String getISSUE_ACCO_TYPE() {
		return ISSUE_ACCO_TYPE;
	}

	/**
	 * @param iSSUE_ACCO_TYPE 要设置的 iSSUE_ACCO_TYPE
	 */
	public void setISSUE_ACCO_TYPE(String iSSUE_ACCO_TYPE) {
		ISSUE_ACCO_TYPE = iSSUE_ACCO_TYPE;
	}

	/**
	 * @return uNDER_WRITER
	 */
	public String getUNDER_WRITER() {
		return UNDER_WRITER;
	}

	/**
	 * @param uNDER_WRITER 要设置的 uNDER_WRITER
	 */
	public void setUNDER_WRITER(String uNDER_WRITER) {
		UNDER_WRITER = uNDER_WRITER;
	}

	/**
	 * @return uNDER_KIND
	 */
	public String getUNDER_KIND() {
		return UNDER_KIND;
	}

	/**
	 * @param uNDER_KIND 要设置的 uNDER_KIND
	 */
	public void setUNDER_KIND(String uNDER_KIND) {
		UNDER_KIND = uNDER_KIND;
	}

	/**
	 * @return rEP_DATE
	 */
	public String getREP_DATE() {
		return REP_DATE;
	}

	/**
	 * @param rEP_DATE 要设置的 rEP_DATE
	 */
	public void setREP_DATE(String rEP_DATE) {
		REP_DATE = rEP_DATE;
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
