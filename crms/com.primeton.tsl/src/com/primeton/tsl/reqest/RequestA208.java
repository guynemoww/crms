/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户关系企业信息查询请求报文			

 */
public class RequestA208 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6060299942952473486L;
	private String FIRST_NO_COM;//起始笔数
	private String RESULT_SIZE_COM;//查询笔数
	private String ECIF_CUST_NO;//客户编号
	private String RELATION_TYPE_COM;//关系类型
	
	/**
	 * 
	 */
	public RequestA208() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @return fIRST_NO_COM
	 */
	public String getFIRST_NO_COM() {
		return FIRST_NO_COM;
	}

	/**
	 * @param fIRST_NO_COM 要设置的 fIRST_NO_COM
	 */
	public void setFIRST_NO_COM(String fIRST_NO_COM) {
		FIRST_NO_COM = fIRST_NO_COM;
	}

	/**
	 * @return rESULT_SIZE_COM
	 */
	public String getRESULT_SIZE_COM() {
		return RESULT_SIZE_COM;
	}

	/**
	 * @param rESULT_SIZE_COM 要设置的 rESULT_SIZE_COM
	 */
	public void setRESULT_SIZE_COM(String rESULT_SIZE_COM) {
		RESULT_SIZE_COM = rESULT_SIZE_COM;
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
	 * @return rELATION_TYPE_COM
	 */
	public String getRELATION_TYPE_COM() {
		return RELATION_TYPE_COM;
	}

	/**
	 * @param rELATION_TYPE_COM 要设置的 rELATION_TYPE_COM
	 */
	public void setRELATION_TYPE_COM(String rELATION_TYPE_COM) {
		RELATION_TYPE_COM = rELATION_TYPE_COM;
	}
	
}
