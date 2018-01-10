/**
 * 
 */
package com.bos.accInfo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.bos.bizApply.ProcessParam;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-10-24 16:43:53
 *
 */
@Bizlet("")
public class GetContractInfoProcessId {
	
	@Bizlet("")
	public Object[] getProcessId(String contractId) {
		String processId = null;
		Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bizApply.bizApply.getContractInfoProcessid", contractId);
		if (null != bizDataObject && bizDataObject.length > 0) {
			DataObject bizData = (DataObject) bizDataObject[0];
			processId = bizData.getString("PROCESSID");
		}
		Map map = new HashMap();
		map.put("processInstId", processId);
		Object[] detailInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.bps.dataset.query.queryWorkItemsByProcessId", map);
		DataObject[] obj = new DataObject[detailInfo.length];
		SimpleDateFormat perdate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		for (int i = 0; i < detailInfo.length; i++) {
			DataObject detailObj = (DataObject) detailInfo[i];
			detailObj.set("performtime", perdate.format(detailObj.get("performtime")));
			detailObj.set("postName", detailObj.get("orgName")+"-"+detailObj.get("postName"));
			if(detailObj.get("nextPostName")==null||"".equals(detailObj.get("nextPostName"))){
				detailObj.set("nextPostName", "");
			}else{
				detailObj.set("nextPostName", detailObj.get("nextOrgName")+"-"+detailObj.get("nextPostName"));
			}
			obj[i] = detailObj;
		}
		Object[] detailInfo2 = obj;
		return detailInfo2;
	}

}
