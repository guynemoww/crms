package com.bos.pub;

import com.bos.pub.socket.LinkSocketService;
import com.eos.runtime.core.IRuntimeListener;
import com.eos.runtime.core.RuntimeEvent;
import com.eos.spring.TraceLogger;

public class SocketServerListener implements IRuntimeListener {
	private TraceLogger log = new TraceLogger(SocketServerListener.class);
	
	public void start(RuntimeEvent paramRuntimeEvent) {
		log.info("SocketServerListener--管理系统启动，开始启动socket server 监听。。。 begin");
		LinkSocketService.getInstance().createLinkSocketStartup();
		log.info("SocketServerListener--管理系统启动，开始启动socket server 监听。。。 end");
	}

	public void stop(RuntimeEvent paramRuntimeEvent) {
		log.info("SocketServerListener--管理系统，开始关闭socket server 监听。。。 begin");
		LinkSocketService.getInstance().destroySocketService();
		log.info("SocketServerListener--管理系统，开始关闭socket server 监听。。。 end");
	}
}
