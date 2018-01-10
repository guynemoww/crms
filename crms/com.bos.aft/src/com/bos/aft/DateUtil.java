package com.bos.aft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eos.system.annotation.Bizlet;

@Bizlet("")
public class DateUtil {

	@Bizlet("获取当前日期月份")
	public static int getDateMm(Date a) {
		int mm;
		mm = a.getMonth();
		return (mm + 1);
	}

	@Bizlet("获取当前日期年份")
	public static int getDateYY(Date a) {
		int mm;
		mm = a.getYear();
		return mm;
	}

	@Bizlet("获取当前日期天数")
	public static int getDateDD(Date a) {
		int mm;
		mm = a.getDay();
		return mm;
	}

	@Bizlet("获取时间差")
	public long dateTimeDifference(String date1, Date date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long days = 0;
		try {
			Date d1 = df.parse(date1);
			long diff = date2.getTime() - d1.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			long reslt=day%365;
			if(reslt<180){
				days=day/365;
			}else{
				days=day/365+1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

}
