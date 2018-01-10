/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *客户地址信息查询请求报文		

 */
public class RequestA011 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7492012747573104360L;
	private String RESOLVE_TYPE;//识别方式| 客户识别方式：1、使用客户编号识别；
	/**
	 * 起始笔数，用于多笔查询，默认从1开始
		查询笔数，用于多笔交易的请求(<=30)
	 */
	private String ECIF_CUST_NO;//客户编号
	private String FIRST_NO_A;//联系地址起始笔数
	private String RESULT_SIZE_A;//联系地址查询笔数
	private String ADDR_TYPE;//地址类型
	private String FIRST_NO_B;//联系电话起始笔数
	private String RESULT_SIZE_B;//联系电话查询笔数
	private String TELE_TYPE;//电话类型
	private String FIRST_NO_C;//网络信息起始笔数
	private String RESULT_SIZE_C;//网络信息查询笔数
	private String INTER_TYPE;//网络地址类型
	/**
	 * 
	 */
	public RequestA011() {
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
	 * @return fIRST_NO_A
	 */
	public String getFIRST_NO_A() {
		return FIRST_NO_A;
	}
	/**
	 * @param fIRST_NO_A 要设置的 fIRST_NO_A
	 */
	public void setFIRST_NO_A(String fIRST_NO_A) {
		FIRST_NO_A = fIRST_NO_A;
	}
	/**
	 * @return rESULT_SIZE_A
	 */
	public String getRESULT_SIZE_A() {
		return RESULT_SIZE_A;
	}
	/**
	 * @param rESULT_SIZE_A 要设置的 rESULT_SIZE_A
	 */
	public void setRESULT_SIZE_A(String rESULT_SIZE_A) {
		RESULT_SIZE_A = rESULT_SIZE_A;
	}
	/**
	 * @return aDDR_TYPE
	 */
	public String getADDR_TYPE() {
		return ADDR_TYPE;
	}
	/**
	 * @param aDDR_TYPE 要设置的 aDDR_TYPE
	 */
	public void setADDR_TYPE(String aDDR_TYPE) {
		ADDR_TYPE = aDDR_TYPE;
	}
	/**
	 * @return fIRST_NO_B
	 */
	public String getFIRST_NO_B() {
		return FIRST_NO_B;
	}
	/**
	 * @param fIRST_NO_B 要设置的 fIRST_NO_B
	 */
	public void setFIRST_NO_B(String fIRST_NO_B) {
		FIRST_NO_B = fIRST_NO_B;
	}
	/**
	 * @return rESULT_SIZE_B
	 */
	public String getRESULT_SIZE_B() {
		return RESULT_SIZE_B;
	}
	/**
	 * @param rESULT_SIZE_B 要设置的 rESULT_SIZE_B
	 */
	public void setRESULT_SIZE_B(String rESULT_SIZE_B) {
		RESULT_SIZE_B = rESULT_SIZE_B;
	}
	/**
	 * @return tELE_TYPE
	 */
	public String getTELE_TYPE() {
		return TELE_TYPE;
	}
	/**
	 * @param tELE_TYPE 要设置的 tELE_TYPE
	 */
	public void setTELE_TYPE(String tELE_TYPE) {
		TELE_TYPE = tELE_TYPE;
	}
	/**
	 * @return fIRST_NO_C
	 */
	public String getFIRST_NO_C() {
		return FIRST_NO_C;
	}
	/**
	 * @param fIRST_NO_C 要设置的 fIRST_NO_C
	 */
	public void setFIRST_NO_C(String fIRST_NO_C) {
		FIRST_NO_C = fIRST_NO_C;
	}
	/**
	 * @return rESULT_SIZE_C
	 */
	public String getRESULT_SIZE_C() {
		return RESULT_SIZE_C;
	}
	/**
	 * @param rESULT_SIZE_C 要设置的 rESULT_SIZE_C
	 */
	public void setRESULT_SIZE_C(String rESULT_SIZE_C) {
		RESULT_SIZE_C = rESULT_SIZE_C;
	}
	/**
	 * @return iNTER_TYPE
	 */
	public String getINTER_TYPE() {
		return INTER_TYPE;
	}
	/**
	 * @param iNTER_TYPE 要设置的 iNTER_TYPE
	 */
	public void setINTER_TYPE(String iNTER_TYPE) {
		INTER_TYPE = iNTER_TYPE;
	}
	
}
