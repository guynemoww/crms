package com.bos.utp.tools;

import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import commonj.sdo.DataObject;

/**
 * 系统常量信息
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
public class SystemInfo {

	/**
	 * 换行符
	 */
	public static String LINE_SEPARATOR = System.getProperty("line.separator",
			"\r\n");

	/**
	 * 文件分隔符，如linux为斜杠/，windows为反斜杠\
	 */
	public static String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * 路径分隔符，如linux为冒号，windows为分号
	 */
	public static String PATH_SEPARATOR = System.getProperty("path.separator");

	/**
	 * war解压后（部署后）的路径，最后带斜杠
	 */
	public static String APP_WAR_PATH = ApplicationContext.getInstance()
			.getWarRealPath();

	private static boolean debugFlag = true;

	public static boolean isDebug() {
		return debugFlag;
	}

	static {
		DataObject config = ConfigurationUtil.getUserConfigMultiValue(
				"CustomConfig", "System", "debug");
		debugFlag = "true".equals(config.getString("debug"));
		if (!APP_WAR_PATH.endsWith("/") && !APP_WAR_PATH.endsWith("\\")) {
			APP_WAR_PATH += "/";
		}
	}
}
