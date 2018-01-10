/**
 * 
 */
package com.primeton.tsl.reqest;

import java.io.Serializable;

/**
 * @author zhouxu
 *客户地址信息创建与维护请求报文			

 */
public class RequestB011 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 417739574296503855L;
	private String RESOLVE_TYPE;//客户识别方式
	private String ECIF_CUST_NO;//客户编号
	private String ADDR_ID;//联系地址ID
	private String ADDR_TYPE;//地址类型
	private String POST_CD;//邮政编码
	private String NATION_CODE;//国家z`
	private String PROVINCE_CODE;//省、直辖市、自治区
	private String CITY_CODE;//城市
	private String COUNTY_CODE;//县、区
	private String STREET_AREA;//街道
	private String ADDR_LINE;//详细地址
	private String ADDR_DESC_A;//联系地址描述
	private String TELE_ID;//联系电话ID
	private String TELE_TYPE;//电话类型
	private String COUNTRY_NO;//国家区号
	private String AREA_NO;//区号
	private String PHONE_NO;//电话号码
	private String EXT_NO;//分机号
	private String INTER_ID;//网络地址ID
	private String INTER_TYPE;//网络地址类型
	private String INTERNET_ADDR;//互联网地址
	private String ADDR_DESC_B;//联系电话描述
	private String ADDR_DESC_C;//网络地址描述
	/**
	 * 
	 */
	public RequestB011() {
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
	 * @return pOST_CD
	 */
	public String getPOST_CD() {
		return POST_CD;
	}
	/**
	 * @param pOST_CD 要设置的 pOST_CD
	 */
	public void setPOST_CD(String pOST_CD) {
		POST_CD = pOST_CD;
	}
	/**
	 * @return nATION_CODE
	 */
	public String getNATION_CODE() {
		return NATION_CODE;
	}
	/**
	 * @param nATION_CODE 要设置的 nATION_CODE
	 */
	public void setNATION_CODE(String nATION_CODE) {
		NATION_CODE = nATION_CODE;
	}
	/**
	 * @return pROVINCE_CODE
	 */
	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}
	/**
	 * @param pROVINCE_CODE 要设置的 pROVINCE_CODE
	 */
	public void setPROVINCE_CODE(String pROVINCE_CODE) {
		PROVINCE_CODE = pROVINCE_CODE;
	}
	/**
	 * @return cITY_CODE
	 */
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	/**
	 * @param cITY_CODE 要设置的 cITY_CODE
	 */
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	/**
	 * @return cOUNTY_CODE
	 */
	public String getCOUNTY_CODE() {
		return COUNTY_CODE;
	}
	/**
	 * @param cOUNTY_CODE 要设置的 cOUNTY_CODE
	 */
	public void setCOUNTY_CODE(String cOUNTY_CODE) {
		COUNTY_CODE = cOUNTY_CODE;
	}
	/**
	 * @return sTREET_AREA
	 */
	public String getSTREET_AREA() {
		return STREET_AREA;
	}
	/**
	 * @param sTREET_AREA 要设置的 sTREET_AREA
	 */
	public void setSTREET_AREA(String sTREET_AREA) {
		STREET_AREA = sTREET_AREA;
	}
	/**
	 * @return aDDR_LINE
	 */
	public String getADDR_LINE() {
		return ADDR_LINE;
	}
	/**
	 * @param aDDR_LINE 要设置的 aDDR_LINE
	 */
	public void setADDR_LINE(String aDDR_LINE) {
		ADDR_LINE = aDDR_LINE;
	}
	
	/**
	 * @return aDDR_DESC_A
	 */
	public String getADDR_DESC_A() {
		return ADDR_DESC_A;
	}
	/**
	 * @param aDDR_DESC_A 要设置的 aDDR_DESC_A
	 */
	public void setADDR_DESC_A(String aDDR_DESC_A) {
		ADDR_DESC_A = aDDR_DESC_A;
	}
	/**
	 * @return aDDR_DESC_B
	 */
	public String getADDR_DESC_B() {
		return ADDR_DESC_B;
	}
	/**
	 * @param aDDR_DESC_B 要设置的 aDDR_DESC_B
	 */
	public void setADDR_DESC_B(String aDDR_DESC_B) {
		ADDR_DESC_B = aDDR_DESC_B;
	}
	/**
	 * @return aDDR_DESC_C
	 */
	public String getADDR_DESC_C() {
		return ADDR_DESC_C;
	}
	/**
	 * @param aDDR_DESC_C 要设置的 aDDR_DESC_C
	 */
	public void setADDR_DESC_C(String aDDR_DESC_C) {
		ADDR_DESC_C = aDDR_DESC_C;
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
	 * @return cOUNTRY_NO
	 */
	public String getCOUNTRY_NO() {
		return COUNTRY_NO;
	}
	/**
	 * @param cOUNTRY_NO 要设置的 cOUNTRY_NO
	 */
	public void setCOUNTRY_NO(String cOUNTRY_NO) {
		COUNTRY_NO = cOUNTRY_NO;
	}
	/**
	 * @return aREA_NO
	 */
	public String getAREA_NO() {
		return AREA_NO;
	}
	/**
	 * @param aREA_NO 要设置的 aREA_NO
	 */
	public void setAREA_NO(String aREA_NO) {
		AREA_NO = aREA_NO;
	}
	/**
	 * @return pHONE_NO
	 */
	public String getPHONE_NO() {
		return PHONE_NO;
	}
	/**
	 * @param pHONE_NO 要设置的 pHONE_NO
	 */
	public void setPHONE_NO(String pHONE_NO) {
		PHONE_NO = pHONE_NO;
	}
	/**
	 * @return eXT_NO
	 */
	public String getEXT_NO() {
		return EXT_NO;
	}
	/**
	 * @param eXT_NO 要设置的 eXT_NO
	 */
	public void setEXT_NO(String eXT_NO) {
		EXT_NO = eXT_NO;
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
	/**
	 * @return iNTERNET_ADDR
	 */
	public String getINTERNET_ADDR() {
		return INTERNET_ADDR;
	}
	/**
	 * @param iNTERNET_ADDR 要设置的 iNTERNET_ADDR
	 */
	public void setINTERNET_ADDR(String iNTERNET_ADDR) {
		INTERNET_ADDR = iNTERNET_ADDR;
	}
	
}
