package com.bos.utp.auth.bizlet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.bos.pub.EntityLogUtil;
import com.bos.pub.GitUtil;
import com.bos.utp.ABFConfigKey;
import com.bos.utp.tools.DBUtil;
import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IUserObject;
import com.eos.data.datacontext.UserObject;
import com.eos.foundation.common.utils.CryptoUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.utility.StringUtil;

import commonj.sdo.DataObject;

/**
 * 
 * 登录相关的构件
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 
 * Revision 1.3 2009/03/30 05:39:38 caisy 代码规范
 * 
 * Revision 1.2 2009/01/07 06:52:46 liuxiang *** empty log message ***
 * 
 * Revision 1.1 2009/01/05 02:34:57 caisy 二期初始版本
 * 
 * Revision 1.4 2008/11/28 04:15:22 wengzr Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 * 
 * Revision 1.3 2008/11/24 13:43:43 wengzr
 * Added:增加验证码可配置,和验证码校验器VerifyCodeLoginValidator
 * 
 * Revision 1.2 2008/11/17 11:20:52 wengzr
 * refactor:将DataObjectExt移到com.bos.utp.tools构件包
 * 
 * Revision 1.1 2008/10/07 09:25:49 wengzr *** empty log message ***
 * 
 * Revision 1.4 2008/09/26 15:24:51 wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 * 
 * Revision 1.3 2008/09/17 16:40:48 wengzr Update:修改登录验证方式
 * 
 */
@Bizlet("登录运算逻辑")
public class LogonUtil {

	/**
	 * 获取加密后的密码<BR>
	 * 如果当前contribution.eosinfo配置了加密算法，则采用相应的加密算法进行加密，否则默认为明文
	 * 
	 * @param password
	 *            明文密码
	 * @return 加密后的密码
	 * @throws Exception
	 */
	@Bizlet(params = { @BizletParam(index = 0, paramAlias = "password") })
	public static String getPassword(String password) throws Exception {
		String algorithm = ABFConfigKey.LOGIN_PASSWORD_ENCRYPTION_ALGORITHM
				.getConfigValue();

		if (algorithm == null || algorithm.equals("")) {
			return password;
		}

		if ("MD5".equals(algorithm)) {
			return CryptoUtil.digestByMD5(password);
		} else if ("SHA".equals(algorithm)) {
			return CryptoUtil.digestBySHA(password);
		} else {
			throw new IllegalArgumentException("不支持该加密算法 " + algorithm);
		}
	}

	/**
	 * 判断是否为初始化登录
	 * 
	 * @param opt
	 *            登录信息
	 * @return
	 */
	@Bizlet("判断是否初始化登录")
	public boolean isInitLogin(String userid, String password) {
		try {

			// 请求参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);

			// 查询操作员表
			Object[] result = DatabaseExt.queryByNamedSql(null,
					"com.bos.utp.auth.permission.queryOperator", map);
			if (result != null && result.length > 0) {
				Map obj = (Map) result[0];
				String status = obj.get("STATUS") + "";
				if ("init".equals(status))
					return true;

				// // 最后更新密码时间
				// Date lastDate = (Date) obj.get("STARTDATE");
				// // 当前时间
				// Date curDate = new Date();
				// 计算间隔时间
				// long jj = DateUtil.interval2Date(lastDate,curDate);

				// 超过90天需要重新设置密码
				// if(jj > 90) return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return false;
	}

	/**
	 * @return
	 * @author 蔡述尧
	 */
	@Bizlet("是否已登录")
	public static boolean isLogon() {
		return DataContextManager.current().getSessionCtx().getUserObject() == null ? false
				: true;
	}

	private static final int USERID = 1;

	private static final int USERNAME = 2;

	private static final int OPERATORID = 3;

	private static final int MENUTYPE = 4;

	private static final int EMPID = 5;

	private static final int EMPNAME = 6;

	private static final int ORGID = 7;

	private static final int ORGNAME = 8;

	private static final int ORGTYPE = 9;

	private static final int ORGCODE = 10;

	private static final int ORGSEQ = 11;

	private static final int ORGLEVEL = 12;

	private static final String SQLBASEINFO = "SELECT U.USERID,U.OPERATORNAME,U.OPERATORID,U.MENUTYPE,E.EMPID,E.EMPNAME,O.ORGID,O.ORGNAME,O.ORGTYPE,O.ORGCODE,O.ORGSEQ,O.ORGLEVEL,U.PASSWORD,O.PARENTORGID,o.ORGDEGREE,o.LEG_ORG  FROM  AC_OPERATOR U LEFT OUTER JOIN OM_EMPLOYEE E ON U.OPERATORID = E.OPERATORID LEFT OUTER JOIN OM_ORGANIZATION O ON E.ORGID = O.ORGID WHERE U.USERID =? ";

	// private static final String SQLROLE = "SELECT ROLEID FROM AC_OPERATORROLE
	// WHERE OPERATORID=?";
	//
	// private static final String SQLORGROLE = "select roleid from ac_role a
	// where a.roletype like '2%' union select roleid from om_partyrole
	// r,(select orgid as partyid from om_emporg where empid = ?) o where
	// r.partyid = o.partyid and r.partytype = 'organization'";

	@Bizlet("签到")
	public static void insertLoginLog() {
		EntityLogUtil.logTrade("01", "", "签到");
	}

	/**
	 * 
	 * 构造userObject
	 * 
	 * @param userid
	 * @param password
	 * @return 构造用户的信息
	 */
	@Bizlet("登录系统，并返回用户对象")
	public static UserObject initUserObject(DataObject acOperator) {
		UserObject uo = new UserObject();
		String userid = acOperator.getString("userid");
		String ipaddress = acOperator.getString("ipaddress");
		HashSet<String> roleSet = new HashSet<String>();
		Connection conn = DBUtil.getConnection();

		HashMap<String, Object> attributes = new HashMap<String, Object>();
		ArrayList<DataObject> roleList = new ArrayList<DataObject>();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Statement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			// 查询人员基本信息
			ps = conn.prepareStatement(SQLBASEINFO);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 存在操作员
				String empid = rs.getString(EMPID);
				// int operatorid = rs.getInt(OPERATORID);
				uo.setUserId(rs.getString(USERID));
				uo.setUserName(rs.getString(USERNAME));
				uo.setUserRealName(rs.getString(EMPNAME));
				uo.setUserOrgId(rs.getString(ORGID));
				uo.setUserOrgName(rs.getString(ORGNAME));
				uo.setUserRemoteIP(ipaddress);
				
				String skin = rs.getString(MENUTYPE);
				// 设置 layout和style的默认值
				/***************************************************************
				 * attributes.put(ABFConfigKey.SKIN_LAYOUTATTR.getConfigValue(),
				 * ABFConfigKey.SKIN_DEFAULT_LAYOUT.getConfigValue());
				 * attributes.put(ABFConfigKey.SKIN_STYLEATTR.getConfigValue(),
				 * ABFConfigKey.SKIN_DEFAULT_STYLE.getConfigValue());
				 **************************************************************/
				if (StringUtil.isNotNullAndBlank(skin)) {
					String[] tmp = skin.split(",");
					if (tmp.length > 0) {
						attributes.put("layout", tmp[0]);
					} else {
						attributes.put("layout", "default");
					}
					if (tmp.length > 1) {
						attributes.put("style", tmp[1]);
					} else {
						attributes.put("style", "default");
					}
				} else {
					attributes.put("layout", "default");
					attributes.put("style", "default");
				}

				attributes.put("orgseq", rs.getString(ORGSEQ));
				attributes.put("password", rs.getString("PASSWORD"));// 给BPS流程使用
				attributes.put("orgcode", rs.getString(ORGCODE));
				attributes.put("orglevel", rs.getString(ORGLEVEL));
				attributes.put("orgtype", rs.getString(ORGTYPE));
				attributes.put("orgdegree", rs.getString("ORGDEGREE"));//获取机构类型：1普通机构，2小贷中心机构
				attributes.put("legorg", rs.getString("LEG_ORG"));//获取机构类型：1普通机构，2小贷中心机构
				attributes.put("empid", empid);
				attributes.put("operatorid", rs.getString(OPERATORID));
				String parentorgid = rs.getString("PARENTORGID");
				if("sysadmin".equals(rs.getString(USERID))){
					//是否超级管理员（用于产品树显示处，“空”表示为超级管理员，1表示不是超级管理员）
					attributes.put("superadmin", null);
				}else{
					attributes.put("superadmin", "1");
				}
				
				if(rs.getString(USERID).indexOf("admin") > -1){
					//是否系统管理员（用于角色权限配置处）
					attributes.put("isSysAdmin", true);
				}else{
					attributes.put("isSysAdmin", false);
				}
				
				rs.close();
				
//				ResultSet rso = null;
//				String ddd = (String) attributes.get("operatorid");
//				String userOpersql = "select DYNAMICSWITCH from AC_OPERATOR where OPERATORID = "+ddd;
//				rso = conn.createStatement().executeQuery(userOpersql);
//				while (rso.next()) {
//					String orgid = rso.getString("DYNAMICSWITCH");
//					attributes.put("dynamicswitch", orgid);//动态登录开关 1打开 0
//				}
//				rso.close();
				
				// String sql = "select distinct t.orgid, o.orgname from
				// AC_OPERATORROLE t "
				// + " JOIN OM_ORGANIZATION O ON O.ORGID=T.ORGID where
				// t.operatorid="
				// + attributes.get("operatorid");
				String sql = "select * from ("
						+ "select distinct t.orgid, o.orgname,t2.posicode,t2.posiname from OM_EMPPOSITION t "
						+ " JOIN OM_ORGANIZATION O ON O.ORGID=T.ORGID JOIN OM_POSITION T2 ON T2.POSITIONID=T.POSITIONID where t.empid="
						+ empid + " and o.status='01'" 
						+ "union select orgid,orgname,'','' from om_organization where status='01' and orgid in (select orgid from om_emporg where empid=" + empid + ")) " 
						+ "order by orgid";
				rs = conn.createStatement().executeQuery(sql);
				List<String> ids = new ArrayList<String>();
				List<String> names = new ArrayList<String>();
				Map<String, Map<String, String>> orgposimap = new HashMap<String, Map<String, String>>(); // key为orgid，value为岗位的Map
				while (rs.next()) {
					String orgid = rs.getString(1);
					Map<String, String> posi = orgposimap.get("org"+orgid);
					if (null == posi) {
						ids.add(orgid);
						names.add(rs.getString(2));
						posi = new HashMap<String, String>();
						orgposimap.put("org"+orgid, posi);
						posi.put("orgname", rs.getString("ORGNAME"));
					}
					
					String posiCode = rs.getString("POSICODE");
					if (posiCode != null && posiCode.length() > 0) {
						posi.put(rs.getString("POSICODE"), rs.getString("POSINAME"));
					}
				}

				attributes.put("orgposimap", orgposimap);
				attributes.put("orgposimapSize", orgposimap.size());
				if ("1".equals("2") // 一个岗位时也要选择
						&& (ids.size() == 1 && ids.get(0).equals(
								uo.getUserOrgId()))) {
					// 只有一个岗位
					Map<String, String> map = orgposimap.values().iterator()
							.next();
					attributes.put("posicode", map.keySet().iterator().next());
					attributes.put("posiname", map.values().iterator().next());

					String iseosadmin = "0";
					String isadmin = "0";
					// 查询角色列表
					if (StringUtil.isNotNullAndBlank(empid)) {
						// 关联员工，查询员工关联机构的角色列表
						ps2 = conn.createStatement();
						// 设置参数
						rs2 = ps2
								.executeQuery("select distinct t.roleid, R.rolename from AC_OPERATORROLE t "
										+ " JOIN AC_ROLE R ON R.ROLEID=T.ROLEID  " +
												"left join OM_PARTYROLE p " +
												"  on p.roleid=t.ROLEID " +
												"where t.operatorid="
										+ attributes.get("operatorid")
										+ " and p.partyid=" + uo.getUserOrgId());

						while (rs2.next()) {
							String id = rs2.getString("roleid");
							if ("eosadmin".equals(id)) {
								iseosadmin = "1";
							}
							if ("orgadmin".equals(id)) {
								isadmin = "1";
							}
							
							attributes.put("iseosadmin", iseosadmin.equals("1") ? true : false);
							attributes.put("isadmin", isadmin.equals("1") ? true : false);
							if (!roleSet.contains(id)) {
								DataObject role = DataObjectUtil
										.createDataObject("commonj.sdo.DataObject");
								role.setString("roleid", rs2.getString(1));
								role.setString("rolename", rs2.getString(2));
								roleSet.add(id);
								roleList.add(role);
							}
						}

						if (null != parentorgid) {
							// 有上级机构
							rs.close();
							ps2.close();
							ps2 = conn.createStatement();
							rs = ps2
									.executeQuery("with parentOrg(parentorgid,orgid,orgcode,orgname,orglevel) as "
											+ " (select parentorgid, orgid,orgcode,orgname,orglevel"
											+ "    from om_organization"
											+ "   where orgid = "
											+ uo.getUserOrgId()
											+ "  union all"
											+ "  select o.parentorgid, o.orgid,o.orgcode,o.orgname,o.orglevel"
											+ "    from parentOrg p, om_organization o"
											+ "   where p.parentorgid = o.orgid)"
											+ "select * from parentOrg order by orglevel desc ");
							StringBuilder sbWoQuote = new StringBuilder();
							StringBuilder sbWoQuote2 = new StringBuilder();
							StringBuilder sbWoQuote3 = new StringBuilder();
							StringBuilder sbWoQuote4 = new StringBuilder();
							while (rs.next()) {
								if (sbWoQuote.length() > 0) {
									sbWoQuote.append(",");
									sbWoQuote2.append(",");
									sbWoQuote3.append(",");
									sbWoQuote4.append(",");
								}
								sbWoQuote.append(rs.getInt("ORGID"));
								sbWoQuote2.append(rs.getString("ORGCODE"));
								sbWoQuote3.append(rs.getString("ORGNAME"));
								sbWoQuote4.append(rs.getString("ORGLEVEL"));
								
							}
							// 此处所指的上级机构中包含当前机构及上级机构
							attributes
									.put("parentorgids", sbWoQuote.toString());
							attributes.put("parentorgcodes", sbWoQuote2
									.toString());
							attributes.put("parentorgnames", sbWoQuote3
									.toString());
							attributes.put("parentorglevels", sbWoQuote4
									.toString());
						} else {
							// 此处所指的上级机构中包含当前机构
							attributes.put("parentorgids", String.valueOf(uo
									.getUserOrgId()));
							attributes.put("parentorgcodes", attributes
									.get("orgcode"));
							attributes.put("parentorgnames", uo
									.getUserOrgName());
							attributes.put("parentorglevels", attributes
									.get("orglevel"));
						}
					}
				} else {
					uo.setUserOrgId(StringUtils.join(ids.iterator(), ","));
					uo.setUserOrgName(StringUtils.join(names.iterator(), ","));
				}
				// 设置角色到用户对象中
				attributes.put("roles", roleList.toArray(new DataObject[roleList.size()]));
				uo.setAttributes(attributes);
				// 更新登录时间
				HashMap<String, Object> sqlParams = new HashMap<String, Object>();
				sqlParams.put("operatorid", attributes.get("operatorid"));

				DatabaseExt.executeNamedSql(null,
						"com.bos.utp.auth.frame.update_operator_loginin",
						sqlParams);
			} else {
				// 无此操作员
				uo = null;
			}
		} catch (Exception e) {
			uo = null;
			LogUtil.logError("登录出错！", e, (Object) null);
		} finally {
			DBUtil.closeAll(conn, new Statement[] { ps, ps1, ps2 },
					new ResultSet[] { rs, rs1, rs2 });
		}

		return uo;
	}
	
	public static final String USER_ID = "userId";
	public static final String USER_ORG_ID = "userOrgId";
	
	public static final String REPORT_ORG_ID = "reportOrgId";

	@SuppressWarnings("unchecked")
	@Bizlet("登录系统后处理选机构")
	public static UserObject selectOrg(UserObject uo, String orgid) {
		String iseosadmin = "0";
		String isadmin = "0";
		Map attributes = uo.getAttributes();
		String[] ids = uo.getUserOrgId().split(",");
		String[] names = uo.getUserOrgName().split(",");
		if (uo.getUserOrgId().contains(",") == false) {
			Map<String, Map<String, String>> orgposimap = (Map<String, Map<String, String>>)attributes.get("orgposimap");
			if (null != orgposimap && orgposimap.size() > 0) {
				ids = new String[orgposimap.size()];
				names = new String[orgposimap.size()];
				String[] set = orgposimap.keySet().toArray(new String[orgposimap.size()]);
				for (int i=0; i<orgposimap.size(); i++) {
					String key = set[i];
					Map<String, String> map = orgposimap.get(key);
					ids[i] = key.replace("org", "");
					names[i] = map.get("orgname");
				}
			}
		}
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(orgid)) {
				uo.setUserOrgId(ids[i]);
				uo.setUserOrgName(names[i]);
				GitUtil.getSession().setAttribute(USER_ID, uo.getUserId());
				GitUtil.getSession().setAttribute(USER_ORG_ID, uo.getUserOrgId());
				Connection conn = DBUtil.getConnection();
				Statement st = null;
				ResultSet rs = null;
				try {
					st = conn.createStatement();
					rs = st
							.executeQuery("select distinct t.roleid, R.rolename from AC_OPERATORROLE t "
									+ " JOIN AC_ROLE R ON R.ROLEID=T.ROLEID " +
											" left join OM_PARTYROLE p " +
											"  on p.roleid=t.ROLEID " +
											"where t.operatorid="
									+ attributes.get("operatorid")
									+ " and p.partyid=" + orgid +"and t.orgid="+orgid);
					ArrayList<DataObject> roleList = new ArrayList<DataObject>();
					while (rs.next()) {
						String id = rs.getString("roleid");
						if ("eosadmin".equals(id)) {
							iseosadmin = "1";
						}
						if ("orgadmin".equals(id)) {
							isadmin = "1";
						}
						DataObject role = DataObjectUtil
								.createDataObject("commonj.sdo.DataObject");
						role.setString("roleid", rs.getString(1));
						role.setString("rolename", rs.getString(2));
						roleList.add(role);
					}
					attributes.put("iseosadmin", iseosadmin.equals("1") ? true : false);
					attributes.put("isadmin", isadmin.equals("1") ? true : false);
					attributes.put("roles", roleList
							.toArray(new DataObject[roleList.size()]));
					// 用于切换岗位，所以暂不移除
					// attributes.remove("orgposimap");
					// attributes.remove("orgposimapSize");

					rs.close();
					rs = st
							.executeQuery("select orgcode,orgseq,orglevel,orgtype,parentorgid,buno from OM_ORGANIZATION where orgid="
									+ orgid);
					String parentorgid = null;
					if (rs.next()) {
						attributes.put("orgseq", rs.getString(2));
						attributes.put("orgcode", rs.getString(1));
						attributes.put("orglevel", rs.getString(3));
						attributes.put("orgtype", rs.getString(4));
						parentorgid = rs.getString(5);
					} else {
						LogUtil.logError("所选机构未查询到！", null);
					}
					if (null != parentorgid) {
						// 有上级机构
						rs.close();
						rs = st
								.executeQuery("with parentOrg(parentorgid,orgid,orgcode,orgname,orglevel,buno) as "
										+ " (select parentorgid, orgid,orgcode,orgname,orglevel,buno"
										+ "    from om_organization"
										+ "   where orgid = "
										+ orgid
										+ "  union all"
										+ "  select o.parentorgid, o.orgid,o.orgcode,o.orgname,o.orglevel,o.buno"
										+ "    from parentOrg p, om_organization o"
										+ "   where p.parentorgid = o.orgid)"
										+ "select * from parentOrg order by orglevel desc ");
						StringBuilder sbWoQuote = new StringBuilder();
						StringBuilder sbWoQuote2 = new StringBuilder();
						StringBuilder sbWoQuote3 = new StringBuilder();
						StringBuilder sbWoQuote4 = new StringBuilder();
						String reportOrgID = "";
						while (rs.next()) {
							if (sbWoQuote.length() > 0) {
								sbWoQuote.append(",");
								sbWoQuote2.append(",");
								sbWoQuote3.append(",");
								sbWoQuote4.append(",");
							}
							sbWoQuote.append(rs.getInt("ORGID"));
							sbWoQuote2.append(rs.getString("ORGCODE"));
							sbWoQuote3.append(rs.getString("ORGNAME"));
							sbWoQuote4.append(rs.getString("ORGLEVEL"));
							
							String buno = rs.getString("BUNO");
							if (buno != null && "0".equals(buno)) {
								reportOrgID = "".equals(reportOrgID) ? String.valueOf(rs.getInt("ORGID")) : reportOrgID;
							}
							
						}
						// 此处所指的上级机构中包含当前机构及上级机构
						attributes.put("parentorgids", sbWoQuote.toString());
						attributes.put("parentorgcodes", sbWoQuote2.toString());
						attributes.put("parentorgnames", sbWoQuote3.toString());
						attributes.put("parentorglevels", sbWoQuote4.toString());
						
						attributes.put(REPORT_ORG_ID, reportOrgID);
						GitUtil.getSession().setAttribute(REPORT_ORG_ID, reportOrgID);
					} else {
						// 此处所指的上级机构中包含当前机构
						attributes.put("parentorgids", String.valueOf(orgid));
						attributes.put("parentorgcodes", attributes.get("orgcode"));
						attributes.put("parentorgnames", uo.getUserOrgName());
						attributes.put("parentorglevels",attributes.get("orglevel"));
						
						attributes.put(REPORT_ORG_ID, String.valueOf(orgid));
						GitUtil.getSession().setAttribute(REPORT_ORG_ID, String.valueOf(orgid));
					}
				} catch (Exception e) {
					LogUtil.logError("选择机构出错！", e, (Object) null);
				} finally {
					DBUtil.closeAll(conn, new Statement[] { st },
							new ResultSet[] { rs });
				}
				break;
			}
		}

		return uo;
	}
	/**
	 * 是否使用验证码
	 * 
	 * @return 使用返回true
	 */
	public static boolean useVerifyCode() {
		return ABFConfigKey.LOGIN_USE_VERIFY_CODE.getBLConfigValue();
	}

	/**
	 * 是否使用多语言选项
	 * 
	 * @return 使用返回true
	 */
	public static boolean useChooseLanguange() {
		return ABFConfigKey.USE_LANGUAGE_CHOOSE.getBLConfigValue();
	}

	/**
	 * 获取配置文件中的默认登录语言，如果未配置则返回zh_CN
	 * 
	 * @return 语言信息 如zh_CN en_US等
	 */
	public static String getDefaultLanguange() {
		return ABFConfigKey.USE_DEFAULT_LANGUAGE.getConfigValue("zh_CN");
	}

	/**
	 * 判断session中的userObject是否存在
	 * 
	 * @param request
	 * @return userObject不为空 返回true
	 */
	public static boolean isLogon(HttpServletRequest request) {
		IUserObject userObject = (IUserObject) request.getSession()
				.getAttribute(IUserObject.KEY_IN_CONTEXT);
		return userObject == null ? false : true;
	}

	/**
	 * @return
	 * @author 蔡述尧
	 */
	@Bizlet("是否使用语言")
	public static void setLocale(String lang) {
		DataContextManager.current().setCurrentLocale(new Locale(lang));
	}
	/**
	 * @return
	 * @author  
	 */
	@Bizlet("获取ip")
	public static String ipManage () {
		String ipAd="";
    
		ipAd=(String) MDC.get("req.remoteAddr");
		System.out.println("本机的ip=" + ipAd);
		return ipAd;
	}
	/**
	 * @return
	 * @author  
	 */
	@Bizlet("90天后的日期")
	public Date getAfterDate(Date basicDate, int n) {
		long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		basicDate.setTime(nDay);
		return basicDate;
	}
}
