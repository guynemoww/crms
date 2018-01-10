/**
 * 
 */
package com.bos.utp.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import cn.com.infosec.IDCard.authapi.AuthAPIException;
import cn.com.infosec.IDCard.authapi.AuthServerException;
import cn.com.infosec.IDCard.authapi.CardAuthManager;
import cn.com.infosec.IDCard.authapi.SysProperty;

import com.bos.utp.tools.DBUtil;
import com.eos.system.annotation.Bizlet;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * @author Administrator
 * @date 2017-09-02 14:39:58
 * 
 */
@Bizlet("")
public class LoginMapSwitch {
	@Bizlet("")
	public String loginSwith(String operatorid, String operDt) {
		String dynamicswitch = null;
		String useridmap = null;
		String msg = "失败";
		Statement s = null;
		ResultSet rso = null;
		ResultSet rso2 = null;
		Connection conn = DBUtil.getConnection();
		String userOpersql = "select DYNAMICSWITCH,USERIDMAP from AC_OPERATOR where OPERATORID='" + operatorid + "'";
		try {
			s = conn.createStatement();
			rso = s.executeQuery(userOpersql);
			while (rso.next()) {
				dynamicswitch = rso.getString("DYNAMICSWITCH");
				useridmap = rso.getString("USERIDMAP");
			}
			rso.close();
			// 是否连接动态密码验证服务器，1为验证，0为不验证
			if ("1".equals(dynamicswitch) || dynamicswitch == "1" || "1".equalsIgnoreCase(dynamicswitch)) {
				// 获取动态密码验证服务器IP、端口、最大连接数
				String dynamicServerIp = null;
				int dynamicServerPort = 4451;
				int maxconn = 1000;
				String userMaps = "select CFG_VALUE,CFG_KEY from tb_pub_config where CFG_GROUP='ftp_usermap'";
				rso2 = 	s.executeQuery(userMaps);
				while (rso2.next()) {
					String cfg_key = rso2.getString("CFG_KEY");
					if("host".equals(cfg_key)){
						dynamicServerIp = rso2.getString("CFG_VALUE");
					}else if("port".equals(cfg_key)){
						dynamicServerPort = Integer.parseInt(rso2.getString("CFG_VALUE"));				
					}else if("maxconn".equals(cfg_key)){
						maxconn = Integer.parseInt(rso2.getString("CFG_VALUE"));
					}
				}
				rso2.close();
				if(dynamicServerIp == null || dynamicServerPort == 0){
					msg = "动态口令ip地址获取失败！";
				}else{
					// 系统参数保存在SysProperty类型对象中
					SysProperty sp = new SysProperty(dynamicServerIp, dynamicServerPort);
					sp.setMaxConn(maxconn);
					boolean result = false;
					try {
						CardAuthManager.setAPIProperty(sp);
						result = CardAuthManager.authCard(useridmap, 9000, operDt);
						System.out.println("验证结果为：" + result);
						if (result) {
							msg = "成功";
						} else {
							msg = "动态密码错误,请重新录入";
						}
					} catch (AuthAPIException e) {
						msg = e.getMessage() + "（动态口令服务器）";
					} catch (AuthServerException e) {
						msg = e.getMessage() + "，登录失败";
					}
				}
			} else {
				msg = "成功";
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, new Statement[] {s}, new ResultSet[] {rso,rso2});
		}

		return msg;
	}
}
