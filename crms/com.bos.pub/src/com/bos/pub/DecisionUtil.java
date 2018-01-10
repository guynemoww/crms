/**
 * 
 */
package com.bos.pub;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bos.utp.tools.SystemInfo;
import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASSession;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.OrderbyType;
import com.eos.das.entity.criteria.impl.ExprTypeImpl;
import com.eos.das.entity.criteria.impl.OrderbyTypeImpl;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.sun.star.sheet.DDELinkMode;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

/**
 * @author 王世春
 * @date 2013-11-28 15:15:10
 * @email wsc.hi@163.com
 */
@Bizlet("决策树、规则相关")
public class DecisionUtil {

	private static final int EXIT_OK = 0;

	private Logger log = GitUtil.getLogger(DecisionUtil.class);

	private static StringBuffer ruleTableResult = new StringBuffer();
	private static StringBuffer rulelogResult = new StringBuffer();

	/**
	 * @param args
	 * @author 王世春
	 */
	/*
	 * public static void main(String[] args) throws Exception { String
	 * excelFile = "E:\\bos\\tmp\\test.xls"; Map<String, Object> param = new
	 * HashMap<String, Object>(); param.put("金额", 33); param.put("期限", 44);
	 * param.put("利率", 55); readDecisionTable(excelFile, param); }
	 */

	private static int decisionNum = 1;

	private static synchronized String decisionName() { // 多线程共用
		return "CrmsRule" + (decisionNum++) + "bean_";
	}

	private int paramNum = 0;

	private String paramName(String enname) {
		paramNum++;// 参数变量个数计数
		if (StringUtils.isAsciiPrintable(enname)) {
			// 可作为变量名称
			return "value_" + enname.trim() + "_" + paramNum;// 跟上英文名称，并跟上参数计数，防止变量名称重复
		}

		String name = "value" + paramNum;
		return name;
	}

	private Map<String, String> paramNameMap = new HashMap<String, String>();

	private Map<String, String> paramTypeMap = new HashMap<String, String>();

	private Map<String, String> paramType2Map = new HashMap<String, String>();

	private String getParamName(String zhname, String type, String type2) {
		if (null == zhname)
			return null;
		if (this.paramNameMap.containsKey(zhname) == false) {
			this.paramNameMap.put(zhname, this.paramName(zhname));
			if (type != null && !"null".equals(type))
				this.paramTypeMap.put(zhname, type);
			if (type2 != null && !"null".equals(type2))
				this.paramType2Map.put(zhname, type2);
		}
		return this.paramNameMap.get(zhname);
	}

	// 此属性不用了，改为从文件读
	private static String ruleClassString = "package temp.decision;\n"
			+ "import java.util.Map;\n" + "public class {classname} {\n\n"
			+ "public Object exe(Map map) {\n{rulecontent}\n}\n\n" + "}";

	private static HashMap<String, Integer> opPriority = new HashMap<String, Integer>();
	static {
		opPriority = new HashMap<String, Integer>();
		opPriority.put("(", Integer.valueOf(0));
		opPriority.put(")", Integer.valueOf(0));

		opPriority.put("&&", Integer.valueOf(10));// 并且、或者：优先级最低
		opPriority.put("||", Integer.valueOf(10));

		opPriority.put("!=", Integer.valueOf(30));// 大于、小于、等于：优先级次之
		opPriority.put("==", Integer.valueOf(30));
		// 加入财报问题 wangshicun 13年8月30
		opPriority.put("~~0==", Integer.valueOf(30));// 整数部分等于
		opPriority.put("~~2==", Integer.valueOf(30));// 取2位小数等于
		opPriority.put("~~6==", Integer.valueOf(30));// 取6位小数等于
		opPriority.put("~~0!=", Integer.valueOf(30));// 整数部分不等于
		opPriority.put("~~2!=", Integer.valueOf(30));// 取2位小数不等于
		opPriority.put("~~6!=", Integer.valueOf(30));// 取6位小数不等于
		opPriority.put("~~6>", Integer.valueOf(30));
		opPriority.put("~~6>=", Integer.valueOf(30));
		opPriority.put("~~6<", Integer.valueOf(30));
		opPriority.put("~~6<=", Integer.valueOf(30));
		// 加入财报问题 wangshicun 13年8月30
		opPriority.put("~~6&gt;", Integer.valueOf(30));
		opPriority.put("~~6&gt;=", Integer.valueOf(30));
		opPriority.put("~~6&lt;", Integer.valueOf(30));
		opPriority.put("~~6&lt;=", Integer.valueOf(30));

		opPriority.put(">", Integer.valueOf(30));
		opPriority.put(">=", Integer.valueOf(30));
		opPriority.put("&gt;", Integer.valueOf(30));
		opPriority.put("&gt;=", Integer.valueOf(30));
		opPriority.put("&lt;", Integer.valueOf(30));
		opPriority.put("&lt;=", Integer.valueOf(30));
		opPriority.put("<=", Integer.valueOf(30));
		opPriority.put("<", Integer.valueOf(30));

		opPriority.put("containsDict", Integer.valueOf(35));
		opPriority.put("contains", Integer.valueOf(35));
		opPriority.put("notContains", Integer.valueOf(35));
		opPriority.put("startsWith", Integer.valueOf(35));
		opPriority.put("notStartsWith", Integer.valueOf(35));
		opPriority.put("endsWith", Integer.valueOf(35));
		opPriority.put("notEndsWith", Integer.valueOf(35));

		opPriority.put("+", Integer.valueOf(40));// 加减
		opPriority.put("-", Integer.valueOf(40));

		opPriority.put("*", Integer.valueOf(50));// 乘除
		opPriority.put("/", Integer.valueOf(50));

		try {
			String path = System.getProperty("user.dir");
			System.out.println("user.dir:" + path);
			File userdir = new File(path);
			if (userdir.exists() == false)
				userdir.mkdirs();
			path += "/temp/decision";
			File file = new File(path);
			if (file.exists() == false)
				file.mkdirs();
			File[] files = file.listFiles();
			for (File f : files) {
				f.delete();// 清空临时目录
			}

			InputStream in = DecisionUtil.class
					.getResourceAsStream("decisionTemplate.txt");
			// byte[] arr = new byte[in.available()];
			// in.read(arr);
			// ruleClassString = new String(arr);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder(4096);
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			ruleClassString = sb.toString();
			reader.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bizlet("执行规则，参数map中必须有规则ID（rid，用于规则测试）或引用标识（rind，用于实际执行）")
	public HashMap<String, Object> testRule(HashMap<String, Object> map,
			Map[] items) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		for (int i = 0, len = items.length; i < len; i++) {
			Map item = items[i];
			Object type = item.get("type");
			Object val = item.get("val");
			Object name = item.get("name");
			if (name == null)
				continue;
			if (val == null) {
				param.put(name.toString(), null);
				continue;
			}
			if ("1".equals(type)) {
				param.put(name.toString(), Double.valueOf(val.toString()));// 实数、小数
				continue;
			} else if ("4".equals(type)) {
				param.put(name.toString(), Long.valueOf(val.toString()));// 整数
				continue;
			} else if ("3".equals(type)) {
				if (null == val)
					param.put(name.toString(), false);
				if ("1".equals(val.toString()) || "y".equals(val.toString())
						|| "Y".equals(val.toString())
						|| "是".equals(val.toString()))
					param.put(name.toString(), true);
				continue;
			} else {
				param.put(name.toString(), val.toString());// 文本型
				continue;
			}
		}

		return this.execRule(map, param);
	}

	private static HashMap<String, DataObject> ruleCache = new HashMap<String, DataObject>();

	private DataObject currentRuleCached = null;

	/**
	 * 更新单个规则的缓存
	 */
	private synchronized void updateRuleCache(HashMap<String, Object> map)
			throws Exception {
		if (null == map.get("rid") && null == map.get("rind")
				&& null == map.get("rule"))
			return;
		DataObject rule = DataFactory.INSTANCE.create("com.bos.pub.decision",
				"TbPubGrantRule");
		Object rid = map.get("rid");
		if (null != map.get("rule") && map.get("rule") instanceof DataObject) {
			rule = (DataObject) map.get("rule");
			rid = rule.get("rid");
		} else {
			if (null == rid) {
				rule.set("rind", map.get("rind"));
				rule.set("rstatus", "2");// 2-生效
				DataObject[] arr = GitUtil.queryEntitiesByTemplate(rule);
				if (null != arr && arr.length > 0) {
					rule = arr[0];
				} else {
					throw new Exception("出错！未查找到生效的规则：" + map.get("rind"));
				}
			} else {
				rule.set("rid", rid);
				DatabaseUtil.expandEntity("default", rule);
			}
			rid = rule.get("rid");
		}
		{
			DataObject ruleCached = ruleCache.get(rid);
			if (null == ruleCached
					|| null == rule.getDate("updateTime")
					|| null == ruleCached.getDate("updateTime")
					|| rule.getDate("updateTime").getTime() > ruleCached
							.getDate("updateTime").getTime()) {
				// 规则内容已更新
			} else {
				return;// 其他线程已处理完成更新
			}
		}
		String rcontent = rule.getString("rcontent");
		if (null == rcontent || rcontent.length() < 1) {
			DatabaseUtil.expandLobProperty(GitUtil.DEFAULT_DS_NAME, rule,
					"rcontent");
			rcontent = rule.getString("rcontent");
		}
		if (null == rcontent || rcontent.length() < 1) {
			return;
		}

		map.put("rcontent", rcontent);
		HashMap<String, Object> re = this.compile(map);
		rule.set("cls", re.get("cls"));

		if (null != rule.get("cls")) {
			ruleCache.put(rid.toString(), rule);
			currentRuleCached = rule;
		}
	}

	@Bizlet("执行规则，参数map中必须有规则ID（rid，用于规则测试）或引用标识（rind，用于实际执行）")
	public HashMap<String, Object> execRule(HashMap<String, Object> map,
			HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if ((null == map.get("rid") && null == map.get("rind"))
				|| null == param)
			return result;

		try {
			HashMap<String, Object> grant = grantTable.get();
			DataObject ruleCached = null;
			if (Boolean.TRUE.equals(grant.get(isInBatchModeKey))) {
				// 批量规则运行模式：不查询数据库
				if (null != map.get("rid")) {
					ruleCached = ruleCache.get(map.get("rid"));
				} else {
					HashMap batchModeRules = (HashMap) grant
							.get(batchModeRulesKey);
					if (null != batchModeRules) {
						DataObject r = (DataObject) batchModeRules.get(map
								.get("rind"));
						if (null != r)
							ruleCached = ruleCache.get(r.get("rid"));
					}
				}
			} else {
				DataObject rule = DataFactory.INSTANCE.create(
						"com.bos.pub.decision", "TbPubGrantRule");
				Object rid = map.get("rid");
				if (null == rid) {
					rule.set("rind", map.get("rind"));
					rule.set("rstatus", "2");// 2-生效
					DataObject[] arr = GitUtil.queryEntitiesByTemplate(rule);
					if (null != arr && arr.length > 0) {
						rule = arr[0];
					} else {
						throw new Exception("出错！未查找到生效的规则：" + map.get("rind"));
					}
				} else {
					rule.set("rid", rid);
					DatabaseUtil.expandEntity("default", rule);
				}
				rid = rule.get("rid");

				ruleCached = ruleCache.get(rid);
				if (null == ruleCached
						|| rule.getDate("updateTime").getTime() > ruleCached
								.getDate("updateTime").getTime()) {
					// 规则内容已更新
					map.put("rule", rule);
					
					updateRuleCache(map);
				}
				ruleCached = ruleCache.get(rid);
				if (ruleCached == null && null != currentRuleCached) {
					ruleCached = currentRuleCached;
				}
			}
			if (null == ruleCached) {
				if (null == ruleCached) {
					throw new Exception("出错！规则缓存查找失败，或未查找到生效的规则："
							+ map.get("rind"));
				}
			}
			if ("业务决策树01".equals(ruleCached.get("rind"))) {
				return bizFlowGrant(map, param, "");
			}
			Class cls = (Class) ruleCached.get("cls");
			if (null == cls) {
				throw new Exception("出错！规则编译失败：" + map.get("rind"));
			}
			Object ruleObj = cls.newInstance();
			Method mt = cls.getMethod("exe", new Class[] { HashMap.class });
			Object obj = mt.invoke(ruleObj, new Object[] { param });
			if(ruleCached.get("pid").equals("41")){
				DecisionUtil.ruleTableResult.append(map.get("rind") + "=" + obj
						+ " ");
			}
			result.put("result", obj);

			Field path = cls.getDeclaredField("path");
			List pathList = (List) path.get(ruleObj);
			log.debug("rule execute path:" + ArrayUtils.toString(pathList));
			result.put("path", pathList);
			// 保存日志到数据库
			if(ruleCached.get("pid").equals("41")){
				
				insertRuleLog(param,pathList,map.get("rind"));
			}
		} catch (Exception e) {
			log.error("执行规则时出错", e);
			if (null != e.getMessage()) {
				result.put("msg", e.getMessage());
			} else if (null != e.getCause()) {
				if (null != e.getCause().getMessage())
					result.put("msg", e.getCause().getMessage());
				else if (null != e.getCause().getCause())
					result.put("msg", e.getCause().getCause().getMessage());
			}

			String msg = "";
			if (null != result.get("msg"))
				msg = result.get("msg").toString();

			if (null != msg && msg.contains("Division is undefined")) {
				result.put("msg", "by zero");
			}
			if (null != msg && msg.contains("by zero")) {
				result.put("msg", "发生除零错误，请检查表达式中的除数是否为0:" + msg);
			}
		}
		return result;
	}

	/**
	 * 执行规则分类下的所有生效的规则
	 * 
	 * @param map
	 *            规则分类的信息，用pid表示规则分类id；用rtype表示规则类别（其中：05-用于财报平衡校验;06-用于财报指标计算;）
	 * @param param
	 *            规则执行时的所有参数，此map的key对应规则分类下参数列表中的参数英文名称，value为参数值
	 * @return key为规则名称，value为map；value的map中，如果存在msg，说明执行出现错误，msg对应的value为错误提示信息；<br/>
	 *         否则key为result的value表示规则执行的结果，key为rind的value表示规则标识。
	 * @throws Exception
	 */
	public HashMap<String, HashMap<String, Object>> execRuleByTypeId(
			HashMap<String, Object> map, HashMap<String, Object> param)
			throws Exception {
		HashMap<String, HashMap<String, Object>> result = new HashMap<String, HashMap<String, Object>>();
		if (map.get("pid") == null)
			return result;
		DataObject rule = DataFactory.INSTANCE.create("com.bos.pub.decision",
				"TbPubGrantRule");
		rule.set("pid", map.get("pid"));
		if (null != map.get("rtype"))
			rule.set("rtype", map.get("rtype"));
		rule.set("rstatus", "2");// 2-生效
		DataObject[] rules = DatabaseUtil.queryEntitiesByTemplate(
				GitUtil.DEFAULT_DS_NAME, rule);
		Connection conn = null;
		IDASSession session = null;
		try {
			conn = ConnectionHelper.getCurrentContributionConnection(GitUtil.DEFAULT_DS_NAME);
			session = DASManager.createDasSession(conn);
			List<DataObject> list = new ArrayList<DataObject>();
			for (int i = 0, len = rules.length; i < len; i++) {
				rule = rules[i];
				DataObject ruleCached = ruleCache.get(rule.get("rid"));
				if (null == ruleCached
						|| rule.getDate("updateTime").getTime() > ruleCached
								.getDate("updateTime").getTime()) {
					// 规则内容已更新，需要刷新缓存
					session.expandLobProperty(rule, "rcontent");
					list.add(rule);
				}
			}
			for (int i = 0, len = list.size(); i < len; i++) {
				rule = list.get(i);
				map.put("rule", rule);
				updateRuleCache(map);// 编译规则
			}
			DataObject ruleCached = null;
			for (int i = 0, len = rules.length; i < len; i++) {
				rule = rules[i];
				ruleCached = ruleCache.get(rule.get("rid"));
				if (null == ruleCached) {
					if (null == ruleCached) {
						throw new Exception("出错！规则缓存查找失败（未找到）！");
					}
				}
				Class cls = (Class) ruleCached.get("cls");
				if (null == cls) {
					throw new Exception("出错！规则编译结果为空！" + rule.get("rname"));
				}
				HashMap<String, Object> ruleresult = new HashMap<String, Object>();
				result.put(rule.getString("rname"), ruleresult);
				ruleresult.put("rind", rule.get("rind"));
				try {
					Method mt = cls.getMethod("exe",
							new Class[] { HashMap.class });
					Object ruleObj = cls.newInstance();
					Object obj = mt.invoke(ruleObj, new Object[] { param });
					ruleresult.put("result", obj);

					Field path = cls.getDeclaredField("path");
					List pathList = (List) path.get(ruleObj);
					log.debug("rule execute path:"
							+ ArrayUtils.toString(pathList));
					ruleresult.put("path", pathList);
				} catch (Exception e) {
					log.error("执行规则时出错", e);
					if (null != e.getMessage())
						ruleresult.put("msg", e.getMessage());
					else if (null != e.getCause())
						ruleresult.put("msg", e.getCause().getMessage());

					String msg = "";
					if (null != ruleresult.get("msg"))
						msg = ruleresult.get("msg").toString();

					if (null != msg && msg.contains("Division is undefined")) {
						ruleresult.put("msg", "by zero");
					}
					if (null != msg && msg.contains("by zero")) {
						ruleresult.put("msg", "发生除零错误，请检查表达式中的除数是否为0:" + msg);
					}
				}
			}
		} catch (Exception e) {
			log.error("执行规则出错", e);
		} finally {
			try {
				if (null != session)
					session.close();
			} catch (Exception e) {
			}
			try {
				if (null != conn && conn.isClosed() == false)
					conn.close();
			} catch (Exception e) {
			}
		}

		return result;
	}

	@Bizlet("xml编译为class对象，并将该对象和错误信息封装到map中返回")
	public HashMap<String, Object> validAndCompile(String xml, String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			if ("class".equals(type)) {
				map2.put("rcontent", xml);
				Class cls = (Class) this.compile(map2).get("cls");
				if (null != cls) {
					map.put("cls", cls);
					log.debug("生成的class对象：" + cls.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e.getMessage());
		}

		return map;
	}

	/**
	 * 授权表相关信息存储
	 */
	private static final ThreadLocal<HashMap<String, Object>> grantTable = new ThreadLocal<HashMap<String, Object>>() {
		protected HashMap<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	private static final String currentOrgid = "currentOrgid";

	/**
	 * @param nodename
	 * @return 根据决策树末级节点名称，查找并计算审批机构
	 */
	public static Object computeGrantTable(String nodename,
			Map<String, Object> params) throws Exception {
		HashMap<String, Object> grant = grantTable.get();
		final String grantTableInd = "bos_grant_table01";// 授权表默认用此标识
		if (grant.containsKey("grantTableCols") == false
				|| null == (Object[]) grant.get("grantTableCols")
				|| ((Object[]) grant.get("grantTableCols")).length < 1) {
			// 从session获取上级机构信息，此信息是在登录时设置好的
			String orgids = DataContextUtil
					.getString("m:userObject/attributes/parentorgids");
			// 查询当前机构及上级机构的纵向授权信息
			if (StringUtils.isBlank(orgids))
				orgids = "-1";

			// 查找各机构的授权表信息
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("grantTableInd", grantTableInd);
			map.put("orgids", orgids);
			Object[] grantTableCols = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.grant.selectGrantTableCols", map);
			grant.put("grantTableCols", grantTableCols);
		}
		Object[] grantTableCols = (Object[]) grant.get("grantTableCols");
		if (null == grantTableCols || grantTableCols.length < 1) {
			// 无授权表项：返回false，表示上级审批
			return false;
		}
		// 搜索中文：符号处理字符串
		if (null != nodename && nodename.contains("：")) {
			nodename = nodename.substring(nodename.indexOf("：") + 1).trim();
		}
		// 搜索英文:符号处理字符串
		if (null != nodename && nodename.contains(":")) {
			nodename = nodename.substring(nodename.indexOf(":") + 1).trim();
		}
		String currentorg = String.valueOf(grant.get(currentOrgid));
		for (int i = 0, len = grantTableCols.length; i < len; i++) {
			HashMap map = (HashMap) grantTableCols[i];
			String orgid = String.valueOf(map.get("ORGID"));
			if (map.get("RIND").equals(nodename) == false)
				continue; // 非当前节点
			if (orgid.equals(currentorg) == false)
				continue;
			String tname = map.get("TNAME").toString();
			if (null == params.get(tname))
				throw new Exception("传入参数的值为空：" + tname);

			BigDecimal tvalue = new BigDecimal(map.get("TVALUE").toString()); // 授权的金额
			BigDecimal realValue = new BigDecimal(String.valueOf(params
					.get(tname))); // 实际的金额

			if (realValue.compareTo(tvalue) <= 0) {// 实际金额小于等于授权金额：本级有权限
				DecisionUtil.ruleTableResult.append(nodename + "=true ");
				return true; // 下级行有权限
			} else {
				DecisionUtil.ruleTableResult.append(nodename + "=false ");
				return false;
			}
		}

		return false; // 表示上级审批
		// if (null == grantTableCols || grantTableCols.length < 1) {
		// // throw new Exception("没有配置授权表项信息");
		// // 没有配置授权表：所有业务总行审批即可
		// HashMap<String, Object> result = new HashMap<String, Object>();
		//
		// String orgid = DataContextUtil
		// .getString("m:userObject/attributes/parentorgids");
		// if (orgid.contains(","))
		// orgid = orgid.substring(orgid.lastIndexOf(",") + 1);
		//
		// String orgcode = DataContextUtil
		// .getString("m:userObject/attributes/parentorgcodes");
		// if (orgcode.contains(","))
		// orgcode = orgcode.substring(orgcode.lastIndexOf(",") + 1);
		//
		// String orgname = DataContextUtil
		// .getString("m:userObject/attributes/parentorgnames");
		// if (orgname.contains(","))
		// orgname = orgname.substring(orgname.lastIndexOf(",") + 1);
		//
		// result.put("orgid", orgid);
		// result.put("orgcode", orgcode);
		// result.put("orgname", orgname);
		// result.put("orglevel", "1");
		// params.put("规则结果：审批机构级别", "1");
		// return result;
		// }
		//
		// orgids = "," + orgids + ",";
		// int orglevel = 1;
		// String orgid = null;
		// String orgcode = null;
		// String orgname = null;
		// for (int i = 0, len = grantTableCols.length; i < len; i++) {
		// HashMap map = (HashMap) grantTableCols[i];
		// if (map.get("RIND").equals(nodename) == false)
		// continue; // 非当前节点
		// // String orgid = "," + map.get("TORG").toString() + ",";
		// if ("1".equals(map.get("ORGLEVEL").toString()))
		// continue;// 总行无需计算
		//
		// // 一个机构对应一条记录
		// if (Integer.valueOf(map.get("ORGLEVEL").toString()) != orglevel + 1)
		// {
		// break;// 总行没给分行授权、则分行不能给支行授权
		// }
		// String tname = map.get("TNAME").toString();
		// // if (params.containsKey(tname) == false)
		// // throw new Exception("没有传入参数：" + tname);
		// if (null == params.get(tname))
		// throw new Exception("传入参数的值为空：" + tname);
		//
		// BigDecimal tvalue = new BigDecimal(map.get("TVALUE").toString()); //
		// 授权的金额
		// BigDecimal realValue = new BigDecimal(String.valueOf(params
		// .get(tname))); // 实际的金额
		// if (Double.valueOf(tvalue.doubleValue() * 10000).longValue() >=
		// Double
		// .valueOf(realValue.doubleValue() * 10000).longValue()) {
		// // 4位小数进行比较
		// orgid = map.get("TORG").toString();
		// orgcode = map.get("ORGCODE").toString();
		// orgname = map.get("ORGNAME").toString();
		// orglevel = orglevel + 1;
		// // 下级行有权限：继续计算下级
		// } else {
		// // 下级行无权限：审批行为上级行
		// break;
		// }
		// }
		// if (null == orgid) {
		// orgid = DataContextUtil
		// .getString("m:userObject/attributes/parentorgids");
		// if (orgid.contains(","))
		// orgid = orgid.substring(orgid.lastIndexOf(",") + 1);
		//
		// orgcode = DataContextUtil
		// .getString("m:userObject/attributes/parentorgcodes");
		// if (orgcode.contains(","))
		// orgcode = orgcode.substring(orgcode.lastIndexOf(",") + 1);
		//
		// orgname = "总行";
		// orglevel = 1;
		// }
		//
		// HashMap<String, Object> result = new HashMap<String, Object>();
		// result.put("orgid", orgid);
		// result.put("orgcode", orgcode);
		// result.put("orgname", orgname);
		// result.put("orglevel", String.valueOf(orglevel));
		// params.put("规则结果：审批机构级别", String.valueOf(orglevel));
		// return result;
	}

	/**
	 * @author 王世春
	 * @date 2013-12-06
	 * @note 返回的map可能为空，但永不为null
	 */
	@Bizlet("xml编译为class对象，编译第一个类时要加载的类较多，约费时600~700ms，后续编译时，约费时200~400ms。调用执行规则时耗时约1ms。因此预编译规则可大幅提高性能。")
	public HashMap<String, Object> compile(HashMap<String, Object> map)
			throws Exception {
		grantTable.get().clear();

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (null == map || null == map.get("rcontent"))
			return result;
		String xml = map.get("rcontent").toString();
		// long time = System.currentTimeMillis();
		long time = System.nanoTime();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();

		String classname = decisionName();
		String path = System.getProperty("user.dir") + File.separator + "temp"
				+ File.separator + "decision";
		// log.debug("规则文件路径：" + path);
		File file = new File(path + File.separator + classname + ".java");
		{
			if (file.exists())
				file.delete();
			if (file.exists() == false)
				file.createNewFile();

			file = File.createTempFile(classname, ".java");
			// 修改为使用临时文件
			// 如需使用user.dir或特定目录屏蔽以下及以上一行，
			// 并修改：/crms/com.bos.pub/src/com/rm/pub/decisionTemplate.txt文件，取消第一行package注释
			classname = file.getName().replace(".java", "");
			log.debug("规则临时文件位置：" + file.getAbsolutePath());

			// FileOutputStream fo = new FileOutputStream(file);
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(file));// ISO-8859-1 或者 UTF-8都不行
			Object obj = xml2bean(xml);

			Map maps = ((Map) obj);
			StringBuffer paramsbTemp = new StringBuffer();
			paramsbTemp.append("log(\"assign param '");
			paramsbTemp.append("logPath(");
			// List items = (List) ((Map)obj).get("items");
			// for(int j=0;j<items.size();j++){
			// List listTemp = items;
			// }
			StringBuffer sb = generateJavaCodeMain((Map) obj);
			StringBuffer paramsb = new StringBuffer();
			for (String key : this.paramTypeMap.keySet()) {
				String type = this.paramTypeMap.get(key);
				String paramName = this.getParamName(key, type, null);
				if (ITEM_TYPE_BOOL.equals(type)) {
					paramsb.append("  Boolean ").append(paramName).append(
							" = toBool(map.get(\"").append(key).append(
							"\"));\n");
				} else if (ITEM_TYPE_NUMBER.equals(type)) {
					// if ("long".equals(this.paramType2Map.get(key))) {
					// paramsb.append(" Long ").append(paramName).append(
					// " = toLong(");
					// } else {
					// paramsb.append(" Double ").append(paramName).append(
					// " = toDouble(");
					// }
					paramsb.append("  BigDecimal ").append(paramName).append(
							" = toBigDecimal(");
					paramsb.append("map.get(\"").append(key).append("\"));\n");
				} else {
					// paramsb.append(" String ").append(paramName).append(
					// " = String.valueOf(map.get(\"").append(key).append(
					// "\")).intern();\n");
					paramsb.append("  String ").append(paramName).append(
							" = String.valueOf(map.get(\"").append(key).append(
							"\"));\n");
				}
				paramsb.append("  log(\"input param '" + key + "'：\" + "
						+ paramName + ");\n");
			}

			String rule = ruleClassString.replace("{classname}", classname)
					.replace("{classname}", classname).replace("{rulecontent}",
							paramsb.append(sb).toString());
			// fo.write(rule.getBytes());// "UTF-8"
			// fo.close();
			out.write(rule);
			out.flush();
			out.close();
		}

		log.debug("explain time:" + (System.nanoTime() - time) / 1000000
				+ " ms");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(out);// 出错时的信息
		int status = com.sun.tools.javac.Main.compile(new String[] { file
				.getAbsolutePath() }
		// "D:\\Primeton\\Platform\\ide\\eclipse\\workspace\\test\\Test2.java"
				, pw);
		log.debug("compile and explain time:" + (System.nanoTime() - time)
				/ 1000000 + " ms");
		if (status == EXIT_OK) {
			Class cls = null;

			try {
				String p = file.getAbsolutePath();
				p = p.substring(0, p.length() - 5) + ".class";
				cls = new DecisionClassLoader(this.getClass().getClassLoader())
						.loadClassFromFile(p);
				if (SystemInfo.isDebug() == false) {
					// file.delete();// 删除.java文件
					// new File(p).delete();// 删除class文件
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					// 修改为使用临时文件
					// 如需使用user.dir或特定目录屏蔽以下一行
					// cls = Class.forName("temp.decision." + classname);
					if (cls == null) {
						cls = Class.forName(classname);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (null != cls)
				log.debug(cls.getName());
			log.debug("编译前总的JVM内存(KB)：" + (totalMemory / 1024.0));
			log.debug("编译前空闲内存(KB)：" + (freeMemory / 1024.0));
			log.debug("编译后总的JVM内存(KB)："
					+ (Runtime.getRuntime().totalMemory() / 1024.0));
			log.debug("编译后空闲内存(KB)："
					+ (Runtime.getRuntime().freeMemory() / 1024.0));
			log
					.debug("编译后总的JVM增加(KB)："
							+ ((Runtime.getRuntime().totalMemory() - totalMemory) / 1024.0));
			log
					.debug("编译后空闲内存增加(KB)："
							+ ((Runtime.getRuntime().freeMemory() - freeMemory) / 1024.0));
			result.put("cls", cls);

			return result;
		} else {
			String msg = new String(out.toByteArray());
			if (null != msg && msg.length() > 0) {
				throw new Exception(msg);
			}
		}
		return result;
	}

	public class DecisionClassLoader extends java.lang.ClassLoader {
		public DecisionClassLoader(ClassLoader parent) {
			super(parent);
		}

		@SuppressWarnings("deprecation")
		public Class loadClassFromFile(String filename) throws Exception {
			FileInputStream fi = new FileInputStream(filename);
			byte[] arr = new byte[fi.available()];
			fi.read(arr);
			fi.close();

			return super.defineClass(arr, 0, arr.length);
		}
	}

	private static final String ITEM_TYPE_NUMBER = "数值型";

	private static final String ITEM_TYPE_STRING = "文本型";

	private static final String ITEM_TYPE_BOOL = "是否型";

	private static final String ITEM_TYPE_CONST = "const"; // 常量

	private static final String ITEM_TYPE_OP = "op"; // 操作符

	private int indent = 1;

	private StringBuffer appendIndent(StringBuffer sb) {
		for (int i = 0; i < indent; i++) {
			sb.append("  ");
		}

		return sb;
	}

	// 判断是否数值
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;

		int sz = str.length();
		if (sz < 1) {
			return false;
		}
		if ('-' == str.charAt(0)) {
			str = str.substring(1);
			if (str.length() < 1) {
				return false;
			}
		}
		if ('0' == str.charAt(0)) {
			// 0开头的不是数值，可能是业务参数的业务字典
			if (sz > 1 && str.charAt(1) != '.')
				return false;// 单独的0是数值，否则0的后面应该为小数点
		}
		boolean flag = false;
		for (int i = 0; i < sz; ++i) {
			char ch = str.charAt(i);
			int chint = (int) ch;
			if ('.' == ch) {
				if (flag)
					return false; // 不能带两个小数点
				flag = true;
				continue;
			}

			if (chint < 48 || chint > 48 + 9)
				return false;
		}

		return true;
	}

	// 在遇到参数定义时增加，在“如果……那么……”结束后减少
	private Map<String, Stack<Integer>> assignMap = new HashMap<String, Stack<Integer>>();

	// 参数定义的参数类型
	private Map<String, String> assignParamTypeMap = new HashMap<String, String>();

	// 生成规则、决策树
	private StringBuffer generateJavaCodeMain(Map map) throws Exception {
		StringBuffer sb = new StringBuffer();
		Object type = map.get("type");
		Object text = map.get("text");

		boolean isDecisionTree = (Boolean) grantTable.get().get(
				"isDecisionTree");
		if ("expr".equals(type)) {
			// 规则结果为
			appendIndent(sb);

			List items = (List) map.get("items");
			sb.append("Object result=(");

			if (items.size() > 0) {
				Map<String, Object> tmpmap = validateExpr(items, 0, items
						.size() - 1);

				// sb.append(generateJavaCodeExpr(items, 0, items.size() - 1));
				sb.append(tmpmap.get(javaExpr));
			} else if (null != text && isDecisionTree) {// 决策树叶子节点：计算授权表（没有设置进入条件）
				sb.append("computeGrantTable(\"" + text + "\")");
			} else {
				sb.append("null");
			}
			sb.append(");\n");
			appendIndent(sb);
			if (null != text && isDecisionTree) {// 决策树
				appendIndent(sb);
				sb.append("logPath(\"" + text + "\");\n\n");
			}else{
				sb.append("log(\"规则结果为：\"+result);\n\n");
				sb.append("logPath(\"规则结果为：\"+result);\n\n");
			}
			appendIndent(sb);
			sb.append("return result;");
			return sb;
		}
		if ("assign".equals(type)) {
			// 参数定义
			appendIndent(sb);
			// String paramName = text.toString();
			List items = (List) map.get("items");
			Map<String, Object> tmpmap = null;
			String tmptype = null;
			if (items.size() > 0) {
				tmpmap = validateExpr(items, 0, items.size() - 1);
				;
				tmptype = tmpmap.get("resulttype").toString();
			} else {
				if (isDecisionTree) {// 决策树叶子节点：计算授权表
					tmptype = ITEM_TYPE_BOOL;
				} else {
					throw new Exception("参数“" + text + "”没有配置表达式");
				}
			}
			Stack<Integer> stack = null;
			if (assignMap.containsKey(text)) {
				stack = assignMap.get(text);
			} else {
				stack = new Stack<Integer>();
				assignMap.put(text.toString(), stack);
			}
			String text2 = text.toString();
			{
				{
					if (assignParamTypeMap.containsKey(text)) {
						String preType = assignParamTypeMap.get(text);
						if (tmptype.equals(preType) == false) {
							throw new Exception(text + " 已作为 " + preType
									+ "后，不能再作为 " + tmptype);
						}
					} else {
						assignParamTypeMap.put(text.toString(), tmptype);
					}
				}

				// 定义的参数名称转换为英文
				Object numbertype = map.get("numbertype");
				text = this.getParamName(text.toString(), null,
						null == numbertype ? null : numbertype.toString()); // 参数定义和业务参数类型不放到一起，因此type参数为null
				if (ITEM_TYPE_BOOL.equals(tmptype)) {
					if (stack.isEmpty()) {
						stack.push(indent);
						sb.append("Boolean ");
					}
					sb.append(text);
					sb.append(" = toBool(");
					// sb.append(generateJavaCodeExpr(items, 0, items.size() -
					// 1));
					if (isDecisionTree && items.size() < 1) {// 决策树叶子节点：计算授权表
						sb.append("computeGrantTable(\"" + text2 + "\")");
					} else {
						sb.append(tmpmap.get(javaExpr));
					}
					sb.append(");\n");
					appendIndent(sb);
					sb.append("log(\"assign param '" + text + "-" + text2
							+ "':\" + " + text + ");\n");
					appendIndent(sb);
					sb.append("logPath(\"" + text2 + "\");\n\n");

					// 将参数设置put到参数map中
					appendIndent(sb);
					sb.append("map.put(\"" + text2 + "\", " + text + ");\n");

					List list = (List) map.get("rules");
					if (null == list) {
					} else {// 决策树时的下级节点
						for (int i = 0, len = list.size(); i < len; i++) {
							// if ("集团客户审批权（符合担保）".equals(text2))
							// System.out.println();

							// 可在此判断“规则结果为的类型应一致”
							Map rule = (Map) list.get(i);
							if ("expr".equals(rule.get("type"))
									&& sb.toString().endsWith("return result;"))
								break;

							sb.append(generateJavaCodeMain(rule));
							if ("expr".equals(rule.get("type")))
								break; // “规则结果为”只能有一个
						}
					}
					return sb;
				} else if (ITEM_TYPE_STRING.equals(tmptype)) {
					if (stack.isEmpty()) {
						stack.push(indent);
						sb.append("String ");
					}
				} else if (ITEM_TYPE_NUMBER.equals(tmptype)) {
					if (stack.isEmpty()) {
						stack.push(indent);
						// sb.append("Double ");
						sb.append("BigDecimal ");
					}
					sb.append(text);
					// sb.append(" = toDouble(");
					sb.append(" = toBigDecimal(");
					// sb.append(generateJavaCodeExpr(items, 0, items.size() -
					// 1));
					if (isDecisionTree && items.size() < 1) {// 决策树叶子节点：计算授权表
						sb.append("computeGrantTable(\"" + text2 + "\")");
					} else {
						sb.append(tmpmap.get(javaExpr));
					}
					sb.append(");\n");
					appendIndent(sb);
					sb.append("log(\"assign param '" + text + "-" + text2
							+ "':\" + " + text + ");\n");
					appendIndent(sb);
					sb.append("logPath(\"" + text2 + "\");\n\n");

					// 将参数设置put到参数map中
					appendIndent(sb);
					sb.append("map.put(\"" + text2 + "\", " + text + ");\n");

					List list = (List) map.get("rules");
					if (null == list) {
					} else {// 决策树时的下级节点
						for (int i = 0, len = list.size(); i < len; i++) {
							// 可在此判断“规则结果为的类型应一致”
							Map rule = (Map) list.get(i);
							if ("expr".equals(rule.get("type"))
									&& sb.toString().endsWith("return result;"))
								break;
							sb.append(generateJavaCodeMain(rule));
							if ("expr".equals(rule.get("type")))
								break; // “规则结果为”只能有一个
						}
					}
					return sb;
				}
			}
			if (stack.isEmpty()) {
				stack.push(indent);
			}
			sb.append(text);
			sb.append(" = (");
			// sb.append(generateJavaCodeExpr(items, 0, items.size() - 1));
			if (isDecisionTree && items.size() < 1) {// 决策树叶子节点：计算授权表
				sb.append("computeGrantTable(\"" + text2 + "\")");
			} else {
				sb.append(tmpmap.get(javaExpr));
			}
			sb.append(");\n");
			appendIndent(sb);
			sb.append("log(\"assign param '" + text + "-" + text2 + "':\" + "
					+ text + ");\n");
			appendIndent(sb);
			sb.append("logPath(\"" + text2 + "\");\n\n");
			// 将参数设置put到参数map中
			appendIndent(sb);
			sb.append("map.put(\"" + text2 + "\", " + text + ");\n");

			List list = (List) map.get("rules");
			if (null == list) {
			} else {// 决策树时的下级节点
				for (int i = 0, len = list.size(); i < len; i++) {
					// 可在此判断“规则结果为的类型应一致”
					Map rule = (Map) list.get(i);
					if ("expr".equals(rule.get("type"))
							&& sb.toString().endsWith("return result;"))
						break;
					sb.append(generateJavaCodeMain(rule));
					if ("expr".equals(rule.get("type")))
						break; // “规则结果为”只能有一个
				}
			}
			return sb;
		}
		
		if ("ifthen".equals(type)) {
			// 如果……那么……
			appendIndent(sb);
			indent++;
			List items = (List) map.get("items");
			Map<String, Object> tmpmap = validateExpr(items, 0,
					items.size() - 1);
			StringBuffer tmpmapsb = new StringBuffer();
			
			tmpmapsb = validateExprs(items, 0,
				items.size() - 1,tmpmapsb);

			//System.out.println(tmpmapsb);
			{
				// String tmptype = guessExprType(items, 0, items.size() - 1);
				String tmptype = tmpmap.get("resulttype").toString();
				if (ITEM_TYPE_BOOL.equals(tmptype) == false)
					throw new Exception("“如果……那么……”中，“如果……”部分的表达式应是逻辑判断");
			}
			// sb.append(generateJavaCodeExpr(items, 0, items.size() - 1));
			sb.append(tmpmapsb);
			sb.append("if (");
			sb.append(tmpmap.get(javaExpr));
			sb.append(") {\n");
			if (null != text) {
				// appendIndent(sb);
				// sb.append("logPath(\"" + text + "\");\n\n");
			}
			List list = (List) map.get("rules");
			/*
			 * if (list.isEmpty()) { if ((Boolean)
			 * grantTable.get().get("isDecisionTree")) {// 决策树叶子节点：计算授权表
			 * appendIndent(sb); sb.append("Object result =
			 * computeGrantTable(\"" + text + "\");\n"); appendIndent(sb);
			 * sb.append("log(\"result:\" + result);\n"); appendIndent(sb);
			 * sb.append("logPath(\"" + text + "\");\n\n"); appendIndent(sb);
			 * sb.append("return result;"); } }
			 */
			if (isDecisionTree && text != null
					&& text.toString().startsWith("授权表判断")) {
				if (list.isEmpty()) {// 决策树叶子节点：计算授权表
					appendIndent(sb);
					sb.append("Object result = computeGrantTable(\"" + text
							+ "\");\n");
					appendIndent(sb);
					sb.append("log(\"result:\" + result);\n");
					appendIndent(sb);
					sb.append("logPath(\"" + text + "\");\n\n");
					appendIndent(sb);
					sb.append("return result;");
				} else {// 非叶子节点：计算授权表
					String tmptype = ITEM_TYPE_BOOL; // 计算结果为是否型
					if (assignParamTypeMap.containsKey(text)) {
						String preType = assignParamTypeMap.get(text);
						if (tmptype.equals(preType) == false) {
							throw new Exception(text + " 已作为 " + preType
									+ "后，不能再作为 " + tmptype);
						}
					} else {
						assignParamTypeMap.put(text.toString(), tmptype);
					}
					String paramName = this.getParamName(text.toString(), null,
							null);
					appendIndent(sb);
					sb.append("Boolean ");
					sb.append(paramName);
					sb.append(" = toBool(computeGrantTable(\"" + text + "\")");
					sb.append(");\n");
					appendIndent(sb);
					sb.append("log(\"assign param(if) '" + paramName + "-"
							+ text + "':\" + " + paramName + ");\n");
					appendIndent(sb);
					sb.append("logPath(\"" + text + "\");\n\n");

					// 将参数设置put到参数map中
					appendIndent(sb);
					sb
							.append("map.put(\"" + text + "\", " + paramName
									+ ");\n");
				}
			}
			for (int i = 0, len = list.size(); i < len; i++) {
				Map rule = (Map) list.get(i);
				if ("expr".equals(rule.get("type"))
						&& sb.toString().endsWith("return result;"))
					break;
				sb.append(generateJavaCodeMain(rule));
				if ("expr".equals(rule.get("type")))
					break;// “规则结果为”只能有一个
			}
			{
				Stack<Integer> stack = null;
				// 遍历map，pop当前indent
				for (String key : assignMap.keySet()) {
					stack = assignMap.get(key);
					if (null == stack || stack.isEmpty())
						continue;
					if (stack.peek() == indent)
						stack.pop();
				}
			}
			indent--;
			sb.append("\n");
			appendIndent(sb);
			sb.append("}\n");

			return sb;
		}
		List list = (List) map.get("rules");
		for (int i = 0, len = list.size(); i < len; i++) {
			// 可在此判断“规则结果为的类型应一致”
			Map rule = (Map) list.get(i);
			if ("expr".equals(rule.get("type"))
					&& sb.toString().endsWith("return result;"))
				break;
			sb.append(generateJavaCodeMain(rule));
			if ("expr".equals(rule.get("type")))
				break;// “规则结果为”只能有一个
		}

		return sb;
	}

	private String guessExprType(Map map) throws Exception {
		Object text = map.get("text");
		Object type = map.get("type");
		Object dictTypeId = map.get("dictTypeId");
		if (ITEM_TYPE_CONST.equals(type)) {
			// 常量
			if (null != dictTypeId && dictTypeId.toString().length() > 0)
				return ITEM_TYPE_STRING;
			if (isNumeric(text.toString()) == false) {
				if ("是".equals(text) || "否".equals(text))
					return ITEM_TYPE_BOOL;
				return ITEM_TYPE_STRING;
			} else {
				return ITEM_TYPE_NUMBER;
			}
		}
		if (ITEM_TYPE_STRING.equals(type)) {
			// 文本型
			if ("是".equals(text) || "否".equals(text))
				return ITEM_TYPE_BOOL;
			return ITEM_TYPE_STRING;
		}
		if (ITEM_TYPE_BOOL.equals(type)) {
			// 是否型
			return ITEM_TYPE_BOOL;
		}
		if ("expr".equals(type)) {
			if (assignParamTypeMap.containsKey(text) == false) {
				throw new Exception("表达式错误："
						+ String.valueOf(text).replace("&gt;", ">").replace(
								"&lt;", "<") + " 应先定义后使用");
			}
			return assignParamTypeMap.get(text);
		}
		return ITEM_TYPE_NUMBER;
	}

	// private String guessExprTypex(List list, int start, int end) {
	// for (int i = start; i <= end; i++) {
	// Map map = (Map) list.get(i);
	// Object name = map.get("name");
	// Object type = map.get("type");
	// if (ITEM_TYPE_OP.equals(type)) {
	// // 操作符
	// if (!"+".equals(name) && !"-".equals(name) && !"*".equals(name)
	// && !"/".equals(name) && !"(".equals(name)
	// && !")".equals(name)) {
	// return ITEM_TYPE_BOOL;
	// }
	// continue;
	// }
	// }
	//
	// for (int i = start; i <= end; i++) {
	// Map map = (Map) list.get(i);
	// Object text = map.get("text");
	// Object type = map.get("type");
	// if (ITEM_TYPE_CONST.equals(type)) {
	// // 常量
	// if (isNumeric(text.toString()) == false) {
	// if ("是".equals(text) || "否".equals(text))
	// return ITEM_TYPE_BOOL;
	// return ITEM_TYPE_STRING;
	// }
	// continue;
	// }
	// if (ITEM_TYPE_STRING.equals(type)) {
	// // 文本型
	// if ("是".equals(text) || "否".equals(text))
	// return ITEM_TYPE_BOOL;
	// return ITEM_TYPE_STRING;
	// }
	// if (ITEM_TYPE_BOOL.equals(type)) {
	// // 是否型
	// return ITEM_TYPE_BOOL;
	// }
	// }
	//
	// return ITEM_TYPE_NUMBER;
	// }

	/**
	 * @param list
	 * @param start
	 * @param end
	 * @return 校验通过时，返回表达式的结果类型；不通过时，抛出异常
	 * @throws Exception
	 */
	private Map<String, Object> validateExpr(List list, int start, int end)
			throws Exception {
		// 中缀表达式，转换为：后缀表达式
		Stack<Map> stack = new Stack<Map>();// 运算符栈。用于：辅助求后缀表达式
		List<Map> postfixList = new ArrayList<Map>();// 实际的后缀表达式
		for (int i = start; i <= end; i++) {
			Map map = (Map) list.get(i);
			String type = (String) map.get("type");
			String name = (String) map.get("name");
			if (ITEM_TYPE_OP.equals(type) && opPriority.containsKey(name)) {
				// 操作符
				if (")".equals(name)) {
					// 遇到右括号，则一直退栈输出，直到退到左括号止
					boolean flg = false;// 未有匹配的括号
					while (stack.isEmpty() == false) {
						Map top = stack.peek();
						String name2 = (String) top.get("name");
						String type2 = (String) top.get("type");
						if (ITEM_TYPE_OP.equals(type2) && "(".equals(name2)) {
							// 遇到左括号
							flg = true;
							stack.pop();
							break;
						}
						top = stack.pop();
						postfixList.add(top);
					}
					if (flg == false) {
						throw new Exception("括号不匹配："
								+ toStringBuffer(list, start, end));
					}
					continue;
				}
				// if ("/".equals(name)) {
				// System.out.println();
				// }
				if ("(".equals(name)) {
					stack.push(map);
					continue;
				}
				// if (stack.isEmpty()) {
				// stack.push(map);// 栈为空时，操作符进栈
				// continue;
				// }
				// 遇到运算符，则与栈顶比较，运算符级别比栈顶级别高则进栈，否则退出栈顶元素并输出，继续比较栈顶元素
				while (stack.isEmpty() == false) {
					Map top = stack.peek();
					String name2 = (String) top.get("name");
					if (opPriority.get(name) > opPriority.get(name2)) {
						break;
					} else {
						postfixList.add(stack.pop());
					}
				}
				stack.push(map);
			} else {
				// 遇到操作数，直接输出
				postfixList.add(map);
			}
		}// 后缀表达式处理完成
		if (stack.isEmpty() == false) {
			for (int len = stack.size(), i = len - 1; i >= 0; i--) {
				postfixList.add(stack.get(i));
			}
		}

		// 模拟计算后缀表达式
		stack.clear();// 用于模拟后缀表达式的计算
		// for (int i = 0, len = postfixList.size(); i < len; i++) {
		// Map map = postfixList.get(i);
		// String type = (String) map.get("type");
		// String name = (String) map.get("name");
		// String text = (String) map.get("text");
		// if (text == null || text.length() < 1) {
		// text = name;
		// }
		// if (ITEM_TYPE_OP.equals(type) && opPriority.containsKey(name)) {
		// // 操作符
		// if (stack.size() < 2) {
		// throw new Exception("表达式错误："
		// + name.replace("&gt;", ">").replace("&lt;", "<")
		// + " 的左边和右边都应有一个操作数（参数）:"
		// + toStringBuffer(list, start, end));
		// }
		// Map right = stack.pop();
		// Map left = stack.pop();
		// if ("&&".equals(name) || "||".equals(name)) {
		// String leftType = (String) left.get("type");
		// String rightType = (String) right.get("type");
		// if (ITEM_TYPE_CONST.equals(leftType)) {
		// leftType = this.guessExprType(left);
		// } else if ("expr".equals(leftType)) {
		// if (assignParamTypeMap.containsKey(left.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(left.get("text")).replace(
		// "&gt;", ">").replace("&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// leftType = assignParamTypeMap.get(left.get("text"));
		// }
		//
		// if (ITEM_TYPE_CONST.equals(rightType)) {
		// rightType = this.guessExprType(right);
		// } else if ("expr".equals(rightType)) {
		// if (assignParamTypeMap.containsKey(right.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(right.get("text"))
		// .replace("&gt;", ">").replace(
		// "&lt;", "<") + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// rightType = assignParamTypeMap.get(right.get("text"));
		// }
		// if (ITEM_TYPE_BOOL.equals(leftType) == false
		// || ITEM_TYPE_BOOL.equals(rightType) == false) {
		// throw new Exception("表达式错误："
		// + text.replace("&gt;", ">")
		// .replace("&lt;", "<")
		// + " 的左边和右边都应有一个”是否型“操作数（参数）:"
		// + toStringBuffer(list, start, end));
		// }
		// Map<String, Object> item = new HashMap<String, Object>(2, 1);
		// stack.push(item);// 计算结果入栈
		// item.put("type", ITEM_TYPE_BOOL);
		// continue;
		// }// 并且、或者处理完毕
		// if ("==".equals(name) || "!=".equals(name)) {
		// if (left.get("type").equals(right.get("type")) == false) {
		// String leftType = (String) left.get("type");
		// String rightType = (String) right.get("type");
		// if (ITEM_TYPE_CONST.equals(leftType)) {
		// leftType = this.guessExprType(left);
		// } else if ("expr".equals(leftType)) {
		// if (assignParamTypeMap
		// .containsKey(left.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(left.get("text"))
		// .replace("&gt;", ">").replace(
		// "&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// leftType = assignParamTypeMap.get(left.get("text"));
		// }
		//
		// if (ITEM_TYPE_CONST.equals(rightType)) {
		// rightType = this.guessExprType(right);
		// } else if ("expr".equals(rightType)) {
		// if (assignParamTypeMap.containsKey(right
		// .get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(right.get("text"))
		// .replace("&gt;", ">").replace(
		// "&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// rightType = assignParamTypeMap.get(right
		// .get("text"));
		// }
		// if (leftType.equals(rightType) == false) {
		// // throw new Exception("表达式错误："
		// // + text.replace("&gt;", ">").replace("&lt;",
		// // "<") + " 的左边和右边的操作数（参数）类型需要相同:"
		// // + toStringBuffer(list, start, end));
		// }
		// Map<String, Object> item = new HashMap<String, Object>(
		// 2, 1);
		// stack.push(item);// 计算结果入栈
		// item.put("type", ITEM_TYPE_BOOL);
		// continue;
		// } else {
		// String leftType = (String) left.get("type");
		// String rightType = (String) right.get("type");
		// if (ITEM_TYPE_CONST.equals(leftType)
		// && ITEM_TYPE_CONST.equals(rightType)) {
		// leftType = this.guessExprType(left);
		// rightType = this.guessExprType(right);
		// }
		// if ("expr".equals(leftType)) {
		// if (assignParamTypeMap
		// .containsKey(left.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(left.get("text"))
		// .replace("&gt;", ">").replace(
		// "&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// leftType = assignParamTypeMap.get(left.get("text"));
		// }
		// if ("expr".equals(rightType)) {
		// if (assignParamTypeMap.containsKey(right
		// .get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(right.get("text"))
		// .replace("&gt;", ">").replace(
		// "&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// rightType = assignParamTypeMap.get(right
		// .get("text"));
		// }
		// if (leftType.equals(rightType) == false) {
		// // throw new Exception("表达式错误："
		// // + text.replace("&gt;", ">").replace("&lt;",
		// // "<") + " 的左边和右边的操作数（参数）类型需要相同:"
		// // + toStringBuffer(list, start, end));
		// }
		// Map<String, Object> item = new HashMap<String, Object>(
		// 2, 1);
		// stack.push(item);// 计算结果入栈
		// item.put("type", ITEM_TYPE_BOOL);
		// continue;
		// }
		// }// 等于、不等于处理完毕
		//
		// // 数学运算：操作数必须为数值
		// String leftType = (String) left.get("type");
		// String rightType = (String) right.get("type");
		// if (ITEM_TYPE_CONST.equals(leftType)) {
		// leftType = this.guessExprType(left);
		// } else if ("expr".equals(leftType)) {
		// if (assignParamTypeMap.containsKey(left.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(left.get("text")).replace(
		// "&gt;", ">").replace("&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// leftType = assignParamTypeMap.get(left.get("text"));
		// }
		// if (ITEM_TYPE_CONST.equals(rightType)) {
		// rightType = this.guessExprType(right);
		// } else if ("expr".equals(rightType)) {
		// if (assignParamTypeMap.containsKey(right.get("text")) == false) {
		// throw new Exception("表达式错误："
		// + String.valueOf(right.get("text")).replace(
		// "&gt;", ">").replace("&lt;", "<")
		// + " 应先定义后使用:"
		// + toStringBuffer(list, start, end));
		// }
		// rightType = assignParamTypeMap.get(right.get("text"));
		// }
		// if (ITEM_TYPE_NUMBER.equals(rightType) == false
		// || ITEM_TYPE_NUMBER.equals(leftType) == false) {
		// throw new Exception("表达式错误："
		// + text.replace("&gt;", ">").replace("&lt;", "<")
		// + " 的左边和右边都应有一个”数值型“操作数（参数）:"
		// + toStringBuffer(list, start, end));
		// }
		// Map<String, Object> item = new HashMap<String, Object>(2, 1);
		// stack.push(item);// 计算结果入栈
		// if ("+".equals(name) || "-".equals(name) || "*".equals(name)
		// || "/".equals(name)) {
		// item.put("type", ITEM_TYPE_NUMBER);
		// } else {
		// item.put("type", ITEM_TYPE_BOOL);
		// }
		// continue;
		// } else {
		// // 操作数
		// stack.push(map);// 操作数需要判断常量的类型，因此需要全部的属性
		// }
		// }// 模拟计算后缀表达式
		for (int i = 0, len = postfixList.size(); i < len; i++) {
			Map map = postfixList.get(i);
			String type = (String) map.get("type");
			String name = (String) map.get("name");
			String text = (String) map.get("text");
			if (text == null || text.length() < 1) {
				text = name;
			}
			if (ITEM_TYPE_OP.equals(type) && opPriority.containsKey(name)) {
				// 操作符
				if (stack.size() < 2) {
					throw new Exception("表达式错误："
							+ name.replace("&gt;", ">").replace("&lt;", "<")
							+ " 的左边和右边都应有一个操作数（参数）:"
							+ toStringBuffer(list, start, end));
				}
				Map right = stack.pop();// 栈顶是右操作符
				Map left = stack.pop();
				String leftType = (String) left.get("type");
				String rightType = (String) right.get("type");

				if (ITEM_TYPE_CONST.equals(leftType)) {// 处理常量类型
					left.put("isconst", true);
					if (ITEM_TYPE_CONST.equals(rightType)) {
						right.put("isconst", true);
						leftType = this.guessExprType(left);
						rightType = this.guessExprType(right);
						if (leftType.equals(rightType) == false)
							throw new Exception("表达式错误：不能在两个不同类型的常量间进行运算");
					} else if ("expr".equals(rightType)) {
						if (assignParamTypeMap.containsKey(right.get("text")) == false) {
							throw new Exception("表达式错误："
									+ String.valueOf(right.get("text"))
											.replace("&gt;", ">").replace(
													"&lt;", "<") + " 应先定义后使用:"
									+ toStringBuffer(list, start, end));
						}
						rightType = assignParamTypeMap.get(right.get("text"));
					}
					if (ITEM_TYPE_BOOL.equals(rightType)
							|| ITEM_TYPE_NUMBER.equals(rightType)
							|| ITEM_TYPE_STRING.equals(rightType)) {
						leftType = rightType;
					}
				}
				if (ITEM_TYPE_CONST.equals(rightType)) {// 处理常量类型
					right.put("isconst", true);
					if (ITEM_TYPE_CONST.equals(leftType)) {
						left.put("isconst", true);
						leftType = this.guessExprType(left);
						rightType = this.guessExprType(right);
						if (leftType.equals(rightType) == false)
							throw new Exception("表达式错误：不能在两个不同类型的常量间进行运算");
					} else if ("expr".equals(leftType)) {
						if (assignParamTypeMap.containsKey(left.get("text")) == false) {
							throw new Exception("表达式错误："
									+ String.valueOf(left.get("text")).replace(
											"&gt;", ">").replace("&lt;", "<")
									+ " 应先定义后使用:"
									+ toStringBuffer(list, start, end));
						}
						leftType = assignParamTypeMap.get(left.get("text"));
					}
					if (ITEM_TYPE_BOOL.equals(leftType)
							|| ITEM_TYPE_NUMBER.equals(leftType)
							|| ITEM_TYPE_STRING.equals(leftType)) {
						rightType = leftType;
					}
				}

				if ("&&".equals(name) || "||".equals(name)) {
					if (ITEM_TYPE_BOOL.equals(leftType) == false
							|| ITEM_TYPE_BOOL.equals(rightType) == false) {
						throw new Exception("表达式错误："
								+ text.replace("&gt;", ">")
										.replace("&lt;", "<")
								+ " 的左边和右边都应有一个”是否型“操作数（参数）:"
								+ toStringBuffer(list, start, end));
					}
					Map<String, Object> item = new HashMap<String, Object>(2, 1);
					stack.push(item);// 计算结果入栈
					item.put("type", ITEM_TYPE_BOOL);
					left.put("type", leftType);
					right.put("type", rightType);
					Map<String, Object> genResult = gen(left, name, right);
					item.put(javaExpr, genResult.get(javaExpr));
					item.put("type", genResult.get("type"));
					continue;
				}// 并且、或者处理完毕
				if ("notcontains,notstartswith,notendswith,containsdict"
						.contains(name.toLowerCase())) {
					if (ITEM_TYPE_STRING.equals(leftType) == false
							|| ITEM_TYPE_STRING.equals(rightType) == false) {
						throw new Exception("表达式错误：" + text
								+ " 的左边和右边都应有一个”文本型“操作数（参数）:"
								+ toStringBuffer(list, start, end));
					}
					Map<String, Object> item = new HashMap<String, Object>(2, 1);
					stack.push(item);// 计算结果入栈
					item.put("type", ITEM_TYPE_BOOL);
					left.put("type", leftType);
					right.put("type", rightType);
					Map<String, Object> genResult = gen(left, name, right);
					item.put(javaExpr, genResult.get(javaExpr));
					item.put("type", genResult.get("type"));
					continue;
				}// 文字包含、不包含、开始于、结束于处理完毕
				if ("==".equals(name) || "!=".equals(name)) {
					if (leftType.equals(rightType) == false) {
						throw new Exception("表达式错误："
								+ text.replace("&gt;", ">")
										.replace("&lt;", "<")
								+ " 的左边和右边的操作数（参数）类型需要相同:"
								+ toStringBuffer(list, start, end));
					} else {
						Map<String, Object> item = new HashMap<String, Object>(
								2, 1);
						stack.push(item);// 计算结果入栈
						item.put("type", ITEM_TYPE_BOOL);
						left.put("type", leftType);
						right.put("type", rightType);
						Map<String, Object> genResult = gen(left, name, right);
						item.put(javaExpr, genResult.get(javaExpr));
						item.put("type", genResult.get("type"));
						continue;
					}
				}// 等于、不等于处理完毕

				// 数学运算：操作数必须为数值
				if (ITEM_TYPE_NUMBER.equals(rightType) == false
						|| ITEM_TYPE_NUMBER.equals(leftType) == false) {
					throw new Exception("表达式错误："
							+ text.replace("&gt;", ">").replace("&lt;", "<")
							+ " 的左边和右边都应有一个”数值型“操作数（参数）:"
							+ toStringBuffer(list, start, end));
				}
				Map<String, Object> item = new HashMap<String, Object>(2, 1);
				stack.push(item);// 计算结果入栈
				if ("+".equals(name) || "-".equals(name) || "*".equals(name)
						|| "/".equals(name)) {
					item.put("type", ITEM_TYPE_NUMBER);
				} else {
					item.put("type", ITEM_TYPE_BOOL);
				}
				left.put("type", leftType);
				right.put("type", rightType);
				Map<String, Object> genResult = gen(left, name, right);
				item.put(javaExpr, genResult.get(javaExpr));
				item.put("type", genResult.get("type"));
				continue;
			} else {
				// 操作数
				stack.push(map);// 操作数需要判断常量的类型，因此需要全部的属性
			}
		}

		if (stack.size() != 1) {
			throw new Exception("表达式错误：操作数（参数）和操作符个数不匹配:"
					+ toStringBuffer(list, start, end));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Map map2 = stack.peek();
		String type = map2.get("type").toString();
		map.put("resulttype", type);
		if (ITEM_TYPE_CONST.equals(type)) {// 常量：说明当前校验的表达式只有唯一的操作数，没有操作符
			if (start != end) {
				throw new Exception("表达式错误(常量操作数:永远不会执行到此):"
						+ toStringBuffer(list, start, end));// 不会发生：永远不会执行到此
			}
			String tmptype = this.guessExprType(map2);
			map.put("resulttype", tmptype);
			if (ITEM_TYPE_STRING.equals(tmptype)) {
				map.put(javaExpr, "\"" + map2.get("text") + "\"");
			} else if (ITEM_TYPE_BOOL.equals(tmptype)) {
				map.put(javaExpr, "toBool(\"" + map2.get("text") + "\")");
			} else if (ITEM_TYPE_NUMBER.equals(tmptype)) {
				map.put(javaExpr, "toBigDecimal(\"" + map2.get("text") + "\")");
			}
		}
		if ("expr".equals(type)) {
			if (assignParamTypeMap.containsKey(map2.get("text")) == false) {
				throw new Exception("表达式错误："
						+ String.valueOf(map2.get("text")).replace("&gt;", ">")
								.replace("&lt;", "<") + " 应先定义后使用:"
						+ toStringBuffer(list, start, end));
			}
			map.put("resulttype", assignParamTypeMap.get(map.get("text")));
		}
		type = map.get("resulttype").toString();
		if (map.containsKey(javaExpr) == false) {
			if (map2.containsKey(javaExpr)) {
				map.put(javaExpr, map2.get(javaExpr));
			} else {// 只有一项的表达式
				String expr = gen(map2, null, null).get(javaExpr).toString();
				map.put(javaExpr, expr);
			}
		}

		return map;
	}

	private static final String javaExpr = "javaExpr";

	private Map<String, Object> gen(Map param1, String op, Map param2)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String type = param1.get("type").toString();
		Object left = param1.get(javaExpr);
		// if (null != param1.get("dictTypeId") || (param2 != null && null
		// !=param2.get("dictTypeId"))) {
		// System.out.println(); //debug
		// }
		if (null == left) {
			if (param1.containsKey("funcname")) {
				left = generateJavaCodeFunc(param1);
			} else {
				String name = null;
				if (null != param1.get("name"))
					name = param1.get("name").toString();
				else if (null != param1.get("entext"))
					name = param1.get("entext").toString();
				else if (null != param1.get("text"))
					name = param1.get("text").toString();

				if (param1.get("isconst") == null) {
					name = getParamName(name, type, String.valueOf(param1
							.get("numbertype")));
					if (ITEM_TYPE_BOOL.equals(type)) {
						left = name;
					} else if (ITEM_TYPE_NUMBER.equals(type)) {
						left = "new BigDecimal(String.valueOf(" + name + "))";
					} else {
						left = name;
					}
				} else {
					if (ITEM_TYPE_BOOL.equals(type)) {
						left = "toBool(\"" + name + "\")";
					} else if (ITEM_TYPE_NUMBER.equals(type)) {
						left = "new BigDecimal(\"" + name + "\")";
					} else {
						left = "\"" + name + "\"";
					}
				}
			}
		}
		map.put(javaExpr, left);
		map.put("type", type);
		if (StringUtils.isEmpty(op))
			return map;

		op = op.replace("&gt;", ">").replace("&lt;", "<");
		Object right = param2.get(javaExpr);
		String type2 = param2.get("type").toString();
		if (null == right) {
			if (param2.containsKey("funcname")) {
				right = generateJavaCodeFunc(param2);
			} else {
				String name = null;
				if (null != param2.get("name"))
					name = param2.get("name").toString();
				else if (null != param2.get("entext"))
					name = param2.get("entext").toString();
				else if (null != param2.get("text"))
					name = param2.get("text").toString();

				if (param2.get("isconst") == null) {
					name = getParamName(name, type, String.valueOf(param2
							.get("numbertype")));

					if (ITEM_TYPE_BOOL.equals(type2)) {
						right = name;
					} else if (ITEM_TYPE_NUMBER.equals(type2)) {
						right = "new BigDecimal(String.valueOf(" + name + "))";
					} else {
						right = name;
					}
				} else {
					if (ITEM_TYPE_BOOL.equals(type2)) {
						right = "toBool(\"" + name + "\")";
					} else if (ITEM_TYPE_NUMBER.equals(type2)) {
						right = "new BigDecimal(\"" + name + "\")";
					} else {
						right = "\"" + name + "\"";
					}
				}
			}
		}
		if ("+-*/".contains(op)) {
			if (ITEM_TYPE_NUMBER.equals(type2) && ITEM_TYPE_NUMBER.equals(type)) {
				String expr = "";
				if ("-".equals(op)) {
					expr = left + ".subtract(" + right + ")";
				} else if ("*".equals(op)) {
					expr = left + ".multiply(" + right + ")";
				} else if ("/".equals(op)) {
					expr = left + ".divide(" + right
							+ ", 16, BigDecimal.ROUND_DOWN)"; // 除法计算过程中保留16位小数，不进行四舍五入，直接截断
				} else {
					expr = left + ".add(" + right + ")";
				}

				map.put(javaExpr, expr);
				map.put("type", ITEM_TYPE_NUMBER);
				return map;
			} else {
				throw new Exception("加减乘除只能在数值间进行");
			}
		}
		if ("&&||".contains(op)) {
			if (ITEM_TYPE_BOOL.equals(type2) && ITEM_TYPE_BOOL.equals(type)) {
				String expr = "(" + left + " " + op + " " + right + ")";
				map.put(javaExpr, expr);
				map.put("type", ITEM_TYPE_BOOL);
				return map;
			} else {
				throw new Exception("“并且、或者”两边必须是是否型参数");
			}
		}
		if ("notcontains,notstartswith,notendswith,containsdict".contains(op
				.toLowerCase())) {
			if (ITEM_TYPE_STRING.equals(type2) && ITEM_TYPE_STRING.equals(type)) {
				String expr = op + "(" + left + "," + right + ")"; // 模板中的函数
				map.put(javaExpr, expr);
				map.put("type", ITEM_TYPE_BOOL);
				return map;
			} else {
				throw new Exception("“包含、不包含、开始于、结尾于”等两边必须是文本型参数");
			}
		}
		if ("!==".contains(op)) {
			map.put("type", ITEM_TYPE_BOOL);
			String prefix = "";
			if (op.startsWith("!"))
				prefix = "!";
			if (ITEM_TYPE_BOOL.equals(type2) && ITEM_TYPE_BOOL.equals(type)) {
				// 2014.08.30 wangshichun start
				String expr = "((" + left + ") " + op + " (" + right + "))";
				map.put(javaExpr, expr);
				return map;
				// 2014.08.30 wangshichun end
			} else if (ITEM_TYPE_NUMBER.equals(type2)
					&& ITEM_TYPE_NUMBER.equals(type)) {// 等于、不等于判断过程中保留16位小数，不进行四舍五入，直接截断
				String expr = left
						+ ".setScale(16, BigDecimal.ROUND_DOWN).equals("
						+ right + ".setScale(16, BigDecimal.ROUND_DOWN))";
				if (prefix.length() > 0)
					expr = "Boolean.valueOf(" + expr + " == false)";
				else
					expr = "Boolean.valueOf(" + expr + ")";
				map.put(javaExpr, expr);
				return map;
			} else if (ITEM_TYPE_STRING.equals(type2)
					&& ITEM_TYPE_STRING.equals(type)) {
				String expr = left + ".equals(" + right + ")";
				if (prefix.length() > 0)
					expr = "Boolean.valueOf(" + expr + " == false)";
				else
					expr = "Boolean.valueOf(" + expr + ")";
				map.put(javaExpr, expr);
				return map;
			} else {
				throw new Exception("“等于、不等于”两边必须是同类型参数");
			}
		}
		// 2014.08.30 wangshichun start
		if (op.startsWith("~~")) {
			map.put("type", ITEM_TYPE_BOOL);
			if (!ITEM_TYPE_NUMBER.equals(type2)
					|| !ITEM_TYPE_NUMBER.equals(type)) {
				throw new Exception("“整数部分等于、取2位小数等于、取2位小数等于”两边必须是数值型参数");
			}

			int num = 16; // 保留的小数位数
			if (op.length() == 3) {
				num = Integer.valueOf(op.substring(2));
				op = "==";
			} else if (op.length() > 3) {
				num = Integer.valueOf(op.substring(2, 3));
				op = op.substring(3);
			}

			if ("!==".contains(op)) {
				String expr = left + ".setScale(" + num
						+ ", BigDecimal.ROUND_DOWN).equals(" + right
						+ ".setScale(" + num + ", BigDecimal.ROUND_DOWN))";
				if (op.equals("!="))
					expr = "Boolean.valueOf(" + expr + " == false)";
				else
					expr = "Boolean.valueOf(" + expr + ")";
				map.put(javaExpr, expr);
				return map;
			} else {
				// >,>=,<,<=
				String expr = "(" + left + ").setScale(" + num
						+ ", BigDecimal.ROUND_DOWN).compareTo(" + right
						+ ".setScale(" + num + ", BigDecimal.ROUND_DOWN)) "
						+ op + "0";
				map.put(javaExpr, expr);
				return map;
			}
		}
		// 2014.08.30 wangshichun end
		// >,>=,<,<=
		map.put("type", ITEM_TYPE_BOOL);
		if (ITEM_TYPE_NUMBER.equals(type2) && ITEM_TYPE_NUMBER.equals(type)) {
			String expr = "(" + left
					+ ").setScale(16, BigDecimal.ROUND_DOWN).compareTo("
					+ right + ".setScale(16, BigDecimal.ROUND_DOWN)) " + op
					+ "0";
			map.put(javaExpr, expr);
			return map;
		} else {
			throw new Exception("“大于、小于”两边必须是数值型参数");
		}

		// return map;
	}

	// 每次调用generateJavaCodeExpr以计算表达式时清空此map
	private Map<Integer, Map<String, Object>> exprMap = new HashMap<Integer, Map<String, Object>>();

	// 生成表达式
	private StringBuffer generateJavaCodeExpr(List list, int start, int end)
			throws Exception {
		exprMap.clear();

		StringBuffer sb = new StringBuffer();
		// 处理括号
		Stack<Integer> braket = new Stack<Integer>();
		for (int i = start; i <= end; i++) {
			Map map = (Map) list.get(i);
			Object name = map.get("name");
			if ("(".equals(name)) {
				braket.push(i);
				continue;
			}
			if (")".equals(name)) {
				if (braket.isEmpty()) {
					throw new Exception("括号不匹配：“)”应有对应的“(”");
				}
				int idx = braket.pop();
				if (idx + 1 > end || i - 1 < idx + 1) {
					throw new Exception("括号不匹配：");
				}
				StringBuffer tmp = this.generateJavaCodeBraketed(list, idx + 1,
						i - 1);
				Map<String, Object> m = new HashMap<String, Object>(3, 1);
				m.put("end", i - 1);
				m.put("expr", tmp);
				exprMap.put(idx + 1, m);

				// String tmptype = this.guessExprType(list, idx + 1, i - 1);
				String tmptype = this.validateExpr(list, idx + 1, i - 1).get(
						"resulttype").toString();
				m.put("type", tmptype);
				continue;
			}
		}
		if (braket.isEmpty() == false) {
			throw new Exception("括号不匹配：“(”应有对应的“)”");
		}

		sb.append(generateJavaCodeBraketed(list, start, end));

		return sb;
	}

	// 处理逻辑运算：并且、或者
	private StringBuffer generateJavaCodeBraketed(List list, int start, int end)
			throws Exception {
		StringBuffer sb = new StringBuffer();

		int pos = start;
		for (int i = start; i <= end; i++) {
			Map map = (Map) list.get(i);
			Object name = map.get("name");
			if (!"&&".equals(name) && !"||".equals(name)) {
				continue;
			}
			if (i <= pos) {// 并且、或者之前至少有一个参数
				throw new Exception("“并且、或者”之前应有逻辑判断");
			}
			// 处理“并且、或者”之前的部分
			{
				// 判断是否在括号内
				Map m = (Map) list.get(pos);// 两个左括号是否会有问题？
				if ("(".equals(m.get("name"))) {
					// 左括号
					m = exprMap.get(pos + 1);
					if (null != m) {
						int nd = Integer.valueOf(m.get("end").toString());
						if (i - 1 <= nd) {
							// 在括号范围内，已计算
							i = nd + 1; // 移动到右括号的位置
							continue;
						}
					}
				}
			}
			StringBuffer tmp = this.generateJavaCodeLogiced(list, pos, i - 1);
			Map<String, Object> m = new HashMap<String, Object>(3, 1);
			m.put("end", i - 1);
			m.put("expr", tmp);
			exprMap.put(pos, m);
			{
				// String tmptype = this.guessExprType(list, pos, i - 1);
				String tmptype = this.validateExpr(list, pos, i - 1).get(
						"resulttype").toString();
				m.put("type", tmptype);
				if (ITEM_TYPE_BOOL.equals(tmptype) == false) {
					throw new Exception("“并且、或者”之前应有逻辑判断，并得到“是否型”结果，而不是："
							+ tmptype);
				}
			}

			// 处理“并且、或者”之后的部分
			pos = i + 1;
		}
		if (pos != start) {
			// 存在“并且、或者”
			if (end < pos) {
				throw new Exception("“并且、或者”之后应有逻辑判断");
			}
			// 处理“并且、或者”之后的部分
			{
				StringBuffer tmp = this.generateJavaCodeLogiced(list, pos, end);
				Map<String, Object> m = new HashMap<String, Object>(3, 1);
				m.put("end", end);
				m.put("expr", tmp);
				exprMap.put(pos, m);

				// String tmptype = this.guessExprType(list, pos, end);
				String tmptype = this.validateExpr(list, pos, end).get(
						"resulttype").toString();
				m.put("type", tmptype);
				if (ITEM_TYPE_BOOL.equals(tmptype) == false) {
					throw new Exception("“并且、或者”之后应有逻辑判断，并得到“是否型”结果，而不是："
							+ tmptype);
				}
			}

			for (int i = start; i <= end; i++) {
				if (exprMap.containsKey(i)) {
					Map<String, Object> m = exprMap.get(i);
					i = Integer.valueOf(m.get("end").toString());
					sb.append(m.get("expr"));
					continue;
				}
				Map map = (Map) list.get(i);
				Object name = map.get("name");
				sb.append(" ");
				sb.append(name); // 操作符两边空格，此处的操作符只能是并且、或者
				if (!"&&".equals(name) && !"||".equals(name)) {
					throw new Exception("校验时错误！");
				}
				sb.append(" ");
			}
		} else {
			// 不存在“并且、或者”
			return generateJavaCodeLogiced(list, start, end);
		}

		return sb;
	}

	// 将表达式直接输出为字符串，用于调试
	private StringBuffer toStringBuffer(List list, int start, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = start; i <= end; i++) {
			if (exprMap.containsKey(i)) {
				Map<String, Object> m = exprMap.get(i);
				i = Integer.valueOf(m.get("end").toString());
				sb.append(m.get("expr"));
				continue;
			}
			Map map = (Map) list.get(i);
			Object name = map.get("name");
			Object text = map.get("text");
			if (null != name) {
				sb.append(name);
			} else {
				sb.append(text);
			}
		}
		return sb;
	}

	// 用于校验表达式
	// private boolean expect(List list, int pos, int idx, String type)
	// throws Exception {
	// if (idx < 0 || pos < 0 || pos + idx > list.size() || null == type)
	// return false;
	//
	// int cnt = 0;
	// for (int i = pos, len = list.size(); i < len; i++) {
	// cnt++;
	// if (cnt > idx)
	// break;
	// if (exprMap.containsKey(i)) {
	// Map<String, Object> m = exprMap.get(i);
	// if (cnt == idx) {
	// if (type.equals(m.get("type")))
	// return true;
	// return false;
	// }
	// i = Integer.valueOf(m.get("end").toString()) + 1;// 加1表示跳过括号、并且、或者
	// continue;
	// }
	// if (cnt == idx) {
	// Map m = (Map) list.get(i);
	// Object tmptype = m.get("type");
	// if (ITEM_TYPE_CONST.equals(m.get("type"))) {
	// // tmptype = this.guessExprType(list, i, i);
	// tmptype = this.validateExpr(list, i, i);
	// }
	// if (type.equals(tmptype))
	// return true;
	// return false;
	// }
	// }
	//
	// return true;
	// }

	// 处理逻辑判断：> < >= <= == != 包含文字 不包含文字 以 不以 开始 结束
	private StringBuffer generateJavaCodeLogiced(List list, int start, int end)
			throws Exception {
		log.debug("表达式：" + toStringBuffer(list, start, end));
		// if ("测试1==是".equals(toStringBuffer(list, start, end).toString())) {
		// System.out.println();
		// }
		StringBuffer sb = new StringBuffer();
		// String pretype = null; // 前一业务参数的类型
		for (int i = start; i <= end; i++) {
			if (exprMap.containsKey(i)) {

				Map<String, Object> m = exprMap.get(i);
				// String type = m.get("type").toString();
				// pretype = type;
				i = Integer.valueOf(m.get("end").toString());
				sb.append(m.get("expr"));
				continue;
			}
			Map map = (Map) list.get(i);
			Object name = map.get("name");
			Object type = map.get("type");
			Object text = map.get("text");
			Object funcname = map.get("funcname");
			if ("expr".equals(type)) {
				// 处理参数定义，已定义的参数在使用时需要判断其实际类型
				if (assignParamTypeMap.containsKey(text)) {
					type = assignParamTypeMap.get(text);
				} else {
					throw new Exception("“" + text + "”应先进行定义，然后再使用");
				}
			} else if (null != funcname) {
				// 决策树的非叶子节点：调用特定规则
				// if (funcname.toString().endsWith("Bool")) {
				// type = ITEM_TYPE_BOOL;
				// } else if (funcname.toString().endsWith("Str")) {
				// type = ITEM_TYPE_NUMBER;
				// } else {
				// type = ITEM_TYPE_STRING;
				// }
			}
			sb.append(" "); // 操作符或参数前加入空格
			if (ITEM_TYPE_OP.equals(type)) {
				// 操作符
				if (i == start) {
					if ("(".equals(name)) {
						sb.append(name);
						continue;
					}
					throw new Exception("表达式中，第一个位置只能为业务参数");
				}
				if (i == end) {
					if (")".equals(name)) {
						sb.append(name);
						continue;
					}
					throw new Exception("表达式中，最后一个位置只能为业务参数");
				}
				if (")".equals(name) || "(".equals(name)) {
					sb.append(name);
					continue;
				}
				// if ("==".equals(name) || "!=".equals(name)) {
				// boolean flg = this.expect(list, i + 1, 1, ITEM_TYPE_NUMBER);
				// if (flg) {// 后一参数为数值
				// // 判断前一参数是否为数值
				// flg = this.expect(list, i - 1, 1, ITEM_TYPE_NUMBER);
				// if (flg == false && !ITEM_TYPE_NUMBER.equals(pretype)) {
				// throw new Exception("“"
				// + name.toString().replace("&lt;", "<")
				// + "”后已有一个数值型业务参数，则其前面只能为数值型业务参数");
				// }
				// } else {
				// flg = this.expect(list, i + 1, 1, ITEM_TYPE_BOOL);
				// if (flg) {// 后一参数为是否型
				// flg = this.expect(list, i - 1, 1, ITEM_TYPE_BOOL);
				// if (flg == false && !ITEM_TYPE_BOOL.equals(pretype)) {
				// // throw new Exception("“"
				// // + name.toString().replace("&lt;", "<")
				// // + "”后已有一个是否型业务参数，则其前面只能为是否型业务参数");
				// }
				// } else {// 后一参数为文本型
				// flg = this.expect(list, i - 1, 1, ITEM_TYPE_STRING);
				// if (flg == false
				// && !ITEM_TYPE_STRING.equals(pretype)) {
				// throw new Exception("“"
				// + name.toString().replace("&lt;", "<")
				// + "”后已有一个文本型业务参数，则其前面只能为文本型业务参数");
				// }
				// }
				// }
				// } else {
				// boolean flg = this.expect(list, i + 1, 1, ITEM_TYPE_NUMBER);
				// if (flg == false) {
				// throw new Exception("“"
				// + name.toString().replace("&lt;", "<")
				// + "”后只能为数值型业务参数");
				// }
				// }
				// pretype = null;
			}
			if (ITEM_TYPE_CONST.equals(type)) {
				// String tmptype = this.guessExprType(list, i, i);
				String tmptype = this.validateExpr(list, i, i)
						.get("resulttype").toString();
				if (ITEM_TYPE_STRING.equals(tmptype)) {
					if ("null".equals(text))
						text = "null";
					else
						text = "\"" + text + "\".intern()";
				} else if (ITEM_TYPE_BOOL.equals(tmptype)) {
					text = "是".equals(text) ? "true" : "false";
				}
				sb.append(text);
				continue;
			}
			Object numbertype = map.get("numbertype");
			Object entext = map.get("entext");
			if (null != entext && entext.toString().length() > 0) {
				text = entext;// 以英文名称为主
			}
			if (null != name) {
				if (null != text) {
					String paramName = this.paramNameMap.get(text);
					if (null != paramName && paramName.startsWith("value")) {
						// 业务参数的自动生成的变量都是value开头的
						sb.append(paramName); // 业务参数
					} else {
						sb.append(name.toString().replace("&lt;", "<")); // 操作符
					}
				} else {
					sb.append(name.toString().replace("&lt;", "<")); // 操作符
				}
			} else if (null != text) {
				if (null != map.get("funcname")) {
					sb.append(this.generateJavaCodeFunc(map));
				} else {
					String paramName = getParamName(text.toString(), type
							.toString(), null == numbertype ? null : numbertype
							.toString());
					sb.append(paramName); // 业务参数
				}
			}
		}

		return sb;
	}

	private StringBuffer generateJavaCodeFunc(Map map) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(map.get("funcname"));
		sb.append("(");
		List args = (List) map.get("args");
		if (null != args && args.size() > 0) {
			if ("paramNotExist".equals(map.get("funcname"))
					|| "paramNull".equals(map.get("funcname"))) {
				if (args.size() == 1) {
					Map arg = (Map) args.get(0);
					List val = (List) arg.get("val");
					if (val.size() == 1) {
						arg = (Map) val.get(0);
						if (null != arg.get("entext")
								&& !"".equals(arg.get("entext"))) {
							sb.append("\"").append(arg.get("entext")).append(
									"\")");
							return sb;
						}
					}
				}
			}
			for (int i = 0, len = args.size(); i < len; i++) {
				if (i > 0)
					sb.append(",");
				Map arg = (Map) args.get(i);
				List items = (List) arg.get("val");

				Object type = arg.get("type");
				// DecisionUtil t = new DecisionUtil();
				DecisionUtil t = this;
				Map<String, Object> tmpmap = t.validateExpr(items, 0, items
						.size() - 1);
				t.assignMap = this.assignMap;
				t.assignParamTypeMap = this.assignParamTypeMap;
				t.paramNameMap = this.paramNameMap;
				t.paramTypeMap = this.paramTypeMap;
				t.paramNum = this.paramNum;

				if (ITEM_TYPE_BOOL.equals(type)) {
					// toBool是自定义函数
					sb.append("toBool(");
					// sb.append(t.generateJavaCodeExpr(items, 0, items.size() -
					// 1));
					sb.append(tmpmap.get(javaExpr));
					sb.append(")");
					this.paramNum = t.paramNum;
					continue;
				}
				if (ITEM_TYPE_NUMBER.equals(type)) {
					Object numbertype = map.get("numbertype");
					// if ("long".equals(numbertype)) {
					// sb.append("toLong(");
					// } else {
					// sb.append("toDouble(");
					// }
					if ("long".equals(numbertype)) {
						sb.append("toBigDecimal(");
						sb.append(tmpmap.get(javaExpr));
						sb.append(").longValue()");
					} else {
						sb.append("toBigDecimal(");
						sb.append(tmpmap.get(javaExpr));
						sb.append(").doubleValue()");
					}
					// sb.append(t
					// .generateJavaCodeExpr(items, 0, items.size() - 1));
					// sb.append(tmpmap.get(javaExpr));
					// sb.append(")");
					this.paramNum = t.paramNum;
					continue;
				}
				// 处理文本
				sb.append("String.valueOf(");
				// sb.append(t.generateJavaCodeExpr(items, 0, items.size() -
				// 1));
				sb.append(tmpmap.get(javaExpr));
				sb.append(").intern()");
				this.paramNum = t.paramNum;
			}
		}
		sb.append(")");

		return sb;
	}

	public static Object xml2bean(String xml) throws Exception {
		if (null == xml || xml.length() < 10)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();
		Document doc = DocumentHelper.parseText(xml);
		// System.out.println(doc.asXML());
		Element ele = doc.getRootElement();
		List list = (List) xml2bean(ele);
		{
			// 决策树时，会出现没有默认的无条件叶子节点：规则结果为
			if (list.size() > 0) {
				Map rule = (Map) list.get(list.size() - 1);
				if ("expr".equals(rule.get("type")) == false) {// 开始节点子节点中，无默认节点（无进入条件）
					if ("assign".equals(rule.get("type"))) {
						List list2 = (List) rule.get("rules");
						if (null != list2 && list2.size() > 0) {// 决策树的非叶子节点
							rule = (Map) list2.get(list2.size() - 1);
							if ("expr".equals(rule.get("type")) == false) {
								// 无默认的return语句：规则结果为
								rule = new HashMap<String, Object>(2, 1);
								list2.add(rule);
								List<Object> items = new ArrayList<Object>(1);
								rule.put("type", "expr");
								rule.put("items", items);

								Map<String, Object> item = new HashMap<String, Object>(
										2, 1);
								items.add(item);
								item.put("type", ITEM_TYPE_CONST);
								item.put("text", "null");
							}
						}
					}
				}
			}
		}
		map.put(ele.getName(), list);
		// System.out.println(map);
		return map;
	}

	private static Object xml2bean(Element ele) throws Exception {
		boolean isDecisionTree = false;
		if (grantTable.get().containsKey("isDecisionTree")) {
			isDecisionTree = (Boolean) grantTable.get().get("isDecisionTree");
		} else {
			isDecisionTree = (ele.asXML().indexOf(" nodename=") > 0);// 决策树节点才有nodename属性
			grantTable.get().put("isDecisionTree", isDecisionTree);
		}

		if ("item".equals(ele.getName())) {
			// 表达式中的一项
			// 必须限于规则结果为、参数定义处理，因为type可能为expr
			Map<String, Object> map = new HashMap<String, Object>(7, 1);
			map.put("type", ele.attribute("type").getStringValue());
			// type="expr"表示引用自定义的参数，等于op时表示操作符，有funcname属性时表示函数否则为业务参数
			Attribute numbertype = ele.attribute("numbertype");
			if (null != numbertype) {
				map.put("numbertype", numbertype.getStringValue());
			}
			if (null != ele.attribute("text"))
				map.put("text", ele.attribute("text").getStringValue());
			Attribute name = ele.attribute("name");
			if (null != name) {
				map.put("name", name.getStringValue());
			}
			Attribute entext = ele.attribute("entext");
			if (null != entext) {// 参数的英文名称
				map.put("entext", entext.getStringValue());
			} else {
				map.put("entext", map.get("text"));
			}
			Attribute dictTypeId = ele.attribute("dicttypeid");
			if (null != dictTypeId) {
				map.put("dictTypeId", dictTypeId.getStringValue());
			}
			Attribute funcname = ele.attribute("funcname");
			if (null != funcname) {
				map.put("funcname", funcname.getStringValue());
				List itemEles = ele.element("args").elements();// 函数参数列表
				List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
				map.put("args", items);
				for (int i = 0, len = itemEles.size(); i < len; i++) {
					items.add(xml2bean((Element) itemEles.get(i)));
				}
			}
			return map;
		}

		if ("arg".equals(ele.getName())) {// 函数参数
			Map<String, Object> map = new HashMap<String, Object>(3, 1);
			map.put("type", ele.attribute("type").getStringValue());
			map.put("text", ele.attribute("text").getStringValue());
			List itemEles = ele.element("val").elements();// 参数表达式
			List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
			map.put("val", items);
			for (int i = 0, len = itemEles.size(); i < len; i++) {
				items.add(xml2bean((Element) itemEles.get(i)));
			}
			return map;
		}

		Attribute type = ele.attribute("type");
		Map<String, Object> map = new HashMap<String, Object>(5, 1);
		if (null != type && "line".equals(type.getStringValue())) {
			// 空行
			return null;
		} else if (null != type && "expr".equals(type.getStringValue())) {
			// 规则结果为
			map.put("type", "expr");
			if (null != ele.attribute("nodename"))
				map.put("text", ele.attributeValue("nodename"));// 决策树节点

			if (ele.element("expr") != null) {
				List itemEles = ele.element("expr").elements();
				List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
				map.put("items", items);
				for (int i = 0, len = itemEles.size(); i < len; i++) {
					items.add(xml2bean((Element) itemEles.get(i)));
				}
			} else {
				map.put("items", new ArrayList<Object>());
			}
			return map;
		} else if (null != type && "assign".equals(type.getStringValue())) {
			// 参数定义
			map.put("type", "assign");
			if (null != ele.attribute("text")) {
				map.put("text", ele.attribute("text").getStringValue());
			} else {
				map.put("text", ele.attributeValue("nodename"));// 决策树节点
			}

			// 表达式的每一项的处理，或决策树特定节点所调用的规则
			List itemEles = ele.element("expr").elements();
			List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
			map.put("items", items);
			for (int i = 0, len = itemEles.size(); i < len; i++) {
				items.add(xml2bean((Element) itemEles.get(i)));
			}

			// 决策树的特定节点下的子节点
			if (null != ele.element("rules")
					&& ele.element("rules").elements().size() > 0
					&& isDecisionTree) {
				List ruleEles = ele.element("rules").elements();
				List<Object> rules = new ArrayList<Object>(ruleEles.size() + 1);
				List<Object> rules2 = new ArrayList<Object>(ruleEles.size() + 1);
				map.put("rules", rules2);
				for (int i = 0, len = ruleEles.size(); i < len; i++) {
					rules.add(xml2bean((Element) ruleEles.get(i)));
				}
				// 调整顺序： 如果那么在最前面，赋值其次，其他的最后
				for (int i = 0, len = rules.size(); i < len; i++) {
					Map<String, Object> tmpmap = (Map<String, Object>) rules
							.get(i);
					if ("ifthen".equals(tmpmap.get("type")) == false)
						continue;
					rules2.add(tmpmap);
				}
				for (int i = 0, len = rules.size(); i < len; i++) {
					Map<String, Object> tmpmap = (Map<String, Object>) rules
							.get(i);
					if ("assign".equals(tmpmap.get("type")) == false)
						continue;
					rules2.add(tmpmap);
				}
				for (int i = 0, len = rules.size(); i < len; i++) {
					Map<String, Object> tmpmap = (Map<String, Object>) rules
							.get(i);
					if ("ifthen".equals(tmpmap.get("type"))
							|| "assign".equals(tmpmap.get("type")))
						continue;
					rules2.add(tmpmap);
				}
			}
			return map;
		} else if (null != type && "ifthen".equals(type.getStringValue())) {
			// 如果……那么……
			map.put("type", "ifthen");
			if (null != ele.attribute("nodename"))
				map.put("text", ele.attributeValue("nodename"));// 决策树节点

			// 如果部分
			List itemEles = ele.element("ifexpr").elements();
			List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
			map.put("items", items);
			for (int i = 0, len = itemEles.size(); i < len; i++) {
				items.add(xml2bean((Element) itemEles.get(i)));
			}

			if (null != ele.element("expr")
					&& ele.element("expr").elements().size() == 1
					&& isDecisionTree) {
				// 决策树节点所调用的规则
				itemEles = ele.element("expr").elements();
				items = new ArrayList<Object>(itemEles.size() + 1);
				for (int i = 0, len = itemEles.size(); i < len; i++) {
					items.add(xml2bean((Element) itemEles.get(i)));
				}

				List ruleEles = ele.element("thenexpr").element("rules")
						.elements();
				List<Object> rules = new ArrayList<Object>(ruleEles.size() + 1);
				List<Object> rules2 = new ArrayList<Object>(ruleEles.size() + 1);

				map.put("rules", rules);
				// 决策树的下级节点部分
				if (ruleEles.size() > 0) {
					// 有下级节点
					Map<String, Object> rule = new HashMap<String, Object>(4, 1);
					// 参数定义
					rule.put("type", "assign");
					rule.put("text", ele.element("expr").element("item")
							.element("args").element("arg").element("val")
							.element("item").attributeValue("text"));
					rule.put("items", items);
					rules.add(0, rule);

					for (int i = 0, len = ruleEles.size(); i < len; i++) {
						rules.add(xml2bean((Element) ruleEles.get(i)));
					}
					// 调整顺序： 结果为在最后
					for (int i = 0, len = rules.size(); i < len; i++) {
						Map<String, Object> tmpmap = (Map<String, Object>) rules
								.get(i);
						if ("expr".equals(tmpmap.get("type")))
							continue;
						rules2.add(tmpmap);
					}
					for (int i = 0, len = rules.size(); i < len; i++) {
						Map<String, Object> tmpmap = (Map<String, Object>) rules
								.get(i);
						if ("expr".equals(tmpmap.get("type")) == false)
							continue;
						rules2.add(tmpmap);
					}
					map.put("rules", rules2);
				} else {
					// 叶子节点
					Map<String, Object> rule = new HashMap<String, Object>(4, 1);
					// 规则结果为
					rule.put("type", "expr");
					rule.put("items", items);
					rules.add(0, rule);
				}
			} else {
				// 那么部分
				List ruleEles = ele.element("thenexpr").element("rules")
						.elements();
				List<Object> rules = new ArrayList<Object>(ruleEles.size() + 1);
				map.put("rules", rules);
				for (int i = 0, len = ruleEles.size(); i < len; i++) {
					Object obj = xml2bean((Element) ruleEles.get(i));
					if (null == obj)
						continue;
					rules.add(obj);
				}
			}
			return map;
		}

		// 处理大类规则
		List itemEles = ele.elements();
		List<Object> items = new ArrayList<Object>(itemEles.size() + 1);
		List<Object> rules2 = new ArrayList<Object>(itemEles.size() + 1);
		int exprcnt = 0;
		for (int i = 0, len = itemEles.size(); i < len; i++) {
			Object obj = xml2bean((Element) itemEles.get(i));
			if (null == obj)
				continue;
			items.add(obj);
			Map tmp = (Map) items.get(items.size() - 1);
			if ("expr".equals(tmp.get("type"))) {
				// 规则结果为
				exprcnt++;
				if (i != len - 1) {
					// 最后一个规则才能为“规则结果为”
					if (isDecisionTree) {
						throw new Exception("一个决策树节点的下级节点中，只能有一个节点是无进入条件的叶子节点");
					} else {
						boolean tmpflag = false;
						for (int j = i + 1; j < len; j++) {
							// 是否为空行
							obj = xml2bean((Element) itemEles.get(j));
							if (null != obj) { // 非空行
								tmpflag = true;
								break;
							}
						}
						if (tmpflag)
							throw new Exception(
									"一个规则中，只能有一个“规则结果为”。\\n一个“如果……那么……”中，“那么”部分只能有一个“规则结果为”");
					}
				}
			}
		}
		// 调整顺序： 规则结果为在最后
		for (int i = 0, len = items.size(); i < len; i++) {
			Map<String, Object> tmpmap = (Map<String, Object>) items.get(i);
			if ("expr".equals(tmpmap.get("type")))
				continue;
			rules2.add(tmpmap);
		}
		for (int i = 0, len = items.size(); i < len; i++) {
			Map<String, Object> tmpmap = (Map<String, Object>) items.get(i);
			if ("expr".equals(tmpmap.get("type")) == false)
				continue;
			rules2.add(tmpmap);
		}
		items = rules2;
		if (exprcnt != 1) {
			// 决策树时，无法判断是否有规则结果为，需要靠测试功能发挥作用
			if (isDecisionTree) {
				// 决策树
				// 可在此处自动加上”规则结果为“，用以给决策树增加默认的返回值为null的节点。
				// 也可不增加，因为如果决策树配置正确时，如果那么中的return语句会覆盖所有分支
			} else {
				throw new Exception(
						"一个规则中，有且只有一个“规则结果为”。\\n一个“如果……那么……”中，“那么”部分有且只有一个“规则结果为”");
			}
		}
		return items;
	}

	private static final int CACHE_INVALID_MILLI_SECOND = 20;

	private static final String isInBatchModeKey = "isInBatchMode";

	private static final String batchModeRulesKey = "batchModeRules";

	private static String PROCESS_INST_ID;// 流程id

	@Bizlet("流程专用，调用决策树进行业务流程授权判断")
	public HashMap<String, Object> bizFlowGrant(HashMap<String, Object> map,
			HashMap<String, Object> param, String processId) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();// 返回参数
		DecisionUtil.PROCESS_INST_ID = processId;
		if (null == map)
			map = new HashMap<String, Object>();
		if (null == param)
			param = new HashMap<String, Object>();
		/*
		 * if(param.containsKey("isBaowaiLoans1") == true &&
		 * param.get("isBaowaiLoans1") != null &&
		 * param.containsKey("isBaowaiLoans2") == true &&
		 * param.get("isBaowaiLoans2") != null){//是否内保外贷
		 * if(param.get("isBaowaiLoans1").toString() == "1" &&
		 * param.get("isBaowaiLoans2").toString() == "1" ){
		 * param.put("isBaowaiLoans","1"); }else{
		 * param.put("isBaowaiLoans","0"); } }
		 */
		// contract_term,期限
		// cycle_unit,期限单位 展期贷款原期限 ori_term 换算以月单位
		if (param.containsKey("contract_term") == false
				&& param.get("contract_term") != null
				&& param.containsKey("cycle_unit1") == false
				&& param.get("cycle_unit1") != null) {// 是否内保外贷
			Double term = 0.0;

			if (param.get("cycle_unit1") == "01") {
				term = Double.valueOf(param.get("contract_term").toString()) * 12;
			} else if (param.get("cycle_unit1") == "02") {
				term = Double.valueOf(param.get("contract_term").toString()) * 6;
			} else if (param.get("cycle_unit1") == "03") {
				term = Double.valueOf(param.get("contract_term").toString()) * 3;
			} else if (param.get("cycle_unit1") == "04") {
				term = Double.valueOf(param.get("contract_term").toString());
			}
			param.put("ori_term", term.toString());
		}

		if (param.containsKey("单一同业单笔金额") == false
				&& param.get("dy_biz_amt") != null) {
			param.put("单笔金额", param.get("dy_biz_amt"));
		}
		if (param.containsKey("平台担保单笔金额") == false
				&& param.get("pt_cust_amt") != null) {
			param.put("单笔金额", param.get("pt_cust_amt"));
		}
		if (param.containsKey("单户金额") == false && param.get("cust_amt") != null) {
			param.put("单户金额", Double
					.parseDouble(param.get("cust_amt") == null ? "0" : param
							.get("cust_amt").toString())
					+ Double.parseDouble(param.get("单笔金额") == null ? "0"
							: param.get("单笔金额").toString()));
		}
		if (param.get("biz_happen_cd2") != null
				&& param.get("biz_happen_cd") == null) {
			param.put("biz_happen_cd", param.get("biz_happen_cd2"));
		}
		if (param.containsKey("cust_num") == false
				&& param.get("cust_num") != null
				&& // 客户编号 （客户名称）
				// param.containsKey("#") == false && param.get("#") != null &&
				// //授权机构 （机构编号）
				param.containsKey("cust_amt2") == false
				&& param.get("cust_amt2") != null
				&& // 授权金额 （单户敞口金额）
				param.containsKey("biz_guaranty") == false
				&& param.get("biz_guaranty") != null
				&& // 担保方式
				param.containsKey("certificate_code") == false
				&& param.get("certificate_code") != null
				&& // 组织机构代码
				param.containsKey("biz_product_cd") == false
				&& param.get("biz_product_cd") != null
				&& // 业务品种
				// param.containsKey("biz_product_cd") == false &&
				// param.get("biz_product_cd") != null && // 币种
				param.containsKey("biz_term") == false
				&& param.get("biz_term") != null
				&& // 期限
				param.containsKey("cycle_unit") == false
				&& param.get("cycle_unit") != null
				&& // 期限单位
				param.containsKey("biz_happen_cd") == false
				&& param.get("biz_happen_cd") != null) { // 业务发生性质
			HashMap<String, Object> o = new HashMap<String, Object>();
			o.put("cust_num", param.get("cust_num"));
			o.put("certificate_code", param.get("certificate_code"));
			o.put("cust_amt2", param.get("cust_amt2"));
			o.put("biz_product_cd", param.get("biz_product_cd"));
			o.put("biz_happen_cd", param.get("biz_happen_cd"));

			Double term = 0.0;

			if (param.get("cycle_unit") == "01") {
				term = Double.valueOf(param.get("biz_term").toString()) * 12;
			} else if (param.get("cycle_unit") == "02") {
				term = Double.valueOf(param.get("biz_term").toString()) * 6;
			} else if (param.get("cycle_unit") == "03") {
				term = Double.valueOf(param.get("biz_term").toString()) * 3;
			} else if (param.get("cycle_unit") == "04") {
				term = Double.valueOf(param.get("biz_term").toString());
			}
			param.put("biz_term", term.toString());
			o.put("biz_term", term.toString());

			// Object[] rules =
			// DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
			// "com.bos.dataset.bizIsNameList.selectList", o);// 查询名单
			//				
			// getList(param, rules[0],"isAgreementList","1"); //抵消协议名单
			// isAgreementList
			// getList(param, rules[1],"isCustGrantList","2"); //isCustGrantList
			// 是否存量客户授权名单：
			// getList(param, rules[2],"isThreetypeGrantList","3");
			// //isThreetypeGrantList 是否后三类重组特别授权名单：
			// getList(param, rules[3],"isStateControlOrgList","4");
			// //isStateControlOrgList 是否优质国有控股担保机构名单：
			// getList(param, rules[4],"isBigCompanyList","5");
			// //isBigCompanyList 是否大优名单：
			// getList(param, rules[5],"isRiskRegroupList","6");
			// //isRiskRegroupList 是否前两类潜在重组化解名单：
			//				
			// if(rules[6].equals("8")){ //isMyelfStockholderList 是否本行关联方：
			// param.put("isMyelfStockholderList",1);
			// }else{
			// param.put("isMyelfStockholderList",2);
			// }

		}

		long time = System.nanoTime();
		HashMap<String, Object> grant = grantTable.get();
		grant.clear(); // 防止线程池缓存造成问题
		grant.put(isInBatchModeKey, Boolean.TRUE); // 批量模式：规则执行时（嵌套调用），不查找数据库判断是否发生更新

		DataObject rule = null;
		HashMap<String, Object> o = new HashMap<String, Object>();
		Object[] rules = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.decision.selectPunish", o);// 查询授权需要用到的所有规则
		HashMap<String, Object> batchModeRules = new HashMap<String, Object>();
		grant.put(batchModeRulesKey, batchModeRules);
		log.debug("授权所有规则查询时间：" + (System.nanoTime() - time) / 1000000 + "ms");
		time = System.nanoTime();

		Connection conn = null; // 数据连接源
		IDASSession session = null; // 用于操作实体
		try {
			conn = ConnectionHelper.getConnection(GitUtil.DEFAULT_DS_NAME);// GitUtil.DEFAULT_DS_NAME:默认数据源
			// 获取一个指定数据源名称的数据库连接代理
			session = DASManager.createDasSession(conn); // 根据Connection创建一个IDASSession，用于数据库操作
			List<DataObject> list = new ArrayList<DataObject>();// 规则

			DataObject ruleDecision = null; // 总行给分行授权的决策树
			// DataObject ruleDecision2 = null; // 分行转授权的决策树
			for (int i = 0, len = rules.length; i < len; i++) { // 循环查询的规则
				rule = (DataObject) rules[i];
				batchModeRules.put(rule.getString("rind"), rule); // 给嵌套调用的规则使用

				DataObject ruleCached = ruleCache.get(rule.get("rid"));
				if (null == ruleCached
						|| null == rule.getDate("updateTime")
						|| null == ruleCached.getDate("updateTime")
						|| rule.getDate("updateTime").getTime() > ruleCached
								.getDate("updateTime").getTime()) {
					// 规则内容已更新，需要刷新缓存
					session.expandLobProperty(rule, "rcontent");
					list.add(rule);
				}

				if (null == ruleDecision
						&& "业务决策树01".equals(rule.getString("rind"))) {
					ruleDecision = rule;
				}
				// else if (null == ruleDecision2
				// && "业务决策树02".equals(rule.getString("rind"))) {
				// ruleDecision2 = rule;
				// }
			}

			for (int i = 0, len = list.size(); i < len; i++) {// 更新缓存
				rule = list.get(i);
				map.put("rule", rule);
				updateRuleCache(map);// 更新单个规则的缓存
			}
			log.debug("授权所有规则缓存更新时间：" + (System.nanoTime() - time) / 1000000
					+ "ms");
			time = System.nanoTime();

			// 开始计算
			// 1、初始化数据
			HashMap<String, Object> org = new HashMap<String, Object>();
			String[] orgidsNoOk = DataContextUtil.getString(
					"m:userObject/attributes/parentorgids").split(",");
			String[] orgcodesNoOk = DataContextUtil.getString(
					"m:userObject/attributes/parentorgcodes").split(",");
			String[] orgnamesNoOk = DataContextUtil.getString(
					"m:userObject/attributes/parentorgnames").split(",");
			String[] orglevelsNoOk = DataContextUtil.getString(
					"m:userObject/attributes/parentorglevels").split(",");

			// 保存查出来的相关机构数据集合对象
			ArrayList<Object> objlist = new ArrayList<Object>();

			// 判断当前机构串中有几个是部门
			int len = 0;
			for (int i = 0; i < orgidsNoOk.length; i++) {
				Map<String, String> maptemp = new HashMap<String, String>();
				maptemp.put("orgid", orgidsNoOk[i]);
				Object[] ojbsNo = DatabaseExt
						.queryByNamedSql(
								GitUtil.DEFAULT_DS_NAME,
								"com.bos.process.processQuery.queryOrgWhereBunoYesOrNo",
								maptemp);
				// 把查出来的数据保存集合
				objlist.add(ojbsNo[0]);
				Map yesornomap = (Map) ojbsNo[0];
				String yesorno = yesornomap.get("YESORNO").toString();
				// 当前机构为部门则,len++
				if ("0".equals(yesorno)) {
					len++;
				}
			}
			// 准备好存全是机构的机构信息数组
			String[] orgids = new String[orgidsNoOk.length - len];
			String[] orgcodes = new String[orgidsNoOk.length - len];
			String[] orgnames = new String[orgidsNoOk.length - len];
			String[] orglevels = new String[orgidsNoOk.length - len];

			// 正确机构信息下标
			int indexOrg = 0;
			// 去除部门后的数组存放
			for (int ob = 0; ob < orgidsNoOk.length; ob++) {
				Map yesornomap = (Map) objlist.get(ob);
				String yesorno = yesornomap.get("YESORNO").toString();
				// 当前机构不是部门则,加入集合
				if ("1".equals(yesorno)) {
					orgids[indexOrg] = orgidsNoOk[ob];
					orgcodes[indexOrg] = orgcodesNoOk[ob];
					orgnames[indexOrg] = orgnamesNoOk[ob];
					orglevels[indexOrg] = orglevelsNoOk[ob];
					indexOrg++;
				}
			}

			String orgid = orgids[orgids.length - 1];// 总行机构ID
			String orgcode = orgcodes[orgcodes.length - 1];// 总行机构编号
			String orgname = orgnames[orgnames.length - 1];// 总行机构名称
			String orglevel = orglevels[orglevels.length - 1];// 总行机构级别

			org.put("orgid", orgid);
			org.put("orgcode", orgcode);
			org.put("orgname", orgname);
			org.put("orglevel", orglevel); // 默认总行机构

			// 2、判断当前是否为总行
			if (orgids.length < 2) {
				log.debug("当前机构为总行，授权判断不用执行，直接返回总行");
				result.put("result", org);
				log.debug("授权判断时间：" + (System.nanoTime() - time) / 1000000
						+ "ms");
				// 保存日志到数据库
				//System.out.println(log);
				insertRuleLog(param,org);
				return result;
			}

			// 3、计算总行是否给一级分行授权
			if (null == ruleDecision) {
				throw new Exception("出错！规则缓存查找失败（未找到授权决策树）！");
			}
			Class cls = null;
			if (null != ruleCache.get(ruleDecision.get("rid")))
				cls = (Class) ruleCache.get(ruleDecision.get("rid")).get("cls");
			if (null == cls)
				throw new Exception("出错！授权决策树编译结果为空：内容不正确，或不处于生效状态！");

			grant.put(currentOrgid, orgids[orgids.length - 2]); // 取到分行
			Object ruleObj = cls.newInstance();
			Method mt = cls.getMethod("exe", new Class[] { HashMap.class });
			Object obj = mt.invoke(ruleObj, new Object[] { param });

			if (obj == null || obj instanceof Boolean == false) {
				throw new Exception("出错！授权决策树计算结果应为是否型！");
			}
			Boolean flag = (Boolean) obj;
			if (flag) {
				// 分行有权限
				int orgindex = orgids.length - 2;
				org.put("orgid", orgids[orgindex]);
				org.put("orgcode", orgcodes[orgindex]);
				org.put("orgname", orgnames[orgindex]);
				org.put("orglevel", orglevels[orgindex]); // 一分有权限机构
			} else {
				// 分行无权限
				result.put("result", org);

				Field path = cls.getDeclaredField("path");
				List pathList = (List) path.get(ruleObj);
				log.debug("rule execute path:" + ArrayUtils.toString(pathList));
				result.put("path", pathList);
				log.debug("授权判断时间：" + (System.nanoTime() - time) / 1000000
						+ "ms");
				System.out.println(log);
				// 保存日志到数据库
				insertRuleLog(param,org);
				return result;
			}

			// 分行有权限
			if (orgids.length < 3) {
				// 当前机构为一级分行
				org.put("orgid", orgids[orgids.length - 2]);
				org.put("orgcode", orgcodes[orgcodes.length - 2]);
				org.put("orgname", orgnames[orgnames.length - 2]);
				org.put("orglevel", orglevels[orglevels.length - 2]);
				result.put("result", org);

				Field path = cls.getDeclaredField("path");
				List pathList = (List) path.get(ruleObj);
				log.debug("rule execute path:" + ArrayUtils.toString(pathList));
				result.put("path", pathList);
				log.debug("授权判断时间：" + (System.nanoTime() - time) / 1000000
						+ "ms");
				// 保存日志到数据库
				//System.out.println(log);
				insertRuleLog(param,org);
				return result;
			}

			// 4、计算分行是否往下转授权
			// if (null == ruleDecision2) {
			// throw new Exception("出错！规则缓存查找失败（未找到授权决策树）！");
			// }
			// cls = null;
			// if (null != ruleCache.get(ruleDecision2.get("rid")))
			// cls = (Class) ruleCache.get(ruleDecision2.get("rid")).get("cls");

			int q = orgids.length - 3;
			for (; q >= 0; q--) {
				String rorgid = orgids[q]; // 获取下级 机构id
				HashMap<String, Object> c = new HashMap<String, Object>();
				c.put("orgid", rorgid);
				// c.put("rind",);
				Object[] ruleCols = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.dataset.bizIsNameList.selectCol", c);// 查询规则

				DataObject ruleDecisionCol = null; // 授权的规则
				DataObject ruleCol = null;
				for (int i = 0; i < ruleCols.length; i++) {
					ruleCol = (DataObject) ruleCols[i];
					if (null == ruleDecisionCol) {
						ruleDecisionCol = ruleCol;
					}
					if (null != ruleCache.get(ruleDecisionCol.getString("RID")))
						cls = (Class) ruleCache.get(ruleDecisionCol.get("RID"))
								.get("cls");
					if (null == cls)
						throw new Exception("出错！授权规则编译结果为空：内容不正确，或不处于生效状态！");

					Object ruleObj1 = cls.newInstance();
					mt = cls.getMethod("exe", new Class[] { HashMap.class });
					obj = mt.invoke(ruleObj1, new Object[] { param });
					if (obj == null || obj instanceof Boolean == false) {
						throw new Exception("出错！转授权规则计算结果应为是否型！");
					}
					flag = (Boolean) obj;

					if (flag == false) { // 授权规则通过
						continue;
					}
					// 单户金额

					if (param.containsKey("单笔金额") == true
							&& param.get("biz_amt") != null) {
						// 单笔金额
						String tname = ruleDecisionCol.getString("TNAME");
						if ("单笔金额".equals(tname)) {
							// 单笔金额在阀值之内，有权限，在判断是否给下级机构转授权
							BigDecimal tvalue = new BigDecimal(ruleDecisionCol
									.get("TVALUE")
									+ "");
							BigDecimal custamt = new BigDecimal(param
									.get("单笔金额")
									+ "");
							if (tvalue.compareTo(custamt) == 0
									|| tvalue.compareTo(custamt) == 1) {
								org.put("orgid", orgids[q]);
								org.put("orgcode", orgcodes[q]);
								org.put("orgname", orgnames[q]);
								org.put("orglevel", orglevels[q]);
								break;
							} else {// 无权限，返回上级机构
								org.put("orgid", orgids[q + 1]);
								org.put("orgcode", orgcodes[q + 1]);
								org.put("orgname", orgnames[q + 1]);
								org.put("orglevel", orglevels[q + 1]);
								result.put("result", org);
								// Field path = cls.getDeclaredField("path");
								// List pathList = (List) path.get(ruleObj);
								// log.debug("rule execute path:" +
								// ArrayUtils.toString(pathList));
								// result.put("path", pathList);
								// 保存日志到数据库
								//System.out.println(log);
								insertRuleLog(param,org);
								return result;
							}
						}
					}
					if (param.containsKey("单户金额") == true
							&& param.get("cust_amt") != null) {

						String tname = ruleDecisionCol.getString("TNAME");
						if ("单户金额".equals(tname)) {
							// 单户金额在阀值之内，有权限，在判断是否给下级机构转授权
							BigDecimal tvalue = new BigDecimal(ruleDecisionCol
									.get("TVALUE")
									+ "");
							BigDecimal custamt = new BigDecimal(param
									.get("单户金额")
									+ "");
							if (tvalue.compareTo(custamt) == 0
									|| tvalue.compareTo(custamt) == 1) {
								org.put("orgid", orgids[q]);
								org.put("orgcode", orgcodes[q]);
								org.put("orgname", orgnames[q]);
								org.put("orglevel", orglevels[q]);
								break;
							} else {// 无权限，返回上级机构
								org.put("orgid", orgids[q + 1]);
								org.put("orgcode", orgcodes[q + 1]);
								org.put("orgname", orgnames[q + 1]);
								org.put("orglevel", orglevels[q + 1]);
								result.put("result", org);
								// Field path = cls.getDeclaredField("path");
								// List pathList = (List) path.get(ruleObj);
								// log.debug("rule execute path:" +
								// ArrayUtils.toString(pathList));
								// result.put("path", pathList);
								// 保存日志到数据库
								//System.out.println(log);
								insertRuleLog(param,org);
								return result;
							}
						}
					}

				}

			}

			/*
			 * int i = orgids.length - 3;
			 * 
			 * 
			 * 
			 * mt = cls.getMethod("exe", new Class[] { HashMap.class }); for (;
			 * i >= 0; i--) { grant.put(currentOrgid, orgids[i]); // 取到下级行
			 * ruleObj = cls.newInstance(); obj = mt.invoke(ruleObj, new
			 * Object[] { param }); if (obj == null || obj instanceof Boolean ==
			 * false) { throw new Exception("出错！转授权决策树计算结果应为是否型！"); } flag =
			 * (Boolean) obj; if (flag) { // 下级行有权限 org.put("orgid", orgids[i]);
			 * org.put("orgcode", orgcodes[i]); org.put("orgname", orgnames[i]);
			 * org.put("orglevel", orglevels[i]); } else { // 下级行无权限
			 * org.put("orgid", orgids[i + 1]); org.put("orgcode", orgcodes[i +
			 * 1]); org.put("orgname", orgnames[i + 1]); org.put("orglevel",
			 * orglevels[i + 1]); break; } }
			 */

			result.put("result", org);
			// Field path = cls.getDeclaredField("path");
			// List pathList = (List) path.get(ruleObj);
			// log.debug("rule execute path:" + ArrayUtils.toString(pathList));
			// result.put("path", pathList);
			// 保存日志到数据库
			//System.out.println(log);
			insertRuleLog(param,org);
			return result;
		} catch (Exception e) {
			log.error("执行规则（授权判断）时出错", e);
			if (null != e.getMessage()) {
				result.put("msg", e.getMessage());
			} else if (null != e.getCause()) {
				if (null != e.getCause().getMessage())
					result.put("msg", e.getCause().getMessage());
				else if (null != e.getCause().getCause())
					result.put("msg", e.getCause().getCause().getMessage());
			}

			String msg = "";
			if (null != result.get("msg"))
				msg = result.get("msg").toString();
			if (null != msg && msg.contains("Division is undefined")) {
				result.put("msg", "by zero");
			}
			if (null != msg && msg.contains("by zero")) {
				result.put("msg", "发生除零错误，请检查表达式中的除数是否为0:" + msg);
			}
		} finally {
			try {
				if (null != session)
					session.close();
			} catch (Exception e) {
			}
			try {
				if (null != conn && conn.isClosed() == false)
					conn.close();
			} catch (Exception e) {
			}
			grant.clear();
		}
		log.debug("授权判断时间：" + (System.nanoTime() - time) / 1000000 + "ms");
		// 保存日志到数据库
		//System.out.println(log);
		insertRuleLog(param,null);
		return result;

	}
	/**
	 * 把规则执行的结果参数保存数据库
	 * 
	 */
	public void insertRuleLog(HashMap<String, Object> param,HashMap<String, Object> org) {
		StringBuffer table = new StringBuffer();
		Set<String> set = param.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			if (str != "rcontent" && str != "rule") {
				table.append(str + "=" + param.get(str) + " ");
			}
		}
		HashMap<String, Object> log = new HashMap<String, Object>();// 日志参数
		Date date = new Date();
		Random r = new Random();
		String PROCESSID = DecisionUtil.PROCESS_INST_ID+ "-"
		+ date.toLocaleString()+"-"+r.nextInt(10000);
		log.put("PROCESSID", PROCESSID);
		log.put("LOGNAME", table.toString()+" rule="+DecisionUtil.rulelogResult+" org="+org.toString());
		log.put("RULE", DecisionUtil.ruleTableResult.toString());
		DecisionUtil.ruleTableResult = new StringBuffer();
		DecisionUtil.rulelogResult = new StringBuffer();
		DatabaseExt.executeNamedSql("default",
				"com.bos.pub.decision.insertRuleLogs", log);
	}
	/**
	 * 把规则执行的结果参数保存数据库
	 * 
	 */
	public void insertRuleLog(HashMap<String, Object> param,List  sb,Object rind) {
		StringBuffer map = new StringBuffer();
		map.append(rind+"="+sb+";");
		DecisionUtil.rulelogResult.append(map);
	}

	/**
	 * 解释执行：用于授权表名单
	 * 
	 * @param param
	 *            参数
	 * @param rules
	 *            名单
	 * @param listName
	 *            参数名
	 * @param count
	 *            判断值
	 * @return 计算结果
	 */
	private void getList(HashMap<String, Object> param, Object rule,
			String listName, String count) {
		if (!rule.equals(count)) {
			if (rule == null || rule == "") {
				param.put(listName, 1);
			} else {
				String[] str = rule.toString().split(",");
				String[] str1 = param.get("biz_guaranty").toString().split(",");
				int out = 0;
				if (str.length > str1.length) { // 包含 少可以，多不可以。。
					for (int i = 0; i < str.length; i++) {
						for (int j = 0; j < str1.length; j++) {
							if (str[i].equals(str1[j])) {
								out++;
							}
						}
					}

					if (str1.length == out) {
						param.put(listName, 1);
					} else {
						param.put(listName, 2);
					}
				}
			}
		} else {
			param.put(listName, 2);
		}
	}

	/**
	 * 解释执行：用于授权表
	 * 
	 * @param left
	 *            左操作数
	 * @param op
	 *            操作符
	 * @param right
	 *            右操作数
	 * @return 计算结果
	 */
	private static Object explainCompute(Object left, String op, Object right)
			throws Exception {
		System.out.println("left:" + left + ",op:" + op + ",right:" + right);
		if (left == null || right == null)
			return true;
		if ("大于等于小于等于不等于>=<==!=".contains(op)) { // && left instanceof Number
													// && right instanceof
													// Number
			BigDecimal bd1 = new BigDecimal(left.toString()).setScale(16);
			BigDecimal bd2 = new BigDecimal(right.toString()).setScale(16);
			int r = bd1.compareTo(bd2);
			if (("==".equals(op) || "<=".equals(op) || ">=".equals(op)
					|| "等于".equals(op) || "小于等于".equals(op) || "大于等于"
					.equals(op))
					&& r == 0)
				return true;
			if (r != 0 && ("!=".equals(op) || "不等于".equals(op)))
				return true;
			if (r < 0
					&& ("<=".contains(op) || "小于等于".equals(op) || "小于"
							.equals(op)))
				return true;
			if (r > 0
					&& (">=".contains(op) || "大于等于".equals(op) || "大于"
							.equals(op)))
				return true;
			return false;
		}

		return null;
	}

	@Bizlet("导入决策表")
	public static void readDecisionTable(String excelFile, Map param)
			throws Exception {
		File file = new File(excelFile);
		if (file.exists() == false) {
			throw new Exception("决策表文件不存在");
		}
		if (null == param)
			param = new HashMap();
		Map[] cols = new Map[0];
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(
				new FileInputStream(file)));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);// 第一行
		HSSFCell cell = row.getCell(0);
		String strCfg = cell.getStringCellValue();
		if (null == strCfg)
			strCfg = "";
		String[] config = strCfg.split("\n");
		int header = 0;
		int op = 0;
		int type = 0;
		int colCnt = 0;
		for (int i = 0, len = config.length; i < len; i++) {
			if (config[i].startsWith("标题行：")) {
				header = Integer.valueOf(config[i].replace("标题行：", ""));
				continue;
			}
			if (config[i].startsWith("操作符：")) {
				op = Integer.valueOf(config[i].replace("操作符：", ""));
				continue;
			}
			if (config[i].startsWith("类型：")) {
				type = Integer.valueOf(config[i].replace("类型：", ""));
				continue;
			}
			if (config[i].startsWith("列数：")) {
				colCnt = Integer.valueOf(config[i].replace("列数：", ""));
				continue;
			}
		}
		if (op == 0 || header == 0 || type == 0 || colCnt == 0) {
			throw new Exception("格式不正确，请在第一个单元格指定“标题行、操作符、类型、列数”");
		}
		row = sheet.getRow(header - 1);
		HSSFRow rowOp = sheet.getRow(op - 1);
		HSSFRow rowType = sheet.getRow(type - 1);
		// int cellsCount = row.getLastCellNum();
		int cellsCount = colCnt;
		cols = new Map[cellsCount - 1];
		for (int i = 0; i < cellsCount - 1; i++) { // 最后一列为结果列
			Map<String, Object> map = new HashMap<String, Object>();
			cols[i] = map;
			map.put("name", row.getCell(i).getStringCellValue().trim());
			map.put("op", rowOp.getCell(i).getStringCellValue().trim());
			map.put("type", rowType.getCell(i).getStringCellValue().trim());
		}

		String tid = GitUtil.genUUIDString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		if (param.get("tname") != null) {
			map.put("tname", param.get("tname"));
		} else {
			map.put("tname", "决策表"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(GitUtil.getBusiTimestamp()));
		}
		map.put("create_time", GitUtil.getBusiTimestamp());
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.decision.insertDeciTable", map); // 插入决策表

		// 插入决策表表头
		Map[] heads = new Map[cols.length];
		for (int i = 0; i < heads.length; i++) {
			heads[i] = new HashMap<String, Object>();
			heads[i].put("tid", tid);
			heads[i].put("hid", GitUtil.genUUIDString());
			heads[i].put("hname", cols[i].get("name"));
			heads[i].put("htype", cols[i].get("type"));
			heads[i].put("hop", cols[i].get("op"));
			heads[i].put("hcolnum", i + 1);
			heads[i].put("create_time", GitUtil.getBusiTimestamp());
		}
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.decision.insertDeciTableHead", heads);

		int rowsCount = sheet.getLastRowNum();
		// List<String[]> list = new ArrayList<String[]>();
		List<Map> list = new ArrayList<Map>();
		for (int j = header; j <= rowsCount; j++) {// 循环读取每一行的信息
		// String[] cells = new String[cols.length + 1];
		// list.add(cells);
			row = sheet.getRow(j);// 获得row对象
			for (int k = 0; k < cols.length + 1; k++) {// 循环读取每一个单元格
				cell = row.getCell(k);
				Object obj = null;
				String colType = null;
				if (k == cols.length) {// 结果列
					colType = ITEM_TYPE_STRING;
				} else {
					colType = cols[k].get("type").toString();
				}
				if (cell != null) {
					// DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
					// "com.bos.pub.decision.insertDeciTableCol", col);
					obj = getCellValue(cell, colType);
				}
				String val = null;
				if (null != obj)
					val = obj.toString().trim();
				if (StringUtils.isBlank(val))
					val = null;

				// cells[k] = val;
				// if (StringUtils.isEmpty(val))
				// continue;

				Map<String, Object> col = new HashMap<String, Object>();
				col.put("tid", tid);
				col.put("cid", GitUtil.genUUIDString());
				col.put("crow", j - header + 1);
				col.put("ccol", k + 1);
				col.put("cval", val);
				list.add(col);
			}
		}
		DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.decision.insertDeciTableCol", list
						.toArray(new Map[list.size()]));

		ServletResponse res = GitUtil.getResponse();
		res.reset();
		res.resetBuffer();
		res.setContentType("text/html;charset=UTF-8");
		String contextPath = ((HttpServletRequest) GitUtil.getRequest())
				.getContextPath();
		((HttpServletResponse) res).sendRedirect(contextPath
				+ "/pub/decision/deciTable/deci_table_import.jsp?tid=" + tid
				+ "&msg=" + URLEncoder.encode("导入成功！", "UTF-8"));
		// return computeDecisionTable(cols, param, list);
	}

	private static Object getCellValue(HSSFCell cell, String type) {
		Object obj;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			obj = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			obj = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			obj = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			obj = cell.getStringCellValue();
			break;
		default:
			obj = cell.getStringCellValue();
			break;
		}
		if (ITEM_TYPE_BOOL.equals(type))
			return "1".equals(obj) || "是".equals(obj)
					|| Boolean.TRUE.equals(obj);
		if (ITEM_TYPE_NUMBER.equals(type))
			return (null == obj ? null : new BigDecimal(obj.toString()));

		return (null == obj ? null : obj.toString());
	}

	@Bizlet("计算决策表")
	public static String computeDecisionTable(String tid, Map param)
			throws Exception {
		ExprTypeImpl expr = new ExprTypeImpl();
		CriteriaType cri = CriteriaType.FACTORY.create();
		cri.set_entity("com.bos.pub.decision.TbPubDeciTableHead");
		cri.set_expr(new ArrayList<ExprType>(1));
		cri.get_expr().add(expr);
		expr.set("tid", tid);

		OrderbyTypeImpl order = new OrderbyTypeImpl();
		order.set_property("hcolnum");
		cri.set_orderby(new ArrayList<OrderbyType>(1));
		cri.get_orderby().add(order);
		DataObject[] heads = GitUtil.queryEntitiesByCriteriaEntity(cri);
		if (heads.length < 1)
			throw new Exception("没有查到表头信息");

		cri.get_orderby().clear();
		order = new OrderbyTypeImpl();
		order.set_property("crow");
		cri.get_orderby().add(order);
		order = new OrderbyTypeImpl();
		order.set_property("ccol");
		cri.get_orderby().add(order);
		cri.set_entity("com.bos.pub.decision.TbPubDeciTableCol");
		DataObject[] cols = GitUtil.queryEntitiesByCriteriaEntity(cri);
		if (cols.length < 1)
			throw new Exception("没有查到决策表内容");

		String result = null;
		int hcolnum = heads[heads.length - 1].getInt("hcolnum"); // 最大的列数
		int hrownum = cols[cols.length - 1].getInt("crow"); // 最大的行数
		int current = 0;
		for (int i = 1; i <= hrownum; i++) {
			boolean flag = true; // 默认满足
			current = (i - 1) * (hcolnum + 1);
			for (int j = 1; j <= hcolnum; j++) {
				Object val = cols[current].get("cval");
				if (null == val || val.toString().trim().length() == 0) {
					current++;
					continue; // 决策表中的单元格为空时，无论实际值为多少都是满足条件
				}
				Object real = param.get(heads[j - 1].get("hname"));
				if (null == real) {
					flag = false; // 决策表中的单元格不为空，但实际值为空，则说明不满足条件
					break;
				}
				flag = (Boolean) explainCompute(real, heads[j - 1]
						.getString("hop"), val);
				current++;
				if (flag == false)
					break;
			}
			if (flag == true) {
				result = cols[current].getString("cval");
				break;
			}
		}

		// System.out.println(result);
		return result;
	}

	/**
	 * @param cols
	 *            列：不包括结果列
	 * @param param
	 *            参数：实际值
	 * @param list
	 *            决策表的内容行
	 * @return
	 */
	public static String computeDecisionTable(Map[] cols, Map param,
			List<String[]> list) throws Exception {
		// Map<String, Object> map1 = new HashMap<String, Object>();
		// map1.put("name", "A");
		// map1.put("op", "<=");
		// Map<String, Object> map2 = new HashMap<String, Object>();
		// map2.put("name", "B");
		// map2.put("op", ">=");
		// Map<String, Object> map3 = new HashMap<String, Object>();
		// map3.put("name", "C");
		// map3.put("op", ">=");

		// Map[] cols = new Map[] { map1, map2, map3 };
		// int[] param = new int[] { 33, 44, 55 };
		// List<String[]> list = new ArrayList<String[]>();
		// list.add(new String[] { "33", "44", "55", "first" });
		// list.add(new String[] { "33", "44", null, "second" });
		// list.add(new String[] { "33", null, null, "third" });

		String result = null;
		for (int i = 0, len = list.size(); i < len; i++) {
			boolean flag = true; // 默认满足
			String[] arr = list.get(i);
			for (int j = 0, lenj = arr.length - 1; j < lenj; j++) {
				flag = (Boolean) explainCompute(param.get(cols[j].get("name")),
						cols[j].get("op").toString(), arr[j]);
				if (flag == false)
					break;
			}
			if (flag == true) {
				result = arr[arr.length - 1];
				break;
			}
		}

		System.out.println(result);
		return result;
	}

	private void testOneByOne(List<String> paramNameList,
			List<Object[]> paramValueList, int start, int end,
			HashMap<String, Object> currentValue) throws Exception {
		if (start == end && end == paramNameList.size() - 1) {
			// 最后一个参数
			Object[] values = paramValueList.get(end);
			if (null == values || values.length == 0)
				return;

			String paramName = paramNameList.get(end);
			Class cls = (Class) currentValue.get("TEST_CLS");
			FileWriter fw = (FileWriter) currentValue.get("TEST_FileWriter");
			for (int i = 0; i < values.length; i++) {
				currentValue.put(paramName, values[i]);
				// System.out.println(currentValue);

				Object ruleObj = cls.newInstance();
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.putAll(currentValue);
				Method mt = cls.getMethod("exe", new Class[] { HashMap.class });
				Object obj = mt.invoke(ruleObj, new Object[] { param });
				Field path = cls.getDeclaredField("path");
				List pathList = (List) path.get(ruleObj);
				// System.out.println(obj);
				// System.out.println(pathList);
				fw.write("\n");
				// fw.write(currentValue.toString()); // 参数值
				for (int j = 0; j < paramNameList.size(); j++) {
					fw.write(currentValue.get(paramNameList.get(j)) + ",");
				}
				fw.write(String.valueOf(obj)); // 执行结果
				if (null != pathList
						&& pathList.size() > 0
						&& currentValue.containsKey("TEST_DECISTION_RULE") == false) {// 决策树执行路径
					for (int j = 0, lenj = pathList.size(); j < lenj; j++) {
						fw.write("," + pathList.get(j));
					}
				}
				long cnt = Long.valueOf(currentValue.get(
						"TEST_FileWriter_Count").toString());
				if (cnt % 100 == 0) {
					fw.flush();
					System.out.println("已处理：" + cnt);
				}
				cnt++;
				currentValue.put("TEST_FileWriter_Count", cnt);
				// fw.flush();
				// fw.close();
			}
			return;
		}

		// 不是最后一个参数
		Object[] values = paramValueList.get(start);
		if (null == values || values.length == 0)
			return;

		String paramName = paramNameList.get(start);
		for (int i = 0; i < values.length; i++) {
			currentValue.put(paramName, values[i]);
			// 递归调用
			testOneByOne(paramNameList, paramValueList, start + 1, end,
					currentValue);
		}
		return;
	}

	/**
	 * 批量测试决策树
	 * 
	 * @param paramNameList
	 *            决策树中所调用的规则的名称列表
	 * @param paramValueList
	 *            名称对应的值列表（列表元素为数组）
	 * @throws Exception
	 */
	public void testBatchTree(List<String> paramNameList,
			List<Object[]> paramValueList) throws Exception {
		// 批量测试
		if (paramNameList.size() != paramValueList.size()) {// 参数名称个数与参数值一一对应
			return;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		String rind = "业务决策树01";
		DataObject rule = DataFactory.INSTANCE.create("com.bos.pub.decision",
				"TbPubGrantRule");
		rule.set("rind", rind);
		rule.set("rstatus", "2");// 2-生效
		DataObject[] arr = GitUtil.queryEntitiesByTemplate(rule);
		if (null != arr && arr.length > 0) {
			rule = arr[0];
		} else {
			throw new Exception("出错！未查找到生效的规则：" + rind);
		}

		DataObject ruleCached = ruleCache.get(rule.get("rid"));
		if (null == ruleCached
				|| rule.getDate("updateTime").getTime() > ruleCached.getDate(
						"updateTime").getTime()) {
			// 规则内容已更新
			map.put("rule", rule);
			updateRuleCache(map);
		}
		ruleCached = ruleCache.get(rule.get("rid"));
		if (ruleCached == null && null != currentRuleCached) {
			ruleCached = currentRuleCached;
		}
		if (null == ruleCached) {
			if (null == ruleCached) {
				throw new Exception("出错！规则缓存查找失败，或未查找到生效的规则：" + map.get("rind"));
			}
		}
		Class cls = (Class) ruleCached.get("cls");
		if (null == cls) {
			throw new Exception("出错！规则编译失败：" + map.get("rind"));
		}

		map = new HashMap<String, Object>();
		map.put("TEST_DECISTION_TREE", true);
		map.put("TEST_CLS", cls);

		FileWriter fw = new FileWriter("F:\\decition_tree_result.csv");
		for (int j = 0; j < paramNameList.size(); j++) {
			if (j != 0)
				fw.write(",");
			fw.write(String.valueOf(paramNameList.get(j)));
		}
		fw.write(",执行结果,执行路径");
		map.put("TEST_FileWriter", fw);
		map.put("TEST_FileWriter_Count", 0);
		testOneByOne(paramNameList, paramValueList, 0,
				paramNameList.size() - 1, map);
		fw.flush();
		fw.close();
		long cnt = Long.valueOf(map.get("TEST_FileWriter_Count").toString());
		System.out.println("已处理：" + cnt);
	}

	/**
	 * 批量测试规则
	 * 
	 * @param paramNameList
	 *            参数名称列表
	 * @param paramValueList
	 *            参数值数组列表
	 * @param ruleInd
	 *            规则标识
	 * @throws Exception
	 */
	public void testBatchRule(List<String> paramNameList,
			List<Object[]> paramValueList, String ruleInd) throws Exception {
		// 批量测试
		if (paramNameList.size() != paramValueList.size()) {// 参数名称个数与参数值一一对应
			return;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		String rind = ruleInd;
		DataObject rule = DataFactory.INSTANCE.create("com.bos.pub.decision",
				"TbPubGrantRule");
		rule.set("rind", rind);
		rule.set("rstatus", "2");// 2-生效
		DataObject[] arr = GitUtil.queryEntitiesByTemplate(rule);
		if (null != arr && arr.length > 0) {
			rule = arr[0];
		} else {
			throw new Exception("出错！未查找到生效的规则：" + rind);
		}

		DataObject ruleCached = ruleCache.get(rule.get("rid"));
		if (null == ruleCached
				|| rule.getDate("updateTime").getTime() > ruleCached.getDate(
						"updateTime").getTime()) {
			// 规则内容已更新
			map.put("rule", rule);
			updateRuleCache(map);
		}
		ruleCached = ruleCache.get(rule.get("rid"));
		if (ruleCached == null && null != currentRuleCached) {
			ruleCached = currentRuleCached;
		}
		if (null == ruleCached) {
			if (null == ruleCached) {
				throw new Exception("出错！规则缓存查找失败，或未查找到生效的规则：" + map.get("rind"));
			}
		}
		Class cls = (Class) ruleCached.get("cls");
		if (null == cls) {
			throw new Exception("出错！规则编译失败：" + map.get("rind"));
		}

		map = new HashMap<String, Object>();
		map.put("TEST_DECISTION_TREE", true);
		map.put("TEST_DECISTION_RULE", true);
		map.put("TEST_CLS", cls);

		FileWriter fw = new FileWriter("F:\\decition_rule_result.csv");
		for (int j = 0; j < paramNameList.size(); j++) {
			if (j != 0)
				fw.write(",");
			fw.write(String.valueOf(paramNameList.get(j)));
		}
		fw.write(",执行结果");
		map.put("TEST_FileWriter", fw);
		map.put("TEST_FileWriter_Count", 0);
		testOneByOne(paramNameList, paramValueList, 0,
				paramNameList.size() - 1, map);
		fw.flush();
		fw.close();
		long cnt = Long.valueOf(map.get("TEST_FileWriter_Count").toString());
		System.out.println("已处理：" + cnt);
	}

	public static void main(String[] args) throws Exception {
		// 决策树测试 START
		String[] paramNames = { "是否低业务", "授权表判断: 公司授信低业务单笔审批权", "是否委托贷款",
				"授权表判断: 公司委托贷款业务单户审批权", "平台项下单户申请", "特别授权1", "特别授权2", "是否零权限",
				"是否大型优质国有企业名单", "是否存量客户授权名单", "是否前两类、潜在贷款重组化解单户审批权",
				"授权表判断: 前两类潜在贷款重组化解单户审批权", "是否集团成员", "母公司为地级市或直辖市区级以上国资委控股",
				"授权表判断: 母公司为地级市或直辖市区级以上国资委控股企业", "集团下单户满足担保不满足担保评级再AA以上",
				"授权表判断: 对公单一集团客户审批权（符合担保条件）", "授权表判断: 对公单一集团客户审批权（不符合担保条件）",
				"评级AAA以上且企业规模大中型", "授权表判断: 信用评级AAA级", "评级AA以上且企业规模大中型",
				"授权表判断: 信用评级AA级", "非保本理财质押",
				"授权表判断: 非保本型（低等级或中等等级）理财产品质押授信业务单户审批权",
				"现房和优质国有控股担保机构存量续借", "授权表判断: 公司授信现房抵押和优质国有控股担保机构担保业务存量续借单户审批权",
				"授权表判断: 公司授信常规业务单户基准审批权" };
		Boolean[] bools = { true, false };
		List<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < paramNames.length; i++) {
			values.add(bools);
		}
		new DecisionUtil().testBatchTree(Arrays.asList(paramNames), values);
		// 决策树测试 END
	}

	public void ruletest() throws Exception {
		String[] paramNames = { "是否低业务", "授权表判断: 公司授信低业务单笔审批权", "是否委托贷款",
				"授权表判断: 公司委托贷款业务单户审批权", "平台项下单户申请", "特别授权1", "特别授权2", "是否零权限",
				"是否大型优质国有企业名单", "是否存量客户授权名单", "是否前两类、潜在贷款重组化解单户审批权",
				"授权表判断: 前两类潜在贷款重组化解单户审批权", "是否集团成员", "母公司为地级市或直辖市区级以上国资委控股",
				"授权表判断: 母公司为地级市或直辖市区级以上国资委控股企业", "集团下单户满足担保不满足担保评级再AA以上",
				"授权表判断: 对公单一集团客户审批权（符合担保条件）", "授权表判断: 对公单一集团客户审批权（不符合担保条件）",
				"评级AAA以上且企业规模大中型", "授权表判断: 信用评级AAA级", "评级AA以上且企业规模大中型",
				"授权表判断: 信用评级AA级", "非保本理财质押",
				"授权表判断: 非保本型（低等级或中等等级）理财产品质押授信业务单户审批权",
				"现房和优质国有控股担保机构存量续借", "授权表判断: 公司授信现房抵押和优质国有控股担保机构担保业务存量续借单户审批权",
				"授权表判断: 公司授信常规业务单户基准审批权" };
		Boolean[] bools = { true, false };
		List<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < paramNames.length; i++) {
			values.add(bools);
		}
		new DecisionUtil().testBatchTree(Arrays.asList(paramNames), values);
	}

	private StringBuffer validateExprs(List list, int start, int end,StringBuffer sbTemp)
			throws Exception {
		// 中缀表达式，转换为：后缀表达式
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		sbTemp.append("");
		sb1.append("log(\"判断依据：");
		sb2.append("logPath(\"判断依据：");
		for (int i = start; i <= end; i++) {
			Map map = (Map) list.get(i);
			String text = (String) map.get("text");
			String entext = (String) map.get("entext");
			String dictTypeId = (String) map.get("dictTypeId");
			if(dictTypeId != null && !(dictTypeId.equals("")) && (entext.equals(text))){
				if(dictTypeId.equals("product")){
					DataObject[] texts = GitUtil.getProductInfo(text);
					text = texts[0].get("productName").toString();
				}else{
					text = GitUtil.getDictName(dictTypeId,text);
				}
			}
			text.replace("\"-\"", "'-'");
			if(text.contains("\"")){
			    text=text.replaceAll("\"","'");
			   }
			if(text.equals("&gt;")){
				text= "大于";
			}if(text.equals("&lt;")){
				text= "小于";
			}if(text.equals("&gt;=")){
				text= "大于等于";
			}if(text.equals("&lt;=")){
				text= "小于等于";
			}if(text.equals("参数无值")){
				text="平台客户名称不空";
			}
			sb1.append(text);
			sb2.append(text);
		}
		sb1.append("\");\n");
		sb2.append("\");\n");
		sbTemp.append(sb1);
		sbTemp.append(sb2);
		sbTemp.append(sb3);
		return sbTemp;
	}

}
