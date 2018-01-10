package com.bos.utp.auth.skin;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bos.utp.ABFConfigKey;

import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.utility.StringUtil;

/**
 * 实现从portalskin转为外部
 * 
 * @author charles (mailto:caisy@primeton.com)
 */
/*
 * Modify history $Log: OneAppPortalSkinImpl.java,v $
 * Modify history Revision 1.3  2010/12/29 08:43:36  caisy
 * Modify history Update:oneAppSkinImpl 优化
 * Modify history
 * Modify history Revision 1.2  2010/12/29 05:58:06  caisy
 * Modify history
 * Modify history Revision 1.1  2010/12/24 20:03:11  caisy
 * Modify history 包重构到com.bos.utp.auth.skin
 * Modify history
 * Modify history Revision 1.2  2010/12/21 03:27:16  caisy
 * Modify history 增加换肤功能
 * Modify history
 * Modify history Revision 1.1  2010/12/20 07:10:13  caisy
 * Modify history 初始化版本
 * Modify history
 */
public class OneAppPortalSkinImpl extends
		DefaultSkinImpl {
     private static  Class cache = null;
     private static Method method = null;
     
     public OneAppPortalSkinImpl(){
    	 try{
 			cache = Class.forName("com.primeton.widget.cache.CacheUtil");
 			Class[] paramTypes = new Class[1];
 			paramTypes[0] = HttpSession.class;
 			 method = cache.getMethod("getUserStyle", paramTypes);		
 			}catch (Exception e) {
 				LogUtil.logWarn("未找到!com.primeton.widget.cache.CacheUtil类和getUserStyle方法", null, (Object) null);
 			}
     }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bos.utp.auth.ISkinConvertor#getLayout()
	 */
	public String getLayout(HttpServletRequest request) {
		return "default";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bos.utp.auth.ISkinConvertor#getOutsideName(java.lang.String)
	 */
	public String getStyle(HttpServletRequest request) {
		/**
		 * 先读取参数 再判断portal session 再 session
		 */
		String style = request.getParameter(ABFConfigKey.SKIN_PARAM_NAME.getConfigValue());
		if (StringUtil.isNullOrBlank(style)) {			
			style =getUserStyle(request);
			if (StringUtil.isNullOrBlank(style)) {
				style = super.getStyle(request);
			}
		}
		if(StringUtil.isNullOrBlank(style)){
			style = ABFConfigKey.SKIN_DEFAULT_STYLE.getConfigValue();
		}
		return "default".equals(style)?"blue":style;
	}
	private static String getUserStyle(HttpServletRequest request){
		String style= null;
		if(cache!=null&&method!=null){
			try {
				style = (String) method.invoke(cache, new Object[]{request.getSession()});
			} catch (Exception e) {
				LogUtil.logError("获取portal的style!", e, (Object) null);
			} 	
		}			
		 return style;		
	}
}
