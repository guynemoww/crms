package com.bos.pub;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.MDC;

import com.bos.utp.auth.bizlet.LogonUtil;
import com.eos.data.datacontext.UserObject;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.logging.Logger;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class SessionListener implements HttpSessionListener {

	private final static Logger log = GitUtil.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		// 会话开始
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		if (null != session && null != session.getAttribute(LogonUtil.USER_ID)) {
			try {
				UserObject u = (UserObject) session.getAttribute("userObject");
				String uip = u.getUserRemoteIP();
				Date time = (Date) u.getAttributes().get("lastActionTime");
				log.info(">>会话超时，自动注销IP【" + uip + "/" + u.getUserId() + "】最后交互时间:[" + (time != null ? DateUtil.DateToString(time, DateStyle.YYYY_MM_DD_HH_MM_SS) : "") + "]");
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.utp.auth.LoginManager");
				Object[] params = new Object[1];
				params[0] = uip;
				logicComponent.invoke("deleteIp", params);
				MDC.put(LogonUtil.USER_ID, session.getAttribute(LogonUtil.USER_ID));
				MDC.put(LogonUtil.USER_ORG_ID, session.getAttribute(LogonUtil.USER_ORG_ID));
			} catch (Throwable e) {
				log.error(">>会话超时，自动注销发生异常！");
				e.printStackTrace();
			}
		}
	}
}
