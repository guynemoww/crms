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
package com.bos.utp.org.party.manager;

import static com.bos.utp.org.util.IOrgConstants.EMPID_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.EMP_REF_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.ORGID_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.ORG_REF_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.POSITIONID_PROPERTY;

import java.util.ArrayList;
import java.util.List;


import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmporg;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.tools.IConstants;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.spring.DASDaoSupport;

/**
 * 组织机构信息管理，提供对组织机构数据实体的数据库操作（持久化）
 *
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
@SuppressWarnings("unused")
public class DefaultOrgManager extends DASDaoSupport {

	/**	springBean名称标识 */
	public static final String SPRING_BEAN_NAME = "DefaultOrgManagerBean";
	
	/**
	 * 根据租户ID查询所有组织机构信息列表
	 * @param tenantID 租户ID
	 * @return 组织机构对象信息列表
	 */
	public OmOrganization[] getAllOrgs(String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmOrganization.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmOrganization.class, criteria);
	}

	/**
	 * 根据机构ID和租户ID查询组织机构信息
	 * 
	 * @param orgid 机构ID
	 * @param tenantID 租户ID
	 * @return 组织机构对象信息，未找到返回null
	 */
	public OmOrganization getOrgById(String orgid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmOrganization.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(ORGID_PROPERTY, orgid));
		OmOrganization[] orgArray = getDASTemplate().queryEntitiesByCriteriaEntity(OmOrganization.class, criteria);
		if (orgArray != null && orgArray.length == 1) {
			return orgArray[0];
		}
		return null;
	}

	/**
	 * 根据租户ID查询组织结构根对象信息列表
	 * 
	 * @param tenantID 租户ID
	 * @return 组织结构对象信息列表
	 */
	public OmOrganization[] getRootOrgs(String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmOrganization.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.isNull(ORG_REF_PROPERTY));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmOrganization.class, criteria);
	}

	/**
	 * 根据父机构ID和租户ID获取子机构列表
	 * @param parentOrgid
	 * @param tenantID
	 * @return
	 */
	public OmOrganization[] getSubOrgs(String parentOrgid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmOrganization.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(ORG_REF_PROPERTY + "." + ORGID_PROPERTY, parentOrgid));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmOrganization.class, criteria);
	}

	/**
	 * 根据机构ID和租户ID获取父机构
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OmOrganization getParentOrg(String orgid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmOrganization.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(ORGID_PROPERTY, orgid));
		criteria.addAssociation(ORG_REF_PROPERTY);
		OmOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OmOrganization.class, criteria);
		if (orgs != null && orgs.length == 1) {
			OmOrganization org = orgs[0];
			return org.getOmOrganization();
		}
		return null;
	}

	/**
	 * 根据机构ID和租户ID获取员工列表
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OmEmployee[] getEmpsByOrg(String orgid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmEmporg.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(ORGID_PROPERTY, orgid));
		criteria.addAssociation(EMP_REF_PROPERTY);
		criteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + IConstants.TENANT_PROPERTY, tenantID));
		OmEmporg[] emporgs = getDASTemplate().queryEntitiesByCriteriaEntity(OmEmporg.class, criteria);
		getDASTemplate().expandEntitiesRelation(emporgs, EMP_REF_PROPERTY); // 虽然用了addAssociation()，此处也必须展开关联实体，否则报ClassCastException

		// 将员工机构关系列表组装为员工列表
		List<OmEmployee> emps = new ArrayList<OmEmployee>(emporgs.length);
		for(OmEmporg emporg : emporgs) {
			emps.add(emporg.getOmEmployee());
		}
		return emps.toArray(new OmEmployee[emps.size()]);
	}

	/**
	 * 通过员工ID和租户ID获取员工所在父机构列表
	 * @param empid
	 * @param tenantID
	 * @return
	 */
	public OmOrganization[] getParentOrgsByEmp(String empid, String tenantID) {
		// 先查出员工机构关系
		IDASCriteria criteria = getDASTemplate().createCriteria(OmEmporg.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		OmEmporg[] emporgs = getDASTemplate().queryEntitiesByCriteriaEntity(OmEmporg.class, criteria);
		
		// 将员工机构关系列表组装为机构列表
		List<OmOrganization> orgs = new ArrayList<OmOrganization>(emporgs.length);
		for(OmEmporg emporg : emporgs) {
			OmOrganization org = OmOrganization.FACTORY.create();
			//org.setOrgid(emporg.getOrgid());
			getDASTemplate().expandEntity(org);
			orgs.add(org);
		}
		return orgs.toArray(new OmOrganization[orgs.size()]);
	}

	/**
	 * 根据机构ID和租户ID获取机构下的岗位
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OmPosition[] getPositionsByOrg(String orgid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(ORG_REF_PROPERTY + "." + ORGID_PROPERTY, orgid));
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, criteria);
		return positions;
	}

	/**
	 * 根据岗位ID和租户ID获取岗位所在的父机构
	 * @param positionid
	 * @param tenantID
	 * @return
	 */
	public OmOrganization getParentOrgByPosition(String positionid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OmPosition.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(POSITIONID_PROPERTY, positionid));
		criteria.addAssociation(ORG_REF_PROPERTY);
		OmPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OmPosition.class, criteria);
		if(positions != null && positions.length == 1) {
			//return positions[0].getOmOrganization();
		}
		return null;
	}
	
}
