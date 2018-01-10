/**
 * 
 */
package com.bos.conPrint.product;

import java.util.Map;

import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

/**
 * <pre>
 * Title: 抵质押，保证 合同打印信息
 * Description: 程序功能的描述
 * </pre>
 * 
 * @author Administrator
 * @version 1.00.00
 * 
 */

public class BigType_ZHSX extends ConInfoLoad_Product {
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> loadCon(Map<String, String> param) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, getLoadConSql(), param);
		if (datas == null || datas.length == 0) {
			return null;
		}
		Map<String, Object> conMap = datas == null || datas.length == 0 ? null : (Map<String, Object>) datas[0];
		if (conMap == null) {
			return null;
		}
		loadConSub(conMap);
		changeCon(conMap);
		setConInfo(conMap);
		return conMap;
	}
}
