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
package com.bos.utp.org;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.org.util.OrgResponse;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import commonj.sdo.DataObject;


/**
 * 员工服务类
 *
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public interface IOrgEmployeeService{
	
	/**
	 * 添加员工
	 * @param employee
	 * @param user
	 * @param org
	 * @return 返回对象,标识符flag为true，添加成功，false，为添加失败；message为提示信息
	 */
	OrgResponse addOrgEmployee(DataObject employee, DataObject user, DataObject org);

	/**
	 *
	 * @param employees OrgEmployee[]
	 */
	void deleteOrgEmployee(OmEmployee[] employees);
	
	/**
	 * 根据id，父节点删除人员
	 * @param id
	 * @param parentId
	 * @param parentType
	 */
	void deleteOrgEmployee(String id, String parentId, String parentType,String isDeleteCascade);

	/**
	 * @param employee OrgEmployee[]
	 */
	void getOrgEmployee(OmEmployee employee);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return OrgEmployee[]
	 */
	OmEmployee[] queryOrgEmployees(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 * 更新员工
	 * @param employee
	 * @param user
	 * @return 返回状态对象
	 */
	OrgResponse updateOrgEmployee(DataObject employee, DataObject user);
	
	/**
	 * @param criteria
	 * @return
	 */
	int countOrgEmployees(CriteriaType criteria);

	/**
	 * 根据机构删除人员和机构的关联关系
	 * @param org
	 */
	void deleteEmpAndOrgRelationship(DataObject org);
	
	/**
	 * 查询出岗位下已添加的员工
	 * @param userid
	 * @param empname
	 * @param positionId
	 * @param page
	 * @return
	 */
	OmEmployee[] queryEmpsInPosition(String userid, String empname, String positionId, PageCond page);
	
	
	/**
	 * 查询出岗位下允许添加到岗位的员工
	 * @param userid
	 * @param empname
	 * @param positionId
	 * @param page
	 * @return
	 */
	OmEmployee[] queryEmpsAllowAddInPosition(String userid, String empname, String positionId, PageCond page);
}