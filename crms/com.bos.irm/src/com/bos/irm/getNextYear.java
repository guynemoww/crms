package com.bos.irm;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.eos.system.annotation.Bizlet;

public class getNextYear {
	

	/**
	 * @return
	 */
	@Bizlet("获取当前日期下一年日期")
	public static java.util.Date getDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		Date date = c.getTime();
		return date;
	}
	@Bizlet("获取有效截止日期后N月")
	public static java.util.Date getDate(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, n);
		Date date = c.getTime();
		return date;
	}
	
	@Bizlet("获取有效截止日期后N天")
	public static java.util.Date getNDate(int n,Date date) {
		Calendar   calendar   =   new GregorianCalendar(); 
	    calendar.setTime(date); 
	    calendar.add(calendar.DATE,n);//把日期往后增加n天.整数往后推,负数往前移动 
	    date=calendar.getTime();   
		return date;
	}
	@Bizlet("获取某个时间的下一年")
	public static Date getDateAfterDay(Date d) {
	    Calendar now = Calendar.getInstance();
	    now.setTime(d);
	    now.set(Calendar.YEAR, now.get(Calendar.YEAR) + 1);
	    return now.getTime();
	}
	@Bizlet("获取某个时间的18月")
	public static Date getDateAfterMonth(Date d) {
	    Calendar now = Calendar.getInstance();
	    now.setTime(d);
	    now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 18);
	    return now.getTime();
	}
	@Bizlet("俩个时间比大小")
	public static Date dateSmaller(Date d1 , Date d2) {
		if(d1.after(d2)){
	    	return d2;
	    }else{
	    	return d1;
	    }
	}
	@Bizlet("俩个时间比大小2")
	public static int dateSmaller2(Date d1 , Date d2) {
		if(d1.after(d2)){
	    	return 1;
	    }else{
	    	return 2;
	    }
	}
}
