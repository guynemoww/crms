package com.bos.utp.auth.permission;

import com.eos.data.datacontext.IUserObject;

/**
 *
 * TODO 需重新规划
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PermissionChecker.java,v $
 * Revision 1.5  2010/12/07 03:09:46  caisy
 *
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
 *
 * Revision 1.2  2008/12/09 14:49:38  lindf
 * 修改AUTH_TYPE_ON和AUTH_TYPE_OFF的值为'1'和'2'，和逻辑流中保持一致
 *
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.7  2008/09/11 13:55:57  wengzr
 * Update:修改登录实现方式和权限校验BUG
 *
 * Revision 1.6  2008/09/11 09:59:07  wengzr
 * Update:修改获取操作员的所有角色实现,增加运算逻辑appendObject和getUniqueObjects
 *
 * Revision 1.5  2008/09/07 07:40:10  wengzr
 * Update:修改权限校验接口
 *
 * Revision 1.4  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 * Revision 1.3  2008/08/17 08:12:56  wengzr
 * Update:增加LDAP,SSO默认实现
 *
 * Revision 1.2  2008/08/14 15:46:45  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public interface PermissionChecker extends java.io.Serializable{

	/**
	 * 特别开通
	 */
	public static final String AUTH_TYPE_ON="1";

	/**
	 * 特别禁止
	 */
	public static final String AUTH_TYPE_OFF="2";

	public static final int AUTH_TYPE_RESULT_ON=1;

	public static final int AUTH_TYPE_RESULT_OFF=2;

	public static final int AUTH_TYPE_RESULT_NONE=0;

	/**
	 * 获取操作员ID
	 * @return
	 */
	public String getOperatorId();

	/**
	 * 获取用户所有角色
	 * @return
	 */
	public String[] getOperatorRoles();

	/**
	 * 初始化用户对象
	 * @param userObject
	 * @param checkGuest
	 */
	public void init(IUserObject userObject,boolean checkGuest);

	/**
	 * 设置是否校验guest用户
	 *
	 * @param checkGuest
	 */
	public void setCheckGuest(boolean checkGuest);

	public boolean getCheckGuest();

	/**
	 * 判断当前操作员是否具有访问权限
	 * @param action 功能入口
	 * @param isResource 是否资源
	 *
	 * @return true-有权限,false-无权限
	 */
	public boolean hasAccessPermission(String action,boolean isResource);

	/**
	 * 资源回收
	 *
	 */
	public void recycle();
}
