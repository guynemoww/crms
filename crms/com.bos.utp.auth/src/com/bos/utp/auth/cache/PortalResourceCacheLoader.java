package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Portal资源(即无须权限校验的资源列表)缓存加载器<BR>
 *
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PortalResourceCacheLoader.java,v $
 * Revision 1.7  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.6  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.5  2010/09/28 07:46:11  caisy
 * 删除无用代码
 *
 * Revision 1.4.6.1  2010/04/20 14:55:03  caisy
 * 缓存优化
 *
 * Revision 1.4  2009/05/14 11:18:59  caisy
 * Update:Portal缓存判断错误，会导致所有的功能被人为portal资源
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/03/23 03:21:34  caisy
 * Update:可管理机构弹出选择界面错误
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.1  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.1  2008/09/23 09:25:03  wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 *
 */
public class PortalResourceCacheLoader extends BasePermissionCacheLoader{

	public PortalResourceCacheLoader(){
		super();
	}

	private Map getPortalResourceAll(){
		Object[] portalResources=null;
		try{
			portalResources=accessor.loadPortalResourceAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		//增加记录避免重复加载
		if(portalResources==null||portalResources.length==0){
			portalResources = new  Object[1];
			HashMap<String, String> m=new HashMap<String,String>();
			m.put("PORTALID","-999999");
			m.put("URL","com.bos.utp.auth.Login.flow");
			portalResources[0] =m; 			
		}
		return PortalResourceCacheManager.buildPortalResourceMap(portalResources);
	}
    private Object getPortalResurce(String url){
    	return null;
    }
	public Object get(Object arg0) {
		return getPortalResurce((String) arg0);
	}

	public Map preLoad() {
		return getPortalResourceAll();
	}

	public void put(Object arg0, Object arg1) {
		// 用于持久化，不需要实现

	}

	public Object remove(Object arg0) {
		// 用于持久化，不需要实现
		return null;
	}

}
