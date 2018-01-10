package com.bos.pub.socket.thread;

import com.bos.pub.socket.service.LinkSocketMessageHandleService;
import com.eos.spring.TraceLogger;

public class LinkSocketMessageAcceptThread {
	private TraceLogger log = new TraceLogger(LinkSocketMessageAcceptThread.class);

	public void start() {
		log.info("LinkSocketMessageAcceptThread--管理系统启动，开始启动socket server 监听。。。 begin");
		LinkSocketMessageHandleService.getInstance().createLinkSocketStartup();
		log.info("LinkSocketMessageAcceptThread--管理系统启动，开始启动socket server 监听。。。 end");
	}
	
	public void stop() {
		log.info("LinkSocketMessageAcceptThread--管理系统，开始关闭socket server 监听。。。 begin");
		LinkSocketMessageHandleService.getInstance().destroySocketService();
		log.info("LinkSocketMessageAcceptThread--管理系统，开始关闭socket server 监听。。。 end");
	}
}
