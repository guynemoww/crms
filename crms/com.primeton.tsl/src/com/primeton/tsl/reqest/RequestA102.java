/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *对私客户基本信息查询请求报文			

 */
public class RequestA102 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5618605291608467557L;
	private String RESOLVE_TYPE;//客户识别方式  必填 1、使用客户编号识别；2、使用三要素组合(客户姓名、证件类型和证件号码)识别；3 - 使用客户账号(卡号、折号、一本通号)识别
	private String ECIF_CUST_NO;//客户编号
	/**以下
	 * 识别方式为2时，必填
	 */
	private String PARTY_NAME;//客户名称
	private String CERT_TYPE;//证件类型
	private String CERT_NO;//证件号码
	private String EXT_ARR_NO;//客户账号
	
	public RequestA102() {
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
	 * @return pARTY_NAME
	 */
	public String getPARTY_NAME() {
		return PARTY_NAME;
	}

	/**
	 * @param pARTY_NAME 要设置的 pARTY_NAME
	 */
	public void setPARTY_NAME(String pARTY_NAME) {
		PARTY_NAME = pARTY_NAME;
	}

	/**
	 * @return cERT_TYPE
	 */
	public String getCERT_TYPE() {
		return CERT_TYPE;
	}

	/**
	 * @param cERT_TYPE 要设置的 cERT_TYPE
	 */
	public void setCERT_TYPE(String cERT_TYPE) {
		CERT_TYPE = cERT_TYPE;
	}

	/**
	 * @return cERT_NO
	 */
	public String getCERT_NO() {
		return CERT_NO;
	}

	/**
	 * @param cERT_NO 要设置的 cERT_NO
	 */
	public void setCERT_NO(String cERT_NO) {
		CERT_NO = cERT_NO;
	}

	/**
	 * @return eXT_ARR_NO
	 */
	public String getEXT_ARR_NO() {
		return EXT_ARR_NO;
	}

	/**
	 * @param eXT_ARR_NO 要设置的 eXT_ARR_NO
	 */
	public void setEXT_ARR_NO(String eXT_ARR_NO) {
		EXT_ARR_NO = eXT_ARR_NO;
	}
	
	
}
