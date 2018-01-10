package com.bos.pub;

import java.io.Reader;
import java.nio.CharBuffer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bos.utp.tools.DBUtil;
import com.eos.system.annotation.Bizlet;
import com.googlecode.aviator.AviatorEvaluator;
import commonj.sdo.DataObject;

/**
 * @author wangshichun@git.com.cn
 * @email wsc.hi@163.com
 * 
 * @description
 */
/**
 * @author Administrator
 * 
 */
@Bizlet("规则、表达式相关操作")
public class RuleExecutor {

	/**
	 * 要解析的表达式
	 */
	private String expr;

	/**
	 * 解析的表达式中用到的参数
	 */
	private Map<String, Object> params = new HashMap<String, Object>();

	@Bizlet("返回实际表达式")
	public String getExpr() {
		return expr;
	}

	public static final String RULE_PREFIX = "rule_";

	public static final String INDEX_PREFIX = "index_";

	/**
	 * 用于记录递归层次
	 */
	private int level = 0;

	private void initRule() {
		if (this.expr.startsWith(RULE_PREFIX) == false) {
			return;
		}

		this.expr = this.expr.substring(RULE_PREFIX.length());
		List<HashMap<String, Object>> list = this.getBySql(
				"select * from tb_pub_rule where rule_ind='" + this.expr
						+ "' and RULE_STATUS='1'", this.params);
		if (list.size() == 0) {
			this.expr = "";
			return;
		}
		HashMap<String, Object> rule = list.get(0);
		this.setExpr(rule.get("RULE_EXPR").toString());

		{
			// 处理嵌套规则
			Object rules = rule.get("RULE_RULE_IND");
			if (null != rules && this.level <= 2) {// 规则嵌套不超过两层。
				String[] arr = rules.toString().split(",");
				for (String p : arr) {
					if (p == null || p.length() == 0)
						continue;

					p = p.trim();

					RuleExecutor ex = new RuleExecutor();
					ex.setLevel(this.level + 1);
					Object re = ex.evalRule(p, params);
					this.put(p, re);
				}
			}
		}

		Object param = rule.get("RULE_PARAM");
		if (null == param)
			return;

		String[] arr = param.toString().split(",");
		StringBuffer sb = new StringBuffer(
				"select * from tb_pub_param where PARAM_STATUS='1' and param_ind in ('x'");
		int cnt = 0;
		for (String p : arr) {
			if (p == null || p.length() == 0)
				continue;
			cnt++;
			sb.append(",'").append(p).append("'");
		}

		sb.append(")");
		if (cnt == 0)
			return;

		list = this.getBySql(sb.toString(), this.params);
		if (list.size() == 0) {
			return;
		}

		for (HashMap<String, Object> p : list) {
			Map<String, Object> re = this.getParamValues(p, null);
			this.putAll(re);
		}
	}

	private void initIndex() {
		if (this.expr.startsWith(INDEX_PREFIX) == false) {
			return;
		}

		this.expr = this.expr.substring(INDEX_PREFIX.length());
		List<HashMap<String, Object>> list = this.getBySql(
				"select * from tb_pub_index_base where index_ind='" + this.expr
						+ "' and index_STATUS='1'", this.params);
		if (list.size() == 0) {
			this.expr = "";
			return;
		}
		HashMap<String, Object> index = list.get(0);
		this.setExpr(index.get("INDEX_EXPR").toString());

		{
			// 处理嵌套指标
			Object indexs = index.get("INDEX_INDEX");
			if (null != indexs && this.level <= 2) {// 嵌套不超过两层。
				String[] arr = indexs.toString().split(",");
				for (String p : arr) {
					if (p == null || p.length() == 0)
						continue;

					p = p.trim();

					RuleExecutor ex = new RuleExecutor();
					ex.setLevel(this.level + 1);
					Object re = ex.evalIndex(p, params);
					this.put(p, re);
				}
			}
		}

		Object param = index.get("INDEX_PARAM");
		if (null == param)
			return;

		String[] arr = param.toString().split(",");
		StringBuffer sb = new StringBuffer(
				"select * from tb_pub_param where PARAM_STATUS='1' and param_ind in ('x'");
		int cnt = 0;
		for (String p : arr) {
			if (p == null || p.length() == 0)
				continue;
			cnt++;
			sb.append(",'").append(p).append("'");
		}

		sb.append(")");
		if (cnt == 0)
			return;

		list = this.getBySql(sb.toString(), this.params);
		if (list.size() == 0) {
			return;
		}

		for (HashMap<String, Object> p : list) {
			Map<String, Object> re = this.getParamValues(p, null);
			this.putAll(re);
		}
	}

	private void setExpr(String expr) {
		this.expr = expr;

		if (null == this.expr)
			return;

		initRule();
		initIndex();
	}

	@Bizlet("根据数据模型“参数”的DataObject中的表达式等信息，获取参数名称及参数值")
	public Map<String, Object> getParamValues(DataObject p,
			Map<String, Object> params) {
		this.setParams(params);

		Map<String, Object> result = new HashMap<String, Object>();
		if ("4".equals(p.get("paramType"))) {
			// 常量
			Object obj = p.get("paramExpr");
			if (null != obj) {
				String str = obj.toString();
				if (str.length() > 0 && Character.isDigit(str.charAt(0))) {
					if (str.contains(".")) {
						obj = Double.valueOf(str);
					} else {
						obj = Long.valueOf(str);
					}
				} else {
					obj = str;
				}
			}
			result.put(String.valueOf(p.get("paramInd")), obj);
		}
		if ("3".equals(p.get("paramType"))) {
			// SQL语句
			List<HashMap<String, Object>> paramList = this.getBySql(String
					.valueOf(p.get("paramExpr")), this.params);
			if (paramList.size() == 0) {
				return result;
			}
			if (paramList.get(0).containsKey("PARAM_KEY") == false) {
				result.put(String.valueOf(p.get("paramInd")), paramList.get(0)
						.values().iterator().next());
			}
			for (HashMap<String, Object> pp : paramList) {
				result.put(String.valueOf(pp.get("PARAM_KEY")), pp
						.get("PARAM_VALUE"));
			}
		}
		if ("1".equals(p.get("paramType")) || "2".equals(p.get("paramType"))) {
			// 1-财报数据代码；2-财报指标代码；
			// TODO: expr以rule开头时从数据库获取规则信息：规则ID、名称、表达式、前置参数
		}

		return result;
	}

	@Bizlet("根据数据模型“参数”的Map中的表达式等信息，获取参数名称及参数值")
	public Map<String, Object> getParamValues(Map<String, Object> p,
			Map<String, Object> params) {
		this.setParams(params);

		Map<String, Object> result = new HashMap<String, Object>();
		if ("4".equals(p.get("PARAM_TYPE"))) {
			// 常量
			Object obj = p.get("PARAM_EXPR");
			if (null != obj) {
				String str = obj.toString();
				if (str.length() > 0 && Character.isDigit(str.charAt(0))) {
					if (str.contains(".")) {
						obj = Double.valueOf(str);
					} else {
						obj = Long.valueOf(str);
					}
				} else {
					obj = str;
				}
			}
			result.put(String.valueOf(p.get("PARAM_IND")), obj);
		}
		if ("3".equals(p.get("PARAM_TYPE"))) {
			// SQL语句
			List<HashMap<String, Object>> paramList = this.getBySql(String
					.valueOf(p.get("PARAM_EXPR")), this.params);
			if (paramList.size() == 0) {
				return result;
			}
			if (paramList.get(0).containsKey("PARAM_KEY") == false) {
				result.put(String.valueOf(p.get("PARAM_IND")), paramList.get(0)
						.values().iterator().next());
			}
			for (HashMap<String, Object> pp : paramList) {
				result.put(String.valueOf(pp.get("PARAM_KEY")), pp
						.get("PARAM_VALUE"));
			}
		}
		if ("1".equals(p.get("PARAM_TYPE")) || "2".equals(p.get("PARAM_TYPE"))) {
			// 1-财报数据代码；2-财报指标代码；
			// TODO: expr以rule开头时从数据库获取规则信息：规则ID、名称、表达式、前置参数
		}

		return result;
	}

	@Bizlet("返回所有已获取的参数名称及参数值")
	public Map<String, Object> getParams() {
		return params;
	}

	private void setParams(Map<String, Object> params) {
		if (null == this.params)
			params = new HashMap<String, Object>();
		if (null != params) {
			this.params.clear();
			this.params.putAll(params);
		}
	}

	@Bizlet("增加参数：名称、参数值")
	public void put(String key, Object value) {
		if (null == this.params)
			params = new HashMap<String, Object>();

		this.params.put(key, value);
	}

	@Bizlet("增加参数Map：名称、参数值")
	public void putAll(Map<String, Object> p) {
		if (null == this.params)
			params = new HashMap<String, Object>();

		this.params.putAll(p);
	}

	private String getColumnLabel(ResultSetMetaData meta, int i)
			throws Exception {
		if (null != meta.getColumnLabel(i)) {
			return meta.getColumnLabel(i);
		} else {
			return meta.getColumnName(i);
		}
	}

	@Bizlet("将传入SQL中的#{参数名称}用params中的key对应的value替换后，执行SQL，并返回首行首列")
	public Object getOneBySql(String sql, Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		Matcher match = sqlParamPattern.matcher(sql);
		while (match.find()) {
			match.appendReplacement(sb, String.valueOf(params.get(match
					.group(1))));
		}
		match.appendTail(sb);
		System.out.println(sb);

		List<HashMap<String, Object>> list = this.getBySql(sb.toString());
		if (list.size() == 0)
			return null;

		return list.get(0).values().iterator().next();
	}

	@Bizlet("将传入SQL（其中无需要替换的参数）执行，并返回首行")
	public Object getOneBySql(String sql) {
		List<HashMap<String, Object>> list = this.getBySql(sql);
		if (list.size() == 0)
			return null;

		return list.get(0);
	}

	private Pattern sqlParamPattern = Pattern
			.compile("\\#\\{([a-zA-Z0-9]+)\\}");

	@Bizlet("将传入SQL中的#{参数名称}用params中的key对应的value替换后，执行SQL，并返回所有查询结果")
	public List<HashMap<String, Object>> getBySql(String sql,
			Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		Matcher match = sqlParamPattern.matcher(sql);
		while (match.find()) {
			match.appendReplacement(sb, String.valueOf(params.get(match
					.group(1))));
		}
		match.appendTail(sb);
		System.out.println(sb);

		return this.getBySql(sb.toString());
	}

	private List<HashMap<String, Object>> getBySql(String sql) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				for (int i = 0; i < meta.getColumnCount(); i++) {
					Object obj = rs.getObject(i + 1);
					if (obj instanceof Clob) {
						Clob clob = (Clob) obj;
						if (clob.length() > 0) {
							Reader reader = clob.getCharacterStream();
							// char[] cbuf = new char[(int)clob.length()];
							CharBuffer cbuf = CharBuffer.allocate((int) clob
									.length());
							reader.read(cbuf);
							// obj = new String(cbuf);
							cbuf.position(0);
							obj = cbuf.toString();
						}
					}
					map.put(this.getColumnLabel(meta, i + 1), obj);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(conn, new Statement[] { st },
					new ResultSet[] { rs });
		}

		return list;
	}

	@Bizlet("根据传入的规则标识、参数键值对，计算规则结果")
	public Object evalRule(String ruleInd, Map<String, Object> params) {
		this.setParams(params);
		this.setExpr(RULE_PREFIX + ruleInd);

		return this.eval();
	}

	@Bizlet("根据传入的指标标识、参数键值对，计算指标结果")
	public Object evalIndex(String indexInd, Map<String, Object> params) {
		this.setParams(params);
		this.setExpr(INDEX_PREFIX + indexInd);

		return this.eval();
	}

	@Bizlet("根据传入的表达式、参数键值对，计算表达式结果")
	public Object eval(String expression, Map<String, Object> params) {
		this.setParams(params);
		this.setExpr(expression);
		Object re = null;
		try {
			re = this.eval();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return re;
	}

	private Object eval() {
		try {
			if (null == this.expr || this.expr.trim().length() == 0)
				return null;

			long time = System.currentTimeMillis();
			this.put("下一步", "nil");
			System.out.println("规则/指标表达式：" + this.expr);
			System.out.println("规则/指标执行参数：" + this.params);
			Object result = null;
			String[] arr = this.expr.split("\n");
			for (int i = 0; i < arr.length; i++) {
				if (null == arr[i] || arr[i].trim().length() == 0)
					continue;

				System.out.println("规则/指标执行表达式：" + arr[i]);
				if (arr[i].startsWith("def ") || arr[i].startsWith("定义：")) {
					int pos = arr[i].indexOf("=");
					String expression = arr[i].substring(pos + 1);
					AviatorEvaluator.compile(expression, true); // compile and
					// cache

					result = AviatorEvaluator.execute(expression, this.params);
					if ("nil".equals(result))
						result = null;
					String key = arr[i].substring(4, pos);
					this.params.put(key, result);
				} else {
					AviatorEvaluator.compile(arr[i], true); // compile and
					// cache

					result = AviatorEvaluator.execute(arr[i], this.params);
					if ("nil".equals(result))
						result = null;
					if (null != result)
						break;
				}
			}

			System.out.println("规则/指标执行时间："
					+ (System.currentTimeMillis() - time) + "ms");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		RuleExecutor exe = new RuleExecutor();
		// exe.setExpr("def a=1+2\na==3 ? 'a'+\"aa is {a}\"+a : 111\n\n");
		// exe.put("a", 123);
		// exe.put("b", 456);
		// exe.put("中文", 111);
		//
		// System.out.println(exe.eval());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("下一步", null);
		System.out.println(exe.eval("定义： b =2\n a<b ? 60 : 下一步", map));
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
