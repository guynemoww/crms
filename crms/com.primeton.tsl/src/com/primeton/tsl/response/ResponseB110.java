/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户关系个人信息创建与维护响应报文				

 */
public class ResponseB110 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1883123791869840084L;
	private String ECIF_CUST_NO;//客户编号
	private String PAR_SEQ_ID;//关联关系记录编号
	private String RELATION_TYPE;//关联关系类型
	private String RELATION_ID;//关系人ID
	private String REL_CUST_NO;//关系人客户编号
	private String REL_NAME;//关系人名称
	private String REL_CERT_TYPE;//关系人证件类型
	private String REL_CERT_NO;//关系人证件号码
	/**
	 * 
	 */
	public ResponseB110() {
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
	/**
	 * @return rEL_CUST_NO
	 */
	public String getREL_CUST_NO() {
		return REL_CUST_NO;
	}
	/**
	 * @param rEL_CUST_NO 要设置的 rEL_CUST_NO
	 */
	public void setREL_CUST_NO(String rEL_CUST_NO) {
		REL_CUST_NO = rEL_CUST_NO;
	}
	/**
	 * @return rEL_NAME
	 */
	public String getREL_NAME() {
		return REL_NAME;
	}
	/**
	 * @param rEL_NAME 要设置的 rEL_NAME
	 */
	public void setREL_NAME(String rEL_NAME) {
		REL_NAME = rEL_NAME;
	}
	/**
	 * @return rEL_CERT_TYPE
	 */
	public String getREL_CERT_TYPE() {
		return REL_CERT_TYPE;
	}
	/**
	 * @param rEL_CERT_TYPE 要设置的 rEL_CERT_TYPE
	 */
	public void setREL_CERT_TYPE(String rEL_CERT_TYPE) {
		REL_CERT_TYPE = rEL_CERT_TYPE;
	}
	/**
	 * @return rEL_CERT_NO
	 */
	public String getREL_CERT_NO() {
		return REL_CERT_NO;
	}
	/**
	 * @param rEL_CERT_NO 要设置的 rEL_CERT_NO
	 */
	public void setREL_CERT_NO(String rEL_CERT_NO) {
		REL_CERT_NO = rEL_CERT_NO;
	}
	
}
