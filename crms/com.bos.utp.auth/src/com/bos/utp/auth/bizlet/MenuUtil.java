package com.bos.utp.auth.bizlet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.eos.common.connection.ConnectionHelper;
import com.eos.das.sql.INamedSqlSession;
import com.eos.das.sql.NamedSqlSessionFactory;
import com.eos.foundation.common.utils.StringUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * 用户工具类,提供与用户相关的常用方法。 如判断用户是否登录等
 * 
 * @author 蔡述尧
 * @date 2008-08-26 14:35:55
 */
/*
 * 修改历史 $Log: MenuUtil.java,v $
 * 修改历史 Revision 1.12  2010/12/09 10:17:07  caisy
 * 修改历史 菜单列表使用标准库排序
 * 修改历史 修改历史 Revision 1.11 2010/12/08 09:56:36 caisy
 * 修改历史 ONE-194 菜单url生成规则依赖应用的端口上下文配置 修改历史 修改历史 Revision 1.10 2010/12/08
 * 04:28:29 caisy 修改历史 菜单的url由功能action+paraminfo组成 修改历史 Revision 1.9 2010/12/01
 * 03:23:14 caisy 配置文件读取方式修改
 * 
 * Revision 1.8 2010/11/30 16:12:23 caisy 编码改为UTF-8
 * 
 * Revision 1.7 2009/10/19 13:36:12 caisy 菜单排序问题
 * 
 * Revision 1.6 2009/09/07 08:59:14 caisy 菜单排序问题
 * 
 * Revision 1.5 2009/09/04 08:18:07 caisy 修正了非叶子菜单有子节点时在同级菜单中排序不正确
 * 
 * Revision 1.4 2009/04/22 10:10:22 caisy 注释
 * 
 * Revision 1.3 2009/03/23 07:28:49 caisy Update:多层菜单无法显示
 * 
 * Revision 1.2 2009/01/05 11:45:55 liuxiang *** empty log message ***
 * 
 * Revision 1.1 2009/01/05 02:34:57 caisy 二期初始版本
 * 
 * Revision 1.1 2008/12/01 09:28:36 caisy Update:UserUtil重构为MenuUtil
 * 
 * Revision 1.2 2008/12/01 09:02:05 caisy Update:登陆后菜单显示按menulevel和displayorder
 * 
 * Revision 1.1 2008/10/07 09:25:49 wengzr *** empty log message ***
 * 
 * Revision 1.6 2008/09/17 12:54:45 wengzr Update:修改在标签和JSP中判断用户是否登录方式
 * 
 * Revision 1.5 2008/09/11 13:55:57 wengzr Update:修改登录实现方式和权限校验BUG
 * 
 * Revision 1.4 2008/09/11 08:16:24 caisy 登录
 * 
 * Revision 1.3 2008/09/09 16:45:08 caisy 登录
 * 
 * Revision 1.2 2008/08/28 17:25:24 caisy 首页菜单
 * 
 * Revision 1.1 2008/08/26 18:36:06 caisy 首页菜单
 * 
 */
@Bizlet("用户工具类")
public class MenuUtil {

	/**
	 * @param funclist
	 *            人员功能列表
	 * @return
	 */
	@Bizlet("根据功能列表获取应用列表")
	public static DataObject[] getAppList(DataObject[] funclist) {
		List<DataObject> applicationList = new ArrayList<DataObject>();
		BigDecimal tmpApp = null;
		if (funclist != null) {
			for (int i = 0; i < funclist.length; i++) {
				DataObject func = funclist[i];
				BigDecimal appid = func.getBigDecimal("APPID");
				if (!appid.equals(tmpApp)) {
					tmpApp = appid;
					DataObject acApplication = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcApplication");
					acApplication.setInt("appid", tmpApp.intValue());
					acApplication.setString("appname", func.getString("APPNAME"));
					applicationList.add(acApplication);
				}
			}
		}
		return (DataObject[]) applicationList.toArray(new DataObject[applicationList.size()]);

	}

	/**
	 * @param funclist
	 *            人员功能列表
	 * @return
	 */
	@Bizlet("根据功能列表获取功能组列表")
	public static DataObject[] getFunctionGroupList(DataObject[] funclist) {
		List<DataObject> functiongroupList = new ArrayList<DataObject>();
		BigDecimal tmpFuncGoup = null;
		if (funclist != null) {
			for (int i = 0; i < funclist.length; i++) {
				DataObject func = funclist[i];
				BigDecimal groupid = func.getBigDecimal("FUNCGROUPID");
				if (!groupid.equals(tmpFuncGoup)) {
					tmpFuncGoup = groupid;
					DataObject acFuncgroup = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFuncgroup");
					acFuncgroup.setInt("funcgroupid", tmpFuncGoup.intValue());
					acFuncgroup.setString("funcgroupname", func.getString("FUNCGRUPNAME"));
					functiongroupList.add(acFuncgroup);
				}
			}

		}
		return (DataObject[]) functiongroupList.toArray(new DataObject[functiongroupList.size()]);
	}

	@Bizlet("根据功能列出菜单列表")
	public static DataObject[] getMenus(DataObject[] menulist, DataObject[] menugrouplist) {
		       return sortMenus(menulist,menugrouplist);
	}
	private static DataObject[] sortMenus(DataObject[] menulist, DataObject[] menugrouplist) {
		List<DataObject> menus = new ArrayList<DataObject>();
		menus.addAll(Arrays.asList(menulist));
		HashSet<String> menugroups = new HashSet<String>();
		// 循环菜单取出菜单的序列号，按照"."拆分作为菜单组编号集合的初始值
	
		for (DataObject menu :menulist){
				String[] ids = menu.getString("menuseq").split("\\.");
				for (String id:ids) {					
						menugroups.add(id);
					}
		}
		for(DataObject group:menugrouplist){
			String groupid = group.getString("menuid");
			if (menugroups.contains(groupid)) {// 有此菜单组
				menus.add(group);
			}
		}

        // 按menulevel、displayorder排序
		Collections.sort(menus, new Comparator<DataObject>(){
			 public int compare(DataObject o1, DataObject o2) {
				 if(o1.getInt("menulevel")!=o2.getInt("menulevel")){
					 return o1.getInt("menulevel")-o2.getInt("menulevel");
				 }else if( o1.getInt("displayorder")!= o2.getInt("displayorder")){
					 return o1.getInt("displayorder")-o2.getInt("displayorder");
				 }else{
					 return 0;
				 }				
			}
		});
		return  menus.toArray(new DataObject[0]);
	}

	/**
	 * @param menulist
	 *            人员叶子菜单列表
	 * @param menugrouplist
	 *            所有菜单组列表
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Bizlet("整理菜单序列号层级关系")
	public static boolean updateMenuSeq() {
		boolean retflag = false;
		Connection conn = null;
		INamedSqlSession session = null;
		try {
			String update_level_1 = "com.bos.utp.auth.menuMaintain.update_level_1";
			String update_level_n = "com.bos.utp.auth.menuMaintain.update_level_n";
			String update_seq_1 = "com.bos.utp.auth.menuMaintain.update_seq_1";
			String update_seq_n = "com.bos.utp.auth.menuMaintain.update_seq_n";
			String select_menu = "com.bos.utp.auth.menuMaintain.select_menu";
			conn = ConnectionHelper.getCurrentContributionConnection("default");
			session = NamedSqlSessionFactory.createSQLMapSession(conn);
			HashMap param = new HashMap();
			int level = 1;
			param.put("menulevel", level);
			// 更新level,seq
			Integer ret = session.execute(update_level_1, null);
			ret = session.execute(update_seq_1, null);
			boolean next = ret.intValue() > 0;
			while (next) {
				next = false;
				param.put("menulevel", level);
				List list = session.queryForList(select_menu, param);
				if (list != null) {
					for (HashMap data : (List<HashMap>) list) {
						param.put("menuid", data.get("MENUID"));
						param.put("menuseq", data.get("MENUSEQ"));
						ret = session.execute(update_level_n, param);
						next = next || (ret.intValue() > 0);
						ret = session.execute(update_seq_n, param);
						next = next || (ret.intValue() > 0);
					}
				}
				level++;
			}
			retflag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retflag;
	}

	@Bizlet("功能url转为菜单url")
	public static String concatUrl(String action, String param) {
		if (StringUtil.isBlank(action)) {
			return null;
		} else {
			if (!StringUtil.isBlank(param)) {
				if (action.indexOf('?') < 0) {
					return action.concat("?").concat(param);
				} else {
					return action.concat("&amp;").concat(param);
				}
			} else {
				return action;
			}
		}
	}

	/**
	 * 把ACMenu对象中的action和param合并为url
	 * 
	 * @param list
	 * @param 存放url的属性名
	 * @return
	 */
	@Bizlet("功能url转为菜单url")
	public static void concatMenuUrlBatch(DataObject[] list, String urlProperty) {
		for (DataObject obj : list) {
			obj.setString(urlProperty, concatUrl(obj.getString("funcaction"), obj.getString("parainfo")));
		}
	}

}
