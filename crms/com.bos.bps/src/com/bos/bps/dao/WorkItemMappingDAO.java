package com.bos.bps.dao;

import com.bos.bps.util.FlowConstants;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * 流程节点映射表操作类
 * @author lijianfei
 * @date 2014-03-18 16:36:06
 *
 */
@Bizlet("节点配置管理")
public class WorkItemMappingDAO {

	/**
	 * 获取默认版本节点映射信息
	 * @param processDefName 流程定义名称
	 * @param activityDefId 节点定义ID
	 * @return
	 */
	@Bizlet("获取节点映射信息")
	public static DataObject getWorkItemMapping(String processDefName,String activityDefId){
		
		//获取模板映射主键
		DataObject template = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTMAPPING_URL);
		template.set("templateName", processDefName);
		template.set("versionStatus", "1");
		DatabaseUtil.expandEntityByTemplate("default", template, template);
		DataObject wiTemplate = DataObjectUtil.createDataObject(FlowConstants.WORKITEMMAPPING_URL);
		wiTemplate.set("tbWfmProcessmapping/processMappingId", template.get("processMappingId"));
		wiTemplate.set("activityDefId", activityDefId);
		DatabaseUtil.expandEntityByTemplate("default", wiTemplate, wiTemplate);
		wiTemplate.set("templateVersion", template.get("templageVersion"));
		return wiTemplate;
	}
	
	/**
	 * 根据版本号，获取节点映射信息
	 * @param processDefName 流程定义名称
	 * @param version  模板版本号
	 * @param activityDefId 节点定义ID
	 * @return
	 */
	public static DataObject getWorkItempMappingByVersion(String processDefName,String version,String activityDefId){
		
		//获取模板映射主键
		DataObject template = DataObjectUtil.createDataObject(FlowConstants.PROCESSINSTMAPPING_URL);
		template.set("templateName", processDefName);
		template.set("templageVersion", version);
		DatabaseUtil.expandEntityByTemplate("default", template, template);
		DataObject wiTemplate = DataObjectUtil.createDataObject(FlowConstants.WORKITEMMAPPING_URL);
		wiTemplate.set("tbWfmProcessmapping/processMappingId", template.get("processMappingId"));
		wiTemplate.set("activityDefId", activityDefId);
		DatabaseUtil.expandEntityByTemplate("default", wiTemplate, wiTemplate);
		return wiTemplate;
		
	}
	
}
