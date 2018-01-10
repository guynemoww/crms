package com.bos.utp.auth.listener;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eos.access.http.IWebInterceptor;
import com.eos.access.http.IWebInterceptorChain;
import com.eos.data.datacontext.DataContextManager;

/**
 * 
 * 在EOS 6.0 build  之前需要 
 * 需要在 eos的config目录下配置handler-web.xml
 * {@code
 * < />
 * 
 * 
 * }
 *
 * @author charles (mailto:caisy@primeton.com)
 */
/*
 * Modify history
 * $Log: WebI18NIntercepor.java,v $
 * Revision 1.2  2010/12/07 03:09:46  caisy
 * 配置读取方式修改
 *
 */
public class WebI18NIntercepor   implements IWebInterceptor {
	
	public void doIntercept(HttpServletRequest request,
			HttpServletResponse response, IWebInterceptorChain interceptorChain)
			throws IOException, ServletException {
		try {
			HttpSession session = request.getSession(false);
			Locale locale = null;
			if (session != null) {
				if (session.getAttribute("LOCALE") instanceof Locale) {
					locale = (Locale) session.getAttribute("LOCALE");
				}
			}
			if (locale == null) {
				locale = request.getLocale();
			}
			DataContextManager.current().setCurrentLocale(locale);
		} finally {
			interceptorChain.doIntercept(request, response);
		}
	}

}
