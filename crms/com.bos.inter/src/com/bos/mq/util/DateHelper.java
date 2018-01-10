package com.bos.mq.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateHelper {
    private static Logger log = Logger.getLogger(DateHelper.class);

	private DateHelper() {
	}

	public static String DATE_FORMAT = "yyyy-MM-dd";

	public static String DATE_YYYYMMDD_FORMAT = "yyyyMMdd";

	public static String TIME_FORMAT = "HH:mm:ss ";

	public static String TIME_HHMMSS_FORMAT = "HHmmss ";

	private static int[] LAST_DAY_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	//private static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

	//private static DateFormat dtf = new SimpleDateFormat(DATE_FORMAT + " " + TIME_FORMAT);
	
	//private static DateFormat stf = new SimpleDateFormat(TIME_HHMMSS_FORMAT);

	//private static DateFormat tf = new SimpleDateFormat(TIME_FORMAT);

	public static String formatDate(Date aValue) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		if (aValue == null) {
			return ""; // in order to deal with null object
		}
		return df.format(aValue);
	}

	public static String formatDateYYYYMMDD(Date aValue) {
		if (aValue == null) {
			return ""; // in order to deal with null object
		}

		DateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT);
		return vFormat.format(aValue);
	}

	public static String dateAdd(String interval, int count, String dateStr) throws Exception {

		if (dateStr == null || dateStr.equals(""))
			return "";
		Calendar cl = Calendar.getInstance();
		Date d1 = getDate(dateStr);
		cl.setTime(d1);
		if (interval.equalsIgnoreCase("y")) {
			cl.add(Calendar.YEAR, count);
		} else if (interval.equalsIgnoreCase("m")) {
			cl.add(Calendar.MONTH, count);
		} else if (interval.equalsIgnoreCase("d")) {
			cl.add(Calendar.DAY_OF_MONTH, count);
		} else {
			throw new Exception("日期增加类型不对！");
		}
		Date d2 = cl.getTime();

		return formatDate(d2);
	}

	/**
	 * DateFormat
	 * 
	 * @return 获取日期格式：2004-01-01
	 */
	public static String formatDate() {
		//Date today = new Date();
		//DateFormat vFormat = new SimpleDateFormat(DATE_FORMAT);
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 
	* @Title: formatTime 
	* @Description: 获取系统时间，格式为：HHMMSS
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author GIT-Sunny
	* @date 2012-12-4 下午08:29:48 
	* @version V1.0
	 */
	public static String formatTime(){
		DateFormat stf = new SimpleDateFormat(TIME_HHMMSS_FORMAT);
		return stf.format(Calendar.getInstance().getTime());
	}

	/**
	 * DateFormat
	 * 
	 * @return 获取日期格式：20040101
	 */
	public static String formatDateYYYYMMDD() {
		Date today = new Date();
		DateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT);
		return vFormat.format(today);
	}

	/**
	 * 
	 * @param aValue
	 * @return 获取日期格式：2004-01-01 00：00：01
	 */
	public static String formatDateTime(Date aValue) {
		DateFormat dtf = new SimpleDateFormat(DATE_FORMAT + " " + TIME_FORMAT);
		if (aValue == null) {
			return ""; // in order to deal with null object
		}
		return dtf.format(aValue);
	}
	
	/**
	 * 
	 * @param aValue
	 * @return 获取日期格式：20040101000001
	 */
	public static String formatDateTimeYYYYMMDDHHMMSS(Date aValue) {
		if (aValue == null) {
			return ""; // in order to deal with null object
		}

		DateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT + TIME_HHMMSS_FORMAT);
		return vFormat.format(aValue);
	}

	/**
	 * 返回不同种格式的日期
	 * 
	 *            获取日期格式：2004年1月31日
	 */
	public static String formatCDate() {
		Date today = new Date();
		DateFormat vFormat = DateFormat.getDateInstance(DateFormat.LONG);
		return vFormat.format(today);
	}

	/**
	 * 返回不同种格式的日期
	 * 
	 *            获取日期格式：2004年6月23日 星期三
	 */
	public static String formatCDay() {
		Date today = new Date();
		DateFormat vFormat = DateFormat.getDateInstance(DateFormat.FULL);
		return vFormat.format(today);
	}

	public static String foramteDate(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";

		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_FORMAT);
		Date sDate = vFormat.parse(aValue);
		return vFormat.format(sDate);
	}

	public static String foramteDateYYYYMMDD(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";

		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT);
		Date sDate = getDate(aValue);
		// Date sDate=new Date(aValue);

		return vFormat.format(sDate);
		// return sDate.toLocaleString();
	}

	public static Date getDate(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return null;
//		return df.parse(aValue);
		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_FORMAT);
		Date sDate = vFormat.parse(aValue);
		return sDate;
	}

	public static Date getDateYYYYMMDD(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return null;
		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT);
		Date sDate = vFormat.parse(aValue);
		return sDate;
	}

	public static Date getDateTime(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return null;
		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_FORMAT + " " + TIME_FORMAT);
		Date sDate = vFormat.parse(aValue);
		return sDate;
	}

	public static Date getDateTimeYYYYMMDDHHMMSS(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return null;
		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT + TIME_HHMMSS_FORMAT);
		Date sDate = vFormat.parse(aValue);
		// Date sDate=getDate(aValue);
		return sDate;
	}

	/**
	 * formatDateTimeYYYYMMDDHHMMSS
	 * 
	 * @return 获取日期格式：20040101000000
	 */
	public static String formatDateTimeYYYYMMDDHHMMSS() {
		Date today = new Date();
		DateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT + TIME_HHMMSS_FORMAT);
		return vFormat.format(today);
	}

	/**
	 * 上年同期
	 * 
	 * @param aValue
	 * @return
	 * @throws java.lang.Exception
	 */
	public static String getPreYearDay(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		Date d0 = getDate(aValue);
		d0.setDate(1);
		d0.setMonth(d0.getMonth() + 1);
		d0.setYear(d0.getYear() - 1);
		d0.setDate(d0.getDate() - 1);
		return formatDate(d0);

	}

	public static String getPreYearLastDay(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		return calculateDate(aValue, -1, 0, 0).substring(0, 4) + "-12-31";

	}

	public static String getPreMonthLastDay(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		String val = calculateDate(aValue, 0, -1, 0);
		return val.substring(0, 8) + lastDayOfMonth(val);
	}

	public static String getPreDay(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		return calculateDate(aValue, 0, 0, -1);
	}

	public static String getYear(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		String[] array = aValue.split("-");
		return array[0];

	}

	public static String getMonth(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		String[] array = aValue.split("-");
		return array[1];

	}

	public static String getDay(String aValue) throws Exception {
		if (aValue == null || aValue.equals(""))
			return "";
		String[] array = aValue.split("-");
		return array[2];

	}

	/**
	 * 
	 * @param rq
	 * @return
	 */
	public static Integer dateToInt(String rq) {
		if (rq == null || rq.length() < 10) {
			return new Integer(0);
		}
		return Integer.valueOf(rq.substring(0, 4) + rq.substring(5, 7) + rq.substring(8, 10));
	}

	/**
	 * 获取某月的最后一天
	 * @param year
	 * @param month 月份区间为 1-12
	 * @return
	 */
	public static String lastDayOfMonth(String year, String month) {
		String lt = Integer.toString(lastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month) - 1));
		return lt.length() == 1 ? ("0" + lt) : lt;
	}

	/**
	 * 获取某月的最后一天
	 * @param date 格式为 yyyy*MM*dd 月份的区间为 1-12
	 * @return
	 */
	public static String lastDayOfMonth(String date) {
		return lastDayOfMonth(date.substring(0, 4), date.substring(5, 7));
	}

	/**
	 * 获取某月的最后一天
	 * @param date
	 * @return
	 */
	public static int lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return lastDayOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
	}

	/**
	 * 获取某月的最后一天
	 * @param year
	 * @param month 月份的区间为 0-11
	 * @return
	 */
	public static int lastDayOfMonth(int year, int month) {
		if (month == 2 && (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))) {
			return 29;
		}
		return LAST_DAY_OF_MONTH[month];
	}

	/**
	 * 在Date的基础上加 year年，month月，day日 处理过程为：1.在Date基础上加 year年 2.在加 year 年的基础上加
	 * month 月 3.在加 month 月的基础上加 day 天 <b>注：</b>此处为按年月日的顺序进行加减，与按日月年顺序进行处理的结果不一致
	 * 
	 * @param date
	 *            用于计算的基础日期
	 * @param year
	 *            加减的年数，负数表示减，正数表示加
	 * @param month
	 *            加减的月数，负数表示减，正数表示加
	 * @param day
	 *            加减的天数，负数表示减，正数表示加
	 * @return 计算后的日期，负数表示减，正数表示加
	 */
	public static String calculateDate(String date, int year, int month, int day) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			Date calDate = calculateDate(df.parse(date), year, month, day);
			return df.format(calDate);
		} catch (Exception e) {
			log.error("日期计算出错", e);
		}
		return "";
	}

	/**
	 * 在Date的基础上加 year年，month月，day日 处理过程为：1.在Date基础上加 year年 2.在加 year 年的基础上加
	 * month 月 3.在加 month 月的基础上加 day 天 <b>注：</b>此处为按年月日的顺序进行加减，与按日月年顺序进行处理的结果不一致
	 * 
	 * @param date
	 *            用于计算的基础日期
	 * @param year
	 *            加减的年数，负数表示减，正数表示加
	 * @param month
	 *            加减的月数，负数表示减，正数表示加
	 * @param day
	 *            加减的天数，负数表示减，正数表示加
	 * @return 计算后的日期，负数表示减，正数表示加
	 */
	public static Date calculateDate(Date date, int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 获取当前的日期和时间 yyy-MM-dd HH:mm:ss 格式
	 * @return
	 */
	public static String currDateAndTime() {
		DateFormat dtf = new SimpleDateFormat(DATE_FORMAT + " " + TIME_FORMAT);
		return dtf.format(Calendar.getInstance().getTime()).trim();
	}
	
	public static Timestamp currDateTime() throws Exception{
		Date today = new Date();
		SimpleDateFormat vFormat = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT + TIME_HHMMSS_FORMAT);
		String aValue = vFormat.format(today);
		Timestamp sDate = Timestamp.valueOf(currDate()+" "+currTime());
		return sDate;
	}
	

	/**
	 * 获取当前日期 yyyy-MM-dd 格式
	 * @return
	 */
	public static String currDate() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(Calendar.getInstance().getTime()).trim();
	}

	/**
	 * 获取当前时间 HH:mm:ss格式
	 * @return
	 */
	public static String currTime() {
		DateFormat tf = new SimpleDateFormat(TIME_FORMAT);
		return tf.format(Calendar.getInstance().getTime()).trim();
	}

	/**
	 * 比较第一个日期同第二个日期的比较值
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(String d1, String d2) throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return compare(df.parse(d1), df.parse(d2));
	}

	/**
	 * 比较第一个日期同第二个日期的比较值
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(Date d1, Date d2) {
		return d1.compareTo(d2);
	}

	
	
	//	将一个日期转化成Calendar   
    public  static Calendar   switchStringToCalendar(Date   date){   
            Calendar   c   =   Calendar.getInstance();   
            c.setTime(date);   
            return   c;   
    } 
    //取得某个时间后n个月的相对应的一天   
    public static  String   getNMonthAfterOneDay(Date sDate,int   n){   
            Calendar   c   =   switchStringToCalendar(sDate);   
            c.add(c.MONTH,n);   
            return   ""+c.get(c.YEAR)+"-"+(c.get(c.MONTH)+1)+"-"+c.get(c.DATE);   
  
    }
    
    
    /**
     * 
    * @Title: diffMonths 
    * @Description: 获取两个日期之间相差的自然月数
    * @param d1 日期参数一
    * @param d2	日期参数二
    * @param isRoundUp	不足一月时是否加一月
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author GIT-Sunny
    * @date 2013-5-24 下午12:14:41 
    * @version V1.0
     */
    public static int diffMonths(Date d1,Date d2 ,boolean isRoundUp ) {
    	int iMonth = 0;
    	//将小的日期放在前面
        if(d1.after(d2)){
        	Date tempDate = d1;
        	d1 = d2;
        	d2 = tempDate;
        }
    	Calendar c1 = Calendar.getInstance();      
        c1.setTime(d1);      
  
        Calendar c2 = Calendar.getInstance();      
        c2.setTime(d2);
        iMonth = Math.abs((c1.get(Calendar.YEAR)-c2.get(Calendar.YEAR))*12	//取两个日期相差年数*12
        		+(c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH)));	//取两个日期相差月数
    	if(isRoundUp && c1.get(Calendar.DAY_OF_MONTH) < c2.get(Calendar.DAY_OF_MONTH)){
    		iMonth = iMonth +1;
    	}
    	if(!isRoundUp && c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH)){
    		iMonth = iMonth -1;
    	}
		return iMonth;
	}
    
    /**
     * 
    * @Title: getMonths 
    * @Description: 去尾法计算两个日期相差自然月数(重构后) 
    * @param end	日期参数一
    * @param begin	日期参数二
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author GIT-Sunny
    * @date 2013-5-24 下午03:25:51 
    * @version V1.0
     */
    public static int getMonths(Date end,Date begin){
    	return diffMonths(end, begin, false); 
    }
    
    /**
     * 
    * @Title: getMonths2 
    * @Description: 进一法获取两个日期相差自然月数 (重构后)
    * @param end	日期参数一
    * @param begin	日期参数二
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author GIT-Sunny
    * @date 2013-5-24 下午03:24:09 
    * @version V1.0
     */
    public static int getMonths2(Date end, Date begin){
    	return diffMonths(end, begin, true);
    }
    
    /**
     * 
    * @Title: getDiffDays 
    * @Description: 取得两个日期相差天数 
    * @param beginDate
    * @param endDate
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author GIT-Sunny
    * @date 2012-10-16 下午07:41:22 
    * @version V1.0
     */
    public static int getDiffDays(Date beginDate, Date endDate){
    	if(beginDate.after(endDate)){//如果开始时间晚于结束时间，置换两个变量
    		Date mDate = beginDate;
    		beginDate = endDate;
    		endDate = mDate;
    	}
    	int iDays = 0;
    	Calendar date1 = Calendar.getInstance();      
        date1.setTime(beginDate);      
  
        Calendar date2 = Calendar.getInstance();      
        date2.setTime(endDate);     
        while(date1.before(date2)){
        	iDays++;
            date1.add(Calendar.DAY_OF_YEAR, 1);
        }
    	return iDays;
    }
    
    public static Calendar getCalendarForStr(String str){
    	if(str==null)
    		return null;
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar;
    }
    
    public static void main(String[] args) throws Exception {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//log.debug(compare("2007-7-4", "2007-07-05"));
//		DateHelper dhp=new DateHelper();
//		String tt="20081205";
//		//String ss= dhp.calculateDate(tt,0,0,1);
//		log.debug("============"+tt);
//		String dd=tt.substring(0, 4)+'-'+tt.substring(4, 6)+'-'+tt.substring(6, 8);
//		log.debug("============"+dd);
//		
//		log.debug("aaaa----------:"+dhp.getMonths2(getDate("2010-09-01 00:00:00.0"),getDate("2010-09-02 00:00:00.0")));
//		log.debug("currentDateTime---->"+dhp.currDateTime());
//		log.debug("{},{}",getDiffDays(getDate("2011-2-29"), getDate("2012-2-28")));
//		log.debug("当前时间："+formatTime());
//		
//		Date minDate = calculateDate(Calendar.getInstance().getTime(),-100,0,0);
//		Date maxDate = calculateDate(Calendar.getInstance().getTime(),100,0,0);
//		System.out.println(minDate);
//		System.out.println(maxDate);
//		System.out.println(getDate("1900-01-01").after(maxDate));
    	Calendar date1 = Calendar.getInstance();
    	Calendar date2 = Calendar.getInstance();
    	date1.setTime(sdf.parse("2012-03-03"));
    	date2.setTime(sdf.parse("2013-04-05"));
    	System.out.println(date1.get(Calendar.MONTH)-date2.get(Calendar.MONTH));
    	System.out.println("diffMonths:"+diffMonths(sdf.parse("2012-04-05"), sdf.parse("2012-04-07"), true));
	}
}
