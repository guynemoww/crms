/**
 * 
 */
package com.bos.mq.server;

import com.bos.jaxb.javabean.CommonRsHdr;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Sunny
 * @date 2014-05-17 13:24:15
 *
 */
@Bizlet("消息解析器")
public class MessageParser {
	/**
	 * @param rsHdr 响应报文头
	 * @return
	 * @author Sunny
	 */
	@Bizlet("解析MQ消息报文头")
	public static DataObject mqParse(CommonRsHdr rsHdr) {
		// TODO 自动生成方法存根
		
		return null;
	}
}
