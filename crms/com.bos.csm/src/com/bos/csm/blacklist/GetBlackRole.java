/**
 * 
 */
package com.bos.csm.blacklist;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.eos.system.annotation.Bizlet;

/**
 * @author git
 * @date 2014-08-19 16:51:21
 *
 */
@Bizlet("获取操作黑名单客户的角色")
public class GetBlackRole {
	
	
	@Bizlet("获取操作黑名单客户的角色")
		public  static String GetOperateBalckRole(HashMap orgMap,String orgNum){
			String 	roleCd = "0";
		//获取岗位的map
			HashMap roleMap =  (HashMap) orgMap.get("org"+orgNum);
			Set keys = roleMap.keySet();
			//Iterator it = roleMap.keySet().iterator();
			//如果是总行的话，获取总行的权限
			if("10001".equals(orgNum)){
				if(keys.contains("P1001")){
					if(keys.contains("P1003")){
						//既存在客户经理岗也存在黑名单管理岗
						roleCd="1";
					}else{
						//只存在总行客户经理岗
						roleCd="2";
					}
				}else if(keys.contains("P1003")){
					//只存在黑名单客户管理岗
					roleCd="1";
				}
				
			}else{//非总行的客户经理岗
				if(keys.contains("P1001")){
					roleCd="2";
				}
			}
			return roleCd;
		
		}
}
