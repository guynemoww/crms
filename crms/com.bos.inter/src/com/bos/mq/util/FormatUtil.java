/**
 * 
 */
package com.bos.mq.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bos.pub.GitUtil;
import com.eos.system.annotation.Bizlet;

/**
 * @author shenglong
 * @date 2014-06-19 09:49:59
 *
 */
@Bizlet("FormatUtil")
public class FormatUtil {
	/**
	 * 获得double的字符串表示，首先对_value按_len进行四舍五入截位，如果截位后小数点后位数小于_len，则使用0补齐。
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return double的字符串
	 */
	@Bizlet("formatDouble")
	public static String formatDouble(double _value, int _len)
			throws IllegalArgumentException {
//		String fStr = String.valueOf(roundDouble(_value, _len));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < _len; i++) {
			sb.append("0");
		}
		_value = new BigDecimal(_value).setScale(_len, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new DecimalFormat("#00.00").format(_value);
	}
	/**
	 * 参数检查
	 * @param _str
	 * @param _value
	 * @throws IllegalArgumentException
	 */
	private static void checkParamPositive(String _str, int _value)
	throws IllegalArgumentException {
		if (_value <= 0) {
	throw new IllegalArgumentException("参数:" + _str + "不能小于等于0");
		}
	}
	
	/**
	 * 判断期限类型
	 * @param startDate
	 * @param endDate
	 * @throws ParseException 
	 * @throws IllegalArgumentException
	 */
	@Bizlet("getLoanDtType")
	public static String getLoanDtType(String startDate,String endDate) throws ParseException
	{
		String temp;
		startDate=startDate.replaceAll("-", "");
		endDate=endDate.replaceAll("-", "");
		//统一转换为yyyyMMdd格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();    
		cal.setTime(sdf.parse(startDate));    
		long time1 = cal.getTimeInMillis();                 
		cal.setTime(sdf.parse(endDate));    
		long time2 = cal.getTimeInMillis();         
		long between_days=(time2-time1)/(1000*3600*24);  
		//放款期限类型计算规则：
		//1、本次放款到期日-本次放款起始日 >5年，则放款期限类型为：长期（LT）
		//2、本次放款到期日-本次放款起始日 <=1年，则放款期限类型为：短期（ST）
		//3、其他放款期限类型为：中期（MT）
		if(between_days>1825)
			temp="LT";
		else if(between_days<=365)
			temp="ST";
		else
			temp="MT";
		return temp;
	}
	
	public static void main(String [] args) throws ParseException
	{
		getLoanDtType("2014-03-01","2014-010-01");  
		System.out.println(getLoanDtType("2014-03-01","2015-010-01"));
	}

}
