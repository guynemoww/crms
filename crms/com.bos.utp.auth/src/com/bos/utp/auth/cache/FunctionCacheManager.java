package com.bos.utp.auth.cache;

import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheRuntimeException;
import com.eos.common.cache.ICache;

/**
 * 
 * 功能缓存管理器,实现对功能缓存的调用和更新.
 * 
 * @author 蔡述尧 caisy (mailto:caisy@primeton.com)
 */
public class FunctionCacheManager {


	public static final String CACHE_INSTANCE = "CacheForFunction";

	public static ICache getCache() {
		ICache icache = CacheFactory.getInstance().findCache(CACHE_INSTANCE);
		if (icache == null) {
			throw new CacheRuntimeException("not found cache \"" + CACHE_INSTANCE + "\"");
		}
		return icache;
	}

	
	/**
	 * 清除对应角色的缓存
	 * 
	 * @param key
	 *            角色ID
	 */
	public static void removeCache(Object key) {
		getCache().remove(key);
	}


	public static void clearCache() {
		getCache().clear();
	}
}
