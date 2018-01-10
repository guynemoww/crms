package com.bos.utp.auth.permission;

import com.bos.utp.auth.bizlet.PermissionUtil;

import com.eos.access.authorization.CheckedResult;
import com.eos.access.authorization.IAccessedResource;
import com.eos.access.authorization.IAccessedResourceCheckedHandler;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.eoscommon.LogUtil;

/**
 * 
 * 权限校验处理器，实现IAccessedResourceCheckedHandler接口，对调用所有资源进行权限验证<BR>
 * 配置在config/user-config.xml<BR>
 * 
 * <pre>
 *        &lt;module name=&quot;Accessed-Resource-Checked&quot;&gt;
 *           &lt;group name=&quot;Provider&quot;&gt;
 *               &lt;!-- 用户自定义资源检查的handler --&gt;
 *               &lt;configValue key=&quot;CheckedHandler&quot;&gt;com.bos.utp.auth.permission.PermissionCheckedHandler&lt;/configValue&gt;
 *               &lt;!-- 用户自定义资源的创建工厂 --&gt;
 *               &lt;configValue key=&quot;ResourceFactory&quot;&gt;com.primeton.ext.access.authorization.DefaultAccessedResourceFactory&lt;/configValue&gt;
 *           &lt;/group&gt;
 *       &lt;/module&gt;
 * </pre>
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: PermissionCheckedHandler.java,v $
 * 修改历史 Revision 1.15  2010/12/24 20:03:10  caisy
 * 修改历史 包重构到com.bos.utp.auth.skin
 * 修改历史
 * 修改历史 Revision 1.14  2010/12/09 06:29:25  caisy
 * 修改历史 重构haspermission返回值
 * 修改历史
 * 修改历史 Revision 1.13  2010/12/08 09:56:36  caisy
 * 修改历史 ONE-194 菜单url生成规则依赖应用的端口上下文配置
 * 修改历史
 * 修改历史 Revision 1.12  2010/12/02 10:43:05  caisy
 * 修改历史 增加调试日志
 * 修改历史
 *  
 * Revision 1.5 2009/05/07 03:51:52 caisy Update:如果eos
 * 
 * Revision 1.4 2009/04/14 03:54:32 caisy 资源权限控制
 * 
 * Revision 1.3 2009/03/30 05:39:38 caisy 代码规范
 * 
 * Revision 1.2 2009/01/21 05:24:54 caisy 消除编译警告
 * 
 * 
 * Revision 1.6 2008/11/28 04:15:22 wengzr Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 * 
 * Revision 1.5 2008/11/22 06:03:11 wengzr Update:登录逻辑增加用户所在机构信息的获取
 * 
 * Revision 1.4 2008/11/20 04:54:35 wengzr
 * Update:修改权限校验PermissionCheckedHandler,增加内置unchecked资源的判断
 * 
 * Revision 1.3 2008/11/12 15:24:07 wengzr Update:修改LOGIN_PAGE_FLOW值
 * 
 * Revision 1.2 2008/11/12 14:34:30 wengzr Added:增加对构件包资源自定义拦截判断
 * 
 * Revision 1.1 2008/10/07 09:25:50 wengzr *** empty log message ***
 * 
 * Revision 1.8 2008/09/25 16:35:51 wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包 并修改缓存模型删除模型中的名称XXXname字段
 * 
 * Revision 1.7 2008/09/23 09:25:03 wengzr
 * Added:增加对Portal资源的权限判断,并设置AuthContributionListener初始化缓存配置
 * 
 * Revision 1.6 2008/09/17 11:06:30 wengzr Update:修改用户登录判断方式
 * 
 * Revision 1.5 2008/09/11 13:55:57 wengzr Update:修改登录实现方式和权限校验BUG
 * 
 * Revision 1.4 2008/09/11 09:59:07 wengzr
 * Update:修改获取操作员的所有角色实现,增加运算逻辑appendObject和getUniqueObjects
 * 
 * 
 * Revision 1.1 2008/08/18 14:19:49 wengzr 提交CVS
 * 
 */
public class PermissionCheckedHandler implements IAccessedResourceCheckedHandler {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3655993611723067375L;

	public CheckedResult check(IAccessedResource accessedResource, IUserObject userObject) {
		try {			
			LogUtil.logDebug("权限检验:uri={0}", null, new Object[]{accessedResource.getResourceURI()});
			return PermissionUtil.hasPermission(accessedResource, userObject);
		} catch (Exception e) {
			LogUtil.logError("权限检验出错", e, (Object) null);
			return CheckedResult.REJECT;
		}
	}
}
