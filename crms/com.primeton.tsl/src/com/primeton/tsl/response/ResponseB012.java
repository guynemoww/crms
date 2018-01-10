/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *
 */
public class ResponseB012 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81201793909822134L;
	private String ECIF_CUST_NO;//客户编号
	/**
	 * 
	 */
	public ResponseB012() {
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
