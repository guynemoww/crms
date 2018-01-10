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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmporg;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.org.util.IOrgConstants;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;

import commonj.sdo.DataObject;

/**
 * 人员机构关系服务类
 * 
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgEmporgService extends DASDaoSupport implements
		IOrgEmporgService {

	private OrgEmppositionService empPositionService = null;

	public void addOrgEmporg(OmEmporg orgEmporgAssosiation) {
		// orgEmporgAssosiation.setTenantid(TenantManager.getCurrentTenantID());
		orgEmporgAssosiation.setIsmain(IOrgConstants.IS_MAIN_YES);
		getDASTemplate().insertEntity(orgEmporgAssosiation);
	}

	public void deleteOrgEmporg(OmEmporg[] orgEmporgs) {
		if (orgEmporgs == null)
			return;
		for (DataObject orgEmporg : orgEmporgs) {
			getDASTemplate().deleteEntityCascade(orgEmporg);
		}
	}

	public void getOrgEmporg(OmEmporg orgEmporg) {
		getDASTemplate().expandEntity(orgEmporg);
	}

	public OmEmporg[] queryOrgEmporgs(CriteriaType criteriaType,
			PageCond pageCond) {
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(
				OmEmporg.class, dasCriteria, pageCond);
	}

	public void updateOrgEmporg(OmEmporg orgEmporg) {
		getDASTemplate().updateEntity(orgEmporg);
	}

	public void deleteEmporgByEmp(OmEmployee emp) {
		OmEmporg[] empOrgs = queryOrgEmporgsByEmp(emp);
		if (empOrgs != null && empOrgs.length > 0) {
			deleteOrgEmporg(empOrgs);
		}
	}

	public OmEmporg[] queryOrgEmporgsByOrg(DataObject org) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmEmporg.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.ORGID_PROPERTY, org
				.getBigDecimal("orgid")));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmEmporg.class,
				dasCriteria);
	}

	/**
	 * 根据员工查询出所有员工机构的关联关系
	 * 
	 * @param emp
	 * @return 员工机构关联关系列表
	 */
	public OmEmporg[] queryOrgEmporgsByEmp(OmEmployee emp) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmEmporg.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.EMP_REF_PROPERTY
				+ "." + IOrgConstants.EMPID_PROPERTY, emp.getEmpid()));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmEmporg.class,
				dasCriteria);
	}

	public OmEmployee[] queryEmpsByOrgDifferFromPosition(OmOrganization org,
			OmPosition position) {
		OmEmporg[] empOrgs = queryOrgEmporgsByOrg(org);
		OmEmployee[] emps = new OmEmployee[empOrgs.length];
		Map<BigDecimal, OmEmployee> map = new HashMap<BigDecimal, OmEmployee>();
		List<OmEmployee> list = new ArrayList<OmEmployee>();
		for (int i = 0; i < empOrgs.length; i++) {
			emps[i] = empOrgs[i].getOmEmployee();
			map.put(emps[i].getEmpid(), emps[i]);
		}
		OmEmployee[] emps2 = empPositionService.queryEmpsByPosition(position);
		for (int i = 0; i < emps2.length; i++) {
			if (map.containsKey(emps2[i].getEmpid())) {
				map.remove(emps2[i].getEmpid());
			}
		}
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			list.add(map.get(iter.next()));
		}
		return (OmEmployee[]) list.toArray();
	}

	public void setEmpPositionService(OrgEmppositionService empPositionService) {
		this.empPositionService = empPositionService;
	}

}
