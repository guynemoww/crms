/**
 * 
 */
package com.bos.mq.client;

import com.eos.system.annotation.Bizlet;

/**
 * @author Sunny
 * @date 2014-04-24 17:32:03
 *
 */
@Bizlet("MQ发送报文与接收报文")
public class MQclient {
	/**
	 * @param obj 报文实体对象
	 * @return
	 * @author Sunny
	 */
	@Bizlet("发送消息")
	public static MQbean sendmsg(Object obj) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * @param mqbean MQ参数传递对象
	 * @return
	 * @author Sunny
	 */
	@Bizlet("接收消息")
	public static Object resvicemsg(MQbean mqbean) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * @return
	 * @author Sunny
	 */
	@Bizlet("发送并接收消息")
	public static Object acceptmsg() {
		// TODO 自动生成方法存根
		return null;
	}
}
