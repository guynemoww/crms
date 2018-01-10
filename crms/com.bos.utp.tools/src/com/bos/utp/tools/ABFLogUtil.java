package com.bos.utp.tools;

import org.apache.log4j.Level;

import com.eos.system.logging.Logger;
import com.eos.system.utility.StringUtil;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * 日志处理类，提供日志记录的工具方法，配置日志记录
 * 
 * @author 蔡述尧
 * @date 2009-04-09 13:47:33
 */
/*
 * 修改历史 $Log: ABFLogUtil.java,v $
 * 修改历史 Revision 1.1  2010/12/07 03:10:10  caisy
 * 修改历史 清除system.out
 * 修改历史
 * 修改历史 Revision 1.6  2010/12/01 03:22:41  caisy
 * 修改历史 更改编码为UTF-8
 * 修改历史
 * 修改历史 Revision 1.5  2010/11/30 16:12:51  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.4  2009/05/27 01:35:58  caisy
 * 修改历史 Update:增加多个日志仓库的日志工厂,在Tools启动时初始化日志工厂
 * 修改历史
 * 修改历史 Revision 1.3  2009/05/24 11:11:39  caisy
 * 修改历史 Update:增加多个日志仓库的日志工厂
 * 修改历史 Revision 1.2 2009/05/04 08:31:47 caisy
 * Update:注释掉测试代码
 * 
 * Revision 1.1 2009/04/22 10:11:33 caisy EOS元数据功能
 * 
 */
@Bizlet("日志工具类")
public class ABFLogUtil {

	public final static String LOGMSG = "com.bos.utp.tools.dataset.toolsview.LogMsg";
	public final static String LOGMSG_MSG = "message";
	public final static String LOGMSG_NAME = "repositoryName";
	public final static String LOGMSG_TAG = "tag";
	
	private final static String DEFAULT_TAG = "ABF_DEFAULT_TAG";
     
	public final static String MULTILOGMSG = "com.bos.utp.tools.dataset.toolsview.LogMsg";
	public final static String MULTILOGMSG_NAME = "repositoryName";
	public final static String MULTILOGMSG_DEBUGMSG = "debugMessage";	
	public final static String MULTILOGMSG_DEBUGTAG = "debugTag";
	public final static String MULTILOGMSG_INFOMSG = "infoMessage";	
	public final static String MULTILOGMSG_INFOTAG = "infoTag";
	public final static String MULTILOGMSG_WARNMSG = "warnMessage";	
	public final static String MULTILOGMSG_WARNTAG = "warnTag";
	public final static String MULTILOGMSG_ERRORMSG = "errorMessage";	
	public final static String MULTILOGMSG_ERRORTAG = "errorTag";
	public final static String MULTILOGMSG_FATALMSG = "fatalMessage";	
	public final static String MULTILOGMSG_FATALTAG = "fatalTag";
	
	/**
	 * @author 蔡述尧
	 * 
	 */
	@Bizlet("debug")
	public static void debug(String repositoryName, String tag, String msg) {
		Logger logger = getLogger(repositoryName,tag);
		if(logger!=null&&StringUtil.isNotNullAndBlank(msg)){
			logger.debug(msg);
		}
	}

	@Bizlet("info")
	public static void info(String repositoryName, String tag, String msg) {
		Logger logger = getLogger(repositoryName,tag);
		if(logger!=null&&StringUtil.isNotNullAndBlank(msg)){
			logger.info(msg);
		}
	}

	@Bizlet("warning")
	public static void warn(String repositoryName, String tag, String msg) {
		Logger logger = getLogger(repositoryName,tag);
		if(logger!=null&&StringUtil.isNotNullAndBlank(msg)){
			logger.warn(msg);
		}
	}

	@Bizlet("error")
	public static void error(String repositoryName, String tag, String msg) {
		Logger logger = getLogger(repositoryName,tag);
		if(logger!=null&&StringUtil.isNotNullAndBlank(msg)){
			logger.error(msg);
		}
	}

	@Bizlet("fatal")
	public static void fatal(String repositoryName, String tag, String msg) {
		Logger logger = getLogger(repositoryName,tag);
		if(logger!=null&&StringUtil.isNotNullAndBlank(msg)){
			logger.fatal(msg);
		}
	}
	/**
	 * 如果根据日志记录级别记录某一级的日志信息如果该级别没有信息则输出级别低于它才最近一条信息
	 * 例如日志级别为 warn    如果warnMessage 为空则 输出 error级别的信息
	 * @param repositoryName
	 * @param debugTag
	 * @param debugMessage
	 * @param infoTag
	 * @param infoMessage
	 * @param warnTag
	 * @param warnMessage
	 * @param errorTag
	 * @param errorMessage
	 * @param fatalTag
	 * @param fatalMessage
	 */
	@Bizlet("log")
	public static void log(String repositoryName,
			String debugTag,String debugMessage,
			String infoTag,String infoMessage,
			String warnTag,String warnMessage,
			String errorTag,String errorMessage,
			String fatalTag,String fatalMessage
			) {		    
		    Logger logger = getLogger(repositoryName,DEFAULT_TAG);
		    Level level =  logger.getEffectiveLevel();
		    if(StringUtil.isNullOrBlank(errorMessage)){
		    	errorMessage = fatalMessage;
		    	errorTag = fatalTag;
		    }else  if(StringUtil.isNullOrBlank(warnMessage)){
		    	warnMessage = errorMessage;
		    	warnTag = errorTag;
		    }else  if(StringUtil.isNullOrBlank(infoMessage)){
		    	infoMessage = warnMessage;
		    	infoTag = warnTag;
		    }else  if(StringUtil.isNullOrBlank(debugMessage)){
		    	debugMessage = infoMessage;
		    	debugTag = infoTag;
		    }
		    switch(level.toInt()){
		       case Level.DEBUG_INT:
		    	  debug(repositoryName,debugTag,debugMessage);
		    	break;
		       case Level.INFO_INT:
			    	  info(repositoryName,infoTag,infoMessage);
			    	break;
		       case Level.WARN_INT:
			    	  warn(repositoryName,warnTag,warnMessage);
			    	break;
		       case Level.ERROR_INT:
			    	  error(repositoryName,errorTag,errorMessage);
			    	break;
		       case Level.FATAL_INT:
			    	  fatal(repositoryName,fatalTag,fatalMessage);
			    	break;
		    }
	}
	// 使用dataobject作为参数类型为 com.bos.utp.dataset.tools.LogMsg
	@Bizlet("debug sdo")
	public static void debug(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, LOGMSG)) {
			debug(logmsg.getString(LOGMSG_NAME),logmsg.getString(LOGMSG_TAG),logmsg.getString(LOGMSG_MSG));			
		}
	}

	@Bizlet("info sdo")
	public static void info(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, LOGMSG)) {
			info(logmsg.getString(LOGMSG_NAME),logmsg.getString(LOGMSG_TAG),logmsg.getString(LOGMSG_MSG));
		}
	}

	@Bizlet("warning sdo")
	public static void warn(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, LOGMSG)) {
			warn(logmsg.getString(LOGMSG_NAME),logmsg.getString(LOGMSG_TAG),logmsg.getString(LOGMSG_MSG));
		}
	}

	@Bizlet("error sdo")
	public static void error(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, LOGMSG)) {
			error(logmsg.getString(LOGMSG_NAME),logmsg.getString(LOGMSG_TAG),logmsg.getString(LOGMSG_MSG));
		}
	}

	@Bizlet("fatal sdo")
	public static void fatal(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, LOGMSG)) {
			fatal(logmsg.getString(LOGMSG_NAME),logmsg.getString(LOGMSG_TAG),logmsg.getString(LOGMSG_MSG));
		}
	}

	@Bizlet("log sdo")
	public static void log(DataObject logmsg) {
		if (DataObjectUtil.checkEntityName(logmsg, MULTILOGMSG)) {
			log(logmsg.getString(MULTILOGMSG_NAME),
				logmsg.getString(MULTILOGMSG_DEBUGTAG),logmsg.getString(MULTILOGMSG_DEBUGMSG),
				logmsg.getString(MULTILOGMSG_INFOTAG),logmsg.getString(MULTILOGMSG_INFOMSG),
				logmsg.getString(MULTILOGMSG_WARNTAG),logmsg.getString(MULTILOGMSG_WARNMSG),
				logmsg.getString(MULTILOGMSG_ERRORTAG),logmsg.getString(MULTILOGMSG_ERRORMSG),
				logmsg.getString(MULTILOGMSG_FATALTAG),logmsg.getString(MULTILOGMSG_FATALMSG)
			);
		}
	}
	@Bizlet("logFactory register")
	public static void register(){
		ABFLogFactory.getInstance().register();
	}
	@Bizlet("logFactory register")
	public static void unRegister(){
		ABFLogFactory.getInstance().unRegister();
	}
	private static Logger getLogger(String repositoryName,String tag){
		return ABFLogFactory.getInstance().getLogger(repositoryName, tag);
	}
}
