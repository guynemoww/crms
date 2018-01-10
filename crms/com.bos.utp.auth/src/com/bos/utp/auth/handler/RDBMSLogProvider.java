package com.bos.utp.auth.handler;


import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.common.utils.DateUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.ext.engine.core.IRuntimeContext;
import commonj.sdo.DataObject;

/**
 * 
 * 数据库日志存储提供者
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: RDBMSLogProvider.java,v $
 * Revision 1.6  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.5  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.3  2009/01/08 02:27:21  liuxiang
 * *** empty log message ***
 *
 * Revision 1.2  2009/01/06 06:31:22  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 * 二期初始版本
 *
 * Revision 1.4  2008/11/25 08:00:59  wengzr
 * Update:修改日志配置和日志管理表
 *
 * Revision 1.3  2008/11/25 06:23:10  wengzr
 * Update:修改业务日志实现方式
 * Update:修改业务日志表和日志配置表,增加日志类型字段
 *
 * Revision 1.2  2008/11/24 07:28:15  wengzr
 * Update:重构PermissionUtil->CacheUtil
 * Update:修改业务日志
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/10/05 14:11:25  wengzr
 * Added:增加业务日志功能
 *
 */
public class RDBMSLogProvider implements LogStorageProvider {

	public void log(IUserObject user, LogConfig logConfig, IRuntimeContext runtimeContext, LogicSignature logicSignature, Throwable t) {

		StringBuffer operationDescription=new StringBuffer();
		operationDescription.append("调用逻辑:").append(logicSignature.getLogicLabel()).append("\n");
		operationDescription.append("输入内容:").append("\n");
		/**for (String key:logicSignature.getParameterNames()) {
			operationDescription.append(key).append("=").append(runtimeContext.get(key)).append(" ");
		}
		operationDescription.append("\n输出内容:").append("\n");
		for(String key:logicSignature.getReturnNames()){
			operationDescription.append(key).append("=").append(runtimeContext.get(key)).append(" ");
		}**/
		
		DataObject logObject=DataObjectUtil.createDataObject("com.bos.utp.dataset.tools.AtOperlog");
		
		logObject.set("operatorid", user.getAttributes().get("operatorid"));
		logObject.set("userid", user.getUserId());
		logObject.set("operatorname", user.getUserName());
		logObject.set("opertime", DateUtil.parse(DateUtil.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
		logObject.set("clientip", user.getUserRemoteIP());
		logObject.set("logtype", logConfig.getLogtype());
		logObject.set("appname", "");
		logObject.set("funcname", logicSignature.getLogicLabel()+"("+logicSignature.getLogicName()+")");
		logObject.set("operdesc", operationDescription.toString());
		logObject.set("logstatus", "0");
		logObject.set("operstatus", "1");
		if(t!=null){
			logObject.set("exception", t.getMessage());
			logObject.set("operstatus", "0");
		}
		
		DatabaseExt.getPrimaryKey(logObject);
		DatabaseUtil.insertEntity("default", logObject);
	}




}
