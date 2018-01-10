package com.bos.pub.socket;

import com.bos.pub.socket.service.request.base.EsbServiceRq;
import com.bos.pub.socket.util.BeanToMapUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.bos.pub.socket.util.EsbSocketMessage;
import com.bos.pub.socket.util.EsbSocketUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.spring.TraceLogger;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Socket Listener Thread
 *
 * @author rendongxie
 * @time 2007-12-20
 */
public class LinkSocketListener implements Runnable {
	private TraceLogger log = new TraceLogger(LinkSocketListener.class);
    private ServerSocket serverSocket;
    private Socket socket = null;
	// 报文长度位数(8)：是整个报文体的长度，包括交易代码。
	private static final int MESSAGE_LENGTH_DIGIT = 8;
	// 报文最小长度、最大长度
	private static final int BUSINESS_CODE_DIGIT = 8;

	public LinkSocketListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() {
    	log.info("LinkSocketListener--run:");
        try {
            int i = 1;
            while (true) {
            	log.info("LinkSocketListener--i:begin"+i);
                socket = serverSocket.accept();
    			socket.setSoTimeout(1*5000);
    			socket.setKeepAlive(true);
    			

    			InputStream in = null;
    			OutputStream out = null;
    			Reader reader = null;
    			try {
    				in = socket.getInputStream();
    				reader = new InputStreamReader(in, EsbSocketConstant.CHARCODE_UTF8);
    				out = socket.getOutputStream();
    				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out,
    						EsbSocketConstant.CHARCODE_UTF8);
    				PrintWriter pw = new PrintWriter(outputStreamWriter, true);

    				char[] messageLen = new char[MESSAGE_LENGTH_DIGIT];
    				int messageLen_ = 0;
    				if (null != reader) {
    					messageLen_ = reader.read(messageLen, 0, MESSAGE_LENGTH_DIGIT);
    				} else if (null != in) {
    					byte[] byteMessageLen = new byte[MESSAGE_LENGTH_DIGIT];
    					messageLen_ = in.read(byteMessageLen, 0, MESSAGE_LENGTH_DIGIT);
    					messageLen = new String(byteMessageLen,
    							EsbSocketConstant.CHARCODE_UTF8).toCharArray();
    				}

    				// 处理F5定时扫描,当F5发送来的检测消息,判断是否为系统交易报文,如报文为""不予继续执行
    				if (messageLen_ >= BUSINESS_CODE_DIGIT) {
    					if (!"".equals(new String(messageLen).trim())) {
    						String strlen = new String(messageLen);
    						int messageLength = Integer.parseInt(strlen);
    						char[] message = new char[messageLength];
    						int message_ = 0;
    						if (null != reader) {
    							message_ = reader.read(message, 0, messageLength);
    						} else if (null != in) {
    							byte[] byteMessage = new byte[messageLength];
    							message_ = in.read(byteMessage, 0, messageLength);
    							message = new String(byteMessage,
    									EsbSocketConstant.CHARCODE_UTF8).toCharArray();
    						}

    						String receiveEsbServiceRq = new String(message);
    						// 获得交易代码
    						EsbServiceRq esbServiceRq = EsbSocketUtil
    								.getEsbServiceRq(receiveEsbServiceRq);
    						Object esbBodyRq = esbServiceRq.getEsbBody();
    						String serviceCodeScene = EsbSocketUtil
    								.getServiceCodeScene(esbServiceRq);
    						log.info("LinkSocketListener--服务端收到的请求交易代码：{0}",
    								new Object[] { serviceCodeScene });
    						log.info("LinkSocketListener--服务端收到请求报文：" + strlen + receiveEsbServiceRq);
    						System.out.println("服务端收到请求报文：" + strlen
    								+ receiveEsbServiceRq);
    						log.info("LinkSocketListener--服务端收到请求报文对象串：" + esbServiceRq.toString());
    						//保存记录报文
    						new EsbSocketService().saveMessageRecord(esbServiceRq.toString(), serviceCodeScene, "request");

    						
    						// 把Bean转换成Map DataObject
    						DataObject dataObject = BeanToMapUtil
    								.convertBean(esbBodyRq);
    						dataObject.setString(EsbSocketConstant.SERVICE_CODE_SCENE,
    								serviceCodeScene);
    						// 调用业务逻辑，返回成功失败数据信息：ReturnCode（服务返回代码） ReturnMsg（服务返回信息）
    						// 报文体数据合法性验证
    						HashMap<String, String> checkData = EsbSocketUtil
    								.checkData(serviceCodeScene, esbServiceRq);
    						String returnCode = checkData
    								.get(EsbSocketConstant.RETURN_CODE);
    						String returnMsg = checkData
    								.get(EsbSocketConstant.RETURN_MSG);

    						// 调用业务成功或失败，生成EsbServiceRs返回；失败无报文体。
    						try {
    							// 国结接口逻辑构件名称
    							String componentName = "com.bos.inter.GjInter";
    							if (EsbSocketConstant.SOCKET_SUCCESS.equals(returnCode)) {
    								Object[] result = null;
//    								GjToLoan gjToLoan = new GjToLoan();
    								if (EsbSocketConstant.XdRq02001000003BODY02
    										.equals(serviceCodeScene)) {
    									// 逻辑流名称——放款通知
    									String operationName = "fktz";
    									ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
    									// 逻辑流的输入参数
    									Object[] params = new Object[1];
    									params[0] = dataObject;
    									result = logicComponent.invoke(operationName, params);
    									
    								} else if (EsbSocketConstant.XdRq02001000002BODY01
    										.equals(serviceCodeScene)) {
    									// 逻辑流名称——还款
    									String operationName = "hk";
    									ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
    									// 逻辑流的输入参数
    									Object[] params = new Object[1];
    									params[0] = dataObject;
    									result = logicComponent.invoke(operationName, params);
//    									DataObject fktz = gjToLoan.hk(dataObject);
    								} else if (EsbSocketConstant.XdRq05002000001BODY01
    										.equals(serviceCodeScene)) {
    									// 逻辑流名称——查询
    									String operationName = "cx";
    									ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
    									// 逻辑流的输入参数
    									Object[] params = new Object[1];
    									params[0] = dataObject;
    									result = logicComponent.invoke(operationName, params);
    								} else if (EsbSocketConstant.WmaRq03002000011BODY02
    										.equals(serviceCodeScene)) {
    									// 逻辑流名称——查询
    									String operationName = "yccx";
    									ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
    									// 逻辑流的输入参数
    									Object[] params = new Object[1];
    									params[0] = dataObject;
    									result = logicComponent.invoke(operationName, params);
    								}
    								
    								
    								//获取国结业务返回的code和msg
    								if(result!=null && result.length>0)
    								{
	    								Object ret = result[0];
	    								DataObject retDo = (DataObject)ret;
	    								returnCode = retDo.getString(EsbSocketConstant.RETURN_CODE);
	    								returnMsg = retDo.getString(EsbSocketConstant.RETURN_MSG);
    								}
    							}
    						} catch (Exception e) {
    							returnCode = "99999999999999";
    							returnMsg = "信贷系统底层发生异常！";
    							log.info("LinkSocketListener--"+e.getMessage());
    							e.printStackTrace();
    						} catch (Throwable e) {
    							returnCode = "99999999999999";
    							returnMsg = "信贷系统底层发生异常！";
								log.info("LinkSocketStartup--" + e.getMessage());
    							e.printStackTrace();
    						}

    						dataObject.setString(EsbSocketConstant.RETURN_CODE,
    								returnCode);
    						dataObject.setString(EsbSocketConstant.RETURN_MSG,
    								returnMsg);

    						EsbSocketMessage dataObject2EsbServiceRs = EsbSocketUtil
    								.dataObject2EsbServiceRs(serviceCodeScene,
    										esbServiceRq, dataObject);
    						String strEsbServiceRs = dataObject2EsbServiceRs
    								.getStrEsbServiceRs();

    						pw.println(strEsbServiceRs);
    						log.info("LinkSocketListener--服务端发送响应报文：" + strEsbServiceRs);
    						//保存记录报文
    						new EsbSocketService().saveMessageRecord(strEsbServiceRs, serviceCodeScene, "response");
    					}
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    				log.error("LinkSocketListener--socket accept handle Exception===" + e, e);
    			} catch (Throwable e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} finally {
    				try {
    					if (out != null) {
    						out.close();
    					}
    					if (in != null) {
    						in.close();
    					}
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		
    			socket.close();

    			i++;
                log.info("LinkSocketListener--i:end"+(i-1));
            }
        } catch (IOException e) {
        	log.info("LinkSocketListener--IOException:"+e);
            e.printStackTrace();
        }catch (Exception e) {
			e.printStackTrace();
			log.info("LinkSocketListener--error:"+e);
		} finally {
			try {
				log.info("LinkSocketListener--finally--");
				boolean closed = serverSocket.isClosed();
				if (!closed) {
					log.info("LinkSocketListener--finally--close:begin");
					serverSocket.close();
					log.info("LinkSocketListener--finally--close:end");
				}
			} catch (IOException e1) {
				log.info("LinkSocketListener--finally--error:"+e1);
				e1.printStackTrace();
			}
		}
    }
}
