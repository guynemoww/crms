/**
 * 
 */
package com.bos.utp.org.util;


import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * 拼接操作者角色信息
 * @author 谭凯
 * @date 2013-07-31 14:32:52
 *
 */
@Bizlet("")
public class RoleConditionUtil {

	/**
	 * 拼接人员角色
	 * @param objs
	 * @return
	 */
	@Bizlet("拼接人员角色")
	public static String roleCondition(DataObject [] objs){
		String roleStr = "";
		if(objs == null || objs.length == 0){
			return roleStr;
		}
		for (DataObject object : objs) {
			roleStr = object.getString("roleid").concat(":").concat(object.getString("rolename")).concat(",");
		}
		if(roleStr != null && !"".equals(roleStr)){
			roleStr = roleStr.substring(0 , roleStr.length()-1);
		}
		return roleStr;
	}
	
	/**
	 * 拼接角色名称
	 * @param param
	 * @return
	 */
	@Bizlet("")
	public static Map<String, String> conditRoleName(String param) {
		String roleid = "";
		Map<String, String> map = new HashMap<String, String>();
		if(param == null || "".equals(param)) return map;
		String[] roleArr = param.split(",");
		for (int i = 0; i < roleArr.length; i++) {
			String string = roleArr[i];
			roleid += "'"+string+"'"+",";
			
		}
		roleid = roleid.substring(0 , roleid.length()-1);
		String roleSQL = "SELECT T.ROLEID , T.ROLENAME , T.ROLETYPE , T.INITFLAG FROM AC_ROLE T WHERE T.ROLEID IN ("+roleid+")";
		if(roleid != null && !"".equals(roleid))
			map.put("sql", roleSQL);
		else map.put("sql", "");
		return map;
	}
	
	/**
	 * 查询员工角色
	 * @param roleid
	 * @return
	 */
	@Bizlet("")
	public static String queryRoleName(DataObject[] roles){
		String roleName = "";
		for (DataObject object :  roles) {
			roleName += object.getString("ROLENAME").concat(",");
		}
		if (roleName != null && !"".equals(roleName)) {
			roleName = roleName.substring(0 , roleName.length()-1);
		}
		return roleName;
	}
	 

}
