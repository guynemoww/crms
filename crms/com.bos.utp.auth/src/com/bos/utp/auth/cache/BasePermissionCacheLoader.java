package com.bos.utp.auth.cache;

import com.bos.utp.ABFConfigKey;

import com.bos.utp.auth.permission.PermissionDataAccessor;
import com.eos.common.cache.ICacheLoader;
/*package com.bos.utp.auth.cache;

import com.bos.utp.ABFConfigKey;

import com.bos.utp.auth.permission.PermissionDataAccessor;
import com.eos.common.cache.ICacheLoader;

/**
 *
 * 权限缓存加载器基本实现<BR>
 * 根据contribution.eosinfo的数据提供者实现，获取对应的提供者实例：
 * <pre>
 *  &lt;!-- 权限校验配置 -->
 *       &lt;group name="permission-config">
 *           &lt;!-- 权限校验handler -->
 *           &lt;configValue key="permission_check_handler">com.bos.utp.auth.permission.PermissionCheckedHandler&lt;/configValue>
 *           &lt;!-- 功能权限数据提供者实现，可以采用服务调用也可以采用数据访问实现 -->
 *           &lt;configValue key="permission_data_provider">com.bos.utp.auth.permission.RDBMSPermissionDataAccessor&lt;/configValue>
 *   &lt;/group>
 *  </pre>
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: BasePermissionCacheLoader.java,v $
 * Revision 1.5  2010/12/07 03:09:46  caisy
 * 配置读取方式修改
 *
 * Revision 1.4  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.3  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
 *
 * Revision 1.2  2008/11/28 04:15:22  wengzr
 * Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public abstract class BasePermissionCacheLoader implements ICacheLoader{

	protected PermissionDataAccessor accessor;

	public BasePermissionCacheLoader(){
		String perssionDataAccessor=ABFConfigKey.PERMISSION_DATA_PROVIDER.getConfigValue();
		try{

			accessor=(PermissionDataAccessor)Class.forName(perssionDataAccessor).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
