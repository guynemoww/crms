package com.bos.pub;

import com.eos.data.datacontext.UserObject;
import commonj.sdo.DataObject;

public class UserUtil {
	// 客户经理
	private static final String[] managerRoles = { "R1006", "R1002", "R1007", "R1003", "R1008", "R1153", "R1147", "R1159" };
	// 行长
	private static final String[] presidentRoles = { "R1038", "R1041" };
	// 支行
	private static final String[] zhPresidentRoles = { "R1010" };
	// 管理员
	private static final String[] adminRoles = { "eosadmin", "appadmin", "orgadmin" };

	/**
	 * 判断当前登陆用户是否拥有客户经理权限
	 * 
	 * @return
	 */
	public static boolean isManager() {
		DataObject[] roles = (DataObject[]) ((UserObject) GitUtil.getSession().getAttribute("userObject")).getAttributes().get("roles");
		return compare(roles, managerRoles);
	}

	public static boolean isAdmin() {
		DataObject[] roles = (DataObject[]) ((UserObject) GitUtil.getSession().getAttribute("userObject")).getAttributes().get("roles");
		return compare(roles, adminRoles);
	}

	public static boolean isPresident() {
		DataObject[] roles = (DataObject[]) ((UserObject) GitUtil.getSession().getAttribute("userObject")).getAttributes().get("roles");
		return compare(roles, presidentRoles);
	}

	public static boolean isZHPresident() {
		DataObject[] roles = (DataObject[]) ((UserObject) GitUtil.getSession().getAttribute("userObject")).getAttributes().get("roles");
		return compare(roles, zhPresidentRoles);
	}

	public static boolean compare(DataObject[] userRoles, String[] roles) {
		if (null != userRoles && userRoles.length > 0) {
			for (int i = 0; i < roles.length; i++) {
				for (int j = 0; j < userRoles.length; j++) {
					if (roles[i].equals(userRoles[j].get("roleid"))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String[] getManagerroles() {
		String[] temps = new String[managerRoles.length];
		for (int i = 0; i < managerRoles.length; i++) {
			temps[i] = managerRoles[i];
		}
		return temps;
	}
}
