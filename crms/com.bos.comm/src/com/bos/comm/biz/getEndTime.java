package com.bos.comm.biz;

import java.util.Calendar;
import java.util.Date;

import com.eos.system.annotation.Bizlet;

@Bizlet("")
public class getEndTime {
	/*
	 * 期限单位 01-年 02-半年 03-季 04-月 05-日
	 */
	@Bizlet("传入参数：当前时间，期限,期限单位   返回 到期日期")
	public static Date getEndTime(Date dt, int limit, String limitType)
			throws Exception {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		if (limitType.equals("01")) {
			rightNow.add(Calendar.YEAR, limit);
			return rightNow.getTime();
		} else if (limitType.equals("02")) {
			rightNow.add(Calendar.MONTH, limit * 6);
			return rightNow.getTime();
		} else if (limitType.equals("03")) {
			rightNow.add(Calendar.MONTH, limit * 3);
			return rightNow.getTime();
		} else if (limitType.equals("04")) {
			rightNow.add(Calendar.MONTH, limit);
			return rightNow.getTime();
		} else if (limitType.equals("05")) {
			rightNow.add(Calendar.DAY_OF_YEAR, limit);
			return rightNow.getTime();
		} else {
			throw new Exception("期限单位为空");
		}

	}
//	public static void main(String args[]){
//		Date dt=new Date();
//		try {
//			System.out.println("time:"+getEndTime(dt,4,"05"));
//		} catch (Exception e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//	}
}
