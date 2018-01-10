/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户关系个人信息删除请求报文				

 */
public class RequestB111 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -268786658492256965L;
	private String PAR_SEQ_ID;//关联关系记录编号 关联关系的唯一记录编号
	private String ECIF_CUST_NO;//客户编号
	private String RELATION_TYPE;//关联关系类型  关联关系记录编号必填或客户编号+关联关系类型+关系人ID  必填
	private String RELATION_ID;//关系人ID
	
	/**
	 * 
	 */
	public RequestB111() {
		// TODO 自动生成的构造函数存根
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
	
}
