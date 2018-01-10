package com.bos.utp.auth.bizlet;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.bos.utp.ABFConfigKey;

import com.bos.utp.auth.ldap.LDAPAuthResult;
import com.bos.utp.auth.ldap.LDAPUtil;
import com.bos.utp.auth.ldap.PasswordExpiredException;
import com.bos.utp.auth.ldap.UserLockoutException;
import com.eos.foundation.common.utils.CryptoUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.utility.StringUtil;


/**
 *
 * LDAP验证实现类
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LDAPAuthenticator.java,v $
 * Revision 1.7  2010/12/07 03:09:46  caisy
 * 配置读取方式修改
 *
 * Revision 1.6  2010/12/01 03:01:36  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.5  2010/12/01 03:00:17  caisy
 * *** empty log message ***
 *
 * Revision 1.4  2010/12/01 02:54:38  caisy
 * *** empty log message ***
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
 * Revision 1.3  2008/12/01 09:34:48  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/11/28 04:15:22  wengzr
 * Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.3  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 * Revision 1.2  2008/09/16 13:00:52  wengzr
 * Refactor:重构JAAS登录模块
 *
 * Revision 1.1  2008/09/16 12:32:47  wengzr
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
@Bizlet("LDAP验证器运算逻辑")
public class LDAPAuthenticator {

	public static final int SUCCESS = 0;

	public static final int FAILURE = -2;

	public static final int NONE=-1;

	/**
	 * 采用上下文绑定验证方式
	 */
	private static final String AUTH_METHOD_BIND = "bind";
	/**
	 * 采用密码比较验证方式
	 */
	private static final String AUTH_METHOD_PASSWORD_COMPARE ="password-compare";


	/**
	 * 验证用户名和密码是否匹配<BR>
	 *
	 * @param userId 用户名
	 * @param password 密码
	 * @return 返回-1,验证失败<BR>
	 *         返回1,验证成功<BR>
	 *         返回0,用户不存在
	 * @throws Exception
	 */
	@Bizlet(params = { @BizletParam(index = 0, paramAlias = "username"),
			@BizletParam(index = 1, paramAlias = "password") })
	public static int authenticate(String username,String password)throws Exception {

		String baseDN = ABFConfigKey.LDAP_BASE_DN.getConfigValue();

		LdapContext ctx = LDAPUtil.getContext();

		if (ctx == null)
			return FAILURE;

		//Process LDAP auth search filter
		String filter = LDAPUtil.getAuthSearchFilter(LDAPUtil.getUserMappings().getProperty("screenName"), username);

		try {
			SearchControls cons = new SearchControls(SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			NamingEnumeration<SearchResult> enu = ctx.search(baseDN, filter, cons);

			if (enu.hasMoreElements()) {

				SearchResult result = enu.nextElement();

				String fullUserDN = LDAPUtil.getNameInNamespace(result);

				Attributes attrs = LDAPUtil.getAttributes(ctx, fullUserDN);

				LDAPAuthResult ldapAuthResult = authenticate(ctx, attrs, fullUserDN, password);

				// Process LDAP failure codes
				String errorMessage = ldapAuthResult.getErrorMessage();
				if (errorMessage != null) {
					if (errorMessage.indexOf(ABFConfigKey.LDAP_ERROR_USER_LOCKOUT.getConfigValue())!= -1) {

						throw new UserLockoutException();
					}
					else if (errorMessage.indexOf(ABFConfigKey.LDAP_ERROR_PASSWORD_EXPIRED.getConfigValue())!= -1) {

						throw new PasswordExpiredException();
					}
				}

				if (!ldapAuthResult.isAuthenticated())
					return FAILURE;

				return SUCCESS;

			}else {
				return NONE;
			}
		}
		catch (Exception e) {
			throw e;
		}finally {
			ctx.close();
		}
	}

	/**
	 * 验证LDAP用户名和密码是否匹配，并返回验证结果.验证方式由两种:<BR>
	 * <li>基于用户绑定方式校验-bind;</li>
	 * <li>基于密码比较验证(采用MD5,SHA等不可逆算法)-password-compare</li>
	 * @param ctx LDAP上下文
	 * @param attrs 返回的LDAP属性
	 * @param userDN 用户条目名
	 * @param password 密码
	 * @return LDAPAuthResult 对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static LDAPAuthResult authenticate(LdapContext ctx, Attributes attrs, String userDN,String password)throws Exception {

		LDAPAuthResult ldapAuthResult = new LDAPAuthResult();

		String authMethod = ABFConfigKey.LDAP_AUTH_METHOD.getConfigValue();

		if (authMethod.equals(AUTH_METHOD_BIND)) {
			try {
				Hashtable<String, String> env =(Hashtable<String, String>)ctx.getEnvironment();

				env.put(Context.SECURITY_PRINCIPAL, userDN);
				env.put(Context.SECURITY_CREDENTIALS, password);
				env.put(Context.REFERRAL,ABFConfigKey.LDAP_REFERRAL.getConfigValue());

				ctx = new InitialLdapContext(env, null);

				// Get LDAP bind results
				Control[] responseControls =  ctx.getResponseControls();

				ldapAuthResult.setAuthenticated(true);
				ldapAuthResult.setResponseControl(responseControls);
			}
			catch (Exception e) {
				LogUtil.logError("Failed to bind to the LDAP server with userDN "+ userDN + " and password " + password, e, new Object[0]);
				ldapAuthResult.setAuthenticated(false);
				ldapAuthResult.setErrorMessage(e.getMessage());
			}
		}
		else if (authMethod.equals(AUTH_METHOD_PASSWORD_COMPARE)) {
			Attribute userPassword = attrs.get("userPassword");

			if (userPassword != null) {
				String ldapPassword = new String((byte[])userPassword.get());

				String encryptedPassword = password;

				String algorithm = ABFConfigKey.LDAP_AUTH_PASSWORD_ENCRYPTION_ALGORITHM.getConfigValue();

				if (StringUtil.isNotNull(algorithm)) {
					encryptedPassword ="{" + algorithm + "}" +CryptoUtil.digestByMD5(password);
					//PwdEncryptor.encrypt(algorithm, password, ldapPassword);
				}

				if (ldapPassword.equals(encryptedPassword)) {
					ldapAuthResult.setAuthenticated(true);
				}else {
					ldapAuthResult.setAuthenticated(false);
					LogUtil.logInfo("Passwords do not match for userDN " + userDN, null, new Object[0]);
				}
			}
		}

		return ldapAuthResult;
	}

	public static void main(String[] args){

		//ZwsUcorZkCrsujLiL6T2vQ==

		String encrypt=CryptoUtil.encryptByDES("000000","sysadmin");//明文,key
		System.out.println("\nencrypt="+encrypt);
		System.out.println("\ndecrypt="+CryptoUtil.decryptByDES(encrypt, "sysadmin"));

	}



}
