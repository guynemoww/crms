/**
 * 
 */
package com.bos.grt.collateralparameter;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-10-18 21:11:09
 *
 */
@Bizlet("")
public class BiztypeSubcontract {
	
	@Bizlet("")
	public String queryBiztypeSubcontract(String subcontractId){
		String bizType = null;
		String bizty = null;
		Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bizApply.bizApply.queryBiztypeSubcontract", subcontractId);
		if (null != bizDataObject && bizDataObject.length > 0) {
			DataObject bizData = (DataObject) bizDataObject[0];
			bizType = bizData.getString("BIZ_TYPE");
		}
		if("01".equals(bizType)||"04".equals(bizType)){//单笔、个人
			bizty = "1";
		}else if("02".equals(bizType)){//综合授信
			bizty = "2";
		}
		return bizty;
	}
}
