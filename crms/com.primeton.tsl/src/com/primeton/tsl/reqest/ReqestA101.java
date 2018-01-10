package com.primeton.tsl.reqest;


public class ReqestA101 {
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
	public int FIRST_NO;// 起始笔数
	public int RESULT_SIZE;// 查询笔数
	public String RESOLVE_TYPE;// 查询方式
	public String PARTY_NAME;// 客户名称
	public String CERT_TYPE;// 证件类型
	public String CERT_NO;// 证件号码
}
