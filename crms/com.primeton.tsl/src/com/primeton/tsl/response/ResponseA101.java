package com.primeton.tsl.response;

import java.sql.Date;

public class ResponseA101 {
	/**
	 * @return fIRST_NO
	 */
	public int getFIRST_NO() {
		return FIRST_NO;
	}
	/**
	 * @param fIRST_NO 要设置的 fIRST_NO
	 */
	public void setFIRST_NO(int fIRST_NO) {
		FIRST_NO = fIRST_NO;
	}
	/**
	 * @return rESULT_SIZE
	 */
	public int getRESULT_SIZE() {
		return RESULT_SIZE;
	}
	/**
	 * @param rESULT_SIZE 要设置的 rESULT_SIZE
	 */
	public void setRESULT_SIZE(int rESULT_SIZE) {
		RESULT_SIZE = rESULT_SIZE;
	}
	/**
	 * @return aLL_RESULT_SIZE
	 */
	public int getALL_RESULT_SIZE() {
		return ALL_RESULT_SIZE;
	}
	/**
	 * @param aLL_RESULT_SIZE 要设置的 aLL_RESULT_SIZE
	 */
	public void setALL_RESULT_SIZE(int aLL_RESULT_SIZE) {
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
	 * @return oPEN_DATE
	 */
	public Date getOPEN_DATE() {
		return OPEN_DATE;
	}
	/**
	 * @param oPEN_DATE 要设置的 oPEN_DATE
	 */
	public void setOPEN_DATE(Date oPEN_DATE) {
		OPEN_DATE = oPEN_DATE;
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
	public int FIRST_NO;// 起始笔数
	public int RESULT_SIZE;// 查询笔数
	public int ALL_RESULT_SIZE;// 总笔数
	public String ECIF_CUST_NO;// ECIF客户编号
	public String PARTY_NAME;// 客户名称
	public String CERT_TYPE;// 证件类型
	public String CERT_NO;// 证件号码
	public String OPEN_ORG;// 开户机构
	public Date OPEN_DATE;// 开户日期
	public String OPEN_TELLER;// 开户柜员
}
