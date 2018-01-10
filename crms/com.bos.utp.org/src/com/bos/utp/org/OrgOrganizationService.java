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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.dataset.organization.impl.OmEmployeeImpl;
import com.bos.utp.dataset.organization.impl.OmPositionImpl;
import com.bos.utp.org.util.OrgHelper;
import com.bos.utp.org.util.OrgResponse;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

/**
 * 组织机构服务类
 * 
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgOrganizationService extends DASDaoSupport implements
		IOrgOrganizationService {

	private IOrgPositionService positionService = null;

	private IOrgEmployeeService employeeService = null;

	public OrgResponse addOrgOrganization(DataObject org, DataObject obj) {
		if (!validateOrgcode(org)) {
			return new OrgResponse(false, "机构代码:" + org.get("orgcode") + "已存在");
		}
		// 设置主键
		getDASTemplate().getPrimaryKey(org);
		if (obj != null && !"0".equals(String.valueOf(obj.getBigDecimal("orgid").longValue()))) {
			getOrgOrganization(obj);
		}
		OrgHelper.expandOrganizationPropertyByParent(org, obj);
		org.set("parentorgid", org.getBigDecimal("orgid").longValue());
		getDASTemplate().insertEntity(org);
		//单独设置下父机构，否则同步流程平台会导致层级出错，迭归机构出现死循环
		org.set("parentorgid", obj.get("orgid"));
		// 更新父机构
		if (obj != null && !"0".equals(String.valueOf(org.getBigDecimal("orgid").longValue()))) {
			OrgHelper.expandParentOrganizationProperty(obj);
			getDASTemplate().updateEntity(obj);
		}
		return new OrgResponse(true, "添加成功");
	}

	/**
	 * 检验机构code是否合格
	 * 
	 * @param orgcode
	 * @return true合格，false不合格
	 */
	public boolean validateOrgcode(DataObject org) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgcode", org.get("orgcode")));
		OmOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(
				OmOrganization.class, dasCriteria);
		if (orgs == null || orgs.length == 0) {
			return true;
		}
		if (!"0".equals(String.valueOf(org.getBigDecimal("orgid").longValue()))
				&& orgs[0].getOrgid().longValue() == org.getBigDecimal("orgid").longValue()) {// 修改的情况，只能为1个
			return orgs.length == 1;
		} else {// 新增,必定没有
			return orgs.length == 0;
		}
	}

	public void deleteOrgOrganization(DataObject[] orgs) {
		if (orgs == null)
			return;
		for (DataObject org : orgs) {
			// 删除机构和人员的关联关系
			if (employeeService != null) {
				employeeService.deleteEmpAndOrgRelationship(org);
			}
			// 删除子机构，子岗位
			deleteSubOrgByParent(org);
			getDASTemplate().deleteEntity(org);
		}
	}

	/**
	 * 根据父机构删除子机构 删除子机构规则： 1.删除子岗位，删除子岗位之前必须先删除子岗位关联的人员关系
	 * 2.删除子机构：删除子机构之前，先级联删除其对应的子机构相关内容
	 * 
	 * @param parentOrg
	 *            父机构
	 */
	private void deleteSubOrgByParent(DataObject parentOrg) {
		// 删除子机构岗位，删除子岗位之前先删除子岗位关联的人员关系
		if (positionService != null) {
			positionService.deleteOrgPositionCascadeByParentOrg(parentOrg);
		}
		// 删除子机构
		DataObject[] children = querySubOrgs(parentOrg.getBigDecimal("orgid").toString());
		if (children != null) {
			deleteOrgOrganization(children);
		}
	}

	public void getOrgOrganization(DataObject org) {
		getDASTemplate().expandEntity(org);
	}

	/**
	 * 获取子机构同时获取父机构信息
	 * 
	 * @param child
	 */
	public OmOrganization getOrgOrganizationWithParent(OmOrganization child) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgid", child.getOrgid()));
		dasCriteria.addAssociation("omOrganization");
		OmOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(
				OmOrganization.class, dasCriteria);
		if (orgs != null && orgs.length == 1) {
			return orgs[0];
		}
		return OmOrganization.FACTORY.create();
	}

	public OmOrganization[] queryOrgOrganizations(CriteriaType criteriaType,
			PageCond pageCond) {
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(
				OmOrganization.class, dasCriteria, pageCond);
	}

	/**
	 * 查出所有的机构
	 * 
	 * @return
	 */
	public OmOrganization[] queryAllOrganizations() {
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(OmOrganization.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteria);
		return getDASTemplate().queryEntitiesByCriteriaEntity(
				OmOrganization.class, dasCriteria);
	}

	public int countOrgOrganizations(CriteriaType criteria) {
		criteria.set_entity(OmOrganization.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteria);
		return getDASTemplate().count(dasCriteria);
	}

	/**
	 * 查询机构下的所有子机构，参数orgid为空时返回顶级机构
	 * 
	 * @param orgid
	 * @return
	 */
	public DataObject[] querySubOrgs(String orgid) {
		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmOrganization.QNAME);
		if (StringUtils.isBlank(orgid)) {
			// orgid为空则查询顶级机构
			dasCriteria.add(ExpressionHelper.isNull("omOrganization"));
		} else {
			dasCriteria.add(ExpressionHelper.eq("omOrganization.orgid", orgid));
		}
		dasCriteria.asc("orgid");

		// OmOrganization[] orgs =
		// getDASTemplate().queryEntitiesByCriteriaEntity(
		// OmOrganization.class, dasCriteria);
		DataObject[] orgs = GitUtil.queryEntitiesByCriteriaEntity(
				getDASTemplate(), dasCriteria);

		return orgs;
	}

	/**
	 * 查询机构下的所有岗位
	 * 
	 * @param orgid
	 * @return
	 */
	public OmPosition[] queryPositionsOfOrg(String orgid) {
		if (StringUtils.isBlank(orgid)) {
			return new OmPosition[0];
		}

		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("omOrganization.orgid", orgid));
		dasCriteria.add(ExpressionHelper.isNull("omPosition.positionid"));

		// OmPosition[] positions = getDASTemplate()
		// .queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		OmPosition[] positions = GitUtil.queryEntitiesByCriteriaEntity(
				getDASTemplate(), OmPositionImpl.class, dasCriteria);

		return positions;
	}

	/**
	 * 查询在机构下且未分配到此机构下级岗位的员工
	 * 
	 * @param orgid
	 * @return
	 */
	public OmEmployee[] queryEmployeesOfOrgNotInPosition(String orgid) {
		if (StringUtils.isBlank(orgid)) {
			return new OmEmployee[0];
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgid", orgid);
		// OmEmployee[] emps =
		// getDASTemplate().queryByNamedSql(OmEmployee.class,
		// "com.bos.utp.org.organization.select_orgemp", map);
		OmEmployee[] emps = GitUtil.queryByNamedSql(getDASTemplate(),
				OmEmployeeImpl.class,
				"com.bos.utp.org.organization.select_orgemp", map);

		return emps;
	}

	/**
	 * 查询机构下的所有员工
	 * 
	 * @param orgid
	 * @return
	 */
	public OmEmployee[] queryEmployeesOfOrg(String orgid) {
		if (StringUtils.isBlank(orgid)) {
			return new OmEmployee[0];
		}

		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmEmployee.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgid", orgid));

		OmEmployee[] emps = getDASTemplate().queryEntitiesByCriteriaEntity(
				OmEmployee.class, dasCriteria);

		return emps;
	}

	public OrgResponse updateOrgOrganization(DataObject org) {
		// if (!validateOrgcode(org)) {
		// return new OrgResponse(false, "不能修改为已存在的机构代码:" + org.getOrgcode());
		// }
		getDASTemplate().updateEntity(org);
		
		return new OrgResponse(true, "修改成功");
	}

	public void deleteOrgOrganization(String id) {
		if (!StringUtils.isBlank(id)) {
			OmOrganization org = OmOrganization.FACTORY.create();
			org.setOrgid(new BigDecimal(id));
			deleteOrgOrganization(new OmOrganization[] { org });
		}
	}

	public void setPositionService(IOrgPositionService positionService) {
		this.positionService = positionService;
	}

	public void setEmployeeService(IOrgEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}