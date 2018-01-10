package com.bos.pub.socket.service;

import com.bos.pub.socket.LinkSocketListener;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.spring.TraceLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Socket Service:Start Up Listener Port
 * 
 * @author rendongxie
 * @time 2007-12-20
 */
public class LinkSocketMessageHandleService {

	private TraceLogger log = new TraceLogger(LinkSocketMessageHandleService.class);

	private ServerSocket serverSocket;

	private Thread thread;
	
	private static LinkSocketMessageHandleService instance = null;

	private LinkSocketMessageHandleService() {
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new LinkSocketMessageHandleService();
		}
	}

	public static LinkSocketMessageHandleService getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public void createLinkSocketStartup() {
		try {
			String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
			String group = EsbSocketConstant.CONTRIBUTION_CRMS_GROUP;
			String port = EsbSocketConstant.CONTRIBUTION_CRMS_PORT;

			String serverPort = ConfigurationUtil.getUserConfigSingleValue(
					module, group, port);
			log.info(
					"LinkSocketMessageHandleService--加载管理socket服务端端口配置：module={0},group={1},port={2}:{3}",
					new Object[] { module, group, port, serverPort });

			int SERVER_PORT = Integer.parseInt(serverPort);
			serverSocket = new ServerSocket();
			serverSocket.setReceiveBufferSize(8192 * 2);
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(SERVER_PORT));

			thread = new Thread(new LinkSocketListener(serverSocket));
			thread.start();
			log.info("LinkSocketMessageHandleService--启动createLinkSocketStartup()-begin："
					+ serverPort);
		} catch (Exception e) {
			log.info(e);
			log.info("LinkSocketMessageHandleService--端口异常 serverPort：");
			destroySocketService();
			// createLinkSocketStartup();
//			log.info("LinkSocketMessageHandleService--重新启动 serverPort：");
		}
	}

	public void destroySocketService() {
		log.info("LinkSocketMessageHandleService--关闭destroySocketService()	停止socket端口:begin");
		try {
			if (!serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			log.info("LinkSocketMessageHandleService--关闭destroySocketService()	停止socket端口:serverSocket.close()");
			e.printStackTrace();
		}
		thread = null;
		serverSocket = null;
		log.info("LinkSocketMessageHandleService--关闭destroySocketService()	停止socket端口:end");
	}
}
