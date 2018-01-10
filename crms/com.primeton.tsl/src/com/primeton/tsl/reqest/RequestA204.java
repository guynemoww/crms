/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户上市信息查询请求报文			

 */
public class RequestA204 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4006220665584562534L;
	private String FIRST_NO;//起始笔数 必填 起始行号，用于多笔查询，默认从1开始
	private String RESULT_SIZE;//查询笔数
	private String ECIF_CUST_NO;//客户编号 必填
	private String STOCK_CODE;//股票代码
	
	/**
	 * 
	 */
	public RequestA204() {
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
	
}
