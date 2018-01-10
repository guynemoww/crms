package com.bos.utp.resourcerights.role;

import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet("角色")
public class Role {
	
//	参数o:session中roles对象 参数str:是否具有的角色用;连接  
	//返回is:0表示没有此几种之一，1表示有此几种之一
	@Bizlet("角色列表中是否包含某角色")
	public String rolesif(String roleid,String str){
		String is = "0";
		String[] roles = str.split(";");
		for(int j =0;j < roles.length;j++){
			if(roleid==roles[j] || roleid.equals(roles[j])){
				is = "1";
				break;
			}
		}
		return is;
	}
	
	@Bizlet("角色列表中是否包含某些角色中的一种")
	public String rolesif2(Object[] o,String str){
		String is = "0";
		DataObject role=DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcRole");
		String[] roles = str.split(";");
		for(int i =0;i<o.length;i++){
			role = (DataObject)o[i];
			String r = role.getString("roleid");
			for(int j =0;j < roles.length;j++){
				if(r==roles[j] || r.equals(roles[j])){
					is = "1";
					break;
				}
			}
			if(is=="1" || "1".equals(is)){
				break;
			}
		}
		return is;
	}
	
	/**
	 * 生成角色ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bizlet("生成角色ID")
	public String generateRoleId(String roleType){
		HashMap sqlParams = new HashMap<String, String>();
		sqlParams.put("roleType", "R"+Integer.parseInt(roleType));
		Object[] roleid = DatabaseExt.queryByNamedSql("default","com.bos.utp.resourcerights.role.role.queryRoleId",sqlParams);
		
		if(null != roleid && roleid.length > 0){
			DataObject obj = (DataObject) roleid[0];
			return obj.getString("roleid");
		}
		
		return null;
	}
	
	/**
	 * 判断该角色是否有关联的用户
	 * @param objs
	 * @return
	 */
	@Bizlet("判断该角色是否有关联的用户")
	public String queryRoleBelongUser(DataObject[] objs){
		//遍历需要删除的用户
		for(int i=0;i<objs.length ; i++){
			DataObject obj = objs[i];
			Map<String, String> map = new HashMap<String, String>();
			map.put("roleid", obj.getString("roleid"));
			//统计角色关联的用户个数
			int cnt = DatabaseExt.countByNamedSql("default", "com.bos.utp.resourcerights.role.rolens.countRoleUser", map);
			//有该角色关联的用户
			if(cnt > 0){
				return "-1";
			}
		}
		return "0";
	}
	/**
	 * 删除角色，
	 * ①删除角色操作员关联表
	 * ②删除角色功能关联表
	 * ③删除角色表
	 * @param roleid
	 */
	@Bizlet("删除角色")
	public void deleteRole(String roleid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleid", roleid);
		// 根据机构类型更新机构排序
		try {
			//删除角色操作员关联表
		    DatabaseExt.executeNamedSql("default","com.bos.utp.resourcerights.role.role.deleteResourceRole", map);
		    //删除角色机构关联表
		    DatabaseExt.executeNamedSql("default","com.bos.utp.resourcerights.role.role.deleteOrgRole", map);
		    //删除角色操作员关联表
		    DatabaseExt.executeNamedSql("default","com.bos.utp.resourcerights.role.role.deleteOperatorRole", map);
		    //删除角色功能关联表
		    DatabaseExt.executeNamedSql("default","com.bos.utp.resourcerights.role.role.deleteAcRoleFunc", map);
		    //删除角色表
		    DatabaseExt.executeNamedSql("default","com.bos.utp.resourcerights.role.role.deleteAcRole", map);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 获取角色ID
	 * @param map
	 * @return 
	 */
	@Bizlet("获取角色ID")
	public Object[] queryRoleID(Map map){
		Object[] res = DatabaseExt.queryByNamedSql("default","com.bos.utp.resourcerights.role.rolens.queryRoleID", map);
		int y,Max = 0;
		if(null != res && res.length > 0){
			int[] rs = new int[res.length];
			for(int i=0;i<res.length;i++){
				DataObject obj = (DataObject) res[i];
				String id = obj.getString("ROLEID");
				if(id.length()>=2){
					String paramRoleID = id.substring(0, 2);//截取1-2位
					if(paramRoleID.equals(map.get("roleType"))){
						int rsRoleID = Integer.parseInt(id.substring(1)) + 1;
						rs[i] = rsRoleID;
					}					
				}
			}
			if(rs[0]>rs[1]){
				Max = rs[0];
			}
			else{
				Max = rs[1];
			}
			for(y=2;y<rs.length;y++){
				if(rs[y]>Max){
					Max = rs[y];
				}
			}
		}
		if(Max == 0){
			return new Object[]{null};
		}
		return new Object[]{"R"+String.valueOf(Max)};
	}
}