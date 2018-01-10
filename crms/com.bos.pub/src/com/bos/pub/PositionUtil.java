/**
 * 
 */
package com.bos.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

/**
 * @author lijf
 * @date 2014-09-16 09:44:04
 *
 */
@Bizlet("岗位处理工具类")
public class PositionUtil {
	public static  TraceLogger logger = new TraceLogger(PositionUtil.class);
	/**
	 * 根据机构id,校验该机构是否配置了所需岗位，0未配置，1,2,3配置
	 * @param rId  如：r_P1001
	 * @param orgid 创建机构
	 * @return
	 */
	@Bizlet("根据机构id,规则ID,校验该机构是否配置了所需岗位")
	public String checkPositionExist(String rId,String orgcode){
		
		String ret = "0";//默认未配置
		if(null!=rId && ""!=rId && !"null".equals(rId)&&rId.indexOf("_")!=-1){
			//通过规则ID,获取要判断的岗位
			String[] rules = rId.split("_");
			String posicode = rules[1];
			//String orglevel = rules[2];
			Map<String,String> temp = new HashMap<String,String>();
			temp.put("posicode", posicode);
			temp.put("orgcode", orgcode);
			//stemp.put("orglevel", orglevel);
			logger.info("------------>传入的发起机构编号为："+orgcode+",规则ID为："+rId);
			Object[] obj = DatabaseExt.queryByNamedSql("default", "com.bos.pub.position.select_position_re",temp);
			if(null != obj && obj.length>0){
				temp = (Map<String,String>)obj[0];
				ret=String.valueOf(temp.get("ORGLEVEL"));
			}else{
				
				ret= "0";
			}
			
		}
		logger.info("------------>返回的岗位标识[isNotConf]值为："+ret);
		return ret;
	}
	
	@Bizlet("获取用户岗位编号")
	public static List<String> getPosicodeByUserNum(String userNum) {
		Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.pub.position.queryPositionByUserNum", userNum);
		List<String> list = new ArrayList<String>();
		for (Object obj : objs) {
			if (obj instanceof Map) {
				Map<String, String> map = (Map<String, String>) obj;
				list.add(map.get("POSICODE"));
			}
		}
		return list;
	}
}
