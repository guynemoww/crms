package com.bos.utp.org.empinfoManager;

import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;
public class RoleRelate {
	
	@Bizlet("")
	public Object getRoleStrByOpr(Object ctOrgEmpQry,Object[] acRole){
		String str = "";
		if(acRole.length>0){
			for (int i = 0; i < acRole.length; i++) {
				DataObject db = (DataObject)acRole[i];
				String temp = db.getString("rolename");
				str+=temp+",";
			}
			str = str.substring(0,str.length()-1);
			DataObject o = (DataObject)ctOrgEmpQry;
			o.set("rolename", str);
			return (Object)o;
		}else{
			return null;
		}
	}
	
	/**
	 * 保存操作员角色关系
	 * @param roles	角色
	 * @param optId 操作员ID
	 */
	@SuppressWarnings("unchecked")
	@Bizlet("保存操作员角色关系")
	public void saveOperatorRole(String roles,String optId){
		Map  map = new HashMap ();
		//创建操作员角色关系
		DataObject optRole = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcOperatorrole");
		String[] roleArr = roles.split(",");
		
		//遍历保存关系
		for (int i = 0; i < roleArr.length; i++) {
			String roleid = roleArr[i];
			optRole.setString("roleid", roleid);
			optRole.setLong("operatorid", Long.valueOf(optId));
			map.put("roleid", roleid);
			map.put("operatorid",  Long.valueOf(optId));
			//插入数据库
			DatabaseExt.executeNamedSql("default", "com.bos.utp.rights.role.rolens.insertOperatorRole", map);
		}
	}
	
	/**
	 * 修改操作员角色关系
	 * @param roles	角色
	 * @param optId 操作员ID
	 */
	@Bizlet("修改操作员角色关系")
	public void updateOperatorRoles(String roles,String optId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorid", Long.valueOf(optId));
		//删除操作角色关系
		DatabaseExt.executeNamedSql("default", "com.bos.utp.rights.role.rolens.deleteOperatorRole", map);
		
		String[] roleArr = roles.split(",");
		//遍历保存关系
		for (int i = 0; i < roleArr.length; i++) {
			String roleid = roleArr[i];
			map.put("roleid", roleid);
			map.put("operatorid",Long.valueOf(optId));
			//插入数据库
			DatabaseExt.executeNamedSql("default", "com.bos.utp.rights.role.rolens.insertOperatorRole", map);
		}
	}

}
