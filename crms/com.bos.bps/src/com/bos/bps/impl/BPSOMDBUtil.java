package com.bos.bps.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.IDASSession;
import com.eos.das.sql.INamedSqlSession;
import com.eos.das.sql.NamedSqlSessionFactory;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSRuntimeException;
import commonj.sdo.DataObject;

@Bizlet("数据库工具类")
public class BPSOMDBUtil {
	// 获取数据源的构件包
	private static String dsPackage = getDsPackage();

	// 缺省数据源名称
	private static String dsName = getDsName();

	/**
	 * 从配置文件中获取数据源的来源构件包
	 * 
	 * @return
	 */
	private static String getDsPackage() {
		return ConfigurationUtil.getContributionConfig(
				"com.bos.bps", "utpframe", "DataSource",
				"contribution");
	}

	/**
	 * 从配置文件中获取数据源的来源构件包
	 * 
	 * @return
	 */
	private static String getDsName() {
		return ConfigurationUtil.getContributionConfig(
				"com.bos.bps", "utpframe", "DataSource", "dsname");
	}

	/**
	 * 关闭命名sql的DAS session和数据库连接。
	 * 
	 * @param session
	 *            数据访问会话对象。
	 * @param conn
	 *            数据库连接。
	 */
	public static void closeSession(INamedSqlSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close session failed!", e);
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close connection failed!", e);
			}
		}
	}

	/**
	 * 关闭查询的DAS session和数据库连接。
	 * 
	 * @param session
	 *            数据访问会话对象。
	 * @param conn
	 *            数据库连接。
	 */
	public static void closeSession(IDASSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close session failed!", e);
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				throw new EOSRuntimeException("Close connection failed!", e);
			}
		}
	}

	/**
	 * 从当前的contribution中根据数据源的别名获得数据库连接。
	 * 
	 * @param dsName
	 *            数据源名称。
	 * @return 获得数据库连接。
	 */
	public static Connection getConnection(String packname, String name) {
		Connection conn = null;
		if (name == null || name.trim().equals("")) {
			name = dsName;
		}
		if (packname == null || packname.trim().equals("")) {
			packname = dsPackage;
		}
		LogUtil.logDebug("获得构件包{0}中的别名为{1}的数据源", null, new Object[] { packname,
				name });
		try {
			conn = ConnectionHelper.getContributionConnection(packname, name);
		} catch (Exception e) {
			LogUtil.logError("获取数据源出错", e, (Object) null);
		}
		return conn;
	}

	/**
	 * 从org.gocom.abframe.auth构件包中根据数据源的别名获得数据库连接。
	 * 
	 * @param dsName
	 *            数据源名称。
	 * @return 获得数据库连接。
	 */
	public static Connection getConnection(String name) {
		if (name == null || name.trim().equals(""))
			name = dsName;

		return getConnection(dsPackage, name);
	}

	/**
	 * 获得org.gocom.abframe.auth构件包的数据源default数据源
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return getConnection(null);
	}

	/**
	 * 关闭连接和数据源
	 */
	public static void closeAll(Connection conn, PreparedStatement[] ps,
			ResultSet[] rs) {
		for (int i = 0; i < rs.length; i++) {
			try {
				if (rs[i] != null) {
					rs[i].close();
				}
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < ps.length; i++) {
			try {
				if (ps[i] != null) {
					ps[i].close();
				}
			} catch (Exception e) {
			}
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@Bizlet("执行命名sql")
	public static List queryNamedSql(String dsName, String namedSql,
			Object param) {
		Connection conn = null;
		INamedSqlSession session = null;
		try {
			conn = getConnection(dsName);
			session = NamedSqlSessionFactory.createSQLMapSession(conn);
			List result = session.queryForList(namedSql, param);
			return result;
		} finally {
			closeSession(session, conn);
		}
	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@Bizlet("执行命名sql")
	public static List queryNamedSql(String namedSql, Object param) {
		return queryNamedSql(null, namedSql, param);
	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@Bizlet("执行命名sql")
	public static Object[] queryObjectsNamedSql(String namedSql, Object param) {
		return queryObjectsNamedSql(null, namedSql, param);

	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@Bizlet("执行命名sql")
	public static Object[] queryObjectsNamedSql(String dsName, String namedSql,
			Object param) {
		List result = queryNamedSql(dsName, namedSql, param);
		return result.toArray();
	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */

	@Bizlet("执行命名sql")
	public static DataObject[] queryDataObjectsNamedSql(String namedSql,
			Object param) {
		return queryDataObjectsNamedSql(null, namedSql, param);

	}

	/**
	 * 执行命名sql
	 * 
	 * @param session
	 * @param namedSql
	 * @param param
	 * @return
	 */
	@Bizlet("执行命名sql")
	public static DataObject[] queryDataObjectsNamedSql(String dsName,
			String namedSql, Object param) {
		@SuppressWarnings("unchecked")
		List<DataObject> result = queryNamedSql(dsName, namedSql, param);
		return result.toArray(new DataObject[result.size()]);
	}
}
