package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;

import com.bos.utp.auth.permission.model.FunctionModel;
import com.bos.utp.auth.permission.model.OperatorFunction;
import com.bos.utp.auth.permission.model.RoleFunction;
import com.bos.utp.auth.permission.model.RoleModel;
import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheRuntimeException;
import com.eos.common.cache.ICache;

/**
 * 
 * 功能权限缓存管理器,实现对功能权限缓存的调用和更新.
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: PermissionCacheManager.java,v $
 * 修改历史 Revision 1.6  2010/12/01 03:23:14  caisy
 * 修改历史 配置文件读取方式修改
 * 修改历史
 * 修改历史 Revision 1.5  2010/11/30 16:12:23  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.4  2009/05/19 05:30:50  caisy
 * 修改历史 Update:Functionaction为空导致无法构件权限缓存
 * 修改历史 Revision 1.3 2009/04/14 03:54:32
 * caisy 资源权限控制
 * 
 * Revision 1.2 2009/03/30 05:39:38 caisy 代码规范
 * 
 * Revision 1.1 2009/01/05 02:34:50 caisy 二期初始版本
 * 
 * Revision 1.2 2008/11/16 08:43:25 wengzr Update:修复NPE错误
 * 
 * Revision 1.1 2008/10/07 09:25:49 wengzr *** empty log message ***
 * 
 * Revision 1.1 2008/09/25 16:35:51 wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包 并修改缓存模型删除模型中的名称XXXname字段
 * 
 * Revision 1.5 2008/09/23 09:25:03 wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 * 
 * Revision 1.4 2008/09/07 07:40:10 wengzr Update:修改权限校验接口
 * 
 * Revision 1.3 2008/08/19 18:51:23 wengzr Update:更新LDAP实现,增加用户组的映射
 * 
 * Revision 1.2 2008/08/18 14:19:49 wengzr 提交CVS
 * 
 * Revision 1.1 2008/08/13 15:55:59 wengzr 提交CVS
 * 
 */
public class PermissionCacheManager {

	public static final String ADDITIONAL_FUNCTION = "_additional_function_";

	public static final String CACHE_INSTANCE = "CacheForPermission";

	private final static String ALLSPACE = "^ *$";

	public static ICache getCache() {
		ICache icache = CacheFactory.getInstance().findCache(CACHE_INSTANCE);
		if (icache == null) {
			throw new CacheRuntimeException("not found cache \"" + CACHE_INSTANCE + "\"");
		}
		return icache;
	}

	/**
	 * 获取角色的权限模型
	 * 
	 * @param roleId
	 *            角色ID
	 * @return RoleModel权限模型对象
	 */
	public static RoleModel getPermissionByRole(String roleId) {
		return (RoleModel) getCache().get(roleId);
	}

	/**
	 * 获取操作员的权限模型<br>
	 * 操作员的特殊功能权限会附加到一个_additional_function为前缀+操作员ID为角色ID的模型中
	 * 
	 * @param operatorId
	 *            操作员ID
	 * @return RoleModel权限模型对象
	 */
	public static RoleModel getPermissionByOperator(String operatorId) {
		return (RoleModel) getCache().get(ADDITIONAL_FUNCTION + operatorId);
	}

	/**
	 * 构造权限内存模型<BR>
	 * 系统权限按授权类型分，角色权限和操作员权限;角色权限即通过角色获取对应角色的功能权限
	 * 操作员权限即通过操作员获取直接赋予操作员的功能权限.在权限处理模型中，统一以角色模型RoleModel来构造内存模型.
	 * 即允许以roleid或operatorid获取对应的权限.
	 * 
	 * @param permissions
	 *            功能权限对象数组
	 * @return
	 */
	public static Map buildPermissionMap(Object[] permissions) {

		Map<String, RoleModel> cache = new HashMap<String, RoleModel>();

		if (permissions != null && permissions.length > 0) {
			for (int i = 0; i < permissions.length; i++) {
				// 角色功能权限
				if (permissions[i] instanceof RoleFunction) {
					RoleFunction roleFunction = (RoleFunction) permissions[i];
					String action = roleFunction.getFuncaction();
					if (action != null && !action.matches(ALLSPACE)) {
						action = action.trim();
						String roleid = roleFunction.getRoleid();
						if (cache.get(roleid) != null) {
							RoleModel roleModel = (RoleModel) cache.get(roleid);
							FunctionModel functionModel = roleModel.getFunctions().get(action);
							if (functionModel == null) {
								functionModel = new FunctionModel(roleFunction);
							}
							functionModel.addResource(roleFunction.getResid(), roleFunction.getRespath(), roleFunction.getRestype());
							// 为角色更新或添加功能,去除首尾空格
							roleModel.getFunctions().put(action, functionModel);
						} else {
							RoleModel roleModel = new RoleModel(roleid);
							FunctionModel functionModel = new FunctionModel(roleFunction);
							functionModel.addResource(roleFunction.getResid(), roleFunction.getRespath(), roleFunction.getRestype());
							roleModel.getFunctions().put(action, functionModel);
							cache.put(roleid, roleModel);
						}
					} else {
						break;
					}
				}
				// 操作员特殊功能
				// RoleModel设置为特殊功能的角色"_additional_function_"+operatorId
				if (permissions[i] instanceof OperatorFunction) {
					OperatorFunction operatorFunction = (OperatorFunction) permissions[i];
					String action = operatorFunction.getFuncaction();
					if (action != null && !action.matches(ALLSPACE)) {
						action.trim();
						String roleid = ADDITIONAL_FUNCTION + operatorFunction.getOperatorid();
						if (cache.get(roleid) != null) {
							RoleModel roleModel = (RoleModel) cache.get(roleid);
							FunctionModel functionModel = roleModel.getFunctions().get(action);
							if (functionModel == null) {
								functionModel = new FunctionModel(operatorFunction);
							}
							functionModel.addResource(operatorFunction.getResid(), operatorFunction.getRespath(), operatorFunction.getRestype());
							// 为角色更新或添加功能
							roleModel.getFunctions().put(action, functionModel);
						} else {
							RoleModel roleModel = new RoleModel(roleid);
							FunctionModel functionModel = new FunctionModel(operatorFunction);
							functionModel.addResource(operatorFunction.getResid(), operatorFunction.getRespath(), operatorFunction.getRestype());
							roleModel.getFunctions().put(action, functionModel);
							cache.put(roleid, roleModel);
						}
					} else {
						break;
					}
				}
			}

		}

		return cache;
	}

	/**
	 * 清除对应角色的缓存
	 * 
	 * @param key
	 *            角色ID
	 */
	public static void removeCacheByRole(Object key) {
		getCache().remove(key);
	}

	/**
	 * 清除对应操作员的功能缓存
	 * 
	 * @param key
	 *            操作员ID
	 */
	public static void removeCacheByOperator(Object key) {
		getCache().remove(ADDITIONAL_FUNCTION + key);
	}

	public static void clearCacheAll() {
		getCache().clear();
	}
}
