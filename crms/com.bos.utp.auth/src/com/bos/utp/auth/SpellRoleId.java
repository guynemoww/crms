package com.bos.utp.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.tools.DBUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/***
 * 
 * @author 刘子良
 * @email：liuzl@primeton.com
 * @date：2013/10/18
 * @param：role 用户所选中授权角色
 * @param：empList：从员工表中查询该用户是否拥有角色
 * @description：拼接ROLEID 并且把拼接的ROLEID更新到员工表【om_employee表里面的SPECIALTY 更新的字段值是以","进行分割的】
 */
@Bizlet("拼接ROLEID")
public class SpellRoleId {
	@Bizlet("拼接ROLEID")
	public static String spellRoleId(DataObject[] role, OmEmployee[] empList) throws Exception {

		// 拼接ROLE
		String roleId = "";
		OmEmployee employee = null;
		if (empList != null && empList.length > 0) {
			for (int i = 0; i < empList.length; i++) {
				employee = (OmEmployee) empList[i];
			}
		}

		if (role != null) {
			for (int i = 0; i < role.length; i++) {
				DataObject AcOperatorrole = role[i];
				roleId += AcOperatorrole.get("roleid").toString() + ",";
			}
			if (!"".equals(roleId)) {
				if (roleId.lastIndexOf(",") != -1) {
					roleId = roleId.substring(0, roleId.lastIndexOf(","));
				}
			}
		}

		// 说明员工里面的角色信息字段为空
		if (employee != null) {
			if (employee.getSpecialty() != null) {
				roleId += "," + employee.getSpecialty();
			}
		}
		return roleId;
	}

	/**
	 * @description:更新员工信息角色【进行对比 更新】
	 * @email：liuzl@primeton.com
	 * @param：empList:查询出来该员工拥有角色
	 * @param：changeRoles：用户选中的拥有角色
	 * @param：emp 用户角色更新条件【获取EmpId】
	 * @author 刘子良
	 * @throws SQLException
	 * @date：2013/10/18
	 */
	@Bizlet("更新员工角色信息")
	public static void updateEmployeeRole(OmEmployee[] empList, DataObject[] changeRoles, DataObject emp) throws Exception {

		// 首选把用户选中的角色ID 进行遍历,拼接成 [appadmin,orgadmin] 这种格式
		String roleId = "";
		if (changeRoles != null && changeRoles.length > 0) {
			for (int i = 0; i < changeRoles.length; i++) {
				DataObject AcOperatorrole = changeRoles[i];
				roleId += AcOperatorrole.get("roleid").toString() + ",";
			}
			if (!"".equals(roleId)) {
				if (roleId.lastIndexOf(",") != -1) {
					roleId = roleId.substring(0, roleId.lastIndexOf(","));
				}
			}
		}

		// 然后查询该用户拥有的角色，首选判断长度、以谁为标准遍历
		String[] empSplit = new String[empList.length];
		String[] changeSplit = new String[changeRoles.length];
		changeSplit = roleId.split(",");

		Set<String> a = new HashSet<String>();
		Set<String> b = new HashSet<String>();

		if (empList.length > 0) {// 说明查询用户信息不为空
			for (int i = 0; i < empList.length; i++) {
				OmEmployee employee = (OmEmployee) empList[i];
				if (employee.getSpecialty().indexOf(",") != -1) {// 说明是有角色，而且是拥有多条角色记录的[appadmin,orgadmin]
					empSplit = employee.getSpecialty().split(",");
				} else {
					empSplit[i] = employee.getSpecialty().toString();
				}
			}
		}

		// 判断用户查询角色集合
		if (empSplit.length > 0) {
			for (int i = 0; i < empSplit.length; i++) {
				a.add(empSplit[i].toString());
			}
		}

		// 判断用户选择的角色集合
		if (changeSplit.length > 0) {
			for (int j = 0; j < changeSplit.length; j++) {
				b.add(changeSplit[j].toString());
			}
		}

		// 拼接更新员工角色字段SQL
		String SQLCODE = "update om_employee set SPECIALTY= ";

		// 拼接更新条件值
		String updateRoleValue = "";

		Set<String> c1 = new HashSet<String>();
		c1.addAll(a);

		Set<String> c2 = new HashSet<String>();
		c2.addAll(b);

		c1.removeAll(b); // 找出集合中的不重复元素

		c2.removeAll(a); // 找出集合中的不重复元素

		Set<String> c3 = new HashSet<String>();

		if (!c1.isEmpty()) {
			Iterator<String> i = c1.iterator();// 先迭代出来
			while (i.hasNext()) {// 遍历
				c3.add(i.next().toString());
			}
		}

		if (!c2.isEmpty()) {
			Iterator<String> i2 = c2.iterator();// 先迭代出来
			while (i2.hasNext()) {// 遍历
				c3.add(i2.next().toString());
			}
		}

		if (!c3.isEmpty()) {
			Iterator<String> i3 = c3.iterator();// 先迭代出来
			while (i3.hasNext()) {// 遍历
				updateRoleValue += i3.next().toString() + ",";
			}
		}

		if (!"".equals(updateRoleValue) && updateRoleValue.length() > 0) {
			if (updateRoleValue.lastIndexOf(",") != -1) {
				updateRoleValue = updateRoleValue.substring(0, updateRoleValue.lastIndexOf(","));
			}
		}

		SQLCODE += " '" + updateRoleValue + "'";
		OmEmployee ep = null;
		if (emp != null) {
			ep = (OmEmployee) emp;
		}
		SQLCODE += " where EMPID=" + ep.getEmpid();

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQLCODE);
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(conn, new Statement[] {}, new ResultSet[] {});
		}
	}
}
