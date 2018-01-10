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

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmpposition;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.org.util.IOrgConstants;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;


import commonj.sdo.DataObject;

/**
 * 岗位和人员的关系服务类
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgEmppositionService extends DASDaoSupport implements IOrgEmppositionService{
	
	public void addOrgEmpposition(OmEmpposition orgEmpposition){
//		orgEmpposition.setTenantid(TenantManager.getCurrentTenantID());
		orgEmpposition.setIsmain("1");
		getDASTemplate().insertEntity(orgEmpposition);
	}
	
	public void addOrgEmpposition(OmEmpposition[] emppositions){
		if(emppositions != null && emppositions.length > 0){
			for(OmEmpposition empposition : emppositions){
				addOrgEmpposition(empposition);
			}
		}
	}

	public void deleteOrgEmpposition(OmEmpposition[] orgEmppositions){
		if(orgEmppositions == null) return;
		for(DataObject orgEmpposition:orgEmppositions){
			getDASTemplate().deleteEntityCascade(orgEmpposition);
		}
	}


	public void getOrgEmpposition(OmEmpposition orgEmpposition){
		getDASTemplate().expandEntity(orgEmpposition);
	}


	public OmEmpposition[]  queryOrgEmppositions(CriteriaType criteriaType,PageCond pageCond){
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OmEmpposition.class, dasCriteria, pageCond);
	}


    public void updateOrgEmpposition(OmEmpposition orgEmpposition){
	    getDASTemplate().updateEntity(orgEmpposition);
    }

	public void deleteEmppositionsByPosition(OmPosition position) {
		OmEmpposition[] empPositions = queryEmppositionsByPosition(position);
		if(empPositions != null && empPositions.length > 0){
			deleteOrgEmpposition(empPositions);
		}
	}

	public void deleteEmppositionsByEmp(OmEmployee emp) {
		OmEmpposition[] empPositions = queryEmppositionsByEmp(emp);
		if(empPositions != null && empPositions.length > 0){
			deleteOrgEmpposition(empPositions);
		}
	}
	
	/**
	 * 根据岗位查询出所有岗位人员关联关系
	 * @param position
	 * @return
	 */
	private OmEmpposition[] queryEmppositionsByPosition(OmPosition position) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("omPosition.positionid", position.getPositionid()));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmEmpposition.class, dasCriteria);
	}


	/**
	 * 根据员工查出所有员工岗位关联关系
	 * @param emp
	 * @return 员工岗位关联关系列表
	 */
	private OmEmpposition[] queryEmppositionsByEmp(OmEmployee emp) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OmEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.EMP_REF_PROPERTY + "." + 
				IOrgConstants.EMPID_PROPERTY, emp.getEmpid()));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OmEmpposition.class, dasCriteria);
	}
    
	public OmEmpposition[] queryOrgEmppositionsOfEmp(String empId, String parentOrgId) {
		DataObject dataObject = OmEmpposition.FACTORY.create();
		dataObject.setBigDecimal("empId", new BigDecimal(parentOrgId));
		dataObject.setBigDecimal("parentOrgId", new BigDecimal(parentOrgId));
		OmEmpposition[] empPositions = getDASTemplate().queryByNamedSql(
				OmEmpposition.class, "om.bos.utp.org.empposition.select_empposition", dataObject);
		return empPositions == null ? new OmEmpposition[0] : empPositions;
	}

	public OmEmployee[] queryEmpsByPosition(OmPosition position) {
		OmEmpposition[] empPositions = queryEmppositionsByPosition(position);
		OmEmployee[] emps = new OmEmployee[empPositions.length];
		for(int i=0;i<empPositions.length;i++){
			emps[i] = empPositions[i].getOmEmployee();
		}
		return emps;
	}


}