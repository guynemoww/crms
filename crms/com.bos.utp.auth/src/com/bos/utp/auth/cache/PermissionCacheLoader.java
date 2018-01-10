package com.bos.utp.auth.cache;

import java.util.Map;

/**
 *
 * 功能权限数据加载器(配置在config/user_config.xml)<BR>
 * 当对应角色的功能发生变更(新增/修改/删除)时，为了保持数据同步从缓存中移除相应角色的key，
 * 当下次调用时，从CacheLoader重新加载数据。
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PermissionCacheLoader.java,v $
 * Revision 1.4  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.3  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
 *
 * Revision 1.2  2008/11/16 08:43:25  wengzr
 * Update:修复NPE错误
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.1  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.4  2008/09/11 09:59:07  wengzr
 * Update:修改获取操作员的所有角色实现,增加运算逻辑appendObject和getUniqueObjects
 *
 * Revision 1.3  2008/08/21 09:42:24  wengzr
 * Refactor:重构sso包分opensso,simple以及cas的集成
 *
 * Revision 1.2  2008/08/19 18:51:23  wengzr
 * Update:更新LDAP实现,增加用户组的映射
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public class PermissionCacheLoader extends BasePermissionCacheLoader{

	public PermissionCacheLoader(){
		super();
	}

	/**
	 * 从数据源中加载对应数据到缓存<BR>
	 * 如果key的前缀为"_additional_function_",则表示获取操作员的功能权限,否则表示获取操作员的角色权限.
	 */
	public Object get(Object key) {

		String roleId=(String)key;
		Object[] permissions=null;
		try{
			if(roleId.startsWith(PermissionCacheManager.ADDITIONAL_FUNCTION)){
				String operatorId=roleId.substring(PermissionCacheManager.ADDITIONAL_FUNCTION.length());
				permissions=accessor.loadOperatorFunctionAll(operatorId);
			}else{
				permissions=accessor.loadRolePermissionAll(roleId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		Map permissionMap=PermissionCacheManager.buildPermissionMap(permissions);
		return permissionMap.get(roleId);
	}

	public Map preLoad() {

		Object[] rolePermissions=new Object[0];
		Object[] operatorPermissions=new Object[0];
		try{
			rolePermissions=accessor.loadRolePermissionAll();
			operatorPermissions=accessor.loadOperatorFunctionAll();

		}catch(Exception e){
			e.printStackTrace();
		}

		//即有角色权限又有操作员权限
		if(rolePermissions.length>0&&operatorPermissions.length>0){
			//角色权限临时变量
			Object[] temp=new Object[rolePermissions.length];
			System.arraycopy(rolePermissions, 0, temp, 0, rolePermissions.length);

			rolePermissions=new Object[temp.length+operatorPermissions.length];
			System.arraycopy(temp, 0, rolePermissions, 0, temp.length);
			System.arraycopy(operatorPermissions, 0, rolePermissions, temp.length, operatorPermissions.length);

		//只有操作员权限
		}else if(operatorPermissions.length>0){
			rolePermissions=new Object[operatorPermissions.length];
			System.arraycopy(operatorPermissions, 0, rolePermissions, 0, operatorPermissions.length);
		}
		return PermissionCacheManager.buildPermissionMap(rolePermissions);

	}

	public void put(Object arg0, Object arg1) {

	}

	public Object remove(Object arg0) {
		return null;
	}

}
