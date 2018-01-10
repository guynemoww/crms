package com.bos.ews;

import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("")
public class CheckRole {
	@Bizlet("操作人角色信息")
	public String checkRoleInfo(DataObject[] rolesArray) {
		for (DataObject role : rolesArray) {
			if ("R1159".equals(role.getString("roleid")) || 
					"R1147".equals(role.getString("roleid")) || 
					"R1153".equals(role.getString("roleid")) || 
					"R1006".equals(role.getString("roleid")) || 
					"R1007".equals(role.getString("roleid")) || 
					"R1002".equals(role.getString("roleid")) || 
					"R1008".equals(role.getString("roleid"))) {
				return "0";//客户经理
			} else {
				continue;
			}
		}
		return "1";
	}
}
