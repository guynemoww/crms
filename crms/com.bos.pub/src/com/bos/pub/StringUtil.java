/**
 * 
 */
package com.bos.pub;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author ganquan
 * @date 2015-07-10 10:35:59
 * 
 */
@Bizlet("")
public class StringUtil {
	@Bizlet("")
	public String addAll(String num, String name) {
		String code = num + "_" + name;
		return code;
	}

	@Bizlet("")
	public String add2(String num, String name) {
		String code = num + name;
		return code;
	}

	@Bizlet("将三个字符串相加")
	public String addThreeString(String num, String name, String id) {
		String code = num + name + id;
		return code;
	}

	@Bizlet("将列相加")
	public String addSql(DataObject[] objs) {
		String column = "";
		for (int i = 0; i < objs.length; i++) {
			if (column == null || "".equals(column)) {
				column += objs[i].getString("paraColumn");
			} else {
				column += "," + objs[i].getString("paraColumn");
			}
		}
		return column;
	}

	@Bizlet("大写下划线转驼峰命名")
	public String camelName(String name) {
		StringBuilder result = new StringBuilder();
		if (null == name || name.isEmpty()) {
			return "";
		} else if (!name.contains("_")) {
			return name.substring(0).toLowerCase();
		}
		String camels[] = name.split("[_]");
		for (String camel : camels) {
			if (camel.isEmpty()) {
				continue;
			}
			if (result.length() == 0) {
				result.append(camel.toLowerCase());
			} else {
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bizlet("获取表名")
	public Map getTableName(DataObject[] objs) {
		Map map = new HashMap();
		for (int i = 0; i < objs.length; i++) {
			DataObject obj = objs[0];
			map.put("name", obj.getString("tableName"));
			map.put("type", obj.getString("dataType"));
		}
		return map;
	}

	@Bizlet("获取对应的列值")
	public String getValueByEntity(String name, String col, DataObject amountApply, DataObject amountDetailApply, DataObject loanrateApply) {
		String value = "";
		if ("TB_BIZ_AMOUNT_APPLY".equals(name)) {
			value = amountApply.getString(col);
		} else if ("TB_BIZ_AMOUNT_DETAIL_APPLY".equals(name)) {
			value = amountDetailApply.getString(col);
		} else if ("TB_BIZ_AMOUNT_LOANRATE_APPLY".equals(name)) {
			value = loanrateApply.getString(col);
		}
		return value;
	}

	/**
	 * 
	 * @param sysParam
	 *            传入从系统参数配置表中查询出的结果
	 * @param colValue
	 *            根据系统参数配置表中获取的参数名查询出与该业务相关信息表中的对应的字段值
	 * @param colType
	 *            根据系统参数配置表中获取的参数名获取参数类型
	 * @return
	 */
	@Bizlet("比较然后给出提示")
	public String sendReminder(DataObject sysParam, Object colValue, String colType) {
		String msg = "";
		// 获取系统参数表对应字段值为了下面的比较
		String paraColunmName = sysParam.getString("PARA_COLUNM_NAME");
		String paraCountSign = sysParam.getString("PARA_COUNT_SIGN");
		String paraContrlLeftval = sysParam.getString("PARA_CONTRL_LEFTVAL");
		String paraContrlRigthval = sysParam.getString("PARA_CONTRL_RIGTHVAL");
		Object result = null;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String condition = colValue + paraCountSign + paraContrlLeftval;
		try {
			// 如果比较条件为between，则需要我们手动转比较方式
			if ("between".equals(paraCountSign)) {
				if (colType.contains("TIMESTAMP") || colType.equals("DATE")) {
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					colValue = df.format(colValue);
					paraContrlLeftval = df.format(paraContrlLeftval);
					paraContrlRigthval = df.format(paraContrlRigthval);
				}
				condition = (colValue + ">=" + paraContrlLeftval + "&&" + colValue + "<=" + paraContrlRigthval);
				result = engine.eval(condition);
				// 如果比较条件为=，考虑到字符串则需要我们手动转为equals方式
			} else if ("=".equals(paraCountSign)) {
				result = colValue.equals(paraContrlLeftval);
			} else {
				// 其他正常比较无需手动转换
				result = engine.eval(condition);
			}

			System.out.println("比较条件==================" + condition);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		// 如果result为true,则循环继续，比较下一个参数，如果有一个不满足条件则退出循环给出对应提示
		if (result != null && !(Boolean) result) {
			msg = "参数名称：" + paraColunmName + "，不满足条件" + condition;
		}
		return msg;

	}

	public static boolean isNull(String str) {
		return !isNotNull(str);
	}

	public static boolean isNotNull(String str) {
		String temp;
		if (str == null || (temp = str.trim()).isEmpty()) {
			return false;
		}
		if (temp.equalsIgnoreCase("null") || temp.equalsIgnoreCase("undefined")) {
			return false;
		}
		return true;
	}

	/**
	 * 删除text中多余空格
	 * 
	 * @param text
	 * @return
	 */
	public final static String trimAll(String text) {
		if (isNull(text)) {
			return text;
		}
		Pattern p = Pattern.compile("\\s{2,}");
		Matcher m = p.matcher(text);
		return m.replaceAll(" ");
	}

	public final static String joinStr(String[] strs, String space, boolean cleanEmpty) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (space == null) {
			space = "";
		}
		StringBuilder sb = new StringBuilder(strs.length * strs[0].length());
		if (cleanEmpty) {
			int i = 0;
			while (i < strs.length && isNull(strs[i])) {
				i++;
			}
			if (i < strs.length) {
				sb.append(strs[i]);
				i++;
				for (; i < strs.length; i++) {
					if (isNull(strs[i])) {
						continue;
					}
					sb.append(space).append(strs[i]);
				}
			}
		} else {
			sb.append(strs[0]);
			for (int i = 1; i < strs.length; i++) {
				sb.append(space).append(strs[i]);
			}
		}
		return sb.toString();
	}
}
