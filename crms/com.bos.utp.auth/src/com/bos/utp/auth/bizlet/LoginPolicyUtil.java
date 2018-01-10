package com.bos.utp.auth.bizlet;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.bos.utp.tools.IPUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.utility.StringUtil;


import commonj.sdo.DataObject;
/**
 * 登录策略工具类
 * @author 刘良金
 * @date 2008-08-21 13:37:39
 */
/*
 * 修改历史
 * $Log: LoginPolicyUtil.java,v $
 * Revision 1.1  2010/12/09 06:27:20  caisy
 * 包重构
 *
 * Revision 1.8  2010/12/07 03:09:46  caisy
 * 配置读取方式修改
 *
 * Revision 1.7  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.6  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.5  2009/04/22 10:10:22  caisy
 * 注释
 *
 * Revision 1.4  2009/01/07 11:42:28  liuxiang
 * *** empty log message ***
 *
 * Revision 1.3  2009/01/07 07:21:23  liuxiang
 * *** empty log message ***
 *
 * Revision 1.2  2009/01/07 06:56:17  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/06 05:32:45  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 * 二期初始版本
 *
 * Revision 1.2  2008/11/28 04:04:38  wengzr
 * Added:增加系统信息工具类SystemInfo
 * Refactor:将Excel工具类从customize移入到utils
 *
 * Revision 1.1  2008/10/07 09:25:48  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/09/22 09:54:16  wengzr
 * Update:重构Utility相关表在数据集utility.datasetx
 *
 * Revision 1.1  2008/09/21 15:54:47  wengzr
 * Update:重构登录限制为登录策略
 *
 * Revision 1.1  2008/08/20 16:25:36  liulj
 * *** empty log message ***
 *
 * Revision 1.1  2008/08/20 16:25:14  liulj
 * 登陆限制的接口类提交 LoginLimitUtil
 *
 */
@Bizlet("")
public class LoginPolicyUtil {

	private static final String DEFAULT_DATASOURCE = "default";   //默认数据源

	public static final String POLICY_TYPE = "1";  //策略类型，1表示白名单限制，0表示黑名单限制

	public static final String ABF_YESORNO     = "1";  //是否启用，y表示启用，n表示不启用

	private static final String LOGIN_NAME_MATCH_STARTWITH = "3";  //前匹配

	private static final String LOGIN_NAME_MATCH_ENDWITH = "4";    //后匹配

	private static final String LOGIN_NAME_MATCH_INCLUDE = "5"; //中间匹配


	/**
	 * 系统用户登陆限制验证，主要验证用户登陆名称和登陆IP，
	 * @param userid 用户登陆名
	 * @param ip     用户登陆IP
	 * @return
	 * @author 刘良金
	 */
	@Bizlet(params = { @BizletParam(index = 0, paramAlias = "userID"),
			           @BizletParam(index = 1, paramAlias = "userIP")})
	public static boolean checkUserLoginPolicy(String userId, String userIP) {

		//创建登陆限制对象，根据使用范围和是否启用查询限制记录
		DataObject loginPolicy  = DataObjectUtil.createDataObject("com.bos.utp.dataset.business.AtLoginPolicy");

		loginPolicy.set("policytype", POLICY_TYPE);
		loginPolicy.set("isenabled", ABF_YESORNO);

		DataObject[] objs = DatabaseUtil.queryEntitiesByTemplate(DEFAULT_DATASOURCE, loginPolicy);
		if( objs != null && objs.length > 0) {
			String loginName = "";
			String loginNameMatchType = "";
			String loginIP = "";

			String startTime = "";
			String endTime = "";

			for( int i = 0; i < objs.length; i++ ) {
				DataObject dataObject = objs[i];

				loginName = (String)dataObject.get("lognamelimit");  //登陆名限制
				loginNameMatchType = (String)dataObject.get("lognamelmttype");  //登陆名限制类型
				loginIP = (String)dataObject.get("logiplimit");          //登陆IP限制
				startTime = (String)dataObject.get("starttimelimit");  //开始登陆时间
				endTime = (String)dataObject.get("endtimelimit");      //截止登陆时间

				//在配置列表中，只要有一种配置在三个条件都满足时返回true
				if( checkUserId(loginName, loginNameMatchType, userId )
						&& IPUtil.isMatchWildcard(loginIP, userIP)
					    && checkLoginTime(startTime, endTime) ){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 验证用户登陆名
	 * @param 登陆名限制
	 * @param 登陆名限制类型
	 * @param userId
	 * @合法返回true 不合法返回false
	 */
	private static boolean checkUserId(String loginName, String loginNameMatchType, String userId) {

		if(StringUtil.isNullOrBlank(loginName))
			return true;

		//匹配类型为前匹配
		if( LOGIN_NAME_MATCH_STARTWITH.equals( loginNameMatchType ) ) {
			if( userId.startsWith( loginName ) )
			    return true;
			else
				return false;
		}
        //匹配类型为中间匹配
		else if( LOGIN_NAME_MATCH_INCLUDE.equals( loginNameMatchType )  ) {
			if( userId.indexOf( loginName )  != -1 )
			    return true;
			else
				return false;
		}
        //匹配类型为后匹配
		else if( LOGIN_NAME_MATCH_ENDWITH.equals( loginNameMatchType )  ) {
			if( userId.endsWith( loginName ) )
			    return true;
			else
				return false;
		}

		return false;
	}
	/**
	 * 验证用户登陆时间
	 * @param 允许登陆开始时间
	 * @param 截止登陆时间
	 * @return 在允许时间内返回true，否则返回false
	 */
	private static boolean checkLoginTime(String startTime, String endTime) {
		if(StringUtil.isNullOrBlank(startTime)&&StringUtil.isNullOrBlank(endTime))
			return true;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date());

		if( str.compareTo(startTime)  > 0 && str.compareTo(endTime) < 0 ) {
			return true;
		}
		return false;
	}

}
