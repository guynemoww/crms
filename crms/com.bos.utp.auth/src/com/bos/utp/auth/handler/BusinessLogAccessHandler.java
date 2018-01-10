package com.bos.utp.auth.handler;


import com.bos.utp.auth.cache.BusinessLogCacheManager;
import com.eos.engine.core.IHandler;
import com.primeton.ext.engine.core.IRuntimeContext;

/**
 * 
 * 业务日志拦截器，只支持逻辑流的拦截
 * 
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: BusinessLogAccessHandler.java,v $
 * 修改历史 Revision 1.6  2010/12/01 03:23:14  caisy
 * 修改历史 配置文件读取方式修改
 * 修改历史
 * 修改历史 Revision 1.5  2010/11/30 16:12:23  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.4  2009/04/27 13:22:59  wengzr
 * 修改历史 Update:重构日志处理方法
 * 修改历史
 * 修改历史 Revision 1.3  2009/03/30 05:39:38  caisy
 * 修改历史 代码规范
 * 修改历史
 * 修改历史 Revision 1.2  2009/02/24 09:30:09  zhujiang
 * 修改历史 *** empty log message ***
 * 修改历史 Revision 1.1 2009/01/05 02:34:57
 * caisy 二期初始版本
 * 
 * Revision 1.3 2008/11/25 06:23:10 wengzr Update:修改业务日志实现方式
 * Update:修改业务日志表和日志配置表,增加日志类型字段
 * 
 * Revision 1.2 2008/11/24 07:28:15 wengzr Update:重构PermissionUtil->CacheUtil
 * Update:修改业务日志
 * 
 * Revision 1.1 2008/10/07 09:25:49 wengzr *** empty log message ***
 * 
 * Revision 1.4 2008/10/05 22:07:50 wengzr Update:修复NPE错误
 * 
 * Revision 1.3 2008/10/05 14:11:25 wengzr Added:增加业务日志功能
 * 
 * Revision 1.2 2008/09/26 15:24:51 wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 * 
 * Revision 1.1 2008/09/25 16:35:51 wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包 并修改缓存模型删除模型中的名称XXXname字段
 * 
 */
public class BusinessLogAccessHandler extends BaseLogAccessHandler {

	private LogConfig getLogConfig(String bizName) {
		return BusinessLogCacheManager.getLogFromCache(bizName);
	}

	public int doAfter(IRuntimeContext context) {
		return processLog(context, getLogConfig(context.getName()), LogConfig.LOG_EVENT_AFTER,null);

	}

	public int doBefore(IRuntimeContext context) {
		return processLog(context, getLogConfig(context.getName()), LogConfig.LOG_EVENT_BEFORE,null);

	}

	public int doException(IRuntimeContext context, Throwable t) {
		return processLog(context, getLogConfig(context.getName()), LogConfig.LOG_EVENT_EXCEPTION,null);
	}

	public int doFinalize(IRuntimeContext context) {
		return IHandler.RET_OK;
	}

}
