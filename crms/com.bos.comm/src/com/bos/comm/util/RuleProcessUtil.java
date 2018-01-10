package com.bos.comm.util;

import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;

/**
 * 规则处理工具类
 * @author lijianfei
 *
 */
public class RuleProcessUtil {

	
	/**
	 * 初始化转授权所需要的参数
	 * @param bizId 业务规则
	 * @return
	 */
	public static Map<String,String> initAuthorizationDatas(Map<String,String> temp){
		
		Map<String,String> rtMap = new HashMap<String,String>();
		
		Object [] obj = DatabaseExt.queryByNamedSql("default","com.bos.dataset.bizFlowGrant.bizFlowGrant",temp);
		
		if(null != obj && obj.length>0){
			
			rtMap = (Map<String,String>)obj[0];
		}
		
		return rtMap;
	}
	
	/**
	 * 根据申请金额，获取零权限对应的岗编号，审批官级别、机构层级
	 * @param temp
	 * @return
	 */
	public static Map<String,String>  getZeroGrantData(Map<String,String> temp){
		
		Map<String,String> rtMap = new HashMap<String,String>();
		
		Object [] obj = DatabaseExt.queryByNamedSql("default","com.bos.dataset.bizFlowGrant.bizFlowZeroGrant",temp);
		
		if(null != obj && obj.length>0){
			
			rtMap = (Map<String,String>)obj[0];
		}
		
		return rtMap;
	}
}
