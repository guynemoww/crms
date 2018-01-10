package com.bos.utp.auth.permission;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.IDASSession;
import com.eos.das.sql.INamedSqlSession;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.utility.StringUtil;


/**
 *
 * 基于命名SQL的权限数据实现类<BR>
 * 实现从指定的数据源中加载所有角色对应的功能权限
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: RDBMSPermissionDataAccessor.java,v $
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
 * Revision 1.2  2008/11/16 08:43:25  wengzr
 * Update:修复NPE错误
 *
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.3  2008/09/23 09:25:03  wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 *
 * Revision 1.2  2008/09/09 13:11:29  wengzr
 * Refactor:重命名authNamingsql为permission
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public class RDBMSPermissionDataAccessor implements PermissionDataAccessor {

	/**
	 * 从当前的contribution中根据数据源的别名获得数据库连接。
	 * @param dsName 数据源名称。
	 * @return 获得数据库连接。
	 */
	protected static Connection getConnection(String dsName) {
		String value = dsName;
		if (value == null || value.trim().equals(""))
			value = "default";

		return ConnectionHelper.getCurrentContributionConnection(value);
	}

	/**
	 * 关闭DAS session和数据库连接。
	 * @param session 数据访问会话对象。
	 * @param conn 数据库连接。
	 */
	protected static void closeConnection(IDASSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close session failed!", e);
			}
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new EOSRuntimeException("Close connection failed!", e);
		}
	}

	/**
	 * 关闭命名sql的DAS session和数据库连接。
	 * @param session 数据访问会话对象。
	 * @param conn 数据库连接。
	 */
	protected static void closeConnection(INamedSqlSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close session failed!", e);
			}
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new EOSRuntimeException("Close connection failed!", e);
		}
	}


	public Object[] loadRolePermissionAll()throws Exception {
		return DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadRolePermissionAll", new HashMap());
	}

	public Object[] loadRolePermissionAll(String roleId)throws Exception {
		Map<String, String> parameterMap=new HashMap<String, String>();
		if(StringUtil.isNotNullAndBlank(roleId))
			parameterMap.put("roleid", roleId);

		return DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadRolePermissionAll", parameterMap);
	}

	public Object[] loadOperatorFunctionAll()throws Exception {
		return DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadOperatorFunctionAll", new HashMap());
	}

	public Object[] loadOperatorFunctionAll(String operatorid)throws Exception {
		Map<String, String> parameterMap=new HashMap<String, String>();
		if(StringUtil.isNotNullAndBlank(operatorid))
			parameterMap.put("operatorid", operatorid);

		return DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadOperatorFunctionAll", parameterMap);

	}

	public Object[] loadPortalResourceAll()throws Exception {
		return DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadPortalResourceAll",new HashMap());
	}
	public Object loadPortalResourceAll(String url)throws Exception {
		Object ret = null;
		HashMap<String ,String> param = new HashMap<String,String>();
		param.put("url", url);
		Object[] obj = DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.permission.loadPortalResourceAll",param);
		if(obj!=null&&obj.length>0){
			ret = obj[0];
		}
		return ret;
	}

}
