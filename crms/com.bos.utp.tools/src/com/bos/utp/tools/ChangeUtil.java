package com.bos.utp.tools;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import com.eos.system.annotation.Bizlet;

/**
 * 
 * 转换工具类
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * Revision 1.1 2008/09/02 17:19:01 wengzr 提交CVS
 * 
 */
public class ChangeUtil {
	/**
	 * 时间格式(年月日）
	 */
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";

	/**
	 * 时间格式（年月）
	 */
	public static final String DATE_FORMAT_YM = "yyyyMM";

	/**
	 * 时间格式（年）
	 */
	public static final String DATE_FORMAT_Y = "yyyy";

	public static final String DATE_FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ChangeUtil类的缺省构造器。
	 */
	private ChangeUtil() {
	}

	/**
	 * 将一个以','分割的字符串，转换为一个Vector对象。这是changeStringToVector(String str, String
	 * token)的简化版本。
	 * 
	 * @param _str
	 *            需要转换的字符串
	 * @return 包含了字符串中元素的Vector对象。
	 * @see #changeStringToVector
	 */
	public static Vector changeStringToVector(String _str) {
		return changeStringToVector(_str, ",");
	}

	/**
	 * 将一个以字符串token分割的字符串，转换为一个Vector对象。如"姓名[token]年龄"被转换为一个Vector，该Vector包含两个元素，第一个是"姓名"，第二个是"年龄"。
	 * 
	 * @param _str
	 *            需要转换的字符串
	 * @param _token
	 *            字符串中分割的token。如空格" "，或":"等。
	 * @return 包含了字符串中元素的Vector对象。
	 */
	public static Vector changeStringToVector(String _str, String _token) {
		if (_str == null) {
			return null;
		}

		Vector<String> temp = new Vector<String>();
		StringTokenizer st = new StringTokenizer(_str, _token);
		while (st.hasMoreTokens()) {
			temp.add(st.nextToken());
		}
		return temp;
	}

	/**
	 * 将一个Vector对象中保存的字符串元素使用","分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str)的逆操作。
	 * 
	 * @param _v
	 *            包含了字符串数据元素的Vector对象
	 * @return 一个以","为分隔符的字符串
	 */
	public static String changeVectorToString(Vector _v) {
		return changeVectorToString(_v, ",");
	}

	/**
	 * 将一个Vector对象中保存的字符串元素使用token分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str, String token)的逆操作。
	 * 
	 * @param _v
	 *            包含了字符串数据元素的Vector对象
	 * @param _token
	 *            字符串中分割的token。如空格" "，或":"等。
	 * @return 一个以token为分隔符的字符串
	 */
	public static String changeVectorToString(Vector _v, String _token) {
		if (_v == null) {
			return null;
		}
		Enumeration enumeration = _v.elements();
		String str = "";
		while (enumeration.hasMoreElements()) {
			str = str + (String) (enumeration.nextElement()) + _token;
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 将一个字符串数组中保存的字符串元素使用","分隔符转换为一个字符串。
	 * 
	 * @param _strArray
	 *            包含了字符串数据元素的字符串数组
	 * @return 一个以","为分隔符的字符串
	 * @see #changeArrayToString
	 */
	public static String changeArrayToString(String[] _strArray) {
		return changeArrayToString(_strArray, ",");
	}

	/**
	 * 将一个字符串数组中保存的字符串元素使用token分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str, String token)的逆操作。
	 * 
	 * @param _strArray
	 *            包含了字符串数据元素的字符串数组
	 * @param _token
	 *            分隔字符串使用的分隔符。
	 * @return 一个以token为分隔符的字符串
	 */
	public static String changeArrayToString(String[] _strArray, String _token) {
		if (_strArray == null) {
			return null;
		}

		int size = _strArray.length;
		if (size == 0) {
			return null;
		} else if (size == 1) {
			return _strArray[0];
		} else {
			String temp = _strArray[0];
			for (int i = 1; i < size; i++) {
				temp = temp + _token + _strArray[i];
			}
			return temp;
		}
	}

	/**
	 * 将一个使用","分隔符分隔的字符串，转变为一个字符串数组。
	 * 
	 * @param _str
	 *            用token分隔符分隔的字符串
	 * @return 字符串数组
	 */
	public static String[] changeStringToArray(String _str) {
		return changeStringToArray(_str, ",");
	}

	/**
	 * 将一个使用token分隔符分隔的字符串，转变为一个字符串数组。
	 * 
	 * @param _str
	 *            用token分隔符分隔的字符串
	 * @param _token
	 *            字符串的分隔符
	 * @return 字符串数组
	 */
	public static String[] changeStringToArray(String _str, String _token) {
		if (_str == null) {
			return null;
		}

		Vector v = changeStringToVector(_str, _token);
		String[] strArray = new String[v.size()];
		int i = 0;
		for (Enumeration em = v.elements(); em.hasMoreElements(); i++) {
			strArray[i] = (String) em.nextElement();
		}
		return strArray;
	}

	/**
	 * 获得以参数_fromDate为基数的年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @param _fromDate
	 *            起算时间
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.util.Date _birthday,
			java.util.Date _fromDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_birthday);
		int birthdayYear = calendar.get(Calendar.YEAR);
		int birthdayMonth = calendar.get(Calendar.MONTH);
		int birthdayDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		calendar.setTime(_fromDate);
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		int age = currentYear - birthdayYear;
		if (!((currentMonth >= birthdayMonth) && (currentDay >= birthdayDay))) {
			age--;
		}
		return age;
	}

	/**
	 * @param date
	 * @return 当月最后一天
	 */
	public static java.util.Date date2LastDayOfMonth(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1); // 加一个月
		calendar.set(Calendar.DAY_OF_MONTH, 0);// 减一天

		return calendar.getTime();
	}

	/**
	 * @param date 日期
	 * @param year 要增加的年
	 * @param month 要增加的月
	 * @param day 要增加的日
	 * @return 增加后的日期
	 */
	public static Date dateAdd(Date date, int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DAY_OF_MONTH, day);

		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(date2LastDayOfMonth(new Date()));
		System.out.println(dateAdd(new Date(), 1, 1, 1));
	}

	/**
	 * 获得当前年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.util.Date _birthday) {
		return getAgeFromBirthday(_birthday, new java.util.Date(System
				.currentTimeMillis()));
	}

	/**
	 * 获得当前年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.sql.Timestamp _birthday) {
		return getAgeFromBirthday(new java.util.Date(_birthday.getTime()),
				new java.util.Date(System.currentTimeMillis()));
	}

	/**
	 * 使用格式{@link #DATE_FORMAT_YMD}格式化日期输出
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String formatDate(java.util.Date _date) {
		return formatDate(_date, DATE_FORMAT_YMD);
	}

	/**
	 * 使用格式<b>_pattern</b>格式化日期输出
	 * 
	 * @param _date
	 *            日期对象
	 * @param _pattern
	 *            日期格式
	 * @return 格式化后的日期
	 */
	public static String formatDate(java.util.Date _date, String _pattern) {
		if (_date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_pattern);
		String stringDate = simpleDateFormat.format(_date);
		return stringDate;
	}

	/**
	 * 使用中文字符以简单的形式（"年 月 日"）格式化时间串
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String simplefFormatChineseDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append("年").append(
				calendar.get(Calendar.MONTH) + 1).append("月").append(
				Calendar.DAY_OF_MONTH).append("日");
		calendar.clear();
		return sb.toString();
	}

	/**
	 * 使用中文字符以复杂的形式（"年 月 日 上午 时 分 秒"）格式化时间串
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String complexFormatChineseDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append("年").append(
				calendar.get(Calendar.MONTH) + 1).append("月").append(
				Calendar.DAY_OF_MONTH).append("日").append(Calendar.HOUR_OF_DAY)
				.append("时").append(Calendar.MINUTE).append("分").append(
						Calendar.SECOND).append("秒");
		calendar.clear();
		return sb.toString();
	}

	/**
	 * 将时间串转变为时间对象，输入参数<b>_dateStr</b>必须遵循格式{@link #DATE_FORMAT_YMD}
	 * 
	 * @param _dateStr
	 *            时间串
	 * @return 时间对象
	 */
	public static java.util.Date changeToDate(String _dateStr)
			throws IllegalArgumentException {
		return changeToDate(_dateStr, DATE_FORMAT_YMD);
	}

	/**
	 * 将时间串转变为时间对象
	 * 
	 * @param _dateStr
	 *            时间串
	 * @param _pattern
	 *            时间串使用的模式
	 * @return 时间对象
	 * @throws ParamValidateException
	 *             当输入的时间串和它使用的模式不匹配时掷出
	 */
	public static java.util.Date changeToDate(String _dateStr, String _pattern)
			throws IllegalArgumentException {
		if (_dateStr == null || _dateStr.trim().equals("")) {
			return null;
		}
		java.util.Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(_pattern);
		try {
			date = format.parse(_dateStr);
		} catch (java.text.ParseException pe) {
			throw new IllegalArgumentException("不能使用模式:[" + _pattern
					+ "]格式化时间串:[" + _dateStr + "]");
		}
		return date;
	}

	/**
	 * 将时间串转变为数据库时间对象，输入参数<b>_dateStr</b>必须遵循格式{@link #DATE_FORMAT_YMD}
	 * 
	 * @param _dateStr
	 *            时间串
	 * @return 数据库时间对象
	 */
	public static java.sql.Date changeToDBDate(String _dateStr)
			throws IllegalArgumentException {
		return changeForDBDate(changeToDate(_dateStr, DATE_FORMAT_YMD));
	}

	/**
	 * 将时间串转变为数据库时间对象
	 * 
	 * @param _dateStr
	 *            时间串
	 * @param _pattern
	 *            时间串使用的模式
	 * @return 时间对象
	 * @throws ParamValidateException
	 *             当输入的时间串和它使用的模式不匹配时掷出
	 */
	public static java.sql.Date changeToDBDate(String _dateStr, String _pattern)
			throws IllegalArgumentException {
		return changeForDBDate(changeToDate(_dateStr, _pattern));
	}

	/**
	 * 将java.util.Date对象转换为java.sql.Date对象
	 * 
	 * @param _date
	 *            待转化的java.util.Date 对象
	 * @return java.sql.Date对象
	 */
	public static java.sql.Date changeForDBDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		return new java.sql.Date(_date.getTime());
	}

	/**
	 * 将java.sql.Date对象转换为java.util.Date对象
	 * 
	 * @param _date
	 *            待转化的java.sql.Date对象
	 * @return java.util.Date对象
	 */
	public static java.util.Date changFromDBDate(java.sql.Date _date) {
		return (java.util.Date) _date;
	}

	/**
	 * 将java.util.Date对象转换为java.sql.Timestamp对象
	 * 
	 * @param _date
	 *            待转化的java.util.Date 对象
	 * @return java.sql.Timestamp对象
	 */
	public static java.sql.Timestamp changeToTimestamp(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		return new java.sql.Timestamp(_date.getTime());
	}

	/**
	 * 将java.sql.Timestamp对象转换为java.util.Date对象
	 * 
	 * @param _date
	 *            待转化的java.sql.Timestamp 对象
	 * @return java.util.Date 对象
	 */
	public static java.util.Date changeFromTimestamp(java.sql.Timestamp _date) {
		return (java.util.Date) _date;
	}

	/**
	 * 改变字符串的编码方式(ISO8859_1)为(GBK)，以支持中文
	 * 
	 * @param _str
	 *            待转变的字符串
	 * @return 采用GBK编码的字符串
	 */
	public static String changeToGB(String _str) throws Exception {
		if (_str == null) {
			return null;
		}
		String gbStr = null;
		try {
			gbStr = new String(_str.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
			throw e;
		}
		return gbStr;
	}

	/**
	 * 改变字符串的编码方式(GBK)为(ISO8859_1)
	 * 
	 * @param _str
	 *            待转变的字符串
	 * @return 采用ISO8859_1编码的字符串
	 */
	public static String changeFromGB(String _str) throws Exception {
		if (_str == null) {
			return null;
		}
		String isoStr = null;
		try {
			isoStr = new String(_str.getBytes("GBK"), "ISO8859_1");
		} catch (Exception e) {
			throw e;
		}
		return isoStr;
	}

	/**
	 * 获得日期的年
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的年
	 */
	public static int getYear(java.util.Date _date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		return year;
	}

	/**
	 * 获得日期的月
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的月
	 */
	public static int getMonth(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		// 以0开始
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		return (month + 1);
	}

	/**
	 * 获得日期的天，以月为基
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的天
	 */
	public static int getDay(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		return day;
	}

	/**
	 * 获得日期的小时
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的小时
	 */
	public static int getHours(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.HOUR);
		calendar.clear();
		return value;
	}

	/**
	 * 获得日期的分钟
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的分钟
	 */
	public static int getMinutes(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.MINUTE);
		calendar.clear();
		return value;
	}

	/**
	 * 获得日期的小秒
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的秒
	 */
	public static int getSeconds(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.SECOND);
		calendar.clear();
		return value;
	}

	/**
	 * 计算两个日期间相隔的天数
	 * 
	 * @param _startDate
	 *            起始日期
	 * @param _endDate
	 *            终止日期
	 * @return 相隔天数, 如果结果为正表示<b>_endDate</b>在<b>_startDate</b>之后；如果结果为负表示<b>_endDate</b>在<b>_startDate</b>之前；如果结果为0表示<b>_endDate</b>和<b>_startDate</b>是同一天。
	 */
	public static int getDayCount(java.util.Date _startDate,
			java.util.Date _endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_startDate);
		int startDay = calendar.get(Calendar.DAY_OF_YEAR);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.setTime(_endDate);
		int endDay = calendar.get(Calendar.DAY_OF_YEAR);
		int endYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		return (endYear - startYear) * 365 + (endDay - startDay);
	}

	/**
	 * 获得两个Date间的月数, 天数超过14天算1个月
	 * 
	 * @param _startDate
	 *            开始时间
	 * @param _endDate
	 *            结束时间
	 * @return 两个Date间的月数
	 */
	public static int getMonthAmount(java.sql.Date _startDate,
			java.sql.Date _endDate) {
		int nYear = 0;
		int nMonth = 0;
		int nDay = 0;
		int nMonthAmount = 0;
		Calendar cldStart = Calendar.getInstance();
		Calendar cldEnd = Calendar.getInstance();
		cldStart.setTime(_startDate);
		cldEnd.setTime(_endDate);
		nYear = cldEnd.get(Calendar.YEAR) - cldStart.get(Calendar.YEAR);
		nMonth = cldEnd.get(Calendar.MONTH) - cldStart.get(Calendar.MONTH);
		nDay = cldEnd.get(Calendar.DATE) - cldStart.get(Calendar.DATE);
		if (nDay > 14) {
			nMonthAmount = nYear * 12 + nMonth + 1;
		} else {
			nMonthAmount = nYear * 12 + nMonth;
		}
		return nMonthAmount;
	}

	/**
	 * 格式化长整形数
	 * 
	 * @param _inStrObj
	 *            长整形字串对象
	 * @return 长整形数
	 */
	public static long toLong(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return 0;
		} else {
			return Long.valueOf(_inStrObj.toString()).longValue();
		}
	}

	/**
	 * 格式化整形数
	 * 
	 * @param _inStrObj
	 *            整形数字串对象
	 * @return 整形数
	 */
	public static int toInteger(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return 0;
		} else {
			return new Integer(_inStrObj.toString()).intValue();
		}
	}

	/**
	 * 格式化双精浮点数
	 * 
	 * @param _inStrObj
	 *            双精浮点数字串对象
	 * @return 双精度浮点数，
	 */
	public static double toDouble(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return 0;
		} else {
			return Double.valueOf(_inStrObj.toString()).doubleValue();
		}
	}

	/**
	 * 格式化浮点数
	 * 
	 * @param _inStrObj
	 *            浮点数字串对象
	 * @return 浮点数，如果数据格式错误，或字串为空，这返回0
	 */
	public static float toFloat(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return 0;
		} else {
			return Float.valueOf(_inStrObj.toString()).floatValue();
		}
	}

	/**
	 * 将字节数组采用编码<b>_encoding</b>转化为字符串
	 * 
	 * @param _bytes
	 *            字节数组
	 * @param _encoding
	 *            编码方式
	 * @throws ParamValidateException
	 *             如果编码方式不支持时掷出
	 * @return 字符串
	 */
	public static String toStr(byte[] _bytes, String _encoding)
			throws IllegalArgumentException {
		if (_bytes == null) {
			return null;
		}

		String s = null;
		try {
			s = new String(_bytes, _encoding);
		} catch (Exception e) {
			throw new IllegalArgumentException("不支持的编码方式:" + _encoding);
		}
		return s;
	}

	/**
	 * 格式化布尔对象
	 * 
	 * @param _boolean
	 *            布尔对象
	 * @return 布尔对象的值，如果<b>_boolean</b>为null, 返回false
	 */
	public static boolean toBoolean(Boolean _boolean) {
		if (_boolean == null) {
			return false;
		} else {
			return _boolean.booleanValue();
		}
	}

	/**
	 * 获得对象的字符串表示， 当<b>_obj</b>为null时用<b>_replaceStr</b>替代
	 * 
	 * @param _obj
	 *            对象
	 * @param _replaceStr
	 *            替代null值的字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(Object _obj, String _replaceStr) {
		if (_obj == null) {
			return _replaceStr;
		} else {
			return _obj.toString();
		}
	}

	/**
	 * 字符串处理， 当<b>_str</b>为null时用<b>_replaceStr</b>替代
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _replaceStr
	 *            替代null值的字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(String _str, String _replaceStr) {
		if (_str == null || _str.equals("null")) {
			return _replaceStr;
		} else {
			return _str;
		}
	}

	/**
	 * 字符串处理， 当<b>_str</b>为null时用<b>""</b>替代
	 * 
	 * @param _str
	 *            原始字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(String _str) {
		return toStr(_str, "");
	}

	/**
	 * 获得对象的字符串表示， 当<b>_obj</b>为null时用<b>""</b>替代
	 * 
	 * @param _obj
	 *            对象
	 * @return 获得对象的字符串
	 */
	public static String toStr(Object _obj) {
		if (_obj == null) {
			return "";
		} else {
			return toStr(_obj.toString());
		}
	}

	/**
	 * 将字符串采用编码<b>_encoding</b>转化为字节数组
	 * 
	 * @param _str
	 *            字符串
	 * @param _encoding
	 *            编码方式
	 * @throws ParamValidateException
	 *             如果编码方式不支持时掷出
	 * @return 字节数组
	 */
	public static byte[] toBytes(String _str, String _encoding)
			throws IllegalArgumentException {
		if (_str == null) {
			return null;
		}
		byte[] b = null;
		try {
			b = _str.getBytes(_encoding);
		} catch (Exception e) {
			throw new IllegalArgumentException("不支持的编码方式:" + _encoding);
		}
		return b;
	}

	/**
	 * 将双精浮点数代表的金额转化中文大写形式
	 * 
	 * @param _dMoney
	 *            代表双精浮点数的金额
	 * @return 金额的中文大写形式，如果输入参数<b>dMoney</b>大于10^8或小于0.01返回空串。
	 */
	@Bizlet("将双精浮点数代表的金额转化中文大写形式")
	public static String toChinese(double _dMoney) {
		String[] strArr = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] strArr1 = { "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
		String[] strArr2 = new String[10];
		String sRtn = "";
		int iTmp;
		double dTmp;
		try {
			_dMoney += 0.001;
			if ((_dMoney >= 100000000) || (_dMoney < 0.01)) {
				sRtn = "";
			} else {
				for (int i = 0; i < 10; i++) {
					dTmp = _dMoney / Math.pow(10, 7 - i);
					iTmp = (new Double(dTmp)).intValue();
					_dMoney -= iTmp * Math.pow(10, 7 - i);
					if (iTmp != 0) {
						strArr2[i] = strArr[iTmp] + strArr1[9 - i];
					} else {
						strArr2[i] = "";
					}
				}
				boolean bFlag = false;
				for (int i = 0; i < 10; i++) {
					if (!"".equals(strArr2[i])) {
						sRtn += strArr2[i];
						bFlag = true;
					} else {
						if (i == 3) {
							sRtn += "万";
							bFlag = true;
						} else if (i == 7) {
							sRtn += "圆";
							bFlag = true;
						} else if (bFlag) {
							sRtn += "零";
							bFlag = false;
						}
					}
				}
				if (sRtn.startsWith("万")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				if (sRtn.startsWith("圆")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				while (sRtn.startsWith("零")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				if (sRtn.lastIndexOf("零") == (sRtn.length() - 1)) {
					sRtn = sRtn.substring(0, sRtn.length() - 1);
				}
				if (sRtn.startsWith("圆")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				iTmp = sRtn.indexOf("圆");
				if (iTmp != -1) {
					if ("零".equals(sRtn.substring(iTmp - 1, iTmp))) {
						sRtn = sRtn.substring(0, iTmp - 1)
								+ sRtn.substring(iTmp, sRtn.length());
					}
				}
				iTmp = sRtn.indexOf("万");
				if (iTmp != -1) {
					if ("零".equals(sRtn.substring(iTmp - 1, iTmp))) {
						sRtn = sRtn.substring(0, iTmp - 1)
								+ sRtn.substring(iTmp, sRtn.length());
					}
				}
				while (sRtn.startsWith("零")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				sRtn += "整";
			}
		} catch (Exception ex) {
		}
		return sRtn;
	}

	/**
	 * 根据输入的String返回BigDecimal，或者若String非数字串，返回null
	 * 
	 * @param _str
	 *            待转化的字符串
	 * @return BigDecimal对象
	 */
	public static BigDecimal toBigDecimal(String _str) {
		BigDecimal bd = null;
		if (_str != null) {
			try {
				bd = new BigDecimal(_str);
			} catch (Exception e) {
				return null;
			}
		}
		return bd;
	}

	/**
	 * 根据年，月，日，转化为Timestamp类型,便于DB插入处理
	 * 
	 * @param _sDate
	 *            格式为：yyyy-mm-dd
	 * @return Timestamp的时间格式
	 */
	public static Timestamp toTimestamp(String _sDate) {
		Timestamp ts = null;
		if (_sDate == null || "".equals(_sDate)) {
			return null;
		}
		ts = Timestamp.valueOf(_sDate + " 00:00:00.000000000");
		return ts;
	}

	/**
	 * 替换Html文档中的"&nbsp"为" ", "&lt"为"<", "&gt"为">"，"<br>
	 * "为"\r\n"
	 * 
	 * @param _rawStr
	 *            原始Html文档
	 * @return 替换后的Html文档
	 */
	public static String changeHtmlStr(String _rawStr) {
		String str = null;
		if (_rawStr != null) {
			str = replaceString("&nbsp;", " ", _rawStr);
			str = replaceString("&lt;", "<", str);
			str = replaceString("&gt;", ">", str);
			str = replaceString("&amp;", "&", str);
			str = replaceString("&quot;", "\"", str);
			str = replaceString("<br>", "\r\n", str);
		}
		return str;
	}

	/**
	 * 使用新串替换原有字符串中老串
	 * 
	 * @param _oldStr
	 *            待替换的字符串
	 * @param _newStr
	 *            新字符串
	 * @param _wholeStr
	 *            整个字符串
	 * @return 替换后新串
	 */
	public static String replaceString(String _oldStr, String _newStr,
			String _wholeStr) {
		if (_wholeStr == null) {
			return null;
		}
		if (_newStr == null) {
			return _wholeStr;
		}

		int start = 0, end = 0;
		StringBuffer result = new StringBuffer();
		result = result.append(_wholeStr);
		while (result.indexOf(_oldStr, start) > -1) {
			start = result.indexOf(_oldStr, start);
			end = start + _oldStr.length();
			result.replace(start, end, _newStr);
			start += _newStr.length();
		}
		return result.toString();
	}

	/**
	 * 如果是正向替换，使用新串替换原有字符串中第一个老串；如果是逆向替换，使用新串替换原有字符串中最后一个老串
	 * 
	 * @param _oldStr
	 *            待替换的字符串
	 * @param _newStr
	 *            新字符串
	 * @param _wholeStr
	 *            整个字符串
	 * @param _reverse
	 *            替换方向，如果为false正向替换，否则逆向替换
	 * @return 替换后新串
	 */
	public static String replaceFirstString(String _oldStr, String _newStr,
			String _wholeStr, boolean _reverse) {
		if (_wholeStr == null) {
			return null;
		}
		if (_newStr == null) {
			return _wholeStr;
		}
		StringBuffer result = new StringBuffer(_wholeStr);
		int start = 0, end = 0;
		if (!_reverse) {
			if (result.indexOf(_oldStr) > -1) {
				start = result.indexOf(_oldStr);
				end = start + _oldStr.length();
				result.replace(start, end, _newStr);
			}
		} else {
			if (result.lastIndexOf(_oldStr) > -1) {
				start = result.lastIndexOf(_oldStr);
				end = start + _oldStr.length();
				result.replace(start, end, _newStr);
			}
		}
		return result.toString();
	}

	/**
	 * 将字符串转换为HTML形式，以便在JavaScript中使用
	 * 
	 * @param _sourceStr
	 *            原字符串
	 * @return 转换后的字符串
	 */
	public static String changeToHTMLStr(String _sourceStr) {
		if (_sourceStr == null) {
			return null;
		}
		StringBuffer buff = new StringBuffer(1024);
		int n = _sourceStr.length();
		char c;
		for (int i = 0; i < n; i++) {
			c = _sourceStr.charAt(i);
			if (c == '"') {
				buff.append('\\');
				buff.append(c);
			} else if (c == '\\') {
				buff.append('\\');
				buff.append(c);
			} else if (c == '\r') {
				buff.append("\\r");
			} else if (c == '\n') {
				buff.append("\\n");
			} else {
				buff.append(c);
			}
		}
		return buff.toString();
	}

	/**
	 * 得到 _value截取小数点后_len位 以后的值
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return 截取以后的值
	 */
	public static float roundFloat(float _value, int _len)
			throws IllegalArgumentException {
		int iLen = _len;
		checkParamPositive("_len", _len);
		float d = (float) Math.pow(10, iLen);
		float fValue = _value * d;
		return Math.round(fValue) / d;
	}

	/**
	 * 获得float的字符串表示，首先对_value按_len进行四舍五入截位，如果截位后小数点后位数小于_len，则使用0补齐。
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return float的字符串
	 */
	public static String formatFloat(float _value, int _len)
			throws IllegalArgumentException {
		String fStr = String.valueOf(roundFloat(_value, _len));
		StringBuffer sb = new StringBuffer(fStr);
		int leftBit = fStr.length() - fStr.indexOf(".") - 1;
		if (leftBit < _len) {
			for (int i = 0; i < (_len - leftBit); i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/**
	 * 得到 _value截取小数点后_len位 以后的值
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return 截取以后的值
	 */
	public static double roundDouble(double _value, int _len)
			throws IllegalArgumentException {
		int iLen = _len;
		checkParamPositive("_len", _len);
		double d = Math.pow(10, iLen);
		double dValue = _value * d;
		return Math.round(dValue) / d;
	}

	/**
	 * 获得double的字符串表示，首先对_value按_len进行四舍五入截位，如果截位后小数点后位数小于_len，则使用0补齐。
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return double的字符串
	 */
	public static String formatDoubleWithComma(double _value, int _len)
			throws IllegalArgumentException {
//		String fStr = String.valueOf(roundDouble(_value, _len));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < _len; i++) {
			sb.append("0");
		}
		_value = new BigDecimal(_value).setScale(_len, BigDecimal.ROUND_HALF_UP).doubleValue();
		if (sb.length() > 0) {
			return new DecimalFormat("#,##0." + sb.toString()).format(_value);
		}
		return new DecimalFormat("#,##0").format(_value);
	}
	
	public static String formatDouble(double _value, int _len)
			throws IllegalArgumentException {
		String fStr = String.valueOf(roundDouble(_value, _len));
		StringBuffer sb = new StringBuffer(fStr);
		int leftBit = fStr.length() - fStr.indexOf(".") - 1;
		if (leftBit < _len) {
			for (int i = 0; i < (_len - leftBit); i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/**
	 * 获得字符串的左边
	 * <p>
	 * _len
	 * </p>
	 * 个字符串
	 * 
	 * @param _str
	 *            字符串
	 * @param _len
	 *            长度
	 * @return
	 *            <p>
	 *            _len
	 *            </p>
	 *            个字符串
	 */
	public static String leftString(String _str, int _len) {
		if (_str == null) {
			return null;
		}
		if (_len < 0) {
			return "";
		}
		if (_str.length() <= _len) {
			return _str;
		} else {
			return _str.substring(0, _len);
		}
	}

	/**
	 * 获得字符串的右边
	 * <p>
	 * _len
	 * </p>
	 * 个字符串
	 * 
	 * @param _str
	 *            字符串
	 * @param _len
	 *            长度
	 * @return 字符串的右边
	 *         <p>
	 *         _len
	 *         </p>
	 *         个字符串
	 */
	public static String rightString(String _str, int _len) {
		if (_str == null) {
			return null;
		}
		if (_len < 0) {
			return "";
		}
		if (_str.length() <= _len) {
			return _str;
		} else {
			return _str.substring(_str.length() - _len);
		}
	}

	/**
	 * 右填充字符
	 * <p>
	 * _padChar
	 * </p>
	 * ，使整个字符串长度为
	 * <p>
	 * _size
	 * </p>
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _size
	 *            添充后字符的总长度
	 * @param _padChar
	 *            待填充字符
	 * @return 右填充后的字符串，如:rightPad('hell', 3, '0')=hell;rightPad('hell', 10,
	 *         '0')=hell000000
	 */
	public static String rightPad(String _str, int _size, char _padChar) {
		if (_str == null) {
			return null;
		}
		int pads = _size - _str.length();
		if (pads <= 0) {
			return _str; // returns original String when possible
		}
		return _str.concat(padding(pads, _padChar));
	}

	/**
	 * 左填充字符
	 * <p>
	 * _padChar
	 * </p>
	 * ，使得填充后的字符串总长为
	 * <p>
	 * _size
	 * </p>
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _size
	 *            添充后字符的总长度
	 * @param _padChar
	 *            待填充字符
	 * @return 左填充后的字符串，如:leftPad('hell', 10, '0')=000000hell;leftPad('hell', 3,
	 *         '0')=hell
	 */
	public static String leftPad(String _str, int _size, char _padChar) {
		if (_str == null) {
			return null;
		}
		int pads = _size - _str.length();
		if (pads <= 0) {
			return _str; // returns original String when possible
		}
		return padding(pads, _padChar).concat(_str);
	}

	/**
	 * 字符串
	 * <p>
	 * padChar
	 * </p>
	 * 重复
	 * <p>
	 * repeat
	 * </p>
	 * 位
	 * 
	 * @param _repeat
	 *            重复次数
	 * @param _padChar
	 *            待重复字符
	 * @return 重复后的结果字串，如:padding(5, 'a')=aaaaa;padding(0, 'a'):""
	 */
	private static String padding(int _repeat, char _padChar) {
		String value = "";
		String padStr = String.valueOf(_padChar);
		if (_repeat > 0) {
			for (int i = 0; i < _repeat; i++) {
				value = value.concat(padStr);
			}
		}
		return value;
	}

	private static void checkParamPositive(String _str, int _value)
			throws IllegalArgumentException {
		if (_value <= 0) {
			throw new IllegalArgumentException("参数:" + _str + "不能小于等于0");
		}
	}

}