/**
 * 创建日期：2014-05-12
 * 创建人：Sunny
 * 功能：初始化线程池Servlet
 **/
package com.bos.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bos.mq.client.Receiver;
import com.bos.mq.server.MQPoolHelpUtilImpl;
import com.git.easyetl.threadpool.common.PoolHelpUtil;
import com.git.easyetl.threadpool.thread.ThreadPool;

/**
 * @author Sunny
 * 
 */
public class InitPoolServlet extends HttpServlet {

	public ThreadPool threadPool;

	public InitPoolServlet() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		PoolHelpUtil poolHelpUtil = new MQPoolHelpUtilImpl();
		threadPool = new ThreadPool();
		threadPool.setPoolHelpUtil(poolHelpUtil);
		threadPool.getInstance();
//		Receiver rec = new Receiver();
//		rec.test();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7145213864259254505L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(arg0, arg1);
	}
}
