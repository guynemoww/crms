package com.bos.inter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.eos.foundation.eoscommon.ConfigurationUtil;

public class IntimeNoteClient {

	public static void intimeNoteSocketClint(String sendInfo) throws IOException{//及时短信
		String module = "IntimeNoteConfig";
		String group = "note_webservice_server";
		String ip = "ip";
		String port = "port";

		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
//		String zip = "172.16.205.9";
//		String zport = "9998";
		int sport = 9998;
		if(zport != null && !"".equals(zport))sport = Integer.parseInt(zport);
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		int sendheadLength = 8;
		int retheadLength = 8;
		try {
			socket = new Socket(zip, sport);//业务系统
			in = socket.getInputStream();
			out = socket.getOutputStream();
			StringBuffer reqXml = new StringBuffer();
			 reqXml.append(sendInfo);
			 System.out.println("info:[" + reqXml + "]");

				byte[] reqXmlBytes = reqXml.toString().getBytes("GBK");
				System.out.println("报文长度:[" + reqXmlBytes.length + "]");
				byte[] headBytes = fillStrLeft(reqXmlBytes.length + "", "0",
						sendheadLength).getBytes();// 长度10位字节 发送内容长度（只是内容）

				byte[] reqTotalBytes = new byte[headBytes.length
						+ reqXmlBytes.length];
				for (int i = 0; i < headBytes.length; i++) {
					reqTotalBytes[i] = headBytes[i];
				}
				for (int i = headBytes.length; i < reqTotalBytes.length; i++) {
					reqTotalBytes[i] = reqXmlBytes[i - headBytes.length];
				}

				System.out.println("packege perform!!!");
				System.out.println(new String(reqTotalBytes, "GBK"));
				out.write(reqTotalBytes);
				out.flush();
				System.out.println("send perform!!!");

				byte[] resHeadBytes = new byte[retheadLength + 5];
				in.read(resHeadBytes);

				String resXml = new String(resHeadBytes, "GBK");

				System.out.println("return：" + resXml);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (socket != null)
				socket.close();
		}
	}
	public static void main(String[] args) throws IOException {
		String sendInfo = "0701|*|crms_note_001|*|9999|*|18202810565|*|短信内容";
		intimeNoteSocketClint(sendInfo);
	}
	
	
	/**
	 * 在字符串左补某字符串，并控制总长度，长度超过则截取
	 * 
	 * @param srcStr
	 *            原字符串
	 * @param fillStr
	 *            填充字符串
	 * @param maxLength
	 *            最大长度
	 * @return 结果字符串
	 */
	public static String fillStrLeft(String srcStr, String fillStr,
			int maxLength) {
		String result = "";
		if (srcStr == null)
			return srcStr;
		for (int i = 0; i < maxLength; i++) {
			srcStr = fillStr + srcStr;
		}
		result = srcStr.substring(srcStr.length() - maxLength);
		return result;
	}

}
