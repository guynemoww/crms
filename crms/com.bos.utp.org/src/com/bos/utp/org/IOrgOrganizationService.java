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
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.org.util.OrgResponse;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import commonj.sdo.DataObject;

/**
 * 组织机构服务类
 * 
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public interface IOrgOrganizationService {

	/**
	 * 添加机构
	 * 
	 * @param orgOrganization
	 * @return 添加机构状态对象
	 */
	OrgResponse addOrgOrganization(DataObject orgOrganization,
			DataObject obj);

	/**
	 * 
	 * @param orgOrganizations
	 *            OrgOrganization[]
	 */
	void deleteOrgOrganization(DataObject[] orgOrganizations);

	/**
	 * 根据id删除机构
	 * 
	 * @param id
	 */
	void deleteOrgOrganization(String id);

	/**
	 * 
	 * @param orgOrganization
	 *            OrgOrganization[]
	 */
	void getOrgOrganization(DataObject orgOrganization);

	/**
	 * 
	 * @param criteria
	 *            CriteriaType
	 * @param page
	 *            PageCond
	 * @return OrgOrganization[]
	 */
	OmOrganization[] queryOrgOrganizations(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 * 修改机构对象
	 * 
	 * @param orgOrganization
	 * @return 修改机构状态
	 */
	OrgResponse updateOrgOrganization(DataObject orgOrganization);

	/**
	 * @param criteria
	 * @return
	 */
	int countOrgOrganizations(CriteriaType criteria);

	/**
	 * 查询机构下的所有子机构，参数orgid为空时返回顶级机构
	 * 
	 * @param orgid
	 * @return
	 */
	DataObject[] querySubOrgs(String orgid);

	/**
	 * 查询机构下的所有岗位
	 * 
	 * @param orgid
	 * @return
	 */
	OmPosition[] queryPositionsOfOrg(String orgid);

	/**
	 * 查询在机构下且未分配到此机构下级岗位的员工
	 * 
	 * @param orgid
	 * @return
	 */
	OmEmployee[] queryEmployeesOfOrgNotInPosition(String orgid);

	/**
	 * 查询机构下的所有员工
	 * 
	 * @param orgid
	 * @return
	 */
	OmEmployee[] queryEmployeesOfOrg(String orgid);

}