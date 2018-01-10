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
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

import com.bos.utp.framework.application.AcFuncgroup;
import com.bos.utp.framework.application.AcFunction;
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
public class AppFuncgroupService extends DASDaoSupport implements IAppFuncgroupService{
	private Logger log = TraceLoggerFactory.getLogger(AppFuncgroupService.class);
	public void addAppFuncgroup(AcFuncgroup appFuncgroup){
		//appFuncgroup.setTenant_id(TenantManager.getCurrentTenantID());
		try{
			getDASTemplate().insertEntity(appFuncgroup);
		} catch (Throwable t) {
			log.error("Insert funcgroup [funcgroupid=" + appFuncgroup.getFuncgroupid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}

	public void deleteAppFuncgroup(AcFuncgroup[] appFuncgroups ){
		for(DataObject appFuncgroup:appFuncgroups){
			try{
				getDASTemplate().deleteEntityCascade(appFuncgroup);
			} catch (Throwable t) {
				log.error("Delete funcgroup [funcgroupid=" + appFuncgroup.get("funcgroupid") + "] failure, please do the operation again or contact the sysadmin.", t);
			}
		}
	}


	public void getAppFuncgroup(AcFuncgroup appFuncgroup){
		getDASTemplate().expandEntity(appFuncgroup);
	}

    public void updateAppFuncgroup(AcFuncgroup appFuncgroup){
	    try{
	    	//更新功能组下所有功能resource
	    	updateResources(appFuncgroup);
		    getDASTemplate().updateEntity(appFuncgroup);
	    } catch (Throwable t) {
			log.error("Update funcgroup [funcgroupid=" + appFuncgroup.getFuncgroupid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
    }

	public AcFuncgroup[] queryAllAppFuncgroups(CriteriaType criteria) {
		criteria.set_entity(AcFuncgroup.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFuncgroup[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFuncgroup.class, dasCriteria);
		return results;
	}
	public int countAppFuncgroup(CriteriaType criteria) {
		criteria.set_entity(AcFuncgroup.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	public AcFuncgroup[]  queryAppFuncgroups(CriteriaType criteria, PageCond pageCond){
		criteria.set_entity(AcFuncgroup.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFuncgroup[] results = getDASTemplate().queryEntitiesByCriteriaEntityWithPage(AcFuncgroup.class, dasCriteria, pageCond);
		return results;
	}
	
	public AcFuncgroup[]  queryAppFuncgroups(CriteriaType criteria){
		criteria.set_entity(AcFuncgroup.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFuncgroup[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFuncgroup.class, dasCriteria);
		return results;
	}

	public void deleteFuncGroupById(String id) {
		AcFuncgroup appFuncgroup = AcFuncgroup.FACTORY.create();
		//appFuncgroup.setFuncgroupid(NumberUtils.createBigDecimal(id));
		appFuncgroup.setFuncgroupid(NumberUtils.createInteger(id));
		try{
			getDASTemplate().deleteEntityCascade(appFuncgroup);
		} catch (Throwable t) {
			log.error("Delete funcgroup [funcgroupid=" + appFuncgroup.getFuncgroupid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}
	
	public void getPrimaryKey(AcFuncgroup appFuncgroup){
		getDASTemplate().getPrimaryKey(appFuncgroup);
	}

	public AcFuncgroup[] getChildFuncGroups(AcFuncgroup appFuncgroup) {
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(AcFuncgroup.QNAME);
		criteria.set("_expr[1]/funcgroupseq", appFuncgroup.getFuncgroupseq());
		criteria.set("_expr[1]/_op","like");
		criteria.set("_expr[1]/_likeRule","end");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcFuncgroup[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcFuncgroup.class, dasCriteria);
		return results;
	}
	
	private void updateResources(AcFuncgroup appFuncgroup){
		AcFuncgroup[] appFuncgroups = getChildFuncGroups(appFuncgroup);
		    
	    //获得funcGroupid数组
	    String[] funcGroupIds = new String[appFuncgroups.length];
	    for(int i=0; i<appFuncgroups.length; i++){
	    	AcFuncgroup funcgroup = appFuncgroups[i];
	    	funcGroupIds[i] = String.valueOf(funcgroup.getFuncgroupid());
	    }
	    BeanFactory beanFactory = BeanFactory.newInstance();
	    IAppFunctionService functionService =  beanFactory.getBean("AppFunctionBean");
	    AcFunction[] functions = functionService.getFunctionsByFuncGroupIds(funcGroupIds);
	    //更新资源
		functionService.updateResoucesBatch(functions);
	}
	
	public void modifyFuncGroupRelation(String funcGroupId, String targetGroupId) {
		//查找当前功能组currentGroup
		AcFuncgroup currentGroup = AcFuncgroup.FACTORY.create();
		currentGroup.setFuncgroupid(NumberUtils.createInteger(funcGroupId));
		getDASTemplate().expandEntity(currentGroup);
		String currentAppId = String.valueOf(currentGroup.getAppApplication().getAppid());
		
		//查找目标功能组targetGroup
		AcFuncgroup targetGroup = AcFuncgroup.FACTORY.create();
		targetGroup.setFuncgroupid(NumberUtils.createInteger(targetGroupId));
		getDASTemplate().expandEntity(targetGroup);
		String targetAppId = String.valueOf(targetGroup.getAppApplication().getAppid());
		
		//查找当前功能组的所有子孙 funcGroups
		AcFuncgroup[] groups = getChildFuncGroups(currentGroup);
		
		//遍历funcGroups
		List<AcFuncgroup> updateList = new ArrayList<AcFuncgroup>();
		List<String> idList = new ArrayList<String>();
		for(AcFuncgroup funcGroup : groups){
			//为当前功能组
			if(StringUtils.equals(String.valueOf(funcGroup.getFuncgroupid()), funcGroupId)){
				//修改当前功能组的父功能组
				funcGroup.setAppFuncgroup(targetGroup);
				funcGroup.setFuncgroupseq(targetGroup.getFuncgroupseq() + funcGroupId + ".");
			}else{
				funcGroup.setFuncgroupseq(StringUtils.replace(funcGroup.getFuncgroupseq(), currentGroup.getFuncgroupseq(), targetGroup.getFuncgroupseq() + funcGroupId + "."));
			}
			//如果应用发生改变修改所有子孙的appid
			if(!StringUtils.equals(currentAppId, targetAppId)){
				funcGroup.setAppApplication(targetGroup.getAppApplication());
			}
			updateList.add(funcGroup);
			idList.add(String.valueOf(funcGroup.getFuncgroupid()));
		}
		//targetGroup.setSubcount(targetGroup.getSubcount().add(NumberUtils.createBigDecimal(String.valueOf(groups.length))));
		targetGroup.setSubcount(targetGroup.getSubcount());
		updateList.add(targetGroup);
		try{
			//批量更新子功能组，功能组，父功能组
			getDASTemplate().updateEntityBatch(updateList.toArray(new AcFuncgroup[updateList.size()]));
			//如果应用改变了需要更新资源
			if(!StringUtils.equals(currentAppId, targetAppId)){
				BeanFactory beanFactory = BeanFactory.newInstance();
			    IAppFunctionService functionService =  beanFactory.getBean("AppFunctionBean");
			    String[] funcGroupIds = idList.toArray(new String[idList.size()]);
			    AcFunction[] functions = functionService.getFunctionsByFuncGroupIds(funcGroupIds);
			    //更新资源
				functionService.updateResoucesBatch(functions);
			}
		} catch (Throwable t) {
			log.error("Update funcgroup failure, please do the operation again or contact the sysadmin.", t);
		}
	}
}
