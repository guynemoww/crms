package com.bos.utp;

import com.eos.foundation.common.utils.StringUtil;

/**
 * 
 * EOS配置项
 * 
 * @author charles (mailto:caisy@primeton.com)
 */
/*
 * Modify history $Log: ABFConfigKey.java,v $
 * Modify history Revision 1.4  2010/12/24 20:03:27  caisy
 * Modify history 包重构到com.bos.utp.auth.skin
 * Modify history
 * Modify history Revision 1.3  2010/12/20 07:10:13  caisy
 * Modify history
 * Modify history Revision 1.2  2010/12/09 06:27:43  caisy
 * Modify history 增加默认skin的配置
 * Modify history
 * Modify history Revision 1.1  2010/12/07 03:09:46  caisy
 * Modify history
 */
public enum ABFConfigKey {

	/** 
	 * 登录相关配置 
	 */
	/** 登录加密算法 * */
	LOGIN_PASSWORD_ENCRYPTION_ALGORITHM(ABFGroup.AUTH_ABFRAME_LOGIN, "password_encryption_algorithm"),
	/** 是否使用验证码 **/
	LOGIN_USE_VERIFY_CODE(ABFGroup.AUTH_ABFRAME_LOGIN, "use_verify_code"),
	/** 是否使用语言选择 **/
	USE_LANGUAGE_CHOOSE(ABFGroup.AUTH_ABFRAME_LOGIN, "use_language_choose"),
	/** 登录的缺省语言 **/
	USE_DEFAULT_LANGUAGE(ABFGroup.AUTH_ABFRAME_LOGIN, "use_default_language"),
	
	/**
	 * 权限校验配置项
	 */
	/** 是否使用ABFrame的权限验证 **/
	PERMISSION_CHECK_USEFLAG(ABFGroup.AUTH_ABFRAME_PERMISSION, "permission_check_isused"),
	/** 权限校验器 * */
	PERMISSION_CHECK_HANDER(ABFGroup.AUTH_ABFRAME_PERMISSION, "permission_check_handler"),
	/** 权限缓存数据提供者 * */
	PERMISSION_DATA_PROVIDER(ABFGroup.AUTH_ABFRAME_PERMISSION, "permission_data_provider"),
	/** 系统管理员账号 逗号分隔userid * */
	PERMISSION_ADMIN_USERS(ABFGroup.AUTH_ABFRAME_PERMISSION, "admin_users"),
	/** 内置不检查权限的资源 * */
	PERMISSION_UNCHECK_RESOURCE(ABFGroup.AUTH_ABFRAME_PERMISSION, "default_uncheck_resource"),
	/** 定义需要权限校验的构件包 * */
	PERMISSION_CHECKED_CONTRIBUTIONS(ABFGroup.AUTH_ABFRAME_PERMISSION, "checked_contributions"),
	/** 定义不需要权限校验的构件包 * */
	PERMISSION_UNCHECKED_CONTRIBUTIONS(ABFGroup.AUTH_ABFRAME_PERMISSION, "unchecked_contributions"),
	/** 不匹配的构件包是否校验 不满足上述两个构件包规则的构件包默认是否使用校验 * */
	PERMISSION_UNMATCH_CHECKED(ABFGroup.AUTH_ABFRAME_PERMISSION, "unmatch_contributions_checked"),
	/** 不在数据库功能表中注册的访问资源默认是否有权限使用，true表示有权限，false表示无 * */
	PERMISSION_UNREGIST_ACCESS(ABFGroup.AUTH_ABFRAME_PERMISSION, "unregistered_function_access"),

	/**
	 * 缓存相关配置
	 */
	/** 权限模型缓存 * */
	CACHE_FOR_PERMISSION(ABFGroup.AUTH_ABFRAME_CACHE, "CacheForPermission"),
	/** 已注册的功能缓存 * */
	CACHE_FOR_FUNCTION(ABFGroup.AUTH_ABFRAME_CACHE, "CacheForFunction"),
	/** Portal资源缓存 * */
	CACHE_FOR_PORTALRESOURCE(ABFGroup.AUTH_ABFRAME_CACHE, "CacheForPortalResource"),
	/** 业务日志缓存 * */
	CACHE_FOR_BUSINESSLOG(ABFGroup.AUTH_ABFRAME_CACHE, "CacheForBusinessLog"),

	/**
	 * 业务日志相关配置
	 */
	/** 业务日志记录器 **/
	BUSINESS_LOG_PROVIDER(ABFGroup.AUTH_ABFRAME_BUSSLOG, "business_log_provider"),

	/**
	 * LDAP相关
	 */
	/**
	 *LDAP相关配置
	 */
	 /** LDAP 服务提供者URL **/
	 LDAP_BASE_PROVIDER_URL(ABFGroup.AUTH_ABFRAME_LDAP,"ldap_base_provider_url"),
	 /** 连接用户名 **/
	 LDAP_SECURITY_PRINCIPAL(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_security_principal"),
	 /** 连接密码 **/
	 LDAP_SECURITY_CREDENTIALS(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_security_credentials"),
	 /** LDAP工厂 **/
	 LDAP_FACTORY_INITIAL(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_factory_initial"),
	 /** LDAP引用 **/
	 LDAP_REFERRAL(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_referral"),
	 /** 是否使用LDAP验证 **/
	 LDAP_AUTH_ENABLED(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_auth_enabled"),
	
	 /**LDAP基本目录名	 **/
	 LDAP_BASE_DN(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_base_dn"),	
	 /** LDAP验证查找过滤	 **/
	 LDAP_AUTH_SEARCH_FILTER(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_auth_search_filter"),
	/** LDAP用户名的映射 **/
	 LDAP_USER_MAPPINGS(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_user_mappings"),
	/**
	 * LDAP验证方式:<BR>
	 * 基于用户绑定方式-bind
	 * 基于用密码比较方式-password-compare
	 */
	 LDAP_AUTH_METHOD(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_auth_method"),
	/**LDAP用户被停用错误信息**/
	 LDAP_ERROR_USER_LOCKOUT(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_error_user_lockout"),
	/**LDAP用户密码过期错误	 **/
	 LDAP_ERROR_PASSWORD_EXPIRED(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_error_password_expired"),
	/**LDAP用户的密码加密算法 **/
	 LDAP_AUTH_PASSWORD_ENCRYPTION_ALGORITHM(ABFGroup.AUTH_ABFRAME_LDAP, "ldap_auth_password_encryption_algorithm"),
	
	/**
	 * skin相关配置
	 */
	 /** user中存放layout的属性名**/
	 SKIN_LAYOUTATTR(ABFGroup.AUTH_ABFRAME_SKIN, "skin_layoutattr"),
	 /** user中存放styyle的属性名**/
	 SKIN_STYLEATTR(ABFGroup.AUTH_ABFRAME_SKIN, "skin_styleattr"),
	 /** layout的位置**/
	 SKIN_LAYOUT_LOC(ABFGroup.AUTH_ABFRAME_SKIN, "skin_layout_loc"),
	 /** styles的位置**/
	 SKIN_STYLE_LOC(ABFGroup.AUTH_ABFRAME_SKIN, "skin_style_loc"),
	 /** layout中style的位置**/
	 SKIN_LAYOUTSTYLE_LOC(ABFGroup.AUTH_ABFRAME_SKIN, "skin_layoutstyle_loc"),     
	 /** 登录缺省的layout **/
	 SKIN_DEFAULT_LAYOUT(ABFGroup.AUTH_ABFRAME_SKIN, "skin_default_layout"),
	/** 登录缺省的style **/
	 SKIN_DEFAULT_STYLE(ABFGroup.AUTH_ABFRAME_SKIN, "skin_default_style"),
	 /** portal：layout和style从参数中获取**/
	 SKIN_PARAM_NAME(ABFGroup.AUTH_ABFRAME_SKIN, "skin_param_name"),
	/** 外部skin的转换器的实现类配置**/
	 SKIN_CONVERTOR(ABFGroup.AUTH_ABFRAME_SKIN, "skin_convertor");
	
	
	private final ABFGroup group;

	private String name;

	ABFConfigKey(ABFGroup group, String name) {
		this.group = group;
		this.name = name;
	}

	public ABFGroup group() {
		return group;
	}

	/**
	 * 以字符串形式返回构件包配置项的值
	 * 
	 * @return 配置项的值
	 */
	public String getConfigValue() {
		return group.getGroupConfig().getConfigValue(name);
	}
	/**
	 * 以字符串形式返回构件包配置项的值，提供为空时的默认值
	 * 
	 * @param  默认值如果配置项为空则返回此默认值
	 * @return 配置项的值
	 */
	public String getConfigValue(String defValue) {
		String value = getConfigValue();		
		return StringUtil.isBlank(value)?defValue:value;
	}
	/**
	 * 以布尔值形式返回构件包配置项的值
	 * 
	 * @return
	 */
	public boolean getBLConfigValue() {
		return Boolean.parseBoolean(getConfigValue());
	}
	/**
	 * 以布尔值形式返回构件包配置项的值,如果配置项无值则返回默认值
	 * @param 默认值
	 * @return 
	 */
	public boolean getBLConfigValue(boolean defValue) {
		String value = getConfigValue();
		return  StringUtil.isBlank(value)?defValue:Boolean.parseBoolean(getConfigValue());
	}

	@Override
	public String toString() {
		return group.toString() + ":" + name;
	}

}