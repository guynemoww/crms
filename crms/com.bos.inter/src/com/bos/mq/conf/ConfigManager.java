
package com.bos.mq.conf;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties config;

	public static void loadProperties() {
		try {
			config = new Properties();
			InputStream in = ConfigManager.class.getResourceAsStream("config.properties"); // abc.properties放在webroot/WEB-INF/classes/目录下
			config.load(in);
			System.out.println("找到配置文件:config.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 取属性值返回String
	public static String getStringProperty(String key) {
		if (config == null){
			ConfigManager cm = new ConfigManager();
			cm.loadProperties();
		}
		return config.getProperty(key).trim();
	}
	// 取属性值返回int
	public static int getIntProperty(String key) {
		if (config == null){
			ConfigManager cm = new ConfigManager();
			cm.loadProperties();
		}
		return Integer.parseInt(config.getProperty(key).toString());
	}

	public static void main(String args[]) {
		System.out.println("Test:" + ConfigManager.getStringProperty("maxPools"));
	}
}
