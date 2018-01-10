/**
 * 
 */
package com.bos.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * @author yyh
 * @date 2015-12-24 15:04:03
 *
 */
@Bizlet("")
public class mappingData {
	@Bizlet("")
	public static String tomapping(String sid,String source){
		if(source==null || source==""){
			return null;
		}
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("source",source);
		map.put("sid",sid);
		String tager=null;
		Object[] tmp =  DatabaseExt.queryByNamedSql("default",
				"com.bos.pub.model.model.selectmappingItems", map);
		arr = new DataObject[tmp.length];
		 if(tmp.length==0){ return null;}
		for (int i = 0; i < tmp.length; i++) {
			arr[i]=(DataObject) tmp[i];
 		}
			if(arr!=null){
		tager= arr[0].getString("DICTNAME");
		}
		return tager;
	}

}
