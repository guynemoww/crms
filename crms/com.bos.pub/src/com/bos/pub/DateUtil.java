/**
 * 
 */
package com.bos.pub;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

/**
 * @author kf_xdxt11
 * @date 2016-07-12 18:21:32
 * 
 */
@Bizlet("通用日期工具类")
public class DateUtil {

	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	private static final Object object = new Object();

	/** 
	 * 获取SimpleDateFormat 
	 * @param pattern 日期格式 
	 * @return SimpleDateFormat对象 
	 * @throws RuntimeException 异常：非法日期格式 
	 */
	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	/** 
	 * 获取日期中的某数值。如获取月份 
	 * @param date 日期 
	 * @param dateType 日期格式 
	 * @return 数值 
	 */
	private static int getInteger(Date date, int dateType) {
		int num = 0;
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			num = calendar.get(dateType);
		}
		return num;
	}

	/** 
	 * 增加日期中某类型的某数值。如增加日期 
	 * @param date 日期字符串 
	 * @param dateType 类型 
	 * @param amount 数值 
	 * @return 计算后日期字符串 
	 */
	private static String addInteger(String date, int dateType, int amount) {
		String dateString = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			myDate = addInteger(myDate, dateType, amount);
			dateString = DateToString(myDate, dateStyle);
		}
		return dateString;
	}

	/** 
	 * 增加日期中某类型的某数值。如增加日期 
	 * @param date 日期 
	 * @param dateType 类型 
	 * @param amount 数值 
	 * @return 计算后日期 
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/** 
	 * 获取精确的日期 
	 * @param timestamps 时间long集合 
	 * @return 日期 
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
				// 因此不能将minAbsoluteValue取默认值0
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					minAbsoluteValue = absoluteValues.get(0);
					for (int i = 1; i < absoluteValues.size(); i++) {
						if (minAbsoluteValue > absoluteValues.get(i)) {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);

					if (timestampsLastTmp.length == 2) {
						long dateOne = timestampsLastTmp[0];
						long dateTwo = timestampsLastTmp[1];
						timestamp = Math.abs(dateOne) < Math.abs(dateTwo) ? dateOne : dateTwo;
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/** 
	 * 判断字符串是否为日期字符串 
	 * @param date 日期字符串 
	 * @return true or false 
	 */
	@Bizlet("判断字符串是否为日期字符串")
	public static boolean isDate(String date) {
		boolean isDate = false;
		if (date != null) {
			if (getDateStyle(date) != null) {
				isDate = true;
			}
		}
		return isDate;
	}

	/** 
	 * 获取日期字符串的日期风格。失敗返回null。 
	 * @param date 日期字符串 
	 * @return 日期风格 
	 */
	@Bizlet("")
	public static DateStyle getDateStyle(String date) {
		DateStyle dateStyle = null;
		Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DateStyle style : DateStyle.values()) {
			if (style.isShowOnly()) {
				continue;
			}
			Date dateTmp = null;
			if (date != null) {
				try {
					ParsePosition pos = new ParsePosition(0);
					dateTmp = getDateFormat(style.getValue()).parse(date, pos);
					if (pos.getIndex() != date.length()) {
						dateTmp = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), style);
			}
		}
		Date accurateDate = getAccurateDate(timestamps);
		if (accurateDate != null) {
			dateStyle = map.get(accurateDate.getTime());
		} else {
			System.out.println("【" + date + "】日期格式非法！");
		}
		return dateStyle;
	}

	/** 
	 * 将日期字符串转化为日期。失败返回null。 
	 * @param date 日期字符串 
	 * @return 日期 
	 */
	@Bizlet("")
	public static Date StringToDate(String date) {
		DateStyle dateStyle = getDateStyle(date);
		return StringToDate(date, dateStyle);
	}

	/** 
	 * 将日期字符串转化为日期。失败返回null。 
	 * @param date 日期字符串 
	 * @param pattern 日期格式 
	 * @return 日期 
	 */
	@Bizlet("")
	public static Date StringToDate(String date, String pattern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(pattern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	/** 
	 * 将日期字符串转化为日期。失败返回null。 
	 * @param date 日期字符串 
	 * @param dateStyle 日期风格 
	 * @return 日期 
	 */
	@Bizlet("")
	public static Date StringToDate(String date, DateStyle dateStyle) {
		Date myDate = null;
		if (dateStyle != null) {
			myDate = StringToDate(date, dateStyle.getValue());
		}
		return myDate;
	}

	/** 
	 * 将日期转化为日期字符串。失败返回null。 
	 * @param date 日期 
	 * @param pattern 日期格式 
	 * @return 日期字符串 
	 */
	@Bizlet("")
	public static String DateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	/** 
	 * 将日期转化为日期字符串。失败返回null。 
	 * @param date 日期 
	 * @param dateStyle 日期风格 
	 * @return 日期字符串 
	 */
	@Bizlet("")
	public static String DateToString(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (dateStyle != null) {
			dateString = DateToString(date, dateStyle.getValue());
		}
		return dateString;
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param newPattern 新日期格式 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, String newPattern) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newPattern);
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param newDateStyle 新日期风格 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, DateStyle newDateStyle) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newDateStyle);
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param olddPattern 旧日期格式 
	 * @param newPattern 新日期格式 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, String olddPattern, String newPattern) {
		return DateToString(StringToDate(date, olddPattern), newPattern);
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param olddDteStyle 旧日期风格 
	 * @param newParttern 新日期格式 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, DateStyle olddDteStyle, String newParttern) {
		String dateString = null;
		if (olddDteStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(), newParttern);
		}
		return dateString;
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param olddPattern 旧日期格式 
	 * @param newDateStyle 新日期风格 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, String olddPattern, DateStyle newDateStyle) {
		String dateString = null;
		if (newDateStyle != null) {
			dateString = StringToString(date, olddPattern, newDateStyle.getValue());
		}
		return dateString;
	}

	/** 
	 * 将日期字符串转化为另一日期字符串。失败返回null。 
	 * @param date 旧日期字符串 
	 * @param olddDteStyle 旧日期风格 
	 * @param newDateStyle 新日期风格 
	 * @return 新日期字符串 
	 */
	@Bizlet("")
	public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
		String dateString = null;
		if (olddDteStyle != null && newDateStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
		}
		return dateString;
	}

	/** 
	 * 增加日期的年份。失败返回null。 
	 * @param date 日期 
	 * @param yearAmount 增加数量。可为负数 
	 * @return 增加年份后的日期字符串 
	 */
	@Bizlet("")
	public static String addYear(String date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/** 
	 * 增加日期的年份。失败返回null。 
	 * @param date 日期 
	 * @param yearAmount 增加数量。可为负数 
	 * @return 增加年份后的日期 
	 */
	@Bizlet("")
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/** 
	 * 增加日期的月份。失败返回null。 
	 * @param date 日期 
	 * @param monthAmount 增加数量。可为负数 
	 * @return 增加月份后的日期字符串 
	 */
	@Bizlet("")
	public static String addMonth(String date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/** 
	 * 增加日期的月份。失败返回null。 
	 * @param date 日期 
	 * @param monthAmount 增加数量。可为负数 
	 * @return 增加月份后的日期 
	 */
	@Bizlet("")
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/** 
	 * 增加日期的天数。失败返回null。 
	 * @param date 日期字符串 
	 * @param dayAmount 增加数量。可为负数 
	 * @return 增加天数后的日期字符串 
	 */
	@Bizlet("")
	public static String addDay(String date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/** 
	 * 增加日期的天数。失败返回null。 
	 * @param date 日期 
	 * @param dayAmount 增加数量。可为负数 
	 * @return 增加天数后的日期 
	 */
	@Bizlet("")
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/** 
	 * 增加日期的小时。失败返回null。 
	 * @param date 日期字符串 
	 * @param hourAmount 增加数量。可为负数 
	 * @return 增加小时后的日期字符串 
	 */
	@Bizlet("")
	public static String addHour(String date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/** 
	 * 增加日期的小时。失败返回null。 
	 * @param date 日期 
	 * @param hourAmount 增加数量。可为负数 
	 * @return 增加小时后的日期 
	 */
	@Bizlet("")
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/** 
	 * 增加日期的分钟。失败返回null。 
	 * @param date 日期字符串 
	 * @param minuteAmount 增加数量。可为负数 
	 * @return 增加分钟后的日期字符串 
	 */
	@Bizlet("")
	public static String addMinute(String date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/** 
	 * 增加日期的分钟。失败返回null。 
	 * @param date 日期 
	 * @param dayAmount 增加数量。可为负数 
	 * @return 增加分钟后的日期 
	 */
	@Bizlet("")
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/** 
	 * 增加日期的秒钟。失败返回null。 
	 * @param date 日期字符串 
	 * @param dayAmount 增加数量。可为负数 
	 * @return 增加秒钟后的日期字符串 
	 */
	@Bizlet("")
	public static String addSecond(String date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/** 
	 * 增加日期的秒钟。失败返回null。 
	 * @param date 日期 
	 * @param dayAmount 增加数量。可为负数 
	 * @return 增加秒钟后的日期 
	 */
	@Bizlet("")
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/** 
	 * 获取日期的年份。失败返回0。 
	 * @param date 日期字符串 
	 * @return 年份 
	 */
	@Bizlet("")
	public static int getYear(String date) {
		return getYear(StringToDate(date));
	}

	/** 
	 * 获取日期的年份。失败返回0。 
	 * @param date 日期 
	 * @return 年份 
	 */
	@Bizlet("")
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/** 
	 * 获取日期的月份。失败返回0。 
	 * @param date 日期字符串 
	 * @return 月份 
	 */
	@Bizlet("")
	public static int getMonth(String date) {
		return getMonth(StringToDate(date));
	}

	/** 
	 * 获取日期的月份。失败返回0。 
	 * @param date 日期 
	 * @return 月份 
	 */
	@Bizlet("")
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH) + 1;
	}

	/** 
	 * 获取日期的天数。失败返回0。 
	 * @param date 日期字符串 
	 * @return 天 
	 */
	@Bizlet("")
	public static int getDay(String date) {
		return getDay(StringToDate(date));
	}

	/** 
	 * 获取日期的天数。失败返回0。 
	 * @param date 日期 
	 * @return 天 
	 */
	@Bizlet("")
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}

	/** 
	 * 获取日期的小时。失败返回0。 
	 * @param date 日期字符串 
	 * @return 小时 
	 */
	@Bizlet("")
	public static int getHour(String date) {
		return getHour(StringToDate(date));
	}

	/** 
	 * 获取日期的小时。失败返回0。 
	 * @param date 日期 
	 * @return 小时 
	 */
	@Bizlet("")
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	/** 
	 * 获取日期的分钟。失败返回0。 
	 * @param date 日期字符串 
	 * @return 分钟 
	 */
	@Bizlet("")
	public static int getMinute(String date) {
		return getMinute(StringToDate(date));
	}

	/** 
	 * 获取日期的分钟。失败返回0。 
	 * @param date 日期 
	 * @return 分钟 
	 */
	@Bizlet("")
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}

	/** 
	 * 获取日期的秒钟。失败返回0。 
	 * @param date 日期字符串 
	 * @return 秒钟 
	 */
	@Bizlet("")
	public static int getSecond(String date) {
		return getSecond(StringToDate(date));
	}

	/** 
	 * 获取日期的秒钟。失败返回0。 
	 * @param date 日期 
	 * @return 秒钟 
	 */
	@Bizlet("")
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/** 
	 * 获取当前操作系统日期 。默认yyyy-MM-dd HH:mm:ss格式。失败返回null。 
	 * @return 日期 
	 */
	@Bizlet("")
	public static String getDate() {
		Date date = Calendar.getInstance().getTime();
		return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
	}
	
	/** 
	 * 获取工作日期 。yyyy-MM-dd
	 * @return 日期 
	 */
	@Bizlet("")
	public static Date getWorkDate() {
		Date date = new Date(GitUtil.getBusiTimestamp().getTime());
		date = StringToDate(getDate(date));
		return date;
	}
	
	/** 
	 * 获取工作日时间。yyyy-MM-dd HH:mm:ss
	 * @return 日期 
	 */
	@Bizlet("")
	public static Date getWorkTime() {
		Date date = new Date(GitUtil.getBusiTimestamp().getTime());
		return date;
	}
	
	/** 
	 * 获取当前操作系统日期 。失败返回null。 
	 * @param style 日期风格
	 * @return 日期 
	 */
	@Bizlet("")
	public static String getDate(DateStyle style) {
		Date date = Calendar.getInstance().getTime();
		return DateToString(date, style);
	}

	/** 
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。 
	 * @param date 日期字符串 
	 * @return 日期 
	 */
	@Bizlet("")
	public static String getDate(String date) {
		return StringToString(date, DateStyle.YYYY_MM_DD);
	}

	/** 
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。 
	 * @param date 日期 
	 * @return 日期 
	 */
	@Bizlet("")
	public static String getDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}

	/** 
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
	 * @param date 日期字符串 
	 * @return 时间 
	 */
	@Bizlet("")
	public static String getTime(String date) {
		return StringToString(date, DateStyle.HH_MM_SS);
	}

	/** 
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
	 * @param date 日期 
	 * @return 时间 
	 */
	@Bizlet("")
	public static String getTime(Date date) {
		return DateToString(date, DateStyle.HH_MM_SS);
	}

	/** 
	 * 获取日期的星期。失败返回null。 
	 * @param date 日期字符串 
	 * @return 星期 
	 */
	@Bizlet("")
	public static Week getWeek(String date) {
		Week week = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			week = getWeek(myDate);
		}
		return week;
	}

	/** 
	 * 获取日期的星期。失败返回null。 
	 * @param date 日期 
	 * @return 星期 
	 */
	@Bizlet("")
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}

	/** 
	 * 获取两个日期相差的月数(忽略天)
	 * @param date 日期字符串 
	 * @param otherDate 另一个日期字符串 
	 * @return 相差月数。
	 */
	@Bizlet("")
	public static double getIntervalMonths(Date date, Date otherDate) {
		int r = getMonth(date) - getMonth(otherDate) + (getYear(date) - getYear(otherDate)) * 12;
		return Math.abs(r);
	}
	
	/** 
	 * 获取两个日期相差的周数
	 * @param date 日期字符串 
	 * @param otherDate 另一个日期字符串 
	 * @return 相差周数。
	 */
	@Bizlet("")
	public static double getIntervalWeeks(Date date, Date otherDate) {
		return getInterval(date, otherDate, "W");
	}

	/** 
	 * 获取两个日期相差的天数 
	 * @param date 日期字符串 
	 * @param otherDate 另一个日期字符串 
	 * @return 相差天数。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date), StringToDate(otherDate));
	}

	/** 
	 *  获取两个日期相差的天数 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @return 相差天数。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getIntervalDays(Date date, Date otherDate) {
		return getInterval(date, otherDate, "D");
	}
	
	/** 
	 *  获取两个日期相差的天数 
	 * @param beginDate 日期 
	 * @param endDate 另一个日期 
	 * @return 相差天数。如果失败则返回-1 
	 * 可能为负数
	 */
	@Bizlet("")
	public static double getDiffDays(Date beginDate, Date endDate) {
		return getInterval(beginDate, endDate, "D", false);
	}

	/** 
	 *  获取两个日期相差的小时数 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @return 相差小时。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getIntervalHours(Date date, Date otherDate) {
		return getInterval(date, otherDate, "H");
	}

	/** 
	 *  获取两个日期相差的分钟数 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @return 相差分钟。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getIntervalMinutes(Date date, Date otherDate) {
		return getInterval(date, otherDate, "M");
	}

	/** 
	 *  获取两个日期相差的秒数 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @return 相差秒钟。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getIntervalSeconds(Date date, Date otherDate) {
		return getInterval(date, otherDate, "S");
	}

	/** 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @param diffType 差额类型：周-W 日-D 时-H 分-M 秒-S
	 * @return 相差数。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getInterval(Date date, Date otherDate, String diffType) {
		return getInterval(date, otherDate, diffType, true);
	}
	
	/** 
	 * @param date 日期 
	 * @param otherDate 另一个日期 
	 * @param diffType 差额类型：周-W 日-D 时-H 分-M 秒-S
	 * @param isAbs 是否绝对值
	 * @return 相差数。如果失败则返回-1 
	 */
	@Bizlet("")
	public static double getInterval(Date beginDate, Date endDate, String diffType, boolean isAbs) {
		double num = -1;
		if (beginDate != null && endDate != null) {
			long time = endDate.getTime() - beginDate.getTime();
			if(isAbs){
				time = Math.abs(time);
			}
			double divisor = -1;
			if ("W".equals(diffType.toUpperCase())) {
				divisor = 7 * 24 * 60 * 60 * 1000;
			} else if ("D".equals(diffType.toUpperCase())) {
				divisor = 24 * 60 * 60 * 1000;
			} else if ("H".equals(diffType.toUpperCase())) {
				divisor = 60 * 60 * 1000;
			} else if ("M".equals(diffType.toUpperCase())) {
				divisor = 60 * 1000;
			} else if ("S".equals(diffType.toUpperCase())) {
				divisor = 1000;
			}
			num = time / divisor;
			num = BigDecimal.valueOf(num).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		}
		return num;
	}

	/**
	 * 根据日期取得对应周周一日期
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(Calendar.MONDAY);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(Calendar.MONDAY);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 取得月的剩余天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getRemainDaysOfMonth(Date date) {
		int dayOfMonth = getDaysOfMonth(date);
		int day = getPassDaysOfMonth(date);
		return dayOfMonth - day;
	}

	/**
	 * 取得月已经过的天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getPassDaysOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getDaysOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}

	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}

	/**
	 * 取得季度天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getDaysOfSeason(Date date) {
		int day = 0;
		Date[] seasonDates = getSeasonDate(date);
		for (Date date2 : seasonDates) {
			day += getDaysOfMonth(date2);
		}
		return day;
	}

	/**
	 * 取得季度剩余天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getRemainDaysOfSeason(Date date) {
		return getDaysOfSeason(date) - getPassDaysOfSeason(date);
	}

	/**
	 * 取得季度已过天数
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getPassDaysOfSeason(Date date) {
		int day = 0;

		Date[] seasonDates = getSeasonDate(date);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
			day = getPassDaysOfMonth(seasonDates[0]);
		} else if (month == Calendar.FEBRUARY || month == Calendar.MAY || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
			day = getDaysOfMonth(seasonDates[0]) + getPassDaysOfMonth(seasonDates[1]);
		} else if (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
			day = getDaysOfMonth(seasonDates[0]) + getDaysOfMonth(seasonDates[1]) + getPassDaysOfMonth(seasonDates[2]);
		}
		return day;
	}

	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}

	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("")
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	// ////////////////////

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		c1.set(2015, 9, 22);
		Calendar c2 = Calendar.getInstance();
		c2.set(2014, 8, 21);
		System.out.println(DateToString(getWorkTime(), DateStyle.MM_DD_HH_MM_SS_CN));
	}

}
