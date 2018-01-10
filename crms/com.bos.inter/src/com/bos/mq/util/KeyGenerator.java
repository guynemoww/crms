package com.bos.mq.util;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

public class KeyGenerator{
    private static Logger log = Logger.getLogger(KeyGenerator.class);
	
	/**
	 * 
	* @Title: genTransFlowNo 
	* @Description: 生成流水号
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author GIT-Sunny
	* @date 2012-9-5 上午11:57:50 
	* @version V1.0
	 */
	public synchronized static String genTransFlowNo() {
		Calendar cal = Calendar.getInstance();
		return Integer.toString(cal.get(Calendar.HOUR_OF_DAY) * 10000 + cal.get(Calendar.MINUTE) * 100 + cal.get(Calendar.SECOND));
	}
	
	/**
	 * 
	* @Title: genTransFlowNo 
	* @Description: 生成流水号
	* @param i
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author GIT-Sunny
	* @date 2012-9-5 上午11:57:32 
	* @version V1.0
	 */
	public synchronized static String genTransFlowNo(int i) {
		Calendar cal = Calendar.getInstance();
		return Integer.toString(250000 + cal.get(Calendar.MINUTE) * 100 + cal.get(Calendar.SECOND)+i);
	}
	
	/**
	 * 获取UUID
	 * 
	 * @Title: getUUID
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-8-31 下午03:53:15
	 * @version V1.0
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();//.replace("-", "");
		return uuid;
	}
	
	public static String genRandomNum(int length){
	    //35是因为数组是从0开始的，26个字母+10个数字
	    final int maxNum = 36;
	    int i; //生成的随机数
	    int count = 0; //生成的密码的长度
	    char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	    StringBuffer pwd = new StringBuffer("");
	    Random r = new Random();
	    while(count < length){
	     //生成随机数，取绝对值，防止生成负数，
	   
	     i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
	   
	     if (i >= 0 && i < str.length) {
	      pwd.append(str[i]);
	      count ++;
	     }
	    }

	    return pwd.toString();
	 }

	public static void main(String[] args) {
		log.debug(Integer.parseInt(genTransFlowNo()));
		log.debug(genRandomNum(21));
	}
	

}
