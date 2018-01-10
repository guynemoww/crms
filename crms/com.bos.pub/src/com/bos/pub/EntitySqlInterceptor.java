package com.bos.pub;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.MDC;

import com.bos.utp.tools.SystemInfo;
import com.eos.system.logging.Logger;
import com.primeton.das.sql.impl.handler.INamedSqlHandler;
import com.primeton.das.sql.impl.ibatis.sqlmap.client.InterceptorContext;
import com.primeton.das.sql.impl.ibatis.sqlmap.client.InterceptorContext.Param;

/**
 * @author 王世春
 * @description 命名SQL拦截器
 */
public class EntitySqlInterceptor implements INamedSqlHandler {
	private final static boolean shouldIntercept = SystemInfo.isDebug();

	private final static Logger log = GitUtil.getLogger(EntitySqlInterceptor.class);

	public void beforeQuery2(InterceptorContext context) {
		if (null == context || shouldIntercept == false)
			return;
		long nano = System.nanoTime();
		List<Param> list = context.getParameters();
		if (null == list || list.isEmpty()) {
			log.debug("SQL's ID: " + context.getMappedId());
			return;
		} else {
			List<String> paramList = new ArrayList<String>(list.size());
			for (Param p : list) {
				paramList.add(String.valueOf(p.getValue()));
			}
			log.debug("SQL's ID: " + context.getMappedId() + "\t Params: " + paramList);
		}
		nano = System.nanoTime() - nano; // ns
		if (MDC.get(Log4jInterceptor.SQL_LOG_TIME) == null) {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(nano));
		} else {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(MDC.get(Log4jInterceptor.SQL_LOG_TIME).toString()) + nano);
		}
	}

	public void beforeQuery(InterceptorContext context) {
		if (null == context || shouldIntercept == false) {
			return;
		}
		long nano = System.nanoTime();
		// 记录命名SQL及其参数
		String sql = context.getSql();
		List<Param> list = context.getParameters();
		if (null == list || list.isEmpty()) {
			log.debug("SQL's ID: " + context.getMappedId() + "\nSQL: " + StringUtil.trimAll(sql));
			return;
		} else {
			Object value;
			for (Param p : list) {
				value = p.getValue();
				if (null == value)
					value = "";
				if (value instanceof String) {
					sql = sql.replaceFirst("\\?", "'" + value.toString() + "'");
				} else {
					sql = sql.replaceFirst("\\?", value.toString());
				}
			}
			log.debug("SQL's ID: " + context.getMappedId() + "\nSQL replace: " + StringUtil.trimAll(sql));
		}
		nano = System.nanoTime() - nano; // ns
		if (MDC.get(Log4jInterceptor.SQL_LOG_TIME) == null) {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(nano));
		} else {
			MDC.put(Log4jInterceptor.SQL_LOG_TIME, Long.valueOf(MDC.get(Log4jInterceptor.SQL_LOG_TIME).toString()) + nano);
		}
	}

	public void beforeUpdate(InterceptorContext context) {
		this.beforeQuery(context);
	}

	public void beforeInsert(InterceptorContext context) {
		this.beforeQuery(context);
	}

	public void beforeDelete(InterceptorContext context) {
		this.beforeQuery(context);
	}
}
