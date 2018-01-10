package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;


import com.bos.utp.tools.DBUtil;
import com.eos.common.cache.ICacheLoader;
import com.eos.system.utility.StringUtil;

/**
 *
 * 业务日志配置缓存加载器
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: BusinessLogCacheLoader.java,v $
 * Revision 1.7  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.6  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.5  2010/10/18 14:04:28  caisy
 * 取消java中直接调用DatabaseExt
 *
 * Revision 1.4  2010/09/28 07:46:11  caisy
 * 删除无用代码
 *
 * Revision 1.3.6.1  2010/04/20 14:55:03  caisy
 * 缓存优化
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
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public class BusinessLogCacheLoader implements ICacheLoader {
	/**
	 * 根据funccode获取监控对象信息
	 * @param key 功能代码
	 * @return 功能码对应的监控对象
	 */
	public Object get(Object key) {
		Object[] objects=findBusinessLogList((String)key);
		if(objects!=null&&objects.length>0){
			return objects[0];
		}
		return null;
	}
	/**
	 * 系统启动，加载业务日志到缓存
	 */
	public Map preLoad() {
		return BusinessLogCacheManager.buildBusinessLogCacheMap(findBusinessLogList(null));
	}
	
	public void put(Object arg0, Object arg1) {
	}
	/**
	 * 清除监控对象信息，暂不需要实现
	 */ 
	public Object remove(Object arg0) {
		return null;
	}

	/**
	 * 获取业务日志信息
	 * @param funccode 功能代码
	 * @return
	 */
   protected Object[] findBusinessLogList(String funccode){
	   Map<String, String> parameterMap=new HashMap<String, String>();
	   if(StringUtil.isNotNullAndBlank(funccode))
		   parameterMap.put("funccode", funccode);

		return DBUtil.queryObjectsNamedSql("com.bos.utp.auth.permission.loadBusinessLogInfo", parameterMap);

   }



}
