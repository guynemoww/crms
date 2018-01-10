package com.bos.utp.auth.permission;



/**
 *
 * 权限数据访问接口
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PermissionDataAccessor.java,v $
 * Revision 1.5  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.4  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.3  2009/05/14 11:18:59  caisy
 * Update:Portal缓存判断错误，会导致所有的功能被人为portal资源
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 *
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.3  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.2  2008/09/23 09:25:03  wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public interface PermissionDataAccessor {

	/**
	 * 获取所有角色的功能访问权限
	 *
	 * @return SDO数据对象数组
	 */
	public Object[] loadRolePermissionAll()throws Exception;

	/**
	 * 获取指定角色的功能访问权限
	 *
	 * @param roleId 角色ID
	 * @return SDO数据对象数组
	 */
	public Object[] loadRolePermissionAll(String roleId)throws Exception;

	/**
	 * 加载所有操作员的功能权限
	 * @return
	 */
	public Object[] loadOperatorFunctionAll()throws Exception;

	/**
	 * 加载指定操作员的功能权限
	 * @param operatorid 操作员ID
	 * @return
	 */
	public Object[] loadOperatorFunctionAll(String operatorid)throws Exception;


	/**
	 * 加载所有Portal资源
	 * @return
	 */
	public Object[] loadPortalResourceAll()throws Exception;
	
	/**
	 * 加载指定Portal资源
	 * @return
	 */
	public Object loadPortalResourceAll(String portalid)throws Exception;



}
