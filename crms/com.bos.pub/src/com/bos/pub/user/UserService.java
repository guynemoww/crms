/**
 * 
 */
package com.bos.pub.user;

import java.util.Map;

import com.bos.pub.UserUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-07-11 10:55:58
 * 
 */
@Bizlet("")
public class UserService {
	@Bizlet
	public Object[] queryByNamedSqlWithPage(String dsName, String nameSqlId, DataObject pageCond, Map<String, Object> param) {
		if (param != null) {
			String searchMode = (String) param.get("searchMode");
			if ("manager".equals(searchMode)) {
				param.put("roles", UserUtil.getManagerroles());
			}
		}
		Object[] datas = DatabaseExt.queryByNamedSqlWithPage(dsName, nameSqlId, pageCond, param);
		return datas;
	}
}
