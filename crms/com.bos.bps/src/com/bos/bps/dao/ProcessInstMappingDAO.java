package com.bos.bps.dao;

import com.bos.bps.util.FlowConstants;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

/**
 * 流程模板映射表操作类
 * @author lijianfei
 * @date 2014-03-18 16:36:06
 *
 */
public class ProcessInstMappingDAO {

	
	/**
	 * 根据模板名称，扩展一条数据
	 * @param processInstId
	 * @return
	 */
	public static DataObject getProcessInstanceByName(String processDefName){
		
		DataObject pim= DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTMAPPING_URL);
		pim.set("templateName", processDefName);
		DatabaseUtil.expandEntityByTemplate("default", pim, pim);
		return pim;
	}
	
	/**
	 * 根据模块类型，模板名称，获取模板相关配置信息
	 * @param templateName
	 * @param busiType
	 * @return
	 */
	public static DataObject getProcessMappingByType(String templateName,String modelType){
		
		DataObject criteriaType = DataFactory.INSTANCE.create("com.primeton.das.criteria","criteriaType");
		criteriaType.set("_entity",FlowConstants.PROCESSINSTMAPPING_URL);
		criteriaType.set("_expr[1]/templateName",templateName);
		criteriaType.set("_expr[1]/_op","like");
		criteriaType.set("_expr[1]/_likeRule","all");
		criteriaType.set("_expr[2]/bizType",modelType);
		DataObject[] datas = DatabaseUtil.queryEntitiesByCriteriaEntity(GitUtil.DEFAULT_DS_NAME,criteriaType);
		
		return datas[0];
	}
}
