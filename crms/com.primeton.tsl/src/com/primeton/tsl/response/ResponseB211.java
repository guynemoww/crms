/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *对公客户关系个人删除响应报文			

 */
public class ResponseB211 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4939272271831680129L;
	private String ECIF_CUST_NO;//客户编号
	
	/**
	 * 
	 */
	public ResponseB211() {
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
	
}
