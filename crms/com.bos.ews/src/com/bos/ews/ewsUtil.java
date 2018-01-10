package com.bos.ews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eos.system.annotation.Bizlet;

@Bizlet("getTimeDifference")
public class ewsUtil {

	@Bizlet("")
	public long dateTimeDifference(String date1, Date date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long days = 0;
		try {
			Date d1 = df.parse(date1);
			long diff = date2.getTime() - d1.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			days = day;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	@Bizlet("getFirstTwoString")
	public String getFirstTwoString(String str1) {
		String reslt;
		if(str1.length()==2){
			reslt=str1;
			
		}else{
		   reslt = str1.substring(0, 2);
	    }
		return reslt;
	}
}
