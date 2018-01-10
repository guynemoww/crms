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
import com.bos.utp.dataset.organization.OmEmpposition;
import com.bos.utp.dataset.organization.OmPosition;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;


/**
 * 岗位和人员的关系服务类
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public interface IOrgEmppositionService{

	/**
	 *
	 * @param orgEmpposition OrgEmpposition
	 */
	void addOrgEmpposition(OmEmpposition orgEmpposition);
	
	/**
	 * @param orgEmppositions
	 */
	void addOrgEmpposition(OmEmpposition[] orgEmppositions);

	/**
	 *
	 * @param orgEmppositions OrgEmpposition[]
	 */
	void deleteOrgEmpposition(OmEmpposition[] orgEmppositions);

	/**
	 *
	 * @param orgEmpposition OrgEmpposition[]
	 */
	void getOrgEmpposition(OmEmpposition orgEmpposition);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return OrgEmpposition[]
	 */
	OmEmpposition[] queryOrgEmppositions(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 *
	 * @param orgEmpposition OrgEmpposition[]
	 */
	void updateOrgEmpposition(OmEmpposition orgEmpposition);

	/**
	 * 根据岗位删除岗位关联的人员关系
	 * @param position 岗位
	 */
	void deleteEmppositionsByPosition(OmPosition position);

	/**
	 * 根据员工删除员工关联的岗位关系
	 * @param emp
	 */
	void deleteEmppositionsByEmp(OmEmployee emp);

	/**
	 * 根据员工ID和父机构ID，查询出子岗位与员工的关系
	 * @param empId 员工ID
	 * @param parentOrgId 父机构ID
	 * @return
	 */
	OmEmpposition[] queryOrgEmppositionsOfEmp(String empId, String parentOrgId);
	
	/**
	 * 根据岗位查询所有的人员列表
	 * @param position
	 * @return 人员列表
	 */
	OmEmployee[] queryEmpsByPosition(OmPosition position);
}
