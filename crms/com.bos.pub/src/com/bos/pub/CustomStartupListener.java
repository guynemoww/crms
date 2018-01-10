package com.bos.pub;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.git.easyetl.threadpool.thread.ThreadPool;

public class CustomStartupListener implements ServletContextListener {
	
	public ThreadPool threadPool;
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent config) {
		System.out.println("\n\n\n\n=============contextInitialized");
	}
}
