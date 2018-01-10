/*******************************************************************************
 * $Header: /scfcvs/ESCF/com.bos.intf/src/com/cs/scf/intf/DateUtil.java,v 1.3 2011/04/16 03:00:37 xulong Exp $
 * $Revision: 1.3 $
 * $Date: 2011/04/16 03:00:37 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2011-4-11
 *******************************************************************************/

package com.bos.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;

import com.eos.system.annotation.Bizlet;

/**
 * 对日期对象与指定格式的
 * 字符串之间转换的处理
 */
@Bizlet("DateUtil")
public class DateUtil {

	/**
	 * @return
	 * @author Administrator
	 */
	@Bizlet("获取系统日期")
	public String getCurDate(String pattern) {
		String cur = null;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		cur = df.format(date);
		return cur;
	}

	/**
	 * @param date
	 * @return
	 * @author Administrator
	 * @throws ParseException 
	 */
	@Bizlet("")
	public String getDate(String cur, String pattern, int num)
			throws ParseException {
		String re = null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(cur);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);
		re = df.format(cal.getTime());
		return re;
	}
	
	
    /****************************************************************************************/
	/**
	 * 将当前日期转换为2008-08-08T20:00:00格式的日期字符串
	 * @return String 2008-08-08T20:00:00格式的日期字符串
	 */
	@Bizlet("")
	public static String getJMTCurrentDate() {
		return getTCurrentDate(new Date());
	}
	
	
	/**
	 * 将指定日期转换为2008-08-08T20:00:00格式的日期字符串
	 * @param date - 指定日期
	 * @return String 2008-08-08T20:00:00格式的日期字符串
	 */
	@Bizlet("")
	public static String getTCurrentDate(Date date) {
        String dateStr = convertDate2String(getCurrentDate(), DEFAULT_DATETIME_FORMAT);
        dateStr = dateStr.substring(0, 10)+"T"+dateStr.substring(11, 19);
        return dateStr;
	}
	
	
	/**
	 * 取得当前日期
	 * @return Date 当前日期
	 */
	@Bizlet("")
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回当前日期对应的默认格式的字符串
	 * @return String 当前日期对应的字符串
	 */
	@Bizlet("")
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 返回当前日期对应的指定格式的字符串
	 * @param dateFormat - 日期格式
	 * @return String 当前日期对应的字符串
	 */
	@Bizlet("")
	public static String getCurrentStringDate(String dateFormat) {
		return convertDate2String(getCurrentDate(), dateFormat);
	}
	
	/**
	 * 返回指定日期字符串对应的指定格式的字符串
	 * @param dateString - 指定日期字符串
	 * @param dateFormat - 日期格式
	 * @return String 指定日期字符串对应的字符串
	 */
	@Bizlet("")
	public static String getDateFormatString(String dateString,String dateFormat) {
		Date date = null;
		try {
			date = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}   
		return new SimpleDateFormat(dateFormat).format(date);  
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * @param date - 要转换的日期
	 * @param dateFormat - 日期格式
	 * @return String 日期对应的字符串
	 */
	@Bizlet("")
	public static String convertDate2String(Date date, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			}
			catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		}
		else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}

	/**
	 * 将字符串转换成默认格式日期
	 * @param stringDate - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	@Bizlet("")
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 将加贸通报文中的日期字符串转换成默认格式日期字符串
	 * @param stringDate - 要转换的字符串格式的日期
	 * @param dateFormat - 目标日期的字符串格式
	 * @param type - 返回的对象(S:字符串;D:日期)
	 * @return Date 字符串对应的日期
	 */
	@Bizlet("")
	public static Object convertJMTString2Date(String stringDate,String dateFormat,String type) {		
		stringDate = stringDate.replace("T", " ");
		if("".equals(dateFormat)||dateFormat == null){
			dateFormat = DEFAULT_DATETIME_FORMAT;
		}
		if("S".equals(type)){
			return getDateFormatString(stringDate,dateFormat);  
		}else{
			return convertString2Date(stringDate,dateFormat);
		}		
	}

	/**
	 * 将字符串转换成指定格式日期
	 * @param stringDate - 要转换的字符串格式的日期
	 * @param dateFormat - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	@Bizlet("")
	public static Date convertString2Date(String stringDate, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			}
			catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		}
		else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		try {
			return sdf.parse(stringDate);
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			return new Date(System.currentTimeMillis());
		}
	}
	/**
	 * 将字符串转换成指定格式日期
	 * @param stringDate - 要转换的字符串格式的日期
	 * @param dateFormat - 字符串对应的日期格式
	 * @return String 日期字符串
	 */
	@Bizlet("")
	public static String strToStrFormat(String str,String dateFormat)
	{
		Date tt=convertString2Date(str,dateFormat);
		String temp=convertDate2String(tt,DEFAULT_DATE_FORMAT);
		return  temp;
	}
	
	/**
	 * 日期加指定天数返回指定字符串日期
	 * @param String - 要添加的日期
	 * @param int - 添加的天数
	 * @return String 日期字符串
	 */
	@Bizlet("")
	public static String getSubDate(String date,int num){
		boolean flag = true;
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, num);
		Date toDate = cal.getTime();
		String temp=convertDate2String(toDate,DEFAULT_DATE_FORMAT);
		return temp;
	}
	
	/**
	 * 生成借据清单的版本号
	 * @param flag
	 * @param date
	 * @param ver
	 * @return
	 */
	@Bizlet("")
    public static String getVerify(String flag, String date, String ver)
    {
        int sum = 0;
        String inputDate = date.replaceAll("-", "");
        try
        {
            for(int i = 0; i < inputDate.length(); i++)
                sum += Integer.parseInt(String.valueOf(inputDate.charAt(i)));

        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return (new StringBuilder(String.valueOf(flag))).append(inputDate).append(sum % 10).append(ver).toString();
    }

	/**
	 * 默认的日期格式
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
	public static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	
	public static String TIME_HHMMSS_FORMAT = "HHmmss";
	
	
	public static void main(String[] args) {
		//String now = getDateFormatString("2012-12-12 00:00:00","yyyyMMdd");
		//String now = "2018-08-08T20:00:00";
		//System.out.println(DateUtil.convertJMTString2Date(now,"yyyy-MM-dd HH:mm:ss","D"));

		String str="20130703";
		String tt=strToStrFormat(str,"yyyyMMdd");
		String aa=getSubDate(tt,35);
		System.out.println(tt);
		System.out.println(aa);
	}

	/**
	 * 获取月初日期
	 * @param date操作日期，null则取系统默认
	 * @param diff天数增减值
	 * @return
	 */
	@Bizlet("获取月初日期")
	public static String getFirstDayOfMonth(Date date, int diff) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		if(date != null){
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, diff);
		String first = format.format(cal.getTime());
		return first;
	}

	/**
	 * 获取月末日期
	 * @param date操作日期，null则取系统默认
	 * @param diff天数增减值
	 * @return
	 */
	@Bizlet("获取月末日期")
	public static String getLastDayOfMonth(Date date, int diff) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		if(date != null){
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.add(Calendar.DAY_OF_MONTH, diff);
		String last = format.format(cal.getTime());
		return last;
	}
	
	public static String formatTime(){
		DateFormat stf = new SimpleDateFormat(TIME_HHMMSS_FORMAT);
		return stf.format(Calendar.getInstance().getTime());
	}
}
