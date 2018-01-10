/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *客户地址信息创建与维护响应报文			

 */
public class ResponseB011 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2267995937149988564L;
	private String ECIF_CUST_NO;//客户编号
	private String ADDR_ID;//联系地址ID
	private String ADDR_TYPE;//地址类型
	private String TELE_ID;//联系电话ID
	private String TELE_TYPE;//电话类型
	private String INTER_ID;//网络地址ID
	private String INTER_TYPE;//网络地址类型
	/**
	 * 
	 */
	public ResponseB011() {
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
	 * @return aDDR_ID
	 */
	public String getADDR_ID() {
		return ADDR_ID;
	}
	/**
	 * @param aDDR_ID 要设置的 aDDR_ID
	 */
	public void setADDR_ID(String aDDR_ID) {
		ADDR_ID = aDDR_ID;
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
	 * @return tELE_ID
	 */
	public String getTELE_ID() {
		return TELE_ID;
	}
	/**
	 * @param tELE_ID 要设置的 tELE_ID
	 */
	public void setTELE_ID(String tELE_ID) {
		TELE_ID = tELE_ID;
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
	 * @return iNTER_ID
	 */
	public String getINTER_ID() {
		return INTER_ID;
	}
	/**
	 * @param iNTER_ID 要设置的 iNTER_ID
	 */
	public void setINTER_ID(String iNTER_ID) {
		INTER_ID = iNTER_ID;
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
