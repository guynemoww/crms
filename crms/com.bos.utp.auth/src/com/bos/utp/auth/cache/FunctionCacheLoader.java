package com.bos.utp.auth.cache;

import java.util.HashMap;
import java.util.Map;


import com.bos.utp.tools.DBUtil;
import com.eos.common.cache.ICacheLoader;
import com.eos.foundation.eoscommon.LogUtil;

/**
 * 
 * 功能缓存，作为判断功能是否加已被定义
 * 
 * 
 * @author 蔡述尧 caisy (mailto:caisy@primeton.com)
 */

public class FunctionCacheLoader implements ICacheLoader {
	public FunctionCacheLoader() {
		super();
	}

	/**
	 * 从数据源中加载对应数据到缓存<BR>
	 * 
	 */
	public Object get(Object key) {		
		return null;		
		
	}

	/**
	 * 预加载数据在cache第一次调用get方法时触发
	 */
	@SuppressWarnings("unchecked")
	public Map preLoad() {
		HashMap ret = null;;
		Object[] objs = DBUtil.queryObjectsNamedSql( "com.bos.utp.auth.permission.loadFunctionCache",null);
		if(objs!=null&&objs.length>0){
			ret = new HashMap();
			for(int i=0;i<objs.length;i++){
				ret.put(((Map)objs[i]).get("FUNCACTION"), objs[i]);
			}
		}
		return ret;	

	}

	public void put(Object arg0, Object arg1) {

	}

	public Object remove(Object arg0) {
		return null;
	}

}
