/**
 * 
 */
package com.bos.comm.acc;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.bos.utp.tools.ChangeUtil;

/**
 * @author C_ture
 * @date 2014-04-16 10:44:06
 *
 */
@Bizlet("财报工具类")
public class AccFinanceCommUtil {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);

	/**
	 * @param paramMap 包括
	 * 1、传入客户财报id-FINANCE_ID,返回指定财报指标； 
	 * 2、传入客户id-PARTY_ID,财报类型-FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报；
	 * 财报截止日期-FINANCE_DEADLINE；返回客户指定类型的最新或指定日期财报指标
	 * 为满足取即期财报新增参数取值范围-DATE_TYPE：1-最近一期（即期），2-指定日期
	 * @return Object[] (INDEX_CD-指标代码,INDEX_VALUE_DATA_TYPE-指标值(数值类型),STRING_TYPE-指标值(字符类型))
	 * STRING_TYPE为空（NullOrEmpty）时取INDEX_VALUE_DATA_TYPE,否则取STRING_TYPE
	 * @author C_ture
	 */
	@Bizlet("获取财报数据（指标)")
	public static Object[] getIndexValue(HashMap<String, Object> paramMap)  throws Exception {
		
		
		String financeId = paramMap.get("FINANCE_ID") != null ? paramMap
				.get("FINANCE_ID").toString() : "";
		String partyId = paramMap.get("PARTY_ID") != null ? paramMap
				.get("PARTY_ID").toString() : "";
		String dateType = paramMap.get("DATE_TYPE") != null ? paramMap
						.get("DATE_TYPE").toString() : "";

		Object[] financeDatas = null;
		if(!financeId.equals("")){
			financeDatas = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.comm.acc.accUtil.selectIndexValueByFinanceId", paramMap);
			
		}else if(!partyId.equals("")){//根据客户id、财报类型financeTypeCd、财报日期取财报指标

			paramMap.put("FINANCE_STATUS_CD", "02");//生效 只能查生效的财报
			String financeTypeCd = paramMap.get("FINANCE_TYPE_CD") != null ? paramMap
					.get("FINANCE_TYPE_CD").toString() : "";
			Date financeDeadline = paramMap.get("FINANCE_DEADLINE") != null ? (Date)paramMap
					.get("FINANCE_DEADLINE"): null;
			if(financeTypeCd.equals("") ){
				throw new Exception("传入的财报类型为空，无法找不到对应的财报记录！");
			}
			if(dateType.equals("1")){//最近一期（即期）
				financeDatas = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.comm.acc.accUtil.selectIndexValueByPartyId", paramMap);
			}else if (dateType.equals("2")){//2-指定日期
				if(financeDeadline==null){
					throw new Exception("传入的财报日期为空，无法找不到对应的财报记录！");
				}
				financeDatas = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.comm.acc.accUtil.selectIndexValueByPartyIds", paramMap);
			}
			if (null == financeDatas || financeDatas.length < 1) {
				return financeDatas;
			}
		}else{
			throw new Exception("传入的财报id和客户id都为空，无法找不到对应的财报记录！");
		}

		return financeDatas;
		
	}

	/**
	 * @param paramMap 包括
	 * 1、传入客户财报id-FINANCE_ID,返回指定财报信息； 
	 * 2、传入客户id-PARTY_ID,财报类型-FINANCE_TYPE_CD 财报类型 1-年报,2-半年报，3-季报，4-月报，5-类年报；
	 * 财报截止日期-FINANCE_DEADLINE(没有财报截止日期可以传入营业日期)；返回客户指定类型的最新或指定日期财报科目
	 * 报表状态-FINANCE_STATUS_CD 01-未生效，02-生效（不给则查所有的）；
	 * @return financeData 传入financeId的返回结果包括财报附注信息+所有科目(科目代码不带"c")、 
	 * 			传入PARTY_ID的返回结果包括所有科目（返回的科目代码前加"c"）
	 * @author C_ture
	 */
	@Bizlet("获取财报数据（附注+科目）")
	public static HashMap getProjectValue(HashMap<String, Object> paramMap) throws Exception {
		HashMap<String, Object> financeData = new HashMap<String, Object>();
		
		String financeId = paramMap.get("financeId") != null ? paramMap
				.get("financeId").toString() : "";
		String partyId = paramMap.get("PARTY_ID") != null ? paramMap
				.get("PARTY_ID").toString() : "";
				//根据financeId取财报信息
				if(!financeId.equals("")){
//					附注信息
					Object[] financeDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.comm.acc.accUtil.selectFinanceDatasByFinanceId", paramMap);
					if (null == financeDatas || financeDatas.length != 1) {
						throw new Exception("传入的财报id找不到对应的财报记录！");
					}
					Map fmap = (Map) financeDatas[0];
					financeData.putAll(fmap);
					//财报科目
					Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.comm.acc.accUtil.selectProjectDatasByFinanceId", paramMap);
					if (null == financeDatas || financeDatas.length != 1) {
						throw new Exception("传入的财报id找不到对应的财报科目，请确认是否已经录入科目！");
					}
					for (int i = 0; i < ProjectDatas.length; i++) {
						Map pmap = (Map) ProjectDatas[i];
						if(pmap.get("PROJECT_VALUE") == null){
							financeData.put((String) pmap.get("PROJECT_CD"),null);
						}else{
						financeData.put((String) pmap.get("PROJECT_CD"), (ChangeUtil
								.formatDouble(new Double(pmap.get("PROJECT_VALUE").toString()), 2)));
						}
					}			
				}else if(!partyId.equals("")){//根据客户id、财报类型financeTypeCd、财报日期取财报科目
					String financeTypeCd = paramMap.get("FINANCE_TYPE_CD") != null ? paramMap
							.get("FINANCE_TYPE_CD").toString() : "";
					Date financeDeadline = paramMap.get("FINANCE_DEADLINE") != null ? (Date)paramMap
							.get("FINANCE_DEADLINE"): null;
					if(financeTypeCd.equals("") || financeDeadline==null){
						throw new Exception("传入的财报类型或财报日期为空，无法找不到对应的财报记录！");
					}
					//财报科目
					Object[] ProjectDatas = DatabaseExt.queryByNamedSql(
							GitUtil.DEFAULT_DS_NAME,
							"com.bos.comm.acc.accUtil.selectProjectDatasByPartyId", paramMap);
					for (int i = 0; i < ProjectDatas.length; i++) {
						Map pmap = (Map) ProjectDatas[i];
						if(pmap.get("PROJECT_VALUE") == null){
							financeData.put((String) pmap.get("PROJECT_CD"),null);
						}else{
						financeData.put("c" +(String) pmap.get("PROJECT_CD"), (ChangeUtil
								.formatDouble(new Double(pmap.get("PROJECT_VALUE").toString()), 2)));//为防止map的key为全数字,科目代码前加字母“c”
						}
					}
				}else{
					throw new Exception("传入的财报id和客户id都为空，无法找不到对应的财报记录！");
				}

		return financeData;
 
	}

}
