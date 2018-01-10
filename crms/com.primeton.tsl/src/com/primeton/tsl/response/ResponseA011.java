/**
 * 
 */
package com.primeton.tsl.response;

import java.io.Serializable;

/**
 * @author zhouxu
 *客户地址信息查询响应报文		

 */
public class ResponseA011 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6561448779345881129L;
	private String ECIF_CUST_NO;//客户编号
	private String FIRST_NO_A;//起始笔数
	private String RESULT_SIZE_A;//查询笔数
	private String ALL_RESULT_SIZE_A;//总笔数
	private String ADDR_ID;//联系地址ID
	private String ADDR_TYPE;//地址类型
	private String POST_CD;//邮政编码
	private String NATION_CODE;//国家
	private String PROVINCE_CODE;//省、直辖市、自治区
	private String CITY_CODE;//城市
	private String COUNTY_CODE;//县、区
	private String STREET_AREA;//街道
	private String ADDR_LINE;//详细地址
	private String ADDR_DESC;//联系地址描述或联系电话描述或网络地址描述
	private String FIRST_NO_B;//起始笔数
	private String RESULT_SIZE_B;//查询笔数
	private String ALL_RESULT_SIZE_B;//总笔数
	private String TELE_ID;//联系电话ID
	private String TELE_TYPE;//电话类型
	private String COUNTRY_NO;//国家区号
	private String AREA_NO;//区号
	private String PHONE_NO;//电话号码
	private String EXT_NO;//分机号
	private String FIRST_NO_C;//起始笔数
	private String RESULT_SIZE_C;//查询笔数
	private String ALL_RESULT_SIZE_C;//总笔数
	private String INTER_ID;//网络地址ID
	private String INTER_TYPE;//网络地址类型
	private String INTERNET_ADDR;//互联网地址
	
	/**
	 * 
	 */
	public ResponseA011() {
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
	 * @return fIRST_NO_A
	 */
	public String getFIRST_NO_A() {
		return FIRST_NO_A;
	}

	/**
	 * @param fIRST_NO_A 要设置的 fIRST_NO_A
	 */
	public void setFIRST_NO_A(String fIRST_NO_A) {
		FIRST_NO_A = fIRST_NO_A;
	}

	/**
	 * @return rESULT_SIZE_A
	 */
	public String getRESULT_SIZE_A() {
		return RESULT_SIZE_A;
	}

	/**
	 * @param rESULT_SIZE_A 要设置的 rESULT_SIZE_A
	 */
	public void setRESULT_SIZE_A(String rESULT_SIZE_A) {
		RESULT_SIZE_A = rESULT_SIZE_A;
	}

	/**
	 * @return aLL_RESULT_SIZE_A
	 */
	public String getALL_RESULT_SIZE_A() {
		return ALL_RESULT_SIZE_A;
	}

	/**
	 * @param aLL_RESULT_SIZE_A 要设置的 aLL_RESULT_SIZE_A
	 */
	public void setALL_RESULT_SIZE_A(String aLL_RESULT_SIZE_A) {
		ALL_RESULT_SIZE_A = aLL_RESULT_SIZE_A;
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
	 * @return aDDR_DESC
	 */
	public String getADDR_DESC() {
		return ADDR_DESC;
	}

	/**
	 * @param aDDR_DESC 要设置的 aDDR_DESC
	 */
	public void setADDR_DESC(String aDDR_DESC) {
		ADDR_DESC = aDDR_DESC;
	}

	/**
	 * @return fIRST_NO_B
	 */
	public String getFIRST_NO_B() {
		return FIRST_NO_B;
	}

	/**
	 * @param fIRST_NO_B 要设置的 fIRST_NO_B
	 */
	public void setFIRST_NO_B(String fIRST_NO_B) {
		FIRST_NO_B = fIRST_NO_B;
	}

	/**
	 * @return rESULT_SIZE_B
	 */
	public String getRESULT_SIZE_B() {
		return RESULT_SIZE_B;
	}

	/**
	 * @param rESULT_SIZE_B 要设置的 rESULT_SIZE_B
	 */
	public void setRESULT_SIZE_B(String rESULT_SIZE_B) {
		RESULT_SIZE_B = rESULT_SIZE_B;
	}

	/**
	 * @return aLL_RESULT_SIZE_B
	 */
	public String getALL_RESULT_SIZE_B() {
		return ALL_RESULT_SIZE_B;
	}

	/**
	 * @param aLL_RESULT_SIZE_B 要设置的 aLL_RESULT_SIZE_B
	 */
	public void setALL_RESULT_SIZE_B(String aLL_RESULT_SIZE_B) {
		ALL_RESULT_SIZE_B = aLL_RESULT_SIZE_B;
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
	 * @return fIRST_NO_C
	 */
	public String getFIRST_NO_C() {
		return FIRST_NO_C;
	}

	/**
	 * @param fIRST_NO_C 要设置的 fIRST_NO_C
	 */
	public void setFIRST_NO_C(String fIRST_NO_C) {
		FIRST_NO_C = fIRST_NO_C;
	}

	/**
	 * @return rESULT_SIZE_C
	 */
	public String getRESULT_SIZE_C() {
		return RESULT_SIZE_C;
	}

	/**
	 * @param rESULT_SIZE_C 要设置的 rESULT_SIZE_C
	 */
	public void setRESULT_SIZE_C(String rESULT_SIZE_C) {
		RESULT_SIZE_C = rESULT_SIZE_C;
	}

	/**
	 * @return aLL_RESULT_SIZE_C
	 */
	public String getALL_RESULT_SIZE_C() {
		return ALL_RESULT_SIZE_C;
	}

	/**
	 * @param aLL_RESULT_SIZE_C 要设置的 aLL_RESULT_SIZE_C
	 */
	public void setALL_RESULT_SIZE_C(String aLL_RESULT_SIZE_C) {
		ALL_RESULT_SIZE_C = aLL_RESULT_SIZE_C;
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
