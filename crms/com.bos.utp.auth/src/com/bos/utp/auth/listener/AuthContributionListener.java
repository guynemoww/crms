package com.bos.utp.auth.listener;

import java.util.Iterator;

import com.bos.utp.ABFConfigKey;
import com.bos.utp.ABFGroup;

import com.eos.access.authorization.AccessedResourceCheckedHandlerProvider;
import com.eos.access.authorization.IAccessedResourceCheckedHandler;
import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheProperty;
import com.eos.common.cache.ICache;
import com.eos.infra.config.Configuration.Group;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;

/**
 * 
 * 构件包监听器<BR>
 * 监听构件包启动时，自动创建权限缓存实例和Portal资源缓存实例,见contribution.eosinf配置
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: AuthContributionListener.java,v $
 * 修改历史 Revision 1.10  2010/12/07 03:09:46  caisy
 * 修改历史 配置读取方式修改
 * 修改历史
 * 修改历史 Revision 1.9  2010/12/01 03:23:14  caisy
 * 修改历史 配置文件读取方式修改
 * 修改历史
 * 修改历史 Revision 1.8  2010/11/30 16:12:23  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.7  2009/05/21 13:17:29  caisy
 * 修改历史 Update:如果eos governor中配置使用portal模式则也同步取消权限校验
 * 修改历史
 * 修改历史 Revision 1.6  2009/05/14 11:18:13  caisy
 * 修改历史 Update:增加是否使用权限校验的开关配置，默认为使用
 * 修改历史 Revision 1.5 2009/05/04 08:31:20
 * caisy Update:注释掉测试代码
 * 
 * Revision 1.4 2009/04/14 03:54:32 caisy 资源权限控制
 * 
 * Revision 1.3 2009/03/30 05:39:38 caisy 代码规范
 * 
 * Revision 1.2 2009/01/21 05:24:54 caisy 消除编译警告
 * 
 * Revision 1.1 2009/01/05 02:34:57 caisy 二期初始版本
 * 
 * Revision 1.3 2008/11/28 04:15:22 wengzr Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 * 
 * Revision 1.2 2008/10/08 08:27:23 wengzr Update:修复NPE错误
 * 
 * Revision 1.1 2008/10/07 09:25:50 wengzr *** empty log message ***
 * 
 * Revision 1.5 2008/09/26 15:24:51 wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 * 
 * Revision 1.4 2008/09/24 10:49:19 wengzr Update:修改当缓存名称已存在时，不创建Cache
 * 
 * Revision 1.3 2008/09/23 11:23:26 wengzr
 * Update:增加Auth构件包contribution.eosinf配置缓存和CheckHandler
 * 
 * Revision 1.2 2008/09/23 09:25:03 wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 * 
 */
public class AuthContributionListener implements IContributionListener {

	public void load(IContributionEvent event) {
//构件包加载处理事件
	}

	public void loadFinished(IContributionEvent event) {
		// 加载缓存
		Group cacheGroup = ABFGroup.AUTH_ABFRAME_CACHE.getGroupConfig();
		if (cacheGroup != null) {
			Iterator cacheKeys = cacheGroup.getValues().keySet().iterator();
			while (cacheKeys.hasNext()) {
				String cacheName = (String) cacheKeys.next();
				String cacheLoader = cacheGroup.getConfigValue(cacheName);
				initializeCacheConfiguration(cacheName, cacheLoader);
			}
		}
		// 注册权限监控handler
		/**boolean checked = ABFConfigKey.PERMISSION_CHECK_USEFLAG.getBLConfigValue();// 是否使用ABFrame校验
		
		if (checked) {
			IAccessedResourceCheckedHandler handler = this.getCheckHandler();
			if (handler != null) {
				AccessedResourceCheckedHandlerProvider.setHandler(handler);
			}
		}else{			
			AccessedResourceCheckedHandlerProvider.setHandler(null);		
		}**/
		// 增加statement监控
		// ISynchronizationManager
		// manager=SynchronizationManagers.getSynchronizationManager();
		// 获取数据库连接的同步扩展实现列表
		// List<IStatementSynchronization> statmeMonitors=
		// manager.getStatementSynchronizations();
		// IStatementSynchronization statementSyn=new
		// StatementMonitor();//用户自己实现的statement同步扩展。
		// 增加该扩展实现到同步列表中，以供系统回调
		// statmeMonitors.add(statementSyn);
	}

	public void unLoad(IContributionEvent event) {
		//构件包删除事件处理
	}

	//TODO 此方法建议移到辅助工具类中 charles
	private void initializeCacheConfiguration(String cacheName, String cacheLoader) {
		ICache cache = CacheFactory.getInstance().findCache(cacheName);
		if (cache == null) {
			CacheProperty cacheProperty = new CacheProperty();
			cacheProperty.setCacheName(cacheName);
			cacheProperty.setCacheLoader(cacheLoader);
			CacheFactory.getInstance().createCache(cacheProperty, false);
		}
	}
//	TODO 此方法建议移到辅助工具类中 charles
	private IAccessedResourceCheckedHandler getCheckHandler() {

		IAccessedResourceCheckedHandler checkHandler = null;

		String checkHandlerName = ABFConfigKey.PERMISSION_CHECK_HANDER.getConfigValue();

		if (checkHandlerName != null) {
			try {
				checkHandler = (IAccessedResourceCheckedHandler) Class.forName(checkHandlerName).newInstance();
			} catch (Exception e) {
			}
		}

		return checkHandler;

	}
}
