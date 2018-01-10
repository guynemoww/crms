package com.bos.bps.dao;


import com.bos.bps.util.FlowConstants;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;

/**
 * @author lijianfei
 * @date 2014-03-18 16:36:42
 *
 */
public class BusinessParameterDAO {

	/**
	 * 查询流程业务参数配置数据
	 * @param workitemMappingId 节点主键
	 * @return
	 */
	public static DataObject[] queryBusinessParameter(String workitemMappingId){
		
		DataObject data = DataObjectUtil.createDataObject(FlowConstants.BUSINESSPARAMETER_URL);
		data.set("isShow","true");
		data.set("workitemMappingId",workitemMappingId);
		DataObject[] datas=DatabaseUtil.queryEntitiesByTemplate("crms",data);

		
		return datas;
	}
}
