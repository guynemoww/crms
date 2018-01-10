/**
 * 
 */
package com.bos.pub;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.eos.access.http.IWebInterceptor;
import com.eos.access.http.IWebInterceptorChain;
import com.eos.data.datacontext.DataContextManager;
import com.eos.system.logging.Logger;
import com.primeton.ext.access.http.HttpMapContextFactory;

/**
 * @author 王世春
 * 
 */
public class Log4jInterceptor implements IWebInterceptor {

	private static ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>();

	public static ServletResponse getResponse() {
		return (ServletResponse) threadLocal.get().get("response");
	}

	public static ServletRequest getRequest() {
		return (ServletRequest) threadLocal.get().get("request");
	}

	public static HttpSession getSession() {
		return (HttpSession) threadLocal.get().get("session");
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.eos.access.http.IWebInterceptor#doIntercept(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eos.access.http.IWebInterceptorChain)
	 */
	public void doIntercept(HttpServletRequest request, HttpServletResponse response, IWebInterceptorChain interceptorChain) throws IOException, ServletException {
		long time = System.nanoTime();
		String uri = request.getRequestURI();
		DataContextManager.current().setMapContextFactory(new MyHttpMapContextFactory(request, response));// 包装request和response

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);
		map.put("session", request.getSession());
		map.put("response", response);
		threadLocal.set(map);

		try {
			if (false == (uri.endsWith(".ext") || uri.endsWith(".ext2") || uri.endsWith(".flow")))
				return;
			String qs = request.getQueryString();
			uri = uri + (qs == null ? "" : "?" + qs);
			MDC.put("req.id", UUID.randomUUID().toString().replaceAll("-", ""));
			MDC.put("req.remoteAddr", request.getRemoteAddr());
			MDC.put("req.serverPort", request.getLocalPort());
			MDC.put("req.requestURI", request.getRequestURI());
			MDC.put("req.requestURIWithQueryString", uri);
			com.eos.data.datacontext.UserObject user = (com.eos.data.datacontext.UserObject) request.getSession().getAttribute("userObject");
			if (null == user) {
				MDC.put("req.loginUserId", "none");
			} else {
				user.put("lastActionTime", new Date());
				MDC.put("req.loginUserId", user.getUserId() + user.getUserName() + "/" + user.getUserOrgId() + user.getUserOrgName());
				MDC.put("req.loginUserId2", user.getUserId() + "/" + user.get("orgcode"));
			}
		} finally {
			// 最后将请求传递到其它过滤器中
			try {
				interceptorChain.doIntercept(request, response);
			} catch (Exception e) {
			}
			if (uri.endsWith(".ext") || uri.endsWith(".ext2") || uri.endsWith(".flow")) {
				Logger log = GitUtil.getLogger(Log4jInterceptor.class);
				if (log.isDebugEnabled()) {
					String needPad = String.valueOf((System.nanoTime() - time) / 1000000);
					needPad = StringUtils.leftPad(needPad, 5, ' ');
					log.debug("time used " + needPad + "ms:" + uri);

					// 记录SQL日志时间
					if (MDC.get(SQL_LOG_TIME) != null) {
						Long nano = Long.valueOf(MDC.get(SQL_LOG_TIME).toString());
						nano /= 1000000;
						needPad = StringUtils.leftPad(nano.toString(), 5, ' ');
						log.debug("time used " + needPad + "ms(sql)");
					}
				}
			}
			MDC.remove("req.tradeLogged");
			MDC.remove(EntityLogUtil.TABLE_PRE);
		}

	}

	public final static String SQL_LOG_TIME = "SQL_LOG_TIME";

	class MyHttpMapContextFactory extends HttpMapContextFactory {
		private ServletResponse res;

		public MyHttpMapContextFactory(ServletRequest request, ServletResponse response) {
			super(request, response);
			this.res = response;
		}

		public ServletResponse getResponse() {
			return this.res;
		}
	}
}
