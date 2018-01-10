package com.bos.bps.op;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

public class DisposeTool {
	/**
	 * 在代理管理中例外流程添加,实现分页的处理
	 */
	@Bizlet("在代理管理中例外流程添加,实现分页的处理")
	public Map[] getExceptionProcess(Map<String,String>[] items,Map<String,Integer> page){
		 int begin=page.get("begin");
		 int length=page.get("length");
		
		 int mapslen= items.length<=length?items.length:items.length-begin>length?length:items.length-begin;
		 Map[] maps=new Map[mapslen];
		 int len=begin+length>items.length?items.length:begin+length;
		 for(int i=begin;i<len;i++){
			 maps[i-begin]=items[i];
		 }
		return maps;
	}
	
	/**
	 * 根据客户经理号，获取营销团队负责人
	 * @param userid
	 * @return
	 */
	public String getSaleTeamerByUserId(String userid){
		
		Object [] items=DatabaseExt.queryByNamedSql("default","com.bos.bps.dataset.query.getSaleTeamerByUserId",userid);
		if(null != items && items.length>0){
			
			Map  temp = (Map)items[0];
			
			return temp.get("USERID")+"_"+temp.get("EMPNAME")+"_"+temp.get("ORGCODE");
		}else{
			return null;
		}
		
	}
	
	/**
	 * 提供一个岗位id和机构id 查询岗位下所有人
	 * @param posicode 岗位id
	 * @param orgcode 机构id
	 */
	@Bizlet("提供一个岗位id和机构id 查询岗位下所有人")
	public Object [] getUserIdName(String posicode,String orgcode,String orglevel){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("posicode", posicode);
		map.put("orgcode", orgcode);
		map.put("orglevel", orglevel);
		Object [] items=DatabaseExt.queryByNamedSql("default","com.bos.bps.dataset.query.getUserIdName",map);
		return items;
	}
	/**
	 * 提供一个岗位id和机构id 查询岗位下所有人，拼字符串 XXX_XX_XX|XXX_XX_XX
	 * @param posicode 岗位id
	 * @param orgcode 机构id
	 * @param orglevel 岗位级别
	 * @return 查询岗位下所有人，拼字符串 XXX_XX_XX|XXX_XX_XX"
	 */
	@Bizlet("提供一个岗位id和机构id 查询岗位下所有人，拼字符串 XXX_XX|XXX_XX")
	public String getUserIdNameStr(String posicode,String orgcode,String orglevel){
		DisposeTool dt=new DisposeTool();
		Object [] items=dt.getUserIdName(posicode, orgcode, orglevel);
		String retStr="";
		for (Object object : items) {
			Map<String,String> map=(HashMap<String,String>) object;
			retStr+=map.get("USERID")+"_"+map.get("OPERATORNAME")+"_"+map.get("DEPARTCODE")+"|";
		}
		return retStr;
	}
	/**
	 * 用于把传入的单个对象 封装成 数组对象返回
	 * @param object 传入对象
	 * @param Object[] 接受对象数组
	 * @return
	 */
	@Bizlet("用于把传入的单个对象 封装成 数组对象返回")
	public Object[] getObjects(Object object,Object[] objects){
		Object [] obj=new Object[objects.length+1];
		for (int i = 0; i <objects.length ; i++) {
			obj[i]=objects[i];
		}
		obj[obj.length-1]=object;
		return obj;
	}
	/**
	 * 用于把数组下标0去除掉
	 * @param objects 传入对象数组
	 * @return 对象数组
	 */
	@Bizlet("用于把数组下标0去除掉")
	public Object[] delIndex0(Object[] objects){
		Object [] obj=new Object[objects.length-1];
		for (int i = 1; i < objects.length; i++) {
			obj[i-1]= objects[i];
			
		}
		return obj;
	}
	/**
	 * 用于分割字符串返回字符串数组
	 * @param str 数据字符串
	 * @param splitstr 分割字符
	 * @return 字符串数组
	 */
	@Bizlet("用于分割字符串返回字符串数组")
	public String [] getStrs(String str,String splitstr){
		String strs[]=str.split(splitstr);
		return strs;
	}
	/**
	 * 用于把两个list合并一个list
	 * @param list 接收list
	 * @param listvo 传入list
	 * @return list
	 */
	@Bizlet("用于把两个list合并一个list")
	public List<Object> getLists(List<Object> list,List<Object> listvo){
		if(list==null){
			List<Object> lists= new ArrayList<Object>();
			for (Object object : listvo) {
				lists.add(object);
			}
			return lists;
		}			
		for (Object object : listvo) {
			list.add(object);
		}
		return list;
	}
	/**
	 * 用于把list放入map组装treegrid数据类型
	 * @param maps 接收map
	 * @param listvo 传入list
	 * @return list
	 */
	@Bizlet("用于把list放入map组装treegrid数据类型")
	public List<Map<String,Object>> getMapTreeGrid(List<Map<String,Object>> maps,List<Object> listvo){
		if(maps==null){
			maps=new ArrayList<Map<String,Object>>();
		}
		int maplen=maps.size();
		Boolean bl=true;
		int ii=0;
		for (int i=0;i<listvo.size();i++) {
			Map<String,Object> map=(Map<String,Object>)listvo.get(i);
			if(bl){
				Map<String,Object> m=new HashMap<String, Object>();
				m.put("ParentTaskUID",-1);
				String defname=(String)map.get("processDefName");
				String [] defnames=defname.split("\\.");
				m.put("type",defnames[defnames.length-2]);
				m.put("UID",maplen==0?1+ii:maplen+ii);
				maps.add(m);
				i--;
				bl=false;
			}else{
				map.put("ParentTaskUID",maplen==0?1:maplen);
				map.put("UID",maplen==0?1+ii:maplen+ii);
				maps.add(map);
			}
			ii++;
		}
		return maps;
	}
}
