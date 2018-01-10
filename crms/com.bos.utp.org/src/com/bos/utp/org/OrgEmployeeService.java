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
import com.bos.utp.dataset.organization.OmEmporg;
import com.bos.utp.dataset.organization.OmEmpposition;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.dataset.privilege.AcOperator;
import com.bos.utp.org.util.IOrgConstants;
import com.bos.utp.org.util.OrgHelper;
import com.bos.utp.org.util.OrgResponse;
import com.bos.utp.rights.user.ICapUserService;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.data.xpath.XPathLocator;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

/**
 * 人员服务类
 * 
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgEmployeeService extends DASDaoSupport implements
		IOrgEmployeeService {

	private ICapUserService userService = null;

	private IOrgEmporgService emporgService = null;

	private IOrgEmppositionService empPositionService = null;

	public OrgResponse addOrgEmployee(DataObject emp, DataObject user,
			DataObject org) {
		if (!validateEmpcode(emp)) {
			return new OrgResponse(false, "员工代码：" + emp.getString("empcode")
					+ "已存在");
		}
		// 存在用户关联
		AcOperator existsUser = userService.getCapUserByUserId(user
				.getString("userid"));
		// emp是否关联用户
		if (existsUser != null) {// 不能关联用户
			OmEmployee[] userRelatedEmployees = queryEmployeesByOperatorId(existsUser);
			if (userRelatedEmployees != null && userRelatedEmployees.length > 0) {
				return new OrgResponse(false, "用户登录名:" + existsUser.getUserid()
						+ "已关联员工");
			}
			user.set("operatorid", existsUser.getOperatorid());
			// user.setPassword(CryptoUtil.digestByMD5(user.getPassword()));
			//更新操作员
			userService.updateCapUser(user);// 覆盖已有的用户信息
		} else {// 新用户
			user.set("userid", user.getString("userid"));
			//新增操作员
			userService.addCapUser(user);
		}
		
		// 关联用户和员工
		emp.set("operatorid", user.getBigDecimal("operatorid"));
		emp.set("userid", user.getString("userid"));

		// 设置主键
		getDASTemplate().getPrimaryKey(emp);
		emp.set("empid", emp.getBigDecimal("operatorid"));

		OrgHelper.expandEmployeeProperty(emp);

		emp.set("orgid", org.getBigDecimal("orgid"));
		getDASTemplate().insertEntity(emp);
		
		// 添加员工机构关系
		addEmporg(emp, org);
		
		addRole(emp.getString("specialty"), user.getBigDecimal("operatorid")
				.longValue(), emp.getBigDecimal("orgid").longValue());
		return new OrgResponse(true, "添加成功");
	}

	/**
	 * 检验员工code是否合格
	 * 
	 * @param emp
	 * @return true合格，false不合格
	 */
	public boolean validateEmpcode(DataObject emp) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				OmEmployee.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.EMPCODE_PROPERTY, emp
				.getString("empcode")));
		// OmEmployee[] existsEmps = getDASTemplate()
		// .queryEntitiesByCriteriaEntity(OmEmployee.class, dasCriteria);
		DataObject[] existsEmps = GitUtil.queryEntitiesByCriteriaEntity(
				getDASTemplate(), dasCriteria);
		if (existsEmps == null || existsEmps.length == 0) {
			return true;
		}
		if (String.valueOf(emp.getBigDecimal("empid")) != null
				&& !"".equals(String.valueOf(emp.getBigDecimal("empid")))
				&& existsEmps[0].getBigDecimal("empid").longValue() == emp
						.getBigDecimal("empid").longValue()) {// 修改的情况，只能为1个
			return existsEmps.length == 1;
		} else {// 新增,必定没有
			return existsEmps.length == 0;
		}
	}

	/**
	 * 添加机构和人员的关系
	 * 
	 * @param emp
	 *            员工
	 * @param org
	 *            机构
	 */
	private void addEmporg(DataObject emp, DataObject org) {
		OmEmporg orgEmpAssosiation = OmEmporg.FACTORY.create();
		XPathLocator xpath = XPathLocator.newInstance();
		xpath.setValue(orgEmpAssosiation, "omEmployee/empid", emp
				.getBigDecimal("empid"));
		xpath.setValue(orgEmpAssosiation, "omOrganization/orgid", org
				.getBigDecimal("orgid"));

		// OmOrganization orgs = OmOrganization.FACTORY.create();
		// orgs.setOrgid(org.getBigDecimal("orgid"));
		//
		// OmEmployee emps = OmEmployee.FACTORY.create();
		// emps.setEmpid(emp.getBigDecimal("empid"));
		//		
		// orgEmpAssosiation.setOmEmployee(emps);
		// orgEmpAssosiation.setOmOrganization(orgs);
		orgEmpAssosiation.setIsmain("1");
		emporgService.addOrgEmporg(orgEmpAssosiation);
	}

	/**
	 * 根据用户查询出它关联的员工
	 * 
	 * @param user
	 * @return
	 */
	private OmEmployee[] queryEmployeesByOperatorId(AcOperator user) {
		if (user != null && String.valueOf(user.getOperatorid()) != null
				&& !"".equals(user.getOperatorid())) {
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(
					OmEmployee.QNAME);
			dasCriteria.add(ExpressionHelper.eq("operatorid", user
					.getOperatorid()));
			return getDASTemplate().queryEntitiesByCriteriaEntity(
					OmEmployee.class, dasCriteria);
		}
		return new OmEmployee[0];
	}

	public void deleteOrgEmployee(OmEmployee[] emps) {
		if (emps == null)
			return;
		for (OmEmployee emp : emps) {
			empPositionService.deleteEmppositionsByEmp(emp);
			emporgService.deleteEmporgByEmp(emp);
			getDASTemplate().deleteEntityCascade(emp);
		}
	}

	public void getOrgEmployee(DataObject emp) {
		getDASTemplate().expandEntity(emp);
	}

	public void getOrgEmployee(OmEmployee emp) {
		getDASTemplate().expandEntity(emp);
	}

	public OmEmployee[] queryOrgEmployees(CriteriaType criteriaType,
			PageCond pageCond) {
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(
				OmEmployee.class, dasCriteria, pageCond);
	}

	// public QueryEmpUser[] queryEmpUsers(CriteriaType criteriaType,PageCond
	// pageCond){
	// criteriaType.set_entity(QueryEmpUser.QNAME);
	// IDASCriteria dasCriteria =
	// getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
	// return
	// getDASTemplate().queryEntitiesByCriteriaEntityWithPage(QueryEmpUser.class,
	// dasCriteria, pageCond);
	// }

	public int countOrgEmployees(CriteriaType criteria) {
		criteria.set_entity(OmEmployee.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteria);
		return getDASTemplate().count(dasCriteria);
	}

	public OrgResponse updateOrgEmployee(OmEmployee emp) {
		if (!validateEmpcode(emp)) {
			return new OrgResponse(false, "不能修改为已存在的员工代码:" + emp.getEmpcode());
		}
		getDASTemplate().updateEntity(emp);
		return new OrgResponse(true, "修改成功");
	}

	public OrgResponse updateOrgEmployee(DataObject emp, DataObject user) {
		if (!validateEmpcode(emp)) {
			return new OrgResponse(false, "不能修改为已存在的员工代码:"
					+ emp.getString("empcode"));
		}
		// 存在用户关联
//		if (!StringUtils.isBlank(emp.getString("userid"))) {
//			AcOperator existsUser = userService.getCapUserByUserId(emp
//					.getString("userid"));
//			if (existsUser == null) {
//				user.set("password", IConstants.DEFAULT_PASSWORD);
//				userService.addCapUser(user);
//				emp.set("userid", user.getString("userid"));
//				emp.setBigDecimal("operatorid", user
//						.getBigDecimal("operatorid"));
//			} else {// 可以关联原来的员工
//				if (existsUser.getOperatorid().longValue() == user
//						.getBigDecimal("operatorid").longValue()) {// 同一个用户，可以更新
//					userService.updateCapUser(user);
//
//				} else {// 不同用户，则不能关联已存在员工的用户
//					OmEmployee[] employees = queryEmployeesByOperatorId(existsUser);
//					if (employees.length > 0) {
//						return new OrgResponse(false, "不能关联到一个已关联员工的用户");
//					}
//				}
//				emp.setBigDecimal("operatorid", existsUser.getOperatorid());
//				emp.set("userid", existsUser.getUserid());
//			}
//		} else {
//			// 取消员工和用户的关联
//			// emp.setUserid("");
//			// emp.setOperatorid(0l);
//		}
//		updateRole(emp.getString("specialty"), user.getBigDecimal("operatorid")
//				.longValue(), emp.getBigDecimal("orgid").longValue());
		//更新员工表
		getDASTemplate().updateEntity(emp);
		//更新操作员表
		user.setString("operatorname", emp.getString("empname"));
		getDASTemplate().updateEntity(user);
		// 更新用户主机构om_emporg表
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("empid", emp.getBigDecimal("empid"));
//		map.put("orgid", emp.getBigDecimal("orgid"));
//		map.put("ismain", "1");
//		// System.out.println(map);
//		getDASTemplate().executeNamedSql(
//				"com.bos.utp.org.omemployee.delOmEmporgByEmpid", map);
//		getDASTemplate().executeNamedSql(
//				"com.bos.utp.org.omemployee.addOmEmporg", map);
//		// getDASTemplate().executeNamedSql("com.bos.utp.org.omemployee.updateEmpMainorg",
//		// map);
		return new OrgResponse(true, "修改成功");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see om.bos.utp.org.IOrgEmployeeService#deleteEmpAndOrgRelationship(om.bos.utp.org.dataset.OrgOrganization)
	 */
	public void deleteEmpAndOrgRelationship(DataObject org) {
		if (org.get("orgid") == null)
			return;
		OmEmporg[] empOrgs = emporgService.queryOrgEmporgsByOrg(org);
		if (empOrgs != null && empOrgs.length > 0) {
			emporgService.deleteOrgEmporg(empOrgs);
		}
	}

	public void deleteOrgEmployee(String id, String parentId,
			String parentType, String isDeleteUserCascade) {
		if (StringUtils.isBlank(parentType))
			return;
		OmEmployee empWillBeDelete = OmEmployee.FACTORY.create();
		empWillBeDelete.setEmpid(new BigDecimal(id));
		if ("true".equals(isDeleteUserCascade)) {
			getOrgEmployee(empWillBeDelete);
		}
		if ("OrgOrganization".equals(parentType)) {// 父节点是机构
			// 删除机构和人员的关系
			OmEmporg empOrgAssosiation = OmEmporg.FACTORY.create();
			OmOrganization org = OmOrganization.FACTORY.create();
			org.setOrgid(new BigDecimal(parentId));
			empOrgAssosiation.setOmOrganization(org);
			empOrgAssosiation.setOmEmployee(empWillBeDelete);
			// 删除关联关系
			// emporgService.deleteOrgEmporg(new OmEmporg[]{empOrgAssosiation});
			// 判断人员是否在其他机构下，如果不在，则删除这个员工
			OmEmporg[] existsEmpOrgAssosiations = emporgService
					.queryOrgEmporgsByEmp(empWillBeDelete);
			if (existsEmpOrgAssosiations.length == 0) {
				deleteOrgEmployee(new OmEmployee[] { empWillBeDelete });
			}
		} else if (OrgPositionNodeType.equals(parentType)) {// 父节点是岗位
			// 直接删除人员和岗位的关联关系
			OmEmpposition empPositionAssosiation = OmEmpposition.FACTORY
					.create();
			OmPosition position = OmPosition.FACTORY.create();
			position.setPositionid(new BigDecimal(parentId));
			empPositionAssosiation.setOmEmployee(empWillBeDelete);
			empPositionAssosiation.setOmPosition(position);
			empPositionService
					.deleteOrgEmpposition(new OmEmpposition[] { empPositionAssosiation });
		}
		AcOperator capUser = AcOperator.FACTORY.create();
		capUser.setUserid(empWillBeDelete.getUserid());
		if (capUser.getUserid() != null) {
			userService.deleteCapUserByTemplate(capUser);
		}
	}

	private static final String OrgPositionNodeType = "OrgPosition";

	public OmEmployee[] queryEmpsAllowAddInPosition(String userid,
			String empname, String positionid, PageCond page) {
		OmEmployee[] emps = getDASTemplate().queryByNamedSqlWithPage(
				OmEmployee.class, "com.bos.utp.org.empQuery.selectEmpAllowAdd",
				page, getNameSqlParamsMap(userid, empname, positionid));
		return emps;
	}

	public Integer countEmpsAllowAddInPosition(String userid, String empname,
			String positionid) {
		Integer[] empSize = getDASTemplate().queryByNamedSql(Integer.class,
				"com.bos.utp.org.empQuery.countEmpAllowAdd",
				getNameSqlParamsMap(userid, empname, positionid));
		if (empSize == null || empSize.length == 0)
			return 0;
		return empSize[0];
	}

	public OmEmployee[] queryEmpsInPosition(String userid, String empname,
			String positionid, PageCond page) {
		OmEmployee[] emps = getDASTemplate().queryByNamedSqlWithPage(
				OmEmployee.class,
				"com.bos.utp.org.empQuery.selectEmpInPosition", page,
				getNameSqlParamsMap(userid, empname, positionid));
		return emps;
	}

	public Integer countEmpsInPosition(String userid, String empname,
			String positionid) {
		Integer[] empSize = getDASTemplate().queryByNamedSql(Integer.class,
				"com.bos.utp.org.empQuery.countEmpInPosition",
				getNameSqlParamsMap(userid, empname, positionid));
		if (empSize == null || empSize.length == 0)
			return 0;
		return empSize[0];
	}

	private HashMap<String, Object> getNameSqlParamsMap(String userid,
			String empname, String positionid) {
		HashMap<String, Object> nameSqlParamsMap = new HashMap<String, Object>();
		if (!StringUtils.isBlank(userid)) {
			nameSqlParamsMap.put("userid", userid);
		}
		if (!StringUtils.isBlank(empname)) {
			nameSqlParamsMap.put("empname", empname);
		}
		if (!StringUtils.isBlank(positionid)) {
			nameSqlParamsMap.put("positionid", new BigDecimal(positionid));
		}
		return nameSqlParamsMap;
	}

	public void setUserService(ICapUserService userService) {
		this.userService = userService;
	}

	public void setEmporgService(IOrgEmporgService emporgService) {
		this.emporgService = emporgService;
	}

	public void setEmpPositionService(IOrgEmppositionService empPositionService) {
		this.empPositionService = empPositionService;
	}

	public void deleteCapUserRelatedEmp(String userId) {
		AcOperator capUser = AcOperator.FACTORY.create();
		capUser.setUserid(userId);
		getDASTemplate().deleteByTemplate(capUser);
	}

	/**
	 * 添加人员角色对应关系
	 * 
	 * @param roleStr
	 *            角色字符串
	 * @param operatorid
	 *            操作者id
	 */
	public void addRole(String roleStr, Long operatorid, Long orgid) {
		if (roleStr == null || operatorid == null || "".equals(operatorid)
				|| roleStr.equals("") || null == orgid || orgid < 1) {
			return;
		}
		String[] arrStr = roleStr.split(",");
		String roleid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		for (String str : arrStr) {
			roleid = str;
			if (roleid == null || "".equals(roleid)) {
				continue;
			}
			map.put("operatorid", operatorid.toString());
			map.put("orgid", orgid);
			map.put("roleid", roleid);
			try {
				DatabaseExt.executeNamedSql("default",
						"com.bos.utp.org.rolens.insertRole", map);
			} catch (Exception e) {
				System.out.println("添加操作者角色对应表异常");
				// break;
				continue;
			}
		}

	}

	/**
	 * 更新人员角色信息
	 * 
	 * @param roleStr
	 * @param operatorid
	 */
	public void updateRole(String roleStr, Long operatorid, Long orgid) {
		if (roleStr == null || operatorid == null || "".equals(operatorid)
				|| roleStr.equals("")) {
			return;
		}
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("operatorid", operatorid.toString());
		updateMap.put("orgid", orgid.toString());
		try {
			DatabaseExt.executeNamedSql("default",
					"com.bos.utp.org.rolens.deleteRole", updateMap);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("删除操作者角色对应表异常");
		}
		addRole(roleStr, operatorid, orgid);

	}

	public static void main(String[] args) {
		String str = "r0001:jueseing";
		System.out.println(str.substring(0, str.indexOf(":")));
	}
}