/**
 * 
 */
package com.bos.irm.modelCalc;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;

/**
 * @author zhangqi
 * @date 2014-04-18 16:47:09
 *
 */
public  class nonFinancialCalc {
	public static  BigDecimal getNonFinanceIndexScore(String indexCode,String indexNum){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("indexCode", indexCode);
		map.put("indexNum", indexNum);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.irm.modelCalc.model.getNonFinanceIndexScore", map);
		BigDecimal value = null;
		
		if(0!=tmp.length){
			
			 value =  (BigDecimal) tmp[0];
		}
		System.out.println(value);
		return value;
	}
}
