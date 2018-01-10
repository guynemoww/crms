package com.bos.utp.auth.ldap;

import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.bos.utp.ABFConfigKey;

import com.eos.foundation.common.utils.StringUtil;

/**
 *
 * LDAP操作工具类<BR>
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LDAPUtil.java,v $
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
 * Revision 1.1  2009/01/05 02:34:57  caisy
 * 二期初始版本
 *
 * Revision 1.2  2008/11/28 04:15:22  wengzr
 * Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.4  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.3  2008/09/16 12:32:47  wengzr
 * Update:登录实现密码加密和LDAP验证
 *
 * Revision 1.2  2008/09/15 10:06:24  wengzr
 * Refactor:重构配置文件的获取方式
 *
 * Revision 1.1  2008/08/21 09:42:24  wengzr
 * Refactor:重构sso包分opensso,simple以及cas的集成
 *
 * Revision 1.3  2008/08/19 18:51:23  wengzr
 * Update:更新LDAP实现,增加用户组的映射
 *
 * Revision 1.2  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/17 08:12:57  wengzr
 * Update:增加LDAP,SSO默认实现
 *
 */
public abstract class LDAPUtil {

	/**
	 * 获取LDAP上下文
	 * @return
	 * @throws Exception
	 */
	public static LdapContext getContext() throws Exception {

		String baseProviderURL = ABFConfigKey.LDAP_BASE_PROVIDER_URL.getConfigValue();
		String pricipal =ABFConfigKey.LDAP_SECURITY_PRINCIPAL.getConfigValue();
		String credentials = ABFConfigKey.LDAP_SECURITY_CREDENTIALS.getConfigValue();

		return getContext(baseProviderURL, pricipal, credentials);
	}

	/**
	 * 获取LDAP上下文
	 * @param providerURL
	 * @param pricipal 用户名
	 * @param credentials 密码
	 * @return
	 * @throws Exception
	 */
	public static LdapContext getContext(String providerURL, String pricipal,String credentials)throws Exception {

		Properties env = new Properties();

		env.put(Context.INITIAL_CONTEXT_FACTORY,ABFConfigKey.LDAP_FACTORY_INITIAL.getConfigValue());
		env.put(Context.PROVIDER_URL, providerURL);
		env.put(Context.SECURITY_PRINCIPAL, pricipal);
		env.put(Context.SECURITY_CREDENTIALS, credentials);
		env.put(Context.REFERRAL,ABFConfigKey.LDAP_REFERRAL.getConfigValue());

		LdapContext ctx = null;

		try {
			ctx = new InitialLdapContext(env, null);
		}
		catch (Exception e) {
		}

		return ctx;
	}


	/**
	 * 在LDAP上下文环境中获取指定基准条目名称(DN)下的用户
	 * @param ctx LDAP上下文
	 * @param maxResults 返回的最大结果
	 * @param baseDN 基准DN
	 * @param userFilter 过滤调节
	 * @return
	 * @throws Exception
	 */
	public static NamingEnumeration<SearchResult> getUsers(LdapContext ctx, int maxResults, String baseDN,String userFilter)
		throws Exception {
		SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, maxResults, 0, null, false, false);
		return ctx.search(baseDN, userFilter, cons);
	}


	/**
	 *
	 * @param binding
	 * @return
	 * @throws Exception
	 */
	public static String getNameInNamespace(Binding binding)throws Exception {

		String baseDN =ABFConfigKey.LDAP_BASE_DN.getConfigValue();
		if (StringUtil.isBlank(baseDN)) {
			return binding.getName();
		} else {
			StringBuilder sb = new StringBuilder(binding.getName());
			return sb.append(",").append(baseDN).toString();
		}
	}

	/**
	 * 获取属性
	 * @param ctx LDAP上下文对象
	 * @param fullDistinguishedName 基准条目名称
	 * @return Attributes
	 * @throws Exception
	 */
	public static Attributes getAttributes(LdapContext ctx, String fullDistinguishedName)
		throws Exception {

		String[] attrIds = {
			"creatorsName", "createTimestamp", "modifiersName","modifyTimestamp"
		};

		Attributes attrs = ctx.getAttributes(fullDistinguishedName);
		NamingEnumeration<? extends Attribute> enu = ctx.getAttributes(fullDistinguishedName, attrIds).getAll();
		while (enu.hasMoreElements()) {
			attrs.put(enu.nextElement());
		}

		return attrs;
	}

	/**
	 * 获取用户映射
	 *
	 * @return Properties
	 */
	public static Properties getUserMappings(){
		String contents =ABFConfigKey.LDAP_USER_MAPPINGS.getConfigValue();
		Properties properties=new Properties();
		String[] userMapping=contents.split(",");
		for(int i=0;i<userMapping.length;i++){
			String[] content=userMapping[i].split("=");
			properties.put(content[0].trim(), content[1].trim());
		}

		return properties;
	}

	/**
	 * 获取LDAP验证查找过滤表达式<BR>
	 * 如果查找出的过滤表达式包含@screen_name@,@user_id@ 将分别用参数screenName,userId值替换
	 * @param screenName 根显示名称
	 * @param userId 用户登录名
	 * @return
	 * @throws Exception
	 */
	public static String getAuthSearchFilter(String screenName,String userId)throws Exception {

		String filter =ABFConfigKey.LDAP_AUTH_SEARCH_FILTER.getConfigValue();
		filter = replace(filter,new String[] {"@screen_name@", "@user_id@"},new String[] {screenName,userId});
		return filter;
	}


	private static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = s.replace(oldSubs[i], newSubs[i]);
		}

		return s;
	}

}

