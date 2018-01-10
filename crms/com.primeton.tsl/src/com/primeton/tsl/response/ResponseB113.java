/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户关系企业信息删除响应报文				

 */
public class ResponseB113 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3192033875853936711L;
	private String ECIF_CUST_NO;//客户编号
	private String PAR_SEQ_ID;//关联关系记录编号
	/**
	 * 
	 */
	public ResponseB113() {
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
	
	
}
