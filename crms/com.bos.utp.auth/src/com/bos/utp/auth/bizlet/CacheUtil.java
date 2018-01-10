package com.bos.utp.auth.bizlet;


import com.bos.utp.auth.cache.BusinessLogCacheManager;
import com.bos.utp.auth.cache.FunctionCacheManager;
import com.bos.utp.auth.cache.PermissionCacheManager;
import com.bos.utp.auth.cache.PortalResourceCacheManager;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;

/**
 *
 * ABFrame 缓存操作工具类
 *
 * @author 翁增仁
 * (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: CacheUtil.java,v $
 * Revision 1.7  2010/12/01 02:53:34  caisy
 * *** empty log message ***
 *
 * Revision 1.6  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.5  2010/09/28 07:46:11  caisy
 * 删除无用代码
 *
 * Revision 1.4.6.1  2010/04/28 09:30:14  caisy
 * 增加功能缓存
 *
 * Revision 1.4  2009/04/22 10:10:22  caisy
 * 注释
 *
 * Revision 1.3  2009/04/14 03:54:32  caisy
 * 资源权限控制
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 * 二期初始版本
 *
 * Revision 1.1  2008/11/24 07:28:15  wengzr
 * Update:重构PermissionUtil->CacheUtil
 * Update:修改业务日志
 *
 * Revision 1.3  2008/10/29 16:07:28  caisy
 * Update:人员权限设置
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.12  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.11  2008/09/23 15:32:40  wengzr
 * Update:修改删除角色缓存和功能缓存参数类型为Object
 *
 * Revision 1.10  2008/09/23 09:25:03  wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 *
 * Revision 1.9  2008/09/10 09:03:30  wengzr
 * Update:重构PermissionUtil,抽取数据对象到DataObjectExtUtil
 *
 * Revision 1.8  2008/09/07 17:00:48  caisy
 * 重命名内部方法 isRepeatProperty为isDuplicateProperty
 *
 * Revision 1.7  2008/09/07 07:40:10  wengzr
 * Update:修改权限校验接口
 *
 * Revision 1.6  2008/08/31 11:27:56  wengzr
 * Update:修改获取操作员所有权限过滤重复角色ID
 *
 * Revision 1.5  2008/08/28 17:30:11  wengzr
 * Update:增加注释
 *
 * Revision 1.4  2008/08/28 12:20:03  wengzr
 * Update:增加缓存删除运算逻辑
 *
 * Revision 1.3  2008/08/21 09:41:07  wengzr
 * Update:修改createPermissionChecker方法实现为MUOContext赋值
 *
 * Revision 1.2  2008/08/17 08:12:57  wengzr
 * Update:增加LDAP,SSO默认实现
 *
 */
@Bizlet("权限运算逻辑")
public class CacheUtil {
	/**
	 * 删除对应角色的功能缓存<BR>
	 * 当新增，修改，删除某个功能时，通过获取该功能的角色列表，进行逐一删除对应缓存
	 * @param roleId 角色ID
	 * @throws Exception
	 */
	@Bizlet(value="删除角色的功能缓存",params = { @BizletParam(index = 0, paramAlias = "roleId") })
	public static void removeCacheByRole(Object roleId)throws Exception{
		PermissionCacheManager.removeCacheByRole(roleId);
	}

	/**
	 * 删除对应操作员的功能缓存<BR>
	 * 当新增，修改，删除某个功能时，通过获取该功能的操作员列表，进行逐一删除对应缓存
	 * @param roleId 操作员ID
	 * @throws Exception
	 */
	@Bizlet(value="删除操作员的功能缓存",params = { @BizletParam(index = 0, paramAlias = "operatorId") })
	public static void removeCacheByOperator(Object operatorId)throws Exception{
		PermissionCacheManager.removeCacheByOperator(operatorId);
	}
	/**
	 * 重载权限资源缓存<BR>
	 * 当新增，修改，删除某个功能时，重载权限资源缓存	 * 
	 * @throws Exception
	 */
	@Bizlet(value="重载Portal资源缓存")
	public static void reloadCacheForPermission()throws Exception{
		PermissionCacheManager.clearCacheAll();
		FunctionCacheManager.clearCache();
	}
	/**
	 * 重载Portal资源缓存<BR>
	 * 当修改Portal资源列表时，需要调用该方法以保持缓存的数据与数据库同步
	 * @throws Exception
	 */
	@Bizlet(value="重载Portal资源缓存")
	public static void reloadCacheForPortalResource()throws Exception{
		PortalResourceCacheManager.clearCache();
	}
	
	/**
	 * 重载功能资源缓存<BR>
	 * 当修改功能资源列表时，需要调用该方法以保持缓存的数据与数据库同步
	 * @throws Exception
	 */
	@Bizlet(value="重载Portal资源缓存")
	public static void reloadCacheForFunction()throws Exception{
		FunctionCacheManager.clearCache();
	}
	/**
	 * 清除业务日志缓存
	 * @throws Exception
	 */
	@Bizlet(value="重载业务日志缓存")
	public static void reloadCacheForBusinessLog()throws Exception{
		BusinessLogCacheManager.clearCache();
	}
}
