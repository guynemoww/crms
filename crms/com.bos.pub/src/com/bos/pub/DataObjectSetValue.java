/**
 * 
 */
package com.bos.pub;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author ganquan
 * @date 2015-07-09 17:10:09
 *
 */
@Bizlet("DataObject赋值")
public class DataObjectSetValue {
	@Bizlet("赋值")
	public DataObject setValue(DataObject o,String column,String value) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("birthday".equals(column)){
			o.setDate(column,format.parse(value));
		}else{
			o.set(column, value);
		}
		return o;
	}

}
