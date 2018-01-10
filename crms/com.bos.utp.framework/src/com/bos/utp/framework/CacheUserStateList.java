package com.bos.utp.framework;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

/***
 * 
 * @author 刘子良
 * @desc:缓存用户资源信息
 */
@Bizlet("cache")
public class CacheUserStateList {
	@Bizlet("cache")
	public static List cacheResource(String userId) {
		//com.bos.utp.framework.ehcache.xml
		//CacheManager manager = CacheManager.create("com\\bos\\utp\\framework\\ehcache.xml");

		//先在cache中查找相关信息
		List roleInfo = null;

		//第一步：以单例的方式创建CacheManager,默认调用JAR包中的ehcache-failsafe.xml配置文件
		CacheManager cacheManager = CacheManager.create();

		//第二步：根据cache名称获取Ehcache，如果在ehcache-failsafe.xml已经配置这个名称的Ehcache，则可以获取到，
		//如果没有配置，也可以new一个Ehcache，当然一般在代码中是通过配置的方式写的。
		String cacheName = "demoCache";
		Ehcache cache = cacheManager.getEhcache(cacheName);
		if (cache == null) {
			cache = new Cache(cacheName, 100, true, false, 120, 120);
			cacheManager.addCache(cache);
		}

		//第三步：根据key获取Ehcache中的存储的Element
		Element element = cache.get(userId);

		//第四步：通过Element得到你要的value
		//element.getValue():只有AcResourcerole对象实现了串行化接口才可以使用getValue(),一般都使用getObjectValue()

		if (element != null) {
			roleInfo = (List) element.getObjectValue();
		}
		if (roleInfo == null) {
			Map map = new HashMap();
			map.put("userId", userId);
			roleInfo = Arrays
					.asList(DatabaseExt
							.queryByNamedSql(
									"default",
									"com.bos.utp.framework.auth.resourceRole.queryResourceIDByUserId",
									map));
			if (roleInfo != null) {
				cache.put(new Element(userId, roleInfo));
			}
		}

		return roleInfo;
	}
	
	/**
	 * 清除缓存
	 */
	@Bizlet("clearCache")
	public static void clearCache(){
		//第一步：以单例的方式创建CacheManager,默认调用JAR包中的ehcache-failsafe.xml配置文件
		CacheManager cacheManager = CacheManager.create();
		
        //第二步：根据cache名称获取Ehcache，如果在ehcache-failsafe.xml已经配置这个名称的Ehcache，则可以获取到，
		//如果没有配置，也可以new一个Ehcache，当然一般在代码中是通过配置的方式写的。
		String cacheName = "demoCache";
		Ehcache cache = cacheManager.getEhcache(cacheName);
		if (cache == null) {
			cache = new Cache(cacheName, 100, true, false, 120, 120);
			cacheManager.addCache(cache);
		}
		
		cacheManager.shutdown();//清除缓存
	}
	
	
}
