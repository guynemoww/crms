/**
 * 
 */
package com.bos.conPrint;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import com.bos.pub.EosUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.TableName;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2015-10-01 17:59:34
 * 
 */

public class ConInfoLoadUtil {
	public static void convertValue(Map<String, Object> dataMap, String fieldName, Object value) {
		if (value == null) {
			return;
		}
		Object tempV = dataMap.get(fieldName);
		if (tempV != null) {
			dataMap.put(fieldName + "__old", tempV);
		}
		dataMap.put(fieldName, value);
	}

	public static Object getValueOld(Map<String, Object> dataMap, String fieldName) {
		Object value = dataMap.get(fieldName + "__old");
		return value == null ? dataMap.get(fieldName) : value;
	}

	private static String[] cnNum = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };

	private static String toNumCN(int value, String type) {
		if (value > cnNum.length || value < 1) {
			return "";
		}
		return cnNum[value - 1];
	}

	private static String[] khNum = { "(一)", "(二)", "(三)", "(四)", "(五)", "(六)", "(七)", "(八)", "(九)", "(十)" };

	private static String toNumKH(int value, String type) {
		if (value > khNum.length || value < 1) {
			return "";
		}
		return khNum[value - 1];
	}

	/**
	 * 数字转换为带括号中文数字
	 * 
	 * @param data
	 * @param codes
	 */
	public static void setNumToCNKH(Map<String, Object> dataMap, String[]... codes) {
		String temp;
		for (String[] code : codes) {
			Object value = dataMap.get(code[0]);
			if (value == null) {
				continue;
			}
			temp = toNumKH(Integer.valueOf((String) value), null);
			convertValue(dataMap, code.length == 1 || code[1] == null ? code[0] : code[1], temp);
		}
	}

	public static void setNumToCN(Map<String, Object> dataMap, String[]... codes) {
		String temp;
		for (String[] code : codes) {
			Object value = dataMap.get(code[0]);
			if (value == null) {
				continue;
			}
			temp = toNumCN(Integer.valueOf((String) value), null);
			convertValue(dataMap, code.length == 1 || code[1] == null ? code[0] : code[1], temp);
		}
	}

	private static String[] yqNum = { "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩" };

	/**
	 * 数字转换为带圆圈数字
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	private static String toNumYQ(int value, String type) {
		if (value > yqNum.length || value < 1) {
			return "";
		}
		return yqNum[value - 1];
	}

	public static void setNumToYQ(Map<String, Object> dataMap, String[]... codes) {
		String temp;
		for (String[] code : codes) {
			Object value = dataMap.get(code[0]);
			if (value == null) {
				continue;
			}
			temp = toNumYQ(Integer.valueOf((String) value), null);
			convertValue(dataMap, code.length == 1 || code[1] == null ? code[0] : code[1], temp);
		}
	}

	/**
	 * 批量将数字转换为中文
	 * 
	 * 
	 * @param data
	 * @param codes
	 *            字段，新字段
	 */
	public static void setMoneyToCN(Map<String, Object> dataMap, String[]... codes) {
		BigDecimal temp;
		for (String[] code : codes) {
			temp = (BigDecimal) dataMap.get(code[0]);
			if (temp == null) {
				continue;
			}
			convertValue(dataMap, code.length == 1 || code[1] == null ? code[0] : code[1], changeMoney(temp));
		}
	}

	/**
	 * 时间数据格式化
	 * 
	 * @param data
	 * @param codes
	 */
	public static void setDateFormat(Map<String, Object> dataMap, String[]... codes) {
		String temp;
		for (String[] code : codes) {
			temp = (String) dataMap.get(code[0]);
			if (temp == null) {
				continue;
			}
			dataMap.put(code.length == 1 || code[1] == null ? code[0] : code[1], changeDate(temp));
		}
	}

	/**
	 * 批量转换字典数据
	 * 
	 * 
	 * @param data
	 * @param codes
	 *            字段，字典编号，新字段
	 */
	public static void setCodeByDict(Map<String, Object> dataMap, String[]... codes) {
		String temp;
		for (String[] code : codes) {
			temp = (String) dataMap.get(code[0]);
			if (temp == null) {
				continue;
			}
			dataMap.put(code.length == 2 || code[2] == null ? code[0] : code[2], BusinessDictUtil.getDictName(code[1], temp));
		}
	}

	/**
	 * 转换数据
	 * 
	 * @param dataMap
	 */
	public static void convertData(Map<String, Object> dataMap) {
		if (dataMap == null || dataMap.isEmpty()) {
			return;
		}
		// 转换机构代码
		String temp = (String) dataMap.get("ORG_NUM");
		if (temp != null) {
			DataObject org = EntityUtil.searchEntity(TableName.OM_ORGANIZATION, "orgCode", temp);
			if (org != null) {
				convertValue(dataMap, "ORG_NUM", org.getString("orgName"));
			}
		}

		// 字典数据转换
		setCodeByDict(dataMap, new String[] { "REPAYMENT_TYPE", "XD_SXCD1162" }//
				, new String[] { "CURRENCY_CD", "CD000001" }//
				, new String[] { "CERT_TYPE", "CDKH0002" }//
				, new String[] { "COMPARTY_NAME", "CDKH0002" }//

		);
	}

	/**
	 * 
	 * @param value
	 * @param defValue
	 * @param values
	 * @return
	 */
	public static String decode(String value, String defValue, String[] values) {
		if (value == null || value.isEmpty()) {
			return defValue;
		}
		boolean setDef = true;
		for (int i = 0; i < values.length; i = i + 2) {
			if (value.equals(values[i])) {
				value = values[i + 1];
				setDef = false;
				break;
			}
		}
		if (setDef) {
			value = defValue;
		}
		return value;
	}

	// 日期转换 原格式yyyy-MM-dd转yyyy年MM月dd日
	@Bizlet("")
	public static String changeDate(String date) {
		String[] array;
		String conDate;
		if ("" != date && null != date) {
			array = date.split("-");
			if (array.length != 3) {
				return null;
			}
			conDate = array[0] + "年" + array[1] + "月" + array[2] + "日";
			return conDate;
		}
		return null;
	}

	@Bizlet
	public DataObject packageMap(Map<String, Object> dataMap) {
		DataObject obj = EosUtil.createDataObject();
		for (String key : dataMap.keySet()) {
			Object data = dataMap.get(key);
			if (data != null && !data.getClass().isPrimitive()) {
				obj.set(key, data);
			}
		}
		return obj;
	}

	public static String getSubType(String conSubType) {
		// 合同担保类型 CDZC0005
		// 01 信用
		// 02 抵押
		// 03 质押
		// 04 保证
		// 05 保证金
		// ------------------
		// 担保合同担保类型 XD_YWDB0131
		// 00 即可抵押也可质押
		// 01 抵押
		// 02 质押
		// 03 保证金
		// 04 保证人
		// 05 信用保险
		String[][] types = { { "01", "05" }, { "02", "01" }, { "03", "02" }, { "04", "04" }, { "05", "03" } };
		for (String[] t : types) {
			if (t[0].equals(conSubType)) {
				return t[1];
			}
		}
		return null;
	}

	// 小写货币转大写汉字
	@Bizlet("")
	public static String changeMoney(BigDecimal money) {
		return NumberToCN.number2CNMontrayUnit(money);
	}

	public static String changeSmailAmt(Double amt) {
		DecimalFormat df = new DecimalFormat("￥###,###.##");
		String smailAmt = df.format(amt);
		return smailAmt;
	}
}
