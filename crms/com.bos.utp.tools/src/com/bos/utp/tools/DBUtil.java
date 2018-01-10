package com.bos.utp.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.bos.pub.ServiceModuleName;
import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.IDASSession;
import com.eos.das.sql.INamedSqlSession;
import com.eos.das.sql.NamedSqlSessionFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.CriteriaUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSRuntimeException;
import commonj.sdo.DataObject;

@Bizlet("数据库工具类")
public class DBUtil {

	// 缺省数据源名称，信贷默认数据库名称
	public final static String DB_NAME_DEF = "default";
	// crms数据库名称 在没有使用数据库别名的Module中使用
	public final static String DB_NAME_CRMS = "crms";
	// 核算数据库名称
	public final static String DB_NAME_APLUS = "aplus";
	// eos框架数据库
	public final static String DB_NAME_BPS = "bps";

	public final static String DB_NAME_SDP = "sdp";

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
		closeConnection(conn, true);
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
		closeConnection(conn, true);
	}

	/**
	 * 从当前的contribution中根据数据源的别名获得数据库连接。
	 * 
	 * @param dsName
	 *            数据源名称。
	 * @return 获得数据库连接。
	 */
	@SuppressWarnings("deprecation")
	public static Connection getConnection(String packname, String name) {
		Connection conn = null;
		if (name == null || name.trim().equals("")) {
			name = DB_NAME_CRMS;
		}
		if (packname == null || packname.trim().equals("")) {
			packname = ServiceModuleName.PUB;
		}
		LogUtil.logDebug("获得构件包{0}中的别名为{1}的数据源", null, new Object[] { packname, name });
		try {
			conn = ConnectionHelper.getConnection(name);
			// conn = ConnectionHelper.getCurrentContributionConnection(name);
			// System.out.println("-----------\n新的获取链接方式" + conn + "\n-----------");
		} catch (Exception e) {
			LogUtil.logError("获取数据源出错", e, (Object) null);
		}
		return conn;
	}

	/**
	 * 根据user-config中的数据库配置名称获取链接
	 * 
	 * @param dsName
	 *            数据源名称。
	 * @return 获得数据库连接。
	 */
	public static Connection getConnection(String name) {
		if (name == null || name.trim().equals("")) {
			name = DB_NAME_CRMS;
		}
		return getConnection(ServiceModuleName.PUB, name);
	}

	/**
	 * 获得com.bos.utp.auth构件包的数据源default数据源
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return getConnection(null);
	}

	private static void closeConnection(Connection conn, boolean throwErr) {
		// System.out.println("-----------\n关闭链接" + conn + "\n-----------");
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				if (throwErr) {
					throw new EOSRuntimeException("Close connection failed!", e);
				}
			}
		}
	}

	private static void closeStatement(Statement... statements) {
		if (statements == null || statements.length == 0) {
			return;
		}
		for (Statement s : statements) {
			try {
				s.close();
			} catch (Exception e) {
			}
		}
	}

	private static void closeResultSet(ResultSet... resultSets) {
		if (resultSets == null || resultSets.length == 0) {
			return;
		}
		for (ResultSet r : resultSets) {
			try {
				r.close();
			} catch (Exception e) {
			}
		}
	}

	public static void closeAll(Connection conn, Statement ps, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(ps);
		closeConnection(conn, false);
	}

	/**
	 * 关闭连接和数据源
	 */
	public static void closeAll(Connection conn, Statement[] statements, ResultSet[] resultsets) {
		closeResultSet(resultsets);
		closeStatement(statements);
		closeConnection(conn, false);
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
	public static List queryNamedSql(String dsName, String namedSql, Object param) {
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
	public static Object[] queryObjectsNamedSql(String dsName, String namedSql, Object param) {
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
	public static DataObject[] queryDataObjectsNamedSql(String namedSql, Object param) {
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
	public static DataObject[] queryDataObjectsNamedSql(String dsName, String namedSql, Object param) {
		@SuppressWarnings("unchecked")
		List<DataObject> result = queryNamedSql(dsName, namedSql, param);
		return result.toArray(new DataObject[result.size()]);
	}

	/**
	 * 更新字段为null
	 * 
	 * @param dsName
	 * @param criteriaEntity
	 * @return 更新的记录数,出错返回-1
	 */
	@Bizlet("更新字段为null")
	public static int updateNullByCriteria(String dsName, DataObject criteriaEntity) {
		int ret = -1;
		Connection conn = getConnection();
		IDASSession session = null;
		try {
			String entityName = criteriaEntity.getString("_entity");
			DataObject obj = DataObjectUtil.createDataObject(entityName);
			session = DASManager.createDasSession(conn);
			IDASCriteria criteria = CriteriaUtil.translateCriteriaEntity2Criteria(dsName, criteriaEntity);
			@SuppressWarnings("unchecked")
			List<String> sel = (List<String>) criteriaEntity.getList("_select/_field");
			for (String field : sel) {
				obj.set(field, null);
			}
			ret = session.update(obj, criteria);
		} catch (Exception e) {
			ret = -1;
			LogUtil.logError("更新字段为null", e, (Object) null);
		} finally {
			if (session != null) {
				session.close();
			}
			closeSession(session, conn);
		}
		return ret;
	}
}