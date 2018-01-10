package com.bos.utp.auth.bizlet;

import java.util.ArrayList;
import java.util.List;

import com.bos.utp.ABFConfigKey;

import com.bos.utp.auth.cache.FunctionCacheManager;
import com.bos.utp.auth.cache.PortalResourceCacheManager;
import com.bos.utp.auth.permission.PermissionChecker;
//import com.bos.utp.auth.permission.PermissionCheckerFactory;
import com.eos.access.authorization.CheckedResult;
import com.eos.access.authorization.IAccessedResource;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.runtime.metadata.IBizMetaData;
import com.eos.runtime.metadata.IContributionMetaData;
import com.eos.runtime.metadata.IPageFlowActionMetaData;
import com.eos.runtime.metadata.IPageFlowMetaData;
import com.eos.runtime.metadata.MetaDataHelper;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import commonj.sdo.DataObject;

/**
 * 功能权限工具类，用于刷新功能树
 * 
 * @author 蔡述尧
 * @date 2009-04-14 16:57:50
 */
/*
 * 修改历史 $Log: PermissionUtil.java,v $
 * 修改历史 Revision 1.10  2010/12/09 06:29:17  caisy
 * 修改历史 重构haspermission返回值
 * 修改历史 
 */
@Bizlet("功能权限工具类")
public class PermissionUtil {
	public static final String ACTION_PREFIX = "?_eosFlowAction=";

	public static final String FUNC_TYPE_FLOW = "0";

	public static final String FUNC_TYPE_BIZ = "1";

	public static final String FUNC_TYPE_OTHER = "3";

	private static String[] TYPES = { "unknown", "jsp", "flow", "biz", "service" };

	private static String[][] SUFFIXS = { { ".jsp" }, { ".flow", ".flowx", ".action", ".flow.ajax", ".flowx.ajax" }, { "biz.ajax", ".bizx.ajax", ".biz", "bizx”,“.biz.ext" },
			{ "remote", ".service.ajax" } };

	/**
	 * @param contributionPattern
	 *            为null表示当前应用
	 * 
	 */
	@Bizlet(value = "测试", params = { @BizletParam(index = 0, paramAlias = "构件包名称模板") })
	public static void retrieveFunction(String contributionPattern) {
		int appid = 101;
		List<DataObject> funcgroups = new ArrayList<DataObject>();
		List<DataObject> funcs = new ArrayList<DataObject>();

		// 获取当前应用的所有构件包
		IContributionMetaData[] contributions = MetaDataHelper.getContributionMetaDatas(null);

		for (IContributionMetaData contribution : contributions) {
			String conname = contribution.getName();
			if (conname.matches(contributionPattern)) {
				DataObject funcgroup = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFuncgroup");
				DatabaseExt.getPrimaryKey(funcgroup);
				funcgroup.set("acApplication/appid", appid);
				funcgroup.set("funcgroupname", contribution.getDisplayName());
				funcgroup.set("funcgroupseq", "." + funcgroup.get("funcgroupid") + ".");
				funcgroup.set("grouplevel", "1");
				funcgroup.set("isleaf", "2");
				funcgroups.add(funcgroup);
				IPageFlowMetaData[] pageflows = contribution.getPageFlowMetaDatas();
				// 获取页面流列表
				for (IPageFlowMetaData pageflow : pageflows) {
					IPageFlowActionMetaData[] actions = pageflow.getActionMetaDatas();
					String pageflowname = getFullName(pageflow);
					for (IPageFlowActionMetaData action : actions) {
						String actionName = action.getName();
						DataObject funcObject = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFunction");
						funcObject.set("funccode", pageflowname + "-" + actionName);
						funcObject.set("funcaction", getFlowAction(pageflow, action));
						funcObject.set("funcdesc", pageflowname + "-" + actionName);
						funcObject.set("acFuncgroup/funcgroupid", funcgroup.get("funcgroupid"));
						funcObject.set("funcname", getFlowAction(pageflow, action));
						funcObject.set("functype", FUNC_TYPE_FLOW);
						funcObject.set("ismenu", "1");
						funcs.add(funcObject);
					}

				}
				// 获取逻辑流列表
				IBizMetaData[] bizs = contribution.getBizMetaDatas();
				for (IBizMetaData biz : bizs) {
					String bizName = getFullName(biz);
					DataObject funcObject = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFunction");
					funcObject.set("funccode", getBizName(biz));
					funcObject.set("funcaction", bizName);
					funcObject.set("funcdesc", bizName);
					funcObject.set("acFuncgroup/funcgroupid", funcgroup.get("funcgroupid"));
					funcObject.set("funcname", bizName);
					funcObject.set("functype", FUNC_TYPE_BIZ);
					funcObject.set("ismenu", "2");
					funcs.add(funcObject);
				}

			}
		}
		DatabaseUtil.saveEntities("default", funcgroups.toArray(new DataObject[funcgroups.size()]));
		DatabaseUtil.saveEntities("default", funcs.toArray(new DataObject[funcs.size()]));
	}

	private static String getFullName(IBizMetaData biz) {
		String name = biz.getName();
		return name.replaceAll("/", "\\.");
	}

	private static String getFullName(IPageFlowMetaData flow) {
		String name = flow.getName();
		return name.replaceAll("/", "\\.");
	}

	private static String getFlowAction(IPageFlowMetaData flow, IPageFlowActionMetaData act) {
		return getFullName(flow) + ACTION_PREFIX + act.getName();
	}

	private static String getBizName(IBizMetaData biz) {
		return getFullName(biz);
		// String[] tmp = getFullName(biz).split("\\.");
		// return tmp[tmp.length - 4] + "." + tmp[tmp.length - 3] + "." +
		// tmp[tmp.length - 2];
	}

	/**
	 * @param connames
	 *            构件包名
	 * @throws Exception
	 */
	@Bizlet("导入构件包")
	public static void importFunctions(int appId, String[] connames) throws Exception {
		List<DataObject> funcgroups = new ArrayList<DataObject>();
		List<DataObject> funcs = new ArrayList<DataObject>();
		List<String> names = new ArrayList<String>();
		for (String name : connames) {
			names.add(name);
		}
		// 获取当前应用的所有构件包
		IContributionMetaData[] contributions = MetaDataHelper.getContributionMetaDatas(null);

		for (IContributionMetaData contribution : contributions) {
			String conname = contribution.getName();
			if (names.contains(conname)) {
				// 构件包作为顶级功能组加入
				DataObject funcgroup = addFuncGroup(funcgroups, appId, contribution);
				IPageFlowMetaData[] pageflows = contribution.getPageFlowMetaDatas();
				// 获取页面流列表
				for (IPageFlowMetaData pageflow : pageflows) {
					// 每一个页面流作为一个功能组
					DataObject subfuncgroup = addFuncGroup(funcgroups, funcgroup, pageflow);
					// 把页面流下的action作为功能加入
					addFunc(funcs, subfuncgroup, pageflow);
				}
				// 获取逻辑流列表
				IBizMetaData[] bizs = contribution.getBizMetaDatas();
				for (IBizMetaData biz : bizs) {
					// 每一个逻辑流作为一个功能加入
					addFunc(funcs, funcgroup, conname, biz);
				}
			}
		}
		DatabaseUtil.saveEntities("default", funcgroups.toArray(new DataObject[funcgroups.size()]));
		DatabaseUtil.saveEntities("default", funcs.toArray(new DataObject[funcs.size()]));
	}

	private static void addFunc(List<DataObject> funcs, DataObject group, IPageFlowMetaData pageflow, IPageFlowActionMetaData action) throws Exception {
		String actionName = getFullName(pageflow) + "-" + action.getName();
		DataObject funcObject = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFunction");
		funcObject.set("funccode", "Func_Flow_" + String.valueOf(DatabaseExt.getNextSequence("AcFunction.funccode")));
		funcObject.set("funcaction", getFlowAction(pageflow, action));
		funcObject.set("funcdesc", getFlowAction(pageflow, action));
		funcObject.set("acFuncgroup/funcgroupid", group.get("funcgroupid"));
		funcObject.set("funcname", actionName);
		funcObject.set("functype", FUNC_TYPE_FLOW);
		funcObject.set("ismenu", "1");
		funcs.add(funcObject);
	}

	private static void addFunc(List<DataObject> funcs, DataObject group, String conname, IBizMetaData biz) throws Exception {
		String bizName = getFullName(biz);
		DataObject funcObject = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFunction");
		funcObject.set("funccode", "Func_Biz_" + String.valueOf(DatabaseExt.getNextSequence("AcFunction.funccode")));
		funcObject.set("funcaction", bizName);
		funcObject.set("funcdesc", bizName);
		funcObject.set("acFuncgroup/funcgroupid", group.get("funcgroupid"));
		funcObject.set("funcname", bizName);
		funcObject.set("functype", FUNC_TYPE_BIZ);
		funcObject.set("ismenu", "2");
		funcs.add(funcObject);
	}

	private static void addFunc(List<DataObject> funcs, DataObject group, IPageFlowMetaData pageflow) throws Exception {
		DataObject mainfuncObject = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFunction");
		mainfuncObject.set("funccode", "Func_Flow_" + String.valueOf(DatabaseExt.getNextSequence("AcFunction.funccode")));
		mainfuncObject.set("funcaction", getFullName(pageflow));
		mainfuncObject.set("funcdesc", getFullName(pageflow));
		mainfuncObject.set("acFuncgroup/funcgroupid", group.get("funcgroupid"));
		mainfuncObject.set("funcname", getFullName(pageflow));
		mainfuncObject.set("functype", FUNC_TYPE_FLOW);
		mainfuncObject.set("ismenu", "1");
		funcs.add(mainfuncObject);
		IPageFlowActionMetaData[] actions = pageflow.getActionMetaDatas();
		for (IPageFlowActionMetaData action : actions) {
			addFunc(funcs, group, pageflow, action);
		}
	}

	private static DataObject addFuncGroup(List<DataObject> funcgroups, DataObject group, IPageFlowMetaData pageflow) {
		DataObject subfuncgroup = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFuncgroup");
		DatabaseExt.getPrimaryKey(subfuncgroup);
		subfuncgroup.set("acApplication/appid", group.get("acApplication/appid"));
		subfuncgroup.set("funcgroupname", "group_" + subfuncgroup.getString("funcgroupid"));
		subfuncgroup.set("acFuncgroup/funcgroupid", group.get("funcgroupid"));
		subfuncgroup.set("funcgroupseq", group.getString("funcgroupseq") + subfuncgroup.getString("funcgroupid") + ".");
		subfuncgroup.set("grouplevel", String.valueOf(group.getInt("grouplevel") + 1));
		subfuncgroup.set("isleaf", "2");
		funcgroups.add(subfuncgroup);
		return subfuncgroup;
	}

	private static DataObject addFuncGroup(List<DataObject> funcgroups, int appId, IContributionMetaData contribution) {
		DataObject funcgroup = DataObjectUtil.createDataObject("com.bos.utp.dataset.privilege.AcFuncgroup");
		DatabaseExt.getPrimaryKey(funcgroup);
		funcgroup.set("acApplication/appid", appId);
		funcgroup.set("funcgroupname", contribution.getDisplayName());
		funcgroup.set("funcgroupseq", "." + funcgroup.get("funcgroupid") + ".");
		funcgroup.set("grouplevel", "1");
		funcgroup.set("isleaf", "2");
		funcgroups.add(funcgroup);
		return funcgroup;
	}

	public static String getLastName(String name) {
		String[] tmp = name.split("\\.");
		int len = tmp.length;
		return tmp[len - 2];
	}

	/**
	 * 判断用户是否有权限访问功能
	 * 
	 * @param url
	 *            功能或者资源的action
	 * @param userObject
	 *            用户信息，不能为空
	 * @return
	 */
	public static CheckedResult hasPermission(IAccessedResource accessedResource, IUserObject userObject) throws Exception {
		return hasPermission(accessedResource.getResourceURI(), userObject);
	}

	/**
	 * <pre>
	 *          判断用户是否有权限访问功能
	 *          不进行权限校验的情况，有如下几种：
	 *          1、为配置中的免检用户如sysadmin
	 *          2、当前所调用的资源为portal资源(的portal资源管理)
	 * </pre>
	 * 
	 * @param url
	 *            功能或者资源的action
	 * @param userObject
	 *            用户信息，不能为空
	 * @return
	 */
	public static CheckedResult hasPermission(String uri, IUserObject userObject) {
		boolean haspermission = false;
		if (uri != null) {
			// uri,packagename,name,params,type
			String[] info = getContributionName(uri);

			// 不进行权限校验的情况，有如下几种：
			// 
			// 用户名为sysadmin账号
			// 注册在功能表中的功能需要做一下验证
			// 当前构件包下的资源不在需要权限校验的范围-
			// 是默认提供的内置资源
			// 是portal资源
			// 调试版
			// haspermission = isAdminUser(userObject);
			// LogUtil.logDebug("权限检验:isAdminUser={0}", null, new Object[] {
			// haspermission });
			// haspermission = haspermission ||
			// isUnCheckedContributions(info[1], info[0]);
			// LogUtil.logDebug("权限检验:isUnCheckedContributions={0}", null, new
			// Object[] { haspermission });
			// haspermission = haspermission ||
			// isUncheckedPermssionResource(info[0]);
			// LogUtil.logDebug("权限检验:isUncheckedPermssionResource={0}", null,
			// new Object[] { haspermission });
			// haspermission = isPortalResource(info[0]);
			// LogUtil.logDebug("权限检验:isPortalResource={0}", null, new Object[]
			// { haspermission });
			// 运行版
			haspermission = isAdminUser(userObject) || isUnCheckedContributions(info[1], info[0]) || isUncheckedPermssionResource(info[0]) || isPortalResource(info[0]);
			if (!haspermission) {
				// session超时或未登录，返回登录页面
				if (userObject == null) {
					LogUtil.logDebug("权限检验:userObject==null", null, (Object) null);
					return CheckedResult.FORWARDLOGIN;
				}
				try {
					// 是否已注册功能
					/**if (isRegistedFunction(info[0])) {
						try {
							PermissionChecker checker = PermissionCheckerFactory.create(userObject, true);
							// 判断请求是否为已授权功能
							haspermission = checker.hasAccessPermission(info[0], false);
							// 判断请求是否为已授权功能的资源，如果是已授权功能则通过逻辑短路跳过资源判断
							haspermission = haspermission || checker.hasAccessPermission(info[0], true);
						} catch (Exception e) {
							e.printStackTrace();
							haspermission = false;
						}
					} else {
						haspermission = ABFConfigKey.PERMISSION_UNREGIST_ACCESS.getBLConfigValue();
					}**/
				} catch (Exception e) {
					LogUtil.logError("判断权限出现异常:url={0}", e, new Object[] { uri });
					haspermission = false;
				}
			}
		}
		return haspermission ? CheckedResult.THREAD_ACCESSED_PASS : CheckedResult.REJECT;
	}

	/**
	 * 判断是否已登记的功能
	 * 
	 * @param url
	 * @return 功能已登记=true
	 */
	public static boolean isRegistedFunction(String url) {
		try {
			Object obj = FunctionCacheManager.getCache().get(url);
			return obj != null;
		} catch (Exception e) {
			LogUtil.logError("获取功能缓存时出现异常", e, (Object) null);
			return false;
		}
	}

	/**
	 * 是否允许校验指定构件包的资源
	 * 
	 * @param ContributionMetaData
	 *            当前构件包元数据，只有进入页面流引擎才会生效,在发起请求进入流程之前获取到的ContributionMetaData为null
	 * @param accessedResource
	 *            当前访问资源
	 * @return 需要校验返回true,不需要校验返回false
	 */
	private static boolean isUnCheckedContributions(String contributionName, String resourceURI) {
		boolean checked = true, unmached = true;

		// 先处理UNCHECK的
		String unchecked_contributions = ABFConfigKey.PERMISSION_UNCHECKED_CONTRIBUTIONS.getConfigValue("");

		for (String definedPath : unchecked_contributions.split(",")) {
			// 如果路径满足不校验的构件包集 checked = false;并且退出
			checked = !matchContributionName(contributionName, resourceURI, definedPath);
			if (!checked) {
				unmached = false;
				break;
			}
		}
		// 如果不满足不校验的构件包集合条件继续判断是否在需要判断的集合中
		if (checked) {
			String contributions = ABFConfigKey.PERMISSION_CHECKED_CONTRIBUTIONS.getConfigValue();

			for (String definedPath : contributions.split(",")) {
				checked = matchContributionName(contributionName, resourceURI, definedPath);
				if (checked) {
					unmached = false;
					break;
				}
			}

		}

		return !(unmached ? ABFConfigKey.PERMISSION_UNMATCH_CHECKED.getBLConfigValue(true) : checked);
	}

	/**
	 * 校验当前资源是否为Portal资源
	 * 
	 * @param type
	 *            资源类型
	 * @param resourceURI
	 *            资源路径
	 * @return
	 */
	private static boolean isPortalResource(String resourceURI) {
		return PortalResourceCacheManager.isPortalResource(resourceURI);
	}

	/**
	 * 当前资源是否不在权限校验定义范围内
	 * 
	 * @param type
	 *            资源类型
	 * @param resourceURI
	 *            资源路径
	 * @return true
	 */
	private static boolean isUncheckedPermssionResource(String resourceURI) {
		boolean unchecked = false;
		for (String resource : ABFConfigKey.PERMISSION_UNCHECK_RESOURCE.getConfigValue().split(",")) {
			unchecked = matchContributionName(null, resourceURI, resource);
			if (unchecked)
				break;
		}
		return unchecked;
	}

	/**
	 * 获取url对应功能的所在的构件包名
	 * 
	 * @param url
	 * @return uri,packagename,name,params,type
	 */
	private static String[] getContributionName(String url) {
		String[] ret = null;
		int bindex = url.lastIndexOf("/");
		int eindex = url.indexOf("?");
		int type = 0;
		boolean GOT = false;
		String qName = url.substring((bindex < 0) ? 0 : bindex + 1, (eindex < 0) ? url.length() : eindex);
		String params = url.substring((eindex < 0) ? url.length() : eindex);
		for (String[] suffixes : SUFFIXS) {
			type++;
			for (String suffix : suffixes) {
				if (qName.endsWith(suffix)) {
					String p = qName.substring(0, qName.length() - suffix.length());
					int i = p.lastIndexOf(".");
					String packageName = ((i < 0) ? null : p.substring(0, i));
					String name = p.substring((i < 0) ? 0 : i + 1);
					GOT = true;
					ret = new String[5];
					ret[0] = packageName + "." + name + "." + TYPES[type];
					ret[1] = packageName;
					ret[2] = name;
					ret[3] = params;
					ret[4] = TYPES[type];
					break;
				}
			}
			if (GOT) {
				break;
			}
		}
		if (!GOT) {
			ret = new String[5];
			ret[0] = url;
			ret[1] = url;
			ret[2] = url;
			;
			ret[3] = params;
			ret[4] = TYPES[type];
		}
		return ret;
	}

	/**
	 * 判断名称是否满足规则
	 * 
	 * @param conName
	 * @param rule
	 * @return
	 */
	private static boolean matchContributionName(String conName, String uri, String rule) {
		boolean matched = false;
		// 如果规则为空则为不匹配，否则继续判断
		if (rule != null && !rule.matches("^ *$")) {
			rule = rule.trim();
			if (rule.endsWith("*")) { // 以*结束
				rule = rule.substring(0, rule.length() - 1);
				if (rule.startsWith("*")) {// 以*开始并且以*结束
					rule = rule.substring(1);
					if (rule != null && !rule.matches("^ *$")) {
						if (conName != null) {
							matched = (conName.indexOf(rule) > -1);
						} else {
							matched = (uri.indexOf(rule) > -1);
						}
					} else {// 由两个**组成
						matched = true;
					}
				} else {
					if (rule != null && !rule.matches("^ *$")) {// 形如bos.utp.*
						if (conName != null) {
							matched = conName.startsWith(rule) || conName.concat(".").startsWith(rule);
						} else {
							matched = uri.startsWith(rule);
						}
					} else {// 只有一个*
						matched = true;
					}
				}
			} else if (rule.startsWith("*")) {// 形如*.auth
				rule = rule.substring(1);
				if (conName != null) {
					matched = conName.endsWith(rule);
				} else {
					matched = uri.endsWith(rule);
				}
			} else {// 形如 com.bos.utp.auth
				if (conName != null) {
					matched = conName.equals(rule);
				} else {
					matched = uri.equals(rule);
				}
			}
		}
		return matched;
	}

	/**
	 * 
	 * @param userObject
	 * @return 是免检用户
	 */
	private static boolean isAdminUser(IUserObject user) {
		boolean ret = false;
		if (user != null) {
			LogUtil.logDebug("权限检验:userId={0}", null, new Object[] { user.getUserId() });
			String users = ABFConfigKey.PERMISSION_ADMIN_USERS.getConfigValue();
			if (users != null && users.trim() != "") {
				String userid = user.getUserId();
				if (users != null) {
					for (String name : users.split(",")) {
						if (name.equals(userid)) {
							ret = true;
							break;
						}
					}
				}
			}
		} else {

		}
		return ret;
	}

	public static void main(String[] args) {
		String[] qNames = {
				"http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.flow",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.flowx?_eosFlowAction=login&a=1",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.flow.ajax",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.login.action",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.flowx.ajax",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.LoginManager.login.remote",
				// "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.LoginManager.login.bizx.ajax",
				"http://www.baidu.com/eos-default/auth/com.bos.utp.auth.LoginManager.login.biz.ajax", "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.LoginManager.login.biz.ext",
				"http://www.baidu.com/eos-default/auth/com.bos.utp.auth.LoginManager.login.service.ajax", "http://www.baidu.com/eos-default/auth/com.bos.utp.auth.Login.login.remote" };

		for (String url : qNames) {
			getContributionName(url);
		}
	}
}