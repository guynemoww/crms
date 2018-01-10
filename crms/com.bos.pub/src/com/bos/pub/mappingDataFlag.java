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
 * @author kf_xdxt14
 * @date 2016-08-18 16:50:22
 *
 */
@Bizlet("")
public class mappingDataFlag {
	@Bizlet("")
	public static String tomapping(String sid,String source){
		if(source==null || source==""){
			return "0";
		}
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("source",source);
		map.put("sid",sid);
		String tager=null;
		Object[] tmp =  DatabaseExt.queryByNamedSql("default",
				"com.bos.pub.model.model.selectmappingItems", map);
		arr = new DataObject[tmp.length];
		 if(tmp.length==0){ return "0";}
		 else{return "11";}
	}
}
