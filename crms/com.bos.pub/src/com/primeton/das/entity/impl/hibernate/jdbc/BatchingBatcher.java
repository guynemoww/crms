package com.primeton.das.entity.impl.hibernate.jdbc;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bos.pub.GitUtil;
import com.eos.system.logging.Logger;
import com.primeton.das.entity.impl.hibernate.HibernateException;
import com.primeton.das.entity.impl.hibernate.Interceptor;
import com.primeton.ext.infra.connection.PreparedStatementWrapper;

public class BatchingBatcher extends AbstractBatcher {
	private int batchSize;

	private Expectation[] expectations;

	private Logger log = GitUtil.getLogger("com.bos.pub.GitUtil");

	public BatchingBatcher(ConnectionManager connectionManager,
			Interceptor interceptor) {
		super(connectionManager, interceptor);
		this.expectations = new Expectation[getFactory().getSettings()
				.getJdbcBatchSize()];
	}

	public void addToBatch(Expectation expectation) throws SQLException,
			HibernateException {
		if (!(expectation.canBeBatched()))
			throw new HibernateException(
					"attempting to batch an operation which cannot be batched");

		PreparedStatement batchUpdate = getStatement();
		// 打印SQL开始
		if (batchUpdate instanceof PreparedStatementWrapper) {
			PreparedStatementWrapper wrapper = (PreparedStatementWrapper) batchUpdate;
			PreparedStatement st = wrapper.getTargetStatement();

			// 只针对c3p0数据源，该类数据源通常是用于开发人员调试的
			if (st.getClass().getName().equals(
					"com.mchange.v2.c3p0.impl.NewProxyPreparedStatement")) {
				try {
					Field innerField = st.getClass().getDeclaredField("inner");
					innerField.setAccessible(true);
					Object obj = innerField.get(st);
					if ("com.ibm.db2.jcc.b.tf".equals(obj.getClass().getName())) {// 在WebSphere中，此处无法加载db2的类
						// com.ibm.db2.jcc.b.tf inner =
						// (com.ibm.db2.jcc.b.tf)obj;
						// 在tomcat中加载的是这个类
						Field DbField = obj.getClass().getDeclaredField("Db");
						DbField.setAccessible(true);
						Field GbField = obj.getClass().getDeclaredField("Gb");
						GbField.setAccessible(true);
						String sql = DbField.get(obj).toString();
						Object[] params = (Object[]) GbField.get(obj);
						for (int i = 0; i < params.length; i++) {
							Object param = params[i];
							if (null == param)
								param = "";
							if (param instanceof Number) {
								sql = sql.replaceFirst("\\?", param.toString());
							} else {
								sql = sql.replaceFirst("\\?", "'"
										+ param.toString() + "'");
							}
						}
						log.debug("\nsql after replace: \n" + sql);// 通过EOS组件增删改数据时，尝试替换后的sql
					} else if ("com.ibm.db2.jcc.am.lo".equals(obj.getClass()
							.getName())) {
						// 在WebSphere中加载的是这个类
						Field mdField = obj.getClass().getDeclaredField("md");
						mdField.setAccessible(true);
						Object[] params = (Object[]) mdField.get(obj);
						// System.out.println("WebSphere db2 param:"
						// + ArrayUtils.toString(params));

						Field hdField = obj.getClass().getDeclaredField("hd");
						hdField.setAccessible(true);
						String sql = hdField.get(obj).toString();
						for (int i = 0; i < params.length; i++) {
							Object param = params[i];
							if (null == param)
								param = "";
							if (param instanceof Number) {
								sql = sql.replaceFirst("\\?", param.toString());
							} else {
								sql = sql.replaceFirst("\\?", "'"
										+ param.toString() + "'");
							}
						}
						log.debug("\nsql after replace(WAS): \n" + sql);
					} else {
						log.debug("\n\n\nDB2 driver class name:"
								+ obj.getClass().getName());
					}
				} catch (Exception e) {
					log.debug("尝试记录SQL日志时出错", e);
				}
			} else {
				log.debug("TargetStatement class name:"
						+ st.getClass().getName());
			}
		}
		// 打印SQL结束

		batchUpdate.addBatch();
		this.expectations[(this.batchSize++)] = expectation;
		if (this.batchSize == getFactory().getSettings().getJdbcBatchSize())
			doExecuteBatch(batchUpdate);
	}

	protected void doExecuteBatch(PreparedStatement ps) throws SQLException,
			HibernateException {
		if (this.batchSize == 0) {
			if (log.isDebugEnabled())
				log.debug("no batched statements to execute");
		} else {
			if (log.isDebugEnabled())
				log.debug("Executing batch size: " + this.batchSize);

			try {
				checkRowCounts(ps.executeBatch(), ps);
			} catch (RuntimeException re) {
				throw re;
			} finally {
				this.batchSize = 0;
			}
		}
	}

	private void checkRowCounts(int[] rowCounts, PreparedStatement ps)
			throws SQLException, HibernateException {
		if (null != rowCounts && rowCounts.length == 1) {
			// if (ps instanceof StatementWrapper) {
			// StatementWrapper st = (StatementWrapper) ps;
			// int cnt = st.getUpdateCount();
			// String sql = st.getSql();
			// sql = (sql == null ? "" : sql.trim().toUpperCase());
			// // 不判断insert语句，只判断update和delete
			// if (sql.startsWith("UPDATE ") || sql.startsWith("DELETE "))
			// if (sql.endsWith("VERSION=?")) {
			// // 根据SQL判断是否本次更新或删除有版本机制
			// log.debug("根据SQL判断出本次更新或删除有版本机制: " + sql);
			// if (cnt > 1) {
			// throw new HibernateException(
			// "发生错误: 一次更新或删除的记录数超过一条！");
			// }
			// if (cnt < 1) {
			// throw new HibernateException(
			// "发生错误: 一次更新或删除的记录数少于一条，该条记录可能已被其他更新覆盖或删除！");
			// }
			// }
			// }
		}
	}
}