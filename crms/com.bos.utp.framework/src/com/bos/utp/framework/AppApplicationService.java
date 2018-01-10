/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bos.utp.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NumberUtils;

import com.bos.utp.framework.application.AcApplication;
import com.bos.utp.framework.application.AcFunction;
import com.bos.utp.framework.constants.IAppConstants;
import com.bos.utp.tools.IconCls;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.spring.BeanFactory;
import com.eos.spring.DASDaoSupport;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;

/**
 * TODO 此处填写 class 信息
 *
 * @author fangwl (mailto:fangwl@primeton.com)
 */
public class AppApplicationService extends DASDaoSupport implements IAppApplicationService{
	private Logger log = TraceLoggerFactory.getLogger(AppApplicationService.class);
	
	public void addAppApplication(AcApplication appApplication){
		getDASTemplate().getPrimaryKey(appApplication);
		//appApplication.setTenant_id(TenantManager.getCurrentTenantID());
		try{
			getDASTemplate().insertEntity(appApplication);
		} catch (Throwable t) {
			log.error("Insert application [appid=" + appApplication.getAppid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}

	public void deleteAppApplication(AcApplication[] appApplications ){
		for(DataObject appApplication:appApplications){
			try{
				getDASTemplate().deleteEntityCascade(appApplication);
			} catch (Throwable t) {
				log.error("Delete application [appid=" + appApplication.get("appid") + "] failure, please do the operation again or contact the sysadmin.", t);
			}
		}
	}


	public void getAppApplication(AcApplication appApplication){
		getDASTemplate().expandEntity(appApplication);
	}


	public AcApplication[]  queryAppApplications(CriteriaType criteria, PageCond pageCond){
		criteria.set_entity(AcApplication.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcApplication[] results = getDASTemplate().queryEntitiesByCriteriaEntityWithPage(AcApplication.class, dasCriteria, pageCond);
		return results;
	}


    public void updateAppApplication(AcApplication appApplication){
	    //更新资源库
	    BeanFactory beanFactory = BeanFactory.newInstance();
	    IAppFunctionService functionService =  beanFactory.getBean("AppFunctionBean");
	    AcFunction[] functions = functionService.getFunctionsByAppId(String.valueOf(appApplication.getAppid()));
	    try{
	    	getDASTemplate().updateEntity(appApplication);
		    functionService.updateResoucesBatch(functions);
	    } catch (Throwable t) {
			log.error("Update application [appid=" + appApplication.getAppid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	    
    }

	public int countAppApplication(CriteriaType criteria) {
		criteria.set_entity(AcApplication.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}

	public AcApplication[] queryAllAppApplications(CriteriaType criteria) {
		criteria.set_entity(AcApplication.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcApplication[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcApplication.class, dasCriteria);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Map> getApplicationTree(DataObject[] application, DataObject[] functionGroup, DataObject[] function) {
		List<Map> treeList = new ArrayList<Map>();
		
		//构造applicationTree
		for(DataObject dataObject : application){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_APPLICATION + "_" + dataObject.get("appid"));
			map.put("realId", dataObject.get("appid"));
			map.put("text", dataObject.get("appname"));
			map.put("type", IAppConstants.TYPE_APPLICATION);
			map.put("iconCls", IconCls.APPLICATION);
			treeList.add(map);
		}
		//构造functiongroupTree
		for(DataObject dataObject : functionGroup){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_FUNCGROUP + "_" + dataObject.get("funcgroupid"));
			map.put("realId", dataObject.get("funcgroupid"));
			map.put("text", dataObject.get("funcgroupname"));
			map.put("type", IAppConstants.TYPE_FUNCGROUP);
			map.put("pid", IAppConstants.TYPE_APPLICATION + "_" + dataObject.getDataObject("appApplication").get("appid"));
			map.put("iconCls", IconCls.FUNCTION_GROUP_CLOSE);
			treeList.add(map);
		}
		//构造functionTree
		for(DataObject dataObject : function){
			Map map = new HashMap();
			map.put("id",IAppConstants.TYPE_FUNCTION + "_" + dataObject.get("funccode"));
			map.put("realId", dataObject.get("funccode"));
			map.put("text", dataObject.get("funcname"));
			map.put("type", IAppConstants.TYPE_FUNCTION);
			map.put("pid", IAppConstants.TYPE_FUNCGROUP + "_" + dataObject.getDataObject("appFuncgroup").get("funcgroupid"));
			map.put("iconCls", IconCls.MENU_ROOT);
			map.put(IAppConstants.FUNCTION_IS_CHECK, String.valueOf(dataObject.get("ischeck")));
			treeList.add(map);
		}
		return treeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map> getApplicationRoot(DataObject[] application) {
		List<Map> nodeList = new ArrayList<Map>();
		Map rootMap = new HashMap();
		rootMap.put("id","root_root");
		rootMap.put("realId", "root");
		rootMap.put("text", IAppConstants.APP_TREE_ROOT);
		rootMap.put("type", "root");
		rootMap.put("iconCls", IconCls.APPLICATION_HOME);
		rootMap.put("isLeaf", false);
		rootMap.put("expanded", true);
		nodeList.add(rootMap);
		//构造应用List
		for(DataObject dataObject : application){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_APPLICATION + "_" + dataObject.get("appid"));
			map.put("realId", dataObject.get("appid"));
			map.put("text", dataObject.get("appname"));
			map.put("type", IAppConstants.TYPE_APPLICATION);
			map.put("pid", "root_root");
			map.put("iconCls", IconCls.APPLICATION);
			map.put("isLeaf", false);
			map.put("expanded", false);
			nodeList.add(map);
		}
		return nodeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getApplicationNode(DataObject[] application) {
		List<Map> nodeList = new ArrayList<Map>();
		//构造应用List
		for(DataObject dataObject : application){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_APPLICATION + "_" + dataObject.get("appid"));
			map.put("realId", dataObject.get("appid"));
			map.put("text", dataObject.get("appname"));
			map.put("type", IAppConstants.TYPE_APPLICATION);
			map.put("pid", "root_root");
			map.put("iconCls", IconCls.APPLICATION);
			map.put("isLeaf", false);
			map.put("expanded", false);
			nodeList.add(map);
		}
		return nodeList;
	}
    
	@SuppressWarnings("unchecked")
	public List<Map> getFunctionGroupNode(DataObject[] functionGroup) {
		List<Map> nodeList = new ArrayList<Map>();
		//构造功能组List
		for(DataObject dataObject : functionGroup){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_FUNCGROUP + "_" + dataObject.get("funcgroupid"));
			map.put("realId",dataObject.get("funcgroupid"));
			map.put("text", dataObject.get("funcgroupname"));
			map.put("type", IAppConstants.TYPE_FUNCGROUP);
			map.put("pid", IAppConstants.TYPE_APPLICATION + "_"+dataObject.getDataObject("acApplication").get("appid"));
			map.put("iconCls", IconCls.FUNCTION_GROUP_CLOSE);
			map.put("isLeaf", false);
			map.put("expanded", false);
			nodeList.add(map);
		}
		return nodeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getSubFunctionGroupAndFunctionNode(DataObject[] functionGroup, DataObject[] function) {
		List<Map> nodeList = new ArrayList<Map>();
		//构造子功能组List
		for(DataObject dataObject : functionGroup){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_SUBFUNCGROUP + "_" + dataObject.get("funcgroupid"));
			map.put("realId",dataObject.get("funcgroupid"));
			map.put("text", dataObject.get("funcgroupname"));
			map.put("type", IAppConstants.TYPE_SUBFUNCGROUP);
			map.put("pid", IAppConstants.TYPE_APPLICATION + "_" + dataObject.getDataObject("acFuncgroup").get("funcgroupid"));
			map.put("iconCls", IconCls.FUNCTION_GROUP_CLOSE);
			map.put("isLeaf", false);
			map.put("expanded", false);
			nodeList.add(map);
		}
		
		//构造功能List
		for(DataObject dataObject : function){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_FUNCTION + "_" + dataObject.get("funccode"));
			map.put("realId",dataObject.get("funccode"));
			map.put("text", dataObject.get("funcname"));
			map.put("type", IAppConstants.TYPE_FUNCTION);
			map.put("pid", IAppConstants.TYPE_FUNCGROUP + "_" + dataObject.getDataObject("acFuncgroup").get("funcgroupid"));
			map.put("iconCls", IconCls.MENU_ROOT);
			map.put("isLeaf", true);
			map.put("expanded", false);
			nodeList.add(map);
		}
		return nodeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map> getFunctionNode(DataObject[] function) {
		List<Map> nodeList = new ArrayList<Map>();
		//构造功能List
		for(DataObject dataObject : function){
			Map map = new HashMap();
			map.put("id", IAppConstants.TYPE_FUNCTION + "_" + dataObject.get("funccode"));
			map.put("realId",dataObject.get("funccode"));
			map.put("text", dataObject.get("funcname"));
			map.put("type", IAppConstants.TYPE_FUNCTION);
			map.put("pid", IAppConstants.TYPE_FUNCGROUP + "_" + dataObject.getDataObject("acFuncgroup").get("funcgroupid"));
			map.put("iconCls", IconCls.MENU_ROOT);
			map.put("isLeaf", true);
			map.put("expanded", false);
			nodeList.add(map);
		}
		return nodeList;
	}

	@SuppressWarnings("deprecation")
	public void deleteApplicationById(String id) {
		AcApplication appApplication = AcApplication.FACTORY.create();
		appApplication.setAppid(NumberUtils.createInteger(id));
		try{
			getDASTemplate().deleteEntityCascade(appApplication);
		} catch (Throwable t) {
			log.error("Delete application [appid=" + appApplication.getAppid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
		
	}
}
