package com.bos.utp.auth.permission;

/**
 * 
 * 权限对象的线程本地变量实现<BR>
 * 将权限对象PermissionChecker设置到线程变量，以便在其他后续处理可以简易获取该对象
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PermissionThreadLocal.java,v $
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
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/08/17 08:12:56  wengzr
 * Update:增加LDAP,SSO默认实现
 *
 * Revision 1.1  2008/08/14 15:46:45  wengzr
 * 提交CVS
 *
 */
public class PermissionThreadLocal {

	private static ThreadLocal<PermissionChecker> _threadLocal =new ThreadLocal<PermissionChecker>();
	
	public static PermissionChecker getPermissionChecker() {
		return _threadLocal.get();
	}

	public static void setPermissionChecker(PermissionChecker permissionChecker) {

		_threadLocal.set(permissionChecker);
	}

	

}
