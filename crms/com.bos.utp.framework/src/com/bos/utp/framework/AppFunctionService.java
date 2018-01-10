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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import com.bos.utp.framework.application.AcFunction;
	    

import com.bos.utp.tools.IAuthConstants;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.spring.DASDaoSupport;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;

/**
 * TODO 此处填写 class 信息
 *
 * @author fangwl (mailto:fangwl@primeton.com)
 */
@SuppressWarnings("unused")
public class AppFunctionService extends DASDaoSupport implements IAppFunctionService{
	private Logger log = TraceLoggerFactory.getLogger(AppFunctionService.class);
	
	public void addAppFunction(AcFunction appFunction){
		//getDASTemplate().getPrimaryKey(appFunction);
		//appFunction.setTenant_id(TenantManager.getCurrentTenantID());
		try {
			getDASTemplate().insertEntity(appFunction);
			getDASTemplate().expandEntity(appFunction);
//			/ResourceRuntimeManager.getInstance().registerManagedResource(adapt(appFunction));
		} catch (Throwable t) {
			log.error("Insert function [funcode=" + appFunction.getFunccode() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}

	public void deleteAppFunction(AcFunction[] appFunctions ){
		for(DataObject appFunction:appFunctions){
			try {
				getDASTemplate().deleteEntityCascade(appFunction);
				////ResourceRuntimeManager.getInstance().unRegisterManagedResource(appFunction.getString("funccode"), IAuthConstants.FUNCTION_TO_RESOURCE_TYPE);
			} catch (Throwable t) {
				log.error("Delete function [funcode=" + appFunction.get("funccode") + "] failure, please do the operation again or contact the sysadmin.", t);
			}
		}
	}


	public void getAppFunction(AcFunction appFunction){
		getDASTemplate().expandEntity(appFunction);
	}
	public int validateAppFunction(AcFunction appFunction){
		return getDASTemplate().expandEntity(appFunction);
	}

    public void updateAppFunction(AcFunction appFunction){
    	//appFunction.setTenant_id(TenantManager.getCurrentTenantID());
    	try {
		    getDASTemplate().updateEntity(appFunction);
		    getDASTemplate().expandEntity(appFunction);
//		  //ResourceRuntimeManager.getInstance().updateRegisteredManagedResource(adapt(appFunction));
	    } catch (Throwable t) {
			log.error("Update function [funcode=" + appFunction.get("funccode") + "] failure, please do the operation again or contact the sysadmin.", t);
		}
    }
	
	public int countAppFunction(CriteriaType criteria) {
		criteria.set_entity(AcFunction.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	public AcFunction[]  queryAppFunctions(CriteriaType criteria, PageCond pageCond){
		criteria.set_entity(AcFunction.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFunction[] results = getDASTemplate().queryEntitiesByCriteriaEntityWithPage(AcFunction.class, dasCriteria, pageCond);
		return results;
	}
	
	public AcFunction[]  queryAppFunctions(CriteriaType criteria){
		criteria.set_entity(AcFunction.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFunction[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFunction.class, dasCriteria);
		return results;
	}

	public void deleteFunctionById(String id) {
		AcFunction appFunction = AcFunction.FACTORY.create();
		appFunction.setFunccode(id);
		try{
			Map<String, String> map = new HashMap<String, String>();
			map.put("sql", "delete from AC_FUNCTION where FUNCCODE='" +
					id + "'");
			DatabaseExt.executeNamedSql("default",
					"com.bos.utp.tools.common.common_update", map);
//			getDASTemplate().deleteEntityCascade(appFunction);
		} catch (Throwable t) {
			log.error("Delete function [funcode=" + appFunction.get("funccode") + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}


	public void initFunctions() {
		AcFunction[] functions = queryAllFunctions();
		for(AcFunction function : functions){
//			ResourceRuntimeManager.getInstance().registerManagedResource(adapt(function));
		}
	}
	
	public void updateResoucesBatch(AcFunction[] appfunctions){
		for(
		AcFunction function : appfunctions){
			////ResourceRuntimeManager.getInstance().updateRegisteredManagedResource(adapt(function));
		}
	}
	
	public AcFunction[] queryAllFunctions() {
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(AcFunction.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFunction[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFunction.class, dasCriteria);
		return results;
	}
	
 

	public AcFunction[] getFunctionsByAppId(String appid) {
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(AcFunction.QNAME);
		criteria.set("_expr[1]/acFuncgroup.acApplication.appid", appid);
		criteria.set("_expr[1]/_op", "=");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFunction[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFunction.class, dasCriteria);
		return results;
	}
	
	public AcFunction[] getFunctionsByFuncGroupIds(String[] funcGroupIds){
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(AcFunction.QNAME);
		criteria.set("_expr[1]/acFuncgroup.funcgroupid", funcGroupIds);
		criteria.set("_expr[1]/_op","in");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFunction[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFunction.class, dasCriteria);
		return results;
	}
}
