package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;


import com.bos.utp.auth.handler.LogConfig;
import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheRuntimeException;
import com.eos.common.cache.ICache;

/**
 *
 * 业务日志缓存管理器
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: BusinessLogCacheManager.java,v $
 * Revision 1.5  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.4  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/01/21 05:24:54  caisy
 * 消除编译警告
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
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
 * Revision 1.2  2008/10/05 14:11:12  wengzr
 * Added:增加业务日志功能
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public class BusinessLogCacheManager {

	public static final String CACHE_INSTANCE="CacheForBusinessLog";

	public static ICache getCache(){
		ICache icache=CacheFactory.getInstance().findCache(CACHE_INSTANCE);
		if(icache==null){
			throw new CacheRuntimeException("not found cache \""+CACHE_INSTANCE+"\"");
		}
		return icache;
	}

	public static Map<String, LogConfig> buildBusinessLogCacheMap(Object[] objects){
		Map<String, LogConfig> cache=new HashMap<String, LogConfig>();
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				LogConfig logInfo=(LogConfig)objects[i];
				cache.put(logInfo.getFuncaction(), logInfo);
			}
		}
		return cache;
	}

	/**
	 * 从缓存里获取日志信息，日志配置不存在则返回null
	 * @param key 缓存key为功能入口funcaction
	 * @return
	 */
	public static LogConfig getLogFromCache(String key){
		return (LogConfig)getCache().get(key);
	}

	public static void clearCache(){
		getCache().clear();
	}


}
