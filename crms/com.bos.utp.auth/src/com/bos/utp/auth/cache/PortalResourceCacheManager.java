package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;

import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheRuntimeException;
import com.eos.common.cache.ICache;

/**
 *
 * Portal资源缓存管理器<BR>
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PortalResourceCacheManager.java,v $
 * Revision 1.6  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.5  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2009/05/14 11:18:59  caisy
 * Update:Portal缓存判断错误，会导致所有的功能被人为portal资源
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/03/09 08:20:18  caisy
 * Update:Portable资源判断出错
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
 *
 * Revision 1.2  2008/12/01 07:11:27  wengzr
 * Update:修复PortalResourceCacheLoader无法触发的BUG
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.1  2008/09/23 09:25:03  wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 *
 */
public class PortalResourceCacheManager {

	public static final String CACHE_INSTANCE="CacheForPortalResource";

	protected static ICache getCache(){
		ICache icache=CacheFactory.getInstance().findCache(CACHE_INSTANCE);
		if(icache==null){
			throw new CacheRuntimeException("not found cache \""+CACHE_INSTANCE+"\"");
		}
		return icache;
	}


	/**
	 * 判断当前资源路径是否为Portal资源
	 * @param url 资源路径
	 * @return
	 */
	public static boolean isPortalResource(String url){
		boolean isPortal = false;
		Object resource=getCache().get(url);
		if(resource!=null){			
			   isPortal = true;			
		}			
		return isPortal;

	}

	/**
	 * 构建Portal资源缓存Map，以portalid为key,资源的URL为value
	 * @param portals 从数据源获取的portal资源对象列表
	 * @return Map
	 */
	public static Map buildPortalResourceMap(Object[] portals){
		Map<Object, Object> cache=new HashMap<Object, Object>();
		if(portals!=null){
			for(int i=0;i<portals.length;i++){
				Map m=(Map)portals[i];
				cache.put(m.get("PORTALID"), m.get("URL"));
			}
		}
		return cache;
	}

	/**
	 * 清除对应角色的缓存
	 * @param key 角色ID
	 */
	public static void removeCache(Object key){
		getCache().remove(key);
	}

	public static void clearCache(){
		getCache().clear();
	}

}
