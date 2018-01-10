package com.bos.bps.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.EvalError;
import bsh.Interpreter;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IApplicationMap;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.ISessionMap;
import com.eos.data.datacontext.IUserObject;
/**
 * 
 * @ClassName: CommonUtil
 * @Description: TODO(公共服务类)
 * @author lijianfei
 * 
 */
public class CommonUtil {
	private static IApplicationMap appMap;



	/***************************************************************************
	 * 
	 * @Title: getIUserObject
	 * @Description: TODO(取得session中用户对象)
	 * @param
	 * @return 设定文件
	 * @return IUserObject 返回类型
	 * @throws
	 */

	public static IUserObject getIUserObject() {
		IUserObject user = null;
		IMUODataContext muo = getIMUODataContext();
		if(null!=muo){
			user = muo.getUserObject();
		}else{
			user = getSessionCtx().getUserObject();
		}
		
		return user;
	}

	/***************************************************************************
	 * 
	 * @Title: getIMUODataContext
	 * @Description: TODO(取得session中MUO对象)
	 * @param
	 * @return 设定文件
	 * @return IMUODataContext 返回类型
	 * @throws
	 */
	public static IMUODataContext getIMUODataContext() {
		IMUODataContext muo = DataContextManager.current().getMUODataContext();
		return muo;
	}
	
	/***************************************************************************
	 * 
	 * @Title: getIMUODataContext
	 * @Description: TODO(取得session中MUO对象)
	 * @param
	 * @return 设定文件
	 * @return IMUODataContext 返回类型
	 * @throws
	 */
	public static ISessionMap getSessionCtx() {
		ISessionMap sessionMap = DataContextManager.current().getSessionCtx();
		return sessionMap;
	}

	
	/***************************************************************************
	 * 
	 * @Title: getApplicationCtx
	 * @Description: TODO(取得全局session容器（Application级）)
	 * @param
	 * @return 设定文件
	 * @return IApplicationMap 返回类型
	 * @throws
	 */
	public static IApplicationMap getApplicationCtx() {

		if (appMap == null) {

			appMap = DataContextManager.current().getApplicationCtx();
		}

		return appMap;

	}

	/***************************************************************************
	 * 
	 * @Title: getUUID
	 * @Description: TODO(通过UUID形式获取主键)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
	
	
	
	/**************************************************************************
	 * 判断表达式是否成立
	 * @param complexExpression
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static boolean checkComplexExpression(String complexExpression,
			Map<String, Object> map) throws Exception {

		Boolean flag;
		try {
			flag = (Boolean)eval(complexExpression, map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}

		return flag.booleanValue();
	}
	
	
	
	
	/*************************************************************************
	 * 表达式计算
	 * @param expr
	 * @return
	 */
	public static String[] evalKey(String expr) {
		String[] arr = new String[0];
		try {
			List<String> list = new ArrayList<String>();
			Pattern pa = Pattern.compile("([a-zA-Z][a-zA-Z0-9_]+)");
			Matcher m = pa.matcher(expr);
			while (m.find()) {
				// System.out.println(m.group());
				list.add(m.group());
			}
			arr = list.toArray(new String[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	}
	

	/*************************************************************************
	 * 表达式计算
	 * @param expr
	 * @param varMap
	 * @return
	 * @throws Exception
	 */
	public static Object eval(String expr, Map varMap) throws Exception {
		Interpreter bsh = new Interpreter();
		Object obj = null;
		if (varMap == null || null == expr || "".equals(expr)) {
			return null;
		}

		Iterator it = varMap.keySet().iterator();
		try {
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = varMap.get(key);
				setValue(bsh, key, value);
			}
			obj = bsh.eval(expr);

			// 返回变量
			return obj;
		}

		catch (Exception ex) {
			throw ex;
		}
	}
	
	/*************************************************************************
	 * 设置表达式值
	 * @param bsh
	 * @param key
	 * @param value
	 * @throws EvalError
	 */
	private static void setValue(Interpreter bsh, String key, Object value)
	throws EvalError {
		if (value instanceof Boolean) {
			bsh.set(key, ((Boolean) value).booleanValue());
		} else if (value instanceof Byte) {
			bsh.set(key, ((Byte) value).byteValue());
		} else if (value instanceof Character) {
			bsh.set(key, ((Character) value).charValue());
		} else if (value instanceof Short) {
			bsh.set(key, ((Short) value).shortValue());
		} else if (value instanceof Integer) {
			bsh.set(key, ((Integer) value).intValue());
		} else if (value instanceof Long) {
			bsh.set(key, ((Long) value).longValue());
		} else if (value instanceof Float) {
			bsh.set(key, ((Float) value).floatValue());
		} else if (value instanceof Double) {
			bsh.set(key, ((Double) value).doubleValue());
		} else {
			bsh.set(key, value);
		}
	}
	
	/**
	 * 格式化岗位，去掉P_,_0?
	 * @return
	 */
	public static String formatPosition(String position){
		
		String postId = "";
		
		if(null != position && !"".equals(position)){
			
			if(position.indexOf(",")!=-1){
				
				String[] strs =  position.split(",");
				for (int i = 0; i < strs.length; i++) {
					String string = strs[i];
					if(string.indexOf("P_")!=-1){
						
						string = string.substring(string.indexOf("P_")+2,string.length());
					}
					if(string.lastIndexOf("_")!=-1){
						
						string = string.substring(0,string.lastIndexOf("_"));
					}
					if(i==strs.length-1){
						postId +="'"+string+"'";
					}else{
						postId +="'"+string+"',";
					}
					
				}
			}else{
				
				if(position.indexOf("P_")!=-1){
					
					position = position.substring(position.indexOf("P_")+2,position.length());
				}
				if(position.lastIndexOf("_")!=-1){
					
					position = position.substring(0,position.lastIndexOf("_"));
				}
				postId = "'"+position+"'";
			}
			
		}
		return postId;
	}
	
	/**
	 * 格式化部门过滤字段串，为其拼接上单引号，如：'01','02'
	 * @param department
	 * @return
	 */
	public static String formatDepartment(String department){
		
		StringBuffer sb = new StringBuffer();
		if(null != department && !"".equals(department)){
			
			if(department.indexOf(",")!=-1){
				
				String[] departs = department.split(",");
				for (int i = 0; i < departs.length; i++) {
					String departId = departs[i];
					if(i == departs.length-1){
						
						sb.append("'").append(departId).append("'");
					}else{
						sb.append("'").append(departId).append("',");
					}
				}
			}else{
				
				sb.append("'").append(department).append("'");
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 获取岗位，不带单引号，去掉P_,_0?
	 * @return
	 */
	public static String getPosition(String position){
		
		
		if(null != position && !"".equals(position)){
			
			if(position.indexOf("P_")!=-1){
				
				position = position.substring(position.indexOf("P_")+2,position.length());
			}
			if(position.lastIndexOf("_")!=-1){
				
				position = position.substring(0,position.lastIndexOf("_"));
			}
			
		}
		return position;
	}
	
	/**
	 * 格式化时间（yyyy-MM-dd hh:mm:ss）
	 * @param dateTime
	 * @return
	 */
	public static String formatDate(String dateTime){
		
		String date="";
		if(null != dateTime && !"".equals(dateTime)){
			
			if(dateTime.length()>=14){
				
				String ymd =dateTime.substring(0,4)+"-"+dateTime.substring(4,6)+"-"+dateTime.substring(6,8);
				String hms = dateTime.substring(8,10)+":"+dateTime.substring(10,12)+":"+dateTime.substring(12);
				date = ymd+" "+hms;
			}
		}
		return date;
	}
	
	/**
	 * 把两个key跟value对应的数组转换成Map
	 * @param keys
	 * @param values
	 * @return
	 */
	public static Map<String,Object> stringArrayToMap(String [] keys,String [] values){
		
		Map<String,Object> temp = new HashMap<String,Object>();
		if(null != keys && keys.length>0){
			
			for (int i = 0; i < values.length; i++) {
				String key = keys[i];
				temp.put(key, values[i]);
			}
			
		}
		return temp;
	}
	
	/**
	 * 通过两个数据，获取以"_"连接，以"|"分隔的字符串
	 * @param userId
	 * @param userName
	 * @return
	 */
	public static String getStrBySplitWithArray(String[] userId,String[] userName){
		
		StringBuffer str = new StringBuffer();
		if(null!=userId&&userId.length>0 && null!=userName&&userName.length>0){
			
			for (int i = 0; i < userId.length; i++) {
				String userid = userId[i];
				if(i==userId.length-1){
					
					str.append(userid).append("_").append(userName[i]);
				}else{
					
					str.append(userid).append("_").append(userName[i]).append("|");
				}
			}
		}
		return str.toString();
	}
}

