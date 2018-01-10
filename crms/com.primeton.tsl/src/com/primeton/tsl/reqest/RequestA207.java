/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户关系个人信息查询请求报文			

 */
public class RequestA207 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1250498401301364844L;
	private String FIRST_NO_PSN;//起始笔数
	private String RESULT_SIZE_PSN;//查询笔数
	private String ECIF_CUST_NO;//客户编号
	private String RELATION_TYPE_PSN;//关系类型
	/**
	 * 
	 */
	public RequestA207() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * @return fIRST_NO_PSN
	 */
	public String getFIRST_NO_PSN() {
		return FIRST_NO_PSN;
	}
	/**
	 * @param fIRST_NO_PSN 要设置的 fIRST_NO_PSN
	 */
	public void setFIRST_NO_PSN(String fIRST_NO_PSN) {
		FIRST_NO_PSN = fIRST_NO_PSN;
	}
	/**
	 * @return rESULT_SIZE_PSN
	 */
	public String getRESULT_SIZE_PSN() {
		return RESULT_SIZE_PSN;
	}
	/**
	 * @param rESULT_SIZE_PSN 要设置的 rESULT_SIZE_PSN
	 */
	public void setRESULT_SIZE_PSN(String rESULT_SIZE_PSN) {
		RESULT_SIZE_PSN = rESULT_SIZE_PSN;
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
	 * @return rELATION_TYPE_PSN
	 */
	public String getRELATION_TYPE_PSN() {
		return RELATION_TYPE_PSN;
	}
	/**
	 * @param rELATION_TYPE_PSN 要设置的 rELATION_TYPE_PSN
	 */
	public void setRELATION_TYPE_PSN(String rELATION_TYPE_PSN) {
		RELATION_TYPE_PSN = rELATION_TYPE_PSN;
	}
	
}
