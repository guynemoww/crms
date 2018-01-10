package com.bos.pub;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;

public class CrmsSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO 自动生成方法存根
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		InetAddress inet=null;
		  String ipAd=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}     
				ipAd=inet.getHostAddress();
					DataObject ipM = DataObjectUtil
						.createDataObject("com.bos.pub.sys.TbPubIpmanage");
						ipM.setString("ip",ipAd);
							DatabaseUtil.deleteByTemplate(GitUtil.DEFAULT_DS_NAME, ipM);
	}

}
