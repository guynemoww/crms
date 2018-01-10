package com.bos.pub;

import com.bos.mq.server.MQPoolHelpUtilImpl;
import com.eos.runtime.core.IRuntimeListener;
import com.eos.runtime.core.RuntimeEvent;
import com.git.easyetl.threadpool.common.PoolHelpUtil;
import com.git.easyetl.threadpool.thread.ThreadPool;

public class ThreadPoolListener implements IRuntimeListener {
	public ThreadPool threadPool;
	public void start(RuntimeEvent arg0) {
		// TODO 自动生成方法存根
		System.out.println("*****开始初始化线程池*******");
		PoolHelpUtil poolHelpUtil = new MQPoolHelpUtilImpl();
		threadPool = new ThreadPool();
		threadPool.setPoolHelpUtil(poolHelpUtil);
		threadPool.getInstance();
		System.out.println("*****线程池初始化完成*******");
	}

	public void stop(RuntimeEvent arg0) {
		// TODO 自动生成方法存根
	}
}
