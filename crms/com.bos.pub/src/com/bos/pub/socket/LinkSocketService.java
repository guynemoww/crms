package com.bos.pub.socket;

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
public class LinkSocketService {

	private TraceLogger log = new TraceLogger(LinkSocketService.class);

	private ServerSocket serverSocket;

	private Thread thread;
	
	private static LinkSocketService instance = null;

	private LinkSocketService() {
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new LinkSocketService();
		}
	}

	public static LinkSocketService getInstance() {
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
					"LinkSocketService--加载管理socket服务端端口配置：module={0},group={1},port={2}:{3}",
					new Object[] { module, group, port, serverPort });

			int SERVER_PORT = Integer.parseInt(serverPort);
			serverSocket = new ServerSocket();
			serverSocket.setReceiveBufferSize(8192 * 2);
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(SERVER_PORT));

			thread = new Thread(new LinkSocketStartup(serverSocket));
			thread.start();
			log.info("LinkSocketService--启动createLinkSocketStartup()-begin："
					+ serverPort);
		} catch (Exception e) {
			log.info(e);
			log.info("LinkSocketService--端口异常 serverPort：");
			destroySocketService();
			// createLinkSocketStartup();
			log.info("LinkSocketService--重新启动 serverPort：");
		}
	}

	public void destroySocketService() {
		log.info("LinkSocketService--关闭destroySocketService()	停止socket端口:begin");
		try {
			if (!serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			log.info("LinkSocketService--关闭destroySocketService()	停止socket端口:serverSocket.close()");
			e.printStackTrace();
		}
		thread = null;
		serverSocket = null;
		log.info("LinkSocketService--关闭destroySocketService()	停止socket端口:end");
	}
}
