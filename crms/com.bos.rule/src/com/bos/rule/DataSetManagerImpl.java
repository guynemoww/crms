package com.bos.rule;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eos.foundation.database.DatabaseExt;
import com.git.easyrule.data.set.DataSetManager;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class DataSetManagerImpl implements DataSetManager {

	private static Logger log = LoggerFactory.getLogger(DataSetManagerImpl.class);
	
	/**
	 * 规则执行SQL时，调用
	 */
	public Map<String,Object> loadDataSetBySql(String sql){
		log.debug("SQL:"+sql);
		
		Map<String,Object> map = null;
		try {
			Map<String,Object> pmap = new HashMap<String,Object>();
			pmap.put("sql", sql);
			Object[] users = (Object[]) DatabaseExt.queryByNamedSql("default",
					"com.bos.utp.tools.common.common_select", pmap);
			//转换DataObject对象为Map
			map =  convertDataObjectToMap((DataObject)users[0]);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("规则引擎查询SQL失败："+e);
		}
		return map;
	}
	
	/**
	 * 将一个DataObject类型对象转换成map对象
	 * @param obj
	 * @return
	 */
	public Map<String,Object> convertDataObjectToMap(DataObject obj){
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		for (int i=0; i<obj.getInstanceProperties().size(); i++){
			Property p = (Property) obj.getInstanceProperties().get(i);
			map.put(p.getName(), obj.getString(i));
		}
		
		return map;
	}
	
}
