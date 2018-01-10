package com.bos.pub;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.MDC;

import com.eos.das.entity.IDASCriteria;
import com.eos.data.datacontext.DataContextManager;
import com.primeton.das.entity.impl.handler.IEntityHandler;
import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * @author 王世春
 * 
 */
public class EntityInterceptor implements IEntityHandler {
	public void afterLoad(DataObject entity, Serializable primaryKey,
			String[] propertyNames, Object[] values) {
	}

	public void beforeLoad(String entityName, IDASCriteria criteria) {
	}

	/*
	 * 拦截数据实体插入操作
	 */
	public void beforeSave(DataObject entity, Serializable primaryKey,
			String[] propertyNames, Object[] values) {
		long nano = System.nanoTime();
		Timestamp time = GitUtil.getBusiTimestamp();
//		Date date = GitUtil.getBusiDate();
		String user = "";
		String org = "";
		if (null != DataContextManager.current().getMUODataContext()) {
			user = GitUtil.getCurrentUserId();
			org = GitUtil.getCurrentOrgCd();
		}
		String autotimes = entity.getString("autotimes");
		String autousers = entity.getString("autousers");
		String autoorgs = entity.getString("autoorgs");
		if (null != autotimes) {
			String[] arr = autotimes.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autotime = arr[i].trim();
				if (autotime.length() > 0) {
					int autotimeidx = ArrayUtils.indexOf(propertyNames,
							autotime);
					if (autotimeidx >= 0) {
						values[autotimeidx] = time;
						entity.set(autotime, time);
					}
				}
			}
		}
		if (null != autousers) {
			String[] arr = autousers.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autouser = arr[i].trim();
				if (autouser.length() > 0) {
					int autouseridx = ArrayUtils.indexOf(propertyNames,
							autouser);
					if (autouseridx >= 0) {
						values[autouseridx] = user;
						entity.set(autouser, user);
					}
				}
			}
		}
		if (null != autoorgs) {
			String[] arr = autoorgs.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autoorg = arr[i].trim();
				if (autoorg.length() > 0) {
					int autoorgidx = ArrayUtils.indexOf(propertyNames, autoorg);
					if (autoorgidx >= 0) {
						values[autoorgidx] = org;
						entity.set(autoorg, org);
					}
				}
			}
		}

		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if ("version".equals(propertyName) && null == values[i])
				values[i] = 1;
//			if ("createTime".equals(propertyName)
//					|| "updateTime".equals(propertyName)
//					|| "lastUpdateTime".equals(propertyName)) {
//				if (null == values[i]) {
//					Property prop = entity.getInstanceProperty(propertyName);
//					if (null == prop)
//						continue;
//					if ("java.sql.Timestamp".equals(prop.getType()
//							.getInstanceClass().getName())) {
//						values[i] = time;
//						entity.set(propertyName, time);
//					} else if ("java.util.Date".equals(prop.getType()
//							.getInstanceClass().getName())) {
//						values[i] = date;
//						entity.set(propertyName, date);
//					} else {
//						values[i] = date;
//						entity.set(propertyName, date);
//					}
//				}
//				continue;
//			}
//			if ("userNum".equals(propertyName)
//					|| "updateUser".equals(propertyName)
//					|| "lastUpdateUser".equals(propertyName)) {
//				if (null == values[i]) {
//					Property prop = entity.getInstanceProperty(propertyName);
//					if (null == prop)
//						continue;
//					values[i] = user;
//					entity.set(propertyName, user);
//				}
//				continue;
//			}
//			if ("orgNum".equals(propertyName)
//					|| "updateOrg".equals(propertyName)
//					|| "lastUpdateOrg".equals(propertyName)) {
//				if (null == values[i]) {
//					Property prop = entity.getInstanceProperty(propertyName);
//					if (null == prop)
//						continue;
//					values[i] = org;
//					entity.set(propertyName, org);
//				}
//				continue;
//			}
		}
		EntityLogUtil.log("insert", entity, primaryKey, propertyNames, values);
		nano = System.nanoTime() - nano; // ns
		if (MDC.get(Log4jInterceptor.SQL_LOG_TIME) == null) {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(nano));
		} else {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(MDC.get(Log4jInterceptor.SQL_LOG_TIME)
					.toString())
					+ nano);
		}
	}

	/*
	 * 拦截数据实体更新操作
	 */
	public void beforeUpdate(DataObject entity, Serializable primaryKey,
			String[] propertyNames, Object[] previousValues,
			Object[] currentValues) {
		long nano = System.nanoTime();
		Object[] values = currentValues;
		Timestamp time = GitUtil.getBusiTimestamp();
//		Date date = GitUtil.getBusiDate();
		String user = "";
		String org = "";
		if (null != DataContextManager.current().getMUODataContext()) {
			user = GitUtil.getCurrentUserId();
			org = GitUtil.getCurrentOrgCd();
		}
		String autotimes = entity.getString("autotimes");
		String autousers = entity.getString("autousers");
		String autoorgs = entity.getString("autoorgs");
		if (null != autotimes) {
			String[] arr = autotimes.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autotime = arr[i].trim();
				if (autotime.length() > 0) {
					int autotimeidx = ArrayUtils.indexOf(propertyNames,
							autotime);
					if (autotimeidx >= 0) {
						values[autotimeidx] = time;
						entity.set(autotime, time);
					}
				}
			}
		}
		if (null != autousers) {
			String[] arr = autousers.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autouser = arr[i].trim();
				if (autouser.length() > 0) {
					int autouseridx = ArrayUtils.indexOf(propertyNames,
							autouser);
					if (autouseridx >= 0) {
						values[autouseridx] = user;
						entity.set(autouser, user);
					}
				}
			}
		}
		if (null != autoorgs) {
			String[] arr = autoorgs.split(",");
			for (int i = 0; i < arr.length; i++) {
				String autoorg = arr[i].trim();
				if (autoorg.length() > 0) {
					int autoorgidx = ArrayUtils.indexOf(propertyNames, autoorg);
					if (autoorgidx >= 0) {
						values[autoorgidx] = org;
						entity.set(autoorg, org);
					}
				}
			}
		}

		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if ("version".equals(propertyName) && null == values[i])
				values[i] = 1;
//			if (("createTime".equals(propertyName) && null == values[i])
//					|| "updateTime".equals(propertyName)
//					|| "lastUpdateTime".equals(propertyName)) {
//				Property prop = entity.getInstanceProperty(propertyName);
//				if (null == prop)
//					continue;
//				if ("java.sql.Timestamp".equals(prop.getType()
//						.getInstanceClass().getName())) {
//					values[i] = time;
//					entity.set(propertyName, time);
//				} else if ("java.util.Date".equals(prop.getType()
//						.getInstanceClass().getName())) {
//					values[i] = date;
//					entity.set(propertyName, date);
//				} else {
//					values[i] = date;
//					entity.set(propertyName, date);
//				}
//				continue;
//			}
//			if ("userNum".equals(propertyName)
//					|| "updateUser".equals(propertyName)
//					|| "lastUpdateUser".equals(propertyName)) {
//				if (null == values[i]) {
//					Property prop = entity.getInstanceProperty(propertyName);
//					if (null == prop)
//						continue;
//					values[i] = user;
//					entity.set(propertyName, user);
//				}
//				continue;
//			}
//			if ("orgNum".equals(propertyName)
//					|| "updateOrg".equals(propertyName)
//					|| "lastUpdateOrg".equals(propertyName)) {
//				if (null == values[i]) {
//					Property prop = entity.getInstanceProperty(propertyName);
//					if (null == prop)
//						continue;
//					values[i] = org;
//					entity.set(propertyName, org);
//				}
//				continue;
//			}
		}
		EntityLogUtil.log("update", entity, primaryKey, propertyNames,
				currentValues);
		nano = System.nanoTime() - nano; // ns
		if (MDC.get(Log4jInterceptor.SQL_LOG_TIME) == null) {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(nano));
		} else {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(MDC.get(Log4jInterceptor.SQL_LOG_TIME)
					.toString())
					+ nano);
		}
	}

	/*
	 * 拦截数据实体删除操作
	 */
	public void afterDelete(DataObject entity, Serializable primaryKey,
			String[] propertyNames, Object[] values) {
		long nano = System.nanoTime();
		EntityLogUtil.log("delete", entity, primaryKey, propertyNames, values);
		nano = System.nanoTime() - nano; // ns
		if (MDC.get(Log4jInterceptor.SQL_LOG_TIME) == null) {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(nano));
		} else {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(MDC.get(Log4jInterceptor.SQL_LOG_TIME)
					.toString())
					+ nano);
		}
	}
}
