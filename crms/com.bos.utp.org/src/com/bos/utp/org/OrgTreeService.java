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

import static com.bos.utp.org.util.IOrgConstants.EMPID_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.EMP_REF_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.ORGID_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.ORG_REF_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.POSITIONID_PROPERTY;
import static com.bos.utp.org.util.IOrgConstants.POSITION_REF_PROPERTY;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmporg;
import com.bos.utp.dataset.organization.OmEmpposition;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.org.util.IOrgConstants;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

/**
 * 组织机构树服务类
 * 
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgTreeService extends DASDaoSupport implements IOrgTreeService {

	private static final String EXPANDED = "expanded";

	private static final String IS_LEAF = "isLeaf";

	private IOrgOrganizationService organizationService;

	private IOrgPositionService positionService;

	/**
	 * 查询机构下的所有子机构，参数orgid为空时返回顶级机构
	 * 
	 * @param orgid
	 * @return
	 */
	public DataObject[] querySubOrgs(String orgid) {
		DataObject[] orgs = organizationService.querySubOrgs(orgid);

		// 设置NUI所需的属性
		for (DataObject org : orgs) {
			// boolean isLeaf = !"2".equalsIgnoreCase(org.getIsleaf());
			org.setBoolean(IS_LEAF, false);
			org.setBoolean(EXPANDED, false);
		}

		return orgs;
	}

	/**
	 * 查询机构下的所有岗位
	 * 
	 * @param orgid
	 * @return
	 */
	public OmPosition[] queryPositionsOfOrg(String orgid) {
		OmPosition[] positions = organizationService.queryPositionsOfOrg(orgid);
		;

		// 设置NUI所需的属性
		for (OmPosition position : positions) {
			// boolean isLeaf = !"2".equalsIgnoreCase(position.getIsleaf());
			position.setBoolean(IS_LEAF, false);
			position.setBoolean(EXPANDED, false);
		}

		return positions;
	}

	/**
	 * 查询在机构下且未分配到此机构下级岗位的员工
	 * 
	 * @param orgid
	 * @return
	 */
	public DataObject[] queryEmployeesOfOrgNotInPosition(String orgid) {
		if (StringUtils.isBlank(orgid)) {
			return new OmEmployee[0];
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgid", orgid);
		// OmEmployee[] emps =
		// getDASTemplate().queryByNamedSql(OmEmployee.class,
		// "com.bos.utp.org.organization.select_orgemp", map);
		DataObject[] emps = GitUtil.queryByNamedSql(getDASTemplate(),
				"com.bos.utp.org.organization.select_orgemp", map);

		// 设置NUI所需的属性
		for (DataObject emp : emps) {
			emp.setBoolean(IS_LEAF, true);
			emp.setBoolean(EXPANDED, true);
		}

		return emps;
	}

	/**
	 * 获取岗位下的所有子岗位
	 * 
	 * @param positionid
	 * @return
	 */
	public OmPosition[] querySubPositions(String positionid) {
		OmPosition[] positions = positionService.querySubPositions(positionid);

		// 设置NUI所需的属性
		for (OmPosition position : positions) {
			// boolean isLeaf = !"2".equalsIgnoreCase(position.getIsleaf());
			position.setBoolean(IS_LEAF, false);
			position.setBoolean(EXPANDED, false);
		}

		return positions;
	}

	/**
	 * 获取岗位下的所有员工
	 * 
	 * @param orgid
	 * @return
	 */
	// public QueryPositionEmp[] queryEmployeesOfPosition(String positionid) {
	// QueryPositionEmp[] emps =
	// positionService.queryEmployeesOfPosition(positionid);
	//		
	// // 设置NUI所需的属性
	// for(QueryPositionEmp emp : emps) {
	// emp.setBoolean(IS_LEAF, true);
	// emp.setBoolean(EXPANDED, true);
	// }
	//		
	// return emps;
	// }
	/**
	 * 复制机构到另一个机构中作为子机构
	 * 
	 * @param orgid
	 * @param targetOrgid
	 */
	public void copyOrgToOrg(String orgid, String targetOrgid) {
		OmOrganization targetOrg = getOrganizationById(targetOrgid);

		OmOrganization org = getOrganizationById(orgid);

		// 克隆一个新机构
		DataObject newOrg = DataObjectUtil.convertDataObject(org,
				OmOrganization.QNAME, false);
		getDASTemplate().getPrimaryKey(newOrg);
		newOrg.set(ORG_REF_PROPERTY, targetOrg);
		getDASTemplate().insertEntity(newOrg);
	}

	/**
	 * 移动机构到另一个机构中作为子机构
	 * 
	 * @param orgid
	 * @param targetOrgid
	 */
	public void moveOrgToOrg(String orgid, String targetOrgid) {
		OmOrganization targetOrg = getOrganizationById(targetOrgid);
		OmOrganization org = getOrganizationById(orgid);
		org.setOmOrganization(targetOrg);
		getDASTemplate().updateEntity(org);
	}

	/**
	 * 通过机构ID获取机构对象
	 * 
	 * @param orgid
	 */
	private OmOrganization getOrganizationById(String orgid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmOrganization.QNAME);
		dasCriteria.add(ExpressionHelper
				.eq(IOrgConstants.ORGID_PROPERTY, orgid));
		OmOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(
				OmOrganization.class, dasCriteria);
		if (orgs != null && orgs.length == 1) {
			return orgs[0];
		}
		return null;
	}

	/**
	 * 复制员工到岗位
	 * 
	 * @param empid
	 * @param targetPositionid
	 */
	public void copyEmpToPosition(String empid, String targetPositionid) {
		OmEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(
				empid, targetPositionid);

		// 当员工在目标岗位中不存在时,才做复制处理
		if (targetEmpposition == null) {
			// 先复制员工到机构中
			OmPosition targetPosition = loadPositionWithOrg(targetPositionid);
			// copyEmpToOrg(empid,
			// String.valueOf(targetPosition.getOmOrganization().getOrgid()));
			// 再保存员工岗位关系
			targetEmpposition = createEmpposition(empid, targetPositionid);
			getDASTemplate().insertEntity(targetEmpposition);
		}
	}

	public void copyEmpToOrg(String empid, String targetOrgid) {
		OmEmporg targetEmporg = loadEmporgWithEmp(empid, targetOrgid);

		// 当员工在目标机构中不存在时,才做复制处理
		if (targetEmporg == null) {
			targetEmporg = createEmporg(empid, targetOrgid);
			targetEmporg.setIsmain("2"); // 是否主机构为n-否
			getDASTemplate().insertEntity(targetEmporg);
		}
	}

	/**
	 * 移动人员从当前机构到另一个机构
	 * 
	 * @param fromOrgid
	 * @param parentOrgid
	 */
	public void moveEmpFromOrgToOrg(String empid, String fromOrgid,
			String targetOrgid) {
		OmEmporg fromEmporg = loadEmporgWithEmp(empid, fromOrgid);
		OmEmporg targetEmporg = loadEmporgWithEmp(empid, targetOrgid);

		// 当员工在目标机构中不存在时
		if (fromEmporg != null && targetEmporg == null) {
			targetEmporg = createEmporg(empid, targetOrgid);
			targetEmporg.setIsmain(fromEmporg.getIsmain());
			getDASTemplate().insertEntity(targetEmporg);

			// 修改员工的主管机构
			OmEmployee emp = targetEmporg.getOmEmployee();
			// emp.setOrgid(tartgetEmporg.getOrgid());
			if ("1".equals(fromEmporg.getIsmain())) {
				emp.setOrgid(new BigDecimal(targetOrgid));
				getDASTemplate().updateEntity(emp);
			}
		}

		getDASTemplate().deleteEntity(fromEmporg);
	}

	/**
	 * 移动人员从岗位到机构
	 * 
	 * @param empid
	 * @param fromPositionid
	 * @param targetOrgid
	 */
	public void moveEmpFromPositionToOrg(String empid, String fromPositionid,
			String targetOrgid) {
		OmEmpposition fromEmpposition = loadEmppositionWithPositionWithOrg(
				empid, fromPositionid);
		OmEmporg tartgetEmporg = loadEmporgWithEmp(empid, targetOrgid);

		// 当员工在目标机构中不存在时
		if (fromEmpposition != null && tartgetEmporg == null) {
			// OmOrganization fromOrg =
			// fromEmpposition.getOmPosition().getOmOrganization();

			// if(!String.valueOf(fromOrg.getOrgid()).equals(targetOrgid)) {
			// 在不同机构下时的处理
			// deleteEmppositionOfOrg(empid,
			// String.valueOf(fromOrg.getOrgid()));
			// moveEmpFromOrgToOrg(empid, String.valueOf(fromOrg.getOrgid()),
			// targetOrgid);
			// }

		}

		getDASTemplate().deleteEntity(fromEmpposition);
	}
	
	public OmOrganization[] parseOrgInfo(OmOrganization[] oaOrg) {
		for(OmOrganization org : oaOrg) {
			org.setBoolean(IS_LEAF, false);
			org.setBoolean(EXPANDED, false);
		}
		
		return oaOrg;
	}
	/**
	 * 移动人员从机构到岗位
	 * 
	 * @param empid
	 * @param positionid
	 * @param targetOrgid
	 */
	public void moveEmpFromOrgToPosition(String empid, String fromOrgid,
			String targetPositionid) {
		OmEmporg fromEmporg = loadEmporgWithEmp(empid, fromOrgid);
		OmEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(
				empid, targetPositionid);

		// 当员工在当前机构中存在,并且在目标岗位中不存在时,才做移动处理
		if (fromEmporg != null && targetEmpposition == null) {
			OmPosition targetPosition = loadPositionWithOrg(targetPositionid);

			if (targetPosition != null) {
				// OmOrganization targetOrg =
				// targetPosition.getOmOrganization();

				// if(targetOrg != null &&
				// !String.valueOf(targetOrg.getOrgid()).equals(fromOrgid)) {
				// 在不同机构下时的处理
				// deleteEmppositionOfOrg(empid, fromOrgid);
				// moveEmpFromOrgToOrg(empid, fromOrgid,
				// String.valueOf(targetOrg.getOrgid()));
				// }

				targetEmpposition = createEmpposition(empid, targetPositionid);
				getDASTemplate().insertEntity(targetEmpposition);
			}
		}
	}

	/**
	 * 移动员工从指定岗位到目标岗位
	 * 
	 * @param empid
	 * @param positionid
	 */
	public void moveEmpFromPositionToPosition(String empid, String positionid,
			String targetPositionid) {
		OmEmpposition fromEmpposition = loadEmppositionWithPositionWithOrg(
				empid, positionid);
		OmEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(
				empid, targetPositionid);

		// 当员工在当前岗位中存在,并且在目标岗位中不存在时,才做移动处理
		if (fromEmpposition != null && targetEmpposition == null) {
			// OmOrganization fromOrg =
			// fromEmpposition.getOmPosition().getOmOrganization();
			OmPosition targetPosition = loadPositionWithOrg(targetPositionid);

			if (targetPosition != null) {
				// OmOrganization targetOrg =
				// targetPosition.getOmOrganization();

				// if(!fromOrg.equals(targetOrg)) {
				// 在不同机构下时的处理
				// deleteEmppositionOfOrg(empid,
				// String.valueOf(fromOrg.getOrgid()));
				// moveEmpFromOrgToOrg(empid,
				// String.valueOf(fromOrg.getOrgid()),
				// String.valueOf(targetOrg.getOrgid()));
				// }
			}

			targetEmpposition = createEmpposition(empid, targetPositionid);
			getDASTemplate().deleteEntity(fromEmpposition);
			getDASTemplate().insertEntity(targetEmpposition);
		}
	}

	/**
	 * 删除人员在机构下的员工岗位关系
	 * 
	 * @param empid
	 * @param orgid
	 */
	private void deleteEmppositionOfOrg(String empid, String orgid) {
		IDASCriteria dasCriteria = DASManager
				.createCriteria(OmEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "."
				+ EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "."
				+ ORG_REF_PROPERTY + "." + ORGID_PROPERTY, orgid));
		getDASTemplate().deleteByCriteriaEntity(dasCriteria);
	}

	/**
	 * 加载员工与机构的关联实体，并且级联加载员工实体
	 * 
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OmEmporg loadEmporgWithEmp(String empid, String orgid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmEmporg.QNAME);
		dasCriteria.add(ExpressionHelper.eq(ORGID_PROPERTY, orgid));
		dasCriteria.addAssociation(EMP_REF_PROPERTY);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "."
				+ EMPID_PROPERTY, empid));
		dasCriteria.addAssociation(ORG_REF_PROPERTY);
		dasCriteria.add(ExpressionHelper.eq(ORG_REF_PROPERTY + "."
				+ ORGID_PROPERTY, orgid));
		OmEmporg[] emporgs = getDASTemplate().queryEntitiesByCriteriaEntity(
				OmEmporg.class, dasCriteria);
		if (emporgs != null && emporgs.length == 1) {
			getDASTemplate().expandRelation(emporgs[0], EMP_REF_PROPERTY); // 加载关联的员工实体
			return emporgs[0];
		}
		return null;
	}

	/**
	 * 加载员工与岗位的关联实体，级联加载岗位实体以及岗位关联的机构实体
	 * 
	 * @param empid
	 * @param positionid
	 * @return
	 */
	private OmEmpposition loadEmppositionWithPositionWithOrg(String empid,
			String positionid) {
		IDASCriteria dasCriteria = DASManager
				.createCriteria(OmEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "."
				+ EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "."
				+ POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(POSITION_REF_PROPERTY);
		dasCriteria.addAssociation(POSITION_REF_PROPERTY + "."
				+ ORG_REF_PROPERTY);
		OmEmpposition[] emppositions = getDASTemplate()
				.queryEntitiesByCriteriaEntity(OmEmpposition.class, dasCriteria);
		if (emppositions != null && emppositions.length == 1) {
			getDASTemplate().expandRelation(emppositions[0],
					POSITION_REF_PROPERTY); // 加载关联的岗位实体
			getDASTemplate().expandRelation(emppositions[0].getOmPosition(),
					ORG_REF_PROPERTY); // 加载岗位关联的机构实体
			return emppositions[0];
		}
		return null;
	}

	/**
	 * 加载岗位，级联加载父机构
	 * 
	 * @param positionid
	 * @return
	 */
	private OmPosition loadPositionWithOrg(String positionid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(ORG_REF_PROPERTY);
		OmPosition[] positions = getDASTemplate()
				.queryEntitiesByCriteriaEntity(OmPosition.class, dasCriteria);
		if (positions != null && positions.length == 1) {
			return positions[0];
		}
		return null;
	}

	/**
	 * 创建员工与机构的关联实体
	 * 
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OmEmporg createEmporg(String empid, String orgid) {
		OmEmployee emp = OmEmployee.FACTORY.create();
		emp.setEmpid(new BigDecimal(empid));

		OmOrganization org = OmOrganization.FACTORY.create();
		org.setOrgid(new BigDecimal(orgid));

		OmEmporg emporg = OmEmporg.FACTORY.create();
		// emporg.setOrgid(Integer.valueOf(orgid));
		// emporg.setTenantid(TenantManager.getCurrentTenantID());
		emporg.setOmEmployee(emp);
		emporg.setOmOrganization(org);
		return emporg;
	}

	/**
	 * 创建员工与岗位的关联实体
	 * 
	 * @param empid
	 * @param positionid
	 * @return
	 */
	private OmEmpposition createEmpposition(String empid, String positionid) {
		OmEmployee emp = OmEmployee.FACTORY.create();
		emp.setEmpid(new BigDecimal(empid));
		// emp.setTenantid(TenantManager.getCurrentTenantID());

		OmPosition position = OmPosition.FACTORY.create();
		position.setPositionid(new BigDecimal(positionid));
		// position.setTenantid(TenantManager.getCurrentTenantID());

		OmEmpposition empposition = OmEmpposition.FACTORY.create();
		// empposition.setTenantid(TenantManager.getCurrentTenantID());
		empposition.setOmEmployee(emp);
		empposition.setOmPosition(position);

		return empposition;
	}

	public void setOrganizationService(
			IOrgOrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public void setPositionService(IOrgPositionService positionService) {
		this.positionService = positionService;
	}

}