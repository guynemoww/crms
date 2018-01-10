package com.bos.pub.socket.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

import com.eos.system.annotation.Bizlet;

@Bizlet("接口公共方法2")
public class LinkSocketStartupUtil {

	/**
	 * 获取报文信息的公共方法
	 * @param reader
	 * @param in
	 * @param messageLen
	 * @return rsMessage
	 */
	@Bizlet("从流中获取报文信息")
	public static String getMessage(Reader reader, InputStream in, char[] messageLen) {
		String rsMessage = "";
		try {
			char[] messageC = new char[512];
			if (null != reader) {
				int len = 0;//每次实际读取的长度
				//循环读取报文信息，当读取内容为空即读完时会返回-1
				while ((len = reader.read(messageC)) != -1) {
					rsMessage += new String(messageC, 0, len);
				}

			} else if (null != in) {
				byte[] messageB = new byte[512];
				int len = 0;
				while ((len = in.read(messageB)) != -1) {
					messageC = new String(messageB, EsbSocketConstant.CHARCODE_UTF8).toCharArray();
					rsMessage += new String(messageC, 0, len);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return rsMessage;
	}
}
