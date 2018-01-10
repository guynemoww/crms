package com.bos.pub;

public class SqlUtil {

	/**
	 * 动态处理sql语句，
	 * 例如：
	 * 将"PARTY_ID, PARTY_NUM, CORP_CUSTOMER_TYPE_CD"
	 * 改为"party_id as partyId,party_num as partyNum,corp_customer_type_cd as corpCustomerTypeCd,"
	 * @param str
	 * @return
	 */
	public static String genrateSqlByTableClumn(String str){
		
		StringBuffer sql = new StringBuffer();
		//去空格
		str =str.replace(" ", "");
		System.out.println("==生成前==>"+str);
		str = str.toLowerCase();
		String [] strarry = str.split(",");
		for (int i = 0; i < strarry.length; i++) {
			String string = strarry[i];
			//先拼接原型
			sql.append(string).append(" as ");
			if(string.indexOf("_")!=-1){
				
				String [] stringarry = string.split("_");
				String tmp="";
				for (int j = 0; j < stringarry.length; j++) {
					String string2 = stringarry[j];
					if(j==0){
						continue;
					}else{
						tmp +=string2.substring(0,1).toUpperCase()+string2.substring(1);
					}
				}
				//再拼接生成后的驼峰串
				sql.append(stringarry[0]+tmp).append(",");
			}else{
				//再拼接生成后的驼峰串
				sql.append(string).append(",");
			}
		}
		
		return sql.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		String str = "PARTY_ID, PARTY_NUM, CORP_CUSTOMER_TYPE_CD, PARTY_NAME, ENGLISH_NAME, BUSINESS_LICENSE_NUM, ORGN_NUM, LOAN_CARD_NUM, BUSINESS_LICENSE_REGISTE_DATE, BUSINESS_LICENSE_CHECK_DATE, BELONG_STATION, ECONOMIC_SECTOR_CODE, ECONOMIC_CATEGORIES_CODE, CUSTOMER_STATUS_CD, MONEY_LAUNDERING_RESULTS_CD, INDUSTRIAL_TYPE_CD, MYSELF_ENTERPRISE_SIZE_CD, PBC_ENTERPRISE_SIZE_CD, BASEL_ENTERPRISE_SIZE_CD, IF_LISTED_COMPANY, DATE_OF_ESTABLISHMENT, REGISTER_ASSETS, REGISTER_ASSETS_CURRENCY_CD, '1111' as STAFF_NUM, TAKING_AMT, ASSETS_TOTAL_AMT, OPERATING_BUSINESS, ANNUAL_INSPECTION_CD, LOAN_CARD_OPEN_DATE, ANNUAL_INSPECTION_INDEX_CD, ANNUAL_INSPECTION_DATE, CORPORATION_E_MAIL, CORPORATION_URL, CONTACT_PHONE, FAX_PHONE, FINANCE_CONTACT_PHONE, NATIONAL_TAX_NO, GOVERNMENT_TENT_NO, CREDIT_RELATIONSHIP_DATE, WHETHER_IMP_EXP, WHETHER_SCIENCE_CORP, OPERATING_AREA, OPERATING_AREA_OWNERSHIP_CD, OPERATING_AREA_OWNER_PERCENT, CORP_HOLDIING_TYPE_CD, WHETHER_CLOSE_CORP, WHETHER_GOVERNMENT_FINANCE_COR, WHETHER_ABOVE_DESIGNATED_SIZE, WHETHER_ABOVE_LIMIT_SIZE, WHETHER_BANK_IMPORTANT_CORP, WHETHER_PASS_PEANUTS, WHETHER_CREDIT_CORP, WHETHER_SPECIAL_CORP, TRADE_COOPERATION_CORP_TYEP_CD, AGENT_NAME, WHETHER_FACTOR, FACTOR_TYPE_CD, WHETHER_IMPORNANT_CORP, WHETHER_VIP_CORP, REGISTER_DATE, REGISTER_ASSETS_RATE, LEGAL_CERTIFICATE_NO, COMPETENT_DEPARTMENT, INITIAL_FUND, FINANCIAL_RESOURCES_CD, HOST_UNIT, CORP_LINKMAN, FINANCE_LINKMAN, LEGAL_CERTIFICATE_END_DATE, PURPOSE_BIZ_SCOPE, REGISTER_ORG, UNIT_SCALE_CD, MANAGE_LEVEL_STATE, OPERATE_STATE, MAJOR_PRODUCT_STATE, OPEN_ACCT_APPROVAL_NO, CREATE_TIME, UPDATE_TIME, ADMINISTRATIVE_DIVISIONS_CD, WHETHER_SETTLEMENT_ACCOUNT, WHETHER_EXP_DRAWBACK_ACCOUNT, HAVE_NOT_PREFERENTIAL_POLICY, WHETHER_ATTENTION_ENTERPRISE, FOURZ_ENTERPRISE_SIZE_CD, FOURR_ENTERPRISE_SIZE_CD, FINANCE_ENTERPRISE_TYPE, GOV_PLATFORM_TYPE, WHETHER_BLACK_LIST, BLACK_LIST_REASON_CD, WHETHER_GROUP, GROUP_REL_TYPE_CD";
		String gstr=genrateSqlByTableClumn(str);
		System.out.println("==生成后===》"+gstr);
	}

}
