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

import java.util.List;
import java.util.Map;

import com.bos.utp.framework.application.AcApplication;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import commonj.sdo.DataObject;

/**
 * TODO 此处填写 class 信息
 *
 * @author fangwl (mailto:fangwl@primeton.com)
 */
public interface IAppApplicationService{

	/**
	 *  新增应用
	 * @param appApplication AppApplication
	 */
	void addAppApplication(AcApplication appApplication);

	/**
	 *  删除应用
	 * @param appApplications AppApplication[]
	 */
	void deleteAppApplication(AcApplication[] appApplications);

	/**
	 *  根据模板获取应用
	 * @param appApplication AppApplication
	 */
	void getAppApplication(AcApplication appApplication);

	/**
	 * 分页查询
	 * @param criteria
	 * @param pageCond
	 * @return
	 */
	AcApplication[] queryAppApplications(CriteriaType criteria,
			PageCond pageCond);

	/**
	 * 修改应用
	 * @param appApplication
	 */
	void updateAppApplication(AcApplication appApplication);
	
	/**
	 * 查询应用记录总数
	 * @param criteria
	 * @return
	 */
	int countAppApplication(CriteriaType criteria);
	
	/**
	 * 查询所有应用
	 * @param criteria
	 * @return
	 */
	AcApplication[] queryAllAppApplications(CriteriaType criteria);
	
	/**
	 * 获取应用节点
	 * @param application
	 * @return
	 */
	List<Map> getApplicationNode(DataObject[] application);
	
	
	/**
	 * 获取工作组节点
	 * @param functionGroup
	 * @return
	 */
	List<Map> getFunctionGroupNode(DataObject[] functionGroup);
	
	
	/**
	 * 获取子功能组和功能节点
	 * @param functionGroup
	 * @param function
	 * @return
	 */
	List<Map> getSubFunctionGroupAndFunctionNode(DataObject[] functionGroup, DataObject[] function);
	
	/**获取功能节点
	 * @param functionGroup
	 * @return
	 */
	List<Map> getFunctionNode(DataObject[] functionGroup);
	
	/**
	 * 获取应用树，静态树
	 * @param application
	 * @param functionGroup
	 * @param function
	 * @return
	 */
	List<Map> getApplicationTree(DataObject[] application, DataObject[] functionGroup, DataObject[] function);
	
	/**
	 * 获取应用树根节点
	 * @param application
	 * @return
	 */
	List<Map> getApplicationRoot(DataObject[] application) ;
	
	/**
	 * 根据应用id删除应用
	 * @param id
	 */
	void deleteApplicationById(String id);

}
