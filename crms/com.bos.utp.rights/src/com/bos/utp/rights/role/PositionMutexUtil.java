/**
 * 
 */
package com.bos.utp.rights.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author ljf
 * @date 2015-08-01 18:17:23
 *岗位互斥处理
 */
@Bizlet("")
public class PositionMutexUtil {
	
	/**
	 * 岗位互斥校验
	 * @param posis
	 * @return
	 */
	@Bizlet("岗位互斥校验")
	public static String checkPositionMutex(String[] posis){
		String ret =null;
		
		List<String> list = Arrays.asList(posis);
		if(null != posis && posis.length>1){
			Map<String,String> map = new HashMap<String,String>();
			for (int i = 0; i < posis.length; i++) {
				String string = posis[i];
				map.put("positionid", string);
				Object[] obj = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.utp.rights.position.checkPositionMutex", map);
				if(null!=obj && obj.length>0){
					for (int j = 0; j < obj.length; j++) {
						Map temp = (Map)obj[j];
						String str = String.valueOf(temp.get("positionid"));
						if(list.contains(str)){
							
							DataObject p = DataObjectUtil.createDataObject("com.bos.utp.dataset.position.OmPosition");
							p.setInt("positionid", Integer.valueOf(string));
							DatabaseUtil.expandEntity(GitUtil.DEFAULT_DS_NAME, p);
							ret ="岗位："+p.getString("posiname")+" 与 "+temp.get("toMutexPosiname")+" 互斥，无法保存！";
							break;
						}
					}
				}
				if(null!=ret){
					
					break;
				}
			}
		}
		return ret;
	}

}
